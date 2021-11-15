package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DatabaseObject {
    void saveTo(Connection connection) throws SQLException;
}
