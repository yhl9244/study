package rmi;

import java.rmi.Naming;

/**
 * Created by suneee on 2018/12/18.
 */
public class RmiClient {

    public static void main(String args[]) throws Exception {
        //客户端运用了服务器暴露的接口，也叫stub(存根)
        RmiserverIntf obj = (RmiserverIntf) Naming.lookup("//localhost/RmiServer");
        System.out.println(obj.getMessage());
    }
}
