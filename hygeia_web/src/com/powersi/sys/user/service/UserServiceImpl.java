/*
 * Copyright 2016 Powersi. All rights reserved.
 */
package com.powersi.sys.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

import com.powersi.comm.service.memory.MemoryDBWrapper;
import com.powersi.config.pojo.BizYyInfo;
import com.powersi.hygeia.comm.license.exception.LicenseWarnException;
import com.powersi.hygeia.comm.license.service.LicenseCheckService;
import com.powersi.hygeia.comm.service.HygeiaBeanService;
import com.powersi.hygeia.framework.BaseServiceImpl;
import com.powersi.hygeia.framework.CodetableMapping;
import com.powersi.hygeia.framework.ParameterMapping;
import com.powersi.hygeia.framework.UserInfo;
import com.powersi.hygeia.framework.entity.SysUserKind;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.BeanHelper;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.SysFunc;
import com.powersi.hygeia.framework.util.UserHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.util.TextHelper;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.sys.manager.dao.DeptDAO;
import com.powersi.sys.manager.dao.MenuDAO;
import com.powersi.sys.manager.dao.RoleDAO;
import com.powersi.sys.user.dao.ChangeDAO;
import com.powersi.sys.user.dao.StaffDAO;
import com.powersi.sys.user.dao.UserDAO;
import com.powersi.sys.user.dto.PwdRetrieveDTO;
import com.powersi.sys.user.dto.SysChangeDTO;
import com.powersi.sys.user.dto.UserKindDTO;
import com.powersi.sys.util.GradeHelper;
import com.powersi.sys.util.PasswordHelper;
import com.powersi.sys.util.UserKindHelper;

