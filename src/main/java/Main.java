import database.Database;
import database.Message;
import diffusion.Diffusion;
import messages.MessageServer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Main {
    private static final Logger LOGGER = Logger.getLogger( Main.class.getName() );
    private static final LogManager LOG_MANAGER = LogManager.getLogManager();
    static {
        try {
            LOG_MANAGER.readConfiguration(new FileInputStream("log.properties"));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Cannot read configuration file", e);
        }
    }
    public static void main(String[] args) throws Exception {
        Database database = new Database();
        database.applyMigrations();


        Diffusion diffusion = new Diffusion(10000, database);
        MessageServer message_server = new MessageServer(10001, database);
        diffusion.start();
        message_server.start();

        boolean running = true;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String command;
        String[] cli_args;
        while(running) {
            System.out.print(">> ");System.out.flush();
            command = reader.readLine();
            cli_args = command.split(" ");
            switch (cli_args[0]) {
                case "/connect" -> diffusion.connect();
                case "/nick" -> diffusion.setNickname(cli_args[1]);
                case "/disconnect" -> {
                    diffusion.disconnect();
                    message_server.disconnect();
                    running=false;
                }
                case "/connected" -> System.out.println(database.getConnected());
                case "/directory" -> System.out.println(database.getDirectory());
                case "/messages" -> {
                    if(cli_args.length == 2) {
                        List<String> messages = database.getMessagesFor(cli_args[1]).stream().map(Message::toString).collect(Collectors.toList());
                        System.out.println(String.join("\n", messages));
                    }
                }
                case "/send" -> {if (cli_args.length >= 3) message_server.sendMessageTo(new Message(String.join(" ", Arrays.copyOfRange(cli_args, 2, cli_args.length))), cli_args[1]);}
            }
        }
    }
}
