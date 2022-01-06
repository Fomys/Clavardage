package gui.composants;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class SearchBar extends JPanel {
	
    private JButton btnSearchIcon ;
    private JButton btnCrossIcon ;
    private JTextField txtSearchUser ; 
	
	public SearchBar() throws IOException {
		
		
		
		initComponents();
    }

    private void initComponents() throws IOException {
    	
		this.setBorder(new LineBorder(new Color(100, 100, 100),2,true));
		this.setBackground(new Color(44,43,42)); 
		
		txtSearchUser = new HintTextField("Rechercher utilisateur");
		txtSearchUser.setBackground(new Color(45,45,45));
		txtSearchUser.setBorder(null); 
		txtSearchUser.setForeground(new Color(150,150,150));

		this.btnSearchIcon = new ButtonIcon("./../images/search.png", 18);
		btnSearchIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSearchUser.requestFocus(); 
			}
		});
		
		this.btnCrossIcon = new ButtonIcon("./../images/cross.png", 18);
		btnCrossIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSearchUser.setText("");
				txtSearchUser.requestFocus();
			}
		});
		

		
		 this.setLayout(new GridBagLayout());
        ((GridBagLayout)this.getLayout()).columnWidths = new int[] {0, 0, 0, 0};
        ((GridBagLayout)this.getLayout()).rowHeights = new int[] {0, 0};
        ((GridBagLayout)this.getLayout()).columnWeights = new double[] {0.05, 0.9, 0.05, 1.0E-4};
        ((GridBagLayout)this.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0, 1.0E-4};
    	
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

}
