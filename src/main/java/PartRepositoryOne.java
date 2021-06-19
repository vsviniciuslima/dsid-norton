import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PartRepositoryOne extends PartRepository implements IPartRepository {

    public PartRepositoryOne (String currentServerName, ArrayList<Part> parts) {
        super(currentServerName);
        this.parts = parts;
//        this.parts = populateRepository();
    }

    public PartRepositoryOne(String currentServerName) {
        this.currentServerName = currentServerName;
    }

    @Override
    public void printMsg() throws RemoteException {
        System.out.println("PartRepositoryOne");
    }

/*    public ArrayList<Part> populateRepository() {
        ArrayList<Part> parts = new ArrayList<>();

        parts.add(new Part(0, "parte1", "descrição1", this.currentServerName, null));
        parts.add(new Part(1, "parte2", "descrição2", this.currentServerName, null));;

        return parts;
    }*/

}
