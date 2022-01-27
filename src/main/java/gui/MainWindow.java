package gui;

import database.Database;
import database.DatabaseObserver;
import database.Message;
import diffusion.Diffusion;
import gui.composants.MainPanel;
import gui.composants.PopUpLogin;
import gui.events.*;
import gui.events.Event;
import messages.MessageServer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

public class MainWindow extends JPanel implements Panel, DatabaseObserver {
    public static Color BLUE_MESSAGE = new Color(59, 130, 247);
    public static Color GREEN = Color.GREEN;
    public static Color RED = Color.RED;
    public static Color BLACK = new Color(44, 43, 42);
    public static Color WHITE = Color.WHITE;
    public static Color BLUE = new Color(59, 130, 247);
    public static Color DARK_MESSAGE = new Color(60, 60, 60);
    public static Color DARK_GRAY = new Color(160, 160, 160);
    public static Color LIGHT_GRAY = new Color(230, 230, 230);


    private final Diffusion diffusion;
    private final MessageServer message_server;
    private final Database database;
    private JFrame main_frame;
    private MainPanel main_panel;

    public MainWindow(Database database, MessageServer message_server, Diffusion diffusion) {
        this.database = database;
        this.message_server = message_server;
        this.diffusion = diffusion;
        this.database.addObserver(this);
        this.initComponents();
    }

    private void initComponents() {
        this.main_frame = new JFrame("Clavardage");
        this.main_panel = new MainPanel(this, this.database);
        this.main_frame.getContentPane().add(this.main_panel);
        this.main_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.main_frame.pack();
        this.main_frame.setMinimumSize(new Dimension(725, 325));
        this.main_panel.setDoubleBuffered(true);
        this.main_frame.setVisible(true);

        Boolean connect = PopUpLogin.dialog(this.database, this.diffusion);
        if (!connect) {
            try {
                this.quit();
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void quit() throws IOException, SQLException {
        this.message_server.quit();
        this.diffusion.quit();
        this.database.quit();
        this.main_frame.dispose();
    }

    @Override
    public void propagate_event(gui.events.Event event) {
        System.out.println("Dispatch " + event.getClass());
        this.main_panel.propagate_event(event);

       if (event instanceof ChangeSelectedUser) {
            this.main_frame.setTitle("Clavardage - " + this.database.getNicknameFor(((ChangeSelectedUser) event).getUUID()));
            this.message_server.requestMessagesSince(new Date(0), ((ChangeSelectedUser) event).getUUID(), this.database.getUUID());
        }
    }

    public void converge_event(Event event) {
        if(event instanceof SendMessage) {
            this.message_server.sendMessageTo(((SendMessage) event).getMessage(), ((SendMessage) event).getTo());
        } else if (event instanceof ChangeNickname) {
            this.diffusion.diffuse_nickname(((ChangeNickname) event).getNickname());
            this.database.setNickname(((ChangeNickname) event).getNickname());
        } else
            this.propagate_event(event);
    }

    public void on_change_nickname(UUID uuid, String nickname) {
        this.propagate_event(new ChangeNickname(uuid, nickname));
    }

    public void on_connect_user(UUID uuid) {
        this.propagate_event(new ConnectedUser(uuid));
    }

    public void on_disconnect_user(UUID uuid) {
        this.propagate_event(new DisconnectUser(uuid));
    }

    public void on_message(Message message) {
        this.propagate_event(new ReceiveMessage(message));
    }
}


