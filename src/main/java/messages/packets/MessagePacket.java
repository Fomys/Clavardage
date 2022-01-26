package messages.packets;

import database.Message;

public class MessagePacket extends Packet {
    private final Message message;

    public MessagePacket(Message message) {
        super(PacketKind.Message);
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "MessagePacket{" +
                "message=" + message +
                '}';
    }
}
