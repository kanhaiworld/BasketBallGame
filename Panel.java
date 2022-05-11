import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.*;
import javax.swing.*;


public class Panel extends JPanel implements ActionListener
{
    public static void main(String[] args){
        
        Frame a = new Frame();
        Dimension size= Toolkit.getDefaultToolkit().getScreenSize();
        a.setSize((int) size.getWidth(),(int) size.getHeight());
        
        JLabel bg = new JLabel();
        bg.setSize((int) size.getWidth(),(int) size.getHeight());
        ImageIcon img = new ImageIcon("Red.jpg");
        bg = new JLabel("",img,JLabel.CENTER);
        bg.setBounds(0,0,(int) size.getWidth(),(int) size.getHeight());
        bg.setBackground(Color.black);

        a.add(bg);
    }
    public Panel(){
        
    }
    public void startGame(){
        
    }
    public void paintComponent(Graphics g){
        
    }
    public void move(){
        
    }
    @Override
 public void actionPerformed(ActionEvent e){
     
 }
}
