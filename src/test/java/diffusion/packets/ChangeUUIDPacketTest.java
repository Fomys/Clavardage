package diffusion.packets;

import org.junit.jupiter.api.Test;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ChangeUUIDPacketTest {
    @Test
    public void create_packet () {
        InetAddress localhost = null;
        try {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            fail("Fail to get localhost address");
        }
        UUID uuid = UUID.randomUUID();

        ChangeUUIDPacket packet = new ChangeUUIDPacket(uuid, localhost);

        assertEquals(uuid, packet.getUUID());
        assertEquals(localhost, packet.getAddress());
        assertEquals(Packet.PacketKind.ChangeUUID, packet.getKind());
    }

    @Test
    public void export_import_packet () {
        InetAddress localhost = null;
        try {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            fail("Fail to get localhost address");
        }
        UUID uuid = UUID.randomUUID();

        ChangeUUIDPacket packet = new ChangeUUIDPacket(uuid, localhost);
        DatagramPacket datagram_packet = packet.to_packet();
        ChangeUUIDPacket new_packet = new ChangeUUIDPacket(datagram_packet);

        assertEquals(packet, new_packet);
    }

}