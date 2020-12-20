package com.powersi.biz.medicare.inhospital.service.bean;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author 刘勇
 *
 */
@Service
public class BeanServiceImpl implements BeanService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private PropertyService propertyService;

	@SuppressWarnings("rawtypes")
	@Override
	public void copyProperties(Object source, Object target) {
		if (source == null || target == null) {
			return;
		}
		if (target instanceof Map) {
			this.copyPropertiesAll(source, target, false);
		} else {
			Map temp = new HashMap();
			this.copyPropertiesAll(source, temp, false);
			this.copyPropertiesAll(temp, target, false);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void copyProperties(Object source, Object target, boolean ignoreNull) {
		if (source == null || target == null) {
			return;
		}
		if (target instanceof Map) {
			this.copyPropertiesAll(source, target, ignoreNull);
		} else {
			Map temp = new HashMap();
			this.copyPropertiesAll(source, temp, ignoreNull);
			this.copyPropertiesAll(temp, target, ignoreNull);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void copyPropertiesAll(Object source, Object target, boolean ignoreNull) {
		try {
			if (source instanceof Map) {
				Set<Entry<String, Object>> entrySet = ((Map) source).entrySet();
				Entry<String, Object> entry = null;
				for (Iterator<Entry<String, Object>> iterator = entrySet.iterator(); iterator.hasNext();) {
					entry = iterator.next();
					this.propertyService.setProperty(target, entry.getKey(), entry.getValue(), ignoreNull);
				}
			} else {
				PropertyDescriptor[] sourceProperties = PropertyUtils.getPropertyDescriptors(source);
				String sourcefieldName = null;
				Object sourcefieldValue = null;
				for (int i = 0; i < sourceProperties.length; i++) {
					sourcefieldName = sourceProperties[i].getName();
					sourcefieldValue = PropertyUtils.getProperty(source, sourcefieldName);
					this.propertyService.setProperty(target, sourcefieldName, sourcefieldValue, ignoreNull);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	public void copyProperties(Object source, Object target, String[] propertyName) {
		if (source == null || target == null) {
			return;
		}
		if (target instanceof Map) {
			this.copyPropertiesAll(source, target, propertyName);
		} else {
			Map temp = new HashMap();
			this.copyPropertiesAll(source, temp, propertyName);
			this.copyPropertiesAll(temp, target, propertyName);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void copyPropertiesAll(Object source, Object target, String[] propertyName) {
		try {
			if (source instanceof Map) {
				Set<Entry<String, Object>> entrySet = ((Map) source).entrySet();
				Entry<String, Object> entry = null;
				for (Iterator<Entry<String, Object>> iterator = entrySet.iterator(); iterator.hasNext();) {
					entry = iterator.next();
					for (String colName : propertyName) {
						if (entry.getKey().equals(colName)) {
							this.propertyService.setProperty(target, entry.getKey(), entry.getValue(), false);
						}
					}
				}
			} else {
				PropertyDescriptor[] sourceProperties = PropertyUtils.getPropertyDescriptors(source);
				String sourcefieldName = null;
				Object sourcefieldValue = null;
				for (int i = 0; i < sourceProperties.length; i++) {
					sourcefieldName = sourceProperties[i].getName();
					for (String colName : propertyName) {
						if (sourcefieldName.equals(colName)) {
							sourcefieldValue = PropertyUtils.getProperty(source, sourcefieldName);
							this.propertyService.setProperty(target, sourcefieldName, sourcefieldValue, false);
						}
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
