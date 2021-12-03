import composants.* ; 

import java.awt.event.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import java.awt.*; 


/*
 * TO DO TO DO to do to do to do to do to doooooooo (sur le thème de la panthère rose) 
 * 
 * liste de jlabel avec pdp et bouton réglages (bloquage / croix) pour les profils
 * message avec saut a la ligne qui beug  
 * Messages vide qui s'envoient quand même wtf
 * Mettre jlabel séparation entre user 
 * recherche utilisateur (voir celui d'imessage aussi) 
 * Element embout rond  
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
	public JPanel searchUser ;  
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
    	scrollText.setBorder(new LineBorder(gris_composant,2,true));
    	searchUser.setBorder(new LineBorder(gris_composant,2,true));
		
    	// on remet le texte si jamais c'est empty 
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
		
		// panel principaux (gauche / droite) 
		JPanel gauche = new JPanel();
		gauche.setBounds(0, 0, 350, 572);
		gauche.setBackground(gris_gauche);
		frame.getContentPane().add(gauche);
		gauche.setLayout(null);
		
		JPanel droite = new JPanel();
		droite.setBounds(350, 0, 650, 572);
		droite.setBackground(new Color(56,56,56));
		frame.getContentPane().add(droite);
		droite.setLayout(null);
		
		/*
		 * Éléments panel gauche 
		 */

		SousJPanel panel_profil = new SousJPanel(gauche, 0,0,-1,50, true); 
		SousJPanel panel_searchUser = new SousJPanel(gauche, 0,panel_profil.getNextHauteur(),-1,50, true); // on le fait commencer à la fin du composant précédent 
		SousJPanel panel_users = new SousJPanel(gauche, 0,panel_searchUser.getNextHauteur(),-1,panel_searchUser.getHauteurPoss(), true); // on le fait commencer à la fin du composant précédent ET remplir toute la fin 
		SousJPanel list_users = new SousJPanel(panel_users, 0,0,-1,-1, true); // on le fait commencer à la fin du composant précédent ET remplir toute la fin 
		
		/*
		 * Éléments panel droite  
		 */
		
		SousJPanel panel_searchMsg = new SousJPanel(droite, 0,0,-1,50, true); 
		
		JPanel panel_conv = new JPanel();
		panel_conv.setPreferredSize(new Dimension(600, JNewMessage.getHauteur_next()));
		JScrollPane scrollMessages = new JScrollPane(panel_conv);
		scrollMessages.setBounds(0, 50, 650, 450);
		droite.add(scrollMessages);
		scrollMessages.setBorder(null);
		panel_conv.setBackground(gris_noir);
		panel_conv.setAutoscrolls(true);
		panel_conv.setLayout(null);
	
		SousJPanel panel_text = new SousJPanel(droite, 0,500,-1,-1, true); 
		panel_text.setBackground(gris_noir);
		panel_text.setBorder(null);
		
		/*
		 * Profil utilisateur 
		 */
		
		JTextArea txtPseudo = new JTextArea(); // La zone de texte du pseudo 
		txtPseudo.setFont(new Font("Arial", Font.PLAIN, 20));
		txtPseudo.setText("Pseudonyme");
		txtPseudo.setBackground(gris_gauche);
		txtPseudo.setForeground(blanc_texte);
		txtPseudo.setEditable(false);
		txtPseudo.setHighlighter(null);
		panel_profil.add(txtPseudo);
		
		ButtonIcon btnRename = new ButtonIcon("./../images/write.png", 25, 307, 14);
		panel_profil.add(btnRename);
		btnRename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Add user");
			}
		});
		
		
		
		
		/*
		DefaultListModel demoList = new DefaultListModel(); // liste des utilisateurs 
		for (int i = 0; i < 20 ; i ++) {
			demoList.addElement("Element "+i);
		}
		JList listUsers = new JList(demoList); // là où on affiche la liste 
		listUsers.setBackground(gris_gauche);
		listUsers.setForeground(blanc_texte);
		*/
		
		/*
		ButtonIcon btnNewUser = new ButtonIcon("./images/new_user.png", 25, 250, 20);
		panel_profil.add(btnNewUser);
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Add user");
				//demoList.addElement("OUI");
			}
		});
		*/
		
		txtPseudo.setBounds(16, 14, 200, 50); // pour mettre les composants 

		/*
		 * Recherche utilisateur + ajout utilisateur 
		 */
		
		searchUser = new JPanel(); 
		searchUser.setLayout(null); 
		searchUser.setBackground(gris_gauche);
		searchUser.setBorder(new LineBorder(gris_composant,2,true));
		panel_searchUser.add(searchUser) ; 
		
		ButtonIcon btnSearchIcon = new ButtonIcon("./../images/search.png", 18, 9, 9);
		searchUser.add(btnSearchIcon);
		btnSearchIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				beauInput(txtSearchUser);
				txtSearchUser.requestFocus();
			}
		});
		
		txtSearchUser = new JTextField();
		txtSearchUser.setBackground(gris_gauche);
		txtSearchUser.setText("Rechercher utilisateur");
		txtSearchUser.setBorder(null); 
		txtSearchUser.setForeground(gris_composant);
		txtSearchUser.setToolTipText("Pour rechercher un utilisateur");
		txtSearchUser.addMouseListener(new MouseAdapter() {     
	        @Override
	        public void mouseClicked(MouseEvent arg0) {
	    		beauInput(txtSearchUser); 
	        }
	    });
		searchUser.add(txtSearchUser);
		
		ButtonIcon btnCrossIcon = new ButtonIcon("./../images/cross.png", 18, 303, 10);
		searchUser.add(btnCrossIcon);
		btnCrossIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSearchUser.setText("");
				txtSearchUser.requestFocus();
				beauInput(txtSearchUser);
			}
		});
		
		searchUser.setBounds(6,6,338,40) ; 
		txtSearchUser.setBounds(0, 0, 300, 40);
		
		/*
		JButton btnDelUser = new JButton("Delete user");
		btnDelUser.setBounds(181, 63, 94, 20);
		panel_profil.add(btnDelUser);
		btnDelUser.setFont(new Font("Arial", Font.PLAIN, 13));
		btnDelUser.setBackground(Color.RED);
		btnDelUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Suppr user"); 
				
			}
		});
		*/
		
		/*
		 * Liste utilisateur 
		 */
		
		
		
		JScrollPane scrollPane = new JScrollPane(list_users);
		//scrollPane.setViewportView(panel_users);
		scrollPane.setBackground(gris_gauche);
		list_users.setPreferredSize(new Dimension(330,8*65)) ; 
		list_users.setBackground(gris_gauche);
		scrollPane.setBounds(0, 0, list_users.getWidth(), list_users.getHeight());
		panel_users.add(scrollPane) ; 
		panel_users.setBorder(null);
		scrollPane.setBorder(null); 
		list_users.setBorder(null); 
		

		list_users.setLayout(new BoxLayout(list_users, BoxLayout.PAGE_AXIS));

		JPanel test = new JLabelSpe("User 1", 27, 50, 350);
		list_users.add(test);
		
		JPanel test2 = new JLabelSpe("User 2", 27, 50, 350);
		list_users.add(test2); 
	
		JPanel test3 = new JLabelSpe("User 3", 27, 50, 350);
		list_users.add(test3); 

		JPanel test4 = new JLabelSpe("User 4", 27, 50, 350);
		list_users.add(test4); 
		
		JPanel test5 = new JLabelSpe("User 5", 27, 50, 350);
		list_users.add(test5); 
		
		JPanel test6 = new JLabelSpe("User 6", 27, 50, 350);
		list_users.add(test6); 
		
		JPanel test7 = new JLabelSpe("User 7", 27, 50, 350);
		list_users.add(test7); 

		
		JPanel test8 = new JLabelSpe("User 8", 27, 50, 350);
		list_users.add(test8); 
		
		
		/*
		 * Recherche message 
		 */
		
		SousJText textSearchMsgIndication = new SousJText(panel_searchMsg, "Rechercher message : ", 10, 14, 160, 25, true, true, true);
		SousJText textSearchMsg = new SousJText(panel_searchMsg, "", 170, 14, 400, 25, true, false, true);
		
		textSearchMsg.setEditable(true);
		textSearchMsg.setForeground(blanc_texte);
		textSearchMsg.setToolTipText(""); 
		textSearchMsg.addMouseListener(new MouseAdapter() {     
	        @Override
	        public void mouseClicked(MouseEvent arg0) {
	    		beauInput(textSearchMsg); 
	        }
	    });
		textSearchMsgIndication.addMouseListener(new MouseAdapter() {     
	        @Override
	        public void mouseClicked(MouseEvent arg0) {
	    		beauInput(textSearchMsg); 
	    		textSearchMsg.requestFocus();
	        }
	    });
		
		ButtonIcon btnSettings = new ButtonIcon("./../images/settings.png", 25, 600, 14);
		panel_searchMsg.add(btnSettings);
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("settings pseudo : oublie pas d'ouvrir une popup");
			}
		});

		/*
		 * Conversation (tout fonctionne dans la partie suivante)
		 */
		
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
                pourcentage(gauche, 0,0, 350,572) ; 
                pourcentage(droite, 350, 0, 650, 572); 
                
                // panels gauche  
                //pourcentage(panel_profil, 0, 0, 350, 70); 
                //pourcentage(panel_searchUser, 0, 70, 350, 50); 
                //pourcentage(panel_users, 0, 120, 350, 452); 
                
                // panels droite 
                pourcentage(panel_searchMsg, 0,0, 650, 50); 
                pourcentage(panel_conv, 0, 50, 650, 450) ; 
                pourcentage(panel_text, 0, 500, 650, 72); 
                
                // panel_profil  
                pourcentage(txtPseudo, 16, 14, 170, 70); 
                //pourcentage(btnNewUser, 250, 20, btnNewUserSize, btnNewUserSize);
        		//pourcentage(btnSettings, 300, 20, btnChangeNickSize, btnChangeNickSize);
        		//pourcentage(btnDelUser, 181, 50, 94, 20); 
                
                // panel_searchUser
        		pourcentage(searchUser, 10,5, 330,40);
        		//pourcentage(btnSearchIcon,9,10,btnSearchIconImgSize,btnSearchIconImgSize); 
        		pourcentage(txtSearchUser, 35, 3, 265, 34); 
        		//pourcentage(btnCrossIcon, 303, 10, btnCrossIconImgSize,btnCrossIconImgSize);

                // panel_users
        		//pourcentage(scrollPane, 0, 0, 350, 452); 
                
                // panel_searchMes 
        		//pourcentage(textSearchMsg, 6, 14, 150, 25); 
        		//pourcentage(textSearchMsgIndication, x,y,width,hauteur);
                
                // panel_conv 
        		pourcentage(scrollMessages,0,50,650,452);
        		
                // panel_text 
                pourcentage(scrollText, 20, 20, 540, 38); 
                pourcentage(btnSendMsg, 580, 20, btnSendMsgImgSize, btnSendMsgImgSize); 
                pourcentage(btnAutreMsg, 580, 50, 60, 20); 
             
                
                gauche.updateUI();
                droite.updateUI();
            }
        }) ; 
	}
}
