package diffusion.messages;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class ConnectMessage extends Message {
    public ConnectMessage(InetAddress address, int port) {
        super(MessageKind.Connect, address, port);
    }

    public ConnectMessage(DatagramPacket packet) {
        super(packet);
    }

    @Override
    public String toString() {
        return "ConnectMessage{}";
    }
}
