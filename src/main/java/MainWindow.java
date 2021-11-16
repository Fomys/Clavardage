
import java.awt.event.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.JTextComponent;
import java.awt.*; 


/*
 * TO DO TO DO to do to do to do to do to doooooooo (sur le thème de la panthère rose) 
 * 
 * faire des classes partout et tout pour rendre le code propre tavu 
 * liste de jlabel avec pdp et bouton réglages (bloquage / croix) pour les profils 
 * Messages vide qui s'envoient quand même wtf
 * recherche utilisateur  
 * Element avec radius 
 * recherche messages 
 * revoir responsive 
 */
public class MainWindow {

	private JFrame frame;
	public int hauteur = 600 ; 
    public int largeur = 1000 ; 
    int[] pos ;
    public JTextArea envoi_msg ; 
    
    /*
	 * Toutes les zones de texte (pour qu'elles se connaissent tout pour l'histoire de mise en surbrillance) 
	 */
	public JTextField txtSearchUser ; 
	public JLabel searchUser ; 
	public JTextField textSearchMsg ; 
	public JScrollPane scrollText ; 
	public JTextArea textMsg ; 
	
	
	/*
	 * Couleurs utiles
	 */
	Color gris_gauche =  new Color(45, 45, 45) ; 
	Color gris_composant =  new Color(150, 150, 150) ; 
	Color blanc_texte =  new Color(230, 230, 230) ; 
	Color gris_noir =  new Color(30, 30, 30) ; 
	
