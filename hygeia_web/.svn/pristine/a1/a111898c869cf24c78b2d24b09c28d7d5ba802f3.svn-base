package com.powersi.ssm.biz.medicare.comminter.action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.comminter.pojo.KAB1DTO;
import com.powersi.biz.medicare.comminter.pojo.KAB2DTO;
import com.powersi.biz.medicare.comminter.pojo.KAB3DTO;
import com.powersi.biz.medicare.comminter.pojo.KAB4DTO;
import com.powersi.biz.medicare.comminter.service.InvoiceManagerService;
import com.powersi.biz.medicare.inhospital.service.bean.BeanService;
import com.powersi.biz.medicare.inhospital.service.util.CommunalService;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.util.BeanHelper;
import com.powersi.hygeia.framework.util.PaginationHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.hygeia.web.util.WebHelper;
import com.powersi.log.service.ErrLogSnService;
import com.powersi.log.service.ErrLogSnService.ProjectType;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.sys.user.service.UserService;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.PagerHelper;

/**
 * 个性化通用接口--发票管理
 * 
 * @author chenxing
 */

@Action(value = "InvoiceManagerAction", results = {
		@Result(name = "InvoiceRepairPrint", location = "/pages/biz/medicare/comminter/InvoiceRepairPrint.jsp"),
		@Result(name = "receiptInvoice", location = "/pages/biz/medicare/comminter/ReceiptInvoice.jsp"),
		@Result(name = "billInfo", location = "/pages/biz/medicare/comminter/billInfo.jsp"),
		@Result(name = "InvoiceInfo", location = "/pages/biz/medicare/comminter/InvoiceManager.jsp"),
		@Result(name = "backInvoice", location = "/pages/biz/medicare/comminter/BackInvoice.jsp"),
		@Result(name = "cancelInvoice", location = "/pages/biz/medicare/comminter/CancelInvoice.jsp"),
		@Result(name = "newInvoice", location = "/pages/biz/medicare/comminter/InvoiceNew.jsp"),
		@Result(name = "useInfo", location = "/pages/biz/medicare/comminter/QueryInvoice.jsp") })
