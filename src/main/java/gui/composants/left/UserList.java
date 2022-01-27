/*
 * Created by JFormDesigner on Mon Dec 06 10:30:23 CET 2021
 */

package gui.composants.left;

import database.Database;
import gui.Panel;
import gui.events.ConnectedUser;
import gui.events.Event;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.UUID;

public class UserList extends JPanel implements Panel {
    private final HashMap<UUID, User> users;
    private final Database database;
    private final Panel parent;
    private JScrollPane scroll_pane;
    private JPanel internal_panel;

    public UserList(Panel parent, Database database) {
        this.parent = parent;
        this.database = database;
        this.users = new HashMap<>();
        initComponents();
    }

    protected void initComponents() {
        this.internal_panel = new JPanel();
        this.internal_panel.setBackground(new Color(44, 43, 42));

        this.scroll_pane = new JScrollPane(this.internal_panel);
        this.scroll_pane.createVerticalScrollBar();
        this.scroll_pane.createHorizontalScrollBar();
        this.scroll_pane.setBorder(null);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.internal_panel.setLayout(new BoxLayout(this.internal_panel, BoxLayout.Y_AXIS));

        this.add(this.scroll_pane);
    }

    public void propagate_event(Event event) {
        for (User user :
                this.users.values()) {
            user.propagate_event(event);
        }
        if (event instanceof ConnectedUser && !this.users.containsKey(((ConnectedUser) event).getUUID())) {
            User new_user = new User(this, this.database, ((ConnectedUser) event).getUUID());
            this.add(new_user);
            this.users.put(((ConnectedUser) event).getUUID(), new_user);
        }
    }

    @Override
    public void converge_event(Event event) {
        this.parent.converge_event(event);
    }
}
