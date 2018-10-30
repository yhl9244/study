package aop;

public class MyCglibTest {

    public static void main(String[] args){
        LoginService loginService = new LoginServiceImpl();

        LoginService proxy = ProxyFactory.createProxy(loginService);

        proxy.login("admin");
    }
}
