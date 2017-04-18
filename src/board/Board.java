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
 * @author computer
 */
public class Board {

    //the array represent the board
    private int[][] board;

    //the size of the board
    private int size;

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
     * update the board after an attack
     *
     * @param loc - size 2 array provide the coordinate of the location
     */
    public void updateBoard(int[] loc) {
        this.board[loc[0]][loc[1]] = 1;
    }

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
