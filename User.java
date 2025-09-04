import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class User
{
    //hB = hitBox
    private int health;
    private boolean isAlive;
    private int x, y, width, height;
    private int vX, vY;
    private int time, hurtTime;
    private boolean hurtState;
    private String direction;
    private int hBX, hBY, hBW, hBH;
    private boolean attackState;
    private int attackTime;
    private String faceDirection;
    private ImageIcon userBack1, userFront1, userRight1, userLeft1, userHurt1;
    private ImageIcon hitBox1;
    
    public User(int w)
    {
        hurtTime = 0; //for invincibility
        hurtState = false; //not hurt
        time = 0;
        health = 10;
        width = 50;
        height = 50;
        x = w/2 - (width/2);
        y = 50;
        vX = 0 ;
        vY = 0;
        direction = "right"; //inital mouse direction (mouse is optional)
        hBW = 100;
        hBH = 100;
        hBX = -100;
        hBY = -100;
        attackState = false; //not attacking yet
        attackTime = 0; //for delay of attack
        faceDirection = "down";//inital direction
        userBack1 = new ImageIcon(User.class.getResource("userBack1.png"));
        userFront1 = new ImageIcon(User.class.getResource("userFront1.png"));
        userRight1 = new ImageIcon(User.class.getResource("userRight1.png"));
        userLeft1 = new ImageIcon(User.class.getResource("userLeft1.png"));
        userHurt1 = new ImageIcon(User.class.getResource("userHurt1.png"));
        hitBox1 = new ImageIcon(User.class.getResource("hitBox1.png"));
    }
    
    public String toString()
    {
        return "Are you alive: " + isAlive 
                + "\nHealth: " + health 
                + "\nCoordinates: " + "(" + x + ", " + y + ")";
    }
    
    public int getCenterX()
    {
        return (x + (x + width))/2;
    }
    
    public int getCenterY()
    {
        return (y + (y + height))/2;
    }
    
    public int getHealth()
    {
        return health;
    }
    
    public boolean getIsAlive()
    {
        return isAlive;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public int getVX()
    {
        return vX;
    }
    
    public int getVY()
    {
        return vY;
    }
    
    public String getDirection()
    {
        return direction;
    }
    
    public String getFaceDirection()
    {
        return faceDirection;
    }
    
    public int getHBX(){
        return hBX;
    }
    
    public int getHBY(){
        return hBY;
    }
    
    public int getHBW(){
        return hBW;
    }
    
    public int getHBH(){
        return hBH;
    }
    
    public void setX(int x)
    {
        this.x = x;
    }
    
    public void setY(int y)
    {
        this.y = y;
    }
    
    public void setVX(int xv)
    {
        if(attackState == false)
            vX = xv;
    }
    
    public void setVY(int yv)
    {
        if(attackState == false)
            vY = yv;
    }
    
    public void setDirection(String d)
    {
        direction = d;
    }
    
    public void setHurtState(boolean b)
    {
        hurtState = b;
    }
    
    public void setFaceDirection(String fd)
    {
        faceDirection = fd;
    }
    
    public double distance(int x1, int y1, int x2, int y2)
    {
        return Math.sqrt(Math.pow((x2 - x1),2) + Math.pow ((y2 - y1),2));
    }
    
    public void drawSelf(Graphics g)    
    {
        Graphics2D g2d = (Graphics2D)g;
        Image uB1 = userBack1.getImage(); 
        Image uF1 = userFront1.getImage(); 
        Image uR1 = userRight1.getImage();
        Image uL1 = userLeft1.getImage();
        Image uH1 = userHurt1.getImage();
        
        Image hB1 = hitBox1.getImage();
        
        if(!hurtState) //not hurt
        {
            if(faceDirection.equals("up"))
                g2d.drawImage(uB1, x -25, y-30, width + 50, height+ 50, null); //addition and subtraction are used to dialate the image and recenter it
            else if(faceDirection.equals("down"))
                g2d.drawImage(uF1, x-25, y-30, width+ 50, height+ 50, null);
            else if(faceDirection.equals("right"))
                g2d.drawImage(uR1, x-25, y-30, width+ 50, height+ 50, null);
            else if(faceDirection.equals("left"))
                g2d.drawImage(uL1, x-25, y-30, width+ 50, height+ 50, null);
        }
        else
            g2d.drawImage(uH1, x-25, y-30, width+ 50, height+ 50, null); //hurtState
        
        if(attackState) //attack
           g2d.drawImage(hB1, hBX, hBY, hBW, hBH, null);
    }
    public void act(int w, int h) 
    {
        x += vX;
        y += vY;
        time++;
        
        if(time <= hurtTime) //i am hurt
            hurtState = true;
        else
            hurtState = false;
        
        
        if(time <= attackTime) //attack
            attackState = true;
        else
        {
            hBX = -100;
            hBY = -100;
            attackState = false;
        }
    }
    
    public void handleCollisions(int w, int h) //handeling collision with the walls
    {
        int nextX, nextY;
        
        nextX = x += vX;
        nextY = y += vY;
        
        if(nextX + width >= w + 1)
            x -= 4;
        if(nextX <= -1)
            x += 4;
        if(nextY + height >= h + 1)
            y -= 4;
        if(nextY <= -1)
            y += 4;
    }
    
    
    public void takeDamage()
    {
        if(time > hurtTime) //invincibility time
        {
           health--;
           hurtTime = time + 100; //can't take damage again for another 100 ticks
        }
    }
    
    public void setDirection(int mx, int my) //setting direction for mouse relative the the loaction of the user (optional)
    {
        if(mx >= x && mx <= x + width && my < y)
            direction = "up";
        else if(mx >= x && mx <= x + width && my > y + height)
            direction = "down";
        else if(my >= y && my <= y + height && mx < x)
            direction = "left";
        else if(my >= y && my <= y + height && mx >= x + width)
            direction = "right";
    }
    
    public void showHitBox() //when user uses arrow keys, depending on direction, it will spawn a box 
    {
        attackTime = time + 30;
        
        if(direction.equals("up"))
        {
            hBY = y - hBH;
            hBX = x - (hBW - width)/2;
            vX = 0; //stopping the user whe attacking
            vY = 0;
        }
        else if(direction.equals("down"))
        {
            hBY = y + height;
            hBX = x - (hBW - width)/2;
            vX = 0;
            vY = 0;
        }
        else if(direction.equals("left"))
        {
            hBY = y - (hBH - height)/2;
            hBX = x - hBW;
            vX = 0;
            vY = 0;
        }
        else if(direction.equals("right"))
        {
            hBY = y - (hBH - height)/2;
            hBX = x + width;
            vX = 0;
            vY = 0;
        }
    }
}