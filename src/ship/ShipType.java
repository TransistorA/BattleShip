/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Joseph DiPalma, Annan Miao, and Ben Xu
* Date: Apr 12, 2017
* Time: 12:58:40 AM
*
* Project: csci205finalproject
* Package: ship
* File: shipType
* Description:
*
* ****************************************
 */
package ship;

public enum ShipType {
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
