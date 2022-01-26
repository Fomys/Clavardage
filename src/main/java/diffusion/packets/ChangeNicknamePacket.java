package diffusion.packets;

import diffusion.Diffusion;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.UUID;

import static diffusion.UUIDUtils.asBytes;
import static diffusion.UUIDUtils.asUUID;
import static java.lang.Math.min;

public class ChangeNicknamePacket extends Packet {
    private final UUID uuid;
    private final String nickname;

    public ChangeNicknamePacket(UUID uuid, String nickname, InetAddress address) {
        super(PacketKind.ChangeNickname, address);
        this.uuid = uuid;
        this.nickname = nickname;
    }

    public ChangeNicknamePacket(DatagramPacket packet) {
        /*
        [0] = 0x03
        [1:36] = uuid
        [37] = nickname_len (<= 254)
        [38:nickname_len] = nickname
         */
        super(packet);
        this.uuid = asUUID(Arrays.copyOfRange(packet.getData(), 1, 17));
        int nickname_len = packet.getData()[37] + 128;
        this.nickname = new String(Arrays.copyOfRange(packet.getData(), 18, nickname_len + 18), StandardCharsets.UTF_8);
        System.out.println(this);
    }


    public String getNickname() {
        return nickname;
    }

    @Override
    public DatagramPacket to_packet() {
        System.out.println("Send " + this);
        byte[] buffer = new byte[PACKET_LEN];
        buffer[0] = this.kind.to_byte();
        System.arraycopy(asBytes(this.uuid), 0, buffer, 1, 16);
        byte[] nickname_buffer = this.nickname.getBytes(StandardCharsets.UTF_8);
        buffer[37] = (byte) (min(nickname_buffer.length, 254) - 128);
        System.arraycopy(nickname_buffer, 0, buffer, 18, min(nickname_buffer.length, 254));
        DatagramPacket packet = new DatagramPacket(buffer, PACKET_LEN);
        packet.setAddress(this.address);
        packet.setPort(Diffusion.PORT);
        return packet;
    }

    @Override
    public String toString() {
        return "ChangeNicknamePacket{" +
                "uuid=" + uuid +
                ", nickname='" + nickname + '\'' +
                '}';
    }

    public UUID getUUID() {
        return this.uuid;
    }
}
