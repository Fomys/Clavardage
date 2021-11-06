import java.awt.EventQueue;

import javax.swing.* ; 
import java.awt.* ;
import javax.swing.GroupLayout.Alignment; 
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

	/**
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
		Droite.add(panel_conv);
		panel_conv.setBackground(Color.RED);
		
		JPanel panel_text = new JPanel();
		Droite.add(panel_text);
		panel_text.setBackground(Color.YELLOW);
		panel_profil.setLayout(null);
		
		// Affichage profil HAUT GAUCHE 
		textField = new JTextField();
		textField.setBounds(104, 6, 130, 26);
		panel_profil.add(textField);
		textField.setColumns(10);
		
		JButton btnChangeNick = new JButton("Change nickname");
		btnChangeNick.setBackground(Color.LIGHT_GRAY);
		btnChangeNick.setForeground(Color.BLACK);
		btnChangeNick.setBounds(239, 5, 156, 29);
		panel_profil.add(btnChangeNick);
	
		
		
		
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


