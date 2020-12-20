/**
 * 
 */
package com.powersi.biz.medicare.inhospital.service.security;

import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

/**
 * 
 * @author LiuYong
 * @version 1.0
 *          com.powersi.biz.medicare.inhospital.service.security.AESServiceImpl
 *          2016年5月13日 上午11:30:58
 */
@Service
public class AESServiceImpl implements AESService {

	/**
	 * @author LiuYong long
	 *         com.powersi.biz.medicare.inhospital.service.security.
	 *         serialVersionUID 2016年5月13日 上午11:30:11
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.biz.medicare.inhospital.service.security.Symmetry#getKey()
	 */
	@Override
	public byte[] getKey() {
		// TODO Auto-generated method stub
		return getKey(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.biz.medicare.inhospital.service.security.Symmetry#getKey(byte
	 * [])
	 */
	@Override
	public byte[] getKey(byte[] seed) {
		// TODO Auto-generated method stub
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance(AESService);
			SecureRandom secureRandom = SecureRandom.getInstance(SHA1PRNG);
			if (seed != null) {
				secureRandom.setSeed(seed);
			}
			keyGenerator.init(128, secureRandom);
			SecretKey secretKey = keyGenerator.generateKey();
			return secretKey.getEncoded();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.biz.medicare.inhospital.service.security.SecurityBase#encrypt
	 * (byte[])
	 */
	@Override
	public byte[] encrypt(byte[] input) {
		// TODO Auto-generated method stub
		try {
			SecretKey secretKey = new SecretKeySpec(this.getKey(AESService.getBytes()), AESService);
			Cipher cipher = Cipher.getInstance(AESService);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return cipher.doFinal(input);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.biz.medicare.inhospital.service.security.AESService#encrypt(
	 * byte[], java.lang.String)
	 */
	@Override
	public byte[] encrypt(byte[] input, byte[] key) {
		// TODO Auto-generated method stub
		try {
			SecretKey secretKey = new SecretKeySpec(this.getKey(key), AESService);
			Cipher cipher = Cipher.getInstance(AESService);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return cipher.doFinal(input);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.biz.medicare.inhospital.service.security.SecurityBase#decrypt
	 * (byte[])
	 */
	@Override
	public byte[] decrypt(byte[] input) {
		// TODO Auto-generated method stub
		try {
			SecretKey secretKey = new SecretKeySpec(this.getKey(AESService.getBytes()), AESService);
			Cipher cipher = Cipher.getInstance(AESService);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return cipher.doFinal(input);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.biz.medicare.inhospital.service.security.AESService#decrypt(
	 * byte[], java.lang.String)
	 */
	@Override
	public byte[] decrypt(byte[] input, byte[] key) {
		// TODO Auto-generated method stub
		try {
			SecretKey secretKey = new SecretKeySpec(this.getKey(key), AESService);
			Cipher cipher = Cipher.getInstance(AESService);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return cipher.doFinal(input);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.biz.medicare.inhospital.service.security.SecurityBase#digest(
	 * byte[])
	 */
	@Override
	public byte[] digest(byte[] input) {
		// TODO Auto-generated method stub
		return digest(input, MD5);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.biz.medicare.inhospital.service.security.AESService#digest(
	 * byte[], java.lang.String)
	 */
	@Override
	public byte[] digest(byte[] input, String algorithm) {
		// TODO Auto-generated method stub
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.update(input);
			return messageDigest.digest();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
