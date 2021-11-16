import javax.swing.* ; 
import java.awt.* ;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs; 
public class Conversation {
	
	private JFrame frame; // window principale 
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Conversation window = new Conversation();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Conversation() {
		initialize();
	}

	/*
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		// on construit la fenetre 
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		
		// on divise la fenetre en 2 (gauche / droite) 
		JPanel Gauche = new JPanel();
		frame.getContentPane().add(Gauche);
		Gauche.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel Droite = new JPanel();
		frame.getContentPane().add(Droite);
		Droite.setLayout(new GridLayout(2, 1, 0, 0));
		
		
		// on divise les côtés en 2 aussi (haut / bas) 
		JPanel panel_profil = new JPanel();
		Gauche.add(panel_profil);
		panel_profil.setBackground(Color.GREEN);

		JPanel panel_users = new JPanel();
		Gauche.add(panel_users);
		panel_users.setBackground(Color.CYAN);
			
		JPanel panel_conv = new JPanel();
		JScrollPane scrollMessages = new JScrollPane(panel_conv);
		Droite.add(scrollMessages);
		panel_conv.setBackground(Color.RED);
	    GridBagLayout gbl_panel_conv = new GridBagLayout();
	    panel_conv.setLayout(gbl_panel_conv);
		
		JPanel panel_text = new JPanel();
		Droite.add(panel_text);
		panel_text.setBackground(Color.YELLOW);
		panel_text.setLayout(new GridLayout(0, 1, 0, 0));
		
		// Affichage profil HAUT GAUCHE 
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(104, 6, 130, 26);
		panel_profil.add(textField);
		textField.setColumns(10);
		
		JButton btnChangeNick = new JButton("Change nickname");
		btnChangeNick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (btnChangeNick.getText() == "OK") { // si on veut valider le changement 
					System.out.println("On valide le changement"); 
					textField.setEditable(false);
					btnChangeNick.setText("Change nickname"); 
				}
				else if (btnChangeNick.getText() == "Change nickname") { // si on veut changer le nickname 
					System.out.println("On veut changer de pseudo la ");
					textField.setEditable(true);
					// il faut aussi check la disponibilité 
					btnChangeNick.setText("OK"); 
				}
				
			}
		});
		btnChangeNick.setBackground(Color.LIGHT_GRAY);
		btnChangeNick.setForeground(Color.BLACK);
		btnChangeNick.setBounds(239, 5, 156, 29);
		panel_profil.add(btnChangeNick);
	
		
		// Affichage liste utilisateur BAS GAUCHE 
		
		DefaultListModel demoList = new DefaultListModel(); // liste des utilisateurs 
		for (int i = 0; i < 10 ; i ++) {
			demoList.addElement("addElements");}
		panel_users.setLayout(new GridLayout(0, 1, 0, 0));
		
		JList listUsers = new JList(demoList); // là où on affiche la liste 
		
		JPanel panel = new JPanel(); // le panel qui stocke les boutons new et del 
		panel_users.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnNewUser = new JButton("New user"); // bouton new 
		panel.add(btnNewUser);
		btnNewUser.setBackground(Color.CYAN);
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Add user");
				demoList.addElement("OUI");
			}
		});

		JButton btnDelUser = new JButton("Delete user"); // bouton del 
		panel.add(btnDelUser);
		btnDelUser.setBackground(Color.RED);
		btnDelUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Suppr user"); 
				int selectedElement = listUsers.getSelectedIndex() ;  
				if (selectedElement != -1) { // si on essaye de supprimer sans selectioner, il ne se passe rien 
					demoList.remove(selectedElement);
				}
				
			}
		});
		
		
		listUsers.setBounds(250, 58, 59, 53); // list des users 
		JScrollPane scrollPane = new JScrollPane(listUsers);
		panel_users.add(scrollPane);
		
		
		
		// Affichage de la conversation HAUT DROITE 
		messageText teste = new messageText("oui","oui");
		JPanel newtest = teste.createComponentGauche(); 
		  
		GridBagConstraints contraintes;
		contraintes = new GridBagConstraints();
	    contraintes.gridx = 0;
	    contraintes.gridy = 0;
	    contraintes.fill = GridBagConstraints.HORIZONTAL;
	    contraintes.weightx = 1;
	    contraintes.weighty = 1;
	    
	    gbl_panel_conv.setConstraints(newtest, contraintes);
	    panel_conv.add(newtest); 
	    
	    JPanel newtest2 = teste.createComponentDroite(); 
		  
		contraintes = new GridBagConstraints();
	    contraintes.gridx = 0;
	    contraintes.gridy = 1;
	    contraintes.fill = GridBagConstraints.HORIZONTAL;
	    contraintes.weightx = 1;
	    contraintes.weighty = 1;
	    
	    gbl_panel_conv.setConstraints(newtest2, contraintes);
	    panel_conv.add(newtest2); 

		
		/*
		JTextArea texttry = new JTextArea();
		  
		GridBagConstraints contraintes;

		contraintes = new GridBagConstraints();
	    contraintes.gridx = 0;
	    contraintes.gridy = 0;
	    contraintes.gridheight = 10;
	    contraintes.weightx = 0.1;
	    contraintes.weighty = 1;
	    gbl_panel_conv.setConstraints(texttry, contraintes);
	    panel_conv.add(texttry); 
	    texttry.setBackground(Color.blue);
	    
	    JTextArea texteu = new JTextArea();
	    		
		contraintes = new GridBagConstraints();
	    contraintes.gridx = 1;
	    contraintes.gridy = 0;
	    contraintes.gridheight = 1;
	    contraintes.fill = GridBagConstraints.HORIZONTAL;
	    contraintes.weightx = 0.9;
	    contraintes.weighty = 0.2;
	    gbl_panel_conv.setConstraints(texteu, contraintes);
	    panel_conv.add(texteu); 
	    texteu.setBackground(Color.green);
	    		
	    		
	    		
	    JTextArea texteuu = new JTextArea();
		
		contraintes = new GridBagConstraints();
	    contraintes.gridx = 1;
	    contraintes.gridy = 11;
	    contraintes.gridheight = 1;
	    contraintes.fill = GridBagConstraints.HORIZONTAL;
	    contraintes.weightx = 0.9;
	    contraintes.weighty = 0.2;
	    gbl_panel_conv.setConstraints(texteuu, contraintes);
	    panel_conv.add(texteuu); 
	    texteuu.setBackground(Color.black);
	    
	    
	    JTextArea texteeuu = new JTextArea();
		
		contraintes = new GridBagConstraints();
	    contraintes.gridx = 1;
	    contraintes.gridy = 3;
	    contraintes.gridheight = 1;
	    contraintes.fill = GridBagConstraints.HORIZONTAL;
	    contraintes.weightx = 0.9;
	    contraintes.weighty = 0.2;
	    gbl_panel_conv.setConstraints(texteeuu, contraintes);
	    panel_conv.add(texteeuu); 
	    texteeuu.setBackground(Color.blue);
	    */
		
