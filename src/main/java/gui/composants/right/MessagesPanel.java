/*
 * Created by JFormDesigner on Mon Dec 06 14:32:52 CET 2021
 */

package gui.composants.right;

import database.Database;
import database.Message;
import gui.MainWindow;
import gui.events.ChangeSelectedUser;
import gui.events.Event;
import gui.Panel;
import gui.events.ReceiveMessage;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.UUID;

public class MessagesPanel extends JPanel implements Panel {
    private final Database database;
    private UUID current_uuid;
    private JTextPane messages_box;
    private JScrollPane scrollPane;
    private Panel parent;


    public MessagesPanel(Panel parent, Database database) {
        this.parent = parent;
        this.database = database;
        this.current_uuid = null;
        this.initComponents();
    }

    private void addMessage(Message message, boolean left) {
        StyledDocument doc = messages_box.getStyledDocument();

        SimpleAttributeSet centrO = new SimpleAttributeSet();
        StyleConstants.setForeground(centrO, MainWindow.DARK_GRAY);
        StyleConstants.setFontSize(centrO, 8);

        SimpleAttributeSet hour = new SimpleAttributeSet();
        StyleConstants.setAlignment(hour, StyleConstants.ALIGN_CENTER);

        SimpleAttributeSet rightO = new SimpleAttributeSet();
        StyleConstants.setBackground(rightO, MainWindow.BLUE_MESSAGE);
        StyleConstants.setForeground(rightO, MainWindow.WHITE);
        StyleConstants.setFontSize(rightO, 14);

        SimpleAttributeSet blue = new SimpleAttributeSet();
        StyleConstants.setAlignment(blue, StyleConstants.ALIGN_RIGHT);
        StyleConstants.setRightIndent(blue, 10);
        StyleConstants.setLeftIndent(blue, 100);

        SimpleAttributeSet leftO = new SimpleAttributeSet();
        StyleConstants.setBackground(leftO, MainWindow.DARK_MESSAGE);
        StyleConstants.setForeground(leftO, MainWindow.WHITE);
        StyleConstants.setFontSize(leftO, 14);


        SimpleAttributeSet gray = new SimpleAttributeSet();
        StyleConstants.setAlignment(gray, StyleConstants.ALIGN_LEFT);
        StyleConstants.setLeftIndent(gray, 10);
        StyleConstants.setRightIndent(gray, 100);


        try {
            doc.insertString(doc.getLength(), "\n\n" + message.getDate(), centrO);

            if (left) {
                doc.setParagraphAttributes(doc.getLength() - message.getDate().toString().length(), doc.getLength(), blue, false);
                doc.insertString(doc.getLength(), "\n" + message.getContent(), rightO);
                doc.setParagraphAttributes(doc.getLength() - message.getContent().length(), doc.getLength(), blue, false);
            } else {
                doc.setParagraphAttributes(doc.getLength() - message.getDate().toString().length(), doc.getLength(), gray, false);
                doc.insertString(doc.getLength(), "\n" + message.getContent(), leftO);
                doc.setParagraphAttributes(doc.getLength() - message.getContent().length(), doc.getLength(), gray, false);
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        this.revalidate();
    }

    protected void initComponents() {
        this.setLayout(new BorderLayout());
        this.messages_box = new JTextPane();
        this.messages_box.setEditable(false);
        this.scrollPane = new JScrollPane(messages_box);
        this.scrollPane.createVerticalScrollBar();

        this.scrollPane = new JScrollPane(messages_box);
        this.add(this.scrollPane, BorderLayout.CENTER);
        this.scrollPane.setBackground(MainWindow.BLACK);
        this.messages_box.setBackground(MainWindow.BLACK);
    }

    @Override
    public void propagate_event(Event event) {
        if (event instanceof ChangeSelectedUser && !((ChangeSelectedUser) event).getUUID().equals(this.current_uuid)) {
            this.current_uuid = ((ChangeSelectedUser) event).getUUID();
            this.messages_box.setText(null);
            for(Message message: Message.AllBetween(this.database.getConnection(), this.current_uuid, this.database.getUUID())) {
                this.addMessage(message, message.getTo().equals(this.current_uuid));
            }
        } else if (event instanceof ReceiveMessage &&
                ((((ReceiveMessage) event).getMessage().getFrom().equals(this.current_uuid) && ((ReceiveMessage) event).getMessage().getTo().equals(this.database.getUUID())) ||
                (((ReceiveMessage) event).getMessage().getTo().equals(this.current_uuid) && ((ReceiveMessage) event).getMessage().getFrom().equals(this.database.getUUID())))) {
            this.addMessage(((ReceiveMessage) event).getMessage(), ((ReceiveMessage) event).getMessage().getTo().equals(this.current_uuid));
        }
    }

    @Override
    public void converge_event(Event event) {
        this.parent.converge_event(event);
    }
}
