/**
 * 
 */
package com.powersi.biz.medicare.inhospital.service.security;

import java.io.Serializable;

/**
 * 安全基础
 * 
 * @author LiuYong
 * @version 1.0 com.powersi.biz.medicare.inhospital.service.security.
 *          SecurityBaseService 2016年5月13日 上午11:17:27
 */
public interface SecurityBaseService extends Serializable {

	/**
	 * 加密
	 * 
	 * @author LiuYong
	 * @version 1.0 com.powersi.biz.medicare.inhospital.service.security.
	 *          SecurityBaseService.encrypt 2016年5月13日 上午11:18:09
	 * @param input
	 * @return byte[]
	 */
	public byte[] encrypt(byte[] input);

	/**
	 * 解密
	 * 
	 * @author LiuYong
	 * @version 1.0 com.powersi.biz.medicare.inhospital.service.security.
	 *          SecurityBaseService.decrypt 2016年5月13日 上午11:20:53
	 * @param input
	 * @return byte[]
	 */
	public byte[] decrypt(byte[] input);

	/**
	 * 摘要
	 * 
	 * @author LiuYong
	 * @version 1.0 com.powersi.biz.medicare.inhospital.service.security.
	 *          SecurityBaseService.digest 2016年5月13日 上午11:21:08
	 * @param input
	 * @return byte[]
	 */
	public byte[] digest(byte[] input);

}
