package RMIC;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ChatRemote extends Remote{ 

    public void setCh(String ch) throws RemoteException;

    public String getCh() throws RemoteException;
    
    public void addMessage(Message message) throws RemoteException;
    
    public ArrayList<Message> getAllMessages() throws RemoteException;
}
