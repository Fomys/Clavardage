import java.awt.EventQueue;
import java.awt.Image;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JPanel;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;

public class mainTest {

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
		frame.getContentPane().setLayout(null);
		
		
		
		DefaultListModel demoList = new DefaultListModel(); // liste des utilisateurs 
		
		JPanel test = new JLabelSpe("User 1", 27, 50, 300);
		JPanel test2 = new JLabelSpe("User 2", 27, 120, 300);
		
	
		frame.getContentPane().add(test); 
		frame.getContentPane().add(test2); 
		
		
		
	}
}
