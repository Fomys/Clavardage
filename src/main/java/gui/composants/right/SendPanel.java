/*
 * Created by JFormDesigner on Mon Dec 06 13:37:28 CET 2021
 */

package gui.composants.right;

import database.Database;
import database.Message;
import gui.Panel;
import gui.composants.ButtonIcon;
import gui.composants.HintTextArea;
import gui.events.ChangeSelectedUser;
import gui.events.Event;
import gui.events.SendMessage;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.UUID;

public class SendPanel extends JPanel implements Panel {
    private final Database database;
    private final Panel parent;
    private JButton attach_button;
    private JTextArea editor_pane;
    private JScrollPane editor_scroll;
    private JButton send_button;
    private UUID current_user;

    public SendPanel(Panel parent, Database database) {
        this.parent = parent;
        this.database = database;
        this.initComponents();
    }

    protected void initComponents() {
        this.attach_button = new JButton();
        this.editor_scroll = new JScrollPane();
        this.editor_pane = new HintTextArea(" Ecrire un message..");
        this.send_button = new ButtonIcon("/send.png", 25);

        editor_scroll.setBorder(null);
        editor_pane.setBorder(new LineBorder(new Color(150, 150, 150), 2, true));
        this.setBorder(null);
        editor_scroll.setBackground(new Color(30, 30, 30));
        editor_pane.setBackground(new Color(30, 30, 30));
        attach_button.setBackground(new Color(30, 30, 30));
        send_button.setBackground(new Color(30, 30, 30));
        editor_pane.setForeground(Color.white);
        this.setBackground(new Color(30, 30, 30));

        setLayout(new GridBagLayout());
        ((GridBagLayout) getLayout()).columnWidths = new int[]{0, 0, 0, 0};
        ((GridBagLayout) getLayout()).rowHeights = new int[]{0, 0};
        ((GridBagLayout) getLayout()).columnWeights = new double[]{0.05, 0.9, 0.05, 1.0E-4};
        ((GridBagLayout) getLayout()).rowWeights = new double[]{1.0, 1.0E-4};

        this.attach_button.setText("Autre");
        add(this.attach_button, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));


        this.editor_pane.setPreferredSize(null);
        this.editor_pane.setMinimumSize(null);
        this.editor_pane.setLineWrap(true);
        this.editor_scroll.setViewportView(this.editor_pane);
        add(this.editor_scroll, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));


        add(this.send_button, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

        this.send_button.addActionListener(e -> {
            if (this.current_user != null) {
                this.converge_event(new SendMessage(new Message(this.editor_pane.getText()), this.current_user));
            }
        });
    }

    public void propagate_event(Event event) {
        if (event instanceof ChangeSelectedUser) {
            this.current_user = ((ChangeSelectedUser) event).getUUID();
        }
    }

    @Override
    public void converge_event(Event event) {
        this.parent.converge_event(event);
    }
}
