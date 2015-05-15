package Maze;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * JPanel extension which handles the main game loop, updates and drawing to the
 * screen.
 */
public class GamePanel extends JPanel implements Runnable
{

    /**
     * Panel width of the GamePanel obtained from the parent StartGame.
     */
    private static final int panelWidth = StartGame.WIDTH;
    /**
     * Panel height of the GamePanel obtained from the parent StartGame.
     */
    private static final int panelHeight = StartGame.HEIGHT;
    /**
     * Holds the difficulty level (integer) obtained in the StartMenu module.
     */
    private static int difficulty;
    /**
     * Calculated x coordinate to center maze on window of arbitrary sizes.
     */
    private int screenX;
    /**
     * Calculated y coordinate to center maze on window of arbitrary sizes.
     */
    private int screenY;
    /**
     * Maze dimension (integer) used when generating a new maze.
     */
    private int mazeDimension;
    /**
     * Initial x coordinate position of the character object on the maze.
     */
    private int initX;
    /**
     * Initial y coordinate position of the character object on the maze.
     */
    private int initY;
    /**
     * Array that contains the character object's x and y position, or the initX
     * and initY values.
     */
    private int[] pPos;
    /**
     * Row counter value which is adjusted (determines next row value) to move
     * to the next cell in order to hide cells in the end game animation.
     *
     * @see #initX
     * @see #initY
     */
    private int rowCounter;
    /**
     * Column counter value which is adjusted (determines next column value) to
     * move to the next cell in order to hide cells in the end game animation.
     */
    private int columnCounter;
    /**
     * Row value which represents current cell that will be set to hidden.
     */
    private int rmX;
    /**
     * Column value which represents current cell that will be set to hidden.
     */
    private int rmY;
    /**
     * The system time when the initial loop begins (updated in
     * UpdateActualTime()).
     */
    private static long startTime = 0;
    /**
     * The level time determined based on difficulty picked by the player. This
     * time is decremented every second.
     */
    private static int levelTime;
    /**
     * Bonus time awarded when the character object passes over a timePoint
     * cell. Award varies depending on the difficulty picked in StartMenu.
     */
    private static int bonusTime;
    /**
     * Container used to determine the correct time (integer values) to draw to
     * the game screen.
     */
    private static int printTime;
    /**
     * Score or points container determined upon completion of a level, awarded
     * score varies depending on the difficulty picked in the StartMenu.
     */
    private static int totalScore;
    /**
     * Boolean which is triggered (true) when the end game menu must be drawn to
     * screen. Signifies that the current game is over (gameOver == true).
     */
    private static boolean finalScreen = false;
    /**
     * Boolean which is triggered (true) once the main thread is initialized,
     * signifying that the main game loop is running.
     */
    private boolean running = false;
    /**
     * Boolean which is triggered (true) when the character object passes over
     * an endPoint cell. Signifies that the character must be sent to the next
     * level, the value is reset once the doNextLevel() is completed.
     *
     * @see #doNextLevel()
     */
    protected static boolean nextLevel = false;
    /**
     * Boolean which is triggered (true) when the character object passes over a
     * timePoint cell. Results in levelTime time being incremented by bonusTime.
     *
     * @see #levelTime
     * @see #bonusTime
     */
    protected static boolean incrementTime = false;
    /**
     * Boolean which is triggered (true) when levelTime becomes zero (0).
     * Signifies that the game is over and action is taken to clear the screen
     * and display the end game menu.
     *
     * @see #levelTime
     * @see #gameOver()
     */
    protected static boolean gameOver = false;
    /**
     * Contains a GenMaze object which is generated upon initialization of
     * GamePanel or when a new level is initialized.
     *
     * @see GenMaze
     */
    private GenMaze maze;
    /**
     * Contains a GraphicsMaze object which is generated upon initialization of
     * GamePanel or when a new level is initialized. The object contains a set
     * of RectCells which represent the actual maze that will be drawn to the
     * screen.
     *
     * @see GraphicsMaze
     */
    private GraphicsMaze gMaze;
    /**
     * Contains a character object which represents the player (user).
     *
     * @see Character
     */
    private Character player;
    /**
     * The main input handler for this object and the character.
     *
     * @see Character
     * @see InputHandler
     * @see MoveHandler
     */
    private InputHandler input;
    /**
     * Font object containing parameters describing the type of font used to
     * draw score and time to the screen.
     *
     */
    private Font msgsFont;
    /**
     * Image object used for double buffering and drawing graphics to the
     * screen.
     *
     */
    private Image dbImage = null;
    /**
     * Buffered image container for the exit button image.
     */
    private BufferedImage exitButton;
    /**
     * Buffered image container for the restart button image.
     *
     */
    private BufferedImage restartButton;
    /**
     * Thread used to handle the main game loop and character movement.
     *
     */
    private Thread animator;

