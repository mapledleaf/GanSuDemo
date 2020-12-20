package com.powersi.ssm.biz.medicare.common.util;

import com.powersi.biz.medicare.comm.pojo.AA13DTO;
import com.powersi.biz.medicare.comm.service.AA13Service;
import com.powersi.biz.utils.ConstantUtils;
import com.powersi.comm.service.memory.MemoryDB;
import com.powersi.hygeia.comm.service.HygeiaBeanService;
import com.powersi.hygeia.framework.BusiContext;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.pcloud.dict.service.DictService;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 医保业务辅助类
 * 
 * @author 刘勇
 *
 */
public abstract class BizHelper implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param bka501
	 *            统筹区编码
	 * @return 所属统筹中心编码
	 */
	@SuppressWarnings("unchecked")
	public static final String getAaa027ByBka501(String bka501) {
		if (StringUtils.isBlank(bka501)) {
			return null;
		}
		MemoryDB memoryDB = HygeiaBeanService.getInstance().getBeanByClass(MemoryDB.class);
		//2018-02-07 lhy 代码中写的判断memorydb中是否existKey的，需全部修改为get后判断是否为null来处理。
		//用existKey会无法从二级缓存中获取，而会到服务器上获取，业务量大了以后会影响性能。
		/*	if (!memoryDB.existKey(AA13Service.MAP_HYGEIA_BASE_AA13)) {
			throw new HygeiaException("memoryDB找不到统筹区信息" + AA13Service.MAP_HYGEIA_BASE_AA13);
		}*/
		List<AA13DTO> aA13DTORows = (List<AA13DTO>) memoryDB.getList(AA13Service.MAP_HYGEIA_BASE_AA13, 0, -1);
		if (aA13DTORows == null || aA13DTORows.size() == 0) {
			throw new HygeiaException("memoryDB找到统筹区信息为空" + AA13Service.MAP_HYGEIA_BASE_AA13);
		}
		for (AA13DTO aA13DTORow : aA13DTORows) {
			if (bka501.equals(aA13DTORow.getBka501())) {
				if (StringUtils.isNotBlank(aA13DTORow.getAaa027())) {
					return aA13DTORow.getAaa027();
				} else {
					throw new HygeiaException("memoryDB找到所属统筹中心信息为空，统筹区编码：" + bka501);
				}
			}
		}
		throw new HygeiaException("memoryDB找不到所属统筹中心信息，统筹区编码：" + bka501);
	}

	/**
	 * 
	 * @param bka501
	 *            统筹区编码
	 * @return 所属统筹中心名称
	 */
	@SuppressWarnings("unchecked")
	public static final String getAaa027NameByBka501(String bka501) {
		if (StringUtils.isBlank(bka501)) {
			return null;
		}
		String aaa027 = getAaa027ByBka501(bka501);
		if (StringUtils.isBlank(aaa027)) {
			return null;
		}
		MemoryDB memoryDB = HygeiaBeanService.getInstance().getBeanByClass(MemoryDB.class);
		//2018-02-07 lhy 代码中写的判断memorydb中是否existKey的，需全部修改为get后判断是否为null来处理。
		//用existKey会无法从二级缓存中获取，而会到服务器上获取，业务量大了以后会影响性能。
		/*if (!memoryDB.existKey(AA13Service.MAP_HYGEIA_BASE_AA13)) {
			return aaa027;
		}*/
		List<AA13DTO> aA13DTORows = (List<AA13DTO>) memoryDB.getList(AA13Service.MAP_HYGEIA_BASE_AA13, 0, -1);
		if (aA13DTORows == null || aA13DTORows.size() == 0) {
			return aaa027;
		}
		for (AA13DTO aA13DTORow : aA13DTORows) {
			if (aaa027.equals(aA13DTORow.getBka501())) {
				return aA13DTORow.getAaa129();
			}
		}
		return aaa027;
	}

	/**
	 * 
	 * @return 登陆人工号
	 */
	public static final String getLoginUser() {
		return UtilFunc.getString(BusiContext.getUserInfo(), "login_user", "");
	}

	/**
	 * 
	 * @return 登陆人
	 */
	public static final String getUserName() {
		return UtilFunc.getString(BusiContext.getUserInfo(), "user_name", getLoginUser());
	}

	/**
	 * 
	 * @return 当前用户所在定点医疗机构编号
	 */
	public static final String getAkb020() {
		return UtilFunc.getString(BusiContext.getUserInfo(), "akb020", "");
	}

	/**
	 * 
	 * @return 当前用户所在定点医疗机构名称
	 */
	public static final String getAkb021() {
		return UtilFunc.getString(BusiContext.getUserInfo(), "akb021", getAkb020());
	}

	/**
	 * <pre>
	440000 广东省
	440100 广州市
	440200 韶关市
	440300 深圳市
	440400 珠海市
	440500 汕头市
	440600 佛山市
	441800 清远市
	 * </pre>
	 * 
	 * @return 定点医疗机构所在行政区划代码
	 */
	public static final String getAaa027() {
		return UtilFunc.getString(BusiContext.getUserInfo(), "aaa027", "");
	}

	/**
	 * 
	 * @return 定点医疗机构所在行政区划名称
	 */
	@SuppressWarnings("unchecked")
	public static final String getAaa027Name() {
		String aaa027 = getAaa027();
		if (StringUtils.isBlank(aaa027)) {
			return null;
		}
		MemoryDB memoryDB = HygeiaBeanService.getInstance().getBeanByClass(MemoryDB.class);
		//2018-02-07 lhy 代码中写的判断memorydb中是否existKey的，需全部修改为get后判断是否为null来处理。
		//用existKey会无法从二级缓存中获取，而会到服务器上获取，业务量大了以后会影响性能。
		/*if (!memoryDB.existKey(AA13Service.MAP_HYGEIA_BASE_AA13)) {
			return aaa027;
		}*/
		List<AA13DTO> aA13DTORows = (List<AA13DTO>) memoryDB.getList(AA13Service.MAP_HYGEIA_BASE_AA13, 0, -1);
		if (aA13DTORows == null || aA13DTORows.size() == 0) {
			return aaa027;
		}
		for (AA13DTO aA13DTORow : aA13DTORows) {
			if (aaa027.equals(aA13DTORow.getBka501())) {
				return aA13DTORow.getAaa129();
			}
		}
		return aaa027;
	}

	/**
	 * 
	 * @return 职工基本医疗保险
	 */
	public static final String getAae140CodeWorkers() {
		return "310";
	}

	/**
	 * 
	 * @return 职工基本医疗保险
	 */
	public static final String getAae140NameWorkers() {
		return "职工基本医疗保险";
	}

	/**
	 * 
	 * @return 居民基本医疗保险
	 */
	public static final String getAae140CodeResident() {
		return "391";
	}

	/**
	 * 
	 * @return 居民基本医疗保险
	 */
	public static final String getAae140NameResident() {
		return "居民基本医疗保险";
	}

	/**
	 * 
	 * @return 住院业务编码
	 */
	public static final String getAka130CodeInhosp() {
		return "12";
	}

	/**
	 * 
	 * @return 住院业务名称
	 */
	public static final String getAka130NameInhosp() {
		return "住院";
	}

	/**
	 * NTS20090900125 中心端和医院端增加病案首页质控结果展示 杨世明 20200909
	 * 获取病案质控信息
	 * @return 病案质控信息URL链接
	 */
	public static String getQualityUrl(){
		DictService dictService = HygeiaBeanService.getInstance().getBeanByClass(DictService.class);
		String qualityUrl = dictService.getValue("HISAPI_BIZ_PARAM", "QUALITY_URL",
			"http://10.137.69.96:20001/rers_ui/#/drgQcGroupResults/qcManage/qcBtchInfo?");
		MemoryDB memoryDB = HygeiaBeanService.getInstance().getBeanByClass(MemoryDB.class);
		String centerYybm = (String) memoryDB.getMapValue("MAP_BIZ_YY_MATCH_PCLOUD_CENTER", ConstantUtils.CAA027 + "_" + getAkb020());
		qualityUrl += centerYybm;
		return qualityUrl;
	}

	/**
	 * NTS20092200037 医院端住院信息查询窗口的查询医保结算清单功能优化 杨世明 20200918
	 * 获取医保结算清单
	 * @return 医保结算清单URL链接
	 */
	public static String getSettlementUrl() {
		DictService dictService = HygeiaBeanService.getInstance().getBeanByClass(DictService.class);
		return dictService.getValue("HISAPI_BIZ_PARAM", "SETTLEMENT_URL",
				"http://10.137.69.96:20001/rers_ui/#/baseInfo/setlListManagecopy?");
	}
}
