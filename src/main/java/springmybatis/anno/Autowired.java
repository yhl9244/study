package springmybatis.anno;

import java.lang.annotation.*;

/**
 * Created by suneee on 2018/10/9.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
    String value() default "";
}
