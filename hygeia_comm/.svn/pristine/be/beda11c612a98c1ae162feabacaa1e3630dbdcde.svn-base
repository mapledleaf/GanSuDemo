/**
 * 
 */
package com.powersi.biz.medicare.inhospital.service.gzip;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * @author LiuYong
 * @version 1.0 com.powersi.biz.medicare.inhospital.service.gzip.GzipService
 *          2016年5月13日 下午5:29:00
 */
public interface GzipService extends Serializable {

	/**
	 * 
	 */
	int BUFFER = 1024;

	/**
	 * 
	 */
	String EXT = ".gz";

	/**
	 * 压缩
	 * 
	 * @author LiuYong
	 * @version 1.0
	 *          com.powersi.biz.medicare.inhospital.service.gzip.GzipService.
	 *          compress 2016年5月13日 下午5:32:14
	 * @param data
	 * @return byte[]
	 */
	byte[] compress(byte[] data);

	/**
	 * 解压缩
	 * 
	 * @author LiuYong
	 * @version 1.0
	 *          com.powersi.biz.medicare.inhospital.service.gzip.GzipService.
	 *          decompress 2016年5月13日 下午5:32:55
	 * @param data
	 * @return byte[]
	 */
	byte[] decompress(byte[] data);

	/**
	 * 压缩
	 * 
	 * @author LiuYong
	 * @version 1.0
	 *          com.powersi.biz.medicare.inhospital.service.gzip.GzipService.
	 *          compress 2016年5月13日 下午5:32:20
	 * @param inputStream
	 * @param outputStream
	 *            void
	 */
	void compress(InputStream inputStream, OutputStream outputStream);

	/**
	 * 解压缩
	 * 
	 * @author LiuYong
	 * @version 1.0
	 *          com.powersi.biz.medicare.inhospital.service.gzip.GzipService.
	 *          decompress 2016年5月13日 下午5:43:28
	 * @param inputStream
	 * @param outputStream
	 *            void
	 */
	void decompress(InputStream inputStream, OutputStream outputStream);

	/**
	 * 压缩
	 * 
	 * @author LiuYong
	 * @version 1.0
	 *          com.powersi.biz.medicare.inhospital.service.gzip.GzipService.
	 *          compress 2016年5月13日 下午5:45:26
	 * @param file
	 * @param isDelete
	 *            void
	 */
	void compress(File file, boolean isDelete);

	/**
	 * 解压缩
	 * 
	 * @author LiuYong
	 * @version 1.0
	 *          com.powersi.biz.medicare.inhospital.service.gzip.GzipService.
	 *          decompress 2016年5月13日 下午5:46:00
	 * @param file
	 * @param isDelete
	 *            void
	 */
	void decompress(File file, boolean isDelete);

}
