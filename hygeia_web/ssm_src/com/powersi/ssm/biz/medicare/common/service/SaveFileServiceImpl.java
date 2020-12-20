package com.powersi.ssm.biz.medicare.common.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.powersi.hygeia.comm.service.SaveFileService;
import com.powersi.hygeia.framework.util.DBHelper;

public class SaveFileServiceImpl implements SaveFileService {

	@Override
	public String saveFile(File file, String tableName, String bzc001, String bzc002) {
		int iReturn = 0;
		try {
			InputStream input = new FileInputStream(file);
			int iQuery = DBHelper.executeInt("select  * from aze1 where bzc001 = '" + bzc001 + "' ");
			if (iQuery > 0) {
				iReturn = DBHelper.update("update  " + tableName + "  set bzc003 = ? where bzc001 = '" + bzc001 + "'  ",
						new Object[] { input });
			} else {
				iReturn = DBHelper.update("insert into " + tableName + "(bzc001,bzc002,bzc003) " + "values(?,?,?)",
						new Object[] { bzc001, bzc002, input });
			}
			DBHelper.commit();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (iReturn == 1) {
			return "执行成功！";
		} else {
			return "执行失败！";
		}
	}

}
