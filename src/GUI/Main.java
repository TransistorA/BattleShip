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
 *
 * @author jrd028
 */
public class Main extends Application {

    private View theView;

    @Override
    public void init() throws Exception {
        super.init();
        this.theView = new View(null);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Now, set up the scene, and connect it to the stage!
        Scene scene = new Scene(this.theView.getRoot());

        primaryStage.setScene(scene);
        //primaryStage.sizeToScene();
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
