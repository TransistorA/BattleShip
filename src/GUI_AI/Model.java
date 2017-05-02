/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2017
 *
 * Name: Joseph DiPalma, Annan Miao, and Ben Xu
 * Date: Apr 10, 2017
 * Time: 10:16:34 AM
 *
 * Project: csci205finalproject
 * Package: GUI_AI
 * File: Model
 * Description:
 *
 * ****************************************
 */
package GUI_AI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import player_AI.Player;
import ship.Ship;
import ship.ShipType;

/**
 * Model part of the MVC design pattern.
 *
 * @author Joseph DiPalma
 */
public class Model {

    // Figure out which Ship the user wants to add.
    private SimpleBooleanProperty carrier;
    private SimpleBooleanProperty battleship;
    private SimpleBooleanProperty cruiser;
    private SimpleBooleanProperty submarine;
    private SimpleBooleanProperty destroyer;

    // Check which ships the user has already added.
    private SimpleBooleanProperty addedCarrier;
    private SimpleBooleanProperty addedBattleship;
    private SimpleBooleanProperty addedCruiser;
    private SimpleBooleanProperty addedSubmarine;
    private SimpleBooleanProperty addedDestroyer;

    // Set up everything for the two players.
    private View p1View;
    private View p2View;
    private Player p1;
    private Player p2;

    // Hold the target location where the user clicked.
    private int[] p1Target = {-1, -1};
    private int[] p2Target = {-1, -1};

    /**
     * Constructor for the Model class.
     *
     * @author Joseph DiPalma, Ben Xu
     */
    public Model(View p1View, View p2View, Player p1, Player p2) {
        this.carrier = new SimpleBooleanProperty(true);
        this.battleship = new SimpleBooleanProperty(false);
        this.cruiser = new SimpleBooleanProperty(false);
        this.submarine = new SimpleBooleanProperty(false);
        this.destroyer = new SimpleBooleanProperty(false);

        this.p1View = p1View;
        this.p2View = p2View;
        this.p1 = p1;
        this.p2 = p2;

        this.addedCarrier = new SimpleBooleanProperty(false);
        this.addedBattleship = new SimpleBooleanProperty(false);
        this.addedCruiser = new SimpleBooleanProperty(false);
        this.addedSubmarine = new SimpleBooleanProperty(false);
        this.addedDestroyer = new SimpleBooleanProperty(false);
    }

    /**
     * Start the timer for the specified player.
     *
     * @author Joseph DiPalma
     *
     * @param p the player to start the timer for
     */
    public void timerStart(Player p) {
        p.getMyView().getTimeline().play();
        p.getMyView().getTimer().start();
    }

    /**
     * Restart the timer for the specified player.
     *
     * @author Joseph DiPalma
     *
     * @param p the player to restart the timer for
     */
    public void timerRestart(Player p) {
        p.getMyView().getTimer().stop();
    }

    /**
     * Enable the attack button for the specified player.
     *
     * @author Joseph DiPalma
     *
     * @param p the player to enable the attack button for
     */
    public void enableAttack(Player p) {
        // Get the view from the player.
        View pView = p.getMyView();
        pView.getAttackBtn().setDisable(false);
    }

    /**
     * Disable the attack button for the specified player.
     *
     * @author Joseph DiPalma
     *
     * @param p the player to disable the attack button for
     */
    public void disableAttack(Player p) {
        // Get the view from the player.
        View pView = p.getMyView();
        pView.getAttackBtn().setDisable(true);
    }

    /**
     * Disable the ship selection buttons for the specified player.
     *
     * @author Joseph DiPalma
     *
     * @param p the player to disable the ship selection buttons for
     */
    public void disableShipSelection(Player p) {
        // Get the view of the player.
        View pView = p.getMyView();

        // Disable all of the buttons.
        pView.getCarrierBtn().setDisable(true);
        pView.getBattleshipBtn().setDisable(true);
        pView.getDestroyerBtn().setDisable(true);
        pView.getSubmarineBtn().setDisable(true);
        pView.getCruiserBtn().setDisable(true);
    }

    /**
     * Finish initializing the ships for the specified player.
     *
     * @author Joseph DiPalma
     *
     * @param p the player to finish initializing the ships for
     */
    public void finishInitShip(Player p) {
        // Call the disableShipSelection method.
        disableShipSelection(p);

        p.shipsAddedTrue();
    }

