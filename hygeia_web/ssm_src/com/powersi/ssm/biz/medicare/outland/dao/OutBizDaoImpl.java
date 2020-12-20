package com.powersi.ssm.biz.medicare.outland.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.powersi.biz.medicare.outland.pojo.OutBizDTO;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.SqlHelper;

/**
 * 
 * @author 董孝辉
 *
 */
@Service
public class OutBizDaoImpl implements OutBizDao {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Override
	public List getCityAndDept(String akb020, OutBizDTO outBizDTO) {
		StringBuffer strB = new StringBuffer();
		strB.append(" select bkd324,bkd325 from kch1 ");
		strB.append(" where 1=1 ");
		if (outBizDTO.getBkd327() != "" && outBizDTO.getBkd327() != null) {
			strB.append(" and bkd327 = '" + outBizDTO.getBkd327() + "' ");
		}
		if (outBizDTO.getBkd326() != "" && outBizDTO.getBkd326() != null) {
			strB.append(" and bkd326 = '" + outBizDTO.getBkd326() + "' ");
		}
		strB.append(" ORDER BY bkd325  ASC ");
		return DBHelper.executeList(strB.toString());
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map getCityRegion(String bkd325) {
		List params = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from kch1 where bkd325 != bkd326");
		SqlHelper.addString(bkd325, "bkd325", "=", sql, params);
		return DBHelper.executeMap(sql.toString(), params.toArray());
	}

}