public class InvoiceManagerAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;
	@Autowired
	private CommunalService communalService;
	@Autowired
	private BeanService beanService;
	private static UserService userService = BeanHelper.getBean(UserService.class);
	@Autowired
	@Qualifier("errLogSnService")
	private ErrLogSnService errLogSnService;
	private KAB1DTO kab1Dto;
	private KAB4DTO kab4Dto;
	private KAB2DTO kab2Dto;
	private KAB1DTO kab1DTO;

	public ErrLogSnService getErrLogSnService() {
		return errLogSnService;
	}

	public void setErrLogSnService(ErrLogSnService errLogSnService) {
		this.errLogSnService = errLogSnService;
	}

	public KAB2DTO getKab2Dto() {
		return kab2Dto;
	}

	public void setKab2Dto(KAB2DTO kab2Dto) {
		this.kab2Dto = kab2Dto;
	}

	public KAB1DTO getKab1DTO() {
		return kab1DTO;
	}

	public void setKab1DTO(KAB1DTO kab1dto) {
		kab1DTO = kab1dto;
	}

	private KAB3DTO kab3DTO;

	public KAB3DTO getKab3DTO() {
		return kab3DTO;
	}

	public void setKab3DTO(KAB3DTO kab3dto) {
		kab3DTO = kab3dto;
	}

	/**
	 * 查询是否领用了发票
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryKab1() {
		try {
			Map retMsg = new HashMap();
			kab1DTO.setAkb020(BizHelper.getAkb020());
			kab1DTO.setBka014(BizHelper.getLoginUser());
			InvoiceManagerService invoiceManagerService = this.hygeiaServiceManager
					.getBeanByClass(InvoiceManagerService.class, kab1DTO.getAkb020());
			retMsg.put("kab1DTO", invoiceManagerService.queryKAB1DTO(kab1DTO));
			this.setJSONReturn(retMsg);
		} catch (Exception e) {
			this.saveJSONError(e.getMessage());
		}
	}

	public KAB1DTO getKab1Dto() {
		return kab1Dto;
	}

	public void setKab1Dto(KAB1DTO kab1Dto) {
		this.kab1Dto = kab1Dto;
	}

	public KAB4DTO getKab4Dto() {
		return kab4Dto;
	}

	public void setKab4Dto(KAB4DTO kab4Dto) {
		this.kab4Dto = kab4Dto;
	}

	/**
	 * 查询发票领退信息
	 * 
	 * @return
	 */
	public String queryInvoicesInfo() {
		try {
			if (WebHelper.isPostRequest(getRequest())) {
				if (kab1Dto == null) {
					kab1Dto = new KAB1DTO();
				}
				PagerHelper.initPagination(getRequest());
				String akb020 = BizHelper.getAkb020();
				kab1Dto.setAkb020(akb020);
				kab1Dto.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
				kab1Dto.setPageSize(PagerHelper.getPaginationObj().getPageSize());
				InvoiceManagerService invoiceManagerService = hygeiaServiceManager
						.getBeanByClass(InvoiceManagerService.class, akb020);
				ListResult listResult = invoiceManagerService.queryInvoicePage(kab1Dto);
				PaginationHelper.getPaginationObj().setCount(listResult.getCount());
				DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(listResult.getList()));
			} else {
				return "InvoiceInfo";
			}
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("获取发票领退记录异常" + errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 领取发票前获取领票人
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryPersons() {
		try {
			Map userInfo = new LinkedHashMap();
			List<Map<String, Object>> userlist = userService.queryUserInfosByCurrentDeptId();
			if (userlist.size() > 0) {
				for (Map<String, Object> map : userlist) {
					userInfo.put(map.get("login_user"), map.get("staff_name"));
				}
			}
			setAttribute("userInfo", userInfo);
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("获取领票人列表出错" + errLogSn + e.getMessage());
		}
		return "newInvoice";
	}

	/**
	 * 领取发票
	 * 
	 * @return
	 */
	public String receiptInvoice() {
		try {
			InvoiceManagerService invoiceManagerService = hygeiaServiceManager
					.getBeanByClass(InvoiceManagerService.class, kab1Dto.getAkb020());
			KAB1DTO kab1 = new KAB1DTO();
			kab1.setAkb020(BizHelper.getAkb020());
			kab1.setBae414("01,02,03");
			kab1.setBae414list(Arrays.asList(kab1.getBae414().split(",")));
			List<KAB1DTO> kab1Info = invoiceManagerService.queryInvoice(kab1);
			if (kab1Info != null) {
				for (KAB1DTO kab1dto : kab1Info) {
					int bae411 = Integer.parseInt(kab1dto.getBae411());
					int bae412 = Integer.parseInt(kab1dto.getBae412());
					if (Integer.parseInt(kab1Dto.getBae411()) >= bae411
							&& Integer.parseInt(kab1Dto.getBae411()) <= bae412) {
						setJSONReturn("您需要领取的发票已被领用！");
						return NONE;
					}
					if (Integer.parseInt(kab1Dto.getBae412()) >= bae411
							&& Integer.parseInt(kab1Dto.getBae412()) <= bae412) {
						setJSONReturn("您需要领取的发票已被领用！");
						return NONE;
					}
				}
			}
			kab1.setBka014(kab1Dto.getBka014());
			kab1.setBae410(kab1Dto.getBae410());
			kab1.setBae414("01,02");
			kab1.setBae414list(Arrays.asList(kab1.getBae414().split(",")));
			kab1Info = invoiceManagerService.queryInvoice(kab1);
			if (kab1Info != null) {
				if (kab1Info.size() > 0) {
					setJSONReturn("请先用完您已领取的发票！");
					return NONE;
				}
			}
			kab1Dto.setUsername(BizHelper.getUserName());
			kab1Dto.setLoginuser(BizHelper.getLoginUser());
			invoiceManagerService.saveInvoice(kab1Dto);
			setJSONReturn("领取发票成功！");
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("领取发票失败：" + errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 退领发票
	 * 
	 * @return
	 */
	public String backInvoice() {
		try {
			String oldKab1id = kab1Dto.getKab1id();
			InvoiceManagerService invoiceManagerService = hygeiaServiceManager
					.getBeanByClass(InvoiceManagerService.class, kab1Dto.getAkb020());
			KAB1DTO oldkab1Dto = invoiceManagerService.queryInvoiceById(oldKab1id, BizHelper.getAkb020());
			oldkab1Dto.setUsername(BizHelper.getUserName());
			oldkab1Dto.setLoginuser(BizHelper.getLoginUser());
			oldkab1Dto.setBae420("02");
			invoiceManagerService.backInvoice(oldkab1Dto);
			this.setJSONReturn("发票退领成功！");
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("退领发票失败：" + errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 获取发票信息
	 * 
	 * @return
	 */
	public String queryInvoiceById() {
		try {
			if (kab1Dto == null) {
				kab1Dto = new KAB1DTO();
			}
			String kab1id = getParameter("kab1id");
			String bz = getParameter("bz");
			String akb020 = BizHelper.getAkb020();
			InvoiceManagerService invoiceManagerService = hygeiaServiceManager
					.getBeanByClass(InvoiceManagerService.class, akb020);
			kab1Dto = invoiceManagerService.queryInvoiceById(kab1id, akb020);
			if ("back".equals(bz)) {
				return "backInvoice";
			} else {
				return "cancelInvoice";
			}
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("获取单条发票信息出错：" + errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 发票作废（未使用的发票）
	 * 
	 * @return
	 */
	public String cancelInvoice() {
		try {
			InvoiceManagerService invoiceManagerService = hygeiaServiceManager
					.getBeanByClass(InvoiceManagerService.class, BizHelper.getAkb020());
			invoiceManagerService.cancelInvoicePiLiang(kab1Dto);
			this.setJSONReturn("发票作废成功！");
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("发票作废失败:" + errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 获取发票使用情况
	 * 
	 * @return
	 */
	public String queryUseInfo() {
		try {
			if (WebHelper.isPostRequest(getRequest())) {
				if (kab2Dto == null) {
					kab2Dto = new KAB2DTO();
				}
				PagerHelper.initPagination(getRequest());
				kab2Dto.setAkb020(BizHelper.getAkb020());
				kab2Dto.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
				kab2Dto.setPageSize(PagerHelper.getPaginationObj().getPageSize());
				InvoiceManagerService invoiceManagerService = hygeiaServiceManager
						.getBeanByClass(InvoiceManagerService.class, kab2Dto.getAkb020());
				ListResult listResult = invoiceManagerService.querytKab2Page(kab2Dto);
				PaginationHelper.getPaginationObj().setCount(listResult.getCount());
				DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(listResult.getList()));
			} else {
				return "useInfo";
			}
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("获取发票使用情况失败" + errLogSn + e.getMessage());
		}
		return NONE;
	}

	public CommunalService getCommunalService() {
		return communalService;
	}

	public void setCommunalService(CommunalService communalService) {
		this.communalService = communalService;
	}

	/**
	 * 查询票据 此外住院结算界面需提供当前发票号码人工设置功能（应对由于纸质发票缺失造成与系统当前发票号码不对应的情况）
	 * 
	 * @return
	 */
	public String updateAndGetKab1() {
		try {
			String flag = this.getRequest().getParameter("flag");
			kab1DTO.setAkb020(BizHelper.getAkb020());
			kab1DTO.setBka014(BizHelper.getLoginUser());
			if (kab3DTO == null) {
				kab3DTO = new KAB3DTO();
			}
			if (!UtilFunc.isNotBlank(kab3DTO.getAaz217())) {
				kab3DTO.setAaz217(kab1DTO.getAaz217());
			}
			kab3DTO.setAkb020(BizHelper.getAkb020());
			InvoiceManagerService invoiceManagerService = this.hygeiaServiceManager
					.getBeanByClass(InvoiceManagerService.class, kab1DTO.getAkb020());
			if ("1".equals(flag)) {
				// 查询
				List<KAB1DTO> kab1DTORows = invoiceManagerService.queryKAB1DTO(kab1DTO);
				if (kab1DTORows != null && kab1DTORows.size() > 0) {
					kab1DTO = kab1DTORows.get(0);
				} else {
					throw new HygeiaException("您当前没有该业务类型的可用发票");
				}
				if (UtilFunc.isEmpty(kab3DTO.getAaz217())) {// 打印查询发票台账信息时，需根据aaz217查询获取唯一记录
					throw new HygeiaException("前台界面传入的就诊登记号为空");
				}
				KAB3DTO kab3DTORow = new KAB3DTO();
				kab3DTORow = invoiceManagerService.selectKab3(kab3DTO);
				if (UtilFunc.isNotBlank(kab3DTORow.getKab1id())) {
					throw new HygeiaException("该笔业务已经打印过发票，发票号：" + kab3DTORow.getBae413());
				}
				this.beanService.copyProperties(kab3DTORow, kab3DTO);
				return "billInfo";
			} else {
				// 修改
				List<KAB1DTO> kab1DTORows = invoiceManagerService.queryKAB1DTO(kab1DTO);
				if (kab1DTORows != null && kab1DTORows.size() > 0) {
				} else {
					throw new HygeiaException("您当前没有该业务类型的可用发票");
				}
				kab1DTORows.get(0).setBka033(BizHelper.getLoginUser());
				kab1DTORows.get(0).setBka034(BizHelper.getUserName());
				// TODO 当前发票号码设置（主动跳号）： service{ update kab1 insert kab2}
				invoiceManagerService.updateBae413AndAbolish(kab1DTO, kab1DTORows);
				return "billInfo";
			}
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 发票作废处理
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String invoiceAbolish() {
		try {
			int i = 0;

			if (kab3DTO == null) {
				kab3DTO = new KAB3DTO();
			}
			String akb020 = BizHelper.getAkb020();
			InvoiceManagerService invoiceManagerService = this.hygeiaServiceManager
					.getBeanByClass(InvoiceManagerService.class, akb020);

			KAB3DTO kab3dtoRow = null;
			kab3dtoRow = invoiceManagerService.selectKab3(kab3DTO);
			if (kab3dtoRow == null) {
				throw new HygeiaException("系统未查询到该笔台账信息");
			}

			String aae011 = BizHelper.getLoginUser();
			String aae012 = BizHelper.getUserName();
			KAB2DTO kab2dtoRow = new KAB2DTO();
			kab2dtoRow.setKab1id(kab3dtoRow.getKab1id());
			kab2dtoRow.setAae011(aae011);
			kab2dtoRow.setAae012(aae012);

			i = invoiceManagerService.abolishInvoice(kab3dtoRow, kab2dtoRow);
			Map map = new HashMap();
			map.put("bae413", kab3dtoRow.getBae413());

			if (i > 0) {
				setJSONReturn("发票作废成功！", map);
			} else {
				setJSONReturn("发票作废失败！", map);
			}

		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			saveJSONError(errLogSn + ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 发票重打、补打查询台账信息
	 * 
	 * @return
	 */
	public String queryKab3Info() {
		try {
			if (WebHelper.isPostRequest(getRequest())) {
				if (kab3DTO == null) {
					kab3DTO = new KAB3DTO();
				}
				PagerHelper.initPagination(getRequest());
				String akb020 = BizHelper.getAkb020();
				kab3DTO.setAkb020(akb020);
				kab3DTO.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
				kab3DTO.setPageSize(PagerHelper.getPaginationObj().getPageSize());

				InvoiceManagerService invoiceManagerService = hygeiaServiceManager
						.getBeanByClass(InvoiceManagerService.class, akb020);
				ListResult listResult = invoiceManagerService.queryKab3InfoPage(kab3DTO);

				PaginationHelper.getPaginationObj().setCount(listResult.getCount());
				DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(listResult.getList()));
			} else {
				return "InvoiceRepairPrint";
			}
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("获取发票领退记录异常" + errLogSn + e.getMessage());
		}
		return NONE;
	}

	public String addErrSNInfo() {
		String errLogSn = this.getErrLogSnService().getErrSN(ProjectType.WEB);
		errLogSn = "错误号 " + errLogSn + " 信息： ";
		return errLogSn;
	}

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
}
