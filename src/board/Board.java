/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Joseph DiPalma, Annan Miao, and Ben Xu
* Date: Apr 10, 2017
* Time: 10:21:16 AM
*
* Project: csci205finalproject
* Package: board
* File: Board
* Description:
*
* ****************************************
 */
package board;

import java.util.Arrays;
import player.Player;

/**
 * The representation of the Board object
 *
 * @author Ben Xu
 */
public class Board {

    // The array representing the board.
    private int[][] board;

    // The size of the square board.
    private int size;

    /**
     * Constructor for the Board class.
     *
     * @author Ben Xu
     *
     * @param size size of the square board
     */
    public Board(int size) {
        this.size = size;
        this.board = new int[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                this.board[x][y] = 0;
            }
        }
    }

    /**
     * Update the board after an attack.
     *
     * @author Ben Xu
     *
     * @param loc - array of size 2 representing an ordered pair of the attack
     * location
     */
    public void updateBoard(int[] loc) {
        this.board[loc[0]][loc[1]] = 1;
    }

    /**
     * Finds the String representation of the Board.
     *
     * @author Ben Xu
     *
     * @return the String representation of the Board
     */
    @Override
    public String toString() {
        String strBoard = "";
        for (int row = 0; row < size; row++) {
            strBoard += Arrays.toString(this.board[row]) + "\n";
        }
        return strBoard;
    }

    public static void main(String[] argus) {
        Board testBoard = new Board(10);
        System.out.println(testBoard.toString());
        int[] loc = {1, 2};
        testBoard.updateBoard(loc);
        System.out.println(testBoard.toString());

        Player p1 = new Player();
        Player p2 = new Player();

        //ship loc
        int[] initLoc1 = {1, 2};
        int[] initLoc2 = {2, 2};
        int[] initLoc3 = {3, 2};

    }

}
