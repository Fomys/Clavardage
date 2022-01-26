package gui.composants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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
        imgSettings = imgSettings.getScaledInstance(imgSize, imgSize, Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(imgSettings));
    }

}
