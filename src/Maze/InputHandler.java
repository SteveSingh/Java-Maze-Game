package Maze;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * This class handles the input events, mainly, key press and release events.
 * The input handler must meet the specification outlined by the MoveHandler,
 * thus it implements the MoveHandler interface.
 */
public class InputHandler implements KeyListener, MoveHandler
{

    /**
     *
     * Boolean that represents upward movement which is triggered upon
     * KeyPressed or KeyReleased of the up arrow key.
     */
    private boolean moveUp;
    /**
     *
     * Boolean that represents downward movement which is triggered upon
     * KeyPressed or KeyReleased of the down arrow key.
     */
    private boolean moveDown;
    /**
     *
     * Boolean that represents horizontal movement towards the left, which is
     * triggered upon KeyPressed or KeyReleased of the left arrow key.
     */
    private boolean moveLeft;
    /**
     *
     * Boolean that represents horizontal movement towards the right, which is
     * triggered upon KeyPressed or KeyReleased of the right arrow key.
     */
    private boolean moveRight;

    //Constructor starts here
    /**
     * The input handler does not require any initializations. It must meet the
     * specification outlined by MoveHandler though.
     */
    public InputHandler()
    {
    }

    //KeyListener methods, only the keyPressed and keyReleased methods are used.
    /**
     * Overridden KeyListener method, not implemented.
     *
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    //Sets direction to true upon specific key press event.
    /**
     * KeyPressed method sets movement booleans to true depending on the key
     * pressed.
     *
     * @param e Key press event handler.
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT)
        {
            setMoveLeft(true);
        }
        if (keyCode == KeyEvent.VK_RIGHT)
        {
            setMoveRight(true);
        }
        if (keyCode == KeyEvent.VK_UP)
        {
            setMoveUp(true);
        }
        if (keyCode == KeyEvent.VK_DOWN)
        {
            setMoveDown(true);
        }
    }
    //Sets direction to false upon specific key release event.

    /**
     * KeyReleased method sets movement booleans to false depending on the key
     * that was initially pressed then released.
     *
     * @param e Key release event handler.
     */
    @Override
    public void keyReleased(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT)
        {
            setMoveLeft(false);
        }
        if (keyCode == KeyEvent.VK_RIGHT)
        {
            setMoveRight(false);
        }
        if (keyCode == KeyEvent.VK_UP)
        {
            setMoveUp(false);
        }
        if (keyCode == KeyEvent.VK_DOWN)
        {
            setMoveDown(false);
        }
    }

    /**
     * Sets boolean moveUp to true if key press event was an up arrow. Sets to
     * false if key release occurs.
     *
     * @param moveUp Direction is up.
     */
    public void setMoveUp(boolean moveUp)
    {
        this.moveUp = moveUp;
    }

    /**
     * Sets boolean moveDown to true if key press event was a down arrow. Sets
     * to false if key release occurs.
     *
     * @param moveDown Direction is down.
     */
    public void setMoveDown(boolean moveDown)
    {
        this.moveDown = moveDown;
    }

    /**
     * Sets boolean moveLeft to true if key press event was a left arrow. Sets
     * to false if key release occurs.
     *
     * @param moveLeft Direction is left.
     */
    public void setMoveLeft(boolean moveLeft)
    {
        this.moveLeft = moveLeft;
    }

    /**
     * Sets boolean moveRight to true if key press event was a right arrow. Sets
     * to false if key release occurs.
     *
     * @param moveRight Direction is right.
     */
    public void setMoveRight(boolean moveRight)
    {
        this.moveRight = moveRight;
    }
    //Getters for booleans

    /**
     * Returns a boolean that represents movement in the down direction.
     * Overrides MoveHandler.
     *
     * @return moveDown Movement in vertical (down) direction.
     */
    @Override
    public boolean moveDown()
    {
        return this.moveDown;
    }

    /**
     * Returns a boolean that represents movement in the up direction. Overrides
     * MoveHandler.
     *
     * @return moveUp Movement in vertical (up) direction.
     */
    @Override
    public boolean moveUp()
    {
        return this.moveUp;
    }

    /**
     * Returns a boolean that represents movement in the left direction.
     * Overrides MoveHandler.
     *
     * @return moveLeft Movement in horizontal (left) direction.
     */
    @Override
    public boolean moveLeft()
    {
        return this.moveLeft;
    }

    /**
     * Returns a boolean that represents movement in the right direction.
     * Overrides MoveHandler.
     *
     * @return moveRight Movement in horizontal (right) direction.
     */
    @Override
    public boolean moveRight()
    {
        return this.moveRight;
    }
}