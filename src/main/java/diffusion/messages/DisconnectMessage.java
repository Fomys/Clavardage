package diffusion.messages;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class DisconnectMessage extends Message {
    public DisconnectMessage(InetAddress address, int port) {
        super(MessageKind.Disconnect, address, port);
    }

    public DisconnectMessage(DatagramPacket packet) {
        super(packet);
    }

    @Override
    public String toString() {
        return "DisconnectMessage{}";
    }
}
