package springmvc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by suneee on 2018/11/9.
 */
@Configuration
@ComponentScan(basePackages = {"springmvc.service"})
public class SpringBeanScanConfig {
}
