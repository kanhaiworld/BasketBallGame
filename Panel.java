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
//import java.util.*;

public class Panel extends JPanel implements ActionListener
{
    //key adapter that allows to see what player clicks    
    MyKeyAdapter kd = new MyKeyAdapter();
    //size of your "game pixel"
    static int PIXEL_SIZE = 20;
    static int ScreenWidth;
    static int ScreenHeight;
    //delay for ticks
    static int DELAY = 5;

    //general game variables
    int points;
    static int hoopXLoc;
    static int hoopYLoc;
    static int lives = 3;
    
    static int basketbalXVal=10*PIXEL_SIZE;
    static int basketbalYVal;

    //person movement  
    boolean right = true;
    boolean left = false;
    
    //person is moving
    boolean shoot = false;
    boolean moveRight = false;
    boolean moveLeft = false;
    
    //variables to start the game
    boolean gameOn = false;
    boolean running = true;
    Timer timer;
    Random random;
 
    //location for objects cloud and ball (x,y)
    static int cloudX;
    static int cloudY;
    static int cloud2X;  
    int x = basketbalXVal;
    static int y = basketbalYVal;
    
    //change in ball slope
    double dx = 1.5;
    double dy = 2;
    //counting ticks 
    int ticks=0;
    //int angle = 10;
    //body variables
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
    //keeping track of random number generated
    static int randomxrem;
    static double angle;
    static double velocity;
    static double t;
    static double t2;
    static double hx;
    static double vy;
    static double vx;
    static double hy;
    static double acc = -9.81;
    static double totalTime;
    static double timeIncrement;
    static double xIncrement;
    static boolean anglec = false;
    static double xa;
    static double ya;
    static double ta;
    static double slope1;
    static double slope2;
    static double slope3;
    static double slope4;
    static double slope5;
    static double slope6;
    static double slope7;
    static double change; 
    public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        //initializing variables
        Frame a = new Frame();
        Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();
        ScreenWidth = (int) screenSize.getWidth();
        ScreenHeight = (int) screenSize.getHeight();
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
        cloudX=ScreenWidth-500;
        cloudY=50;
        cloud2X=cloudX-700;
        a.setSize((int) screenSize.getWidth(),(int) screenSize.getHeight());
        a.setBackground(Color.black);
         
        //velocity = (int) (Math.random()*20) +1;
        velocity = 96;
        angle = Math.toRadians(60.0);
        t = (velocity* Math.sin(angle))/16;
        hx = velocity * t * Math.cos(angle);
        t2 = (velocity * Math.sin(angle))/32;
        hy = (-16 * (t2*t2)) + (velocity* t2 * Math.sin(angle));
        //System.out.println(t);
        //System.out.println(hx);
        //System.out.println(t2);
        //System.out.println(hy);
        
        vy = velocity * Math.sin(angle);
        vx = velocity * Math.cos(angle);
        totalTime = - 2.0 * vy / acc;
        timeIncrement = totalTime / 7;
        xIncrement = vx * timeIncrement;
        double[] xval = new double[7];
        double[] yval = new double[7];
        for (int i = 1; i <= 7; i++) {
            ta += timeIncrement;
            xa += xIncrement;
            ya = vy* ta + 0.5 * acc * ta * ta;
            xval[i-1] = xa;
            yval[i-1] = ya;
        }
        //change = (int)(timeIncrement*10);
        System.out.println(change);
        slope1 = yval[0];
        slope2 = (yval[1]-yval[0]);
        slope3 = (yval[2]-yval[1]);
        slope4 = (yval[3]-yval[2]);
        slope5 = (yval[4]-yval[3]);
        slope6 = (yval[5]-yval[4]);
        slope7 = (yval[6]-yval[5]);
        /*
        for (int i =0; i<xval.length; i++){
            System.out.print(xval[i]+",");
        }
        System.out.println();
        for (int i =0; i<yval.length; i++){
            System.out.print(yval[i]+",");
        }
        */
        //System.out.println("hi");
        //playing audio(music)
       File file = new File("correcttasty.wav");
       AudioInputStream audiostream = AudioSystem.getAudioInputStream(file);
       Clip clip = AudioSystem.getClip();
       
