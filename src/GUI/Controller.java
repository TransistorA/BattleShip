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
    private View theView1;
    private View theView2;

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

        // This is all things that haven't been put in View yet but will be
        // in the future.
        /*
        this.theView.getRotateCWButton().setOnAction(this);
        this.theView.getRotateCCWButton().setOnAction(this);
         */
        // View 1.
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

}
