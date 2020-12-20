package com.powersi.biz.medicare.diagnose.service;

import java.util.Map;

import com.powersi.biz.medicare.comm.pojo.Kc90DTO;
import com.powersi.biz.medicare.diagnose.pojo.DiagnoseInfoDTO;

public interface AsnyHandPhotoService {
	
	public int saveTemPhoto(Kc90DTO kc90);
	
	public int saveTemFingerVeins(Map<String, Object> para);
	
	/**
	 * 指静脉建模
	 * @param kc90
	 * @return
	 */
	public int modeling(Kc90DTO kc90);

	/**
	 * @param uuid
	 * @param apiFlag 1：api 0：web
	 * @return
	 */
	public String getPhotoPath(String uuid,String apiFlag);
	
	/**
	 * 密码重置，保存拍照，删除kz11数据，往kz21插入一条记录
	 * @param diagnosInfoDTO
	 * @return
	 */
	public int savePhotoUpdateKz11SaveKz21(DiagnoseInfoDTO diagnosInfoDTO);

}
