package Maze;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 * The animated main menu seen at the startup of each game session.
 */
public class StartMenu extends JFrame implements Runnable, MouseListener, MouseMotionListener
{

    /**
     * Set to true when the mouse is inside the JFrame.
     */
    protected static boolean mouseOnScreen;
    /**
     * Set to true while the user is inside the menu.
     */
    protected static boolean inMenu;
    /**
     * Set to true when the mouse is clicked.
     */
    protected static boolean mouseClicked;
    /**
     * Width of the JFrame
     */
    protected static int WIDTH;
    /**
     * Height of the JFrame
     */
    protected static int HEIGHT;
    /**
     * X position of the mouse
     */
    protected static int m_x;
    /**
     * Y position of the mouse
     */
    protected static int m_y;
    /**
     * X position of Rectangle r1. Updated for an animated effect.
     */
    protected static int x1;
    /**
     * X position of Rectangle r2. Updated for an animated effect.
     */
    protected static int x2;
    /**
     * X position of Rectangle r3. Updated for an animated effect.
     */
    protected static int x3;
    /**
     * X position of Rectangle r4. Updated for an animated effect.
     */
    protected static int x4;
    /**
     * X position of Rectangle r5. Updated for an animated effect.
     */
    protected static int x5;
    /**
     * Y position of Rectangle r1. Updated for an animated effect.
     */
    protected static int y1;
    /**
     * Y position of Rectangle r2. Updated for an animated effect.
     */
    protected static int y2;
    /**
     * Y position of Rectangle r3. Updated for an animated effect.
     */
    protected static int y3;
    /**
     * Y position of Rectangle r4. Updated for an animated effect.
     */
    protected static int y4;
    /**
     * Y position of Rectangle r5. Updated for an animated effect.
     */
    protected static int y5;
    /**
     * X direction of Rectangle r2. Updated for an animated effect.
     */
    protected static int xdir1;
    /**
     * X direction of Rectangle r3. Updated for an animated effect.
     */
    protected static int xdir2;
    /**
     * X direction of Rectangle r4. Updated for an animated effect.
     */
    protected static int xdir3;
    /**
     * Y direction of Rectangle r1. Updated for an animated effect.
     */
    protected static int ydir1;
    /**
     * Y direction of Rectangle r5. Updated for an animated effect.
     */
    protected static int ydir2;
    private Image dbImage;
    private Graphics dbGraphics;
    private static int dif = 0;
    private static Font titleFont, buttonFont;
    /**
     * One of the rectangles to be animated in the menu.
     */
    protected static Rectangle r1, r2, r3, r4, r5;
    /**
     * String for one one of the authors (full name here). This is displayed on
     * one of the rectangles when the r4 Rectangle object is hovered over with
     * the mouse.
     */
    private String rishabh, steve, saj;

    /**
     * Initializes the Rectangle object positions, and sets up the JFrame
     * properties such as title, dimensions, visibility, resizability, and
     * location.
     *
     * @throws FontFormatException
     * @throws IOException
     */
    public StartMenu() throws FontFormatException, IOException
    {
        WIDTH = HEIGHT = 600;
        titleFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/CustomFont.ttf")).deriveFont(Font.BOLD, 52f);
        buttonFont = new Font("Verdana", Font.PLAIN, 13);
        steve = "Steve Singh";
        rishabh = "Rishabh Kumar";
        saj = "Sajeevan Yogeswaran";

// ---- Rectangle Position and Directions ----
        x1 = 5;
        y1 = 25;
        x2 = 396;
        y2 = 554;
        x3 = 235;
        y3 = 26;
        x4 = 235;
        y4 = 240;
        x5 = 550;
        y5 = 56;
        xdir1 = -1;
        xdir2 = -3;
        xdir3 = +2;
        ydir1 = +1;
        ydir2 = +2;
        addMouseMotionListener(this);
        addMouseListener(this);

// ---- Frame Properties ----

        setTitle("Maze Game Menu");
        setSize(WIDTH, HEIGHT);
        setBackground(new Color(155, 153, 200));
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        inMenu = true;

    }

