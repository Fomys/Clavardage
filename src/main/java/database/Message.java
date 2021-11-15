package database;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class Message implements Serializable, DatabaseObject {
    private String from;
    private String to;
    private String content;
    private Date date;

    public void setFrom(String nickname) {
        this.from = nickname;
    }

    public void setTo(String nickname){
        this.to = nickname;
    }

    public Message(String content) {
        this.date = new Date();
        this.from = null;
        this.to = null;
        this.content = content;
    }

    public Message(String from, String to, String content, Date date) {
        this.from = from;
        this.to = to;
        this.content = content;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}';
    }

    public void saveTo(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO `messages` (`from`, `to`, `message`) VALUES (?, ?, ?);");
        statement.setString(1, this.from);
        statement.setString(2, this.to);
        statement.setString(3, this.content);
        statement.execute();
    }
}
