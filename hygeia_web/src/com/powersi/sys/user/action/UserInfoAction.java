/**
 * Copyright 2013 Powersi. All rights reserved.
 * 
 */
package com.powersi.sys.user.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.powersi.hygeia.framework.CodetableMapping;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.hygeia.web.util.DownloadHelper;
import com.powersi.hygeia.web.util.JsonHelper;
import com.powersi.hygeia.web.util.WebHelper;
import com.powersi.sys.manager.dao.DeptDAO;
import com.powersi.sys.user.dao.ChangeDAO;
import com.powersi.sys.user.dao.StaffDAO;
import com.powersi.sys.user.dto.SearchChangeDTO;
import com.powersi.sys.user.dto.SearchUserDTO;
import com.powersi.sys.user.service.UserService;
import com.powersi.sys.util.UserKindHelper;

/**
 * The Class UserInfoAction.
 */
@Action(value = "UserInfoAction", results = {
		@Result(name = "query", location = "/pages/sys/user/QueryUserInfoList.jsp"),
		@Result(name = "infoDetail", location = "/pages/sys/user/QueryUserInfoDetail.jsp"),
		@Result(name = "changeInfo", location = "/pages/sys/user/QueryUserInfoChange.jsp")
})
public class UserInfoAction extends BaseAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The search user dto. */
	private SearchUserDTO searchUserDto;

	/** The search change dto. */
	private SearchChangeDTO searchChangeDto;

	/** The user. */
	private Map user;

	/** The staff dao. */
	public static StaffDAO staffDAO = getBean(StaffDAO.class);

	/** The change dao. */
	public static ChangeDAO changeDAO = getBean(ChangeDAO.class);

	/** The dept dao. */
	public static DeptDAO deptDAO = getBean(DeptDAO.class);

	/** The user service. */
	public static UserService userService = getBean(UserService.class);

	/**
	 * Gets the search user dto.
	 * 
	 * @return the search user dto
	 */
	public SearchUserDTO getSearchUserDto() {
		return searchUserDto;
	}

	/**
	 * Sets the search user dto.
	 * 
	 * @param searchUserDto
	 *            the new search user dto
	 */
	public void setSearchUserDto(SearchUserDTO searchUserDto) {
		this.searchUserDto = searchUserDto;
	}

	/**
	 * Gets the search change dto.
	 * 
	 * @return the search change dto
	 */
	public SearchChangeDTO getSearchChangeDto() {
		return searchChangeDto;
	}

	/**
	 * Sets the search change dto.
	 * 
	 * @param searchChangeDto
	 *            the new search change dto
	 */
	public void setSearchChangeDto(SearchChangeDTO searchChangeDto) {
		this.searchChangeDto = searchChangeDto;
	}

	/**
	 * Gets the user.
	 * 
	 * @return the user
	 */
	public Map getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 * 
	 * @param user
	 *            the new user
	 */
	public void setUser(Map user) {
		this.user = user;
	}

	/**
	 * 根据条件查询用户.
	 * 
	 * @return the string
	 */
	public String queryList() {
		try {
			if (searchUserDto == null) {
				searchUserDto = new SearchUserDTO();
			}

			if (isPostRequest()) {
				initPager();
				return renderGrid(staffDAO.getUserList(searchUserDto));
			} else {
				Map<String, String> code = UserKindHelper
						.valueMap(getUserInfo()
								.getUserKind());
				setAttribute("codeUserKind", code);

				// 部门列表
				setAttribute("deptList", userService.getDeptTree());

				return "query";
			}
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * 根据条件查询用户.
	 * 
	 * @return the string
	 */
	public String queryUserChangeInfo() {
		try {
			if (searchChangeDto == null) {
				searchChangeDto = new SearchChangeDTO();
				java.util.Date now = new java.util.Date();
				searchChangeDto.setStartTime(DateFunc.dateToString(
						DateFunc.addMonths(now, -1), "yyyy-MM-dd")
						+ " 00:00:00");
				searchChangeDto.setEndTime(DateFunc.dateToString(now, "yyyy-MM-dd")
						+ " 23:59:59");
				searchChangeDto.setEventType("0");
			}
			searchChangeDto.setChangeType("1");// 设置变更类型为人员基本信息变更

			if (isPostRequest()) {
				initPager();
				return renderGrid(changeDAO.queryChangeDetail(searchChangeDto));
			} else {
				return "changeInfo";
			}
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * 查询用户信息.
	 * 
	 * @return the string
	 */
	public String queryUserInfo() {
		// 获取用户信息
		String staffId = this.getParameter("staff_id");
		user = staffDAO.findUser(staffId);
		if (user != null && user.size() > 0) {
			String deptId = getString(user, "dept_id");
			if (UtilFunc.hasText(deptId)) {
				user.put("dept_name", UtilFunc.getValue(
						deptDAO.getDeptInfo(deptId), "dept_name"));
			}

			user.put(
					"staff_sta_name",
					CodetableMapping.getDisplayValue("staff_sta",
							user.get("staff_sta")));
			user.put(
					"grade_name",
					CodetableMapping.getDisplayValue("grade_id",
							user.get("grade_id")));
			user.put(
					"staff_role_name",
					CodetableMapping.getDisplayValue("staff_role",
							user.get("staff_role")));
			user.put(
					"center_name",
					CodetableMapping.getDisplayValue("aaa027_list",
							user.get("center_id")));
			user.put(
					"staff_level_name",
					CodetableMapping.getDisplayValue("staff_level",
							user.get("staff_level")));
		}

		this.setAttribute("staff_id", staffId);
		this.setAttribute("user", user);

		return "infoDetail";
	}

	/**
	 * 导出用户信息excel.
	 * 
	 * @return the string
	 */
	public String exportUserInfoXls() {
		try {
			String staffId = this.getParameter("staff_id");
			if(staffId == null){
				throw new HygeiaException("需要导出的用户不能为空");
			}
			
			List<Integer> lst = null;
			if(staffId.startsWith("[")){
				lst = JsonHelper.toList(staffId, Integer.class);
			} else {
				lst = new ArrayList<Integer>();
				lst.add(Integer.valueOf(staffId));
			}
			
			String fileName = "用户信息.xls";
			HSSFWorkbook wb = new HSSFWorkbook();

			if (lst != null && lst.size() > 0) {
				for (Integer userId : lst) {
					Map map = staffDAO.findUser(String.valueOf(userId));

					String sheetName = (String) map.get("staff_name");
					if (UtilFunc.isEmpty(sheetName)) {
						sheetName = "Sheet1";
					}

					HSSFSheet sheet = wb.createSheet(sheetName);
					userService.initExportUserInfoXls(wb, sheet, map);
				}
			}
			HttpServletResponse response = getResponse();
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-disposition", String.format(
					"attachment; filename=\"%1$s\"",
					DownloadHelper.encodeFileName(fileName)));
			wb.write(response.getOutputStream());

			return NONE;
		} catch (Exception ex) {
			if (!WebHelper.isClientAbortException(ex)) {
				renderError(ex);
			}
			return NONE;
		}
	}
}