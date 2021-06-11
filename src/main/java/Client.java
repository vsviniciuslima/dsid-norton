import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    private Client() {
    }

    public static Registry registry;
    public static IPartRepository currentStub;
    public static Part currentPart;

    
    public static void main(String[] args) {
        try {
            instantiateRegistryAndMakeFirstBind();
            Scanner stdin = new Scanner(System.in);

            currentStub.printMsg();
            System.out.println("The current server name is " + currentStub.getCurrentServerName());

            System.out.println("Enter the new server name");

            switchServer(stdin.next());
            currentStub.printMsg();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void instantiateRegistryAndMakeFirstBind() throws RemoteException, NotBoundException {
        registry = LocateRegistry.getRegistry(null);
        currentStub = (IPartRepository) registry.lookup("PartRepositoryOne");
    }

    public static void switchServer(String newServer) throws NotBoundException, RemoteException {
        currentStub = (IPartRepository) registry.lookup(newServer);
    }
}
