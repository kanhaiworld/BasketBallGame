import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.*;
import javax.swing.*;
import java.util.Random;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class Panel extends JPanel implements ActionListener
{
    MyKeyAdapter kd = new MyKeyAdapter(); 
    static int PIXEL_SIZE = 20;
    
    static int ScreenWidth;
    static int ScreenHeight;
    static final int ALL_PIXELS = (ScreenWidth*ScreenHeight)/PIXEL_SIZE;
    static int DELAY = 40;
  
    final int xv[] = new int[ALL_PIXELS];
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
    
    int x = basketbalXVal;
    int y = basketbalYVal;
    double dx = 1.5;
    double dy = 2;
    int ticks=0;
    int angle = 10;
    
    public static void main(String[] args){
        
        Frame a = new Frame();
        Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();
        ScreenWidth = (int) screenSize.getWidth();
        ScreenHeight = (int) screenSize.getHeight();
        //PIXEL_SIZE = 16;//(ScreenWidth*ScreenHeight*20)/1350000;
        //System.out.print(ScreenWidth + " " + ScreenHeight);
        a.setSize((int) screenSize.getWidth(),(int) screenSize.getHeight());
        a.setBackground(Color.black);
        //System.out.println("listening");

        
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
        
        
         
        //System.out.println("I am here!");
        
        this.addKeyListener(kd);
        startGame();

    }
     public void startGame(){
        //newHoopL();
        gameOn = true;
        timer = new Timer(DELAY,this);
        timer.start();
    }
    /*
    public int[] projectilecalc(int angle, int initial){
       int t = 5;
       int a = -32;
       double vx = -32*t + initial * Math.sin(angle);
       double hx = -16*t + initial * t * Math.sin(angle);
       double vy = initial * Math.cos(angle);
       double hy = initial * t*Math.cos(angle) + 5;
       
    }
    */
    
    public void actionPerformed(ActionEvent e){
        //System.out.print("tick");
        ticks++;
        if(ticks>40){
            dx = 1;
            dy = 1.5;
        }
        if(ticks>90){
            dx = 2;
            dy = 2;
        }
        if(ticks>110){
            dx= 3;
            dy= 1; 
        }
        if(ticks>140){
            dx= 4;
            dy= 1; 
        }
        if(ticks>200){
            dx= 3;
            dy= -1; 
        }
        if(ticks>250){
            dx= 2;
            dy= -2; 
        }
        if(ticks>290){
            dx= 1.5;
            dy= -2; 
        }
        x+=dx;
        y-=dy;
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
                //int i =40;
                //g.clearRect(basketbalXVal,basketbalYVal,PIXEL_SIZE,PIXEL_SIZE);
                //g.setColor(Color.red);
                //g.clearRect(0,0,getWidth(),getHeight());
                g.setColor(Color.red);
                g.fillOval(x,y,PIXEL_SIZE,PIXEL_SIZE);
                
                //g.clearRect(basketbalXVal,basketbalYVal,PIXEL_SIZE,PIXEL_SIZE);
                
                /*
                 while(i>0){   
                    x+=1.5;
                    y-=2;
                    g.setColor(Color.red);
                    g.fillOval(x,y,PIXEL_SIZE,PIXEL_SIZE);
                }
                */
                
            }
            
            g.setColor(Color.red);
            g.fillOval(basketbalXVal,basketbalYVal,PIXEL_SIZE,PIXEL_SIZE);
            if(shoot == true){
              g.clearRect(basketbalXVal,basketbalYVal,PIXEL_SIZE,PIXEL_SIZE);  
            }
    }
    /*
    public void drawball(Graphics g){
         g.fillOval(basketbalXVal,basketbalYVal,PIXEL_SIZE,PIXEL_SIZE);
    
        }
    */
    public void arc(Graphics g){
        int x = basketbalXVal;
        int y = basketbalYVal;
        g.setColor(Color.black);
        
        //int slope = (int) Math.tan(Math.toRadians(angle));
        //System.out.println("slope: "+slope);
        //g.drawArc( x*PIXEL_SIZE, y*PIXEL_SIZE, 80, 80, 0, 110 );
        for(int i=0;i<4;i++){
            //g.setColor(Color.white);
            //g.fillOval(x,y,PIXEL_SIZE,PIXEL_SIZE);
            x+=1*PIXEL_SIZE;
            y-=1.5*PIXEL_SIZE;
            //g.setColor(Color.red);
            g.fillOval(x,y,PIXEL_SIZE/2,PIXEL_SIZE/2);
            
        }
        for(int i=0;i<3;i++){
            x+=2*PIXEL_SIZE;
            y-=2*PIXEL_SIZE;
            g.fillOval(x,y,PIXEL_SIZE/2,PIXEL_SIZE/2);
        }
        for(int i=0;i<2;i++){
            x+=3*PIXEL_SIZE;
            y-=PIXEL_SIZE;
            g.fillOval(x,y,PIXEL_SIZE/2,PIXEL_SIZE/2);
        }
        /*
        for(int i=0;i<1;i++){
            x+=4*PIXEL_SIZE;
            y-=PIXEL_SIZE;
            g.fillOval(x,y,PIXEL_SIZE/2,PIXEL_SIZE/2);
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
        */
    }
    
    public void newHoopL(){
        hoopXLoc = random.nextInt((int)(ScreenWidth/PIXEL_SIZE))*PIXEL_SIZE;
        
    }
    
    public void checkHoop(){
        if((xv[0] == hoopXLoc)){
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
    */
    
    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            
            int key = e.getKeyCode();
            if(key==32){
                System.out.print("yes");
                shoot = true;
            }
            /*
            if(key==KeyEvent.VK_UP && angle !=180){
                System.out.print("yes");
                angle+=5;
            }
            if(key==KeyEvent.VK_DOWN && angle !=90){
                System.out.print("yes");
                angle-=5;
            }
            */
        }
        
    } 
}
