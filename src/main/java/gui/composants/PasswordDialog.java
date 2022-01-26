package gui.composants;

import javax.swing.*;

public class PasswordDialog {
    public static String dialog(JFrame parent, String message, String title) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(message);
        JPasswordField pass = new JPasswordField(10);
        panel.add(label);
        panel.add(pass);
        String[] options = new String[]{"Valider", "Annuler"};
        int option = JOptionPane.showOptionDialog(parent, panel, title, JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if(option == 0) {
            return String.valueOf(pass.getPassword());
        } else {
            return null;
        }

    }
}
