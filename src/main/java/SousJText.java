import java.awt.Color;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;

public class SousJText extends JTextField{
	
	/*
	 * Paramètre : 
	 * 	- JComponent parent : composant sur lequel on va récupérer la couleur de fond / de devant et la police histoire d'économiser un peu de place 
	 */
	public SousJText(JComponent parent) {
		this.setEditable(false);
		this.setBorder(new LineBorder(parent.getBackground()));
		this.setBackground(parent.getBackground());
		this.setForeground(parent.getForeground());
		this.setFont(parent.getFont());
	}

	/*
	 * Paramètres : 
	 * 	- JComponent parent : composant sur lequel on va récupérer la couleur de fond / de devant et la police histoire d'économiser un peu de place 
	 * 	- String text : le texte qui sera affiché dur l'élément
	 */
	public SousJText(JComponent parent, String texte) {
		this.setEditable(false);
		this.setBorder(new LineBorder(parent.getBackground()));
		this.setBackground(parent.getBackground());
		this.setForeground(parent.getForeground());
		this.setFont(parent.getFont());
		this.setText(texte);
	}

	/*
	 * Paramètres : 
	 * 	- JComponent parent : composant sur lequel on va récupérer la couleur de fond / de devant et la police histoire d'économiser un peu de place 
	 * 	- String text : le texte qui sera affiché dur l'élément
	 * 	- x,y,width, height : les dimensions et positions pour le setBounds
	 */
	public SousJText(JComponent parent, String texte, int x, int y, int width, int height) {
		this.setEditable(false);
		this.setBorder(new LineBorder(parent.getBackground()));
		this.setBackground(parent.getBackground());
		this.setForeground(parent.getForeground());
		this.setFont(parent.getFont());
		this.setText(texte);
		this.setBounds(x,y,width,height);
	}
	
	/*
	 * Paramètres : 
	 * 	- JComponent parent : composant sur lequel on va récupérer la couleur de fond / de devant et la police histoire d'économiser un peu de place 
	 * 	- String text : le texte qui sera affiché dur l'élément
	 * 	- x,y,width, height : les dimensions et positions pour le setBounds
	 * 	- boolean plain : si true, alors le texte est en gras 
	 */
	public SousJText(JComponent parent, String texte, int x, int y, int width, int height, boolean plain) {
		if (plain) {this.setFont(new Font(parent.getFont().getFontName(), Font.BOLD, parent.getFont().getSize()));}
		this.setEditable(false);
		this.setBorder(new LineBorder(parent.getBackground()));
		this.setBackground(parent.getBackground());
		this.setForeground(parent.getForeground());
		this.setText(texte);
		this.setBounds(x,y,width,height);
		
	
	}
	
	/*
	 * Paramètres : 
	 * 	- JComponent parent : composant sur lequel on va récupérer la couleur de fond / de devant et la police histoire d'économiser un peu de place 
	 * 	- String text : le texte qui sera affiché dur l'élément
	 * 	- x,y,width, height : les dimensions et positions pour le setBounds
	 * 	- boolean plain : si true, alors le texte est en gras 
	 * 	- boolean gray : si on veut le texte en gris 
	 */
	public SousJText(JComponent parent, String texte, int x, int y, int width, int height, boolean plain, boolean gray) {
		if (plain) {this.setFont(new Font(parent.getFont().getFontName(), Font.BOLD, parent.getFont().getSize()));}
		this.setEditable(false);
		this.setBorder(new LineBorder(parent.getBackground()));
		this.setBackground(parent.getBackground());
		this.setForeground(new Color(160,160,160));
		this.setText(texte);
		this.setBounds(x,y,width,height);
		
	
	}

}
