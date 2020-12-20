package com.powersi.ssm.biz.medicare.universal.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.diagnose.pojo.KCD2_his;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh120002Service;
import com.powersi.biz.medicare.inhospital.service.bean.BeanService;
import com.powersi.biz.medicare.universal.pojo.KA20_HisDTO;
import com.powersi.biz.medicare.universal.pojo.KA40_HISDTO;
import com.powersi.biz.medicare.universal.pojo.KCD1_Hosp_HisDTO;
import com.powersi.biz.medicare.universal.pojo.KCD1_Mz_HisDTO;
import com.powersi.biz.medicare.universal.pojo.KCD2_Fee_HisDTO;
import com.powersi.biz.medicare.universal.pojo.KCD2_His;
import com.powersi.biz.medicare.universal.pojo.KCD2_HospFee_HisDTO;
import com.powersi.biz.medicare.universal.service.api.DiagnoseQueryApiService;
import com.powersi.biz.medicare.universal.service.api.FeeCheckOutApiService;
import com.powersi.comm.service.memory.MemoryDB;
import com.powersi.comm.utils.TimeUtils;
import com.powersi.comm.utils.ToolUtil;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.PaginationHelper;
import com.powersi.hygeia.framework.util.SysFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.hygeia.web.util.JsonHelper;
import com.powersi.log.service.ErrLogSnService;
import com.powersi.log.service.ErrLogSnService.ProjectType;
import com.powersi.ssm.biz.medicare.api.service.mcce.MCCEbizh120002ServiceImpl;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.PagerHelper;

import net.sf.json.JSONArray;

@Action(value = "DiagnoseQueryAction_HIS", results = {
		@Result(name = "queryHospFee", location = "/pages/biz/medicare/diagnose_his/chargeBatchUpload.jsp"),
		@Result(name = "catalog_his", location = "/pages/biz/medicare/diagnose_his/catalog_his.jsp"),
		@Result(name = "loadUploadData", location = "/pages/biz/medicare/diagnose_his/chargeUpload_dialog.jsp"),
		@Result(name = "mZQueryPersonInfo", location = "/pages/biz/medicare/diagnose_his/selectVisPerson.jsp"),
		@Result(name = "buymedicareInfo", location = "/pages/biz/medicare/diagnose_his/buymedicareInfo.jsp") })
