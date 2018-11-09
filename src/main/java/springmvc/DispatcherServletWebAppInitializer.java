package springmvc;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by suneee on 2018/11/9.
 */
public class DispatcherServletWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


    @Nullable
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {SpringBeanScanConfig.class};
    }

    @Nullable
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
