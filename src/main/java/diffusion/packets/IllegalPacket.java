package diffusion.packets;

import java.net.DatagramPacket;

public class IllegalPacket extends Packet {
    public IllegalPacket(DatagramPacket packet) {
        super(packet);
    }
}
