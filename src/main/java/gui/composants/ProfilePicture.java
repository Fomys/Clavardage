package gui.composants;

import javax.swing.*;
import java.io.IOException;

public class ProfilePicture extends JLabel {

    private final int imgSize; // pour stocker la taille de l'image

    /*
     * Paramètres :
     * 	- String way : le chemin de la photo, ici c'est souvent "./images/nomPhotos.png"
     * 	- int btnSize : la taille de l'image
     */
    public ProfilePicture(String way, int imgSize) throws IOException {

        this.imgSize = imgSize;
        this.setBorder(BorderFactory.createEmptyBorder());
        //this.setContentAreaFilled(false);
        //Image imgSettings = ImageIO.read(getClass().getResource(way));
        //imgSettings = imgSettings.getScaledInstance(imgSize, imgSize, imgSettings.SCALE_SMOOTH);
        //this.setIcon(new ImageIcon(imgSettings));
    }


}
