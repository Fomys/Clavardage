package database;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import diffusion.packets.ChangeNicknamePacket;
import diffusion.packets.ChangeUUIDPacket;
import diffusion.packets.Packet;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Database {
    static ReentrantLock lock = new ReentrantLock();
    private final BiMap<UUID, InetAddress> directory;
    private final BiMap<InetAddress, UUID> reverse_directory;
    private final Map<InetAddress, Boolean> connected;
    private final BiMap<UUID, String> nicknames;

    private final Connection connection;
    private final List<DatabaseObserver> observers;
    private final List<DatabaseObserver> pending_observers;
    private UUID user;

    public Database() throws SQLException {
        this.observers = new ArrayList<>();
        this.pending_observers = new ArrayList<>();
        this.directory = HashBiMap.create();
        this.reverse_directory = this.directory.inverse();
        this.connected = new HashMap<>();
        this.connection = DriverManager.getConnection("jdbc:sqlite:messages.sqlite3");
        this.user = null;
        this.nicknames = HashBiMap.create();
    }

    public BiMap<UUID, InetAddress> getDirectory() {
        return this.directory;
    }

    public BiMap<InetAddress, UUID> getReverseDirectory() {
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
                this.notify_disconnect_user(this.reverse_directory.get(packet.getAddress()));
                this.reverse_directory.remove(packet.getAddress());
                this.connected.put(packet.getAddress(), false);
            }
            case ChangeUUID -> {
                if (!this.connected.getOrDefault(packet.getAddress(), false)) {
                    this.connected.put(packet.getAddress(), true);
                }
                if (!this.directory.containsKey(((ChangeUUIDPacket) packet).getUUID())) {
                    if (this.directory.containsKey(((ChangeUUIDPacket) packet).getUUID())) {
                        this.notify_disconnect_user(((ChangeUUIDPacket) packet).getUUID());
                    }
                    this.directory.forcePut(((ChangeUUIDPacket) packet).getUUID(), packet.getAddress());
                    this.notify_connect_user(((ChangeUUIDPacket) packet).getUUID());
                }
            }
            case ChangeNickname -> {
                if (!this.connected.getOrDefault(packet.getAddress(), false)) {
                    this.connected.put(packet.getAddress(), true);
                }
                this.nicknames.forcePut(((ChangeNicknamePacket) packet).getUUID(), ((ChangeNicknamePacket) packet).getNickname());
                this.notify_change_nickname(((ChangeNicknamePacket) packet).getUUID(), ((ChangeNicknamePacket) packet).getNickname());
            }
        }
    }


    public void applyMigrations() throws SQLException {
        Statement stmt = this.connection.createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `migrations` (`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT);");
        ResultSet result = stmt.executeQuery("SELECT MAX(`id`) FROM 'migrations';");
        int last_migration = result.getInt(1);
        switch (last_migration) {
            case 0:
                stmt.executeUpdate("CREATE TABLE `messages` (" +
                        "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                        "`uuid` CHAR(36), " +
                        "`from` CHAR(36), " +
                        "`to` CHAR(36), " +
                        "`date` DATE, " +
                        "`message` TEXT);");
                stmt.executeUpdate("CREATE TABLE `users` (" +
                        "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                        "`uuid` CHAR(36), " +
                        "`username` CHAR(36), " +
                        "`password` CHAR(36)" +
                        ");");
                stmt.executeUpdate("INSERT INTO `migrations` VALUES (1);");
            default:
        }
    }

    public void sendMessageTo(InetAddress dest_address, Message message) {
        if (message.getTo() == null)
            message.setTo(this.reverse_directory.get(dest_address));
        if (message.getFrom() == null)
            message.setFrom(this.user);
        try {
            message.saveTo(this.connection);
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

    synchronized private void notify_disconnect_user(UUID uuid) {
        this.update_observers();
        for (DatabaseObserver observer :
                this.observers)
            observer.on_disconnect_user(uuid);
    }

    synchronized private void notify_change_nickname(UUID uuid, String nickname) {
        this.update_observers();
        for (DatabaseObserver observer : this.observers) {
            observer.on_change_nickname(uuid, nickname);
        }
    }

    private void update_observers() {
        while (this.pending_observers.size() != 0) {
            this.observers.add(this.pending_observers.remove(0));
        }
    }

    synchronized private void notify_connect_user(UUID uuid) {
        this.update_observers();
        for (DatabaseObserver observer :
                this.observers)
            observer.on_connect_user(uuid);
    }

    public UUID getUUID() {
        return this.user;
    }

    public Boolean set_user(String username, String password) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement("SELECT `password`, `uuid` FROM `users` WHERE `username` = ?;");
        statement.setString(1, username);
        ResultSet request_result = statement.executeQuery();
        if (request_result.next()) {
            if (request_result.getString("password").equals(password)) {
                this.user = UUID.fromString(request_result.getString("uuid"));
                this.notify_change_nickname(this.user, username);
                return true;
            }
        }
        return false;
    }

    public boolean checkUser(String username) {
        try {
            PreparedStatement statement = this.connection.prepareStatement("SELECT COUNT(*) AS `rowcount` FROM `users` WHERE `username` == ?;");
            statement.setString(1, username);
            ResultSet results = statement.executeQuery();
            return results.getInt("rowcount") > 0;
        } catch (SQLException throwables) {
            return false;
        }
    }

    public void receiveMessage(Message message) {
        try {
            message.saveTo(this.connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.notify_new_message(message);
    }

    synchronized public void addObserver(DatabaseObserver observer) {
        this.pending_observers.add(observer);
    }

    public String getNickname() {
        return this.getNicknameFor(this.user);
    }

    public void setNickname(String nickname) {
        this.nicknames.put(this.user, nickname);
    }

    public String getNicknameFor(UUID uuid) {
        return this.nicknames.getOrDefault(uuid, "Unknown");
    }

    public boolean checkNickname(String nickname) {
        return !this.nicknames.inverse().containsKey(nickname);
    }

    public void quit() throws SQLException {
        this.connection.close();
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void disconnect(InetAddress address) {
        this.notify_disconnect_user(this.reverse_directory.get(address));
    }

}