    /**
     * Initializes the GamePanel object by adjusting panel dimensions,
     * generating a new maze, initializing input handling and character
     * position. Button images are loaded at this point and the main game thread
     * is started in this.
     *
     * @param mazeSize Initial maze dimensions (mazeSize by mazeSize).
     */
    public GamePanel(int mazeSize)
    {
        input = new InputHandler();
        addKeyListener(input);
        setBackground(Color.white);
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setDoubleBuffered(true);
        initLevel(mazeSize);
        adjustDifficulty();

        try
        {
            exitButton = ImageIO.read(new File("res/exitBt.png"));
            restartButton = ImageIO.read(new File("res/restartBt.png"));
        } catch (IOException e)
        {
        }

        msgsFont = new Font("SansSerif", Font.BOLD, 24);
        setFocusable(true);
        requestFocus();
        startGame();

    }

    /**
     * Adjust the difficulty of the maze by obtaining the user's input from
     * StartMenu. Depending on the difficulty, the levelTime, bonusTime and
     * scoring calculations are adjusted.
     */
    private void adjustDifficulty()
    {
        int dif = StartMenu.getDif();
        if (dif == 1)
        {
            levelTime = 100;
            bonusTime = 5;
            difficulty = dif;
        } else if (dif == 2)
        {
            levelTime = 70;
            bonusTime = 4;
            difficulty = dif;
        } else if (dif == 3)
        {
            levelTime = 40;
            bonusTime = 2;
            difficulty = dif;
        } else
        {
            levelTime = 100;
            bonusTime = 6;
            difficulty = dif;
        }

    }

    /**
     * Main thread initialization occurs here, the thread animator is checked
     * for a null value along with the game loops "running" status. The thread
     * is initialized and then started and goes to run(), where the main loop
     * begins.
     */
    private void startGame()
    {
        if (animator == null || !running)
        {
            animator = new Thread(this);
            animator.start();
        }
    }

