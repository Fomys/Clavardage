package diffusion.packets;

import diffusion.Diffusion;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.UUID;

import static diffusion.UUIDUtils.asBytes;
import static diffusion.UUIDUtils.asUUID;

public class ChangeUUIDPacket extends Packet {
    private final UUID uuid;

    public ChangeUUIDPacket(UUID uuid, InetAddress address) {
        super(PacketKind.ChangeUUID, address);
        this.uuid = uuid;
    }

    public ChangeUUIDPacket(DatagramPacket packet) {
        super(packet);
        this.uuid = asUUID(Arrays.copyOfRange(packet.getData(), 1, 17));
    }

    @Override
    public DatagramPacket to_packet() {
        byte[] buffer = new byte[PACKET_LEN];
        buffer[0] = this.kind.to_byte();
        System.arraycopy(asBytes(this.uuid), 0, buffer, 1, 16);
        DatagramPacket packet = new DatagramPacket(buffer, PACKET_LEN);
        packet.setAddress(this.address);
        packet.setPort(Diffusion.PORT);
        return packet;
    }

    public UUID getUUID() {
        return uuid;
    }
}
