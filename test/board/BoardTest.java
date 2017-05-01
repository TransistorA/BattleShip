/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2017
 *
 * Name: Joseph DiPalma, Annan Miao, and Ben Xu
 * Date: May 1, 2017
 * Time: 3:58:33 PM
 *
 * Project: csci205finalproject
 * Package: board
 * File: BoardTest
 * Description:
 *
 * ****************************************
 */
package board;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the Board class.
 *
 * @author Joseph DiPalma
 */
public class BoardTest {

    // Create the Board.
    Board board;

    public BoardTest() {
    }

    @Before
    public void setUp() {
        board = new Board(10);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of updateBoard method, of class Board.
     *
     * @author Joseph DiPalma
     */
    @Test
    public void testUpdateBoard() {
        System.out.println("updateBoard");

        int[] loc = {0, 0};
        // Test the update board at the origin.
        board.updateBoard(loc);
        int exp = 1;
        int actual = board.getBoard()[0][0];
        assertEquals(exp, actual);

        // Test the update bpard at a location other than the origin.
        int[] loc2 = {8, 7};
        board.updateBoard(loc2);
        int actual2 = board.getBoard()[8][7];
        assertEquals(exp, actual2);
    }

}
