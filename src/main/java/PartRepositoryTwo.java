import java.rmi.RemoteException;

public class PartRepositoryTwo extends PartRepository implements IPartRepository {

    public PartRepositoryTwo(String currentServerName) {
        super(currentServerName);
    }

    @Override
    public void printMsg() throws RemoteException {
        System.out.println("PartRepositoryTwo");
    }
}
