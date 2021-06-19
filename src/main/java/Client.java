import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    private Client() {
    }

    public static Registry registry;
    public static IPartRepository currentStub;
    public static Part currentPart;
    public static Scanner stdIn;

    
    public static void main(String[] args) {
        try {
            instantiateRegistryAndScannerAndMakeFirstBind();

            System.out.println("The current server name is " + currentStub.getCurrentServerName());
//            currentStub.printMsg();

            printClientActions();

            String action = stdIn.next();
            while (!action.equals("quit")) {
                switch(action) {
                    case "bind": switchServer();
                        break;
                    case "listp": listp();
                        break;
                    case "getp": setCurrentPart();
                        break;
                    case "showp": showp();
                        break;
                    case "addp": addp();
                        break;
                    case "rename": rename();
                        break;
                    default: printClientActionsWithInvalidAction();
                }
                printClientActions();
                action = stdIn.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void rename() throws RemoteException {
//        currentStub.findPartById(currentPart.getId()).setName(stdIn.next());
        currentStub.rename(stdIn.nextInt(), stdIn.next());
    }

    private static void addp() throws RemoteException {
        currentStub.addNewPartToRepository(currentPart);
    }

    private static void listp() throws RemoteException {
        ArrayList<Part> parts = currentStub.getCurrentRepositoryParts();
        for(Part part : parts) {
            System.out.println(part.toString());
        }
    }

    public static void instantiateRegistryAndScannerAndMakeFirstBind() throws RemoteException, NotBoundException {
        registry = LocateRegistry.getRegistry(null);
        currentStub = (IPartRepository) registry.lookup("PartRepositoryOne");
        stdIn = new Scanner(System.in);
    }

    public static void switchServer() throws NotBoundException, RemoteException {
        System.out.println();
        System.out.println("*************************************************");
        System.out.println("*             Input the server name             *");
        System.out.println("*************************************************");
        System.out.println();
        System.out.print("> ");
        currentStub = (IPartRepository) registry.lookup(stdIn.next());
    }

    public static void printClientActions() {
        System.out.println();
        System.out.println();
        System.out.println("*************************************************");
        System.out.println("** Input an action to interact with the server **");
        System.out.println("*************************************************");
        System.out.print(
                "* 1. bind                                       *\n" +
                "* 2. listp                                      * \n" +
                "* 3. getp                                       * \n" +
                "* 4. showp                                      * \n" +
                "* 5. clearlist                                  * \n" +
                "* 6. addsubpart                                 * \n" +
                "* 7. addp                                       *\n");
        System.out.println("*************************************************");
        System.out.println();
        System.out.println();
        System.out.print("> ");
    }

    public static void setCurrentPart() throws RemoteException {
        System.out.println();
        System.out.println("*************************************************");
        System.out.println("*            Input the id of the part           *");
        System.out.println("*************************************************");
        System.out.println();
        System.out.print("> ");
        Client.currentPart = currentStub.findPartById(stdIn.nextInt());
        System.out.println();
        System.out.println("*************************************************");
        System.out.printf("*            Selected part ID: %s                *%n", currentPart.getId());
        System.out.println("*************************************************");
    }

    public static void showp() {
        System.out.println(Client.currentPart.toString());
        System.out.println("input the new name");
        Client.currentPart.setName(stdIn.next());
        System.out.println(Client.currentPart.toString());
    }

    public static void printClientActionsWithInvalidAction() {
        System.out.println();
        System.out.println();
        System.out.println("*************************************************");
        System.out.println("*                 INVALID ACTION                *");
        System.out.println("*************************************************");
    }
}
