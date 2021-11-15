package diffusion.packets;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class DisconnectPacket extends Packet {
    public DisconnectPacket(InetAddress address, int port) {
        super(PacketKind.Disconnect, address, port);
    }

    public DisconnectPacket(DatagramPacket packet) {
        super(packet);
    }

    @Override
    public String toString() {
        return "DisconnectMessage{}";
    }
}
