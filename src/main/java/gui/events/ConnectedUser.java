package gui.events;

import java.util.UUID;

public class ConnectedUser implements Event {
    private final UUID uuid;

    public ConnectedUser(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUUID() {
        return this.uuid;
    }
}
