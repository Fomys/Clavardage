package gui.composants.left;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class ProfilePicture extends JLabel {

    private final int imgSize; // pour stocker la taille de l'image

    /*
     * Paramètres :
     * 	- String way : le chemin de la photo, ici c'est souvent "./images/nomPhotos.png"
     * 	- int btnSize : la taille de l'image
     */
    public ProfilePicture(String way, int imgSize) {
        this.imgSize = imgSize;
        this.setBorder(BorderFactory.createEmptyBorder());
        Image imgSettings = null;
        try {
            imgSettings = ImageIO.read(Objects.requireNonNull(getClass().getResource(way)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert imgSettings != null;
        imgSettings = imgSettings.getScaledInstance(imgSize, imgSize, Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(imgSettings));
    }


}
