import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

public class Server extends PartRepository {

    public static void main(String[] args) {
        try {
            Long serverLong = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
            String serverId = serverLong.toString().substring(0,5);
            String serverName = args.length == 0 ? "server" + serverId : args[0] + serverId;

            IPartRepository serverRepository = new PartRepository(serverName, serverId.toString().substring(0, 5));
            IPartRepository serverRepositoryStub = (IPartRepository) UnicastRemoteObject.exportObject(serverRepository, 0);
            LocateRegistry.getRegistry().rebind(serverName, serverRepositoryStub);

            System.err.println("Server ready!");
            System.err.println("Server name: " + serverName);

        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

}
