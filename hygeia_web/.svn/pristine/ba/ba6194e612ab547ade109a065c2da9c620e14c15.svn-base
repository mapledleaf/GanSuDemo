package com.powersi.ssm.biz.medicare.catalog.action;

import com.powersi.biz.medicare.catalog.pojo.DiseaseDTO;
import com.powersi.biz.medicare.catalog.pojo.HospCataDTO;
import com.powersi.biz.medicare.catalog.service.api.HospCataApiService;
import com.powersi.biz.medicare.comm.pojo.DiseaseDirDTO;
import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.inhospital.service.bean.BeanService;
import com.powersi.biz.medicare.inhospital.service.util.CommunalService;
import com.powersi.comm.utils.ToolUtil;
import com.powersi.hygeia.comm.service.CharCodeSwitch;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.CodetableMapping;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.PaginationHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.hygeia.web.util.JsonHelper;
import com.powersi.hygeia.web.util.WebHelper;
import com.powersi.log.service.ErrLogSnService;
import com.powersi.log.service.ErrLogSnService.ProjectType;
import com.powersi.ssm.biz.medicare.common.service.CharCodeSwitchImpl;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.PagerHelper;

import jxl.Sheet;
import jxl.Workbook;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
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

@Action(value = "HospCataAction", results = {
		@Result(name = "cataManage", location = "/pages/biz/medicare/catalog/HospCataManage.jsp"),
		@Result(name = "cataEdit", location = "/pages/biz/medicare/catalog/HospCataDetail.jsp"),
		@Result(name = "itemEdit", location = "/pages/biz/medicare/catalog/HospItemDetail.jsp"),
		@Result(name = "diseaseEdit", location = "/pages/biz/medicare/catalog/HospDiseaseDetail.jsp"),
		@Result(name = "matchEdit", location = "/pages/biz/medicare/catalog/HospMatchDetail.jsp") })
