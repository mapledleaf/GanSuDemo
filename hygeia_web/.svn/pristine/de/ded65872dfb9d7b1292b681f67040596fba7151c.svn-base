/**
 * Copyright 2013 Powersi. All rights reserved.
 * 
 */
package com.powersi.sys.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.powersi.hygeia.framework.entity.SysUserKind;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.BeanHelper;
import com.powersi.hygeia.framework.util.RefreshMappingUtil;
import com.powersi.sys.user.dto.UserKindDTO;

/**
 * 用户类型辅助类.
 */
public abstract class UserKindHelper {
	
	/** The kind cache. */
	private static Map<String, UserKindDTO> kindCache = Collections.synchronizedMap(new LinkedHashMap<String, UserKindDTO>());
	
	/** The type cache. */
	private static Map<String, UserKindDTO> typeCache = Collections.synchronizedMap(new HashMap<String, UserKindDTO>());
	
	/** The value cache. */
	private static Map<String, String> valueCache = Collections.synchronizedMap(new LinkedHashMap<String, String>());
	
	 /** The _size. */
    static int size = 0;

    /** The _times. */
    static int times = 0;
    
    /**
     * 获取用户类型对象.
     *
     * @param userKind the user kind
     * @return the user kind dto
     */
    public static UserKindDTO get(String userKind){
    	init();
    	
    	return kindCache.get(userKind);
    }
    
    /**
     * 根据登录类型获取用户类型对象.
     *
     * @param loginType the login type
     * @return the user kind dto
     */
    public static UserKindDTO findType(String loginType){
    	init();
    	
    	return typeCache.get(loginType);
    }
    
    /**
     * 获取所有用户类型列表.
     *
     * @return the list
     */
    public static List<UserKindDTO> list(){
    	init();
    	
    	List<UserKindDTO> lst = new ArrayList<UserKindDTO>();
    	lst.addAll(kindCache.values());
    	
    	return Collections.unmodifiableList(lst);
    }
    
    /**
     * 获取指定用户类型的列表.
     *
     * @param userKind the user kind
     * @return the list
     */
    public static List<UserKindDTO> list(String userKind){
    	init();
    	
    	List<UserKindDTO> lst = new ArrayList<UserKindDTO>();
    	Map.Entry<String, UserKindDTO> entry = null;
    	Iterator<Map.Entry<String, UserKindDTO>> iter = kindCache.entrySet().iterator();
    	int kind = Integer.parseInt(userKind);
    	while(iter.hasNext()){
    		entry = iter.next();
    		if(Integer.valueOf(entry.getKey()) <= kind){
    			lst.add(entry.getValue());
    		}
    	}
    
    	return Collections.unmodifiableList(lst);
    }
    
    /**
     * 获取指定用户类型的值映射.
     *
     * @param userKind the user kind
     * @return the list
     */
    public static Map<String, String> valueMap(String userKind){
    	init();
    	
    	Map<String, String> map = new LinkedHashMap<String, String>(valueCache.size());
    	Iterator<Entry<String, String>> iter = valueCache.entrySet().iterator();
    	int kind = Integer.parseInt(userKind);
    	Entry<String, String> entry = null;
    	while(iter.hasNext()){
    		entry = iter.next();
    		if(Integer.valueOf(entry.getKey()) <= kind){
    			map.put(entry.getKey(), entry.getValue());
    		}
    	}
    
    	return map;
    }
    
    /**
     * 初始化.
     */
    public static void init() {
    	if(size == 0){
    		refresh();
    		
    		RefreshMappingUtil.add("userkind", UserKindHelper.class);
    	}
    }
	
 	/**
 	 * 刷新映射缓存.
 	 *
 	 */
    public static void refresh() {
        try {
            synchronized (kindCache) {
                // kindCache.clear();
                Set kindSet = new HashSet(kindCache.keySet());
                Set typeSet = new HashSet(typeCache.keySet());

                List<SysUserKind> lst = com.powersi.hygeia.framework.util.UserKindHelper.list();

                String kindKey = null;
                for (SysUserKind suk : lst) {
                	UserKindDTO obj = new UserKindDTO();
                	BeanHelper.copyProperties(suk, obj);
                	
                    kindKey = obj.getUserKind().toString();
                    kindSet.remove(kindKey);

                    kindCache.put(kindKey, obj);
                    valueCache.put(kindKey, obj.getKindName());
                    
                    kindKey = obj.getLoginType().toString();
                    typeSet.remove(kindKey);

                    typeCache.put(kindKey, obj);
                }

                for (Iterator it = kindSet.iterator(); it.hasNext();) {
                    kindKey = it.next().toString();
                    kindCache.remove(kindKey);
                    valueCache.remove(kindKey);
                }
                
                for (Iterator it = typeSet.iterator(); it.hasNext();) {
                    kindKey = it.next().toString();
                    typeCache.remove(kindKey);
                }

                // 记录数
                size = kindCache.size();
                if (size == 0) {
                    size = 1;
                }
                
                times ++;
            }
        } catch (HygeiaException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HygeiaException("获取用户类型映射信息出错", ex);
        }
    }
    
	public static int getTimes() {
		return times;
	}
	
	public static int  getSize() {
		return size;
	}
}