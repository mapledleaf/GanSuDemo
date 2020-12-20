package com.powersi.sys.manager.action;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.comm.pojo.ListResultDTO;
import com.powersi.comm.service.memory.MemoryDB;
import com.powersi.hygeia.framework.redis.Redis;
import com.powersi.hygeia.framework.redis.RedisHelper;
import com.powersi.hygeia.web.util.DataGridHelper;
import com.powersi.hygeia.web.util.PagerHelper;
import com.powersi.ssm.biz.medicare.inhospital.action.BaseInhospitalManagerAction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Action(value = "RedisInfo", results = {
		@Result(name = "success", location = "/pages/sys/manager/RedisInfo.jsp")
})
public class RedisInfoAction extends BaseInhospitalManagerAction{
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MemoryDB memoryDB;
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String execute() throws Exception {
		PagerHelper.initPagination(this.getRequest());
		int index = PagerHelper.getPaginationObj().getPageIndex();
		int size = PagerHelper.getPaginationObj().getPageSize();
		int count = 0;
		
		String key = this.getSearchTerm();
		Set<String> keys=RedisHelper.use("sessionRedis").keys("*"+key+"*");
		List<Map> list = new ArrayList<>();
		if (keys.size() > 0) {
			Iterator<String> it = keys.iterator();
			while (it.hasNext()) {
				String subKey = it.next();
				count++;
				if(count>=(index*size) && count<((index+1)*size)) {
					Map keyInfo = new HashMap<>();
					keyInfo.put("key",subKey);
					keyInfo.put("type",RedisHelper.use("sessionRedis").type(subKey));
					list.add(keyInfo);
				}
			}
		}
		
		ListResult listResult = ListResultDTO.newInstance()
				.setCount(keys.size()).setList(list);
		PagerHelper.getPaginationObj().setCount(listResult.getCount());
		DataGridHelper.render(this.getRequest(), this.getResponse(),
				PagerHelper.getPaginatedList(listResult.getList()));
		return NONE;
	}
	
	/**
	 * 获取key对应的值
	 * @return
	 */
	public String queryDeital() {
		String key = getParameter("key");
		String conDition = getParameter("conDition");
		Redis redis = RedisHelper.open("sessionRedis");
		try {
			String  type = redis.getJedis().type(key);
			Map<String, String> value = new HashMap<String,String>();
			if("string".equals(type)) {
				String val = getVal(redis,key);
				value.put("BZ", "0");
				value.put("value", val);
				setJSONReturn(value);
			}else if("hash".equals(type) ) {
				Object val;
				Object json;
				if(conDition != null && !"".equals(conDition)) {
					val = this.memoryDB.getMapValue(key, conDition);
				    json = String.valueOf(val);
				}else {
					val = this.memoryDB.getMap(key);	
					json = JSONObject.fromObject(val);
					//json = String.valueOf(val);
				}	
				setJSONReturn(json.toString());
			}else if("set".equals(type)) {
				List<String> list = new ArrayList<String>();
				byte[] bkey = redis.keyToBytes(key);
				Set<byte[]> bval = redis.getJedis().smembers(bkey);
				Iterator<byte[]> it = bval.iterator();
				while(it.hasNext()){
					byte[] val = it.next();
					ByteArrayInputStream bis = new ByteArrayInputStream(val);
					ObjectInputStream ois =new ObjectInputStream(bis);
					Object obj=ois.readObject();
					ois.close();
					bis.close();
					list.add(String.valueOf(obj));
				}
				String json;
				if(conDition != null && !"".equals(conDition)) {
					List<String> list_1 = new ArrayList<String>();
					list_1.add(list.get(Integer.parseInt(conDition)));
					json = JSONArray.fromObject(list_1).toString();
				}else {
					json = JSONArray.fromObject(list).toString();
				}
				setJSONReturn(json);
			}else if("list".equals(type)) {
				int len = this.memoryDB.getListSize(key);
				Object val;
				if(conDition != null && !"".equals(conDition)) {
					val = this.memoryDB.getListValue(key, Integer.parseInt(conDition));
				}else {
					val = this.memoryDB.getList(key, 0, len);
				}
				String json = JSONArray.fromObject(val).toString();
				setJSONReturn(json);
			}else {
				value.put("BZ", "0");
				value.put("value", null);
				setJSONReturn(value);
			}
		} catch (Exception e) {
			saveJSONError("获取对应value值出错！"+e.getMessage());
			e.printStackTrace();
		}finally {
			RedisHelper.close("sessionRedis");
		}
		return NONE;
	}
	
	/**
	 * 返回可筛选的条件
	 * @return
	 */
	public String queryConDition() {
		String key = getParameter("key");
		Map<String, String> tiaoJian = new HashMap<String,String>();
		Redis redis = RedisHelper.open("sessionRedis");
		try {
			String  type = redis.getJedis().type(key);
			if("set".equals(type) || "list".equals(type)) {
				if("set".equals(type)) {
					Long len = redis.getJedis().scard(key);//获取set长度
					tiaoJian.put("lenght", String.valueOf(len));
				}else{
					int len = this.memoryDB.getListSize(key);//获取list长度
					tiaoJian.put("lenght", String.valueOf(len));
				}
				tiaoJian.put("type", type);
				setJSONReturn(tiaoJian);
			}else {
				tiaoJian.put("type", type);
				setJSONReturn(tiaoJian);
			} 
		}catch (Exception e) {
			saveJSONError("获取条件时出错！"+e.getMessage());
			e.printStackTrace();
		}
		return NONE;
	}

	private String getVal(Redis redis,String key) {
		Object obj = null;
		try {
			byte[] bkey = redis.keyToBytes(key);
			byte[] byt = redis.getJedis().get(bkey);
			ByteArrayInputStream bis = new ByteArrayInputStream(byt);
			ObjectInputStream ois =new ObjectInputStream(bis);
			obj = ois.readObject();
			ois.close();
			bis.close();
		} catch (Exception e) {//
			e.printStackTrace();
			obj = redis.getJedis().get(key);
		}
		return String.valueOf(obj);
	}
}
