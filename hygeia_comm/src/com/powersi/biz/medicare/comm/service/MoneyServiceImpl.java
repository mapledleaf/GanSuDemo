package com.powersi.biz.medicare.comm.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 金额大小写转换
 * 
 * @author 刘勇
 *
 */
@Service
public class MoneyServiceImpl implements MoneyService {

	private static final long serialVersionUID = 1L;

	/** 大写数字 */
	private static final String[] NUMBERS = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };

	/** 整数部分的单位 */
	private static final String[] IUNIT = { "元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "万", "拾", "佰",
			"仟" };

	/** 小数部分的单位 */
	private static final String[] DUNIT = { "角", "分", "厘" };

	private static final Character[] CN_NUMERIC = { '一', '二', '三', '四', '五', '六', '七', '八', '九', '壹', '贰', '叁', '肆',
			'伍', '陆', '柒', '捌', '玖', '十', '百', '千', '拾', '佰', '仟', '万', '亿', '○', 'Ｏ', '零' };

	private static final Map<Character, Integer> cnNumeric;

	static {
		cnNumeric = new HashMap<Character, Integer>(40, 0.85f);
		for (int j = 0; j < 9; j++) {
			cnNumeric.put(CN_NUMERIC[j], j + 1);
		}
		for (int j = 9; j < 18; j++) {
			cnNumeric.put(CN_NUMERIC[j], j - 8);
		}
		cnNumeric.put('两', 2);
		cnNumeric.put('十', 10);
		cnNumeric.put('拾', 10);
		cnNumeric.put('百', 100);
		cnNumeric.put('佰', 100);
		cnNumeric.put('千', 1000);
		cnNumeric.put('仟', 1000);
		cnNumeric.put('万', 10000);
		cnNumeric.put('亿', 100000000);
	}

	@Override
	public String toChinese(String str) {
		if (StringUtils.isBlank(str)) {
			return "";
		}

		str = str.trim();
		str = str.replaceAll(",", "");
		boolean isnegative = false;
		isnegative = StringUtils.isNotBlank(str) && str.length() > 1 && "-".equals(str.substring(0, 1)) ? true : false;
		if (isnegative) {
			str = str.replaceAll("-", "");
		}

		String integerStr;// 整数部分数字
		String decimalStr;// 小数部分数字

		// 初始化：分离整数部分和小数部分
		if (str.indexOf(".") > 0) {
			integerStr = str.substring(0, str.indexOf("."));
			decimalStr = str.substring(str.indexOf(".") + 1);
		} else if (str.indexOf(".") == 0) {
			integerStr = "";
			decimalStr = str.substring(1);
		} else {
			integerStr = str;
			decimalStr = "";
		}
		// integerStr去掉首0，不必去掉decimalStr的尾0(超出部分舍去)
		if (!integerStr.equals("")) {
			integerStr = Long.toString(Long.parseLong(integerStr));
			if (integerStr.equals("0")) {
				integerStr = "";
			}
		}
		// overflow超出处理能力，直接返回
		if (integerStr.length() > IUNIT.length) {
			System.out.println(str + ":超出处理能力");
			return str;
		}

		int[] integers = toArray(integerStr);// 整数部分数字
		boolean isMust5 = isMust5(integerStr);// 设置万单位
		int[] decimals = toArray(decimalStr);// 小数部分数字

		if (isnegative) {
			return "负" + this.getChineseInteger(integers, isMust5) + this.getChineseDecimal(decimals);
		}
		return this.getChineseInteger(integers, isMust5) + this.getChineseDecimal(decimals);
	}

	/**
	 * 整数部分和小数部分转换为数组，从高位至低位
	 */
	private int[] toArray(String number) {
		int[] array = new int[number.length()];
		for (int i = 0; i < number.length(); i++) {
			array[i] = Integer.parseInt(number.substring(i, i + 1));
		}
		return array;
	}

	/**
	 * 得到中文金额的整数部分。
	 */
	private String getChineseInteger(int[] integers, boolean isMust5) {
		StringBuffer chineseInteger = new StringBuffer("");
		int length = integers.length;
		for (int i = 0; i < length; i++) {
			// 0出现在关键位置：1234(万)5678(亿)9012(万)3456(元)
			// 特殊情况：10(拾元、壹拾元、壹拾万元、拾万元)
			String key = "";
			if (integers[i] == 0) {
				if ((length - i) == 13)// 万(亿)(必填)
				{
					key = IUNIT[4];
				} else if ((length - i) == 9)// 亿(必填)
				{
					key = IUNIT[8];
				} else if ((length - i) == 5 && isMust5)// 万(不必填)
				{
					key = IUNIT[4];
				} else if ((length - i) == 1)// 元(必填)
				{
					key = IUNIT[0];
				}
				// 0遇非0时补零，不包含最后一位
				if ((length - i) > 1 && integers[i + 1] != 0) {
					key += NUMBERS[0];
				}
			}
			chineseInteger.append(integers[i] == 0 ? key : (NUMBERS[integers[i]] + IUNIT[length - i - 1]));
		}
		return chineseInteger.toString();
	}

	/**
	 * 得到中文金额的小数部分。
	 */
	private String getChineseDecimal(int[] decimals) {
		StringBuffer chineseDecimal = new StringBuffer("");
		for (int i = 0; i < decimals.length; i++) {
			// 舍去3位小数之后的
			if (i == 3) {
				break;
			}
			chineseDecimal.append(decimals[i] == 0 ? "" : (NUMBERS[decimals[i]] + DUNIT[i]));
		}
		return chineseDecimal.toString();
	}

	/**
	 * 判断第5位数字的单位"万"是否应加。
	 */
	private boolean isMust5(String integerStr) {
		int length = integerStr.length();
		if (length > 4) {
			String subInteger = "";
			if (length > 8) {
				// 取得从低位数，第5到第8位的字串
				subInteger = integerStr.substring(length - 8, length - 4);
			} else {
				subInteger = integerStr.substring(0, length - 4);
			}
			return Integer.parseInt(subInteger) > 0;
		} else {
			return false;
		}
	}

}
