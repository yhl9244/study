package springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springmvc.service.IndexService;

import javax.annotation.Resource;

/**
 * Created by suneee on 2018/11/9.
 */
@Controller
public class IndexController {

    @Resource
    private IndexService indexService;

    /**
     * value:访问地址
     * produces：解决乱码问题
     * @return
     *
     */
    @RequestMapping(value = "/index1", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String index1() {
        return "手写springboot。。。";
    }

    @RequestMapping(value = "/index2", produces = "text/html;charset=UTF-8")
    public String index2(Model model){
        String show = indexService.show();
        model.addAttribute("str", show);
        return "springboot";
    }
}
