package com.powersi.biz.medicare.personalizationinter.service;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.diagnose.pojo.DiagnoseInfoDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.personalizationinter.pojo.JA;
import com.powersi.biz.medicare.personalizationinter.pojo.JC;

/**
 * 个性化接口服务类
 * 
 * @author 刘勇
 *
 */
public interface PersonalizationInterfaceService {

	/**
	 * 就诊登记回参
	 */
	public String JZDJHC = "JZDJHC";

	/**
	 * 结算结果回参
	 */
	public String JSJGHC = "JSJGHC";

	/**
	 * 
	 */
	public String prefixKey = "CLIENT";

	/**
	 * 缓存读写锁_就诊登记信息
	 */
	public Object cacheReadOrWriteLockJZDJHC = new Object();

	/**
	 * 缓存读写锁_结算结果信息
	 */
	public Object cacheReadOrWriteLockJSJGHC = new Object();

	/**
	 * 是否需要回传医院（个性化接口）
	 * 
	 * @param hctype
	 *            JZDJHC JSJGHC
	 * @param akb020
	 *            医院编码
	 * @return
	 */
	public boolean isNeedHCHosp(String hctype, String akb020);

	/**
	 * CLIENT_JZDJHC_YYBM
	 * 
	 * @param akb020
	 * @param ja
	 * @param inHospitalDTO
	 */
	public void addJZDJHC(String akb020, JA ja, InHospitalDTO inHospitalDTO);

	/**
	 * CLIENT_JSJGHC_YYBM
	 * 
	 * @param akb020
	 * @param jc
	 * @param inHospitalDTO
	 */
	public void addJSJGHC(String akb020, JC jc, InHospitalDTO inHospitalDTO);

	/**
	 * 
	 * @param hctype
	 * @param yybm
	 * @return
	 */
	public List<String> findHCList(String hctype, String yybm);

	/**
	 * CLIENT_JZDJHC_YYBM
	 * 
	 * @param akb020
	 * @param ja
	 * @param diagnoseInfoDTO
	 */
	public  void addJZDJHC(String akb020, JA ja, DiagnoseInfoDTO diagnoseInfoDTO);
	
	/**
	 * CLIENT_JSJGHC_YYBM
	 * 
	 * @param akb020
	 * @param jc
	 * @param diagnoseInfoDTO
	 */
	public void addJSJGHC(String akb020, JC jc, DiagnoseInfoDTO diagnoseInfoDTO,Map<String,Object> mPayInfo);

}
