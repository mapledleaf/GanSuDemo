package com.powersi.biz.medicare.comminter.service;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.comminter.pojo.KAB1DTO;
import com.powersi.biz.medicare.comminter.pojo.KAB2DTO;
import com.powersi.biz.medicare.comminter.pojo.KAB3DTO;
import com.powersi.biz.medicare.inhospital.pojo.FeeFinDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.pojo.PayFinDTO;

public interface InvoiceManagerService {

	/**
	 * 获取发票领用记录(分页)
	 * @param kab1
	 * @return
	 */
	public ListResult queryInvoicePage(KAB1DTO kab1);
	
	/**
	 * 获取发票领用记录(不分页)
	 * @param kab1
	 * @return
	 */
	public List<KAB1DTO> queryInvoice(KAB1DTO kab1);
	
	/**
	 * 获取单条发票领用记录
	 * @param kab1id
	 * @return
	 */
	public KAB1DTO queryInvoiceById(String kab1id,String akb020);
	/**
	 * 领取发票
	 * @param kab1
	 * @return
	 */
	public int saveInvoice(KAB1DTO kab1);
	
	/**
	 * 发票使用情况查询
	 * @param kab2
	 * @return
	 */
	public ListResult querytKab2Page(KAB2DTO kab2);
	
	/**
	 * 查询是否领用了发票
	 */
	public List<KAB1DTO> queryKAB1DTO(KAB1DTO kab1DTO);
	
	/**
	 * 退费台账处理
	 */
	public int updateKab3(KAB3DTO kab3DTO);
	
	/**
	 * 台账处理 查询是否存在发票
	 */
	public KAB3DTO selectKab3(KAB3DTO kab3DTO);
	
	/**
	 * 发票退领 1、写入历史记录表2、更新领用信息
	 * @param kab1
	 * @return
	 */
	public int backInvoice(KAB1DTO kab1);
	
	/**
	 * 批量作废发票 (只能作废未领取的发票)
	 * @param kab1
	 * @return
	 */
	public int cancelInvoicePiLiang(KAB1DTO kab1);
	
	/**
	 * 发票作废处理
	 * @param kab3DTO
	 * @param kab2DTO
	 * @return
	 */
	public int abolishInvoice(KAB3DTO kab3DTO, KAB2DTO kab2dto);
	
	/**
	 * 获取结算台账信息记录(分页)
	 * @param kab1
	 * @return
	 */
	public ListResult queryKab3InfoPage(KAB3DTO kab3);
	
	/**
	 * 设置发票时修改发票号
	 * @param kab1DTO
	 * @return
	 */
	public int updateInvoice(KAB1DTO kab1DTO);
	
	/**
	 * 保存kab3结算台账信息
	 * @param kab3DTO
	 * @return
	 */
	public int saveKab3info(KAB3DTO kab3DTO);
	
	/**
	 * 修改票号与作废票号处理
	 * @return
	 */
	public int updateBae413AndAbolish(KAB1DTO kab1DTO,List<KAB1DTO> kab1DTORows);

	/**
	 * 打印后处理相关发票业务表：
	 * update kab3
	 * insert kab2
	 * update kab1 
	 * @param kab3DTO
	 * @return
	 */
	public int processBizAfterPrint(KAB3DTO kab3DTO);
	
	/**
	 * 获取费用信息
	 * @return
	 */
	public List<FeeFinDTO> queryFeeFinInfo(InHospitalDTO inHospitalDTO);
	
	public List<PayFinDTO> queryPayFinInfo(InHospitalDTO inHospitalDTO);
	
	public List<Map<String, Object>> queryBizFeeInfo(InHospitalDTO inHospitalDTO);	
}
