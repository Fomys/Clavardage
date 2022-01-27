package gui.composants.left;

import database.Database;
import gui.Panel;
import gui.events.ChangeNickname;
import gui.events.Event;

import javax.swing.*;
import java.awt.*;


public class NicknameDisplay extends JLabel implements Panel {
    private final Database database;
    private final Panel parent;

    public NicknameDisplay(Panel parent, Database database) {
        super();
        this.parent = parent;
        this.database = database;
        this.initComponents();
    }

    private void initComponents() {
        this.setFont(new Font("Arial", Font.PLAIN, 20));
        this.setForeground(new Color(200, 200, 200));

        this.setText(this.database.getNickname());
    }

    @Override
    public void propagate_event(Event event) {
        if (event instanceof ChangeNickname && ((ChangeNickname) event).getUUID() == this.database.getUUID()) {
            this.setText(((ChangeNickname) event).getNickname());
        }
    }

    @Override
    public void converge_event(Event event) {
        this.parent.converge_event(event);
    }
}
