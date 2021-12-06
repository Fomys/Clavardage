package gui;

import database.Database;
import database.DatabaseObserver;
import database.Message;
import gui.composants.MainPanel;

import javax.swing.*;

public class MainWindow {

    private final Database database;
    private JFrame main_frame;
    private JPanel main_panel;

    public MainWindow(Database database) {
        this.database = database;
        this.build();
    }

    private void build() {
        this.main_frame = new JFrame("Clavardage");
        this.main_panel = new MainPanel(this.database);
        this.main_frame.getContentPane().add(this.main_panel);
        this.main_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.main_frame.pack();
        this.main_frame.setVisible(true);
    }
}


