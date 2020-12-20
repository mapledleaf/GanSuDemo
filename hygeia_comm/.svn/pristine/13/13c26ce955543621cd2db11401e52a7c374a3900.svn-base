package com.powersi.biz.medicare.inhospital.service.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.powersi.biz.medicare.inhospital.service.date.DateService;

/**
 * 
 * @author 刘勇
 *
 */
@Service
public class CommunalServiceImpl implements CommunalService {

	private static final long serialVersionUID = 1L;
	private final Logger _logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DateService dateService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String getSqlValues(List rowValueList, List<String> rowNameList) {
		StringBuilder stringBuilder = new StringBuilder();
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		for (Object object : rowValueList) {
			map = gson.fromJson(object.toString(), map.getClass());
			stringBuilder.append("(");
			for (String rowName : rowNameList) {
				stringBuilder
						.append(map.get(rowName) == null ? "null"
								: "\'" + map.get(rowName).toString().replaceAll("\\'", "").replaceAll("\\\r", "")
										.replaceAll("\\\n", "").replaceAll("\\\\", "\\\\\\\\").trim() + "\'")
						.append(",");
			}
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
			stringBuilder.append("),");
		}
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		return stringBuilder.toString();
	}

	@Override
	public String uuid() {
		return this.uuid("");
	}

	@Override
	public String uuid(String prefix) {
		StringBuilder sb = new StringBuilder();
		sb.append(prefix);
		sb.append(UUID.randomUUID().toString().toLowerCase());
		return sb.toString();
	}

	@Override
	public String nvl(String cur, String def) {
		if (StringUtils.isNotBlank(cur)) {
			return cur;
		} else {
			return def;
		}
	}

	@Override
	public String exceptionString(Throwable e) {
		if (e == null) {
			return "";
		}
		String excStrCur = "";
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		try {
			e.printStackTrace(printWriter);
			printWriter.flush();
			stringWriter.flush();
			printWriter.close();
			stringWriter.close();
			excStrCur = stringWriter.toString();
			printWriter = null;
			stringWriter = null;
		} catch (Throwable e1) {
			e1.printStackTrace(printWriter);
			printWriter.flush();
			stringWriter.flush();
		} finally {
			if (printWriter != null) {
				printWriter.close();
				printWriter = null;
			}
			if (stringWriter != null) {
				try {
					stringWriter.close();
					excStrCur = stringWriter.toString();
					stringWriter = null;
				} catch (Throwable e2) {
					e2.printStackTrace();
				}
			}
		}
		return excStrCur;
	}

	@Override
	public char ascii2char(int asc) {
		return (char) asc;
	}

	@Override
	public int char2ascii(char c) {
		return (int) c;
	}

	@Override
	public String crypt(String ec2) {
		try {
			if (StringUtils.isBlank(ec2)) {
				return "";
			}
			int liRet = 0;
			int liLen = 0;
			int liAsc = 0;
			liLen = ec2.length();
			liRet = char2ascii(ec2.substring(0, 1).toCharArray()[0]) % 10;
			StringBuilder lsbRet = new StringBuilder();
			for (int i = 3; i <= liLen;) {
				liAsc = char2ascii(ec2.substring(i - 1, i).toCharArray()[0]);
				if ((char2ascii(ec2.substring(i - 2, i - 1).toCharArray()[0]) % 2) == 0) {
					lsbRet.append(ascii2char(liAsc + (i - 1) / 2 + liRet));
				} else {
					lsbRet.append(ascii2char(liAsc - (i - 1) / 2 - liRet));
				}
				i = i + 2;
			}
			return lsbRet.toString();
		} catch (Exception e) {
			throw new RuntimeException("Uncrypt(" + ec2 + ")," + e.getMessage(), e);
		}
	}

	@Override
	public String str(Map<?, ?> map, Object key, String defaultValue) {
		if (map == null) {
			return defaultValue;
		}
		if (key == null) {
			return defaultValue;
		}
		Object value = map.get(key);
		if (value == null) {
			return defaultValue;
		}
		if (StringUtils.isBlank(value.toString())) {
			return defaultValue;
		}
		return value.toString();
	}

