import java.rmi.RemoteException;

public class PartRepositoryOne extends PartRepository implements IPartRepository {

    public PartRepositoryOne(String currentServerName) {
        super(currentServerName);
    }

    @Override
    public void printMsg() throws RemoteException {
        System.out.println("PartRepositoryOne");
    }
}
