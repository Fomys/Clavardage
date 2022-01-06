package gui.composants;

import javax.swing.*;

public class PopUpJava {
    public PopUpJava() {
        JFrame jFrame = new JFrame();
        String getMessage = JOptionPane.showInputDialog(jFrame, "Entrez votre nouveau pseudo");

        // checker si le pseudo est dispo et soit erreur soit validation 
        JOptionPane.showMessageDialog(jFrame, "Votre nouveau pseudo est : "+getMessage);
    }
}