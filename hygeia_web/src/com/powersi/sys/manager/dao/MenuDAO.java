/*
 * Copyright 2015 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.powersi.hygeia.framework.BaseDAO;

/**
 * 菜单DAO接口.
 */
public interface MenuDAO extends BaseDAO {
	
	/** DAO对应表名. */
	public static final String TABLE_NAME = "sys_menu";
	
    /**
     * 获取菜单树.
     *
     * @param rootMenuId 根菜单编号
     * @return the menu tree
     */
    public List getMenuTree(String rootMenuId);

    /**
     * 获取菜单树.
     *
     * @param rootMenuId 根菜单编号
     * @param filterMap 条件选项
     * @return the menu tree
     */
    public List getMenuTree(String rootMenuId, Map filterMap);

    /**
     * 获取菜单.
     *
     * @param menuId the menu id
     * @return the menu
     */
    public Map getMenuInfo(String menuId);

    /**
     * 是否存在子菜单.
     *
     * @param menuId the menu id
     * @return true, if successful
     */
    public boolean hasChildMenu(String menuId);

    /**
     * 检查菜单是否嵌套.
     *
     * @param menuId the menu id
     * @param menuUpId the menu up id
     * @return true, if successful
     */
    public boolean checkMenuNesting(String menuId, String menuUpId);

    /**
     * 插入菜单.
     *
     * @param menuMap the menu map
     * @return the int
     */
    public int insertMenu(Map menuMap);

    /**
     * 更新菜单.
     *
     * @param menuMap the menu map
     * @return the int
     */
    public int updateMenu(Map menuMap);
    
    /**
     * 更新菜单，修改是否有效标记.
     *
     * @param menuMap the menu map
     * @return the int
     *///  2016-09-09 lingang hygeia_web修改支持mysql数据库
    public int updateMenuValidFlag(Map menuMap);

    /**
     * 删除菜单.
     *
     * @param menuId the menu id
     * @return the int
     */
    public int deleteMenu(String menuId);
    
    /**
     * 根据角色获取菜单树.
     *
     * @param roles the roles
     * @return the menu tree by roles
     */
    public List getMenuTreeByRoles(String roles);
    
    
    /**
     * Gets the menu task.
     *
     * @param menuId the menu id
     * @return the menu task
     */
    public Map getMenuTask(String menuId);
    
    
    /**
     * Save menu task.
     *
     * @param taskMap the task map
     * @return the int
     */
    public int saveMenuTask(Map taskMap);
    
    
    /**
     * Delete menu task.
     *
     * @param menuId the menu id
     * @return the int
     */
    public int deleteMenuTask(String menuId);
    
    /**
     * Query.
     *
     * @param param the param
     * @return the list
     */
    public List query(Map param);
    
    /**
     * Query.
     *
     * @param menus the menus
     * @return the list
     */
    public List query(String menus);
    
    
    /**
     * Change menu id.
     *
     * @param oldMenuId the old menu id
     * @param newMenuId the new menu id
     */
    public void changeMenuId(String oldMenuId, String newMenuId);
    
    /**
     * Keys.
     *
     * @return the sets the
     */
    public Set<String> keys();
    
    /**
     * 插入菜单.
     *
     * @param data the data
     * @return the int
     */
    public int insert(List data);

    /**
     * 更新菜单.
     *
     * @param data the data
     * @return the int
     */
    public int update(List data);
    
 
    /**
     * Query menu role.
     *
     * @param param the param
     * @return the list
     */
    public List queryMenuRole(Map param);
    
    /**
     * Query menu role.
     *
     * @param menus the menus
     * @return the list
     */
    public List queryMenuRole(String menus);
    
  
    /**
     * Delete menu role.
     *
     * @param data the data
     * @return the int
     */
    public int deleteMenuRole(List data);
    
    /**
     * Insert menu role.
     *
     * @param data the data
     * @return the int
     */
    public int insertMenuRole(List data);
}
