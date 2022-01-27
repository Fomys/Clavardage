import database.Database;
import database.Message;
import diffusion.Diffusion;
import gui.MainWindow;
import messages.MessageServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database();
        database.applyMigrations();


        Diffusion diffusion = new Diffusion(database);
        MessageServer message_server = new MessageServer(10001, database);


        diffusion.start();
        message_server.start();
        MainWindow window = new MainWindow(database, message_server, diffusion);
    }
}
