package com.powersi.biz.medicare.inhospital.service.db;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.comminter.pojo.KAB3DTO;
import com.powersi.biz.medicare.inhospital.pojo.BizDTO;
import com.powersi.biz.medicare.inhospital.pojo.BizFinDTO;
import com.powersi.biz.medicare.inhospital.pojo.FeeDTO;
import com.powersi.biz.medicare.inhospital.pojo.FeeFinDTO;
import com.powersi.biz.medicare.inhospital.pojo.FeeStatDTO;
import com.powersi.biz.medicare.inhospital.pojo.FeeStatFinDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.pojo.Kcg4DTO;
import com.powersi.biz.medicare.inhospital.pojo.PayDTO;
import com.powersi.biz.medicare.inhospital.pojo.PayFinDTO;
import com.powersi.biz.medicare.inhospital.pojo.SceneDTO;
import com.powersi.biz.medicare.inhospital.pojo.SceneFinDTO;
import com.powersi.biz.medicare.query.dto.KCD1Log;

/**
 * 
 * @author 刘勇
 *
 */
public interface BizService extends Serializable {

	public String table_name = "kcd1";

	/**
	 * 
	 * @param bizDTO
	 * @param kcg4dtoList
	 * @return
	 */
	public int insert(BizDTO bizDTO, List<Kcg4DTO> kcg4dtoList);

	/**
	 * 
	 * @param payDTORows
	 * @param sceneDTORows
	 * @param feeStatDTORows
	 * @param bizDTO
	 * @param feeDTORows
	 * @param bka438
	 * @return
	 */
	public int insertTryCalcData(List<PayDTO> payDTORows, List<SceneDTO> sceneDTORows, List<FeeStatDTO> feeStatDTORows,
			BizDTO bizDTO, List<FeeDTO> feeDTORows, String bka438);

	/**
	 * 
	 * @param payFinDTORows
	 * @param sceneFinDTORows
	 * @param feeStatFinDTORows
	 * @param bizFinDTO
	 * @param feeFinDTORows
	 * @param kab3dto
	 * @param bka438
	 * @return
	 */
	public int insertCalcData(List<PayFinDTO> payFinDTORows, List<SceneFinDTO> sceneFinDTORows,
			List<FeeStatFinDTO> feeStatFinDTORows, BizFinDTO bizFinDTO, List<FeeFinDTO> feeFinDTORows, KAB3DTO kab3dto,
			String bka438);

	/**
	 * 
	 * @param bizDTO
	 * @return
	 */
	public int delete(BizDTO bizDTO);

	/**
	 * 更新结算云住院信息，其中包括多诊断信息kcg4，以及历史修改记录kcd1log
	 * 
	 * @param bizDTO
	 * @param kcg4dtoList 多诊断信息
	 * @param bkl004      kcd1log日志id
	 * @return
	 */
	public int update(BizDTO bizDTO, List<Kcg4DTO> kcg4dtoList, String bkl004);

	/**
	 * 
	 * @param bizDTO
	 * @return
	 */
	@Transactional
	public int update(BizDTO bizDTO);

	/**
	 * 
	 * @param bizDTO
	 * @return
	 */
	public BizDTO select(BizDTO bizDTO);

	/**
	 * 
	 * @param bizDTO
	 * @return
	 */
	public BizDTO selectByBka012(BizDTO bizDTO);

	/**
	 * 
	 * @param bizDTO
	 * @return
	 */
	public ListResult selectList(BizDTO bizDTO);

	/**
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public List<InHospitalDTO> selectAll(InHospitalDTO inHospitalDTO);

	/**
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public List<InHospitalDTO> selectAllMinCols(InHospitalDTO inHospitalDTO);

	/**
	 * 查询家庭病床登记信息
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public List<InHospitalDTO> selectHomeSickbedInfo(InHospitalDTO inHospitalDTO);

	/**
	 * 
	 * @param inHospitalDTO
	 * @param bizDTO
	 * @param feeDTORows
	 * @param sceneDTORows
	 * @param payDTORows
	 * @param feeStatDTORows
	 * @return
	 */
	public int cancelOutCharge(InHospitalDTO inHospitalDTO, BizDTO bizDTO, List<FeeDTO> feeDTORows,
			List<SceneDTO> sceneDTORows, List<PayDTO> payDTORows, List<FeeStatDTO> feeStatDTORows);

	/**
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public int cancelOutRegister(InHospitalDTO inHospitalDTO);

	/**
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public int cancelRegister(InHospitalDTO inHospitalDTO);

	/**
	 * 根据aaz217查询多诊断信息
	 * 
	 * @param aaz217
	 * @return
	 */
	public List<Kcg4DTO> loadDiagnoseInfosByAaz217(String aaz217);

	/**
	 * 获取kcd1日志修改记录
	 * 
	 * @param aaz217
	 * @return
	 */
	public List<KCD1Log> selectKcd1Log(String aaz217);

	/**
	 * 查询是否存在在院业务
	 * 
	 * @param bizFinDTO
	 * @return
	 */
	public boolean isExistInHospital(BizFinDTO bizFinDTO);

	/**
	 * 跨年断帐时，保存未合并前的计算结果值
	 * 
	 * @param list
	 */
	public void saveCalcResultBeforeMerge(List<PayDTO> list);

}
