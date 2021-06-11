import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

public class PartRepositoryOne extends PartRepository implements IPartRepository {

    public PartRepositoryOne(String currentServerName) {
        super(currentServerName);
        this.parts = populateRepository();
    }

    @Override
    public void printMsg() throws RemoteException {
        System.out.println("PartRepositoryOne");
    }

    public ArrayList<Part> populateRepository() {
        return (ArrayList<Part>) Arrays.asList(
                new Part(),
                new Part()
        );
    }
}
