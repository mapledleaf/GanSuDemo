package com.powersi.biz.medicare.medicarepay.service;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.comm.pojo.ParamDTO;
import com.powersi.biz.medicare.medicarepay.pojo.AccountCancelApplyQueryDTO;
import com.powersi.biz.medicare.medicarepay.pojo.AccountDeclareQueryDTO;
import com.powersi.biz.medicare.medicarepay.pojo.AccountDeclareReportResultDTO;
import com.powersi.biz.medicare.medicarepay.pojo.BillQueryDTO;
import com.powersi.biz.medicare.medicarepay.pojo.DeductionQueryDTO;
import com.powersi.biz.medicare.medicarepay.pojo.MedicalVillagePayDTO;
import com.powersi.biz.medicare.medicarepay.pojo.VillageRationDTO;

/**
 * 医疗拨付esb服务接口
 * 
 * 2018年7月12日上午11:43:24
 *
 * @author lwyao
 *
 */
public interface MedicarePayEsbService {

	/**
	 * 获取医保中心端码表
	 * 
	 * @author lwyao
	 * @date 2018年9月29日
	 * @param p
	 * @return
	 */
	public Map<String, Map<String, String>> getCenterCodetypes(ParamDTO p);

	/**
	 * 查询申报台账汇总
	 * 
	 * @author lwyao
	 * @param q
	 * @return
	 */
	public AccountDeclareReportResultDTO queryDeclareAccount(AccountDeclareQueryDTO q);

	/**
	 * 查询申报台账明细
	 * 
	 * @author lwyao
	 * @param q
	 * @return
	 */
	public ListResult queryDeclareAccountDetail(AccountDeclareQueryDTO q);

	/**
	 * 保存申报明细修改
	 * 
	 * @author lwyao
	 * @date 2018年9月11日
	 * @param p
	 * @return
	 */
	public Map<String, Object> saveDeclareAccountDetailChanges(ParamDTO p);

	/**
	 * 确认申报
	 * 
	 * @author lwyao
	 * @param q
	 * @return
	 */
	public boolean confirmDeclareAccount(AccountDeclareQueryDTO q);
	
	/**
	 * TS19052700048 - 【需求开发】湘潭体检系统结算申报拨付流程改造
	 * 体检结算申报确认
	 * 
	 * @author lubin
	 * 2019年5月27日下午4:33:22
	 * @param q
	 * @return the boolean
	 */
	public boolean confirmAccountDeclareHealth(AccountDeclareQueryDTO q);


	/**
	 * 异地就医结算申报查询
	 * 
	 * @author lwyao
	 * @date 2018年11月19日
	 * @param q 必填:{aaa027=统筹区编码,akb020=医疗机构编码,aae030=开始时间(yyyy-MM-dd),aae031=结束时间(yyyy-MM-dd),bkp002=结算申报类型,daa027=拨付中心,aaz262=结算申报人编码,aae011,结算申报人名称}
	 * @return {reportHtml:汇总报表,details:明细列表,count:明细数据总条数}
	 */
	public Map<String, Object> queryDeclareAccountYD(ParamDTO q);
	
	/**
	 * TS19052700048 - 【需求开发】湘潭体检系统结算申报拨付流程改造
	 * 体检结算申报查询
	 * 
	 * @author lubin
	 * 2019年5月27日下午2:15:15
	 * @param q
	 * @return the Map<String,Object>
	 */
	public Map<String, Object> queryAccountDeclareHealth(ParamDTO q);

	/**
	 * 查询扣付业务
	 * 
	 * @author lwyao
	 * @param q
	 * @return
	 */
	public ListResult queryDeductionBiz(DeductionQueryDTO q);

	/**
	 * 查询扣付费用
	 * 
	 * @author lwyao
	 * @param q
	 * @return
	 */
	public ListResult queryDeductionFee(DeductionQueryDTO q);

	/**
	 * 保存费用申述信息
	 * 
	 * @author lwyao
	 * @param param
	 * @return
	 */
	public int saveFeeAppeal(ParamDTO param);

	/**
	 * 异地就医-疑点费用人员信息查询
	 * 
	 * @author lwyao
	 * @date 2018年11月21日
	 * @param q
	 * @return
	 */
	public ListResult queryDeductionBizYD(ParamDTO q);

	/**
	 * 异地就医-疑点人员费用明细查询
	 * 
	 * @author lwyao
	 * @date 2018年11月21日
	 * @param q
	 * @return
	 */
	public ListResult queryDeductionFeeYD(ParamDTO q);

	/**
	 * 异地就医-疑点人员费用申诉反馈意见保存
	 * 
	 * @author lwyao
	 * @date 2018年11月21日
	 * @param p
	 * @return
	 */
	public Map<String, Object> saveFeeAppealYD(ParamDTO p);
	
