package com.powersi.ssm.biz.medicare.declare.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.biz.medicare.declare.pojo.DeclarePayDTO;
import com.powersi.biz.medicare.declare.pojo.HospitalAppealDTO;
import com.powersi.biz.medicare.declare.service.api.DeclarePayService;
import com.powersi.comm.utils.ToolUtil;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.PaginationHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.hygeia.web.util.JsonHelper;
import com.powersi.powerreport.service.PowerReportDao;
import com.powersi.powerreport.service.PowerReportImpl;
import com.powersi.ssm.biz.medicare.common.service.CodeTableSwitch;
import com.powersi.ssm.biz.medicare.common.service.CodeTableSwitchImpl;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.PagerHelper;

import sun.misc.BASE64Encoder;

@Action(value = "DeclarePayAction", results = {
		@Result(name = "appealDetail", location = "/pages/biz/medicare/appeal/BusinessAppealDetail.jsp"),
		@Result(name = "getDoctorAppealHandle", location = "/pages/biz/medicare/appeal/DoctorAppealDetail.jsp"),
		@Result(name = "getHospitalAppealHandle", location = "/pages/biz/medicare/appeal/HospitalAppealDetail.jsp") })
public class DeclarePayAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;
	private DeclarePayDTO declarePayDTO;
	private HospitalAppealDTO appealDTO;
	private File fileUpload;// 上传文件
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 结算申报
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String verifyCenterDecl() throws HygeiaException {
		try {
			if (declarePayDTO == null) {
				declarePayDTO = new DeclarePayDTO();
			}
			String akb020 = BizHelper.getAkb020();
			String aaa027 = BizHelper.getAaa027();
			DeclarePayService declService = hygeiaServiceManager.getBeanByClass(DeclarePayService.class, akb020);
			declarePayDTO.setAkb020(akb020);
			declarePayDTO.setAaa027(aaa027);

			Map<String, Object> mRet = declService.verifyCenterDecl(akb020, declarePayDTO);

			if (declarePayDTO.getBka210().equals("3")) {
				List lReturn = (List) mRet.get("declareinfo");
				CodeTableSwitch codeService = new CodeTableSwitchImpl();
				List retList = codeService.loadDeclInfo(lReturn, BizHelper.getAaa027());
				setJSONReturn(retList);
			} else if (declarePayDTO.getBka210().equals("4") || declarePayDTO.getBka210().equals("1")) {
				if (mRet.isEmpty()) {
					throw new HygeiaException("没有获取到明细信息！！");
				}
				// String month = DateFunc.dateToString(DateFunc.getDate(), "yyyyMM");
				PowerReportImpl pri = new PowerReportImpl();
				String strIdFirst = UtilFunc.getUUID();
				String strIdSecond = UtilFunc.getUUID();
				// String strIdThird = UtilFunc.getUUID();

				// 判断bizID是否已经存在Map,存在先删除老数据，在新增
				PowerReportDao dao = new PowerReportDao();
				Map checkMap = null;
				checkMap = dao.getReportBaseInfoBizID(strIdFirst);
				if (checkMap != null) {
					pri.delReport(null, strIdFirst);
					DBHelper.commit();
				}
				checkMap = dao.getReportBaseInfoBizID(strIdSecond);
				if (checkMap != null) {
					pri.delReport(null, strIdSecond);
					DBHelper.commit();
				}
				// checkMap = dao.getReportBaseInfoBizID(strIdThird);
				// if (checkMap != null) {
				// pri.delReport(null, strIdThird);
				// DBHelper.commit();
				// }

				pri.createReport("decl/" + declarePayDTO.getAaa027() + "/decl_" + declarePayDTO.getAaa027() + "_"
						+ declarePayDTO.getAae140() + "_1" + ".xls", strIdFirst, 1, mRet, "结算申报一级表", "sys");
				pri.createReport("decl/" + declarePayDTO.getAaa027() + "/decl_" + declarePayDTO.getAaa027() + "_"
						+ declarePayDTO.getAae140() + "_2" + ".xls", strIdSecond, 1, mRet, "结算申报二级表", "sys");
				// pri.createReport("decl/" + declarePayDTO.getAaa027() + "/decl_" +
				// declarePayDTO.getAaa027()+"_"+declarePayDTO.getAae140() + "_3" + ".xls",
				// strIdThird, 1, mRet,
				// "结算申报三级表", "sys");

				// Map<String, String> map = new HashMap<String, String>();
				// map.put("firstId", strIdFirst);
				// map.put("secondId", strIdSecond);
				// map.put("thirdId", strIdThird);
				// setJSONReturn(map);
				mRet.put("firstId", strIdFirst);
				mRet.put("secondId", strIdSecond);
				setJSONReturn(mRet);
			}

		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询申报明细表 分页查询
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String queryDeclDetail() throws HygeiaException {
		try {
			if (declarePayDTO == null) {
				declarePayDTO = new DeclarePayDTO();
			}
			String akb020 = BizHelper.getAkb020();
			String aaa027 = BizHelper.getAaa027();
			DeclarePayService declService = hygeiaServiceManager.getBeanByClass(DeclarePayService.class, akb020);
			declarePayDTO.setAkb020(akb020);
			declarePayDTO.setAaa027(aaa027);

			PagerHelper.initPagination(getRequest());

			int pageIndex = PagerHelper.getPaginationObj().getPageIndex();
			int pageSize = PagerHelper.getPaginationObj().getPageSize();

			int first_row = pageSize * (pageIndex - 1) + 1;
			int last_row = pageSize * pageIndex;

			Map<String, Object> mRet = declService.queryDeclDetail(akb020, declarePayDTO, first_row, last_row);

			List declDetail = (List) mRet.get("third");
			int row_sum = Integer.parseInt(mRet.get("row_sum").toString());
			if (row_sum > 0) {
				PaginationHelper.getPaginationObj().setCount(row_sum);
				DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(declDetail));
			}

		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 结算申报
	 * 
	 * @return
	 */
	public String cancelDeclarePay() throws HygeiaException {
		try {
			if (declarePayDTO == null) {
				declarePayDTO = new DeclarePayDTO();
			}
			String akb020 = BizHelper.getAkb020();
			String aaa027 = BizHelper.getAaa027();
			DeclarePayService declService = hygeiaServiceManager.getBeanByClass(DeclarePayService.class, akb020);
			declarePayDTO.setAkb020(akb020);
			declarePayDTO.setAaa027(aaa027);

			String strResult = declService.cancelDeclarePay(akb020, declarePayDTO);
			if (strResult.equals("1")) {
				setJSONReturn("取消申报成功！");
			} else {
				setJSONReturn("取消申报失败！");
			}

		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 结算申报
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String verifySNYDDecl() throws HygeiaException {

		try {
			if (declarePayDTO == null) {
				declarePayDTO = new DeclarePayDTO();
			}
			String akb020 = BizHelper.getAkb020();
			String aaa027 = BizHelper.getAaa027();
			DeclarePayService declService = hygeiaServiceManager.getBeanByClass(DeclarePayService.class, akb020);
			declarePayDTO.setAkb020(akb020);
			declarePayDTO.setAaa027(aaa027);

			Map<String, Object> mRet = declService.verifySNYDDecl(akb020, declarePayDTO);

			if (declarePayDTO.getBka210().equals("3")) {
				List lReturn = (List) mRet.get("declareinfo");
				CodeTableSwitch codeService = new CodeTableSwitchImpl();
				List retList = codeService.loadDeclInfo(lReturn, BizHelper.getAaa027());
				setJSONReturn(retList);
			} else if (declarePayDTO.getBka210().equals("4") || declarePayDTO.getBka210().equals("1")) {
				if (mRet.isEmpty()) {
					throw new HygeiaException("没有获取到明细信息！！");
				}
				// String month = DateFunc.dateToString(DateFunc.getDate(), "yyyyMM");
				PowerReportImpl pri = new PowerReportImpl();
				String strIdFirst = UtilFunc.getUUID();
				String strIdSecond = UtilFunc.getUUID();
				String strIdThird = UtilFunc.getUUID();
				String strIdFourth = UtilFunc.getUUID();
				String strIdFifth = UtilFunc.getUUID();

				// 判断bizID是否已经存在Map,存在先删除老数据，在新增
				PowerReportDao dao = new PowerReportDao();
				Map checkMap = null;
				checkMap = dao.getReportBaseInfoBizID(strIdFirst);
				if (checkMap != null) {
					pri.delReport(null, strIdFirst);
					DBHelper.commit();
				}
				checkMap = dao.getReportBaseInfoBizID(strIdSecond);
				if (checkMap != null) {
					pri.delReport(null, strIdSecond);
					DBHelper.commit();
				}
				checkMap = dao.getReportBaseInfoBizID(strIdThird);
				if (checkMap != null) {
					pri.delReport(null, strIdThird);
					DBHelper.commit();
				}
				checkMap = dao.getReportBaseInfoBizID(strIdFourth);
				if (checkMap != null) {
					pri.delReport(null, strIdFourth);
					DBHelper.commit();
				}
				checkMap = dao.getReportBaseInfoBizID(strIdFifth);
				if (checkMap != null) {
					pri.delReport(null, strIdFifth);
					DBHelper.commit();
				}

				if (declarePayDTO.getBka210().equals("9")) {
					pri.createReport("decl/decl_snyd_main.xls", strIdFirst, 1, mRet, "申报汇总表", "sys");
					pri.createReport("decl/decl_snyd_cz_stat.xls", strIdSecond, 1, mRet, "城职汇总表", "sys");
					pri.createReport("decl/decl_snyd_cz_detail.xls", strIdThird, 1, mRet, "城职明细表", "sys");
					pri.createReport("decl/decl_snyd_cj_stat.xls", strIdFourth, 1, mRet, "城居汇总表", "sys");
					pri.createReport("decl/decl_snyd_cj_detail.xls", strIdFifth, 1, mRet, "城居明细表", "sys");
				} else {
					pri.createReport("decl/decl_snyd_main.xls", strIdFirst, 1, mRet, "申报汇总表", "sys");
					pri.createReport("decl/decl_snyd_cz_stat.xls", strIdSecond, 1, mRet, "城职汇总表", "sys");
					pri.createReport("decl/decl_snyd_cz_detail.xls", strIdThird, 1, mRet, "城职明细表", "sys");
					pri.createReport("decl/decl_snyd_cj_stat.xls", strIdFourth, 1, mRet, "城居汇总表", "sys");
					pri.createReport("decl/decl_snyd_cj_detail.xls", strIdFifth, 1, mRet, "城居明细表", "sys");
				}
				Map<String, String> map = new HashMap<String, String>();
				map.put("firstId", strIdFirst);
				map.put("secondId", strIdSecond);
				map.put("thirdId", strIdThird);
				map.put("fourthId", strIdFourth);
				map.put("fifthId", strIdFifth);
				setJSONReturn(map);
			}

		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 结算申报
	 * 
	 * @return
	 */
	public String cancelSNYDDecl() throws HygeiaException {
		try {
			if (declarePayDTO == null) {
				declarePayDTO = new DeclarePayDTO();
			}
			String akb020 = BizHelper.getAkb020();
			String aaa027 = BizHelper.getAaa027();
			DeclarePayService declService = hygeiaServiceManager.getBeanByClass(DeclarePayService.class, akb020);
			declarePayDTO.setAkb020(akb020);
			declarePayDTO.setAaa027(aaa027);

			String strResult = declService.cancelSNYDDecl(akb020, declarePayDTO);
			if (strResult.equals("1")) {
				setJSONReturn("取消申报成功！");
			} else {
				setJSONReturn("取消申报失败！");
			}

		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	@SuppressWarnings("rawtypes")
	public String getBusinessAppealInfo() {
		try {
			PagerHelper.initPagination(getRequest());
			if (appealDTO == null) {
				appealDTO = new HospitalAppealDTO();
			}

			String akb020 = BizHelper.getAkb020();
			String aaa027 = BizHelper.getAaa027();
			DeclarePayService declService = hygeiaServiceManager.getBeanByClass(DeclarePayService.class, akb020);
			appealDTO.setAkb020(akb020);
			appealDTO.setAaa027(aaa027);
			Map mRet = declService.getBusinessAppealInfo(appealDTO);
			if (mRet.isEmpty()) {
				throw new HygeiaException("没有获取到明细信息！！");
			} else {
				List lReturn = JsonHelper.toList(mRet.get("list").toString());
				DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(lReturn));
			}
		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getBusinessAppealDetail() {
		try {
			PagerHelper.initPagination(getRequest());
			if (appealDTO == null) {
				appealDTO = new HospitalAppealDTO();
			}

			String akb020 = BizHelper.getAkb020();
			String aaa027 = BizHelper.getAaa027();
			String aaz217 = getParameter("aaz217");
			String apa709 = getParameter("apa709");
			if (StringUtils.isBlank(aaz217)) {
				throw new HygeiaException("就诊ID不能为空，请检查业务信息！");
			}
			DeclarePayService declService = hygeiaServiceManager.getBeanByClass(DeclarePayService.class, akb020);
			appealDTO.setAkb020(akb020);
			appealDTO.setAaa027(aaa027);
			appealDTO.setAaz217(aaz217);
			appealDTO.setApa709(apa709);
			Map mRet = declService.getBusinessAppealDetail(appealDTO);
			if (mRet.isEmpty()) {
				throw new HygeiaException("没有获取到明细信息！！");
			} else {
				Map diagnoseMap = JsonHelper.toMap(mRet.get("person").toString());
				// List ruleList =
				// JsonHelper.toList(mRet.get("ruleList").toString());
				diagnoseMap.put("aaz217", aaz217);
				diagnoseMap.put("apa709", apa709);
				setAttribute("diagnoseMap", diagnoseMap);
				// setAttribute("ruleList", ruleList);
			}
		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return "appealDetail";
	}

	@SuppressWarnings("rawtypes")
	public String getBusinessAppealRules() {
		try {
			PagerHelper.initPagination(getRequest());
			if (appealDTO == null) {
				appealDTO = new HospitalAppealDTO();
			}

			String akb020 = BizHelper.getAkb020();
			String aaa027 = BizHelper.getAaa027();
			String aaz217 = getParameter("aaz217");
			String aaz328 = getParameter("aaz328");
			if (StringUtils.isBlank(aaz217)) {
				throw new HygeiaException("就诊ID不能为空，请检查业务信息！");
			}
			DeclarePayService declService = hygeiaServiceManager.getBeanByClass(DeclarePayService.class, akb020);
			appealDTO.setAkb020(akb020);
			appealDTO.setAaa027(aaa027);
			appealDTO.setAaz217(aaz217);
			appealDTO.setAaz328(aaz328);
			Map mRet = declService.getBusinessAppealDetail(appealDTO);
			if (mRet.isEmpty()) {
				throw new HygeiaException("没有获取到明细信息！！");
			} else {
				List ruleList = JsonHelper.toList(mRet.get("ruleList").toString());
				// setAttribute("ruleList", ruleList);
				renderJson(ruleList);
			}
		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	@SuppressWarnings("rawtypes")
	public String getAppealDataInfo() {
		try {
			PagerHelper.initPagination(getRequest());
			if (appealDTO == null) {
				appealDTO = new HospitalAppealDTO();
			}

			String akb020 = BizHelper.getAkb020();
			String aaa027 = BizHelper.getAaa027();
			String aaz217 = getParameter("aaz217");
			String aaz328 = getParameter("aaz328");
			if (StringUtils.isBlank(aaz217)) {
				throw new HygeiaException("就诊ID不能为空，请检查业务信息！");
			}
			DeclarePayService declService = hygeiaServiceManager.getBeanByClass(DeclarePayService.class, akb020);
			appealDTO.setAkb020(akb020);
			appealDTO.setAaa027(aaa027);
			appealDTO.setAaz217(aaz217);
			appealDTO.setAaz328(aaz328);
			Map mRet = declService.getBusinessAppealDetail(appealDTO);
			if (mRet.isEmpty()) {
				throw new HygeiaException("没有获取到明细信息！！");
			} else {
				List list = JsonHelper.toList(mRet.get("appealDataList").toString());
				renderJson(list);
			}
		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	public String saveAppeal() {
		try {
			PagerHelper.initPagination(getRequest());
			if (appealDTO == null) {
				appealDTO = new HospitalAppealDTO();
			}

			String akb020 = BizHelper.getAkb020();
			String aaa027 = BizHelper.getAaa027();
			String aaz217 = getParameter("aaz217");
			String aaz328 = getParameter("aaz328");
			DeclarePayService declService = hygeiaServiceManager.getBeanByClass(DeclarePayService.class, akb020);
			appealDTO.setAkb020(akb020);
			appealDTO.setAaa027(aaa027);
			appealDTO.setAaz217(aaz217);
			appealDTO.setAaz328(aaz328);
			String strReturn = declService.saveAppeal(appealDTO);
			if (strReturn.equals("1")) {
				setJSONReturn("保存申述信息成功！");
			} else {
				setJSONReturn("保存申述信息失败！");
			}
		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	public String checkFileName() {
		try {
			String aaz217 = getParameter("aaz217");
			String fileName = getParameter("fileName");
			String akb020 = BizHelper.getAkb020();
			DeclarePayService declService = hygeiaServiceManager.getBeanByClass(DeclarePayService.class, akb020);
			boolean flag = declService.checkFileNameIsExist(aaz217, fileName);
			renderJson(flag);
		} catch (Exception e) {
			logger.error(ToolUtil.getExceptionInfo(e));
			saveJSONError(e.getMessage());
		}
		return NONE;
	}

	public String deleteAppealData() {
		try {
			String akb020 = BizHelper.getAkb020();
			String aaz530 = getParameter("aaz530");
			DeclarePayService declService = hygeiaServiceManager.getBeanByClass(DeclarePayService.class, akb020);
			declService.deleteAppealData(aaz530);
			saveJSONMessage("删除成功");
		} catch (Exception e) {
			logger.error(ToolUtil.getExceptionInfo(e));
			saveJSONError(e.getMessage());
		}
		return NONE;
	}

	public String uploadAppealData() {
		try {
			String akb020 = BizHelper.getAkb020();
			String userId = BizHelper.getLoginUser();
			DeclarePayService declService = hygeiaServiceManager.getBeanByClass(DeclarePayService.class, akb020);
			String bpd413 = getParameter("bpd413");
			String flag = getParameter("flag");// 附件是否已上传
			String fileName = getParameter("fileName");
			InputStream input = new FileInputStream(fileUpload);
			byte[] b = new byte[(int) fileUpload.length()];
			input.read(b);
			input.close();

			BASE64Encoder en = new BASE64Encoder();
			String file_content = en.encode(b);

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("bpd413", bpd413);
			params.put("file_content", file_content);
			params.put("file_name", fileName);
			params.put("flag", flag);
			params.put("userId", userId);
			declService.uploadAppealData(params);
			saveJSONMessage("上传成功");
		} catch (Exception e) {
			logger.error(ToolUtil.getExceptionInfo(e));
			saveJSONError(e.getMessage());
		}
		return NONE;
	}

	/**
	 * 返回医师申诉处理页面
	 * 
	 * @Description: TODO(查询医师基本信息)
	 * @return
	 * @date 2016年8月3日 下午7:49:59
	 * @author tanrui
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getDoctorAppealHandle() {

		try {
			if (appealDTO == null) {
				appealDTO = new HospitalAppealDTO();
			}

			String akb020 = BizHelper.getAkb020();
			String aaa027 = BizHelper.getAaa027();
			String aaz010 = getParameter("aaz010");
			String aaz328 = getParameter("aaz328");
			String apa709 = getParameter("apa709");
			DeclarePayService declService = hygeiaServiceManager.getBeanByClass(DeclarePayService.class, akb020);
			appealDTO.setAkb020(akb020);
			appealDTO.setAaa027(aaa027);
			appealDTO.setAaz010(aaz010);
			appealDTO.setAaz328(aaz328);
			appealDTO.setApa709(apa709);
			Map mRet = declService.getBusinessAppealDetail(appealDTO);
			if (mRet.isEmpty()) {
				throw new HygeiaException("没有获取到明细信息！！");
			} else {
				Map doctorMap = new HashMap();
				if (mRet.get("doctor") == null || mRet.get("doctor") == "null") {
					doctorMap.put("aaz010", aaz010);
					doctorMap.put("aaz328", aaz328);
					doctorMap.put("apa709", apa709);
					setAttribute("doctorMap", doctorMap);
				} else {
					doctorMap = JsonHelper.toMap(mRet.get("doctor").toString());
					doctorMap.put("aaz010", aaz010);
					doctorMap.put("aaz328", aaz328);
					doctorMap.put("apa709", apa709);
					setAttribute("doctorMap", doctorMap);
				}
			}
		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return "getDoctorAppealHandle";
	}

	/**
	 * 返回医院申诉处理页面
	 * 
	 * @Description: TODO()
	 * @return
	 * @date 2016年8月4日 下午4:24:01
	 * @author tanrui
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getHospitalAppealHandle() {

		try {
			if (appealDTO == null) {
				appealDTO = new HospitalAppealDTO();
			}

			String akb020 = BizHelper.getAkb020();
			String aaa027 = BizHelper.getAaa027();
			String aaz010 = getParameter("aaz010");
			String aaz328 = getParameter("aaz328");
			String apa709 = getParameter("apa709");
			DeclarePayService declService = hygeiaServiceManager.getBeanByClass(DeclarePayService.class, akb020);
			appealDTO.setAkb020(akb020);
			appealDTO.setAaa027(aaa027);
			appealDTO.setAaz010(aaz010);
			appealDTO.setAaz328(aaz328);
			appealDTO.setApa709(apa709);
			Map mRet = declService.getBusinessAppealDetail(appealDTO);
			if (mRet.isEmpty()) {
				throw new HygeiaException("没有获取到明细信息！！");
			} else {
				Map hospitalMap = JsonHelper.toMap(mRet.get("hospital").toString());
				// List ruleList =
				// JsonHelper.toList(mRet.get("ruleList").toString());
				hospitalMap.put("aaz010", aaz010);
				hospitalMap.put("aaz328", aaz328);
				hospitalMap.put("apa709", apa709);
				setAttribute("hospitalMap", hospitalMap);
				// setAttribute("ruleList", ruleList);
			}
		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return "getHospitalAppealHandle";
	}

	/**
	 * 获取待申诉的详细
	 * 
	 * @Description: TODO(分为医师和医院)
	 * @return
	 * @date 2016年8月3日 下午9:14:27
	 * @author tanrui
	 */
	@SuppressWarnings("rawtypes")
	public String getStayAppealDetail() {
		try {
			if (appealDTO == null) {
				appealDTO = new HospitalAppealDTO();
			}

			String akb020 = BizHelper.getAkb020();
			String aaa027 = BizHelper.getAaa027();
			String aaz328 = getParameter("aaz328");
			PagerHelper.initPagination(getRequest());
			DeclarePayService declService = hygeiaServiceManager.getBeanByClass(DeclarePayService.class, akb020);
			appealDTO.setAkb020(akb020);
			appealDTO.setAaa027(aaa027);
			appealDTO.setAaz328(aaz328);

			Map mRet = declService.searchStayAppealDetail(appealDTO);
			if (mRet.isEmpty()) {
				throw new HygeiaException("没有获取到明细信息！！");
			} else {
				List<Map<String, Object>> list = JsonHelper.toList(mRet.get("list").toString());
				DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(list));
			}

		} catch (Exception e) {
			logger.error(ToolUtil.getExceptionInfo(e));
			saveJSONError(e.getMessage());
		}
		return NONE;
	}

	/**
	 * 获取疑点信息
	 * 
	 * @Description: TODO(医师)
	 * @return
	 * @date 2016年8月3日 下午9:14:27
	 * @author tanrui
	 */
	@SuppressWarnings("rawtypes")
	public String getDoubtDetailDoctor() {
		try {
			if (appealDTO == null) {
				appealDTO = new HospitalAppealDTO();
			}

			String akb020 = BizHelper.getAkb020();
			String aaa027 = BizHelper.getAaa027();
			String aaz328 = getParameter("aaz328");
			String aaz010 = getParameter("aaz010");
			PagerHelper.initPagination(getRequest());
			DeclarePayService declService = hygeiaServiceManager.getBeanByClass(DeclarePayService.class, akb020);
			appealDTO.setAkb020(akb020);
			appealDTO.setAaa027(aaa027);
			appealDTO.setAaz328(aaz328);
			appealDTO.setAaz010(aaz010);

			Map mRet = declService.getBusinessAppealDetail(appealDTO);
			if (mRet.isEmpty()) {
				throw new HygeiaException("没有获取到明细信息！！");
			} else {
				List<Map<String, Object>> list = JsonHelper.toList(mRet.get("detailList").toString());
				DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(list));
			}

		} catch (Exception e) {
			logger.error(ToolUtil.getExceptionInfo(e));
			saveJSONError(e.getMessage());
		}
		return NONE;
	}

	public File getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}

	public DeclarePayDTO getDeclarePayDTO() {
		return declarePayDTO;
	}

	public void setDeclarePayDTO(DeclarePayDTO declarePayDTO) {
		this.declarePayDTO = declarePayDTO;
	}

	public HospitalAppealDTO getAppealDTO() {
		return appealDTO;
	}

	public void setAppealDTO(HospitalAppealDTO appealDTO) {
		this.appealDTO = appealDTO;
	}

}
