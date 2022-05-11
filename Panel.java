import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.*;
import javax.swing.*;
import java.util.Random;


public class Panel extends JPanel
{
    static final int PIXEL_SIZE = 20;
    static final int SCREEN_WIDTH = 1500;
    static final int SCREEN_HEIGHT = 900;
    static final int ALL_PIXELS = (SCREEN_WIDTH*SCREEN_HEIGHT)/PIXEL_SIZE;
    static int DELAY = 75;
  
    final int x[] = new int[ALL_PIXELS];
    final int y[] = new int[ALL_PIXELS];

    int points;
    int hoopXLoc;

    int basketbalXVal=5*PIXEL_SIZE;
    int basketbalYVal= 36*PIXEL_SIZE;
    //char direction = 'R';
    
    boolean right = true;
    boolean left = false;
    
    
    boolean gameOn = false;
    Timer timer;
    Random random;
    
    public static void main(String[] args){
        
        Frame a = new Frame();
        Dimension size= Toolkit.getDefaultToolkit().getScreenSize();
        a.setSize(1500,900);
        a.setBackground(Color.black); 
        
        /*
        JLabel bg = new JLabel();
        bg.setSize((int) size.getWidth(),(int) size.getHeight());
        ImageIcon img = new ImageIcon("monkey.jpg");
        bg = new JLabel("",img,JLabel.CENTER);
        bg.setBounds(0,0,(int) size.getWidth(),(int) size.getHeight());
        //.setBackground(Color.black);

        a.add(bg);
        */
    }
    public Panel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        //this.setBackground(Color.black); 
        this.setFocusable(true);
        
        
         
     
        //this.addKeyListener(new KeyAdapter());
        

    }
     public void startGame(){
        newHoopL();
        gameOn = true;
        //timer = new Timer(DELAY,this);
        timer.start();
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
        arc(g);
    }
    
    public void draw(Graphics g){
         
            for(int i =0; i<SCREEN_HEIGHT/PIXEL_SIZE;i++){
                //g.drawLine(i*PIXEL_SIZE, 0, i*PIXEL_SIZE, SCREEN_HEIGHT);
                
                g.drawLine(0,i*PIXEL_SIZE, SCREEN_WIDTH, i*PIXEL_SIZE);
            }
            for(int i =0; i<SCREEN_WIDTH/PIXEL_SIZE;i++){
                g.drawLine(i*PIXEL_SIZE, 0, i*PIXEL_SIZE, SCREEN_HEIGHT);
                
                //g.drawLine(0,i*PIXEL_SIZE, SCREEN_WIDTH, i*PIXEL_SIZE);
            }
   
            
            g.setColor(Color.orange);
            g.fillOval(5*PIXEL_SIZE,basketbalYVal,PIXEL_SIZE,PIXEL_SIZE);
    }
    public void drawball(Graphics g){
         g.fillOval(basketbalXVal,basketbalYVal,PIXEL_SIZE,PIXEL_SIZE);
    
    }
    
    public void arc(Graphics g){
        basketbalYVal+=PIXEL_SIZE;
        basketbalXVal+=PIXEL_SIZE;
        drawball(g);
    }
    
    public void newHoopL(){
        hoopXLoc = random.nextInt((int)(SCREEN_WIDTH/PIXEL_SIZE))*PIXEL_SIZE;
        
    }
    
    public void checkHoop(){
        if((x[0] == hoopXLoc)){
            points ++;
            newHoopL();
        }
    }
    
    public void move(){
        
    }
 
    public void checkBlock(){
        
    }
    /*
    @Override
  public void actionPerformed(ActionEvent e){
        if(gameOn){
            move();
            checkApple();
            checkCollisions();
            
        }
        repaint();
    }
    
    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            
            int key = e.getKeyCode();
            if((key==KeyEvent.VK_LEFT) && (right == false)){
                left = true;
                up = false;
                down=false;
            }
            if((key==KeyEvent.VK_RIGHT) && (left == false)){
                right = true;
                up = false;
                down=false;
            }
            if((key==KeyEvent.VK_UP) && (down == false)){
                up = true;
                right = false;
                left =false;
            }
            if((key==KeyEvent.VK_DOWN) && (up == false)){
                down = true;
                right = false;
                left =false;
            }
            if((key==KeyEvent.VK_ENTER) && (gameOn == false)){
                GUI new1 = new GUI();
                //GamePanel();
                //window.setVisible(true);
            }
            
            /*
            switch(e.getKeyCode()){
                
                case KeyEvent.VK_LEFT:
                    if(direction != 'R'){
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L'){
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D'){
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U'){
                        direction = 'D';
                    }
                    break;
                    
            }
            
            
        }
    } 
    */
}
