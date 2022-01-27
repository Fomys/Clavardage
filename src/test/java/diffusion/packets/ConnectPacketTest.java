package diffusion.packets;

import org.junit.jupiter.api.Test;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ConnectPacketTest {
    @Test
    public void create_packet () {
        InetAddress localhost = null;
        try {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            fail("Fail to get localhost address");
        }

        ConnectPacket packet = new ConnectPacket(localhost);

        assertEquals(localhost, packet.getAddress());
        assertEquals(Packet.PacketKind.Connect, packet.getKind());
    }

    @Test
    public void export_import_packet () {
        InetAddress localhost = null;
        try {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            fail("Fail to get localhost address");
        }

        ConnectPacket packet = new ConnectPacket(localhost);
        DatagramPacket datagram_packet = packet.to_packet();
        ConnectPacket new_packet = new ConnectPacket(datagram_packet);

        assertEquals(packet, new_packet);
    }

}