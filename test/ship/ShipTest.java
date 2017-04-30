/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2017
 *
 * Name: Joseph DiPalma, Annan Miao, and Ben Xu
 * Date: Apr 14, 2017
 * Time: 6:25:15 AM
 *
 * Project: csci205finalproject
 * Package: ship
 * File: ShipTest
 * Description:
 *
 * ****************************************
 */
package ship;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.geometry.Orientation;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static ship.ShipType.CRUISER;

/**
 * Tests the Ship class.
 *
 * @author Joseph DiPalma
 */
public class ShipTest {

    public ShipTest() {
    }

    /**
     * Test of rotateCW method, of class Ship.
     *
     * @author Joseph DiPalma
     */
    @Test
    public void testRotateCW() {
        System.out.println("rotateCW");

        // When the ship location is in the middle of the board with no problems
        // and is horizontal.
        int[][] temp = {{2, 2}, {3, 2}, {4, 2}};

        ArrayList<int[]> shipLoc = new ArrayList<int[]>(Arrays.asList(temp));

        Ship ship = new Ship(shipLoc, CRUISER);

        int[][] exp = {{2, 2}, {2, 3}, {2, 4}};
        ArrayList<int[]> expResult = new ArrayList<int[]>(Arrays.asList(exp));

        ship.rotateCW();

        ArrayList<int[]> actual = ship.getShipLoc();

        // Loop over to check if they are equal.
        for (int i = 0; i < actual.size(); i++) {
            System.out.println(
                    "Expected: " + Arrays.toString(expResult.get(i)) + "Actual" + Arrays.toString(
                    actual.get(
                            i)));
            assertArrayEquals(expResult.get(i), actual.get(i));
        }

        // Check to make sure the ship changed to vertical.
        assertEquals(Orientation.VERTICAL, ship.getOrientation());

        // When the ship is on the edge of the board and could go over and
        // is horizontal.
        int[][] temp2 = {{2, 9}, {3, 9}, {4, 9}};

        ArrayList<int[]> shipLoc2 = new ArrayList<int[]>(Arrays.asList(temp2));

        Ship ship2 = new Ship(shipLoc2, CRUISER);

        int[][] exp2 = {{2, 7}, {2, 8}, {2, 9}};
        ArrayList<int[]> expResult2 = new ArrayList<int[]>(Arrays.asList(exp2));

        ship2.rotateCW();

        ArrayList<int[]> actual2 = ship2.getShipLoc();

        // Loop over to check if they are equal.
        for (int j = 0; j < actual2.size(); j++) {
            System.out.println(
                    "Expected 2: " + Arrays.toString(expResult2.get(j)) + "Actual 2" + Arrays.toString(
                    actual2.get(
                            j)));
            assertArrayEquals(expResult2.get(j), actual2.get(j));
        }

        // Check to make sure the ship changed to vertical.
        assertEquals(Orientation.VERTICAL, ship2.getOrientation());

        // When the ship is in the middle of the board with no problems and
        // is vertical.
        int[][] temp3 = {{7, 3}, {7, 4}, {7, 5}};

        ArrayList<int[]> shipLoc3 = new ArrayList<int[]>(Arrays.asList(temp3));

        Ship ship3 = new Ship(shipLoc3, CRUISER);
        ship3.setOrientation(Orientation.VERTICAL);

        int[][] exp3 = {{5, 3}, {6, 3}, {7, 3}};
        ArrayList<int[]> expResult3 = new ArrayList<int[]>(Arrays.asList(exp3));

        ship3.rotateCW();

        ArrayList<int[]> actual3 = ship3.getShipLoc();

        // Loop over to check if they are equal.
        for (int k = 0; k < actual3.size(); k++) {
            System.out.println(
                    "Expected 3: " + Arrays.toString(expResult3.get(k)) + "Actual 3" + Arrays.toString(
                    actual3.get(
                            k)));
            assertArrayEquals(expResult3.get(k), actual3.get(k));
        }

        // Check to make sure the ship changed to horizontal.
        assertEquals(Orientation.HORIZONTAL, ship3.getOrientation());

        // When the ship is on the edge of the board and could go over and
        // is vertical.
        int[][] temp4 = {{0, 2}, {0, 3}, {0, 4}};

        ArrayList<int[]> shipLoc4 = new ArrayList<int[]>(Arrays.asList(temp4));

        Ship ship4 = new Ship(shipLoc4, CRUISER);
        ship4.setOrientation(Orientation.VERTICAL);

        int[][] exp4 = {{0, 2}, {1, 2}, {2, 2}};
        ArrayList<int[]> expResult4 = new ArrayList<int[]>(Arrays.asList(exp4));

        ship4.rotateCW();

        ArrayList<int[]> actual4 = ship4.getShipLoc();

        // Loop over to check if they are equal.
        for (int h = 0; h < actual4.size(); h++) {
            System.out.println(
                    "Expected 4: " + Arrays.toString(expResult4.get(h)) + "Actual 4" + Arrays.toString(
                    actual4.get(
                            h)));
            assertArrayEquals(expResult4.get(h), actual4.get(h));
        }

        // Check to make sure the ship changed to horizontal.
        assertEquals(Orientation.HORIZONTAL, ship4.getOrientation());
    }

