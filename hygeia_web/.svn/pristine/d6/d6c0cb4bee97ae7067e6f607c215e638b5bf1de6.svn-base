/*
 * Copyright 2013 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.powersi.hygeia.framework.BaseServiceImpl;
import com.powersi.hygeia.framework.UserInfo;
import com.powersi.hygeia.framework.util.BeanHelper;
import com.powersi.hygeia.framework.util.SysFunc;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.sys.manager.dao.MenuDAO;
import com.powersi.sys.manager.dao.RoleDAO;
import com.powersi.sys.user.dao.ChangeDAO;
import com.powersi.sys.user.dto.SysChangeDTO;

/**
 * The Class MenuServiceImpl.
 */
public class MenuServiceImpl extends BaseServiceImpl implements MenuService{
	
	/** The role dao. */
	public static RoleDAO roleDAO = BeanHelper.getBean(RoleDAO.class);

	/* (non-Javadoc)
	 * @see com.powersi.sys.manager.service.MenuService#initMenuExportInfo(org.apache.poi.hssf.usermodel.HSSFWorkbook, org.apache.poi.hssf.usermodel.HSSFSheet, java.util.List)
	 */
	public void initMenuExportInfo(HSSFWorkbook wb, HSSFSheet sheet, List lst) {
		HSSFDataFormat txtFmt = wb.createDataFormat();
		HSSFFont menuFont = wb.createFont();
		menuFont.setColor(HSSFColor.BLUE.index);
		
		HSSFFont folderFont = wb.createFont();
		folderFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// bold.setColor(HSSFColor.WHITE.index);
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
		
		for(int i = 0; i < lst.size(); i ++) {
			Map row = (Map)lst.get(i);
			if(!"1".equals(UtilFunc.getString(row, "menu_type"))){
				continue;
			}
			
			
			HSSFRow xlsRow = sheet.createRow(rowNum);
			menuLevel = Integer.parseInt(UtilFunc.getString(row, "menu_level"));
			if(colNum < menuLevel){
				colNum = menuLevel;
			}
			
			HSSFCell cell = xlsRow.createCell(menuLevel - 1);
			cell.setCellStyle(Integer.parseInt(UtilFunc.getString(row, "menu_flag", "1")) == 1 ? menuStyle : folderStyle);
			cell.setCellValue(new HSSFRichTextString(UtilFunc.getString(row, "menu_name", "")));
			
			rowNum ++;
		}
		
		// adjust the column widths
		for(int i = 0; i < colNum; i ++){
			sheet.autoSizeColumn((short) i);
		}
	}

	/* (non-Javadoc)
	 * @see com.powersi.sys.manager.service.MenuService#saveChangeInfo(java.lang.String, java.util.List, com.powersi.hygeia.framework.UserInfo)
	 */
	public void saveChangeInfo(String roleId,List<String> newMenuIdList,UserInfo userinfo) {
		List<String> oldMenuIdList = getMenuIdList(roleDAO.getRoleMenu(roleId));
        List<String> addMenuList = getAddMenuItem(oldMenuIdList, newMenuIdList);//获取增加的菜单权限信息
        List<String> delMenuList = getDelMenuItem(oldMenuIdList, newMenuIdList);//获取删除的菜单权限信息
        saveMenuChangeInfo(addMenuList,delMenuList, roleDAO.getRoleInfo(roleId),userinfo);
	}
	