	@Override
	public String strKey(Map<?, ?> map, Object value, String defaultKey) {
		if (map == null) {
			return defaultKey;
		}
		if (value == null) {
			return defaultKey;
		}
		if (!map.containsValue(value)) {
			return defaultKey;
		}
		for (Map.Entry<?, ?> entry : map.entrySet()) {
			if (String.valueOf(value).equals(String.valueOf(entry.getValue()))) {
				return String.valueOf(entry.getKey());
			}
		}
		return defaultKey;
	}

	@Override
	public List<String> tableLedger(Date begin_date, Date end_date) {
		if (begin_date == null || end_date == null) {
			return null;
		}
		if (begin_date.compareTo(end_date) > 0) {
			return null;
		}
		List<String> ledgerRows = new ArrayList<String>();
		String ledgerRow = null;
		int amount = 0;
		while (true) {
			ledgerRow = dateService.dateToString(DateUtils.addMonths(begin_date, amount++), yyyyMM);
			ledgerRows.add(ledgerRow);
			if (ledgerRow.equals(dateService.dateToString(end_date, yyyyMM))) {
				break;
			}
		}
		return ledgerRows;
	}

	@Override
	public void error(Logger logger, Exception exception, String message) {
		this.error(logger, (Throwable) exception, message);
	}

	@Override
	public void debug(Logger logger, Exception exception, String message) {
		this.debug(logger, (Throwable) exception, message);
	}

	@Override
	public void debug(Logger logger, Throwable exception, String message) {
		Logger loggerTemp = logger == null ? _logger : logger;
		if (exception == null) {
			if (StringUtils.isNotBlank(message)) {
				loggerTemp.debug(message);
			}
			return;
		}
		loggerTemp.debug(new StringBuilder("").append(StringUtils.isBlank(message) ? "" : message)
				.append(exception.getMessage()).append(":").append(this.exceptionString(exception)).toString());
	}

	@Override
	public void error(Logger logger, Throwable exception, String message) {
		Logger loggerTemp = logger == null ? _logger : logger;
		if (exception == null) {
			if (StringUtils.isNotBlank(message)) {
				loggerTemp.error(message);
			}
			return;
		}
		loggerTemp.error(new StringBuilder("").append(StringUtils.isBlank(message) ? "" : message)
				.append(exception.getMessage()).append(":").append(this.exceptionString(exception)).toString());
	}

	@Override
	public String retainTwoDecimalPlaces(String money) {
		String strmoney = String.valueOf(this.getRound(Double.parseDouble(money), 2));
		String str = ".";
		int index = strmoney.indexOf(str);
		if (index == -1) {
			return strmoney + ".00";
		}
		if (strmoney.length() == index + 1) {
			return strmoney + "00";
		}
		if (strmoney.length() == index + 2) {
			return strmoney + "0";
		}
		if (strmoney.length() == index + 3) {
			return strmoney;
		}
		return money;
	}

	@Override
	public double getRound(double dNumber, int iPRECISION) {
		double nTemp1 = 0.0D;
		double nTemp2 = 0.0D;
		nTemp1 = Math.abs(dNumber);
		nTemp2 = (new BigDecimal(nTemp1)).setScale(iPRECISION, 4).doubleValue();
		if (nTemp1 - nTemp2 - 0.5D * Math.pow(10D, -1 * iPRECISION) < 9.9999999999999995E-08D
				* Math.pow(10D, -1 * (iPRECISION - 2))
				&& nTemp1 - nTemp2 - 0.5D * Math.pow(10D, -1 * iPRECISION) > -9.9999999999999995E-08D
						* Math.pow(10D, -1 * (iPRECISION - 2))) {
			nTemp1 = (new BigDecimal(nTemp2 + Math.pow(10D, -1 * iPRECISION))).setScale(iPRECISION, 4).doubleValue();
		} else {
			nTemp1 = (new BigDecimal(nTemp2)).setScale(iPRECISION, 4).doubleValue();
		}
		return nTemp1 * (double) signum(dNumber);
	}

	@Override
	public int signum(double a) {
		return a != 0.0D ? a <= 0.0D ? -1 : 1 : 0;
	}

	@Override
	public int signum(int a) {
		return a != 0 ? a <= 0 ? -1 : 1 : 0;
	}

