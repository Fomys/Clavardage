/*
 * Created by JFormDesigner on Fri Dec 03 18:07:24 CET 2021
 */

package gui.composants;

import database.Database;
import gui.Panel;
import gui.composants.left.LeftPanel;
import gui.composants.right.RightPanel;
import gui.events.Event;

import javax.swing.*;
import java.awt.*;

/**
 * @author unknown
 */
public class MainPanel extends JPanel implements Panel {
    private final Panel parent;
    private final Database database;
    private LeftPanel left_panel;
    private RightPanel right_panel;

    public MainPanel(Panel parent, Database database) {
        this.parent = parent;
        this.database = database;
        initComponents();
    }

    public void initComponents() {
        this.setBackground(new Color(44, 43, 42));

        this.left_panel = new LeftPanel(this, this.database);
        this.right_panel = new RightPanel(this, this.database);

        this.setLayout(new GridBagLayout());
        ((GridBagLayout) this.getLayout()).columnWidths = new int[]{350, 0, 0};
        ((GridBagLayout) this.getLayout()).rowHeights = new int[]{74, 0};
        ((GridBagLayout) this.getLayout()).columnWeights = new double[]{0.0, 1.0, 1.0E-4};
        ((GridBagLayout) this.getLayout()).rowWeights = new double[]{1.0, 1.0E-4};

        this.add(this.left_panel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        this.add(this.right_panel, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
    }

    public void propagate_event(Event event) {
        this.left_panel.propagate_event(event);
        this.right_panel.propagate_event(event);
    }

    @Override
    public void converge_event(Event event) {
        this.parent.converge_event(event);
    }
}