	/**
	 * Save menu change info.
	 *
	 * @param addMenuList the add menu list
	 * @param delMenuIdList the del menu id list
	 * @param roleInfo the role info
	 * @param userinfo the userinfo
	 */
	private void saveMenuChangeInfo(List<String> addMenuList,List<String> delMenuIdList,Map<String,Object> roleInfo,UserInfo userinfo){
		String roleId =  UtilFunc.getString(roleInfo, "role_id");
		SysChangeDTO changeDto = new  SysChangeDTO();
		changeDto.setLogId(String.valueOf(SysFunc.getMaxNo("log_id")));
		changeDto.setLoingUser(userinfo.getLoginUser());
		changeDto.setUserId(roleId);
		changeDto.setUserName(UtilFunc.getString(roleInfo, "role_name"));
		
		List<Map<String,Object>> detailList = new ArrayList<Map<String,Object>>();
		Map<String,Object> detailMap = null;
		MenuDAO menuDAO = BeanHelper.getBean(MenuDAO.class);
		List<Map<String,String>> allMenuList = menuDAO.getMenuTree("0");//获取所有菜单
		
		//获取增加的角色
		for(int i=0;i<addMenuList.size();i++){
			String obj = addMenuList.get(i);
			Map menuMap = getMenuMap(allMenuList,obj) ;
			if(UtilFunc.isEmpty(menuMap)){
				continue;
			}
			if("2".equals(UtilFunc.getString(menuMap, "menu_type"))){
				continue;
			}
			detailMap = new HashMap<String,Object>();
			detailMap.put("log_id", changeDto.getLogId());
			detailMap.put("old_value", "");
			detailMap.put("new_value",  menuMap.get("menu_name"));
			detailMap.put("item_name",  "菜单");
			detailMap.put("detail_id", String.valueOf(SysFunc.getMaxNo("detail_id")));
			detailMap.put("item_event_code", "1");
			detailMap.put("item_type", "2");
			detailList.add(detailMap);
		}
		//获取删除的角色
		for(int j=0;j<delMenuIdList.size();j++){
			String obj = delMenuIdList.get(j);
			Map menuMap = getMenuMap(allMenuList,obj) ;
			if("2".equals(UtilFunc.getString(menuMap, "menu_type"))){
				continue;
			}
			detailMap = new HashMap<String,Object>();
			detailMap.put("log_id", changeDto.getLogId());
			detailMap.put("old_value",  UtilFunc.getString(menuMap, "menu_name"));
			detailMap.put("new_value", "");
			detailMap.put("item_name", "菜单");
			detailMap.put("detail_id", String.valueOf(SysFunc.getMaxNo("detail_id")));
			detailMap.put("item_event_code", "2");
			detailMap.put("item_type", "2");
			detailList.add(detailMap);
		}
		
		ChangeDAO changeDao = BeanHelper.getBean(ChangeDAO.class);
		changeDao.insertChangeEvent(changeDto);
		changeDao.insertChangeDetail(detailList);
	}
	
	/**
	 * 获取增加的菜单权限信息.
	 *
	 * @param oldMenuIdList the old menu id list
	 * @param newMenuIdList the new menu id list
	 * @return the adds the menu item
	 */
	private List<String> getAddMenuItem(List<String> oldMenuIdList,List<String> newMenuIdList){
		List<String> retList = new ArrayList<String>();
		for(int i=0;i<newMenuIdList.size();i++){
			String obj =  newMenuIdList.get(i);
			if(!oldMenuIdList.contains(obj)){
				retList.add(obj);
			}
		}
		return retList;
	}
	
	/**
	 * 获取删除的菜单权限信息.
	 *
	 * @param oldMenuIdList the old menu id list
	 * @param newMenuIdList the new menu id list
	 * @return the del menu item
	 */
	private List<String> getDelMenuItem(List<String> oldMenuIdList,List<String> newMenuIdList){
		List<String> retList = new ArrayList<String>();
		for(int i=0;i<oldMenuIdList.size();i++){
			String obj = oldMenuIdList.get(i);
			if(!newMenuIdList.contains(obj)){
				retList.add(obj);
			}
		}
		return retList;
	}
	
	/**
	 * 根据菜单list生成菜单id的list.
	 *
	 * @param menuList the menu list
	 * @return the menu id list
	 */
	private List<String> getMenuIdList(List<Map<String,String>> menuList){
		List menuIdList = new ArrayList<String>();
		if(menuList!=null && menuList.size()>0){
			for(int i=0;i<menuList.size();i++){
				Map temMap = menuList.get(i);
				menuIdList.add(UtilFunc.getString(temMap, "menu_id"));
			}
		}
		return menuIdList;
	}
	
	/**
	 * 从所有菜单List中获取菜单详情Map.
	 *
	 * @param allMenuList the all menu list
	 * @param menuId the menu id
	 * @return the menu map
	 */
	private Map getMenuMap(List<Map<String,String>> allMenuList,String menuId){
		for(int i=0;i<allMenuList.size();i++){
			Map tempMap = allMenuList.get(i);
			String troleid =  UtilFunc.getString(tempMap, "menu_id");
			if(troleid.equals(menuId)){
				return tempMap;
			}
		}
		return null;
	}

}
