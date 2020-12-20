/*
 * Copyright 2014 Powersi. All rights reserved.
 */
package com.powersi.sys.message.action;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.powersi.hygeia.framework.UserInfo;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.BeanHelper;
import com.powersi.hygeia.framework.util.CollectionHelper;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.action.UploadAction;
import com.powersi.hygeia.web.util.WebHelper;
import com.powersi.sys.entity.SysBulletins;
import com.powersi.sys.message.dao.BulletinDAO;
import com.powersi.sys.message.dto.SearchBulletinDTO;
import com.powersi.sys.message.service.BulletinService;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.PagerHelper;

/**
 * The Class BulletinEditAction.
 */
@Action(value = "BulletinEditAction", results = {
		@Result(name = "save", location = "BulletinEditAction", type = "chain"),
		@Result(name = "edit", location = "/pages/sys/message/BulletinEdit.jsp"),
		@Result(name = "corp", location = "/pages/sys/message/BulletinSelCorpUser.jsp"),
		@Result(name = "center", location = "/pages/sys/message/BulletinSelCenterUser.jsp"),
		@Result(name = "hosp", location = "/pages/sys/message/BulletinSelHospUser.jsp"),
		@Result(name = "reply", location = "/pages/sys/message/BulletinViewReplyInfo.jsp"),
		@Result(name = "addreply", location = "/pages/sys/message/BulletinReplyAdd.jsp"),
		@Result(name = "add", location = "/pages/sys/message/BulletinEdit.jsp")
})
public class BulletinEditAction extends UploadAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The search bulletin dto. */
	private SearchBulletinDTO searchBulletinDto;

	/** The sys bulletin. */
	private SysBulletins sysBulletin;

	/** The bulletin id. */
	private Long bulletinId;

	/** The audit type. */
	private String auditType;
	
	/** The Constant MAX_FILE_SIZE. */
	private static final long MAX_FILE_SIZE = 10 * 1024 * 1024;

	/** The buletin svc. */
	private static BulletinService buletinSvc = BeanHelper
			.getBean(BulletinService.class);

	/** The bulletin dao. */
	private static BulletinDAO bulletinDao = BeanHelper
			.getBean(BulletinDAO.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		try {
			setAttribute("disabeldUserKind", "true");

			if(auditType == null){
				auditType = "0";
			}
			
			if (bulletinId != null && bulletinId.longValue() != 0) {
				sysBulletin = bulletinDao.getBulletin(bulletinId);
				if (sysBulletin == null) {
					throw new HygeiaException("编号[" + bulletinId + "]的公告不存在");
				}

				List lst = bulletinDao.getReceiveList(bulletinId);
				String receiveString = toJson(lst);
				sysBulletin.setReceiveString(receiveString);

				sysBulletin.setAuditType(auditType);
				
				setAttribute("fileList", bulletinDao.getFileList(bulletinId));
			} else {
				sysBulletin = new SysBulletins();

				java.util.Date now = DateFunc.getDate();
				sysBulletin.setBulletinId(Long.valueOf(0));
				sysBulletin.setDisOrder(Integer.valueOf(1000));
				sysBulletin.setBulletinType("0");
				sysBulletin.setEffectDate(now);
				sysBulletin.setExpireDate(DateFunc.getDate(2099, 1, 1));
				sysBulletin.setAuditFlag("0");
				sysBulletin.setUserKind(getParameter("userKind", "9"));
				sysBulletin.setReplyFlag("1");

				sysBulletin.setAuditType("0");
				
				setAttribute("fileList", Collections.EMPTY_LIST);
			}

			return "edit";
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * Save.
	 * 
	 * @return the string
	 */
	public String save() {
		try {
			if (isEmpty(sysBulletin.getBulletinTitle())) {
				throw new HygeiaException("公告标题不能为空");
			}

			String content = sysBulletin.getBulletinContent();
			if (isEmpty(content)) {
				throw new HygeiaException("公告内容不能为空");
			}

			if (sysBulletin.getBulletinType().equals("1")) {
				if (!content.startsWith("http://")
						&& !content.startsWith("https://")) {
					throw new HygeiaException("外部链接的公告内容必须是有效的http链接地址");
				}
			}

			if (sysBulletin.getEffectDate() == null) {
				throw new HygeiaException("生效时间不能为空");
			}

			if (sysBulletin.getExpireDate() == null) {
				sysBulletin.setExpireDate(DateFunc.getDate(2099, 1, 1));
			}

			if (CollectionHelper.isNotEmpty(uploads)) {
				for (int i = 0; i < uploads.size(); i++) {
					if (uploads.get(i).length() > MAX_FILE_SIZE) {
						throw new HygeiaException(String.format(
								"%1$s文件长度超过最大限制%2$d（实际长度%3$d)",
								uploadsFileName.get(i),
								MAX_FILE_SIZE, uploads.get(i).length()));
					}
				}
			}
			String delFiles = getParameter("delFiles");

			buletinSvc.insertOrUpdateBulletin(sysBulletin, bulletinId, uploads,
					uploadsFileName, uploadsContentType, delFiles);

			setAttribute("bulletinId", sysBulletin.getBulletinId());
			
			bulletinId = sysBulletin.getBulletinId();
			auditType = sysBulletin.getAuditType();
			
			if(isEmpty(auditType) || "0".equals(auditType)) {
				setAttribute("close", "true");
			}
			
			saveMessage("保存公告信息成功");
			return "add";
		} catch (Exception ex) {
			renderError(ex);
			try {
				return execute();
			} catch (Exception ie) {
				return renderError(ex);
			}
		}
	}

	/**
	 * 获取接收者列表.
	 * 
	 * @return the receive users
	 * @throws Exception
	 *             the exception
	 */
	public String getReceiveUsers() throws Exception {
		int type = getParameterInt("type", 0);
		if (searchBulletinDto == null) {
			searchBulletinDto = new SearchBulletinDTO();
		}

		if (3 == type || 9 == type || 2 == type) {
			setJSONReturn(buletinSvc.findReceviveList(type,
					searchBulletinDto));
		} else if (0 == type) {
			if (bulletinId == null || bulletinId.longValue() == 0) {
				saveJSONError("公告编号不能为空");
			} else {
				setJSONReturn(bulletinDao.getReceiveList(bulletinId));
			}
		} else {
			saveJSONError("不存在的接收人类型(" + getParameter("type", "") + ")");
		}

		return NONE;
	}

	/**
	 * 添加回执.
	 * 
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public String addReplyInfo() throws Exception {
		Map allParamMap = this.getAllParameters();
		UserInfo userinfo = this.getUserInfo();
		String bulletinId = UtilFunc.getString(allParamMap, "bulletinId");
		if (isEmpty(bulletinId)) {
			throw new HygeiaException("公告不存在");
		}

		// 标识回执还是添加回执 tipFlag=0打开添加回执界面 tipFlag=1是需要回执，为2时为添加回执意见
		int tipFlag = Integer.parseInt(UtilFunc.getString(allParamMap,
				"tipFlag", "0"));
		if (tipFlag == 0) {
			this.setAttribute("bulletinId", bulletinId);

			Map filter = new HashMap();
			filter.put("reply_staff", userinfo.getUserId());
			List lst = bulletinDao.getReplyInfo(Long.valueOf(bulletinId),
					filter);
			if (CollectionHelper.isNotEmpty(lst)) {
				Map map = (Map) lst.get(0);
				String replyInfo = UtilFunc.getString(map, "reply_info");
				if (!isEmpty(replyInfo)) {
					this.setAttribute("replyContent", replyInfo);
					this.setAttribute("replyDate", DateFunc
							.datetimeToString((Date) (map.get("reply_date"))));
					this.setAttribute("replyDisabled", "true");
				}
			}

			return "addreply";
		}

		// 验证1---登陆者是否属于回执者范围
		boolean ableflag = bulletinDao
				.isAbleToReply(Long.parseLong(bulletinId));
		if (!ableflag) {
			throw new HygeiaException("您不能回执该公告");
		}
		// 验证2---登陆者部门是否有人对公告已进行回执
		/*
		 * boolean dupflag =
		 * bulletinDao.hasDuplulitReplyInfo(Long.parseLong(bulletinId)); if
		 * (dupflag) { throw new HygeiaException("该公告已存在本部门的回执"); }
		 */

		Map replmap = new HashMap();
		replmap.put("bulletin_id", bulletinId);
		if("9".equals(userinfo.getUserKind())){
			replmap.put("reply_org", UtilFunc.getString(userinfo, "dept_id", "0"));
		} else{
			replmap.put("reply_org", UtilFunc.getString(userinfo, "org_id", "0"));
		}
		
		replmap.put("reply_staff", userinfo.getUserId());
		replmap.put("reply_man", userinfo.getUserName());

		if (1 == tipFlag) {
			bulletinDao.addReplyInfo(replmap);
			this.saveJSONMessage("保存回执标记成功");
		} else {
			replmap.put("reply_info",
					UtilFunc.getString(allParamMap, "replyContent"));
			bulletinDao.addReplyInfo(replmap);
			this.saveJSONMessage("保存回执意见成功");
		}
		return NONE;
	}

	/**
	 * 统计回执率.
	 * 
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public String staticsReplyInfo() throws Exception {
		if (searchBulletinDto == null) {
			searchBulletinDto = new SearchBulletinDTO();
			searchBulletinDto.setBulletinId(this.getParameter("bulletinId"));
			searchBulletinDto.setReplyStatus("1");
		}

		if (isEmpty(searchBulletinDto.getBulletinId())) {
			throw new HygeiaException("该公告不存在!");
		}

		if (WebHelper.isPostRequest(getRequest())) {
			PagerHelper.initPagination(this.getRequest());
			List replyList = buletinSvc.findReplyList(searchBulletinDto);

			DataGridHelper.render(getRequest(), getResponse(),
					PagerHelper.getPaginatedList(replyList));

			return NONE;
		} else {
			this.setAttribute("searchBulletinDto", searchBulletinDto);
			return "reply";
		}

		/*
		 * if ("1".equals(searchBulletinDto.getReplyStatus())) { // 所有回执数
		 * this.setAttribute("replyCount", replyList.size()); // 未回执数=消息数-回执数
		 * long allcount = buletinSvc.countBulletinRecive(Long
		 * .parseLong(searchBulletinDto.getBulletinId()));
		 * this.setAttribute("unreplyCount", allcount - replyList.size()); }
		 * else { // 未回执数=消息数 long allcount =
		 * buletinSvc.countBulletinRecive(Long
		 * .parseLong(searchBulletinDto.getBulletinId()));
		 * this.setAttribute("replyCount", allcount - replyList.size()); //
		 * 已回执数=all-未回执 this.setAttribute("unreplyCount", replyList.size()); }
		 */
	}

	/**
	 * 保存显示序号.
	 * 
	 * @return the string
	 */
	public String saveDisOrder() {
		try {
			bulletinId = Long.valueOf(getParameter("bulletin_id"));
			if (bulletinId == null || bulletinId.longValue() == 0) {
				throw new HygeiaException("无效的公告编号");
			}

			bulletinDao.updateDisOrder(bulletinId,
					Integer.parseInt(getParameter("dis_order")));
			
			saveJSONMessage("保存显示序号成功");
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}

	/**
	 * Sets the sys bulletin.
	 * 
	 * @param sysBulletin
	 *            the new sys bulletin
	 */
	public void setSysBulletin(SysBulletins sysBulletin) {
		this.sysBulletin = sysBulletin;
	}

	/**
	 * Gets the sys bulletin.
	 * 
	 * @return the sys bulletin
	 */
	public SysBulletins getSysBulletin() {
		return sysBulletin;
	}

	/**
	 * Gets the bulletin id.
	 * 
	 * @return the bulletin id
	 */
	public Long getBulletinId() {
		return bulletinId;
	}

	/**
	 * Sets the bulletin id.
	 * 
	 * @param bulletinId
	 *            the new bulletin id
	 */
	public void setBulletinId(Long bulletinId) {
		this.bulletinId = bulletinId;
	}

	/**
	 * Gets the search bulletin dto.
	 * 
	 * @return the search bulletin dto
	 */
	public SearchBulletinDTO getSearchBulletinDto() {
		return searchBulletinDto;
	}

	/**
	 * Sets the search bulletin dto.
	 * 
	 * @param searchBulletinDto
	 *            the new search bulletin dto
	 */
	public void setSearchBulletinDto(SearchBulletinDTO searchBulletinDto) {
		this.searchBulletinDto = searchBulletinDto;
	}
	
	/**
	 * Gets the audit type.
	 *
	 * @return the audit type
	 */
	public final String getAuditType() {
		return auditType;
	}

	/**
	 * Sets the audit type.
	 *
	 * @param auditType the new audit type
	 */
	public final void setAuditType(String auditType) {
		this.auditType = auditType;
	}
}