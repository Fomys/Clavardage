import database.Database;
import diffusion.Diffusion;
import gui.MainWindow;
import messages.MessageServer;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database();
        database.applyMigrations();


        MessageServer message_server = new MessageServer(10001, database);
        Diffusion diffusion = new Diffusion(database, message_server);


        diffusion.start();
        message_server.start();
        MainWindow window = new MainWindow(database, message_server, diffusion);
    }
}