    /**
     * Display a victory message for the victorious player.
     *
     * @author Joseph DiPalma
     *
     * @param p the player to display the victory message for
     */
    public void displayVictoryMsg(Player p) {
        // Get the player's view.
        View winView = p.getMyView();

        // Call the method in view to show the win window.
        winView.showWinOrLoss("win");
    }

    /**
     * Display a loss message for the losing player.
     *
     * @author Joseph DiPalma
     *
     * @param p the player to display the loss message to
     */
    public void displayDefeatMsg(Player p) {
        // Get the player's view.
        View loseView = p.getMyView();

        // Call the method in view to show the loss window.
        loseView.showWinOrLoss("lose");
    }

    /**
     * Display to the user whether they hit or missed the opponent's ship.
     *
     * @author Joseph DiPalma
     */
    public void displayTargetStatus() {

    }

    /**
     * Attack the ship of the player.
     *
     * @author Joseph DiPalma
     *
     * @param player the player to attack the ship of
     */
    public void attackShip(int player) {
        if (player == 1) {

            // The player hit a ship.
            if (this.p1View.board[this.p2Target[0]][this.p2Target[1]].getFill() != Paint.valueOf(
                    "GREY")) {
                Rectangle hit = new Rectangle(500 + 30 * this.p2Target[0],
                                              100 + 30 * this.p2Target[1], 75,
                                              75);
                hit.setFill(Paint.valueOf("RED"));
                hit.setStroke(Paint.valueOf("BLACK"));

                this.p1View.board[this.p2Target[0]][this.p2Target[1]] = hit;
            }

            // The player missed the ships.
            else {
                Rectangle miss = new Rectangle(500 + 30 * this.p2Target[0],
                                               100 + 30 * this.p2Target[1], 75,
                                               75);
                miss.setFill(Paint.valueOf("WHITE"));
                miss.setStroke(Paint.valueOf("BLACK"));

                this.p1View.board[this.p2Target[0]][this.p2Target[1]] = miss;
            }
        }
        else if (player == 2) {

            // The player hit a ship.
            if (this.p2View.board[this.p1Target[0]][this.p1Target[1]].getFill() != Paint.valueOf(
                    "GREY")) {
                Rectangle hit = new Rectangle(500 + 30 * this.p1Target[0],
                                              100 + 30 * this.p1Target[1], 75,
                                              75);
                hit.setFill(Paint.valueOf("RED"));
                hit.setStroke(Paint.valueOf("BLACK"));

                this.p2View.board[this.p1Target[0]][this.p1Target[1]] = hit;
            }

            // The player missed the ships.
            else {
                Rectangle miss = new Rectangle(500 + 30 * this.p1Target[0],
                                               100 + 30 * this.p1Target[1], 75,
                                               75);
                miss.setFill(Paint.valueOf("WHITE"));
                miss.setStroke(Paint.valueOf("BLACK"));

                this.p2View.board[this.p1Target[0]][this.p1Target[1]] = miss;
            }
        }
    }

    /**
     * Start the game for both players.
     *
     * @author Joseph DiPalma
     *
     * @see
     * <a href="http://stackoverflow.com/questions/8065532/how-to-randomly-pick-an-element-from-an-array">
     * http://stackoverflow.com/questions/8065532/how-to-randomly-pick-an-element-from-an-array</a>
     */
    public void startGame() {
        // Make a random choice for the player.
        int[] choice = new int[]{0, 1};
        Random rand = new Random();
        int playerToGo = choice[rand.nextInt(choice.length)];

        // Update both of the ships.
        //updateShip(p1View, 1);
        //updateShip(p2View, 2);
        // End the ship initialization.
        //finishInitShip(p1);
        //finishInitShip(p2);
        // Disable the ships.
        //disableGUIControl(p1);
        //disableGUIControl(p2);
        int i = playerToGo;

//        this.p1View.getTimeline().play();
//        this.p1View.getTimer().start();
        while ((p1.didILose() == false) && (p2.didILose() == false)) {
            System.out.println("i is: " + i);

            // Player 1's turn.
            if (i % 2 == 0) {
                // Start the timer.
                timerStart(p1);

                // Allow p1 to attack.
                enableAttack(p1);
                disableAttack(p2);

                int[] loc = getTargetLocation(1);

                // Attack the given location.
                p1.attack(loc, p2);
                this.attackShip(2);

                // Defend the given location.
                p2.defend(loc);

                // Display the message whether or not it hits.
                displayTargetStatus();

                // Disable the attack button for p1.
                disableAttack(p1);

                // Check if p2 lost.
                if (p2.didILose()) {
                    displayVictoryMsg(p1);
                    displayDefeatMsg(p2);
                }

                timerRestart(p1);
            }

            // Player 2's turn.
            else if (i % 2 == 1) {
                // Start the timer.
                timerStart(p2);

                // Allow p2 to attack.
                enableAttack(p2);
                disableAttack(p1);

                int[] loc = getTargetLocation(2);

                // Attack the given location.
                p2.attack(loc, p1);
                this.attackShip(2);

                // Defend the given location.
                p1.defend(loc);

                // Display the message whether or not it hits.
                displayTargetStatus();

                // Disable the attack button for p2.
                disableAttack(p2);

                // Check if p1 lost.
                if (p1.didILose()) {
                    displayVictoryMsg(p2);
                    displayDefeatMsg(p1);
                }

                timerRestart(p2);
            }
        }

        i++;
    }