	@Override
	public boolean isAlphanumericChinesecharacters(String key) {
		if (key == null || "".equals(key.trim())) {
			return false;
		}
		Pattern pattern = Pattern.compile(
				"^[a-z0-9A-Z\u4e00-\u9fa5\\w\\-\\——\\*\\$\\%\\#\\@\\!\\&\\^\\/\\\\\\(\\)\\（\\）\\、\\。\\.\\，\\,\\;\\；\\:\\：\\'\\|]+$");
		return pattern.matcher(key).find();
	}

	@Override
	public int calcAgeByBirth(Date birthday) {
		int age = 0;
		try {
			Calendar now = Calendar.getInstance();
			// 当前时间
			now.setTime(new Date());
			Calendar birth = Calendar.getInstance();
			birth.setTime(birthday);
			if (birth.after(now)) {
				age = 0;
			} else {
				age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
				if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
					age += 1;
				}
			}
			return age;
		} catch (Exception e) {
			return 0;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public HSSFWorkbook exportToExcel(List<Map> list, Map<String, Object> gridexportMap) {
		HSSFWorkbook workbook = new HSSFWorkbook();// 创建一个Excel文件
		HSSFSheet sheet = workbook.createSheet();// 创建一个Excel的Sheet
		sheet.createFreezePane(1, 3);// 冻结
		for (int i = 0; i < 37; i++) {
			sheet.setColumnWidth(i, 2500);
		}
		// 标题设置字体
		HSSFFont headfont = workbook.createFont();
		headfont.setFontName("黑体");
		headfont.setFontHeightInPoints((short) 22);// 字体大小
		headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
		// 标题样式
		HSSFCellStyle headstyle = workbook.createCellStyle();
		headstyle.setFont(headfont);
		headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		headstyle.setLocked(true);
		headstyle.setWrapText(true);// 自动换行
		// Sheet样式
		HSSFCellStyle sheetStyle = workbook.createCellStyle();

		// 设置列的样式
		for (int i = 0; i <= 14; i++) {
			sheet.setDefaultColumnStyle((short) i, sheetStyle);
		}
		HSSFFont columnHeadFont = workbook.createFont();
		columnHeadFont.setFontName("宋体");
		columnHeadFont.setFontHeightInPoints((short) 10);
		columnHeadFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 列头的样式
		HSSFCellStyle columnHeadStyle = workbook.createCellStyle();
		columnHeadStyle.setFont(columnHeadFont);
		columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		columnHeadStyle.setLocked(true);
		columnHeadStyle.setWrapText(true);
		columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框的颜色
		columnHeadStyle.setBorderLeft((short) 1);// 边框的大小
		columnHeadStyle.setRightBorderColor(HSSFColor.BLACK.index);// 右边框的颜色
		columnHeadStyle.setBorderRight((short) 1);// 边框的大小
		columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
		columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色
		// 普通单元格样式
		HSSFCellStyle style = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 10);
		style.setFont(font);
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 左右居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);// 上下居中
		style.setWrapText(true);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft((short) 1);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight((short) 1);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
		style.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．
		try {
			// 创建第一行
			HSSFRow row0 = sheet.createRow(0);
			// 设置行高
			row0.setHeight((short) 900);
			int k = 0;
			HSSFCell cellx = null;
			for (String title : gridexportMap.keySet()) {
				// 创建第一列
				cellx = row0.createCell(k);
				cellx.setCellValue(new HSSFRichTextString(
						gridexportMap.get(title) == null ? "" : gridexportMap.get(title).toString()));
				cellx.setCellStyle(columnHeadStyle);
				k++;
			}
			// 遍历list 数组 加载excle 数据行
			Map<String, String> vo = null;
			int y = 0;
			HSSFRow row = null;
			for (int i = 0; list != null && i < list.size(); i++) {
				vo = list.get(i);
				row = sheet.createRow(1 + i);
				y = 0;
				cellx = row.createCell(0);
				for (String title : gridexportMap.keySet()) {
					cellx = row.createCell(y);
					cellx.setCellValue(new HSSFRichTextString(vo.get(title) == null ? "" : vo.get(title).toString()));
					cellx.setCellStyle(style);
					y++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return workbook;
	}

}
