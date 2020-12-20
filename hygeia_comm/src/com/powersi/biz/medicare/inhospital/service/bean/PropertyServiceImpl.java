package com.powersi.biz.medicare.inhospital.service.bean;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powersi.biz.medicare.inhospital.service.date.DateService;

/**
 * 
 * @author 刘勇
 *
 */
@Service
public class PropertyServiceImpl implements PropertyService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private DateService dateService;

	@Override
	public void setProperty(Object bean, String field, Object value) {
		this.setProperty(bean, field, value, false);
	}

	@SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
	@Override
	public void setProperty(Object bean, String field, Object value, boolean ignoreNull) {
		try {
			if (StringUtils.isBlank(field)) {
				return;
			}
			field = field.toLowerCase();
			if ("class".equalsIgnoreCase(field)) {
				return;
			}
			if (bean instanceof Map) {
				if (StringUtils.isBlank(ObjectUtils.toString(value)) && ignoreNull) {

				} else {
					if (value instanceof Date) {
						value = this.dateService.dateToString((Date) value, DateService._yyyyMMddHHmmss);
					}
					((Map) bean).put(field, value);
				}
			} else {
				PropertyDescriptor propertyDescriptor = PropertyUtils.getPropertyDescriptor(bean, field);
				if (propertyDescriptor == null) {
					return;
				}
				String simpleName = propertyDescriptor.getPropertyType().getSimpleName();
				if (StringUtils.isBlank(simpleName)) {
					return;
				}
				if (StringUtils.isBlank(ObjectUtils.toString(value))) {
					if (ignoreNull) {

					} else {
						PropertyUtils.setProperty(bean, field, value);
					}
				} else {
					if (simpleName.equalsIgnoreCase("Date") || simpleName.equalsIgnoreCase("Timestamp")) {
						if (value instanceof Date) {
							PropertyUtils.setProperty(bean, field, value);
						} else {
							PropertyUtils.setProperty(bean, field,
									this.dateService.toDate(
											this.dateService.formatDateTime(ObjectUtils.toString(value)),
											DateService._yyyyMMddHHmmss));
						}
					} else {
						if (value instanceof Date) {
							value = this.dateService.dateToString((Date) value, DateService.yyyyMMdd);
						}
						if (simpleName.equalsIgnoreCase("BigDecimal")) {
							if (NumberUtils.isNumber(ObjectUtils.toString(value))) {
								PropertyUtils.setProperty(bean, field, new BigDecimal(ObjectUtils.toString(value)));
							}
						} else if (simpleName.equalsIgnoreCase("String")) {
							PropertyUtils.setProperty(bean, field, ObjectUtils.toString(value));
						} else if (simpleName.equalsIgnoreCase("Integer") || simpleName.equalsIgnoreCase("int")) {
							if (NumberUtils.isNumber(ObjectUtils.toString(value))) {
								PropertyUtils.setProperty(bean, field, Integer.valueOf(ObjectUtils.toString(value)));
							}
						} else if (simpleName.equalsIgnoreCase("Boolean")) {
							PropertyUtils.setProperty(bean, field, Boolean.valueOf(ObjectUtils.toString(value)));
						} else if (simpleName.equalsIgnoreCase("Long")) {
							if (NumberUtils.isNumber(ObjectUtils.toString(value))) {
								PropertyUtils.setProperty(bean, field, Long.valueOf(ObjectUtils.toString(value)));
							}
						} else if (simpleName.equalsIgnoreCase("Float")) {
							if (NumberUtils.isNumber(ObjectUtils.toString(value))) {
								PropertyUtils.setProperty(bean, field, Float.valueOf(ObjectUtils.toString(value)));
							}
						} else if (simpleName.equalsIgnoreCase("Double")) {
							if (NumberUtils.isNumber(ObjectUtils.toString(value))) {
								PropertyUtils.setProperty(bean, field, Double.valueOf(ObjectUtils.toString(value)));
							}
						} else if (simpleName.equalsIgnoreCase("Short")) {
							if (NumberUtils.isNumber(ObjectUtils.toString(value))) {
								PropertyUtils.setProperty(bean, field, Short.valueOf(ObjectUtils.toString(value)));
							}
						} else {
							if (!simpleName.equalsIgnoreCase("Class")) {
								throw new RuntimeException("UnSupport Type " + simpleName);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