/**
 * 用户服务接口实现.
 */
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	/** The staff dao. */
	public static StaffDAO staffDAO = BeanHelper.getBean(StaffDAO.class);

	/** The role dao. */
	public static RoleDAO roleDAO = BeanHelper.getBean(RoleDAO.class);

	/** The change dao. */
	public static ChangeDAO changeDao = BeanHelper.getBean(ChangeDAO.class);

	/** The user dao. */
	public static UserDAO userDAO = BeanHelper.getBean(UserDAO.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.service.UserService#verifyLogin(java.util.Map)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void verifyLogin(Map userInfo) {
		if (UtilFunc.isEmpty(userInfo)) {
			throw new HygeiaException("请输入用户信息");
		}
		String loginUser = UtilFunc.getString(userInfo, "login_user");
		if (UtilFunc.isEmpty(loginUser)) {
			throw new HygeiaException("请输入用户名");
		}
		String password = UtilFunc.getString(userInfo, "password");
		String userKind = UtilFunc.getString(userInfo, "user_kind");
		if (UtilFunc.isEmpty(userKind)) {
			throw new HygeiaException("请输入用户类别");
		}
		UserKindDTO dto = UserKindHelper.get(userKind);
		if (dto == null) {
			throw new HygeiaException("无效的用户类别" + userKind);
		}
		UserDAO dao = getBean(UserDAO.class);
		Map userMap = dao.getUserByLogin(dto, loginUser);
		if (UtilFunc.isEmpty(userMap)) {
			throw new HygeiaException("用户不存在");
		}
		userInfo.putAll(userMap);
		if (!UtilFunc.getString(userMap, "lock_state", "0").equals("0")) {
			throw new HygeiaException("用户已经被锁定:" + UtilFunc.getString(userMap, "lock_reason"));
		}
		int maxPwsErrorCount = ParameterMapping.getMaxPswErrorTimes();
		int pwdErrorCount = -1;
		if (maxPwsErrorCount > 0) {
			Object errcountObj = userMap.get("password_errorcount");
			pwdErrorCount = (errcountObj == null ? -1 : Integer.parseInt(errcountObj.toString()));
			if (pwdErrorCount >= maxPwsErrorCount) {
				throw new HygeiaException("用户已经被锁定：密码错误次数过多");
			}
		}
		// 加载用户配置
		UserHelper.loadUserConfig(userInfo);
		boolean pwdFlag = PasswordHelper.equalsPassword(password, UtilFunc.getString(userMap, "password"));
		// 处理密码出错次数
		if (pwdErrorCount != -1) {
			if ((pwdFlag && pwdErrorCount > 0) || pwdFlag == false) {
				dao.updatePasswordErrorCount(userMap, pwdFlag);
				// 显式提交
				DBHelper.commit();
			}
		}
		if (!pwdFlag) {
			throw new HygeiaException("密码不正确");
		}
		// 加载医院信息
		if (UtilFunc.hasText(UtilFunc.getString(userInfo, "akb020", ""))) {
			Properties configs = (Properties) HygeiaBeanService.getInstance().getBean("configs");
			if (!"1".equals(configs.getProperty("pcloud_rpc.debug_web", "0"))) {
				this.loadHospitalInfoWithCache(userInfo);
				String akb020 = UtilFunc.getString(userInfo, "akb020", "");
				try {
					HygeiaBeanService.getInstance().getBeanByClass(LicenseCheckService.class).checkLicense(1, akb020,null);
				} catch (LicenseWarnException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

	/**
	 * 
	 * @param userInfo
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void loadHospitalInfoWithCache(Map userInfo) {
		String akb020 = UtilFunc.getString(userInfo, "akb020", "");
		if (!UtilFunc.hasText(akb020)) {
			return;
		}
		MemoryDBWrapper memoryDBWrapper = (MemoryDBWrapper) HygeiaBeanService.getInstance()
				.getBean("memoryDBWrapper_core");
		BizYyInfo yyInfo = (BizYyInfo) memoryDBWrapper.getMemoryDB().getMapValue("MAP_BIZ_YY_INFO", akb020);
		if (yyInfo == null) {
			yyInfo = new BizYyInfo();
			yyInfo.setYybm("RC0044");
			yyInfo.setAssist_uuid("4fe89533-36a3-4757-aa59-b34dea536b1a");
			yyInfo.setYymc("演示");
			yyInfo.setLb("11");
			yyInfo.setLb_name("医院");
			yyInfo.setYyjb("1");
			yyInfo.setYyjb_name("一级");
			yyInfo.setPassword("888888");
			yyInfo.setValid_flag("1");
			yyInfo.setLxr("创智和宇");
			yyInfo.setLxdh("137888888");
			yyInfo.setLxr("创智和宇");
			yyInfo.setTcqbm("430399");
			memoryDBWrapper.getMemoryDB().setMapValue("MAP_BIZ_YY_INFO","RC0044",yyInfo);
//			throw new HygeiaException("memoryDB(MAP_BIZ_YY_INFO)找不到医院信息，编码：" + akb020);
		}
		if (StringUtils.isBlank(yyInfo.getYymc())) {
			throw new HygeiaException("医院缓存医院名称为空，医院编码：" + akb020);
		}
		if (StringUtils.isBlank(yyInfo.getTcqbm())) {
			throw new HygeiaException("医院缓存统筹区编码为空，医院编码：" + akb020);
		}
		if (userInfo.containsKey("akb021")) {
			if (!yyInfo.getYymc().equals(UtilFunc.getString(userInfo, "akb021", ""))) {
				DBHelper.executeUpdate("update kb01 set akb021 = ? where akb020 = ? and aae100 = '1'",
						new String[] { yyInfo.getYymc(), akb020 });
				userInfo.put("akb021", yyInfo.getYymc());
			}
		}
		if (userInfo.containsKey("aaa027")) {
			String bka501 = yyInfo.getTcqbm();
			String aaa027 = BizHelper.getAaa027ByBka501(bka501);
			if (StringUtils.isNotBlank(aaa027) && !aaa027.equals(UtilFunc.getString(userInfo, "aaa027", ""))) {
				DBHelper.executeUpdate("update kb01 set aaa027 = ? where akb020 = ? and aae100 = '1'",
						new String[] { aaa027, akb020 });
				userInfo.put("aaa027", aaa027);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.user.service.UserService#changePassword(java.lang.String,
	 * java.lang.String)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void changePassword(String oldPassword, String newPassword) {
		UserInfo user = getUserInfo();
		if (!user.isValidUser()) {
			throw new HygeiaException("无效的用户");
		}

		if (oldPassword.equals(newPassword)) {
			throw new HygeiaException("新密码与密码相同，无需修改");
		}

		if (newPassword.length() < 6) {
			throw new HygeiaException("新密码必须至少包含 6 个字符");
		}

		// 校验用户密码
		Map userMap = (Map) user.clone();
		userMap.put("password", oldPassword);

		verifyLogin(userMap);

		// 修改用户密码
		UserDAO dao = getBean(UserDAO.class);
		String pwd = PasswordHelper.createPassword(newPassword);
		dao.changePassword(userMap, pwd);

		// 修改内存用户密码
		user.put("password", "{md5}" + pwd);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.service.UserService#getDeptTree()
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> getDeptTree() {
		Map<String, String> trees = new LinkedHashMap<String, String>();
		DeptDAO deptDAO = BeanHelper.getBean(DeptDAO.class);
		List<Map<String, Object>> lst = deptDAO.getDeptTree();
		for (Map<String, Object> map : lst) {
			int level = Integer.parseInt(UtilFunc.getString(map, "dept_level", "0"));

			StringBuilder sb = new StringBuilder();
			for (int i = 1; i < level; i++) {
				sb.append("　");// 使用全角空格
			}
			sb.append(map.get("dept_name"));
			trees.put(map.get("dept_id") + "", sb.toString());
		}

		return trees;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.user.service.UserService#initExportUserInfoXls(org.apache
	 * .poi.hssf.usermodel.HSSFWorkbook, org.apache.poi.hssf.usermodel.HSSFSheet,
	 * java.util.Map)
	 */
	@SuppressWarnings("rawtypes")
	public void initExportUserInfoXls(HSSFWorkbook wb, HSSFSheet sheet, Map userinfo) {
		// HSSFDataFormat txtFmt = wb.createDataFormat();
		// 设置列宽
		sheet.setColumnWidth(0, 3500);
		sheet.setColumnWidth(1, 6500);
		sheet.setColumnWidth(2, 3500);
		sheet.setColumnWidth(3, 6500);
		// 标题样式
		HSSFFont headfont = wb.createFont();
		headfont.setFontName("黑体");
		headfont.setFontHeightInPoints((short) 22);// 字体大小
		headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
		HSSFCellStyle headstyle = wb.createCellStyle();
		headstyle.setFont(headfont);
		headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		headstyle.setLeftBorderColor(HSSFColor.BLACK.index);// 边框颜色
		headstyle.setBorderLeft((short) 1);// 边框的大小
		headstyle.setRightBorderColor(HSSFColor.BLACK.index);// 边框颜色
		headstyle.setBorderRight((short) 1);// 边框的大小
		headstyle.setTopBorderColor(HSSFColor.BLACK.index);// 边框颜色
		headstyle.setBorderTop((short) 1);// 边框的大小
		headstyle.setBottomBorderColor(HSSFColor.BLACK.index);// 边框颜色
		headstyle.setBorderBottom((short) 1);// 边框的大小
		headstyle.setLocked(true);

		// 标签样式
		HSSFFont cellfont = wb.createFont();
		cellfont.setFontHeightInPoints((short) 12);// 字体大小
		HSSFCellStyle cellstyle = wb.createCellStyle();
		cellstyle.setFont(cellfont);
		cellstyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 左右居中
		cellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		cellstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); // 填充模式
		cellstyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index); // 背景色的设定
		cellstyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index); // 前景色的设定
		cellstyle.setLeftBorderColor(HSSFColor.BLACK.index);// 边框颜色
		cellstyle.setBorderLeft((short) 1);// 边框的大小
		cellstyle.setRightBorderColor(HSSFColor.BLACK.index);// 边框颜色
		cellstyle.setBorderRight((short) 1);// 边框的大小
		cellstyle.setTopBorderColor(HSSFColor.BLACK.index);// 边框颜色
		cellstyle.setBorderTop((short) 1);// 边框的大小
		cellstyle.setBottomBorderColor(HSSFColor.BLACK.index);// 边框颜色
		cellstyle.setBorderBottom((short) 1);// 边框的大小

		// 值样式
		HSSFCellStyle valuestyle = wb.createCellStyle();
		valuestyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 左右居中
		valuestyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		valuestyle.setLeftBorderColor(HSSFColor.BLACK.index);// 边框颜色
		valuestyle.setBorderLeft((short) 1);// 边框的大小
		valuestyle.setRightBorderColor(HSSFColor.BLACK.index);// 边框颜色
		valuestyle.setBorderRight((short) 1);// 边框的大小
		valuestyle.setTopBorderColor(HSSFColor.BLACK.index);// 边框颜色
		valuestyle.setBorderTop((short) 1);// 边框的大小
		valuestyle.setBottomBorderColor(HSSFColor.BLACK.index);// 边框颜色
		valuestyle.setBorderBottom((short) 1);// 边框的大小

		int rowNum = 0;
		// int colNum = 0;
		HSSFCell cell = null;
		HSSFRow row = null;

		// title
		row = sheet.createRow(rowNum++);
		row.setHeight((short) 900);
		cell = row.createCell(0);
		cell.setCellValue("个人基本信息");
		cell.setCellStyle(headstyle);// 合并单元格

		CellRangeAddress cra = new CellRangeAddress(0, (short) 0, 0, (short) 3);
		sheet.addMergedRegion(cra);

		// 登录名： Login_name 操作员名称： User_name
		row = sheet.createRow(rowNum++);
		cell = row.createCell(0);
		cell.setCellStyle(cellstyle);
		cell.setCellValue(TextHelper.getText("login_user"));
		cell = row.createCell(1);
		cell.setCellValue(userinfo.get("login_user") + "");
		cell.setCellStyle(valuestyle);
		cell = row.createCell(2);
		cell.setCellStyle(cellstyle);
		cell.setCellValue(TextHelper.getText("staff_name"));
		cell = row.createCell(3);
		cell.setCellValue(userinfo.get("staff_name") + "");
		cell.setCellStyle(valuestyle);

		// 显示序号： Dis_order 操作员状态： Staff_sta
		row = sheet.createRow(rowNum++);
		cell = row.createCell(0);
		cell.setCellStyle(cellstyle);
		cell.setCellValue(TextHelper.getText("dis_order"));
		cell = row.createCell(1);
		cell.setCellValue(userinfo.get("dis_order") + "");
		cell.setCellStyle(valuestyle);
		cell = row.createCell(2);
		cell.setCellStyle(cellstyle);
		cell.setCellValue(TextHelper.getText("staff_sta"));
		cell = row.createCell(3);
		cell.setCellStyle(valuestyle);
		if (userinfo.get("staff_sta") != null) {
			cell.setCellValue(CodetableMapping.getDisplayValue("staff_sta", userinfo.get("staff_sta")));
		} else {
			cell.setCellValue("");
		}

		// 操作级别： Staff_level 所属中心： Center_id
		row = sheet.createRow(rowNum++);
		cell = row.createCell(0);
		cell.setCellStyle(cellstyle);
		cell.setCellValue(TextHelper.getText("staff_level"));
		cell = row.createCell(1);
		cell.setCellStyle(valuestyle);
		if (userinfo.get("staff_level") != null) {
			cell.setCellValue(CodetableMapping.getDisplayValue("staff_level", userinfo.get("staff_level")));
		} else {
			cell.setCellValue("");
		}

		cell = row.createCell(2);
		cell.setCellStyle(cellstyle);
		cell.setCellValue(TextHelper.getText("center_id"));
		cell = row.createCell(3);
		cell.setCellStyle(valuestyle);
		if (userinfo.get("center_id") != null) {
			cell.setCellValue(CodetableMapping.getDisplayValue("center_id", userinfo.get("center_id")));
		} else {
			cell.setCellValue("");
		}

		// 用户级别： grade_id 所属部门： dept_id
		row = sheet.createRow(rowNum++);
		cell = row.createCell(0);
		cell.setCellStyle(cellstyle);
		cell.setCellValue(TextHelper.getText("grade_id"));
		cell = row.createCell(1);
		if (userinfo.get("grade_id") != null) {
			Map<String, String> gradeMap = GradeHelper.valueMap();
			Set<String> keySet = gradeMap.keySet();
			for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
				String key = it.next();

				if (key.equals(userinfo.get("grade_id").toString().trim())) {
					cell.setCellValue(gradeMap.get(key));
					break;
				}
			}
		} else {
			cell.setCellValue("");
		}
		cell.setCellStyle(valuestyle);
		cell = row.createCell(2);
		cell.setCellStyle(cellstyle);
		cell.setCellValue(TextHelper.getText("dept_id"));
		cell = row.createCell(3);
		if (userinfo.get("dept_id") != null) {
			Map<String, String> deptMap = getDeptTree();
			Set<String> keySet = deptMap.keySet();
			for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
				Object key = it.next();
				if (key.toString().equals(userinfo.get("dept_id").toString().trim())) {
					cell.setCellValue(deptMap.get(key));
					break;
				}
			}
		} else {
			cell.setCellValue("");
		}
		cell.setCellStyle(valuestyle);

		// 邮编： Postal_code 地址： Address
		row = sheet.createRow(rowNum++);
		cell = row.createCell(0);
		cell.setCellStyle(cellstyle);
		cell.setCellValue(TextHelper.getText("postal_code"));
		cell = row.createCell(1);
		cell.setCellValue(userinfo.get("postal_code") == null ? "" : (userinfo.get("staff_sta") + ""));
		cell.setCellStyle(valuestyle);
		cell = row.createCell(2);
		cell.setCellStyle(cellstyle);
		cell.setCellValue(TextHelper.getText("address"));
		cell = row.createCell(3);
		cell.setCellValue(userinfo.get("address") == null ? "" : (userinfo.get("staff_sta") + ""));
		cell.setCellStyle(valuestyle);

		// 办公电话： Office_phone 传真号码： Fax
		row = sheet.createRow(rowNum++);
		cell = row.createCell(0);
		cell.setCellStyle(cellstyle);
		cell.setCellValue(TextHelper.getText("office_phone"));
		cell = row.createCell(1);
		cell.setCellValue(userinfo.get("office_phone") == null ? "" : (userinfo.get("staff_sta") + ""));
		cell.setCellStyle(valuestyle);
		cell = row.createCell(2);
		cell.setCellStyle(cellstyle);
		cell.setCellValue(TextHelper.getText("fax"));
		cell = row.createCell(3);
		cell.setCellValue(userinfo.get("fax") == null ? "" : (userinfo.get("staff_sta") + ""));
		cell.setCellStyle(valuestyle);

		// 手机： Mobile_phone 住宅电话： Home_phone
		row = sheet.createRow(rowNum++);
		cell = row.createCell(0);
		cell.setCellStyle(cellstyle);
		cell.setCellValue(TextHelper.getText("mobile_phone"));
		cell = row.createCell(1);
		cell.setCellValue(userinfo.get("mobile_phone") == null ? "" : (userinfo.get("staff_sta") + ""));
		cell.setCellStyle(valuestyle);
		cell = row.createCell(2);
		cell.setCellStyle(cellstyle);
		cell.setCellValue(TextHelper.getText("home_phone"));
		cell = row.createCell(3);
		cell.setCellValue(userinfo.get("home_phone") == null ? "" : (userinfo.get("staff_sta") + ""));
		cell.setCellStyle(valuestyle);

		// Email： E_mail
		row = sheet.createRow(rowNum++);
		cell = row.createCell(0);
		cell.setCellStyle(cellstyle);
		cell.setCellValue(TextHelper.getText("email"));
		cra = new CellRangeAddress(rowNum - 1, (short) rowNum - 1, 1, (short) 3);
		sheet.addMergedRegion(cra);

		cell = row.createCell(1);
		cell.setCellValue(userinfo.get("email") == null ? "" : (userinfo.get("staff_sta") + ""));
		cell.setCellStyle(valuestyle);
		cell = row.createCell(2);
		cell.setCellStyle(valuestyle);
		cell = row.createCell(3);
		cell.setCellStyle(valuestyle);

		// 备注： Remark
		row = sheet.createRow(rowNum++);
		cell = row.createCell(0);
		cell.setCellStyle(cellstyle);
		cell.setCellValue(TextHelper.getText("remark"));

		cra = new CellRangeAddress(rowNum - 1, (short) rowNum - 1, 1, (short) 3);
		sheet.addMergedRegion(cra);

		cell = row.createCell(1);
		cell.setCellValue(userinfo.get("remark") == null ? "" : (userinfo.get("staff_sta") + ""));
		cell.setCellStyle(valuestyle);
		cell = row.createCell(2);
		cell.setCellStyle(valuestyle);
		cell = row.createCell(3);
		cell.setCellStyle(valuestyle);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.user.service.UserService#initExportUserListXls(org.apache
	 * .poi.hssf.usermodel.HSSFWorkbook, org.apache.poi.hssf.usermodel.HSSFSheet,
	 * java.util.Map, boolean, int)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void initExportUserListXls(HSSFWorkbook wb, HSSFSheet sheet, Map map, boolean initFlag, int userSeq) {
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 5000);
		sheet.setColumnWidth(4, 5000);
		if (initFlag) {
			// 标题样式
			HSSFFont headfont = wb.createFont();
			headfont.setFontName("黑体");
			headfont.setFontHeightInPoints((short) 15);// 字体大小
			headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
			HSSFCellStyle headstyle = wb.createCellStyle();
			headstyle.setFont(headfont);
			headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
			headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中

			// 标签样式
			HSSFFont cellfont = wb.createFont();
			cellfont.setFontHeightInPoints((short) 12);// 字体大小
			cellfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
			HSSFCellStyle cellstyle = wb.createCellStyle();
			cellstyle.setFont(cellfont);
			cellstyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 左右居中
			cellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中

			int rowNum = 0;
			HSSFCell cell = null;
			HSSFRow row = null;

			// title
			row = sheet.createRow(rowNum++);
			row.setHeight((short) 600);

			cell = row.createCell(0);
			cell.setCellValue("导出用户列表");
			cell.setCellStyle(headstyle);// 合并单元格
			CellRangeAddress cra = new CellRangeAddress(0, (short) 0, 0, (short) 4);
			sheet.addMergedRegion(cra);

			String[] cellValues = new String[] { "page_rowno", "login_user", "user_name", "center_id", "dept_id" };
			row = sheet.createRow(rowNum++);
			for (int i = 0; i < cellValues.length; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(cellstyle);
				cell.setCellValue(TextHelper.getText(cellValues[i]));
			}
		} else {
			// 值样式
			HSSFCellStyle valuestyle = wb.createCellStyle();
			valuestyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 左右居中
			valuestyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中

			HSSFCell cell = null;
			HSSFRow row = null;

			String[] cellValues = new String[] { "login_user", "staff_name", "center_id", "dept_id" };
			row = sheet.createRow(userSeq + 2);

			cell = row.createCell(0);
			cell.setCellStyle(valuestyle);
			cell.setCellValue(userSeq + 1);

			for (int i = 0; i < cellValues.length; i++) {
				cell = row.createCell(i + 1);
				String cellValue = cellValues[i];
				if ("center_id".equals(cellValue)) {
					cell.setCellStyle(valuestyle);
					cell.setCellValue(CodetableMapping.getDisplayValue("aaa027_list", map.get(cellValue) + ""));
				} else if ("dept_id".equals(cellValue)) {
					cell.setCellStyle(valuestyle);
					cell.setCellValue(CodetableMapping.getDisplayValue("sys_dept", map.get(cellValue) + ""));
				} else if ("staff_name".equals(cellValue)) {
					HSSFFont linkFont = wb.createFont();
					linkFont.setColor(HSSFColor.BLUE.index);
					linkFont.setUnderline(HSSFFont.U_SINGLE);
					cell.getCellStyle().setFont(linkFont);
					// 设置超链接到相对应的权限sheet
					cell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
					cell.setCellFormula(
							"HYPERLINK(\"#" + map.get(cellValue) + "!A11\",\"" + map.get(cellValue) + "\")");
					HSSFRichTextString hssfRichTextString = new HSSFRichTextString(map.get(cellValue) + "");
					cell.setCellValue(hssfRichTextString);

				} else {
					cell.setCellStyle(valuestyle);
					cell.setCellValue(map.get(cellValue) + "");
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.service.UserService#initExportUserRightXls(org.
	 * apache .poi.hssf.usermodel.HSSFWorkbook,
	 * org.apache.poi.hssf.usermodel.HSSFSheet, java.util.Map)
	 */
	@SuppressWarnings("rawtypes")
	public void initExportUserRightXls(HSSFWorkbook wb, HSSFSheet sheet, Map map) {
		RoleDAO roleDAO = BeanHelper.getBean(RoleDAO.class);
		StaffDAO staffDAO = BeanHelper.getBean(StaffDAO.class);
		String roles = UtilFunc.joinList(roleDAO.getUserRoleList(map.get("staff_id") + "", staffDAO.getUserKind()),
				"role_id", ",");
		if (UtilFunc.isEmpty(roles)) {
			roles = "-1";
		}

		MenuDAO dao = BeanHelper.getBean(MenuDAO.class);
		List lst = dao.getMenuTreeByRoles(roles);

		initRightXls(wb, sheet, lst);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.user.service.UserService#initExportRoleListXls(org.apache
	 * .poi.hssf.usermodel.HSSFWorkbook, org.apache.poi.hssf.usermodel.HSSFSheet,
	 * java.util.Map, boolean, int)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void initExportRoleListXls(HSSFWorkbook wb, HSSFSheet sheet, Map map, boolean initFlag, int roleSeq) {
		sheet.setColumnWidth(1, 5000);
		sheet.setColumnWidth(2, 5000);
		sheet.setColumnWidth(3, 5000);
		if (initFlag) {
			// 标题样式
			HSSFFont headfont = wb.createFont();
			headfont.setFontName("黑体");
			headfont.setFontHeightInPoints((short) 15);// 字体大小
			headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
			HSSFCellStyle headstyle = wb.createCellStyle();
			headstyle.setFont(headfont);
			headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
			headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中

			// 标签样式
			HSSFFont cellfont = wb.createFont();
			cellfont.setFontHeightInPoints((short) 12);// 字体大小
			cellfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
			HSSFCellStyle cellstyle = wb.createCellStyle();
			cellstyle.setFont(cellfont);
			cellstyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 左右居中
			cellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中

			int rowNum = 0;
			HSSFCell cell = null;
			HSSFRow row = null;

			// title
			row = sheet.createRow(rowNum++);
			row.setHeight((short) 600);
			cell = row.createCell(0);
			cell.setCellValue("导出角色列表");
			cell.setCellStyle(headstyle);// 合并单元格
			CellRangeAddress cra = new CellRangeAddress(0, (short) 0, 0, (short) 3);
			sheet.addMergedRegion(cra);

			row = sheet.createRow(rowNum++);
			String[] cellValues = new String[] { "page_rowno", "rolename", "dept_id", "roledesc" };
			for (int i = 0; i < cellValues.length; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(cellstyle);
				cell.setCellValue(TextHelper.getText(cellValues[i]));
			}
		} else {
			// 值样式
			HSSFCellStyle valuestyle = wb.createCellStyle();
			valuestyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 左右居中
			valuestyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中

			HSSFCell cell = null;
			HSSFRow row = null;

			String[] cellValues = new String[] { "role_name", "dept_id", "role_desc" };
			row = sheet.createRow(roleSeq + 2);

			cell = row.createCell(0);
			cell.setCellStyle(valuestyle);
			cell.setCellValue(roleSeq + 1);

			for (int i = 0; i < cellValues.length; i++) {
				cell = row.createCell(i + 1);
				String cellValue = cellValues[i];
				if ("dept_id".equals(cellValue)) {
					cell.setCellStyle(valuestyle);
					cell.setCellValue(CodetableMapping.getDisplayValue("sys_dept", map.get(cellValue) + ""));
				} else if ("role_name".equals(cellValue)) {
					HSSFFont linkFont = wb.createFont();
					linkFont.setColor(HSSFColor.BLUE.index);
					linkFont.setUnderline(HSSFFont.U_SINGLE);
					cell.getCellStyle().setFont(linkFont);
					// 设置超链接到相对应的权限sheet
					cell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
					cell.setCellFormula(
							"HYPERLINK(\"#" + map.get(cellValue) + "!A11\",\"" + map.get(cellValue) + "\")");
					HSSFRichTextString hssfRichTextString = new HSSFRichTextString(map.get(cellValue) + "");
					cell.setCellValue(hssfRichTextString);

				} else {
					cell.setCellStyle(valuestyle);
					if (map.get(cellValue) != null) {
						cell.setCellValue(map.get(cellValue) + "");
					}
				}

			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.service.UserService#initExportRoleRightXls(org.
	 * apache .poi.hssf.usermodel.HSSFWorkbook,
	 * org.apache.poi.hssf.usermodel.HSSFSheet, java.util.Map)
	 */
	@SuppressWarnings("rawtypes")
	public void initExportRoleRightXls(HSSFWorkbook wb, HSSFSheet sheet, Map map) {
		String roles = map.get("role_id") + "";
		if (UtilFunc.isEmpty(roles)) {
			roles = "-1";
		}

		MenuDAO dao = BeanHelper.getBean(MenuDAO.class);
		List lst = dao.getMenuTreeByRoles(roles);

		initRightXls(wb, sheet, lst);
	}

	/**
	 * Inits the right xls.
	 * 
	 * @param wb
	 *            the wb
	 * @param sheet
	 *            the sheet
	 * @param lst
	 *            the lst
	 */
	@SuppressWarnings("rawtypes")
	private void initRightXls(HSSFWorkbook wb, HSSFSheet sheet, List lst) {
		HSSFDataFormat txtFmt = wb.createDataFormat();

		HSSFFont menuFont = wb.createFont();
		menuFont.setColor(HSSFColor.BLUE.index);

		HSSFFont folderFont = wb.createFont();
		folderFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		folderFont.setColor(HSSFColor.BLACK.index);

		HSSFCellStyle menuStyle = wb.createCellStyle();
		menuStyle.setDataFormat(txtFmt.getFormat("@"));
		menuStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		menuStyle.setFont(menuFont);

		HSSFCellStyle folderStyle = wb.createCellStyle();
		folderStyle.setDataFormat(txtFmt.getFormat("@"));
		folderStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		folderStyle.setFont(folderFont);

		int rowNum = 0;
		int colNum = 0;
		int menuLevel = 0;

		for (int i = 0; i < lst.size(); i++) {
			Map row = (Map) lst.get(i);
			if (!"1".equals(UtilFunc.getString(row, "menu_type"))) {
				continue;
			}

			HSSFRow xlsRow = sheet.createRow(rowNum);
			menuLevel = Integer.parseInt(UtilFunc.getString(row, "menu_level"));
			if (colNum < menuLevel) {
				colNum = menuLevel;
			}

			HSSFCell cell = xlsRow.createCell(menuLevel - 1);
			cell.setCellStyle(Integer.parseInt(UtilFunc.getString(row, "menu_flag")) == 1 ? menuStyle : folderStyle);

			HSSFRichTextString hssfRichTextString = new HSSFRichTextString(UtilFunc.getString(row, "menu_name", ""));
			cell.setCellValue(hssfRichTextString);

			rowNum++;
		}

		for (int i = 0; i < colNum; i++) {
			sheet.autoSizeColumn((short) i);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.service.UserService#saveAddUserChangeInfo(java.util
	 * .Map, java.util.List, java.util.List, com.powersi.hygeia.framework.UserInfo)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void saveAddUserChangeInfo(Map<String, Object> newUserMap, List<String> newRoleList, List newOrgs,
			UserInfo userInfo) {
		// String userId = (String) newUserMap.get("staff_id");
		// 保存用户信息改变事件
		SysChangeDTO changeDto = saveChangeEvent(newUserMap, userInfo);

		List<Map<String, Object>> detailList = new ArrayList<Map<String, Object>>();
		Map<String, Object> detailMap = null;
		Set<String> keyset = newUserMap.keySet();
		// 操作级别
		Map<String, String> gradeMap = GradeHelper.valueMap();
		// 部门列表
		DeptDAO deptDAO = BeanHelper.getBean(DeptDAO.class);
		List<Map<String, Object>> deptList = deptDAO.getDeptTree();

		// 保存用户增加详情
		detailMap = new HashMap<String, Object>();
		detailMap.put("item_name", TextHelper.getText("userinfo"));
		detailMap.put("old_value", "");
		detailMap.put("new_value", newUserMap.get("staff_name"));
		detailMap.put("log_id", changeDto.getLogId());
		detailMap.put("detail_id", SysFunc.getMaxNo("detail_id") + "");
		detailMap.put("item_event_code", ChangeDAO.ITEM_EVENT_TYPE_ADD);
		detailMap.put("item_type", ChangeDAO.ITEM_TYPE_USER_INFO);
		detailList.add(detailMap);

		for (Iterator<String> it = keyset.iterator(); it.hasNext();) {
			String newVal = "";
			String key = it.next();
			Object objec2 = newUserMap.get(key);
			if (objec2 != null) {
				newVal = objec2 + "";
			}

			if (!UtilFunc.isEmpty(newVal)) {
				detailMap = new HashMap<String, Object>();

				if ("password".equals(key) || "staff_id".equals(key)) {
					continue;
				} else if (key.equals("staff_sta")) {
					detailMap.put("new_value", CodetableMapping.getDisplayValue("staff_sta", newVal));
				} else if (key.equals("staff_level")) {
					detailMap.put("new_value", CodetableMapping.getDisplayValue("staff_level", newVal));
				} else if (key.equals("center_id")) {
					detailMap.put("new_value", CodetableMapping.getDisplayValue("aaa027_list", newVal));
				} else if (key.equals("grade_id")) {
					detailMap.put("new_value", getGradeName(gradeMap, newVal));
				} else if (key.equals("dept_id")) {
					detailMap.put("new_value", getDeptName(deptList, newVal));
				} else {
					detailMap.put("new_value", newVal);
				}
				detailMap.put("item_name", TextHelper.getText(key));
				detailMap.put("old_value", "");
				detailMap.put("log_id", changeDto.getLogId());
				detailMap.put("detail_id", SysFunc.getMaxNo("detail_id") + "");
				detailMap.put("item_event_code", ChangeDAO.ITEM_EVENT_TYPE_ADD);
				detailMap.put("item_type", ChangeDAO.ITEM_TYPE_USER_INFO);
				detailList.add(detailMap);
			}
		}
		changeDao.insertChangeDetail(detailList);

		// 保存角色变更信息
		saveRoleChangeInfo(changeDto.getLogId(), newRoleList, null, ChangeDAO.ITEM_EVENT_TYPE_ADD);

		// 保存组织变更信息
		saveOrgChangeInfo(changeDto.getLogId(), newOrgs, null, ChangeDAO.ITEM_EVENT_TYPE_ADD);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.service.UserService#saveDelUserChangeInfo(java.util
	 * .Map, com.powersi.hygeia.framework.UserInfo)
	 */
	public void saveDelUserChangeInfo(Map<String, Object> delUserMap, UserInfo userInfo) {
		// 保存用户信息改变事件
		SysChangeDTO changeDto = saveChangeEvent(delUserMap, userInfo);

		List<Map<String, Object>> detailList = new ArrayList<Map<String, Object>>();
		Map<String, Object> detailMap = null;
		// 保存用户删除详情
		detailMap = new HashMap<String, Object>();
		detailMap.put("item_name", TextHelper.getText("userinfo"));
		detailMap.put("old_value", delUserMap.get("staff_name"));
		detailMap.put("new_value", "");
		detailMap.put("log_id", changeDto.getLogId());
		detailMap.put("detail_id", SysFunc.getMaxNo("detail_id") + "");
		detailMap.put("item_event_code", ChangeDAO.ITEM_EVENT_TYPE_DEL);
		detailMap.put("item_type", ChangeDAO.ITEM_TYPE_USER_INFO);
		detailList.add(detailMap);

		changeDao.insertChangeDetail(detailList);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.service.UserService#saveUpdateUserChangeInfo(java
	 * .util.Map, java.util.List, java.util.List,
	 * com.powersi.hygeia.framework.UserInfo)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void saveUpdateUserChangeInfo(Map<String, Object> newUserMap, List<String> newRoleList, List newOrgs,
			UserInfo userInfo) {
		String userId = (String) newUserMap.get("staff_id");
		// 保存用户信息改变事件
		SysChangeDTO changeDto = saveChangeEvent(newUserMap, userInfo);

		// 基本信息的匹配
		List<Map<String, Object>> detailList = new ArrayList<Map<String, Object>>();
		Map<String, Object> detailMap = null;
		Map<String, Object> oldUserMap = staffDAO.findUser(userId);

		if (oldUserMap != null) {
			// 操作级别
			Map<String, String> gradeMap = GradeHelper.valueMap();
			// 部门列表
			DeptDAO deptDAO = BeanHelper.getBean(DeptDAO.class);
			List<Map<String, Object>> deptList = deptDAO.getDeptTree();
			Set<String> keyset = oldUserMap.keySet();
			for (Iterator<String> it = keyset.iterator(); it.hasNext();) {
				String oldVal = "";
				String newVal = "";
				String key = it.next();
				Object objec1 = oldUserMap.get(key);
				if (objec1 != null) {
					oldVal = objec1 + "";
				}
				Object objec2 = newUserMap.get(key);
				if (objec2 != null) {
					newVal = objec2 + "";
				}
				if (key.equals("is_developer")) {
					continue;
				}
				if (!oldVal.equals(newVal)) {
					detailMap = new HashMap<String, Object>();
					if (key.equals("staff_sta")) {
						detailMap.put("old_value", CodetableMapping.getDisplayValue("staff_sta", oldVal));
						detailMap.put("new_value", CodetableMapping.getDisplayValue("staff_sta", newVal));
					} else if (key.equals("staff_level")) {
						detailMap.put("old_value", CodetableMapping.getDisplayValue("staff_level", oldVal));
						detailMap.put("new_value", CodetableMapping.getDisplayValue("staff_level", newVal));
					} else if (key.equals("center_id")) {
						detailMap.put("old_value", CodetableMapping.getDisplayValue("aaa027_list", oldVal));
						detailMap.put("new_value", CodetableMapping.getDisplayValue("aaa027_list", newVal));
					} else if (key.equals("grade_id")) {
						detailMap.put("old_value", getGradeName(gradeMap, oldVal));
						detailMap.put("new_value", getGradeName(gradeMap, newVal));
					} else if (key.equals("dept_id")) {
						detailMap.put("old_value", getDeptName(deptList, oldVal));
						detailMap.put("new_value", getDeptName(deptList, newVal));
					} else {
						detailMap.put("old_value", oldVal);
						detailMap.put("new_value", newVal);
					}

					detailMap.put("item_name", TextHelper.getText(key));
					detailMap.put("log_id", changeDto.getLogId());
					detailMap.put("detail_id", SysFunc.getMaxNo("detail_id") + "");
					detailMap.put("item_event_code", ChangeDAO.ITEM_EVENT_TYPE_UPT);
					detailMap.put("item_type", ChangeDAO.ITEM_TYPE_USER_INFO);
					detailList.add(detailMap);
				}
			}
			changeDao.insertChangeDetail(detailList);

			// 保存角色变更信息
			List<Map<String, Object>> roleInfo = roleDAO.getUserRoleList(userId, staffDAO.getUserKind());
			saveRoleChangeInfo(changeDto.getLogId(), newRoleList, roleInfo, ChangeDAO.ITEM_EVENT_TYPE_UPT);

			// 保存组织变更信息
			List<Map<String, Object>> orgInfo = staffDAO.getUserOrgList(userId);
			saveOrgChangeInfo(changeDto.getLogId(), newOrgs, orgInfo, ChangeDAO.ITEM_EVENT_TYPE_UPT);
		}
	}

	/**
	 * 保存用户角色变更信息.
	 * 
	 * @param logId
	 *            the log id
	 * @param newRoleList
	 *            the new role list
	 * @param roleInfo
	 *            the role info
	 * @param type
	 *            1:新增 2：删除 3：更新
	 */
	private static void saveRoleChangeInfo(String logId, List<String> newRoleList, List<Map<String, Object>> roleInfo,
			String type) {
		List<Map<String, Object>> detailList = new ArrayList<Map<String, Object>>();
		Map<String, Object> detailMap = null;

		// 角色信息的匹配,增加和删除
		List<Map<String, Object>> allRoles = roleDAO.getAllRoleList();
		List<String> oldRoleList = new ArrayList<String>();
		if (roleInfo != null && roleInfo.size() > 0) {
			for (int i = 0; i < roleInfo.size(); i++) {
				Map<String, Object> temMap = roleInfo.get(i);
				oldRoleList.add(temMap.get("role_id") + "");
			}
		}

		// 获取增加的角色
		for (int i = 0; i < newRoleList.size(); i++) {
			String obj = newRoleList.get(i) + "";
			if (!oldRoleList.contains(obj)) {
				detailMap = new HashMap<String, Object>();
				detailMap.put("log_id", logId);
				detailMap.put("old_value", "");
				detailMap.put("new_value", getRoleName(allRoles, obj));
				detailMap.put("item_name", TextHelper.getText("roleinfo"));
				detailMap.put("detail_id", SysFunc.getMaxNo("detail_id") + "");
				detailMap.put("item_event_code", type);
				detailMap.put("item_type", ChangeDAO.ITEM_TYPE_USER_INFO);
				detailList.add(detailMap);
			}
		}
		// 获取删除的角色
		for (int j = 0; j < oldRoleList.size(); j++) {
			String obj = oldRoleList.get(j) + "";
			if (!newRoleList.contains(obj)) {
				detailMap = new HashMap<String, Object>();
				detailMap.put("log_id", logId);
				detailMap.put("old_value", getRoleName(allRoles, obj));
				detailMap.put("new_value", "");
				detailMap.put("item_name", TextHelper.getText("roleinfo"));
				detailMap.put("detail_id", SysFunc.getMaxNo("detail_id") + "");
				detailMap.put("item_event_code", type);
				detailMap.put("item_type", ChangeDAO.ITEM_TYPE_USER_INFO);
				detailList.add(detailMap);
			}
		}

		changeDao.insertChangeDetail(detailList);
	}

	/**
	 * 保存用户数据权限变更信息.
	 * 
	 * @param logId
	 *            the log id
	 * @param newOrgInfo
	 *            the new org info
	 * @param orgInfo
	 *            the org info
	 * @param type
	 *            1:新增 2：删除 3：更新
	 */
	@SuppressWarnings("rawtypes")
	private static void saveOrgChangeInfo(String logId, List newOrgInfo, List<Map<String, Object>> orgInfo,
			String type) {
		/*
		 * List<Map<String, Object>> detailList = new ArrayList<Map<String, Object>>();
		 * Map<String, Object> detailMap = null;
		 * 
		 * // 数据权限信息的匹配,增加和删除 List<Map<String, Object>> allOrgs = staffDAO.getOrgTree();
		 * List<String> oldOrgList = new ArrayList<String>(); if (orgInfo != null &&
		 * orgInfo.size() > 0) { for (int i = 0; i < orgInfo.size(); i++) { Map<String,
		 * Object> temMap = orgInfo.get(i); oldOrgList.add(temMap.get("org_id") + ""); }
		 * } List newOrgList = new ArrayList(); if (newOrgInfo != null &&
		 * newOrgInfo.size() > 0) { for (int i = 0; i < newOrgInfo.size(); i++) { Map
		 * temMap = (Map) newOrgInfo.get(i); newOrgList.add(temMap.get("org_id") + "");
		 * } }
		 * 
		 * // 获取增加的数据权限 for (int i = 0; i < newOrgList.size(); i++) { String obj =
		 * newOrgList.get(i) + ""; if (!oldOrgList.contains(obj)) { detailMap = new
		 * HashMap<String, Object>(); detailMap.put("log_id", logId);
		 * detailMap.put("old_value", ""); detailMap.put("new_value",
		 * getOrgName(allOrgs, obj)); detailMap.put("item_name",
		 * TextHelper.getText("dataright")); detailMap.put("detail_id",
		 * SysFunc.getMaxNo("detail_id") + ""); detailMap.put("item_event_code", type);
		 * detailMap.put("item_type", ChangeDAO.ITEM_TYPE_USER_INFO);
		 * detailList.add(detailMap); } } // 获取删除的数据权限 for (int j = 0; j <
		 * oldOrgList.size(); j++) { String obj = oldOrgList.get(j) + ""; if
		 * (!newOrgList.contains(obj)) { detailMap = new HashMap<String, Object>();
		 * detailMap.put("log_id", logId); detailMap.put("old_value",
		 * getOrgName(allOrgs, obj)); detailMap.put("new_value", "");
		 * detailMap.put("item_name", TextHelper.getText("dataright"));
		 * detailMap.put("detail_id", SysFunc.getMaxNo("detail_id") + "");
		 * detailMap.put("item_event_code", type); detailMap.put("item_type",
		 * ChangeDAO.ITEM_TYPE_USER_INFO); detailList.add(detailMap); } }
		 * 
		 * changeDao.insertChangeDetail(detailList);
		 */
	}

	/**
	 * 保存信息改变事件.
	 * 
	 * @param userMap
	 *            the user map
	 * @param userInfo
	 *            the user info
	 * @return the sys change dto
	 */
	private static SysChangeDTO saveChangeEvent(Map<String, Object> userMap, UserInfo userInfo) {
		String userId = userMap.get("staff_id") + "";
		SysChangeDTO changeDto = new SysChangeDTO();
		changeDto.setLogId(SysFunc.getMaxNo("log_id") + "");
		changeDto.setLoingUser(userInfo.getLoginUser());
		changeDto.setUserId(userId);
		changeDto.setUserName(userMap.get("login_user") + "");

		changeDao.insertChangeEvent(changeDto);
		return changeDto;
	}

	/**
	 * 获取组织名称.
	 *
	 * @param allRoles
	 *            the all roles
	 * @param roleId
	 *            the role id
	 * @return the org name
	 */
	/*
	 * private static String getOrgName(List<Map<String, Object>> allOrgs, String
	 * orgId) { for (int i = 0; i < allOrgs.size(); i++) { Map<String, Object>
	 * tempMap = allOrgs.get(i); String troleid = tempMap.get("org_id") + ""; if
	 * (troleid.equals(orgId)) { return (String) tempMap.get("org_name"); } } return
	 * null; }
	 */

	/**
	 * 获取角色名称.
	 * 
	 * @param allRoles
	 *            the all roles
	 * @param roleId
	 *            the role id
	 * @return the role name
	 */
	private static String getRoleName(List<Map<String, Object>> allRoles, String roleId) {
		for (int i = 0; i < allRoles.size(); i++) {
			Map<String, Object> tempMap = allRoles.get(i);
			String troleid = tempMap.get("role_id") + "";
			if (troleid.equals(roleId)) {
				return (String) tempMap.get("role_name");
			}
		}
		return null;
	}

	/**
	 * 获取科室名称.
	 * 
	 * @param allDeptList
	 *            the all dept list
	 * @param deptId
	 *            the dept id
	 * @return the dept name
	 */
	private static String getDeptName(List<Map<String, Object>> allDeptList, String deptId) {
		for (int i = 0; i < allDeptList.size(); i++) {
			Map<String, Object> tempMap = allDeptList.get(i);
			String troleid = tempMap.get("dept_id") + "";
			if (troleid.equals(deptId)) {
				return (String) tempMap.get("dept_name");
			}
		}
		return null;
	}

	/**
	 * 获取等级名称.
	 * 
	 * @param allGradeMap
	 *            the all grade map
	 * @param gradeId
	 *            the grade id
	 * @return the grade name
	 */
	private static String getGradeName(Map<String, String> allGradeMap, String gradeId) {
		Set<String> keyset = allGradeMap.keySet();
		for (Iterator<String> it = keyset.iterator(); it.hasNext();) {
			String troleid = it.next();
			if (troleid.equals(gradeId)) {
				return allGradeMap.get(troleid);
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.service.UserService#supportBookmark()
	 */
	public boolean supportBookmark() {
		return DBHelper.existTable("SYS_USER_CONFIG");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.service.UserService#saveBookmark(java.lang.String)
	 */
	public int saveBookmark(String bookmark) {
		return userDAO.saveConfig("bookmark", bookmark);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.service.UserService#getBookmark()
	 */
	public String getBookmark() {
		if (!supportBookmark()) {
			return "";
		} else {
			return userDAO.getConfig("bookmark");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.service.UserService#queryUserInfo()
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map queryUserInfo() {
		UserInfo userInfo = getUserInfo();
		java.util.Map map = null;
		if ("9".equals(userInfo.getUserKind())) {
			map = staffDAO.findUser(userInfo.getUserId());
			if (map != null && map.size() > 0) {
				map.put("org_id", userInfo.get("dept_id"));
				map.put("org_name", CodetableMapping.getDisplayValue("sys_dept", userInfo.get("dept_id")));
			}
		} else {
			map = userDAO.getUserInfo(userInfo.getUserKind(), userInfo.getUserId());
		}

		if (map == null) {
			map = new java.util.HashMap();
		}

		map.putAll(userInfo);
		return map;
	}

	/** The update cols. */
	private static String[] updateCols = new String[] { "sex", "idcardno", "birthdate", "nationality", "position",
			"position_desc", "address", "postal_code", "mobile_phone", "home_phone", "office_phone", "fax", "email",
			"qq", "msn", "remark" };

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.service.UserService#updateUserInfo(java.util.Map)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int updateUserInfo(Map params) {
		UserInfo userInfo = getUserInfo();
		Map map = new HashMap();
		for (String col : updateCols) {
			if (params.containsKey(col)) {
				map.put(col, params.get(col));
			}
		}

		if ("9".equals(userInfo.getUserKind())) {
			map.put("staff_id", userInfo.getUserId());

			return staffDAO.updateUser(map);
		} else {
			map.put("user_kind", userInfo.getUserKind());
			map.put("user_id", userInfo.getUserId());

			return userDAO.updateUser(map);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.service.UserService#getConfig()
	 */
	@SuppressWarnings("rawtypes")
	public Map getConfig() {
		return userDAO.getConfig();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.service.UserService#getConfig(java.lang.String,
	 * java.lang.String)
	 */
	public String getConfig(String code, String defaultValue) {
		return userDAO.getConfig(code, defaultValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.service.UserService#saveConfig(java.lang.String,
	 * java.lang.String)
	 */
	public int saveConfig(String code, String value) {
		return userDAO.saveConfig(code, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.service.UserService#confirmUserExist(java.util.Map)
	 */
	@SuppressWarnings("rawtypes")
	public void confirmUserExist(Map user) {
		userDAO.confirmUserExist(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.service.UserService#queryCorpUserPay(java.lang.
	 * String)
	 */
	@SuppressWarnings("rawtypes")
	public Map queryCorpUserPay(String corpId) {
		return userDAO.queryCorpUserPay(corpId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.service.UserService#getRoleMenus(java.util.Set)
	 */
	@SuppressWarnings("rawtypes")
	public List getRoleMenus(Set roleSet) {
		return userDAO.getRoleMenus(roleSet);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.service.UserService#getRetrieveQuestion(java.lang.
	 * Long)
	 */
	public PwdRetrieveDTO getRetrieveQuestion(Long personId) {
		return userDAO.getRetrieveQuestion(personId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.user.service.UserService#saveRetrieveQuestion(com.powersi
	 * .sys.user.dto.PwdRetrieveDTO)
	 */
	public void saveRetrieveQuestion(PwdRetrieveDTO dto) {
		userDAO.saveRetrieveQuestion(dto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.service.UserService#updateRetrieveQuestion(com.
	 * powersi.sys.user.dto.PwdRetrieveDTO)
	 */
	public void updateRetrieveQuestion(PwdRetrieveDTO dto) {
		userDAO.updateRetrieveQuestion(dto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.service.UserService#queryPersonUser(java.lang.
	 * String)
	 */
	@SuppressWarnings("rawtypes")
	public Map queryPersonUser(String idcard) {
		return userDAO.queryPersonUser(idcard);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.service.UserService#findUser(com.powersi.hygeia.
	 * framework.entity.SysUserKind, java.lang.String)
	 */
	@SuppressWarnings("rawtypes")
	public Map findUser(SysUserKind userKind, String userId) {
		return userDAO.findUser(userKind, userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.service.UserService#updatePasswordErrorCount(java.
	 * util.Map, boolean)
	 */
	@SuppressWarnings("rawtypes")
	public void updatePasswordErrorCount(Map user, boolean pwdFlag) {
		userDAO.updatePasswordErrorCount(user, pwdFlag);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.service.UserService#changePassword(java.util.Map,
	 * java.lang.String)
	 */
	@SuppressWarnings("rawtypes")
	public void changePassword(Map user, String pwd) {
		userDAO.changePassword(user, pwd);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.service.UserService#lockUser(java.util.Map,
	 * java.lang.String)
	 */
	@SuppressWarnings("rawtypes")
	public void lockUser(Map user, String reason) {
		userDAO.lockUser(user, reason);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.service.UserService#unlockUser(java.util.Map)
	 */
	@SuppressWarnings("rawtypes")
	public void unlockUser(Map user) {
		userDAO.unlockUser(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.service.UserService#resetPassword(java.util.Map)
	 */
	@SuppressWarnings("rawtypes")
	public void resetPassword(Map user) {
		userDAO.resetPassword(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.service.UserService#clearPasswordError(java.util.
	 * Map)
	 */
	@SuppressWarnings("rawtypes")
	public void clearPasswordError(Map user) {
		userDAO.clearPasswordError(user);
	}

	@Override
	public List<Map<String, Object>> queryUserInfosByCurrentDeptId() {
		UserInfo userInfo = getUserInfo();
		return staffDAO.getUserListByDeptId(userInfo.get("dept_id").toString());
	}

}
