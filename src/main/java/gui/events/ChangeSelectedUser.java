package gui.events;

import java.util.UUID;

public class ChangeSelectedUser implements Event {
    private final UUID uuid;

    public ChangeSelectedUser(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUUID() {
        return this.uuid;
    }
}
