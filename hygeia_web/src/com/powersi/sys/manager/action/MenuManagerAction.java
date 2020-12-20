/*
 * Copyright 2015 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;

import com.powersi.hygeia.framework.GlobalContext;
import com.powersi.hygeia.framework.ParameterMapping;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.BeanHelper;
import com.powersi.hygeia.framework.util.CollectionHelper;
import com.powersi.hygeia.framework.util.DAOHelper;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.LogHelper;
import com.powersi.hygeia.framework.util.SysFunc;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.action.UploadAction;
import com.powersi.hygeia.web.util.JsonHelper;
import com.powersi.hygeia.web.util.WebHelper;
import com.powersi.sys.entity.DownloadFile;
import com.powersi.sys.manager.dao.MenuDAO;
import com.powersi.sys.manager.dao.RoleDAO;
import com.powersi.sys.manager.service.MenuService;
import com.powersi.sys.user.dao.StaffDAO;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.DownloadHelper;
import com.powersi.sys.util.MenuHelper;
import com.powersi.sys.util.PagerHelper;

/**
 * The Class MenuManagerAction.
 */
@Action(value = "MenuManager", results = {
		@Result(name = "success", location = "/pages/sys/manager/MenuManager.jsp"),
		@Result(name = "menuTree", location = "/pages/sys/manager/MenuTree.jsp"),
		@Result(name = "list", location = "/pages/sys/manager/MenuList.jsp")
})
public class MenuManagerAction extends UploadAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The role dao. */
	public static RoleDAO roleDAO = BeanHelper.getBean(RoleDAO.class);

	/** The staff dao. */
	public static StaffDAO staffDAO = BeanHelper.getBean(StaffDAO.class);

	/** The menu dao. */
	public static MenuDAO menuDAO = BeanHelper.getBean(MenuDAO.class);

	/** The menu service. */
	public static MenuService menuService = BeanHelper
			.getBean(MenuService.class);

	//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
	 boolean checkRight = !ParameterMapping.getStringByCode("check_conn_mysql", "0").equals("0");
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		try {
			setAttribute("roleList", roleDAO.getAllRoleList());
			return SUCCESS;
		} catch (Exception ex) {
			saveError(ex);
			return ERROR;
		}
	}

	/**
	 * Gets the menu tree.
	 * 
	 * @return the menu tree
	 */
	@Action("GetMenuTree")
	public String getMenuTree() {
		try {
			String rootMenuid = getParameter("root_menu_id");
			if (!UtilFunc.hasText(rootMenuid)) {
				rootMenuid = "0";
			}

			List lst = menuDAO.getMenuTree(rootMenuid);
			setJSONReturn(lst);
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}

	/**
	 * Gets the menu.
	 * 
	 * @return the menu
	 */
	@Action("GetMenuInfo")
	public String getMenuInfo() {
		try {
			String menuId = getParameter("menu_id");
			if (!UtilFunc.hasText(menuId)) {
				throw new HygeiaException("菜单编号不能为空");
			}

			Map map = menuDAO.getMenuInfo(menuId);

			// 获取角色信息
			String roles = UtilFunc.joinList(roleDAO.getMenuRole(menuId),
					"role_id", ",");
			if (UtilFunc.hasLength(roles)) {
				map.put("roles", roles);
			}

			setJSONReturn(map);
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}

	/**
	 * Save menu.
	 * 
	 * @return the string
	 */
	@Actions({
			@Action("AddMenu"),
			@Action("ModifyMenu")
	})
	public String saveMenu() {
		try {
			Map map = new HashMap();
			map.putAll(getAllParameters());

			String menuId = UtilFunc.getString(map, "menu_id");
			String menuUpId = UtilFunc.getString(map, "menu_up_id");
			validate(map);
			
			if (menuId.equals("-1")) {
				menuId = String.valueOf(SysFunc.getMaxNo("menu_id"));
				map.put("menu_id", menuId);

				//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
			    if(checkRight){
			    	//lingang 增加menu_fid字段
			    	String menu_fid = "-0-";
			    	if(!"0".equals(menuUpId)) {
			    		menu_fid = menuDAO.getMenuInfo(menuUpId).get("menu_fid").toString();
			    	}
			    	map.put("menu_fid", menu_fid+menuId+"-");
			    }
				
				menuDAO.insertMenu(map);
			} else {
				if (menuDAO.checkMenuNesting(menuId, menuUpId)) {
					throw new HygeiaException("菜单不能嵌套，请修改上级菜单");
				}

				//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
			    if(checkRight){
			    	//lingang 增加menu_fid字段
			    	String menu_fid = "-0-";
			    	if(!"0".equals(menuUpId)) {
			    		menu_fid = menuDAO.getMenuInfo(menuUpId).get("menu_fid").toString();
			    	}
			    	map.put("menu_fid", menu_fid+menuId+"-");
			    	menuDAO.updateMenuValidFlag(map);
			    }
				
				menuDAO.updateMenu(map);
			}

			// 处理菜单角色
			roleDAO.deleteRoleMenuByMenu(menuId);

			if ("1".equals(UtilFunc.getString(map, "right_flag", "1"))) {
				String[] roles = getParameterValues("roles");
				if (roles != null && roles.length > 0) {
					roleDAO.insertRoleMenuByMenu(menuId, Arrays.asList(roles));
				}
			}

			Map retMap = new HashMap();
			retMap.put("menu_id", menuId);
			setJSONReturn("保存成功", retMap);

			// RefreshMappingUtil.refresh("menuright");
			MenuHelper.updateCache();
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}

	/**
	 * Del menu.
	 * 
	 * @return the string
	 */
	@Action("DelMenu")
	public String delMenu() {
		try {
			Map map = new HashMap();
			map.putAll(getAllParameters());

			String menuId = UtilFunc.getString(map, "menu_id");
			if (!UtilFunc.hasText(menuId) || menuId.equals("0")) {
				throw new HygeiaException("菜单编号不能为空");
			}

			if (!getUserInfo().isSuperUser() && menuDAO.hasChildMenu(menuId)) {
				throw new HygeiaException("存在子菜单或者权限，不能删除");
			}

			menuDAO.deleteMenu(menuId);

			Map retMap = new HashMap();
			retMap.put("menu_id", menuId);
			setJSONReturn("删除成功", retMap);

			// RefreshMappingUtil.refresh("menuright");
			MenuHelper.updateCache();
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}

	/**
	 * Gets the menu tree.
	 * 
	 * @return the menu tree
	 */
	public String getMenuTreeByRoles() {
		try {
			String roles = getParameter("roles");
			if (UtilFunc.isEmpty(roles)) {
				roles = "-1";
			}

			List lst = menuDAO.getMenuTreeByRoles(roles);
			setJSONReturn(lst);
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}

	/** 按角色或者按用户查询菜单树 show the menu tree. */
	public static final int QUERY_USER = 0;

	/** The Constant QUERY_ROLE. */
	public static final int QUERY_ROLE = 1;

	/**
	 * Show menu tree.
	 * 
	 * @return the string
	 */
	public String showMenuTree() {
		int queryType = QUERY_USER;// 0代表根据用户查询，1代表根据角色查询
		String queryId = getParameter("staff_id");
		if (UtilFunc.isEmpty(queryId)) {
			queryId = getParameter("role_id");
			queryType = QUERY_ROLE;
		}

		if (UtilFunc.isEmpty(queryId)) {
			throw new HygeiaException("用户编号不能为空");
		}

		Map rsMap = null;
		if (QUERY_USER == queryType) {
			// 获取用户信息
			rsMap = staffDAO.findUser(queryId);
			// 获取角色信息
			List roleList = roleDAO.getUserRoleList(queryId,
					staffDAO.getUserKind());

			String roles = UtilFunc.joinList(roleList, "role_id", ",");
			String rolenames = UtilFunc.joinList(roleList, "role_name", ",");
			if (UtilFunc.hasLength(roles)) {
				rsMap.put("roles", roles);
				rsMap.put("rolenames", rolenames);
			}
		} else if (QUERY_ROLE == queryType) {
			rsMap = new HashMap();
			Map roleMap = roleDAO.getRoleInfo(queryId);
			rsMap.put("staff_name", roleMap.get("role_name"));
			rsMap.put("login_user", roleMap.get("role_name"));

			if (UtilFunc.hasLength(queryId)) {
				rsMap.put("roles", queryId);
				rsMap.put("rolenames", roleMap.get("role_name"));
			}
		}

		setJSONAttribute("rsMap", rsMap);
		return "menuTree";
	}

	/**
	 * Export menu tree by roles.
	 * 
	 * @return the string
	 */
	public String exportMenuTreeByRoles() {
		try {
			String roles = getParameter("roles");
			if (UtilFunc.isEmpty(roles)) {
				roles = "-1";
			}

			List lst = menuDAO.getMenuTreeByRoles(roles);

			HSSFWorkbook wb = new HSSFWorkbook();
			String loginUser = getParameter("loginUser");
			String fileName = String.format("权限菜单%1$s.xls",
					loginUser == null ? "" : "(" + loginUser + ")");
			String sheetName = getParameter("userName");
			if (UtilFunc.isEmpty(sheetName)) {
				sheetName = "Sheet1";
			}
			HSSFSheet sheet = wb.createSheet(sheetName);
			// 初始化excel
			menuService.initMenuExportInfo(wb, sheet, lst);

			HttpServletResponse response = getResponse();
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-disposition", String.format(
					"attachment; filename=\"%1$s\"",
					new String(fileName.getBytes(GlobalContext.getCharset()),
							"ISO8859-1")));
			wb.write(response.getOutputStream());

			return NONE;
		} catch (Exception ex) {
			// 对于用户取消下载的异常不进行处理
			if (!"org.apache.catalina.connector.ClientAbortException".equals(ex
					.getClass().getName())) {
				saveError(ex);
			}
			return NONE;
		}
	}

	/**
	 * Validate.
	 * 
	 * @param map
	 *            the map
	 */
	private void validate(Map map) {
		String menuId = UtilFunc.getString(map, "menu_id");
		if (!UtilFunc.hasText(menuId) || menuId.equals("0")) {
			throw new HygeiaException("菜单编号不能为空");
		}

		String menuUpId = UtilFunc.getString(map, "menu_up_id");
		if (!UtilFunc.hasText(menuUpId)) {
			throw new HygeiaException("上级菜单编号不能为空");
		}

		if (menuId.equals(menuUpId)) {
			throw new HygeiaException("上级菜单不能与菜单编号相同");
		}

		if (!UtilFunc.hasText(UtilFunc.getString(map, "menu_name"))) {
			throw new HygeiaException("菜单名称不能为空");
		}

		if (!UtilFunc.hasText(UtilFunc.getString(map, "menu_order"))) {
			throw new HygeiaException("显示序号不能为空");
		}
		// 2016-09-09 lingang hygeia_web修改支持mysql数据库
		//查看是否存在窗口类型
		if(!UtilFunc.hasText(UtilFunc.getString(map, "win_type"))) {
			map.put("win_type", 2);
		}
		//查看是否存在外部程序标志
		if(!UtilFunc.hasText(UtilFunc.getString(map, "out_app"))) {
			map.put("out_app", 0);
		}
	}

	/**
	 * 刷新菜单权限缓存.
	 * 
	 * @return the string
	 */
	@Actions({
			@Action("RefreshMenuMapping"),
			@Action("RefreshRoleMapping")
	})
	public String refreshCache() {
		try {
			MenuHelper.refreshCache();
		} catch (Exception ex) {
			LogHelper.getLogger().warn("刷新菜单权限缓存出错", ex);
		}

		return NONE;
	}

	/**
	 * Gets the menu task.
	 * 
	 * @return the menu task
	 */
	@Action("GetMenuTask")
	public String getMenuTask() {
		try {
			setJSONReturn(menuDAO.getMenuTask(getParameter("menu_id")));
		} catch (Exception ex) {
			saveJSONError("获取任务菜单出错", ex);
		}

		return NONE;
	}

	/**
	 * Save menu task.
	 * 
	 * @return the string
	 */
	@Action("SaveMenuTask")
	public String saveMenuTask() {
		try {
			menuDAO.saveMenuTask(getAllParameters());

			saveJSONMessage("保存任务菜单成功");
		} catch (Exception ex) {
			saveJSONError("保存任务菜单出错", ex);
		}

		return NONE;
	}

	/**
	 * Delete menu task.
	 * 
	 * @return the string
	 */
	@Action("DelMenuTask")
	public String delMenuTask() {
		try {
			menuDAO.deleteMenuTask(getParameter("menu_id"));

			saveJSONMessage("删除任务菜单成功");
		} catch (Exception ex) {
			saveJSONError("删除任务菜单出错", ex);
		}

		return NONE;
	}

	/**
	 * Query.
	 * 
	 * @return the string
	 */
	public String query() {
		try {
			if (WebHelper.isPostRequest(getRequest())) {
				PagerHelper.initPagination(getRequest());
				List lst = menuDAO.query(getAllParameters());
				DataGridHelper.render(getRequest(), getResponse(),
						PagerHelper.getPaginatedList(lst));
				return NONE;
			} else {
				return "list";
			}
		} catch (Exception ex) {
			saveError("查询菜单出错", ex);
			return ERROR;
		}
	}

	/**
	 * Delete.
	 * 
	 * @return the string
	 */
	public String delete() {
		return delMenu();
	}

	/**
	 * Save.
	 * 
	 * @return the string
	 */
	public String save() {
		try {
			Map map = new HashMap();
			map.putAll(getAllParameters());

			String id = UtilFunc.getString(map, "id");
			String menuId = UtilFunc.getString(map, "menu_id");
			String menuUpId = UtilFunc.getString(map, "menu_up_id");
			validate(map);

			if (UtilFunc.isBlank(id)) {
				if ("0".equals(menuId)) {
					menuId = String.valueOf(SysFunc.getMaxNo("menu_id"));
					map.put("menu_id", menuId);
				}

				if (!UtilFunc.isEmpty(menuDAO.getMenuInfo(menuId))) {
					throw new HygeiaException("菜单编号 " + menuId + " 已经存在");
				}

				menuDAO.insertMenu(map);
			} else {
				if (menuDAO.checkMenuNesting(menuId, menuUpId)) {
					throw new HygeiaException("菜单不能嵌套，请修改上级菜单");
				}

				if (!menuId.equals(id)) {
					if (!UtilFunc.isEmpty(menuDAO.getMenuInfo(menuId))) {
						throw new HygeiaException("菜单编号 " + menuId + " 已经存在");
					}

					menuDAO.changeMenuId(id, menuId);
				}

				menuDAO.updateMenu(map);
			}

			Map retMap = new HashMap();
			retMap.put("menu_id", menuId);
			setJSONReturn("保存成功", retMap);

			// RefreshMappingUtil.refresh("menuright");
			MenuHelper.updateCache();
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}

	public String download() {
		try {
			List<Map> menuList = null;
			List<Map> roleList = null;
			if ("all".equals(getParameter("range"))) {
				menuList = menuDAO.query(getAllParameters());
			} else {
				menuList = menuDAO.query(getParameter("menus"));
			}

			String roles = getParameter("roles");
			if ("none".equals(roles)) {
				roleList = null;
			} else {
				if ("all".equals(getParameter("range"))) {
					roleList = menuDAO.queryMenuRole(getAllParameters());
				} else {
					roleList = menuDAO.queryMenuRole(getParameter("menus"));
				}

				if ("admin".equals(roles)
						&& CollectionHelper.isNotEmpty(roleList)) {
					List tmpList = new ArrayList();
					for (Map map : roleList) {
						if ("1".equals(UtilFunc.getString(map, "role_id"))) {
							tmpList.add(map);
						}
					}
					roleList.clear();
					roleList.addAll(tmpList);
					tmpList.clear();
				}
			}

			String fileName = "sys_menu_"
					+ DateFunc.dateToString(new java.util.Date(),
							"yyyyMMddHHmmss");
			String data = null;
			if ("sql".equals(getParameter("type"))) {
				fileName += ".sql";
				
				StringBuilder sql = new StringBuilder();
				sql.append("prompt Created on ").append(DateFunc.datetimeToString(new java.util.Date()));
				sql.append(" by ").append(getUserInfo().getUserName()).append("\r\n");
				sql.append("prompt Connect to ").append(DBHelper.getConnection().getMetaData().getURL());
				sql.append(" by ").append(DBHelper.getConnection().getMetaData().getUserName()).append("\r\n");
				sql.append("\r\n");
				sql.append(buildInsertSql("SYS_MENU", menuList)).append("\r\n");
				sql.append(buildInsertSql("SYS_ROLE_MENUS", roleList)).append("\r\n");
				sql.append("commit;");

				data = sql.toString();
			} else {
				fileName += ".json";

				Map<String, List> map = new LinkedHashMap<String, List>();

				map.put("menus", menuList);
				map.put("roles", roleList);

				data = JsonHelper.toJson(map);
			}

			DownloadFile df = new DownloadFile(fileName, data);
			DownloadHelper.download(getResponse(), df);
		} catch (Exception ex) {
			saveJSONError("导出系统任务出错", ex);
		}

		return NONE;
	}

	private String buildInsertSql(String tableName, List<Map> data) {
		if(CollectionHelper.isEmpty(data)){
			return "";
		}
		
		StringBuilder sql = new StringBuilder();
		List<String> cols = DAOHelper.getTableColumns(tableName);

		StringBuilder colSql = new StringBuilder();
		for (String col : cols) {
			if (colSql.length() > 0) {
				colSql.append(", ");
			}
			colSql.append(col);
		}
		String insertSql = String.format("insert into %1$s (%2$s)\r\n",
				tableName.toUpperCase(), colSql.toString());

		Object val;
		for (Map map : data) {
			StringBuilder valSql = new StringBuilder();
			for (String col : cols) {
				if (valSql.length() > 0) {
					valSql.append(", ");
				}

				val = map.get(col);
				if (val == null) {
					valSql.append("null");
				} else if (val instanceof Number) {
					valSql.append(val.toString());
				} else if (val instanceof java.util.Date) {
					valSql.append("to_date(").append(DateFunc.datetimeToString((java.util.Date)val)).append(", yyyy-mm-dd hh24:mi:ss)");
				} else {
					valSql.append(buildSqlValue(val.toString()));
				}
			}

			sql.append(insertSql);
			sql.append("values (");
			sql.append(valSql.toString());
			sql.append(");\r\n");
		}

		return sql.toString();
	}

	private String buildSqlValue(String value){
		StringBuilder sb = new StringBuilder(value.length() + 10);
		sb.append("'");
		for(char ch : value.toCharArray()){
			if(ch == '\n'){
				sb.append("' || chr(10) || '");
			} else if(ch == '\r'){
				sb.append("' || chr(13) || '");
			} else if(ch == '\t'){
				sb.append("' || chr(8) || '");
			} else if(ch == '&'){
				sb.append("' || chr(38) || '");
			} else if(ch == '\''){
				sb.append("''");
			} else{
				sb.append(ch);
			}
		}
		sb.append("'");
		return sb.toString();
	}
	/**
	 * Upload.
	 * 
	 * @return the string
	 */
	public String upload() {
		try {
			if (CollectionHelper.isEmpty(uploads)) {
				saveJSONError("导入文件不能为空");
				return NONE;
			}

			if (!UtilFunc.startsWithIgnoreCase(uploadsFileName.get(0),
					"sys_menu")) {
				saveJSONError("导入文件名必须使用sys_menu开始");
				return NONE;
			}
			
			if (!UtilFunc.endsWithIgnoreCase(uploadsFileName.get(0),
					".json")) {
				saveJSONError("导入文件只支持json格式的文件");
				return NONE;
			}

			String json = new String(UtilFunc.readFile(uploads.get(0)), GlobalContext.getCharset());
			
			Map data = JsonHelper.toMap(json);
			List<Map<String, Object>> menus = (List) data.get("menus");
			List<Map<String, Object>> roles = (List) data.get("roles");
			
			Set<String> keys = menuDAO.keys();
			List<Map<String, Object>> inserts = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> updates = new ArrayList<Map<String, Object>>();
			
			for (Map<String, Object> map : menus) {
				String key = map.get("menu_id").toString();
				if (!keys.contains(key)) {
					inserts.add(map);
				} else {
					updates.add(map);
				}
			}
			
			int insertcnt = 0, updatecnt = 0, rolecnt = 0;
			if (inserts.size() > 0) {
				insertcnt = inserts.size();
				
				menuDAO.insert(inserts);
			}
			
			if("true".equals(getParameter("forceUpdate")) && updates.size() > 0){
				updatecnt = updates.size();
				
				menuDAO.update(updates);
			}
			
			if(roles.size() > 0){
				rolecnt = roles.size();
				
				menuDAO.deleteMenuRole(roles);
				menuDAO.insertMenuRole(roles);
			}
			
			if(insertcnt > 0 || updatecnt > 0 || rolecnt > 0){
				MenuHelper.updateCache();
			}

			saveJSONMessage(String.format(
					"导入系统菜单成功\n(共%1$d项 其中新增%2$d项 更新%3$d项)\n共导入%4$d项菜单角色",
					menus.size(), insertcnt, updatecnt, rolecnt));
		} catch (Exception ex) {
			saveJSONError("导入系统菜单出错", ex);
		}
		
		return NONE;
	}
}
