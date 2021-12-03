package database;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import diffusion.packets.ChangeNicknamePacket;
import diffusion.packets.Packet;

import java.net.InetAddress;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class Database {
    private final BiMap<String, InetAddress> directory;
    private final BiMap<InetAddress, String> reverse_directory;
    private final Map<InetAddress, Boolean> connected;
    private final Connection database;
    private String nickname;
    private final List<DatabaseObserver> observers;

    public BiMap<String, InetAddress> getDirectory() {
        return this.directory;
    }

    public BiMap<InetAddress, String> getReverseDirectory() {
        return this.reverse_directory;
    }

    public Map<InetAddress, Boolean> getConnected() {
        return this.connected;
    }

    public Database() throws SQLException {
        this.observers = new ArrayList<>();
        this.directory = HashBiMap.create();
        this.reverse_directory = this.directory.inverse();
        this.connected = new HashMap<>();
        this.database = DriverManager.getConnection("jdbc:sqlite:messages.sqlite3");
        this.nickname = null;
    }

    public void update(Packet packet) {
        switch (packet.getKind()) {
            case Connect -> {
                this.reverse_directory.remove(packet.getAddress());
                this.connected.put(packet.getAddress(), true);
            }
            case Disconnect -> {
                this.reverse_directory.remove(packet.getAddress());
                this.connected.put(packet.getAddress(), false);
            }
            case ChangeNickname -> {
                if(!this.connected.getOrDefault(packet.getAddress(), false)) {
                    this.connected.put(packet.getAddress(), true);
                }
                if (!this.directory.containsKey(((ChangeNicknamePacket) packet).getNickname())) {
                    this.directory.forcePut(((ChangeNicknamePacket) packet).getNickname(), packet.getAddress());
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
                        "`message` TEXT)");
                stmt.executeUpdate("INSERT INTO `migrations` VALUES (1)");
            case 1:
                stmt.executeUpdate("ALTER TABLE `messages` ADD COLUMN `date` DATE;");
                stmt.executeUpdate("INSERT INTO `migrations` VALUES (2)");
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
        this.notifyNewMessage(message);
    }

    private void notifyNewMessage(Message message) {
        for (DatabaseObserver observer:
                this.observers) {
            observer.on_message(message);
        }
    }

    public List<Message> getMessagesFor(String nickname) {
        List<Message> results = new ArrayList<>();
        try {
            PreparedStatement statement = this.database.prepareStatement("SELECT `from`, `to`, `message`, `date` " +
                    "FROM messages WHERE `from` = ? OR `to` = ?;");
            statement.setString(1, nickname);
            statement.setString(2, nickname);
            ResultSet request_result = statement.executeQuery();
            while (request_result.next()) {
                results.add(new Message(request_result.getString("from"), request_result.getString("to"), request_result.getString("message"), request_result.getDate("date")));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return results;
    }

    public synchronized List<Message> getMessagesFor(String nickname, Date since) {
        List<Message> results = new ArrayList<>();
        try {
            PreparedStatement statement = this.database.prepareStatement("SELECT `from`, `to`, `message`, `date` " +
                    "FROM messages WHERE (`from` = ? OR `to` = ?) AND `date` >= ? ORDER BY `date`;");
            statement.setString(1, nickname);
            statement.setString(2, nickname);
            statement.setDate(3, new java.sql.Date(since.getTime()));
            java.sql.Date a = new java.sql.Date(since.getTime());
            ResultSet request_result = statement.executeQuery();
            while (request_result.next()) {
                results.add(new Message(request_result.getString("from"), request_result.getString("to"), request_result.getString("message"), request_result.getDate("date")));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return results;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void receiveMessageFor(InetAddress from_address, Message message) {
        message.setFrom(this.reverse_directory.get(from_address));
        message.setTo(this.nickname);
        try {
            message.saveTo(this.database);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.notifyNewMessage(message);
    }

    public void addObserver(DatabaseObserver observer) {
        this.observers.add(observer);
    }
}
