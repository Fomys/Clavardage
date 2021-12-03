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
		frame.setBounds(100, 100, 1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		
		user u1 = new user("Pierre", "bonjour", "00h00"); 
		user u2 = new user("Louis", "Saluuuut ", "00h01"); 
		user u3 = new user("Mateo", "oui", "00h02"); 
		user u4 = new user("Mayeul", "non ", "00h03"); 
		user u5 = new user("Celest", "mais", "00h04"); 
		user u6 = new user("Andrea", "euh ", "00h05"); 
		
		DefaultListModel<user> listModel = new DefaultListModel<>(); 
		listModel.addElement(u1);
		listModel.addElement(u2);
		listModel.addElement(u3);
		listModel.addElement(u4);
		listModel.addElement(u5);
		listModel.addElement(u6);
		
		JList<user> userList = new JList<>(listModel); 
		userList.setBounds(10,10,1000,1000);
		userList.setCellRenderer(new userRenderer());

		
		// POURQUOI c'est que les stats du premier qui s'affichent à chaque fois ?? 
		// ca affiche tous les composants a chaque ligne putain  
		// POURQUOI c'est pas scrollable ? 
		JScrollPane scrollPane = new JScrollPane(userList);
		//scrollPane.setViewportView(userList);
		scrollPane.setBounds(10,10,400,400);
		frame.getContentPane().add(scrollPane); 
		
	}

}



 
