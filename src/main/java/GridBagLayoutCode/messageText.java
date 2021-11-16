import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class messageText {

	private String hour = ""; 
	private String message = "" ; 
	
	public messageText(String hour, String message) {
		
		this.hour = hour ; 
		this.message = message ; 
		
	}
	
	public JPanel createComponentGauche() {
		GridBagLayout gbl_panel_conv = new GridBagLayout();
	    GridBagConstraints contraintes;
	    JPanel panel = new JPanel();
		panel.setLayout(gbl_panel_conv); 
		
		JTextArea heure = new JTextArea(this.hour); 
	    heure.setEnabled(false);
	    JTextArea texte = new JTextArea(this.message);
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
	    
	    return panel; 
	}
	
	public JPanel createComponentDroite() {
		GridBagLayout gbl_panel_conv = new GridBagLayout();
	    GridBagConstraints contraintes;
	    JPanel panel = new JPanel();
		panel.setLayout(gbl_panel_conv); 
		
		JTextArea heure = new JTextArea(this.hour); 
	    heure.setEnabled(false);
	    JTextArea texte = new JTextArea(this.message);
		texte.setLineWrap(true);
	    
	    contraintes = new GridBagConstraints();
	    contraintes.gridx = 0;
	    contraintes.gridy = 0;
	    contraintes.gridwidth = 2;
	    contraintes.gridheight = 2;
	    contraintes.fill = GridBagConstraints.BOTH;
	    contraintes.weightx = 1;
	    contraintes.weighty = 1;
	    gbl_panel_conv.setConstraints(texte, contraintes);
	    panel.add(texte);
	    
	    contraintes = new GridBagConstraints();
	    contraintes.gridx = 2;
	    contraintes.gridy = 1;
	    gbl_panel_conv.setConstraints(heure, contraintes);
	    panel.add(heure); 
	    
	    return panel; 
	}
	
	
	
}