		/*
		JButton efface = new JButton("efface");
	    JButton demarre = new JButton("demarre");
	    JCheckBox relief = new JCheckBox("relief");
   	    JCheckBox  gras = new JCheckBox("gras");
	    JTextArea texte = new JTextArea();
	    JTextArea texte2 = new JTextArea();
	    JPanel dessin = new JPanel();
	    
	    JTextArea timeSent = new JTextArea("11h43");
	    timeSent.setEditable(false);
	    timeSent.setBackground(Color.cyan);
	    
		
	    JTextArea space = new JTextArea();
	    space.setEditable(false);
	    space.setBackground(Color.black);
	    
	    
	    JTextArea timeSent2 = new JTextArea("11h43");
	    timeSent2.setEditable(false);
	    timeSent2.setBackground(Color.cyan);
	    
		
	    JTextArea space2 = new JTextArea();
	    space2.setEditable(false);
	    space2.setBackground(Color.black);
	    
	    GridBagConstraints contraintes;

	    
	    contraintes = new GridBagConstraints();
	    contraintes.gridx = 0;
	    contraintes.gridy = 0;
	    contraintes.gridwidth = 2;
	    contraintes.gridheight = 2;
	    contraintes.fill = GridBagConstraints.BOTH;
	    contraintes.weightx = 1;
	    contraintes.weighty = 1;
	    gbl_panel_conv.setConstraints(texte2, contraintes);
	    panel_conv.add(texte2);
	    
	    contraintes = new GridBagConstraints();
	    contraintes.gridx = 2;
	    contraintes.gridy = 1;
	    contraintes.gridwidth = 1;
	    contraintes.gridheight = 1;
	    contraintes.ipadx = 40;
	    gbl_panel_conv.setConstraints(timeSent, contraintes);
	    panel_conv.add(timeSent);
	    
	    contraintes = new GridBagConstraints();
	    contraintes.gridx = 3;
	    contraintes.gridy = 1;
	    contraintes.gridwidth = 1;
	    contraintes.gridheight = 1;
	    contraintes.ipadx = 40;
	    gbl_panel_conv.setConstraints(space, contraintes);
	    panel_conv.add(space);
	    
	    
	    
	    
	  
	    
	    contraintes = new GridBagConstraints();
	    contraintes.gridx = 1;
	    contraintes.gridy = 2;
	    contraintes.gridwidth = 2;
	    contraintes.gridheight = 2;
	    contraintes.fill = GridBagConstraints.BOTH;
	    contraintes.weightx = 1;
	    contraintes.weighty = 1;
	    gbl_panel_conv.setConstraints(texte, contraintes);
	    panel_conv.add(texte);
	    
	    contraintes = new GridBagConstraints();
	    contraintes.gridx = 0;
	    contraintes.gridy = 2;
	    contraintes.gridwidth = 1;
	    contraintes.gridheight = 1;

	    contraintes.fill = GridBagConstraints.VERTICAL;
	    contraintes.ipadx = 40;
	    gbl_panel_conv.setConstraints(relief, contraintes);
	    panel_conv.add(relief);
	    */
	  
	 
	 
