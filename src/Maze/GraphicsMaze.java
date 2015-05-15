package Maze;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.imageio.ImageIO;

/**
 * Generates a graphical representation of the GenMaze object.
 *
 * @see GenMaze
 */
public class GraphicsMaze
{

    /**
     * Buffered image container for the start point cell (Starting position)
     * image.
     */
    private BufferedImage goLine;
    /**
     * Buffered image container for the end point cell (Finish position) image.
     */
    private BufferedImage finishLine;
    /**
     * Buffered image container for the time point cell (bonus time) image.
     */
    private BufferedImage bonusTime;
    /**
     * Buffered image container for vertical walls (East and West) image.
     */
    private BufferedImage verticalWall;
    /**
     * Buffered image container for horizontal walls (North and South) image.
     */
    private BufferedImage horizontalWall;
    /**
     * Default cell tile color if tile is not a start, end or time point cell.
     */
    private Color cellColor = new Color(40, 40, 40);
    /**
     * Default solid wall color that the buffered wall image is drawn over.
     */
    private Color wallColor = new Color(147, 201, 243);
    /**
     * Thread safe array list of RectCell objects used for generating and
     * drawing.
     */
    private static CopyOnWriteArrayList<RectCell> walls;
    /**
     * Thread safe array list of RectCell objects used for cloning and collision
     * detection.
     */
    private static CopyOnWriteArrayList<RectCell> copy;
    /**
     * GenMaze object containing the hypothetical/conceptual maze which is then
     * converted into a graphical maze in this.
     */
    private GenMaze maze;

    /**
     * Initializes the specific images required for cell tiles and walls as well
     * as initializing an thread safe array list of RectCell objects.
     *
     * @param m GenMaze object containing completed (conceptual) maze.
     */
    public GraphicsMaze(GenMaze m)
    {
        walls = new CopyOnWriteArrayList<>();
        this.maze = m;

        try
        {
            goLine = ImageIO.read(new File("res/startLine.png"));
            finishLine = ImageIO.read(new File("res/finishLine.png"));
            bonusTime = ImageIO.read(new File("res/bonusTime.png"));
            verticalWall = ImageIO.read(new File("res/VerticalWall.png"));
            horizontalWall = ImageIO.read(new File("res/HorizontalWall.png"));
        } catch (IOException e)
        {
        }
    }

    /**
     * Draws a maze using rectangle objects where the maze is drawn based on the
     * GenMaze object representation. Depending on the visibility, the cell,
     * it's cell type and the walls that it contains are drawn to the screen.
     * Rectangle objects are created for all of the components in a cell (cell
     * box (tile), walls and type) and a RectCell object is initialized based on
     * these arguments. Each RectCell object is added to the list, once all
     * rectangles in the maze have been drawn and added, a clone is made for
     * safety when doing collision detection/avoidance.
     *
     * @param g Graphics rendering object used to draw to the window
     * (JFrame/JPanel).
     */
    public void draw(Graphics g)
    {

        walls.clear();
        boolean done;
        for (int j = 1; j <= this.maze.getGridDimensions(); j++)
        {
            for (int i = 1; i <= this.maze.getGridDimensions(); i++)
            {
                if (maze.getGrid(i, j).isVisibile())
                {
                    //gets the generated x and y coordinates from the specific cell and creates a rectangle.
                    Rectangle r = new Rectangle(maze.getGrid(i, j).getCoordX(), maze.getGrid(i, j).getCoordY(), 40, 40);
                    boolean sp, ep, tp;
                    sp = false;
                    ep = false;
                    tp = false;

                    if (maze.getGrid(i, j).isStartPoint())
                    {
                        sp = true;
                        if (goLine == null)
                        {   // the sprite has no image
                            g.setColor(new Color(245, 220, 215));
                            g.fillRect(r.x, r.y, r.width, r.height);
                        } else
                        {
                            g.drawImage(goLine, r.x, r.y, r.width, r.height, null);
                        }
                    } else if (maze.getGrid(i, j).isEndPoint())
                    {
                        ep = true;
                        if (finishLine == null)
                        {   // the sprite has no image
                            g.setColor(new Color(245, 220, 215));
                            g.fillRect(r.x, r.y, r.width, r.height);
                        } else
                        {
                            g.drawImage(finishLine, r.x, r.y, r.width, r.height, null);
                        }
                    } else if (maze.getGrid(i, j).isTimePoint())
                    {
                        tp = true;
                        if (bonusTime == null)
                        {   // the sprite has no image
                            g.setColor(new Color(245, 220, 215));
                            g.fillRect(r.x, r.y, r.width, r.height);
                        } else
                        {
                            g.drawImage(bonusTime, r.x, r.y, r.width, r.height, null);
                        }
                    } else
                    {
                        g.setColor(cellColor);
                        g.fillRect(r.x, r.y, r.width, r.height);
                    }

                    Rectangle s;
                    if (maze.getGrid(i, j).isSouthWall())
                    {
                        s = new Rectangle(maze.getGrid(i, j).getCoordX(), maze.getGrid(i, j).getCoordY() + 38, 40, 2);
                        g.setColor(wallColor);
                        g.fillRect(s.x, s.y, s.width, s.height);
                        g.drawImage(horizontalWall, s.x, s.y, null);
                    } else
                    {
                        s = null;
                    }

                    Rectangle n;
                    if (maze.getGrid(i, j).isNorthWall())
                    {
                        n = new Rectangle(maze.getGrid(i, j).getCoordX(), maze.getGrid(i, j).getCoordY(), 40, 2);
                        g.setColor(wallColor);
                        g.fillRect(n.x, n.y, n.width, n.height);
                        g.drawImage(horizontalWall, n.x, n.y, null);
                    } else
                    {
                        n = null;
                    }

                    Rectangle w;
                    if (maze.getGrid(i, j).isWestWall())
                    {
                        w = new Rectangle(maze.getGrid(i, j).getCoordX(), maze.getGrid(i, j).getCoordY(), 2, 40);
                        g.setColor(wallColor);
                        g.fillRect(w.x, w.y, w.width, w.height);
                        g.drawImage(verticalWall, w.x, w.y, null);
                    } else
                    {
                        w = null;
                    }

                    Rectangle e;
                    if (maze.getGrid(i, j).isEastWall())
                    {
                        e = new Rectangle(maze.getGrid(i, j).getCoordX() + 38, maze.getGrid(i, j).getCoordY(), 2, 40);
                        g.setColor(wallColor);
                        g.fillRect(e.x, e.y, e.width, e.height);
                        g.drawImage(verticalWall, e.x, e.y, null);
                    } else
                    {
                        e = null;
                    }
                    RectCell cellInfo = new RectCell(r, n, s, e, w, sp, ep, tp);
                    walls.add(cellInfo);
                }
            }

        }
        copy = (CopyOnWriteArrayList<RectCell>) walls.clone(); // make a clone just to be safe.
    }

    /**
     * Returns a clone (for safety) of the RectCell array list which is required
     * by the character object's collision detection.
     *
     * @return copy Cloned version of walls ArrayList
     * @see #copy
     */
    public CopyOnWriteArrayList<RectCell> getRectangles()
    {
        return copy;
    }
}
