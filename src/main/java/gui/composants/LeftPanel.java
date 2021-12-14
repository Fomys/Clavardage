/*
 * Created by JFormDesigner on Mon Dec 06 09:20:39 CET 2021
 */

package gui.composants;

import database.Database;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

/**
 * @author unknown
 */
public class LeftPanel extends JPanel {
    private JTextField search_bar;
    private UserList user_list;
    private Database database;

    public LeftPanel(Database database) throws IOException {
        this.database = database;
        initComponents();
    }

    private void initComponents() throws IOException {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        this.search_bar = new JTextField();
        this.user_list = new UserList(this.database);
        
        this.setLayout(new GridBagLayout());
        ((GridBagLayout)this.getLayout()).columnWidths = new int[] {0, 0};
        ((GridBagLayout)this.getLayout()).rowHeights = new int[] {0, 0, 0};
        ((GridBagLayout)this.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
        ((GridBagLayout)this.getLayout()).rowWeights = new double[] {0.0, 1.0, 1.0E-4};

        add(this.search_bar, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));
        add(this.user_list, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));
    }
}
