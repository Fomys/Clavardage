package database;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

public class Message implements Serializable, DatabaseObject {
    private String from;
    private String to;
    private String content;
    private Date date;
    private UUID uuid;

    public void setFrom(String nickname) {
        this.from = nickname;
    }

    public void setTo(String nickname){
        this.to = nickname;
    }

    public void setDate(Date date) {this.date = date;}

    public Message(String content) {
        this.date = new Date();
        this.from = null;
        this.to = null;
        this.content = content;
        this.uuid = UUID.randomUUID();
    }

    public Message(String from, String to, String content, Date date, UUID uuid) {
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
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM `messages` WHERE `from` = ? AND `to` = ? AND `message` = ? and `date` = ? and `uuid` = ?");
        statement.setString(1, this.from);
        statement.setString(2, this.to);
        statement.setString(3, this.content);
        statement.setDate(4, new java.sql.Date(this.date.getTime()));
        statement.setString(5, this.uuid.toString());
        ResultSet results = statement.executeQuery();

        if(!results.next()) {
            PreparedStatement insert_statement = connection.prepareStatement("INSERT INTO `messages` (`from`, `to`, `message`, `date`) VALUES (?, ?, ?, ?);");
            insert_statement.setString(1, this.from);
            insert_statement.setString(2, this.to);
            insert_statement.setString(3, this.content);
            insert_statement.setDate(4, new java.sql.Date(this.date.getTime()));
            insert_statement.execute();
        }
    }
}
