package spring;

import org.springframework.stereotype.Service;
import springmybatis.anno.Autowired;

@Service
public class TestServiceImpl2 implements TestService2 {

    @Autowired
    TestService testService;

    @Override
    public void hello() {
        testService.hello();
    }
}
