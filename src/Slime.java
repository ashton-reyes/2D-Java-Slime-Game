
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Slime
{
    private int health;
    private int x, y, diam;
    private int vX, vY;
    private int time, moveTime;
    private int hitTime, hurtTime;
    private int randomDir;
    public String faceDirection;
    private boolean hurtState;
    private boolean slimeTypeFast, slimeTypeTank, slimeTypeBoss;
    private ImageIcon slimeRight1, slimeLeft1, slimeFront1, slimeBack1;
    private ImageIcon slimeFastRight1, slimeFastLeft1, slimeFastFront1, slimeFastBack1;
    private ImageIcon slimeTankRight1, slimeTankLeft1, slimeTankFront1, slimeTankBack1;
    private ImageIcon slimeBossRight1, slimeBossLeft1, slimeBossFront1, slimeBossBack1;
    private ImageIcon slimeHurt1, slimeFastHurt1, slimeTankHurt1, slimeBossHurt1;
    
    public Slime(int w, int h, int type)
    {
        vX = 0;
        vY = 0; 
        time = 0; 
        moveTime = 0;
        randomDir = 0; //0 is up, 1 is right, 2 is down, 3 is left
        hitTime = 0;
        hurtTime = 0;
        hurtState = false; //starts false, becomes true when contact with the user or hit
        faceDirection = "down"; //inital direction
        slimeRight1 = new ImageIcon(Slime.class.getResource("slimeRight1.png"));
        slimeLeft1 = new ImageIcon(Slime.class.getResource("slimeLeft1.png"));
        slimeBack1 = new ImageIcon(Slime.class.getResource("slimeBack1.png"));
        slimeFront1 = new ImageIcon(Slime.class.getResource("slimeFront1.png"));
        slimeHurt1 = new ImageIcon(Slime.class.getResource("slimeHurt1.png"));
        slimeFastRight1 = new ImageIcon(Slime.class.getResource("slimeFastRight1.png"));
        slimeFastLeft1 = new ImageIcon(Slime.class.getResource("slimeFastLeft1.png"));
        slimeFastFront1 = new ImageIcon(Slime.class.getResource("slimeFastFront1.png"));
        slimeFastBack1 = new ImageIcon(Slime.class.getResource("slimeFastBack1.png"));
        slimeFastHurt1 = new ImageIcon(Slime.class.getResource("slimeFastHurt1.png"));
        slimeTankRight1 = new ImageIcon(Slime.class.getResource("slimeTankRight1.png"));
        slimeTankLeft1 = new ImageIcon(Slime.class.getResource("slimeTankLeft1.png"));
        slimeTankFront1 = new ImageIcon(Slime.class.getResource("slimeTankFront1.png"));
        slimeTankBack1 = new ImageIcon(Slime.class.getResource("slimeTankBack1.png"));
        slimeTankHurt1 = new ImageIcon(Slime.class.getResource("slimeTankHurt1.png"));
        slimeBossRight1 = new ImageIcon(Slime.class.getResource("slimeBossRight1.png"));
        slimeBossLeft1 = new ImageIcon(Slime.class.getResource("slimeBossLeft1.png"));
        slimeBossFront1 = new ImageIcon(Slime.class.getResource("slimeBossFront1.png"));
        slimeBossBack1 = new ImageIcon(Slime.class.getResource("slimeBossBack1.png"));
        slimeBossHurt1 = new ImageIcon(Slime.class.getResource("slimeBossHurt1.png"));
        
        //regular slimes (blue)
        if(type == 0)
        {
            x = (int)(Math.random()*(w - 100) + 50);
            y = (int)(Math.random()*(h - 400) + 200);
            health = 2;
            diam = 60;
            slimeTypeFast = false;
            slimeTypeTank = false;
            slimeTypeBoss = false;
        }
        
        //fast slimes (red)
        else if(type == 1)
        {
            x = (int)(Math.random()*(w - 100) + 50);
            y = (int)(Math.random()*(h - 400) + 200);
            health = 2;
            diam = 50;
            slimeTypeFast = true;
            slimeTypeTank = false;
            slimeTypeBoss = false;
        }
        
        //tank slime (yellow)
        else if(type == 2)
        {
            x = (int)(Math.random()*(w - 160) + 80);
            y = (int)(Math.random()*(h - 400) + 200);
            diam = 70;
            health = 4;
            slimeTypeFast = false;
            slimeTypeTank = true;
            slimeTypeBoss = false;
        }
        
        //boss slime (black)
        else if(type ==3)
        {
            x = w/2;
            y = h/2;
            diam = 150;
            health = 7;
            slimeTypeFast = false;
            slimeTypeTank = false;
            slimeTypeBoss = true;
        }
    }
    
    public String toString()
    {
        return "Health: " + health;
    }
    
    public int getHealth()
    {
        return health;
    }
    
    public int getCenterX()
    {
        return x + diam/2;
    }
    
    public int getCenterY()
    {
        return y + diam/2;
    }
    
    public double distance(int x1, int y1, int x2, int y2)
    {
        return Math.sqrt(Math.pow((x2 - x1),2) + Math.pow((y2 - y1),2));
    }
    
    public void drawSelf(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        
        Image sR1 = slimeRight1.getImage();
        Image sL1 = slimeLeft1.getImage();
        Image sF1 = slimeFront1.getImage();
        Image sB1 = slimeBack1.getImage();
        Image sH1 = slimeHurt1.getImage();
        
        Image sFR1 = slimeFastRight1.getImage();
        Image sFL1 = slimeFastLeft1.getImage();
        Image sFF1 = slimeFastFront1.getImage();
        Image sFB1 = slimeFastBack1.getImage();
        Image sFH1 = slimeFastHurt1.getImage();
        
        Image sTR1 = slimeTankRight1.getImage();
        Image sTL1 = slimeTankLeft1.getImage();
        Image sTF1 = slimeTankFront1.getImage();
        Image sTB1 = slimeTankBack1.getImage();
        Image sTH1 = slimeTankHurt1.getImage();
        
        Image sBR1 = slimeBossRight1.getImage();
        Image sBL1 = slimeBossLeft1.getImage();
        Image sBF1 = slimeBossFront1.getImage();
        Image sBB1 = slimeBossBack1.getImage();
        Image sBH1 = slimeBossHurt1.getImage();
        
        if(health > 0) //Alive
        {
            if(!hurtState) //not hurt
            {
                if(!slimeTypeFast && !slimeTypeTank && !slimeTypeBoss) //regular slimes
                {
                   if(faceDirection.equals("up"))
                        g2d.drawImage(sB1, x-25, y-25, diam+50, diam+50, null);
                    else if(faceDirection.equals("right"))
                        g2d.drawImage(sR1, x-25, y-25, diam+50, diam+50, null);
                    else if(faceDirection.equals("left"))
                        g2d.drawImage(sL1, x-25, y-25, diam+50, diam+50, null);
                    else if(faceDirection.equals("down"))
                        g2d.drawImage(sF1, x-25, y-25, diam+50, diam+50, null); 
                }
                
                else if(slimeTypeFast) //fast slimes
                {
                    if(faceDirection.equals("up"))
                        g2d.drawImage(sFB1, x-5, y-5, diam+10, diam+10, null);
                    else if(faceDirection.equals("right"))
                        g2d.drawImage(sFR1, x-5, y-5, diam+10, diam+10, null);
                    else if(faceDirection.equals("left"))
                        g2d.drawImage(sFL1, x-5, y-5, diam+10, diam+10, null);
                    else if(faceDirection.equals("down"))
                        g2d.drawImage(sFF1, x-5, y-5, diam+10, diam+10, null);
                }
                else if(slimeTypeTank) // tank slimes
                {
                    if(faceDirection.equals("up"))
                        g2d.drawImage(sTB1, x-25, y-25, diam+50, diam+50, null);
                    else if(faceDirection.equals("right"))
                        g2d.drawImage(sTR1, x-25, y-25, diam+50, diam+50, null);
                    else if(faceDirection.equals("left"))
                        g2d.drawImage(sTL1, x-25, y-25, diam+50, diam+50, null);
                    else if(faceDirection.equals("down"))
                        g2d.drawImage(sTF1, x-25, y-25, diam+50, diam+50, null); 
                }
                else if(slimeTypeBoss) //boss slimes
                {
                    if(faceDirection.equals("up"))
                        g2d.drawImage(sBB1, x-20, y-20, diam+40, diam+40, null);
                    else if(faceDirection.equals("right"))
                        g2d.drawImage(sBR1, x-20, y-20, diam+40, diam+40, null);
                    else if(faceDirection.equals("left"))
                        g2d.drawImage(sBL1, x-20, y-20, diam+40, diam+40, null);
                    else if(faceDirection.equals("down"))
                        g2d.drawImage(sBF1, x-20, y-20, diam+40, diam+40, null); 
                }
            }
            else //slimes are hurt
            {
                if(!slimeTypeFast && !slimeTypeTank && !slimeTypeBoss)
                    g2d.drawImage(sH1, x - 10, y - 10, diam + 20, diam + 20, null);
                else if(slimeTypeFast)
                    g2d.drawImage(sFH1,x-10, y-10, diam+20, diam+20, null); 
                else if(slimeTypeTank)
                    g2d.drawImage(sTH1,x-20, y-20, diam+40, diam+40, null); 
                else if(slimeTypeBoss)
                    g2d.drawImage(sBH1, x, y, diam, diam, null); 
            }
            
        }
        else //slimes are dead, are teleported outside of the map
        {
            x = -100;
            y = -100;
            vX = 0;
            vY = 0;
        }
    }
    
    public void handleCollisions(User u, int w, int h)
    {
        int centerX, centerY;
        int nextX, nextY;
        nextX = x + vX;
        nextY = y + vY;
        centerX = (2*nextX + diam)/2;
        centerY = (2*nextY + diam)/2;
        int random;
        random = 0;
        
        //detecting collisions with walls
        if(nextY + diam > h) 
        {
            random = (int)(Math.random() * 2);
            vY *= -1;
            if(random == 0)
                vX *= -1;
        }
        if(nextX + diam > w)
        {
            vX *= -1;
            if(random == 0)
                vY *= -1;
        }
        if(nextY + diam < diam)
        {
            vY *= -1;
            if(random == 0)
                vX *= -1;
        }
        if(nextX + diam < diam)
        {
            vX *= -1;
            if(random == 0)
                vY *= -1;
        }
        
        x += vX; //updating velocities
        y += vY;
        
        if(detectCollision(u))
        {
            u.takeDamage();

            if(u.getCenterX() < centerX && u.getCenterY() < centerY) //top left
            {
                u.setX(u.getX() - 10);
                u.setY(u.getY() - 10);
                vX *= -1;
                vY *= -1;
                takeDamage();
            }
            else if(u.getCenterX() < centerX && u.getCenterY() > centerY) //bottom left
            {
                u.setX(u.getX() - 10);
                u.setY(u.getY() + 10);
                vX *= -1;
                vY *= -1;
                takeDamage();
            }
            else if(u.getCenterX() > centerX && u.getCenterY() > centerY) //bottom right
            {
                u.setX(u.getX() + 10);
                u.setY(u.getY() + 10);
                vX *= -1;
                vY *= -1;
                takeDamage();
            }
            else if(u.getCenterX() > centerX && u.getCenterY() < centerY) //top right
            {
                u.setX(u.getX() + 10);
                u.setY(u.getY() - 10);
                vX *= -1;
                vY *= -1;
                takeDamage();
            }
            else if(u.getCenterX() > centerX)
            {
                u.setX(u.getX() + 10);
                vX *= -1;
                vY *= -1;
                takeDamage();
            }
        }
        
        if(detectAttack(u))
        {
            takeDamage();
        }
    }
    
    public void takeDamage()
    {
        if(hitTime > hurtTime) //Not hurt
        {
           health--;
           hurtTime = time + 50; //Invincible for 50 ticks
        }
    }

    public boolean detectCollision(User u)
    {
        boolean output;
        int radius, centerX, centerY;
        int nextX, nextY;
        
        output = false;
        radius = diam/2;
        nextX = x + vX;
        nextY = y + vY;
        centerX = (2*nextX + diam)/2;
        centerY = (2*nextY + diam)/2;
        
        for(int i = u.getX(); i <= u.getX() + u.getWidth(); i++)  //detecting collision with the user
        {
            for(int j = u.getY(); j <= u.getY() + u.getHeight(); j++)
            {
                if(distance(i, j, centerX, centerY) < radius - 5)
                    output = true;
            }
        }
        
        return output;
    }
    
    public boolean detectAttack(User u)
    {
        boolean output;
        int radius, centerX, centerY;
        int nextX, nextY;
        
        output = false;
        radius = diam/2;
        nextX = x + vX;
        nextY = y + vY;
        centerX = (2*nextX + diam)/2;
        centerY = (2*nextY + diam)/2;
        
        for(int i = u.getHBX(); i <= u.getHBX() + u.getHBW(); i++) //detecting collition with the hitBox (attack)
        {
            for(int j = u.getHBY(); j <= u.getHBY() + u.getHBH(); j++)
            {
                if(distance(i, j, centerX, centerY) < radius - 5)
                    output = true;
            }
        }
        
        return output;
    }
   
    
    public void act(User u)
    {
        if(hitTime <= hurtTime) //Hurt
            hurtState = true;
        else
            hurtState = false;

        
        if(time >= moveTime) //making slimes move
        {
            randomDir = (int)(Math.random()*4); //random direction
            if(randomDir == 0) //up
            {
                if(slimeTypeFast) //fast slimes
                {
                    vX = 0;
                    vY = -2;
                }
                else if(slimeTypeBoss) //boss slimes
                {
                    vX = 0;
                    vY = -3;
                }
                else //regular and tank slimes
                {
                    vX = 0;
                    vY = -1;
                }
                faceDirection = "up"; //for sprites

            }
            else if(randomDir == 1) //right
            {
                if(slimeTypeFast)
                {
                    vX = 2;
                    vY = 0;
                }
                else if(slimeTypeBoss)
                {
                    vX = 3;
                    vY = 0;
                }
                else
                {
                    vX = 1;
                    vY = 0;

                }
                faceDirection = "right";
            }
            else if(randomDir == 2) //down
            {
                if(slimeTypeFast)
                {
                    vX = 0;
                    vY = 2;
                }
                else if(slimeTypeBoss)
                {
                    vX = 0;
                    vY = 3;
                }
                else
                {
                    vX = 0;
                    vY = 1;

                }
                faceDirection = "down"; 
            }
            else if(randomDir == 3) //left
            {
                if(slimeTypeFast)
                {
                    vX = -2;
                    vY = 0;
                }
                else if(slimeTypeBoss)
                {
                    vX = -3;
                    vY = 0;
                }
                else
                {
                    vX = -1;
                    vY = 0;

                }
                faceDirection = "left";

            }

            moveTime = time + 40; //keeps increasing moveTime when time reaches moveTime. essentially, every 40 ticks, slime changes direction
        }
        
        if(hurtState == false)
        {
            x += vX;
            y += vY;
        }
        
        time++;
        hitTime++;
    }

}
