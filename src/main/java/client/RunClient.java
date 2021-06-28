package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class RunClient {

    public static void main(String[] args) throws NotBoundException, RemoteException {

        Scanner stdIn = new Scanner(System.in);
        RMIClient client = new RMIClient();

        client.startClient();
        client.printClientActions();
        System.out.print(">>> ");

        String action = stdIn.next();
        while (!action.equals("quit")) {
            switch(action) {
                case "bind": client.bind();
                    break;
                case "listp": client.listp();
                    break;
                case "getp": client.getp();
                    break;
                case "showp": client.showp();
                    break;
                case "clearlist": client.clearList();
                    break;
                case "addsubpart": client.addSubPart();
                    break;
                case "addp": client.addp();
                    break;
                case "help": client.printClientActions();
                    break;
                case "printservers": client.printAvailableServers();
                    break;
                default: client.printClientActionsWithInvalidAction();
            }
            System.out.println();
            System.out.print(">>> ");
            action = stdIn.next();
        }
    }
}