    /**
     * Test of rotateCCW method, of class Ship.
     *
     * @author Joseph DiPalma
     */
    @Test
    public void testRotateCCW() {
        System.out.println("rotateCCW");

        // When the ship location is in the middle of the board with no problems
        // and is horizontal.
        int[][] temp = {{3, 6}, {4, 6}, {5, 6}};

        ArrayList<int[]> shipLoc = new ArrayList<int[]>(Arrays.asList(temp));

        Ship ship = new Ship(shipLoc, CRUISER);

        int[][] exp = {{5, 6}, {5, 7}, {5, 8}};
        ArrayList<int[]> expResult = new ArrayList<int[]>(Arrays.asList(exp));

        ship.rotateCCW();

        ArrayList<int[]> actual = ship.getShipLoc();

        // Loop over to check if they are equal.
        for (int i = 0; i < actual.size(); i++) {
            System.out.println(
                    "Expected: " + Arrays.toString(expResult.get(i)) + "Actual" + Arrays.toString(
                    actual.get(
                            i)));
            assertArrayEquals(expResult.get(i), actual.get(i));
        }

        // Check to make sure the ship changed to vertical.
        assertEquals(Orientation.VERTICAL, ship.getOrientation());

        // When the ship is on the edge of the board and could go over and
        // is horizontal.
        int[][] temp2 = {{7, 9}, {8, 9}, {9, 9}};

        ArrayList<int[]> shipLoc2 = new ArrayList<int[]>(Arrays.asList(temp2));

        Ship ship2 = new Ship(shipLoc2, CRUISER);

        int[][] exp2 = {{9, 7}, {9, 8}, {9, 9}};
        ArrayList<int[]> expResult2 = new ArrayList<int[]>(Arrays.asList(exp2));

        ship2.rotateCCW();

        ArrayList<int[]> actual2 = ship2.getShipLoc();

        // Loop over to check if they are equal.
        for (int j = 0; j < actual2.size(); j++) {
            System.out.println(
                    "Expected 2: " + Arrays.toString(expResult2.get(j)) + "Actual 2" + Arrays.toString(
                    actual2.get(
                            j)));
            assertArrayEquals(expResult2.get(j), actual2.get(j));
        }

        // Check to make sure the ship changed to vertical.
        assertEquals(Orientation.VERTICAL, ship2.getOrientation());

        // When the ship is in the middle of the board with no problems and
        // is vertical.
        int[][] temp3 = {{6, 1}, {6, 2}, {6, 3}};

        ArrayList<int[]> shipLoc3 = new ArrayList<int[]>(Arrays.asList(temp3));

        Ship ship3 = new Ship(shipLoc3, CRUISER);
        ship3.setOrientation(Orientation.VERTICAL);

        int[][] exp3 = {{4, 3}, {5, 3}, {6, 3}};
        ArrayList<int[]> expResult3 = new ArrayList<int[]>(Arrays.asList(exp3));

        ship3.rotateCCW();

        ArrayList<int[]> actual3 = ship3.getShipLoc();

        // Loop over to check if they are equal.
        for (int k = 0; k < actual3.size(); k++) {
            System.out.println(
                    "Expected 3: " + Arrays.toString(expResult3.get(k)) + "Actual 3" + Arrays.toString(
                    actual3.get(
                            k)));
            assertArrayEquals(expResult3.get(k), actual3.get(k));
        }

        // Check to make sure the ship changed to horizontal.
        assertEquals(Orientation.HORIZONTAL, ship3.getOrientation());

        // When the ship is on the edge of the board and could go over and
        // is vertical.
        int[][] temp4 = {{0, 7}, {0, 8}, {0, 9}};

        ArrayList<int[]> shipLoc4 = new ArrayList<int[]>(Arrays.asList(temp4));

        Ship ship4 = new Ship(shipLoc4, CRUISER);
        ship4.setOrientation(Orientation.VERTICAL);

        int[][] exp4 = {{0, 9}, {1, 9}, {2, 9}};
        ArrayList<int[]> expResult4 = new ArrayList<int[]>(Arrays.asList(exp4));

        ship4.rotateCCW();

        ArrayList<int[]> actual4 = ship4.getShipLoc();

        // Loop over to check if they are equal.
        for (int h = 0; h < actual4.size(); h++) {
            System.out.println(
                    "Expected 4: " + Arrays.toString(expResult4.get(h)) + "Actual 4" + Arrays.toString(
                    actual4.get(
                            h)));
            assertArrayEquals(expResult4.get(h), actual4.get(h));
        }

        // Check to make sure the ship changed to horizontal.
        assertEquals(Orientation.HORIZONTAL, ship4.getOrientation());
    }
}
