package com.powersi.ssm.biz.medicare.jdreport.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.hosp.pojo.JdReportDTO;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.BeanHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.ssm.biz.medicare.jdreport.dao.ApiJdReportDao;

/**
 * 
  * @ClassName: ApiJdReportServiceImpl
  * @Description: API接口开发与联调进度上报服务实现类
  * @author zhos
  * @date 2017年2月22日 下午6:57:37
  *
 */
public class ApiJdReportServiceImpl implements ApiJdReportService {

	private static ApiJdReportDao dao = BeanHelper.getBean(ApiJdReportDao.class);
	
	@Override
	public int saveJdReportInfo(JdReportDTO dto, List<Map<String, Object>> list) {
		int iReturn = -1;
		try {
			List<JdReportDTO> paramList = new ArrayList<>();
			for (Map<String,Object> map : list) {
				JdReportDTO jdReportDTO = new JdReportDTO();
				com.powersi.ssm.biz.medicare.jdreport.util.ZsBeanHelper.copyProperties(map, jdReportDTO,true);
				paramList.add(jdReportDTO);
			}
			iReturn = dao.saveJdReport(dto,paramList);
		} catch (Exception e) {
			iReturn = -1;
			throw new HygeiaException(e);
		}
		
		return iReturn;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map getJdReportInfo(JdReportDTO dto) {
		Map result = new HashMap<>();
		
		List<JdReportDTO> list = dao.queryJdReport(dto);
		List <Map> listMap = new ArrayList<>();
		Map<String,Object> map = new HashMap<>();
		
		if(!UtilFunc.isEmpty(list)){
			for (int i = 0; i < list.size(); i++) {
				map = new HashMap<>();
				com.powersi.ssm.biz.medicare.jdreport.util.ZsBeanHelper.copyProperties(list.get(i), map,true);
				listMap.add(map);
			}
			
		}else{
			//未查询到进度上报信息，查需模板
			List<Map> listmb = dao.queryJdReportMb();
			if(!UtilFunc.isEmpty(listmb)){
				for (int i = 0; i < listmb.size(); i++) {
					map = new HashMap<>();
					map.put("level3_id", listmb.get(i).get("typeid"));
					map.put("level3_value", listmb.get(i).get("typevalue"));
					map.put("plan_complete_time", "");
					map.put("develop_result", "0");
					map.put("debug_result", "0");
					map.put("ywno", "");
					map.put("remark", "");
					map.put("level1_id", "1");
					map.put("level1_value", "API接口");
					if("11".equals(listmb.get(i).get("fid"))){
						map.put("level2_id", "11");
						map.put("level2_value", "门诊就医业务");
					}else if("12".equals(listmb.get(i).get("fid"))){
						map.put("level2_id", "12");
						map.put("level2_value", "住院就医业务");
					}else if("13".equals(listmb.get(i).get("fid"))){
						map.put("level2_id", "13");
						map.put("level2_value", "病案信息上传");
					}else if("14".equals(listmb.get(i).get("fid"))){
						map.put("level2_id", "14");
						map.put("level2_value", "医疗费用审核申诉");
					}else if("15".equals(listmb.get(i).get("fid"))){
						map.put("level2_id", "15");
						map.put("level2_value", "医院预约挂号");
					}else if("16".equals(listmb.get(i).get("fid"))){
						map.put("level2_id", "16");
						map.put("level2_value", "其他接口");
					}
					
					listMap.add(map);
				}
				
			}
		}
		
		if(!UtilFunc.isEmpty(listMap)){
			result.put("reportList", listMap);
			Map maptemp = new HashMap<>();
			maptemp.put("his_developers", listMap.get(0).get("his_developers"));
			result.put("his_developers", maptemp);
		}
		
		return result;
	}

}
