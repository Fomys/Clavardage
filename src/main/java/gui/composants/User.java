package gui.composants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class User extends JPanel{

	private String nickname ; 
	private String police = "Lucida Grande";
	private int taillePolice = 13 ; 
	
	private JLabel label1;
    private JLabel label2;
    private JLabel label4;
    private JLabel label3;
	
	public User (String nickname){ 
		this.nickname = nickname ; 
		
		this.setBackground(new Color(45,45,45));
		this.setForeground(Color.WHITE);
		this.setFont(new Font(police, Font.PLAIN, taillePolice));
		
		this.setMaximumSize(new Dimension (350,50));
		
		initComponents();
		
		
	}
	
	private void initComponents() {
		label1 = new JLabel();
        label2 = new JLabel();
        label4 = new JLabel();
        label3 = new JLabel();

        //======== this ========
        
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {50, 200, 50, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {20, 30, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 1.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};
        

        //---- label1 ----
        label1.setText("img");
        add(label1, new GridBagConstraints(0, 0, 1, 2, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 5), 0, 0));

        //---- label2 ----
        label2.setText("pseudo");
        add(label2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label4 ----
        label4.setText("date");
        add(label4, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- label3 ----
        label3.setText("messadslqkdjldkjqlkdjqsldkjqslkdjqslkdjqslkdjqslkdjqslkdjqlkjdqslkdjqsge");
        add(label3, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 5), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }


    
	
	
}