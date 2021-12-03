package messages.packets;

import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Date;

public abstract class Packet implements Serializable {
    protected final PacketKind kind;

    public PacketKind getKind() {
        return kind;
    }

    public enum PacketKind {
        Message,
        GetMessages,
        Illegal;

        private static PacketKind from_byte(byte value) {
            switch (value) {
                default -> {
                    return Illegal;
                }
                case 0x01 -> {
                    return Message;
                }
            }
        }

        public byte to_byte() {
            switch (this) {
                case Message -> {
                    return 0x01;
                }
                default -> {
                    return 0x00;
                }
            }
        }
    }

    public Packet(PacketKind kind) {
        this.kind = kind;
    }

    @Override
    public String toString() {
        return "Packet{" +
                "kind=" + kind +
                '}';
    }
}