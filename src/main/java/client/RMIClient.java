package client;

import model.Part;
import repository.IPartRepository;

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

    // Métodos Auxiliares
    public void newServerCreated(String serverName) throws RemoteException {
        this.availabLeServerNames.add(serverName);
    }

    // Métodos relacionados ao servidor
    public void startClient() throws RemoteException, NotBoundException {
        registry = LocateRegistry.getRegistry(null);
        bind();
    }
    public void bind() throws RemoteException {
        printAvailableServers();
        decorateText("Insira o nome do servidor desejado");
        System.out.print(">>> ");
        try {
            currentStub = (IPartRepository) registry.lookup(stdIn.next());
            currentStub.getCurrentServerName();
        } catch (Exception e) {
            System.err.println("Servidor inválido ou não disponível. Por favor, escolha outro servidor.");
        }
    }
    public void forceBind(String serverName) throws NotBoundException, RemoteException {
        try {
            currentStub = (IPartRepository) registry.lookup(serverName);
        } catch (Exception e) {
            bind();
        }
    }
    public void switchToCurrentPartServer(Long id) throws RemoteException, NotBoundException {
        String partId = id.toString().substring(0, 5);
        if(!currentStub.getCurrentServerName().equals(getServerNameFromPartId(partId))) {
            forceBind(getServerNameFromPartId(partId));
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
        });
    }
    public void printAvailableServers() throws RemoteException {
        getServerIds();
        decorateText("Os servidores disponíveis são:");
        availabLeServerNames.clear();
        availabLeServerNames.addAll(Arrays.asList(registry.list()));
        availabLeServerNames.forEach(server -> System.out.println("* " + server));
    }

    // Métodos relacionados às partes
    public void listp() {
        try {
            ArrayList<Part> parts = currentStub.getCurrentRepositoryParts();
            decorateText("Peças:");
            for(Part part : parts) {
                System.out.println("* Peça: " + part.getName() + ", ID: " + part.getId());
            }
            System.out.println("*************************************************");
            System.out.println("* Nome do repositório: " + currentStub.getCurrentServerName());
            System.out.println("* Quantidade de peças no repositório: " + currentStub.getQuantityOfPartsInCurrentRepository());
        } catch (RemoteException e) {
            System.out.println("Você não selecionou nenhum servidor");
        }

    }
    public void getp() throws RemoteException {
        decorateText("Insira o id da Parte");
        System.out.print(">>> ");
        this.currentPart = currentStub.findPartById(stdIn.nextLong());
        decorateText("Id da peça selecionada: " + currentPart.getId());
    }
    public void showp() throws RemoteException, NotBoundException {
        try {
            currentStub.findPartById(currentPart.getId()).printPartInfo();
        } catch (NullPointerException e) {
            System.err.println("Você não selecionou nenhuma parte");
        } catch (NoSuchElementException e) {
            String previousStub = currentStub.getCurrentServerName();
            switchToCurrentPartServer(currentPart.getId());
            currentStub.findPartById(currentPart.getId()).printPartInfo();
            forceBind(previousStub);
        }
    }
    public void clearList() throws RemoteException, NotBoundException {
        switchToCurrentPartServer(currentPart.getId());
        currentStub.clearSubParts(this.currentPart);
        this.currentPart = currentStub.findPartById(this.currentPart.getId());
    }
    public void addSubPart() throws RemoteException {
        decorateText("Insira a quantidade de sub-partes a serem inseridas");
        currentStub.addSubParts(stdIn.nextLong(), this.currentPart);
        this.currentPart = currentStub.findPartById(this.currentPart.getId());
    }
    public void addp() throws RemoteException {
        System.out.print("Insira o nome da nova peça: ");
        String name = stdIn.next();
        System.out.print("Insira a descrição da nova peça: ");
        String description = stdIn.next();
        try {
            currentStub.addNewPartToRepository(name, description, this.currentPart.getSubParts());
//            currentStub.findPartById(newPartId).setSubParts(this.currentPart.getSubParts());
        } catch (NullPointerException e) {
            currentStub.addNewPartToRepository(name, description, new HashSet<>());
            System.err.println("A lista de subparts atual está vazia. Logo, a parte foi criada sem sub-peças.");
        }
    }

    // Métodos relacionados ao CLI
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
        decorateText("Ação inválida");
    }
    public static void decorateText(String text) {
        System.out.println();
        System.out.println("*************************************************");
        System.out.println(text);
        System.out.println("*************************************************");
    }

}
