/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2017
 *
 * Name: Joseph DiPalma, Annan Miao, and Ben Xu
 * Date: Apr 26, 2017
 * Time: 10:22:52 AM
 *
 * Project: csci205finalproject
 * Package: networking
 * File: Server
 * Description:
 *
 * ****************************************
 */


import GUI.Controller;
import GUI.Model;
import GUI.View;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.application.Application;
import player.Player;

/**
 * Server aspect of the network.
 *
 * @author Ben Xu
 */
public class Server {

    ServerSocket hostServer;
    Socket hostSocket;
    ObjectInputStream input;
    ObjectOutputStream output;
    Controller control;

    /**
     * Constructor for the Server class.
     *
     * @author Ben Xu
     */
    public Server() {
        try {
            System.out.println("Server Started");
            hostServer = new ServerSocket(1024);
            hostSocket = hostServer.accept();
            System.out.print("connected");
            output = new ObjectOutputStream(hostSocket.getOutputStream());
            input = new ObjectInputStream(hostSocket.getInputStream());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ServerSocket getHostServer() {
        return hostServer;
    }

    /**
     * Initialize the server.
     *
     * @author Ben Xu
     *
     * @throws IOException if IO operations fail
     */
    public void initServer() throws IOException {
        hostSocket = hostServer.accept();
    }

    /**
     * Initialize the Controller for the server.
     *
     * @author Ben Xu
     *
     * @return the Controller for the server
     */
    private Controller initController() {
        View v1 = new View();
        View v2 = new View();
        Player p1 = new Player(v1);
        Player p2 = new Player(v2);
        Model model = new Model(v1, v2, p1, p2);
        Controller control = new Controller(model);
        return control;
    }

    public static void main(String[] argus) {
        Application.launch();
        Server test = new Server();
    }

    public Socket getHostSocket() {
        return hostSocket;
    }

}