    /**
     * Show the GUI for the specified player.
     *
     * @author Joseph DiPalma
     *
     * @param p the player to show the GUI for
     */
    public void showGUI(Player p) {

    }

    /**
     * Disable the GUI for the specified player.
     *
     * @author Joseph DiPalma
     *
     * @param p the player to disable the GUI for
     */
    public void disableGUIControl(Player p) {
        // Call the disableShipSelection method.
        disableShipSelection(p);

        // Call the disableAttack method.
        disableAttack(p);
    }

    /**
     * Build the ship in the GUI corresponding to the Ship object.
     *
     * @author Annan Miao
     *
     * @param ship Ship object to build in the GUI
     */
    public void buildShipMy(Ship ship, Player player) {
        boolean noAlert = true;

        for (int[] position : ship.getShipLoc()) {
            if (position[0] > player.getMyView().board.length - 1 || position[1] > player.getMyView().board.length - 1) {
                //System.out.println("case 1");
                noAlert = false;
                Alert alert = new Alert(
                        Alert.AlertType.ERROR);
                alert.setTitle("Ship Location Error");
                alert.setHeaderText(
                        "Ship out of board range!");
                alert.setContentText(
                        "You cannot set ship here!");
                alert.show();
                return;

                //System.out.println(alert.toString());
            }
            else if (player.getMyView().board[position[0]][position[1]].getFill() != Paint.valueOf(
                    "GREY")) {
                //System.out.println("case 2");
                noAlert = false;
                Alert alert = new Alert(
                        Alert.AlertType.ERROR);
                alert.setTitle("Ship Location Error");
                alert.setHeaderText(
                        "Ship cannot overlap!");
                alert.setContentText(
                        "You cannot set ship here!");
                alert.show();
                return;
                //System.out.println(alert.toString());
            }
        }

        //System.out.println("case 3");
        //System.out.println(alert.toString());
        if (noAlert) {
            //System.out.println("case 3");

            for (int[] position : ship.getShipLoc()) {
                Rectangle r = player.getMyView().board[position[0]][position[1]];
                player.getMyView().myBoard.getChildren().remove(r);
                r.setFill(Paint.valueOf(ship.getShipType().getColor()));
                player.getMyView().myBoard.add(r, position[0], position[1]);
            }
            // Make the user lose the ability to select the toggle button for the ship.
            ShipType shipType = ship.getShipType();
            if (null != shipType) {
                switch (shipType) {
                    case CARRIER:
                        this.setAddedCarrier(new SimpleBooleanProperty(true));
                        // Set the added carrier attribute to true.
                        player.addedCarrier();
                        // Make the user lose the ability to select the carrier.
                        player.getMyView().getCarrierBtn().setDisable(true);
                        break;
                    case BATTLESHIP:
                        this.setAddedBattleship(new SimpleBooleanProperty(true));
                        // Set the added battleship attribute to true.
                        player.addedBattleship();
                        // Make the user lose the ability to select the battleship.
                        player.getMyView().getBattleshipBtn().setDisable(true);
                        break;
                    case CRUISER:
                        this.setAddedCruiser(new SimpleBooleanProperty(true));
                        // Set the added cruiser attribute to true.
                        player.addedCruiser();
                        // Make the user lose the ability to select the cruiser.
                        player.getMyView().getCruiserBtn().setDisable(true);
                        break;
                    case SUBMARINE:
                        this.setAddedSubmarine(new SimpleBooleanProperty(true));
                        // Set the added submarine attribute to true.
                        player.addedSubmarine();
                        // Make the user lose the ability to select the submarine.
                        player.getMyView().getSubmarineBtn().setDisable(true);
                        break;
                    case DESTROYER:
                        this.setAddedDestroyer(new SimpleBooleanProperty(true));
                        // Set the added destroyer attribute to true.
                        player.addedDestroyer();
                        // Make the user lose the ability to select the destroyer.
                        player.getMyView().getDestroyerBtn().setDisable(true);
                        break;
                    default:
                        break;
                }
            }

        }
    }

