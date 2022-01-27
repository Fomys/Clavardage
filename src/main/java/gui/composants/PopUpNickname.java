package gui.composants;

import database.Database;
import gui.Panel;
import gui.events.ChangeNickname;
import gui.events.Event;

import javax.swing.*;

public class PopUpNickname extends JPanel implements Panel {
    private final Database database;
    private final Panel parent;

    public PopUpNickname(Panel parent, Database database) {
        this.parent = parent;
        this.database = database;

        JFrame jFrame = new JFrame();

        String new_nickname = JOptionPane.showInputDialog(jFrame, "Entrez votre nouveau pseudo");
        if (database.checkNickname(new_nickname)) {
            this.converge_event(new ChangeNickname(this.database.getUUID(), new_nickname));
        } else {
            JOptionPane.showMessageDialog(jFrame, "Ce pseudo est déjà pris");
        }
    }

    @Override
    public void propagate_event(Event event) {
    }

    @Override
    public void converge_event(Event event) {
        this.parent.converge_event(event);
    }
}