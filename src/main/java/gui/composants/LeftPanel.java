/*
 * Created by JFormDesigner on Mon Dec 06 09:20:39 CET 2021
 */

package gui.composants;

import database.Database;
import database.DatabaseObserver;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;



/**
 * @author unknown
 */
public class LeftPanel extends JPanel implements DatabaseObserver {
    private JLabel nickname;
    private SearchBar searchBar;
    private JButton btnOtherUser ;
    private JButton btnSettings ;
    private UserList user_list;
    private Database database;
    private PopUpJava pseudoChange;

    public LeftPanel(Database database) throws IOException {
        this.database = database;
        initComponents();
        this.database.addObserver(this);
    }

    private void initComponents() throws IOException {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        this.nickname = new JLabel();
        this.searchBar = new SearchBar(); 
        this.user_list = new UserList(this.database);
		this.btnOtherUser = new ButtonIcon("/write.png", 25);
		this.btnSettings = new ButtonIcon("/settings.png", 25) ;

		nickname.setText(this.database.getNickname());
		nickname.setFont(new Font("Arial", Font.PLAIN, 20));
		nickname.setForeground(new Color(200,200,200)); 
		btnOtherUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Add user");
			}
		});
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("settings pseudo : oublie pas d'ouvrir une popup");
				pseudoChange = new PopUpJava() ; 
			}
		});
        
        this.setLayout(new GridBagLayout());
        ((GridBagLayout)this.getLayout()).columnWidths = new int[] {0, 0, 0, 0};
        ((GridBagLayout)this.getLayout()).rowHeights = new int[] {0, 40, 0, 0};
        ((GridBagLayout)this.getLayout()).columnWeights = new double[] {0.9, 0.05, 0.05, 1.0E-4};
        ((GridBagLayout)this.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0, 1.0E-4};

        add(this.nickname, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 5, 5, 0), 0, 0));
        add(this.btnOtherUser, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));
        add(this.btnSettings, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));
        add(this.searchBar, new GridBagConstraints(0, 1, 3, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 5, 5, 0), 0, 0));
        add(this.user_list, new GridBagConstraints(0, 2, 3, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));
        
        this.setBackground(new Color(44, 43, 42));
    }

    public UserList get_user_list() {
        return this.user_list;
    }

    @Override
    public void on_change_nickname(String nickname) {
        this.nickname.setText(nickname);
    }
}
