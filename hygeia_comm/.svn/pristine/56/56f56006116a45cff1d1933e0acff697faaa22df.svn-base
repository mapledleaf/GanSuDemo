/**
 * 
 */
package com.powersi.biz.medicare.inhospital.service.security;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.springframework.stereotype.Service;

/**
 * @author LiuYong
 * @version 1.0
 *          com.powersi.biz.medicare.inhospital.service.security.RSAServiceImpl
 *          2016年5月13日 下午3:50:23
 */
@Service
public class RSAServiceImpl implements RSAService {

	/**
	 * @author LiuYong long
	 *         com.powersi.biz.medicare.inhospital.service.security.
	 *         serialVersionUID 2016年5月13日 下午3:50:26
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.biz.medicare.inhospital.service.security.UnSymmetry#sign(byte
	 * [], java.security.PrivateKey)
	 */
	@Override
	public byte[] sign(byte[] input, PrivateKey privateKey) {
		// TODO Auto-generated method stub
		try {
			Signature signature = Signature.getInstance(MD5withRSAService);
			signature.initSign(privateKey);
			signature.update(input);
			return signature.sign();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.biz.medicare.inhospital.service.security.UnSymmetry#verify(
	 * byte[], java.security.PublicKey, byte[])
	 */
	@Override
	public boolean verify(byte[] input, PublicKey publicKey, byte[] signature) {
		// TODO Auto-generated method stub
		try {
			Signature signatures = Signature.getInstance(MD5withRSAService);
			signatures.initVerify(publicKey);
			signatures.update(input);
			return signatures.verify(signature);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.biz.medicare.inhospital.service.security.UnSymmetry#encrypt(
	 * byte[], java.security.PrivateKey)
	 */
	@Override
	public byte[] encrypt(byte[] input, PrivateKey privateKey) {
		// TODO Auto-generated method stub
		try {
			PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
			KeyFactory keyFactory = KeyFactory.getInstance(RSAService);
			Key privateKeys = keyFactory.generatePrivate(pKCS8EncodedKeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, privateKeys);
			int len = input.length;
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段加密
			while (len - offSet > 0) {
				if (len - offSet > MAX_ENCRYPT_BLOCK) {
					cache = cipher.doFinal(input, offSet, MAX_ENCRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(input, offSet, len - offSet);
				}
				byteArrayOutputStream.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_ENCRYPT_BLOCK;
			}
			byte[] encrypt = byteArrayOutputStream.toByteArray();
			byteArrayOutputStream.close();
			return encrypt;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.biz.medicare.inhospital.service.security.UnSymmetry#decrypt(
	 * byte[], java.security.PublicKey)
	 */
	@Override
	public byte[] decrypt(byte[] input, PublicKey publicKey) {
		// TODO Auto-generated method stub
		try {
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
			KeyFactory keyFactory = KeyFactory.getInstance(RSAService);
			Key publicKeys = keyFactory.generatePublic(x509EncodedKeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, publicKeys);
			int len = input.length;
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段解密
			while (len - offSet > 0) {
				if (len - offSet > MAX_DECRYPT_BLOCK) {
					cache = cipher.doFinal(input, offSet, MAX_DECRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(input, offSet, len - offSet);
				}
				byteArrayOutputStream.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_DECRYPT_BLOCK;
			}
			byte[] decrypt = byteArrayOutputStream.toByteArray();
			byteArrayOutputStream.close();
			return decrypt;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.biz.medicare.inhospital.service.security.UnSymmetry#encrypt(
	 * byte[], java.security.PublicKey)
	 */
	@Override
	public byte[] encrypt(byte[] input, PublicKey publicKey) {
		// TODO Auto-generated method stub
		try {
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
			KeyFactory keyFactory = KeyFactory.getInstance(RSAService);
			Key publicKeys = keyFactory.generatePublic(x509EncodedKeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, publicKeys);
			int len = input.length;
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段解密
			while (len - offSet > 0) {
				if (len - offSet > MAX_ENCRYPT_BLOCK) {
					cache = cipher.doFinal(input, offSet, MAX_ENCRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(input, offSet, len - offSet);
				}
				byteArrayOutputStream.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_ENCRYPT_BLOCK;
			}
			byte[] decrypt = byteArrayOutputStream.toByteArray();
			byteArrayOutputStream.close();
			return decrypt;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.biz.medicare.inhospital.service.security.UnSymmetry#decrypt(
	 * byte[], java.security.PrivateKey)
	 */
	@Override
	public byte[] decrypt(byte[] input, PrivateKey privateKey) {
		// TODO Auto-generated method stub
		try {
			PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
			KeyFactory keyFactory = KeyFactory.getInstance(RSAService);
			Key privateKeys = keyFactory.generatePrivate(pKCS8EncodedKeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, privateKeys);
			int len = input.length;
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段解密
			while (len - offSet > 0) {
				if (len - offSet > MAX_DECRYPT_BLOCK) {
					cache = cipher.doFinal(input, offSet, MAX_DECRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(input, offSet, len - offSet);
				}
				byteArrayOutputStream.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_DECRYPT_BLOCK;
			}
			byte[] decrypt = byteArrayOutputStream.toByteArray();
			byteArrayOutputStream.close();
			return decrypt;
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
		return null;
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
		return null;
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
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(SHA);
			messageDigest.update(input);
			return messageDigest.digest();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.biz.medicare.inhospital.service.security.RSAService#
	 * getPublicKey( java.io.File, java.lang.String, java.lang.String)
	 */
	@Override
	public PublicKey getPublicKey(File cer, String password, String alias) {
		// TODO Auto-generated method stub
		try {
			KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
			keystore.load(new FileInputStream(cer), password.toCharArray());
			Certificate certificate = keystore.getCertificate(alias);
			return certificate.getPublicKey();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.biz.medicare.inhospital.service.security.RSAService#
	 * getKeyPair(java. io.File, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public KeyPair getKeyPair(File cer, String storePassword, String keyPassword, String alias) {
		// TODO Auto-generated method stub
		try {
			KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
			keystore.load(new FileInputStream(cer), storePassword.toCharArray());
			Key key = keystore.getKey(alias, keyPassword.toCharArray());
			if (key instanceof PrivateKey) {
				Certificate certificate = keystore.getCertificate(alias);
				PublicKey publicKey = certificate.getPublicKey();
				return new KeyPair(publicKey, (PrivateKey) key);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
	}

}
