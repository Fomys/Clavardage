package diffusion;

import diffusion.messages.ChangeNicknameMessage;
import diffusion.messages.ConnectMessage;
import diffusion.messages.DisconnectMessage;
import diffusion.messages.Message;

import java.io.IOException;
import java.net.*;
import java.util.logging.Logger;

public class Diffusion extends Thread {
    private static final Logger LOGGER = Logger.getLogger( Diffusion.class.getName() );

    private final InetAddress BROADCAST_ADDRESS = Inet4Address.getByAddress(new byte[] {-1, -1, -1, -1});

    private DatagramSocket socket;
    private boolean running = true;
    private final Cache cache;
    private String nickname;

    public Diffusion(int port, Cache cache) throws Exception {
        this.socket = new DatagramSocket(port, Inet4Address.getByAddress(new byte[] {0,0,0,0}));
        this.socket.setBroadcast(true);
        this.cache = cache;
    }

    public void disconnect() throws IOException {
        DisconnectMessage disconnect_message = new DisconnectMessage(BROADCAST_ADDRESS, this.socket.getLocalPort());
        this.socket.send(disconnect_message.to_packet());
        this.running = false;
    }

    public void setNickname(String nickname) throws IOException {
        ChangeNicknameMessage connect_message = new ChangeNicknameMessage(nickname, BROADCAST_ADDRESS, this.socket.getLocalPort());
        this.nickname = nickname;
        this.socket.send(connect_message.to_packet());
    }

    public void run() {
        LOGGER.info("Start diffusion server...");
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
            Message message = Message.from_packet(recv_packet);
            if (message.getKind() == Message.MessageKind.Illegal) {
                LOGGER.warning("Illegal message received from " + message.getAddress() + ":" + message.getPort());
                continue;
            }
            LOGGER.info("Message received from "+ message.getAddress() + ":" + message.getPort() + " : " + message);
            this.cache.update(message);
            if(message.getKind() == Message.MessageKind.Connect && this.nickname != null) {
                ChangeNicknameMessage change_nickname_message = new ChangeNicknameMessage(this.nickname, message.getAddress(), message.getPort());
                try {
                    this.socket.send(change_nickname_message.to_packet());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        this.socket.close();
    }

    public void connect() throws IOException {
        ConnectMessage connect_message = new ConnectMessage(BROADCAST_ADDRESS, this.socket.getLocalPort());

        this.socket.send(connect_message.to_packet());
    }
}
