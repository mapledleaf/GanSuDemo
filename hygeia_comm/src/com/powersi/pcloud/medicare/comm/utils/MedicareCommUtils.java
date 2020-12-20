package com.powersi.pcloud.medicare.comm.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Base64Utils;

import com.powersi.hygeia.comm.service.HygeiaBeanService;

/**
 * 
 * @author 刘勇
 *
 */
public class MedicareCommUtils {

	/**
	 * 
	 * @return
	 */
	public static String getRandomNumber() {
		return getRandomNumber("hygeia");
	}

	/**
	 * 
	 * @return
	 */
	public static String getRandomNumber(String uuidxm) {
		StringBuilder sb = new StringBuilder();
		if (uuidxm != null && !"".equals(uuidxm.trim())) {
			sb.append(uuidxm);
		} else {
			sb.append("uuidxm");
		}
		sb.append("|");
		sb.append(dateToString(new Date(), "yyyyMMddHHmmssSSSSSS"));
		sb.append("|");
		sb.append(UUID.randomUUID());
		return sb.toString();
	}

	/**
	 * 
	 * @param clazz
	 * @param centerId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBeanByCenterId(Class<T> clazz, String centerId) {
		if (clazz == null) {
			return null;
		}
		if (HygeiaBeanService.getInstance() == null) {
			return null;
		}
		if (HygeiaBeanService.getInstance().getmContext() == null) {
			return null;
		}
		if (StringUtils.isBlank(centerId)) {
			return HygeiaBeanService.getInstance().getBeanByClass(clazz);
		}
		String beanId = clazz.getSimpleName() + "_" + centerId;
		if (HygeiaBeanService.getInstance().getmContext().containsBean(beanId)) {
			return (T) HygeiaBeanService.getInstance().getBean(beanId);
		}
		return HygeiaBeanService.getInstance().getBeanByClass(clazz);
	}

	/**
	 * 
	 * @param src
	 * @return
	 */
	public static boolean isValidatedBase64Code(String src) {
		if (StringUtils.isBlank(src)) {
			return false;
		}
		try {
			byte[] srcbytes = Base64Utils.decodeFromString(src);
			if (srcbytes != null && srcbytes.length > 0 && src.equals(Base64Utils.encodeToString(srcbytes))) {
				return true;
			}
		} catch (Throwable e) {
			return false;
		}
		return false;
	}

	/**
	 * 
	 * @param ic_reg_permit
	 *            密文
	 * @return
	 */
	public static boolean isValidatedICRegPermitSimple(final String ic_reg_permit) {
		if (StringUtils.isBlank(ic_reg_permit)) {
			return false;
		}
		if (ic_reg_permit.length() < 20) {
			return false;
		}
		try {
			String srcPermit = "", permitTime = "";
			srcPermit = new String(Base64Utils.decode(ic_reg_permit.getBytes()));
			if (StringUtils.isBlank(srcPermit)) {
				return false;
			}
			if (srcPermit.length() >= 14) {
				permitTime = srcPermit.substring(0, 14);
			}
			if (isValidatedDateSimple(permitTime, "yyyyMMddHHmmss")) {
				return true;
			}
		} catch (Throwable e) {
			return false;
		}
		return false;
	}

	/**
	 * 
	 * @param ic_pay_permit
	 *            密文
	 * @return
	 */
	public static boolean isValidatedICPayPermitSimple(final String ic_pay_permit) {
		if (StringUtils.isBlank(ic_pay_permit)) {
			return false;
		}
		if (ic_pay_permit.length() < 20) {
			return false;
		}
		try {
			String srcPermit = "", permitTime = "";
			srcPermit = new String(Base64Utils.decode(ic_pay_permit.getBytes()));
			if (StringUtils.isBlank(srcPermit)) {
				return false;
			}
			if (srcPermit.length() >= 14) {
				permitTime = srcPermit.substring(0, 14);
			}
			if (isValidatedDateSimple(permitTime, "yyyyMMddHHmmss")) {
				return true;
			}
		} catch (Throwable e) {
			return false;
		}
		return false;
	}

