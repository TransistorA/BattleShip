/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Joseph DiPalma, Annan Miao, and Ben Xu
* Date: Apr 10, 2017
* Time: 10:16:34 AM
*
* Project: csci205finalproject
* Package: GUI
* File: Model
* Description:
*
* ****************************************
 */
package GUI;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import player.Player;
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

    private View p1View;
    private View p2View;
    private Player p1;
    private Player p2;

    /**
     * Constructor for the Model class.
     *
     * @author Joseph DiPalma, Ben Xu
     */
    public Model(View p1View, View p2View) {
        this.carrier = new SimpleBooleanProperty(true);
        this.battleship = new SimpleBooleanProperty(false);
        this.cruiser = new SimpleBooleanProperty(false);
        this.submarine = new SimpleBooleanProperty(false);
        this.destroyer = new SimpleBooleanProperty(false);
        this.p1View = p1View;
        this.p2View = p2View;

        this.addedCarrier = new SimpleBooleanProperty(false);
        this.addedBattleship = new SimpleBooleanProperty(false);
        this.addedCruiser = new SimpleBooleanProperty(false);
        this.addedSubmarine = new SimpleBooleanProperty(false);
        this.addedDestroyer = new SimpleBooleanProperty(false);

        this.updateShip(p1View);
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

    public int[] getTargetLocation() {
        //TODO
        return null;
    }

    public void enableAttack(Player p) {

    }

    public void disableAttack(Player p) {

    }

    public void disableShipSelection(Player p) {

    }

    public void finishInitShip(Player p) {

    }

    public void displayVictoryMsg(Player p) {

    }

    public void displayDefeatMsg(Player p) {

    }

    public void displayTargetStatus() {

    }

    public void startGame() {

    }

    public void showGUI(Player p) {

    }

    public void disableGUIControl(Player p) {

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

    /**
     * Build the ship in the GUI corresponding to the Ship object.
     *
     * @author Annan Miao
     *
     * @param ship Ship object to build in the GUI
     */
    public void buildShipMy(Ship ship, View view) {
        for (int[] position : ship.getShipLoc()) {
            Rectangle r = view.board[position[0]][position[1]];
            view.myBoard.getChildren().remove(r);
            r.setFill(Paint.valueOf(ship.getShipType().getColor()));
            view.myBoard.add(r, position[0], position[1]);
        }
        // Make the user lose the ability to select the toggle button for the ship.
        ShipType shipType = ship.getShipType();
        if (shipType == ShipType.CARRIER) {
            this.setAddedCarrier(new SimpleBooleanProperty(true));
        }
        else if (shipType == ShipType.BATTLESHIP) {
            this.setAddedBattleship(new SimpleBooleanProperty(true));
        }
        else if (shipType == ShipType.CRUISER) {
            this.setAddedCruiser(new SimpleBooleanProperty(true));
        }
        else if (shipType == ShipType.SUBMARINE) {
            this.setAddedSubmarine(new SimpleBooleanProperty(true));
        }
        else if (shipType == ShipType.DESTROYER) {
            this.setAddedDestroyer(new SimpleBooleanProperty(true));
        }
    }

    /**
     * Update the ship when it is rotated or hit.
     *
     * @author Joseph DiPalma
     */
    public void updateShip(View view) {
        // Create or rotate ship, or destroy ship when clicking on ship area
        for (int i = 0; i < view.board.length; i++) {
            for (Rectangle r : view.board[i]) {
                r.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseevent) {
                        int col = view.myBoard.getColumnIndex(r);
                        int row = view.myBoard.getRowIndex(r);
                        int[][] temp = null;
                        if (view.getCarrierBtn().isSelected() && !(view.getCarrierBtn().isDisable())) {
                            temp = new int[5][2];
                            for (int i = 0; i < 5; i++) {
                                int[] tempArray = {col + i, row};
                                temp[i] = tempArray;
                            }
                            // Make the user lose the ability to select the carrier.
                            view.getCarrierBtn().setDisable(true);
                        }
                        else if (view.getBattleshipBtn().isSelected() && !(view.getBattleshipBtn().isDisable())) {
                            temp = new int[4][2];
                            for (int i = 0; i < 4; i++) {
                                int[] tempArray = {col + i, row};
                                temp[i] = tempArray;
                            }
                            // Make the user lose the ability to select the carrier.
                            view.getBattleshipBtn().setDisable(true);
                        }
                        else if (view.getCruiserBtn().isSelected() && !(view.getCruiserBtn().isDisable())) {
                            temp = new int[3][2];
                            for (int i = 0; i < 3; i++) {
                                int[] tempArray = {col + i, row};
                                temp[i] = tempArray;
                            }
                            // Make the user lose the ability to select the carrier.
                            view.getCruiserBtn().setDisable(true);
                        }
                        else if (view.getSubmarineBtn().isSelected() && !(view.getSubmarineBtn().isDisable())) {
                            temp = new int[3][2];
                            for (int i = 0; i < 3; i++) {
                                int[] tempArray = {col + i, row};
                                temp[i] = tempArray;
                            }
                            // Make the user lose the ability to select the carrier.
                            view.getSubmarineBtn().setDisable(true);
                        }
                        else if (view.getDestroyerBtn().isSelected() && !(view.getDestroyerBtn().isDisable())) {
                            temp = new int[2][2];
                            for (int i = 0; i < 2; i++) {
                                int[] tempArray = {col + i, row};
                                temp[i] = tempArray;
                            }
                            // Make the user lose the ability to select the carrier.
                            view.getDestroyerBtn().setDisable(true);
                        }
                        ArrayList<int[]> shipLoc = new ArrayList<int[]>(
                                Arrays.asList(temp));
                        Ship ship = null;
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
                        try {
                            Model.this.buildShipMy(ship, view);
                        } catch (Exception e) {
                            // System.out.println(e.toString());
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Out of range!");
                            alert.setHeaderText("Incorrect input specified!");
                            alert.setContentText("Out of range!");
                            alert.show();
                        }
                    }
                });
            }
        }
    }

}
