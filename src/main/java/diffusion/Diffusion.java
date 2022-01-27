package diffusion;

import database.Database;
import database.User;
import diffusion.packets.*;
import messages.MessageServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

public class Diffusion extends Thread {
    public static int PORT = 10001;
    private final InetAddress BROADCAST_ADDRESS = Inet4Address.getByAddress(new byte[]{-1, -1, -1, -1});
    private final Database database;
    private final DatagramSocket socket;
    private final MessageServer message_server;
    private boolean running = true;

    public Diffusion(Database database, MessageServer message_server) throws Exception {
        super("Diffusion");
        this.message_server = message_server;
        this.socket = new DatagramSocket(PORT, Inet4Address.getByAddress(new byte[]{0, 0, 0, 0}));
        this.socket.setBroadcast(true);
        this.database = database;
    }

    public InetAddress getBROADCAST_ADDRESS() {
        return this.BROADCAST_ADDRESS;
    }

    public void disconnect() throws IOException {
        DisconnectPacket disconnect_message = new DisconnectPacket(BROADCAST_ADDRESS);
        this.socket.send(disconnect_message.to_packet());
        this.running = false;
    }

    public void diffuse_nickname(String nickname) {
        if (this.database.getUUID() != null) {
            ChangeNicknamePacket connect_message = new ChangeNicknamePacket(this.database.getUUID(), nickname, BROADCAST_ADDRESS);
            try {
                this.socket.send(connect_message.to_packet());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
        byte[] buffer = new byte[512];
        while (this.running) {
            DatagramPacket recv_packet = new DatagramPacket(buffer, buffer.length);
            try {
                socket.receive(recv_packet);
            } catch (IOException e) {
                return;
            }
            Packet packet = Packet.from_packet(recv_packet);
            if (packet.getKind() == Packet.PacketKind.Illegal) {
                continue;
            }
            try {
                this.database.update(packet);
            } catch (IOException throwables) {
                throwables.printStackTrace();
            }
            if (packet.getKind() == Packet.PacketKind.Connect && this.database.getUUID() != null) {
                this.on_connect_packet((ConnectPacket) packet);
            }
            if (packet.getKind() == Packet.PacketKind.UserPacket) {
                try {
                    this.on_user_packet((UserPacket) packet);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else if (packet.getKind() == Packet.PacketKind.RequestMessagesSince) {
                this.on_request_messages_since((RequestMessagesSince) packet);
            }

        }
        this.socket.close();
    }

    private void on_request_messages_since(RequestMessagesSince packet) {
        this.message_server.requestMessagesSince(packet.getSince(), packet.getUUID1(), packet.getUUID2(), packet.getAddress());
    }

    private void on_user_packet(UserPacket packet) throws SQLException {
        User user = packet.to_user();
        user.saveTo(this.database.getConnection());
    }

    public void on_connect_packet(ConnectPacket packet) {
        ChangeUUIDPacket change_uuid_packet = new ChangeUUIDPacket(this.database.getUUID(), packet.getAddress());
        ChangeNicknamePacket change_nickname_message = new ChangeNicknamePacket(this.database.getUUID(), this.database.getNickname(), packet.getAddress());
        try {
            this.socket.send(change_uuid_packet.to_packet());
            for (User user :
                    User.all(database.getConnection())) {
                this.diffuse_new_user(user, packet.getAddress());
            }
            this.socket.send(change_nickname_message.to_packet());
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void connect() throws IOException {
        ConnectPacket connect_message = new ConnectPacket(BROADCAST_ADDRESS);
        this.socket.send(connect_message.to_packet());
    }

    public void diffuse_new_user(User user, InetAddress address) throws IOException {
        UserPacket user_packet = new UserPacket(user, address);
        this.socket.send(user_packet.to_packet());
    }

    public void diffuse_uuid(UUID uuid, InetAddress address) throws IOException {
        ChangeUUIDPacket change_uuid_packet = new ChangeUUIDPacket(uuid, address);
        this.socket.send(change_uuid_packet.to_packet());
    }

    public void requestMessageSince(Date date, UUID uuid1, UUID uuid2) throws IOException {
        RequestMessagesSince request_message_since_packet = new RequestMessagesSince( date, uuid1, uuid2,this.getBROADCAST_ADDRESS());
        this.socket.send(request_message_since_packet.to_packet());
    }
}
