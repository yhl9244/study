package spring;

import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Override
    public void hello() {
        System.out.println("我爱你");
    }
}
