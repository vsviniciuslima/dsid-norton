import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Set;

public interface IPartRepository extends Remote {

    // Métodos relacionados ao servidor
    String getCurrentServerName() throws RemoteException;
    ArrayList<Part> getCurrentRepositoryParts() throws RemoteException;
    Integer getQuantityOfPartsInCurrentRepository() throws RemoteException;
    Long addNewPartToRepository(String name, String description, Set<SubpartContainer> subparts) throws RemoteException;

    // Métodos relacionados às partes
    Part findPartById(Long id) throws RemoteException;
    void addSubParts(Long qtd, Part part) throws RemoteException;
    void clearSubParts(Part part) throws RemoteException;
}
