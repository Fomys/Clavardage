import java.awt.EventQueue;
import java.awt.Image;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.BoxLayout;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class mainTest {
	
	public class MyRoundJPanel extends JPanel {

	    private static final long serialVersionUID = 1L;

	    private Shape shape;

	    public MyRoundJPanel() {
	        super();
	        setOpaque(false);
	    }

	    protected void paintComponent(Graphics g) {
	         g.setColor(getBackground());
	         g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 30, 30);
	         super.paintComponent(g);
	    }
	    protected void paintBorder(Graphics g) {
	         g.setColor(Color.black);
	         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 30,30);
	    }
	    protected void repaintBorder(Graphics g, boolean focus) {
	    	System.out.println("oui");
	    	if (focus) {
	         g.setColor(Color.red);
	         }
	    	else {

		         g.setColor(Color.blue);
	    	}
	         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 30,30);
	    }
	    public boolean contains(int x, int y) {
	         if (shape == null || !shape.getBounds().equals(getBounds())) {
	             shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 30,30);
	         }
	         return shape.contains(x, y);
	    }
	    
	}
	
	public class MyRoundJText extends JTextField  {

	    private static final long serialVersionUID = 1L;

	    private Shape shape;

	    public MyRoundJText() {
	        super();
	        this.setBackground(Color.black);
	        this.setForeground(new Color(160,160,160));
	    }

	    protected void paintComponent(Graphics g) {
	         g.setColor(getBackground());
	         g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 30, 30);
	         super.paintComponent(g);
	    }
	    protected void paintBorder(Graphics g) {
	         g.setColor(new Color(150,150,150));
	         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 30,30);
	    }
	    protected void repaintBorder(Graphics g, boolean focus) {
	    	System.out.println("oui");
	    	if (focus) {
	         g.setColor(Color.red);
	         }
	    	else {

		         g.setColor(Color.blue);
	    	}
	         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 30,30);
	    }
	    public boolean contains(int x, int y) {
	         if (shape == null || !shape.getBounds().equals(getBounds())) {
	             shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 30,30);
	         }
	         return shape.contains(x, y);
	    }
	    
	}
	
	public JPanel PanelTexte() {
		JPanel p = new JPanel() {
		     @Override
		     protected void paintComponent(Graphics g) {
		    	int arc_size = 25 ; 
		        super.paintComponent(g);
		        Dimension arcs = new Dimension(arc_size,arc_size);
		        int width = getWidth();
		        int height = getHeight();
		        Graphics2D graphics = (Graphics2D) g;
		        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		        //Draws the rounded opaque panel with borders.
		        graphics.setColor(getBackground());
		        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height*2);//paint background
		        graphics.setColor(getForeground());

		        System.out.println(width + " "+  height);
		        graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height*2);//paint border
		     }
		  };
		  return p ; 
	}

	private JFrame frame;
	private JTable table;
	private JTextField txtPseudonyme;
	private JTextField txtDernierMessage;
	private JTextField txth;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainTest window = new mainTest();
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
	public mainTest() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		
		JPanel p = PanelTexte() ;
		  p.setOpaque(false);
		  p.setBounds(500,500,200,30);
		frame.getContentPane().add(p); 
		
		MyRoundJPanel teste = new MyRoundJPanel();  
		teste.setBounds(500,200,200,30); 
		teste.setBackground(Color.WHITE);
		frame.getContentPane().add(teste);
		
		
		JButton button = new JButton("button");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teste.repaintBorder(teste.getGraphics(), true);
			}
		});
		
		JButton button2 = new JButton("button");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teste.repaintBorder(teste.getGraphics(), false);
			}
		});
		
		button.setBounds(10,10,100,100);
		button.setBackground(Color.yellow); 
		frame.getContentPane().add(button); 
		
		button2.setBounds(150,10,100,100);
		button2.setBackground(Color.yellow); 
		frame.getContentPane().add(button2); 
		
		MyRoundJText text = new MyRoundJText(); 
		text.setBounds(50,500,100,30);
		frame.getContentPane().add(text); 
		
		
		
	}
	
	

}



	
