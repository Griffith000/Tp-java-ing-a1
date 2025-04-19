
package socket.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("Client starting...");
        Scanner scanner = new Scanner(System.in);
        try {
            Socket socket = new Socket("127.0.0.1", 9000);
            System.out.println("Connected to server");
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = br.readLine();
            System.out.println(message);
            String id = scanner.nextLine();
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println(id);
            pw.flush();     
            scanner.close();
            
            socket.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Client stopped");
    }
}