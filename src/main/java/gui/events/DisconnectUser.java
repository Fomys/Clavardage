package gui.events;

import java.util.UUID;

public class DisconnectUser implements Event {
    private final UUID uuid;

    public DisconnectUser(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUUID() {
        return this.uuid;
    }
}
