import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JLabelSpe extends JPanel{
	
	private String name = ""; 
	
	private String police = "Lucida Grande";
	private int taillePolice = 13 ; 
	
	public JLabelSpe(String name, int x,int y, int width) throws IOException {

		this.name = name ; 
		
		this.setLayout(null); // pour l'absolute position 
		int height = (int) (width * 0.2); // on calcule nous même la hauteur parce que sinon ca fait des betises
		this.setBounds(x,y, width, height);
		
		this.setBackground(new Color(60,60,60));
		this.setForeground(Color.WHITE);
		this.setFont(new Font(police, Font.PLAIN, taillePolice));
		
		ButtonIcon button = new ButtonIcon("./images/profil.png",(int) (width * 0.133),(int) (height * 0.133),(int) (width * 0.0167),(int) (height * 0.833), this); 
		this.add(button);
		
		// on appelle la classe spéciale créée pour économiser le code 
		SousJText txtPseudonyme = new SousJText(this, name, (int) (width*0.2) , (int) (height * 0.1), (int) (width * 0.50), (int) (height/2), true);
		this.add(txtPseudonyme);
		
		SousJText txtDernierMessage = new SousJText(this,"Dernier message..",(int) (width*0.2), (int) (height*0.5), (int) (width * 0.43), (int) (height * 0.43), false, true);
		this.add(txtDernierMessage);
		
		SousJText txth = new SousJText(this, "00h00",(int) (width*0.793), (int) (height*0.15), (int) (width * 0.187), (int) (height * 0.43), false, true);
		this.add(txth);
		
		
	}
	
	@Override
	public String toString() {
	    return this.name;
	}

}
