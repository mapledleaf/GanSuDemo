package com.powersi.biz.medicare.iccard.service;

import java.io.Serializable;

import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;

/**
 * 用卡_业务辅助
 * 
 * @author 刘勇
 *
 */
public interface CheckCardService extends Serializable {

	/**
	 * 湘潭医保校验社保卡鉴权
	 * 
	 * <pre>
	 * 由于API接口文档中去掉了持卡就诊登记许可号（ic_reg_permit），因此目前api都没有送此参数，导致原来按照ic_reg_permit中的信息进行卡鉴权就起不到
	 * 作用。这样会有很大风险——挂失的卡、无效卡也可以就医结算！请修改程序，确保api、web都要走卡鉴权过程。如果ic_reg_permit没有，建议走缓存的方式：在读卡
	 * 时就将卡号、终端号等信息缓存下来，来替代ic_reg_permit中的信息。
	 * 强调说明：就诊结算必须要走卡鉴权。但人员信息查询等等查询窗口，不要走卡鉴权，不要盲目使用，否则会把卡管系统拖死的。请各位核查。
	 * </pre>
	 * 
	 * @param yybm
	 *            医院编码(akb020)
	 * @param shbzkkh
	 *            社会保障卡卡号(bka100)
	 * @return
	 */
	public boolean checkCardAuthentication(String yybm, String shbzkkh);

	/**
	 * 判断鉴权结果_有开关_一般在登记保存阶段调用
	 * 
	 * @param inHospitalDTOTemp
	 *            参数需包含ic_reg_permit（ 持卡就诊登记许可号）和bka100（社保卡号）
	 * @return
	 */
	public InHospitalDTO authenticationSSCard(InHospitalDTO inHospitalDTOTemp);

	/**
	 * 出院结算先试算拿到pcardinfo和ppayinfo
	 * 
	 * <pre>
	 * 1、输入参数pCardInfo，该参数用于传入卡的基本信息，依次为：卡识别码、卡号。各数据项之间以“|”分割，且最后一个数据项以“|”结尾；
	 * 2、输入参数pPayInfo，该参数用于传入消费相关信息，依次为：本次消费总金额(小于42949672.95的小数，小数点后保留两位)、个人账户交易金额和统筹基金支付金额相加的
	 *    总金额（小于42949672.95的小数，小数点后保留两位）、交易时间（格式为YYYYMMDDHHMMSS）。各数据项之间以“|”分割，且最后一个数据项以“|”结尾；
	 * </pre>
	 * 
	 * @param inHospitalDTO
	 * @param InHospitalDTOReturn
	 * @return
	 */
	public InHospitalDTO loadCardAndPayInfo(InHospitalDTO inHospitalDTO, InHospitalDTO InHospitalDTOReturn);

	/**
	 * 是否需要写社保卡
	 */
	public String CheckDoDebit = "CheckDoDebit";

	/**
	 * 持卡就诊
	 */
	public String CheckReadCard = "CheckReadCard";

	/**
	 * 判断鉴权结果
	 */
	public String CheckAuthenticationSSCard = "CheckAuthenticationSSCard";

	/**
	 * 校验写社保卡同时保存TAC码
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public InHospitalDTO checkDoDebitAndSaveTAC(InHospitalDTO inHospitalDTO);

	/**
	 * 加载TAC码(ic_pay_permit)
	 * 
	 * <pre>
	 * 返回值为“|”分割的字符串，第一位固定为错误代码，“1”表示成功，“0”表示失败。 当函数执行成功时，返回1 |持卡就诊结算许可号|
	 * 。持卡就诊结算许可号是访问结算请求必须携带的参数，持卡就诊结算许可号里包括了交易验证码（TAC），返回举例：
	 * 1|ag2918d84710481ccdd2498s49fhfj92i1114|。当函数执行失败时，该输出参数为错误代码(“0”)、错误描叙,例如：
	 * 0|用户密码错误|
	 * </pre>
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public InHospitalDTO loadTACByDoDebit(InHospitalDTO inHospitalDTO);

	/**
	 * 读卡解析转换
	 * 
	 * <pre>
	 * 1、返回值为“|”分割的字符串，第一位固定为错误代码，“1”表示成功，“0”表示失败。
	 * 2、当函数执行成功时，该输出参数为读出的社保卡基本信息各数据项，依次为：错误代码(“1”)、发卡地区行政区划代码（卡识别码前6位）、社会保障号码、卡号、
	 *   卡识别码、姓名、卡复位信息（仅取历史字节）、规范版本、发卡日期、卡有效期、终端机编号、终端设备号、持卡就诊登记许可号。各数据项之间以“|”分割，且
	 *   最后一个数据项以“|”结尾。例如：1|639900|111111198101011110|X00000019|639900D15600000500BF7C7A48FB4966|张三
	 *   |00814E43238697159900BF7C7A|1.00|20101001|20201001|410100813475|终端设备号|持卡就诊登记许可号|
	 * 3、当函数执行失败时，该输出参数为错误代码(“0”)、错误描叙,例如：0|卡片上电失败|
	 * </pre>
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public InHospitalDTO convertQueryStringByReadCardBase(InHospitalDTO inHospitalDTO);

	/**
	 * 持卡就诊判断
	 * 
	 * <pre>
	 * 1、如果持卡就诊（CheckReadCard）开关开启，没有加载bke548则抛出异常提示："持卡就诊，请先读卡！"
	 * </pre>
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public boolean checkReadCard(InHospitalDTO inHospitalDTO);

	/**
	 * 持卡结算判断
	 * 
	 * <pre>
	 * 1、如果持卡结算（CheckDoDebit）开关开启，没有加载bke549或pcardinfo或ppayinfo则抛出异常提示："持卡结算，请先读写卡！"
	 * </pre>
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public boolean checkDoDebit(InHospitalDTO inHospitalDTO);

}