        clip.open(audiostream);
        clip.start();
  
        
        
       
    }
    public Panel(){
        //starting game - starting game functions
        random = new Random();
        this.setFocusable(true);
        this.addKeyListener(kd);
        startGame();
        }
     public void startGame(){
        //starting timer and ticks
        gameOn = true;
        timer = new Timer(DELAY,this);
        timer.start();
    }
    //this is called every "tick" these actions are performed
    public void actionPerformed(ActionEvent e){
        if(anglec == true){
            t = (velocity* Math.sin(angle))/16;
            hx = velocity * t * Math.cos(angle);
            t2 = (velocity * Math.sin(angle))/32;
            hy = (-16 * (t2*t2)) + (velocity* t2 * Math.sin(angle));
            //System.out.println(t);
            //System.out.println(hx);
            //System.out.println(t2);
            //System.out.println(hy);
            anglec = false;
        }
        /*
        within this many actions occur here are the most important:
        ball moves in arc
        points are awarded
        lives are removed if necessary
        animations are played (clouds, npc movement(randomized))
        */
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
                

            }
            if(ticks>40){
                bbodyx-= 1;
                bheadx-= 1;
                bleg1x-= 1;
                bleg2x-= 1;
                bhand1x-= 1;
                bhand2x-= 1;
                
                dx = xIncrement/100;
                dy = slope1/100;
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
                bbodyy+=wrandy;
                bheady+=wrandy; 
                bleg1y+=wrandy;
                bleg2y+=wrandy;
                bhand1y+=wrandy;
                bhand2y+=wrandy;
            }
            if(ticks>90){
                
                dx = xIncrement/100;
                dy = slope2/100;
            }
            if(ticks>110){
                
                dx= xIncrement/1004;
                dy= slope3/100; 
            }
            if(ticks>140){
                dx= xIncrement/100;
                dy= slope4/100; 
            }
            if(ticks>200){
                dx= xIncrement/100;
                dy= slope5/100; 
            }   
            if(ticks>250){
                dx= xIncrement/100;
                dy= slope6/100; 
            }
            if(ticks>290){
                bbodyx = hoopXLoc-5-165;
                bheadx = hoopXLoc-5-170;
                bleg1x = hoopXLoc-5-170;
                bleg2x = hoopXLoc-5-160;
                bhand1x= hoopXLoc-5-180;
                bhand2x= hoopXLoc-5-150;
                dx= xIncrement/100;
                dy= slope7/100; 
            }
            
            if(ticks>440){
                shoot=false;
                x=basketbalXVal;
                y=basketbalYVal;
                ticks=0;   
            }
            if(x>=hoopXLoc-80 && x <=hoopXLoc+80 && y==hoopYLoc){
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

                if(lives==0){
                    running=false;
                }
            }

            if(x>=bheadx && x<=bheadx+20 && y < bbodyy && y > bhand1y-40){
                shoot=false;
                x=basketbalXVal;
                y=basketbalYVal;
                ticks=0;
                lives--;

                bbodyy = ScreenHeight-92-40-PIXEL_SIZE;
                bheady = ScreenHeight-92-60-PIXEL_SIZE;
                bleg1y = ScreenHeight-92-PIXEL_SIZE;
                bleg2y = ScreenHeight-92-PIXEL_SIZE;
                bhand1y = ScreenHeight-92-30-PIXEL_SIZE-60;
                bhand2y = ScreenHeight-92-30-PIXEL_SIZE;
                bbodyx+= randomxrem*40+150;
                bheadx+= randomxrem*40+150;
                bleg1x+= randomxrem*40+150;
                bleg2x+= randomxrem*40+150;
                bhand1x+= randomxrem*40+150;
                bhand2x+= randomxrem*40+150;
                
                if(lives==0){
                    running=false;
                }
            }

            x+=dx;
            y-=dy;
            repaint();
        }
        repaint();
    }
    //method required to use graphics (used to draw shapes)
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
        arc(g);
    }
    /*
    is called every tick to redraw in order to make animations
    within this everything on the screen is drawn including:
    all backgrounds
    players
    ball animations
    npc animations
    */
    public void draw(Graphics g){
            //bg
            g.setColor(new Color(173,216,230));
            g.fillRect(0,0,ScreenWidth, ScreenHeight - PIXEL_SIZE);
            //hills & mountain
            int[] a = new int[3];
            int[] b = new int[3];
            a[0]=200*3+600; 
            a[1]=125*3+500; 
            a[2]=100*3+400;
            b[0]=100*10+500; 
            b[1]=25*10+500; 
            b[2]=100*10+500;
            g.setColor(new Color(172,172,172));
            Polygon p = new Polygon(a, b, 3);
            g.fillPolygon(p);
            g.setColor(new Color(15,114,22));
            g.fillOval(ScreenWidth/4-900, ScreenHeight-ScreenHeight/4+30, 1250, 2000);
            g.setColor(new Color(71,182,37));
            g.fillOval(ScreenWidth/4-700, ScreenHeight-ScreenHeight/4, 1250, 2000);
            g.setColor(new Color(116,204,140));
            g.fillOval(ScreenWidth/4+300, ScreenHeight-ScreenHeight/4-70, 1250, 2000);
            g.setColor(new Color(15,114,22));
            g.fillOval(ScreenWidth/4, ScreenHeight-ScreenHeight/4+50, 1250, 2000);
            g.setColor(new Color(15,114,22));
            g.fillOval(ScreenWidth/4+900, ScreenHeight-ScreenHeight/4+10, 1250, 2000);
            g.setColor(new Color(71,182,37));
            g.fillOval(ScreenWidth/4+500, ScreenHeight-ScreenHeight/4+20, 1250, 2000);
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
            
            //player
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
             
            //hoop
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
            
            //lives & score & title display
            g.setColor(new Color(65,105,225));
            g.setFont(new Font("Courier",Font.BOLD,40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: "+ points, (ScreenWidth - metrics.stringWidth("Score: "+ points))/2, g.getFont().getSize());
            g.setFont(new Font("Courier",Font.BOLD,40));
            g.setColor(Color.red);
            g.drawString("Lives: "+ lives, (metrics.stringWidth("Lives: "+ lives)), g.getFont().getSize());
            
            g.setColor(new Color(162,38,194));
           
            g.setFont(new Font("Monospaced",Font.BOLD,96));

            
            g.drawString("Basketball Game",(ScreenWidth - metrics.stringWidth("Basketball Game"))/2-200, 200);
           
            //ball animation
            if(shoot==true){

                g.setColor(Color.red);
                g.fillOval(x,y,PIXEL_SIZE,PIXEL_SIZE);
 
            }
            
            //person animations
            if(moveRight == true){
                bodyx+=10;
                headx += 10; 
                leg1x += 10;
                leg2x += 10;
                hand1x += 10;
                hand2x += 10;
                basketbalXVal+=10; 
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
            }
            moveLeft = false;
            
            //changing arm if shooting
            if(shoot == true){

                g.clearRect(basketbalXVal,basketbalYVal,10,PIXEL_SIZE);
              g.setColor(Color.black);
              g.fillRect(basketbalXVal,basketbalYVal,10,PIXEL_SIZE);
            }
            else{
                g.setColor(Color.red);
                g.fillOval(basketbalXVal,basketbalYVal,PIXEL_SIZE,PIXEL_SIZE);
            }
            g.setColor(Color.yellow);
            //g.fillOval((int)hy,500,PIXEL_SIZE,PIXEL_SIZE);
            g.drawArc(basketbalXVal,basketbalYVal-(int)hx*2,(int) hy*5,(int) hx*5,0,180); 
    }
    //draws the reference arc(black dots)
    public void arc(Graphics g){
        int x = basketbalXVal;
        int y = basketbalYVal;
        g.setColor(Color.black);
        for(int i=0;i<4;i++){
            
            x+=1*PIXEL_SIZE;
            y-=1.5*PIXEL_SIZE;
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
        //prints game over screen if 0 lives
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
    
    //changes hoop location
    public void newHoopL(){
        hoopXLoc = random.nextInt((int)(ScreenWidth/PIXEL_SIZE))*PIXEL_SIZE;
        
    }
 
    //player inputs to move and shoot
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
            if(key==KeyEvent.VK_UP){
                anglec = true;
                angle+=Math.toRadians(1.0);
            }
            if(key==KeyEvent.VK_DOWN){
                anglec = true;
                angle-=Math.toRadians(1.0);
            }
        }
        
    } 
}
