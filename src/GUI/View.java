/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Joseph DiPalma, Annan Miao, and Ben Xu
* Date: Apr 10, 2017
* Time: 10:16:41 AM
*
* Project: csci205finalproject
* Package: GUI
* File: View
* Description:
*
* ****************************************
 */
package GUI;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import ship.Ship;
import ship.ShipType;
import static ship.ShipType.CRUISER;

/**
 * View part of the MVC design pattern.
 *
 * @author Joseph DiPalma
 */
public class View {

    // Create the boards.
    private Rectangle[][] board = new Rectangle[10][10];
    private Rectangle[][] enemyBoard = new Rectangle[10][10];
    private GridPane myBoard;
    private GridPane opponentBoard;

    private Model theModel;

    private BorderPane root;

    private GridPane grids;

    // The right pane for the ship selection.
    private FlowPane rightPane;

    // Set up the radio buttons for the ships.
    private RadioButton carrierBtn, battleshipBtn, cruiserBtn, submarineBtn, destroyerBtn;
    private RadioButton shipHorizontal, shipVertical;
    ToggleGroup shipGroup, shipDirection;

    /**
     * Constructor for the View class.
     *
     * @author Joseph DiPalma
     *
     * @param theModel Model object that handles all of the logic
     */
    public View(Model theModel) {
        this.theModel = theModel;

        root = new BorderPane();
        root.setPadding(new Insets(15, 15, 15, 15));
        root.setPrefHeight(1500);
        root.setPrefWidth(1500);

        grids = new GridPane();

        myBoard = new GridPane();
        // Create the board.
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Rectangle r1 = new Rectangle(100 + 30 * j, 100 + 30 * i, 30, 30);
                r1.setFill(Paint.valueOf("BLUE"));
                r1.setStroke(Paint.valueOf("BLACK"));
                board[i][j] = r1;
                myBoard.add(r1, i, j);
            }
        }

        opponentBoard = new GridPane();
        // Create the enemy board.
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Rectangle r1 = new Rectangle(500 + 30 * j, 100 + 30 * i, 30, 30);
                r1.setFill(Paint.valueOf("BLUE"));
                r1.setStroke(Paint.valueOf("BLACK"));
                enemyBoard[i][j] = r1;
                opponentBoard.add(r1, i, j);
            }
        }

        // Create or rotate ship, or destroy ship when clicking on ship area
        for (int i = 0; i < this.board.length; i++) {
            for (Rectangle r : this.board[i]) {

                r.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseevent) {

                        if (r.getFill() == Paint.valueOf("RED")) {
                            r.setFill(Paint.valueOf("BLUE"));
                        }

                        else {
                            int col = myBoard.getColumnIndex(r);
                            int row = myBoard.getRowIndex(r);

                            if (shipHorizontal.isSelected()) {
                                int[][] temp = {{col, row}, {col + 1, row}, {col + 2, row}};
                                ArrayList<int[]> shipLoc = new ArrayList<int[]>(
                                        Arrays.asList(temp));
                                Ship ship = new Ship(shipLoc, CRUISER);
                                try {
                                    buildShipMy(ship);
                                } catch (Exception e) {
                                    Alert alert = new Alert(
                                            Alert.AlertType.ERROR);
                                    alert.setTitle("Out of range!");
                                    alert.setHeaderText(
                                            "Incorrect input specified!");
                                    alert.setContentText("Out of range!");
                                    alert.show();
                                }
                            }

                            else if (shipVertical.isSelected()) {
                                int[][] temp = {{col, row}, {col, row + 1}, {col, row + 2}};
                                ArrayList<int[]> shipLoc = new ArrayList<int[]>(
                                        Arrays.asList(temp));
                                Ship ship = new Ship(shipLoc, CRUISER);
                                try {
                                    buildShipMy(ship);
                                } catch (Exception e) {
                                    Alert alert = new Alert(
                                            Alert.AlertType.ERROR);
                                    alert.setTitle("Out of range!");
                                    alert.setHeaderText(
                                            "Incorrect input specified!");
                                    alert.setContentText("Out of range!");
                                    alert.show();
                                }
                            }
                        }

                        //System.out.println(row);
                    }
                });
            }
        }

        // Switch color on the enemy grid when clicked.
        for (int i = 0; i < this.enemyBoard.length; i++) {
            for (Rectangle r : this.enemyBoard[i]) {
                r.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseevent) {
                        if (r.getFill() == Paint.valueOf("RED")) {
                            r.setFill(Paint.valueOf("BLUE"));
                        }

                        else {
                            int col = opponentBoard.getColumnIndex(r);
                            int row = opponentBoard.getRowIndex(r);

                            if (shipHorizontal.isSelected()) {
                                int[][] temp = {{col, row}, {col + 1, row}, {col + 2, row}};
                                ArrayList<int[]> shipLoc = new ArrayList<int[]>(
                                        Arrays.asList(temp));
                                Ship ship = new Ship(shipLoc, CRUISER);
                                try {
                                    buildShipEnemy(ship);
                                } catch (Exception e) {
                                    Alert alert = new Alert(
                                            Alert.AlertType.ERROR);
                                    alert.setTitle("Out of range!");
                                    alert.setHeaderText(
                                            "Incorrect input specified!");
                                    alert.setContentText("Out of range!");
                                    alert.show();
                                }
                            }

                            else if (shipVertical.isSelected()) {
                                int[][] temp = {{col, row}, {col, row + 1}, {col, row + 2}};
                                ArrayList<int[]> shipLoc = new ArrayList<int[]>(
                                        Arrays.asList(temp));
                                Ship ship = new Ship(shipLoc, CRUISER);
                                try {
                                    buildShipEnemy(ship);
                                } catch (Exception e) {
                                    Alert alert = new Alert(
                                            Alert.AlertType.ERROR);
                                    alert.setTitle("Out of range!");
                                    alert.setHeaderText(
                                            "Incorrect input specified!");
                                    alert.setContentText("Out of range!");
                                    alert.show();
                                }
                            }
                        }
                    }
                });
            }
        }

        // Add the pane for the user to select the ship.
        rightPane = new FlowPane();
        rightPane.getChildren().add(new Label("Ship options: "));
        carrierBtn = new RadioButton("Carrier");
        battleshipBtn = new RadioButton("Battleship");
        cruiserBtn = new RadioButton("Cruiser");
        submarineBtn = new RadioButton("Submarine");
        destroyerBtn = new RadioButton("Destroyer");
        shipGroup = new ToggleGroup();
        carrierBtn.setToggleGroup(shipGroup);
        battleshipBtn.setToggleGroup(shipGroup);
        cruiserBtn.setToggleGroup(shipGroup);
        submarineBtn.setToggleGroup(shipGroup);
        destroyerBtn.setToggleGroup(shipGroup);

        shipHorizontal = new RadioButton("Create Horizontal Ship");
        shipVertical = new RadioButton("Create Vertical Ship");
        shipDirection = new ToggleGroup();
        shipHorizontal.setToggleGroup(shipDirection);
        shipVertical.setToggleGroup(shipDirection);

        // Set the ship type.
        carrierBtn.setUserData(new Ship(ShipType.CARRIER));
        battleshipBtn.setUserData(new Ship(ShipType.BATTLESHIP));
        cruiserBtn.setUserData(new Ship(ShipType.CRUISER));
        submarineBtn.setUserData(new Ship(ShipType.SUBMARINE));
        destroyerBtn.setUserData(new Ship(ShipType.DESTROYER));

        carrierBtn.setSelected(true);

        shipHorizontal.setSelected(true);

        rightPane.getChildren().addAll(carrierBtn, battleshipBtn, cruiserBtn,
                                       submarineBtn, destroyerBtn,
                                       shipHorizontal,
                                       shipVertical);

        root.setRight(rightPane);

        // Add both boards to the center pane.
        grids.setMargin(opponentBoard, new Insets(5.0));
        grids.setMargin(myBoard, new Insets(5.0));
        grids.add(opponentBoard, 50, 50);
        grids.add(myBoard, 70, 50);
        grids.setPrefSize(1000, 1000);

        root.setCenter(grids);

    }

    public BorderPane getRoot() {
        return root;
    }

    public RadioButton getCarrierBtn() {
        return carrierBtn;
    }

    public RadioButton getBattleshipBtn() {
        return battleshipBtn;
    }

    public RadioButton getCruiserBtn() {
        return cruiserBtn;
    }

    public RadioButton getSubmarineBtn() {
        return submarineBtn;
    }

    public RadioButton getDestroyerBtn() {
        return destroyerBtn;
    }

    public Rectangle[][] getBoard() {
        return board;
    }

    public Rectangle[][] getEnemyBoard() {
        return enemyBoard;
    }

    public void buildShipMy(Ship ship) {
        for (int[] position : ship.getShipLoc()) {
            Rectangle r = board[position[0]][position[1]];
            myBoard.getChildren().remove(r);
            r.setFill(Paint.valueOf("RED"));
            myBoard.add(r, position[0], position[1]);
        }
    }

    public void buildShipEnemy(Ship ship) {
        for (int[] position : ship.getShipLoc()) {
            Rectangle r = enemyBoard[position[0]][position[1]];
            opponentBoard.getChildren().remove(r);
            r.setFill(Paint.valueOf("RED"));
            opponentBoard.add(r, position[0], position[1]);
        }
    }

}
