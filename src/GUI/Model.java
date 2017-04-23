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

import javafx.beans.property.SimpleBooleanProperty;
import player.Player;

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

}
