package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by suneee on 2018/12/18.
 */
public interface RmiserverIntf extends Remote {

    public String getMessage() throws RemoteException;
}