    /**
     * Paints the images in a double-buffered manner, in order to reduce any
     * "streak" or flickering effects.
     *
     * @param g 2D Graphics object
     * @see #paintComponent(java.awt.Graphics)
     *
     */
    @Override
    public void paint(Graphics g)
    {
        dbImage = createImage(getWidth(), getHeight());
        dbGraphics = dbImage.getGraphics();
        try
        {
            paintComponent(dbGraphics);

        } catch (InterruptedException ex)
        {
            Logger.getLogger(StartMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        g.drawImage(dbImage, 0, 0, this);
        try
        {
            Thread.sleep(8);
        } catch (InterruptedException ex)
        {
            Logger.getLogger(StartMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Handles the creation of the five rectangles to be animated, paints them,
     * and employs collision detection for when they hit the edges of the JFrame
     * or one another. Creates and handles the animated difficulty buttons which
     * change colour upon being hovered over by the mouse. Sets the difficulty
     * and begins the game when a difficulty button is clicked. When the mouse
     * hovers over the black rectangle with a white border (r4), the names of
     * the authors are displayed in r1, r2, and r5. Finally, a call to repaint
     * is made, in order to create an animated effect for each object.
     *
     * @param g 2D Graphics object
     * @see StartGame
     * @see #paint(java.awt.Graphics)
     * @see #setDif(int)
     *
     */
    public void paintComponent(Graphics g) throws InterruptedException
    {

        y1 += ydir1;
        r1 = new Rectangle(x1, y1, 40, 200);
        g.setColor(new Color(0, 0, 0));
        g.fillRect(r1.x, r1.y, r1.width, r1.height);
        g.setColor(new Color(0, 50, 180));
        g.fillRect(r1.x + 2, r1.y + 2, r1.width - 4, r1.height - 4);
        if (r1.y > HEIGHT - r1.height || r1.y < 25)
        {
            ydir1 *= -1;
        }

        x2 += xdir1;
        r2 = new Rectangle(x2, y2, 200, 40);
        g.setColor(new Color(0, 0, 0));
        g.fillRect(r2.x, r2.y, r2.width, r2.height);
        g.setColor(new Color(80, 50, 100));
        g.fillRect(r2.x + 2, r2.y + 2, r2.width - 4, r2.height - 4);
        if (r2.x >= 396 || r2.x <= 3)
        {
            xdir1 *= -1;
        }
        if (r2.intersects(r1))
        {
            xdir1 *= -1;
            ydir1 *= -1;
        }


        x3 += xdir2;
        r3 = new Rectangle(x3, y3, 100, 30);
        g.setColor(new Color(0, 0, 0));
        g.fillRect(r3.x, r3.y, r3.width, r3.height);
        g.setColor(new Color(120, 30, 225));
        g.fillRect(r3.x + 2, r3.y + 2, r3.width - 4, r3.height - 4);
        if (r3.x < 4 || r3.x > 494)
        {
            xdir2 *= -1;
        }
        if (r3.intersects(r1))
        {
            xdir2 *= -1;
            ydir1 *= -1;
        }

        x4 += xdir3;
        r4 = new Rectangle(x4, y4, 150, 20);
        g.setColor(new Color(240, 240, 255));
        g.fillRect(r4.x, r4.y, r4.width, r4.height);
        g.setColor(new Color(40, 40, 40));
        g.fillRect(r4.x + 2, r4.y + 2, r4.width - 4, r4.height - 4);
        if (r4.x < 3 || r4.x >= 446)
        {
            xdir3 *= -1;
        }
        if (r4.intersects(r1))
        {
            xdir3 *= -1;
            ydir1 *= -1;
        }

        y5 += ydir2;
        r5 = new Rectangle(x5, y5, 30, 180);
        g.setColor(new Color(0, 0, 0));
        g.fillRect(r5.x, r5.y, r5.width, r5.height);
        g.setColor(new Color(0, 145, 255));
        g.fillRect(r5.x + 2, r5.y + 2, r5.width - 4, r5.height - 4);
        if (r5.y > HEIGHT - r5.height || r5.y < 26)
        {
            ydir2 *= -1;
        }
        if (r5.intersects(r2))
        {
            ydir2 *= -1;
            xdir1 *= -1;
        }
        if (r5.intersects(r3))
        {
            ydir2 *= -1;
            xdir2 *= -1;
        }
        if (r5.intersects(r4))
        {
            ydir2 *= -1;
            xdir3 *= -1;
        }

        if (inMenu)
        {
            Rectangle r_mouse = new Rectangle(m_x, m_y, 1, 1);
            Rectangle r_easybutton = new Rectangle(WIDTH / 2 - 50, HEIGHT / 2, 100, 30);
            Rectangle r_mediumbutton = new Rectangle(WIDTH / 2 - 50, HEIGHT / 2 + 50, 100, 30);
            Rectangle r_hardbutton = new Rectangle(WIDTH / 2 - 50, HEIGHT / 2 + 100, 100, 30);


            g.setColor(new Color(230, 230, 255));
            g.setFont(titleFont);
            g.drawString("Maze Game", WIDTH / 2 - 205, 200);


            g.setColor(new Color(0, 0, 0));
            g.setFont(titleFont);
            g.drawString("Maze Game", WIDTH / 2 - 200, 200);

            g.setColor(new Color(120, 30, 225));
            g.setFont(titleFont);
            g.drawString("Maze Game", WIDTH / 2 - 201, 200);

            g.setFont(buttonFont);

            g.setColor(new Color(40, 40, 40));

            //For Easy button
            g.fillRoundRect(WIDTH / 2 - 55, HEIGHT / 2 - 5, 110, 40, 20, 20);
            //For Medium button
            g.fillRoundRect(WIDTH / 2 - 55, HEIGHT / 2 + 45, 110, 40, 20, 20);
            //For Hard button
            g.fillRoundRect(WIDTH / 2 - 55, HEIGHT / 2 + 95, 110, 40, 20, 20);

            g.setColor(new Color(120, 30, 225));

            //For Easy button
            g.fillRect(WIDTH / 2 - 50, HEIGHT / 2, 100, 30);
            //For Medium button
            g.fillRect(WIDTH / 2 - 50, HEIGHT / 2 + 50, 100, 30);
            //For Hard button
            g.fillRect(WIDTH / 2 - 50, HEIGHT / 2 + 100, 100, 30);



            g.setColor(new Color(200, 200, 200));
            g.drawString("Easy", WIDTH / 2 - 15, HEIGHT / 2 + 20);
            g.drawString("Medium", WIDTH / 2 - 20, HEIGHT / 2 + 70);
            g.drawString("Hard", WIDTH / 2 - 15, HEIGHT / 2 + 120);


            if (r_easybutton.intersects(r_mouse) && mouseOnScreen)
            {
                g.setColor(new Color(205, 161, 203));
                g.fillRect(WIDTH / 2 - 50, HEIGHT / 2, 100, 30);
                g.setColor(new Color(255, 255, 255));
                g.drawString("Easy", WIDTH / 2 - 15, HEIGHT / 2 + 20);
                if (mouseClicked)
                {

                    setDif(1);
                    StartGame game = new StartGame();
                    this.setVisible(false);
                    game.setVisible(true);
                    inMenu = false;
                    this.dispose();

                }
            }

            if (r_mouse.intersects(r4))
            {
                g.setColor(new Color(240, 240, 255));
                g.drawString("Authors", r4.x + 45, r4.y + 15);
                g.drawString(saj.substring(0, 8), r2.x + 64, r2.y + 15);
                g.drawString(saj.substring(9, 19), r2.x + 58, r2.y + 30);
                for (int i = 0; i < rishabh.length(); i++)
                {
                    g.drawString("" + rishabh.charAt(i), r1.x + 15, r1.y + 15 + 15 * i);
                }
                for (int j = 0; j < steve.length(); j++)
                {
                    g.drawString("" + steve.charAt(j), r5.x + 10, r5.y + 18 + 15 * j);
                }
            }

            if (r_mediumbutton.intersects(r_mouse) && mouseOnScreen)
            {
                g.setColor(new Color(205, 161, 203));
                g.fillRect(WIDTH / 2 - 50, HEIGHT / 2 + 50, 100, 30);
                g.setColor(new Color(255, 255, 255));
                g.drawString("Medium", WIDTH / 2 - 20, HEIGHT / 2 + 70);
                if (mouseClicked)
                {

                    setDif(2);
                    StartGame game = new StartGame();
                    this.setVisible(false);
                    game.setVisible(true);
                    inMenu = false;
                    this.dispose();

                }
            }
            if (r_hardbutton.intersects(r_mouse) && mouseOnScreen)
            {
                g.setColor(new Color(205, 161, 203));
                g.fillRect(WIDTH / 2 - 50, HEIGHT / 2 + 100, 100, 30);
                g.setColor(new Color(255, 255, 255));
                g.drawString("Hard", WIDTH / 2 - 15, HEIGHT / 2 + 120);
                if (mouseClicked)
                {

                    setDif(3);
                    StartGame game = new StartGame();
                    this.setVisible(false);
                    game.setVisible(true);
                    inMenu = false;
                    this.dispose();

                }
            }
        }

        repaint();

    }

    /**
     * Returns the difficulty setting chosen by the user at the beginning of the
     * game. Easy, Medium, and Hard are represented by an integer of 1, 2, and 3
     * (respectively). This difficulty setting is made use of by the GamePanel
     * class.
     *
     *
     *@return dif The difficulty setting as an integer value.
     * @see GamePanel
     */
    public static int getDif()
    {
        return dif;
    }

    /**
     * Sets the difficulty setting chosen by the user at the beginning of the
     * game via the Easy, Medium, and Hard buttons. Easy, Medium, and Hard are
     * represented by an integer of 1, 2, and 3 (respectively).
     *
     * @param d Difficulty integer
     * @see #getDif()
     */
    private void setDif(int d)
    {
        dif = d;
    }

    /**
     * Sets the static boolean mouseClicked to true, upon each mouse click. This
     * mouse function is used by the paintComponent method to select a
     * difficulty for the game.
     *
     * @param e MouseEvent event type
     * @see #paintComponent(java.awt.Graphics)
     */
    @Override
    public void mousePressed(MouseEvent e)
    {
        mouseClicked = true;
    }

    /**
     * Sets the static boolean mouseOnScreen to true, upon each mouse movement
     * in the JFrame. Also updates the current mouse position (m_x, m_y), which
     * aids the paintComponent method in animating each difficulty button.
     *
     * @param e MouseEvent event type
     * @see #paintComponent(java.awt.Graphics)
     */
    @Override
    public void mouseMoved(MouseEvent e)
    {
        mouseOnScreen = true;
        m_x = e.getX();
        m_y = e.getY();
        e.consume();
    }

    /**
     * Sets the static boolean mouseClicked to false, upon each mouse release.
     *
     * @param e MouseEvent event type
     */
    @Override
    public void mouseReleased(MouseEvent e)
    {
        mouseClicked = false;
    }

    /**
     * Sets the static boolean mouseOnScreen to true, upon each mouse entry into
     * the JFrame. Sets the initial mouse position (m_x, m_y).
     *
     * @param e MouseEvent event type
     * @see #paintComponent(java.awt.Graphics)
     */
    @Override
    public void mouseEntered(MouseEvent e)
    {
        m_x = e.getX();
        m_y = e.getY();
        mouseOnScreen = true;
        e.consume();
    }

    /**
     * Sets the static boolean mouseOnScreen to false, upon each mouse exit out
     * of the JFrame. Also aids the paintComponent method in animating each
     * difficulty button.
     *
     * @param e MouseEvent event type
     * @see #paintComponent(java.awt.Graphics)
     */
    @Override
    public void mouseExited(MouseEvent e)
    {
        mouseOnScreen = false;
        e.consume();
    }

    /**
     * This method is not employed, but is included as part of the MouseListener
     * interface.
     *
     * @param e MouseEvent event type
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {
        //-- Not Implemented
    }

    /**
     * This method is not employed, but is included as part of the
     * MouseMotionListener interface.
     *
     * @param e MouseEvent event type
     */
    @Override
    public void mouseDragged(MouseEvent e)
    {
        //-- Not Implemented
    }

    /**
     * Implemented as part of the Runnable interface.
     */
    @Override
    public void run()
    {
        try
        {
        } catch (Exception e)
        {
        }

    }

    /**
     * Sets up a StartMenu object to run as a thread.
     *
     * @param args Array of command-line arguments
     */
    public static void main(String[] args) throws FontFormatException, IOException
    {
        StartMenu menu = new StartMenu();
        Thread th = new Thread(menu);
        th.start();

    }
}
