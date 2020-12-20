package com.powersi.biz.medicare.comm.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.comm.pojo.KA06;
import com.powersi.biz.medicare.comm.pojo.KAD5;
import com.powersi.biz.medicare.comm.pojo.KAE8;
import com.powersi.biz.medicare.comm.pojo.KE06;
import com.powersi.biz.medicare.comm.pojo.MediDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.config.pojo.BizYyInfo;

/**
 * 
 * 医保结算云公用服务接口(面向其他开发人员开放)
 * 
 * @author 刘勇
 *
 */
public interface MCCEBusniessService extends Serializable {

	/**
	 * 检索中心
	 */
	public String mccc_bizh120302 = "mccc_bizh120302";

	/**
	 * 同步
	 */
	public String bka977_yes = "1";

	/**
	 * 不同步
	 */
	public String bka977_no = "0";

	/**
	 * 广州
	 */
	public String aaa027_440100 = "440100";

	/**
	 * 珠海
	 */
	public String aaa027_440400 = "440400";

	/**
	 * 清远
	 */
	public String aaa027_441800 = "441800";

	/**
	 * 省内异地门诊
	 */
	public String aka130_71 = "71";

	/**
	 * 省内异地住院
	 */
	public String aka130_72 = "72";

	/**
	 * 缓存KA06MAP名称
	 */
	public String MAP_HYGEIA_BASE_KA06 = "MAP_HYGEIA_BASE_KA06";

	/**
	 * 缓存KAE8前缀，后面跟医院编码
	 */
	public String MAP_HYGEIA_DB_KAE8_ = "MAP_HYGEIA_DB_KAE8_";

	/**
	 * 缓存KAD5MAP名称
	 */
	public String MAP_HYGEIA_BASE_KAD5 = "MAP_HYGEIA_BASE_KAD5";

	/**
	 * 费用明细业务表
	 */
	public String kcd2 = "kcd2";

	/**
	 * 费用明细完成表
	 */
	public String kc22 = "kc22";

	/**
	 * 日期格式(金保核三)
	 */
	public String yyyyMMdd = "yyyyMMdd";

	/**
	 * 
	 * @param aaa027
	 *            医保分中心（统筹区）
	 * @param aka130
	 *            业务类别编码
	 * @param tableName
	 *            表名称比如： kcd2 kc22
	 * @return 费用明细同步中心标识 1同步0不同步
	 */
	public String getBka977(String aaa027, String aka130, String tableName);

	/**
	 * 
	 * @param akb020
	 *            医院编号
	 * @return 就医登记号
	 */
	public String getAaz217(String akb020);

	/**
	 * 
	 * @param akb020
	 *            医院编号
	 * @return 费用序号
	 */
	public Long getAaz213(String akb020);

	/**
	 * 
	 * @return UUID(小写)
	 */
	public String uuid();

	/**
	 * 目录基准库
	 * 
	 * @param bkc109
	 *            异名ID
	 * @param kae8Map
	 *            医院目录匹配关系缓存
	 * @param kad5Map
	 *            中心基准目录缓存
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public KAD5 queryKAD5(String aaa027, String bkc109, Map kae8Map, Map kad5Map);

	/**
	 * 医院目录匹配关系
	 * 
	 * @param akb020
	 *            医疗服务机构编号
	 * @param ake005
	 *            医疗机构三大目录编码
	 * @param kae8Map
	 *            医院目录匹配关系缓存
	 * @param kad5Map
	 *            中心基准目录缓存
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<KAE8> queryKAE8(String akb020, String ake005, Map kae8Map, Map kad5Map);

	/**
	 * 医院目录匹配关系
	 * 
	 * @param inHospitalDTORow
	 * @param kae8Map
	 *            医院目录匹配关系缓存
	 * @param kad5Map
	 *            中心基准目录缓存
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public KAE8 filterKAE8(InHospitalDTO inHospitalDTORow, Map kae8Map, Map kad5Map);
	
	/**
	 * 医院目录匹配关系
	 * 
	 * @param inHospitalDTORow
	 * @param kae8Map
	 *            医院目录匹配关系缓存
	 * @param kad5Map
	 *            中心基准目录缓存
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public KAE8 filterKAE8Pos(InHospitalDTO inHospitalDTORow, Map kae8Map, Map kad5Map);

	/**
	 * 疾病目录
	 * 
	 * @param aaa027
	 *            医保分中心（统筹区）编码
	 * @param aka120
	 *            疾病编码
	 * @return
	 */
	public List<KA06> queryKA06(String aaa027, String aka120);

