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
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * @author unknown
 */
public class MessagesPanel extends JPanel implements DatabaseObserver {
    private final Database database;

    private final String current_user;
    private ArrayList<MessageDisplay> messages;
    
    private JTextPane chatBox; 
    private JScrollPane scrollPane; 


    public MessagesPanel(Database database) {
        this.database = database;
        this.current_user = "alpine-1"; // TODO virer ça et le changer au clic
        this.messages = new ArrayList<>();
        this.database.addObserver(this);
        initComponents();
    }

    private void addMessage(Message message, boolean left) {
    	
    	StyledDocument doc = chatBox.getStyledDocument();  
    	
        SimpleAttributeSet rightO = new SimpleAttributeSet();
        StyleConstants.setAlignment(rightO, StyleConstants.ALIGN_RIGHT);
        StyleConstants.setBackground(rightO, Color.RED);
        StyleConstants.setBackground(rightO, new Color(59,130,247));
        StyleConstants.setForeground(rightO, new Color(255,255,255)); 
        StyleConstants.setRightIndent(rightO, 10);
        StyleConstants.setLeftIndent(rightO, 100);
        
        SimpleAttributeSet leftO = new SimpleAttributeSet();
        StyleConstants.setAlignment(leftO, StyleConstants.ALIGN_LEFT);
        StyleConstants.setBackground(leftO, new Color(60,60,60));
        StyleConstants.setForeground(leftO, new Color(255,255,255));
        StyleConstants.setLeftIndent(leftO, 10);
        StyleConstants.setRightIndent(leftO, 100);
        
        try {
        	if (left) {
        		System.out.println("left"); 
            	chatBox.getDocument().insertString(doc.getLength(), "\n\n"+message.getContent(), rightO);
        	    chatBox.setParagraphAttributes(rightO, false);
        	}
        	else {
        		System.out.println("right"); 
             	chatBox.getDocument().insertString(doc.getLength(), "\n\n"+message.getContent(), leftO);
         	    chatBox.setParagraphAttributes(leftO, false);
        	}
        	
    	    
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	/*
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
                    }
        */
        
        this.revalidate();
    }

    private void initComponents() {

		this.setLayout(new BorderLayout());
    	chatBox = new JTextPane();
        chatBox.setEditable(false);
        scrollPane = new JScrollPane(chatBox);
        this.scrollPane.createVerticalScrollBar();
        
        scrollPane = new JScrollPane(chatBox);
        this.add(scrollPane, BorderLayout.CENTER);
        
    }

    @Override
    public void on_message(Message message) {
        if(message.getFrom().equals(UserList.getCurrentUser().getNickname()) || message.getTo().equals(UserList.getCurrentUser().getNickname())) {
            this.addMessage(message, message.getTo().equals(this.database.getNickname()));
        }
    }
}
