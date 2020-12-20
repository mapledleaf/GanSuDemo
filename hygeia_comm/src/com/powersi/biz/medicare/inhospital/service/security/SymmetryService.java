/**
 * 
 */
package com.powersi.biz.medicare.inhospital.service.security;

/**
 * 对称加密
 * 
 * @author LiuYong
 * @version 1.0
 *          com.powersi.biz.medicare.inhospital.service.security.SymmetryService
 *          2016年5月13日 上午10:50:47
 */
public interface SymmetryService extends SecurityBaseService {

	/**
	 * 生成密钥
	 * 
	 * @author LiuYong
	 * @version 1.0 com.powersi.biz.medicare.inhospital.service.security.
	 *          SymmetryService. getKey 2016年5月13日 下午2:57:51
	 * @return byte[]
	 */
	public byte[] getKey();

	/**
	 * 生成密钥
	 * 
	 * @author LiuYong
	 * @version 1.0 com.powersi.biz.medicare.inhospital.service.security.
	 *          SymmetryService. getKey 2016年5月13日 上午11:28:44
	 * @param seed
	 * @return byte[]
	 */
	public byte[] getKey(byte[] seed);

}
