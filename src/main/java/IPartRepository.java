import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/*
* the remote interface provides the description of all the methods of a particular object.
* the client communicates with this remote interface
* */
public interface IPartRepository extends Remote {
    String getCurrentServerName() throws RemoteException;
    Integer getQuantityOfPartsInCurrentRepository() throws RemoteException;
    ArrayList<Part> getCurrentRepositoryParts() throws RemoteException;
    Part findPartById(Long id) throws RemoteException;
    void addNewPartToRepository(Part part) throws RemoteException;
    void rename(int id, String name) throws RemoteException;
}
