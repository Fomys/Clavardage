package messages;

import database.Database;
import database.Message;
import messages.packets.MessagePacket;
import messages.packets.Packet;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

public class Client extends Thread {
    private Socket socket;
    private boolean running = true;
    private Database database;
    private ObjectOutputStream output_stream;
    private ObjectInputStream input_stream;

    public Client(Socket socket, Database database) {
        this.socket = socket;
        this.database = database;
        try {
            this.output_stream = new ObjectOutputStream(this.socket.getOutputStream());
            this.input_stream = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        this.running = false;
    }

    @Override
    public void run() {
        Packet packet;
        while(this.running) {
            try {
                 packet = (Packet) input_stream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                continue;
            }
            this.packetHandler(packet);
        }
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void packetHandler(Packet packet) {
        switch (packet.getKind()) {
            case Message -> messageHandler((MessagePacket) packet);
            case Illegal -> System.out.println("Illegal packet");
        }
    }

    void messageHandler(MessagePacket packet) {
        this.database.receiveMessageFor(this.socket.getInetAddress(), packet.getMessage());
    }

    void sendMessage(Message message) throws IOException {
        this.database.sendMessageTo(this.socket.getInetAddress(), message);
        this.output_stream.writeObject(new MessagePacket(message));
    }
}
