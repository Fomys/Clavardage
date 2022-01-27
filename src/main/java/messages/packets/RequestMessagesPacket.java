package messages.packets;

import java.util.Date;
import java.util.UUID;

public class RequestMessagesPacket extends Packet {
    private final Date since;
    private final UUID uuid1;
    private final UUID uuid2;

    public RequestMessagesPacket(Date since, UUID uuid1, UUID uuid2) {
        super(PacketKind.GetMessages);
        this.since = since;
        this.uuid1 = uuid1;
        this.uuid2 = uuid2;
    }

    public UUID getUUID1() {
        return this.uuid1;
    }

    public UUID getUUID2() {
        return this.uuid2;
    }

    public Date getSince() {
        return this.since;
    }

    @Override
    public String toString() {
        return "RequestMessagesPacket{" +
                "since=" + since +
                '}';
    }
}
