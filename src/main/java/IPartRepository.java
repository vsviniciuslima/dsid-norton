import java.rmi.Remote;
import java.rmi.RemoteException;

/*
* the remote interface provides the description of all the methods of a particular object.
* the client communicates with this remote interface
* */
public interface IPartRepository extends Remote {
    void printMsg() throws RemoteException;
    String getCurrentServerName() throws RemoteException;
}
