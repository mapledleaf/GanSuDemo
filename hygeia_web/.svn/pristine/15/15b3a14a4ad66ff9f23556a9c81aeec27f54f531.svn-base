package com.powersi.ssm.biz.inhospital;

import java.util.HashMap;
import java.util.Map;

import com.powersi.biz.medicare.comm.pojo.MediDTO;

/**
 * 
 * @author 刘勇
 *
 */
public class TestWebMain {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		try {
			Map source = new HashMap();
			Map target = new HashMap();
			source.put("aac003", "刘勇");
			source.put("aac002", "430921198306024513");
			com.powersi.hygeia.framework.util.BeanHelper.copyProperties(source, target);
			System.out.println(target.toString());
			MediDTO sourceMediDTO = new MediDTO();
			sourceMediDTO.setAac003("刘勇");
			sourceMediDTO.setAac002("430921198306024513");
			sourceMediDTO.setPageSize(100);
			MediDTO targetMediDTO = new MediDTO();
			com.powersi.hygeia.framework.util.BeanHelper.copyProperties(sourceMediDTO, targetMediDTO);
			System.out.println(targetMediDTO.toString());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
