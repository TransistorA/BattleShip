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

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author computer
 */
public class Server {

    ServerSocket hostServer;
    Socket hostSocket;
    //DataInputStream dis;
    //DataOutputStream dos;

    public Server() {
        try {
            System.out.println("Server Started");
            hostServer = new ServerSocket(1024);
            hostSocket = hostServer.accept();
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
        //Scanner in = new Scanner(System.in);
        //String t = in.nextLine();
        //System.out.println(host.getHostSocket().isConnected());
    }

}
