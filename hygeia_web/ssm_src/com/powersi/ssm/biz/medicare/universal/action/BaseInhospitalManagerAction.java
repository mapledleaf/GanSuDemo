package com.powersi.ssm.biz.medicare.universal.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.powersi.biz.medicare.comm.pojo.KA06DTO;
import com.powersi.biz.medicare.iccard.service.CheckCardService;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.pojo.SearchInHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.bean.BeanService;
import com.powersi.biz.medicare.inhospital.service.date.DateService;
import com.powersi.biz.medicare.inhospital.service.util.CommunalService;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.CodetableMapping;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.log.service.ErrLogSnService;
import com.powersi.log.service.ErrLogSnService.ProjectType;
import com.powersi.pcloud.medicare.comm.utils.MedicareCommUtils;
import com.powersi.ssm.biz.medicare.common.service.QueryStringService;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.ssm.biz.medicare.inhospital.service.InhospitalManagerService;
import com.powersi.ssm.biz.medicare.outland.service.OutBizService;

/**
 * 住院管理基准Action
 * 
 * @author 刘勇
 *
 */
public class BaseInhospitalManagerAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private InHospitalDTO inHospitalDTO = null;
	private SearchInHospitalDTO searchInHospitalDTO = null;
	private KA06DTO ka06dto = null;
	private String searchType = null;// py首拼码wb五笔码name名称code编码(queryType)
	private String searchTerm = null;// (queryValue)
	private String ake007 = null;
	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;
	@Autowired
	private BeanService beanService;
	@Autowired
	private CommunalService communalService;
	@Autowired
	private InhospitalManagerService inhospitalManagerService;
	@Autowired
	private QueryStringService queryStringService;
	@Autowired
	private DateService dateService;
	@Autowired
	private OutBizService outBizService;
	@Autowired
	@Qualifier("errLogSnService")
	private ErrLogSnService errLogSnService;

	/**
	 * 场景阶段（1：业务开始 2：业务结算 3：业务结束）
	 */
	public static final String bka438_1 = "1";

	/**
	 * 场景阶段（1：业务开始 2：业务结算 3：业务结束）
	 */
	public static final String bka438_2 = "2";

	/**
	 * 场景阶段（1：业务开始 2：业务结算 3：业务结束）
	 */
	public static final String bka438_3 = "3";

	/**
	 * 其他
	 */
	public static final String amc029_9 = "9";

	/**
	 * 生育
	 */
	public static final String amc050_1 = "1";

	/**
	 * 计划生育手术
	 */
	public static final String amc050_2 = "2";

	/**
	 * 生育合并计生手术
	 */
	public static final String amc050_3 = "3";

	/**
	 * 广州
	 */
	public static final String aaa027_440100 = "440100";

	/**
	 * 珠海
	 */
	public static final String aaa027_440400 = "440400";

	/**
	 * 清远
	 */
	public static final String aaa027_441800 = "441800";

	/**
	 * 医疗康复住院
	 */
	public static final String bka006_421 = "421";

	/**
	 * 分娩期住院
	 */
	public static final String bka006_527 = "527";

	/**
	 * 职业康复住院
	 */
	public static final String bka006_422 = "422";

	/**
	 * 康复体检住院
	 */
	public static final String bka006_423 = "423";

	/**
	 * 辅助器具配置住院（综合）
	 */
	public static final String bka006_424 = "424";

	/**
	 * 辅助器具配置住院（康复）
	 */
	public static final String bka006_425 = "425";

	/**
	 * 工伤普通住院
	 */
	public static final String bka006_420 = "420";

	/**
	 * 急诊留观
	 */
	public static final String aka130_17 = "17";

	/**
	 * 门特
	 */
	public static final String aka130_16 = "16";

	/**
	 * 家庭病床
	 */
	public static final String aka130_14 = "14";

	/**
	 * 门慢
	 */
	public static final String aka130_13 = "13";

	/**
	 * 住院
	 */
	public static final String aka130_12 = "12";

	/**
	 * 购药
	 */
	public static final String aka130_10 = "10";
	/**
	 * 工伤住院
	 */
	public static final String aka130_42 = "42";

	/**
	 * 生育住院
	 */
	public static final String aka130_52 = "52";

	/**
	 * 省内异地住院
	 */
	public static final String aka130_72 = "72";

	/**
	 * 跨省异地就医住院
	 */
	public static final String aka130_82 = "82";

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
	 * 
	 * @return
	 */
	public String addErrSNInfo() {
		String errLogSn = this.getErrLogSnService().getErrSN(ProjectType.WEB);
		errLogSn = "错误号 " + errLogSn + " 信息： ";
		return errLogSn;
	}

	@Override
	public void saveJSONError(String message) {
		if (StringUtils.isNotBlank(message)) {
			message = MedicareCommUtils.thinErrorMessage(message);
		}
		this.saveJSONError(message, null);
	}

	@Override
	public void saveError(String message) {
		if (StringUtils.isNotBlank(message)) {
			message = MedicareCommUtils.thinErrorMessage(message);
		}
		this.saveError(message, null);
	}

	/**
	 * 
	 */
	public void validateaaz217() {
		if (StringUtils.isNotBlank(this.getInHospitalDTO().getAaz217())) {
			if (!this.getQueryStringService().isValidatedaaz217(this.getInHospitalDTO().getAaz217())) {
				throw new HygeiaException("录入的就医登记号无效(" + this.getInHospitalDTO().getAaz217() + ")");
			}
		}
	}

	/**
	 * 持卡就诊
	 */
	public void checkReadCard() {
		CheckCardService checkCardService = this.getHygeiaServiceManager().getBeanByClass(CheckCardService.class,
				this.getInHospitalDTO().getAkb020());
		checkCardService.checkReadCard(this.getInHospitalDTO());
	}

	/**
	 * 持卡结算
	 */
	public void checkDoDebit() {
		CheckCardService checkCardService = this.getHygeiaServiceManager().getBeanByClass(CheckCardService.class,
				this.getInHospitalDTO().getAkb020());
		checkCardService.checkDoDebit(this.getInHospitalDTO());
	}

	public void loadHidDataAndItemName() {
		if (StringUtils.isBlank(this.getInHospitalDTO().getKcd1id_hid())
				&& StringUtils.isNotBlank(this.getInHospitalDTO().getKcd1id())) {
			this.getInHospitalDTO().setKcd1id_hid(this.getInHospitalDTO().getKcd1id());
		}
		if (StringUtils.isBlank(this.getInHospitalDTO().getAaz217_hid())
				&& StringUtils.isNotBlank(this.getInHospitalDTO().getAaz217())) {
			this.getInHospitalDTO().setAaz217_hid(this.getInHospitalDTO().getAaz217());
		}
		if (StringUtils.isBlank(this.getInHospitalDTO().getKcd1id())
				&& StringUtils.isNotBlank(this.getInHospitalDTO().getKcd1id_hid())) {
			this.getInHospitalDTO().setKcd1id(this.getInHospitalDTO().getKcd1id_hid());
		}
		if (StringUtils.isBlank(this.getInHospitalDTO().getAaz217())
				&& StringUtils.isNotBlank(this.getInHospitalDTO().getAaz217_hid())) {
			this.getInHospitalDTO().setAaz217(this.getInHospitalDTO().getAaz217_hid());
		}
		if (StringUtils.isNotBlank(this.getInHospitalDTO().getAae140())) {
			this.getInHospitalDTO().setAae140_name(CodetableMapping.getDisplayValue("aae140",
					this.getInHospitalDTO().getAae140(), this.getInHospitalDTO().getAae140()));
		}
		if (StringUtils.isNotBlank(this.getInHospitalDTO().getBka006())) {
			this.getInHospitalDTO()
					.setBka006_name(CodetableMapping.getDisplayValue("bka006$" + this.getInHospitalDTO().getAaa027(),
							this.getInHospitalDTO().getBka006(), this.getInHospitalDTO().getBka006()));
		}
		if (StringUtils.isNotBlank(this.getInHospitalDTO().getAac004())) {
			this.getInHospitalDTO().setAac004_name(CodetableMapping.getDisplayValue("aac004",
					this.getInHospitalDTO().getAac004(), this.getInHospitalDTO().getAac004()));
		}
		if (StringUtils.isNotBlank(this.getInHospitalDTO().getBka035())) {
			this.getInHospitalDTO().setBka035_name(CodetableMapping.getDisplayValue("bka035",
					this.getInHospitalDTO().getBka035(), this.getInHospitalDTO().getBka035()));
		}
		if (StringUtils.isNotBlank(this.getInHospitalDTO().getBac001())) {
			this.getInHospitalDTO().setBac001_name(CodetableMapping.getDisplayValue("bac001",
					this.getInHospitalDTO().getBac001(), this.getInHospitalDTO().getBac001()));
		}
		if (StringUtils.isNotBlank(this.getInHospitalDTO().getBka066())) {
			this.getInHospitalDTO().setBka066(CodetableMapping.getDisplayValue("bka066",
					this.getInHospitalDTO().getBka066(), this.getInHospitalDTO().getBka066()));
		}
		if (StringUtils.isNotBlank(this.getInHospitalDTO().getAac013())) {
			this.getInHospitalDTO().setAac013_name(CodetableMapping.getDisplayValue("aac013",
					this.getInHospitalDTO().getAac013(), this.getInHospitalDTO().getAac013()));
		}
		if (StringUtils.isNotBlank(this.getInHospitalDTO().getBka888())) {
			this.getInHospitalDTO().setBka888_name(CodetableMapping.getDisplayValue("bka888",
					this.getInHospitalDTO().getBka888(), this.getInHospitalDTO().getBka888()));
		}
		if (StringUtils.isNotBlank(this.getInHospitalDTO().getBke956())) {
			this.getInHospitalDTO().setBke956_name(CodetableMapping.getDisplayValue("bke956",
					this.getInHospitalDTO().getBke956(), this.getInHospitalDTO().getBke956()));
		}
		if (StringUtils.isNotBlank(this.getInHospitalDTO().getBac032())) {
			this.getInHospitalDTO().setBac032_name(CodetableMapping.getDisplayValue("bac032",
					this.getInHospitalDTO().getBac032(), this.getInHospitalDTO().getBac032()));
		}
		if (StringUtils.isNotBlank(this.getInHospitalDTO().getBmc077())) {
			String bmc077_name = "1".equals(this.getInHospitalDTO().getBmc077()) ? "是" : "否";
			this.getInHospitalDTO().setBmc077_name(bmc077_name);
		}
		if (StringUtils.isNotBlank(this.getInHospitalDTO().getAab301())
				&& StringUtils.isBlank(this.getInHospitalDTO().getAab301_name())) {
			this.getInHospitalDTO()
					.setAab301_name(this.getOutBizService().getBkd324(this.getInHospitalDTO().getAab301()));
		}
		if (StringUtils.isNotBlank(this.getInHospitalDTO().getAab301())
				&& StringUtils.isBlank(this.getInHospitalDTO().getBaa027())) {
			this.getInHospitalDTO().setBaa027(this.getInHospitalDTO().getAab301());
		}
		if (StringUtils.isNotBlank(this.getInHospitalDTO().getBka006())) {
			this.getInHospitalDTO().setBka006_name(CodetableMapping.getDisplayValue("bka006",
					this.getInHospitalDTO().getBka006(), this.getInHospitalDTO().getBka006()));
		}
		if (StringUtils.isNotBlank(this.getInHospitalDTO().getBaa027())) {
			this.getInHospitalDTO().setBaa027_name(CodetableMapping.getDisplayValue("aaa027",
					this.getInHospitalDTO().getBaa027(), this.getInHospitalDTO().getBaa027()));
		}
	}

	/**
	 * 初始化住院流程后台参数
	 */
	public void initCtrlInHospitalDTO() {
		this.initCtrlInHospitalDTO(null);
	}

	/**
	 * 
	 * @param aka130Code
	 *            业务类别编码
	 */
	public void initCtrlInHospitalDTO(String aka130Code) {
		this.getInHospitalDTO().setAaa027(BizHelper.getAaa027());
		this.getInHospitalDTO().setAaa027_name(BizHelper.getAaa027Name());
		this.getInHospitalDTO().setAkb020(BizHelper.getAkb020());
		this.getInHospitalDTO().setAkb021(BizHelper.getAkb021());
		this.getInHospitalDTO().setAae011(BizHelper.getLoginUser());
		this.getInHospitalDTO().setBka015(BizHelper.getUserName());
		this.getInHospitalDTO().setAae036(new Date());
		this.getQueryStringService().convertQueryString(this.getInHospitalDTO());
		if (StringUtils.isNotBlank(this.getInHospitalDTO().getBke549())) {
			this.loadTACByDoDebit();
		}
		if (StringUtils.isBlank(aka130Code)) {
			return;
		}
		this.getInHospitalDTO().setAka130(aka130Code);
		this.getInHospitalDTO().setAka130_name(CodetableMapping.getDisplayValue("aka130", aka130Code, aka130Code));
	}

	/**
	 * 
	 */
	private void loadTACByDoDebit() {
		CheckCardService checkCardService = this.getHygeiaServiceManager().getBeanByClass(CheckCardService.class,
				this.getInHospitalDTO().getAkb020());
		InHospitalDTO inHospitalDTOReturn = checkCardService.loadTACByDoDebit(this.getInHospitalDTO());
		this.getBeanService().copyProperties(inHospitalDTOReturn, this.getInHospitalDTO(), true);
	}

	/**
	 * 
	 * @return
	 */
	public InHospitalDTO getInHospitalDTO() {
		if (inHospitalDTO == null) {
			inHospitalDTO = new InHospitalDTO();
		}
		return inHospitalDTO;
	}

	public void setInHospitalDTO(InHospitalDTO inHospitalDTO) {
		this.inHospitalDTO = inHospitalDTO;
	}

	public SearchInHospitalDTO getSearchInHospitalDTO() {
		return searchInHospitalDTO;
	}

	public void setSearchInHospitalDTO(SearchInHospitalDTO searchInHospitalDTO) {
		this.searchInHospitalDTO = searchInHospitalDTO;
	}

	public HygeiaServiceManager getHygeiaServiceManager() {
		return hygeiaServiceManager;
	}

	public void setHygeiaServiceManager(HygeiaServiceManager hygeiaServiceManager) {
		this.hygeiaServiceManager = hygeiaServiceManager;
	}

	public BeanService getBeanService() {
		return beanService;
	}

	public void setBeanService(BeanService beanService) {
		this.beanService = beanService;
	}

	public CommunalService getCommunalService() {
		return communalService;
	}

	public void setCommunalService(CommunalService communalService) {
		this.communalService = communalService;
	}

	public InhospitalManagerService getInhospitalManagerService() {
		return inhospitalManagerService;
	}

	public void setInhospitalManagerService(InhospitalManagerService inhospitalManagerService) {
		this.inhospitalManagerService = inhospitalManagerService;
	}

	public QueryStringService getQueryStringService() {
		return queryStringService;
	}

	public void setQueryStringService(QueryStringService queryStringService) {
		this.queryStringService = queryStringService;
	}

	public DateService getDateService() {
		return dateService;
	}

	public void setDateService(DateService dateService) {
		this.dateService = dateService;
	}

	public OutBizService getOutBizService() {
		return outBizService;
	}

	public void setOutBizService(OutBizService outBizService) {
		this.outBizService = outBizService;
	}

	public KA06DTO getKa06dto() {
		return ka06dto;
	}

	public void setKa06dto(KA06DTO ka06dto) {
		this.ka06dto = ka06dto;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchTerm() {
		if (this.searchTerm != null) {
			this.searchTerm = this.searchTerm.trim();
		}
		return this.searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
		if (this.searchTerm != null) {
			this.searchTerm = this.searchTerm.trim();
		}
	}

	public String getAke007() {
		return ake007;
	}

	public void setAke007(String ake007) {
		this.ake007 = ake007;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ErrLogSnService getErrLogSnService() {
		return errLogSnService;
	}

	public void setErrLogSnService(ErrLogSnService errLogSnService) {
		this.errLogSnService = errLogSnService;
	}

}
