package socket.client;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ThreadEcriture extends Thread {
    private Socket socket;
    public ThreadEcriture(Socket socket) {
        this.socket = socket;
    }
    public void run() {
        try {
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String message = scanner.nextLine();
                pw.println(message);
                pw.flush();
                
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
