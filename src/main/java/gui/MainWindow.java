package gui;

import database.Database;
import database.DatabaseObserver;
import database.Message;
import gui.composants.MainPanel;
import messages.MessageServer;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.*;

public class MainWindow {

    private final Database database;
    private JFrame main_frame;
    private MainPanel main_panel;
    private MessageServer message_server ; 

    public MainWindow(Database database, MessageServer message_server) throws IOException {
        this.database = database;
        this.message_server = message_server; 
        this.build();
    }

    private void build() throws IOException {
        this.main_frame = new JFrame("Clavardage");
        this.main_panel = new MainPanel(this.database, this.message_server);
        this.main_frame.getContentPane().add(this.main_panel);
        this.main_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.main_frame.pack();
        this.main_frame.setMinimumSize(new Dimension(725, 325));
        this.main_panel.setDoubleBuffered(true);
        this.main_frame.setVisible(true);
    }
}


