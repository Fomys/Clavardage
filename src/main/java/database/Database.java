package database;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import diffusion.packets.ChangeNicknamePacket;
import diffusion.packets.Packet;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.*;
import java.util.Date;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.currentThread;

public class Database {
    static ReentrantLock lock = new ReentrantLock();
    private final BiMap<String, InetAddress> directory;
    private final BiMap<InetAddress, String> reverse_directory;
    private final Map<InetAddress, Boolean> connected;
    private final Connection database;
    private final List<DatabaseObserver> observers;
    private final List<DatabaseObserver> pending_observers;
    private String nickname;

    public Database() throws SQLException {
        this.observers = new ArrayList<>();
        pending_observers = new ArrayList<>();
        this.directory = HashBiMap.create();
        this.reverse_directory = this.directory.inverse();
        this.connected = new HashMap<>();
        this.database = DriverManager.getConnection("jdbc:sqlite:messages.sqlite3");
        this.nickname = null;
    }

    public BiMap<String, InetAddress> getDirectory() {
        return this.directory;
    }

    public BiMap<InetAddress, String> getReverseDirectory() {
        return this.reverse_directory;
    }

    public Map<InetAddress, Boolean> getConnected() {
        return this.connected;
    }

    public void update(Packet packet) throws IOException {
        switch (packet.getKind()) {
            case Connect -> {
                this.reverse_directory.remove(packet.getAddress());
                this.connected.put(packet.getAddress(), true);
            }
            case Disconnect -> {
                this.reverse_directory.remove(packet.getAddress());
                this.connected.put(packet.getAddress(), false);
                // TODO: envoyer la déconnection
            }
            case ChangeNickname -> {
                if (!this.connected.getOrDefault(packet.getAddress(), false)) {
                    this.connected.put(packet.getAddress(), true);
                }
                if (!this.directory.containsKey(((ChangeNicknamePacket) packet).getNickname())) {
                    if (this.directory.containsKey(((ChangeNicknamePacket) packet).getNickname())) {
                        this.notify_disconnect_user(((ChangeNicknamePacket) packet).getNickname());
                    }
                    this.directory.forcePut(((ChangeNicknamePacket) packet).getNickname(), packet.getAddress());
                    this.notify_connect_user(((ChangeNicknamePacket) packet).getNickname());
                }
            }
        }
    }


    public void applyMigrations() throws SQLException {
        Statement stmt = this.database.createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `migrations` (`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT);");
        ResultSet result = stmt.executeQuery("SELECT MAX(`id`) FROM 'migrations';");
        int last_migration = result.getInt(1);
        switch (last_migration) {
            case 0:
                stmt.executeUpdate("CREATE TABLE `messages` (" +
                        "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                        "`from` TEXT, " +
                        "`to` TEXT, " +
                        "`message` TEXT);");
                stmt.executeUpdate("INSERT INTO `migrations` VALUES (1);");
            case 1:
                stmt.executeUpdate("ALTER TABLE `messages` ADD COLUMN `date` DATE;");
                stmt.executeUpdate("INSERT INTO `migrations` VALUES (2);");
            case 2:
                stmt.executeUpdate("ALTER TABLE `messages` ADD COLUMN `uuid` CHAR(36);");
                stmt.executeUpdate("INSERT INTO `migrations` VALUES (3);");
            default:
        }
    }

    public void sendMessageTo(InetAddress dest_address, Message message) {
        message.setTo(this.reverse_directory.get(dest_address));
        message.setFrom(this.nickname);
        try {
            message.saveTo(this.database);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.notify_new_message(message);
    }

    synchronized private void notify_new_message(Message message) {
        this.update_observers();
        for (DatabaseObserver observer :
                this.observers)
            observer.on_message(message);
    }

    synchronized private void notify_disconnect_user(String username) {
        this.update_observers();
        for (DatabaseObserver observer :
                this.observers)
            observer.on_disconnect_user(username);
    }

    synchronized private void notify_change_username(String nickname) {
        this.update_observers();
        for(DatabaseObserver observer : this.observers) {
            observer.on_change_nickname(nickname);
        }
    }

    private void update_observers() {
        while (this.pending_observers.size() != 0) {
            this.observers.add(this.pending_observers.remove(0));
        }
    }

    synchronized private void notify_connect_user(String username) throws IOException {
        this.update_observers();
        for (DatabaseObserver observer :
                this.observers)
            observer.on_connect_user(username);
    }

    public List<Message> getMessagesFor(String nickname) {
        List<Message> results = new ArrayList<>();
        try {
            PreparedStatement statement = this.database.prepareStatement("SELECT `uuid`, `from`, `to`, `message`, `date` " +
                    "FROM messages WHERE `from` = ? OR `to` = ?;");
            statement.setString(1, nickname);
            statement.setString(2, nickname);
            ResultSet request_result = statement.executeQuery();
            while (request_result.next()) {
                results.add(new Message(
                        request_result.getString("from"),
                        request_result.getString("to"),
                        request_result.getString("message"),
                        request_result.getDate("date"),
                        UUID.nameUUIDFromBytes(request_result.getBytes("uuid"))
                ));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return results;
    }

    public List<Message> getMessagesFor(String nickname, Date since) {
        List<Message> results = new ArrayList<>();
        try {
            PreparedStatement statement = this.database.prepareStatement("SELECT `uuid`, `from`, `to`, `message`, `date` " +
                    "FROM messages WHERE (`from` = ? OR `to` = ?) AND `date` >= ? ORDER BY `date`;");
            statement.setString(1, nickname);
            statement.setString(2, nickname);
            statement.setDate(3, new java.sql.Date(since.getTime()));
            ResultSet request_result = statement.executeQuery();
            while (request_result.next()) {
                results.add(new Message(
                        request_result.getString("from"),
                        request_result.getString("to"),
                        request_result.getString("message"),
                        request_result.getDate("date"),
                        UUID.nameUUIDFromBytes(request_result.getBytes("uuid"))
                ));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return results;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
        this.notify_change_username(this.nickname);
    }

    public void receiveMessageFor(InetAddress from_address, Message message) {
        message.setFrom(this.reverse_directory.get(from_address));
        message.setTo(this.nickname);
        try {
            message.saveTo(this.database);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.notify_new_message(message);
    }

    synchronized public void addObserver(DatabaseObserver observer) {
        this.pending_observers.add(observer);
    }
}
