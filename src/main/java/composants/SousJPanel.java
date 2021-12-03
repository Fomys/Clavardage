package composants ; 

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class SousJPanel extends JPanel{
	
	private JPanel parent ; 
	
	/*
	 * Paramètre : 
	 * 	- JComponent parent : composant sur lequel on va récupérer la couleur de fond / de devant et la police histoire d'économiser un peu de place 
	 */
	public SousJPanel(JPanel parent) {
		this.parent = parent ; 
		this.setBorder(new LineBorder(parent.getBackground()));
		this.setBackground(parent.getBackground());
		this.setForeground(parent.getForeground());
		this.setFont(parent.getFont());
		this.setLayout(parent.getLayout());
	}
	
	/*
	 * Paramètre : 
	 * 	- JComponent parent : composant sur lequel on va récupérer la couleur de fond / de devant et la police histoire d'économiser un peu de place
	 * 	- int x, int y, int height, int width : on set position et taille, si ca vaut -1, alors on fill entièrement le parent 
	 */
	public SousJPanel(JPanel parent, int x, int y, int width, int height) {
		this.parent = parent ; 
		this.setBorder(new LineBorder(parent.getBackground()));
		this.setBackground(parent.getBackground());
		this.setForeground(parent.getForeground());
		this.setFont(parent.getFont());
		this.setLayout(parent.getLayout());
		if (height == -1) {height = parent.getHeight();}
		if (width == -1) {width = parent.getWidth();}
		this.setBounds(x,y,height,width);
	}
	
	/*
	 * Paramètre : 
	 * 	- JComponent parent : composant sur lequel on va récupérer la couleur de fond / de devant et la police histoire d'économiser un peu de place
	 * 	- int x, int y, int height, int width : on set position et taille, si ca vaut -1, alors on fill entièrement le parent
	 * 	- boolean add : si c'est true, on le rajoute dans le parent directement  
	 */
	public SousJPanel(JPanel parent, int x, int y, int width, int height, boolean add) {
		this.parent = parent ; 
		this.setBorder(new LineBorder(parent.getBackground()));
		this.setBackground(parent.getBackground());
		this.setForeground(parent.getForeground());
		this.setFont(parent.getFont());
		this.setLayout(parent.getLayout());
		if (height == -1) {height = parent.getHeight();} // on lui donne la hauteur restante et non la hauteur max 
		if (width == -1) {width = parent.getWidth();}
		this.setBounds(x,y,width,height);
		if (add) {parent.add(this);} 
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