package diffusion.packets;

import diffusion.Diffusion;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import static diffusion.UUIDUtils.asBytes;
import static diffusion.UUIDUtils.asUUID;

public class RequestMessagesSince extends Packet {
    private final Date since;
    private final UUID uuid1;
    private final UUID uuid2;

    public RequestMessagesSince(Date since, UUID uuid1, UUID uuid2, InetAddress address) {
        super(PacketKind.RequestMessagesSince, address);
        this.since = since;
        this.uuid1 = uuid1;
        this.uuid2 = uuid2;
    }

    public RequestMessagesSince(DatagramPacket packet) {
        super(packet);
        this.uuid1 = asUUID(Arrays.copyOfRange(packet.getData(), 1, 17));
        this.uuid2 = asUUID(Arrays.copyOfRange(packet.getData(), 18, 34));
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.put(Arrays.copyOfRange(packet.getData(), 35, 35 + Long.BYTES));
        buffer.flip();
        this.since = new Date(buffer.getLong());
    }

    public DatagramPacket to_packet() {
        byte[] buffer = new byte[PACKET_LEN];
        buffer[0] = this.kind.to_byte();
        System.arraycopy(asBytes(this.uuid1), 0, buffer, 1, 16);
        System.arraycopy(asBytes(this.uuid2), 0, buffer, 18, 16);
        ByteBuffer b = ByteBuffer.allocate(Long.BYTES);
        b.putLong(this.since.getTime());
        System.arraycopy(b.array(), 0, buffer, 35, Long.BYTES);
        DatagramPacket packet = new DatagramPacket(buffer, PACKET_LEN);
        packet.setAddress(this.address);
        packet.setPort(Diffusion.PORT);
        return packet;
    }

    public Date getSince() {
        return this.since;
    }

    public UUID getUUID1() {
        return this.uuid1;
    }

    public UUID getUUID2() {
        return this.uuid2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RequestMessagesSince that = (RequestMessagesSince) o;
        return Objects.equals(getSince(), that.getSince()) && Objects.equals(uuid1, that.uuid1) && Objects.equals(uuid2, that.uuid2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSince(), uuid1, uuid2);
    }
}
