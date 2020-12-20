package com.powersi.biz.medicare.hosp.service.db;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.powersi.biz.medicare.hosp.pojo.Bkn2Dto;
import com.powersi.biz.medicare.hosp.pojo.Bkn2DtoExample;

public interface Bkn2DBService {
	 int countByExample(Bkn2DtoExample example);

	    int deleteByExample(Bkn2DtoExample example);

	    int deleteByPrimaryKey(Integer id);

	    int insert(Bkn2Dto record);
	    @Transactional
	    int insertSelective(Bkn2Dto record);

	    List<Bkn2Dto> selectByExample(Bkn2DtoExample example);

	    Bkn2Dto selectByPrimaryKey(Integer id);

	    int updateByExampleSelective(@Param("record") Bkn2Dto record, @Param("example") Bkn2DtoExample example);

	    int updateByExample(@Param("record") Bkn2Dto record, @Param("example") Bkn2DtoExample example);

	    int updateByPrimaryKeySelective(Bkn2Dto record);

	    int updateByPrimaryKey(Bkn2Dto record);
	    public List<Bkn2Dto> selectByAaz217(String akb020,String aaz217);
}