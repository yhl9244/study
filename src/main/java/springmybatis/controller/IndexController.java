package springmybatis.controller;

import springmybatis.anno.Autowired;
import springmybatis.anno.Compoment;
import springmybatis.service.IndexService;

@Compoment
public class IndexController {

    @Autowired
    private IndexService indexService;

    public void index() {
        indexService.index();
    }
}
