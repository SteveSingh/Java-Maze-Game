package Maze;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * The intermediate window (JFrame) between the Main Menu and the game loop, the
 * GamePanel object (JPanel extension) is contained within this JFrame.
 */
public class StartGame extends JFrame
{

    /**
     * The main game panel object which handles the game loop, this panel
     * extends the JPanel class.
     */
    private GamePanel gp;
    /**
     * Window width of the JFrame and children.
     */
    public final static int WIDTH = 600;
    /**
     * Window height of the JFrame and children.
     */
    public final static int HEIGHT = 600;

    /**
     * Initializes the window's (JFrame) size, visibility and title along with
     * other parameters and does a method call to add the game panel (JPanel
     * extension) and it's game loop to the JFrame window.
     */
    public StartGame()
    {
        super("The Maze Game");
        makeGUI();
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        pack();
        setResizable(false);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setBackground(new Color(245, 220, 215));

    }

    /**
     * Initializes the GamePanel object while passing in an argument that
     * represents the dimensions of the initial maze to be generated (N by N
     * maze). Adds the GamePanel (JPanel extension) to the JFrame component.
     */
    private void makeGUI()
    {
        int N = 2;
        gp = new GamePanel(N);
        this.add(gp);
    }
}
