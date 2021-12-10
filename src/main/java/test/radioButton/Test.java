package test.radioButton;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

public class Test {

    private ImageIcon ii1;
    private ImageIcon ii2;
    private JRadioButton jrb = new JRadioButton("Click me :)");
    private JFrame frame = new JFrame();

    public Test() {
        try {
            ii1 = new ImageIcon(ImageIO.read(new URL("http://cdn.macrumors.com/article/2010/09/03/145454-itunes_10_icon.jpg")).getScaledInstance(48, 48, Image.SCALE_SMOOTH));
            ii2 = new ImageIcon(ImageIO.read(new URL("http://www.quarktet.com/Icon-small.jpg")).getScaledInstance(48, 48, Image.SCALE_SMOOTH));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        initComponents();
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Test();
            }
        });
    }

    private void initComponents() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jrb.setIcon(ii1);
        jrb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (jrb.getIcon() == ii1) {
                    jrb.setIcon(ii2);
                } else {
                    jrb.setIcon(ii1);
                }
            }
        });

        frame.add(jrb);
        frame.pack();
        frame.setVisible(true);
    }
}