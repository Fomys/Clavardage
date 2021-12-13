/*
 * Created by JFormDesigner on Mon Dec 06 13:37:28 CET 2021
 */

package gui.composants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.*;

import database.Message;
import messages.MessageServer;

/**
 * @author unknown
 */
public class SendPanel extends JPanel {
    private JButton attach_button;
    private JTextArea editor_pane;
    private JScrollPane editor_scroll;
    private JButton send_button;
    private MessageServer message_server ; 

    public SendPanel(MessageServer message_server) {
    	this.message_server = message_server ; 
        initComponents();
    }

    private void initComponents() {
        this.attach_button = new JButton();
        this.editor_scroll = new JScrollPane();
        this.editor_pane = new JTextArea();
        this.send_button = new JButton();
        

        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 1.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {1.0, 1.0E-4};

        this.attach_button.setText("Joindre");
        add(this.attach_button, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 5), 0, 0));
        

        this.editor_pane.setPreferredSize(null);
        this.editor_pane.setMinimumSize(null);
        this.editor_pane.setLineWrap(true);
        this.editor_scroll.setViewportView(this.editor_pane);
        add(this.editor_scroll, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 5), 0, 0));

        this.send_button.setText("Envoyer");
        add(this.send_button, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));
        this.send_button.addActionListener(e->{
			try {
				this.message_server.sendMessageTo(new Message("bonjour"), "test");
			} catch (IOException e1) {
				// TODO Afficher l'erreur proprement 
				e1.printStackTrace();
			}
		
	});
    }

}
