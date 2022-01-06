/*
 * Created by JFormDesigner on Fri Dec 03 18:07:24 CET 2021
 */

package gui.composants;

import database.Database;
import messages.MessageServer;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

/**
 * @author unknown
 */
public class MainPanel extends JPanel {
    private LeftPanel left_panel;
    private RightPanel right_panel;
    private Database database;
    private MessageServer message_server;

    public MainPanel(Database database, MessageServer message_server) throws IOException {
        this.database = database;
        this.message_server = message_server ; 
        initComponents();
    }

    private void initComponents() throws IOException {
        this.left_panel = new LeftPanel(this.database);
        this.right_panel = new RightPanel(this.database, this.message_server);


        this.setLayout(new GridBagLayout());
        ((GridBagLayout)this.getLayout()).columnWidths = new int[] {350, 0, 0};
        ((GridBagLayout)this.getLayout()).rowHeights = new int[] {74, 0};
        ((GridBagLayout)this.getLayout()).columnWeights = new double[] {0.0, 1.0, 1.0E-4};
        ((GridBagLayout)this.getLayout()).rowWeights = new double[] {1.0, 1.0E-4};


        this.add(this.left_panel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5,5,5,5), 0, 0));
        this.add(this.right_panel, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5,5,5,5), 0, 0));
        
        this.setBackground(new Color(44, 43, 42));
    }

    public LeftPanel get_left_panel() {
        return this.left_panel;
    }
}
