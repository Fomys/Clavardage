package messages;

import database.Database;
import database.Message;
import messages.packets.MessagePacket;
import messages.packets.Packet;
import messages.packets.RequestMessagesPacket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.List;

public class Client extends Thread {
    private final ObjectOutputStream output_stream;
    private final ObjectInputStream input_stream;
    private final Socket socket;
    private boolean running = true;
    private final Database database;

    public Client(Socket socket, Database database) throws IOException {
        super("Client " + socket.getInetAddress());
        this.socket = socket;
        this.database = database;
        this.output_stream = new ObjectOutputStream(this.socket.getOutputStream());
        this.input_stream = new ObjectInputStream(this.socket.getInputStream());
    }

    public void disconnect() {
        this.running = false;
    }

    @Override
    public void run() {
        Packet packet;
        while (this.running) {
            try {
                packet = (Packet) input_stream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                break;
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
            case GetMessages -> requestMessagesSinceHandler((RequestMessagesPacket) packet);
            case Illegal -> System.out.println("Illegal packet");
        }
    }

    private void requestMessagesSinceHandler(RequestMessagesPacket packet) {
        List<Message> messages = this.database.getMessagesFor(this.database.getReverseDirectory().get(this.socket.getInetAddress()), packet.getSince());
        for (Message message : messages) {
            try {
                this.output_stream.writeObject(new MessagePacket(message));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    void messageHandler(MessagePacket packet) {
        this.database.receiveMessageFor(this.socket.getInetAddress(), packet.getMessage());
    }

    void sendMessage(Message message) throws IOException {
        this.database.sendMessageTo(this.socket.getInetAddress(), message);
        this.output_stream.writeObject(new MessagePacket(message));
    }

    public void requestMessagesSince(Date since) throws IOException {
        this.output_stream.writeObject(new RequestMessagesPacket(since));
    }
}
