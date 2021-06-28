package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClient extends Remote {

    void newServerCreated(String serverName) throws RemoteException;
}
