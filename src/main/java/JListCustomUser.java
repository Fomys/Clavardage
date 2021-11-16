import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
 

public class JListCustomUser {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JListCustomUser window = new JListCustomUser();
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
	public JListCustomUser() throws IOException {
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
		
		user u1 = new user("Pierre", "bonjour", "00h00"); 
		user u2 = new user("Louis", "Saluuuut ", "00h01"); 
		
		DefaultListModel<user> listModel = new DefaultListModel<>(); 
		listModel.addElement(u1);
		listModel.addElement(u2);
		
		JList<user> userList = new JList<>(listModel); 
		userList.setCellRenderer(new userRenderer("Pierre", 27, 50, 300));
		frame.getContentPane().add(new JScrollPane(userList)); 
		
		
		
	}

}



 
