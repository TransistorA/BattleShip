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
package networking;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author computer
 */
public class Client {

    Socket clientSocket;
    DataInputStream din;
    DataOutputStream dout;

    public Client(String ipAddress) {
        try {
            //s=new Socket("10.10.0.3,10");
            clientSocket = new Socket(ipAddress, 1024);
            System.out.println(clientSocket);
            //din = new DataInputStream(clientSocket.getInputStream());
            //dout = new DataOutputStream(clientSocket.getOutputStream());
            //ClientChat();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void ClientChat() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1;
        do {
            s1 = br.readLine();
            dout.writeUTF(s1);
            dout.flush();
            System.out.println("Server Message:" + din.readUTF());
        } while (!s1.equals("stop"));
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public static void main(String as[]) {
        //new Client();
    }
}
