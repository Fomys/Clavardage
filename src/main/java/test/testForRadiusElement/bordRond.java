package test.testForRadiusElement ; 
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

import javax.swing.plaf.ScrollBarUI;
import javax.swing.plaf.basic.*;



public class bordRond {


	private JFrame frame;
	public JPanel p ; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bordRond window = new bordRond();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public bordRond() throws IOException {
		initialize();
	}

	
	public int arc_size = 15 ; 
	public int height_box = 100; 
	public int width_box = 200; 
	
	public class PanelRond extends JPanel {
		
	}

	/*
	 * Pour créer panel arrondi 
	 */
	public JPanel PanelTexte() {
		JPanel p = new JPanel() {
		     @Override
		     protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        Dimension arcs = new Dimension(arc_size,arc_size);
		        int width = getWidth();
		        int height = getHeight();
		        Graphics2D graphics = (Graphics2D) g;
		        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		        //Draws the rounded opaque panel with borders.
		        graphics.setColor(getBackground());
		        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width*2, arcs.height*2);//paint background
		        graphics.setColor(Color.blue);
		        graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width*2, arcs.height*2);//paint border
		     }
		  };
		  return p ; 
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
			
		frame = new JFrame();
	    JFrame frame = new JFrame("Rounded corner text filed demo");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //JTextField field = new RoundJTextField(15);
	    //frame.add(field);
	    //frame.setVisible(true);
	    
	    //field.setForeground(Color.red);

	  frame.getContentPane().setLayout(null);
	  frame.setSize(500, 500);
	  
	  JPanel p = PanelTexte() ; 
	  p.setBounds(16,6,width_box,height_box);
	  p.setOpaque(false);
	  frame.getContentPane().setBackground(Color.red);
	  frame.getContentPane().setLayout(null);
	  frame.getContentPane().add(p);
	  p.setLayout(null);
	  
	  JTextArea textArea = new JTextArea();
	  JScrollPane scrollMessages = new JScrollPane(textArea); 
	  p.add(scrollMessages);
	  textArea.setLineWrap(true);
	  textArea.setBackground(new Color(238,238,238));
	  scrollMessages.setBackground(new Color(238,238,238));
	  scrollMessages.setBounds(6,6,187,80);
	  scrollMessages.setBorder(new LineBorder(new Color(238,238,238)));
	  
	  JButton btnNewButton = new JButton("New button");
	  btnNewButton.addActionListener(new ActionListener() {
	  	public void actionPerformed(ActionEvent e) {
	  		System.out.println("Click "); 
	  		p.setBorder(new LineBorder(new Color(0,238,238), 4, true));
	  	}
	  });
	  btnNewButton.setBounds(323, 307, 117, 29);
	  frame.getContentPane().add(btnNewButton);
	  
	  JButton btnNewButton_1 = new JButton("New button");
	  btnNewButton_1.setBounds(134, 324, 117, 29);
	  frame.getContentPane().add(btnNewButton_1);
	  
	  // scrollMessages.getVerticalScrollBar().setUI(new ScrollBarUI () {}); // scroll bar invisible mais qui marche 
	 
	  frame.setVisible(true);
		
		
	}
}
