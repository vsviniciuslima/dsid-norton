import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

public class RMIClient implements IClient {

    public static Scanner stdIn = new Scanner(System.in);
    public Registry registry;
    public IPartRepository currentStub;
    public Part currentPart;
    public List<String> availabLeServerNames = new ArrayList<>();
    public Map<String, String> serverIds = new HashMap<>();

    public void startClient() throws RemoteException, NotBoundException {
        registry = LocateRegistry.getRegistry(null);

/*        for(String server : registry.list()) {
            registry.unbind(server);
        }*/

        bind();
    }

    public void bind() throws NotBoundException, RemoteException {
        printAvailableServers();
        decorateText("Insira o nome do servidor desejado");
        System.out.print(">>> ");
        currentStub = (IPartRepository) registry.lookup(stdIn.next());
    }

    public void forceBind(String serverName) throws NotBoundException, RemoteException {
        currentStub = (IPartRepository) registry.lookup(serverName);
    }

    public void rename() throws RemoteException {
//        currentStub.findPartById(currentPart.getId()).setName(stdIn.next());
        currentStub.rename(stdIn.nextInt(), stdIn.next());
    }

    public void addp() throws RemoteException {
        currentStub.addNewPartToRepository(currentPart);
    }

    public void listp() throws RemoteException {
        ArrayList<Part> parts = currentStub.getCurrentRepositoryParts();
        decorateText("Peças:");
        for(Part part : parts) {
            System.out.println("* Peça: " + part.getName() + ", ID: " + part.getId());
        }
        System.out.println("*************************************************");
    }

    public void getp() throws RemoteException {
        decorateText("Insira o id da Parte");
        System.out.print(">>> ");
        this.currentPart = currentStub.findPartById(stdIn.nextLong());
        decorateText("Id da peça selecionada: " + currentPart.getId());
    }

/*    public Part findPartThroughRepositories(Long id, ArrayList<String> possibleServerNames) throws RemoteException, NotBoundException {
        String currentServerName = currentStub.getCurrentServerName();
        if(currentStub.findPartById(id).isPresent()) {
            return currentStub.findPartById(id).get();
        } else {
            possibleServerNames.removeIf(serverName -> serverName.equals(currentServerName));
            for(String server : possibleServerNames) {
                currentStub = (IPartRepository) registry.lookup(server);
                return findPartThroughRepositories(id, possibleServerNames);
            }
        }
        return null;
    }*/



    public void newServerCreated(String serverName) throws RemoteException {
        this.availabLeServerNames.add(serverName);
    }

    public void printAvailableServers() throws RemoteException {
        getServerIds();
        decorateText("Os servidores disponíveis são:");
        availabLeServerNames.clear();
        availabLeServerNames.addAll(Arrays.asList(registry.list()));
        availabLeServerNames.forEach(server -> System.out.println("* " + server));
    }

    /* cli methods */
    public void printClientActions() {
        decorateText("Insira o comando desejado");
        System.out.println("* bind");
        System.out.println("* listp");
        System.out.println("* getp");
        System.out.println("* showp");
        System.out.println("* clearlist");
        System.out.println("* addsubpart");
        System.out.println("* addp");
        System.out.println("* printservers");
        System.out.println("*************************************************");
    }

    public void printClientActionsWithInvalidAction() {
        decorateText("Invalid Action");
    }

    public static void decorateText(String text) {
        System.out.println();
        System.out.println("*************************************************");
        System.out.println(text);
        System.out.println("*************************************************");
    }


    public void showp() throws RemoteException, NotBoundException {
        try {
            this.currentPart.printPartInfo();
            printSubParts();
        } catch (NullPointerException e) {
            System.out.println("Você não selecionou nenhuma parte");
        }
    }

    public void printSubParts() throws RemoteException, NotBoundException {
        for(Long[] part : this.currentPart.getSubParts()) {
            Long qtd = part[0];
            Long partId = part[1];

            switchToCurrentPartServer(partId);
            System.out.println("quantidade: " + qtd + ", peça: " + currentStub.findPartById(partId));
        }
    }

    public String getServerNameFromPartId(String serverId) throws RemoteException {
        getServerIds();
        return this.serverIds.get(serverId);
    }

    public void getServerIds() throws RemoteException {
        availabLeServerNames.clear();
        availabLeServerNames.addAll(Arrays.asList(registry.list()));
        availabLeServerNames.forEach(server -> {
            this.serverIds.put(server.substring(server.length() - 5, server.length()), server);
            System.out.println(server.substring(server.length() - 5 ,server.length()));
        });
    }

    public String extractId(String input) {
        return input.substring(0,5);
    }
    public void clearList() throws RemoteException, NotBoundException {
        switchToCurrentPartServer(currentPart.getId());
        currentStub.findPartById(currentPart.getId()).clearSubParts();
    }

    public void switchToCurrentPartServer(Long id) throws RemoteException, NotBoundException {
        String partId = id.toString().substring(0, 5);
        if(!currentStub.getCurrentServerName().equals(getServerNameFromPartId(partId))) {
            forceBind(getServerNameFromPartId(partId));
        }
    }

    public void addSubPart() throws NotBoundException, RemoteException {
        decorateText("Insira a quantidade de sub-partes a serem inseridas");
//        switchToCurrentPartServer(currentPart.getId());
        currentStub.findPartById(currentPart.getId()).addSubParts(stdIn.nextLong(), currentPart.getId());
        System.out.println("novo nome");
        currentStub.findPartById(currentPart.getId()).setName(stdIn.next());
    }
}
