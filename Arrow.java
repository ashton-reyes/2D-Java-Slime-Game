import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Arrow
{
    //p = projectile
    //w = warning
    private int pX, pY, pW, pH;
    private int pVx;
    private int wX, wY, wW, wH;
    private boolean pRight;
    private ImageIcon arrowRight1, arrowLeft1;
    private ImageIcon warningRight1, warningLeft1;
    
    public Arrow(int h) //arrows on the left side
    {
        pW = 60;
        pH = 8;
        pX = -2000; //spawns far back so it takes time to travel into the map
        pY = (int)(Math.random()*(h-20) + 10); //random height
        pVx = (int)(Math.random()*15 + 10); //random velocity
        pRight = true;
        wX = 0;
        wY = pY - 5;
        wW = 35;
        wH = 15;
        arrowRight1 = new ImageIcon(Arrow.class.getResource("arrowRight1.png"));
        arrowLeft1 = new ImageIcon(Arrow.class.getResource("arrowLeft1.png"));
        warningRight1 = new ImageIcon(Arrow.class.getResource("warningRight1.png"));
        warningLeft1 = new ImageIcon(Arrow.class.getResource("warningLeft1.png"));
    }

    public Arrow(int w, int h)
    {
        pW = 60;
        pH = 8;
        pX = w + 2000; //spawns far back so it takes time to travel into the map
        pY = (int)(Math.random()*(h-20) + 10); //random height
        pVx = -(int)(Math.random()*15 + 10); //random velocity
        pRight = false;
        wW = 35;
        wH = 15;
        wX = w - wW;
        wY = pY - 5;
        arrowRight1 = new ImageIcon(Arrow.class.getResource("arrowRight1.png"));
        arrowLeft1 = new ImageIcon(Arrow.class.getResource("arrowLeft1.png"));
        warningRight1 = new ImageIcon(Arrow.class.getResource("warningRight1.png"));
        warningLeft1 = new ImageIcon(Arrow.class.getResource("warningLeft1.png"));
    }
    
    public String toString()
    {
        return "Testing";
    }
    
    public double distance(int x1, int y1, int x2, int y2)
    {
        return Math.sqrt(Math.pow((x2 - x1),2) + Math.pow ((y2 - y1),2));
    }
    
    public void drawSelf(Graphics g, int w)
    {
        Graphics2D g2d = (Graphics2D)g;
        Image aR1 = arrowRight1.getImage();
        Image aL1 = arrowLeft1.getImage();
        Image wR1 = warningRight1.getImage();
        Image wL1 = warningLeft1.getImage();
        
        if(pRight)
        {
            g2d.drawImage(aR1, pX-31, pY-10, pW+50, pH+20, null); //draws the arrow going right
            
            if(pX < -200) //makes the warning disappear when false
                g2d.drawImage(wR1, wX - 1, wY, wW, wH, null); //draws the warning on the left side
        }
        else
        {
            g2d.drawImage(aL1, pX-31, pY-10, pW+50, pH+20, null); //draws the arrow going left
            
            if(pX > w + 200) //makes the warning disappear when false
                g2d.drawImage(wL1, wX , wY, wW, wH, null); //draws the warning on the right side
        }
    }
    
    public void act(int w, int h)
    {
        if(pRight)
        {
            pX += pVx;
        
            if(pX > w + 500) //respawns the arrow when it passes a point after the map
            {
                pVx = (int)(Math.random()*15 + 10); //random velocity
                pX = -1000; //put back to take time before entering the map
                pY = (int)(Math.random()*(h-20) + 10); //random height
                wY = pY - 5;
                
            }
        }
        else
        {
            pX += pVx;
            
            if(pX < -500)//respawns the arrow when it passes a point after the map
            {
                pVx = -(int)(Math.random()*15 + 10); //random velocity
                pX = w + 1000 + pW; //put back to take time before entering the map
                pY = (int)(Math.random()*(h-20) + 10); //random height
                wY = pY - 5;
            }
        }
    }
    
    public void handleCollisions(User u, int w, int h)
    {
        if(detectCollision(u))
        {
            u.takeDamage();
            pY -=1000; //just teleports the arrow above the map so it continues the path as if it didn't hit the user and teleports back regularly
        }
            
    }
    
    public boolean detectCollision(User u)
    {
        boolean output;
        
        output = false;
        
        for(int i = u.getX(); i <= u.getX() + u.getWidth(); i++) //user
        {
            for(int j = u.getY(); j <= u.getY() + u.getHeight(); j++) //user
            {
                for(int k = pX; k <= pX + pW; k++) //projectile
                {
                      for(int l = pY; l <= pY + pH; l++) //projectile
                    {
                        if((i == k && j == l))
                            output = true;
                    }
                }
            }
        }
        
        return output;
    }
}
