package springmybatis.anno;

import java.lang.annotation.*;

/**
 * Created by suneee on 2018/10/9.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Compoment {
    String value() default "";
}
