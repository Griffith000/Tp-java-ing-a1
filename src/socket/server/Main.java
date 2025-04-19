package socket.server;

import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9000);
            System.out.println("Server started. Waiting for clients...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected");
                Discussion discussion = new Discussion(clientSocket);
                discussion.start();
            }
        } catch (Exception e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}