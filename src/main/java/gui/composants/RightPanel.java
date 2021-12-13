/*
 * Created by JFormDesigner on Mon Dec 06 13:47:58 CET 2021
 */

package gui.composants;

import database.Database;

import javax.swing.*;

/**
 * @author unknown
 */
public class RightPanel extends JPanel {
    private final Database database;
    private JSplitPane split_pane;
    private SendPanel send_panel;
    private MessagesPanel messages_panel;

    public RightPanel(Database database) {
        this.database = database;
        initComponents();
    }

    private void initComponents() {
        this.split_pane = new JSplitPane();
        this.messages_panel = new MessagesPanel(this.database);
        this.send_panel = new SendPanel();

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        this.split_pane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        this.split_pane.setResizeWeight(0.8);

        this.split_pane.setTopComponent(this.messages_panel);
        this.split_pane.setBottomComponent(this.send_panel);
        this.add(this.split_pane);
    }
}
