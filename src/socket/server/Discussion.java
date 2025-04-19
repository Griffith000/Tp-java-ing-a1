package socket.server;
import java.net.Socket;

import socket.client.ThreadEcriture;
import socket.client.ThreadLecture;

public class Discussion extends Thread {
    Socket s;
    public Discussion (Socket s){
        this.s=s;
    }
    public void run(){
            try {
          ThreadEcriture threadEcriture = new ThreadEcriture(s);
          ThreadLecture threadLecture = new ThreadLecture(s);
          threadLecture.start();
          threadEcriture.start();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
    
}
