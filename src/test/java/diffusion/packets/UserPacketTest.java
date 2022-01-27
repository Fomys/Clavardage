package diffusion.packets;


import org.junit.jupiter.api.Test;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserPacketTest {
    @Test
    public void create_packet () {
        InetAddress localhost = null;
        try {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            fail("Fail to get localhost address");
        }
        UUID uuid = UUID.randomUUID();
        String username = "This is a username";
        String password = "This is a password";

        UserPacket packet = new UserPacket(uuid, username, password, localhost);

        assertEquals(uuid, packet.getUUID());
        assertEquals(username, packet.getUsername());
        assertEquals(password, packet.getPassword());
        assertEquals(localhost, packet.getAddress());
        assertEquals(Packet.PacketKind.UserPacket, packet.getKind());
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
        String username = "This is a username";
        String password = "This is a password";

        UserPacket packet = new UserPacket(uuid, username, password, localhost);
        DatagramPacket datagram_packet = packet.to_packet();
        UserPacket new_packet = new UserPacket(datagram_packet);

        assertEquals(packet, new_packet);
    }
}