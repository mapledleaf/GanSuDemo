/**
 * 
 */
package com.powersi.biz.medicare.inhospital.service.security;

/**
 * 
 * @author LiuYong
 * @version 1.0 com.powersi.biz.medicare.inhospital.service.security.AESService
 *          2016年5月13日 上午11:10:36
 */
public interface AESService extends SymmetryService {

	/**
	 * 
	 */
	public String AESService = "AESService";

	/**
	 * 
	 */
	public String SHA1PRNG = "SHA1PRNG";

	/**
	 * 
	 */
	public String SHA = "SHA";

	/**
	 * 
	 */
	public String MD5 = "MD5";

	/**
	 * 加密
	 * 
	 * @author LiuYong
	 * @version 1.0
	 *          com.powersi.biz.medicare.inhospital.service.security.AESService.
	 *          encrypt 2016年5月13日 下午2:42:38
	 * @param input
	 * @param key
	 *            密钥
	 * @return byte[]
	 */
	public byte[] encrypt(byte[] input, byte[] key);

	/**
	 * 解密
	 * 
	 * @author LiuYong
	 * @version 1.0
	 *          com.powersi.biz.medicare.inhospital.service.security.AESService.
	 *          encrypt 2016年5月13日 下午2:41:53
	 * @param input
	 * @param key
	 *            密钥
	 * @return byte[]
	 */
	public byte[] decrypt(byte[] input, byte[] key);

	/**
	 * 摘要
	 * 
	 * @author LiuYong
	 * @version 1.0
	 *          com.powersi.biz.medicare.inhospital.service.security.AESService.
	 *          digest 2016年5月13日 下午2:32:09
	 * @param input
	 * @param algorithm
	 *            支持MD5、SHA
	 * @return byte[]
	 */
	public byte[] digest(byte[] input, String algorithm);

}
