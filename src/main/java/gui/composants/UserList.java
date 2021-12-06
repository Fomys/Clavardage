/*
 * Created by JFormDesigner on Mon Dec 06 10:30:23 CET 2021
 */

package gui.composants;

import database.Database;
import database.DatabaseObserver;
import database.Message;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.*;

/**
 * @author unknown
 */
public class UserList extends JPanel implements DatabaseObserver {
    private JScrollPane scroll_pane;
    private JPanel internal_panel;
    private HashMap<String, JLabelSpe> users;
    private Database database;

    public UserList(Database database) {
        this.users = new HashMap<>();
        this.database = database;
        this.database.addObserver(this);
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        this.internal_panel = new JPanel();
        this.scroll_pane = new JScrollPane(this.internal_panel);
        this.scroll_pane.createVerticalScrollBar();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.internal_panel.setLayout(new BoxLayout(this.internal_panel, BoxLayout.Y_AXIS));
        this.add(this.scroll_pane);
    }

    @Override
    public void on_message(Message message) {}

    @Override
    public void on_new_user(String username) {
        JLabelSpe new_button = null;
        try {
            new_button = new JLabelSpe(username, 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.users.put(username, new_button);
        this.internal_panel.add(new_button);
        this.validate();
    }

    @Override
    public void on_connect_user(String username) {
        if(!this.users.containsKey(username)) {
            this.on_new_user(username);
        }
    }

    @Override
    public void on_disconnect_user(String username) {}
}
