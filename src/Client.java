/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2017
 *
 * Name: Joseph DiPalma, Annan Miao, and Ben Xu
 * Date: Apr 26, 2017
 * Time: 10:27:19 AM
 *
 * Project: csci205finalproject
 * Package: networking
 * File: Client
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
import java.net.Socket;
import player.Player;

/**
 * Client aspect of the network.
 *
 * @author Ben Xu
 */
public class Client {

    Socket clientSocket;
    private ObjectInputStream input = null;
    private ObjectOutputStream output = null;

    private Controller initController() throws InterruptedException {
        View v1 = new View();
        View v2 = new View();
        Player p1 = new Player(v1);
        Player p2 = new Player(v2);
        Model model = new Model(v1, v2, p1, p2);
        Controller control = new Controller(model);
        return control;
    }

    public Client(String serverIp) {
        try {
            clientSocket = new Socket(serverIp, 1024);
            System.out.println(clientSocket);
            input = new ObjectInputStream(clientSocket.getInputStream());
            output = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void sendToServer() throws IOException, InterruptedException {
        Controller control = initController();
        output = new ObjectOutputStream(clientSocket.getOutputStream());
        output.writeObject(control);

        output.flush();
        output.close();
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public ObjectInputStream getInputStream() {
        return input;
    }

    public ObjectOutputStream getOutputStream() {
        return output;
    }
}
