/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze;

/**
 * MoveHandler specification that all InputHandlers (regardless of input type)
 * must meet in order for the character object to correctly move. This class
 * defines the specification that the character object depends on and one that
 * all input types must meet.
 */
public interface MoveHandler
{

    /**
     * Returns a boolean that represents movement in the down direction.
     *
     */
    public boolean moveDown();

    /**
     * Returns a boolean that represents movement in the up direction.
     *
     */
    public boolean moveUp();

    /**
     * Returns a boolean that represents movement in the left direction.
     *
     */
    public boolean moveLeft();

    /**
     * Returns a boolean that represents movement in the right direction.
     *
     */
    public boolean moveRight();
}
