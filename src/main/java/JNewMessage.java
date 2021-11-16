
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.JTextComponent;
import java.awt.*; 
import javax.swing.JTextArea; 


public class JNewMessage extends JTextArea {
	
	private String message = "" ; 
	private String horaire = "";
	private String who = ""; 
	public static int hauteur_next = 10 ; 
	
	public JNewMessage(String message, String horaire, String who, Boolean me_sending) {
		
		// on attribue les valeurs
		this.message = message ; 
		this.horaire = horaire ; 
		this.who = who ; 
		
		// on setup la zone de texte
		this.setLineWrap(true);
		this.setText(message);
		this.setEditable(false);
		
		// pour adapter la taille (dépend nb caractère) 
		FontMetrics fm = new JLabel().getFontMetrics(new Font("Arial", Font.PLAIN, 13));
		int width = fm.stringWidth(message)+5 ; 
		int height = (width/440) * 16 + 16;
		if (width>440) {width=440; }
		
		// pour adapter la position et la couleur (dépend de qui l'a envoyé)		
		// on envoie 
		this.setForeground(Color.white); 
		if (me_sending) { // si side vaut true, on met a droite 
			// on la place vers la gauche de l'écran 
			this.setBounds(650 - 30 - width, hauteur_next, width, height);
			this.setBackground(new Color(59, 130, 247));
		}
		else { // on le place a gauche de l'écran en adaptant la taille 
			this.setBounds(10,hauteur_next,width, height);
			this.setBackground(new Color(65,65,65)); 
		}
		
		hauteur_next += height + 10;
		
	}

	public static int getHauteur_next() { // sert pour adapter prefered size scroll panel 
		return hauteur_next;
	}

	
	
	
} 

