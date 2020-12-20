package com.powersi.ssm.biz.medicare.jdreport.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import com.powersi.biz.medicare.hosp.pojo.JdReportDTO;
import com.powersi.biz.medicare.hosp.pojo.JdReportHisDTO;
import com.powersi.hygeia.framework.util.DAOHelper;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.SqlHelper;
import com.powersi.hygeia.framework.util.UtilFunc;

/**
 * 
  * @ClassName: ApiJdReportDaoImpl
  * @Description: api接口开发与联调进度上报DAO实现类
  * @author Comsys-asus
  * @date 2017年2月22日 下午7:01:29
  *
 */
public class ApiJdReportDaoImpl implements ApiJdReportDao {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Map> queryJdReportMb() {
		StringBuilder sql = new StringBuilder("select typeid,typevalue,fid from yw_type_item where level ='3'");
		return DBHelper.executeList(sql.toString(), null);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<JdReportDTO> queryJdReport(JdReportDTO dto) {
		List params = new ArrayList();
		StringBuilder sql = new StringBuilder("select id, level1_id, level1_value, level2_id, level2_value, level3_id, level3_value,"
				+ "plan_complete_time, develop_result,debug_result, ywno,yybm, create_time, remark,his_developers from yw_jd_report where 1=1");
		SqlHelper.addString(dto.getYybm(), "yybm", "=", sql, params);
		SqlHelper.addString(dto.getLevel2_id(), "level2_id", "=", sql, params);
		sql.append(" ORDER BY level2_id");
		return (List<JdReportDTO>)DBHelper.executeList(sql.toString(),UtilFunc.toArray(params));
	}

	@Transactional
	@Override
	public int saveJdReport(JdReportDTO dto, List<JdReportDTO> list) {
		List<JdReportDTO> reportList =  this.queryJdReport(dto);
		
		if(!UtilFunc.isEmpty(reportList)){
			String uuid = UUID.randomUUID().toString();
			List<JdReportHisDTO> reportHisList = new ArrayList<>();
			for (int i = 0; i < reportList.size(); i++) {
				JdReportHisDTO jdReportHisDTO = new JdReportHisDTO();
				com.powersi.ssm.biz.medicare.jdreport.util.ZsBeanHelper.copyProperties(reportList.get(i), jdReportHisDTO,true);
				jdReportHisDTO.setId(UUID.randomUUID().toString());
				jdReportHisDTO.setCreate_time(new Date());
				jdReportHisDTO.setReport_id(uuid);
				reportHisList.add(jdReportHisDTO);
			}
			//插入历史
			DAOHelper.insert("yw_jd_report_his", reportHisList);
			Map<String, String> parMap = new HashMap<String, String>();
			parMap.put("yybm", dto.getYybm());
			DAOHelper.delete("yw_jd_report", parMap, new String[]{"yybm"});
		}
		
		for (JdReportDTO jdReportDTO : list) {
			jdReportDTO.setId(UUID.randomUUID().toString());
			jdReportDTO.setYybm(dto.getYybm());
			jdReportDTO.setCreate_time(new Date());
			jdReportDTO.setHis_developers(dto.getHis_developers());
		}
		return DAOHelper.insert("yw_jd_report", list);
	}

}
