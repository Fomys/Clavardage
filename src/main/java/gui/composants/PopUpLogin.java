package gui.composants;

import database.Database;
import diffusion.Diffusion;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class PopUpLogin {
    private static String ask_username(JFrame parent) {
        String username = "";
        while (username != null && username.equals("")) {
            username = JOptionPane.showInputDialog(parent, "Entrez votre identifiant :");
            if (username != null && username.equals("")) {
                JOptionPane.showMessageDialog(parent, "Vous devez entrer un identifiant !");
            }
        }
        return username;
    }

    private static String ask_password(JFrame parent) {
        String password = "";
        while (password != null && password.equals("")) {
            password = PasswordDialog.dialog(parent, "Entrez votre mot de passe :", "Mot de passe");
            if (password != null && password.equals("")) {
                JOptionPane.showMessageDialog(parent, "Vous devez entrer un mot de passe !");
            }
        }
        return password;
    }

    private static String ask_create_password(JFrame parent) {
        String password = "";
        String password_confirm = null;
        while (!password.equals(password_confirm)) {
            while (password.equals("")) {
                password = ask_password(parent);
                if (password == null) {
                    return null;
                }
            }
            password_confirm = "";
            while (password_confirm.equals("")) {
                password_confirm = ask_password(parent);
                if (password_confirm == null) {
                    return null;
                }
            }
            if (!password.equals(password_confirm)) {
                JOptionPane.showMessageDialog(parent, "Vous devez entrer deux mots de passe identiques !");
                password = "";
                password_confirm = null;
            }
        }
        return password;
    }


    public static Boolean dialog(Database database, Diffusion diffusion) {
        try {
            diffusion.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JFrame parent = new JFrame();
        while (database.getUUID() == null) {
            String username = ask_username(parent);
            if (username == null) {
                return false;
            }

            if (database.checkUser(username)) {
                String password = ask_password(parent);
                if (password == null) {
                    return false;
                }
                Boolean success = null;
                try {
                    success = database.set_user(username, password);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                if (!success) {
                    JOptionPane.showMessageDialog(parent, "Mot de passe invalide !");
                } else {
                    try {
                        diffusion.diffuse_uuid(database.getUUID(), diffusion.getBROADCAST_ADDRESS());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    diffusion.diffuse_nickname(username);
                }
            } else {
                if (JOptionPane.showConfirmDialog(
                        parent,
                        "Cet utilisateur n'existe sur aucun des postes connecté, voulez-vous le créer ?",
                        "Création de compte",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE) == JOptionPane.OK_OPTION) {
                    String password = ask_create_password(parent);
                    if (password != null) {
                        database.User user = new database.User(username, password);
                        try {
                            user.saveTo(database.getConnection());
                            database.set_user(username, password);
                            diffusion.diffuse_new_user(user, diffusion.getBROADCAST_ADDRESS());
                            diffusion.diffuse_uuid(user.getUUID(), diffusion.getBROADCAST_ADDRESS());
                        } catch (SQLException | IOException throwables) {
                            throwables.printStackTrace();
                        }
                        diffusion.diffuse_nickname(username);
                    }
                }
            }
//
//            if (database.checkUser(username)) {
//                String password = null;
//                while (password == null || password.equals("")) {
//                    password = PasswordDialog.dialog(parent, "Entrez votre mot de passe :", "Mot de passe");
//                    if (password == null) {
//                        continue;
//                    } else if (password.equals("")) {
//                        JOptionPane.showMessageDialog(parent, "Vous devez entrer un mot de passe !");
//                    }
//                }
//            } else {
//                int create = JOptionPane.showConfirmDialog(
//                        parent,
//                        "Cet utilisateur n'existe sur aucun des postes connecté, voulez-vous le créer ?",
//                        "Création de compte",
//                        JOptionPane.YES_NO_OPTION,
//                        JOptionPane.WARNING_MESSAGE);
//                if(create == JOptionPane.OK_OPTION) {
//                String password = null;
//                String check_password = null;
//                while(password == null || !password.equals(check_password)) {
//                    password = null;
//                    check_password = null;
//                    while(password == null) {
//                        password = PasswordDialog.dialog(parent, "Entrez votre mot de passe :", "Mot de passe");
//                    }
//                    while(check_password == null) {
//                        check_password = PasswordDialog.dialog(parent, "Confirmez votre mot de passe :", "Mot de passe");
//                    }
//                }
//                try {
//                    database.createUser(username, password);
//                    database.setUser(username, password);
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//            }
//        }}
        }
        return true;
    }
}