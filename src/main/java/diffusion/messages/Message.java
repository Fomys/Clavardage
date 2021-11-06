package diffusion.messages;

import java.net.DatagramPacket;
import java.net.InetAddress;

public abstract class Message {
    protected final int port;
    protected final InetAddress address;
    public int PACKET_LEN = 256;
    protected final MessageKind kind;

    public MessageKind getKind() {
        return kind;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public enum MessageKind {
        Connect,
        Disconnect,
        ChangeNickname,
        Illegal;

        private static MessageKind from_byte(byte value) {
            switch (value) {
                default -> {
                    return Illegal;
                }
                case 0x01 -> {
                    return Connect;
                }
                case 0x02 -> {
                    return Disconnect;
                }
                case 0x03 -> {
                    return ChangeNickname;
                }
            }
        }

        public byte to_byte() {
            switch (this) {
                case Connect -> {
                    return 0x01;
                }
                case Disconnect -> {
                    return 0x02;
                }
                case ChangeNickname -> {
                    return 0x03;
                }
                default -> {
                    return 0x00;
                }
            }
        }
    }

    public Message(MessageKind kind, InetAddress address, int port) {
        this.kind = kind;
        this.address = address;
        this.port = port;
    }

    public Message(DatagramPacket packet) {
        this.kind = MessageKind.from_byte(packet.getData()[0]);
        this.address = packet.getAddress();
        this.port = packet.getPort();
    }

    public static Message from_packet(DatagramPacket packet) {
        switch (MessageKind.from_byte(packet.getData()[0])) {
            case Connect -> {
                return new ConnectMessage(packet);
            }
            case Disconnect -> {
                return new DisconnectMessage(packet);
            }
            case ChangeNickname -> {
                return new ChangeNicknameMessage(packet);
            }
            default -> {
                return new IllegalMessage(packet);
            }
        }
    }

    public DatagramPacket to_packet() {
        byte[] buffer = new byte[PACKET_LEN];
        buffer[0] = this.kind.to_byte();
        DatagramPacket packet = new DatagramPacket(buffer, PACKET_LEN);
        packet.setAddress(this.address);
        packet.setPort(this.port);
        return packet;
    }

    @Override
    public String toString() {
        return "Message{kind=" + kind + "}";
    }
}
