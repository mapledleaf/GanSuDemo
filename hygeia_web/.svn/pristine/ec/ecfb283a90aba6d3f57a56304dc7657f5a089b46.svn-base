package com.powersi.ssm.biz.medicare.health.action;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.comm.pojo.ListResultDTO;
import com.powersi.biz.medicare.health.pojo.HealthDTO;
import com.powersi.biz.medicare.health.service.api.HealthApiService;
import com.powersi.biz.medicare.inhospital.service.bean.BeanService;
import com.powersi.biz.medicare.inhospital.service.util.CommunalService;
import com.powersi.comm.utils.ToolUtil;
import com.powersi.hygeia.comm.entity.AZE1;
import com.powersi.hygeia.comm.service.CharCodeSwitch;
import com.powersi.hygeia.comm.service.FileDownLoad;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.CodetableMapping;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.DAOHelper;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.hygeia.web.util.JsonHelper;
import com.powersi.hygeia.web.util.WebHelper;
import com.powersi.log.service.ErrLogSnService;
import com.powersi.log.service.ErrLogSnService.ProjectType;
import com.powersi.ssm.biz.medicare.common.service.CharCodeSwitchImpl;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.ssm.biz.medicare.common.util.Utils;
import com.powersi.ssm.biz.medicare.health.service.HealthService;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.PagerHelper;
import com.powersi.sys.util.StringUtil;

@Action(value = "HealthAction", results = {
		@Result(name = "queryItemCatalog", location = "/pages/biz/medicare/health/queryItemCatalog.jsp"),
		@Result(name = "upKeh1", location = "/pages/biz/medicare/health/HospItemUp.jsp"),
		@Result(name = "upKeh2", location = "/pages/biz/medicare/health/upKeh2.jsp") })
