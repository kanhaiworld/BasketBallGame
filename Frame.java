
import javax.swing.JFrame; 
public class Frame extends JFrame
{
  
    public Frame()
    {
        this.add(new Panel());
        this.setTitle("Basketball Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setResizable(false); 
        this.pack();/* 
        When we add new components to a JFrame, pack will fit those nicely to 
        to the components we have made for the Panel. 
        */
        this.setVisible(true); 
        //this.setLocationRelativeTo(null); 
    }

}