	/*
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
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
	public MainWindow() throws IOException {
		initialize();
	}

	
	/*
	 * Création d'un JPanel qui sera arrondi sur les bords 
	 */
	public JPanel PanelTexte() {
		JPanel p = new JPanel() {
		     @Override
		     protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        Dimension arcs = new Dimension(15,15);
		        int width = getWidth();
		        int height = getHeight();
		        Graphics2D graphics = (Graphics2D) g;
		        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		        //Draws the rounded opaque panel with borders.
		        graphics.setColor(getBackground());
		        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width*2, arcs.height*2);//paint background
		        graphics.setColor(getForeground());
		        graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width*2, arcs.height*2);//paint border
		     }
		  };
		  return p ; 
	}	
	
	/*
	 * Pour que les cases où on peut rentrer du texte deviennent bleu ou repasse gris et aient un texte de base 
	 */
	public void beauInput(JTextComponent input){
		
		// D'abord, on remet en gris TOUS les composants 
		textSearchMsg.setBorder(new LineBorder(gris_composant,2,true));
    	scrollText.setBorder(new LineBorder(gris_composant,2,true));
    	searchUser.setBorder(new LineBorder(gris_composant,2,true));
		
    	// on remet le texte si jamais c'est empty 
		if (textSearchMsg.getText().isEmpty()) {textSearchMsg.setText("Rechercher message");textSearchMsg.setForeground(gris_composant);};
		if (textMsg.getText().isEmpty()) {textMsg.setText(" Ecrire un message..");textMsg.setForeground(gris_composant);};
		if (txtSearchUser.getText().isEmpty()) {txtSearchUser.setText("Rechercher utilisateur");txtSearchUser.setForeground(gris_composant);};
		
		// ensuite, on regarde si l'élément a déjà ou non un texte dedans (qui n'est pas le texte par défaut) 
		System.out.println();
		if (input.getForeground().getRGB() == -6908266) { // si la couleur du texte est gris, alors y'a jamais eu de texte
			input.setText(""); // on vide le texte 
			input.setForeground(blanc_texte); // On change la couleur du texte 
		}
		
		if (input.getToolTipText().equalsIgnoreCase("Ecrivez un message")) { // si le tiptool est écrivez un message, alors on modif le scrollpanel 
			scrollText.setBorder(new LineBorder(new Color(54,88,153),3));
		}
		else if (input.getToolTipText().equalsIgnoreCase("Pour rechercher un utilisateur")) {// pour modif la zone user search 
			searchUser.setBorder(new LineBorder(new Color(54,88,153),3));
		}
		else if (input.getToolTipText().equalsIgnoreCase("Pour rechercher un message particulier")) { // pour modif la zone message search 
			textSearchMsg.setBorder(new LineBorder(new Color(54,88,153),3)); // On le met en surbrillance peut importe a quel moment on clic 
		}
		 	
	}
	
	/*
	 * Pour rendre responsive la verticale 
	 */
	public int pourcentageVert(int haut_component) {
		
		int new_hauteur = frame.getSize().height ; 
		// System.out.println("calcul hauteur " + frame.getSize().height + " " + hauteur +  " " + haut_component + " " + ((new_hauteur * haut_component) / hauteur) );
		return (new_hauteur * haut_component) / hauteur ; 
	}
	
	/*
	 * Pour rendre responsive l'horizontale  
	 */
	public int pourcentageHori(int larg_component) {
		
		int new_largeur = frame.getSize().width ; 
		//System.out.println("calcul largeur " + frame.getSize().width + " " + largeur + " " + larg_component + " " + ((new_largeur * larg_component) / largeur) );
		return (new_largeur * larg_component) / largeur ; 
	}
	
	/*
	 * Pour rendre responsive 
	 */
	public void pourcentage(JComponent composant, int pointX, int pointY, int larg, int haut) {
		
	    int x = pourcentageHori(pointX);
	    int y = pourcentageVert(pointY);
	    int width = pourcentageHori(larg);
	    int height = pourcentageVert(haut);
	    
	    composant.setBounds(x,y, width,height);

	}

	
	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(45, 45, 45));
		frame.setBounds(100, 100, largeur, hauteur);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(450, 400));
		frame.getContentPane().setLayout(null);
		
		// panel principaux (Gauche / Droite) 
		JPanel Gauche = new JPanel();
		Gauche.setBounds(0, 0, 350, 572);
		Gauche.setBackground(gris_gauche);
		frame.getContentPane().add(Gauche);
		Gauche.setLayout(null);
		
		JPanel Droite = new JPanel();
		Droite.setBounds(350, 0, 650, 572);
		Droite.setBackground(new Color(56,56,56));
		frame.getContentPane().add(Droite);
		Droite.setLayout(null);
		
		
		
		/*
		 * Éléments panel gauche 
		 */

		SousJPanel panel_profil = new SousJPanel(Gauche, 0,0,-1,100, true); 
		SousJPanel panel_searchUser = new SousJPanel(Gauche, 0,panel_profil.getNextHauteur(),-1,50, true); // on le fait commencer à la fin du composant précédent 
		SousJPanel panel_users = new SousJPanel(Gauche, 0,panel_searchUser.getNextHauteur(),-1,panel_searchUser.getHauteurPoss(), true); // on le fait commencer à la fin du composant précédent ET remplir toute la fin 

		
		
		/*
		 * Éléments panel droite  
		 */
		
		SousJPanel panel_searchMsg = new SousJPanel(Droite, 0,0,-1,50, true); 
		
		
		
		JPanel panel_conv = new JPanel();
		panel_conv.setPreferredSize(new Dimension(600, JNewMessage.getHauteur_next()));
		JScrollPane scrollMessages = new JScrollPane(panel_conv);
		scrollMessages.setBounds(0, 50, 650, 450);
		Droite.add(scrollMessages);
		scrollMessages.setBorder(null);
		panel_conv.setBackground(gris_noir);
		panel_conv.setAutoscrolls(true);
		panel_conv.setLayout(null);
	
		SousJPanel panel_text = new SousJPanel(Droite, 0,500,-1,-1, true); 
		panel_text.setBackground(gris_noir);
		panel_text.setBorder(null);
		
		
		/*
		JPanel panel_profil = new JPanel();
		panel_profil.setBounds(0, 0, 350, 100);
		Gauche.add(panel_profil);
		panel_profil.setBackground(gris_gauche);
		panel_profil.setLayout(null);

		JPanel panel_searchUser = new JPanel();
		panel_searchUser.setBounds(0, 100, 350, 50);
		Gauche.add(panel_searchUser);
		panel_searchUser.setBackground(gris_gauche);
		panel_searchUser.setLayout(null);
		
		JPanel panel_users = new JPanel();
		panel_users.setBounds(0, 150, 350, 422);
		Gauche.add(panel_users);
		panel_users.setBackground(gris_gauche);
		panel_users.setLayout(null);
		
		
		JPanel panel_searchMsg = new JPanel();
		panel_searchMsg.setBounds(0, 0, 650, 50);
		Droite.add(panel_searchMsg);
		panel_searchMsg.setBackground(new Color(56,56,56));
		panel_searchMsg.setLayout(null);
		
		JPanel panel_text = new JPanel();
		panel_text.setBounds(0, 500, 650, 70);
		Droite.add(panel_text);
		panel_text.setBackground(gris_noir);
		panel_text.setLayout(null);
		*/
	
		
		/*
		 * Profil utilisateur 
		 */
		
		JTextArea txtPseudo = new JTextArea(); // La zone de texte du pseudo 
		txtPseudo.setFont(new Font("Arial", Font.PLAIN, 20));
		txtPseudo.setText("Bienvenue\nPseudonyme");
		txtPseudo.setBackground(gris_gauche);
		txtPseudo.setForeground(blanc_texte);
		txtPseudo.setEditable(false);
		txtPseudo.setHighlighter(null);
		panel_profil.add(txtPseudo);
		
		/*
		JButton btnRename = new JButton(); // bouton d'envoi 
		Image btnWriteNewIcon = ImageIO.read(getClass().getResource("./images/write.png"));
		int btnWriteNewIconSize = 25 ; 
		btnRename.setContentAreaFilled(false);
		btnRename.setBorder(BorderFactory.createEmptyBorder());
		btnWriteNewIcon = btnWriteNewIcon.getScaledInstance(btnWriteNewIconSize, btnWriteNewIconSize, btnWriteNewIcon.SCALE_SMOOTH); 
		btnRename.setIcon(new ImageIcon(btnWriteNewIcon));
		btnRename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Go écrire a un nouvel utilisateur "); 
			}
		});
		panel_profil.add(btnRename);
		*/
		
		ButtonIcon btnSettings = new ButtonIcon("./images/settings.png", 25, 300, 20);
		panel_profil.add(btnSettings);
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("settings pseudo : oublie pas d'ouvrir une popup");
			}
		});
		
		/*
		JButton btnSettings = new JButton();
		int btnChangeNickSize = 25 ; 
		btnSettings.setBorder(BorderFactory.createEmptyBorder());
		btnSettings.setContentAreaFilled(false);
		Image imgSettings = ImageIO.read(getClass().getResource("./images/settings.png"));
		imgSettings = imgSettings.getScaledInstance(btnChangeNickSize, btnChangeNickSize, imgSettings.SCALE_SMOOTH); 
	    btnSettings.setIcon(new ImageIcon(imgSettings));
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("settings pseudo : oublie pas d'ouvrir une popup");
			}
		});
		panel_profil.add(btnSettings);
		*/
		
		
		DefaultListModel demoList = new DefaultListModel(); // liste des utilisateurs 
		for (int i = 0; i < 20 ; i ++) {
			demoList.addElement("Element "+i);
		}
		
		JList listUsers = new JList(demoList); // là où on affiche la liste 
		listUsers.setBackground(gris_gauche);
		listUsers.setForeground(blanc_texte);
		
		
		ButtonIcon btnNewUser = new ButtonIcon("./images/new_user.png", 25, 250, 20);
		panel_profil.add(btnNewUser);
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Add user");
				demoList.addElement("OUI");
			}
		});
		
		/*
		int btnNewUserSize = 25 ; 
		double btnNewUserSize2 = 30 ; 
		Image imgNewUser = ImageIO.read(getClass().getResource("./images/new_user.png"));
		Image imgNewUser2 = imgNewUser.getScaledInstance((int)btnNewUserSize2, (int)btnNewUserSize2, imgNewUser.SCALE_SMOOTH);
		imgNewUser = imgNewUser.getScaledInstance(btnNewUserSize, btnNewUserSize, imgNewUser.SCALE_SMOOTH);  
		JButton btnNewUser = new JButton();
		btnNewUser.setBounds(287, 44, 50, 50);
		panel_profil.add(btnNewUser);
		btnNewUser.setFont(new Font("Arial", Font.PLAIN, 13));
		btnNewUser.setBorder(BorderFactory.createEmptyBorder());
		btnNewUser.setContentAreaFilled(false);
		btnNewUser.setRolloverIcon(new ImageIcon(imgNewUser2)) ; 
		btnNewUser.setIcon(new ImageIcon(imgNewUser));
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Add user");
				demoList.addElement("OUI");
			}
		});
		*/
		
		txtPseudo.setBounds(16, 14, 200, 50); // pour mettre les composants 
		//btnRename.setBounds(250,38,btnWriteNewIconSize,btnWriteNewIconSize);
		//btnSettings.setBounds(300, 20, btnChangeNickSize, btnChangeNickSize);
		
		
		
		
		/*
		 * Recherche utilisateur + ajout utilisateur 
		 */
		
		searchUser = new JLabel(); 
		searchUser.setBackground(gris_gauche);
		searchUser.setBorder(new LineBorder(gris_composant,2,true));
		panel_searchUser.add(searchUser) ; 
		
		JButton btnSearchIcon = new JButton(); // bouton d'envoi 
		Image btnSearchIconImg = ImageIO.read(getClass().getResource("./images/search.png"));
		int btnSearchIconImgSize = 18 ; 
		btnSearchIcon.setContentAreaFilled(false);
		btnSearchIcon.setBorder(BorderFactory.createEmptyBorder());
		btnSearchIconImg = btnSearchIconImg.getScaledInstance(btnSearchIconImgSize, btnSearchIconImgSize, btnSearchIconImg.SCALE_SMOOTH); 
		btnSearchIcon.setIcon(new ImageIcon(btnSearchIconImg));
		btnSearchIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				beauInput(txtSearchUser);
				txtSearchUser.requestFocus();
			}
		});
		searchUser.add(btnSearchIcon);
		
		txtSearchUser = new JTextField();
		txtSearchUser.setBackground(gris_gauche);
		txtSearchUser.setText("Rechercher utilisateur");
		txtSearchUser.setBorder(new LineBorder(gris_gauche)); 
		txtSearchUser.setForeground(gris_composant);
		txtSearchUser.setToolTipText("Pour rechercher un utilisateur");
		txtSearchUser.addMouseListener(new MouseAdapter() {     
	        @Override
	        public void mouseClicked(MouseEvent arg0) {
	    		beauInput(txtSearchUser); 
	        }
	    });
		searchUser.add(txtSearchUser);
		
		JButton btnCrossIcon = new JButton(); // bouton d'envoi 
		Image btnCrossIconImg = ImageIO.read(getClass().getResource("./images/cross.png"));
		int btnCrossIconImgSize = 18 ; 
		btnCrossIcon.setContentAreaFilled(false);
		btnCrossIcon.setBorder(BorderFactory.createEmptyBorder());
		btnCrossIconImg = btnCrossIconImg.getScaledInstance(btnCrossIconImgSize, btnCrossIconImgSize, btnCrossIconImg.SCALE_SMOOTH); 
		btnCrossIcon.setIcon(new ImageIcon(btnCrossIconImg));
		btnCrossIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtSearchUser.setText("");
				txtSearchUser.requestFocus();
				beauInput(txtSearchUser);
			}
		});
		searchUser.add(btnCrossIcon);
		
		searchUser.setBounds(6,6,338,40) ; 
		txtSearchUser.setBounds(0, 0, 300, 40);
		btnSearchIcon.setBounds(9,9,btnSearchIconImgSize,btnSearchIconImgSize);
		btnCrossIcon.setBounds(303,10,btnCrossIconImgSize,btnCrossIconImgSize);
		
		
		JButton btnDelUser = new JButton("Delete user");
		btnDelUser.setBounds(181, 63, 94, 20);
		panel_profil.add(btnDelUser);
		btnDelUser.setFont(new Font("Arial", Font.PLAIN, 13));
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
		
		/*
		 * Liste utilisateur 
		 */
		
		
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		panel_users.add(scrollPane);
		scrollPane.setViewportView(listUsers);
		scrollPane.setBackground(gris_gauche);
		panel_users.setBackground(gris_gauche);
		scrollPane.setBounds(0, 55, 350, 367);
		
		
		/*
		 * Recherche message 
		 */
		
		textSearchMsg = new JTextField();
		textSearchMsg.setBackground(gris_composant);
		textSearchMsg.setHorizontalAlignment(SwingConstants.CENTER);
		textSearchMsg.setText("Rechercher message");
		textSearchMsg.setBorder(new LineBorder(gris_composant,2,true));
		textSearchMsg.setForeground(gris_composant);
		textSearchMsg.setToolTipText("Pour rechercher un message particulier");
		textSearchMsg.setOpaque(false);
		textSearchMsg.addMouseListener(new MouseAdapter() {     
	        @Override
	        public void mouseClicked(MouseEvent arg0) {
	        	beauInput(textSearchMsg); 
	        }
	    });
		panel_searchMsg.add(textSearchMsg);
		
		textSearchMsg.setBounds(35, 15, 580, 25);
		

		/*
		 * Conversation 
		 */
		
		JTextArea textArea1 = new JTextArea();
		textArea1.setRows(10);
		textArea1.setLineWrap(true);
		textArea1.setBounds(181, 10, 444, 40);
		//panel_conv.add(textArea1);
		
		JTextArea textArea2 = new JTextArea();
		textArea2.setRows(4);
		textArea2.setLineWrap(true);
		textArea2.setBounds(10, 65, 444, 100);
		//panel_conv.add(textArea2);
		textArea2.setColumns(10);
		
		/* 
		 * Envoi message 
		 */

		textMsg = new JTextArea();
		textMsg.setFont(new Font("Arial", Font.PLAIN, 13));
		textMsg.setLineWrap(true);
		textMsg.setText(" Ecrire un message.. ");
		textMsg.setBackground(gris_noir);
		textMsg.setForeground(gris_composant);
		textMsg.setToolTipText("Ecrivez un message");
		scrollText = new JScrollPane (textMsg);
		scrollText.setBackground(gris_composant);
		scrollText.setBorder(new LineBorder(gris_composant,2,true));
		panel_text.add(scrollText);
		textMsg.addMouseListener(new MouseAdapter() {     
	        @Override
	        public void mouseClicked(MouseEvent arg0) {
	        	beauInput(textMsg); 
	        }
	    });
		
		JButton btnSendMsg = new JButton(); // bouton d'envoi 
		Image btnSendMsgImg = ImageIO.read(getClass().getResource("./images/send.png"));
		int btnSendMsgImgSize = 30 ; 
		btnSendMsg.setContentAreaFilled(false);
		btnSendMsg.setBorder(BorderFactory.createEmptyBorder());
		btnSendMsgImg = btnSendMsgImg.getScaledInstance(btnSendMsgImgSize, btnSendMsgImgSize, btnSendMsgImg.SCALE_SMOOTH); 
	    btnSendMsg.setIcon(new ImageIcon(btnSendMsgImg));
		btnSendMsg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("On envoie le message : " + textMsg.getText());
				if (textMsg.getText() == "") {System.out.println("message vide");}
				envoi_msg = new JNewMessage(textMsg.getText(),"heure","me",true) ; 
		        panel_conv.add(envoi_msg);
				panel_conv.setPreferredSize(new Dimension(600, JNewMessage.getHauteur_next()));
		        panel_conv.updateUI();
		        textMsg.setText("");
			}
		});
		panel_text.add(btnSendMsg);
		
		JButton btnAutreMsg = new JButton("AUTRE"); // bouton d'envoi 
		btnAutreMsg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("On envoie le message :" + textMsg.getText()+".");
				if (textMsg.getText() == "") {System.out.println("message vide");}
				envoi_msg = new JNewMessage(textMsg.getText(),"heure","autre",false) ; 
		        panel_conv.add(envoi_msg);
				panel_conv.setPreferredSize(new Dimension(600, JNewMessage.getHauteur_next()));
		        panel_conv.updateUI();
		        textMsg.setText("");
			}
		});
		panel_text.add(btnAutreMsg);
		
		
		scrollText.setBounds(20, 20, 540, 38);
		btnSendMsg.setBounds(580, 20, 60, 20);
		btnAutreMsg.setBounds(580, 50, 60, 20 );
		
		// pour rendre la fenetre responsive 
		frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                System.out.println("Window Resized: " + frame.getSize());
                
                // panels gauche et droite 
                pourcentage(Gauche, 0,0, 350,572) ; 
                pourcentage(Droite, 350, 0, 650, 572); 
                
                // panels gauche  
                pourcentage(panel_profil, 0, 0, 350, 70); 
                pourcentage(panel_searchUser, 0, 70, 350, 50); 
                pourcentage(panel_users, 0, 120, 350, 452); 
                
                // panels droite 
                pourcentage(panel_searchMsg, 0,0, 650, 50); 
                pourcentage(panel_conv, 0, 50, 650, 450) ; 
                pourcentage(panel_text, 0, 500, 650, 72); 
                
                // panel_profil  
                pourcentage(txtPseudo, 16, 14, 170, 70); 
                //pourcentage(btnNewUser, 250, 20, btnNewUserSize, btnNewUserSize);
        		//pourcentage(btnSettings, 300, 20, btnChangeNickSize, btnChangeNickSize);
        		pourcentage(btnDelUser, 181, 50, 94, 20); 
                
                // panel_searchUser
        		pourcentage(searchUser, 10,5, 330,40);
        		pourcentage(btnSearchIcon,9,10,btnSearchIconImgSize,btnSearchIconImgSize); 
        		pourcentage(txtSearchUser, 35, 3, 265, 34); 
        		pourcentage(btnCrossIcon, 303, 10, btnCrossIconImgSize,btnCrossIconImgSize);

                // panel_users
        		pourcentage(scrollPane, 0, 0, 350, 452); 
                
                // panel_searchMes 
        		pourcentage(textSearchMsg, 35, 15, 580, 25); 
                
                // panel_conv 
        		pourcentage(scrollMessages,0,50,650,452);
        		
                // panel_text 
                pourcentage(scrollText, 20, 20, 540, 38); 
                pourcentage(btnSendMsg, 580, 20, btnSendMsgImgSize, btnSendMsgImgSize); 
                pourcentage(btnAutreMsg, 580, 50, 60, 20); 
             
                
                Gauche.updateUI();
                Droite.updateUI();
            }
        }) ; 
	}
}