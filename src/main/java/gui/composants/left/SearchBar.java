package gui.composants.left;

import database.Database;
import gui.Panel;
import gui.composants.ButtonIcon;
import gui.composants.HintTextField;
import gui.events.Event;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SearchBar extends JPanel implements gui.Panel {
    private final Panel parent;
    private final Database database;
    private JButton btnSearchIcon;
    private JButton btnCrossIcon;
    private JTextField txtSearchUser;

    public SearchBar(Panel parent, Database database) {
        this.parent = parent;
        this.database = database;
    }

    protected void initComponents() {

        this.setBorder(new LineBorder(new Color(100, 100, 100), 2, true));
        this.setBackground(new Color(44, 43, 42));

        txtSearchUser = new HintTextField("Rechercher utilisateur");
        txtSearchUser.setBackground(new Color(45, 45, 45));
        txtSearchUser.setBorder(null);
        txtSearchUser.setForeground(new Color(150, 150, 150));

        this.btnSearchIcon = new ButtonIcon("/search.png", 18);
        btnSearchIcon.addActionListener(e -> txtSearchUser.requestFocus());

        this.btnCrossIcon = new ButtonIcon("/cross.png", 18);
        btnCrossIcon.addActionListener(e -> {
            txtSearchUser.setText("");
            txtSearchUser.requestFocus();
        });


        this.setLayout(new GridBagLayout());
        ((GridBagLayout) this.getLayout()).columnWidths = new int[]{0, 0, 0, 0};
        ((GridBagLayout) this.getLayout()).rowHeights = new int[]{0, 0};
        ((GridBagLayout) this.getLayout()).columnWeights = new double[]{0.05, 0.9, 0.05, 1.0E-4};
        ((GridBagLayout) this.getLayout()).rowWeights = new double[]{0.0, 0.0, 1.0, 1.0E-4};

        add(btnSearchIcon, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        add(txtSearchUser, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        add(btnCrossIcon, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
    }

    @Override
    public void propagate_event(Event event) {
    }

    @Override
    public void converge_event(Event event) {
        this.parent.converge_event(event);
    }

}
