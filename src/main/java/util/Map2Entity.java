package util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * map转entity工具； 只匹配entity中完全匹配的属性
 * 
 * @author JianGuo.zyj
 * @since 2016年9月28日
 */
public class Map2Entity {

	/**
	 * * Map&lt;String,Object&gt; 装换为 Entity</br>
	 * Exception return null
	 * @param data
	 * @param clazz
	 * @return T
	 */
	public static <T> T transMap2Entity(Map<String, Object> data, Class<T> clazz) {
		if (data == null || data.isEmpty()) {
			return null;
		}
		try {
			T t = clazz.newInstance();
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			String key;
			for (PropertyDescriptor property : propertyDescriptors) {
				key = property.getName();
				if (data.containsKey(key)) {
					Object val = data.get(key);
					Method setter = property.getWriteMethod();
					if(setter == null){
						continue;
					}
					setter.invoke(t, val);
				}
				
			}
			return t;
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Entity 装换为 Map&lt;String,Object&gt;</br>
	 * Exception return null
	 * @param entity
	 * @return map
	 */
	public static Map<String, Object> transEntity2Map(Object entity) {
		return transEntity2Map(entity, null);
	}
	
	/**
	 * Entity 装换为 Map&lt;String,Object&gt;</br>
	 * Exception return null
	 * @param entity
	 * @param unTrans
	 * @return map
	 */
	public static Map<String, Object> transEntity2Map(Object entity, List<String> unTrans) {
		if (entity == null) {
			return null;
		}
		if(unTrans == null){
			unTrans = Lists.newArrayList();
		}
		Map<String, Object> data = Maps.newHashMap();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(entity.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			String key;
			for (PropertyDescriptor property : propertyDescriptors) {
				key = property.getName();
				if ("class".equals(key) || unTrans.contains(key)) {
					continue;
				}
				Method getter = property.getReadMethod();
				if(getter == null){
					continue;
				}
				Object val = getter.invoke(entity);
				data.put(key, val);
				
			}
			return data;
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
}
