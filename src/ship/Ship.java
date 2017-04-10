/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Joseph DiPalma, Annan Miao, and Ben Xu
* Date: Apr 8, 2017
* Time: 12:32:47 PM
*
* Project: csci205finalproject
* Package: ship
* File: Ship
* Description:
*
* ****************************************
 */
package ship;

/**
 * Interface for all of the Battleship ships.
 *
 * @author Joseph DiPalma
 */
public interface Ship {

    /**
     * Update the ship when it has been hit.
     *
     * @author Joseph DiPalma
     *
     * @param hitTarget the location to record the hit on the ship
     */
    public void updateShip(int[] hitTarget);

    /**
     * Check if the ship has been sunk.
     *
     * @author Joseph DiPalma
     *
     * @return true if the ship has been sunk, false otherwise
     */
    public boolean checkIfSunk();

    /**
     * Rotate the ship clockwise when the user is initially placing it on the
     * board.
     *
     * @author Joseph DiPalma
     */
    public void rotateCW();

    /**
     * Rotate the ship counterclockwise when the user is initially placing it on
     * the board.
     *
     * @author Joseph DiPalma
     */
    public void rotateCCW();

}
