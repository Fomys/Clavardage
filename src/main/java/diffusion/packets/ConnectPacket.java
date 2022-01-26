package diffusion.packets;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class ConnectPacket extends Packet {
    public ConnectPacket(InetAddress address) {
        super(PacketKind.Connect, address);
    }

    public ConnectPacket(DatagramPacket packet) {
        super(packet);
    }

    @Override
    public String toString() {
        return "ConnectMessage{}";
    }
}