	/**
	 * 疾病基准目录
	 * 
	 * @param inHospitalDTORow
	 * @return
	 */
	public KA06 filterKA06(InHospitalDTO inHospitalDTORow);

	/**
	 * 疾病匹配目录
	 * @param inHospitalDTORow
	 * @return
	 */
	public KE06 filterKE06(InHospitalDTO inHospitalDTO);
	/**
	 * 
	 * @param akb020
	 *            医疗机构编码
	 * @return 医院信息
	 */
	public BizYyInfo getAkb020Info(String akb020);

	/**
	 * 
	 * @param akb020
	 *            医疗机构编码
	 * @return 医疗机构所属统筹中心编码
	 */
	public String getAaa027(String akb020);

	/**
	 * 
	 * @param akb020
	 *            医疗机构编码
	 * @return 医疗机构所属行政区划编码
	 */
	public String getBka501(String akb020);

	/**
	 * 
	 * @param akb020
	 *            医疗机构编码
	 * @return 医疗机构所属统筹中心名称
	 */
	public String getAaa027Name(String akb020);

	/**
	 * 
	 * @param akb020
	 *            医疗机构编码
	 * @return 医疗机构名称
	 */
	public String getAkb021(String akb020);

	/**
	 * 
	 * @param bka501
	 *            统筹区编码
	 * @return 所属统筹中心编码
	 */
	public String getAaa027ByBka501(String bka501);

	/**
	 * 
	 * @param bka501
	 *            统筹区编码
	 * @return 所属统筹中心名称
	 */
	public String getAaa027NameByBka501(String bka501);

	/**
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public List<InHospitalDTO> getPersoninfo(InHospitalDTO inHospitalDTO);

	/**
	 * 
	 * @param mediDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getPersoninfoList(MediDTO mediDTO);

	/**
	 * 是否有效的医院编号
	 * 
	 * @param akb020
	 *            医院编号
	 * @return
	 */
	public boolean isValidatedAkb020(String akb020);

	/**
	 * 
	 * @param aac001
	 * @return
	 */
	public String getAac001Ecdemic(String aac001);

	/**
	 * 
	 * @param aac001
	 * @return
	 */
	public String getValueByAac001Ecdemic(String aac001);

	/**
	 * 
	 * @param clazz
	 * @param centerId
	 * @return
	 */
	public <T> T getBeanByCenterId(Class<T> clazz, String centerId);

	/**
	 * 
	 * @param aaz140
	 *            险种编码
	 * @param bka511
	 *            城镇职工对应待遇值
	 * @param bka512
	 *            城乡居民对应待遇值
	 * @return bka513 自付比例
	 */
	public String getBka513ByAae140(String aaz140, String bka511, String bka512);

	/**
	 * api_白名单_开关
	 * 
	 * @param function_id
	 * @return
	 */
	public boolean isApiWhiteList(String function_id);

	/**
	 * 
	 * @param function_id
	 * @return
	 */
	public boolean isCheckRepeatRequestGivenFunctionID(String function_id);

	/**
	 * 获取生育疾病一级目录
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public Boolean isMaternityGradeOneDisease(InHospitalDTO inHospitalDTO);

	/**
	 * 获取医院所属目录中心
	 * 
	 * @param key
	 * @return
	 */
	public String getBke216(String key);

	/**
	 * 
	 * @param a000
	 * @return
	 */
	public String getTheA000(double a000);

	/**
	 * 
	 * @param kad5
	 * @param kae8
	 */
	public void copyPropertiesCatalog(KAD5 kad5, KAE8 kae8);

	

}
