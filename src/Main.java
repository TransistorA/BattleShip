/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Final;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class Main extends Application {

    private Rectangle[][] board = new Rectangle[10][10];
    private Paint red = Paint.valueOf("RED");
    private Paint blue = Paint.valueOf("BLUE");

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();

        // create the board
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Rectangle r1 = new Rectangle(100 + 30 * j, 100 + 30 * i, 20, 20);
                r1.setFill(blue);
                board[i][j] = r1;
                root.getChildren().add(r1);
            }
        }

        // switch color when clicked
        for (int i = 0; i < board.length; i++) {
            for (Rectangle r : board[i]) {
                r.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseevent) {
                        if (r.getFill() == blue) {
                            r.setFill(red);
                        }
                        else if (r.getFill() == red) {
                            r.setFill(blue);
                        }
                    }
                });
            }
        }

        // check the total number of ships
        Button finish = new Button("Finish Creating Ship!");
        finish.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int countTotal = countShip();
                int count2 = count2SpaceShip();
                int count3 = count3SpaceShip();
                int count4 = count4SpaceShip();
                int count1 = countTotal - 2 * count2 - 3 * count3 - 4 * count4;

                System.out.println(
                        count1 + " " + count2 + " " + count3 + " " + count4);
            }
        });
        root.getChildren().add(finish);

        Scene scene = new Scene(root, 600, 500);

        primaryStage.setTitle("BattleShip!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Check the total number of rectangles that represents ship
     *
     * @return the total number of ship squares
     */
    public int countShip() {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (Rectangle r : board[i]) {
                if (r.getFill() == red) {
                    count += 1;
                }
            }
        }
        return count;
    }

    public int count4SpaceShip() {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length - 3; j++) {
                if (board[i][j].getFill() == red && board[i][j + 1].getFill() == red && board[i][j + 2].getFill() == red && board[i][j + 3].getFill() == red) {
                    count += 1;
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length - 3; j++) {
                if (board[j][i].getFill() == red && board[j + 1][i].getFill() == red && board[j + 2][i].getFill() == red && board[j + 3][i].getFill() == red) {
                    count += 1;
                }
            }
        }
        return count;
    }

    public int count3SpaceShip() {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length - 2; j++) {
                if (board[i][j].getFill() == red && board[i][j + 1].getFill() == red && board[i][j + 2].getFill() == red) {
                    count += 1;
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length - 2; j++) {
                if (board[j][i].getFill() == red && board[j + 1][i].getFill() == red && board[j + 2][i].getFill() == red) {
                    count += 1;
                }
            }
        }
        return count;
    }

    public int count2SpaceShip() {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length - 1; j++) {
                if (board[i][j].getFill() == red && board[i][j + 1].getFill() == red) {
                    count += 1;
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length - 1; j++) {
                if (board[j][i].getFill() == red && board[j + 1][i].getFill() == red) {
                    count += 1;
                }
            }
        }
        return count;
    }

}
