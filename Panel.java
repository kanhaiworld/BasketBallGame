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
import java.lang.Math;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

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
    //static final int ALL_PIXELS = (ScreenWidth*ScreenHeight)/PIXEL_SIZE;
    static int DELAY = 5;
  
    //final int xv[] = new int[ALL_PIXELS];
    //final int y[] = new int[ALL_PIXELS];

    int points;
    static int hoopXLoc;
    static int hoopYLoc;
    static int lives = 3;

    static int basketbalXVal=10*PIXEL_SIZE;
    static int basketbalYVal;
    //char direction = 'R';
    
    boolean right = true;
    boolean left = false;
    //upon key listener getting indicated will turn to true and be used inside paint to draw an arc
    
    boolean shoot = false;
    boolean moveRight = false;
    boolean moveLeft = false;
    
    boolean gameOn = false;
    boolean running = true;
    Timer timer;
    Random random;
    
    static int cloudX;
    static int cloudY;
    static int cloud2X;
    static int [] bxval;
    
    int x = basketbalXVal;
    static int y = basketbalYVal;
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
    
    static int bbodyx;
    static int bheadx; 
    static int bleg1x;
    static int bleg2x;
    static int bhand1x;
    static int bhand2x;

    static int bbodyy;
    static int bheady; 
    static int bleg1y;
    static int bleg2y;
    static int bhand1y;
    static int bhand2y;
    static int wrandy;
    static int randomxrem;
    int rimx; 
    
    static boolean lifelost = false;
    
    
    public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        
        Frame a = new Frame();
        Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();
        ScreenWidth = (int) screenSize.getWidth();
        ScreenHeight = (int) screenSize.getHeight();
        //int basketbalXVal=ScreenWidth-50-10*PIXEL_SIZE;
        basketbalYVal=ScreenHeight-150;
        y=ScreenHeight-150;
        hoopXLoc=ScreenWidth-200;
        hoopYLoc=ScreenHeight-400;
        bbodyx= hoopXLoc-5-165;
        bheadx= hoopXLoc-5-170;
        bleg1x= hoopXLoc-5-170;
        bleg2x= hoopXLoc-5-160;
        bhand1x= hoopXLoc-5-180;
        bhand2x= hoopXLoc-5-150;
        
        bbodyy = ScreenHeight-92-40-PIXEL_SIZE;
        bheady = ScreenHeight-92-60-PIXEL_SIZE;
        bleg1y = ScreenHeight-92-PIXEL_SIZE;
        bleg2y = ScreenHeight-92-PIXEL_SIZE;
        bhand1y = ScreenHeight-92-30-PIXEL_SIZE-60;
        bhand2y = ScreenHeight-92-30-PIXEL_SIZE;
        //int bxval[] = {bbodyx, bheadx, bleg1x, bleg2x, bhand1x, bhand2x};
        
        cloudX=ScreenWidth-500;
        cloudY=50;
        cloud2X=cloudX-700;
        //System.out.print(ScreenHeight);
        //PIXEL_SIZE = 20;//(ScreenWidth*ScreenHeight*20)/1350000;
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
       
       File file = new File("correcttasty.wav");
       AudioInputStream audiostream = AudioSystem.getAudioInputStream(file);
       Clip clip = AudioSystem.getClip();
       
        clip.open(audiostream);
        clip.start();
           //lifelost=false;
        
        
       
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
        if(shoot==true){
            cloudX++;
            cloud2X--;
        }
        else{
            cloudX--;
            cloud2X++;
        }
        if(shoot == true){
    
            ticks++;
            if(ticks<40){
                dx=0;
                dy=0;
                int randomx = (int) (Math.random()*4) +1;
                randomxrem = randomx;
                bbodyx-= randomx;
                bheadx-= randomx;
                bleg1x-= randomx;
                bleg2x-= randomx;
                bhand1x-= randomx;
                bhand2x-= randomx;
                
                /*
                for(int i = 0; i< bxval.length; i++){
                    bxval[i]+=5;
                }
                */
            }
            if(ticks>40){
                bbodyx-= 1;
                bheadx-= 1;
                bleg1x-= 1;
                bleg2x-= 1;
                bhand1x-= 1;
                bhand2x-= 1;
                
                dx = 1;
                dy = 1.5;
            }
            if(ticks==90){
                int randy = (int) (Math.random()*80) +100;
                wrandy = randy;
                bbodyy-=randy;
                bheady-=randy; 
                bleg1y-=randy;
                bleg2y-=randy;
                bhand1y-=randy;
                bhand2y-=randy;
            }
            if(ticks==140){
                //int randy = (int) (Math.random()*100) +1;
                bbodyy+=wrandy;
                bheady+=wrandy; 
                bleg1y+=wrandy;
                bleg2y+=wrandy;
                bhand1y+=wrandy;
                bhand2y+=wrandy;
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
                bbodyx = hoopXLoc-5-165;
                bheadx = hoopXLoc-5-170;
                bleg1x = hoopXLoc-5-170;
                bleg2x = hoopXLoc-5-160;
                bhand1x= hoopXLoc-5-180;
                bhand2x= hoopXLoc-5-150;
                dx= 1.5;
                dy= -2; 
            }
            
            if(ticks>440){
                shoot=false;
                //System.out.println("x:"+x);
                //System.out.println("y:"+y);
                //System.out.println("bx:"+basketbalXVal);
                //System.out.println("by:"+basketbalYVal);
                x=basketbalXVal;
                y=basketbalYVal;
                ticks=0;   
            }
            if(x>=hoopXLoc-100 && x <=hoopXLoc+100 && y==hoopYLoc){
                shoot = false;
                x=basketbalXVal;
                y=basketbalYVal;
                ticks=0; 
                points++;
                System.out.println(points);
                hoopXLoc = (int)(Math.random() * 501) + 1000;
                bbodyx = hoopXLoc-5-165;
                bheadx = hoopXLoc-5-170;
                bleg1x = hoopXLoc-5-170;
                bleg2x = hoopXLoc-5-160;
                bhand1x= hoopXLoc-5-180;
                bhand2x= hoopXLoc-5-150;
            }
            if(ticks>439 && !(x>=hoopXLoc-100 && x <=hoopXLoc+100 && y==hoopYLoc)) {
                lives--;
                lifelost=true;
                
                //Audio a = new Audio();
                //a.playsound();
            
                if(lives==0){
                    running=false;
                }
            }
            //working
            //x>=bheadx && x<=bheadx+20 || 
            if(x>=bheadx && x<=bheadx+20 && y < bleg2y && y > bhand1y){
                shoot=false;
                x=basketbalXVal;
                y=basketbalYVal;
                ticks=0;
                lives--;
                //Audio a = new Audio();
                //a.playsound();
                lifelost=true;
                bbodyy = ScreenHeight-92-40-PIXEL_SIZE;
                bheady = ScreenHeight-92-60-PIXEL_SIZE;
                bleg1y = ScreenHeight-92-PIXEL_SIZE;
                bleg2y = ScreenHeight-92-PIXEL_SIZE;
                bhand1y = ScreenHeight-92-30-PIXEL_SIZE-60;
                bhand2y = ScreenHeight-92-30-PIXEL_SIZE;
                //bhand2x += randomxrem*40;
                bbodyx+= randomxrem*40;
                bheadx+= randomxrem*40;
                bleg1x+= randomxrem*40;
                bleg2x+= randomxrem*40;
                bhand1x+= randomxrem*40;
                bhand2x+= randomxrem*40;
                
                if(lives==0){
                    running=false;
                }
            }
            /*
            if(hand2x + 40 == bbodyx){
              lives--;
                
                if(lives==0){
                    running=false;
                }  
            }
            */
            
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
            g.fillRect(0,ScreenHeight-90,ScreenWidth,90); 
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
            
            
            g.fillOval(cloud2X, cloudY+50, 50, 60);
            g.fillOval(cloud2X+15, cloudY-25+50, 70, 80);
            g.fillOval(cloud2X+30, cloudY+30+50, 70, 50);
            g.fillOval(cloud2X+60, cloudY+50, 80, 60);
            g.fillOval(cloud2X+50, cloudY-30+50, 60, 40);
            g.fillOval(cloud2X+80, cloudY-20+50, 70, 60);
            g.fillOval(cloud2X+80, cloudY+20+50, 70, 60);
            g.fillOval(cloud2X+100, cloudY+50, 70, 60);
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
            g.fillRect(headx, ScreenHeight-92-60-PIXEL_SIZE, 20,20);
            g.fillRect(bodyx, ScreenHeight-92-40-PIXEL_SIZE, 10,40); 
            g.fillRect(leg1x, ScreenHeight-92-PIXEL_SIZE, 8, 30);
            g.fillRect(leg2x, ScreenHeight-92-PIXEL_SIZE, 8, 30);
            g.fillRect(hand1x, ScreenHeight-92-30-PIXEL_SIZE, 40, 10);
            g.fillRect(hand2x, ScreenHeight-92-30-PIXEL_SIZE, 10, 30);
            
            //blocker
            g.setColor(Color.white);
            g.fillRect(bheadx, bheady, 20,20);
            g.fillRect(bbodyx, bbodyy, 10,40); 
            g.fillRect(bleg1x, bleg1y, 8, 30);
            g.fillRect(bleg2x, bleg2y, 8, 30);
            g.fillRect(bhand1x, bhand1y, 10, 30);
            g.fillRect(bhand2x, bhand2y, 10, 30);
            
            g.setColor(Color.white);
            g.fillRect(hoopXLoc-PIXEL_SIZE,hoopYLoc,PIXEL_SIZE,PIXEL_SIZE);
            g.fillRect(hoopXLoc+5*PIXEL_SIZE,hoopYLoc,PIXEL_SIZE,PIXEL_SIZE);
            g.setColor(Color.orange);
            g.fillRect(hoopXLoc,hoopYLoc,5*PIXEL_SIZE,PIXEL_SIZE);
            g.setColor(Color.white);
            g.fillRect(hoopXLoc,hoopYLoc+PIXEL_SIZE,PIXEL_SIZE,PIXEL_SIZE);
            g.fillRect(hoopXLoc+2*PIXEL_SIZE,hoopYLoc+PIXEL_SIZE,PIXEL_SIZE,PIXEL_SIZE);
            g.fillRect(hoopXLoc+4*PIXEL_SIZE,hoopYLoc+PIXEL_SIZE,PIXEL_SIZE,PIXEL_SIZE);
            g.fillRect(hoopXLoc+3*PIXEL_SIZE,hoopYLoc+2*PIXEL_SIZE,PIXEL_SIZE,PIXEL_SIZE);
            g.fillRect(hoopXLoc+PIXEL_SIZE,hoopYLoc+2*PIXEL_SIZE,PIXEL_SIZE,PIXEL_SIZE);
            g.fillRect(hoopXLoc+3*PIXEL_SIZE,hoopYLoc+3*PIXEL_SIZE,PIXEL_SIZE,PIXEL_SIZE);
            g.fillRect(hoopXLoc+PIXEL_SIZE,hoopYLoc+3*PIXEL_SIZE,PIXEL_SIZE,PIXEL_SIZE);
            g.fillRect(hoopXLoc+2*PIXEL_SIZE,hoopYLoc+4*PIXEL_SIZE,PIXEL_SIZE,PIXEL_SIZE);
            
            //g.fillRect(hoopXLoc+4*PIXEL_SIZE,hoopYLoc+2*PIXEL_SIZE,PIXEL_SIZE,PIXEL_SIZE);
            
            g.setColor(new Color(65,105,225));
            g.setFont(new Font("Courier",Font.BOLD,40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: "+ points, (ScreenWidth - metrics.stringWidth("Score: "+ points))/2, g.getFont().getSize());
            g.setFont(new Font("Courier",Font.BOLD,40));
            //FontMetrics metrics = getFontMetrics(g.getFont());
            g.setColor(Color.red);
            g.drawString("Lives: "+ lives, (metrics.stringWidth("Lives: "+ lives)), g.getFont().getSize());
            
            g.setColor(new Color(162,38,194));
           
            //g.setFont(g.getFont().deriveFont(Font.BOLD,96F));
            g.setFont(new Font("Monospaced",Font.BOLD,96));
            //g.setFont(new Font("Courier",Font.BOLD,40));
            //String text = "BasketBallHeop";
            
            g.drawString("Basketball Game",(ScreenWidth - metrics.stringWidth("Basketball Game"))/2-200, 200);
            
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
            
            
            //g.setColor(Color.red);
            //g.fillOval(basketbalXVal,basketbalYVal,PIXEL_SIZE,PIXEL_SIZE);
            
            if(moveRight == true){
                bodyx+=10;
                headx += 10; 
                leg1x += 10;
                leg2x += 10;
                hand1x += 10;
                hand2x += 10;
                basketbalXVal+=10; 
                //x+=10; 
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
                //x-=10; 
            }
            moveLeft = false;
            
            if(shoot == true){
              g.clearRect(basketbalXVal,basketbalYVal,PIXEL_SIZE,PIXEL_SIZE);
              g.setColor(new Color(173,216,230));
              g.fillRect(basketbalXVal,basketbalYVal,PIXEL_SIZE,PIXEL_SIZE);
            }
            else{
                g.setColor(Color.red);
                g.fillOval(basketbalXVal,basketbalYVal,PIXEL_SIZE,PIXEL_SIZE);
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
       if(running ==false && lives==0){
               g.setColor(new Color(173,216,230));
                g.fillRect(0,0,ScreenWidth,ScreenHeight);
                g.setColor(new Color(65,105,225));
                g.setFont(new Font("Courier",Font.BOLD,40));
                FontMetrics metrics1 = getFontMetrics(g.getFont());
                g.drawString("Score: "+ points, (ScreenWidth - metrics1.stringWidth("Size: "+ points))/2, g.getFont().getSize());
                g.setColor(new Color(0,100,0));
                g.setFont(new Font("Courier",Font.BOLD,75));
                FontMetrics metrics2 = getFontMetrics(g.getFont());
                g.drawString("Game Over", (ScreenWidth - metrics2.stringWidth("Game Over"))/2, ScreenHeight/2);
       }
    }
    
    public void newHoopL(){
        hoopXLoc = random.nextInt((int)(ScreenWidth/PIXEL_SIZE))*PIXEL_SIZE;
        
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
            
            if(key==32 && ticks==0){
                //System.out.print("yes");
                x=basketbalXVal;
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
