/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Joseph DiPalma, Annan Miao, and Ben Xu
* Date: Apr 10, 2017
* Time: 10:21:15 AM
*
* Project: csci205finalproject
* Package: ship
* File: Play
* Description:
*
* ****************************************
 */
package ship;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author am049
 */
public class Play extends Application {

    private int length;

    @Override
    public void start(Stage primaryStage) {

        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        Group root = new Group();
        //root.getChildren().add(btn);

        for (int i = 0; i < length; i++) {
            Rectangle r1 = new Rectangle(50, 30, Color.BLUE);
            r1.setX(100);
            r1.setY(150 + 30 * i);
            root.getChildren().add(r1);
        }

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
