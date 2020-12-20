package com.powersi.biz.medicare.inhospital.service.api.mcce;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;

/**
 * 针对医院修改已录入病案首页信息、取消结算的流程规范
 * 
 * <pre>
 * bizh120133 回退申请
 * 变更对内容及范围	一、	医院前台系统：
 * 1.	提供回退申请界面，通过该界面，发起重新录入病案首页的申请；
 * 2.	界面描述：
 * （1）	录入界面类似与现有的 “取消门诊登记”； 
 * （2）	通过输入查询条件获取本笔业务的业务信息，需包含：人员身份信息，业务类型、待遇类型、病区、科室、疾病诊断、医疗总费用、医保记账金额、结算时间；
 * （3）	增加输入框：“申请理由”供医院填写输入（用于保存）；
 * （4）	增加下拉框：“申请类别”用来区分该申请时用来“取消结算”还是“重录病案首页”。（用于保存）
 * （5）	保存后，申请信息上传至中心。
 * （6）	中心审核通过后，医院前台可以对已经录入保存的病案首页信息进行修改再保存；
 * （7）	中心审核通过后，医院前台可以取消结算
 * 二、	API校验：
 * 1.	通过API调用取消结算接口时，校验如果该笔业务没有审核通过的回退申请，则返回提示“没有回退申请，不能取消结算”；
 * 2.	通过API上传病案首页信息时，如果该笔业务已经上传过病案首页了，则返回提示“没有回退申请，不能重复上传病案首页”.
 * </pre>
 * 
 * @author 许祥军、刘勇
 *
 */
public interface MCCEbizh120133Service extends MCCEsbService {

	/**
	 * 回退申请
	 */
	public String serviceid = mcce_ + "bizh120133";

	/**
	 * 回退申请保存
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public InHospitalDTO fallbackApplySave(InHospitalDTO inHospitalDTO);

	/**
	 * 回退申请查询
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> fallbackApplyQuery(InHospitalDTO inHospitalDTO);

	/**
	 * 更新，将记录置为无效（kc68）
	 * @param inHospitalDTO
	 * @return
	 */
	public int fallUpdatebackApply(InHospitalDTO inHospitalDTO);
	/**
	 * 回退申请查询
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public InHospitalDTO fallbackApplyQueryDTO(InHospitalDTO inHospitalDTO);
	
	/**
	 * 判断该笔业务是否可以做取消申请
	 * @param inHospitalDTO
	 */
	public void checkBackApply(InHospitalDTO inHospitalDTO);

}