public class DiagnoseQueryAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	HygeiaServiceManager hygeiaServiceManager;
	@Autowired
	MemoryDB memoryDB;
	@Autowired
	@Qualifier("errLogSnService")
	private ErrLogSnService errLogSnService;

	private String errNum = "";// 错误号
	
	@Autowired
	private BeanService beanService;

	public BeanService getBeanService() {
		return beanService;
	}

	public void setBeanService(BeanService beanService) {
		this.beanService = beanService;
	}

	/**
	 * 缓存中存储指令列表List的key
	 */
	private static final String HC_TRANS_ORDER_KEY = "HC_TRANS_";

	public Object addManualTransOrder = new Object();

	
	private KCD1_Mz_HisDTO kcd1_Mz_HisDTO;

	private KCD1_Hosp_HisDTO kcd1_Hosp_HisDTO;
	
	public KCD1_Mz_HisDTO getKcd1_Mz_HisDTO() {
		return kcd1_Mz_HisDTO;
	}

	public void setKcd1_Mz_HisDTO(KCD1_Mz_HisDTO kcd1_Mz_HisDTO) {
		this.kcd1_Mz_HisDTO = kcd1_Mz_HisDTO;
	}

	public KCD1_Hosp_HisDTO getKcd1_Hosp_HisDTO() {
		return kcd1_Hosp_HisDTO;
	}

	public void setKcd1_Hosp_HisDTO(KCD1_Hosp_HisDTO kcd1_Hosp_HisDTO) {
		this.kcd1_Hosp_HisDTO = kcd1_Hosp_HisDTO;
	}
	
	/**
	 * 购药收费，查询流水号详情通过时间或者流水号
	 * 
	 * @return
	 */
	public String queryNumberDetailsByTimeOrNumber() {
		try {
			// 获取页面传来的数据
			String akc190 = this.getParameter("akc190");
			String  aae031 = null;// 结束时间
			String aae030 = null;// 开始时间
			String aac001 = URLDecoder.decode(this.getParameter("aac001"), "UTF-8");
			if (StringUtils.isNotBlank(this.getParameter("aae036"))) {
				aae031 = this.getParameter("aae036");
			}
			if (StringUtils.isNotBlank(this.getParameter("aae030"))) {
				aae030 = this.getParameter("aae030");
			}
			String akb020 = BizHelper.getAkb020();
			DiagnoseQueryApiService diagnoseQueryService_HIS = (DiagnoseQueryApiService) hygeiaServiceManager
					.getBean("diagnoseQueryApiService", akb020);
			// List<Kcd2_Fee_HisDTO> HisFymxTempMzDTOList =
			// diagnoseQueryService_HIS.queryNumberDetails(bkm902, aae036, akb020);
			Map map = new HashMap();
			map.put("akc190", akc190);
			map.put("aae031", aae031);
			map.put("aae030", aae030);
			map.put("akb020", akb020);
			map.put("aac001", aac001);
			map.put("bka044", this.getParameter("bka044"));
			List<KCD2_Fee_HisDTO> HisFymxTempMzDTOList = diagnoseQueryService_HIS.queryOutpatientPersonInfo(map);
			setJSONReturn("查询成功！", HisFymxTempMzDTOList);
		} catch (Exception e) {
			logger.error("查询药品详情失败！错误信息：" + ToolUtil.getExceptionInfo(e));
			errNum = errLogSnService.getErrSN(ProjectType.WEB);
			saveJSONError("查询药品详情失败！\r\n" + "错误号： " + errNum + " " + (e.getMessage() == null ? "" : e.getMessage()));
		}
		return NONE;
	}
	
	/**
	 * 购药收费，根据流水号查询详细药品数据
	 * 
	 * @return
	 */
	public String queryDiagnoseByNumber() {
		try {
			String akc190 = URLDecoder.decode(getParameter("akc190"), "UTF-8");
			// JSONArray jsonArray = JSONArray.fromObject(jsonFee);
			DiagnoseQueryApiService diagnoseQueryApiService_HIS = (DiagnoseQueryApiService) hygeiaServiceManager
					.getBean("diagnoseQueryApiService", BizHelper.getAkb020());
			List<KCD2_Fee_HisDTO> Kcd2FeeHisDTOList = diagnoseQueryApiService_HIS.queryDiagnoseDetails(akc190,BizHelper.getAkb020());
			setJSONReturn("查询成功！", Kcd2FeeHisDTOList);
		} catch (Exception e) {
			String errNum = errLogSnService.getErrSN(ProjectType.WEB);
			logger.error("查询流水号详情失败！错误信息：" + ToolUtil.getExceptionInfo(e));
			saveJSONError("查询流水号详情失败！\r\n" + "错误号： " + errNum + " " + (e.getMessage() == null ? "" : e.getMessage()));
		}
		return NONE;
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * 校验费用结转
	 * 
	 * @return
	 */
	public String checkFee() {

		// 调用hygeia_esb方法进行费用校验
		FeeCheckOutApiService feeCheckOutService_HIS = (FeeCheckOutApiService) hygeiaServiceManager
				.getBean("feeCheckOutApiService", BizHelper.getAkb020());
		try {
			JSONArray jsonArray = JSONArray.fromObject(getParameter("datas"));
			List<KCD2_Fee_HisDTO> drugList = JSONArray.toList(jsonArray, KCD2_Fee_HisDTO.class);
			String aae140 = this.getParameter("aae140");
			List<KCD2_Fee_HisDTO> feeList = feeCheckOutService_HIS.drugFeeCheckOut(drugList, aae140);
			// 结转费用校验通过
			if (feeList.size() == drugList.size()) {
				setJSONReturn("校验完毕", feeList);
			} else {
				// 存在未通过校验的数据
				Iterator<KCD2_Fee_HisDTO> kit = drugList.iterator();
				while (kit.hasNext()) {
					KCD2_Fee_HisDTO fee = kit.next();
					int size = feeList.size();
					for (int i = 0; i < size; i++) {
						if (feeList.get(i).getAaz213() == fee.getAaz213()) {
							kit.remove();
							break;
						}
					}
				}
				setJSONReturn("-1", drugList);
			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NONE;
	}

	/**
	 * 门诊收费，查询医院费用
	 * 
	 * @return
	 */
	public String queryHospFee() {
		try {
			String akb020 = BizHelper.getAkb020();
			DiagnoseQueryApiService diagnoseQueryApiService = (DiagnoseQueryApiService) hygeiaServiceManager
					.getBean("diagnoseQueryApiService", BizHelper.getAkb020());
			Map map = new HashMap();
			map.put("akb020", akb020);
			List<KCD2_HospFee_HisDTO> Kcd2_HospFee_HisDTOList = diagnoseQueryApiService.queryHospFeeInfo(map);
			// this.setAttribute("加载完毕",Kcd2_HospFee_HisDTOList);
			setJSONReturn("查询完毕", Kcd2_HospFee_HisDTOList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NONE;
	}

	/**
	 * 费用上传
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String uploadAndCheckHospFee() {
		// 获取住院号
		String akc190 = this.getParameter("akc190");
		String akb020 = BizHelper.getAkb020();
		//by tangliang 20180627  在HYGEIA_AUTO_HOSPFEE_+akc190之间添加akb020避免不同医疗机构之间在redis中重复
		try {
			if (memoryDB.plusLong("HYGEIA_AUTO_HOSPFEE_"+akb020+akc190, 1*60, 1) != 1) {
				setJSONReturn("-4", null);
				return NONE;
			}
		} catch (IOException e) {
			logger.error("费用上次失败："+e.getMessage());
		}
		
		try {
			FeeCheckOutApiService feeCheckOutService_HIS = (FeeCheckOutApiService) hygeiaServiceManager
					.getBean("feeCheckOutApiService", BizHelper.getAkb020());
			DiagnoseQueryApiService diagnoseQueryApiService = (DiagnoseQueryApiService) hygeiaServiceManager
					.getBean("diagnoseQueryApiService", BizHelper.getAkb020());
			// 获取住院费用信息
			List<KCD2_HospFee_HisDTO> hospfeeList = new ArrayList<KCD2_HospFee_HisDTO>();
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("akc190", akc190);
			param.put("akb020", BizHelper.getAkb020());
			// 获取住院登记信息
			List<KCD1_Hosp_HisDTO> kcd1hisList = diagnoseQueryApiService.queryHospHisInfo(param);
			KCD1_Hosp_HisDTO hishospdto = new KCD1_Hosp_HisDTO();
			if (kcd1hisList.size() > 0) {
				hishospdto = kcd1hisList.get(0);
			}
			// 获取已经上传了的费用数量,记录aaz213,使其在一个批次内不重复
			List<KCD2_HospFee_HisDTO> feeListSize = diagnoseQueryApiService.queryInhospPersonFee(param);
			long aaz213 = 1L;
			if (feeListSize != null) {
				aaz213 += feeListSize.size();
			}
			// 如果是已上传的数据则不再上传
			if ("1".equals(this.getParameter("value"))) {
				setJSONReturn("-5", null);
				return NONE;
			} else {
				param.put("BZ1", this.getParameter("value"));
			}
			hospfeeList = diagnoseQueryApiService.queryInhospPersonFee(param);
			
			
			String strlength = hishospdto.getAaz217();
			String aaz218yd = strlength.substring(0,2);
			// 剔除以上传(传输标准为1)的费用
			if (!"yd".equals(aaz218yd)) {
			  hospfeeList = deleteUploadedFee(hospfeeList);
			}
			// 如果费用为null则数据异常
			if (hospfeeList == null || hospfeeList.size() == 0) {
				// 该条登记信息无费用明细，异常
				setJSONReturn("-3", hospfeeList);
				return NONE;
			}
			// 调用接口校验费用
			List<KCD2_HospFee_HisDTO> uploadList = new ArrayList<KCD2_HospFee_HisDTO>();
			try {
				uploadList = feeCheckOutService_HIS.uploadAndCheckFee(hospfeeList);
			} catch (Exception ex) {
				setJSONReturn("-6", ex.getMessage());
				return NONE;
			}
			String expMessage = "";
			
			List<InHospitalDTO> inHospitalDTORows = new ArrayList<InHospitalDTO>();//费用信息
			InHospitalDTO inHospitalDTO = new InHospitalDTO();
			//费用数据参数封装
			loadInhospitalData(uploadList,hishospdto,aaz213,inHospitalDTORows,inHospitalDTO);
			
			if(StringUtils.isNotBlank(hishospdto.getAaz217())) {
				if("yd".equals(aaz218yd)) { //判断是否是省异地就医的
					//省异地费用上传需将费用新至省异地费用库 用于计算费用
					try {
						MCCEbizh120002Service mCCEbizh120002Service =hygeiaServiceManager.getBeanByClass(MCCEbizh120002ServiceImpl.class,  BizHelper.getAkb020());
						mCCEbizh120002Service.checkAndSaveFeeInfo(inHospitalDTO, inHospitalDTORows);
					}catch (Exception e) {
						this.saveJSONError(e.getMessage());
					}
				}
			}
			
			
			// 将校验通过的数据插入结算云表kcd2
			for (int i = 0; i < inHospitalDTORows.size();i++) {
				
			//	kcd2.setBka511(hisdto.getBke215());
				Map hosphisMap = new HashMap();
				hosphisMap.put("aaz213", uploadList.get(i).getAaz213());
				hosphisMap.put("akb020", uploadList.get(i).getAkb020());
				try {
					if (StringUtils.isNotBlank(inHospitalDTORows.get(i).getBz3())) {
						expMessage += inHospitalDTORows.get(i).getBz3() + "\n";
					} else {
						if (!"yd".equals(aaz218yd)) {
						   diagnoseQueryApiService.insertKcd2(inHospitalDTORows.get(i));
						}
						// 上传成功，修改该条费用标记
						hosphisMap.put("BZ1", "1");
						diagnoseQueryApiService.updataKcd2hospFeeBZ1(hosphisMap);
					}
				} catch (Exception ex) {
					logger.error("住院费用从临时表上传至结算云表出错，费用序号为：" + inHospitalDTORows.get(i).getAaz213() + "," + ToolUtil.getExceptionInfo(ex));
					errNum = errLogSnService.getErrSN(ProjectType.WEB);
					setJSONReturn("-6", "错误号： " + errNum + " " + ex.getMessage());
					// 记录上传失败
					hosphisMap.put("BZ1", "2");
					diagnoseQueryApiService.updataKcd2hospFeeBZ1(hosphisMap);
					return NONE;
				}
			}
			// 返回异常给页面
			if (StringUtils.isNotBlank(expMessage)) {
				setJSONReturn("-6", expMessage);
				return NONE;
			}
			// 判断校验数据是否与传入的数据一致
			if (uploadList.size() != hospfeeList.size()) {
				if (uploadList.size() == 0) {
					setJSONReturn("-2", hospfeeList);
					return NONE;
				}
				Iterator<KCD2_HospFee_HisDTO> ith = hospfeeList.iterator();
				while (ith.hasNext()) {
					KCD2_HospFee_HisDTO hisdto = ith.next();
					for (int i = 0; i < uploadList.size(); i++) {
						if (uploadList.get(i).getAaz213() == (hisdto.getAaz213())) {
							ith.remove();
						}
					}
				}
				setJSONReturn("-2", hospfeeList);
				return NONE;
			}
			setJSONReturn("0", null);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("费用上传是失败："+e.getMessage());
		}finally{
			//by tangliang 20180627  在HYGEIA_AUTO_HOSPFEE_+akc190之间添加akb020避免不同医疗机构之间在redis中重复
			memoryDB.delete("HYGEIA_AUTO_HOSPFEE_"+akb020+akc190);
		}
		return NONE;
	}
	
	private void loadInhospitalData(List<KCD2_HospFee_HisDTO> uploadList,KCD1_Hosp_HisDTO hishospdto,long aaz213,List<InHospitalDTO> inHospitalDTORows,InHospitalDTO inHospitalDTO) {
		inHospitalDTO = new InHospitalDTO();
		this.getBeanService().copyProperties(hishospdto, inHospitalDTO, true);//登记信息
		//inHospitalDTORows费用信息
		for(KCD2_HospFee_HisDTO dto:uploadList) {
			InHospitalDTO hos = new InHospitalDTO();
			KCD2_his kcd2 = new KCD2_his();
			UUID uuid = UUID.randomUUID();
			hos.setKcd2id(uuid.toString());
			hos.setKcd1id(hishospdto.getKcd1id());
			hos.setAkb020(dto.getAkb020());// 医院编码
			hos.setAaz217(hishospdto.getAaz217());// 就医登记号
			hos.setBka001((String.valueOf(1)));// 费用批次
			hos.setAaz213((String.valueOf(aaz213++)));// 费用序列号
			hos.setAaa027(BizHelper.getAaa027());//统筹区编码
			hos.setAaz231(dto.getAaz231());//社保三大目录ID
			hos.setAke001(dto.getAke001());//社保三大目录编码
			hos.setAke002(dto.getAke002());//社保三大目录名称
			hos.setAaz277(dto.getAaz277());//医疗机构三大目录ID
			hos.setAke005(dto.getAke005());//医疗机构三大目录编码
			hos.setAke006(dto.getAke006());//医疗机构三大目录名称
			hos.setAke105(dto.getAke105());//药监局药品编码
			hos.setAke003(dto.getAke003());//三大目录类别(码表 AKE003)
			if (StringUtils.isBlank(dto.getAka063())) {
				hos.setAka063("099");// 统计类别
			} else {
				hos.setAka063(dto.getAka063());// 统计类别
			}
			hos.setAka065(dto.getAka065());//收费项目等级(甲、乙、丙)
			hos.setBka511(dto.getBka511());//城职对应待遇值（自付比例支付类型）
			hos.setBka071(dto.getBka071());//医疗机构序列号
			hos.setAka070(dto.getAka070());//剂型
			hos.setAka067(dto.getAka067());//药品剂量单位
			hos.setAka074(dto.getAka074());//规格
			hos.setBka073(dto.getBka073());//厂家
			hos.setAkc226(dto.getAkc226());//数量
			hos.setBka040(dto.getBka040());//标准单价
			hos.setAae019(BigDecimal.valueOf(dto.getAae019()));//金额
			hos.setBkz103(dto.getBkz103());//限制用药标识
			hos.setBka067(dto.getBka067());//费用冻结标志
			hos.setBka062(dto.getBka062());//退费对应的费用序列号
			hos.setBka059(dto.getBka059());//退费对应的费用序列号
			hos.setBka069(dto.getBka069());//费用上传时间
			hos.setAke007(DateFunc.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));//费用发生时间
			hos.setBka063(BizHelper.getLoginUser());//录入人工号
			hos.setBka064(BizHelper.getUserName());//录入人名称
			hos.setBka065(DateFunc.dateToString(new Date(), "yyyy-MM-dd"));//录入时间
			hos.setBka070(dto.getBka070());//处方号
			hos.setBka074(dto.getBka074());//处方医师编号
			hos.setBka075(dto.getBka075());//处方医师名称
			hos.setBkh015(dto.getBkh015());//费用套餐id
			hos.setBka080(dto.getBka080());//优惠金
			hos.setBkm001(dto.getBkm001());//是否在岗医师标识：0，非在岗；1，在岗
			hos.setBkm002(dto.getBkm002());//超时标志，0未超时，1超时上传未申诉，2超时上传正在申诉，3超时上传申诉审核同意，4超时上传申诉审核不同意
			hos.setBka044("0");//传输标志
			hos.setBka971("0");//修改计数器,默认插入时为1，执行一次update需要加一
			hos.setBka972("0");//// 传输打包标志 0未打包 1已打包
			hos.setBka973(DateFunc.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));//数据最后修改时间 
			hos.setBka974(dto.getBka974());//最后传输批次编号
			inHospitalDTO.setHospInfoflag("1");
			inHospitalDTORows.add(hos);
		}
		
	}
	
	
	

	/**
	 * 删除已经上传的费用明细
	 * 
	 * @return
	 */
	private List<KCD2_HospFee_HisDTO> deleteUploadedFee(List<KCD2_HospFee_HisDTO> feeList) {
		Iterator<KCD2_HospFee_HisDTO> it = feeList.iterator();
		while (it.hasNext()) {
			KCD2_HospFee_HisDTO hisdto = it.next();
			if ("1".equals(hisdto.getBz1())) {
				it.remove();
			}
		}
		return feeList;
	}

	/**
	 * 查询单个人员信息以及人员信息产生的费用
	 * 
	 * @return
	 */
	public String queryOnePersonInfo() {
		try {
			DiagnoseQueryApiService diagnoseQueryApiService = (DiagnoseQueryApiService) hygeiaServiceManager
					.getBean("diagnoseQueryApiService", BizHelper.getAkb020());
			// 获取个人电脑号
			String aac001 = getParameter("aac001");
			Map map = new HashMap();
			map.put("aac001", aac001);
			map.put("akb020", BizHelper.getAkb020());
			map.put("bka039", "0");
			map.put("aaz218", "");
			List<KCD1_Hosp_HisDTO> personList = diagnoseQueryApiService.queryYunInHospPersonInfo(map);
			// 查询费用信息
			if (personList != null && personList.size() > 0) {
				KCD1_Hosp_HisDTO hospdto = personList.get(0);
				// 获取住院号
				String akc190 = hospdto.getAkc190();
				Map param = new HashMap();
				param.put("akc190", akc190);
				param.put("akb020", BizHelper.getAkb020());
				List<KCD2_HospFee_HisDTO> feeList = diagnoseQueryApiService.queryHospFeeInfo(param);
				// 查询费用信息显示在页面上
				this.setAttribute("feeList", feeList);
			}
			setJSONReturn("校验完毕", personList);
		} catch (IOException e) {
			logger.error("通用接口查询单个人员信息以及人员信息产生的费用异常：" + ToolUtil.getExceptionInfo(e));
			e.printStackTrace();
		}
		return NONE;
	}

	/**
	 * 住院人员信息查询 分页
	 * 
	 * @return
	 */
	public String queryInHospitalPersonInfo_page() {
		DiagnoseQueryApiService diagnoseQueryApiService = (DiagnoseQueryApiService) hygeiaServiceManager
				.getBean("diagnoseQueryApiService", BizHelper.getAkb020());
		kcd1_Hosp_HisDTO = kcd1_Hosp_HisDTO == null ? new KCD1_Hosp_HisDTO() : kcd1_Hosp_HisDTO;
		kcd1_Hosp_HisDTO.setAkb020(BizHelper.getAkb020());
		PagerHelper.initPagination(getRequest());
		kcd1_Hosp_HisDTO.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
		kcd1_Hosp_HisDTO.setPageSize(PagerHelper.getPaginationObj().getPageSize());
		try {
			ListResult hospList = diagnoseQueryApiService.queryInhospPersonInfo_page(kcd1_Hosp_HisDTO);
			PaginationHelper.getPaginationObj().setCount(hospList.getCount());
			DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(hospList.getList()));
		} catch (Exception ex) {
			logger.error("通用接口--业务查询住院人员信息出错：" + ToolUtil.getExceptionInfo(ex));
		}
		return NONE;
	}

	/**
	 * 查询住院人员费用
	 * 
	 * @return
	 */
	public String queryInHospitalPersonFee_page() {
		DiagnoseQueryApiService diagnoseQueryApiService = (DiagnoseQueryApiService) hygeiaServiceManager
				.getBean("diagnoseQueryApiService", BizHelper.getAkb020());
		KCD2_HospFee_HisDTO hosp = new KCD2_HospFee_HisDTO();
		hosp.setAkb020(BizHelper.getAkb020());
		hosp.setAkc190(kcd1_Hosp_HisDTO.getAkc190());
		PagerHelper.initPagination(getRequest());
		hosp.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
		hosp.setPageSize(PagerHelper.getPaginationObj().getPageSize());
		try {
			ListResult hospList = diagnoseQueryApiService.queryInhospPersonFee_page(hosp);
			PaginationHelper.getPaginationObj().setCount(hospList.getCount());
			DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(hospList.getList()));
		} catch (Exception ex) {
			logger.error("通用接口--业务查询住院人员费用出错：" + ToolUtil.getExceptionInfo(ex));
		}
		return NONE;

	}

	/**
	 * 查询门诊人员信息
	 * 
	 * @return
	 */
	public String queryOutpatientPersonInfo_page() {
		DiagnoseQueryApiService diagnoseQueryApiService = (DiagnoseQueryApiService) hygeiaServiceManager
				.getBean("diagnoseQueryApiService", BizHelper.getAkb020());
		KCD1_Mz_HisDTO mz = new KCD1_Mz_HisDTO();
		mz.setAkb020(BizHelper.getAkb020());
		PagerHelper.initPagination(getRequest());
		mz.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
		mz.setPageSize(PagerHelper.getPaginationObj().getPageSize());

		// 获取页面传回的表单数据
		//String bkm902 = kcd1_Mz_HisDTO.getBkm902();
		Date aae036 = kcd1_Mz_HisDTO.getAae036();
		String bka044 = kcd1_Mz_HisDTO.getBka044();
	//	mz.setBkm902(bkm902);
		mz.setAae036(aae036);
		mz.setBka044(bka044);
		try {
			ListResult hospList = diagnoseQueryApiService.queryOutpatientPersonInfo_page(mz);
			PaginationHelper.getPaginationObj().setCount(hospList.getCount());
			DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(hospList.getList()));
		} catch (Exception ex) {
			logger.error("通用接口--业务查询门诊人员信息出错：\n" + ToolUtil.getExceptionInfo(ex));
		}
		return NONE;
	}

	/**
	 * 查询门诊人员费用
	 * 
	 * @return
	 */
	public String queryOutpatientPersonFee_page() {
		DiagnoseQueryApiService diagnoseQueryApiService = (DiagnoseQueryApiService) hygeiaServiceManager
				.getBean("diagnoseQueryApiService", BizHelper.getAkb020());
		KCD2_Fee_HisDTO fee = new KCD2_Fee_HisDTO();
		fee.setAkb020(BizHelper.getAkb020());
	//	fee.setBkm902(kcd1_Mz_HisDTO.getBkm902());
		PagerHelper.initPagination(getRequest());
		fee.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
		fee.setPageSize(PagerHelper.getPaginationObj().getPageSize());
		try {
			ListResult hospList = diagnoseQueryApiService.queryOutpatientPersonFee_page(fee);
			PaginationHelper.getPaginationObj().setCount(hospList.getCount());
			DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(hospList.getList()));
		} catch (Exception ex) {
			logger.error("通用接口--业务查询门诊费用明细出错：" + ToolUtil.getExceptionInfo(ex));
		}
		return NONE;
	}

	/**
	 * 住院人员信息查询
	 * 
	 * @return
	 */
	public String queryInHospitalPersonInfo() {
		try {
			// 获取页面传回的表单数据
			String aac001 = this.getParameter("aac001");
			String aac002 = this.getParameter("aac002");
			String bka100 = this.getParameter("bka100");
			String bka044 = this.getParameter("bka044");
			String kcd1id = this.getParameter("kcd1hid");
			String akc190 = this.getParameter("akc190");
			String aac003 = URLDecoder.decode(this.getParameter("aac003"), "UTF-8");
			Date aae036 = null;
			if (StringUtils.isNotBlank(this.getParameter("aae036"))) {
				aae036 = TimeUtils.parseDate(this.getParameter("aae036"), "yyyy-MM-dd");
			}
			String bka019 = this.getParameter("bka019");
			String bka020 = this.getParameter("bka020");
			DiagnoseQueryApiService diagnoseQueryApiService = (DiagnoseQueryApiService) hygeiaServiceManager
					.getBean("diagnoseQueryApiService", BizHelper.getAkb020());
			// 调用查询方法查询人员信息
			Map param = new HashMap();
			param.put("akb020", BizHelper.getAkb020());
			param.put("aac001", aac001);
			param.put("aac002", aac002);
			param.put("bka100", bka100);
			param.put("aae036", aae036);
			param.put("bka019", bka019);
			param.put("bka020", bka020);
			param.put("kcd1id", kcd1id);
			param.put("akc190", akc190);
			param.put("aac003", aac003);
			param.put("bka044", bka044);
			List<KCD1_Hosp_HisDTO> kcd1HospHisList = diagnoseQueryApiService.queryInhospPersonInfo(param);
			// 将查询人员信息灌入
			setJSONReturn("校验完毕", kcd1HospHisList);
		} catch (IOException e) {
			logger.error("通用接口住院人员信息查询异常：" + ToolUtil.getExceptionInfo(e));
			e.printStackTrace();
		}
		return NONE;
	}

	/**
	 * 查询住院人员费用
	 * 
	 * @return
	 */
	public String queryInHospitalPersonFee() {
		try {
			// 根据人员信息查询发生的费用
			// 获取住院号
			String akc190 = this.getParameter("akc190");
			// 获取上传标记
			String BZ1 = this.getParameter("BZ1");
			// 根据住院号查询
			DiagnoseQueryApiService diagnoseQueryApiService = (DiagnoseQueryApiService) hygeiaServiceManager
					.getBean("diagnoseQueryApiService", BizHelper.getAkb020());
			Map param = new HashMap();
			param.put("akb020", BizHelper.getAkb020());
			param.put("akc190", akc190);
			param.put("BZ1", BZ1);
			List<KCD2_HospFee_HisDTO> kcd2HospfeeHisList = diagnoseQueryApiService.queryInhospPersonFee(param);
			for(int i = 0; i < kcd2HospfeeHisList.size(); i++) {
				if(null != kcd2HospfeeHisList.get(i).getBz1()) {
					if("1".equals(kcd2HospfeeHisList.get(i).getBz1())) {
						kcd2HospfeeHisList.get(i).setBz1("成功");
					}else {
						kcd2HospfeeHisList.get(i).setBz1("失败");
					}
				}
			}
			setJSONReturn("校验完毕", kcd2HospfeeHisList);
		} catch (IOException e) {
			logger.error("通用接口查询住院人员费用异常：" + ToolUtil.getExceptionInfo(e));
			e.printStackTrace();
		}
		return NONE;
	}

	/**
	 * 查询门诊人员信息
	 * 
	 * @return
	 */
	public String queryOutpatientPersonInfo() {
		try {
			// 获取页面传回的表单数据
			// String aac001 = kcd1_Mz_HisDTO.getAac001();
			String aac001 = this.getParameter("aac001");
			String aac002 = this.getParameter("aac002");
			String bka100 = this.getParameter("bka100");
			String bkm902 = this.getParameter("bkm902");
			Date aae036 = null;
			// 获取就医登记时间
			if (StringUtils.isNotBlank(this.getParameter("aae036"))) {
				aae036 = TimeUtils.parseDate(this.getParameter("aae036"), "yyyy-MM-dd");
			}
			DiagnoseQueryApiService diagnoseQueryApiService = (DiagnoseQueryApiService) hygeiaServiceManager
					.getBean("diagnoseQueryApiService", BizHelper.getAkb020());
			// 调用查询方法查询人员信息
			Map param = new HashMap();
			param.put("akb020", BizHelper.getAkb020());
			param.put("aac001", aac001);
			param.put("aac002", aac002);
			param.put("bka100", bka100);
			param.put("aae036", aae036);
			param.put("bkm902", bkm902);
			List<KCD1_Mz_HisDTO> kcd1MzHisList = diagnoseQueryApiService.queryOutpatientPersonInfo(param);
			// 将查询人员信息灌入
			setJSONReturn("校验完毕", kcd1MzHisList);
		} catch (IOException e) {
			logger.error("通用接口查询门诊人员信息异常：" + ToolUtil.getExceptionInfo(e));
			e.printStackTrace();
		}
		return NONE;
	}

	/**
	 * 查询门诊人员费用
	 * 
	 * @return
	 */
	public String queryOutpatientPersonFee() {
		try {
			// 根据人员信息查询发生的费用
			// 获取住院号
			String bkm902 = this.getParameter("bkm902");
			// 根据住院号查询
			DiagnoseQueryApiService diagnoseQueryApiService = (DiagnoseQueryApiService) hygeiaServiceManager
					.getBean("diagnoseQueryApiService", BizHelper.getAkb020());
			Map param = new HashMap();
			param.put("akb020", BizHelper.getAkb020());
			param.put("bkm902", bkm902);
			List<KCD2_Fee_HisDTO> kcd2_Fee_HisList = diagnoseQueryApiService.queryOutpatientPersonFee(param);
			setJSONReturn("校验完毕", kcd2_Fee_HisList);
		} catch (IOException e) {
			logger.error("通用接口查询门诊人员费用信息异常：" + ToolUtil.getExceptionInfo(e));
			e.printStackTrace();
		}
		return NONE;
	}

	/**
	 * 住院费用重传通过住院号
	 * 
	 * @return
	 */
	public String inhospitalFeeUploadAgainByAkc190() {
		try {
			// 获取住院号
			String akc190 = this.getParameter("akc190");
			// 通过住院号查询登记信息
			DiagnoseQueryApiService diagnoseQueryApiService = (DiagnoseQueryApiService) hygeiaServiceManager
					.getBean("diagnoseQueryApiService", BizHelper.getAkb020());
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("akb020", BizHelper.getAkb020());
			param.put("akc190", akc190);
			List<KCD1_Hosp_HisDTO> hisdto = diagnoseQueryApiService.queryInhospPersonInfo(param);
			// 如果未找到登记信息，返回
			if (hisdto.size() == 0) {
				this.setJSONReturn("-1", null);
				return NONE;
			} else {
				KCD1_Hosp_HisDTO hisdt = hisdto.get(0);
				long tranflag = addManualTransOrder(BizHelper.getAkb020(), "ZYFY," + BizHelper.getAkb020() + ","
						+ hisdt.getAkc190() + "," + TimeUtils.formatDate(hisdt.getAae036(), "yyyy-MM-dd HH:mm:ss.SSS"));
				if (tranflag == -1L) {
					logger.info("重传住院费用，目前已经设置重传，请稍后再试");
					setJSONReturn("-1", null);
					return NONE;
				}
				setJSONReturn("0", null);
			}
		} catch (Exception ex) {
			logger.warn("通用接口出院登记时，重传费用失败，未找到住院号：" + ToolUtil.getExceptionInfo(ex));
		}
		return NONE;
	}

	/**
	 * 打开购药页面
	 * 
	 * @return
	 */
	public String openBuyDrug() {
		try {
			// 获取险种
			String aae140 = this.getParameter("aae140");
			ArrayList<String> aae140List = new ArrayList<String>();
			aae140List.add(aae140);
			this.setAttribute("aae140List", JsonHelper.toJson(aae140List));
		} catch (Exception ex) {
			throw new HygeiaException("页面打开异常！");
		}
		return "buymedicareInfo";
	}

	/**
	 * 住院费用重传
	 * 
	 * @return
	 */
	public String inhospitalfeeUploadAgain() {
		try {
			// 获取费用序列号aaz213，修改传输标记
			JSONArray jsonArray = JSONArray.fromObject(getParameter("feeUpload"));
			List<KCD1_Hosp_HisDTO> kcd1_Hosp_HisList = JSONArray.toList(jsonArray, KCD1_Hosp_HisDTO.class);
			DiagnoseQueryApiService diagnoseQueryApiService = (DiagnoseQueryApiService) hygeiaServiceManager
					.getBean("diagnoseQueryApiService", BizHelper.getAkb020());
			for (KCD1_Hosp_HisDTO hisdto : kcd1_Hosp_HisList) {
				// 发送redis，到hygeia_esb
				long tranflag = addManualTransOrder(BizHelper.getAkb020(),
						"ZYFY," + BizHelper.getAkb020() + "," + hisdto.getAkc190() + ","
								+ TimeUtils.formatDate(hisdto.getAae036(), "yyyy-MM-dd HH:mm:ss.SSS"));
				if (tranflag == -1L) {
					setJSONReturn("-1", kcd1_Hosp_HisList);
					return NONE;
				}
				setJSONReturn("设置重传成功", kcd1_Hosp_HisList);
			}
		} catch (IOException e) {
			logger.error("通用接口住院费用重传异常：" + ToolUtil.getExceptionInfo(e));
			e.printStackTrace();
		}
		return NONE;
	}

	/**
	 * 查询未上传的费用
	 * 
	 * @return
	 */
	public String searchuploadFailFee() {
		try {
			String akc190 = this.getParameter("akc190");
			DiagnoseQueryApiService diagnoseQueryApiService = (DiagnoseQueryApiService) hygeiaServiceManager
					.getBean("diagnoseQueryApiService", BizHelper.getAkb020());
			Map param = new HashMap();
			param.put("akc190", akc190);
			param.put("akb020", BizHelper.getAkb020());
			param.put("BZ1", "0");
			List<KCD2_HospFee_HisDTO> hospList = diagnoseQueryApiService.queryInhospPersonFee(param);
			param.put("BZ1", "2");
			List<KCD2_HospFee_HisDTO> hospFeeList = diagnoseQueryApiService.queryInhospPersonFee(param);
			int maxSize = hospList.size() + hospFeeList.size();// 记录未上传或者上传失败的条数
			double maxSum = 0; // 记录未上传或者上传失败的金额
			if (hospList.size() > 0) {
				for (KCD2_HospFee_HisDTO hosp : hospList) {
					maxSum = maxSum + hosp.getAae019();
				}
			}
			if (hospFeeList.size() > 0) {
				for (KCD2_HospFee_HisDTO hosp : hospFeeList) {
					maxSum = maxSum + hosp.getAae019();
				}
			}
			List result = new ArrayList();
			result.add(maxSize);
			result.add(SysFunc.getRound(maxSum, 2));
			if (maxSize > 0) {
				this.setJSONReturn("-1", result);
				return NONE;
			}
			this.setJSONReturn("0", null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NONE;
	}

	/**
	 * 门诊费用重传
	 * 
	 * @return
	 */
	public String outpatientfeeUploadAgain() {
		// 获取登记序列号kcd1id，修改传输标记
		try {
			JSONArray jsonArray = JSONArray.fromObject(getParameter("feeUpload"));
			List<KCD1_Mz_HisDTO> kcd1_Mz_HisList = JSONArray.toList(jsonArray, KCD1_Mz_HisDTO.class);
			DiagnoseQueryApiService diagnoseQueryApiService = (DiagnoseQueryApiService) hygeiaServiceManager
					.getBean("diagnoseQueryApiService", BizHelper.getAkb020());
			for (KCD1_Mz_HisDTO hisdto : kcd1_Mz_HisList) {
				// 设置为3表示重传
				hisdto.setBka044("0");
				diagnoseQueryApiService.updateOutpatientFeeUploadFlag(hisdto);
				long tranflag = addManualTransOrder(BizHelper.getAkb020(),
						"MZFY," + BizHelper.getAkb020() + "," //+ hisdto.getBkm902() + ","
								+ TimeUtils.formatDate(hisdto.getAae036(), "yyyy-MM-dd HH:mm:ss.SSS"));
				if (tranflag == -1L) {
					setJSONReturn("-1", kcd1_Mz_HisList);
					return NONE;
				}
				// memoryDB.insertListAtEnd("HC_MZYH_"+BizHelper.getAkb020()+"_"+hisdto.getKcd1id(),
				// arg1);
				// 发送消息到消息队列
				setJSONReturn("设置重传成功", kcd1_Mz_HisList);
			}
		} catch (IOException e) {
			logger.error("通用接口门诊费用重传异常：" + ToolUtil.getExceptionInfo(e));
			e.printStackTrace();
		}

		return NONE;
	}

	/**
	 * 目录重传
	 * 
	 * @return
	 */
	public String tranCatalog() {
		try {
			long tranflag = this.addManualTransOrder(BizHelper.getAkb020(),
					"YYML," + BizHelper.getAkb020() + ",YYML" + "," + TimeUtils.formatDate(new Date(), "yyyyMMdd"));
			if (tranflag == -1L) {
				setJSONReturn("-1", null);
				return NONE;
			}
			setJSONReturn("0", null);
		} catch (Exception ex) {
			logger.error("目录重传异常：" + ToolUtil.getExceptionInfo(ex));
		}
		return NONE;
	}

	/**
	 * 登记信息重传
	 * 
	 * @return
	 */
	public String regInfoAgain() {
		try {
			String type = this.getParameter("type");
			long tranflag = 0;
			if ("MZDJ".equals(type)) {
				String akc190 = this.getParameter("akc190");
				tranflag = this.addManualTransOrder(BizHelper.getAkb020(), "MZFY," + BizHelper.getAkb020() + ","
						+ akc190 + "," + TimeUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));

			} else if ("ZYDJ".equals(type)) {
				String akc190 = this.getParameter("akc190");
				tranflag = this.addManualTransOrder(BizHelper.getAkb020(), "ZYFY," + BizHelper.getAkb020() + ","
						+ akc190 + "," + TimeUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
			}
			if (tranflag == -1L) {
				setJSONReturn("-1", null);
				return NONE;
			}
			setJSONReturn("0", null);
		} catch (Exception ex) {
			logger.error("目录重传异常：" + ToolUtil.getExceptionInfo(ex));
		}
		return NONE;
	}

	/**
	 * 获取费用数量
	 * 
	 * @param akb020
	 * @return
	 */
	protected int getHospFeeSize(String akb020, KCD2_HospFee_HisDTO hospdto) {
		DiagnoseQueryApiService diagnoseQueryApiService = (DiagnoseQueryApiService) hygeiaServiceManager
				.getBean("diagnoseQueryApiService", akb020);
		hospdto.setAkb020(akb020);
		// 设置页码
		hospdto.setCurrentPage(1);
		// 设置大小
		hospdto.setPageSize(1);
		// 分页首次查询
		ListResult hospList = diagnoseQueryApiService.queryInhospPersonFee_page(hospdto);
		return hospList.getCount();
	}

	/**
	 * 分页查询
	 * 
	 * @param pageSize
	 *            页面大小
	 * @param pageindex
	 *            页码
	 * @param akb020
	 *            医院编码
	 * @return
	 */
	protected List<KCD2_HospFee_HisDTO> queryHospFeeByPage(int pageSize, int pageindex, String akb020,
			KCD2_HospFee_HisDTO hospdto) {
		List<KCD2_HospFee_HisDTO> hospfeeList = new ArrayList<>();

		DiagnoseQueryApiService diagnoseQueryApiService = (DiagnoseQueryApiService) hygeiaServiceManager
				.getBean("diagnoseQueryApiService", akb020);

		hospdto.setAkb020(akb020);
		// 设置页码
		hospdto.setCurrentPage(pageindex);
		// 设置大小
		hospdto.setPageSize(pageSize);
		// 分页首次查询
		ListResult hospList = diagnoseQueryApiService.queryInhospPersonFee_page(hospdto);
		// 获取集合
		List<KCD2_HospFee_HisDTO> Ka20_HisDTOList = (List<KCD2_HospFee_HisDTO>) hospList.getList();

		hospfeeList = (List<KCD2_HospFee_HisDTO>) hospList.getList();

		return hospfeeList;
	}

	/**
	 * 同步目录
	 * 
	 * @return
	 */
	public String syncCatalog() {
		try {
			// 获取目录数据
			DiagnoseQueryApiService diagnoseQueryApiService = (DiagnoseQueryApiService) hygeiaServiceManager
					.getBean("diagnoseQueryApiService", BizHelper.getAkb020());
			// 获取医院编码
			String akb020 = BizHelper.getAkb020();
			KA20_HisDTO ka20 = new KA20_HisDTO();
			ka20.setAkb020(BizHelper.getAkb020());
			// 设置页码
			ka20.setCurrentPage(1);
			// 设置大小
			ka20.setPageSize(300);
			// 分页首次查询
			ListResult hospList = diagnoseQueryApiService.queryKa20_page(ka20);
			// 获取集合
			List<KA20_HisDTO> Ka20_HisDTOList = (List<KA20_HisDTO>) hospList.getList();
			// 获取最大参数
			int sizeMax = hospList.getCount();
			int index = 1;
			while (300 * (index - 1) <= sizeMax) {
				List rows_val = new ArrayList();
				for (KA20_HisDTO his : Ka20_HisDTOList) {
					// 插入ka40表及上传目录成功
					KA40_HISDTO ka40 = new KA40_HISDTO();
					UUID uuid = UUID.randomUUID();
					ka40.setKa40id(uuid.toString());
					ka40.setAaa027(BizHelper.getAaa027());
					ka40.setAkb020(BizHelper.getAkb020());
					ka40.setAke005(his.getAke001());//医疗机构三大目录编码
					ka40.setAke006(his.getAke002());//医疗机构三大目录名称
					ka40.setAke003(his.getAka063());//目录类别
					ka40.setBkm005(his.getAka070());//剂型
					ka40.setBkc140(his.getBka040());//单价
					ka40.setBkc141(his.getAka052());//单位
					ka40.setBkc139(his.getAka074());//规格
					ka40.setBkc141(his.getBka073());//生产单位
					ka40.setAka064(his.getAka064());//处方药标志1,处方用药,0,非处方用药
					ka40.setAae100("1");// 有效标记
					rows_val.add(ka40);
				}
				try {
					diagnoseQueryApiService.insertKa40(rows_val);
				} catch (Exception ex) {
					logger.error("目录上传失败！" + ToolUtil.getExceptionInfo(ex));
					errNum = errLogSnService.getErrSN(ProjectType.WEB);
					setJSONReturn("-1", "错误号： " + errNum + " " + Ka20_HisDTOList);
					return NONE;
				}
				// 页码加1
				index = index + 1;
				// 设置页码
				ka20.setCurrentPage(index);
				// 查询
				hospList = diagnoseQueryApiService.queryKa20_page(ka20);
				// 重置集合
				Ka20_HisDTOList = (List<KA20_HisDTO>) hospList.getList();
			}
			// 同步到结算云
			setJSONReturn("0", Ka20_HisDTOList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NONE;
	}

	/**
	 * 查询目录数据
	 * 
	 * @return
	 */
	public String queryCatalog() {
		DiagnoseQueryApiService diagnoseQueryApiService = (DiagnoseQueryApiService) hygeiaServiceManager
				.getBean("diagnoseQueryApiService", BizHelper.getAkb020());
		KA20_HisDTO ka20 = new KA20_HisDTO();
		ka20.setAkb020(BizHelper.getAkb020());
		PagerHelper.initPagination(getRequest());
		ka20.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
		ka20.setPageSize(PagerHelper.getPaginationObj().getPageSize());

		ListResult hospList = diagnoseQueryApiService.queryKa20_page(ka20);

		PaginationHelper.getPaginationObj().setCount(hospList.getCount());
		DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(hospList.getList()));
		return NONE;
	}

	/**
	 * 单个上传时，加载需要上传的费用数据
	 * 
	 * @return
	 */
	public String loadUploadData() {
		String akc190 = getParameter("akc190");
		DiagnoseQueryApiService diagnoseQueryApiService = (DiagnoseQueryApiService) hygeiaServiceManager
				.getBean("diagnoseQueryApiService", BizHelper.getAkb020());
		Map param = new HashMap();
		param.put("akb020", BizHelper.getAkb020());
		param.put("akc190", akc190);
		param.put("BZ1", "");
		// 查询人员信息
		List<KCD1_Hosp_HisDTO> personList = diagnoseQueryApiService.queryInhospPersonInfo(param);
		// 查询费用信息
		List<KCD2_HospFee_HisDTO> feeList = diagnoseQueryApiService.queryHospFeeInfo(param);
		for(int i = 0; i < feeList.size(); i++) {
			if(null != feeList.get(i).getBz1()) {
				if("1".equals(feeList.get(i).getBz1())) {
					feeList.get(i).setBz1("成功");
				}else {
					feeList.get(i).setBz1("失败");
				}
			}
		}
		List<String> akc190List = new ArrayList<String>();
		akc190List.add(akc190);
		this.setAttribute("personinfo", JsonHelper.toJson(personList));
		this.setAttribute("feeinfo", JsonHelper.toJson(feeList));
		this.setAttribute("akc190", JsonHelper.toJson(akc190List));
		return "loadUploadData";
	}

	/**
	 * 门诊购药查询人员信息，通过姓名查询
	 * 
	 * @return
	 */
	public String mZQueryPersonInfo() {
		try {
			// 获取姓名
			String aac001 = URLDecoder.decode(getParameter("aac001"), "UTF-8");
			logger.info("通用接口--获取电脑号：" + aac001);
			String aae140 = URLDecoder.decode(getParameter("aae140"), "UTF-8");
			logger.info("通用接口--获取险种：" + aae140);
			DiagnoseQueryApiService diagnoseQueryApiService = (DiagnoseQueryApiService) hygeiaServiceManager
					.getBean("diagnoseQueryApiService", BizHelper.getAkb020());
			Map param = new HashMap();
			param.put("akb020", BizHelper.getAkb020());
			param.put("aac001", aac001);
			param.put("bka044", "0");// 未上传
			// param.put("aae036", new Date());
			// param.put("bka013", new Date());
			List<String> aae140List = new ArrayList<String>();
			aae140List.add(aae140);
			List<KCD1_Mz_HisDTO> mzList = diagnoseQueryApiService.queryOutpatientPersonInfo(param);
			this.setAttribute("mzList", JsonHelper.toJson(mzList));
			this.setAttribute("aae140List", JsonHelper.toJson(aae140List));
		} catch (Exception ex) {
			logger.error("通用接口，门诊购药失败！详情：" + ToolUtil.getExceptionInfo(ex));
		}
		return "mZQueryPersonInfo";
	}

	/**
	 * 判断门诊人员登记信息是否存在临时表
	 * 
	 * @return
	 */
	public String mZCheckIsExPersonInfo() {
		try {
			// 获取姓名
			String aac001 = URLDecoder.decode(getParameter("aac001"), "UTF-8");
			logger.info("通用接口--获取姓名：" + aac001);
			DiagnoseQueryApiService diagnoseQueryApiService = (DiagnoseQueryApiService) hygeiaServiceManager
					.getBean("diagnoseQueryApiService", BizHelper.getAkb020());
			Map param = new HashMap();
			param.put("akb020", BizHelper.getAkb020());
			param.put("aac001", aac001);
			param.put("bka044", "0");// 未上传
			List<KCD1_Mz_HisDTO> mzList = diagnoseQueryApiService.queryOutpatientPersonInfo(param);
			this.setJSONReturn("mzList", mzList);
		} catch (Exception ex) {
			logger.error("通用接口，门诊购药失败！详情：" + ToolUtil.getExceptionInfo(ex));
		}
		return NONE;
	}

	/**
	 * 往存储传输指令缓存list中添加指令
	 * 
	 * @param yybm
	 *            传输指令对应的医院编码
	 * @param order_param_str
	 *            传输指令参数字符串
	 * @return
	 */
	public synchronized long addManualTransOrder(String yybm, String order_param_str) {
		if (existManualTransOrder(yybm)) {
			return -1L;
		}
		return memoryDB.insertListAtEnd(HC_TRANS_ORDER_KEY + yybm, order_param_str);
	}

	/**
	 * 获取并删除手动传输指令参数
	 * 
	 * @param yybm
	 *            传输指令对应的医院编码
	 * @return 没有则返回null，否则返回指令参数的list
	 */
	public List<String> getAndDelManualTransOrders(String yybm) {
		if (!existManualTransOrder(yybm)) {
			return null;
		}
		// 拼接出对应医院编码存储指令的key值
		String listKey = HC_TRANS_ORDER_KEY + yybm;
		List<String> order_list = memoryDB.getList(listKey, 0, -1);
		memoryDB.delete(listKey);
		return order_list;
	}

	/**
	 * 判断是否存在当前医院的手动传输指令
	 * 
	 * @param yybm
	 *            传输指令对应的医院编码
	 * @return
	 */
	public boolean existManualTransOrder(String yybm) {
		// 拼接出对应医院编码存储指令的key值
		String listKey = HC_TRANS_ORDER_KEY + yybm;
		if (!memoryDB.existKey(listKey) || memoryDB.getListSize(listKey) == 0) {
			return false;
		}
		return true;
	}

	/**
	 * 查询结算云中的ka40目录表
	 * 
	 * @return
	 */
	public String queryCatalogByKa40() {
		DiagnoseQueryApiService diagnoseQueryApiService = (DiagnoseQueryApiService) hygeiaServiceManager
				.getBean("diagnoseQueryApiService", BizHelper.getAkb020());
		KA40_HISDTO ka40 = new KA40_HISDTO();
		ka40.setAkb020(BizHelper.getAkb020());
		PagerHelper.initPagination(getRequest());
		ka40.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
		ka40.setPageSize(PagerHelper.getPaginationObj().getPageSize());
		ListResult ka40List = diagnoseQueryApiService.queryKA40(ka40);
		PaginationHelper.getPaginationObj().setCount(ka40List.getCount());
		DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(ka40List.getList()));
		return NONE;
	}
	




}
