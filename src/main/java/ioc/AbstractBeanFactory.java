package ioc;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by suneee on 2018/9/18.
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    /**
     * 容器
     */
    private Map<String, BeanDefinition> map = new HashMap<String, BeanDefinition>();

    /**
     * 根据bean的名称获取bean， 如果没有，则抛出异常 如果有， 则从bean定义对象获取bean实例
     */
    public Object getBean(String name) throws Exception {
        BeanDefinition beanDefinition = map.get(name);
        if(beanDefinition == null){
            throw new IllegalArgumentException("No bean named " + name + " is defined");
        }
        Object bean = beanDefinition.getBean();
        if(bean == null){
            bean = doCreate(beanDefinition);
        }
        return bean;
    }

    /**
     * 注册 bean定义 的抽象方法实现，这是一个模板方法， 调用子类方法doCreate，
     */
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception {
        Object bean = doCreate(beanDefinition);
        beanDefinition.setBean(bean);
        map.put(name,beanDefinition);
    }

    /**
     * 减少一个bean
     */
    abstract Object doCreate(BeanDefinition beandefinition) throws Exception;
}
