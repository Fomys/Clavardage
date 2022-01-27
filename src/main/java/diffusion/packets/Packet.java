package diffusion.packets;

import diffusion.Diffusion;

import java.net.DatagramPacket;
import java.net.InetAddress;

public abstract class Packet {
    protected final InetAddress address;
    protected final PacketKind kind;
    public int PACKET_LEN = 512;

    public Packet(PacketKind kind, InetAddress address) {
        this.kind = kind;
        this.address = address;
    }

    public Packet(DatagramPacket packet) {
        this.kind = PacketKind.from_byte(packet.getData()[0]);
        this.address = packet.getAddress();
    }

    public static Packet from_packet(DatagramPacket packet) {
        switch (PacketKind.from_byte(packet.getData()[0])) {
            case Connect -> {
                return new ConnectPacket(packet);
            }
            case Disconnect -> {
                return new DisconnectPacket(packet);
            }
            case ChangeNickname -> {
                return new ChangeNicknamePacket(packet);
            }
            case UserPacket -> {
                return new UserPacket(packet);
            }
            case ChangeUUID -> {
                return new ChangeUUIDPacket(packet);
            }
            case RequestMessagesSince -> {
                return new RequestMessagesSince(packet);
            }
            default -> {
                return new IllegalPacket(packet);
            }
        }
    }

    public PacketKind getKind() {
        return kind;
    }

    public InetAddress getAddress() {
        return address;
    }

    public DatagramPacket to_packet() {
        byte[] buffer = new byte[PACKET_LEN];
        buffer[0] = this.kind.to_byte();
        DatagramPacket packet = new DatagramPacket(buffer, PACKET_LEN);
        packet.setAddress(this.address);
        packet.setPort(Diffusion.PORT);
        return packet;
    }

    @Override
    public String toString() {
        return "Message{kind=" + kind + "}";
    }

    public enum PacketKind {
        Connect,
        Disconnect,
        ChangeNickname,
        ChangeUUID,
        UserPacket,
        RequestMessagesSince,
        Illegal;

        private static PacketKind from_byte(byte value) {
            switch (value) {
                case 0x01 -> {
                    return Connect;
                }
                case 0x02 -> {
                    return Disconnect;
                }
                case 0x03 -> {
                    return ChangeNickname;
                }
                case 0x04 -> {
                    return ChangeUUID;
                }
                case 0x05 -> {
                    return UserPacket;
                }
                case 0x06 -> {
                    return RequestMessagesSince;
                }
                default -> {
                    return Illegal;
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
                case ChangeUUID -> {
                    return 0x04;
                }
                case UserPacket -> {
                    return 0x05;
                }
                case RequestMessagesSince -> {
                    return 0x06;
                }
                default -> {
                    return 0x00;
                }
            }
        }
    }
}
