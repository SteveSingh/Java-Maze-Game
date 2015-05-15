package Maze;

import java.util.Random;

/**
 * Recursively generates a maze by "destroying" or hiding walls in each Cell
 * object in a two dimensional array. The generated maze is only "conceptual" in
 * that it does not have any graphical components. The start point, end point
 * and time point cells can be placed at any location since the method of
 * generating is based on the Depth First Search which ensures that there is
 * always one unique path between two points.
 */
public class GenMaze
{

    /**
     * A two dimensional array that represents a grid, where each block in the
     * grid is a cell.
     */
    private Cell grid[][];
    /**
     * The two dimensional array's grid dimensions (N by N).
     */
    private int gridDimensions;
    /**
     * Panel width used to determine cell positions.
     */
    private int pW;
    /**
     * Panel height used to determine cell positions.
     */
    private int pH;

    /**
     * Initializes a grid (2D Array) of size dim+2 and calls methods to
     * initialize each cell object in the array, a pair of random numbers are
     * generated to produce a random timePoint cell and the startPoint cell is
     * initialized as well (startPoint at 1,1). Using this startPoint, the maze
     * is randomly generated and the endPoint is set to the N by Nth cell.
     *
     * @param dim Maze dimensions
     * @param pWidth Panel width
     * @param pHeight Panel height
     */
    public GenMaze(int dim, int pWidth, int pHeight)
    {
        this.gridDimensions = dim;
        this.pW = pWidth;
        this.pH = pHeight;
        initializeGrid();

        //generate random start point
        int tpX, tpY;
        tpX = generateNum(dim);
        tpY = generateNum(dim);

        //Set the flag startPoint to true for the cell at spX, spY.
        grid[tpX][tpY].setTimePoint(true);
        grid[1][1].setStartPoint(true);
        generateMaze(1, 1);

        //Set end point
        grid[gridDimensions][gridDimensions].setEndPoint(true);
    }

    /**
     * Initializes the two dimensional array of cell objects by determine their
     * physical x and y coordinates, the visibility, row and column numbers as
     * well as the cell numbers that each cell holds. The method initializes the
     * two dimensional array in a manner that ensures that the grid is N+2
     * larger than the required grid. This ensures that there is a border of
     * cells 1 cell thick around the actual grid (N by N). These border cells
     * are initialized to visited and invisible, ensuring that the maze
     * generating algorithm does not accidentally cross into this region.
     */
    private void initializeGrid()
    {
        //Make grid +2 larger so that the border of the actual grid behaves as a
        //visited region for random starting points
        //Set border cells to true; x axis borders first, then y axis

        int count = 0;
        int dx = 0;
        int dy = 0;
        int posX, posY;
        posX = pW;
        posY = pH;

        this.grid = new Cell[this.gridDimensions + 2][this.gridDimensions + 2];

        for (int y = 0; y < this.gridDimensions + 2; y++)
        {
            for (int x = 0; x < this.gridDimensions + 2; x++)
            {
                this.grid[x][y] = new Cell(count);
                this.grid[x][y].setPositionX(x);
                this.grid[x][y].setPositionY(y);
                count++;
            }
        }

        count = 0;
        //Set the cell numbers.
        for (int y = 0; y < this.gridDimensions + 2; y++)
        {
            for (int x = 0; x < this.gridDimensions + 2; x++)
            {
                this.grid[x][y] = new Cell(count);
                this.grid[x][y].setPositionX(x);
                this.grid[x][y].setPositionY(y);
                count++;
            }
        }

        //Initialize and calculate the actual (graphics) coordinates.
        for (int y = 1; y <= this.gridDimensions; y++)
        {
            for (int x = 1; x <= this.gridDimensions; x++)
            {
                this.grid[x][y].setCoordX(pW + dx);
                this.grid[x][y].setCoordY(pH + dy);
                dx += 40;
            }
            dx = 0;
            dy += 40;
        }
        for (int i = 0; i < this.gridDimensions + 2; i++)
        {
            //All border cells have been visited
            this.grid[i][0].setVisitedFlag(true);
            this.grid[i][this.gridDimensions + 1].setVisitedFlag(true);
            //Set the visibility to false, these cells don't actually exist.
            this.grid[i][0].setVisibility(false);
            this.grid[i][this.gridDimensions + 1].setVisibility(false);
            //Set walls/cells as BORDERS
            this.grid[i][0].setBorder(true);
            this.grid[i][this.gridDimensions + 1].setBorder(true);
        }
        for (int j = 0; j < this.gridDimensions + 2; j++)
        {
            //All border cells have been visited
            this.grid[0][j].setVisitedFlag(true);
            this.grid[this.gridDimensions + 1][j].setVisitedFlag(true);
            //Set the visibility to false, these cells don't actually exist.
            this.grid[0][j].setVisibility(false);
            this.grid[this.gridDimensions + 1][j].setVisibility(false);
            //Set walls/cells as BORDERS
            this.grid[0][j].setBorder(true);
            this.grid[this.gridDimensions + 1][j].setBorder(true);
        }

    }

