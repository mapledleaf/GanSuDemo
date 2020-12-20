package com.powersi.ssm.biz.medicare.medicarepay.dao;

import java.util.Map;

import com.powersi.hygeia.framework.util.DAOHelper;

public class MedicarepayDAOImpl implements MedicarepayDAO {

	@Override
	public int insertCodetype(Map<String, Object> codetype) {
		return DAOHelper.insert("sys_code_table", codetype);
	}

}