    /**
     * Update the ship when it is rotated or hit.
     *
     * @author Joseph DiPalma
     *
     * @param view the view to update the ship in
     * @param player the player to update the ship for
     */
    public void updateShip(View view, int player) {

        //System.out.println("run update ship");
        // Create or rotate ship, or destroy ship when clicking on ship area
        for (int i = 0; i < view.board.length; i++) {
            for (Rectangle r : view.board[i]) {
                r.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseevent) {
                        int col = view.myBoard.getColumnIndex(r);
                        int row = view.myBoard.getRowIndex(r);
                        //System.out.println(col + " " + row);

                        int[][] temp = null;

                        // Update the location in the loc array.
                        if (player == 1) {
                            p1Target[0] = col;
                            p1Target[1] = row;
                        }
                        else if (player == 2) {
                            p2Target[0] = col;
                            p2Target[1] = row;
                        }

                        System.out.println("p1 loc is: " + Arrays.toString(
                                p1Target));
                        System.out.println("p2 loc is: " + Arrays.toString(
                                p2Target));

                        if (view.getShipHorizontal().isSelected()) {
                            if (view.getCarrierBtn().isSelected() && !(view.getCarrierBtn().isDisable())) {
                                temp = new int[5][2];
                                for (int i = 0; i < 5; i++) {
                                    int[] tempArray = {col + i, row};
                                    temp[i] = tempArray;
                                }
                            }
                            else if (view.getBattleshipBtn().isSelected() && !(view.getBattleshipBtn().isDisable())) {
                                temp = new int[4][2];
                                for (int i = 0; i < 4; i++) {
                                    int[] tempArray = {col + i, row};
                                    temp[i] = tempArray;
                                }
                            }
                            else if (view.getCruiserBtn().isSelected() && !(view.getCruiserBtn().isDisable())) {
                                temp = new int[3][2];
                                for (int i = 0; i < 3; i++) {
                                    int[] tempArray = {col + i, row};
                                    temp[i] = tempArray;
                                }
                            }
                            else if (view.getSubmarineBtn().isSelected() && !(view.getSubmarineBtn().isDisable())) {
                                temp = new int[3][2];
                                for (int i = 0; i < 3; i++) {
                                    int[] tempArray = {col + i, row};
                                    temp[i] = tempArray;
                                }
                            }
                            else if (view.getDestroyerBtn().isSelected() && !(view.getDestroyerBtn().isDisable())) {
                                temp = new int[2][2];
                                for (int i = 0; i < 2; i++) {
                                    int[] tempArray = {col + i, row};
                                    temp[i] = tempArray;
                                }
                            }
                        }

                        else if (view.getShipVertical().isSelected()) {
                            if (view.getCarrierBtn().isSelected() && !(view.getCarrierBtn().isDisable())) {
                                temp = new int[5][2];
                                for (int i = 0; i < 5; i++) {
                                    int[] tempArray = {col, row + i};
                                    temp[i] = tempArray;
                                }
                            }
                            else if (view.getBattleshipBtn().isSelected() && !(view.getBattleshipBtn().isDisable())) {
                                temp = new int[4][2];
                                for (int i = 0; i < 4; i++) {
                                    int[] tempArray = {col, row + i};
                                    temp[i] = tempArray;
                                }
                            }
                            else if (view.getCruiserBtn().isSelected() && !(view.getCruiserBtn().isDisable())) {
                                temp = new int[3][2];
                                for (int i = 0; i < 3; i++) {
                                    int[] tempArray = {col, row + i};
                                    temp[i] = tempArray;
                                }
                            }
                            else if (view.getSubmarineBtn().isSelected() && !(view.getSubmarineBtn().isDisable())) {
                                temp = new int[3][2];
                                for (int i = 0; i < 3; i++) {
                                    int[] tempArray = {col, row + i};
                                    temp[i] = tempArray;
                                }
                            }
                            else if (view.getDestroyerBtn().isSelected() && !(view.getDestroyerBtn().isDisable())) {
                                temp = new int[2][2];
                                for (int i = 0; i < 2; i++) {
                                    int[] tempArray = {col, row + i};
                                    temp[i] = tempArray;
                                }
                            }
                        }
                        Ship ship = null;
                        try {
                            ArrayList<int[]> shipLoc = new ArrayList<int[]>(
                                    Arrays.asList(temp));

                            if (view.getCarrierBtn().isSelected()) {
                                ship = new Ship(shipLoc, ShipType.CARRIER);
                            }
                            else if (view.getBattleshipBtn().isSelected()) {
                                ship = new Ship(shipLoc, ShipType.BATTLESHIP);
                            }
                            else if (view.getCruiserBtn().isSelected()) {
                                ship = new Ship(shipLoc, ShipType.CRUISER);
                            }
                            else if (view.getSubmarineBtn().isSelected()) {
                                ship = new Ship(shipLoc, ShipType.SUBMARINE);
                            }
                            else if (view.getDestroyerBtn().isSelected()) {
                                ship = new Ship(shipLoc, ShipType.DESTROYER);
                            }
                        } catch (NullPointerException n) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Ship already placed");
                            alert.setHeaderText("Duplicate ship");
                            alert.setContentText("Already placed this ship");
                            alert.show();
                        }
                        try {
                            System.out.println(
                                    "carrier " + getAddedCarrier().get());
                            System.out.println(
                                    "cruiser " + getAddedCruiser().get());
                            System.out.println(
                                    "destroyer " + getAddedDestroyer().get());
                            System.out.println(
                                    "battleship " + getAddedBattleship().get());
                            System.out.println(
                                    "submarine " + getAddedSubmarine().get());

                            //if ((!getAddedCarrier().get()) || (!getAddedCruiser().get()) || (!getAddedDestroyer().get()) || (!getAddedBattleship().get()) || (!getAddedSubmarine().get())) {
                            if ((!p1.getShipsAdded()) && (!p2.getShipsAdded())) {
                                System.out.println("p1 " + p1.getShipsAdded());
                                System.out.println("p2 " + p2.getShipsAdded());
                                System.out.println("Working");
                                if (player == 1) {
                                    Model.this.buildShipMy(ship, p1);
                                }

                                else if (player == 2) {
                                    Model.this.buildShipMy(ship, p2);
                                }
                            }

                            //}
                        } catch (Exception e) {
                            System.out.println(e.toString());
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Out of range!");
                            alert.setHeaderText("Incorrect input specified!");
                            alert.setContentText("Out of range!");
                            alert.show();
                        }

                        //return loc;
                    }
                });
            }
        }

