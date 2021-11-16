import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;
 
/**
 * Custom renderer to display a country's flag alongside its name
 *
 * @author wwww.codejava.net
 */
public class userRenderer extends JPanel implements ListCellRenderer<user> {
 
	private String name = ""; 
	
	private String police = "Lucida Grande";
	private int taillePolice = 13 ;
	
	public userRenderer(String name, int x, int y, int width) throws IOException {
		this.name = name ; 
		
		//this.setLayout(null); // pour l'absolute position 
		int height = (int) (width * 0.2); // on calcule nous même la hauteur parce que sinon ca fait des betises
		this.setBounds(x,y, width, height);
		
		this.setBackground(new Color(60,60,60));
		this.setForeground(Color.WHITE);
		this.setFont(new Font(police, Font.PLAIN, taillePolice));
		this.setBorder(new LineBorder(new Color(80,80,80)));
		
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
    public Component getListCellRendererComponent(JList<? extends user> list, user name,  int index,
        boolean isSelected, boolean cellHasFocus) {
          
    	
    	
        return this;
    }
     
}