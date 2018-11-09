package springmvc;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import javax.servlet.ServletException;
import java.io.File;

/**
 * Created by suneee on 2018/11/9.
 */
public class SpringbootApp {

    public static void main(String[] args) throws ServletException, LifecycleException {
        // 创建tomcat容器
        Tomcat tomcat = new Tomcat();
        // 设置端口号
        tomcat.setPort(8080);
        // 读取项目路径,加载静态资源
        StandardContext context = (StandardContext) tomcat.addWebapp("/", new File("src/main").getAbsolutePath());
        // 禁止重新载入
        context.setReloadable(false);
        // class文件读取地址
        File additionWebInfClasses = new File("target/class");
        // 创建Webroot
        WebResourceRoot resources = new StandardRoot(context);
        // tomcat内部读取Class执行(/WEB-INF/classes虚拟出来的路劲)
        resources.addPreResources(
                new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
        tomcat.start();
        // 异步等待请求执行
        tomcat.getServer().await();
    }
}
