package socket.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ThreadLecture extends Thread {
    private Socket socket;
    public ThreadLecture(Socket socket) {
        this.socket = socket;
    }
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = br.readLine();
            System.out.println(message);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
