/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2017
 *
 * Name: Joseph DiPalma, Annan Miao, and Ben Xu
 * Date: Apr 29, 2017
 * Time: 11:11:00 PM
 *
 * Project: csci205finalproject
 * Package: networking
 * File: GameStartView
 * Description:
 *
 * ****************************************
 */
package networking;

import GUI.Controller;
import GUI.Model;
import GUI.View;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import player.Player;

/**
 *
 * @author computer
 */
public class GameStartView extends Application {

    ServerSocket hostServer;
    Socket hostSocket;
    Server mainServer;
    Client mainClient;
    Scene scene;
    View theView1;
    View theView2;
    Player p1;
    Player p2;
    Model theModel;
    Controller theCtrl;

    Thread initGame;

    @Override
    public void start(Stage primaryStage) throws Exception {
        theView1 = new View();
        theView2 = new View();
        p1 = new Player(theView1);
        p2 = new Player(theView2);
        theModel = new Model(theView1, theView2, p1, p2);
        theCtrl = new Controller(theModel);
        // Create the Player and View objects.

//        Runnable checkInitTask = new Runnable() {
//            @Override
//            public void run() {
//                //System.out.println(theModel.readyToStart());
//                while (true) {
//                    //System.out.print(theView2.readyToStart());
//                    //System.out.print(theView1.readyToStart() + "\n");
//
//                        theModel.startGame();
//                    }
//                }
//            }
//
//        };
//        System.out.println("first object is: " + firstObject);
//        if (firstObject == 0) {
//            System.out.println("first object");
//
//            this.theView1 = new View();
//            this.theView2 = new View();
//            this.p1 = new Player(
//                    this.theView1);
//            this.p2 = new Player(
//                    this.theView2);
//            this.theModel = new Model(
//                    this.theView1,
//                    this.theView2,
//                    this.p1, this.p2);
//
//            this.theCtrl = new Controller(
//                    this.theModel);
//
//            firstObject++;
//        }
        VBox root = new VBox(5);
        root.setPrefWidth(500);
        root.setPadding(new Insets(10, 10, 10, 10));

        //flow pane object
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setAlignment(Pos.CENTER);

        // display labels
        Label displayTxt = new Label("Game Mode Selection:");

        displayTxt.setWrapText(true);
        pane.add(displayTxt, 0, 0);

        //host game button relate
        Button hostGame = new Button();
        hostGame.setText("Host Game");
        Label ipAddress = new Label();
        ipAddress.setVisible(false);
        Label hostDisplay = new Label("Server Address:");
        hostDisplay.setVisible(false);
        pane.add(ipAddress, 0, 2);
        pane.add(hostDisplay, 0, 1);
        pane.add(hostGame, 0, 1);

        // connect to host button related
        Button connectToOpp = new Button();
        connectToOpp.setText("Connect to Opponent");
        pane.add(connectToOpp, 0, 2);
        //Label ipLabel = new Label("Enter Host IP Address:");
        TextField ipInput = new TextField();
        ipInput.setVisible(false);
        Button connect = new Button();
        connect.setVisible(false);
        connect.setText("Connect");
        pane.add(ipInput, 0, 1);
        pane.add(connect, 0, 2);
        //ipLabel.setVisible(false);
        root.getChildren().add(pane);

        //host Game button interact
        hostGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //hide the buttons
                hostGame.setVisible(false);
                connectToOpp.setVisible(false);

                //change display text
                displayTxt.setText(
                        "Server Started, waiting for opponent to connect...");
                try {
                    ipAddress.setText(
                            InetAddress.getLocalHost().getHostAddress());
                } catch (UnknownHostException ex) {
                }
                hostDisplay.setVisible(true);
                ipAddress.setVisible(true);

                Runnable serverTask;
                serverTask = new Runnable() {
                    @Override
                    public void run() {
//                        GameStartView.this.theView1 = new View();
//                        GameStartView.this.theView2 = new View();
//                        GameStartView.this.p1 = new Player(
//                                GameStartView.this.theView1);
//                        GameStartView.this.p2 = new Player(
//                                GameStartView.this.theView2);
//                        GameStartView.this.theModel = new Model(
//                                GameStartView.this.theView1,
//                                GameStartView.this.theView2,
//                                GameStartView.this.p1, GameStartView.this.p2);
//                        try {
//                            GameStartView.this.theCtrl = new Controller(
//                                    GameStartView.this.theModel);
//                        } catch (InterruptedException ex) {
//                        }
                        mainServer = new Server();
                        if (mainServer.hostSocket.isConnected()) {
                            System.out.println("CONNECTED!");

                            try {
                                mainServer.hostSocket.close();
                            } catch (IOException ex) {

                            }
                            //run the model here
                            //display View 1
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    theModel.updateShip(
                                            theView1, 1);
                                    theModel.updateShip(
                                            theView2, 2);
                                    theModel.showGUI(
                                            p1);
                                }
                            });
                        }
                    }
                };
                Thread ServerThread = new Thread(serverTask);

                ServerThread.start();

            }
        });

        connectToOpp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //hide the buttons
                hostGame.setVisible(false);
                connectToOpp.setVisible(false);

                //show the ip input dialog and button
                ipInput.setVisible(true);
                connect.setVisible(true);

                //change display text
                displayTxt.setText(
                        "Enter Host IP Address");
                connect.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Runnable clientTask;
                        clientTask = new Runnable() {
                            @Override
                            public void run() {
                                mainClient = new Client(ipInput.getText());
                                if (mainClient.getClientSocket().isConnected()) {
                                    System.out.println("CONNECTED!");

                                    try {
                                        mainClient.clientSocket.close();
                                    } catch (IOException ex) {

                                    }

                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            theModel.updateShip(
                                                    theView1, 1);
                                            theModel.updateShip(
                                                    theView2, 2);
                                            theModel.showGUI(
                                                    p2);
                                        }
                                    });
                                }
                            }
                        };
                        Thread clientThread = new Thread(clientTask);

                        clientThread.start();
//                        initGame = new Thread(checkInitTask);
//                        initGame.start();
                    }
                });
            }
        });

        scene = new Scene(root);
        primaryStage.setTitle("Battleship!");
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
