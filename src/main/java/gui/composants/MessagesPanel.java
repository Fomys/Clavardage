/*
 * Created by JFormDesigner on Mon Dec 06 14:32:52 CET 2021
 */

package gui.composants;

import database.Database;
import database.DatabaseObserver;
import database.Message;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import javax.swing.*;

/**
 * @author unknown
 */
public class MessagesPanel extends JPanel implements DatabaseObserver {
    private final Database database;

    private final String current_user;
    private ArrayList<MessageDisplay> messages;

    private JPanel internal_panel;
    private JScrollPane scroll_pane;

    public MessagesPanel(Database database) {
        this.database = database;
        this.current_user = "alpine-1"; // TODO virer ça et le changer au clic
        this.messages = new ArrayList<>();
        this.database.addObserver(this);
        initComponents();
    }

    private void addMessage(Message message, boolean left) {
        MessageDisplay message_display = new MessageDisplay(message, left);

        if(this.messages.isEmpty()) {
            this.messages.add(message_display);
            this.internal_panel.add(message_display, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0), 0);
        } else {
            int i = 0;
            while(i < this.messages.size() && this.messages.get(i).getMessage().getDate().before(message.getDate())) { i += 1; }

            this.messages.add(i, message_display);
            this.internal_panel.add(message_display, new GridBagConstraints(0, i, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0), i);


//            ((GridBagLayout)this.internal_panel.getLayout()).rowHeights = ((GridBagLayout)this.internal_panel.getLayout()).rowHeights;
//            ((GridBagLayout)this.internal_panel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
        }
        this.revalidate();
    }

    private void initComponents() {
        this.internal_panel = new JPanel();
        this.scroll_pane = new JScrollPane(this.internal_panel);
        this.scroll_pane.createVerticalScrollBar();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.internal_panel.setLayout(new GridBagLayout());

        ((GridBagLayout)this.internal_panel.getLayout()).columnWidths = new int[] {0, 0};
        ((GridBagLayout)this.internal_panel.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};

        
        this.add(this.scroll_pane);
    }

    @Override
    public void on_message(Message message) {
        if(message.getFrom().equals(UserList.getCurrentUser().getNickname()) || message.getTo().equals(UserList.getCurrentUser().getNickname())) {
            this.addMessage(message, message.getTo().equals(this.database.getNickname()));
        }
    }

    @Override
    public void on_connect_user(String username) {

    }

    @Override
    public void on_disconnect_user(String username) {

    }
}
