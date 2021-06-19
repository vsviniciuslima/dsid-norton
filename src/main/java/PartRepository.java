import java.rmi.RemoteException;
import java.rmi.activation.Activatable;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Optional;

public abstract class PartRepository implements IPartRepository {

    String currentServerName;
    ArrayList<Part> parts = new ArrayList<>();
    public PartRepository(String currentServerName) {
        this.currentServerName = currentServerName;
    }

    public PartRepository() { }

    public String getCurrentServerName() {
        return currentServerName;
    }

    public Integer getQuantityOfPartsInCurrentRepository() throws RemoteException {
        return parts.size();
    }
    public ArrayList<Part> getCurrentRepositoryParts() throws RemoteException {
        return this.parts;
    }
    public Part findPartById(int id) throws RemoteException {
        return parts.stream().filter(part -> part.getId() == id).findFirst().get();
    }
    public Boolean addNewPartToRepository(Part part) throws RemoteException {
        return this.parts.add(part);
    }

    public void rename(int id, String name) throws RemoteException {
        this.parts.get(id).setName(name);
    }
}
