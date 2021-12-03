import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

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
	
	private SousJText txtPseudonyme ; 
	private SousJText txtDernierMessage ; 
	private SousJText txth ; 
	
	private static ArrayList<JLabelSpe> all_component = new ArrayList<JLabelSpe>();
	
	public JLabelSpe(String name, int x,int y, int width) throws IOException {

		all_component.add(this); 
		
		this.name = name ; 
		
		this.setLayout(null); // pour l'absolute position 
		int height = (int) (width * 0.2); // on calcule nous même la hauteur parce que sinon ca fait des betises
		this.setBounds(x,y, width, height);
		
		this.setBackground(new Color(45,45,45));
		this.setForeground(Color.WHITE);
		this.setFont(new Font(police, Font.PLAIN, taillePolice));
		
		this.setMaximumSize(new Dimension(width,height));
		this.setMinimumSize(getMaximumSize());
		
		ButtonIcon button = new ButtonIcon("./images/profil.png", 30, 20,14); 
		this.add(button);
		
		// on appelle la classe spéciale créée pour économiser le code 
		SousJText txtPseudonyme = new SousJText(this, name, (int) (width*0.2) , (int) (height * 0.05), (int) (width * 0.50), (int) (height/2), true, false, false, true);
		this.add(txtPseudonyme);
		this.txtPseudonyme = txtPseudonyme ; 
		
		SousJText txtDernierMessage = new SousJText(this,"Dernier message..",(int) (width*0.2), (int) (height*0.40), (int) (width * 0.43), (int) (height * 0.43), false, true, false, true);
		this.add(txtDernierMessage);
		this.txtDernierMessage = txtDernierMessage; 
		
		SousJText txth = new SousJText(this, "00h00",(int) (width*0.793), (int) (height*0.10), (int) (width * 0.187), (int) (height * 0.43), false, true, false, true);
		this.add(txth);
		this.txth = txth ; 
		
		JLabelSpe oui = this ; 
		
		this.addMouseListener(new MouseAdapter() {     
	        @Override
	        public void mouseClicked(MouseEvent arg0) {
	        	oui.changeColor();
	        }
	    });
		
		
	}
	
	public void changeColor() {
		for (int i=0; i<this.all_component.size(); i++) {

			Color gris = new Color(45,45,45); 
			JLabelSpe element = all_component.get(i) ; 
			element.setBackground(gris);
			element.txtPseudonyme.setBackground(gris);
			element.txtDernierMessage.setBackground(gris);
			element.txth.setBackground(gris);
			element.txtPseudonyme.setForeground(Color.WHITE);
			element.txtDernierMessage.setForeground(new Color(160,160,160));
			element.txth.setForeground(new Color(160,160,160));
		}
		
		Color blue = new Color(59,130,247) ; 
    	this.setBackground(blue);
    	txtPseudonyme.changeColor();
    	txtDernierMessage.changeColor(); 
    	txth.changeColor();

		this.txtPseudonyme.setForeground(new Color(255,255,255));
		this.txtDernierMessage.setForeground(new Color(230,230,230));
		this.txth.setForeground(new Color(230,230,230));
		
	}
	
	@Override
	public String toString() {
	    return this.name;
	}

}
