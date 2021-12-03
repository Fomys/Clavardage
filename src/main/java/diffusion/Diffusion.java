package diffusion;

import database.Database;
import diffusion.packets.ChangeNicknamePacket;
import diffusion.packets.ConnectPacket;
import diffusion.packets.DisconnectPacket;
import diffusion.packets.Packet;

import java.io.IOException;
import java.net.*;
import java.util.logging.Logger;

public class Diffusion extends Thread {
    private final InetAddress BROADCAST_ADDRESS = Inet4Address.getByAddress(new byte[] {-1, -1, -1, -1});

    private DatagramSocket socket;
    private boolean running = true;
    private final Database database;

    public Diffusion(int port, Database database) throws Exception {
        super("Diffusion");
        this.socket = new DatagramSocket(port, Inet4Address.getByAddress(new byte[] {0,0,0,0}));
        this.socket.setBroadcast(true);
        this.database = database;
    }

    public void disconnect() throws IOException {
        DisconnectPacket disconnect_message = new DisconnectPacket(BROADCAST_ADDRESS, this.socket.getLocalPort());
        this.socket.send(disconnect_message.to_packet());
        this.running = false;
    }

    public void setNickname(String nickname) throws IOException {
        ChangeNicknamePacket connect_message = new ChangeNicknamePacket(nickname, BROADCAST_ADDRESS, this.socket.getLocalPort());
        this.database.setNickname(nickname);
        this.socket.send(connect_message.to_packet());
    }

    public void run() {
        byte[] buffer = new byte[512];
        while(this.running) {
            DatagramPacket recv_packet = new DatagramPacket(buffer, buffer.length);
            try {
                socket.receive(recv_packet);
            } catch (IOException e) {
                // TODO: Envoyer à la GUI
                e.printStackTrace();
                continue;
            }
            Packet packet = Packet.from_packet(recv_packet);
            if (packet.getKind() == Packet.PacketKind.Illegal) {
                continue;
            }
            this.database.update(packet);
            if(packet.getKind() == Packet.PacketKind.Connect && this.database.getNickname() != null) {
                ChangeNicknamePacket change_nickname_message = new ChangeNicknamePacket(this.database.getNickname(), packet.getAddress(), packet.getPort());
                try {
                    this.socket.send(change_nickname_message.to_packet());
                    // TODO: envoyer paquet de changement refusé
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        this.socket.close();
    }

    public void connect() throws IOException {
        ConnectPacket connect_message = new ConnectPacket(BROADCAST_ADDRESS, this.socket.getLocalPort());

        this.socket.send(connect_message.to_packet());
    }
}
