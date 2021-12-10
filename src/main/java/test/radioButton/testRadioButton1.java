package test.radioButton;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class testRadioButton1 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testRadioButton1 window = new testRadioButton1();
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
	public testRadioButton1() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JRadioButton Button = new JRadioButton("New radio button");
		Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Button.isSelected()) {System.out.println("oui");} 
				else {
				System.out.println("NOZDNZLKJ"); }
			}
		});
		Button.setBounds(54, 99, 141, 23);
		frame.getContentPane().add(Button);
		//Button.setLayout(null);

		

		Image btnSendMsgImg = ImageIO.read(getClass().getResource("./../../images/send.png"));
		int btnSendMsgImgSize = 30 ; 
		//btnSendMsg.setContentAreaFilled(false);
		//btnSendMsg.setBorder(BorderFactory.createEmptyBorder());
		btnSendMsgImg = btnSendMsgImg.getScaledInstance(btnSendMsgImgSize, btnSendMsgImgSize, btnSendMsgImg.SCALE_SMOOTH); 
		Button.setIcon(new ImageIcon(btnSendMsgImg));
	    Button.setBounds(10,10,200,30); 
		Button.setLayout(null); 
		JTextArea prout = new JTextArea("oui");
		prout.setEditable(false);
		Button.add(prout);
		prout.setBounds(10,10,10,10);
		Button.setBackground(Color.black); 
		
		
		
		
	}
}
