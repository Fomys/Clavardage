package test.testForTransparent ; 
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsDevice.WindowTranslucency;
import java.awt.GraphicsEnvironment;
import java.awt.Paint;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class transparent extends JFrame {

public transparent() {
    super("Transparent Window");
    setBackground(new Color(0,0,0,0));

    //setting it causes the frame to be transparent .Hence both panel and frame are transparent.
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(300,200);
    getContentPane().setLayout(new FlowLayout());
    setUndecorated(true);


    JPanel jp=new JPanel(){
        public void paintComponent(Graphics g)
        {
            //super.paintComponent(g);
            Graphics2D g2=(Graphics2D)g;
            Paint gp=new GradientPaint(0, 0, new Color(100,20,210,105), 0, 200, new Color(80,20,40,105));
            g2.setPaint(gp);
            g2.fillRect(0, 0, getWidth(),getHeight());
        }
    };
    //setOpacity(0.6f);
    setContentPane(jp);
    JButton jbtn=new JButton("Enter");
    jp.add(jbtn);
    setVisible(true);
}
public static void main(String[] args) {
    // TODO Auto-generated method stub
    GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice gd=ge.getDefaultScreenDevice();
    if(!gd.isWindowTranslucencySupported(WindowTranslucency.PERPIXEL_TRANSLUCENT))
    {
        System.out.println("Per-pixel Transency not supported");
        System.exit(0);
    }
    JFrame.setDefaultLookAndFeelDecorated(true);

    //setting it to true causes the look to be handled by look and feel. otherwise os look and feel is used
    //In other words ,it is,modify default look and feel-yes or no?
    SwingUtilities.invokeLater(new Runnable(){public void run(){new transparent();}});
}
/*public void paint(Graphics g)
{
    super.paint(g);
    Graphics2D g2=(Graphics2D)g;
    GradientPaint gp=new GradientPaint(0, 0, new Color(20,20,210,30), 300, 200, new Color(10,20,40,255),true);
    g2.setPaint(gp);
    g2.fillRect(0, 0, getWidth(),getHeight());
}*/
}