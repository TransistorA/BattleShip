/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Joseph DiPalma, Annan Miao, and Ben Xu
* Date: Apr 10, 2017
* Time: 10:16:41 AM
*
* Project: csci205finalproject
* Package: GUI
* File: View
* Description:
*
* ****************************************
 */
package GUI;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Rectangle;

/**
 * View part of the MVC design pattern.
 *
 * @author Joseph DiPalma
 */
public class View {

    private Model theModel;

    private FlowPane root;

    private Group grid;

    /**
     * Constructor for the View class.
     *
     * @author Joseph DiPalma
     *
     * @param theModel Model object that handles all of the logic
     */
    public View(Model theModel) {
        this.theModel = theModel;

        grid = new Group();

        root = new FlowPane();
        root.setPadding(new Insets(15, 15, 15, 15));
        root.setHgap(10.0);
        root.setVgap(10.0);

        // Loop to create the rows and columns.
        for (int r = 1; r < 11; r++) {
            //System.out.println("r is: " + (((double) r) * 10.0));
            for (int c = 1; c < 11; c++) {
                grid.getChildren().add(new Rectangle(100 + 30 * c,
                                                     100 + 30 * r, 20.0,
                                                     20.0));

                //System.out.println("c is: " + (((double) c) * 10.0));
            }
        }

        root.getChildren().add(grid);

    }

    public FlowPane getRoot() {
        return root;
    }

}
