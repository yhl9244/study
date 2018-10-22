package mybatis.TestMybatis.Test;

import mybatis.TestMybatis.config.AppConfig;
import mybatis.TestMybatis.service.AccountService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by suneee on 2018/10/8.
 */
public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        AccountService accountService = context.getBean(AccountService.class);
        accountService.query();
    }
}
