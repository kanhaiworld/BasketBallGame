import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.*;
import javax.swing.*;
import java.util.Random;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList; 

import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class Panel extends JPanel implements ActionListener
{
    ArrayList<Integer> PersonX = new ArrayList<>();    
    MyKeyAdapter kd = new MyKeyAdapter(); 
    static int PIXEL_SIZE = 20;
    
    static int ScreenWidth;
    static int ScreenHeight;
    static final int ALL_PIXELS = (ScreenWidth*ScreenHeight)/PIXEL_SIZE;
    static int DELAY = 10;
  
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
    
    boolean shoot = false;
    boolean moveRight = false;
    boolean moveLeft = false;
    
    boolean gameOn = false;
    Timer timer;
    Random random;
    
    static int cloudX;
    static int cloudY;
    
    int x = basketbalXVal;
    int y = basketbalYVal;
    double dx = 1.5;
    double dy = 2;
    int ticks=0;
    int angle = 10;
    int bodyx = 165;
    int headx = 160; 
    int leg1x = 170;
    int leg2x = 160;
    int hand1x = 165;
    int hand2x = 150;
    int polex; 
    int backBoardx; 
    int rimx; 
    
    public static void main(String[] args){
        
        Frame a = new Frame();
        Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();
        ScreenWidth = (int) screenSize.getWidth();
        ScreenHeight = (int) screenSize.getHeight();
        cloudX=ScreenWidth-500;
        cloudY=50;
        System.out.print(ScreenHeight);
        PIXEL_SIZE = 20;//(ScreenWidth*ScreenHeight*20)/1350000;
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
        
        if(shoot == true){
            
            ticks++;
            if(ticks<40){
                dx=0;
                dy=0;
            }
            if(ticks>40){
                dx = 1;
                dy = 1.5;
            }
            if(ticks>90){
                dx = 2;
                dy = 1.5;
            }
            if(ticks>110){
                dx= 3;
                dy= 1.5; 
            }
            if(ticks>140){
                dx= 4;
                dy= 1.5; 
            }
            if(ticks>200){
                dx= 3;
                dy= -1.5; 
            }   
            if(ticks>250){
                dx= 2;
                dy= -1.5; 
            }
            if(ticks>290){
                dx= 1.5;
                dy= -2; 
            }
            
            if(ticks>390){
                shoot=false;
            }
            
            x+=dx;
            y-=dy;
            repaint();
        }
        repaint();
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
        arc(g);
    }
    
    public void draw(Graphics g){
            //bg
            g.setColor(new Color(173,216,230));
            g.fillRect(0,0,ScreenWidth, ScreenHeight - PIXEL_SIZE);
            //ground
            g.setColor(new Color(0,100,0));
            g.fillRect(0,775,ScreenWidth,PIXEL_SIZE); 
            //clouds
            g.setColor(Color.white);
            g.fillOval(cloudX, cloudY, 50, 60);
            g.fillOval(cloudX+15, cloudY-25, 70, 80);
            g.fillOval(cloudX+30, cloudY+30, 70, 50);
            g.fillOval(cloudX+60, cloudY, 80, 60);
            g.fillOval(cloudX+50, cloudY-30, 60, 40);
            g.fillOval(cloudX+80, cloudY-20, 70, 60);
            g.fillOval(cloudX+80, cloudY+20, 70, 60);
            g.fillOval(cloudX+100, cloudY, 70, 60);
            
            g.fillOval(cloudX+200, cloudY+50, 50, 60);
            g.fillOval(cloudX+15+200, cloudY-25+50, 70, 80);
            g.fillOval(cloudX+30+200, cloudY+30+50, 70, 50);
            g.fillOval(cloudX+60+200, cloudY+50, 80, 60);
            g.fillOval(cloudX+50+200, cloudY-30+50, 60, 40);
            g.fillOval(cloudX+80+200, cloudY-20+50, 70, 60);
            g.fillOval(cloudX+80+200, cloudY+20+50, 70, 60);
            g.fillOval(cloudX+100+200, cloudY+50, 70, 60);
            /*
            for(int i =0; i<ScreenHeight/PIXEL_SIZE;i++){
                //g.drawLine(i*PIXEL_SIZE, 0, i*PIXEL_SIZE, SCREEN_HEIGHT);
                
                g.drawLine(0,i*PIXEL_SIZE, ScreenWidth, i*PIXEL_SIZE);
            }
            for(int i =0; i<ScreenWidth/PIXEL_SIZE;i++){
                g.drawLine(i*PIXEL_SIZE, 0, i*PIXEL_SIZE, ScreenHeight);
                
                //g.drawLine(0,i*PIXEL_SIZE, SCREEN_WIDTH, i*PIXEL_SIZE);
            }
            */
            g.setColor(Color.black);
            g.fillRect(headx, 700-PIXEL_SIZE, 20,20);
            g.fillRect(bodyx, 720-PIXEL_SIZE, 10,40); 
            g.fillRect(leg1x, 760-PIXEL_SIZE, 8, 30);
            g.fillRect(leg2x, 760-PIXEL_SIZE, 8, 30);
            g.fillRect(hand1x, 730-PIXEL_SIZE, 40, 10);
            g.fillRect(hand2x, 730-PIXEL_SIZE, 10, 30);
            
            
               
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
              g.setColor(new Color(173,216,230));
              g.fillRect(basketbalXVal,basketbalYVal,PIXEL_SIZE,PIXEL_SIZE);
            }
            
            if(moveRight == true){
                bodyx+=10;
                headx += 10; 
                leg1x += 10;
                leg2x += 10;
                hand1x += 10;
                hand2x += 10;
                basketbalXVal+=10; 
                x+=10; 
            }
            moveRight = false;
            
            if(moveLeft == true){
                bodyx -= 10;
                headx -= 10; 
                leg1x -= 10;
                leg2x -= 10;
                hand1x -= 10;
                hand2x -= 10;
                basketbalXVal-=10;
                x-=10; 
            }
            moveLeft = false;
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
                //System.out.print("yes");
                shoot = true;
            }
            if(key==KeyEvent.VK_RIGHT){
                moveRight = true;
            }
            if(key==KeyEvent.VK_LEFT){
                moveLeft = true;
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
