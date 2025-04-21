package RMIC;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ChatImplementation extends UnicastRemoteObject implements ChatRemote {
    private ArrayList<Message> messages;
    private String channel;
    
    public ChatImplementation() throws RemoteException {
        messages = new ArrayList<Message>();
        channel = "default";
    }
    
    @Override
    public void addMessage(Message message) throws RemoteException {
        messages.add(message);
        System.out.println("New message from " + message.getPseudo() + ": " + message.getBody());
    }

    @Override
    public ArrayList<Message> getAllMessages() throws RemoteException {
        return messages;
    }

    @Override
    public void setCh(String ch) throws RemoteException {
        this.channel = ch;
        System.out.println("Chat channel set to: " + ch);
    }

    @Override
    public String getCh() throws RemoteException {
        return this.channel;
    }
}