        /*
        JButton button;

    	GridBagConstraints c = new GridBagConstraints();
        button = new JButton("Button 1");
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        panel_conv.add(button, c);

        button = new JButton("Button 2");
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        panel_conv.add(button, c);

        button = new JButton("Button 3");
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        panel_conv.add(button, c);

        button = new JButton("Long-Named Button 4");
        c.ipady = 40;      //make this component tall
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        panel_conv.add(button, c);
        */
	      
		
		
        /*
        JButton button;

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL ; 
        panel_conv.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        JTextArea textArea = new JTextArea("testArea"); 
        c.weightx = 0.75 ; 
        c.gridwidth = 2 ; 
        c.gridx = 0 ; 
        c.gridy = 3 ; 
        panel_conv.add(textArea, c);
        	        
        JTextArea textArea2 = new JTextArea("testArea2"); 
        textArea2.setBackground(Color.RED);
        c.weightx = 0.25 ; 
        c.gridwidth = 2 ; 
        c.gridx = 1 ; 
        c.gridy = 3 ; 
        panel_conv.add(textArea2, c);
		               
        JTextArea textArea20 = new JTextArea("testArea"); 
        c.weightx = 0.75 ; 
        c.gridwidth = 2 ; 
        c.gridx = 2 ; 
        c.gridy = 4; 
        panel_conv.add(textArea20, c);

        JTextArea textArea23 = new JTextArea("testArea2"); 
        textArea23.setBackground(Color.RED);
        c.weightx = 0.25 ; 
        c.gridwidth = 5 ; 
        c.gridx = 0 ; 
        c.gridy = 4 ; 
        panel_conv.add(textArea23, c);       
		*/
        
        

        /*
        JButton button;

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL ; 
       
		panel_conv.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        JTextArea textArea = new JTextArea("testArea"); 
        c.weightx = 0.75 ; 
        c.gridwidth = 2 ; 
        c.gridx = 0 ; 
        c.gridy = 3 ; 
        panel_conv.add(textArea, c);

		panel_conv.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        JTextArea textArea20 = new JTextArea("testArea"); 
        c.weightx = 0.75 ; 
        c.gridwidth = 2 ; 
        c.gridx = 2 ; 
        c.gridy = 4; 
        panel_conv.add(textArea20, c);
        */
        

		

	 
		
		// Affichage du message à envoyer BAS DROITE 
		
		JTextArea textMsg = new JTextArea(); // zone d'écriture du message scrollable 
		textMsg.setLineWrap(true);
		JScrollPane scrollText = new JScrollPane (textMsg);
		panel_text.add(scrollText);
		
		
		JButton btnSendMsg = new JButton("Send Message"); // bouton d'envoi 
		btnSendMsg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("On envoie le message : " + textMsg.getText()); 


				
				
				textMsg.setText("");

			
			}
		});
		panel_text.add(btnSendMsg);
		
	}
}


/*
 * Division de l'écran en 4 
 * https://stackoverflow.com/questions/30195185/how-to-make-4-quadrant-java-swing-gui-whose-sides-always-conforms-to-the-golden
 */

