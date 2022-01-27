/*
 * Created by JFormDesigner on Mon Dec 06 09:20:39 CET 2021
 */

package gui.composants.left;

import database.Database;
import gui.composants.ButtonIcon;
import gui.composants.PopUpNickname;
import gui.events.Event;
import gui.Panel;

import javax.swing.*;
import java.awt.*;


/**
 * @author unknown
 */
public class LeftPanel extends JPanel implements Panel {
    private final Panel parent;
    private final Database database;
    private NicknameDisplay nickname_display;
    private JButton button_edit_nickname;
    private UserList user_list;

    public LeftPanel(Panel parent, Database database) {
        this.parent = parent;
        this.database = database;
        initComponents();
    }

    protected void initComponents() {
        this.setBackground(new Color(44, 43, 42));

        this.nickname_display = new NicknameDisplay(this, this.database);
        this.user_list = new UserList(this, this.database);
        this.button_edit_nickname = new ButtonIcon("/settings.png", 25);

        this.setLayout(new GridBagLayout());
        ((GridBagLayout) this.getLayout()).columnWidths = new int[]{0, 0, 0};
        ((GridBagLayout) this.getLayout()).rowHeights = new int[]{0, 40, 0};
        ((GridBagLayout) this.getLayout()).columnWeights = new double[]{0.9, 0.05, 1.0E-4};
        ((GridBagLayout) this.getLayout()).rowWeights = new double[]{0.0, 1.0, 1.0E-4};

        add(this.nickname_display, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 5, 5, 0), 0, 0));
        add(this.button_edit_nickname, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));
        add(this.user_list, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

        this.button_edit_nickname.addActionListener(e -> new PopUpNickname(this, database));
    }

    public void propagate_event(Event event) {
        this.user_list.propagate_event(event);
        this.nickname_display.propagate_event(event);
    }

    public void converge_event(Event event) {
        this.parent.converge_event(event);
    }
}
