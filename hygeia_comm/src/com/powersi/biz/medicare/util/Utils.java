package com.powersi.biz.medicare.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

/**
 * 通用工具类 2017年5月10日上午10:40:55
 *
 * @author lwyao
 *
 */
public abstract class Utils {

	private static final char[] CHINESENUM = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' };
	private static final char[] CHINESEUNIT = { '分', '角', '元', '拾', '佰', '仟', '万', '拾', '佰', '仟', '亿', '拾', '佰', '仟' };

	/** 计算方法calculator统计时忽略字段 */
	public final static String SUM_IGNORE_FIELD_KEY = "sum_ignore_field";
	/** 计算方法calculator需要统计的字段 */
	public final static String SUM_FIELD_KEY = "sum_field";
	/** JS计算引擎 */
	public final static ScriptEngine JEM = new ScriptEngineManager().getEngineByName("JavaScript");
	final static DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,##0.00");
	static {
		DECIMAL_FORMAT.setRoundingMode(RoundingMode.HALF_UP);
		try {
			JEM.eval(new InputStreamReader(Utils.class.getResourceAsStream("math.min.js")));
			JEM.eval("math.config({number: 'BigNumber'})");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据公式计算结果保存到每个list的map中
	 * 
	 * @author lwyao
	 * @param formula (可支持简单的JS运算和赋值判断,赋值判断如flag==1?'1':flag==2?'2':'3';保留key:
	 *                sum_ignore_field[统计时忽略字段,多个字段以”,“分隔]，sum_field[需要统计的字段])，二选一，都不传时，字段默认都统计
	 * @param datas
	 * @param isTotal (是否统计)
	 * @return
	 */
	public static Map<String, Object> calculator(Map<String, String> formula, List<Map<String, Object>> datas, boolean isTotal) {
		Map<String, Object> sum = new HashMap<>();
		String sumFieldStr = formula.get(SUM_FIELD_KEY), sumIgnoreFieldStr = formula.get(SUM_IGNORE_FIELD_KEY);
		formula.remove(SUM_FIELD_KEY);
		List<String> sumFields = Arrays.asList(sumFieldStr == null ? new String[] {} : sumFieldStr.split(",|;"));
		formula.remove(SUM_IGNORE_FIELD_KEY);
		List<String> sumIgnoreFields = Arrays.asList(sumIgnoreFieldStr == null ? new String[] {} : sumIgnoreFieldStr.split(",|;"));
		String intRegex = "^[-]?\\d{1,9}$", /* 整数类型正则表达式 */ moneyRegex = "^[-]?\\d{1,}\\.\\d{1,}$";/* 货币类型正则表达式 */
		Set<String> avg = new HashSet<>();
		for (int i = 0; i < datas.size(); i++) {
			Map<String, Object> data = datas.get(i);
			for (String key : formula.keySet()) {
				String strFormula = formula.get(key);
				if (key.matches("^.*%$")) {
					key = key.replaceAll("%$", "");
					if (!avg.contains(key)) {
						avg.add(key);
					}
				}
				if (key.equals("index")) {
					data.put(key, i + 1);
				} else if (strFormula != null) {
					strFormula = replaceFormula(strFormula, data);
					Object val = null;
					if (strFormula.matches(moneyRegex) || strFormula.matches(intRegex)) { // 数字类型
						val = DECIMAL_FORMAT.format(new BigDecimal(strFormula));
					} else { // 通过JS计算引擎进行简单计算
						try {
							val = JEM.eval("math.parser().eval('" + strFormula + "')");
						} catch (Exception e) {
							try {
								val = JEM.eval(strFormula);
							} catch (ScriptException e1) {
								val = 0;
								e1.printStackTrace();
							}
						}
					}
					if (val instanceof BigDecimal) {
						data.put(key, DECIMAL_FORMAT.format(val));
					} else if (val instanceof ScriptObjectMirror) {
						String temp = DECIMAL_FORMAT.format(((ScriptObjectMirror) val).to(Number.class));
						data.put(key, "�".equals(temp) ? "0.00" : temp);
					} else {
						data.put(key, val);
					}
				}
			}
			if (isTotal) { // 汇总计算
				for (String key : data.keySet()) {
					Object val = null;
					if (sumIgnoreFields.contains(key)) // 这些字段跳出统计
						continue;
					if (sumFields.size() > 0 && !sumFields.contains(key)) // 这些字段需要统计
						continue;
					val = data.get(key) instanceof String ? data.get(key).toString().replaceAll(",", "") : data.get(key);
					if (val == null)
						continue;
					String k = "sum_" + key;
					Number n = sum.get(k) instanceof Number ? (Number) sum.get(k) : 0;
					if (val instanceof BigDecimal || val instanceof Double || val instanceof Float || n instanceof BigDecimal
							|| (val instanceof String && val.toString().matches(moneyRegex))) {
						n = new BigDecimal(n.toString()).add(new BigDecimal(val.toString()));
					} else if (val instanceof Integer || val instanceof Long || (val instanceof String && val.toString().matches(intRegex))) {
						n = n.longValue() + Long.parseLong(val.toString());
					} else {
						continue;
					}
					sum.put(k, n);
				}
			}
		}
		sum.forEach((key, val) -> sum.put(key, val instanceof BigDecimal ? DECIMAL_FORMAT.format(val) : val));
		if (isTotal) { // 平均计算
			for (String key : avg) {
				String sumKey = "sum_" + key;
				if (sum.containsKey(sumKey)) {
					sum.put("avg_" + key, new BigDecimal(sum.get(sumKey).toString()).divide(new BigDecimal(datas.size()), RoundingMode.HALF_UP));
					sum.remove(sumKey);
				}
			}
		}
		return sum;
	}

	private static String replaceFormula(String formula, Map<String, Object> data) {
		Matcher m = Pattern.compile("[^\\(\\)\\+\\-\\\\*\\s/='\\d\\?:|&\\W]+\\w{0,}").matcher(formula);
		boolean isAgainReplace = false;
		while (isAgainReplace = m.find()) {
			String f = m.group();
			int prefixIdx = formula.indexOf(f, m.start());
			int suffixIdx = prefixIdx + f.length();
			boolean isReplace = true;
			if (prefixIdx > 0 && suffixIdx <= formula.length() - 1) {
				String prefix = formula.substring(prefixIdx - 1, prefixIdx);
				String suffix = formula.substring(suffixIdx, suffixIdx + 1);
				if ("'".equals(prefix) || "'".equals(suffix))
					isReplace = false;
			}
			if (isReplace) {
				Object v = data.get(f);
				if (v instanceof String && !(v = ((String) v).replaceAll(",", "")).toString().matches("^[-]?\\d{1,}\\.\\d{1,}$|^[-]?\\d{1,9}$")) {
					v = "'" + v + "'";
				}
				formula = formula.replaceFirst(f, v == null || "".equals(v) ? "0" : v + "");
				break;
			}
		}
		return isAgainReplace ? replaceFormula(formula, data) : formula;
	}

	/**
	 * 将JavaBean转成map或将map的key转成小写
	 * 
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> Map<String, Object> toMap(T obj) {
		Map<String, Object> params = null;
		if (obj instanceof Map) {
			params = new HashMap<String, Object>();
			for (Entry<String, Object> entry : ((Map<String, Object>) obj).entrySet()) {
				params.put(entry.getKey().toLowerCase(), entry.getValue());
			}
		} else {
			params = beanToMap(obj, true);
		}
		return params;
	}

	private static final BigDecimal BIGDECIMAL_ZERO = new BigDecimal("0.00");
	static {
		BigDecimalConverter bd = new BigDecimalConverter(BIGDECIMAL_ZERO); // 这里一定要注册默认值，使用null也可以
		ConvertUtils.register(bd, java.math.BigDecimal.class);
	}

	/**
	 * Map转实体
	 * 
	 * @param map
	 * @param obj          (实例化的对象或Class)
	 * @param isIgnoreCase
	 * @return
	 * @author lwyao
	 */
	@SuppressWarnings("unchecked")
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
			BeanUtils.populate(obj, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (T) obj;
	}

	/**
	 * 实体转map
	 * 
	 * @param obj
	 * @param isLowerCase
	 * @return
	 * @author lwyao
	 */
	public static Map<String, Object> beanToMap(Object obj, boolean... isLowerCase) {
		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				if (isLowerCase != null && isLowerCase.length > 0 && isLowerCase[0])
					key = key.toLowerCase();
				if (!key.equals("class")) {
					Method getter = property.getReadMethod();
					if (getter == null)
						continue;
					Object value = getter.invoke(obj);
					map.put(key, value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 将集合转成字符串用逗号,分隔
	 * 
	 * @author lwyao
	 * @param c 集合中的泛型必须是基础类型,String、int、long 等
	 * @return
	 */
	public static String collectionToStr(Collection<?> c) {
		return c != null && c.size() > 0 ? c.toString().replaceAll("\\[|\\]|\\s", "") : "";
	}

	/**
	 * 将字符串转义成正则
	 * 
	 * @author lwyao
	 * @param str
	 * @return
	 */
	public static String toRegxStr(String str) {
		if (str != null) {
			String[] fbsArr = { "\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|" };
			for (String key : fbsArr) {
				if (str.contains(key)) {
					str = str.replace(key, "\\" + key);
				}
			}
		}
		return str;
	}

	/**
	 * 返回关于钱的中文式大写数字,支仅持到亿
	 * 
	 * @throws Exception
	 */
	private static String arabNumToChineseRMB(String moneyNum) throws Exception {
		String res = "";
		int i = 2;
		int len = moneyNum.length();
		if (len > 12) {
			throw new Exception("Number too large!");
		}
		if ("0".equals(moneyNum))
			return "零元";
		for (len--; len >= 0; len--) {
			res = CHINESEUNIT[i++] + res;
			int num = Integer.parseInt(moneyNum.charAt(len) + "");
			res = CHINESENUM[num] + res;
		}
		return res.replaceAll("零[拾佰仟]", "零").replaceAll("零+亿", "亿").replaceAll("零+万", "万").replaceAll("零+元", "元").replaceAll("零+", "零");

	}

	/**
	 * 整数位支持12位,到仟亿 支持到小数点后3位,如果大于3位,那么会四舍五入到3位
	 * 
	 * @throws Exception
	 */
	public static String arabNumToChineseRMB(double moneyNum) throws Exception {
		String suf = "";
		String res = "";
		String money = String.format("%.2f", moneyNum);
		if (moneyNum < 0) {
			suf = "负";
			moneyNum = moneyNum * (-1);
			money = String.format("%.2f", moneyNum);
		}
		int i = 0;
		if (moneyNum == 0.0)
			return "零元";
		String inte = money.split("\\.")[0];
		int deci = Integer.parseInt(money.split("\\.")[1].substring(0, 2));
		while (deci > 0) {
			res = CHINESEUNIT[i++] + res;
			res = CHINESENUM[deci % 10] + res;
			deci /= 10;
		}
		res = res.replaceAll("零[分角]", "零");
		if (i < 2) {
			res = "零" + res;
		}
		res = res.replaceAll("零+", "零");
		if (res.endsWith("零")) {
			res = res.substring(0, res.length() - 1) + "整";
		}
		return suf + arabNumToChineseRMB(inte) + res;
	}

	/**
	 * <p>
	 * Title: getAge
	 * </p>
	 * <p>
	 * Description: 由出生日期获得年龄
	 * </p>
	 * 
	 * @author lb
	 * @date 2018年8月4日
	 * @param birthDay
	 * @return
	 */
	public static int getAge(Date birthDay) {
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthDay)) {
			return 0;
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthDay);

		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth)
					age--;
			} else {
				age--;
			}
		}
		return age;
	}
}
