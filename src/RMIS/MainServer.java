package RMIS;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import RMIC.ChatImplementation;

public class MainServer {
    public static void main(String[] args) {
        try {
            System.out.println("Server started");
            
            LocateRegistry.createRegistry(9002);
            
            String IP = "127.0.0.1:9002";
            String URL = "rmi://" + IP + "/Chat";
            
    
            ChatImplementation chat = new ChatImplementation();
            Naming.rebind(URL, chat);
            
            System.out.println("Chat server ready at: " + URL);
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}