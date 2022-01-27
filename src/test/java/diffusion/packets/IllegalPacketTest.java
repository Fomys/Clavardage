package diffusion.packets;

import org.junit.jupiter.api.Test;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.*;

class IllegalPacketTest {
    @Test
    public void create_packet () {
        InetAddress localhost = null;
        try {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            fail("Fail to get localhost address");
        }

        IllegalPacket packet = new IllegalPacket(localhost);

        assertEquals(localhost, packet.getAddress());
        assertEquals(Packet.PacketKind.Illegal, packet.getKind());
    }

    @Test
    public void export_import_packet () {
        InetAddress localhost = null;
        try {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            fail("Fail to get localhost address");
        }

        IllegalPacket packet = new IllegalPacket(localhost);
        DatagramPacket datagram_packet = packet.to_packet();
        IllegalPacket new_packet = new IllegalPacket(datagram_packet);

        assertEquals(packet, new_packet);
    }


}