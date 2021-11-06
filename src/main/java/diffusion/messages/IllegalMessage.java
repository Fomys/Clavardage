package diffusion.messages;

import java.net.DatagramPacket;

public class IllegalMessage extends Message {
    public IllegalMessage(DatagramPacket packet) {
        super(packet);
    }
}
