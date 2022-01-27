package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class User implements DatabaseObject {
    private final UUID uuid;
    private final String username;
    private final String password;

    public User(UUID uuid, String username, String password) {
        this.uuid = uuid;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password) {
        this.uuid = UUID.randomUUID();
        this.username = username;
        this.password = password;
    }

    public static ArrayList<User> all(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT `uuid`, `username`, `password` FROM `users`;");
        ResultSet request_result = statement.executeQuery();
        ArrayList<User> results = new ArrayList<>();
        while (request_result.next()) {
            results.add(new User(
                    UUID.fromString(request_result.getString("uuid")),
                    request_result.getString("username"),
                    request_result.getString("password")
            ));
        }
        return results;
    }

    @Override
    public void saveTo(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT `uuid` FROM `users` WHERE `uuid` = ?;");
        statement.setString(1, uuid.toString());
        ResultSet request_result = statement.executeQuery();
        if (!request_result.next()) {
            statement = connection.prepareStatement("INSERT INTO `users` (uuid, username, password) VALUES (?, ?, ?);");
            statement.setString(1, this.uuid.toString());
            statement.setString(2, this.username);
            statement.setString(3, this.password);
            statement.execute();
        }
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public UUID getUUID() {
        return this.uuid;
    }
}
