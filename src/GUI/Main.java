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

/**
 * Main method for the project.
 *
 * @author Joseph DiPalma
 */
public class Main extends Application {

    // The current player.
    private View theView1;
    // The enemy.
    private View theView2;
    private Model theModel;
    private Controller theCtrl;

    @Override
    public void init() throws Exception {
        super.init();
        this.theView1 = new View();
        this.theView2 = new View();
        this.theModel = new Model(theView1, theView2);
        this.theCtrl = new Controller(theModel, theView1);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Now, set up the scene, and connect it to the stage!
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