/*
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.WindowConstants;

public class Test extends JFrame {

    public static final double GOLDEN_RATIO = 1.6180339887498948482;
    public static final double RELATIVE_LENGTH_OF_LONGER_SIDE = 1 / GOLDEN_RATIO;
    public static final double RELATIVE_LENGTH_OF_SHORTER_SIDE = 1 - (1 / GOLDEN_RATIO);
    private static final int screenHeight = 500;
    private static final int LENGTH_OF_LONGER_SIDE_FOR_RATIO = (int) (screenHeight * RELATIVE_LENGTH_OF_LONGER_SIDE);
    private static final int LENGTH_OF_SHORTER_SIDE_FOR_RATIO = (int) (screenHeight * RELATIVE_LENGTH_OF_SHORTER_SIDE);
    private static final int MIN_LENGTH_OF_LONGER_SIDE_FOR_RATIO = (int) (50 * RELATIVE_LENGTH_OF_LONGER_SIDE);
    private static final int MIN_LENGTH_OF_SHORTER_SIDE_FOR_RATIO = (int) (50 * RELATIVE_LENGTH_OF_SHORTER_SIDE);

    public Test() {
        buildGUI();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void buildGUI() {
        JPanel pane = new JPanel();
        // make GridBagLayout
        GroupLayout layout = new GroupLayout(pane);
        pane.setLayout(layout);

        // Make 4 components to put in the four grid spaces.
        JButton filler1 = new JButton("Press here to swap");
        JButton filler2 = new JButton("Press here to swap");
        JButton filler3 = new JButton("Press here to swap");
        Object[] objects = new Object[50];
        for (int i = 0; i < 50; i++) {
            objects[i] = "Test" + i;
        }
        JTree jTree = new JTree(objects);
        JScrollPane scrollPane = new JScrollPane(jTree);
        JButton button = new JButton("Press here to swap");
        JPanel filler4 = new JPanel(new BorderLayout());
        filler4.add(button);
        ActionListener l = (e) -> {
            if (filler4.getComponents()[0] instanceof JButton) {
                filler4.remove(button);
                filler4.add(scrollPane, BorderLayout.CENTER);
            } else {
                filler4.remove(scrollPane);
                filler4.add(button, BorderLayout.CENTER);
            }
            filler4.repaint();
            filler4.revalidate();
        };
        filler1.addActionListener(l);
        filler2.addActionListener(l);
        filler3.addActionListener(l);
        button.addActionListener(l);
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(filler1, MIN_LENGTH_OF_LONGER_SIDE_FOR_RATIO, LENGTH_OF_LONGER_SIDE_FOR_RATIO, Short.MAX_VALUE)
                        .addComponent(filler2, MIN_LENGTH_OF_LONGER_SIDE_FOR_RATIO, LENGTH_OF_LONGER_SIDE_FOR_RATIO, Short.MAX_VALUE)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(filler3, MIN_LENGTH_OF_SHORTER_SIDE_FOR_RATIO, LENGTH_OF_SHORTER_SIDE_FOR_RATIO, Short.MAX_VALUE)
                        .addComponent(filler4, MIN_LENGTH_OF_SHORTER_SIDE_FOR_RATIO, LENGTH_OF_SHORTER_SIDE_FOR_RATIO, Short.MAX_VALUE)
                ));
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(filler1, MIN_LENGTH_OF_SHORTER_SIDE_FOR_RATIO, LENGTH_OF_SHORTER_SIDE_FOR_RATIO, Short.MAX_VALUE)
                        .addComponent(filler3, MIN_LENGTH_OF_SHORTER_SIDE_FOR_RATIO, LENGTH_OF_SHORTER_SIDE_FOR_RATIO, Short.MAX_VALUE)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(filler2, MIN_LENGTH_OF_LONGER_SIDE_FOR_RATIO, LENGTH_OF_LONGER_SIDE_FOR_RATIO, Short.MAX_VALUE)
                        .addComponent(filler4, MIN_LENGTH_OF_LONGER_SIDE_FOR_RATIO, LENGTH_OF_LONGER_SIDE_FOR_RATIO, Short.MAX_VALUE)
                ));
        add(pane);
        setSize(new Dimension(200, 200));
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new Test().setVisible(true);
        });
    }

}
*/
/*
 * Liste utilisateur 
 * https://stackoverflow.com/questions/33894580/scrollable-list-with-items-of-set-pixel-height-in-java-swing
 */ 

/*
import javax.swing.*;

public class ButtonsInScrollPane{

    public static void main(String[] args){
        JFrame frame = new JFrame();
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
        p.add(getJButton(p));
        JScrollPane scroll = new JScrollPane(p);
        frame.setContentPane(scroll);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        frame.setVisible(true);
    }

    static public JButton getJButton(JPanel p){
        JButton b = new JButton("more");
        b.addActionListener(evt->{
            p.add(getJButton(p));
            p.revalidate();
            p.repaint();
            });
        return b;
    }    
}
*/


