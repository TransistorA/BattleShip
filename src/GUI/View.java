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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
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
    private RadioButton carrierBtn;
    private RadioButton battleshipBtn;
    private RadioButton cruiserBtn;
    private RadioButton submarineBtn;
    private RadioButton destroyerBtn;
    ToggleGroup shipGroup;

    private Button createShipBtn;

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

        // Switch color on my grid when clicked.
        for (int i = 0; i < this.board.length; i++) {
            for (Rectangle r : this.board[i]) {

                r.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseevent) {
                        if (r.getFill() == Paint.valueOf("BLUE")) {
                            r.setFill(Paint.valueOf("RED"));
                        }
                        else if (r.getFill() == Paint.valueOf("RED")) {
                            r.setFill(Paint.valueOf("BLUE"));
                        }

                        int[][] temp = {{2, 2}, {3, 2}, {4, 2}};
                        ArrayList<int[]> shipLoc = new ArrayList<int[]>(
                                Arrays.asList(temp));
                        Ship ship = new Ship(shipLoc, CRUISER);

                        int col = myBoard.getColumnIndex(r);
                        int row = myBoard.getRowIndex(r);
                        System.out.println(row);
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
                        if (r.getFill() == Paint.valueOf("BLUE")) {
                            r.setFill(Paint.valueOf("RED"));
                        }
                        else if (r.getFill() == Paint.valueOf("RED")) {
                            r.setFill(Paint.valueOf("BLUE"));
                        }
                        int col = opponentBoard.getColumnIndex(r);
                        int row = opponentBoard.getRowIndex(r);
                        System.out.println(row);
                    }
                });
            }
        }

        // Create the ship on the board after selecting the type
        createShipBtn = new Button("Create Ship!");
        createShipBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

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

        // Set the ship type.
        carrierBtn.setUserData(new Ship(ShipType.CARRIER));
        battleshipBtn.setUserData(new Ship(ShipType.BATTLESHIP));
        cruiserBtn.setUserData(new Ship(ShipType.CRUISER));
        submarineBtn.setUserData(new Ship(ShipType.SUBMARINE));
        destroyerBtn.setUserData(new Ship(ShipType.DESTROYER));

        carrierBtn.setSelected(true);
        rightPane.getChildren().addAll(carrierBtn, battleshipBtn, cruiserBtn,
                                       submarineBtn, destroyerBtn, createShipBtn);

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

    public Button getCreateShipBtn() {
        return createShipBtn;
    }

    public Rectangle[][] getBoard() {
        return board;
    }

    public Rectangle[][] getEnemyBoard() {
        return enemyBoard;
    }

    public void buildShip(Ship ship) {

    }

}
