package com.powersi.ssm.biz.medicare.common.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.lang.reflect.Field;

public class Utils {
	
	/**
	 * Map转javaBean
	 * @param map
	 * @param obj
	 * @param isIgnoreCase
	 * @return
	 */
	public static <T> T mapToBean(Map<String, Object> map, Object obj, boolean... isIgnoreCase) {
		if (map == null || obj == null) {
			return null;
		}
		if (isIgnoreCase == null || isIgnoreCase.length == 0)
			isIgnoreCase = new boolean[] { false };
		if (obj instanceof Class) {
			try {
				obj = ((Class<?>) obj).newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		try {
			if (isIgnoreCase != null && isIgnoreCase.length > 0 && isIgnoreCase[0]) {
				Map<String, Object> temp = new HashMap<String, Object>();
				for (Entry<String, Object> entry : map.entrySet()) {
					temp.put(entry.getKey().toLowerCase(), entry.getValue());
				}
				map.putAll(temp);
			}
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = isIgnoreCase != null && isIgnoreCase[0] ? property.getName().toLowerCase() : property.getName();
				if (map.containsKey(key)) {
					Object value = map.get(key);
					Method setter = property.getWriteMethod();
					if (property.getPropertyType() == String.class && value instanceof Number)
						value = value + "";
					if ((property.getPropertyType() == Integer.class || property.getPropertyType() == int.class) && value instanceof String)
						value = Integer.parseInt((String) value);
					if (value != null && property.getPropertyType() == BigDecimal.class && !(value instanceof BigDecimal))
						value = new BigDecimal(value + "");
					if (value instanceof Map && property.getPropertyType() != Map.class)
						value = mapToBean((Map<String, Object>) value, property.getPropertyType(), true);
					if (setter != null) {
						try {
							setter.invoke(obj, value);
						} catch (Exception e) {
							System.out.println("字段[" + property.getName() + "]转[" + obj.getClass() + "]时错误");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (T) obj;
	}
	
	
	public static HashMap<String, Object> convertToMap(Object obj)
            throws Exception {

        HashMap<String, Object> map = new HashMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            boolean accessFlag = fields[i].isAccessible();
            fields[i].setAccessible(true);

            Object o = fields[i].get(obj);
            if (o != null)
                map.put(varName, o.toString());

            fields[i].setAccessible(accessFlag);
        }

        return map;
    }
}
