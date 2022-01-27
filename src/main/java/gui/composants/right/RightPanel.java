/*
 * Created by JFormDesigner on Mon Dec 06 13:47:58 CET 2021
 */

package gui.composants.right;

import database.Database;
import gui.MainWindow;
import gui.Panel;
import gui.events.Event;

import javax.swing.*;

public class RightPanel extends JPanel implements Panel {
    private final Database database;
    private final Panel parent;
    private JSplitPane split_pane;
    private SendPanel send_panel;
    private MessagesPanel messages_panel;

    public RightPanel(Panel parent, Database database) {
        this.parent = parent;
        this.database = database;
        this.initComponents();
    }

    protected void initComponents() {
        this.setBackground(MainWindow.BLACK);
        this.setBorder(null);

        this.split_pane = new JSplitPane();
        this.messages_panel = new MessagesPanel(this, this.database);
        this.send_panel = new SendPanel(this, this.database);

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        this.split_pane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        this.split_pane.setResizeWeight(0.8);

        this.split_pane.setTopComponent(this.messages_panel);
        this.split_pane.setBottomComponent(this.send_panel);
        this.add(this.split_pane);

        this.split_pane.setBackground(MainWindow.BLACK);
        this.messages_panel.setBorder(null);
        this.send_panel.setBorder(null);
    }

    public void propagate_event(Event event) {
        this.messages_panel.propagate_event(event);
        this.send_panel.propagate_event(event);
    }

    public void converge_event(Event event) {
        this.parent.converge_event(event);
    }
}
