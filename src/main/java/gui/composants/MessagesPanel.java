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
    	
    	SimpleAttributeSet centrO = new SimpleAttributeSet();
        StyleConstants.setForeground(centrO, new Color(150,150,150));
        StyleConstants.setFontSize(centrO, 8);
        
        SimpleAttributeSet hour = new SimpleAttributeSet();
       StyleConstants.setAlignment(hour, StyleConstants.ALIGN_CENTER);
    	
        SimpleAttributeSet rightO = new SimpleAttributeSet();
        StyleConstants.setBackground(rightO, new Color(59,130,247));
        StyleConstants.setForeground(rightO, new Color(255,255,255)); 
        StyleConstants.setFontSize(rightO, 14);
       
        
        SimpleAttributeSet blue = new SimpleAttributeSet(); 
        StyleConstants.setAlignment(blue, StyleConstants.ALIGN_RIGHT);
        StyleConstants.setRightIndent(blue, 10);
        StyleConstants.setLeftIndent(blue, 100);
        
        SimpleAttributeSet leftO = new SimpleAttributeSet();
        StyleConstants.setBackground(leftO, new Color(60,60,60));
        StyleConstants.setForeground(leftO, new Color(255,255,255));
        StyleConstants.setFontSize(leftO, 14);
        
        
        SimpleAttributeSet gray = new SimpleAttributeSet(); 
        StyleConstants.setAlignment(gray, StyleConstants.ALIGN_LEFT);
        StyleConstants.setLeftIndent(gray, 10);
        StyleConstants.setRightIndent(gray, 100);
        
        
        try {
        	doc.insertString(doc.getLength(), "\n\n"+message.getDate(), centrO);
        	
        	if (message.getContent().charAt(0) == '0') {
        		doc.setParagraphAttributes(doc.getLength() - message.getDate().toString().length(), doc.getLength() , blue, false);
            	doc.insertString(doc.getLength(), "\n"+message.getContent(), rightO);
        	    doc.setParagraphAttributes(doc.getLength() - message.getContent().length(), doc.getLength(), blue, false);
        	}
        	else {
        		doc.setParagraphAttributes(doc.getLength() - message.getDate().toString().length(), doc.getLength() , gray, false);
             	doc.insertString(doc.getLength(), "\n"+message.getContent(), leftO);
         	    doc.setParagraphAttributes(doc.getLength() - message.getContent().length(), doc.getLength(), gray, false);
        	}
        	
    	    
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        
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
        
        scrollPane.setBackground(new Color(30,30,30));
        chatBox.setBackground(new Color(30,30,30));
        
        
    }

    @Override
    public void on_message(Message message) {
        if(message.getFrom().equals(UserList.getCurrentUser().getNickname()) || message.getTo().equals(UserList.getCurrentUser().getNickname())) {
            this.addMessage(message, message.getTo().equals(this.database.getNickname()));
        }
    }
}
