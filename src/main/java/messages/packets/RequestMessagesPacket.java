package messages.packets;

import java.util.Date;

public class RequestMessagesPacket extends Packet {
    private final Date since;

    public RequestMessagesPacket(Date since) {
        super(PacketKind.GetMessages);
        this.since = since;
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