    /**
     * Hides the cell (sets visibility to false) of the cell located at row x
     * and column y.
     *
     * @param x Row
     * @param y Column
     */
    public void hideCell(int x, int y)
    {
        this.grid[x][y].setVisibility(false);
    }

    /**
     * Returns the full size of the two dimensional grid (N+2).
     *
     * @return grid.length Length of the overall grid (2D Array)
     * @see #grid
     */
    public int size()
    {
        //Returns the full (visible and invisible) grid size.
        return this.grid.length;
    }

    /**
     * Returns the cell object located at row x and column y.
     *
     * @param x Row
     * @param y Column
     * @return grid[x][y] Two dimensional grid containing cell objects.
     * @see #grid
     */
    public Cell getGrid(int x, int y)
    {
        return grid[x][y];
    }

    //returns the visible maze grid size (i.e. N by N maze where N = gridDimensions)
    /**
     * Returns only the visible portion of the grid size (N), which is
     * essentially the maze dimensions.
     *
     * @return gridDimensions Maze dimension integer (N)
     * @see #gridDimensions
     */
    public int getGridDimensions()
    {
        return gridDimensions;
    }

    /**
     * Returns a random integer number based on the maze size argument.
     *
     * @param n Maze size obtained from GamePanel.
     * @return random int
     */
    public static int generateNum(int n)
    {
        return (int) (new Random().nextInt(n));
    }

    /**
     * Generates a maze by starting at the unvisited cell located at row x and
     * column y. The algorithm has four possible directions to travel when all
     * walls are existing, it has a 25% chance of picking either of the
     * directions randomly, once it has picked a direction and ensured it is
     * unvisited (based on random number generator and visited flag), it makes a
     * recursive call to the cell next to it. In order to traverse to an
     * unvisited cell, one of the boolean flags representing the existence of a
     * wall must be set to false, thus, destroying that specific wall. Once
     * recursion reaches its deepest point, all cells are visited and the
     * recursive calls terminate and recurse back out.
     *
     * @param x Row
     * @param y Column
     */
    private void generateMaze(int x, int y)
    {
        //set the initial cell visited flag to visited (true).
        grid[x][y].setVisitedFlag(true);

        //while there are unvisited cell blocks, randomly pick a cell and visit it, then recurse.
        while (!grid[x][y + 1].isVisitedFlag() || !grid[x + 1][y].isVisitedFlag() || !grid[x][y - 1].isVisitedFlag() || !grid[x - 1][y].isVisitedFlag())
        {
            while (true)
            {
                double randomize = Math.random() * (1);
                if (randomize < 0.25 && !grid[x][y + 1].isVisitedFlag())
                {
                    grid[x][y].setSouthWall(false);
                    grid[x][y + 1].setNorthWall(false);
                    generateMaze(x, y + 1);
                    break;

                } else if (randomize >= 0.25 && randomize < 0.50 && !grid[x + 1][y].isVisitedFlag())
                {
                    grid[x][y].setEastWall(false);
                    grid[x + 1][y].setWestWall(false);

                    generateMaze(x + 1, y);
                    break;

                } else if (randomize >= 0.5 && randomize < 0.75 && !grid[x][y - 1].isVisitedFlag())
                {
                    grid[x][y].setNorthWall(false);
                    grid[x][y - 1].setSouthWall(false);
                    generateMaze(x, y - 1);
                    break;

                } else if (randomize >= 0.75 && randomize < 1.00 && !grid[x - 1][y].isVisitedFlag())
                {
                    grid[x][y].setWestWall(false);
                    grid[x - 1][y].setEastWall(false);
                    generateMaze(x - 1, y);

                    break;

                }
            }
        }

    }
}
