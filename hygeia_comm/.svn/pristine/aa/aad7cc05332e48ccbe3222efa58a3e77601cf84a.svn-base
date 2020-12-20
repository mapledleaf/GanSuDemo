package com.powersi.biz.medicare.comm.service;

import org.springframework.stereotype.Service;

/**
 * 摘自雷总的金额大写转换
 * 
 * @author 刘勇
 *
 */
@Service
public class ConvertNumberServiceImple implements ConvertNumberService {
	private static final long serialVersionUID = 1L;
	/** 定义数组存放数字对应的大写 */
	private final static String[] STR_NUMBER = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
	/** 定义数组存放位数的大写 */
	private final static String[] STR_MODIFY = { "", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟" };

	/**
	 * 转化整数部分
	 * 
	 * @param tempString
	 * @return 返回整数部分
	 */
	private String getInteger(final String tempString) {
		/** 用来保存整数部分数字串 */
		String strInteger = null;//
		/** 记录"."所在位置 */
		int intDotPos = tempString.indexOf(".");
		final int intSignPos = tempString.indexOf("-");
		if (intDotPos == -1) {
			intDotPos = tempString.length();
		}
		/** 取出整数部分 */
		strInteger = tempString.substring(intSignPos + 1, intDotPos);
		strInteger = new StringBuffer(strInteger).reverse().toString();

		StringBuffer sbResult = new StringBuffer();
		for (int i = 0; i < strInteger.length(); i++) {
			sbResult.append(STR_MODIFY[i]);
			sbResult.append(STR_NUMBER[strInteger.charAt(i) - 48]);
		}

		sbResult = sbResult.reverse();

		replace(sbResult, "零拾", "零");
		replace(sbResult, "零佰", "零");
		replace(sbResult, "零仟", "零");
		replace(sbResult, "零万", "万");
		replace(sbResult, "零亿", "亿");

		replace(sbResult, "零零", "零");
		replace(sbResult, "零零零", "零");
		/** 这两句不能颠倒顺序 */
		replace(sbResult, "零零零零万", "");
		replace(sbResult, "零零零零", "");
		/** 这样读起来更习惯. */
		// replace(sbResult, "壹拾亿", "拾亿");
		// replace(sbResult, "壹拾万", "拾万");
		insert(sbResult, "拾亿", "零");
		insert(sbResult, "拾万", "零");
		/** 删除个位上的零 */
		if (sbResult.charAt(sbResult.length() - 1) == '零' && sbResult.length() != 1) {
			sbResult.deleteCharAt(sbResult.length() - 1);
		}

		if (strInteger.length() == 2) {
			replace(sbResult, "壹拾", "拾");
		}
		/** 将结果反转回来. */
		return sbResult.toString();
	}

	/**
	 * 转化小数部分 例：输入22.34返回叁肆
	 * 
	 * @param tempString
	 * @return
	 */
	private String getFraction(final String tempString) {
		String strFraction = null;
		final int intDotPos = tempString.indexOf(".");
		/** 没有点说明没有小数，直接返回 */
		if (intDotPos == -1) {
			return "";
		}
		strFraction = tempString.substring(intDotPos + 1);
		final StringBuffer sbResult = new StringBuffer(strFraction.length());

		for (int i = 0; i < strFraction.length(); i++) {
			sbResult.append(STR_NUMBER[strFraction.charAt(i) - 48]);
			if (i == 0) {
				sbResult.append("角");
			} else if (i == 1) {
				sbResult.append("分");
			}
		}
		return sbResult.toString();
	}

	/**
	 * 判断传入的字符串中是否有.如果有则返回点
	 * 
	 * @param tempString
	 * @return
	 */
	private String getDot(final String tempString) {
		return tempString.indexOf(".") != -1 ? "圆" : "圆整";
	}

	/**
	 * 判断传入的字符串中是否有-如果有则返回负
	 * 
	 * @param tempString
	 * @return
	 */
	private String getSign(final String tempString) {
		return tempString.indexOf("-") != -1 ? "负" : "";
	}

	/**
	 * 将一个数字转化为金额
	 * 
	 * @param tempNumber
	 *            传入一个double的变量
	 * @return 返一个转换好的字符串
	 */
	@Override
	public String numberToChinese(final double tempNumber) {
		final java.text.DecimalFormat df = new java.text.DecimalFormat("#.#########");
		final String pTemp = String.valueOf(df.format(tempNumber));
		final StringBuffer sbResult = new StringBuffer(
				getSign(pTemp) + getInteger(pTemp) + getDot(pTemp) + getFraction(pTemp));
		return sbResult.toString();
	}

	/**
	 * 替代字符
	 * 
	 * @param pValue
	 * @param pSource
	 * @param pDest
	 */
	private void replace(final StringBuffer pValue, final String pSource, final String pDest) {
		if (pValue == null || pSource == null || pDest == null) {
			return;
		}
		/** 记录pSource在pValue中的位置 */
		int intPos = 0;
		do {
			intPos = pValue.toString().indexOf(pSource);
			/** 没有找到pSource */
			if (intPos == -1) {
				break;
			}
			pValue.delete(intPos, intPos + pSource.length());
			pValue.insert(intPos, pDest);
		} while (true);
	}

	/**
	 * 插入字符
	 * 
	 * @param pValue
	 * @param pSource
	 * @param pDest
	 */
	private void insert(final StringBuffer pValue, final String pSource, final String pDest) {
		if (pValue == null || pSource == null || pDest == null) {
			return;
		}
		/** 记录pSource在pValue中的位置 */
		int intPos = 0;
		do {
			intPos = pValue.toString().indexOf(pSource);
			/** 没有找到pSource */
			if (intPos == -1) {
				break;
			}
			pValue.insert(intPos + pSource.length(), pDest);
		} while (false);
	}

}
