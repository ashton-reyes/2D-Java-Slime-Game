import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Map extends JComponent implements KeyListener, MouseListener, MouseMotionListener {

    //instance variables
    private int WIDTH;
    private int HEIGHT;
    private User user;
    private ArrayList<Slime> allSlime;
    private ArrayList<Arrow> allArrows;
    private ImageIcon background1;
    private ImageIcon health10, health9, health8, health7, health6, health5, health4, health3, health2, health1, health0;
    private ImageIcon gameWin1, gameOver1;
    
    private int level, phase;
    
    //Default Constructor
    public Map() {
        //initializing instance variables
        WIDTH = 1000;
        HEIGHT = 500;
        user = new User(WIDTH);

        //instantiating the Arrows
        allArrows = new ArrayList<Arrow>();
        allSlime = new ArrayList<Slime>();
        background1 = new ImageIcon(Map.class.getResource("background1.png"));
        
        health10 = new ImageIcon(Map.class.getResource("hearts10.png"));
        health9 = new ImageIcon(Map.class.getResource("hearts9.png"));
        health8 = new ImageIcon(Map.class.getResource("hearts8.png"));
        health7 = new ImageIcon(Map.class.getResource("hearts7.png"));
        health6 = new ImageIcon(Map.class.getResource("hearts6.png"));
        health5 = new ImageIcon(Map.class.getResource("hearts5.png"));
        health4 = new ImageIcon(Map.class.getResource("hearts4.png"));
        health3 = new ImageIcon(Map.class.getResource("hearts3.png"));
        health2 = new ImageIcon(Map.class.getResource("hearts2.png"));
        health1 = new ImageIcon(Map.class.getResource("hearts1.png"));
        health0 = new ImageIcon(Map.class.getResource("hearts0.png"));
        
        gameWin1 = new ImageIcon(Map.class.getResource("gameWin1.png"));
        gameOver1 = new ImageIcon(Map.class.getResource("gameOver1.png"));
        
        level = 1; //starts at level 1, ends at 9
        phase = 2;//2 means fight, 1 means spawn
        
        if(level == 1)
        {
            for (int i = 0; i < 1; i++)//0, 1, 2
            {
                //left arrows
                Arrow left = new Arrow(HEIGHT);
                allArrows.add(left);
            }
            for (int i = 0; i < 1; i++)//0, 1, 2
            {
                //right arrows
                Arrow right = new Arrow(WIDTH, HEIGHT);
                allArrows.add(right);
            }
            for (int i = 0; i < 5; i++)
            {
                //regular slimes
                Slime slime = new Slime(WIDTH, HEIGHT, 0);
                allSlime.add(slime);
            }
        }
        
        //Setting up the GUI
        JFrame gui = new JFrame(); //This makes the gui box
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Makes sure program can close
        gui.setTitle("Learning Graphics"); //This is the title of the game, you can change it
        gui.setPreferredSize(new Dimension(WIDTH + 5, HEIGHT + 30)); //Setting the size for gui
        gui.setResizable(false); //Makes it so the gui cant be resized
        gui.getContentPane().add(this); //Adding this class to the gui

        /*If after you finish everything, you can declare your buttons or other things
          *at this spot. AFTER gui.getContentPane().add(this) and BEFORE gui.pack();
         */
        gui.pack(); //Packs everything together
        gui.setLocationRelativeTo(null); //Makes so the gui opens in the center of screen
        gui.setVisible(true); //Makes the gui visible
        gui.addKeyListener(this);//stating that this object will listen to the keyboard
        gui.addMouseListener(this); //stating that this object will listen to the Mouse
        gui.addMouseMotionListener(this); //stating that this object will acknowledge when the Mouse moves
    }
    
    public void spawn(int lev)
    {
        if(lev == 2)
        {
            user.setX(WIDTH/2);
            user.setY(50);
            for (int i = 0; i < 1; i++)//0, 1, 2
            {
                //left arrows
                Arrow left = new Arrow(HEIGHT);
                allArrows.add(left);
            }

            for (int i = 0; i < 3; i++)
            {
                //regular slimes
                Slime slime = new Slime(WIDTH, HEIGHT, 0);
                allSlime.add(slime);
            }
            for (int i = 0; i < 2; i++)
            {
                //fast slimes
                Slime slime = new Slime(WIDTH, HEIGHT, 1);
                allSlime.add(slime);
            }
        }
        if(lev == 3)
        {
            user.setX(WIDTH/2);
            user.setY(50);
            for (int i = 0; i < 1; i++)//0, 1, 2
            {
                //right arrows
                Arrow right = new Arrow(WIDTH, HEIGHT);
                allArrows.add(right);
            }
            for (int i = 0; i < 3; i++)
            {
                //regular slimes
                Slime slime = new Slime(WIDTH, HEIGHT, 0);
                allSlime.add(slime);
            }
            for (int i = 0; i < 2; i++)
            {
                //fast slimes
                Slime slime = new Slime(WIDTH, HEIGHT, 1);
                allSlime.add(slime);
            }
            for (int i = 0; i < 1; i++)
            {
                //tank slimes
                Slime slime = new Slime(WIDTH, HEIGHT, 2);
                allSlime.add(slime);
            }
        }
        if(lev == 4)
        {
            user.setX(WIDTH/2);
            user.setY(50);
            for (int i = 0; i < 2; i++)
            {
                //regular slimes
                Slime slime = new Slime(WIDTH, HEIGHT, 0);
                allSlime.add(slime);
            }
            for (int i = 0; i < 1; i++)
            {
                //tank slimes
                Slime slime = new Slime(WIDTH, HEIGHT, 2);
                allSlime.add(slime);
            }
        }
        if(lev == 5)
        {
            user.setX(WIDTH/2);
            user.setY(50);
            for (int i = 0; i < 1; i++)//0, 1, 2
            {
                //left arrows
                Arrow left = new Arrow(HEIGHT);
                allArrows.add(left);
            }
            for (int i = 0; i < 1; i++)//0, 1, 2
            {
                //right arrows
                Arrow right = new Arrow(WIDTH, HEIGHT);
                allArrows.add(right);
            }
            for (int i = 0; i < 5; i++)
            {
                //fast slimes
                Slime slime = new Slime(WIDTH, HEIGHT, 1);
                allSlime.add(slime);
            }
            for (int i = 0; i < 2; i++)
            {
                //tank simes
                Slime slime = new Slime(WIDTH, HEIGHT, 2);
                allSlime.add(slime);
            }
        }
        if(lev == 6)
        {
            user.setX(30);
            user.setY(HEIGHT/2);
            for (int i = 0; i < 1; i++)
            {
                //boss slimes
                Slime slime = new Slime(WIDTH, HEIGHT, 3);
                allSlime.add(slime);
            }
        }
        if(lev == 7)
        {
            user.setX(WIDTH/2);
            user.setY(50);
            for (int i = 0; i < 4; i++)
            {
                //regular slimes
                Slime slime = new Slime(WIDTH, HEIGHT, 0);
                allSlime.add(slime);
            }
            for (int i = 0; i < 4; i++)
            {
                //fast slimes
                Slime slime = new Slime(WIDTH, HEIGHT, 1);
                allSlime.add(slime);
            }
            for (int i = 0; i < 4; i++)
            {
                //tank slimes
                Slime slime = new Slime(WIDTH, HEIGHT, 2);
                allSlime.add(slime);
            }
        }
        if(lev == 8)
        {
            user.setX(30);
            user.setY(HEIGHT/2);
            for (int i = 0; i < 1; i++)//0, 1, 2
            {
                //left arrows
                Arrow left = new Arrow(HEIGHT);
                allArrows.add(left);
            }
            for (int i = 0; i < 1; i++)//0, 1, 2
            {
                //right arrows
                Arrow right = new Arrow(WIDTH, HEIGHT);
                allArrows.add(right);
            }
            for (int i = 0; i < 2; i++)
            {
                //boss slimes
                Slime slime = new Slime(WIDTH, HEIGHT, 3);
                allSlime.add(slime);
            }
        }
        
    }

    //This method will acknowledge user input
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        //WASD for movement
        if (key == 87) {
            user.setVY(-2);
            user.setFaceDirection("up");
        }
        if (key == 83) {
            user.setVY(2);
            user.setFaceDirection("down");
        }
        if (key == 68) {
            user.setVX(2);
            user.setFaceDirection("right");
        }
        if (key == 65) {
            user.setVX(-2);
            user.setFaceDirection("left");
        }
        
        //arrow keys for attack
        if(key == 38)
        {
            user.setDirection("up");
            user.showHitBox();
        }
        if(key == 40)
        {
            user.setDirection("down");
            user.showHitBox();
        }
        if(key == 39)
        {
            user.setDirection("right");
            user.showHitBox();
        }
        if(key == 37)
        {
            user.setDirection("left");
            user.showHitBox();
        }
    }

    //All your UI drawing goes in here
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        
        Image b1 = background1.getImage();
        Image h10 = health10.getImage();
        Image h9 = health9.getImage();
        Image h8 = health8.getImage();
        Image h7 = health7.getImage();
        Image h6 = health6.getImage();
        Image h5 = health5.getImage();
        Image h4 = health4.getImage();
        Image h3 = health3.getImage();
        Image h2 = health2.getImage();
        Image h1 = health1.getImage();
        Image h0 = health0.getImage();
        Image gO1 = gameOver1.getImage();
        Image gW1 = gameWin1.getImage();
        
        g2d.drawImage(b1, 0, 0, WIDTH, HEIGHT, null); //grass
        
        //drawing arrows
        for (int i = 0; i < allArrows.size(); i++) {
            Arrow currentArrow = allArrows.get(i);
            currentArrow.drawSelf(g, WIDTH);
        }

        //drawing slimes
        for (int i = 0; i < allSlime.size(); i++) {
            Slime currentSlime = allSlime.get(i);
            currentSlime.drawSelf(g);
        }

        //drawing user
        user.drawSelf(g);
        
        //drawing health bar
        if(user.getHealth() == 10)
            g2d.drawImage(h10, 25, 5, 250, 60, null);
        else if(user.getHealth() == 9)
            g2d.drawImage(h9, 25, 5, 250, 60, null);
        else if(user.getHealth() == 8)
            g2d.drawImage(h8, 25, 5, 250, 60, null);
        else if(user.getHealth() == 7)
            g2d.drawImage(h7, 25, 5, 250, 60, null);
        else if(user.getHealth() == 6)
            g2d.drawImage(h6, 25, 5, 250, 60, null);
        else if(user.getHealth() == 5)
            g2d.drawImage(h5, 25, 5, 250, 60, null);
        else if(user.getHealth() == 4)
            g2d.drawImage(h4, 25, 5, 250, 60, null);
        else if(user.getHealth() == 3)
            g2d.drawImage(h3, 25, 5, 250, 60, null);
        else if(user.getHealth() == 2)
            g2d.drawImage(h2, 25, 5, 250, 60, null);
        else if(user.getHealth() == 1)
            g2d.drawImage(h1, 25, 5, 250, 60, null);
        else if(user.getHealth() <= 0)
            g2d.drawImage(h0, 25, 5, 250, 60, null);
        
        //drawing level
        Font f = new Font("Arial", Font.BOLD, 30);
        g.setFont(f);
        g.setColor(Color.BLACK);
        g.drawString("LEVEL:" + level, 30, 480);
        
        
        if(user.getHealth() <= 0) //drawing game over screen
            g2d.drawImage(gO1, 0, 0, WIDTH, HEIGHT, null);
        if(level >= 9) //drawing game win screen
            g2d.drawImage(gW1, 0, 0, WIDTH, HEIGHT, null);
    }

    public void loop() {
        
        if(user.getHealth() > 0 || level == 9) //game runs as long as the user's healh is above 0 or level is less than 9
        {
            //making user act
            user.act(WIDTH, HEIGHT);
        
            //keeping track of the amount of slimes killed
            int slimeKillCount = 0;

            //making all of the arrows act
            for (int i = 0; i < allArrows.size(); i++) {
                Arrow currentArrow = allArrows.get(i);
                currentArrow.act(WIDTH, HEIGHT);
            }

            //making all of the slimes act
            for (int i = 0; i < allSlime.size(); i++) {
                Slime currentSlime = allSlime.get(i);
                currentSlime.act(user);
            }

            //handeling user collisions
            user.handleCollisions(WIDTH, HEIGHT);

            //handeling arrow collisions
            for (int i = 0; i < allArrows.size(); i++) {
                Arrow currentArrow = allArrows.get(i);
                currentArrow.handleCollisions(user, WIDTH, HEIGHT);
            }

            //handeling slime collisions
            for (int i = 0; i < allSlime.size(); i++) {
                Slime currentSlime = allSlime.get(i);
                currentSlime.handleCollisions(user, WIDTH, HEIGHT);
            }

            //constantly checking the health of all slimes in the array to see if they hit 0, and if so, increase slimeKillCount
            for (int i = 0; i < allSlime.size(); i++) 
            {
                Slime currentSlime = allSlime.get(i);
                if(currentSlime.getHealth() <= 0)
                    slimeKillCount++;
            }

            if(level == 1)
            {
                if(slimeKillCount == 5) //when 5 slimes killed, change to level 2
                {
                    phase = 1;
                    level = 2;
                }
            }
            else if(level == 2)
            {
                if(phase == 1)
                {
                    spawn(level);
                    phase = 2;//start fighting
                }

                if(slimeKillCount == 10)
                {
                    phase = 1;
                    level = 3;//to start the next level
                }
            }
            else if(level == 3)
            {
                if(phase == 1)
                {
                    spawn(level);
                    phase = 2;
                }

                if(slimeKillCount == 16)
                {
                    phase = 1;
                    level = 4;
                }
            }
            else if(level == 4)
            {
                if(phase == 1)
                {
                    spawn(level);
                    phase = 2;
                }

                if(slimeKillCount == 19)
                {
                    phase = 1;
                    level = 5;
                }
            }
            else if(level == 5)
            {
                if(phase == 1)
                {
                    spawn(level);
                    phase = 2;
                }

                if(slimeKillCount == 26)
                {
                    phase = 1;
                    level = 6;
                }
            }
            else if(level == 6)
            {
                if(phase == 1)
                {
                    spawn(level);
                    phase = 2;
                }

                if(slimeKillCount == 27)
                {
                    phase = 1;
                    level = 7;

                }
            }
            else if(level == 7)
            {
                if(phase == 1)
                {
                    spawn(level);
                    phase = 2;
                }

                if(slimeKillCount == 39)
                {
                    phase = 1;
                    level = 8;
                }
            }
            else if(level == 8)
            {
                if(phase == 1)
                {
                    spawn(level);
                    phase = 2;
                }

                if(slimeKillCount == 41)
                {
                    phase = 1;
                    level = 9; //game win
                }
            }
        }

        //Do not write below this
        repaint();
    }

    //These methods are required by the compiler.  
    //You might write code in these methods depending on your goal.
    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        //getting the key pressed
        int key = e.getKeyCode();
        
        if (key == 87) {
            user.setVY(0);
        }
        if (key == 83) {
            user.setVY(0);
        }
        if (key == 68) {
            user.setVX(0);
        }
        if (key == 65) {
            user.setVX(0);
        }
    }

    public void mousePressed(MouseEvent e) {
        int mouseX, mouseY;

        mouseX = e.getX();
        mouseY = e.getY();
        user.setDirection(mouseX-4, mouseY-25);
        user.showHitBox();
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        

    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void start(final int ticks) {
        Thread gameThread = new Thread() {
            public void run() {
                while (true) {
                    loop();
                    try {
                        Thread.sleep(1000 / ticks);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        gameThread.start();
    }

    public static void main(String[] args) {
        Map m = new Map();
        m.start(60);
    }
}
