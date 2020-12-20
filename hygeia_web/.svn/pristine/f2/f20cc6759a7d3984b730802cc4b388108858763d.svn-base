package com.powersi.ssm.biz.medicare.medicarepay.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.powersi.biz.medicare.medicarepay.pojo.MedicalVillagePayDTO;
import com.powersi.biz.medicare.medicarepay.pojo.VillageRationDTO;
import com.powersi.biz.medicare.medicarepay.service.MedicarePayEsbService;
import com.powersi.biz.medicare.util.Utils;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;

import net.sf.json.JSONArray;

/**
 * @author dannie
 *
 *类说明：乡镇对村卫生室拨付统计控制类
 *@date 2018年11月25日下午4:01:02
 */
@Action(value = "MedicalVillagePayAction", results = {
		@Result(name = "initPage", location = "/pages/biz/medicare/medicarepay/medicalVillagePay.jsp"),
		@Result(name = "initRation", location = "/pages/biz/medicare/medicarepay/villageRationManage.jsp"),
		@Result(name = "queryPayTypeEdit", location = "/pages/biz/medicare/medicarepay/villageRationEdit.jsp"),
		@Result(name = "queryPayTypeEdit_gr", location = "/pages/biz/medicare/medicarepay/villageRationEdit_gr.jsp")
})
public class MedicalVillagePayAction extends MedicarepayAction {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MedicalVillagePayDTO dto;
	VillageRationDTO rationDto;
	public MedicalVillagePayDTO getDto() {
		return dto;
	}
	public void setDto(MedicalVillagePayDTO dto) {
		this.dto = dto;
	}
	public VillageRationDTO getRationDto() {
		return rationDto;
	}
	public void setRationDto(VillageRationDTO rationDto) {
		this.rationDto = rationDto;
	}
	
	/**
	 * @return
	 *方法说明：初始化加载乡镇对村卫生室的拨付统计页面
	 *@date 2018年11月25日下午4:23:53
	 *@author dannie
	 */
	public String init() {
		return "initPage";
	}
	
