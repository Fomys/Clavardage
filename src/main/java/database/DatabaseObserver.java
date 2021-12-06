package database;

public interface DatabaseObserver {
    void on_message(Message message);
    void on_new_user(String username);
    void on_connect_user(String username);
    void on_disconnect_user(String username);
}
