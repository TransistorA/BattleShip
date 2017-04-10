/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2017
 *
 * Name: Joseph DiPalma, Annan Miao, and Ben Xu
 * Date: Apr 8, 2017
 * Time: 1:06:12 PM
 *
 * Project: csci205finalproject
 * Package: ship
 * File: Ship
 * Description:
 *
 * ****************************************
 */
package ship;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.geometry.Orientation;

/**
 * Enumerated type representing the type of the ship.
 *
 * @author Joseph DiPalma
 */
enum ShipType {
    CARRIER(5), BATTLESHIP(4), CRUISER(3), SUBMARINE(3), DESTROYER(2);

    private int size;

    /**
     * Initialize the enumerated type ShipType.
     *
     * @author Joseph DiPalma
     *
     * @param size the size of the ship represented by the ShipType
     */
    ShipType(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}

/**
 * Ship in Battleship game.
 *
 * @author Joseph DiPalma
 */
public class Ship {

    // Type of ship.
    private ShipType shipType;

    // Orientation of the ship (horizontal or vertical).
    private Orientation orientation = Orientation.HORIZONTAL;

    // The status of the ship (floating or sunk).
    private boolean isSunk;

    // The locations where the ship has been hit (this is an array of 0's and
    // the 0's change to 1's where the ship has been hit).
    private int[] shipHits;

    // The location of the ship on the board.
    private ArrayList<int[]> shipLoc;

    // The number of times the ship has been hit.
    private int numHits;

    /**
     * Constructor for the Ship class.
     *
     * @author Joseph DiPalma
     *
     * @param shipLoc location of the ship
     * @param shipType type of the ship
     */
    public Ship(ArrayList<int[]> shipLoc, ShipType shipType) {
        this.shipType = shipType;
        this.isSunk = false;
        this.shipHits = new int[this.shipType.getSize()];
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
    public void updateShip(int[] hitTarget) {
        // Loop over the entire ship to add the hit target.
        for (int i = 0; i < this.shipType.getSize(); i++) {
            if (Arrays.equals(this.shipLoc.get(i), hitTarget)) {
                this.shipHits[i] = 1;
                this.numHits++;
                break;
            }
        }

        // Check if the ship has been sunk.
        if (this.numHits == this.shipType.getSize()) {
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
    public boolean checkIfSunk() {
        return isSunk;
    }

    public void rotateCW() {

    }

    public void rotateCCW() {

    }
}
