package gui.composants;

import database.Database;

import javax.swing.*;

public class PopUpJava {
    public PopUpJava(Database database) {
        JFrame jFrame = new JFrame();
        String getMessage = JOptionPane.showInputDialog(jFrame, "Entrez votre nouveau pseudo");
        database.setNickname(getMessage);
        // checker si le pseudo est dispo et soit erreur soit validation 
        JOptionPane.showMessageDialog(jFrame, "Votre nouveau pseudo est : " + getMessage);

    }
}