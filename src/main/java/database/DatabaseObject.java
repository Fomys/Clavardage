package database;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseObject {
    void saveTo(Connection connection) throws SQLException;
}
