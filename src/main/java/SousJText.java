import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;

public class SousJText extends JTextField{
	
	private JComponent parent ; 
	private JLabelSpe parentLabel ; 
	
	/*
	 * Paramètre : 
	 * 	- JComponent parent : composant sur lequel on va récupérer la couleur de fond / de devant et la police histoire d'économiser un peu de place 
	 */
	public SousJText(JComponent parent) {
		this.parent = parent ; 
		this.setEditable(false);
		this.setBorder(new LineBorder(parent.getBackground()));
		this.setBackground(parent.getBackground());
		this.setForeground(parent.getForeground());
		this.setFont(parent.getFont());
		this.setBorder(null);
	}

	/*
	 * Paramètres : 
	 * 	- JComponent parent : composant sur lequel on va récupérer la couleur de fond / de devant et la police histoire d'économiser un peu de place 
	 * 	- String text : le texte qui sera affiché dur l'élément
	 */
	public SousJText(JComponent parent, String texte) {
		this.parent = parent ; 
		this.setEditable(false);
		this.setBorder(new LineBorder(parent.getBackground()));
		this.setBackground(parent.getBackground());
		this.setForeground(parent.getForeground());
		this.setFont(parent.getFont());
		this.setText(texte);
		this.setBorder(null);
	}

	/*
	 * Paramètres : 
	 * 	- JComponent parent : composant sur lequel on va récupérer la couleur de fond / de devant et la police histoire d'économiser un peu de place 
	 * 	- String text : le texte qui sera affiché dur l'élément
	 * 	- x,y,width, height : les dimensions et positions pour le setBounds
	 */
	public SousJText(JComponent parent, String texte, int x, int y, int width, int height) {
		this.parent = parent ; 
		this.setEditable(false);
		this.setBorder(new LineBorder(parent.getBackground()));
		this.setBackground(parent.getBackground());
		this.setForeground(parent.getForeground());
		this.setFont(parent.getFont());
		this.setText(texte);
		this.setBorder(null);
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
		this.parent = parent ; 
		if (plain) {this.setFont(new Font(parent.getFont().getFontName(), Font.BOLD, parent.getFont().getSize()));}
		this.setEditable(false);
		this.setBorder(new LineBorder(parent.getBackground()));
		this.setBackground(parent.getBackground());
		this.setForeground(parent.getForeground());
		this.setText(texte);
		this.setBorder(null);
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
		this.parent = parent ; 
		if (plain) {this.setFont(new Font(parent.getFont().getFontName(), Font.BOLD, parent.getFont().getSize()));}
		this.setEditable(false);
		this.setBorder(new LineBorder(parent.getBackground()));
		this.setBackground(parent.getBackground());
		if(gray) {this.setForeground(new Color(160,160,160));}
		else {this.setForeground(parent.getForeground());}
		this.setText(texte);
		this.setBorder(null);
		this.setBounds(x,y,width,height);
	}
	
	/*
	 * Paramètres : 
	 * 	- JComponent parent : composant sur lequel on va récupérer la couleur de fond / de devant et la police histoire d'économiser un peu de place 
	 * 	- String text : le texte qui sera affiché dur l'élément
	 * 	- x,y,width, height : les dimensions et positions pour le setBounds
	 * 	- boolean plain : si true, alors le texte est en gras 
	 * 	- boolean gray : si on veut le texte en gris 
	 * 	- boolean add : si on veut l'ajouter direct au parent 
	 */
	public SousJText(JComponent parent, String texte, int x, int y, int width, int height, boolean plain, boolean gray, boolean add) {
		this.parent = parent ; 
		if (plain) {this.setFont(new Font(parent.getFont().getFontName(), Font.BOLD, parent.getFont().getSize()));}
		this.setEditable(false);
		this.setBorder(new LineBorder(parent.getBackground()));
		this.setBackground(parent.getBackground());
		if(gray) {this.setForeground(new Color(160,160,160));}
		else {this.setForeground(parent.getForeground());}
		this.setText(texte);
		this.setBounds(x,y,width,height);
		this.setHighlighter(null);
		this.setBorder(null);
		if (add) {parent.add(this); }
		
	}
	
	
	public SousJText(JLabelSpe parent, String texte, int x, int y, int width, int height, boolean plain, boolean gray, boolean add, boolean mouse) {
		this.parentLabel = parent ; 
		if (plain) {this.setFont(new Font(parent.getFont().getFontName(), Font.BOLD, parent.getFont().getSize()));}
		this.setEditable(false);
		this.setBorder(new LineBorder(parent.getBackground()));
		this.setBackground(parent.getBackground());
		if(gray) {this.setForeground(new Color(160,160,160));}
		else {this.setForeground(parent.getForeground());}
		this.setText(texte);
		this.setBounds(x,y,width,height);
		this.setHighlighter(null);
		this.setBorder(null);
		
		SousJText oui = this ; 
		this.addMouseListener(new MouseAdapter() {     
	        @Override
	        public void mouseClicked(MouseEvent arg0) {
	        	oui.parentLabel.changeColor(); 
	        }
	    });
		
	}
	
	
	
	public void changeColor() {
		this.setBackground(new Color(59,130,247));
	}	
	/*
	 * Fonction qui donne le y du prochain composant pour pas qu'ils se marchent dessus 
	 */
	public int getNextHauteur() {
		System.out.println(this.getBounds().getHeight() + " " +  this.getBounds().getY());
		return (int) (this.getBounds().getHeight() + this.getBounds().getY()) ; 
	}
	
	/*
	 * Pour récupérer la hauteur du parent  
	 */
	public int getHauteurParent() {
		return this.parent.getHeight() ;
	}	
	
	/*
	 * Pour avoir la hauteur max possible 
	 */
	public int getHauteurPoss() {	
		return getHauteurParent() - getNextHauteur();
	}

}
