package springmybatis.util;

import springmybatis.service.IndexService;
import springmybatis.util.AnnotationConfigApplicationContext;

/**
 * Created by suneee on 2018/10/9.
 */
public class Test {

    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        IndexService indexService = (IndexService) context.getBean("IndexService");
        indexService.index();
        System.out.println(indexService);
    }
}
