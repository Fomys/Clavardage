import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class message_droite {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					message_droite window = new message_droite();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public message_droite() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 300, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    GridBagLayout gbl_panel_conv = new GridBagLayout();
	    GridBagConstraints contraintes;
	    JPanel panel = new JPanel();
		panel.setLayout(gbl_panel_conv); 
		
		JTextArea heure = new JTextArea("11h42"); 
	    heure.setEnabled(false);
	    JTextArea texte = new JTextArea();
		texte.setLineWrap(true);
	    
	    contraintes = new GridBagConstraints();
	    contraintes.gridx = 0;
	    contraintes.gridy = 1;
	    gbl_panel_conv.setConstraints(heure, contraintes);
	    panel.add(heure); 

	    contraintes = new GridBagConstraints();
	    contraintes.gridx = 1;
	    contraintes.gridy = 0;
	    contraintes.gridwidth = 2;
	    contraintes.gridheight = 2;
	    contraintes.fill = GridBagConstraints.BOTH;
	    contraintes.weightx = 1;
	    contraintes.weighty = 1;
	    gbl_panel_conv.setConstraints(texte, contraintes);
	    panel.add(texte);
	    

		frame.getContentPane().add(panel);
		
	}

}
