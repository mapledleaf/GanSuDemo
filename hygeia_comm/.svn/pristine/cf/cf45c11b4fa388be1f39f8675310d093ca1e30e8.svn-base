/**
 * 
 */
package com.powersi.biz.medicare.inhospital.service.coder;

import org.springframework.stereotype.Service;

/**
 * @author LiuYong
 * @version 1.0
 *          com.powersi.biz.medicare.inhospital.service.coder.BASE64ServiceImpl
 *          2016年5月13日 下午3:30:07
 */
@Service
public class BASE64ServiceImpl implements BASE64Service {

	/**
	 * @author LiuYong long com.powersi.biz.medicare.inhospital.service.coder.
	 *         serialVersionUID 2016年5月13日 下午3:30:11
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.biz.medicare.inhospital.service.coder.Coder#encode(byte[])
	 */
	@Override
	public String encode(byte[] input) {
		// TODO Auto-generated method stub
		return ENCODER.encodeToString(input);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.biz.medicare.inhospital.service.coder.Coder#decode(java.lang.
	 * String)
	 */
	@Override
	public byte[] decode(String input) {
		// TODO Auto-generated method stub
		try {
			return DECODER.decode(input);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
