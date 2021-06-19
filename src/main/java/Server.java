import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server extends PartRepositoryOne {

    public static ArrayList<IPartRepository> availableServers = new ArrayList<>();

    public Server() {
        super("PartRepositoryOne");
    }

    public static void main(String[] args) {
        try {
            // Instantiating the implementation class
            IPartRepository partRepositoryOne = new PartRepositoryOne("PartRepositoryOne", populateRepositoryOne());
            IPartRepository partRepositoryTwo = new PartRepositoryOne("PartRepositoryTwo", populateRepositoryTwo());
//            IPartRepository partRepositoryTwo = new PartRepositoryTwo("PartRepositoryTwo");
            availableServers.add(partRepositoryOne);
            availableServers.add(partRepositoryTwo);

            // Exporting the object of implementation class
            // (here we are exporting the remote object to the stub
            IPartRepository partRepositoryOneStub = (IPartRepository) UnicastRemoteObject.exportObject(partRepositoryOne, 0);
            IPartRepository partRepositoryTwoStub = (IPartRepository) UnicastRemoteObject.exportObject(partRepositoryTwo, 0);

            // Binding the remote object (stub) in the registry
            Registry registry = LocateRegistry.getRegistry();


            registry.rebind("PartRepositoryOne", partRepositoryOneStub);
            registry.rebind("PartRepositoryTwo", partRepositoryTwoStub);

            System.err.println("Server ready!");
            printAvailableServers();

        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public static ArrayList<Part> populateRepositoryOne() {
        ArrayList<Part> parts = new ArrayList<>();

        parts.add(new Part(0, "parte1", "descrição1", "one", null));
        parts.add(new Part(1, "parte2", "descrição2", "one", null));;

        return parts;
    }

    public static ArrayList<Part> populateRepositoryTwo() {
        ArrayList<Part> parts = new ArrayList<>();

        parts.add(new Part(3, "parte3", "descrição3", "two", null));
        parts.add(new Part(4, "parte4", "descrição4", "two", null));;

        return parts;
    }

    public static void printAvailableServers() {
        System.out.println();
        System.out.println("*************************************************");
        System.out.println("*           The available servers are:          *");
        System.out.println("*************************************************");
        System.out.println("*                                               *");
        for(IPartRepository server : availableServers) {
            try {
                System.out.println("*               " + server.getCurrentServerName() + "               *");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        System.out.println("*                                               *");
        System.out.println("*************************************************");
        System.out.println();
        System.out.print("> ");
    }
}
