/**
 * Copyright 2013 Powersi. All rights reserved.
 * 
 */
package com.powersi.sys.user.action;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.powersi.hygeia.framework.GlobalContext;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.hygeia.web.util.DownloadHelper;
import com.powersi.hygeia.web.util.WebHelper;
import com.powersi.sys.manager.dao.DeptDAO;
import com.powersi.sys.manager.dao.RoleDAO;
import com.powersi.sys.user.dao.ChangeDAO;
import com.powersi.sys.user.dao.StaffDAO;
import com.powersi.sys.user.dto.SearchChangeDTO;
import com.powersi.sys.user.dto.SearchUserDTO;
import com.powersi.sys.user.service.UserService;

/**
 * The Class UserRightAction.
 */
@Action(value = "UserRightAction", results = {
		@Result(name = "queryByStaff", location = "/pages/sys/user/QueryRightInfoByStaff.jsp"),
		@Result(name = "queryByRole", location = "/pages/sys/user/QueryRightInfoByRole.jsp"),
		@Result(name = "changeInfo", location = "/pages/sys/user/QueryRightChangeInfo.jsp")
})
public class UserRightAction extends BaseAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The search user dto. */
	private SearchUserDTO searchUserDto;

	/** The search change dto. */
	private SearchChangeDTO searchChangeDto;

	/** The dept dao. */
	public static DeptDAO deptDAO = getBean(DeptDAO.class);

	/** The role dao. */
	public static RoleDAO roleDAO = getBean(RoleDAO.class);

	/** The staff dao. */
	public static StaffDAO staffDAO = getBean(StaffDAO.class);

	/** The change dao. */
	public static ChangeDAO changeDAO = getBean(ChangeDAO.class);

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
				// 部门列表
				setAttribute("deptList", userService.getDeptTree());

				return "queryByStaff";
			}
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * Query role list.
	 * 
	 * @return the string
	 */
	public String queryRoleList() {
		try {
			if (searchUserDto == null) {
				searchUserDto = new SearchUserDTO();
			}

			if (isPostRequest()) {
				initPager();
				return renderGrid(roleDAO.searchRoleListBySearchDto(searchUserDto));
			} else {
				// 部门列表
				setAttribute("deptList", userService.getDeptTree());

				return "queryByRole";
			}
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * 查询用户权限变更信息.
	 * 
	 * @return the strings
	 */
	public String queryUserChangeInfo() {
		try {
			if (searchChangeDto == null) {
				searchChangeDto = new SearchChangeDTO();
				java.util.Date now = new java.util.Date();
				searchChangeDto.setStartTime(DateFunc.dateToString(
						DateFunc.addMonths(now, -1), "yyyy-MM-dd")
						+ " 00:00:00");
				searchChangeDto.setEndTime(DateFunc.dateToString(
						now, "yyyy-MM-dd")
						+ " 23:59:59");
				searchChangeDto.setEventType("0");
			}

			searchChangeDto.setChangeType("2");// 设置变更类型为人员权限信息变更

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
	 * Export info.
	 * 
	 * @return the string
	 */
	public String exportInfo() {
		try {
			String staffId = this.getParameter("staffId");
			JSONArray json = JSONArray.fromObject(staffId);
			String fileName = String.format("用户权限菜单.xls");
			HSSFWorkbook wb = new HSSFWorkbook();

			// 导出每个人的权限信息
			if (json != null && json.size() > 0) {
				// 创建第一个sheet为人员list,并初始化头部
				HSSFSheet listSheet = wb.createSheet("人员列表");
				userService
						.initExportUserListXls(wb, listSheet, null, true, -1);

				for (int index = 0; index < json.size(); index++) {
					JSONObject jObject = (JSONObject) json.get(index);
					String userId = (String) jObject.get("staff_id");
					Map map = staffDAO.findUser(userId);

					// 初始化人员list的
					String sheetName = (String) map.get("staff_name");
					if (UtilFunc.isEmpty(sheetName)) {
						sheetName = "Sheet1";
					}

					HSSFSheet sheet = wb.createSheet(sheetName);
					userService.initExportUserListXls(wb, listSheet, map,
							false, index);
					userService.initExportUserRightXls(wb, sheet, map);
				}
			}
			HttpServletResponse response = getResponse();
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-disposition", String.format(
					"attachment; filename=\"%1$s\"",
					new String(fileName.getBytes(GlobalContext.getCharset()),
							"ISO8859-1")));
			wb.write(response.getOutputStream());

			return NONE;
		} catch (Exception ex) {
			if (!WebHelper.isClientAbortException(ex)) {
				renderError(ex);
			}
			return NONE;
		}
	}

	/**
	 * 根据角色导出角色权限.
	 * 
	 * @return the string
	 */
	public String exportRoleInfo() {
		try {
			String roles = this.getParameter("roleIds");
			JSONArray json = JSONArray.fromObject(roles);
			String fileName = String.format("角色权限菜单.xls");
			HSSFWorkbook wb = new HSSFWorkbook();

			if (json != null && json.size() > 0) {
				// 创建第一个sheet为角色list,并初始化头部
				HSSFSheet listSheet = wb.createSheet("角色列表");
				userService
						.initExportRoleListXls(wb, listSheet, null, true, -1);
				for (int index = 0; index < json.size(); index++) {
					JSONObject jObject = (JSONObject) json.get(index);
					String roleId = (String) jObject.get("role_id");
					Map map = roleDAO.getRoleInfo(roleId);

					String sheetName = (String) map.get("role_name");
					if (UtilFunc.isEmpty(sheetName)) {
						sheetName = "Sheet1";
					}

					HSSFSheet sheet = wb.createSheet(sheetName);
					userService.initExportRoleListXls(wb, listSheet, map,
							false, index);
					userService.initExportRoleRightXls(wb, sheet, map);
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
			// 对于用户取消下载的异常不进行处理
			if (!WebHelper.isClientAbortException(ex)) {
				renderError(ex);
			}
			return NONE;
		}
	}
}