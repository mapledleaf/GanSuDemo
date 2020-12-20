/*
 * Copyright 2013 Powersi. All rights reserved.
 */
package com.powersi.sys.message.service;

import java.util.List;

import com.powersi.hygeia.framework.BaseService;
import com.powersi.sys.entity.SysBulletins;
import com.powersi.sys.message.dto.SearchBulletinDTO;


/**
 * The Interface BulletinService.
 */
public interface BulletinService extends BaseService {
	
	/**
	 * 插入或者更新公告.
	 *
	 * @param sysBulletin the sys bulletin
	 * @param bulletinId the bulletin id
	 * @param uploads the uploads
	 * @param uploadsFileName the uploads file name
	 * @param uploadsContentType the uploads content type
	 * @param delFiles the del files
	 * @return the int
	 */
	public int insertOrUpdateBulletin(SysBulletins sysBulletin, Long bulletinId,List uploads,List uploadsFileName,
			List uploadsContentType, String delFiles);
	
	/**
	 * 获取公告.
	 *
	 * @param bulletinId the bulletin id
	 * @return the bulletin
	 */
	public SysBulletins getBulletin(Long bulletinId);
	
	/**
	 * 获取接收人列表.
	 *
	 * @param type 2：单位 3：医院 9：中心
	 * @param searchBulletinDto the search bulletin dto
	 * @return the list
	 */
	public List findReceviveList(int type, SearchBulletinDTO searchBulletinDto);
	
	/**
	 * 获取回执信息列表.
	 *
	 * @param searchBulletinDto the search bulletin dto
	 * @return the list
	 */
	public List findReplyList(SearchBulletinDTO searchBulletinDto);
	
	/**
	 * 获取发送的总共公告数量.
	 *
	 * @param bulletinId the bulletin id
	 * @return the long
	 */
	public long countBulletinRecive(Long bulletinId);
	
	
	/**
	 * Query message list.
	 *
	 * @param loginFlag the login flag
	 * @return the list
	 */
	public List queryMessageList(boolean loginFlag);
	
}
