package springmybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springmybatis.anno.Autowired;
import springmybatis.service.TestService;

@Controller
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/query.do")
    public String query() {
        testService.query();
        testService.query();
        return "ok";
    }
}
