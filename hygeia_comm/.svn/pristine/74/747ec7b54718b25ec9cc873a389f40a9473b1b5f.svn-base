package com.powersi.biz.medicare.comm.pojo;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.cglib.proxy.Callback;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.powersi.comm.mybatis.Page;

/**
 * 兼容map和mysql分页的DTO
 * 
 * @author lwyao
 * 
 */
@SuppressWarnings("serial")
public class PageMapDTO extends Page implements Map<String, Object> {

	private Map<String, Object> _param = new LinkedHashMap<>();

	private Object synchronizationBean(Object key, Object value, int opt) {
		Object ret = null;
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(this.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				if (opt == 2) {
					Object o = property.getReadMethod().invoke(this, new Object[] {});
					if (o == null)
						_param.remove(property.getName());
					else
						_param.put(property.getName(), o);
					ret = (Map<String, Object>) this;
				} else if (property.getName().equals(key)) {
					Method method = null;
					if (opt == 0) {
						method = property.getReadMethod();
						if (method != null) {
							ret = method.invoke(this, new Object[] {});
						}
					} else if (opt == 1) {
						method = property.getWriteMethod();
						if (method != null) {
							if (!method.getParameterTypes()[0].isArray() && value instanceof Object[]) {
								Object[] vs = (Object[]) value;
								value = method.getParameterTypes()[0] == int.class ? Integer.parseInt(vs[vs.length - 1].toString())
										: vs[vs.length - 1];
							} else if (method.getParameterTypes()[0] == int.class && value instanceof String) {
								value = Integer.parseInt(value.toString());
							}
							ret = method.invoke(this, new Object[] { value }) == null ? "set" : "set";
						}
					}
					return ret;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int size() {
		toJson();
		return _param.size();
	}

	@Override
	public boolean isEmpty() {
		return _param.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return _param.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return _param.containsValue(value);
	}

	@Override
	public Object get(Object key) {
		return _param.get(key) != null ? _param.get(key) : synchronizationBean(key, null, 0);
	}

	@Override
	public Object put(String key, Object value) {
		return "set".equals(synchronizationBean(key, value, 1)) ? null
				: value instanceof Object[] && ((Object[]) value).length == 1 ? _param.put(key, ((Object[]) value)[0]) : _param.put(key, value);
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		m.forEach((key, value) -> this.put(key, value));
	}

	@SuppressWarnings("unchecked")
	public <T> T putAllRetBean(Map<String, Object> m) {
		if (m instanceof PageMapDTO)
			((PageMapDTO) m).toJson();
		this.putAll(m);
		return (T) this;
	}

	@Override
	public Object remove(Object key) {
		this.put((String) key, null);
		return _param.remove(key);
	}

	@Override
	public void clear() {
		_param.clear();
	}

	@Override
	public Set<String> keySet() {
		return _param.keySet();
	}

	@Override
	public Collection<Object> values() {
		return _param.values();
	}

	@Override
	public Set<Entry<String, Object>> entrySet() {
		return _param.entrySet();
	}

	public String toString() {
		return toJson();
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> toMap() {
		return (Map<String, Object>) synchronizationBean(null, null, 2);
	}

	public String toJson() {
		Gson gson = (new GsonBuilder()).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		Map<String, Object> map = toMap();
		if (map.get("callbacks") instanceof Callback[])
			map.remove("callbacks");
		map.remove("empty");
		map.remove("class");
		return gson.toJson(map);
	}

	public boolean compareTo(Object o) {
		return toString().equals(o.toString());
	}

}
