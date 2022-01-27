package gui.events;

import java.util.UUID;

public class ChangeNickname implements Event {
    private final String nickname;
    private final UUID uuid;

    public ChangeNickname(UUID uuid, String nickname) {
        this.uuid = uuid;
        this.nickname = nickname;
    }

    public String getNickname() {
        return this.nickname;
    }

    public UUID getUUID() {
        return this.uuid;
    }
}
