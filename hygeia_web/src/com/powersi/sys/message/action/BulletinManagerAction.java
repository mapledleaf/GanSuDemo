/**
 * Copyright 2011 Powersi. All rights reserved.
 */
package com.powersi.sys.message.action;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.powersi.hygeia.framework.CodetableMapping;
import com.powersi.hygeia.framework.GlobalContext;
import com.powersi.hygeia.framework.cache.DBCache;
import com.powersi.hygeia.framework.cache.DBCacheManager;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.BeanHelper;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.powerreport.service.store.PowerReportStore;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.sys.entity.DownloadFile;
import com.powersi.sys.entity.FileBulletins;
import com.powersi.sys.entity.SysBulletins;
import com.powersi.sys.message.dao.BulletinDAO;
import com.powersi.sys.message.dto.SearchBulletinDTO;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.PagerHelper;

/**
 * The Class BulletinManagerAction.
 */
@Action(value = "BulletinManagerAction", results = {
		@Result(name = "success", location = "/pages/sys/message/BulletinManager.jsp"),
		@Result(name = "viewBulletinInfo", location = "/pages/sys/message/BulletinView.jsp"),
		@Result(name = "viewBulletinFile", type = "stream", params = {
				"contentType", "${downloadFile.contentType}",
				"contentDisposition", "${downloadFile.contentDisposition}",
				"inputName", "downloadFile.fileStream" }),
		@Result(name = "listBulletin", location = "/pages/sys/message/BulletinList.jsp"),
		@Result(name = "viewBulletin", location = "/pages/sys/message/BulletinView.jsp"),
		@Result(name = "secondAudit", location = "/pages/sys/message/BulletinSecondAudit.jsp"),
		@Result(name = "auditList", location = "/pages/sys/message/BulletinAudit.jsp"),
		@Result(name = "replyList", location = "/pages/sys/message/BulletinReply.jsp") })
