package com.powersi.biz.medicare.special.service;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.special.pojo.SpecialManagerDTO;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface SpecialManagerService {
	
	
	/**
	 * <p>Title: getSpeAppInfList</p>  
	 * <p>Description: 分页查询特殊业务申请</p>  
	 * @author lb
	 * @date 2018年7月11日  
	 * @param dto
	 * @return List 申请集合
	 */
	public ListResult getSpeAppInfList(SpecialManagerDTO dto);
	
	
	/**
	 * Query 申请列表查询（LOG）.
	 *
	 * @param param the param
	 * @return the list
	 */
	public List getSpeAppInfListLog(SpecialManagerDTO dto);
	
	
	/**
	 * Query 申请详细信息查询.
	 *
	 * @param param the param
	 * @return the list
	 */
	public SpecialManagerDTO getSpeAppInfDetail(SpecialManagerDTO dto);
	
	
	/**
	 * Query 申请详细信息查询（LOG）.
	 *
	 * @param param the param
	 * @return the list
	 */
	public Map getSpeAppInfDetailLog(SpecialManagerDTO dto);
	
	
	/**
	 * <p>Title: addSpeAppInf</p>  
	 * <p>Description: 申请新增保存</p>  
	 * @author lb
	 * @date 2018年7月10日  
	 * @param dto
	 * @return aaz267
	 * @throws ParseException 
	 */
	public SpecialManagerDTO addSpeAppInf(SpecialManagerDTO dto) throws ParseException;
	
	
	/**
	 * <p>Title: deleteSpeAppInf</p>  
	 * <p>Description: 删除申请</p>  
	 * @author lb
	 * @date 2018年7月11日  
	 * @param dto
	 * @return 
	 */
	public SpecialManagerDTO deleteSpeAppInf(SpecialManagerDTO dto);
	
	
	/**
	 * <p>Title: modifySpeAppInf</p>  
	 * <p>Description: 申请修改</p>  
	 * @author lb
	 * @date 2018年7月18日  
	 * @param dto
	 * @return
	 */
	public SpecialManagerDTO modifySpeAppInf(SpecialManagerDTO dto);
	
	
	/**
	 * <p>Title: addSpeAppInfBatch_131</p>  
	 * <p>Description: </p>  
	 * @author lb
	 * @date 2018年7月10日  
	 * @param dto
	 * @return
	 */
	public int addSpeAppInfBatch_131(SpecialManagerDTO dto);
	
	
	/**
	 * Query 获取特殊申请关联的业务基本信息列表.
	 *
	 * @param param the param
	 * @return the list
	 */
	public List getBizWithSpeApp(SpecialManagerDTO dto);
	
	
	/**
	 * 
	 * <p>Title: getOtherAppInfList</p>  
	 * <p>Description: 获取申请关联的定点医疗机构、药品/项目、疾病信息。用comFlag区分</p>  
	 * @author lb
	 * @date 2018年7月12日  
	 * @param drugDto
	 * @return List 定点医疗机构、药品/项目、疾病信息
	 */
	public ListResult getOtherAppInfList(SpecialManagerDTO dto);
	
	
	/**
	 * 
	 * <p>Title: getdrugInfList</p>  
	 * <p>Description: 获取药品项目信息</p>  
	 * @author lb
	 * @date 2018年7月12日  
	 * @param drugDto
	 * @return List 药品/项目
	 */
	public ListResult getdrugInfList(SpecialManagerDTO dto);
	
	
	/**
	 * <p>Title: getDiseaseInfList</p>  
	 * <p>Description: 获取疾病信息</p>  
	 * @author lb
	 * @date 2018年7月13日  
	 * @param dto
	 * @return
	 */
	public ListResult getDiseaseInfList(SpecialManagerDTO dto);
	
	
	/**
	 * <p>Title: getDiseaseInfList</p>  
	 * <p>Description: 查询疾病分型限额相关</p>  
	 * @author lb
	 * @date 2018年7月13日  
	 * @param dto
	 * @return
	 */
	public List getDiseaseLimit(SpecialManagerDTO dto);
	
	
	/**
	 * <p>Title: getHospInfList</p>  
	 * <p>Description: 获取医疗机构信息</p>  
	 * @author lb
	 * @date 2018年7月14日  
	 * @param dto
	 * @return
	 */
	public ListResult getHospInfList(SpecialManagerDTO dto);
	
	
	/**
	 * <p>Title: getApplyPrintInfo</p>  
	 * <p>Description: 获取模板路径和申请数据</p>  
	 * @author lb
	 * @date 2018年7月25日  
	 * @param dto
	 * @return
	 */
	public Map getApplyPrintInfo(SpecialManagerDTO dto) throws Exception;
	
	
	/**
	 * <p>Title: loadJsdModule</p>  
	 * <p>Description: 生成打印文件</p>  
	 * @author lb
	 * @date 2018年8月4日  
	 * @param mData
	 * @param reportXLS
	 * @return
	 */
	public String loadJsdModule(SpecialManagerDTO dto);
		
	
	/**
	 * <p>Title: getTemplatePath</p>  
	 * <p>Description: 返回模板路径</p>  
	 * @author lb
	 * @date 2018年7月30日  
	 * @param dto
	 * @return
	 */
	public String getTemplatePath(SpecialManagerDTO dto) throws Exception;
	
	
	/**
	 * <p>Title: uploadBatchAddFile</p>  
	 * <p>Description: </p>  
	 * @author lb
	 * @date 2018年7月31日  
	 * @param fileMap
	 * @return
	 */
	public Map analysisBatchAddFile(SpecialManagerDTO dto, Map fileMap) throws Exception;

	
	/**
	 * <p>Title: verifyAfterQuery</p>  
	 * <p>Description: 在查询人员之后校验信息</p>  
	 * @author tjb
	 * @date 2018年8月1日  
	 * @param dto
	 * @return
	 */
	public SpecialManagerDTO verifyAfterQuery(SpecialManagerDTO dto);
	
	/**
	 * 
	 * @code:TS18102300165 - 【需求开发】湖南城乡一体化医保项目系统研发
	 * @Title:getPersonInfo
	 * @Description:获取人员信息，由于结算云没有统一的获取人员控件
	 * @author tjb
	 * @date 2018年10月23日
	 * @param dto
	 * @return      
	 * List<Map>
	 */
	public List<Map> getPersonInfo(SpecialManagerDTO dto);
	
	/**
	 * 
	 * @code:TS18102300165 - 【需求开发】湖南城乡一体化医保项目系统研发
	 * @Title:getPrintData
	 * @Description:得到打印数据
	 * @author tjb
	 * @date 2018年11月1日
	 * @param dto
	 * @return      
	 * Map
	 */
	public Map getPrintData(SpecialManagerDTO dto);
	
	/**
	 * 
	 * @code:TS18111200077 - 【需求开发】湖南城乡一体化医保项目系统研发
	 * @Title:getPolicys
	 * @Description:得到政策值
	 * @author tjb
	 * @date 2018年11月12日
	 * @param dto
	 * @return      
	 * Map<String,String>
	 */
	public Map getPolicys(SpecialManagerDTO dto);
	
	/**
	 * <p>Title: queryBizFin</p>  
	 * <p>Description: 获取住院信息</p>  
	 * @author lb
	 * @date 2018年7月14日  
	 * @param dto
	 * @return
	 */
	public ListResult queryBizFin(SpecialManagerDTO dto);

	/**
	 * @Description: 获取人员信息集合
	 * @author: xiexiao
	 * @date: 2019年1月17日
	 * @param param
	 * @return
	 */
	public List getPersonInfo(Map param);
	
	/**
	 * TS19082800090 - 【需求开发】结算云在A医院做了普通住院申请，没有在A医院登记住院，想在B医院住院时做申请提示申请时间重叠，无法再次申请。  zhuli 2019.08.28
	 * @Description 普通住院申请新增前校验
	 * @author Zhu Li
	 * @Date 2019年8月28日下午2:52:02
	 * @param dto
	 * @return
	 */
	public Map verifyRepeat(SpecialManagerDTO dto);

	/**
	 * 查询门特资料文件信息
	 *
	 * @param dto 门特申请信息
	 * @return 文件信息
	 */
	List<Map> getKcc6Info(SpecialManagerDTO dto);

	/**
	 * 医院审核门特资料文件信息
	 *
	 * @param dto 门特申请信息
	 */
    void updateKcc6(SpecialManagerDTO dto);
}
