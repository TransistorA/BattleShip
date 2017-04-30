/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Joseph DiPalma, Annan Miao, and Ben Xu
* Date: Apr 10, 2017
* Time: 10:33:14 AM
*
* Project: csci205finalproject
* Package: GUI
* File: Main
* Description:
*
* ****************************************
 */
package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import player.Player;

/**
 * Main method for the project.
 *
 * @author Joseph DiPalma
 */
public class Main extends Application {

    // The current player.
    private View theView1;
    private Player p1;
    // The enemy.
    private View theView2;
    private Player p2;

    private Model theModel;
    private Controller theCtrl;

    @Override
    public void init() throws Exception {
        super.init();
        this.theView1 = new View();
        this.theView2 = new View();

        this.p1 = new Player(this.theView1);
        this.p2 = new Player(this.theView2);

        this.theModel = new Model(theView1, theView2, p1, p2);

        // I don't think that the controller needs to take in a view.  I believe
        // that it should get both theView1 and theView2 from theModel and then
        // bind everything from there.
        this.theCtrl = new Controller(theModel);

        /*
        This is not going to work.  I'm going to have to make some kind of button
        that the user selects when they are done selecting their ships.  This will
        allow me to start the game and not prematurely disable the buttons.
         */
        this.theModel.updateShip(this.theView1, 1);
        this.theModel.updateShip(this.theView2, 2);
//        while (true) {
//            System.out.println("while true");
//            if (this.p1.getShipsAdded() && this.p2.getShipsAdded()) {
//                this.theModel.startGame();
//                break;
//            }
//        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Now, set up the scene, and connect it to the stage.
        // The client needs to make sure both GUIs are visible to the correct
        // player.  I need to see the code for the client before I can
        // fix this code.
        Scene scene = new Scene(this.theView1.getRoot(), 1500, 1500);

        primaryStage.setTitle("Battleship");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        // primaryStage.setFullScreen(true);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
