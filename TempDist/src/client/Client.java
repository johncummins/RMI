// Client.java

package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import server.ExamServer;


public class Client {

    private static int token;
    public static ExamServer server;
    public int getToken() {
        return Client.token;
    }
    public static void setToken(int newToken) {
        token = newToken;
    }


    public static void main(String[] args) {

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "ExamServer";
            Registry registry = LocateRegistry.getRegistry(1099);
            server = (ExamServer) registry.lookup(name);
            //Login.LoginNow();
        } catch (Exception e) {
            System.err.println("Error getting assessment:");
            e.printStackTrace();
        }

        Login newlogin = new Login();
        newlogin.LoginNow();

    }
}
