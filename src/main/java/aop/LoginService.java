package aop;

public interface LoginService {

    String login(String userId);

    boolean isLegal(String userId);
}
