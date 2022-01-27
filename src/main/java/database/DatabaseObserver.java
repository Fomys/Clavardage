package database;

import java.io.IOException;
import java.util.UUID;

public interface DatabaseObserver {
    default void on_message(Message message) {
    }

    default void on_connect_user(UUID uuid) {
    }

    default void on_disconnect_user(UUID uuid) {
    }

    default void on_change_nickname(UUID uuid, String nickname) {
    }
}
