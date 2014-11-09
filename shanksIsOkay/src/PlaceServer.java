import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class PlaceServer {
    public static void main(String args[]) {
        if (args.length != 1) {
            System.err.println("usage: java SampleServer rmi_port");
            System.exit(1);
        }
        try {
			LocateRegistry.createRegistry(1099);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        // Create and install a security manager
       // if (System.getSecurityManager() == null)
         //   System.setSecurityManager(new RMISecurityManager());
        try {
            // first command-line argument is the port of the rmiregistry
            int port = Integer.parseInt(args[0]);
            String url = "//localhost:" + port + "/Places";
            System.out.println("binding " + url);
            
            Naming.rebind(url, new Places());
            
            // Naming.rebind("Sample", new Sample());
            System.out.println("server " + url + " is running...");
        }
        catch (Exception e) {
            System.out.println("Sample server failed:" + e.getMessage());
        }
    }
}
