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

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Controller part of the MVC design pattern.
 *
 * @author Joseph DiPalma
 */
public class Controller implements EventHandler<ActionEvent> {

    private Model theModel;
    private View theView;

    /**
     * Constructor for the Controller class.
     *
     * @author Joseph DiPalma
     *
     * @param theModel model part of the MVC design pattern
     * @param theView view part of the MVC design pattern
     */
    public Controller(Model theModel, View theView) {
        this.theModel = theModel;
        this.theView = theView;

        // This is all things that haven't been put in View yet but will be
        // in the future.
        /*
        this.theView.getRotateCWButton().setOnAction(this);
        this.theView.getRotateCCWButton().setOnAction(this);
         */
        this.theModel.getCarrier().bind(
                this.theView.getCarrierBtn().selectedProperty());
        this.theModel.getBattleship().bind(
                this.theView.getBattleshipBtn().selectedProperty());
        this.theModel.getCruiser().bind(
                this.theView.getCruiserBtn().selectedProperty());
        this.theModel.getSubmarine().bind(
                this.theView.getSubmarineBtn().selectedProperty());
        this.theModel.getDestroyer().bind(
                this.theView.getDestroyerBtn().selectedProperty());

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
    }

}
