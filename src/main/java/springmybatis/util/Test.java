package springmybatis.util;

import springmybatis.controller.IndexController;
import springmybatis.service.IndexService;

/**
 * Created by suneee on 2018/10/9.
 */
public class Test {

    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        IndexController indexController = (IndexController) context.getBean("IndexController");
        indexController.index();
        System.out.println(indexController);
    }
}
