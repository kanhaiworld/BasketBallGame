import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.*;
import javax.swing.*;
import java.util.Random;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class Panel extends JPanel implements ActionListener
{
    static int PIXEL_SIZE = 20;
    
    static int ScreenWidth;
    static int ScreenHeight;
    static final int ALL_PIXELS = (ScreenWidth*ScreenHeight)/PIXEL_SIZE;
    static int DELAY = 75;
  
    final int x[] = new int[ALL_PIXELS];
    //final int y[] = new int[ALL_PIXELS];

    int points;
    int hoopXLoc;

    int basketbalXVal=10*PIXEL_SIZE;
    int basketbalYVal= 36*PIXEL_SIZE;
    //char direction = 'R';
    
    boolean right = true;
    boolean left = false;
    //upon key listener getting indicated will turn to true and be used inside paint to draw an arc
    boolean shoot =false;
    
    boolean gameOn = false;
    Timer timer;
    Random random;
    
    public static void main(String[] args){
        
        Frame a = new Frame();
        Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();
        ScreenWidth = (int) screenSize.getWidth();
        ScreenHeight = (int) screenSize.getHeight();
        //PIXEL_SIZE = 16;//(ScreenWidth*ScreenHeight*20)/1350000;
        System.out.print(ScreenWidth + " " + ScreenHeight);
        a.setSize((int) screenSize.getWidth(),(int) screenSize.getHeight());
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
        //this.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        //this.setBackground(Color.black); 
        this.setFocusable(true);
        
        
         
     
        //this.addKeyListener(new KeyAdapter());
        

    }
     public void startGame(){
        newHoopL();
        gameOn = true;
        timer = new Timer(DELAY,this);
        timer.start();
    }
    
    public void actionPerformed(ActionEvent e){
        repaint();
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
        arc(g);
    }
    
    public void draw(Graphics g){
         
            for(int i =0; i<ScreenHeight/PIXEL_SIZE;i++){
                //g.drawLine(i*PIXEL_SIZE, 0, i*PIXEL_SIZE, SCREEN_HEIGHT);
                
                g.drawLine(0,i*PIXEL_SIZE, ScreenWidth, i*PIXEL_SIZE);
            }
            for(int i =0; i<ScreenWidth/PIXEL_SIZE;i++){
                g.drawLine(i*PIXEL_SIZE, 0, i*PIXEL_SIZE, ScreenHeight);
                
                //g.drawLine(0,i*PIXEL_SIZE, SCREEN_WIDTH, i*PIXEL_SIZE);
            }
               
            if(shoot==true){
                int x = basketbalXVal;
                int y = basketbalYVal;
        
                for(int i=0;i<40;i++){
                    g.setColor(Color.white);
                    g.fillOval(x,y,PIXEL_SIZE,PIXEL_SIZE);
                    x+=1.5;
                    y-=2;
                    g.setColor(Color.red);
                    g.fillOval(x,y,PIXEL_SIZE,PIXEL_SIZE);
            
                }
            }
            
            g.setColor(Color.red);
            g.fillOval(basketbalXVal,basketbalYVal,PIXEL_SIZE,PIXEL_SIZE);
    }
    /*
    public void drawball(Graphics g){
         g.fillOval(basketbalXVal,basketbalYVal,PIXEL_SIZE,PIXEL_SIZE);
    
        }
    */
    public void arc(Graphics g){
        int x = basketbalXVal;
        int y = basketbalYVal;
        
        for(int i=0;i<4;i++){
            //g.setColor(Color.white);
            //g.fillOval(x,y,PIXEL_SIZE,PIXEL_SIZE);
            x+=1.5*PIXEL_SIZE;
            y-=2*PIXEL_SIZE;
            //g.setColor(Color.red);
            g.fillOval(x,y,PIXEL_SIZE,PIXEL_SIZE);
            
        }
        for(int i=0;i<3;i++){
            x+=2*PIXEL_SIZE;
            y-=2*PIXEL_SIZE;
            g.fillOval(x,y,PIXEL_SIZE,PIXEL_SIZE);
        }
        for(int i=0;i<2;i++){
            x+=3*PIXEL_SIZE;
            y-=PIXEL_SIZE;
            g.fillOval(x,y,PIXEL_SIZE,PIXEL_SIZE);
        }
        for(int i=0;i<1;i++){
            x+=4*PIXEL_SIZE;
            y-=PIXEL_SIZE;
            g.fillOval(x,y,PIXEL_SIZE,PIXEL_SIZE);
        }
        for(int i=0;i<2;i++){
            x+=3*PIXEL_SIZE;
            y-=-PIXEL_SIZE;
            g.fillOval(x,y,PIXEL_SIZE,PIXEL_SIZE);
        }
        for(int i=0;i<3;i++){
            x+=2*PIXEL_SIZE;
            y-=-2*PIXEL_SIZE;
            g.fillOval(x,y,PIXEL_SIZE,PIXEL_SIZE);
        }
        for(int i=0;i<6;i++){
            x+=1.5*PIXEL_SIZE;
            y-=-2*PIXEL_SIZE;
            g.fillOval(x,y,PIXEL_SIZE,PIXEL_SIZE);
        }
    }
    
    public void newHoopL(){
        hoopXLoc = random.nextInt((int)(ScreenWidth/PIXEL_SIZE))*PIXEL_SIZE;
        
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
