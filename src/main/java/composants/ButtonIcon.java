package composants ; 

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

public class ButtonIcon extends JButton {

	private int imgSize ; // pour stocker la taille de l'image
	
	/*
	 * Paramètres :
	 * 	- String way : le chemin de la photo, ici c'est souvent "./images/nomPhotos.png"
	 * 	- int btnSize : la taille de l'image 
	 */
	public ButtonIcon(String way, int imgSize) throws IOException {
		
		this.imgSize = imgSize ; 
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);
		Image imgSettings = ImageIO.read(getClass().getResource(way));
		imgSettings = imgSettings.getScaledInstance(imgSize, imgSize, imgSettings.SCALE_SMOOTH); 
		this.setIcon(new ImageIcon(imgSettings));
	}
	
	/*
	 * Paramètres :
	 * 	- String way : le chemin de la photo, ici c'est souvent "./images/nomPhotos.png"
	 * 	- int btnSize : la taille de l'image 
	 * 	- x,y,width, height : les dimensions et positions pour le setBounds
	 */
	public ButtonIcon(String way, int imgSize, int x, int y, int width, int height) throws IOException {
		this.imgSize = imgSize ; 
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);
		Image imgSettings = ImageIO.read(getClass().getResource(way));
		imgSettings = imgSettings.getScaledInstance(imgSize, imgSize, imgSettings.SCALE_SMOOTH); 
		this.setIcon(new ImageIcon(imgSettings));
		this.setBounds(x,y,width, height);
	}

	
	/*
	 * Paramètres :
	 * 	- String way : le chemin de la photo, ici c'est souvent "./images/nomPhotos.png"
	 * 	- int btnSize : la taille de l'image 
	 * 	- x,y,size : position et la dimension (parce que carré tavu)
	 * 	- Composant parent 
	 */
	public ButtonIcon(String way, int imgSize, int x, int y) throws IOException {
		this.imgSize = imgSize ; 
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);
		Image imgSettings = ImageIO.read(getClass().getResource(way));
		imgSettings = imgSettings.getScaledInstance(imgSize, imgSize, imgSettings.SCALE_SMOOTH); 
		this.setIcon(new ImageIcon(imgSettings));
		this.setBounds(x,y,imgSize, imgSize);
	}
	
	// Faire une version avec agrandissement quand on passe dessus ? 
	// setRolloverIcon 
	/*  int btnNewUserSize = 25 ; 
		double btnNewUserSize2 = 30 ; 
		Image imgNewUser = ImageIO.read(getClass().getResource("./images/new_user.png"));
		Image imgNewUser2 = imgNewUser.getScaledInstance((int)btnNewUserSize2, (int)btnNewUserSize2, imgNewUser.SCALE_SMOOTH);
		imgNewUser = imgNewUser.getScaledInstance(btnNewUserSize, btnNewUserSize, imgNewUser.SCALE_SMOOTH);  
		
		
		JButton btnNewUser = new JButton();
		btnNewUser.setBounds(287, 44, 50, 50);
		panel_profil.add(btnNewUser);
		btnNewUser.setFont(new Font("Arial", Font.PLAIN, 13));
		btnNewUser.setBorder(BorderFactory.createEmptyBorder());
		btnNewUser.setContentAreaFilled(false);
		btnNewUser.setRolloverIcon(new ImageIcon(imgNewUser2)) ; 
		btnNewUser.setIcon(new ImageIcon(imgNewUser));
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Add user");
				demoList.addElement("OUI");
			}
		}); */ 
	
}
