package com.powersi.ssm.biz.medicare.special.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.biz.medicare.catalog.service.api.HospCataApiService;
import com.powersi.biz.medicare.comm.pojo.KA16DTO;
import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.hosp.pojo.HospInfoDTO;
import com.powersi.biz.medicare.hosp.service.api.HospApiService;
import com.powersi.biz.medicare.inhospital.service.bean.BeanService;
import com.powersi.biz.medicare.special.pojo.TwoDisThreeMasterTeamDTO;
import com.powersi.biz.medicare.special.service.TeamVsSelfPayApplyService;
import com.powersi.biz.medicare.special.pojo.MediSpecDTO;
import com.powersi.comm.utils.ToolUtil;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.hygeia.web.util.JsonHelper;
import com.powersi.hygeia.web.util.WebHelper;
import com.powersi.ssm.biz.medicare.common.service.CodeTableSwitch;
import com.powersi.ssm.biz.medicare.common.service.CodeTableSwitchImpl;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.PagerHelper;

/**
 * 
 * 两病三师团队备案vs自费补偿申请
 * 
 * @author zhos
 *
 */
@Action(value = "TeamVsSelfPayApplyAction", results = {
		@Result(name = "initTwoDiseaseThreeMasterApply", location = "/pages/biz/medicare/special/TwoDiseaseThreeMasterNew.jsp"),
		@Result(name = "teamEdit", location = "/pages/biz/medicare/special/TwoDiseaseThreeMasterDetail.jsp"),
		@Result(name = "paysDetail", location = "/pages/biz/medicare/special/PaysCompensationDetail.jsp"),
		@Result(name = "chooseSelfPayDisease", location = "/pages/biz/medicare/comm/ChooseSelfPayDisease.jsp") })
public class TeamVsSelfPayApplyAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private MediSpecDTO mediSpecDto;
	private KA16DTO ka16dto;
	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;
	@Autowired
	private BeanService beanService;
	private TwoDisThreeMasterTeamDTO tDisThreeMTeamDto;

	public TwoDisThreeMasterTeamDTO gettDisThreeMTeamDto() {
		return tDisThreeMTeamDto;
	}

	public void settDisThreeMTeamDto(TwoDisThreeMasterTeamDTO tDisThreeMTeamDto) {
		this.tDisThreeMTeamDto = tDisThreeMTeamDto;
	}

	/**
	 * 保存两病三师备案申请
	 * 
	 * @return
	 */
	public String saveTwoDiseaseThreeMasterApply() {
		try {
			if (WebHelper.isPostRequest(getRequest())) {

				List<Map<String, Object>> insertData = new ArrayList<>();
				Map<String, Object> mapData = new HashMap<String, Object>();
				beanService.copyProperties(tDisThreeMTeamDto, mapData, true);
				insertData.add(mapData);
				TeamVsSelfPayApplyService service = hygeiaServiceManager.getBeanByClass(TeamVsSelfPayApplyService.class,
						BizHelper.getAkb020());
				service.dealwithTeamApply("insert", insertData, tDisThreeMTeamDto);

				setJSONReturn("保存两病三师备案申请成功！");
			}
		} catch (Exception e) {
			logger.error("保存两病三师备案申请失败！错误信息：" + ToolUtil.getExceptionInfo(e));
			saveJSONError("保存两病三师备案申请失败！\r\n" + (e.getMessage() == null ? "" : e.getMessage()));
		}
		return NONE;
	}

	/**
	 * 删除两病三师备案申请信息
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String delTwoDiseaseThreeMasterApply() {
		try {
			int i = 0;

			if (tDisThreeMTeamDto == null) {
				tDisThreeMTeamDto = new TwoDisThreeMasterTeamDTO();
			}
			String akb020 = BizHelper.getAkb020();
			TeamVsSelfPayApplyService service = hygeiaServiceManager.getBeanByClass(TeamVsSelfPayApplyService.class,
					akb020);
			tDisThreeMTeamDto.setAkb020(akb020);

			String data = getParameter("data");
			if (UtilFunc.hasText(data)) {
				i = service.dealwithTeamApply("delete", JsonHelper.toList(data), tDisThreeMTeamDto);
			}

			Map map = new HashMap();
			map.put("data", data);

			if (i > 0) {
				setJSONReturn("删除两病三师备案申请信息成功！", map);
			} else {
				setJSONReturn("删除两病三师备案申请信息失败！", map);
			}

		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 获取两病三师备案申请信息
	 * 
	 * @return
	 */
	public String getTwoDiseaseThreeMasterApply() {
		try {
			if (WebHelper.isPostRequest(getRequest())) {
				PagerHelper.initPagination(getRequest());
				String akb020 = BizHelper.getAkb020();
				TeamVsSelfPayApplyService service = hygeiaServiceManager.getBeanByClass(TeamVsSelfPayApplyService.class,
						akb020);
				// List<Map> result = service.queryTeamApply(tDisThreeMTeamDto);
				// setJSONReturn(result);
				DataGridHelper.render(getRequest(), getResponse(),
						PagerHelper.getPaginatedList(service.queryTeamApply(tDisThreeMTeamDto)));
			}
		} catch (Exception e) {
			logger.error("获取两病三师备案申请信息失败！错误信息：" + ToolUtil.getExceptionInfo(e));
			saveJSONError("获取两病三师备案申请信息失败！\r\n" + (e.getMessage() == null ? "" : e.getMessage()));
		}

		return NONE;
	}

	/**
	 * 查询需要修改的两病三师备案申请信息
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String queryTwoDisThreeMApplyEdit() {
		try {
			if (tDisThreeMTeamDto == null) {
				tDisThreeMTeamDto = new TwoDisThreeMasterTeamDTO();
			}
			String akb020 = BizHelper.getAkb020();
			TeamVsSelfPayApplyService service = hygeiaServiceManager.getBeanByClass(TeamVsSelfPayApplyService.class,
					akb020);
			tDisThreeMTeamDto.setAkb020(akb020);
			Map mapData = service.queryTeamApply(tDisThreeMTeamDto).get(0);

			String[] propertyName = new String[] { "akb020", "aaz308", "bka801", "bka802", "bka803", "bka804", "bka805",
					"aae016", "aae100", "bkm025", "aae011", "bae100" };
			beanService.copyProperties(mapData, tDisThreeMTeamDto, propertyName);
			String aae036Str = mapData.get("aae036").toString();
			Date aae036 = DateFunc.parseDatetime(aae036Str);
			tDisThreeMTeamDto.setAae036(aae036);

			loadCodeSelectData();

			Map bka503List = new LinkedHashMap();
			bka503List.put(tDisThreeMTeamDto.getBka801(), tDisThreeMTeamDto.getBka802());
			setAttribute("bka503List", bka503List);
			Map bka503List1 = new LinkedHashMap();
			bka503List1.put(tDisThreeMTeamDto.getBka803(), tDisThreeMTeamDto.getBka804());
			setAttribute("bka503List1", bka503List1);

		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}

		return "teamEdit";
	}

	/**
	 * 修改两病三师备案申请信息
	 * 
	 * @return
	 */
	public String updateTowDisThreeMApply() {
		try {

			String akb020 = BizHelper.getAkb020();
			TeamVsSelfPayApplyService service = hygeiaServiceManager.getBeanByClass(TeamVsSelfPayApplyService.class,
					akb020);
			List<Map<String, Object>> insertData = new ArrayList<>();
			Map<String, Object> mapData = new HashMap<String, Object>();

			tDisThreeMTeamDto.setAae011(BizHelper.getLoginUser());
			tDisThreeMTeamDto.setBae100(BizHelper.getUserName());
			beanService.copyProperties(tDisThreeMTeamDto, mapData, true);
			insertData.add(mapData);
			int i = service.dealwithTeamApply("update", insertData, tDisThreeMTeamDto);
			if (i > 0) {
				setJSONReturn("修改两病三师备案申请信息成功！");
			} else {
				setJSONReturn("修改两病三师备案申请信息失败！");
			}

		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;

	}

	/**
	 * 初始化两病三师申请页面
	 * 
	 * @return
	 */
	public String initTwoDiseaseThreeMasterApply() {
		try {
			loadCodeSelectData();
			return "initTwoDiseaseThreeMasterApply";
		} catch (Exception e) {
			logger.error("初始化加载两病三师申请页面信息失败！错误信息：" + ToolUtil.getExceptionInfo(e));
			saveError(e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 加载控件值
	 */
	@SuppressWarnings("rawtypes")
	void loadCodeSelectData() {
		Map bka019List = new LinkedHashMap();
		Map bka503List = new LinkedHashMap();
		loadBka019List(bka019List);
		setAttribute("bka019List", bka019List);
		setAttribute("bka503List", bka503List);
	}

	/**
	 * 加载科室
	 * 
	 * @param bka019List
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	void loadBka019List(Map bka019List) {
		HospApiService hospApiService = hygeiaServiceManager.getBeanByClass(HospApiService.class,
				BizHelper.getAkb020());
		HospInfoDTO hospInfoDto = new HospInfoDTO();
		hospInfoDto.setAkb020(BizHelper.getAkb020());
		hospInfoDto.setAae016("1");
		hospInfoDto.setAae100("1");
		List<HospInfoDTO> deptRows = hospApiService.queryHospDept(BizHelper.getAkb020(), hospInfoDto);
		if (deptRows != null) {
			for (HospInfoDTO deptRow : deptRows) {
				bka019List.put(deptRow.getBkc156(), deptRow.getBkc157());
			}
		}
	}

	/**
	 * 加载医保医师
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String loadBka503List() {
		try {
			Map bka503List = new HashMap();
			String bkc156 = getParameter("bkc156");
			if (StringUtils.isNotBlank(bkc156)) {
				HospApiService hospApiService = hygeiaServiceManager.getBeanByClass(HospApiService.class,
						BizHelper.getAkb020());
				HospInfoDTO hospInfoDto = new HospInfoDTO();
				hospInfoDto.setAkb020(BizHelper.getAkb020());
				hospInfoDto.setAae016("1");
				hospInfoDto.setAae100("1");
				hospInfoDto.setBkc156(bkc156);
				List<HospInfoDTO> doctorRows = hospApiService.queryHospDoctor(BizHelper.getAkb020(), hospInfoDto);
				if (doctorRows != null) {
					for (HospInfoDTO doctorRow : doctorRows) {
						// bka503List.put(doctorRow.getBkc274()+"|"+doctorRow.getBkc269(),
						// doctorRow.getBkc275());
						bka503List.put(doctorRow.getBkc274(), doctorRow.getBkc275());
					}
				}
			}
			setJSONReturn(bka503List);
		} catch (Exception e) {
			logger.error("加载医保医生信息错误：" + ToolUtil.getExceptionInfo(e));
			saveJSONError(e.getMessage());
		}
		return NONE;
	}

	/**
	 * 自费补偿申请获取人员信息 校验能否申请
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getPersonByPaysCompensation() {
		try {
			if (mediSpecDto == null) {
				mediSpecDto = new MediSpecDTO();
			}
			TeamVsSelfPayApplyService service = hygeiaServiceManager.getBeanByClass(TeamVsSelfPayApplyService.class,
					BizHelper.getAkb020());
			mediSpecDto.setAkb020(BizHelper.getAkb020());
			mediSpecDto.setAkb021(BizHelper.getAkb021());
			mediSpecDto.setAaa027(BizHelper.getAaa027());
			Map mInfo = service.getPersonByPaysCompensation(mediSpecDto);

			CodeTableSwitch codeService = new CodeTableSwitchImpl();
			Map mm = codeService.loadPersonInfo(mInfo, BizHelper.getAaa027());
			if (mInfo.isEmpty()) {
				saveJSONMessage("没有对应的申请信息!");
			} else {
				setJSONReturn(mm);
			}
		} catch (Exception e) {
			saveJSONError(e);
		}
		return NONE;
	}

	/**
	 * 保存自费补偿申请
	 * 
	 * @return
	 */
	public String savePaysCompensation() {
		try {
			if (mediSpecDto == null) {
				mediSpecDto = new MediSpecDTO();
			}
			TeamVsSelfPayApplyService service = hygeiaServiceManager.getBeanByClass(TeamVsSelfPayApplyService.class,
					BizHelper.getAkb020());
			mediSpecDto.setAkb020(BizHelper.getAkb020());
			mediSpecDto.setAkb021(BizHelper.getAkb021());
			mediSpecDto.setAaa027(BizHelper.getAaa027());
			int iReturn = service.savePaysCompensation(mediSpecDto);
			if (iReturn > 0) {
				setJSONReturn("保存成功!");
			} else {
				saveJSONMessage("保存失败!");
			}
		} catch (Exception e) {
			saveJSONError(e);
		}
		return NONE;
	}

	/**
	 * 查询自费补偿申请信息
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	public String queryPaysCompensationList() {
		try {
			if (mediSpecDto == null) {
				mediSpecDto = new MediSpecDTO();
			}
			TeamVsSelfPayApplyService service = hygeiaServiceManager.getBeanByClass(TeamVsSelfPayApplyService.class,
					BizHelper.getAkb020());
			mediSpecDto.setAkb020(BizHelper.getAkb020());
			mediSpecDto.setAkb021(BizHelper.getAkb021());
			mediSpecDto.setAaa027(BizHelper.getAaa027());
			List mInfo = service.queryPaysCompensationList(mediSpecDto);

			CodeTableSwitch codeService = new CodeTableSwitchImpl();
			List retList = codeService.loadSelfPay(mInfo, BizHelper.getAaa027());
			if (mInfo.isEmpty()) {
				saveJSONMessage("没有对应的申请信息!");
			} else {
				setJSONReturn(mInfo);
			}
		} catch (Exception e) {
			saveJSONError(e);
		}
		return NONE;
	}

	/**
	 * 删除自费补偿申请
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String delPaysCompensation() {

		try {
			int i = 0;
			String akb020 = BizHelper.getAkb020();
			TeamVsSelfPayApplyService service = hygeiaServiceManager.getBeanByClass(TeamVsSelfPayApplyService.class,
					akb020);

			String data = getParameter("data");
			if (UtilFunc.hasText(data)) {
				i = service.delPaysCompensation(akb020, JsonHelper.toList(data));
			}

			Map map = new HashMap();
			map.put("data", data);

			if (i > 0) {
				setJSONReturn("删除申请信息成功！", map);
			} else {
				setJSONReturn("删除申请信息失败！", map);
			}

		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 修改自费补偿申请
	 * 
	 * @return
	 */
	public String modifyPaysCompensation() {
		return NONE;
	}

	public String getDetailByAaz267() {
		try {
			if (mediSpecDto == null) {
				mediSpecDto = new MediSpecDTO();
			}
			TeamVsSelfPayApplyService service = hygeiaServiceManager.getBeanByClass(TeamVsSelfPayApplyService.class,
					BizHelper.getAkb020());
			mediSpecDto.setAkb020(BizHelper.getAkb020());
			mediSpecDto = service.getDetailByAaz267(mediSpecDto);

		} catch (Exception e) {
			saveJSONError(e);
		}
		return "paysDetail";
	}

	/**
	 * 
	 * 自费补偿疾病选择
	 * 
	 * @return
	 */
	public String chooseSelfPayDisease() {
		try {
			if (this.isPostRequest()) {
				try {
					PagerHelper.initPagination(this.getRequest());
					this.getKa16dto().setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
					this.getKa16dto().setPageSize(PagerHelper.getPaginationObj().getPageSize());
					this.getKa16dto().setAaa027(BizHelper.getAaa027());
					String akb020 = BizHelper.getAkb020();
					HospCataApiService hospCataService = hygeiaServiceManager.getBeanByClass(HospCataApiService.class,
							akb020);

					ListResult listResult = hospCataService.querySelfPayDisease(akb020, this.getKa16dto());

					PagerHelper.getPaginationObj().setCount(listResult.getCount());
					DataGridHelper.render(this.getRequest(), this.getResponse(),
							PagerHelper.getPaginatedList(listResult.getList()));
				} catch (Exception e) {
					logger.error(ToolUtil.getExceptionInfo(e));
					saveJSONError(e.getMessage());
				}
				return NONE;
			} else {
				return "chooseSelfPayDisease";
			}
		} catch (Exception e) {
			logger.error(ToolUtil.getExceptionInfo(e));
			saveJSONError(e.getMessage());
			return ERROR;
		}
	}

	public MediSpecDTO getMediSpecDto() {
		return mediSpecDto;
	}

	public void setMediSpecDto(MediSpecDTO mediSpecDto) {
		this.mediSpecDto = mediSpecDto;
	}

	public KA16DTO getKa16dto() {
		return ka16dto;
	}

	public void setKa16dto(KA16DTO ka16dto) {
		this.ka16dto = ka16dto;
	}

}