//
//        System.out.println(Arrays.toString(loc));
//        // Return the updated location.
//        return loc;
    }

    public void setAddedCarrier(SimpleBooleanProperty addedCarrier) {
        this.addedCarrier = addedCarrier;
    }

    public void setAddedBattleship(SimpleBooleanProperty addedBattleship) {
        this.addedBattleship = addedBattleship;
    }

    public void setAddedCruiser(SimpleBooleanProperty addedCruiser) {
        this.addedCruiser = addedCruiser;
    }

    public void setAddedSubmarine(SimpleBooleanProperty addedSubmarine) {
        this.addedSubmarine = addedSubmarine;
    }

    public void setAddedDestroyer(SimpleBooleanProperty addedDestroyer) {
        this.addedDestroyer = addedDestroyer;
    }

    public SimpleBooleanProperty getCarrier() {
        return carrier;
    }

    public SimpleBooleanProperty getBattleship() {
        return battleship;
    }

    public SimpleBooleanProperty getCruiser() {
        return cruiser;
    }

    public SimpleBooleanProperty getSubmarine() {
        return submarine;
    }

    public SimpleBooleanProperty getDestroyer() {
        return destroyer;
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public View getP1View() {
        return p1View;
    }

    public View getP2View() {
        return p2View;
    }

    public SimpleBooleanProperty getAddedCarrier() {
        return addedCarrier;
    }

    public SimpleBooleanProperty getAddedBattleship() {
        return addedBattleship;
    }

    public SimpleBooleanProperty getAddedCruiser() {
        return addedCruiser;
    }

    public SimpleBooleanProperty getAddedSubmarine() {
        return addedSubmarine;
    }

    public SimpleBooleanProperty getAddedDestroyer() {
        return addedDestroyer;
    }

    public int[] getTargetLocation(int player) {
        // Player 1.
        if (player == 1) {
            return this.p1Target;
        }

        // Player 2.
        else {
            return this.p2Target;
        }
    }

}
