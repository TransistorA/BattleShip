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
     * Constructor for the Ship class.
     *
     * @author Joseph DiPalma
     *
     * @param type the type of ship
     */
    public Ship(ShipType type) {
        this.shipType = type;
        this.isSunk = false;
        this.shipHits = new int[this.shipType.getSize()];
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
     * Rotates the position of the ship clockwise.
     *
     * @author Joseph DiPalma
     */
    public void rotateCW() {
        // Find the x and y coordinates of the pivot point.
        int pivotX = this.shipLoc.get(0)[0];
        int pivotY = this.shipLoc.get(0)[1];
        int[] pivot = this.shipLoc.get(0);

        // I'll assume the x and y bounds are 10 for now.
        // This means the x and y coordinates will range from 0 -> 9.
        int xBound = 10;
        int yBound = 10;

        // Temporary ArrayList to hold the updated values.
        ArrayList<int[]> tempLoc = new ArrayList<int[]>();

        // The ship is horizontal.
        if (this.orientation == Orientation.HORIZONTAL) {
            // Add the pivot to the location.
            tempLoc.add(pivot);

            // Loop over all of the values except the pivot.
            for (int i = 1; i < this.shipLoc.size(); i++) {
                // Hold the new point.
                int[] newLoc = {pivotX, pivotY + i};

                // Add the new point to the ship location.
                tempLoc.add(newLoc);
            }

            // Make sure I haven't gone past the bounds.
            // Check the last value in order to determine if I went over the bounds.
            int[] lastPair = tempLoc.get(tempLoc.size() - 1);

            if (lastPair[1] >= yBound) {
                // Find the difference between the bounds.
                int diff = lastPair[1] - yBound + 1;

                // Loop over the ship location to fix the bounds.
                for (int j = 0; j < tempLoc.size(); j++) {
                    // Get the old value of the y coordinate.
                    int oldY = tempLoc.get(j)[1];

                    // Get the last ordered pair and update it.
                    int[] tempPair = tempLoc.get(j);
                    tempPair[1] = oldY - diff;

                    // Update the location of the ship.
                    tempLoc.set(j, tempPair);
                }
            }
            // Change the orientation of the ship.
            this.orientation = Orientation.VERTICAL;
        }
        // The ship is vertical.
        else {

            // Loop over all of the values .
            for (int i = this.shipLoc.size() - 1; i >= 0; i--) {
                // Hold the new point.
                int[] newLoc = {pivotX - i, pivotY};

                // Add the new point to the ship location.
                tempLoc.add(newLoc);
            }

            // Make sure I haven't gone past the bounds.
            // Check the first value in order to determine if I went over the bounds.
            int[] firstPair = tempLoc.get(0);

            if (firstPair[0] < 0) {
                // Find the difference between the bounds.
                int diff = -firstPair[0];

                // Loop over the ship location to fix the bounds.
                for (int j = 0; j < tempLoc.size(); j++) {
                    // Get the old value of the x coordinate.
                    int oldX = tempLoc.get(j)[0];

                    // Get the old ordered pair and update it.
                    int[] tempPair = tempLoc.get(j);
                    tempPair[0] = oldX + diff;

                    // Update the location of the ship.
                    tempLoc.set(j, tempPair);
                }
            }
            // Change the orientation of the ship.
            this.orientation = Orientation.HORIZONTAL;
        }
        // Update the ship's position.
        this.shipLoc = tempLoc;
    }

    /**
     * Rotates the position of the ship counterclockwise.
     *
     * @author Joseph DiPalma
     */
    public void rotateCCW() {
        // Find the x and y coordinates of the pivot point.
        int pivotX = this.shipLoc.get(this.shipLoc.size() - 1)[0];
        int pivotY = this.shipLoc.get(this.shipLoc.size() - 1)[1];
        int[] pivot = this.shipLoc.get(this.shipLoc.size() - 1);

        // I'll assume the x and y bounds are 10 for now.
        // This means the x and y coordinates will range from 0 -> 9.
        int xBound = 10;
        int yBound = 10;

        // Temporary ArrayList to hold the updated values.
        ArrayList<int[]> tempLoc = new ArrayList<int[]>();

        // The ship is horizontal.
        if (this.orientation == Orientation.HORIZONTAL) {
            // Add the pivot to the location.
            tempLoc.add(pivot);

            // Loop over all of the values.
            for (int i = 0; i < this.shipLoc.size() - 1; i++) {
                // Hold the new point.
                int[] newLoc = {pivotX, pivotY + (i + 1)};

                // Add the new point to the ship location.
                tempLoc.add(newLoc);
            }
            // Make sure I haven't gone past the bounds.
            // Check the last value to make sure I didn't go over the bounds.
            int[] lastPair = tempLoc.get(tempLoc.size() - 1);

            if (lastPair[1] > yBound) {
                // Find the difference between the bounds.
                int diff = lastPair[1] - yBound + 1;

                // Loop over the ship location to fix the bounds.
                for (int j = 0; j < tempLoc.size(); j++) {
                    // Get the old value of the y coordinate.
                    int oldY = tempLoc.get(j)[1];

                    // Get the last ordered pair and update it.
                    int[] tempPair = tempLoc.get(j);
                    tempPair[1] = oldY - diff;

                    // Update the location of the ship.
                    tempLoc.set(j, tempPair);
                }
            }
            // Change the orientation of the ship.
            this.orientation = Orientation.VERTICAL;
        }
        // The ship is vertical.
        else {
            // Loop over all of the values except the pivot.
            for (int i = this.shipLoc.size() - 1; i > 0; i--) {
                // Hold the new point.
                int[] newLoc = {pivotX - i, pivotY};

                // Add the new point to the ship location.
                tempLoc.add(newLoc);
            }
            // Add the pivot to the location.
            tempLoc.add(pivot);

            // Make sure I haven't gone past the bounds.
            // Check the first value to make sure I didn't go over the bounds.
            int[] firstPair = tempLoc.get(0);

            if (firstPair[0] < 0) {
                // Find the difference between the bounds.
                int diff = -firstPair[0];

                // Loop over the ship location to fix the bounds.
                for (int j = 0; j < tempLoc.size(); j++) {
                    // Get the old value of the x coordinate.
                    int oldX = tempLoc.get(j)[0];

                    // Get the old ordered pair and update it.
                    int[] tempPair = tempLoc.get(j);
                    tempPair[0] = oldX + diff;

                    // Update the location of the ship.
                    tempLoc.set(j, tempPair);
                }
            }
            // Change the orientation of the ship.
            this.orientation = Orientation.HORIZONTAL;
        }
        // Update the ship's position.
        this.shipLoc = tempLoc;
    }

    /**
     * set the position of the ship with default horizontal position
     *
     * @param startLoc - an order pair [x,y]
     */
    public void setLocation(int[] startLoc) {
        this.shipLoc.add(startLoc);
        for (int i = 0; i < this.shipType.getSize() - 1; i++) {
            int[] t = {startLoc[0] + 1, startLoc[1]};
            this.shipLoc.add(Arrays.copyOf(t, t.length));
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

    public ShipType getShipType() {
        return shipType;
    }

    public ArrayList<int[]> getShipLoc() {
        return shipLoc;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public boolean isIsSunk() {
        return isSunk;
    }

    public int[] getShipHits() {
        return shipHits;
    }
}
