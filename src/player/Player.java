/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Joseph DiPalma, Annan Miao, and Ben Xu
* Date: Apr 10, 2017
* Time: 10:18:20 AM
*
* Project: csci205finalproject
* Package: player
* File: Player
* Description:
*
* ****************************************
 */
package player;

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

    //array of ships
    private ArrayList<Ship> myShips;
    //index for initalize ship, internal use only
    private int initIndex;

    //ship type configuration
    private final int[] shipConfig;

    //type array:Carrier, Battleship, Cruiser, Submarine, Destroyer
    private final ShipType[] types;

    //totalShip count
    private final int totalShips;

    //current player board
    private Board myBoard;

    //current target board that stores the attack attempts
    private Board targetBoard;

    //count of the unsunk ships for current player
    private int shipRemain;

    //setup the configuration of the player ships
    public Player() {
        this.initIndex = 0;
        //init constants
        this.shipConfig = new int[]{1, 1, 1, 1, 1};
        this.totalShips = 17;
        //Carrier, Battleship, Cruiser, Submarine, Destroyer
        this.types = new ShipType[]{ShipType.CARRIER, ShipType.BATTLESHIP, ShipType.CRUISER, ShipType.SUBMARINE, ShipType.DESTROYER};

        //init ship array
        this.myShips = new ArrayList<>();
        initShips();
    }

    private void initShips() {
        for (int i = 0; i < shipConfig.length; i++) {
            for (int x = 0; x < shipConfig[i]; x++) {
                myShips.add(new Ship(types[i]));
            }
        }

    }

    public void initShipLocations(int[] loc) {
        if (this.initIndex < this.myShips.size()) {
            this.myShips.get(initIndex).setLocation(loc);
        }
        this.initIndex += 1;
    }

    public boolean attack(int[] loc, Player enemy) {
        //update self tracking board
        this.updateSelf(loc);
        //update ships for enemy
        return enemy.updateShips(loc);
    }

    public void defend(int[] loc) {
        if (this.updateShips(loc)) {
            this.updateSelf(loc);
        }
    }

    public int getShipRemain() {
        return shipRemain;
    }

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

//    private void updateEnemy(int[] loc) {
//        this.myBoard.updateBoard(loc);
//    }
    private void updateSelf(int[] loc) {
        this.targetBoard.updateBoard(loc);
    }

}
