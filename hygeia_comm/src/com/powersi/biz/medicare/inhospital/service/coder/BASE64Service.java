/**
 * 
 */
package com.powersi.biz.medicare.inhospital.service.coder;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

/**
 * 
 * @author LiuYong
 * @version 1.0 com.powersi.biz.medicare.inhospital.service.coder.BASE64Service
 *          2016年5月15日 上午8:39:54
 */
public interface BASE64Service extends CoderService {

	/**
	 * 
	 */
	Decoder DECODER = Base64.getDecoder();

	/**
	 * 
	 */
	Encoder ENCODER = Base64.getEncoder();

}
