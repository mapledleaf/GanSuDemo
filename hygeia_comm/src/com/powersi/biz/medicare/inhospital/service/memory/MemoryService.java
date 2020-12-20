package com.powersi.biz.medicare.inhospital.service.memory;

import java.io.Serializable;

/**
 * 面向内存服务接口(实现业务逻辑)
 * 
 * @author 刘勇
 *
 */
public interface MemoryService extends Serializable {

	/**
	 * aaz217 = akb020 + yyyyMMdd + 000000
	 * 
	 * @param akb020
	 *            医院编号(流水号,不带业务逻辑)
	 * @param incrNum
	 *            增长因子(正数)
	 * @return 就医登记号=医院编码(6位流水号)+8位年月日+6位流水号
	 */
	public String getAaz217(String akb020, long incrNum);

	/**
	 * aaz217 = akb020 + yyyyMMdd + 000000
	 * 
	 * @param akb020
	 *            医院编号(流水号,不带业务逻辑)
	 * @return 就医登记号=医院编码(6位流水号)+8位年月日+6位流水号
	 */
	public String getAaz217(String akb020);
	
	/**
	 * 生成序列号
	 * @param colm 列的名称
	 * @param akb020 医院编码
	 * @return yyyyMMddHHmmss+00000X
	 */
	public String getSerialNum(String colm, String akb020);

}
