package diffusion.packets;

import org.junit.jupiter.api.Test;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ChangeNicknamePacketTest {
    @Test
    public void create_packet () {
        InetAddress localhost = null;
        try {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            fail("Fail to get localhost address");
        }
        UUID uuid = UUID.randomUUID();
        String nickname = "This is a username";

        ChangeNicknamePacket packet = new ChangeNicknamePacket(uuid, nickname, localhost);

        assertEquals(uuid, packet.getUUID());
        assertEquals(nickname, packet.getNickname());
        assertEquals(localhost, packet.getAddress());
        assertEquals(Packet.PacketKind.ChangeNickname, packet.getKind());
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
        String nickname = "This is a username";

        ChangeNicknamePacket packet = new ChangeNicknamePacket(uuid, nickname, localhost);
        DatagramPacket datagram_packet = packet.to_packet();
        ChangeNicknamePacket new_packet = new ChangeNicknamePacket(datagram_packet);

        assertEquals(packet, new_packet);
    }

}