package test.testForList ; 
 
import java.awt.Component;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.ListCellRenderer;
 
/**
 * Custom renderer to display a country's flag alongside its name
 *
 * @author wwww.codejava.net
 */
public class CountryRenderer extends JPanel implements ListCellRenderer<Country> {
 
	public CountryRenderer() {
        setOpaque(true);
    }
	
    @Override
    public Component getListCellRendererComponent(JList<? extends Country> list, Country country, int index,
        boolean isSelected, boolean cellHasFocus) {
          
        String code = country.getCode();
        
        // to load and resize the image 
		Image imgSettings = null;
		try {
			imgSettings = ImageIO.read(getClass().getResource("./images/" + code + "1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imgSettings = imgSettings.getScaledInstance(25, 25, imgSettings.SCALE_SMOOTH); 
         
		// create the button and put the image on it
        JButton buttontest = new JButton() ; 
        buttontest.setIcon(new ImageIcon(imgSettings));
        add(buttontest); 
        
        // create the text (name of the country) 
        JTextField txtest = new JTextField(); 
        txtest.setText(country.getName());
        add(txtest); 
        
         
        return this;
    }
     
}