import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Optional;

public abstract class PartRepository implements IPartRepository {

    String currentServerName;
    ArrayList<Part> parts = new ArrayList<>();

    public PartRepository(String currentServerName) {
        this.currentServerName = currentServerName;
    }

    public String getCurrentServerName() {
        return currentServerName;
    }

    public Integer getQuantityOfPartsInCurrentRepository() throws RemoteException {
        return parts.size();
    }
    public ArrayList<IPart> getCurrentRepositoryParts() throws RemoteException {
        return this.parts;
    }
    public Optional<IPart> findPartById(Long id) throws RemoteException {
        return parts.stream().filter(part -> part.getId().equals(id)).findFirst();
    }
    public Boolean addNewPartToRepository(IPart part) throws RemoteException {
        return this.parts.add(part);
    }
}