    /**
     * The paint method is overridden and is invoked by Swing to draw components
     * to the screen. Double buffering is used to enable faster performance,
     * where an image/graphic is loaded into a buffer while another is being
     * drawn to the screen.
     *
     * @param g Graphics rendering object used to draw to the window
     * (JFrame/JPanel).
     */
    @Override
    public void paint(Graphics g)
    {

        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        dbImage = createImage(getWidth(), getHeight());
        g2d = (Graphics2D) dbImage.getGraphics();
        Rectangle r = new Rectangle(0, 0, panelWidth, panelHeight);
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(0, 0, panelWidth, panelHeight);
        paintComponent(g2d);

        g.drawImage(dbImage, 0, 0, this);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    /**
     * Overrides the paintComponent method in JPanel and does draw calls to the
     * JPanel window. The paintComponent method draws the player, maze, time and
     * score to the screen as well as the end menu screen.
     *
     * @param g Graphics rendering object used to draw to the window
     * (JFrame/JPanel).
     */
    @Override
    public void paintComponent(Graphics g)
    {
        g.setColor(new Color(40, 40, 40));
        g.fillRect(0, 0, 600, 600);

        gMaze.draw(g);
        //Check if player has reached next level, if false continue playing
        if (!nextLevel)
        {
            player.draw(g);
        } else
        {
            doNextLevel();
        }
        doTime(g);
        doScore(g);
        if (gameOver && finalScreen)
        {
            doExitBt(g);
            doRestartBt(g);
        }
    }

    /**
     * Overridden Runnable method which is required when starting a thread. The
     * thread then executes the method's instructions, the main game loop
     * execution occurs in this method, mainly, where the game (character
     * movement) is updated and time is updated.
     */
    @Override
    public void run()
    {
        running = true;

        while (running)
        {
            if (levelTime <= 0)
            {
                gameOver = true;
            }
            if (gameOver && !finalScreen)
            {
                gameOver();
            }
            gameUpdate();
            updateActualTime();
            try
            {
                Thread.sleep(10);
                if (gameOver && !finalScreen)
                {
                    Thread.sleep(100);
                }
            } catch (InterruptedException ex)
            {
            }
        }
    }

    /**
     * Updating portion of the game loop, the method updates the character's
     * position on the screen while gameOver is false (not over) and while
     * finalScreen is true (End game menu).
     */
    private void gameUpdate()
    {
        if (!gameOver)
        {
            player.move();
        }
        if (finalScreen)
        {
            player.move();
        }
        repaint();
    }

    /**
     * Draws an image and rectangle representing the restart game option. A
     * constant check for intersection is done with the character object, until
     * either the restart button or the exit button has been intersected. Upon
     * intersection the game loop is reinitialized to start the game from the
     * beginning.
     *
     * @param g Graphics rendering object used to draw to the window
     * (JFrame/JPanel).
     */
    private void doExitBt(Graphics g)
    {
        Rectangle ex = new Rectangle(492, 500, 108, 43);
        g.drawImage(exitButton, ex.x, ex.y, ex.width, ex.height, null);
        if (player.getBounds().intersects(ex))
        {
            System.exit(0);
        }

    }

    /**
     * Draws an image and rectangle representing the restart game option. A
     * constant check for intersection is done with the character object, until
     * either the restart button or the exit button has been intersected. Upon
     * intersection the game loop is reinitialized to start the game from the
     * beginning.
     *
     * @param g Graphics rendering object used to draw to the window
     * (JFrame/JPanel).
     */
    private void doRestartBt(Graphics g)
    {
        Rectangle ex = new Rectangle(0, 500, 108, 35);
        g.drawImage(restartButton, ex.x, ex.y, ex.width, ex.height, null);
        if (player.getBounds().intersects(ex))
        {
            adjustDifficulty();
            gameOver = false;
            finalScreen = false;
            running = true;
            rmY = 1;
            initLevel(2);
        }

    }

    /**
     * Handles the end game animation where cells generated by the maze are
     * wiped off the screen with a 100ms period between each wipe. The method
     * determines the row and column of the nth cell and performs a hideCell()
     * on that cell, the row and column is continuously decremented until the
     * 0th cell is reached and hidden. Upon completion, the finalScreen is set
     * to true enabling the drawing of the final menu screen (Restart/Exit
     * menu).
     *
     */
    private void gameOver()
    {
        columnCounter--;
        rmX = columnCounter;
        if (rmY >= 0)
        {
            rmY = maze.size() - rowCounter;
        }
        if (rmX == 0)
        {
            columnCounter = maze.size();
            rowCounter++;
        }
        if (rmX >= 0 && rmY >= 0)
        {
            maze.hideCell(rmX, rmY);
        } else
        {
            finalScreen = true;
        }
        repaint();
    }

    /**
     * Draws a string of the score to the window (JFrame/JPanel).
     *
     * @param g Graphics rendering object used to draw to the window
     * (JFrame/JPanel).
     */
    private void doScore(Graphics g)
    {
        String msg = "Score: " + totalScore;
        g.setColor(new Color(255, 255, 255));
        g.setFont(msgsFont);
        g.drawString(msg, 350, 100);
    }

    /**
     * Draws a string of the current game/level time to the window
     * (JFrame/JPanel). The remaining time is also updated if there is any
     * "bonus time", the amount of bonus time is based on the difficulty picked.
     *
     * @param g Graphics rendering object used to draw to the window
     * (JFrame/JPanel).
     */
    private void doTime(Graphics g)
    {
        printTime = levelTime;
        if (printTime <= 0)
        {
            printTime = 0;
        }
        if (incrementTime && printTime > 0)
        {
            levelTime += bonusTime;
            for (int y = 0; y < mazeDimension; y++)
            {
                for (int x = 0; x < mazeDimension; x++)
                {
                    if (maze.getGrid(x, y).isTimePoint())
                    {
                        maze.getGrid(x, y).setTimePoint(false);
                    }
                }
            }
            incrementTime = false;
        }
        String msg = "Time: " + printTime + " ";

        if (printTime > 10)
        {
            g.setColor(new Color(255, 255, 255));
            g.setFont(msgsFont);
            g.drawString(msg, 150, 100);
        } else
        {
            g.setColor(new Color(255, 255, 255));
            g.setFont(msgsFont);
            g.drawString(msg.substring(0, 5), 150, 100);
            g.setColor(Color.red);
            g.setFont(msgsFont);
            g.drawString(msg.substring(6, 8), 226, 100);
        }

    }

    /**
     * Calculates time based on the system time and handles inconsistencies in
     * time (Lag). The method's main purpose is to handle the game time or level
     * time for each maze instance that the user plays. Level time is determined
     * by the difficulty that was initially picked by the user.
     */
    public static void updateActualTime()
    {
        if ((System.currentTimeMillis() - startTime >= 1000))
        {
            levelTime -= 1;
            startTime = System.currentTimeMillis();
        }
    }

    /**
     * Initializes the maze, graphics maze and character's position in the maze
     * upon creation of the GamePanel object. The maze is also centered based on
     * the window's dimensions.
     */
    private void initLevel(int mazeSize)
    {
        mazeDimension = mazeSize;

        screenX = (StartGame.WIDTH - mazeDimension * 40) / 2;
        screenY = (StartGame.HEIGHT - mazeDimension * 40) / 2;
        totalScore = 0;
        maze = new GenMaze(mazeDimension, screenX, screenY);
        pPos = initializePlayer();
        initX = pPos[0];
        initY = pPos[1];
        gMaze = new GraphicsMaze(maze);
        player = new Character(initX, initY, gMaze);
        player.setMover(input);
        columnCounter = maze.size();
        rowCounter = 1;

    }

    /**
     * The method is called whenever the character reaches an endpoint cell.
     * When called, the method determines the score for completing the level
     * (calculated based on maze size and difficulty), then checks if the maze's
     * dimensions are below 10 by 10. If the dimensions are below this, then the
     * maze size is incremented by one level; once the maximum (N= 10) maze size
     * is reached then doNextLevel() will continue to generate 10 by 10 sized
     * mazes. The method essentially reinitializes the maze, graphics maze and
     * character position to a new maze. The new maze size is also used to
     * determine the center position of the maze relative to the window's
     * dimensions.
     */
    private void doNextLevel()
    {
        totalScore += mazeDimension * difficulty * 10;

        //Create a new maze
        if (mazeDimension < 10)
        {
            mazeDimension += 1;
            screenX = (StartGame.WIDTH - mazeDimension * 40) / 2;
            screenY = (StartGame.HEIGHT - mazeDimension * 40) / 2;
            maze = new GenMaze(mazeDimension, screenX, screenY);
        } else
        {
            mazeDimension = 10;
            screenX = (StartGame.WIDTH - mazeDimension * 40) / 2;
            screenY = (StartGame.HEIGHT - mazeDimension * 40) / 2;
            maze = new GenMaze(mazeDimension, screenX, screenY);
        }
        columnCounter = maze.size();
        pPos = initializePlayer();
        initX = pPos[0];
        initY = pPos[1];
        gMaze = new GraphicsMaze(maze);
        player = new Character(initX, initY, gMaze);
        player.setMover(input);
        incrementTime = false;
        nextLevel = false;

    }

    /**
     * Upon generation of a new maze (GenMaze/Array of Cell objects), the
     * character's position relative to the maze must be reinitialized. Calling
     * this method returns an array of integers (size 2) that contains within it
     * the starting x position coordinate and starting y position coordinate.
     *
     */
    private int[] initializePlayer()
    {
        int startX, startY;
        startX = 0;
        startY = 0;

        for (int j = 0; j < maze.getGridDimensions(); j++)
        {
            for (int i = 0; i < maze.getGridDimensions(); i++)
            {
                if (maze.getGrid(i, j).isStartPoint())
                {
                    startX = maze.getGrid(i, j).getCoordX();
                    startY = maze.getGrid(i, j).getCoordY();
                }
            }
        }
        int[] pos =
        {
            startX + 10, startY + 10
        };

        return (pos);
    }
}
