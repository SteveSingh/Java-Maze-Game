package Maze;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Character class which contains information on how a character object (player)
 * should behave; handles movement upon input, collisions and redraw functions.
 *
 */
public class Character
{

    /**
     * Buffered image container for the character's sprite.
     *
     */
    private BufferedImage playerImg;
    /**
     * Character object's (Rectangle) movement speed in pixels.
     *
     */
    private static int movementSpeed;
    /**
     * x coordinate of character object's current position in the maze.
     *
     */
    private int xPos;
    /**
     * y coordinate of character object's current position in the maze.
     *
     */
    private int yPos;
    /**
     * Character object's width.
     *
     */
    private int width;
    /**
     * Character object's height.
     *
     */
    private int height;
    /**
     * Graphics Maze object used to obtain a clone of the current maze drawn in
     * order for correct collision detection.
     *
     */
    private GraphicsMaze colMaze;
    /**
     * Move handler object which character object depends on to correctly handle
     * movements, all input handlers must meet this specification.
     *
     */
    private MoveHandler mover = null;

    /**
     * Initializes the initial x and y position of the character object, the
     * speed and the dimensions (size of player). Obtains the most recent drawn
     * copy of the maze for collision detection and loads the sprite
     * representing the character object.
     *
     * @param xPos Initial x position.
     * @param yPos Initial y position.
     * @param g Graphics rendering object used to draw to the window
     * (JFrame/JPanel).
     */
    public Character(int xPos, int yPos, GraphicsMaze g)
    {
        this.xPos = xPos;
        this.yPos = yPos;
        movementSpeed = 2;
        width = 20;
        height = 20;
        this.colMaze = g;

        try
        {
            playerImg = ImageIO.read(new File("res/playerSprite.png"));
        } catch (IOException e)
        {
        }
    }

    /**
     * Sets up the move handler to be used with the input handler m.
     *
     * @param m An input handler which meets the specifications of the move
     * handler object.
     */
    public void setMover(MoveHandler m)
    {
        this.mover = m;
    }

    /**
     * Returns a new rectangle object at the player's current position.
     *
     * @return Rectangle Rectangle object with character object's current
     * position and size.
     */
    public Rectangle getBounds()
    {

        return new Rectangle(getxPos(), getyPos(), getWidth(), getHeight());
    }

    /**
     * Returns the current x coordinate of the player.
     *
     * @return xPos Character rectangle object's x position.
     * @see #xPos
     */
    public int getxPos()
    {
        return xPos;
    }

    /**
     * Returns the current x coordinate of the player.
     *
     * @return yPos Character rectangle object's y position.
     * @see #yPos
     */
    public int getyPos()
    {
        return yPos;
    }

    /**
     * Returns the width of the player.
     *
     * @return width Character rectangle object's width.
     * @see #width
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * Returns the height of the player.
     *
     * @return height Character rectangle object's height.
     * @see #height
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * Collision detection/avoidance, projects a copy of the character object to
     * a new position corresponding to the direction "direc" and then checks if
     * a collision will occur at that point before moving the player. Possible
     * collisions that can occur are wall collisions, time point collisions and
     * end point collisions. The character can't pass walls (wall collision) and
     * certain events are triggered when the character object collides with an
     * end point or time point. Returns true if collision occurs otherwise
     * returns false.
     *
     * @param direc String representing the anticipated direction.
     */
    public boolean collide(String direc)
    {

        for (RectCell it : colMaze.getRectangles())
        {

            Rectangle r1 = it.eastWall;
            Rectangle r2 = it.northWall;
            Rectangle r3 = it.southWall;
            Rectangle r4 = it.westWall;
            Rectangle Point = it.getCellBox();
            Rectangle pos;

            if (direc.equals("up"))
            {
                pos = new Rectangle(this.xPos, this.yPos - movementSpeed, 20, 20);
            } else if (direc.equals("down"))
            {
                pos = new Rectangle(this.xPos, this.yPos + movementSpeed, 20, 20);
            } else if (direc.equals("left"))
            {
                pos = new Rectangle(this.xPos - movementSpeed, this.yPos, 20, 20);
            } else
            {
                pos = new Rectangle(this.xPos + movementSpeed, this.yPos, 20, 20);
            }

            if ((r1 != null) && (r1.intersects(pos)))
            {

                return true;
            }
            if ((r2 != null) && (r2.intersects(pos)))
            {
                return true;
            }
            if ((r3 != null) && (r3.intersects(pos)))
            {
                return true;
            }
            if ((r4 != null) && (r4.intersects(pos)))
            {
                return true;
            }
            //Check if finish point/block
            if ((Point != null) && (Point.intersects(pos) && (it.isEndPoint())))
            {
                GamePanel.nextLevel = true;
            }
            //Check if time point/block
            if ((Point != null) && (Point.intersects(pos) && (it.isTimePoint())))
            {
                GamePanel.incrementTime = true;
            }
        }

        return false;
    }

    /**
     * Performs a move operation in the corresponding direction if there are no
     * possible collisions.
     */
    public void move()
    {
        if ((mover.moveUp()) && (!collide("up")))
        {
            this.yPos -= movementSpeed;
        }

        if ((mover.moveDown()) && (!collide("down")))
        {
            this.yPos += movementSpeed;
        }
        if ((mover.moveLeft()) && (!collide("left")))
        {
            this.xPos -= movementSpeed;
        }
        if ((mover.moveRight()) && (!collide("right")))
        {
            this.xPos += movementSpeed;
        }
        if (this.getxPos() < 0)
        {
            this.xPos = StartGame.WIDTH / 2;
            this.yPos = StartGame.HEIGHT / 2;
        }
        if (this.getxPos() > StartGame.WIDTH)
        {
            this.xPos = StartGame.WIDTH / 2;
            this.yPos = StartGame.HEIGHT / 2;
        }
        if (this.getyPos() < 0)
        {
            this.xPos = StartGame.WIDTH / 2;
            this.yPos = StartGame.HEIGHT / 2;
        }
        if (this.getyPos() > StartGame.HEIGHT)
        {
            this.xPos = StartGame.WIDTH / 2;
            this.yPos = StartGame.HEIGHT / 2;
        }

    }

    /**
     * Redraws the character object based on the current position and size.
     *
     * @param g Graphics rendering object used to draw to the window
     * (JFrame/JPanel).
     */
    public void draw(Graphics g)
    {
        Rectangle r = getBounds();
        g.drawImage(playerImg, r.x, r.y, r.width, r.height, null);
    }
}
