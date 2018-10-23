package netty.io;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by suneee on 2018/10/23.
 */
public class IOClient {

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8000);
                while (true){
                    try {
                        socket.getOutputStream().write(("hello world : " + new Date()).getBytes());
                        Thread.sleep(2000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
