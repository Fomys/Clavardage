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
    private HashMap<String, User> users;
    private Database database;
    
    private static User current_user ; 

    public UserList(Database database) throws IOException {
        this.users = new HashMap<>();
        this.database = database;
        this.database.addObserver(this);
        initComponents();
    }

    private void initComponents() {
        this.internal_panel = new JPanel();
        this.scroll_pane = new JScrollPane(this.internal_panel);
        this.scroll_pane.createVerticalScrollBar();
        this.scroll_pane.createHorizontalScrollBar(); 
        this.scroll_pane.setBorder(null);
        //this.scroll_pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.internal_panel.setLayout(new BoxLayout(this.internal_panel, BoxLayout.Y_AXIS));
        this.add(this.scroll_pane);
        
        internal_panel.setBackground(new Color(44, 43, 42));
    }


    
    private void push_up(User user) {
        this.internal_panel.setComponentZOrder(user, 0);
        this.validate();
    }


    @Override
    public void on_message(Message message) {
        // TODO: vérifier que c'est pas cassé
        this.push_up(this.users.get(message.getFrom()));
    }

    @Override
    public void on_connect_user(String username) throws IOException {
        if(!this.users.containsKey(username)) {
            User new_user;
            new_user = new User(username, this.database);
            this.users.put(username, new_user);
            this.internal_panel.add(new_user);
            // TODO pour les tests avec plusieurs utilisateurs : 
           //this.internal_panel.add(new User("oui"));
        }
        this.push_up(this.users.get(username));
    }

    @Override
    public void on_disconnect_user(String username) {}

	public static User getCurrentUser() {
		return current_user;
	}

	public static void setCurrentUser(User current_user_u) {
		current_user = current_user_u;
	}
}
