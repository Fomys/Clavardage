package database;

import java.io.IOException;

public interface DatabaseObserver {
    default void on_message(Message message) {}
    default void on_connect_user(String username) throws IOException {}
    default void on_disconnect_user(String username) {}
    default void on_change_nickname(String nickname) {}
}
