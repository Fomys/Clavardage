package gui.composants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;

import database.Database;
import database.DatabaseObserver;
import database.Message;


public class User extends JPanel implements DatabaseObserver {

	private String nickname ; 
	private String police = "Lucida Grande";
	private int taillePolice = 13 ; 
	
	private ProfilePicture profilePic;
    private JLabel nameDisplayed;
    private JLabel dateLastMsg;
    private JLabel lastMsg;
    private JLabel lblLed ;
    
    private Color blue = new Color(59,130,247) ; 
    private Color blancNickname = new Color(255,255,255) ; 
    private Color grisFonce = new Color(160,160,160) ; 
    private Color grisClair = new Color(230,230,230) ;
    private Color grisBg = new Color(44,43,42) ; 
    
	
	public User (String nickname, Database database) throws IOException{ 
		this.nickname = nickname ; 
		this.setBackground(new Color(44,43,42));
		this.setForeground(Color.WHITE);
		this.setFont(new Font(police, Font.PLAIN, taillePolice));
		this.setMaximumSize(new Dimension (320,50));
		
		initComponents();

		//database.addObserver(this);
		
		
	}
	
	private void toBlue() {
		this.setBackground(blue);
		nameDisplayed.setForeground(blancNickname);
		dateLastMsg.setForeground(grisClair); 
		lastMsg.setForeground(grisClair); 
	}
	
	private void toGray() {
		this.setBackground(grisBg);
		nameDisplayed.setForeground(Color.white);
		dateLastMsg.setForeground(grisFonce); 
		lastMsg.setForeground(grisFonce); 
	}
	
	private void clickedOn() {

		if (this != UserList.getCurrentUser()){ // si celui sur lequel on clic n'est pas le courant 
			if (UserList.getCurrentUser() != null) {
				UserList.getCurrentUser().toGray();
			}
			UserList.setCurrentUser(this); 
			this.toBlue();
		}
	}
	
	private void initComponents() throws IOException {

        nameDisplayed = new JLabel();
        dateLastMsg = new JLabel();
        lastMsg = new JLabel();


        //======== this ========
        
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {50, 200, 50, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {20, 30, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 1.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};
        
        // ajout de la photo de profil 
        ProfilePicture profilePic = new ProfilePicture("./../images/profil.png", 35); 
        add(profilePic, new GridBagConstraints(0, 0, 1, 2, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 5, 5), 0, 0));
        profilePic.addMouseListener(new MouseAdapter() {@Override public void mouseClicked(MouseEvent arg0) {clickedOn();}});

        //---- nameDisplayed ----
        nameDisplayed.setText(this.nickname);
        nameDisplayed.setFont(new Font(police, Font.BOLD, taillePolice));
        nameDisplayed.setForeground(Color.white);
        add(nameDisplayed, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(10, 0, 0, 5), 0, 0));
        nameDisplayed.addMouseListener(new MouseAdapter() {@Override public void mouseClicked(MouseEvent arg0) {clickedOn();}});

        //---- dateLastMsg ----
        dateLastMsg.setText("date");
        dateLastMsg.setForeground(new Color(160,160,160)); 
        add(dateLastMsg, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(10, 0, 0, 0), 0, 0));
        dateLastMsg.addMouseListener(new MouseAdapter() {@Override public void mouseClicked(MouseEvent arg0) {clickedOn();}});

        //---- lastMsg ----
        lastMsg.setText("mess");
        lastMsg.setForeground(new Color(160,160,160)); 
        add(lastMsg, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(-10, 0, 0, 0), 0, 0));
        lastMsg.addMouseListener(new MouseAdapter() {@Override public void mouseClicked(MouseEvent arg0) {clickedOn();}});

        //---- ConnectedVisualiser ----
        lblLed = new JLabel("•");
        lblLed.setForeground(Color.green);
        add(lblLed, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 40, -10, 0), 0, 0));
        lblLed.addMouseListener(new MouseAdapter() {@Override public void mouseClicked(MouseEvent arg0) {clickedOn();}});
        
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        
        this.addMouseListener(new MouseAdapter() {     
	        @Override
	        public void mouseClicked(MouseEvent arg0) {
	        	clickedOn() ; 
	        }
	    });
        
    }

	public String getNickname() {
		return nickname;
	}

	@Override
	public void on_message(Message message) {
		// TODO Auto-generated method stub
		if(message.getFrom().equals(this.getNickname()) || message.getTo().equals(this.getNickname())) {
		this.lastMsg.setText(message.getContent());}
	}
}