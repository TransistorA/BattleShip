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
 * Initialize and synchronize network connection for multiplayer.
 *
 * @author Ben Xu
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
        // Create the Player and View objects.
        theView1 = new View();
        theView2 = new View();
        p1 = new Player(theView1);
        p2 = new Player(theView2);
        theModel = new Model(theView1, theView2, p1, p2);
        theCtrl = new Controller(theModel);

        VBox root = new VBox(5);
        root.setPrefWidth(500);
        root.setPadding(new Insets(10, 10, 10, 10));

        // Create the GridPane object for the networking option box.
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setAlignment(Pos.CENTER);

        // Display the labels on the networking option box.
        Label displayTxt = new Label("Game Mode Selection:");

        displayTxt.setWrapText(true);
        pane.add(displayTxt, 0, 0);

        // Create the Button to allow the user to select the option to host the game.
        Button hostGame = new Button();
        hostGame.setText("Host Game");
        Label ipAddress = new Label();
        ipAddress.setVisible(false);
        Label hostDisplay = new Label("Server Address:");
        hostDisplay.setVisible(false);
        pane.add(ipAddress, 0, 2);
        pane.add(hostDisplay, 0, 1);
        pane.add(hostGame, 0, 1);

        // Connect functionality to the host game button.
        Button connectToOpp = new Button();
        connectToOpp.setText("Connect to Opponent");
        pane.add(connectToOpp, 0, 2);
        TextField ipInput = new TextField();
        ipInput.setVisible(false);
        Button connect = new Button();
        connect.setVisible(false);
        connect.setText("Connect");
        pane.add(ipInput, 0, 1);
        pane.add(connect, 0, 2);
        root.getChildren().add(pane);

        // Handle method for the host game Button.
        hostGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Hide the buttons.
                hostGame.setVisible(false);
                connectToOpp.setVisible(false);

                // Change the display text.
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
                        mainServer = new Server();
                        if (mainServer.hostSocket.isConnected()) {
                            System.out.println("CONNECTED!");

                            try {
                                mainServer.hostSocket.close();
                            } catch (IOException ex) {

                            }
                            // Run the Model and display the View.
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
                // Hide the buttons.
                hostGame.setVisible(false);
                connectToOpp.setVisible(false);

                // Show the IP address input dialog and Button.
                ipInput.setVisible(true);
                connect.setVisible(true);

                // Change display text.
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
