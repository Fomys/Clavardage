package database;

public interface DatabaseObserver {
    void on_message(Message message);
}
