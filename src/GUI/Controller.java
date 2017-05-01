/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Joseph DiPalma, Annan Miao, and Ben Xu
* Date: Apr 10, 2017
* Time: 10:16:50 AM
*
* Project: csci205finalproject
* Package: GUI
* File: Controller
* Description:
*
* ****************************************
 */
package GUI;

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
     * Event handler for the GUI.
     *
     * @author Joseph DiPalma
     *
     * @param event event from the GUI to respond to
     *
     * This code was based off of information found at the below website.
     *
     * @see
     * <a href="http://stackoverflow.com/questions/25409044/javafx-multiple-buttons-to-same-handler">
     * http://stackoverflow.com/questions/25409044/javafx-multiple-buttons-to-same-handler</a>
     */
    @Override
    public void handle(ActionEvent event) {

        // This is all things that haven't been put in View yet but will be
        // in the future.
        /*
        try {
            if (event.getSource() == rotateCWButton) {
                // Call the rotateCW method in Ship.
            }

            else if (event.getSource() == rotateCCWButton) {
                // Call the rotateCCW method in Ship.
            }
        }
        catch (RotationException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Rotation failed!");
            alert.setHeaderText("Rotation failed!");
            alert.setContentText("Cannot rotate the specified ship!");
            alert.show();
        }
         */
        // Check if the user wants to finish selecting their ships.
    }

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
                            r.setFill(Paint.valueOf("DARKRED")); // hit sound here
                            click();
                            checkWin();
                        }
                        else {
                            r.setFill(Paint.valueOf("LIGHTGREY"));
                            click();
                            checkWin();
                        }
                    }
                });
            }
        }
    }

    /**
     * Use DARKRED for clicked ship, grey for unclicked area,lightgrey for
     * clicked area other colors for unclicked ship
     *
     *
     * @return updated click location(col,row)
     */
    public void click() {
        Rectangle r;
        int col, row;

        Random random = new Random();
        int choice = random.nextInt(5 - 0 + 1) + 0; // random number between 0 and 5

        if (choice == 0) { // the next computer click must be on a ship
            do {
                col = random.nextInt(9 + 1 - 0) + 0;
                row = random.nextInt(9 + 1 - 0) + 0;
                r = theView1.getBoard()[col][row];
            } while (r.getFill() == Paint.valueOf("DARKRED") || r.getFill() == Paint.valueOf(
                    "LIGHTGREY") || r.getFill() == Paint.valueOf("GREY"));
        }
        else { // the next computer click is random
            col = random.nextInt(9 + 1 - 0) + 0;
            row = random.nextInt(9 + 1 - 0) + 0;
            r = theView1.getBoard()[col][row];
        }

        if (r.getFill() != Paint.valueOf("GREY") && r.getFill() != Paint.valueOf(
                "LIGHTGREY")) {
            r.setFill(Paint.valueOf("DARKRED"));
        }
        else {
            r.setFill(Paint.valueOf("LIGHTGREY"));
        }

    }

    // clear the board where we set ship
    public void clearBoard() {
        int length = this.theView1.getBoard().length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                this.theView1.getEnemyBoard()[i][j].setFill(
                        Paint.valueOf("GREY"));
            }
        }
    }

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
     * Place 5 different ships on enemy board
     */
    public void placeShips() {
        buildShip(5);
        buildShip(4);
        buildShip(3);
        buildShip(3);
        buildShip(2);
    }

    public void checkWin() {
        int length = this.theView1.getBoard().length;
        int count = 0, enemyCount = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (this.theView1.getEnemyBoard()[i][j].getFill() == Paint.valueOf(
                        "DARKRED")) {
                    count += 1;
                }
                if (this.theView1.getBoard()[i][j].getFill() == Paint.valueOf(
                        "DARKRED")) {
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
}
