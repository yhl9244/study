package springmybatis.test;

import org.apache.catalina.startup.Tomcat;

public class TomcatServer {

    public static void main(String[] args) {
        try {
            Tomcat tomcat = new Tomcat();
            tomcat.setPort(9090);
            tomcat.addWebapp("/", "D:\\temp\\");
            tomcat.start();
            tomcat.getServer().await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}