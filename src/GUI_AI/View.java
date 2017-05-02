/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2017
 *
 * Name: Joseph DiPalma, Annan Miao, and Ben Xu
 * Date: Apr 10, 2017
 * Time: 10:16:41 AM
 *
 * Project: csci205finalproject
 * Package: GUI_AI
 * File: View
 * Description:
 *
 * ****************************************
 */
package GUI_AI;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import ship.Ship;
import ship.ShipType;

/**
 * View part of the MVC design pattern.
 *
 * @author Joseph DiPalma
 */
public class View {

    // Create the boards.
    public Rectangle[][] board = new Rectangle[10][10];
    private Rectangle[][] enemyBoard = new Rectangle[10][10];
    public GridPane myBoard;
    private GridPane opponentBoard;

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

    // Set up the buttons for selecting the orientation of the ship.
    private RadioButton shipHorizontal;
    private RadioButton shipVertical;
    ToggleGroup orientationGroup;

    // Set up the button for attacking.
    private Button attackBtn;

    // Set up the button for when the user is done selecting their ships.
    private Button shipSelectionDone;

    private Button startGame, clearBoards;

    // Set up the timer for the player.
    private AnimationTimer timer;
    private Integer i = 60;
    private Text timerText;
    private Timeline timeline;
    private EventHandler onFinished;
    private KeyFrame keyFrame;
    private Duration duration;

    /**
     * Constructor for the View class.
     *
     * @author Joseph DiPalma
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
     * button-background-image</a>
     *
     * @see
     * <a href="http://www.java2s.com/Tutorials/Java/JavaFX/1010__JavaFX_Timeline_Animation.htm">
     * http://www.java2s.com/Tutorials/Java/JavaFX/1010__JavaFX_Timeline_Animation.htm</a>
     */
    public View() {
        root = new BorderPane();
        root.setPadding(new Insets(15, 15, 15, 15));
        root.setPrefHeight(1500);
        root.setPrefWidth(1500);

        grids = new GridPane();

        // Create my board.
        myBoard = new GridPane();
        createBoard();

        // Create the enemy board.
        opponentBoard = new GridPane();
        createEnemyBoard();

        // Create the buttons so the user can decide whether to place the ship
        // with horizontal or vertical orientation.
        shipHorizontal = new RadioButton("Horizontal");
        shipVertical = new RadioButton("Vertical");
        shipHorizontal.setSelected(true);

        // Hold the orientation buttons in a toggle group.
        orientationGroup = new ToggleGroup();
        shipHorizontal.setToggleGroup(orientationGroup);
        shipVertical.setToggleGroup(orientationGroup);

        bottomPane = new FlowPane(Orientation.HORIZONTAL);
        bottomPane.setAlignment(Pos.BASELINE_CENTER);

        Font font = new Font("Times New Roman", 24);

        attackBtn = new Button("Attack");
        attackBtn.setFont(font);
        attackBtn.setDisable(true);

        shipSelectionDone = new Button("Confirm ship selection");
        shipSelectionDone.setFont(font);

        // start game vs computer (without networking)
        startGame = new Button("Start Game vs Computer");
        startGame.setFont(font);

        // clear the board and restart the game
        clearBoards = new Button("Clear Boards");
        clearBoards.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clearBoards();
            }
        });

        timeline = new Timeline();
        timeline.setCycleCount(60);

        timerText = new Text(i.toString());
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                timerText.setText(i.toString());
                i--;
            }
        };
        // When the timer reaches 0.
        onFinished = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timeline.stop();

                // Reset the timer.
                i = 60;
            }
        };

        duration = Duration.millis(60000);
        keyFrame = new KeyFrame(duration, onFinished);

        timeline.getKeyFrames().add(keyFrame);

        bottomPane.getChildren().add(attackBtn);
        bottomPane.getChildren().add(shipSelectionDone);
        bottomPane.getChildren().add(timerText);
        bottomPane.getChildren().add(startGame);
        bottomPane.setHgap(10);
        root.setBottom(bottomPane);

        // Add the pane for the user to select the ship.
        rightPane = new FlowPane(Orientation.VERTICAL);
        rightPane.setAlignment(Pos.CENTER_RIGHT);
        rightPane.setVgap(10);

        // Create all of the labels and buttons related to the ships.
        createShips();

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

        // Add the label for the orientation.
        rightPane.getChildren().add(new Label(
                "Select the orientation of the ship:"));

        // Add the buttons to select horizontal or vertical ship orientation.
        rightPane.getChildren().add(shipHorizontal);
        rightPane.getChildren().add(shipVertical);
        rightPane.getChildren().add(clearBoards);

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

    /**
     * Create all of the Label, ToggleButton, and ToggleGroup objects for the
     * ships.
     *
     * @author Joseph DiPalma
     */
    public void createShips() {
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
    }

    /**
     * Create the board for the enemy.
     *
     * @author Joseph DiPalma
     */
    public void createEnemyBoard() {
        // Create the enemy board.
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Rectangle r1 = new Rectangle(500 + 30 * j, 100 + 30 * i, 75,
                                             75);
                r1.setFill(Paint.valueOf("GREY"));
                r1.setStroke(Paint.valueOf("BLACK"));
                enemyBoard[i][j] = r1;
                opponentBoard.add(r1, i, j);
            }
        }
    }

    /**
     * Create my board.
     *
     * @author Joseph DiPalma
     */
    public void createBoard() {
        // Create the board.
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Rectangle r1 = new Rectangle(100 + 30 * j, 100 + 30 * i, 75,
                                             75);
                r1.setFill(Paint.valueOf("GREY"));
                r1.setStroke(Paint.valueOf("BLACK"));
                board[i][j] = r1;
                myBoard.add(r1, i, j);
            }
        }
    }

    /**
     * Display a victory message for the victor or a loss message for the loser.
     *
     * @author Joseph DiPalma
     *
     * @param winOrLoss the string containing the message to display to the
     * player
     */
    public void showWinOrLoss(String winOrLoss) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText("You " + winOrLoss + "!");
        alert.show();
    }

    /**
     * Display an error message when the user has not added all of their ships.
     *
     * @author Joseph DiPalma
     */
    public void showShipSelectionError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ship Error");
        alert.setHeaderText("Ship Selection Error");
        alert.setContentText(
                "Add the rest of your ships before continuing!");
        alert.show();
    }

    /**
     * Clear the two boards and restart the game.
     *
     * @author Annan Miao
     */
    public void clearBoards() {
        int length = board.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                board[i][j].setFill(
                        Paint.valueOf("GREY"));
                enemyBoard[i][j].setFill(
                        Paint.valueOf("GREY"));
            }
        }
        carrierBtn.setDisable(false);
        battleshipBtn.setDisable(false);
        cruiserBtn.setDisable(false);
        submarineBtn.setDisable(false);
        destroyerBtn.setDisable(false);
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

    public GridPane getMyBoard() {
        return myBoard;
    }

    public GridPane getOpponentBoard() {
        return opponentBoard;
    }

    public Button getAttackBtn() {
        return attackBtn;
    }

    public Button getShipSelectionDone() {
        return shipSelectionDone;
    }

    public Button getStartGame() {
        return startGame;
    }

    public RadioButton getShipHorizontal() {
        return shipHorizontal;
    }

    public RadioButton getShipVertical() {
        return shipVertical;
    }

    public AnimationTimer getTimer() {
        return timer;
    }

    public Timeline getTimeline() {
        return timeline;
    }
}
