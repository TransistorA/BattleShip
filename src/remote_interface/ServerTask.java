/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Joseph DiPalma, Annan Miao, and Ben Xu
* Date: Apr 30, 2017
* Time: 10:28:50 PM
*
* Project: csci205finalproject
* Package: networking
* File: ServerTask
* Description:
*
* ****************************************
 */
package remote_interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author jrd028
 */
public interface ServerTask extends Remote {

    <T> T executeTask(Task<T> t) throws RemoteException;
}
