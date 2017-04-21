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

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import ship.Ship;
import ship.ShipType;

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
    private ToggleButton carrierBtn;
    private ToggleButton battleshipBtn;
    private ToggleButton cruiserBtn;
    private ToggleButton submarineBtn;
    private ToggleButton destroyerBtn;
    ToggleGroup shipGroup;

    // Set up the buttons for rotating the ships.
    private FlowPane bottomPane;
    private Button rotateCWBtn;
    private Button rotateCCWBtn;

    /**
     * Constructor for the View class.
     *
     * @author Joseph DiPalma
     *
     * @param theModel Model object that handles all of the logic
     *
     * The images used for the rotate buttons were found at the below website.
     *
     * @see
     * <a href="http://stackoverflow.com/questions/27909652/android-rotate-an-
     * image-clockwise-and-anticlockwise-with-two-simple-buttons">
     * http://stackoverflow.com/questions/27909652/android-rotate-an-image-
     * clockwise-and-anticlockwise-with-two-simple-buttons</a>
     *
     * The code to add the rotate images to the buttons was based off of code
     * found at the below websites.
     *
     * @see
     * <a href="http://stackoverflow.com/questions/29984228/javafx-button-background-image">
     * http://stackoverflow.com/questions/29984228/javafx-button-background-image<\a>
     *
     * @see
     * <a href="https://www.quora.com/How-do-I-set-size-of-a-image-inside-
     * button-in-javafx-Is-it-possible-to-do-this-in-css">https://www.quora.com/
     * How-do-I-set-size-of-a-image-inside-button-in-javafx-Is-it-possible-
     * to-do-this-in-css</a>
     *
     * @see
     * <a href="http://stackoverflow.com/questions/29984228/javafx-button-
     * background-image">http://stackoverflow.com/questions/29984228/javafx-
     * button-background-image<\a>
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
                Rectangle r1 = new Rectangle(100 + 30 * j, 100 + 30 * i, 75,
                                             75);
                r1.setFill(Paint.valueOf("GREY"));
                r1.setStroke(Paint.valueOf("BLACK"));
                board[i][j] = r1;
                myBoard.add(r1, i + 50, j + 50);
            }
        }

        opponentBoard = new GridPane();
        // Create the enemy board.
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Rectangle r1 = new Rectangle(500 + 30 * j, 100 + 30 * i, 75,
                                             75);
                r1.setFill(Paint.valueOf("GREY"));
                r1.setStroke(Paint.valueOf("BLACK"));
                enemyBoard[i][j] = r1;
                opponentBoard.add(r1, i + 50, j + 50);
            }
        }

        // Add the buttons for rotating CW and CCW.
        bottomPane = new FlowPane(Orientation.HORIZONTAL);
        bottomPane.setAlignment(Pos.BASELINE_CENTER);
        rotateCWBtn = new Button("Rotate ship clockwise");
        rotateCCWBtn = new Button("Rotate ship counterclockwise");

        // Add the images to the rotate buttons.
        Image cwImage = new Image(
                getClass().getResource("/GUI/cwbtn.png").toExternalForm());
        ImageView cwImageView = new ImageView(cwImage);
        rotateCWBtn.setGraphic(cwImageView);
        Image ccwImage = new Image(
                getClass().getResource("/GUI/ccwbtn.png").toExternalForm());
        ImageView ccwImageView = new ImageView(ccwImage);
        rotateCCWBtn.setGraphic(ccwImageView);

        bottomPane.getChildren().add(new Label("Rotate ship: "));
        bottomPane.getChildren().add(rotateCWBtn);
        bottomPane.getChildren().add(rotateCCWBtn);
        bottomPane.setHgap(10);
        root.setBottom(bottomPane);

        // Add the pane for the user to select the ship.
        rightPane = new FlowPane(Orientation.VERTICAL);
        rightPane.setAlignment(Pos.CENTER_RIGHT);
        rightPane.setVgap(10);

        rightPane.getChildren().add(new Label("Ship options: "));
        carrierBtn = new ToggleButton("Carrier", new Rectangle(10.0, 10.0,
                                                               Paint.valueOf(
                                                                       ShipType.CARRIER.getColor())));
        battleshipBtn = new ToggleButton("Battleship", new Rectangle(10.0, 10.0,
                                                                     Paint.valueOf(
                                                                             ShipType.BATTLESHIP.getColor())));
        cruiserBtn = new ToggleButton("Cruiser", new Rectangle(10.0, 10.0,
                                                               Paint.valueOf(
                                                                       ShipType.CRUISER.getColor())));
        submarineBtn = new ToggleButton("Submarine", new Rectangle(10.0, 10.0,
                                                                   Paint.valueOf(
                                                                           ShipType.SUBMARINE.getColor())));
        destroyerBtn = new ToggleButton("Destroyer", new Rectangle(10.0, 10.0,
                                                                   Paint.valueOf(
                                                                           ShipType.DESTROYER.getColor())));
        shipGroup = new ToggleGroup();
        carrierBtn.setToggleGroup(shipGroup);
        battleshipBtn.setToggleGroup(shipGroup);
        cruiserBtn.setToggleGroup(shipGroup);
        submarineBtn.setToggleGroup(shipGroup);
        destroyerBtn.setToggleGroup(shipGroup);

        // Switch color on my grid when clicked.
        for (int i = 0; i < this.board.length; i++) {
            for (Rectangle r : this.board[i]) {

                r.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseevent) {
                        if (r.getFill() == Paint.valueOf("GREY")) {
                            // Find the ship that was clicked.
                            if (carrierBtn.isSelected()) {
                                r.setFill(Paint.valueOf(
                                        ShipType.CARRIER.getColor()));
                            }

                            else if (battleshipBtn.isSelected()) {
                                r.setFill(Paint.valueOf(
                                        ShipType.BATTLESHIP.getColor()));
                            }

                            else if (cruiserBtn.isSelected()) {
                                r.setFill(Paint.valueOf(
                                        ShipType.CRUISER.getColor()));
                            }

                            else if (submarineBtn.isSelected()) {
                                r.setFill(Paint.valueOf(
                                        ShipType.SUBMARINE.getColor()));
                            }

                            else if (destroyerBtn.isSelected()) {
                                r.setFill(Paint.valueOf(
                                        ShipType.DESTROYER.getColor()));
                            }

                        }
                        else if (r.getFill() != Paint.valueOf("GREY")) {
                            r.setFill(Paint.valueOf("GREY"));
                        }
                        int col = grids.getColumnIndex(r);
                        System.out.println(col);
                    }
                });
            }
        }

        // Set the ship type.
        carrierBtn.setUserData(new Ship(ShipType.CARRIER));
        battleshipBtn.setUserData(new Ship(ShipType.BATTLESHIP));
        cruiserBtn.setUserData(new Ship(ShipType.CRUISER));
        submarineBtn.setUserData(new Ship(ShipType.SUBMARINE));
        destroyerBtn.setUserData(new Ship(ShipType.DESTROYER));

        carrierBtn.setSelected(true);
        rightPane.getChildren().add(carrierBtn);
        rightPane.getChildren().add(battleshipBtn);
        rightPane.getChildren().add(cruiserBtn);
        rightPane.getChildren().add(submarineBtn);
        rightPane.getChildren().add(destroyerBtn);

        root.setRight(rightPane);

        // Add both boards to the center pane.
        grids.setAlignment(Pos.CENTER);
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

    public ToggleButton getCarrierBtn() {
        return carrierBtn;
    }

    public ToggleButton getBattleshipBtn() {
        return battleshipBtn;
    }

    public ToggleButton getCruiserBtn() {
        return cruiserBtn;
    }

    public ToggleButton getSubmarineBtn() {
        return submarineBtn;
    }

    public ToggleButton getDestroyerBtn() {
        return destroyerBtn;
    }

    public Rectangle[][] getBoard() {
        return board;
    }

    public Rectangle[][] getEnemyBoard() {
        return enemyBoard;
    }

}
