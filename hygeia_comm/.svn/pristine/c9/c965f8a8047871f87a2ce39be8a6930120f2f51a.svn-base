/**
 * 
 */
package com.powersi.biz.medicare.inhospital.service.security;

import java.io.File;
import java.security.KeyPair;
import java.security.PublicKey;

/**
 * @author LiuYong
 * @version 1.0 com.powersi.biz.medicare.inhospital.service.security.RSAService
 *          2016年5月13日 下午3:44:22
 */
public interface RSAService extends UnSymmetryService {

	/**
	 * 
	 */
	public String RSAService = "RSAService";

	/**
	 * RSAService最大加密明文大小
	 */
	public int MAX_ENCRYPT_BLOCK = 117;

	/**
	 * RSAService最大解密密文大小
	 */
	public int MAX_DECRYPT_BLOCK = 128;

	public String SHA = "SHA";

	public String MD5withRSAService = "MD5withRSAService";

	/**
	 * 获取公钥
	 * 
	 * @author LiuYong
	 * @version 1.0
	 *          com.powersi.biz.medicare.inhospital.service.security.RSAService.
	 *          getPublicKey 2016年5月13日 下午3:49:23
	 * @param cer
	 * @param password
	 * @param alias
	 * @return PublicKey
	 */
	public PublicKey getPublicKey(File cer, String password, String alias);

	/**
	 * 获取密钥对
	 * 
	 * @author LiuYong
	 * @version 1.0
	 *          com.powersi.biz.medicare.inhospital.service.security.RSAService.
	 *          getKeyPair 2016年5月13日 下午3:57:35
	 * @param cer
	 * @param storePassword
	 * @param keyPassword
	 * @param alias
	 * @return KeyPair
	 */
	public KeyPair getKeyPair(File cer, String storePassword, String keyPassword, String alias);

}
