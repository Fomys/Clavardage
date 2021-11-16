import java.awt.event.*;
import javax.swing.*;
 
public class SizeFrame extends JFrame implements ComponentListener {
 

  JLabel label;
 
  SizeFrame(){
    getContentPane().setLayout(null);
    label = new JLabel();
    label.setBounds(0, 0, 450, 272);
    getContentPane().add(label);
    getContentPane().addComponentListener(this);
  }
 
  public void componentHidden(ComponentEvent ce) {};
  public void componentShown(ComponentEvent ce) {};
  public void componentMoved(ComponentEvent ce) {};
  public void componentResized(ComponentEvent ce) {
    int height = this.getHeight();
    int width = this.getWidth();
    label.setText("Size: " + height + "x" + width);
  };
 
  public static void main(String[] arguments) {
 
    //JFrame.setDefaultLookAndFeelDecorated(true);
    SizeFrame frame = new SizeFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("JFrame Resizing Example");
    frame.setSize(300,150);
    frame.setVisible(true);
 
  }
}
