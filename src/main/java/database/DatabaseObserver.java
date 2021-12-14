package database;

import java.io.IOException;

public interface DatabaseObserver {
    void on_message(Message message);
    void on_connect_user(String username) throws IOException;
    void on_disconnect_user(String username);
}
