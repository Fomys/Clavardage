package messages;

import database.Database;
import database.Message;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class MessageServer extends Thread {
    private final ServerSocket socket;
    private final Database database;
    private boolean running = true;
    private HashMap<InetAddress, Client> clients;

    public MessageServer(int port, Database database) throws IOException {
        this.socket = new ServerSocket(port, 2, InetAddress.getByAddress(new byte[] {0,0,0,0}));
        this.database = database;
        this.clients = new HashMap<>();
    }

    public void disconnect() {
        this.running = false;
        for (Client client: this.clients.values()) {
            client.disconnect();
        }
    }

    public void sendMessageTo(Message message, String to) throws IOException {
        this.sendMessageTo(message, this.database.getDirectory().get(to));
    }

    public void sendMessageTo(Message message, InetAddress to) throws IOException {
        if(this.clients.containsKey(to)) {
            this.clients.get(to).sendMessage(message);
        }
        if(this.database.getConnected().getOrDefault(to, false)) {
            Socket client_socket = new Socket(to, this.socket.getLocalPort());
            Client client = new Client(client_socket, this.database);
            client.sendMessage(message);
            this.clients.put(to, client);
        }
    }

    @Override
    public void run() {
        Socket client_socket;
        while(this.running) {
            try {
                client_socket = this.socket.accept();
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }

            Client new_client = new Client(client_socket, this.database);
            new_client.start();
            this.clients.put(client_socket.getInetAddress(), new_client);
        }
    }
}
