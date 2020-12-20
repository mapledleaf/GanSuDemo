package com.powersi.hygeia.comm.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.powersi.comm.exception.ApusException;
import com.powersi.comm.utils.ToolUtil;

/**
 * 使用gzip进行字符串的压缩和解压缩的工具类 由于此类不是每个项目必须要用到的，因此service
 * 不采用注解定义，需要使用的工程请定义本service到xml中
 * 
 * @author 黄尧
 *
 */
public class GzipUtils {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final int BUFFER_SIZE = 2 * 1024;

	/**
	 * 压缩成ZIP
	 * 
	 * @param srcFiles
	 * @param out
	 */
	public static void toZip(List<File> srcFiles, OutputStream out) {
		long start = System.currentTimeMillis();
		ZipOutputStream zipOutputStream = null;
		try {
			zipOutputStream = new ZipOutputStream(out);
			for (File srcFile : srcFiles) {
				byte[] buf = new byte[BUFFER_SIZE];
				zipOutputStream.putNextEntry(new ZipEntry(srcFile.getName()));
				int len = -1;
				FileInputStream fileInputStream = new FileInputStream(srcFile);
				while ((len = fileInputStream.read(buf)) != -1) {
					zipOutputStream.write(buf, 0, len);
				}
				zipOutputStream.closeEntry();
				fileInputStream.close();
			}
			long end = System.currentTimeMillis();
			System.out.println("压缩完成，耗时：" + (end - start) + " ms");
		} catch (Exception e) {
			throw new ApusException("压缩成ZIP时异常:" + e.getMessage(), e);
		} finally {
			if (zipOutputStream != null) {
				try {
					zipOutputStream.close();
					zipOutputStream = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 使用gzip进行压缩
	 * 
	 * @param primStr
	 * @return
	 */
	public byte[] gzip(String primStr) {
		if (primStr == null || primStr.length() == 0) {
			return null;
		}
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		GZIPOutputStream gZIPOutputStream = null;
		try {
			gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
			gZIPOutputStream.write(primStr.getBytes("UTF-8"));
		} catch (IOException e) {
			throw new ApusException("使用gzip进行压缩时异常:" + e.getMessage(), e);
		} finally {
			if (gZIPOutputStream != null) {
				try {
					gZIPOutputStream.close();
					gZIPOutputStream = null;
				} catch (IOException e) {
					this.logger.error("使用gzip进行压缩时异常1:" + ToolUtil.getExceptionInfo(e));
				}
			}
		}
		return byteArrayOutputStream.toByteArray();
	}

	/**
	 * 使用gzip进行解压缩
	 * 
	 * @param compressed
	 * @return
	 */
	public String gunzip(byte[] compressed) {
		if (compressed == null) {
			return null;
		}
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ByteArrayInputStream byteArrayInputStream = null;
		GZIPInputStream gZIPInputStream = null;
		String decompressed = null;
		try {
			byteArrayInputStream = new ByteArrayInputStream(compressed);
			gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
			byte[] buffer = new byte[1024];
			int offset = -1;
			while ((offset = gZIPInputStream.read(buffer)) != -1) {
				byteArrayOutputStream.write(buffer, 0, offset);
			}
			decompressed = byteArrayOutputStream.toString();
		} catch (IOException e) {
			throw new ApusException("使用gzip进行解压缩时异常:" + e.getMessage(), e);
		} finally {
			if (gZIPInputStream != null) {
				try {
					gZIPInputStream.close();
					gZIPInputStream = null;
				} catch (IOException e) {
					this.logger.error("使用gzip进行解压缩时异常1:" + ToolUtil.getExceptionInfo(e));
				}
			}
			if (byteArrayInputStream != null) {
				try {
					byteArrayInputStream.close();
					byteArrayInputStream = null;
				} catch (IOException e) {
					this.logger.error("使用gzip进行解压缩时异常2:" + ToolUtil.getExceptionInfo(e));
				}
			}
			if (byteArrayOutputStream != null) {
				try {
					byteArrayOutputStream.flush();
					byteArrayOutputStream.close();
					byteArrayOutputStream = null;
				} catch (IOException e) {
					this.logger.error("使用gzip进行解压缩时异常3:" + ToolUtil.getExceptionInfo(e));
				}
			}
		}
		return decompressed;
	}

}
