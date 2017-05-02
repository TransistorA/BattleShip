/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2017
 *
 * Name: Joseph DiPalma, Annan Miao, and Ben Xu
 * Date: Apr 10, 2017
 * Time: 10:16:50 AM
 *
 * Project: csci205finalproject
 * Package: GUI_AI
 * File: Controller
 * Description:
 *
 * ****************************************
 */
package GUI_AI;

import java.util.Random;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 * Controller part of the MVC design pattern.
 *
 * @author Joseph DiPalma
 */
public class Controller implements EventHandler<ActionEvent> {

    private Model theModel;
    private View theView1;
    private View theView2;

    private int[][] board2; // copy of the enemyBoard and differentiate ships with 0 and 1

    private int[] location = {5, 5}; // previous click location of the computere

    /**
     * Constructor for the Controller class.
     *
     * @author Joseph DiPalma
     *
     * @param theModel model part of the MVC design pattern
     * @param theView view part of the MVC design pattern
     */
    public Controller(Model theModel) {
        this.theModel = theModel;
        this.theView1 = this.theModel.getP1View();
        this.theView2 = this.theModel.getP2View();

        this.theModel.getCarrier().bind(
                this.theView1.getCarrierBtn().selectedProperty());
        this.theModel.getBattleship().bind(
                this.theView1.getBattleshipBtn().selectedProperty());
        this.theModel.getCruiser().bind(
                this.theView1.getCruiserBtn().selectedProperty());
        this.theModel.getSubmarine().bind(
                this.theView1.getSubmarineBtn().selectedProperty());
        this.theModel.getDestroyer().bind(
                this.theView1.getDestroyerBtn().selectedProperty());

        this.theModel.getAddedCarrier().bind(
                this.theView1.getCarrierBtn().selectedProperty());
        this.theModel.getAddedBattleship().bind(
                this.theView1.getBattleshipBtn().selectedProperty());
        this.theModel.getAddedCruiser().bind(
                this.theView1.getCruiserBtn().selectedProperty());
        this.theModel.getAddedSubmarine().bind(
                this.theView1.getSubmarineBtn().selectedProperty());
        this.theModel.getAddedDestroyer().bind(
                this.theView1.getDestroyerBtn().selectedProperty());

        // Check if the user is done selecting their ships.
        this.theView1.getShipSelectionDone().setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    theModel.getP1().shipsAddedTrue();
                    // Check if the user has added all of their ships.
                    if (theModel.getP1().getShipsAdded()) {
                        theModel.disableShipSelection(theModel.getP1());

                        theView1.getShipSelectionDone().setDisable(true);

                        theView1.getShipHorizontal().setDisable(true);
                        theView1.getShipVertical().setDisable(true);

                        // Check if the other user has added all of their ships.
                        theModel.getP2().shipsAddedTrue();
                        if (theModel.getP2().getShipsAdded()) {
                            theModel.startGame();
                        }
                    }
                    // The user has not added all of their ships.
                    else {
                        theView1.showShipSelectionError();
                    }
                } catch (Exception ex) {
                    theView1.showShipSelectionError();
                }
            }
        });

        this.theView1.getStartGame().setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startGame();
            }
        });

        // View 2.
        this.theModel.getCarrier().bind(
                this.theView2.getCarrierBtn().selectedProperty());
        this.theModel.getBattleship().bind(
                this.theView2.getBattleshipBtn().selectedProperty());
        this.theModel.getCruiser().bind(
                this.theView2.getCruiserBtn().selectedProperty());
        this.theModel.getSubmarine().bind(
                this.theView2.getSubmarineBtn().selectedProperty());
        this.theModel.getDestroyer().bind(
                this.theView2.getDestroyerBtn().selectedProperty());

        this.theModel.getAddedCarrier().bind(
                this.theView2.getCarrierBtn().selectedProperty());
        this.theModel.getAddedBattleship().bind(
                this.theView2.getBattleshipBtn().selectedProperty());
        this.theModel.getAddedCruiser().bind(
                this.theView2.getCruiserBtn().selectedProperty());
        this.theModel.getAddedSubmarine().bind(
                this.theView2.getSubmarineBtn().selectedProperty());
        this.theModel.getAddedDestroyer().bind(
                this.theView2.getDestroyerBtn().selectedProperty());

        // Check if the user is done selecting their ships.
        this.theView2.getShipSelectionDone().setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    theModel.getP2().shipsAddedTrue();
                    // Check if the user has added all of their ships.
                    if (theModel.getP2().getShipsAdded()) {
                        theModel.disableShipSelection(theModel.getP2());

                        theView2.getShipSelectionDone().setDisable(true);

                        theView2.getShipHorizontal().setDisable(true);
                        theView2.getShipVertical().setDisable(true);

                        // Check if the other user has added all of their ships.
                        theModel.getP1().shipsAddedTrue();
                        if (theModel.getP1().getShipsAdded()) {
                            theModel.startGame();
                        }
                    }
                    // The user has not added all of their ships.
                    else {
                        theView2.showShipSelectionError();
                    }
                } catch (Exception ex) {
                    theView2.showShipSelectionError();
                }
            }
        });
    }

    /**
     * Start the game. When user clicks on enemyBoard, computer clicks on
     * userBoard and show red if hit, show white if miss
     */
    public void startGame() {
        int length = this.theView1.getBoard().length;
        setEnemyBoard();

        for (int i = 0; i < length; i++) {
            for (Rectangle r : this.theView1.getEnemyBoard()[i]) {
                r.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseevent) {
                        int col = theView1.getOpponentBoard().getColumnIndex(r);
                        int row = theView1.getOpponentBoard().getRowIndex(r);
                        if (board2[col][row] == 1) {
                            r.setFill(Paint.valueOf("RED")); // hit sound here
                            click();
                            checkWin();
                        }
                        else {
                            r.setFill(Paint.valueOf("WHITE"));
                            click();
                            checkWin();
                        }
                    }
                });
            }
        }
    }

    /**
     * Update the board when a user clicks.
     *
     * @author Annan Miao
     */
    public void click() {
        Rectangle r;
        int col, row;

        Random random = new Random();
        int choice = random.nextInt(6 - 0 + 1) + 0; // random number between 0 and 6

        if (choice == 0) { // the next computer click must be on a ship
            do {
                col = random.nextInt(9 + 1 - 0) + 0;
                row = random.nextInt(9 + 1 - 0) + 0;
                r = theView1.getBoard()[col][row];
            } while (r.getFill() == Paint.valueOf("RED") || r.getFill() == Paint.valueOf(
                    "WHITE") || r.getFill() == Paint.valueOf("GREY"));
        }
        else { // the next computer click is random
            col = random.nextInt(9 + 1 - 0) + 0;
            row = random.nextInt(9 + 1 - 0) + 0;
            r = theView1.getBoard()[col][row];
        }

        if (r.getFill() != Paint.valueOf("GREY") && r.getFill() != Paint.valueOf(
                "WHITE")) {
            r.setFill(Paint.valueOf("RED"));
        }
        else {
            r.setFill(Paint.valueOf("WHITE"));
        }

    }

    /**
     * Clear the board where a ship was set.
     *
     * @author Annan Miao
     */
    public void clearBoard() {
        int length = this.theView1.getBoard().length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                this.theView1.getEnemyBoard()[i][j].setFill(
                        Paint.valueOf("GREY"));
            }
        }
    }

    /**
     * Sets the ships on the enemy board and then stores the ship location and
     * clears the board.
     *
     * @author Annan Miao
     */
    public void setEnemyBoard() {
        int length = theView1.getBoard().length;
        board2 = new int[length][length];

        placeShips();

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (this.theView1.getEnemyBoard()[i][j].getFill() != Paint.valueOf(
                        "GREY")) {
                    board2[i][j] = 1; // 1 represents ship area and 0 represents sea area
                }
                else {
                    board2[i][j] = 0;
                }
            }
        }
        clearBoard();
    }

    /**
     * Place the five ships on the enemy board.
     *
     * @author Annan Miao
     */
    public void placeShips() {
        buildShip(5);
        buildShip(4);
        buildShip(3);
        buildShip(3);
        buildShip(2);
    }

    /**
     * Determine if anyone won yet.
     *
     * @author Annan Miao
     */
    public void checkWin() {
        int length = this.theView1.getBoard().length;
        int count = 0, enemyCount = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (this.theView1.getEnemyBoard()[i][j].getFill() == Paint.valueOf(
                        "RED")) {
                    count += 1;
                }
                if (this.theView1.getBoard()[i][j].getFill() == Paint.valueOf(
                        "RED")) {
                    enemyCount += 1;
                }
            }
        }
        // the total number of ship area is 17
        if (count == 17) {
            theView1.showWinOrLoss("win");
        }
        if (enemyCount == 17) {
            theView1.showWinOrLoss("lose");
        }
    }

    /**
     * Create a ship of the specified size on the enemy board.
     *
     * @author Annan Miao
     *
     * @param shipSize the size of the ship to create
     */
    public void buildShip(int shipSize) {
        boolean set = false;
        while (set != true) {
            Random random = new Random();
            int col = random.nextInt(9 + 1 - 0) + 0;
            int row = random.nextInt(9 + 1 - 0) + 0;
            if (col + shipSize > 9 && row + shipSize > 9) {
                set = false;
            }
            else if (col + shipSize > 9 && row + shipSize <= 9 && checkOverlap(
                    shipSize, col, row, 0) == true) {
                for (int i = 0; i < shipSize; i++) {
                    theView1.getEnemyBoard()[col][row + i].setFill(
                            Paint.valueOf("RED"));
                }
                set = true;
            }
            else if (row + shipSize > 9 && col + shipSize <= 9 && checkOverlap(
                    shipSize, col, row, 1) == true) {
                for (int i = 0; i < shipSize; i++) {
                    theView1.getEnemyBoard()[col + i][row].setFill(
                            Paint.valueOf("RED"));
                }
                set = true;
            }
            else if (row + shipSize <= 9 && col + shipSize <= 9 && checkOverlap(
                    shipSize, col, row, 1) == true) {
                for (int i = 0; i < shipSize; i++) {
                    theView1.getEnemyBoard()[col + i][row].setFill(
                            Paint.valueOf("RED"));
                }
                set = true;
            }
        }
    }

    /**
     * Check if the ship can be built at the location
     *
     * @param shipSize the ship size
     * @param col the col index
     * @param row the row index
     * @param horizontal 1 if horizontal and 0 if vertical
     * @return true if no overlap, false if overlap
     */
    /**
     * Check if any of the ships overlap.
     *
     * @author Annan Miao
     *
     * @param shipSize the size of the ship
     * @param col the column on the board
     * @param row the row on the board
     * @param horizontal indicates whether the ship is horizontal or vertical, 1
     * if horizontal and 0 if vertical
     * @return true if no ships overlap, false otherwise
     */
    public boolean checkOverlap(int shipSize, int col, int row, int horizontal) {
        if (horizontal == 1) {
            for (int i = 0; i < shipSize; i++) {
                if (theView1.getEnemyBoard()[col + i][row].getFill() != Paint.valueOf(
                        "GREY")) {
                    return false;
                }
            }
            return true;
        }
        for (int i = 0; i < shipSize; i++) {
            if (theView1.getEnemyBoard()[col][row + i].getFill() != Paint.valueOf(
                    "GREY")) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void handle(ActionEvent event) {
    }
}
