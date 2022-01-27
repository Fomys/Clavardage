package diffusion.packets;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class IllegalPacket extends Packet {
    public IllegalPacket(InetAddress address) {
        super(PacketKind.Illegal, address);
    }
    public IllegalPacket(DatagramPacket packet) {
        super(packet);
    }
}
