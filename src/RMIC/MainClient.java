package RMIC;

import java.rmi.Naming;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MainClient {

    public static void main(String[] args) {
        try {
            System.out.println("Client starting...");
            String IP = "127.0.0.1:9002";
            String URL = "rmi://" + IP + "/Chat";

            ChatRemote chatRemote = (ChatRemote) Naming.lookup(URL);
            System.out.println("Connected to chat server");

            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error: " + e.getMessage(),
                    "Connection Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}