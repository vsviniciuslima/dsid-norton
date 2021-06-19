import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Optional;

/*
* the remote interface provides the description of all the methods of a particular object.
* the client communicates with this remote interface
* */
public interface IPartRepository extends Remote {
    public void printMsg() throws RemoteException;
    public String getCurrentServerName() throws RemoteException;
    public Integer getQuantityOfPartsInCurrentRepository() throws RemoteException;
    public ArrayList<Part> getCurrentRepositoryParts() throws RemoteException;
    public Part findPartById(int id) throws RemoteException;
    public Boolean addNewPartToRepository(Part part) throws RemoteException;
    public void rename(int id, String name) throws RemoteException;
}
