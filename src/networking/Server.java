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
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import remote_interface.ServerTask;
import remote_interface.Task;

/**
 *
 * @author computer
 */
public class Server implements ServerTask {

    ServerSocket hostServer;
    Socket hostSocket;
    //DataInputStream dis;
    //DataOutputStream dos;

    public Server() {
        super();
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

    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        //String t = in.nextLine();
        //System.out.println(host.getHostSocket().isConnected());

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "server";
            ServerTask host = new Server();
            ServerTask stub = (ServerTask) UnicastRemoteObject.exportObject(host,
                                                                            0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
        } catch (Exception e) {
            System.err.println("Server exception:");
            e.printStackTrace();
        }

    }

    @Override
    public <T> T executeTask(Task<T> t) {
        return t.execute();
    }

}
