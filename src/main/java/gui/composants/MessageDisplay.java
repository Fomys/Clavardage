/*
 * Created by JFormDesigner on Mon Dec 06 14:44:17 CET 2021
 */

package gui.composants;

import database.Message;

import java.awt.*;
import javax.swing.*;

/**
 * @author unknown
 */
public class MessageDisplay extends JPanel {
    private final Boolean left;
    private Message message;
    public MessageDisplay(Message message, Boolean left) {
        this.message = message;
        this.left = left;
        initComponents();
    }

    public Message getMessage() {return this.message;}

    private void initComponents() {
        panel2 = new JTextArea(this.message.getContent());

        this.setPreferredSize(new Dimension(0,400));
        
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0};
        if(left) {
            ((GridBagLayout) getLayout()).columnWeights = new double[]{0.8, 0.2, 1.0E-4};
        } else {
            ((GridBagLayout) getLayout()).columnWeights = new double[]{0.2, 0.8, 1.0E-4};
        }
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

        if(left) {
            add(panel2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 0, 0), 0, 0));
        } else {
            add(panel2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
            new Insets(0, 0, 0, 0), 0, 0));
        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JTextArea panel2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
