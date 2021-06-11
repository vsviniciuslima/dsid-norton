import java.util.ArrayList;

public abstract class PartRepository implements IPartRepository {

    String currentServerName;
    ArrayList<Part> parts = new ArrayList<>();

    public PartRepository(String currentServerName) {
        this.currentServerName = currentServerName;
    }

    public String getCurrentServerName() {
        return currentServerName;
    }
}
