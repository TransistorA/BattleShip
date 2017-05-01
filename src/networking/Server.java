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
package networking;

import GUI.Controller;
import GUI.Model;
import GUI.View;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import player.Player;

/**
 *
 * @author computer
 */
public class Server {

    ServerSocket hostServer;
    Socket hostSocket;
    ObjectInputStream input;
    ObjectOutputStream output;

    public Server() {
        Controller control = initController();
        try {
            System.out.println("Server Started");
            hostServer = new ServerSocket(1024);
            hostSocket = hostServer.accept();
            System.out.print("connected");
            OutputStream output = hostSocket.getOutputStream();
            System.out.println(control);
            output.close();
            hostServer.close();
            hostSocket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ServerSocket getHostServer() {
        return hostServer;
    }

    public void initServer() throws IOException {
        hostSocket = hostServer.accept();
    }

    public Socket getHostSocket() {
        return hostSocket;
    }

    public static void main(String as[]) {
        Server host = new Server();

    }

    private Controller initController() {
        View v1 = new View();
        View v2 = new View();
        Player p1 = new Player(v1);
        Player p2 = new Player(v2);
        Model model = new Model(v1, v2, p1, p2);
        Controller control = new Controller(model);
        return control;
    }

}
