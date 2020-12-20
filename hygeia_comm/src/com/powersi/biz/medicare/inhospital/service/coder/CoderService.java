/**
 * 
 */
package com.powersi.biz.medicare.inhospital.service.coder;

import java.io.Serializable;

/**
 * 字符编码、解码
 * 
 * @author LiuYong
 * @version 1.0 com.powersi.biz.medicare.inhospital.service.coder.CoderService
 *          2016年5月15日 上午8:38:45
 */
public interface CoderService extends Serializable {

	/**
	 * 编码
	 * 
	 * @author LiuYong
	 * @version 1.0
	 *          com.powersi.biz.medicare.inhospital.service.coder.CoderService.
	 *          encode 2016年5月15日 上午8:39:07
	 * @param input
	 * @return String
	 */
	String encode(byte[] input);

	/**
	 * 解码
	 * 
	 * @author LiuYong
	 * @version 1.0
	 *          com.powersi.biz.medicare.inhospital.service.coder.CoderService.
	 *          decode 2016年5月15日 上午8:39:16
	 * @param input
	 * @return byte[]
	 */
	byte[] decode(String input);

}
