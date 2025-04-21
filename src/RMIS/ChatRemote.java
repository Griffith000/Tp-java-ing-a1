package RMIS;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import RMIC.Message;

public interface ChatRemote extends Remote{ 

    public void setCh(String ch) throws RemoteException;

    public String getCh() throws RemoteException;
    
    
    
    public ArrayList<Message> getAllMessages() throws RemoteException;
}
