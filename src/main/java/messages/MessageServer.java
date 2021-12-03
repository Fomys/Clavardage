package messages;

import database.Database;
import database.Message;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;

public class MessageServer extends Thread {
    private final ServerSocket socket;
    private final Database database;
    private boolean running = true;
    private HashMap<InetAddress, Client> clients;

    public MessageServer(int port, Database database) throws IOException {
        super("MessageServer");
        this.socket = new ServerSocket(port, 2, InetAddress.getByAddress(new byte[] {0,0,0,0}));
        this.database = database;
        this.clients = new HashMap<>();
    }

    synchronized public void disconnect() {
        this.running = false;
        for (Client client: this.clients.values()) {
            client.disconnect();
        }
    }

    synchronized private Client getClient(InetAddress to) throws IOException {
        if(this.clients.containsKey(to)) {
            return this.clients.get(to);
        } else if(this.database.getConnected().getOrDefault(to, false)) {
            Socket client_socket = new Socket(to, this.socket.getLocalPort());
            Client client = new Client(client_socket, this.database);
            this.clients.put(to, client);
            return client;
        } else {
            return null;
        }
    }

    synchronized public void requestMessagesSince(Date since, String to) throws IOException {
        this.requestMessagesSince(since, this.database.getDirectory().get(to));
    }

    synchronized public void requestMessagesSince(Date since, InetAddress to) throws IOException {
        Client client = this.getClient(to);
        if(client != null) {
            client.requestMessagesSince(since);
        }
    }

    synchronized public void sendMessageTo(Message message, String to) throws IOException {
        this.sendMessageTo(message, this.database.getDirectory().get(to));
    }

    synchronized public void sendMessageTo(Message message, InetAddress to) throws IOException {
        Client client = this.getClient(to);
        if(client != null) {
            client.sendMessage(message);
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

            Client new_client = null;
            try {
                new_client = new Client(client_socket, this.database);
                new_client.start();
                this.clients.put(client_socket.getInetAddress(), new_client);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
