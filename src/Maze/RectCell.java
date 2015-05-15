package Maze;

import java.awt.Rectangle;

/**
 * RectCell represents an object that contains graphical information (Rectangle
 * objects) on a specific cell. The RectCell is then used to draw the specific
 * cell and its walls to the screen, depending on the characteristics of each
 * rectangle in the RectCell (non existent or existent), a rectangle object is
 * correspondingly created. For example, if the cell has a north, east and west
 * wall and is a timePoint, then the object would be described as follows:
 *
 * cellBox (Rectangle) northWall (Rectangle) eastWall (Rectangle) westWall
 * (Rectangle) southWall (NULL) startPoint (false) endPoint (false) timePoint
 * (true)
 *
 */
public class RectCell
{

    /**
     * Rectangle object representing the cell tile.
     */
    private Rectangle cellBox;
    /**
     * Rectangle object representing the north wall of the cell.
     */
    protected Rectangle northWall;
    /**
     * Rectangle object representing the south wall of the cell.
     */
    protected Rectangle southWall;
    /**
     * Rectangle object representing the east wall of the cell.
     */
    protected Rectangle eastWall;
    /**
     * Rectangle object representing the west wall of the cell.
     */
    protected Rectangle westWall;
    /**
     * A boolean that distinguishes the cell between a normal cell tile or a
     * start point/position cell (startPoint = true, then cell is a start
     * point).
     */
    private boolean startPoint;
    /**
     * A boolean that distinguishes the cell between a normal cell tile or a end
     * point/position cell (endPoint = true, then cell is a end point).
     */
    private boolean endPoint;
    /**
     * A boolean that distinguishes the cell between a normal cell tile or a
     * time point/position cell (timePoint = true, then cell is a time point).
     */
    private boolean timePoint;

    /**
     * Creates a RectCell object which represents a cell and it's walls as a set
     * of graphical rectangles (Graphics package) and booleans which correspond
     * to the type of cell the object is (i.e., Start point, end point, bonus
     * time point).
     *
     * @param cellBox Rectangle object representing the cell tile.
     * @param northWall Rectangle object representing the cell's north wall.
     * @param southWall Rectangle object representing the cell's south wall.
     * @param eastWall Rectangle object representing the cell's east wall.
     * @param westWall Rectangle object representing the cell's west wall.
     * @param sp Boolean that determines whether the cell is a start point.
     * @param ep Boolean that determines whether the cell is a end point.
     * @param tp Boolean that determines whether the cell is a time (bonus)
     * point.
     */
    public RectCell(Rectangle cellBox, Rectangle northWall, Rectangle southWall, Rectangle eastWall, Rectangle westWall, boolean sp, boolean ep, boolean tp)
    {
        this.cellBox = cellBox;
        this.northWall = northWall;
        this.southWall = southWall;
        this.eastWall = eastWall;
        this.westWall = westWall;
        this.startPoint = sp;
        this.endPoint = ep;
        this.timePoint = tp;
    }

    /**
     * Returns a boolean that represents whether this has a start point.
     *
     * @return startPoint Cell's type
     * @see #startPoint
     */
    public boolean isStartPoint()
    {
        return startPoint;
    }

    /**
     * Returns a boolean that represents whether this has an end point.
     *
     * @return endPoint Cell's type
     * @see #endPoint
     */
    public boolean isEndPoint()
    {
        return endPoint;
    }

    /**
     * Returns a boolean that represents whether this has a time (bonus) point.
     *
     * @return timePoint Cell's type
     * @see #timePoint
     */
    public boolean isTimePoint()
    {
        return timePoint;
    }

    /**
     * Sets the time point boolean to true if this is a time point cell,
     * otherwise it is false.
     *
     * @param t Sets timePoint to t
     * @see #timePoint
     */
    public void setTimePoint(boolean t)
    {
        this.timePoint = t;
    }

    /**
     * Returns the rectangle object (graphical) that represents this.
     *
     * @return cellBox Cell's tile
     * @see #cellBox
     */
    public Rectangle getCellBox()
    {
        return cellBox;
    }

    /**
     * Returns the northern wall rectangle object (graphical) for this.
     *
     * @return northWall Cell's North wall.
     * @see #northWall
     */
    public Rectangle getNorthWall()
    {
        return northWall;
    }

    /**
     * Returns the southern wall rectangle object (graphical) for this.
     *
     * @return southWall Cell's South wall.
     * @see #southWall
     */
    public Rectangle getSouthWall()
    {
        return southWall;
    }

    /**
     * Returns the eastern wall rectangle object (graphical) for this.
     *
     * @return eastWall Cell's East wall.
     * @see #eastWall
     */
    public Rectangle getEastWall()
    {
        return eastWall;
    }

    /**
     * Returns the western wall rectangle object (graphical) for this.
     *
     * @return westWall Cell's West wall.
     * @see #westWall
     */
    public Rectangle getWestWall()
    {
        return westWall;
    }
}
