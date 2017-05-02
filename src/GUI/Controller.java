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
     */
    public Controller(Model theModel) {
        this.theModel = theModel;
        this.theView1 = this.theModel.getP1View();
        this.theView2 = this.theModel.getP2View();

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
        this.theModel.getP1View().getShipSelectionDone().setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Controller.this.theModel.getP1().shipsAddedTrue();
                    // Check if the user has added all of their ships.
                    if (Controller.this.theModel.getP1().getShipsAdded()) {
                        Controller.this.theModel.disableShipSelection(
                                Controller.this.theModel.getP1());

                        theView1.getShipSelectionDone().setDisable(true);

                        theView1.getShipHorizontal().setDisable(true);
                        theView1.getShipVertical().setDisable(true);

//                        System.out.println("player 1 done");
                        // Check if the other user has added all of their ships.
                        Controller.this.theModel.getP1().shipsAddedTrue();
                        Controller.this.theModel.getP2().shipsAddedTrue();

//                        System.out.println(
//                                "p1 ships added: " + Controller.this.theModel.isP1InitDone());
//                        System.out.println(
//                                "p2 ships added: " + Controller.this.theModel.isP2InitDone());
                        Controller.this.theModel.setP1InitDone(true);
                        theView1.setP1InitDone(true);
                        Controller.this.theModel.startGame();
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
                    Controller.this.theModel.getP2().shipsAddedTrue();
                    // Check if the user has added all of their ships.
                    if (Controller.this.theModel.getP2().getShipsAdded()) {
                        Controller.this.theModel.disableShipSelection(
                                Controller.this.theModel.getP2());

                        theView2.getShipSelectionDone().setDisable(true);

                        theView2.getShipHorizontal().setDisable(true);
                        theView2.getShipVertical().setDisable(true);

//                        System.out.println("player 2 done");
                        // Check if the other user has added all of their ships.
                        Controller.this.theModel.getP1().shipsAddedTrue();
                        Controller.this.theModel.getP2().shipsAddedTrue();
                        theView2.setP2InitDone(true);
//                        System.out.println(
//                                "p1 ships added: " + Controller.this.theModel.isP1InitDone());
//                        System.out.println(
//                                "p2 ships added: " + Controller.this.theModel.isP2InitDone());
//                        System.out.println(
//                                "p1 ships added: " + theView2.isP2InitDone());
//                        System.out.println(
//                                "p2 ships added: " + theView1.isP1InitDone());
                        Controller.this.theModel.setP2InitDone(true);

                        Controller.this.theModel.startGame();

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

    }

    public Model getTheModel() {
        return theModel;
    }

}