	/**
	 * 查询结算周期
	 * TS19032600105 - 【需求开发】医疗机构客户端拨付单查询条件设置
	 *  
	 * @author lubin
	 * 2019年3月27日上午11:42:03
	 * @param p
	 * @return the Map<String,Object>
	 */
	public Map<String, Object> querySettlementCycle(ParamDTO p);

	/**
	 * 查询拨付单
	 * 
	 * @author lwyao
	 * @param q
	 * @return
	 */
	public ListResult queryBill(BillQueryDTO q);
	
	/**
	 * TS19052700048 - 【需求开发】湘潭体检系统结算申报拨付流程改造
	 * 查询体检拨付单
	 * 
	 * @author lubin
	 * 2019年5月29日下午1:34:49
	 * @param q
	 * @return the ListResult
	 */
	public ListResult queryBillHealth(BillQueryDTO q);

	/**
	 * 查询拨付单明细
	 * 
	 * @author lwyao
	 * @param q
	 * @return
	 */
	public ListResult queryBillDetail(BillQueryDTO q);
	
	/**
	 * TS19052700048 - 【需求开发】湘潭体检系统结算申报拨付流程改造
	 * 查询体检拨付单明细
	 * 
	 * @author lubin
	 * 2019年5月29日下午2:15:00
	 * @param q
	 * @return the ListResult
	 */
	public ListResult queryBillHealthDetail(BillQueryDTO q);

	/**
	 * 拨付单业务明细查询
	 * 
	 * @author lwyao
	 * @param q
	 * @return
	 */
	public ListResult queryBillBiz(BillQueryDTO q);
	
	/**
	 * TS19052700048 - 【需求开发】湘潭体检系统结算申报拨付流程改造
	 * 体检拨付单业务明细查询
	 * 
	 * @author lubin
	 * 2019年5月29日下午2:45:10
	 * @param q
	 * @return the ListResult
	 */
	public ListResult queryBillHealthBiz(BillQueryDTO q);

	/**
	 * 拨付单确认
	 * 
	 * @author lwyao
	 * @param p
	 * @return
	 */
	public int confirmBill(ParamDTO p);

	/**
	 * 查询登账数据
	 * 
	 * @author lubin 2018年11月7日下午3:11:54
	 * @param q
	 * @return the ListResult
	 */
	public ListResult queryAccountBiz(AccountCancelApplyQueryDTO q);

	/**
	 * 保存取消登账申请数据
	 * 
	 * @author lubin 2018年11月7日下午3:15:24
	 * @param param
	 * @return the int
	 */
	public int saveAccountCancelApply(ParamDTO param);

	/**
	 * 查询取消登账申请数据
	 * 
	 * @author lubin 2018年11月8日上午8:57:24
	 * @param q
	 * @return the ListResult
	 */
	public ListResult queryAccountApply(AccountCancelApplyQueryDTO q);

	/**
	 * @param dto
	 * @return 方法说明：获取乡镇卫生院对村卫生室的结算拨付数据
	 * @date 2018年11月27日上午9:56:57
	 * @author dannie
	 */
	public List<Map<String, Object>> getPayData_village(MedicalVillagePayDTO dto);

	/**
	 * @param dto
	 * @return 方法说明：计算与保存乡镇卫生院对村卫生室的结算拨付数据
	 * @date 2018年11月27日上午10:01:25
	 * @author dannie
	 */
	public Map<String, Object> saveData_village(MedicalVillagePayDTO dto);

	/**
	 * @param dto
	 * @return 方法说明：取消乡镇卫生院对村卫生室的结算拨付
	 * @date 2018年11月27日上午10:03:25
	 * @author dannie
	 */
	public int updData_village(MedicalVillagePayDTO dto);

	/**
	 * @param dto
	 * @return 方法说明：获取乡镇卫生院对村卫生室的结算拨付数据明细
	 * @date 2018年11月27日上午9:59:48
	 * @author dannie
	 */
	public Map<String, Object> getDetailData_village(MedicalVillagePayDTO dto);

	/**
	 * @param dto
	 * @return 方法说明：查询kct1表数据
	 * @date 2018年11月28日下午7:36:16
	 * @author dannie
	 */
	public Map<String, Object> getKct1Data(VillageRationDTO dto);

	/**
	 * @param dto
	 * @return 方法说明：新增kct1
	 * @date 2018年11月28日下午7:37:22
	 * @author dannie
	 */
	public int insertKct1(VillageRationDTO dto);

	/**
	 * @param dto
	 * @return 方法说明：更新kct1
	 * @date 2018年11月28日下午7:37:54
	 * @author dannie
	 */
	public int updateKct1(VillageRationDTO dto);

	/**
	 * @param dto
	 * @return 方法说明：删除kct1
	 * @date 2018年11月28日下午7:38:01
	 * @author dannie
	 */
	public int deleteKct1(VillageRationDTO dto);

}
