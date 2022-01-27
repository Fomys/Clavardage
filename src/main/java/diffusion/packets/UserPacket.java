package diffusion.packets;

import database.User;
import diffusion.Diffusion;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

import static diffusion.UUIDUtils.asBytes;
import static diffusion.UUIDUtils.asUUID;
import static java.lang.Math.min;

public class UserPacket extends Packet {
    private final String password;
    private final String username;
    private final UUID uuid;

    public UserPacket(UUID uuid, String username, String password, InetAddress address) {
        super(PacketKind.UserPacket, address);
        this.uuid = uuid;
        this.username = username;
        this.password = password;
    }

    public UserPacket(User user, InetAddress address) {
        super(PacketKind.UserPacket, address);
        this.uuid = user.getUUID();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    public UserPacket(DatagramPacket packet) {
        /*
        [0] = 0x05
        [1:36] = uuid
        [37] = username_len (<= 254)
        [38:username_len] = username
        [username_len+1] = password_len
        [username_len+2:username_len+2+password_len] = password
         */
        super(packet);
        this.uuid = asUUID(Arrays.copyOfRange(packet.getData(), 1, 17));
        int username_len = packet.getData()[18] + 128;
        int password_len = packet.getData()[19 + username_len] + 128;
        this.username = new String(Arrays.copyOfRange(packet.getData(), 19, 19 + username_len), StandardCharsets.UTF_8);
        this.password = new String(Arrays.copyOfRange(packet.getData(), 20 + username_len, 20 + username_len + password_len), StandardCharsets.UTF_8);
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "UserPacket{" +
                "password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", uuid=" + uuid +
                '}';
    }

    @Override
    public DatagramPacket to_packet() {
        byte[] buffer = new byte[PACKET_LEN];
        buffer[0] = this.kind.to_byte();
        System.arraycopy(asBytes(this.uuid), 0, buffer, 1, 16);
        byte[] username_buffer = this.username.getBytes(StandardCharsets.UTF_8);
        byte[] password_buffer = this.password.getBytes(StandardCharsets.UTF_8);
        int username_len = min(username_buffer.length, 254);
        int password_len = min(password_buffer.length, 254);
        buffer[18] = (byte) (username_len - 128);
        buffer[19 + username_len] = (byte) (password_len - 128);
        System.arraycopy(username_buffer, 0, buffer, 19, username_len);
        System.arraycopy(password_buffer, 0, buffer, 20 + username_len, password_len);
        DatagramPacket packet = new DatagramPacket(buffer, PACKET_LEN);
        packet.setAddress(this.address);
        packet.setPort(Diffusion.PORT);
        return packet;
    }

    public User to_user() {
        return new User(this.uuid, this.username, this.password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPacket that = (UserPacket) o;
        return Objects.equals(getPassword(), that.getPassword()) && Objects.equals(getUsername(), that.getUsername()) && Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPassword(), getUsername(), uuid);
    }
}
