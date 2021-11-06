package diffusion.messages;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static java.lang.Math.min;

public class ChangeNicknameMessage extends Message {
    private final String nickname;

    public ChangeNicknameMessage(String nickname, InetAddress address, int port) {
        super(MessageKind.ChangeNickname, address, port);
        this.nickname = nickname;
    }

    public ChangeNicknameMessage(DatagramPacket packet) {
        /*
        [0] = 0x03
        [1] = nickname_len (<= 254)
        [2:nickname_len] = nickname
         */
        super(packet);
        int nickname_len = packet.getData()[1];
        this.nickname = new String(Arrays.copyOfRange(packet.getData(), 2, 2+nickname_len), StandardCharsets.UTF_8);
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public DatagramPacket to_packet() {
        byte[] buffer = new byte[PACKET_LEN];
        buffer[0] = this.kind.to_byte();
        byte[] nickname_buffer = this.nickname.getBytes(StandardCharsets.UTF_8);
        buffer[1] = (byte) min(nickname_buffer.length, 254);
        System.arraycopy(nickname_buffer, 0, buffer, 2, min(nickname_buffer.length, 254));
        DatagramPacket packet = new DatagramPacket(buffer, PACKET_LEN);
        packet.setAddress(this.address);
        packet.setPort(this.port);
        return packet;
    }

    @Override
    public String toString() {
        return "ChangeNicknameMessage{nickname='" + nickname + "'}";
    }
}
