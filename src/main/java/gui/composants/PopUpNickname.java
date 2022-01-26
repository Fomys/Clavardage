package gui.composants;

import database.Database;
import diffusion.Diffusion;

import javax.swing.*;
import java.io.IOException;

public class PopUpNickname {
    public PopUpNickname(Database database, Diffusion diffusion) {
        JFrame jFrame = new JFrame();

        String getMessage = JOptionPane.showInputDialog(jFrame, "Entrez votre nouveau pseudo");
        try {
            diffusion.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (database.checkNickname(getMessage)) {
            database.setNickname(getMessage);
        } else {
            JOptionPane.showMessageDialog(jFrame, "Ce pseudo est déjà pris");
        }
    }
}