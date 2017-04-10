/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Joseph DiPalma, Annan Miao, and Ben Xu
* Date: Apr 8, 2017
* Time: 1:07:30 PM
*
* Project: csci205finalproject
* Package: ship
* File: Cruiser
* Description:
*
* ****************************************
 */
package ship;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Cruiser in Battleship game.
 *
 * @author Joseph DiPalma
 */
public class Cruiser implements Ship {

    // Length of the ship.
    int size;

    // Orientation of the ship (horizontal or vertical).
    String orientation;

    // The status of the ship (floating or sunk).
    boolean isSunk;

    // The locations where the ship has been hit (this is an array of 0's and
    // the 0's change to 1's where the ship has been hit).
    int[] shipHits;

    // The location of the ship on the board.
    ArrayList<int[]> shipLoc;

    // The number of times the ship has been hit.
    int numHits;

    /**
     * Initialize the Cruiser type of Ship.
     *
     * @author Joseph DiPalma
     *
     * @param orientation whether the ship should be horizontal or vertical
     * @param shipLoc the location of the ship on the board, it is an ArrayList
     * of ordered pairs
     */
    public Cruiser(String orientation, ArrayList<int[]> shipLoc) {
        this.size = 3;
        this.orientation = orientation;
        this.isSunk = false;
        this.shipHits = new int[size];
        this.shipLoc = shipLoc;
        this.numHits = 0;
    }

    /**
     * Update the ship when it has been hit.
     *
     * @author Joseph DiPalma
     *
     * @param hitTarget the location where the ship was hit
     */
    @Override
    public void updateShip(int[] hitTarget) {
        // Loop over the entire ship to add the hit target.
        for (int i = 0; i < this.size; i++) {
            if (Arrays.equals(this.shipLoc.get(i), hitTarget)) {
                this.shipHits[i] = 1;
                this.numHits++;
                break;
            }
        }

        // Check if the ship has been sunk.
        if (this.numHits == this.size) {
            this.isSunk = true;
        }
    }

    /**
     * Finds the status of the ship.
     *
     * @author Joseph DiPalma
     *
     * @return true if the ship is sunk, false otherwise
     */
    @Override
    public boolean checkIfSunk() {
        return isSunk;
    }

    @Override
    public void rotateCW() {

    }

    @Override
    public void rotateCCW() {

    }

}
