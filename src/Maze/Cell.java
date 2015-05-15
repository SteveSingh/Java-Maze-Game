package Maze;

/**
 * Cell class which describes a cell object that contains a north, east, south,
 * and west wall as well as information that describes whether it is a cell that
 * is visible, visited, or it's type (border, time point, end point, etc...).
 *
 */
public class Cell
{

    /**
     * Boolean which represents whether a hypothetical/conceptual north wall
     * exists for this cell object.
     *
     */
    private boolean northWall;
    /**
     * Boolean which represents whether a hypothetical/conceptual east wall
     * exists for this cell object.
     *
     */
    private boolean eastWall;
    /**
     * Boolean which represents whether a hypothetical/conceptual south wall
     * exists for this cell object.
     *
     */
    private boolean southWall;
    /**
     * Boolean which represents whether a hypothetical/conceptual west wall
     * exists for this cell object.
     *
     */
    private boolean westWall;
    /**
     * Boolean flag which represents whether this hypothetical/conceptual cell
     * object has been "visited" or not. Required for the maze generation
     * algorithm (DFS).
     *
     * @see GenMaze
     *
     */
    private boolean visitedFlag;
    /**
     * Boolean flag which describes the whether this hypothetical/conceptual
     * cell object is a time point. The object can either be a normal cell
     * (tile), a time point, end point or start point.
     *
     */
    private boolean timePoint;
    /**
     * Boolean flag which describes the whether this hypothetical/conceptual
     * cell object is a start point. The object can either be a normal cell
     * (tile), a time point, end point or start point.
     *
     */
    private boolean startPoint;
    /**
     * Boolean flag which describes the whether this hypothetical/conceptual
     * cell object is a end point. The object can either be a normal cell
     * (tile), a time point, end point or start point.
     *
     */
    private boolean endPoint;
    /**
     * Boolean flag which represents whether this hypothetical/conceptual cell
     * object is a visible cell or not, if it is invisible then it will not be
     * drawn to the screen.
     *
     */
    private boolean visibile;
    /**
     * Boolean flag which represents whether this hypothetical/conceptual cell
     * object is a border cell or not.
     *
     */
    private boolean border;
    /**
     * Represents the row value of this cell object.
     *
     */
    private int positionX;
    /**
     * Represents the column value of this cell object.
     *
     */
    private int positionY;
    /**
     * Represents the cell number based on the dimensions of the maze (i.e., 2
     * by 2 maze has 1 to 4 cell numbers).
     *
     */
    private int cellNumber;
    /**
     * Represents the physical x coordinate of this cell object on the maze
     * (Graphical maze).
     *
     */
    private int coordX;
    /**
     * Represents the physical y coordinate of this cell object on the maze
     * (Graphical maze).
     *
     */
    private int coordY;

    /**
     * Initializes this cell object where each wall in the cell is existent.
     * thus a completely closed cell is created. The cell is initially visible,
     * unvisited and does not have a specific cell type (time point, end point,
     * etc...).
     *
     * @param cNum Cell number assigned by GenMaze, dependent on the maze (grid)
     * size.
     */
    public Cell(int cNum)
    {
        //Initialize all walls as true -- all walls corresponding to this cell
        //exist (are up)
        this.northWall = this.eastWall = this.southWall = this.westWall = true;
        //Initialize the cell so that it has not been visited yet.
        this.visitedFlag = false;
        this.timePoint = false;
        this.visibile = true;
        this.border = false;
        this.startPoint = false;
        this.endPoint = false;

        //These represent the row and column
        this.positionX = 0;
        this.positionY = 0;
        //Cell number
        this.cellNumber = cNum;
        //These are actual coordinates on the xy plane of the graphics.
        this.coordX = 0;
        this.coordY = 0;
    }

    /**
     * Returns a boolean that represents whether this is a time point cell.
     *
     * @return timePoint Cell type
     * @see #timePoint
     */
    public boolean isTimePoint()
    {
        return timePoint;
    }

    /**
     * Sets a boolean that represents whether this is a time point cell.
     *
     * @param timePoint Sets timePoint to timePoint
     * @see #timePoint
     */
    public void setTimePoint(boolean timePoint)
    {
        this.timePoint = timePoint;
    }

    /**
     * Returns the physical x coordinate on the completed maze.
     *
     * @return coordX x coordinate value.
     * @see #coordX
     */
    public int getCoordX()
    {
        return coordX;
    }

    /**
     * Sets the physical x coordinate on the completed maze.
     *
     * @param coordX x coordinate value.
     * @see #coordX
     */
    public void setCoordX(int coordX)
    {
        this.coordX = coordX;
    }

    /**
     * Returns the physical y coordinate on the completed maze.
     *
     * @return coordY y coordinate value.
     * #see #coordY
     */
    public int getCoordY()
    {
        return coordY;
    }

    /**
     * Sets the physical y coordinate on the completed maze.
     *
     * @param coordY y coordinate value.
     * #see #coordY
     */
    public void setCoordY(int coordY)
    {
        this.coordY = coordY;
    }

    /**
     * Returns a boolean that represents whether this is a border cell.
     *
     * @return border Cell type
     * @see #border
     */
    public boolean isBorder()
    {
        return border;
    }