public class HealthAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;
	private HealthDTO healthDTO;
	private File imgFile;// 上传文件

	private String imgFileName;// 上传文件名称
	private final HealthService ser = getBean(HealthService.class);
	@Autowired
	private BeanService beanService;
	@Autowired
	@Qualifier("errLogSnService")
	private ErrLogSnService errLogSnService;
	@Autowired
	private CommunalService communalService;
	private String searchTerm = null;// (queryValue)

	/**
	 * 查询体检目录信息
	 * 
	 * 
	 * @return
	 */
	public String queryItemCatalog() {
		try {
			if (WebHelper.isPostRequest(getRequest())) {
				try {
					PagerHelper.initPagination(getRequest());
					if (healthDTO == null) {
						healthDTO = new HealthDTO();
					}

					String akb020 = BizHelper.getAkb020();
					String aaa027 = BizHelper.getAaa027();
					HealthApiService healthApiService = hygeiaServiceManager.getBeanByClass(HealthApiService.class,
							akb020);
					healthDTO.setCurrentPage((PagerHelper.getPaginationObj().getPageIndex()));
					healthDTO.setPageSize(PagerHelper.getPaginationObj().getPageSize());
					if (StringUtils.isBlank(healthDTO.getAaa027())) {
						healthDTO.setAaa027(aaa027);
					}
					healthDTO.setAkb020(akb020);
					ListResult listResult = healthApiService.queryItemCatalog(healthDTO);
					List<HealthDTO> list = (List<HealthDTO>) listResult.getList();
					for (int i = 0; i < list.size(); i++) {
						list.get(i).setBkh046_name(CodetableMapping.getDisplayValue("bkh046", list.get(i).getBkh046(),
								list.get(i).getBkh046()));
					}
					ListResult retList = ListResultDTO.newInstance().setCount(list.size()).setList(list);

					PagerHelper.setPageParam(getRequest(), healthDTO);
					DataGridHelper.render(getRequest(), getResponse(),
							PagerHelper.getPaginatedList(retList.getList(), new int[] { retList.getCount() }));

				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
					// 修改概要：TS19052200153 - 【问题修复】【体检费用结算】-在体检明细信息界面双击明细信息增加结论信息，弹出窗口需要优化
					// 修改描述：ERROR是系统级错误，应放在最外层的try-catch
					// 修改人及修改时间：daliang.long 20190531
				}
			}
		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
			return ERROR;

		}
		return NONE;
	}

	/**
	 * 中心目录下载
	 * 
	 * @return
	 */
	public String healthDownloadOnCenter() {
		try {
			if (WebHelper.isPostRequest(getRequest())) {
				try {
					PagerHelper.initPagination(getRequest());
					if (healthDTO == null) {
						healthDTO = new HealthDTO();
					}

					String akb020 = BizHelper.getAkb020();
					String aaa027 = BizHelper.getAaa027();
					healthDTO.setAaa027(aaa027);
					healthDTO.setAkb020(akb020);
					HealthApiService healthApiService = hygeiaServiceManager.getBeanByClass(HealthApiService.class,
							akb020);
					healthDTO.setAkb020(akb020);
					int down = healthApiService.healthDownloadOnCenter(healthDTO);
					if (down == 0) {
						this.setJSONReturn("中心未发现新的项目目录");
					} else {
						this.setJSONReturn("下载成功！中心目录已成功保存至医院本地数据库！");
					}
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
					// 修改概要：TS19052200153 - 【问题修复】【体检费用结算】-在体检明细信息界面双击明细信息增加结论信息，弹出窗口需要优化
					// 修改描述：ERROR是系统级错误，应放在最外层的try-catch
					// 修改人及修改时间：daliang.long 20190531
				}
			}
		} catch (Exception e) {
			logger.error(ToolUtil.getExceptionInfo(e));
			this.saveJSONError(e.getMessage());
			return ERROR;
		}
		return NONE;
	}

	/**
	 * 保存新增体检目录信息
	 * 
	 * @return
	 */
	public String saveHealthCata() {
		try {

			try {
				if (healthDTO == null) {
					healthDTO = new HealthDTO();
				}
				String akb020 = BizHelper.getAkb020();
				String aaa027 = BizHelper.getAaa027();
				healthDTO.setAkb020(akb020);
				HealthApiService healthApiService = hygeiaServiceManager.getBeanByClass(HealthApiService.class, akb020);
				int size = healthApiService.checkKeh1(healthDTO);
				if (size > 0) {
					setJSONReturn("目录编码已经存在！");
					return NONE;
				}
				CharCodeSwitch charCode = new CharCodeSwitchImpl();
				String aka020 = charCode.LoadPY(healthDTO.getBkh028());
				String aka021 = charCode.LoadWB(healthDTO.getBkh028());
				healthDTO.setAka020(aka020);
				healthDTO.setAka021(aka021);
				healthDTO.setAaa027(aaa027);
				healthApiService.saveHealthCata(healthDTO);
				setJSONReturn("保存目录信息成功！");
			} catch (Throwable e) {
				String errLogSn = this.addErrSNInfo();
				this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
						.append(this.addNotBlankParameters()).append(":处理结果:").toString());
				this.saveJSONError(errLogSn + e.getMessage());
				// 修改概要：TS19052200153 - 【问题修复】【体检费用结算】-在体检明细信息界面双击明细信息增加结论信息，弹出窗口需要优化
				// 修改描述：ERROR是系统级错误，应放在最外层的try-catch
				// 修改人及修改时间：daliang.long 20190531
			}

		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
			return ERROR;
		}
		return NONE;
	}

	/**
	 * 保存新增体检目录信息
	 * 
	 * @return
	 */
	public String updateHospCata() {
		try {

			try {
				if (healthDTO == null) {
					healthDTO = new HealthDTO();
				}
				String akb020 = BizHelper.getAkb020();
				String aaa027 = BizHelper.getAaa027();
				healthDTO.setAkb020(akb020);
				HealthApiService healthApiService = hygeiaServiceManager.getBeanByClass(HealthApiService.class, akb020);
				CharCodeSwitch charCode = new CharCodeSwitchImpl();
				String aka020 = charCode.LoadPY(healthDTO.getBkh028());
				String aka021 = charCode.LoadWB(healthDTO.getBkh028());
				healthDTO.setAka020(aka020);
				healthDTO.setAka021(aka021);
				healthDTO.setAaa027(aaa027);
				int saveCount = healthApiService.updateHospCata(healthDTO);
				//修改概要：TS19080100095 - 【问题修复】【医院体检项目目录维护】-界面提示信息优化且“备注”信息写值问题
				//修改描述：提示有问题 应为 修改 
				//修改人及修改时间：李嘉伦 20190802
				if (saveCount > 0) {
					setJSONReturn("修改目录信息成功！");
				} else {
					setJSONReturn("修改目录信息失败！");
				}
			} catch (Throwable e) {
				String errLogSn = this.addErrSNInfo();
				this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
						.append(this.addNotBlankParameters()).append(":处理结果:").toString());
				this.saveJSONError(errLogSn + e.getMessage());
				// 修改概要：TS19052200153 - 【问题修复】【体检费用结算】-在体检明细信息界面双击明细信息增加结论信息，弹出窗口需要优化
				// 修改描述：ERROR是系统级错误，应放在最外层的try-catch
				// 修改人及修改时间：daliang.long 20190531
			}

		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
			return ERROR;
		}
		return NONE;
	}

	/**
	 * 上传目录文件与插入数据库
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	public String uploadFileCata() {
		List list = null;
		try {

			try {
				if (healthDTO == null) {
					healthDTO = new HealthDTO();
				}
				String akb020 = BizHelper.getAkb020();
				String aaa027 = BizHelper.getAaa027();
				healthDTO.setAaa027(aaa027);
				healthDTO.setAkb020(akb020);
				String upFileName = DateFunc.dateToString(new Date(), "yyyyMMdd") + "_"
						+ String.valueOf(new Date().getTime()) + "_" + imgFileName;
				// 将文件上传至web服务器[如果是集群服务器，上传的EXCEL将无法寻找回来]
				this.uploadFile(imgFile, upFileName, "/uploadfile/hospfile");
				// 解析上传的文件信息返回List
				HealthApiService healthApiService = hygeiaServiceManager.getBeanByClass(HealthApiService.class, akb020);

				list = ser.getExcelContextCata(imgFile, healthDTO);

				Map mImport = healthApiService.chackImportDetail(list, healthDTO);
				String username = BizHelper.getUserName();
				healthDTO.setBkc033(this.getImgFileName());
				healthDTO.setBkc030(username.length() > 20 ? username.substring(0, 20) : username);// 名称超过20个字符截取
				healthDTO.setBkc031(BizHelper.getLoginUser()); // 导入人工号
				healthDTO.setAaa027(BizHelper.getAaa027()); // 统筹区编码

				List listImport = healthApiService.saveExcelAsCata(mImport, healthDTO);

				this.setJSONReturn("上传成功！", listImport);

			} catch (Throwable e) {
				String errLogSn = this.addErrSNInfo();
				this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
						.append(this.addNotBlankParameters()).append(":处理结果:").toString());
				this.saveJSONError(errLogSn + e.getMessage());
				// 修改概要：TS19052200153 - 【问题修复】【体检费用结算】-在体检明细信息界面双击明细信息增加结论信息，弹出窗口需要优化
				// 修改描述：ERROR是系统级错误，应放在最外层的try-catch
				// 修改人及修改时间：daliang.long 20190531
			}

		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
			return ERROR;
		}
		return NONE;
	}

	/**
	 * 体检结论批量导入
	 * 
	 * @return
	 */
	public String uploadFileConclusion() {
		List list = null;
		try {

			try {
				if (healthDTO == null) {
					healthDTO = new HealthDTO();
				}
				String akb020 = BizHelper.getAkb020();
				String aaa027 = BizHelper.getAaa027();
				healthDTO.setAaa027(aaa027);
				healthDTO.setAkb020(akb020);
				String upFileName = DateFunc.dateToString(new Date(), "yyyyMMdd") + "_"
						+ String.valueOf(new Date().getTime()) + "_" + imgFileName;
				// 将文件上传至web服务器[如果是集群服务器，上传的EXCEL将无法寻找回来]
				this.uploadFile(imgFile, upFileName, "/uploadfile/hospfile");
				// 解析上传的文件信息返回List
				HealthApiService healthApiService = hygeiaServiceManager.getBeanByClass(HealthApiService.class, akb020);

				list = ser.getExcelConclusion(imgFile, healthDTO);

				Map mImport = healthApiService.chackImportConclusion(healthDTO, list);
				String username = BizHelper.getUserName();
				healthDTO.setBkc033(this.getImgFileName());
				healthDTO.setBkc030(username.length() > 20 ? username.substring(0, 20) : username);// 名称超过20个字符截取
				healthDTO.setBkc031(BizHelper.getLoginUser()); // 导入人工号
				List listImport = healthApiService.saveExcelAsConclusion(mImport, healthDTO);
				this.setJSONReturn("上传成功！", listImport);

			} catch (Throwable e) {
				String errLogSn = this.addErrSNInfo();
				this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
						.append(this.addNotBlankParameters()).append(":处理结果:").toString());
				this.saveJSONError(errLogSn + e.getMessage());
				// 修改概要：TS19052200153 - 【问题修复】【体检费用结算】-在体检明细信息界面双击明细信息增加结论信息，弹出窗口需要优化
				// 修改描述：ERROR是系统级错误，应放在最外层的try-catch
				// 修改人及修改时间：daliang.long 20190531
			}

		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
			return ERROR;
		}
		return NONE;
	}

	/**
	 * 上传文件方法
	 * 
	 * @param upload
	 * @param filePath
	 */
	public void uploadFile(File upload, String uploadFileName, String filePath) {
		String filepath = ServletActionContext.getServletContext().getRealPath(filePath);
		File saveFile = null;
		if (upload != null) {
			File saveDir = new File(filepath);
			if (!saveDir.exists()) {
				saveDir.mkdirs();
			}
			saveFile = new File(filepath + "/" + uploadFileName);
			try {
				FileUtils.copyFile(upload, saveFile);
			} catch (IOException e) {
				throw new HygeiaException("读取Excel文件出错，请确认文件是否正确！！");
			}
		}
	}

	/**
	 * 模板下载
	 * 
	 * @param upload
	 * @param uploadFileName
	 * @param filePath
	 */
	public void downLoadExampleXls() {
		try {
			FileDownLoad f = new FileDownLoad();
			Map map = new HashMap();
			String bzc001 = getParameter("bzc001");
			if (StringUtil.isBlank(bzc001)) {
				throw new HygeiaException("模板编号为空，请检查模板信息！");
			}
			map.put("bzc001", bzc001);
			String[] wheres = { "bzc001" };
			AZE1 aze1 = DAOHelper.queryForObject("AZE1", map, wheres, AZE1.class);
			f.downLoad(getRequest(), getResponse(), aze1.getBzc002(), aze1.getBzc003());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}

	}

	public String queryAllImportItem() {
		try {
			try {
				PagerHelper.initPagination(getRequest());
				if (healthDTO == null) {
					healthDTO = new HealthDTO();
				}
				String akb020 = BizHelper.getAkb020();
				String aaa027 = BizHelper.getAaa027();
				HealthApiService healthApiService = hygeiaServiceManager.getBeanByClass(HealthApiService.class, akb020);
				String bkc133 = getParameter("bkc133");
				healthDTO.setAkb020(akb020);
				healthDTO.setBkc133(bkc133);
				healthDTO.setAkb020(akb020);
				healthDTO.setAaa027(aaa027);
				healthDTO.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
				healthDTO.setPageSize(PagerHelper.getPaginationObj().getPageSize());
				PagerHelper.setPageParam(getRequest(), healthDTO);
				ListResult listResult = healthApiService.queryKeh20(healthDTO);
				DataGridHelper.render(getRequest(), getResponse(),
						PagerHelper.getPaginatedList(listResult.getList(), new int[] { listResult.getCount() }));

			} catch (Throwable e) {
				String errLogSn = this.addErrSNInfo();
				this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
						.append(this.addNotBlankParameters()).append(":处理结果:").toString());
				this.saveJSONError(errLogSn + e.getMessage());
				// 修改概要：TS19052200153 - 【问题修复】【体检费用结算】-在体检明细信息界面双击明细信息增加结论信息，弹出窗口需要优化
				// 修改描述：ERROR是系统级错误，应放在最外层的try-catch
				// 修改人及修改时间：daliang.long 20190531
			}

		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
			return ERROR;
		}
		return NONE;
	}

	/**
	 * 查询导入体检目录临时表详情信息
	 * 
	 * @return
	 */
	public String queryImportCata() {
		try {

			try {
				PagerHelper.initPagination(getRequest());
				if (healthDTO == null) {
					healthDTO = new HealthDTO();
				}
				String akb020 = BizHelper.getAkb020();
				HealthApiService healthApiService = hygeiaServiceManager.getBeanByClass(HealthApiService.class, akb020);
				healthDTO.setAkb020(akb020);
				healthDTO.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
				healthDTO.setPageSize(PagerHelper.getPaginationObj().getPageSize());
				PagerHelper.setPageParam(getRequest(), healthDTO);
				ListResult listResult = healthApiService.queryKeh21(healthDTO);
				DataGridHelper.render(getRequest(), getResponse(),
						PagerHelper.getPaginatedList(listResult.getList(), new int[] { listResult.getCount() }));
			} catch (Throwable e) {
				String errLogSn = this.addErrSNInfo();
				this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
						.append(this.addNotBlankParameters()).append(":处理结果:").toString());
				this.saveJSONError(errLogSn + e.getMessage());
				// 修改概要：TS19052200153 - 【问题修复】【体检费用结算】-在体检明细信息界面双击明细信息增加结论信息，弹出窗口需要优化
				// 修改描述：ERROR是系统级错误，应放在最外层的try-catch
				// 修改人及修改时间：daliang.long 20190531
			}

		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
			return ERROR;
		}
		return NONE;
	}

	/**
	 * 保存验证通过信息
	 * 
	 * @return
	 */
	public String saveCatalogInfo() {
		try {

			try {
				PagerHelper.initPagination(getRequest());
				if (healthDTO == null) {
					healthDTO = new HealthDTO();
				}
				String akb020 = BizHelper.getAkb020();
				String bkc133 = getParameter("bkc133");
				HealthApiService healthApiService = hygeiaServiceManager.getBeanByClass(HealthApiService.class, akb020);
				healthDTO.setAkb020(akb020);
				healthDTO.setBkc133(bkc133);
				healthDTO.setBkc252("1");
				healthDTO.setPageSize(999999999);

				int listSize = healthApiService.saveImportCata(healthDTO);
				if (listSize > 0) {
					// 任务概述:TS19052200128 - 【问题修复】【医院体检项目目录维护】-界面优化及功能缺陷汇总
					// 修改说明:导入的数据“导入时间”需要进行格式化(yyyy-MM-dd)显示
					// 修改人及修改时间：daliang.long 20190528
					setJSONReturn("保存成功[" + listSize + "]条数据！");
				} else {
					setJSONReturn("未保存任何数据，请检查数据是否重复！");
				}
			} catch (Throwable e) {
				String errLogSn = this.addErrSNInfo();
				this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
						.append(this.addNotBlankParameters()).append(":处理结果:").toString());
				this.saveJSONError(errLogSn + e.getMessage());
				// 修改概要：TS19052200153 - 【问题修复】【体检费用结算】-在体检明细信息界面双击明细信息增加结论信息，弹出窗口需要优化
				// 修改描述：ERROR是系统级错误，应放在最外层的try-catch
				// 修改人及修改时间：daliang.long 20190531
			}

		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
			return ERROR;
		}
		return NONE;
	}

	/**
	 * 删除导入体检目录信息
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String deleItemInfo() {
		try {
			try {
				int i = 0;
				String akb020 = BizHelper.getAkb020();
				String aaa027 = BizHelper.getAkb020();
				if (healthDTO == null) {
					healthDTO = new HealthDTO();
				}
				HealthApiService healthApiService = hygeiaServiceManager.getBeanByClass(HealthApiService.class, akb020);
				healthDTO.setAkb020(akb020);
				healthDTO.setAaa027(aaa027);
				String bkc133 = getParameter("bkc133");
				healthDTO.setBkc133(bkc133);
				i += healthApiService.deleteKeh21(healthDTO);
				i += healthApiService.deleteKeh20(healthDTO);
				Map map = new HashMap();
				map.put("data", healthDTO.getBkc133());

				if (i > 0) {
					setJSONReturn("删除信息成功！", map);
				} else {
					setJSONReturn("删除信息失败！", map);
				}
			} catch (Throwable e) {
				String errLogSn = this.addErrSNInfo();
				this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
						.append(this.addNotBlankParameters()).append(":处理结果:").toString());
				this.saveJSONError(errLogSn + e.getMessage());
				// 修改概要：TS19052200153 - 【问题修复】【体检费用结算】-在体检明细信息界面双击明细信息增加结论信息，弹出窗口需要优化
				// 修改描述：ERROR是系统级错误，应放在最外层的try-catch
				// 修改人及修改时间：daliang.long 20190531
			}

		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
			return ERROR;
		}
		return NONE;
	}

	/**
	 * 删除体检目录信息
	 * 
	 * @return
	 */
	public String deleteKeh1() {
		try {

			try {
				String akb020 = BizHelper.getAkb020();
				if (healthDTO == null) {
					healthDTO = new HealthDTO();
				}
				String data = getParameter("data");
				if (UtilFunc.hasText(data)) {
					HealthApiService healthApiService = hygeiaServiceManager.getBeanByClass(HealthApiService.class,
							akb020);
					healthDTO.setAkb020(akb020);
					List<HealthDTO> hs = new ArrayList<HealthDTO>();
					List<Map<String, Object>> list = JsonHelper.toList(data);
					for (int i = 0; i < list.size(); i++) {
						Map<String, Object> map = list.get(i);
						HealthDTO dto = new HealthDTO();
						this.beanService.copyProperties(map, dto, true);
						hs.add(dto);
					}
					healthApiService.deleteKeh1(healthDTO, hs);
					setJSONReturn("操作成功！");
				}
			} catch (Throwable e) {
				String errLogSn = this.addErrSNInfo();
				this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
						.append(this.addNotBlankParameters()).append(":处理结果:").toString());
				this.saveJSONError(errLogSn + e.getMessage());
				// 修改概要：TS19052200153 - 【问题修复】【体检费用结算】-在体检明细信息界面双击明细信息增加结论信息，弹出窗口需要优化
				// 修改描述：ERROR是系统级错误，应放在最外层的try-catch
				// 修改人及修改时间：daliang.long 20190531
			}

		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
			return ERROR;
		}
		return NONE;

	}

	/**
	 * 查询匹配体检目录信息
	 * 
	 * 
	 * @return
	 */
	public String queryItemMatch() {
		try {
			if (WebHelper.isPostRequest(getRequest())) {
				try {
					PagerHelper.initPagination(getRequest());
					if (healthDTO == null) {
						healthDTO = new HealthDTO();
					}
					// TS19052200158 - 【问题修复】【体检费用结算】-界面功能逻辑与需求不相符合 daliang.long 20180527
					String text = getSearchTerm();
					String type = this.getHealthDTO().getSearchType();
					this.getHealthDTO().setSearchTerm(this.getSearchTerm());
					String akb020 = BizHelper.getAkb020();
					String aaa027 = BizHelper.getAaa027();
					HealthApiService healthApiService = hygeiaServiceManager.getBeanByClass(HealthApiService.class,
							akb020);
					healthDTO.setCurrentPage((PagerHelper.getPaginationObj().getPageIndex()));
					healthDTO.setPageSize(PagerHelper.getPaginationObj().getPageSize());
					healthDTO.setAkb020(akb020);
					healthDTO.setAaa027(aaa027);
					PagerHelper.setPageParam(getRequest(), healthDTO);
					ListResult listResult = healthApiService.queryItemMatch(healthDTO);
					List<HealthDTO> newList = (List<HealthDTO>) listResult.getList();
					for (HealthDTO dto : newList) {
						dto.setBkh046_name(
								CodetableMapping.getDisplayValue("bkh046", dto.getBkh046(), dto.getBkh046()));
					}
					ListResult retList = ListResultDTO.newInstance().setCount(listResult.getCount()).setList(newList);
					DataGridHelper.render(getRequest(), getResponse(),
							PagerHelper.getPaginatedList(retList.getList(), new int[] { retList.getCount() }));

				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
					// 修改概要：TS19052200153 - 【问题修复】【体检费用结算】-在体检明细信息界面双击明细信息增加结论信息，弹出窗口需要优化
					// 修改描述：ERROR是系统级错误，应放在最外层的try-catch
					// 修改人及修改时间：daliang.long 20190531
				}

			}
		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
			return ERROR;
		}
		return NONE;
	}

	/**
	 * 保存匹配信息到临时表
	 * 
	 * @author
	 * @return
	 */
	public String saveMatchCata() {
		try {

			try {
				String insert = getParameter("data");
				int count = 0;
				healthDTO = new HealthDTO();

				if (UtilFunc.hasText(insert)) {
					String akb020 = BizHelper.getAkb020();
					healthDTO.setAkb020(akb020);
					HealthApiService healthApiService = hygeiaServiceManager.getBeanByClass(HealthApiService.class,
							akb020);
					List<HealthDTO> hs = new ArrayList<HealthDTO>();
					List<Map<String, Object>> list = JsonHelper.toList(insert);
					for (int i = 0; i < list.size(); i++) {
						Map<String, Object> map = list.get(i);
						HealthDTO dto = new HealthDTO();
						this.beanService.copyProperties(map, dto, true);
						hs.add(dto);
					}
					count = healthApiService.saveMatchCata(healthDTO, hs);
				}
				if (count > 0) {
					setJSONReturn("操作成功！");
				} else {
					setJSONReturn("操作失败！！");
				}
			} catch (Throwable e) {
				String errLogSn = this.addErrSNInfo();
				this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
						.append(this.addNotBlankParameters()).append(":处理结果:").toString());
				this.saveJSONError(errLogSn + e.getMessage());
				// 修改概要：TS19052200153 - 【问题修复】【体检费用结算】-在体检明细信息界面双击明细信息增加结论信息，弹出窗口需要优化
				// 修改描述：ERROR是系统级错误，应放在最外层的try-catch
				// 修改人及修改时间：daliang.long 20190531
			}

		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
			return ERROR;
		}
		return NONE;
	}

	/**
	 * 提交匹配目录给中心审批
	 * 
	 * @author
	 */
	public String submitMatchCata() {
		try {

			try {
				healthDTO = healthDTO == null ? new HealthDTO() : healthDTO;
				String akb020 = BizHelper.getAkb020();
				healthDTO.setAkb020(akb020);
				healthDTO.setAaa027(BizHelper.getAaa027());
				HealthApiService healthApiService = hygeiaServiceManager.getBeanByClass(HealthApiService.class, akb020);
				int count = healthApiService.submitMatchCata(healthDTO);
				setJSONReturn(count);
			} catch (Throwable e) {
				String errLogSn = this.addErrSNInfo();
				this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
						.append(this.addNotBlankParameters()).append(":处理结果:").toString());
				this.saveJSONError(errLogSn + e.getMessage());
				// 修改概要：TS19052200153 - 【问题修复】【体检费用结算】-在体检明细信息界面双击明细信息增加结论信息，弹出窗口需要优化
				// 修改描述：ERROR是系统级错误，应放在最外层的try-catch
				// 修改人及修改时间：daliang.long 20190531
			}

		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
			return ERROR;
		}
		return NONE;
	}

	/**
	 * 查询结算云提交到中心审核的体检目录状态信息
	 */
	public String queryMatchCenter() {
		try {
			try {
				// aae016=0 未审核 =1 已审核 = 2 审核不通过 =3 未提交到中心审核
				String akb020 = BizHelper.getAkb020();
				String aaa027 = BizHelper.getAaa027();
				healthDTO.setAkb020(akb020);
				healthDTO.setAaa027(aaa027);
				HealthApiService healthApiService = hygeiaServiceManager.getBeanByClass(HealthApiService.class, akb020);
				PagerHelper.setPageParam(getRequest(), healthDTO);
				ListResult listResult = healthApiService.queryMatchCenter(healthDTO);

				List<HealthDTO> list = (List<HealthDTO>) listResult.getList();
				// 修改概要：TS19052300179 - 【问题修复】【体检项目目录匹配】-匹配信息TAB页签优化
				// 修改描述：统计类别需显示中文，从码表中获取
				// 修改人及时间：daliang.long 20190529
				for (int i = 0; i < list.size(); i++) {
					list.get(i).setBkh046_name(CodetableMapping.getDisplayValue("bkh046", list.get(i).getBkh046(),
							list.get(i).getBkh046()));
				}
				DataGridHelper.render(getRequest(), getResponse(),
						PagerHelper.getPaginatedList(listResult.getList(), new int[] { listResult.getCount() }));

			} catch (Throwable e) {
				String errLogSn = this.addErrSNInfo();
				this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
						.append(this.addNotBlankParameters()).append(":处理结果:").toString());
				this.saveJSONError(errLogSn + e.getMessage());
				// 修改概要：TS19052200153 - 【问题修复】【体检费用结算】-在体检明细信息界面双击明细信息增加结论信息，弹出窗口需要优化
				// 修改描述：ERROR是系统级错误，应放在最外层的try-catch
				// 修改人及修改时间：daliang.long 20190531
			}

		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
			return ERROR;
		}
		return NONE;

	}

	/**
	 * 删除匹配目录信息
	 * 
	 * @author
	 */
	public String delMatchCata() {
		try {
			try {
				String del = getParameter("data");
				int count = 0;
				if (UtilFunc.hasText(del)) {
					String akb020 = BizHelper.getAkb020();
					healthDTO = healthDTO == null ? new HealthDTO() : healthDTO;
					healthDTO.setAkb020(akb020);
					HealthApiService healthApiService = hygeiaServiceManager.getBeanByClass(HealthApiService.class,
							akb020);
					List<HealthDTO> hs = new ArrayList<HealthDTO>();
					List<Map<String, Object>> list = JsonHelper.toList(del);
					for (int i = 0; i < list.size(); i++) {
						Map<String, Object> map = list.get(i);
						HealthDTO dto = new HealthDTO();
						this.beanService.copyProperties(map, dto, true);
						hs.add(dto);
					}
					count = healthApiService.delMatchCata(healthDTO, hs);
				}
				setJSONReturn(count);
				// 任务概要：TS19052200142 - 【问题修复】【体检项目目录匹配】-界面优化及缺陷汇总
				// 修改说明：本处程序正确执行完应返回NONE
				// 修改人及时间：daliang.long 20190528
				return NONE;
			} catch (Throwable e) {
				String errLogSn = this.addErrSNInfo();
				this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
						.append(this.addNotBlankParameters()).append(":处理结果:").toString());
				this.saveJSONError(errLogSn + e.getMessage());
				// 修改概要：TS19052200153 - 【问题修复】【体检费用结算】-在体检明细信息界面双击明细信息增加结论信息，弹出窗口需要优化
				// 修改描述：ERROR是系统级错误，应放在最外层的try-catch
				// 修改人及修改时间：daliang.long 20190531
			}

		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
			return ERROR;
		}
		return NONE;
	}

	/**
	 * 自动匹配目录
	 * 
	 * @author
	 * @return
	 */
	public void autoMatchCata() {
		try {
			try {
				healthDTO = healthDTO == null ? new HealthDTO() : healthDTO;
				healthDTO.setAkb020(BizHelper.getAkb020());
				if (UtilFunc.isEmpty(healthDTO.getAaa027()))
					throw new HygeiaException("请选择定点中心");
				String akb020 = BizHelper.getAkb020();
				healthDTO.setBke205(BizHelper.getLoginUser());
				healthDTO.setBke206(BizHelper.getUserName());
				HealthApiService healthApiService = hygeiaServiceManager.getBeanByClass(HealthApiService.class, akb020);
				int count = healthApiService.autoMatchCatch(healthDTO);
				setJSONReturn(count);
			} catch (Throwable e) {
				String errLogSn = this.addErrSNInfo();
				this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
						.append(this.addNotBlankParameters()).append(":处理结果:").toString());
				this.saveJSONError(errLogSn + e.getMessage());
			}

		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}
	}

	/**
	 * 查询待修改的医院目录
	 * 
	 * @return
	 */
	public String queryHospCataEdit() {
		try {
			String akb020 = BizHelper.getAkb020();
			HealthApiService healthApiService = hygeiaServiceManager.getBeanByClass(HealthApiService.class, akb020);
			this.getHealthDTO().setAkb020(akb020);
			this.getHealthDTO().setFlag("1");
			ListResult listResult = healthApiService.queryItemCatalog(this.getHealthDTO());
			HealthDTO dto = (HealthDTO) listResult.getList().get(0);
			Map<String, Object> mapRow = new HashMap<>();

			mapRow = Utils.convertToMap(dto);
			healthDTO = Utils.mapToBean(mapRow, HealthDTO.class, true);
			healthDTO.setBkh044(
					(float) (null != mapRow.get("bkh044") ? Double.parseDouble(mapRow.get("bkh044").toString()) : 0.0));
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}

		return "upKeh1";
	}

	/******************* 体检结论action **********************/
	public String queryConclusions() {
		try {
			if (WebHelper.isPostRequest(getRequest())) {

				try {
					PagerHelper.initPagination(getRequest());
					if (healthDTO == null) {
						healthDTO = new HealthDTO();
					}

					String akb020 = BizHelper.getAkb020();
					String aaa027 = BizHelper.getAaa027();
					HealthApiService healthApiService = hygeiaServiceManager.getBeanByClass(HealthApiService.class,
							akb020);
					healthDTO.setCurrentPage((PagerHelper.getPaginationObj().getPageIndex()));
					healthDTO.setPageSize(PagerHelper.getPaginationObj().getPageSize());
					healthDTO.setAkb020(akb020);
					healthDTO.setAaa027(aaa027);
					ListResult listResult = healthApiService.queryConclusions(healthDTO);
					PagerHelper.setPageParam(getRequest(), healthDTO);
					DataGridHelper.render(getRequest(), getResponse(),
							PagerHelper.getPaginatedList(listResult.getList(), new int[] { listResult.getCount() }));

				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
					// 修改概要：TS19052200153 - 【问题修复】【体检费用结算】-在体检明细信息界面双击明细信息增加结论信息，弹出窗口需要优化
					// 修改描述：ERROR是系统级错误，应放在最外层的try-catch
					// 修改人及修改时间：daliang.long 20190531
				}

			}
		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
			return ERROR;
		}
		return NONE;

	}

	public String saveConclusions() {
		try {
			try {
				if (healthDTO == null) {
					healthDTO = new HealthDTO();
				}
				String akb020 = BizHelper.getAkb020();
				HealthApiService healthApiService = hygeiaServiceManager.getBeanByClass(HealthApiService.class, akb020);
				String data = getParameter("data");
				List<HealthDTO> list = JsonHelper.toList(data, HealthDTO.class);
				List<HealthDTO> dataRow = new ArrayList<HealthDTO>();
				for (HealthDTO dto : list) {
					if (StringUtils.isNotBlank(dto.getAka020()) && StringUtils.isNotBlank(dto.getAka021())) {
						break;
					}
					CharCodeSwitch charCode = new CharCodeSwitchImpl();
					String aka020 = charCode.LoadPY(dto.getBkh050());
					String aka021 = charCode.LoadWB(dto.getBkh050());
					dto.setAka020(aka020);
					dto.setAka021(aka021);
					dataRow.add(dto);
				}
				int saveCount = 0;
				if (dataRow.size() == 0) {
					saveCount = healthApiService.saveConclusions(list);
				} else {
					saveCount = healthApiService.saveConclusions(dataRow);
				}
				if (saveCount > 0) {
					setJSONReturn("保存成功！");
				} else {
					setJSONReturn("未保存任何数据！");
				}
			} catch (Throwable e) {
				String errLogSn = this.addErrSNInfo();
				this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
						.append(this.addNotBlankParameters()).append(":处理结果:").toString());
				this.saveJSONError(errLogSn + e.getMessage());
			}

		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
			return ERROR;
		}
		return NONE;
	}

	public String deleteConclusions() {
		try {

			try {
				if (healthDTO == null) {
					healthDTO = new HealthDTO();
				}
				String akb020 = BizHelper.getAkb020();
				HealthApiService healthApiService = hygeiaServiceManager.getBeanByClass(HealthApiService.class, akb020);
				String data = getParameter("data");
				List<HealthDTO> list = new ArrayList<HealthDTO>();
				for (Map<String, Object> map : JsonHelper.toList(data)) {
					HealthDTO dto = new HealthDTO();
					this.beanService.copyProperties(map, dto, true);
					list.add(dto);

				}
				int count = healthApiService.deleteConclusions(list);
				if (count > 0) {
					setJSONReturn("操作成功！");
				} else {
					setJSONReturn("未执行成功！");
				}
			} catch (Throwable e) {
				String errLogSn = this.addErrSNInfo();
				this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
						.append(this.addNotBlankParameters()).append(":处理结果:").toString());
				this.saveJSONError(errLogSn + e.getMessage());
				// 修改概要：TS19052200153 - 【问题修复】【体检费用结算】-在体检明细信息界面双击明细信息增加结论信息，弹出窗口需要优化
				// 修改描述：ERROR是系统级错误，应放在最外层的try-catch
				// 修改人及修改时间：daliang.long 20190531
			}

		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
			return ERROR;
		}
		return NONE;
	}

	/**
	 * 查询结论导入信息
	 * 
	 * @return
	 */
	public String queryAllImportSion() {
		try {

			try {
				PagerHelper.initPagination(getRequest());
				if (healthDTO == null) {
					healthDTO = new HealthDTO();
				}
				String akb020 = BizHelper.getAkb020();
				String aaa027 = BizHelper.getAaa027();
				HealthApiService healthApiService = hygeiaServiceManager.getBeanByClass(HealthApiService.class, akb020);
				// String bkc133 = getParameter("bkc133");
				healthDTO.setAkb020(akb020);
				// healthDTO.setBkc133(bkc133);
				healthDTO.setAkb020(akb020);
				healthDTO.setAaa027(aaa027);
				healthDTO.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
				healthDTO.setPageSize(PagerHelper.getPaginationObj().getPageSize());
				PagerHelper.setPageParam(getRequest(), healthDTO);
				ListResult listResult = healthApiService.queryKeh23(healthDTO);
				DataGridHelper.render(getRequest(), getResponse(),
						PagerHelper.getPaginatedList(listResult.getList(), new int[] { listResult.getCount() }));

			} catch (Throwable e) {
				String errLogSn = this.addErrSNInfo();
				this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
						.append(this.addNotBlankParameters()).append(":处理结果:").toString());
				this.saveJSONError(errLogSn + e.getMessage());
				// 修改概要：TS19052200153 - 【问题修复】【体检费用结算】-在体检明细信息界面双击明细信息增加结论信息，弹出窗口需要优化
				// 修改描述：ERROR是系统级错误，应放在最外层的try-catch
				// 修改人及修改时间：daliang.long 20190531
			}

		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
			return ERROR;
		}
		return NONE;
	}

	public String saveConCluSionInfo() {
		try {

			try {
				PagerHelper.initPagination(getRequest());
				if (healthDTO == null) {
					healthDTO = new HealthDTO();
				}
				String akb020 = BizHelper.getAkb020();
				String bkc133 = getParameter("bkc133");
				HealthApiService healthApiService = hygeiaServiceManager.getBeanByClass(HealthApiService.class, akb020);
				healthDTO.setAkb020(akb020);
				healthDTO.setBkc133(bkc133);
				healthDTO.setBkc252("1");
				healthDTO.setPageSize(999999999);

				int listSize = healthApiService.saveConCluSionInfo(healthDTO);
				if (listSize > 0) {
					setJSONReturn("成功保存[" + listSize + "条数据！]");
				} else {
					setJSONReturn("未保存任何数据，请检查是数据是否重复！");
				}
			} catch (Throwable e) {
				String errLogSn = this.addErrSNInfo();
				this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
						.append(this.addNotBlankParameters()).append(":处理结果:").toString());
				this.saveJSONError(errLogSn + e.getMessage());
				// 修改概要：TS19052200153 - 【问题修复】【体检费用结算】-在体检明细信息界面双击明细信息增加结论信息，弹出窗口需要优化
				// 修改描述：ERROR是系统级错误，应放在最外层的try-catch
				// 修改人及修改时间：daliang.long 20190531
			}

		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
			return ERROR;
		}
		return NONE;
	}

	/**
	 * 删除导入体检目录信息
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String delConCluSionInfo() {
		try {

			try {
				int i = 0;
				String akb020 = BizHelper.getAkb020();
				if (healthDTO == null) {
					healthDTO = new HealthDTO();
				}
				HealthApiService healthApiService = hygeiaServiceManager.getBeanByClass(HealthApiService.class, akb020);
				healthDTO.setAkb020(akb020);
				String bkc133 = getParameter("bkc133");
				healthDTO.setBkc133(bkc133);
				i += healthApiService.deleteKeh23(healthDTO);
				i += healthApiService.deleteKeh20(healthDTO);
				Map map = new HashMap();
				map.put("data", healthDTO.getBkc133());

				if (i > 0) {
					setJSONReturn("删除信息成功！", map);
				} else {
					setJSONReturn("删除信息失败！", map);
				}
			} catch (Throwable e) {
				String errLogSn = this.addErrSNInfo();
				this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
						.append(this.addNotBlankParameters()).append(":处理结果:").toString());
				this.saveJSONError(errLogSn + e.getMessage());
				// 修改概要：TS19052200153 - 【问题修复】【体检费用结算】-在体检明细信息界面双击明细信息增加结论信息，弹出窗口需要优化
				// 修改描述：ERROR是系统级错误，应放在最外层的try-catch
				// 修改人及修改时间：daliang.long 20190531
			}

		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
			return ERROR;
		}
		return NONE;
	}

	/**
	 * 查询待修改的医院目录
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryHospKeh2Edit() {
		try {
			try {
				String akb020 = BizHelper.getAkb020();
				String aaa027 = BizHelper.getAaa027();
				HealthApiService healthApiService = hygeiaServiceManager.getBeanByClass(HealthApiService.class, akb020);
				this.getHealthDTO().setAkb020(akb020);
				this.getHealthDTO().setAaa027(aaa027);
				this.getHealthDTO().setFlag("1");
				ListResult listResult = healthApiService.queryConclusions(healthDTO);
				HealthDTO dto = (HealthDTO) listResult.getList().get(0);
				Map mapRow = new HashMap();

				mapRow = Utils.convertToMap(dto);
				healthDTO = (HealthDTO) Utils.mapToBean(mapRow, HealthDTO.class, true);
				healthDTO.setAae100(dto.getAae100());
			} catch (Throwable e) {
				String errLogSn = this.addErrSNInfo();
				this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
						.append(this.addNotBlankParameters()).append(":处理结果:").toString());
				this.saveJSONError(errLogSn + e.getMessage());
			}

		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}

		return "upKeh2";
	}

	/**
	 * 查询结算云未审核的结论
	 */
	public String queryMatchInfo() {
		try {
			try {
				// aae016=0 未审核 =1 已审核 = 2 审核不通过 =3 未提交到中心审核
				String akb020 = BizHelper.getAkb020();
				String aaa027 = BizHelper.getAaa027();
				healthDTO.setAkb020(akb020);
				healthDTO.setAkb020(akb020);
				HealthApiService healthApiService = hygeiaServiceManager.getBeanByClass(HealthApiService.class, akb020);
				PagerHelper.setPageParam(getRequest(), healthDTO);
				ListResult listResult = healthApiService.queryConclusionInfoOnCenter(healthDTO);
				DataGridHelper.render(getRequest(), getResponse(),
						PagerHelper.getPaginatedList(listResult.getList(), new int[] { listResult.getCount() }));
			} catch (Throwable e) {
				String errLogSn = this.addErrSNInfo();
				this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
						.append(this.addNotBlankParameters()).append(":处理结果:").toString());
				this.saveJSONError(errLogSn + e.getMessage());
				// 修改概要：TS19052200153 - 【问题修复】【体检费用结算】-在体检明细信息界面双击明细信息增加结论信息，弹出窗口需要优化
				// 修改描述：ERROR是系统级错误，应放在最外层的try-catch
				// 修改人及修改时间：daliang.long 20190531
			}

		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
			return ERROR;
		}
		return NONE;
	}

	/**
	 * 保存结算匹配信息到临时表
	 * 
	 * @author
	 * @return
	 */
	public String saveMatchConclusion() {
		try {
			try {
				String insert = getParameter("data");
				int count = 0;
				healthDTO = new HealthDTO();

				if (UtilFunc.hasText(insert)) {
					String akb020 = BizHelper.getAkb020();
					healthDTO.setAkb020(akb020);
					HealthApiService healthApiService = hygeiaServiceManager.getBeanByClass(HealthApiService.class,
							akb020);
					count = healthApiService.saveMatchConclusion(healthDTO, JsonHelper.toList(insert, HealthDTO.class));
				}
				setJSONReturn(count);
			} catch (Throwable e) {
				String errLogSn = this.addErrSNInfo();
				this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
						.append(this.addNotBlankParameters()).append(":处理结果:").toString());
				this.saveJSONError(errLogSn + e.getMessage());
				// 修改概要：TS19052200153 - 【问题修复】【体检费用结算】-在体检明细信息界面双击明细信息增加结论信息，弹出窗口需要优化
				// 修改描述：ERROR是系统级错误，应放在最外层的try-catch
				// 修改人及修改时间：daliang.long 20190531
			}

		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
			return ERROR;
		}
		return NONE;
	}

	/**
	 * 自动匹配目录
	 * 
	 * @author
	 * @return
	 */
	public void autoSaveMatchConclusion() {
		try {
			try {
				healthDTO = healthDTO == null ? new HealthDTO() : healthDTO;
				String akb020 = BizHelper.getAkb020();
				healthDTO.setAkb020(akb020);
				if (UtilFunc.isEmpty(healthDTO.getAaa027()))
					throw new HygeiaException("请选择定点中心");
				HealthApiService healthApiService = hygeiaServiceManager.getBeanByClass(HealthApiService.class, akb020);
				int count = healthApiService.autoSaveMatchConclusion(healthDTO);
				setJSONReturn(count);
			} catch (Throwable e) {
				String errLogSn = this.addErrSNInfo();
				this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
						.append(this.addNotBlankParameters()).append(":处理结果:").toString());
				this.saveJSONError(errLogSn + e.getMessage());
			}

		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}
	}

	/**
	 * 提交匹配目录给中心审批
	 * 
	 * @author
	 */
	public String submitMatchConclusion() {
		try {
			try {
				healthDTO = healthDTO == null ? new HealthDTO() : healthDTO;
				String akb020 = BizHelper.getAkb020();
				healthDTO.setAkb020(akb020);
				if (UtilFunc.isEmpty(healthDTO.getAaa027()))
					throw new HygeiaException("请选择定点中心");
				HealthApiService healthApiService = hygeiaServiceManager.getBeanByClass(HealthApiService.class, akb020);
				int count = healthApiService.submitMatchConclusion(healthDTO);
				setJSONReturn(count);
			} catch (Throwable e) {
				String errLogSn = this.addErrSNInfo();
				this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
						.append(this.addNotBlankParameters()).append(":处理结果:").toString());
				this.saveJSONError(errLogSn + e.getMessage());
				// 修改概要：TS19052200153 - 【问题修复】【体检费用结算】-在体检明细信息界面双击明细信息增加结论信息，弹出窗口需要优化
				// 修改描述：ERROR是系统级错误，应放在最外层的try-catch
				// 修改人及修改时间：daliang.long 20190531
			}

		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
			return ERROR;
		}
		return NONE;
	}

	/**
	 * 删除结算匹配信息
	 * 
	 * @return
	 */
	public String delMatchCataOnKeh6() {
		try {
			try {
				String del = getParameter("data");
				if (UtilFunc.hasText(del)) {
					String akb020 = BizHelper.getAkb020();
					healthDTO = healthDTO == null ? new HealthDTO() : healthDTO;
					healthDTO.setAkb020(akb020);
					HealthApiService healthApiService = hygeiaServiceManager.getBeanByClass(HealthApiService.class,
							akb020);
					healthApiService.delMatchCataOnKeh6(healthDTO, JsonHelper.toList(del, HealthDTO.class));
				}
			} catch (Throwable e) {
				String errLogSn = this.addErrSNInfo();
				this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
						.append(this.addNotBlankParameters()).append(":处理结果:").toString());
				this.saveJSONError(errLogSn + e.getMessage());
				// 修改概要：TS19052200153 - 【问题修复】【体检费用结算】-在体检明细信息界面双击明细信息增加结论信息，弹出窗口需要优化
				// 修改描述：ERROR是系统级错误，应放在最外层的try-catch
				// 修改人及修改时间：daliang.long 20190531
			}

		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
			return ERROR;
		}
		return NONE;
	}

	/**
	 * 中心结论目录下载
	 * 
	 * @return
	 */
	public String doDownload() {
		try {
			if (WebHelper.isPostRequest(getRequest())) {
				try {
					PagerHelper.initPagination(getRequest());
					if (healthDTO == null) {
						healthDTO = new HealthDTO();
					}

					String akb020 = BizHelper.getAkb020();
					String aaa027 = BizHelper.getAaa027();
					healthDTO.setAaa027(aaa027);
					healthDTO.setAkb020(akb020);
					HealthApiService healthApiService = hygeiaServiceManager.getBeanByClass(HealthApiService.class,
							akb020);
					healthDTO.setAkb020(akb020);
					int down = healthApiService.doDownload(healthDTO);
					if (down == 0) {
						this.setJSONReturn("中心未发现新的体检结论目录");
					} else {
						this.setJSONReturn("下载成功！中心结论目录已成功保存至医院本地数据库！");
					}
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
					// 修改概要：TS19052200153 - 【问题修复】【体检费用结算】-在体检明细信息界面双击明细信息增加结论信息，弹出窗口需要优化
					// 修改描述：ERROR是系统级错误，应放在最外层的try-catch
					// 修改人及修改时间：daliang.long 20190531
				}

			}

		} catch (Exception e) {
			logger.error(ToolUtil.getExceptionInfo(e));
			this.saveJSONError(e.getMessage());
			return ERROR;
		}
		return NONE;
	}

	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

	public HealthDTO getHealthDTO() {
		return healthDTO;
	}

	public void setHealthDTO(HealthDTO healthDTO) {
		this.healthDTO = healthDTO;

	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public ErrLogSnService getErrLogSnService() {
		return errLogSnService;
	}

	public void setErrLogSnService(ErrLogSnService errLogSnService) {
		this.errLogSnService = errLogSnService;
	}

	public CommunalService getCommunalService() {
		return communalService;
	}

	public void setCommunalService(CommunalService communalService) {
		this.communalService = communalService;
	}

	public String getSearchTerm() {
		if (this.searchTerm != null) {
			this.searchTerm = this.searchTerm.trim();
		}
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	/**
	 * 
	 * @return
	 */
	public String addErrSNInfo() {
		String errLogSn = this.getErrLogSnService().getErrSN(ProjectType.WEB);
		errLogSn = "错误号 " + errLogSn + " 信息： ";
		return errLogSn;
	}

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String addNotBlankParameters() {
		Map allParameters = this.getAllParameters();
		if (allParameters == null || allParameters.size() == 0) {
			return "{}";
		}
		Map parameters = new HashMap();
		String key = "", value = "";
		Object objValue = null;
		Iterator it = allParameters.keySet().iterator();
		while (it.hasNext()) {
			key = it.next().toString();
			objValue = allParameters.get(key);
			if (objValue != null) {
				value = objValue.toString();
				if (StringUtils.isNotBlank(value)) {
					parameters.put(key, value);
				}
			}
		}
		return parameters.toString();
	}
	
	
	/**
	  * 任务概要：TS19080100098 - 【问题修复】【体检项目目录匹配】-“匹配信息”TAB页签中双击提示删除成功，界面依旧存在该值
	  * 修改说明：区分未审核(keh3)与其他(keh4)操作的表 
	  * 修改人及时间：李嘉伦 20190801
	  * 删除未审核匹配目录信息
	 * @author
	 * @return none
	 */
	public String delMatchCataTemp() {
		try {
			try {
				String del = getParameter("data");
				int count = 0;
				if (UtilFunc.hasText(del)) {
					String akb020 = BizHelper.getAkb020();
					healthDTO = healthDTO == null ? new HealthDTO() : healthDTO;
					healthDTO.setAkb020(akb020);
					HealthApiService healthApiService = hygeiaServiceManager.getBeanByClass(HealthApiService.class, akb020);
					List<HealthDTO> hs = new ArrayList<HealthDTO>();
					List<Map<String, Object>> list = JsonHelper.toList(del);
					for (int i = 0; i < list.size(); i++) {
						Map<String, Object> map = list.get(i);
						HealthDTO dto = new HealthDTO();
						this.beanService.copyProperties(map, dto, true);
						hs.add(dto);
					}
					count = healthApiService.delMatchCata_keh3(healthDTO, hs);
				}
				setJSONReturn(count);
				return NONE;
			} catch (Throwable e) {
				String errLogSn = this.addErrSNInfo();
				this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
						.append(this.addNotBlankParameters()).append(":处理结果:").toString());
				this.saveJSONError(errLogSn + e.getMessage());
			}
		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
			return ERROR;
		}
		return NONE;
	}

}
