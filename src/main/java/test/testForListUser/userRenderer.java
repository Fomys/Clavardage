package test.testForListUser ; 
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
public class userRenderer extends JLabel implements ListCellRenderer<user> {
	
	private String police = "Lucida Grande";
	private int taillePolice = 13 ;
	
    @Override
    public Component getListCellRendererComponent(JList<? extends user> list, user user,  int index,
        boolean isSelected, boolean cellHasFocus) {
        
    	int width = 300 ; 
    	int x = 27 ; 
    	int y = 50 ; 
    	
    	int height = (int) (width * 0.2); // on calcule nous même la hauteur parce que sinon ca fait des betises
		this.setLayout(null);
		this.setPreferredSize(new Dimension(width, height));
		this.setLocation(x, y);
		
		this.setBackground(new Color(60,60,60));
		this.setForeground(Color.WHITE);
		this.setFont(new Font(police, Font.PLAIN, taillePolice));
		this.setBorder(new LineBorder(new Color(80,80,80)));
		
		/*
		ButtonIcon button = null;
		try {
			button = new ButtonIcon("./images/profil.png",30, 20,14);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		this.add(button);
		
		// on appelle la classe spéciale créée pour économiser le code 
		SousJText txtPseudonyme = new SousJText(this, user.getName(), (int) (width*0.2) , (int) (height * 0.1), (int) (width * 0.50), (int) (height/2), true);
		this.add(txtPseudonyme);
		
		SousJText txtDernierMessage = new SousJText(this,user.getLastMsg(),(int) (width*0.2), (int) (height*0.5), (int) (width * 0.43), (int) (height * 0.43), false, true);
		this.add(txtDernierMessage);
		
		SousJText txth = new SousJText(this, user.getHourlast(),(int) (width*0.793), (int) (height*0.15), (int) (width * 0.187), (int) (height * 0.43), false, true);
		this.add(txth);
		*/
    	
        return this;
        

    }
     
}