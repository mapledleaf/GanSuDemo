/*
 * Copyright 2013 Powersi. All rights reserved.
 */
package com.powersi.sys.message.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import com.powersi.hygeia.framework.BaseServiceImpl;
import com.powersi.hygeia.framework.util.BeanHelper;
import com.powersi.hygeia.framework.util.CollectionHelper;
import com.powersi.hygeia.framework.util.SysFunc;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.util.JsonHelper;
import com.powersi.sys.entity.SysBulletins;
import com.powersi.sys.message.dao.BulletinDAO;
import com.powersi.sys.message.dto.SearchBulletinDTO;

/**
 * The Class BulletinServiceImpl.
 */
public class BulletinServiceImpl extends BaseServiceImpl implements
		BulletinService {

	/** The bulletin dao. */
	private static BulletinDAO bulletinDao = BeanHelper
			.getBean(BulletinDAO.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.message.service.BulletinService#insertOrUpdateBulletin
	 * (com.powersi.sys.entity.SysBulletins, java.lang.Long, java.util.List,
	 * java.util.List, java.util.List, java.lang.String)
	 */
	public int insertOrUpdateBulletin(SysBulletins sysBulletin,
			Long bulletinId, List uploads, List uploadsFileName,
			List uploadsContentType, String delFiles) {

		if (UtilFunc.isEmpty(sysBulletin.getAuditType())
				|| "0".equals(sysBulletin.getAuditType())) {
			sysBulletin.setAuditFlag("0");
			sysBulletin.setValidFlag("1");
			sysBulletin.setSecondAuditFlag("1");
			sysBulletin.setSendDate(new java.util.Date());
			sysBulletin.setSenderMan(getUserInfo().getUserName());
			sysBulletin.setSenderStaff(Integer.valueOf(getUserInfo()
					.getUserId()));
		}

		if (sysBulletin.getBulletinId().longValue() == 0) {
			bulletinId = Long.valueOf(SysFunc.getMaxNo("bulletin_id"));
			sysBulletin.setBulletinId(bulletinId);
			bulletinDao.insertBulletin(sysBulletin);
		} else {
			bulletinId = sysBulletin.getBulletinId();
			bulletinDao.updateBulletin(sysBulletin);
		}

		/*
		 * List userKind = new ArrayList();
		 * userKind.add(sysBulletin.getUserKind());
		 * bulletinDao.insertUserList(bulletinId, userKind);
		 */

		if (CollectionHelper.isNotEmpty(uploads)) {
			bulletinDao.insertFile(sysBulletin.getBulletinId(), uploads, uploadsFileName, uploadsContentType);
		}

		if (UtilFunc.hasText(delFiles)) {
			bulletinDao.deleteFile(bulletinId, UtilFunc.split(delFiles, ","));
		}

		// 处理接收用户列表
		bulletinDao.delBulletinReceive(bulletinId);

		String receives = sysBulletin.getReceiveUsers();
		if (!UtilFunc.isEmpty(receives)) {
			//List<Map<String, Object>> recList = JsonHelper.toList(receives);
				/*for (Map bulReciMap : recList) {
					bulReciMap.put("bulletin_id", bulletinId);
					bulReciMap.put("bulletin_type",
							sysBulletin.getBulletinType());
					bulReciMap.put("receive_statue", Integer.valueOf(0));
					bulReciMap.put("receive_count", Integer.valueOf(0));
				}*/
			List<Map<String, Object>> recList=new ArrayList<>();
			Map<String, Object> bulReciMap=new HashMap<String, Object>();
			bulReciMap.put("bulletin_id", bulletinId);
			bulReciMap.put("bulletin_type", sysBulletin.getBulletinType());
			bulReciMap.put("receive_statue", Integer.valueOf(0));
			bulReciMap.put("receive_count", Integer.valueOf(0));
			bulReciMap.put("receive_org", receives);
			recList.add(bulReciMap);
			if (CollectionHelper.isNotEmpty(recList)) {

				bulletinDao.insertBulletinReceive(recList);
			}
		}

		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.message.service.BulletinService#getBulletin(java.lang
	 * .Long)
	 */
	public SysBulletins getBulletin(Long bulletinId) {
		return bulletinDao.getBulletin(bulletinId);
	}

	/**
	 * 获取接收人列表.
	 * 
	 * @param type
	 *            2：单位 3：医院 9：中心
	 * @param searchBulletinDto
	 *            the search bulletin dto
	 * @return the list
	 */
	public List findReceviveList(int type, SearchBulletinDTO searchBulletinDto) {
		List rsList = null;
		switch (type) {
		case 2:
			rsList = bulletinDao.findCorpList(searchBulletinDto);
			break;
		case 3:
			rsList = bulletinDao.findHospList(searchBulletinDto);
			break;
		case 9:
			rsList = bulletinDao.findCenterList(searchBulletinDto);
			break;
		}

		return rsList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.message.service.BulletinService#findReplyList(com.powersi
	 * .sys.message.dto.SearchBulletinDTO)
	 */
	public List findReplyList(SearchBulletinDTO searchBulletinDto) {
		return bulletinDao.findReplyList(searchBulletinDto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.message.service.BulletinService#countBulletinRecive(java
	 * .lang.Long)
	 */
	public long countBulletinRecive(Long bulletinId) {
		return bulletinDao.getReceiveCount(bulletinId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.message.service.BulletinService#queryMessageList(boolean)
	 */
	public List queryMessageList(boolean loginFlag) {
		List lst = null;
		if (loginFlag) {
			lst = bulletinDao.getLoginMessage();
		} else {
			if (!bulletinDao.hasNewMessage()) {
				return null;
			}

			lst = bulletinDao.getQueryMessage();
		}

		bulletinDao.updateQueryMessage();
		return lst;
	}
}
