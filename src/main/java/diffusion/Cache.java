package diffusion;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import diffusion.messages.ChangeNicknameMessage;
import diffusion.messages.Message;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Cache {
    private static final Logger LOGGER = Logger.getLogger( Cache.class.getName() );
    private final BiMap<String, InetAddress> directory;
    private final BiMap<InetAddress, String> reverse_directory;
    private final Map<InetAddress, Boolean> connected;

    public Map<InetAddress, Boolean> getConnected() {
        return connected;
    }

    public BiMap<String, InetAddress> getDirectory() {
        return directory;
    }

    public Cache() {
        this.directory = HashBiMap.create();
        this.reverse_directory = this.directory.inverse();
        this.connected = new HashMap<>();
    }

    public void update(Message message) {
        switch (message.getKind()) {
            case Connect -> {
                if(this.reverse_directory.containsKey(message.getAddress())) {
                    LOGGER.warning("Delete existing nickname for " + message.getAddress() + " because someone try " +
                            "to login with the same IP address");
                    this.reverse_directory.remove(message.getAddress());
                }
                this.connected.put(message.getAddress(), true);
                LOGGER.info("New connection: "+ message.getAddress());
            }
            case Disconnect -> {
                this.reverse_directory.remove(message.getAddress());
                this.connected.put(message.getAddress(), false);
                LOGGER.info("Disconnection: " + message.getAddress());
            }
            case ChangeNickname -> {
                if(!this.connected.getOrDefault(message.getAddress(), false)) {
                    this.connected.put(message.getAddress(), true);
                }
                if(this.directory.containsKey(((ChangeNicknameMessage) message).getNickname())) {
                    LOGGER.warning(message.getAddress() + " tries to use an already used nickname ");
                } else {
                    this.directory.forcePut(((ChangeNicknameMessage) message).getNickname(), message.getAddress());
                    LOGGER.info("New nickname for " + message.getAddress());
                }
            }
        }
    }
}