	/**
	 * 
	 * @param akb020
	 * @param aaz217
	 * @return
	 */
	public static boolean isValidatedaaz217ByAkb020Simple(final String akb020, final String aaz217) {
		if (!isValidatedAkb020Simple(akb020)) {
			return false;
		}
		if (!isValidatedaaz217Simple(aaz217)) {
			return false;
		}
		if(StringUtils.isNotBlank(aaz217) && ("yd".equals(aaz217.substring(0, 2)) || "po".equals(aaz217.substring(0,2)))){
			return true;
		}
		if (akb020.equals(aaz217.substring(0, 6))) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param aaz217
	 * @return
	 */
	public static boolean isValidatedaaz217Simple(final String aaz217) {
		if (StringUtils.isNotBlank(aaz217) && aaz217.length() == 20) {
			String lsyyyyMMdd = aaz217.substring(6, 14);
			boolean lbdateFormate = isValidatedDateSimple(lsyyyyMMdd, "yyyyMMdd");
			if (lbdateFormate && lsyyyyMMdd.compareTo("20150101") >= 0
					&& isValidatedAkb020Simple(aaz217.substring(0, 6))) {
				return true;
			}
		}else if(StringUtils.isNotBlank(aaz217) && ("yd".equals(aaz217.substring(0, 2)) || "po".equals(aaz217.substring(0,2)))){
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param akb020
	 * @return
	 */
	public static boolean isValidatedAkb020Simple(final String akb020) {
		if (StringUtils.isBlank(akb020)) {
			return false;
		}
		if (akb020.length() == 6) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static boolean isValidatedDateSimple(String date, String format) {
		if (StringUtils.isBlank(date)) {
			return false;
		}
		if (StringUtils.isBlank(format)) {
			return false;
		}
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			return date.equals(simpleDateFormat.format(simpleDateFormat.parse(date)));
		} catch (Throwable e) {
			return false;
		}
	}

	/**
	 * 
	 * @param message
	 * @return
	 */
	public static String thinErrorMessage(final String message) {
		String lsmessage = message;
		if (StringUtils.isNotBlank(lsmessage)) {
			lsmessage = lsmessage.replaceAll("java.lang.RuntimeException", "");
		}
		if (StringUtils.isNotBlank(lsmessage)) {
			lsmessage = lsmessage.replaceAll("java.lang.NullPointerException", "");
		}
		if (StringUtils.isNotBlank(lsmessage)) {
			lsmessage = lsmessage.replaceAll("com.powersi.biz.core.ws.BizException", "");
		}
		if (StringUtils.isNotBlank(lsmessage)) {
			lsmessage = lsmessage.replaceAll("com.powersi.hygeia.framework.exception.HygeiaException", "");
		}
		if (StringUtils.isNotBlank(lsmessage)) {
			lsmessage = lsmessage.replaceAll("com.powersi.comm.exception.ApusException", "");
		}
		if (StringUtils.isNotBlank(lsmessage)) {
			// lsmessage = lsmessage.replaceAll(":", "");
		}
		int index = -1;
		if (StringUtils.isNotBlank(lsmessage)) {
			index = -1;
			index = lsmessage.indexOf("at ");
			if (index != -1) {
				lsmessage = lsmessage.substring(0, index);
			}
		}
		if (StringUtils.isNotBlank(lsmessage)) {
			index = -1;
			index = lsmessage.indexOf("<");
			int cdata_index = lsmessage.indexOf("<![CDATA[");
			if (index != -1 && cdata_index == -1) {
				lsmessage = lsmessage.substring(0, index);
			}
		}
		if (StringUtils.isNotBlank(lsmessage)) {
			return lsmessage;
		}
		return message;
	}

	/**
	 * 
	 * @param strDate
	 *            yyyyMMdd
	 * @return yyyyMMdd
	 */
	public static String getLastDateOfMonthSimple(String strDate) {
		if (StringUtils.isBlank(strDate)) {
			return null;
		}
		if (isValidatedDateSimple(strDate, "yyyyMM")) {
			strDate = strDate + "01";
		}
		if (!isValidatedDateSimple(strDate, "yyyyMMdd")) {
			return null;
		}
		return dateToString(getLastDateOfMonth(toDate(strDate)));
	}

	/**
	 * 
	 * @param date
	 * @return yyyyMMdd
	 */
	public static Date getLastDateOfMonth(Date date) {
		try {
			Calendar cal = new GregorianCalendar();
			cal.setTime(getDate(date));
			cal.set(5, 1);
			cal.add(2, 1);
			cal.add(5, -1);
			return cal.getTime();
		} catch (Throwable e) {

		}
		return null;
	}

	/**
	 * 
	 * @param date
	 * @return yyyyMMdd
	 */
	public static Date getDate(Date date) {
		try {
			return toDate(dateToString(date));
		} catch (Throwable e) {

		}
		return null;
	}

	/**
	 * 
	 * @param dtDate
	 *            yyyyMMdd
	 * @return
	 */
	public static String dateToString(Date dtDate) {
		return dateToString(dtDate, "yyyyMMdd");
	}

	/**
	 * 
	 * @param dtDate
	 * @param strFormat
	 * @return
	 */
	public static String dateToString(Date dtDate, String strFormat) {
		if (dtDate == null) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat(strFormat);
		return df.format(dtDate);
	}

	/**
	 * 
	 * @param strDate
	 *            yyyyMMdd
	 * @return
	 */
	public static Date toDate(String strDate) {
		try {
			SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
			return sdfDate.parse(strDate);
		} catch (Throwable e) {

		}
		return null;
	}

}
