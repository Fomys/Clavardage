package database;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

public class Message implements Serializable, DatabaseObject {
    private final String content;
    private final UUID uuid;
    private UUID from;
    private UUID to;
    private Date date;

    public Message(String content) {
        this.date = new Date();
        this.from = null;
        this.to = null;
        this.content = content;
        this.uuid = UUID.randomUUID();
    }

    public Message(UUID from, UUID to, String content, Date date, UUID uuid) {
        this.from = from;
        this.to = to;
        this.content = content;
        this.date = date;
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Message{" +
                "from='" + this.from + '\'' +
                ", to='" + this.to + '\'' +
                ", content='" + this.content + '\'' +
                ", date=" + this.date +
                ", uuid=" + this.uuid +
                '}';
    }

    public void saveTo(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM `messages` WHERE `uuid` = ?");
        statement.setString(1, this.uuid.toString());
        ResultSet results = statement.executeQuery();

        if (!results.next()) {
            PreparedStatement insert_statement = connection.prepareStatement(
                    "INSERT INTO `messages` (`from`, `to`, `message`, `date`, `uuid`) VALUES (?, ?, ?, ?, ?);");
            insert_statement.setString(1, this.from.toString());
            insert_statement.setString(2, this.to.toString());
            insert_statement.setString(3, this.content);
            insert_statement.setDate(4, new java.sql.Date(this.date.getTime()));
            insert_statement.setString(5, this.uuid.toString());
            insert_statement.execute();
        }
    }

    public UUID getFrom() {
        return this.from;
    }

    public void setFrom(UUID nickname) {
        this.from = nickname;
    }

    public UUID getTo() {
        return this.to;
    }

    public void setTo(UUID to) {
        this.to = to;
    }

    public UUID GetUuid() {
        return this.uuid;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return this.content;
    }
}
