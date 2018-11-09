package springmvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by suneee on 2018/11/9.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"springmvc.controller"})
public class WebConfig extends WebMvcConfigurationSupport {

    /**
     * 配置springmvc视图解析器
     */
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        // 配置前缀
        viewResolver.setPrefix("/WEB-INF/views/");
        // 配置后缀
        viewResolver.setSuffix(".jsp");
        //可以在jsp页面中通过${}访问beans
        viewResolver.setExposeContextBeansAsAttributes(true);
        return viewResolver;
    }
}
