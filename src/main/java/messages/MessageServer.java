package messages;

import database.Database;
import database.Message;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class MessageServer extends Thread {
    private final ServerSocket socket;
    private final Database database;
    private final HashMap<InetAddress, Client> clients;
    private boolean running = true;

    public MessageServer(int port, Database database) throws IOException {
        super("MessageServer");
        this.socket = new ServerSocket(port, 2, InetAddress.getByAddress(new byte[]{0, 0, 0, 0}));
        this.database = database;
        this.clients = new HashMap<>();
    }

    public void disconnect() {
        this.running = false;
        for (Client client : this.clients.values()) {
            client.disconnect();
        }
    }

    private Client getClient(InetAddress to) {
        if (this.clients.containsKey(to) && this.clients.get(to).connected()) {
            return this.clients.get(to);
        } else if (this.database.getConnected().getOrDefault(to, false)) {
            Socket client_socket;
            try {
                client_socket = new Socket(to, this.socket.getLocalPort());
                Client client = new Client(client_socket, this.database);
                this.clients.put(to, client);
                client.start();
                return client;
            } catch (IOException e) {
                this.database.disconnect(to);
            }
        }
        return null;
    }

    public void requestMessagesSince(Date since, UUID uuid1, UUID uuid2, InetAddress to) {
        Client client = this.getClient(to);
        try {
            if (client != null) {
                client.requestMessagesSince(since, uuid1, uuid2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageTo(Message message, UUID to) {
        try {
            this.sendMessageTo(message, this.database.getDirectory().get(to));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageTo(Message message, InetAddress to) throws IOException {
        Client client = this.getClient(to);
        if (client != null) {
            client.sendMessage(message);
        }
    }

    @Override
    public void run() {
        Socket client_socket;
        while (this.running) {
            try {
                client_socket = this.socket.accept();
            } catch (IOException e) {
                return;
            }

            Client new_client;
            try {
                new_client = new Client(client_socket, this.database);
                this.clients.put(client_socket.getInetAddress(), new_client);
                new_client.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