public class HospCataAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private File imgFile;
	private String imgFileName;
	private DiseaseDTO diseaseDto;
	private HospCataDTO hospCataDto;
	private DiseaseDirDTO diseaseDirDTO;
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;
	@Autowired
	private BeanService beanService;
	@Autowired
	private CommunalService communalService;
	@Autowired
	@Qualifier("errLogSnService")
	private ErrLogSnService errLogSnService;

	/**
	 * 查询医院目录信息
	 */
	public String queryHospCata() {
		try {
			if (WebHelper.isPostRequest(getRequest())) {
				this.hospCataDto = (this.hospCataDto == null ? new HospCataDTO() : this.hospCataDto);
				HospCataApiService hospCataService = (HospCataApiService) this.hygeiaServiceManager
						.getBeanByClass(HospCataApiService.class, BizHelper.getAkb020());
				PagerHelper.initPagination(getRequest());
				this.hospCataDto.setAkb020(BizHelper.getAkb020());
				this.hospCataDto.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
				this.hospCataDto.setPageSize(PagerHelper.getPaginationObj().getPageSize());
				ListResult listResult = hospCataService.queryHospCataPage(this.hospCataDto);
				this.loadCodeValue(listResult.getList());
				PaginationHelper.getPaginationObj().setCount(listResult.getCount());
				DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(listResult.getList()));
			} else {
				return "cataManage";
			}
		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}
	
	/**
	 * 查询医院疾病信息
	 */
	public String queryHospDiseasePage() {
		try {
			this.diseaseDto = (this.diseaseDto == null ? new DiseaseDTO() : this.diseaseDto);
			HospCataApiService hospCataService = (HospCataApiService) this.hygeiaServiceManager
					.getBeanByClass(HospCataApiService.class, BizHelper.getAkb020());
			PagerHelper.initPagination(getRequest());
			this.diseaseDto.setAkb020(BizHelper.getAkb020());
			this.diseaseDto.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
			this.diseaseDto.setPageSize(PagerHelper.getPaginationObj().getPageSize());
			if(StringUtils.isBlank(this.diseaseDto.getAka421())&&StringUtils.isNotBlank(this.diseaseDto.getAka121())){
				this.diseaseDto.setAka421(this.diseaseDto.getAka121());
			}
			if(StringUtils.isBlank(this.diseaseDto.getAka420())&&StringUtils.isNotBlank(this.diseaseDto.getAka120())){
				this.diseaseDto.setAka420(this.diseaseDto.getAka120());
			}
			ListResult listResult = hospCataService.queryHospDiseasePage(this.getDiseaseDto());
			this.loadCodeValue(listResult.getList());
			PaginationHelper.getPaginationObj().setCount(listResult.getCount());
			DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(listResult.getList()));
		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 修改目录信息时查询目录详情
	 */
	public String queryHospCataEdit() {
		try {
			this.hospCataDto = (this.hospCataDto == null ? new HospCataDTO() : this.hospCataDto);
			HospCataApiService hospCataService = (HospCataApiService) this.hygeiaServiceManager
					.getBeanByClass(HospCataApiService.class, BizHelper.getAkb020());
			this.hospCataDto.setAkb020(BizHelper.getAkb020());
			this.hospCataDto.setAae406(BizHelper.getUserName());
			this.hospCataDto.setAae407(BizHelper.getLoginUser());
			HospCataDTO hospCata = hospCataService.queryHospCata(this.hospCataDto).get(0);
			this.beanService.copyProperties(hospCata, this.getHospCataDto(), true);
		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}
		String ake003 = this.hospCataDto.getAke003();
		if ("2".equals(ake003) || "3".equals(ake003))
			return "itemEdit";
		else
			return "cataEdit";
	}

	/**
	 * 修改目录信息时查询目录详情
	 */
	public String queryHospDiseaseEdit() {
		try {
			this.diseaseDto = (this.diseaseDto == null ? new DiseaseDTO() : this.diseaseDto);
			HospCataApiService hospCataService = (HospCataApiService) this.hygeiaServiceManager
					.getBeanByClass(HospCataApiService.class, BizHelper.getAkb020());
			this.diseaseDto.setAkb020(BizHelper.getAkb020());
			Object hospDisease = hospCataService.queryHospDiseasePage(this.diseaseDto).
					getList().get(0);
			this.beanService.copyProperties(hospDisease, this.getDiseaseDto(), true);
		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}
		return "diseaseEdit";
	}
	
	
	/**
	 * 修改目录信息
	 * @return
	 */
	public String updateHospCata() {
		try {
			this.hospCataDto = (this.hospCataDto == null ? new HospCataDTO() : this.hospCataDto);
			this.hospCataDto.setAkb020(BizHelper.getAkb020());
			HospCataApiService hospCataService = (HospCataApiService) this.hygeiaServiceManager
					.getBeanByClass(HospCataApiService.class, this.hospCataDto.getAkb020());
			int i = hospCataService.updateHospCata(this.hospCataDto);
			setJSONReturn(Integer.valueOf(i));
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("修改医院目录时出错！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
		return NONE;
	}

	/**
	 * 修改疾病目录信息
	 * @return
	 */
	public String updateHospDisease() {
		try {
			this.diseaseDto = (this.diseaseDto == null ? new DiseaseDTO() : this.diseaseDto);
			HospCataApiService hospCataService = (HospCataApiService) this.hygeiaServiceManager
					.getBeanByClass(HospCataApiService.class, this.diseaseDto.getAkb020());
			hospCataService.updateHospDisease(this.diseaseDto);
			setJSONReturn("修改目录信息成功！");
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("修改医院疾病目录时出错！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
		return NONE;
	}
	
	/**
	 * 查询中心可匹配目录
	 * 
	 * @return
	 */
	public String queryCenterCata() {
		try {
			this.hospCataDto = (this.hospCataDto == null ? new HospCataDTO() : this.hospCataDto);
			HospCataApiService hospCataService = (HospCataApiService) this.hygeiaServiceManager
					.getBeanByClass(HospCataApiService.class, BizHelper.getAkb020());
			PagerHelper.initPagination(getRequest());
			if (StringUtils.isBlank(this.hospCataDto.getBkc143())
					&& StringUtils.isNotBlank(this.hospCataDto.getAke006()))
				this.hospCataDto.setBkc143(this.hospCataDto.getAke006());
			if (StringUtils.isBlank(this.hospCataDto.getBkc144())
					&& StringUtils.isNotBlank(this.hospCataDto.getAke005()))
				this.hospCataDto.setBkc144(this.hospCataDto.getAke005());
			this.hospCataDto.setAkb020(BizHelper.getAkb020());
			this.hospCataDto.setAaa027(BizHelper.getAaa027());
			this.hospCataDto.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
			this.hospCataDto.setPageSize(PagerHelper.getPaginationObj().getPageSize());
			ListResult listResult = hospCataService.queryCenterCata(this.hospCataDto);
			this.loadCodeValue(listResult.getList());
			PaginationHelper.getPaginationObj().setCount(listResult.getCount());
			DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(listResult.getList()));
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("查询中心可匹配目录出错！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
		return NONE;
	}
	
	/**
	 * 查询中心可匹配药品目录
	 * TS19042300092 - 【问题修复】有本位码按照本位码查询
	 * add 2019/04/24 by ChenXing
	 * @return
	 */
	public String queryCenterWaitCata() {
		try {
			this.hospCataDto = (this.hospCataDto == null ? new HospCataDTO() : this.hospCataDto);
			HospCataApiService hospCataService = (HospCataApiService) this.hygeiaServiceManager
					.getBeanByClass(HospCataApiService.class, BizHelper.getAkb020());
			PagerHelper.initPagination(getRequest());
			if (StringUtils.isBlank(this.hospCataDto.getBkc143())
					&& StringUtils.isNotBlank(this.hospCataDto.getAke006()))
				this.hospCataDto.setBkc143(this.hospCataDto.getAke006());
			if (StringUtils.isBlank(this.hospCataDto.getBkc144())
					&& StringUtils.isNotBlank(this.hospCataDto.getAke005()))
				this.hospCataDto.setBkc144(this.hospCataDto.getAke005());
			this.hospCataDto.setAkb020(BizHelper.getAkb020());
			this.hospCataDto.setAaa027(BizHelper.getAaa027());
			this.hospCataDto.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
			this.hospCataDto.setPageSize(PagerHelper.getPaginationObj().getPageSize());
			ListResult listResult = hospCataService.queryCenterWaitCata(this.hospCataDto);
			this.loadCodeValue(listResult.getList());
			PaginationHelper.getPaginationObj().setCount(listResult.getCount());
			DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(listResult.getList()));
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("查询中心可匹配目录出错！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
		return NONE;
	}

	/**
	 * 查询中心疾病信息
	 * 
	 * @return
	 */
	public String queryCenterDisease() {
		try {
			this.diseaseDto = (this.diseaseDto == null ? new DiseaseDTO() : this.diseaseDto);
			HospCataApiService hospCataService = (HospCataApiService) this.hygeiaServiceManager
					.getBeanByClass(HospCataApiService.class, BizHelper.getAkb020());
			PagerHelper.initPagination(getRequest());
			this.diseaseDto.setAaa027("430399");
			this.diseaseDto.setAkb020(BizHelper.getAkb020());
			this.diseaseDto.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
			this.diseaseDto.setPageSize(PagerHelper.getPaginationObj().getPageSize());

			ListResult listResult = hospCataService.queryCenterDisease(this.diseaseDto);
			PaginationHelper.getPaginationObj().setCount(listResult.getCount());
			DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(listResult.getList()));
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("查询中心疾病信息出错！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
		return NONE;
	}

	/**
	 * 查询特殊疾病限额
	 * 
	 * @return
	 */
	public String queryDiseaseDirectory() {
		try {
			this.diseaseDirDTO = (this.diseaseDirDTO == null ? new DiseaseDirDTO() : this.diseaseDirDTO);
			HospCataApiService hospCataService = hygeiaServiceManager.getBeanByClass(HospCataApiService.class,
					BizHelper.getAkb020());
			PagerHelper.initPagination(getRequest());
			this.diseaseDirDTO.setAkb020(BizHelper.getAkb020());
			this.diseaseDirDTO.setAaa027(BizHelper.getAaa027());
			this.diseaseDirDTO.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
			this.diseaseDirDTO.setPageSize(PagerHelper.getPaginationObj().getPageSize());
			ListResult listResult = hospCataService.queryDiseaseDirectory(this.diseaseDirDTO);
			PaginationHelper.getPaginationObj().setCount(listResult.getCount());
			DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(listResult.getList()));
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("查询特殊疾病限额出错！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
		return NONE;
	}

	/**
	 * 查询疾病限额详情
	 * 
	 * @return
	 */
	public void loadDiseaseDirDetail() {
		try {
			this.diseaseDirDTO = (this.diseaseDirDTO == null ? new DiseaseDirDTO() : this.diseaseDirDTO);
			HospCataApiService hospCataService = hygeiaServiceManager.getBeanByClass(HospCataApiService.class,
					BizHelper.getAkb020());
			if (StringUtils.isBlank(diseaseDirDTO.getAaz149()))
				throw new HygeiaException("aaz149不能为空");
			diseaseDirDTO.setAaa027(BizHelper.getAaa027());
			diseaseDirDTO.setAkb020(BizHelper.getAkb020());
			List<DiseaseDirDTO> diseaseList = hospCataService.loadDiseaseDirDetail(diseaseDirDTO);
			setJSONReturn(diseaseList);
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("查询疾病限额详情出错！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}

	}

	/**
	 * 删除医院目录信息
	 * 
	 * @return
	 */
	public String delHospCata() {
		try {
			String caa027 = getParameter("caa027");
			HospCataApiService hospCataService = (HospCataApiService) this.hygeiaServiceManager
					.getBeanByClass(HospCataApiService.class, BizHelper.getAkb020());
			List<HospCataDTO> hospCataList = new ArrayList<>();
			for (Map<String, Object> hospCataMap : JsonHelper.toList(getParameter("data"))) {
				HospCataDTO hospCataDTO = new HospCataDTO();
				this.beanService.copyProperties(hospCataMap, hospCataDTO, true);
				hospCataDTO.setCaa027(caa027);
				hospCataList.add(hospCataDTO);
			}
			int iReturn = hospCataService.delHospCata(BizHelper.getAkb020(), hospCataList);
			saveJSONMessage("成功删除" + iReturn + "条目录信息！");
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("删除医院目录失败！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
		return NONE;
	}
	
	/**
	 * 删除医院疾病目录信息
	 * @return
	 */
	public String delHospDisease() {
		try {
			String caa027 = getParameter("caa027");
			HospCataApiService hospCataService = (HospCataApiService) this.hygeiaServiceManager
					.getBeanByClass(HospCataApiService.class, BizHelper.getAkb020());
			List<DiseaseDTO> diseaseList = new ArrayList<>();
			for (Map<String, Object> diseaseMap : JsonHelper.toList(getParameter("data"))) {
				DiseaseDTO diseaseDto = new DiseaseDTO();
				this.beanService.copyProperties(diseaseMap, diseaseDto, true);
				diseaseDto.setCaa027(caa027);
				diseaseList.add(diseaseDto);
			}
			int iReturn = hospCataService.delHospDisease(BizHelper.getAkb020(), diseaseList);
			saveJSONMessage("成功删除" + iReturn + "条目录信息！");
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("删除医院目录失败！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
		return NONE;
	}
	/**
	 * 保存医院目录信息
	 */
	public String saveHospCata() {
		try {
			this.hospCataDto = (this.hospCataDto == null ? new HospCataDTO() : this.hospCataDto);
			HospCataApiService hospCataService = (HospCataApiService) this.hygeiaServiceManager
					.getBeanByClass(HospCataApiService.class, BizHelper.getAkb020());
			CharCodeSwitch charCode = new CharCodeSwitchImpl();
			this.hospCataDto.setAka020(charCode.LoadPY(this.hospCataDto.getAke006()));
			this.hospCataDto.setAka021(charCode.LoadWB(this.hospCataDto.getAke006()));
			this.hospCataDto.setAkb020(BizHelper.getAkb020());
			this.hospCataDto.setAaa027(BizHelper.getAaa027());
			hospCataService.saveHospCata(this.hospCataDto);
			setJSONReturn("保存目录信息成功！");
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("保存医院目录信息出错！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
		return NONE;
	}
	
	/**
	 * 保存医院目录信息
	 */
	public String saveHospDisease() {
		try {
			this.diseaseDto = (this.diseaseDto == null ? new DiseaseDTO() : this.diseaseDto);
			HospCataApiService hospCataService = (HospCataApiService) this.hygeiaServiceManager
					.getBeanByClass(HospCataApiService.class, BizHelper.getAkb020());
			CharCodeSwitch charCode = new CharCodeSwitchImpl();
			this.diseaseDto.setAka020(charCode.LoadPY(this.diseaseDto.getAka421()));
			this.diseaseDto.setAka021(charCode.LoadWB(this.diseaseDto.getAka421()));
			this.diseaseDto.setAkb020(BizHelper.getAkb020());
			this.diseaseDto.setAaa027(BizHelper.getAaa027());
			hospCataService.saveHospDisease(this.diseaseDto);
			setJSONReturn("保存目录信息成功！");
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("保存医院目录信息出错！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
		return NONE;
	}

	/**
	 * 自动匹配医院目录
	 * 
	 * @return
	 */
	public String autoMatchCata() {
		try {
			this.hospCataDto = (this.hospCataDto == null ? new HospCataDTO() : this.hospCataDto);
			if (UtilFunc.isEmpty(hospCataDto.getAaa027()))
				throw new HygeiaException("请选择定点中心");
			if (UtilFunc.isEmpty(hospCataDto.getAaz107()))
				throw new HygeiaException("定点医疗机构编号为空");
			if (UtilFunc.isEmpty(hospCataDto.getCaa027()))
				throw new HygeiaException("请选择中心系统");
			if (UtilFunc.isEmpty(hospCataDto.getAke003()))
				throw new HygeiaException("目录类别为空");

			HospCataApiService hospCataService = (HospCataApiService) this.hygeiaServiceManager
					.getBeanByClass(HospCataApiService.class, BizHelper.getAkb020());
			this.hospCataDto.setAae407(BizHelper.getLoginUser());
			this.hospCataDto.setAae406(BizHelper.getUserName());
			this.hospCataDto.setAkb020(BizHelper.getAkb020());
			//修改概要：TS19081900174 - 【需求开发】结算云删除了药品目录匹配信息和药品信息，然后点击自动匹配，会自动匹配成功一条之前删除了的药品匹配信息
			//修改描述：自动匹配只查本地的有效目录 增加一个aae100的入参
			//修改人及修改时间：李嘉伦 20190821
			this.hospCataDto.setAae100("1");
			int iReturn = hospCataService.doAutoMatchCata(this.hospCataDto);
			setJSONReturn(iReturn);
		} catch (Exception ex) {
			this.logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}
	
	/**
	 * 自动匹配医院疾病目录
	 * @return
	 */
	public String autoMatchDisease() {
		try {
			this.diseaseDto = (this.diseaseDto == null ? new DiseaseDTO() : this.diseaseDto);

			HospCataApiService hospCataService = (HospCataApiService) this.hygeiaServiceManager
					.getBeanByClass(HospCataApiService.class, BizHelper.getAkb020());
			this.diseaseDto.setBke205(BizHelper.getLoginUser());
			this.diseaseDto.setBke206(BizHelper.getUserName());
			this.diseaseDto.setAkb020(BizHelper.getAkb020());
			int iReturn = hospCataService.doAutoMatchDisease(this.diseaseDto);
			setJSONReturn(iReturn);
		} catch (Exception ex) {
			this.logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}
	
	/**
	 * 保存医院目录匹配信息
	 */
	public String saveMatchCata() {
		try {
			this.hospCataDto = (this.hospCataDto == null ? new HospCataDTO() : this.hospCataDto);
			if (UtilFunc.isEmpty(hospCataDto.getAaa027()))
				throw new HygeiaException("请选择定点中心");
			if (UtilFunc.isEmpty(hospCataDto.getAaz107()))
				throw new HygeiaException("定点医疗机构编号为空");
			if (UtilFunc.isEmpty(hospCataDto.getCaa027()))
				throw new HygeiaException("请选择中心系统");

			HospCataApiService hospCataService = (HospCataApiService) this.hygeiaServiceManager
					.getBeanByClass(HospCataApiService.class, BizHelper.getAkb020());
			hospCataDto.setAae100("1");
			hospCataDto.setAae407(BizHelper.getLoginUser());
			hospCataDto.setAae406(BizHelper.getUserName());
			hospCataDto.setAkb020(BizHelper.getAkb020());
			int iReturn = hospCataService.doMatchCata(this.hospCataDto);
			setJSONReturn(iReturn);
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("保存目录匹配出错！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
		return NONE;
	}
	
	
	/**
	 * 保存医院目录匹配信息
	 */
	public String saveMatchDisease() {
		try {
			this.diseaseDto = (this.diseaseDto == null ? new DiseaseDTO() : this.diseaseDto);
			if (UtilFunc.isEmpty(diseaseDto.getCaa027()))
				throw new HygeiaException("请选择中心系统");

			HospCataApiService hospCataService = (HospCataApiService) this.hygeiaServiceManager
					.getBeanByClass(HospCataApiService.class, BizHelper.getAkb020());
			diseaseDto.setAae100("1");
			diseaseDto.setBke205(BizHelper.getLoginUser());
			diseaseDto.setBke206(BizHelper.getUserName());
			diseaseDto.setAkb020(BizHelper.getAkb020());
			int iReturn = hospCataService.doMatchDisease(this.diseaseDto);
			setJSONReturn(iReturn);
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("保存疾病匹配出错！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
		return NONE;
	}
	/**
	 * 删除目录匹配信息 (修改审核标志，有效标志)
	 */
	public void delMatchCata() {
		try {
			//TS20010800160 - 【需求开发】结算云药品目录匹配界面优化   可删除多个匹配信息
			String caa027 = getParameter("caa027");
			HospCataApiService hospCataService = (HospCataApiService) this.hygeiaServiceManager
					.getBeanByClass(HospCataApiService.class, BizHelper.getAkb020());
			List<HospCataDTO> hospCataList = new ArrayList<>();
			for (Map<String, Object> hospCataMap : JsonHelper.toList(getParameter("data"))) {
				HospCataDTO hospCataDTO = new HospCataDTO();
				this.beanService.copyProperties(hospCataMap, hospCataDTO, true);
				hospCataDTO.setCaa027(caa027);
				hospCataList.add(hospCataDTO);
			}
			int iReturn = hospCataService.updateKae8Rows(BizHelper.getAkb020(),hospCataList);
			setJSONReturn(iReturn);
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("删除目录匹配信息出错！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
	}
	
	/**
	 * 删除疾病目录匹配信息 (修改审核标志，有效标志)
	 */
	public void delMatchDisease() {
		try {
			this.diseaseDto = (this.diseaseDto == null ? new DiseaseDTO() : this.diseaseDto);
			HospCataApiService hospCataService = hygeiaServiceManager.getBeanByClass(HospCataApiService.class,
					BizHelper.getAkb020());
			this.diseaseDto.setAkb020(BizHelper.getAkb020());
			int iReturn = hospCataService.delMatchDisease(this.diseaseDto);
			setJSONReturn(iReturn);
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("删除疾病匹配信息出错！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
	}

	/**
	 * 查询目录导入明细(有效)
	 */
	public String queryImportCata() {
		try {
			this.hospCataDto = (this.hospCataDto == null ? new HospCataDTO() : this.hospCataDto);
			HospCataApiService hospCataService = (HospCataApiService) this.hygeiaServiceManager
					.getBeanByClass(HospCataApiService.class, BizHelper.getAkb020());
			PagerHelper.initPagination(getRequest());
			this.hospCataDto.setAkb020(BizHelper.getAkb020());
			this.hospCataDto.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
			this.hospCataDto.setPageSize(PagerHelper.getPaginationObj().getPageSize());
			ListResult listResult = hospCataService.queryKad7Page(this.hospCataDto);

			PaginationHelper.getPaginationObj().setCount(listResult.getCount());
			DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(listResult.getList()));
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("查询目录导入数据失败！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
		return NONE;
	}

	/**
	 * 查询目录导入事件(有效)
	 */
	public String queryAllImportItem() {
		try {
			this.hospCataDto = (this.hospCataDto == null ? new HospCataDTO() : this.hospCataDto);
			HospCataApiService hospCataService = (HospCataApiService) this.hygeiaServiceManager
					.getBeanByClass(HospCataApiService.class, BizHelper.getAkb020());
			PagerHelper.initPagination(getRequest());
			this.hospCataDto.setAkb020(BizHelper.getAkb020());
			this.hospCataDto.setBkc031(BizHelper.getLoginUser());
			this.hospCataDto.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
			this.hospCataDto.setPageSize(PagerHelper.getPaginationObj().getPageSize());
			ListResult listResult = hospCataService.queryKad6(this.hospCataDto);
			PaginationHelper.getPaginationObj().setCount(listResult.getCount());
			DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(listResult.getList()));
		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 导入目录验证通过
	 */
	public String saveCatalogInfo() {
		try {
			this.hospCataDto = (this.hospCataDto == null ? new HospCataDTO() : this.hospCataDto);
			HospCataApiService hospCataService = (HospCataApiService) this.hygeiaServiceManager
					.getBeanByClass(HospCataApiService.class, BizHelper.getAkb020());
			if (StringUtils.isBlank(this.hospCataDto.getBkc133()))
				throw new HygeiaException("bkc133不能为空！");

			this.hospCataDto.setAkb020(BizHelper.getAkb020());
			this.hospCataDto.setBkc252("1");
			hospCataService.saveImportCata(this.hospCataDto);
			setJSONReturn("保存导入的目录信息成功！");
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("保存目录导入数据失败！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
		return NONE;
	}
	
	/**
	 * 导入目录验证通过
	 */
	public String saveDiseaselogInfo() {
		try {
			this.hospCataDto = (this.hospCataDto == null ? new HospCataDTO() : this.hospCataDto);
			HospCataApiService hospCataService = (HospCataApiService) this.hygeiaServiceManager
					.getBeanByClass(HospCataApiService.class, BizHelper.getAkb020());
			if (StringUtils.isBlank(this.hospCataDto.getBkc133()))
				throw new HygeiaException("bkc133不能为空！");

			this.hospCataDto.setAkb020(BizHelper.getAkb020());
			this.hospCataDto.setBkc252("1");
			hospCataService.saveImportDisease(this.hospCataDto);
			setJSONReturn("保存导入的目录信息成功！");
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("保存目录导入数据失败！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
		return NONE;
	}

	/**
	 * 导入目录验证不通过
	 * 
	 * @return
	 */
	public String deleItemInfo() {
		try {
			this.hospCataDto = (this.hospCataDto == null ? new HospCataDTO() : this.hospCataDto);
			HospCataApiService hospCataService = (HospCataApiService) this.hygeiaServiceManager
					.getBeanByClass(HospCataApiService.class, BizHelper.getAkb020());
			if (StringUtils.isBlank(this.hospCataDto.getBkc133()))
				throw new HygeiaException("bkc133不能为空！");

			this.hospCataDto.setAkb020(BizHelper.getAkb020());
			hospCataService.deleItemInfo(this.hospCataDto);
			setJSONReturn("删除导入的目录信息成功！");
		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 生成目录
	 * @return
	 */
	public String copyHospCata() {
		try {
			this.hospCataDto = (this.hospCataDto == null ? new HospCataDTO() : this.hospCataDto);
			HospCataApiService hospCataService = (HospCataApiService) this.hygeiaServiceManager
					.getBeanByClass(HospCataApiService.class, BizHelper.getAkb020());

			this.hospCataDto.setCaa027(getParameter("caa027"));
			this.hospCataDto.setAkb020(BizHelper.getAkb020());
			this.hospCataDto.setBkc003(BizHelper.getAkb020());
			this.hospCataDto.setBkc002(BizHelper.getAkb021());
			int iReturn = hospCataService.copyHospCata(this.hospCataDto);
			saveJSONMessage("更新成功" + iReturn + "条目录信息！");
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("自动生成目录数据出错！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
		return NONE;
	}
	
	/**
	 * 生成医院疾病目录
	 * @return
	 */
	public String copyHospDisease() {
		try {
			this.diseaseDto = (this.diseaseDto == null ? new DiseaseDTO() : this.diseaseDto);
			HospCataApiService hospCataService = (HospCataApiService) this.hygeiaServiceManager
					.getBeanByClass(HospCataApiService.class, BizHelper.getAkb020());

			this.diseaseDto.setCaa027(getParameter("caa027"));
			this.diseaseDto.setAkb020(BizHelper.getAkb020());
			this.diseaseDto.setBkc003(BizHelper.getAkb020());
			this.diseaseDto.setBkc002(BizHelper.getAkb021());
			int iReturn = hospCataService.copyHospDisease(this.diseaseDto);
			saveJSONMessage("更新成功" + iReturn + "条目录信息！");
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("自动生成目录数据出错！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
		return NONE;
	}
	
	/**
	 * 生成目录所有目录
	 * @return
	 */
	public String copyAllCata() {
		try {
			this.hospCataDto = (this.hospCataDto == null ? new HospCataDTO() : this.hospCataDto);
			HospCataApiService hospCataService = (HospCataApiService) this.hygeiaServiceManager
					.getBeanByClass(HospCataApiService.class, BizHelper.getAkb020());

			this.hospCataDto.setCaa027(getParameter("caa027"));
			this.hospCataDto.setAkb020(BizHelper.getAkb020());
			this.hospCataDto.setBkc003("admin");
			this.hospCataDto.setBkc002("医保系统管理员");
			int iReturn = hospCataService.copyAllCata(this.hospCataDto);
			saveJSONMessage("更新成功" + iReturn + "条目录信息！");
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("自动生成目录数据出错！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
		return NONE;
	}

	/**
	 * 药品本位码模糊匹配查询
	 */
	public void queryDrugGBCode() {
		this.hospCataDto = (this.hospCataDto == null ? new HospCataDTO() : this.hospCataDto);
		HospCataApiService hospCataService = (HospCataApiService) this.hygeiaServiceManager
				.getBeanByClass(HospCataApiService.class, BizHelper.getAkb020());
		PagerHelper.initPagination(getRequest());
		this.hospCataDto.setAkb020(BizHelper.getAkb020());
		this.hospCataDto.setAaa027(BizHelper.getAaa027());
		this.hospCataDto.setSearchTerm(getParameter("searchTerm"));
		this.hospCataDto.setAke006(getParameter("ake006"));
		this.hospCataDto.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
		this.hospCataDto.setPageSize(PagerHelper.getPaginationObj().getPageSize());
		ListResult listResult = hospCataService.drugGBCodeFuzzyMatch(this.hospCataDto);
		this.loadAka070(listResult.getList());
		PaginationHelper.getPaginationObj().setCount(listResult.getCount());
		DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(listResult.getList()));
	}
	
	
	/**
	 * 目录匹配时加载医院信息
	 */
	public void queryDesignatedCenter() {
		HospCataApiService hospCataApiService = (HospCataApiService) this.hygeiaServiceManager
				.getBeanByClass(HospCataApiService.class, BizHelper.getAkb020());
		try {
			HospCataDTO HospCataDTO = new HospCataDTO();
			HospCataDTO.setAaa027(BizHelper.getAaa027());
			HospCataDTO.setAkb020(BizHelper.getAkb020());
			HospCataDTO.setCaa027(getParameter("caa027"));
			List<Map<String, Object>> list = hospCataApiService.queryDesignatedCenter(HospCataDTO);
			if (list.size() > 1) { // 多中心时让当前医院所属中心排第一个
				for (Map<String, Object> map : list) {
					if (HospCataDTO.getAaa027().equals(map.get("aaa027"))) {
						list.remove(map);
						list.set(0, map);
						break;
					}
				}
			}
			setJSONReturn(list);
		} catch (IOException ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("加载定点中心失败：\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
	}

	/**
	 * 查询医院目录匹配信息
	 */
	public void queryMatchCata() {
		try {
			this.hospCataDto = (this.hospCataDto == null ? new HospCataDTO() : this.hospCataDto);
			this.hospCataDto.setAkb020(BizHelper.getAkb020());
			if (UtilFunc.isEmpty(this.hospCataDto.getAaa027()))
				throw new HygeiaException("请选择定点中心");
			if (UtilFunc.isEmpty(this.hospCataDto.getCaa027()))
				throw new HygeiaException("请选择中心系统");
			if (UtilFunc.isEmpty(this.hospCataDto.getAke003()))
				throw new HygeiaException("目录类别为空");
			HospCataApiService hospCataService = (HospCataApiService) this.hygeiaServiceManager
					.getBeanByClass(HospCataApiService.class, this.hospCataDto.getAkb020());
			PagerHelper.initPagination(getRequest());
			this.hospCataDto.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
			this.hospCataDto.setPageSize(PagerHelper.getPaginationObj().getPageSize());
			ListResult listResult = hospCataService.getCataMatch(this.hospCataDto);
			this.loadCodeValue(listResult.getList());
			PaginationHelper.getPaginationObj().setCount(listResult.getCount());
			DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(listResult.getList()));
		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}
	}
	
	/**
	 * 查询医院目录匹配信息
	 */
	public void queryMatchDisease() {
		try {
			this.diseaseDto = (this.diseaseDto == null ? new DiseaseDTO() : this.diseaseDto);
			this.diseaseDto.setAkb020(BizHelper.getAkb020());
			HospCataApiService hospCataService = (HospCataApiService) this.hygeiaServiceManager
					.getBeanByClass(HospCataApiService.class, this.diseaseDto.getAkb020());
			PagerHelper.initPagination(getRequest());
			this.diseaseDto.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
			this.diseaseDto.setPageSize(PagerHelper.getPaginationObj().getPageSize());
			ListResult listResult = hospCataService.getDiseaseMatch(this.diseaseDto);
			this.loadCodeValue(listResult.getList());
			PaginationHelper.getPaginationObj().setCount(listResult.getCount());
			DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(listResult.getList()));
		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}
	}

	/**
	 * 把未审核的目录匹配信息提交至中心审核
	 */
	public void submitMatchCata() {
		try {
			this.hospCataDto = (this.hospCataDto == null ? new HospCataDTO() : this.hospCataDto);
			this.hospCataDto.setAkb020(BizHelper.getAkb020());
			if (UtilFunc.isEmpty(this.hospCataDto.getAaa027()))
				throw new HygeiaException("请选择定点中心");
			if (UtilFunc.isEmpty(this.hospCataDto.getCaa027()))
				throw new HygeiaException("请选择中心系统");
			HospCataApiService hospCataService = hygeiaServiceManager.getBeanByClass(HospCataApiService.class,
					this.hospCataDto.getAkb020());
			int count = hospCataService.submitMatchCata(this.hospCataDto);
			setJSONReturn(count);
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("提交未审核目录出错！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
	}
	
	/**
	 * 把未审核的目录匹配信息提交至中心审核
	 */
	public void submitMatchDisease() {
		try {
			this.diseaseDto = (this.diseaseDto == null ? new DiseaseDTO() : this.diseaseDto);
			this.diseaseDto.setAkb020(BizHelper.getAkb020());
			this.diseaseDto.setAaa027(BizHelper.getAaa027());
			if (UtilFunc.isEmpty(this.diseaseDto.getCaa027()))
				throw new HygeiaException("请选择中心系统");
			HospCataApiService hospCataService = hygeiaServiceManager.getBeanByClass(HospCataApiService.class,
					this.diseaseDto.getAkb020());
			int count = hospCataService.submitMatchDisease(this.diseaseDto);
			setJSONReturn(count);
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("提交未审核目录出错！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
	}

	/**
	 * 上传目录文件与插入数据库
	 */
	public String uploadFileCata() {
		try {
			this.hospCataDto = (this.hospCataDto == null ? new HospCataDTO() : this.hospCataDto);
			HospCataApiService hospCataService = hygeiaServiceManager.getBeanByClass(HospCataApiService.class,
					BizHelper.getAkb020());
			this.uploadFile("/uploadfile/hospfile");
			this.hospCataDto.setBkc033(this.getImgFileName());
			this.hospCataDto.setBkc030(BizHelper.getUserName());
			this.hospCataDto.setBkc031(BizHelper.getLoginUser());
			this.hospCataDto.setAaa027(BizHelper.getAaa027());
			this.hospCataDto.setAkb020(BizHelper.getAkb020());
			String pageFlag = getParameter("pageFlag");
			
			List<HospCataDTO> hospCataList = null;
			if ("item".equals(pageFlag))
				hospCataList = this.getItemExcelContext(imgFile, this.hospCataDto);
			else if("cata".equals(pageFlag))
				hospCataList = this.getCataExcelContext(imgFile, this.hospCataDto);
			else if("disease".equals(pageFlag))
				hospCataList = this.getDiseaseExcelContext(imgFile, this.hospCataDto);
			
			hospCataService.chackImportDetail(this.hospCataDto, hospCataList, pageFlag);
			this.setJSONReturn("上传目录数据成功！");
		} catch (Throwable ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("批量导入上传目录文件出错！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
		return NONE;
	}

	/**
	 * 解析上传的文件返回list数据(项目目录)
	 * 
	 * @return
	 */
	private List<HospCataDTO> getItemExcelContext(File cata_img, HospCataDTO hospCataDto) {
		List<HospCataDTO> rsList = new ArrayList<>();
		Sheet sheet = null;
		try {
			sheet = Workbook.getWorkbook(cata_img).getSheet(0);// 获取文件第一页
		} catch (Exception e) {
			throw new HygeiaException("获取excel文件出错！");
		}

		if (sheet.getRows() >= 2) {
			List<String> cloumNameList = new ArrayList<>();
			cloumNameList.add("医院项目编码*");
			cloumNameList.add("医院项目名称*");
			cloumNameList.add("目录类别*");
			cloumNameList.add("单价*");
			cloumNameList.add("生产单位");
			cloumNameList.add("产地");
			cloumNameList.add("英文名称");
			cloumNameList.add("规格型号");
			cloumNameList.add("商品名");
			cloumNameList.add("别名");
			cloumNameList.add("备注");
			for (int i = 0; i < cloumNameList.size(); i++) {
				if (!cloumNameList.get(i).equals(sheet.getCell(i, 0).getContents())) {
					throw new HygeiaException("文件对应列不符合模版标识内容,请重新下载模版并填写相关数据！" + cloumNameList.get(i));
				}
			}
		} else
			throw new HygeiaException("上传文件为空,请核查!");

		for (int i = 1; i < sheet.getRows(); i++) {
			HospCataDTO hospCata = new HospCataDTO();
			String ake005 = sheet.getCell(0, i).getContents().trim();
			if (StringUtils.isBlank(ake005))
				hospCata.setAae111(hospCata.getAae111() + "【医院项目编码】不能为空; ");
			else
				hospCata.setAke005(ake005);

			String ake006 = sheet.getCell(1, i).getContents().trim();
			if (StringUtils.isBlank(ake006))
				hospCata.setAae111(hospCata.getAae111() + "【目录名称】不能为空; ");
			else
				hospCata.setAke006(ake006);

			String ake003 = sheet.getCell(2, i).getContents().trim();
			if (StringUtils.isBlank(ake003))
				hospCata.setAae111(hospCata.getAae111() + "【目录类别】不能为空; ");
			else {
				for (Object key : CodetableMapping.get("ake003").keySet()) {
					if (CodetableMapping.get("ake003").get(key).equals(ake003)) {
						hospCata.setAke003(key.toString());
						break;
					}
				}
				if (StringUtils.isBlank(hospCata.getAke003()))
					hospCata.setAae111(hospCata.getAae111() + "【目录类别】需从下拉框中选择; ");
			}

			String bkc140 = sheet.getCell(3, i).getContents().trim();
			if (StringUtils.isBlank(bkc140))
				hospCata.setAae111(hospCata.getAae111() + "【单价】不能为空; ");
			else {
				try {
					hospCata.setBkc140(new BigDecimal(bkc140));
				} catch (Exception e) {
					hospCata.setAae111(hospCata.getAae111() + "录入的【单价】不是数字; ");
				}
			}

			hospCata.setBkc141(sheet.getCell(4, i).getContents().trim());
			hospCata.setBkm019(sheet.getCell(5, i).getContents().trim());
			hospCata.setAka062(sheet.getCell(6, i).getContents().trim());
			hospCata.setBkc139(sheet.getCell(7, i).getContents().trim());
			hospCata.setBkm022(sheet.getCell(8, i).getContents().trim());
			hospCata.setBkm021(sheet.getCell(9, i).getContents().trim());
			hospCata.setBkm025(sheet.getCell(10, i).getContents().trim());
			CharCodeSwitch charCode = new CharCodeSwitchImpl();
			hospCata.setAka020(charCode.LoadPY(hospCata.getAke006()));
			hospCata.setAka021(charCode.LoadWB(hospCata.getAke006()));
			hospCata.setBkc002(hospCataDto.getBkc030());
			hospCata.setBkc003(hospCataDto.getBkc031());
			hospCata.setAaa027(hospCataDto.getAaa027());
			rsList.add(hospCata);
		}

		return rsList;
	}

	/**
	 * 解析上传的文件返回list数据(药品目录)
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private List<HospCataDTO> getCataExcelContext(File cata_img, HospCataDTO hospCataDto) {
		List<HospCataDTO> rsList = new ArrayList<>();
		Sheet sheet = null;
		try {
			sheet = Workbook.getWorkbook(cata_img).getSheet(0);// 获取文件第一页
		} catch (Exception e) {
			throw new HygeiaException("获取excel文件出错！");
		}
		if (sheet.getRows() >= 2) {
			List<String> cloumNameList = new ArrayList<>();
			cloumNameList.add("目录编码*");
			cloumNameList.add("目录名称*");
			cloumNameList.add("目录类别*");
			cloumNameList.add("单价*");
			cloumNameList.add("医院剂型*");
			cloumNameList.add("规格型号*");
			cloumNameList.add("药监本位码");
			cloumNameList.add("生产单位");
			cloumNameList.add("产地");
			cloumNameList.add("英文名称");
			cloumNameList.add("商品名");
			cloumNameList.add("别名");
			cloumNameList.add("包装价格");
			cloumNameList.add("包装规格");
			cloumNameList.add("批准文号");
			cloumNameList.add("批准日期");
			cloumNameList.add("处方用药标识");
			cloumNameList.add("危重病用标志");
			cloumNameList.add("毒麻精贵标志");
			cloumNameList.add("备注");
			for (int i = 0; i < cloumNameList.size(); i++) {
				if (!cloumNameList.get(i).equals(sheet.getCell(i, 0).getContents())) {
					throw new HygeiaException("文件对应列不符合模版标识内容,请重新下载模版并填写相关数据！正确为【" + 
							cloumNameList.get(i)+"】-当前为【"+sheet.getCell(i, 0).getContents()+"】");
				}
			}
		} else
			throw new HygeiaException("上传文件为空,请核查!");
		
		if(sheet.getRows()>5000){
			throw new HygeiaException("批量上传一次最多5000条，请将模版数据拆分成多个模版分批上传!");
		}
		
		for (int i = 1; i < sheet.getRows(); i++) {
			HospCataDTO hospCata = new HospCataDTO();
			String ake005 = sheet.getCell(0, i).getContents().trim();
			if (StringUtils.isBlank(ake005))
				hospCata.setAae111(hospCata.getAae111() + "【目录编码】不能为空; ");
			else
				hospCata.setAke005(ake005);

			String ake006 = sheet.getCell(1, i).getContents().trim();
			if (StringUtils.isBlank(ake006))
				hospCata.setAae111(hospCata.getAae111() + "目录名称不能为空; ");
			else
				hospCata.setAke006(ake006);

			String ake003 = sheet.getCell(2, i).getContents().trim();
			if (StringUtils.isBlank(ake003))
				hospCata.setAae111(hospCata.getAae111() + "【目录类别】不能为空; ");
			else {
				for (Object key : CodetableMapping.get("ake003").keySet()) {
					if (CodetableMapping.get("ake003").get(key).equals(ake003)) {
						hospCata.setAke003(key.toString());
						break;
					}
				}
				if (StringUtils.isBlank(hospCata.getAke003()))
					hospCata.setAae111(hospCata.getAae111() + "【目录类别】需从下拉框中选择; ");
			}

			String bkc140 = sheet.getCell(3, i).getContents().trim();
			if (StringUtils.isBlank(bkc140))
				hospCata.setAae111(hospCata.getAae111() + "【单价】不能为空; ");
			else {
				try {
					hospCata.setBkc140(new BigDecimal(bkc140));
				} catch (Exception e) {
					hospCata.setAae111(hospCata.getAae111() + "录入的【单价】不是数字; ");
				}
			}

			String bkm005 = sheet.getCell(4, i).getContents().trim();
			if (StringUtils.isBlank(bkm005))
				hospCata.setAae111(hospCata.getAae111() + "【医院剂型】不能为空; ");
			else {
				for (Object key : CodetableMapping.get("aka070").keySet()) {
					if (CodetableMapping.get("aka070").get(key).equals(bkm005)) {
						hospCata.setBkm005(key.toString());
						break;
					}
				}
				if (StringUtils.isBlank(hospCata.getBkm005()))
					hospCata.setAae111(hospCata.getAae111() + "【医院剂型】需从下拉框中选择; ");
			}

			String bkc139 = sheet.getCell(5, i).getContents().trim();
			if (StringUtils.isBlank(bkc139))
				hospCata.setAae111(hospCata.getAae111() + "【规格型号】不能为空; ");
			else
				hospCata.setBkc139(bkc139);

			hospCata.setBkm017(sheet.getCell(6, i).getContents().trim());
			hospCata.setBkc141(sheet.getCell(7, i).getContents().trim());
			hospCata.setBkm019(sheet.getCell(8, i).getContents().trim());
			hospCata.setAka062(sheet.getCell(9, i).getContents().trim());
			hospCata.setBkm022(sheet.getCell(10, i).getContents().trim());
			hospCata.setBkm021(sheet.getCell(11, i).getContents().trim());
			if(StringUtils.isNotBlank(sheet.getCell(12, i).getContents().trim())){
				try {
					hospCata.setBkm014(new BigDecimal(sheet.getCell(12, i).getContents().trim()));
				} catch (Exception e) {
					hospCata.setAae111(hospCata.getAae111() + "录入的【包装价格】不是数字; ");
				}
			}
			hospCata.setBkm020(sheet.getCell(13, i).getContents().trim());
			hospCata.setBkm007(sheet.getCell(14, i).getContents().trim());
			hospCata.setBkm009(
					DateFunc.dateToString(DateFunc.parseDate(sheet.getCell(15, i).getContents().trim()), "yyyyMMdd"));

			String aka064 = sheet.getCell(16, i).getContents().trim(),
					aka067 = sheet.getCell(17, i).getContents().trim(),
					aka066 = sheet.getCell(18, i).getContents().trim();
			Map mYes_no = CodetableMapping.get("yes_no");
			for (Object key : mYes_no.keySet()) {
				if (mYes_no.get(key).equals(aka064))
					hospCata.setAka064(key.toString());
				if (mYes_no.get(key).equals(aka067))
					hospCata.setAka067(key.toString());
				if (mYes_no.get(key).equals(aka066))
					hospCata.setAka066(key.toString());
			}
			if (StringUtils.isNotBlank(hospCata.getAka064()) && !"0".equals(hospCata.getAka064())
					&& !"1".equals(hospCata.getAka064()))
				hospCata.setAae111(hospCata.getAae111() + "【处方用药标识】需从下拉框中选择; ");
			if (StringUtils.isNotBlank(hospCata.getAka067()) && !"0".equals(hospCata.getAka067())
					&& !"1".equals(hospCata.getAka067()))
				hospCata.setAae111(hospCata.getAae111() + "【危重病用标志】需从下拉框中选择; ");
			if (StringUtils.isNotBlank(hospCata.getAka066()) && !"0".equals(hospCata.getAka066())
					&& !"1".equals(hospCata.getAka066()))
				hospCata.setAae111(hospCata.getAae111() + "【毒麻精贵标志】需从下拉框中选择; ");

			hospCata.setBkm025(sheet.getCell(19, i).getContents().trim());
			CharCodeSwitch charCode = new CharCodeSwitchImpl();
			hospCata.setAka020(charCode.LoadPY(hospCata.getAke006()));
			hospCata.setAka021(charCode.LoadWB(hospCata.getAke006()));
			hospCata.setBkc002(hospCataDto.getBkc030());
			hospCata.setBkc003(hospCataDto.getBkc031());
			hospCata.setAaa027(hospCataDto.getAaa027());
			rsList.add(hospCata);
		}
		return rsList;
	}
	
	/**
	 * 解析上传的文件返回list数据(药品目录)
	 * 
	 * @return
	 */
	private List<HospCataDTO> getDiseaseExcelContext(File cata_img, HospCataDTO hospCataDto) {
		List<HospCataDTO> rsList = new ArrayList<>();
		Sheet sheet = null;
		try {
			sheet = Workbook.getWorkbook(cata_img).getSheet(0);// 获取文件第一页
		} catch (Exception e) {
			throw new HygeiaException("获取excel文件出错！");
		}
		if (sheet.getRows() >= 2) {
			List<String> cloumNameList = new ArrayList<>();
			cloumNameList.add("疾病编码*");
			cloumNameList.add("疾病名称*");
			cloumNameList.add("疾病分类");
			cloumNameList.add("英文化学名");
			cloumNameList.add("备注");
			for (int i = 0; i < cloumNameList.size(); i++) {
				if (!cloumNameList.get(i).equals(sheet.getCell(i, 0).getContents())) {
					throw new HygeiaException("文件对应列不符合模版标识内容,请重新下载模版并填写相关数据！正确为【" + 
							cloumNameList.get(i)+"】-当前为【"+sheet.getCell(i, 0).getContents()+"】");
				}
			}
		} else
			throw new HygeiaException("上传文件为空,请核查!");

		for (int i = 1; i < sheet.getRows(); i++) {
			HospCataDTO hospCata = new HospCataDTO();
			String aka420 = sheet.getCell(0, i).getContents().trim();
			if (StringUtils.isBlank(aka420))
				hospCata.setAae111(hospCata.getAae111() + "【疾病编码】不能为空; ");
			else
				hospCata.setAka420(aka420);

			String aka421 = sheet.getCell(1, i).getContents().trim();
			if (StringUtils.isBlank(aka420))
				hospCata.setAae111(hospCata.getAae111() + "【疾病名称】不能为空; ");
			else
				hospCata.setAka421(aka421);

			hospCata.setAka122(sheet.getCell(2, i).getContents().trim());
			hospCata.setAka062(sheet.getCell(3, i).getContents().trim());
			hospCata.setAae013(sheet.getCell(4, i).getContents().trim());

			CharCodeSwitch charCode = new CharCodeSwitchImpl();
			hospCata.setAka020(charCode.LoadPY(hospCata.getAka421()));
			hospCata.setAka021(charCode.LoadWB(hospCata.getAka421()));
			hospCata.setBkc003(BizHelper.getLoginUser());
			hospCata.setBkc002(BizHelper.getUserName());
			rsList.add(hospCata);
		}
		return rsList;
	}
	
	/**
	 * 批量删除目录匹配信息 (修改审核标志，有效标志)
	 */
	public void updateKae8Rows() {
		try {
			String caa027 = getParameter("caa027");
			HospCataApiService hospCataService = (HospCataApiService) this.hygeiaServiceManager
					.getBeanByClass(HospCataApiService.class, BizHelper.getAkb020());
			List<HospCataDTO> hospCataList = new ArrayList<>();
			for (Map<String, Object> hospCataMap : JsonHelper.toList(getParameter("data"))) {
				HospCataDTO hospCataDTO = new HospCataDTO();
				this.beanService.copyProperties(hospCataMap, hospCataDTO, true);
				hospCataDTO.setCaa027(caa027);
				hospCataList.add(hospCataDTO);
			}
			int iReturn = hospCataService.updateKae8Rows(BizHelper.getAkb020(), hospCataList);
			setJSONReturn(iReturn);
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("删除目录匹配信息出错！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
	}
	
	
	
	private void loadCodeValue(List<?> list) {
		try {
			this.loadDispValue(list, "ake003", "ake003");
			this.loadDispValue(list, "aka070", "aka070");
			this.loadDispValue(list, "aae016", "aae016");
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void loadDispValue(List<?> list, String fieldName, String codeType) throws Exception {
		for (Object obj : list) {
			if (obj instanceof HospCataDTO) {
				String dataValue = (String) this.getValueByName(obj, fieldName);
				if (!"".equals(dataValue) || null != dataValue) {
					String disPlayValue = CodetableMapping.getDisplayValue(codeType, dataValue, dataValue);
					this.setValueByName(obj, fieldName, disPlayValue);
				}
			} else if (obj instanceof Map) {
				String dataValue = (String) (((Map) obj).get(fieldName));
				if (!"".equals(dataValue) || null != dataValue) {
					String disPlayValue = CodetableMapping.getDisplayValue(codeType, dataValue, dataValue);
					((Map) obj).put(fieldName, disPlayValue);
				}
			}else if (obj instanceof DiseaseDTO && "aae016".contentEquals(codeType)) {
				String aae016 = ((DiseaseDTO) obj).getAae016();
				if (!"".equals(aae016) || null != aae016) {
					String disPlayValue = CodetableMapping.getDisplayValue(codeType, aae016, aae016);
					((DiseaseDTO) obj).setAae016(disPlayValue);
				}
			}
		}
	}

	private Object getValueByName(Object obj, String fieldName) throws Exception {
		String getter = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		Method method = ((HospCataDTO) obj).getClass().getMethod(getter, new Class[] {});
		Object value = method.invoke(obj);
		return value;
	}

	private void setValueByName(Object obj, String fieldName, String fieldValue) throws Exception {
		String setter = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		Method method = ((HospCataDTO) obj).getClass().getMethod(setter, String.class);
		method.invoke(obj, fieldValue);
	}

	private void uploadFile(String filePath) {
		String uploadFileName = DateFunc.dateToString(new Date(), "yyyyMMdd") + "_"
				+ String.valueOf(new Date().getTime()) + "_" + imgFileName;
		String filepath = ServletActionContext.getServletContext().getRealPath(filePath);
		File saveFile = null;
		if (this.imgFile != null) {
			File saveDir = new File(filepath);
			if (!saveDir.exists()) {
				saveDir.mkdirs();
			}
			saveFile = new File(filepath + "/" + uploadFileName);
			try {
				FileUtils.copyFile(this.imgFile, saveFile);
			} catch (IOException e) {
				throw new HygeiaException("读取Excel文件出错，请确认文件是否正确！！");
			}
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void loadAka070(List<?> list) {
		for (Object obj : list) {
			if (obj instanceof Map) {
				String aka070 = (String) (((Map) obj).get("aka070"));
				String disPlayValue = CodetableMapping.getDisplayValue("aka070", aka070, "请选择...");
				((Map) obj).put("aka070Name", disPlayValue);
			}
		}
	}

	private String addErrSNInfo() {
		String errLogSn = this.errLogSnService.getErrSN(ProjectType.WEB);
		errLogSn = "错误号 " + errLogSn + " 信息： ";
		return errLogSn;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String addNotBlankParameters() {
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

	public DiseaseDTO getDiseaseDto() {
		return diseaseDto;
	}

	public void setDiseaseDto(DiseaseDTO diseaseDto) {
		this.diseaseDto = diseaseDto;
	}

	public HospCataDTO getHospCataDto() {
		return this.hospCataDto;
	}

	public void setHospCataDto(HospCataDTO hospCataDto) {
		this.hospCataDto = hospCataDto;
	}

	public DiseaseDirDTO getDiseaseDirDTO() {
		return diseaseDirDTO;
	}

	public void setDiseaseDirDTO(DiseaseDirDTO diseaseDirDTO) {
		this.diseaseDirDTO = diseaseDirDTO;
	}

	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}
}