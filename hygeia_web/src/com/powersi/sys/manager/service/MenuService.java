/*
 * Copyright 2013 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.powersi.hygeia.framework.BaseService;
import com.powersi.hygeia.framework.UserInfo;

/**
 * The Interface MenuService.
 */
public interface MenuService extends BaseService{
	
	/**
	 * 初始化菜单导出信息.
	 *
	 * @param wb the wb
	 * @param sheet the sheet
	 * @param lst the lst
	 */
	public void initMenuExportInfo(HSSFWorkbook wb,HSSFSheet sheet,List lst);
	
	/**
	 * 保存权限菜单变更信息.
	 *
	 * @param roleId the role id
	 * @param newMenuIdList the new menu id list
	 * @param userinfo the userinfo
	 */
	public void  saveChangeInfo(String roleId,List<String> newMenuIdList,UserInfo userinfo);
}
