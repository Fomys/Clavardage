package diffusion.packets;

import org.junit.jupiter.api.Test;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RequestMessagesSinceTest {
    @Test
    public void create_packet () {
        InetAddress localhost = null;
        try {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            fail("Fail to get localhost address");
        }

        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        Date since = new Date(0);
        RequestMessagesSince packet = new RequestMessagesSince(since, uuid1, uuid2, localhost);

        assertEquals(uuid1, packet.getUUID1());
        assertEquals(uuid2, packet.getUUID2());
        assertEquals(since, packet.getSince());
        assertEquals(localhost, packet.getAddress());
        assertEquals(Packet.PacketKind.RequestMessagesSince, packet.getKind());
    }

    @Test
    public void export_import_packet () {
        InetAddress localhost = null;
        try {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            fail("Fail to get localhost address");
        }

        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        Date since = new Date(0);
        RequestMessagesSince packet = new RequestMessagesSince(since, uuid1, uuid2, localhost);
        DatagramPacket datagram_packet = packet.to_packet();
        RequestMessagesSince new_packet = new RequestMessagesSince(datagram_packet);

        assertEquals(packet, new_packet);
    }
}
