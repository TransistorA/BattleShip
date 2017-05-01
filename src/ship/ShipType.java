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

/**
 * Enumerated type representing the type of ship.
 *
 * @author Joseph DiPalma
 */
public enum ShipType {
    CARRIER(5, "PURPLE"), BATTLESHIP(4, "BLUE"), CRUISER(3, "GREEN"), SUBMARINE(
            3,
            "ORANGE"), DESTROYER(
            2, "YELLOW");

    public int size;

    public String color;

    /**
     * Initialize the enumerated type ShipType.
     *
     * @author Joseph DiPalma
     *
     * @param size the size of the ship represented by the ShipType
     * @param color the color of the ship represented by ShipType
     */
    ShipType(int size, String color) {
        this.size = size;
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

}