	/**
	 * @return
	 *方法说明：乡镇对村卫生室拨付统计
	 *@date 2018年11月25日下午4:25:37
	 *@author dannie
	 */
	public String getPayData() {
		try {
			dto.setAkb020(BizHelper.getAkb020());
			List<Map<String,Object>> rtList = hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, dto.getAkb020()).getPayData_village(dto);
			if(rtList == null || rtList.size() < 1) {
				setJSONReturn("no");
			}else {
				setJSONReturn(rtList);
			}
		} catch (Exception e) {
			saveJSONError(e);
			return NONE;
		} 
		return NONE;
	}
	
	/**
	 * @return
	 *方法说明：获取拨付明细
	 *@date 2018年11月26日上午10:03:57
	 *@author dannie
	 */
	public String getDetailData() {
		try {
			dto.setAkb020(BizHelper.getAkb020());
			dto.setAkb021(BizHelper.getAkb021());
			Map<String, Object> mData = new HashMap<String, Object>();
			mData = hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, dto.getAkb020()).getDetailData_village(dto);
			setJSONReturn(mData);
		} catch (Exception e) {
			saveJSONError(e);
			return NONE;
		} 
		return NONE;
	}

	/**
	 * @return
	 *方法说明：保存乡镇对村卫生室的拨付数据
	 *@date 2018年11月26日上午10:04:01
	 *@author dannie
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public String saveData() {
		try {
			Map<String, Object> mData = new HashMap<String, Object>();

			dto.setAkb020(BizHelper.getAkb020());
			dto.setAkb021(BizHelper.getAkb021());
			dto.setDaa027(BizHelper.getAaa027());
			dto.setAaz262(BizHelper.getLoginUser());
			dto.setAae011(BizHelper.getUserName());
			JSONArray jsonArray = JSONArray.fromObject(dto.getBizinfo()==null?"":dto.getBizinfo());
			List<Map<String, Object>> lstBiz = JSONArray.toList(jsonArray, HashMap.class);
			dto.setLstBiz(lstBiz);
			
			//计算并保存结算数据
			mData = hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, dto.getAkb020()).saveData_village(dto);
			
			setJSONReturn(mData);
		} catch (Exception e) {
			saveJSONError(e);
			return NONE;
		} 
		return NONE;
	}

	/**
	 * @return
	 *方法说明：更新乡镇对村卫生室的拨付状态
	 *@date 2018年11月26日上午10:04:05
	 *@author dannie
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public String updData() {
		try {
			dto.setAkb020(BizHelper.getAkb020());
			
			JSONArray jsonArray = JSONArray.fromObject(dto.getBizinfo()==null?"":dto.getBizinfo());
			List<Map<String, Object>> lstBiz = JSONArray.toList(jsonArray, HashMap.class);
			dto.setLstBiz(lstBiz);
			
			hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, dto.getAkb020()).updData_village(dto);
			
			setJSONReturn("ok");
		} catch (Exception e) {
			saveJSONError(e);
			return NONE;
		} 
		return NONE;
	}
	
	/**
	 * @return
	 *方法说明：初始化村卫生室定额维护界面
	 *@date 2018年11月28日下午7:58:03
	 *@author dannie
	 */
	@SuppressWarnings("unchecked")
	public String initRation() {
		rationDto = new VillageRationDTO();
		rationDto.setAkb020(BizHelper.getAkb020());
		rationDto.setOperType("init");
		Map<String,Object> rtMap = hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, rationDto.getAkb020()).getKct1Data(rationDto);
		//获取村卫生室列表
		List<Map<String,Object>> villagelist = (List<Map<String, Object>>) rtMap.get("datalist");
		setAttribute("village_list", villagelist);
		return "initRation";
	}
	
	/**
	 * @return
	 *方法说明：获取kct1数据列表
	 *@date 2018年11月28日下午7:45:11
	 *@author dannie
	 */
	@SuppressWarnings("unchecked")
	public String getRationData() {
		try {
			rationDto.setAkb020(BizHelper.getAkb020());
			rationDto.setOperType("query");
			Map<String,Object> rtMap = hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, rationDto.getAkb020()).getKct1Data(rationDto);
			List<Map<String,Object>> rtList = (List<Map<String, Object>>) rtMap.get("datalist");
			if(rtList == null || rtList.size() < 1) {
				setJSONReturn("no");
			}else {
				setJSONReturn(rtList);
			}
		} catch (Exception e) {
			saveJSONError(e);
			return NONE;
		} 
		return NONE;
	}
	
	/**
	 * @return
	 *方法说明：加载kct1新增或者修改页面
	 *@date 2018年11月28日下午7:45:14
	 *@author dannie
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String queryPayTypeEdit() {
		try {
			String ration_type = this.getParameter("ration_type")==null?"1":this.getParameter("ration_type");
			if (!UtilFunc.isNotEmpty(rationDto)) {
				rationDto = new VillageRationDTO();
			}
			if(rationDto.getRation_type() == null || "".equals(rationDto.getRation_type())) {
				rationDto.setRation_type(ration_type);
			}
			rationDto.setAkb020(BizHelper.getAkb020());
			// kct1id有值时，页面为修改，给medicalPayTypeDto赋值
			if (UtilFunc.isNotEmpty(rationDto.getKct1id())) {
				rationDto.setOperType("update");
				Map<String,Object> rtMap = hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, rationDto.getAkb020()).getKct1Data(rationDto);

				//获取村卫生室列表
				List<Map<String,Object>> villagelist = (List<Map<String, Object>>) rtMap.get("villagelist");
				setAttribute("village_list", villagelist);
				
				List<Map<String,Object>> datalist = (List<Map<String, Object>>) rtMap.get("datalist");
				if (UtilFunc.isNotEmpty(datalist)) {
					Map map = (Map) datalist.get(0);
					Utils.mapToBean(map, rationDto, true);
				}
				this.setAttribute("village_no", rationDto.getVillage_no());
			}else {
				rationDto.setOperType("init");
				Map<String,Object> rtMap = hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, rationDto.getAkb020()).getKct1Data(rationDto);

				//获取村卫生室列表
				List<Map<String,Object>> villagelist = (List<Map<String, Object>>) rtMap.get("datalist");
				setAttribute("village_list", villagelist);
			}
			if("1".equals(rationDto.getRation_type())) {//村卫生室定额维护
				return "queryPayTypeEdit";
			}else {//参保人定额维护
				return "queryPayTypeEdit_gr";
			}
		} catch (Exception ex) {
			return renderError(ex);
		}
	}
	
	/**
	 * @return
	 *方法说明：新增kct1
	 *@date 2018年11月28日下午7:47:35
	 *@author dannie
	 */
	public String addPayTypeData() {
		try {
			rationDto.setAkb020(BizHelper.getAkb020());
			rationDto.setAaz262(BizHelper.getLoginUser());
			rationDto.setAae011(BizHelper.getUserName());
			hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, rationDto.getAkb020()).insertKct1(rationDto);
			
			setJSONReturn("ok");
		} catch (Exception e) {
			saveJSONError(e);
			return NONE;
		} 
		return NONE;
	}
	
	/**
	 * @return
	 *方法说明：变更kct1
	 *@date 2018年11月28日下午7:47:35
	 *@author dannie
	 */
	public String updPayTypeData() {
		try {
			rationDto.setAkb020(BizHelper.getAkb020());
			rationDto.setAaz262(BizHelper.getLoginUser());
			rationDto.setAae011(BizHelper.getUserName());
			hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, rationDto.getAkb020()).updateKct1(rationDto);
			
			setJSONReturn("ok");
		} catch (Exception e) {
			saveJSONError(e);
			return NONE;
		} 
		return NONE;
	}
	
	/**
	 * @return
	 *方法说明：删除kct1
	 *@date 2018年11月28日下午7:47:35
	 *@author dannie
	 */
	public String delPayTypeData() {
		try {
			rationDto.setAkb020(BizHelper.getAkb020());
			
			hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, rationDto.getAkb020()).deleteKct1(rationDto);
			
			setJSONReturn("ok");
		} catch (Exception e) {
			saveJSONError(e);
			return NONE;
		} 
		return NONE;
	}
}
