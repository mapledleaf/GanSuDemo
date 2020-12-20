package com.powersi.ssm.biz;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.comm.service.memory.MemoryDB;
import com.powersi.config.pojo.BizYyInfo;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.test.base.TestBase;

/**
 * 手动刷新biz_yy_info缓存
 * 
 * @author Administrator
 *
 */
public class TestBizYyInfo extends TestBase {
	@Autowired
	private MemoryDB memoryDB;

	@Test
	public void testInitBizYyInfo() {
		try {
			List<BizYyInfo> detail = DBHelper.executeBeanList(BizYyInfo.class, "select * from biz_yy_info ");
			Map<String, BizYyInfo> mapBizRowsLocal = new HashMap<String, BizYyInfo>();
			Iterator<BizYyInfo> iterator = detail.iterator();
			BizYyInfo bizRow = null;
			while (iterator != null && iterator.hasNext()) {
				bizRow = iterator.next();
				mapBizRowsLocal.put(String.valueOf(bizRow.getYybm()), bizRow);
			}
			if (detail != null && detail.size() > 0) {
				this.memoryDB.delete("MAP_BIZ_YY_INFO");
				this.memoryDB.initMap("MAP_BIZ_YY_INFO", 0, mapBizRowsLocal);
			}
			System.out.println("缓存数据(政策参数、统筹区管理)刷新完成(Ok)");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
