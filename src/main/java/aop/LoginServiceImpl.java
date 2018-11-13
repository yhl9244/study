package aop;

import org.springframework.aop.framework.AopContext;

public class LoginServiceImpl implements LoginService {

    public String login(String useId) {
        if(isLegal(useId)) {
            System.out.println(useId+"正常登录");
            return "success";
        } else {
            System.out.println(useId + "为非法用户,禁止登录");

            return "fail";
        }
        /*LoginService proxy = (LoginService) AopContext.currentProxy();
        if(proxy.isLegal(useId)) {
            System.out.println(useId+"正常登录");
            return "success";
        } else {
            System.out.println(useId + "为非法用户,禁止登录");

            return "fail";
        }*/
    }

    public boolean isLegal(String useId) {
        return !"123".equalsIgnoreCase(useId);
    }
}
