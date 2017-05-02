/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2017
 *
 * Name: Joseph DiPalma, Annan Miao, and Ben Xu
 * Date: Apr 10, 2017
 * Time: 10:33:14 AM
 *
 * Project: csci205finalproject
 * Package: GUI_AI
 * File: Main
 * Description:
 *
 * ****************************************
 */
package GUI_AI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import player_AI.Player;

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

        this.theCtrl = new Controller(theModel);

        this.theModel.updateShip(this.theView1, 1);
        this.theModel.updateShip(this.theView2, 2);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(this.theView1.getRoot(), 1500, 1500);

        primaryStage.setTitle("Battleship");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
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
