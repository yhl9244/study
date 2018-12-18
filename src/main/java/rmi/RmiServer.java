package rmi;

import sun.rmi.server.UnicastServerRef;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by suneee on 2018/12/18.
 */
public class RmiServer extends UnicastRemoteObject implements RmiserverIntf {

    private static final String MESSAGE = "Hello World";

    protected RmiServer() throws RemoteException {
        super(0);
    }

    public String getMessage(){
        return MESSAGE;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("RMI Server Started...");

        try {
            LocateRegistry.createRegistry(1099); // 一个JVM可以创建一个记录器
            System.out.println("java RMI registry created....");
        } catch (RemoteException e) {
            //e.printStackTrace();
            System.out.println("java RMI registry already exists....");
        }

        RmiServer obj = new RmiServer();
        // Bind this object instance to the name "RmiServer"
        Naming.rebind("//localhost/RmiServer", obj);//通过Naming源码可知，rebind方法将RmiServer类型的对象记录到了本地记录器
        System.out.println("PeerServer bound in registry");
    }
}
