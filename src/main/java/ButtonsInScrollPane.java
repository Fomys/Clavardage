
/*
 * Liste utilisateur 
 * https://stackoverflow.com/questions/33894580/scrollable-list-with-items-of-set-pixel-height-in-java-swing
 */ 

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

