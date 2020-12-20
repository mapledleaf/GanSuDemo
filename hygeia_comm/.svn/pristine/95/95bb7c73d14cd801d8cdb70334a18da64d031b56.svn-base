package com.powersi.biz.medicare.comminter.service;

import java.util.List;
import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.comminter.pojo.KAB1DTO;
import com.powersi.biz.medicare.comminter.pojo.KAB2DTO;
import com.powersi.biz.medicare.comminter.pojo.KAB3DTO;
import com.powersi.biz.medicare.comminter.pojo.KAB4DTO;

public interface InvoiceDBManagerService {
	
	public static final String TRADE_TABLE_NAME ="kab3";
	
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
	public KAB1DTO queryInvoiceById(String kab1id);
	/**
	 * 领取发票
	 * @param kab1
	 * @param kab4
	 * @return
	 */
	public int saveInvoice(KAB1DTO kab1, KAB4DTO kab4);
	
	/**
	 * 退领发票
	 * @param kab1
	 * @param kab4
	 * @return
	 */
	public int updateBackInvoice(KAB1DTO kab1, KAB4DTO kab4);
	
	/**
	 * 更新发票领用信息
	 * @param kab1
	 * @return
	 */
	public int updateInvoice(KAB1DTO kab1);

	/**
	 * 保存结算信息台账表
	 * @param kab2dto
	 * @return
	 */
	public int insertKab3Info(KAB3DTO kab3dto);
	
	/**
	 * 作废发票时还原台账信息
	 * @param kab2
	 * @return
	 */
	public int updateKab3(KAB3DTO kab3);
	
	/**
	 * 作废发票时获取要还原台的账信息
	 * @param kab2
	 * @return
	 */
	public KAB3DTO quertKab3ByBae413(KAB3DTO kab3);
	
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
	 * 台账处理 查询是否存在发票
	 */
	public KAB3DTO selectKab3(KAB3DTO kab3DTO);
	
	/**
	 * 更新发票使用情况表
	 * @param kab2dto
	 * @return
	 */
	public int updateKab2(KAB2DTO  kab2dto);
	
	/**
	 * 发票作废更新台账表
	 * @param kab3dto
	 * @return
	 */
	public int updateKab3Abolish(KAB3DTO kab3dto);
	
	/**
	 * 获取结算信息台账记录(分页)
	 * @param kab1
	 * @return
	 */
	public ListResult queryKab3InfoPage(KAB3DTO kab3);
	
	/**
	 * 判断发票是否可以作废
	 * @param kab2dto
	 * @return
	 */
	public List<KAB2DTO> querykab2Bae413(KAB2DTO kab2dto);
	
	/**
	 * 批量作废发票(不含台账处理)
	 * @param kab1
	 * @param kab2DTORows
	 * @return
	 */
	public int updateCancelInvoicePiLiang(KAB1DTO kab1, List<KAB2DTO> kab2DTORows);
	
	/**
	 * 打印后处理相关发票业务表：
	 * @param kab1dto
	 * @param kab2dto
	 * @param kab3dto
	 * @return
	 */
	public int updateBizAfterPrint(KAB1DTO kab1dto, KAB2DTO kab2dto, KAB3DTO kab3dto);
	
	/**
	 * 发票作废业务处理
	 * @param kab2dtoRow
	 * @param kab3dtoRow
	 * @return
	 */
	public int updateInvoiceAblish(KAB2DTO kab2dtoRow, KAB3DTO kab3dtoRow);
	
	/**
	 * 跳号设置：修改票号与作废票号处理
	 * @param kab1dto
	 * @param kab2dtoRows
	 * @return
	 */
	public int updateBae413AndAbolish(KAB1DTO kab1dto, List<KAB2DTO> kab2dtoRows);
}