    /**
     * Sets a boolean that represents whether this is a border cell.
     *
     * @param border Sets border to border.
     * @see #border
     */
    public void setBorder(boolean border)
    {
        this.border = border;
    }

    /**
     * Returns the row number determined by GenMaze.
     *
     * @return positionX Row number
     * @see #positionX
     */
    public int getPositionX()
    {
        return positionX;
    }

    /**
     * Sets the row number determined by GenMaze.
     *
     * @param positionX Row number
     * @see #positionX
     */
    public void setPositionX(int positionX)
    {
        this.positionX = positionX;
    }

    /**
     * Returns the column number determined by GenMaze.
     *
     * @return positionY Column number
     * @see #positionY
     */
    public int getPositionY()
    {
        return positionY;
    }

    /**
     * Sets the column number determined by GenMaze.
     *
     * @param positionY Column number
     * @see #positionY
     */
    public void setPositionY(int positionY)
    {
        this.positionY = positionY;
    }

    /**
     * Returns a boolean that represents whether this is a start point cell.
     *
     * @return startPoint Cell type
     * @see #startPoint
     */
    public boolean isStartPoint()
    {
        return startPoint;
    }

    /**
     * Sets a boolean that represents whether this is a start point cell.
     *
     * @param startPoint Cell type
     * @see #startPoint
     */
    public void setStartPoint(boolean startPoint)
    {
        this.startPoint = startPoint;
    }

    /**
     * Returns a boolean that represents whether this is a end point cell.
     *
     * @return endPoint Cell type
     * @see #endPoint
     */
    public boolean isEndPoint()
    {
        return endPoint;
    }

    /**
     * Sets a boolean that represents whether this is a end point cell.
     *
     * @param endPoint Cell type
     * @see #endPoint
     */
    public void setEndPoint(boolean endPoint)
    {
        this.endPoint = endPoint;
    }

    /**
     * Returns a boolean that describes the visibility of the cell (true ==
     * visible).
     *
     * @return visible Visibility status.
     * @see #visibile
     */
    public boolean isVisibile()
    {
        return visibile;
    }

    /**
     * Set a boolean that describes the visibility of the cell (true ==
     * visible).
     *
     * @param visibility Visibility status.
     * @see #visibile
     */
    public void setVisibility(boolean visibility)
    {
        this.visibile = visibility;
    }

    /**
     * Returns a boolean that represents whether the cell has a north wall or
     * not.
     *
     * @return northWall North wall status.
     * @see #northWall
     */
    public boolean isNorthWall()
    {
        return northWall;
    }

    /**
     * Sets a boolean that represents whether the cell has a north wall or not.
     *
     * @param northWall North wall status.
     * @see #northWall
     */
    public void setNorthWall(boolean northWall)
    {
        this.northWall = northWall;
    }

    /**
     * Returns a boolean that represents whether the cell has an east wall or
     * not.
     *
     * @return eastWall East wall status.
     * @see #eastWall
     */
    public boolean isEastWall()
    {
        return eastWall;
    }

    /**
     * Sets a boolean that represents whether the cell has an east wall or not.
     *
     * @param eastWall East wall status.
     * @see #eastWall
     */
    public void setEastWall(boolean eastWall)
    {
        this.eastWall = eastWall;
    }

    /**
     * Returns a boolean that represents whether the cell has a south wall or
     * not.
     *
     * @return southWall South wall status.
     * @see #southWall
     */
    public boolean isSouthWall()
    {
        return southWall;
    }

    /**
     * Sets a boolean that represents whether the cell has a south wall or not.
     *
     * @param southWall South wall status.
     * @see #southWall
     */
    public void setSouthWall(boolean southWall)
    {
        this.southWall = southWall;
    }

    /**
     * Returns a boolean that represents whether the cell has a west wall or
     * not.
     *
     * @return westWall West wall status.
     * @see #westWall
     */
    public boolean isWestWall()
    {
        return westWall;
    }

    /**
     * Sets a boolean that represents whether the cell has a west wall or not.
     *
     * @param westWall West wall status.
     * @see #westWall
     */
    public void setWestWall(boolean westWall)
    {
        this.westWall = westWall;
    }

    /**
     * Returns a boolean that represents whether this cell has been visited or
     * not.
     *
     * @return visitedFlag Visited status.
     * @see #visitedFlag
     */
    public boolean isVisitedFlag()
    {
        return visitedFlag;
    }

    /**
     * Sets a boolean that represents whether this cell has been visited or not.
     *
     * @param visitedFlag Visited status.
     * @see #visitedFlag
     */
    public void setVisitedFlag(boolean visitedFlag)
    {
        this.visitedFlag = visitedFlag;
    }

    /**
     * Returns the cell number determined by GenMaze.
     *
     * @return cellNumber Cell number determined by grid size.
     *  @see #cellNumber
     */
    public int getCellNumber()
    {
        return cellNumber;
    }

    /**
     * Sets the cell number determined by GenMaze.
     *
     * @param cellNumber Cell number determined by grid size.
     *  @see #cellNumber
     */
    public void setCellNumber(int cellNumber)
    {
        this.cellNumber = cellNumber;
    }
}
