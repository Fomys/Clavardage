import diffusion.Cache;
import diffusion.Diffusion;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

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
        Cache cache = new Cache();
        Diffusion diffusion = new Diffusion(10000, cache);
        diffusion.start();

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
                    running=false;
                }
                case "/connected" -> System.out.println(cache.getConnected());
                case "/directory" -> System.out.println(cache.getDirectory());
            }
        }
    }
}
