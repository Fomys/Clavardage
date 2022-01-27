package messages.packets;

import java.io.Serializable;

public abstract class Packet implements Serializable {
    protected final PacketKind kind;

    public Packet(PacketKind kind) {
        this.kind = kind;
    }

    public PacketKind getKind() {
        return kind;
    }

    @Override
    public String toString() {
        return "Packet{" +
                "kind=" + kind +
                '}';
    }

    public enum PacketKind {
        Message,
        Illegal
    }
}