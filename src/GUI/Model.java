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

    /**
     * Constructor for the Model class.
     *
     * @author Joseph DiPalma
     */
    public Model() {
        this.carrier = new SimpleBooleanProperty(true);
        this.battleship = new SimpleBooleanProperty(false);
        this.cruiser = new SimpleBooleanProperty(false);
        this.submarine = new SimpleBooleanProperty(false);
        this.destroyer = new SimpleBooleanProperty(false);
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

}