public class BulletinManagerAction extends BaseAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The search bulletin dto. */
	private SearchBulletinDTO searchBulletinDto;

	/** The bulletin id. */
	private Long bulletinId = null;

	/** The download file. */
	private DownloadFile downloadFile;

	/** The bulletin dao. */
	public static BulletinDAO bulletinDao = getBean(BulletinDAO.class);

	/** The sys bulletin. */
	private SysBulletins sysBulletin;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		try {
			if (searchBulletinDto == null) {
				searchBulletinDto = new SearchBulletinDTO();
				java.util.Date now = new java.util.Date();
				searchBulletinDto.setBeginDate(DateFunc
						.dateToString(DateFunc.addDays(now, -30)) + " 00:00:00");
				searchBulletinDto.setEndDate(DateFunc.dateToString(now) + " 23:59:59");
				searchBulletinDto.setAuditFlag(new ArrayList<String>());
				/*searchBulletinDto.getAuditFlag().add("0");
				searchBulletinDto.getAuditFlag().add("1");
				searchBulletinDto.getAuditFlag().add("2");*/
				/*searchBulletinDto.setSecondAuditFlag(new ArrayList<String>());
				searchBulletinDto.getSecondAuditFlag().add("0");
				searchBulletinDto.getSecondAuditFlag().add("2");*/
				searchBulletinDto.setUserKind(new ArrayList<String>());

				Map<String, Object> map = CodetableMapping
						.get("bulletin_user_kind");
				for (String key : map.keySet()) {
					searchBulletinDto.getUserKind().add(key);
				}
			}
			searchBulletinDto.setQueryType("1");// 编辑

			if (isPostRequest()) {
				initPager();
		
				renderGrid(bulletinDao.queryList(searchBulletinDto));
				return NONE;
			} else {
				return SUCCESS;
			}
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * Delete.
	 * 
	 * @return the string
	 */
	public String delete() {
		try {
			if (bulletinId == null || bulletinId.longValue() == 0) {
				throw new HygeiaException("删除公告，无效的公告编号");
			}

			bulletinDao.deleteBulletin(bulletinId);

			Map map = new HashMap();
			map.put("bulletin_id", bulletinId);
			return renderJson("删除公告成功", map);
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 */
	/**
	 * Audit list.
	 * 
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public String auditList() throws Exception {
		try {
			if (searchBulletinDto == null) {
				searchBulletinDto = new SearchBulletinDTO();
				java.util.Date now = new java.util.Date();
				searchBulletinDto.setBeginDate(DateFunc
						.dateToString(DateFunc.addDays(now, -30)) + " 00:00:00");
				searchBulletinDto.setEndDate(DateFunc.dateToString(now) + " 23:59:59");
				searchBulletinDto.setAuditFlag(new ArrayList<String>());
				searchBulletinDto.getAuditFlag().add("0");
				searchBulletinDto.getAuditFlag().add("1");
				searchBulletinDto.getAuditFlag().add("2");
			}
			searchBulletinDto.setQueryType("2");// 审核

			if (isPostRequest()) {
				initPager();

				renderGrid(bulletinDao.queryList(searchBulletinDto));
				return NONE;
			} else {
				return "auditList";
			}
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * Audit.
	 * 
	 * @return the string
	 */
	public String audit() {
		try {
			if (bulletinId == null || bulletinId.longValue() == 0) {
				throw new HygeiaException("审核通过公告，无效的公告编号");
			}

			String auditType = "1";

			checkAudit(bulletinId, auditType);
			bulletinDao.auditBulletin(bulletinId, auditType,
					getString(getAllParameters(), "auditFlag", "1"),
					getParameter("auditOpinion"));

			Map map = new HashMap();
			map.put("bulletin_id", bulletinId);
			return renderJson("审核公告成功", map);
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * Cancel.
	 * 
	 * @return the string
	 */
	public String cancel() {
		try {
			if (bulletinId == null || bulletinId.longValue() == 0) {
				throw new HygeiaException("取消审核公告，无效的公告编号");
			}

			bulletinDao.cancelBulletin(bulletinId, "1");

			Map map = new HashMap();
			map.put("bulletin_id", bulletinId);
			return renderJson("取消审核公告成功", map);
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * View.
	 * 
	 * @return the string
	 */
	public String view() {
		try {
			if (bulletinId == null || bulletinId.longValue() == 0) {
				throw new HygeiaException("查看公告，无效的公告编号");
			}

			Map map = bulletinDao.viewBulletin(bulletinId);
			if (isEmpty(map)) {
				throw new HygeiaException("编号[" + bulletinId + "]的公告不存在");
			}

			map.put("file_list", bulletinDao.getFileList(bulletinId));
			setBulletinInfo(map);

			this.setAttribute("viewFlag", "true");
			return "viewBulletin";
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * Sets the bulletin info.
	 * 
	 * @param map
	 *            the map
	 */
	private void setBulletinInfo(Map map) {
		sysBulletin = new SysBulletins();
		sysBulletin.setBulletinTitle(getString(map, "bulletin_title"));
		sysBulletin.setBulletinContent(getString(map,
				"bulletin_content"));
		sysBulletin.setBulletinId(Long.parseLong(getString(map,
				"bulletin_id", "0")));
		sysBulletin.setAuditFlag(getString(map, "audit_flag"));
		sysBulletin.setSenderMan(getString(map, "sender_man"));
		sysBulletin.setReceiveUsers(getString(map, "receive_users"));
		if (map.get("audit_date") != null) {
			sysBulletin.setBulletinDate(DateFunc
					.datetimeToString((java.util.Date) map.get("audit_date")));
		} else {
			sysBulletin.setBulletinDate(DateFunc
					.datetimeToString((java.util.Date) map.get("send_date")));
		}
		sysBulletin.setFileList((List) map.get("file_list"));
		sysBulletin.setSecondAuditFlag(getString(map,
				"second_audit_flag"));

		sysBulletin.setReplyFlag(getString(map, "reply_flag"));

		this.setAttribute("fileList", map.get("file_list"));
	}

	/**
	 * List.
	 * 
	 * @return the string
	 */
	public String list() {
		try {
			if (searchBulletinDto == null) {
				searchBulletinDto = new SearchBulletinDTO();
				java.util.Date now = new java.util.Date();
				searchBulletinDto.setBeginDate(DateFunc
						.dateToString(DateFunc.addDays(now, -30)) + " 00:00:00");
				searchBulletinDto.setEndDate(DateFunc.dateToString(now) + " 23:59:59");
			}

			// 查询审核审签通过的记录
			searchBulletinDto.setEffectDate(DateFunc
					.datetimeToString(new java.util.Date()));
			searchBulletinDto.setSecondAuditFlag(new ArrayList<String>());
			searchBulletinDto.getSecondAuditFlag().add("1");
			searchBulletinDto.setAuditFlag(new ArrayList<String>());
			searchBulletinDto.getAuditFlag().add("1");
			String receive_users=BizHelper.getAaa027();
			searchBulletinDto.setReceive_users(receive_users);
			
			searchBulletinDto.setQueryType("0");// 列表
			List userKinds = new ArrayList();
			userKinds.add("0");
			userKinds.add(getUserInfo().getUserKind());
			searchBulletinDto.setUserKind(userKinds);

			if (isPostRequest()) {
				PagerHelper.initPagination(this.getRequest());
				List list=bulletinDao.queryList(searchBulletinDto);
				//renderGrid(bulletinDao.queryList(searchBulletinDto));
				DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(list));
				return NONE;
			} else {
				return "listBulletin";
			}
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * Query reply list.
	 * 
	 * @return the string
	 */
	public String queryReplyList() {
		try {
			if (searchBulletinDto == null) {
				searchBulletinDto = new SearchBulletinDTO();
				java.util.Date now = new java.util.Date();
				searchBulletinDto.setBeginDate(DateFunc
						.dateToString(DateFunc.addDays(now, -30)) + " 00:00:00");
				searchBulletinDto.setEndDate(DateFunc.dateToString(now) + " 23:59:59");
				
				List userKind = new ArrayList();
				Map<String, Object> map = CodetableMapping
						.get("bulletin_user_kind");
				for (String key : map.keySet()) {
					userKind.add(key);
				}
				searchBulletinDto.setUserKind(userKind);

				List replyFlag = new ArrayList();
				replyFlag.add("0");
				replyFlag.add("1");
				replyFlag.add("2");
				searchBulletinDto.setReplyFlag(replyFlag);
			}

			// 查询审核审签通过的记录
			searchBulletinDto.setSecondAuditFlag(new ArrayList<String>());
			searchBulletinDto.getSecondAuditFlag().add("1");
			searchBulletinDto.setAuditFlag(new ArrayList<String>());
			searchBulletinDto.getAuditFlag().add("1");

			searchBulletinDto.setQueryType("4");// 回执

			if (isPostRequest()) {
				initPager();

				renderGrid(bulletinDao.queryList(searchBulletinDto));
				return NONE;
			} else {
				return "replyList";
			}
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * Gets the file.
	 * 
	 * @return the file
	 */
	public void getFile() {
		try {
			String bid = getParameter("bid");
			String fid = getParameter("fid");			
			String storeid = java.net.URLDecoder.decode(getParameter("storeid"),"utf-8");
			if (isEmpty(bid)) {
				throw new HygeiaException("公告编号不能为空");
			}
			if (isEmpty(fid)) {
				throw new HygeiaException("附件编号不能为空");
			}

			String fileName = String.format("%1$s_%2$s.tmp", bid, fid);

			String path = GlobalContext.getPhysicalPath("downloads/bulletin/"
					+ fileName);
			boolean fileExists = UtilFunc.fileExists(path);
			
			
			FileBulletins file=bulletinDao.getFileAttch(Long.valueOf(bid), fid, !fileExists);
			if(file==null){
				throw new HygeiaException("无法获取指定的公告附件");
			}
			if (!fileExists) {
			/*	FileDownLoad f = new FileDownLoad();
				f.downLoad(getRequest(), getResponse(), file.getFile_name(), file.getFile_content());*/
				/*synchronized (BulletinManagerAction.class) {
					DBHelper.blobToFile(map.get("file_content"), path);
				}*/
				// 获取reportID bizID 差数
				//String reportID = request.getParameter("reportID");
				//String bizID = request.getParameter("bizID");
				// 设置response的编码方式
				// 获取报表的名称
				//String reportName = powerReport.getReportName(reportID, bizID) + ".xls";
				
				getRequest().setCharacterEncoding("gbk");
				String reportName = new String(file.getFile_name().getBytes("GBK"), "ISO8859-1");
				getResponse().setContentType("application/octet-stream");
				// 发送到客户端的文件名称
				getResponse().setHeader("Content-Disposition", "attachment;filename=" + reportName);
				// 根据参数获取的输出流
				OutputStream myout = getResponse().getOutputStream();
				PowerReportStore powerReportStore = (PowerReportStore) BeanHelper.getBean("PowerReportStore");
				powerReportStore.get(storeid, myout);
				//PowerReport powerReport = BeanHelper.getBean(PowerReport.class);
				//powerReport.getReport("239889c899cf48e5a6e4833cafa929a8", null, myout);//201803-9b9405a57ae24eaaabd5bcac3967cbc3
			}						

			/*downloadFile = new DownloadFile(path);
			downloadFile.setFileName(getString(map, "file_name"));*/
			/*Map map = bulletinDao.getFile(Long.valueOf(bid), fid, !fileExists);
			if (isEmpty(map)) {
				throw new HygeiaException("无法获取指定的公告附件");
			}

			if (!fileExists) {
				FileDownLoad f = new FileDownLoad();
				f.downLoad(getRequest(), getResponse(), map.get("file_name").toString(), (Blob)map.get("file_content"));
				synchronized (BulletinManagerAction.class) {
					DBHelper.blobToFile(map.get("file_content"), path);
				}
			}

			downloadFile = new DownloadFile(path);
			downloadFile.setFileName(getString(map, "file_name"));*/

		//	return "viewBulletinFile";
		} catch (Exception ex) {
			  throw new HygeiaException(ex);
		}
	}

	/**
	 * 公告信息(缓存).
	 * 
	 * @return the menu render
	 * @throws Exception
	 *             the exception
	 */
	public String bulletinInfo() throws Exception {
		try {
			String bulletinId = this.getParameter("bulletinId");
			if (isEmpty(bulletinId)) {
				throw new HygeiaException("查看公告，公告编号不能为空");
			}

			/*// 检查是否存在该用户类型的公告
			DBCache cache = DBCacheManager.getInstance().getCache( "bulletinlist");
			Map findMap = null;
			findMap = (Map) cache.find(new Object[] { bulletinId });
			if (isEmpty(findMap)) {
				throw new HygeiaException("编号[" + bulletinId + "]的公告不存在");
			}*/

			Map map = null;
			DBCache cacheDetail = DBCacheManager.getInstance().getCache("bulletindetail");
			List lst = cacheDetail.query(new Object[] { bulletinId });
			if (!isEmpty(lst)) {
				map = (Map) lst.get(0);
			}

			if (isEmpty(map)) {
				throw new HygeiaException("编号[" + bulletinId + "]的公告不存在");
			}

			if (map.get("file_list") == null) {
				map.put("file_list",
						bulletinDao.getFileList(Long.valueOf(bulletinId)));
			}

/*			// 获取操作员的回执
			String replyFlag = getString(map, "reply_flag", "0");
			if (!"0".equals(replyFlag)) {
				UserInfo userInfo = getUserInfo();
				Map filter = new HashMap();
				filter.put("reply_staff", userInfo.getUserId());
				List replyList = bulletinDao.getReplyInfo(
						Long.valueOf(bulletinId), filter);
				if (CollectionHelper.isNotEmpty(replyList)) {
					Map replyMap = (Map) replyList.get(0);
					this.setAttribute("replyContent",
							getString(replyMap, "reply_info"));
					java.util.Date replyDate = (Date) replyMap
							.get("reply_date");
					if (replyDate != null) {
						this.setAttribute("replyDate",
								DateFunc.datetimeToString(replyDate));
					}

					this.setAttribute("replyDisabled", "true");
				} else {
					// 类型1自动保存确认回执
					if ("1".equals(replyFlag)) {
						if (getUserInfo().isValidUser()) {
							Map replmap = new HashMap();
							replmap.put("bulletin_id", bulletinId);
							if ("9".equals(userInfo.getUserKind())) {
								replmap.put("reply_org", getString(
										userInfo, "dept_id", "0"));
							} else {
								replmap.put("reply_org", getString(
										userInfo, "org_id", "0"));
							}

							replmap.put("reply_staff", userInfo.getUserId());
							replmap.put("reply_man", userInfo.getUserName());

							bulletinDao.addReplyInfo(replmap);
						}

						this.setAttribute("replyDisabled", "true");
					}

					// 类型2自动添加回执意见
					if ("2".equals(replyFlag)) {
						this.setAttribute("replyContent", "已阅");
					}
				}
			}*/

			setBulletinInfo(map);
			return "viewBulletinInfo";
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * Query second audit list.
	 * 
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public String querySecondAuditList() throws Exception {
		try {
			if (searchBulletinDto == null) {
				searchBulletinDto = new SearchBulletinDTO();
				java.util.Date now = new java.util.Date();
				searchBulletinDto.setBeginDate(DateFunc
						.dateToString(DateFunc.addDays(now, -30)) + " 00:00:00");
				searchBulletinDto.setEndDate(DateFunc.dateToString(now) + " 23:59:59");
				searchBulletinDto.setSecondAuditFlag(new ArrayList<String>());
				searchBulletinDto.getSecondAuditFlag().add("0");
				searchBulletinDto.getSecondAuditFlag().add("2");
			}
			searchBulletinDto.setQueryType("3");// 审签
			searchBulletinDto.setAuditFlag(new ArrayList<String>());
			searchBulletinDto.getAuditFlag().add("1");

			if (isPostRequest()) {
				initPager();

				renderGrid(bulletinDao.queryList(searchBulletinDto));
				return NONE;
			} else {
				return "secondAudit";
			}
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * Audit.
	 * 
	 * @return the string
	 */
	public String secondAudit() {
		try {
			if (bulletinId == null || bulletinId.longValue() == 0) {
				throw new HygeiaException("审签通过公告，无效的公告编号");
			}

			String auditType = "2";

			checkAudit(bulletinId, auditType);
			bulletinDao.auditBulletin(bulletinId, auditType,
					getString(getAllParameters(), "auditFlag", "1"),
					getParameter("auditOpinion"));

			flushCache();

			Map map = new HashMap();
			map.put("bulletin_id", bulletinId);
			return renderJson("审签公告成功", map);
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * Cancel.
	 * 
	 * @return the string
	 */
	public String cancelSecondAudit() {
		try {
			if (bulletinId == null || bulletinId.longValue() == 0) {
				throw new HygeiaException("取消审签公告，无效的公告编号");
			}

			bulletinDao.cancelBulletin(bulletinId, "2");

			flushCache();

			Map map = new HashMap();
			map.put("bulletin_id", bulletinId);
			return renderJson("取消审签公告成功", map);
		} catch (Exception ex) {
			return renderError(ex);
		}
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
	 * Gets the download file.
	 * 
	 * @return the download file
	 */
	public DownloadFile getDownloadFile() {
		return downloadFile;
	}

	/**
	 * Sets the download file.
	 * 
	 * @param downloadFile
	 *            the new download file
	 */
	public void setDownloadFile(DownloadFile downloadFile) {
		this.downloadFile = downloadFile;
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
	 * Sets the sys bulletin.
	 * 
	 * @param sysBulletin
	 *            the new sys bulletin
	 */
	public void setSysBulletin(SysBulletins sysBulletin) {
		this.sysBulletin = sysBulletin;
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
	 * Check audit.
	 * 
	 * @param bulletinId
	 *            the bulletin id
	 * @param auditType
	 *            审核类型(1 审核 2审签)
	 */
	private void checkAudit(Long bulletinId, String auditType) {
		SysBulletins bulletin = bulletinDao.getBulletin(bulletinId);
		if (bulletin == null) {
			throw new HygeiaException(String.format("编号[%1$d]的公告不存在",
					bulletinId));
		}

		long staffId = Long.parseLong(getUserInfo().getAdminId());
		if (staffId == 0) {
			throw new HygeiaException("无效的操作员");
		}

		if ("1".equals(auditType)) {
			if ("1".equals(bulletin.getAuditFlag())) {
				throw new HygeiaException("公告已经审核");
			}

		/*	if ("1".equals(bulletin.getSecondAuditFlag())) {
				throw new HygeiaException("公告已经审签");
			}

			if (bulletin.getSenderStaff().longValue() == staffId) {
				throw new HygeiaException("不能审核您本人发布的公告");
			}*/
		} 
		/*else {
			if (!"1".equals(bulletin.getAuditFlag())) {
				throw new HygeiaException("公告没有审核");
			}

			if ("1".equals(bulletin.getSecondAuditFlag())) {
				throw new HygeiaException("公告已经审签");
			}

			if (bulletin.getSenderStaff().longValue() == staffId) {
				throw new HygeiaException("不能审签您本人发布的公告");
			}

			if (bulletin.getAuditStaff().longValue() == staffId) {
				throw new HygeiaException("不能审签您本人审核的公告");
			}
		}*/
	}

	/**
	 * Flush cache.
	 */
	private void flushCache() {
		DBCacheManager.getInstance().flushCache("bulletinlist");
		DBCacheManager.getInstance().flushCache("bulletindetail");
		DBCacheManager.getInstance().flushCache("bulletinreceive");
	}
}