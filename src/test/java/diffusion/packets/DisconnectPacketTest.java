package diffusion.packets;

import org.junit.jupiter.api.Test;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.*;

class DisconnectPacketTest {
    @Test
    public void create_packet () {
        InetAddress localhost = null;
        try {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            fail("Fail to get localhost address");
        }

        DisconnectPacket packet = new DisconnectPacket(localhost);

        assertEquals(localhost, packet.getAddress());
        assertEquals(Packet.PacketKind.Disconnect, packet.getKind());
    }

    @Test
    public void export_import_packet () {
        InetAddress localhost = null;
        try {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            fail("Fail to get localhost address");
        }

        DisconnectPacket packet = new DisconnectPacket(localhost);
        DatagramPacket datagram_packet = packet.to_packet();
        DisconnectPacket new_packet = new DisconnectPacket(datagram_packet);

        assertEquals(packet, new_packet);
    }


}