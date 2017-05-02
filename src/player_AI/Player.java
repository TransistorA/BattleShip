/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2017
 *
 * Name: Joseph DiPalma, Annan Miao, and Ben Xu
 * Date: Apr 10, 2017
 * Time: 10:18:20 AM
 *
 * Project: csci205finalproject
 * Package: player_AI
 * File: Player
 * Description:
 *
 * ****************************************
 */
package player_AI;

import GUI_AI.View;
import board.Board;
import java.util.ArrayList;
import ship.Ship;
import ship.ShipType;

/**
 * The class that represent different players
 *
 * @author Ben Xu
 */
public class Player {

    // Array of the Ship objects for this Player.
    private ArrayList<Ship> myShips;

    // Index used internally to initialize the ship.
    private int initIndex;

    // Configure the ship type.
    private final int[] SHIP_CONFIG;

    // Holds the ShipType types for the Ship objects.
    private final ShipType[] TYPES;

    // Total number of ships.
    private final int TOTAL_SHIPS;

    // Board for the current Player.
    private Board myBoard;

    // Store the attack attempts from the current Player.
    private Board targetBoard;

    // Keeps track of how many ships remain for the player.
    private int shipRemain;

    private View myView;

    // Keep track if all of the ships have been added.
    private boolean shipsAdded;

    // Keep track of which ships have been added.
    private boolean carrierAdded = false;
    private boolean battleshipAdded = false;
    private boolean cruiserAdded = false;
    private boolean destroyerAdded = false;
    private boolean submarineAdded = false;

    /**
     * Constructor for the Player class.
     *
     * @author Ben Xu
     *
     * @param view view part of the MVC design pattern
     */
    public Player(View view) {
        this.initIndex = 0;
        //init constants
        this.SHIP_CONFIG = new int[]{1, 1, 1, 1, 1};
        this.TOTAL_SHIPS = 5;
        this.shipRemain = 5;

        // Add all of the ShipType types.
        this.TYPES = new ShipType[]{ShipType.CARRIER, ShipType.BATTLESHIP, ShipType.CRUISER, ShipType.SUBMARINE, ShipType.DESTROYER};

        this.myView = view;

        this.shipsAdded = false;

        this.myBoard = new Board(10);
        this.targetBoard = new Board(10);

        // Initialize the array of ships.
        this.myShips = new ArrayList<>();
        initShips();
    }

    /**
     * Initialize the ships.
     *
     * @author Ben Xu
     */
    private void initShips() {
        for (int i = 0; i < SHIP_CONFIG.length; i++) {
            for (int x = 0; x < SHIP_CONFIG[i]; x++) {
                myShips.add(new Ship(TYPES[i]));
            }
        }
    }

    /**
     * Initialize the location of the ship.
     *
     * @author Ben Xu
     *
     * @param loc the location to initialize for the ship
     */
    public void initShipLocations(int[] loc) {
        if (this.initIndex < this.myShips.size()) {
            this.myShips.get(initIndex).setLocation(loc);
        }
        this.initIndex += 1;
    }

    /**
     * Attack the enemy.
     *
     * @author Ben Xu
     *
     * @param loc location to attack on the enemy Board
     * @param enemy Player object representing the enemy
     * @return true if successful, false otherwise
     */
    public boolean attack(int[] loc, Player enemy) {
        // Update the self tracking board.
        this.updateSelf(loc);

        // Update the ships for the enemy.
        return enemy.updateShips(loc);
    }

    /**
     * Update the Board when the enemy attacks.
     *
     * @author Ben Xu
     *
     * @param loc the location where the enemy attacked
     */
    public void defend(int[] loc) {
        if (this.updateShips(loc)) {
            this.updateSelf(loc);
        }
    }

    /**
     * Update the ship where it was hit.
     *
     * @author Ben Xu
     *
     * @param loc location where the ship was hit
     * @return true if successful, false otherwise
     */
    private boolean updateShips(int[] loc) {
        boolean isHit = false;
        for (Ship s : myShips) {
            for (int i = 0; i < s.getShipLoc().size(); i++) {
                if (loc == s.getShipLoc().get(i)) {
                    s.getShipHits()[i] = 1;
                    isHit = true;
                }
            }
            if (s.isIsSunk()) {
                shipRemain -= 1;
            }
        }
        return isHit;
    }

    /**
     * Update my Board.
     *
     * @author Ben Xu
     *
     * @param loc location to update on my Board
     */
    private void updateSelf(int[] loc) {
        this.targetBoard.updateBoard(loc);
    }

    /**
     * Checks whether or not the current player has lost yet.
     *
     * @return true if the player lost, false otherwise
     */
    public boolean didILose() {
        if (this.shipRemain == 0) {
            return true;

        }
        return false;
    }

    /**
     * Determines if all of the ships have been added.
     *
     * @author Joseph DiPalma
     */
    public void shipsAddedTrue() {
        this.shipsAdded = false;
        if (this.battleshipAdded && this.carrierAdded && this.cruiserAdded && this.destroyerAdded && this.submarineAdded) {
            this.shipsAdded = true;
        }
    }

    public void addedCarrier() {
        this.carrierAdded = true;
    }

    public void addedBattleship() {
        this.battleshipAdded = true;
    }

    public void addedSubmarine() {
        this.submarineAdded = true;
    }

    public void addedDestroyer() {
        this.destroyerAdded = true;
    }

    public void addedCruiser() {
        this.cruiserAdded = true;
    }

    public View getMyView() {
        return myView;
    }

    public Board getMyBoard() {
        return myBoard;
    }

    public boolean getShipsAdded() {
        return this.shipsAdded;
    }

    public int getShipRemain() {
        return shipRemain;
    }
}
