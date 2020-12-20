/**
 * 
 */
package com.powersi.biz.medicare.inhospital.service.security;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 非对称加密
 * 
 * @author LiuYong
 * @version 1.0 com.powersi.biz.medicare.inhospital.service.security.
 *          UnSymmetryService 2016年5月13日 上午11:24:32
 */
public interface UnSymmetryService extends SecurityBaseService {

	/**
	 * 签名
	 * 
	 * @author LiuYong
	 * @version 1.0 com.powersi.biz.medicare.inhospital.service.security.
	 *          UnSymmetryService. sign 2016年5月13日 上午11:24:51
	 * @param input
	 * @param privateKey
	 * @return byte[]
	 */
	public byte[] sign(byte[] input, PrivateKey privateKey);

	/**
	 * 验证签名
	 * 
	 * @author LiuYong
	 * @version 1.0 com.powersi.biz.medicare.inhospital.service.security.
	 *          UnSymmetryService. verify 2016年5月13日 上午11:25:10
	 * @param input
	 * @param publicKey
	 * @param signature
	 * @return boolean
	 */
	public boolean verify(byte[] input, PublicKey publicKey, byte[] signature);

	/**
	 * 加密
	 * 
	 * @author LiuYong
	 * @version 1.0
	 *          com.powersi.biz.medicare.inhospital.service.security.RSA.encrypt
	 *          2016年5月13日 上午10:39:47
	 * @param input
	 * @param privateKey
	 * @return byte[]
	 */
	public byte[] encrypt(byte[] input, PrivateKey privateKey);

	/**
	 * 解密
	 * 
	 * @author LiuYong
	 * @version 1.0 com.powersi.biz.medicare.inhospital.service.security.
	 *          UnSymmetryService. decrypt 2016年5月13日 上午11:26:14
	 * @param input
	 * @param publicKey
	 * @return byte[]
	 */
	public byte[] decrypt(byte[] input, PublicKey publicKey);

	/**
	 * 加密
	 * 
	 * @author LiuYong
	 * @version 1.0 com.powersi.biz.medicare.inhospital.service.security.
	 *          UnSymmetryService. encrypt 2016年5月13日 上午11:27:35
	 * @param input
	 * @param publicKey
	 * @return byte[]
	 */
	public byte[] encrypt(byte[] input, PublicKey publicKey);

	/**
	 * 解密
	 * 
	 * @author LiuYong
	 * @version 1.0 com.powersi.biz.medicare.inhospital.service.security.
	 *          UnSymmetryService. decrypt 2016年5月13日 上午11:27:48
	 * @param input
	 * @param privateKey
	 * @return byte[]
	 */
	public byte[] decrypt(byte[] input, PrivateKey privateKey);

}
