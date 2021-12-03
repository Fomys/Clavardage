package gui;

import database.Database;
import database.DatabaseObserver;
import database.Message;

import javax.swing.*;

public class MainWindow {
    private static class Observer implements DatabaseObserver {
        private final JPanel panel;

        public Observer(JPanel panel) {
            this.panel = panel;
        }

        @Override
        public void on_message(Message message) {
            SwingUtilities.invokeLater(() -> {
                JLabel label = new JLabel(message.toString());
                this.panel.add(label);
                this.panel.revalidate();
            });
        }
    }

    private final Database database;
    private JFrame main_frame;
    private JPanel main_panel;

    public MainWindow(Database database) {
        this.database = database;
        this.build();
    }

    private void build() {
        this.main_frame = new JFrame("Main frame");
        this.main_panel = new JPanel();
        this.main_frame.getContentPane().add(this.main_panel);
        this.database.addObserver(new Observer(this.main_panel));
        this.main_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.main_frame.pack();
        this.main_frame.setVisible(true);
    }
}


