package gui.composants;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ButtonIcon extends JButton {

	/*
	 * Paramètres :
	 * 	- String way : le chemin de la photo, ici c'est souvent "./images/nomPhotos.png"
	 * 	- int btnSize : la taille de l'image 
	 * 	- x,y,size : position et la dimension (parce que carré tavu)
	 * 	- Composant parent 
	 */
	public ButtonIcon(String way, int imgSize) throws IOException {
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);
		Image imgSettings = ImageIO.read(getClass().getResource(way));
		imgSettings = imgSettings.getScaledInstance(imgSize, imgSize, imgSettings.SCALE_SMOOTH); 
		this.setIcon(new ImageIcon(imgSettings));
	}
	
}
