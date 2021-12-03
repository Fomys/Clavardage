package test.testForListUser ; 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

public class JListCustomRendererExample implements Runnable {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new JListCustomRendererExample());
    }
    
    private final CountryModel model;
    
    public JListCustomRendererExample() {
        this.model = new CountryModel();
    }

    @Override
    public void run() {
        JFrame frame = new JFrame("JList Renderer Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.YELLOW);
        
        frame.add(createMainPanel(model), BorderLayout.CENTER);
        frame.add(createButtonPanel(), BorderLayout.SOUTH);
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    private JPanel createMainPanel(CountryModel model) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setBackground(Color.red);
        
        JList<Country> countryList = new JList<>(model.getListModel());
        countryList.setCellRenderer(new CountryRenderer());
        panel.add(new JScrollPane(countryList), BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setBackground(Color.black);
        
        JButton button = new JButton("Select Countries");
        panel.add(button, BorderLayout.CENTER);
        
        return panel;
    }
    
    public class CountryRenderer implements ListCellRenderer<Country> {

        private final JLabel label;
        
        public CountryRenderer() {
            this.label = new JLabel();
            label.setOpaque(true);
            label.setBackground(Color.orange);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Country> list, 
                Country country, int index, boolean isSelected, boolean cellHasFocus) {

        	
            label.setIcon(new ImageIcon(country.getImage()));
            label.setText(country.getName());
            label.setLayout(null);
            JButton test = country.getButton(); 
            test.setBounds(10,10,50,50);
            label.add(test); 
            

            if (isSelected) {
                label.setBackground(list.getSelectionBackground());
                label.setForeground(list.getSelectionForeground());
            } else {
                label.setBackground(list.getBackground());
                label.setForeground(list.getForeground());
            }

            return label;
        }
    }
    
    public class CountryModel {

        private final DefaultListModel<Country> listModel;

        public CountryModel() {
            Country us = new Country("USA", "1", "A");
            Country in = new Country("India", "2","b");
            Country vn = new Country("Vietnam", "3","c");
            Country ca = new Country("Canada", "4","d");
            Country de = new Country("Denmark", "5","e");
            Country fr = new Country("France", "6","f");
            Country gb = new Country("Great Britain", "7","g");
            Country jp = new Country("Japan", "8","h");

            // create the model and add elements
            listModel = new DefaultListModel<>();
            listModel.addElement(us);
            listModel.addElement(in);
            listModel.addElement(vn);
            listModel.addElement(ca);
            listModel.addElement(de);
            listModel.addElement(fr);
            listModel.addElement(gb);
            listModel.addElement(jp);
        }

        public DefaultListModel<Country> getListModel() {
            return listModel;
        }

    }
    
    public class Country {
        
        private final Image image;
         
        private final String name;
        private final String code;
        private final String letter ; 
        
        private JButton button ; 
     
        public Country(String name, String code, String letter) {
            this.name = name;
            this.code = code;
            this.image = getImage(code);
            this.letter = letter ; 
            this.button = getButton(name); 
            
        }
        
        public String getLetter() {
        	return this.letter ; 
        }
        
        private Image getImage(String code) {
            Image image = null;
            try {
                URL url = getClass().getResource("./images/little_empty.png");
                image = ImageIO.read(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return image;
        }
     
        private JButton getButton(String name) {
        	JButton button = new JButton(name); 
        	return button ; 
        }
        
        public JButton getButton() {
        	return button ; 
        }        
        
        public String getName() {
            return name;
        }
     
        public String getCode() {
            return code;
        }
        
       

        
        public Image getImage() {
            return image;
        }

        @Override
        public String toString() {
            return name;
        }
        
    } 

}