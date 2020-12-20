/**
 * 
 */
package com.powersi.biz.medicare.inhospital.service.gzip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.springframework.stereotype.Service;

/**
 * @author LiuYong
 * @version 1.0 com.powersi.biz.medicare.inhospital.service.gzip.GzipServiceImpl
 *          2016年5月13日 下午5:56:09
 */
@Service
public class GzipServiceImpl implements GzipService {

	/**
	 * @author LiuYong long
	 *         com.powersi.biz.medicare.inhospital.service.gzip.serialVersionUID
	 *         2016年5月13日 下午5:56:12
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.biz.medicare.inhospital.service.gzip.GzipService#compress(
	 * byte[])
	 */
	@Override
	public byte[] compress(byte[] data) {
		// TODO Auto-generated method stub
		ByteArrayInputStream byteArrayInputStream = null;
		ByteArrayOutputStream byteArrayOutputStream = null;
		try {
			byteArrayInputStream = new ByteArrayInputStream(data);
			byteArrayOutputStream = new ByteArrayOutputStream();
			this.compress(byteArrayInputStream, byteArrayOutputStream);
			byteArrayOutputStream.flush();
			byte[] compress = byteArrayOutputStream.toByteArray();
			return compress;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (byteArrayOutputStream != null) {
					byteArrayOutputStream.close();
				}
				if (byteArrayInputStream != null) {
					byteArrayInputStream.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.biz.medicare.inhospital.service.gzip.GzipService#decompress(
	 * byte[])
	 */
	@Override
	public byte[] decompress(byte[] data) {
		// TODO Auto-generated method stub
		ByteArrayInputStream byteArrayInputStream = null;
		ByteArrayOutputStream byteArrayOutputStream = null;
		try {
			byteArrayInputStream = new ByteArrayInputStream(data);
			byteArrayOutputStream = new ByteArrayOutputStream();
			this.decompress(byteArrayInputStream, byteArrayOutputStream);
			byteArrayOutputStream.flush();
			byte[] decompress = byteArrayOutputStream.toByteArray();
			return decompress;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (byteArrayOutputStream != null) {
					byteArrayOutputStream.close();
				}
				if (byteArrayInputStream != null) {
					byteArrayInputStream.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.biz.medicare.inhospital.service.gzip.GzipService#compress(
	 * java.io. InputStream, java.io.OutputStream)
	 */
	@Override
	public void compress(InputStream inputStream, OutputStream outputStream) {
		// TODO Auto-generated method stub
		GZIPOutputStream gZIPOutputStream = null;
		try {
			gZIPOutputStream = new GZIPOutputStream(outputStream);
			int count = 0;
			byte[] data = new byte[BUFFER];
			while ((count = inputStream.read(data, 0, BUFFER)) != -1) {
				gZIPOutputStream.write(data, 0, count);
			}
			gZIPOutputStream.finish();
			gZIPOutputStream.flush();
			gZIPOutputStream.close();
			gZIPOutputStream = null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (gZIPOutputStream != null) {
					gZIPOutputStream.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.biz.medicare.inhospital.service.gzip.GzipService#decompress(
	 * java.io. InputStream, java.io.OutputStream)
	 */
	@Override
	public void decompress(InputStream inputStream, OutputStream outputStream) {
		// TODO Auto-generated method stub
		GZIPInputStream gZIPInputStream = null;
		try {
			gZIPInputStream = new GZIPInputStream(inputStream);
			int count = 0;
			byte[] data = new byte[BUFFER];
			while ((count = gZIPInputStream.read(data, 0, BUFFER)) != -1) {
				outputStream.write(data, 0, count);
			}
			gZIPInputStream.close();
			gZIPInputStream = null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (gZIPInputStream != null) {
					gZIPInputStream.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.biz.medicare.inhospital.service.gzip.GzipService#compress(
	 * java.io. File, boolean)
	 */
	@Override
	public void compress(File file, boolean isDelete) {
		// TODO Auto-generated method stub
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			fileOutputStream = new FileOutputStream(file.getPath() + EXT);
			this.compress(fileInputStream, fileOutputStream);
			fileOutputStream.flush();
			fileOutputStream.close();
			fileOutputStream = null;
			fileInputStream.close();
			fileInputStream = null;
			if (isDelete) {
				file.delete();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.biz.medicare.inhospital.service.gzip.GzipService#decompress(
	 * java.io. File, boolean)
	 */
	@Override
	public void decompress(File file, boolean isDelete) {
		// TODO Auto-generated method stub
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			fileOutputStream = new FileOutputStream(file.getPath().replace(EXT, ""));
			this.decompress(fileInputStream, fileOutputStream);
			fileOutputStream.flush();
			fileOutputStream.close();
			fileOutputStream = null;
			fileInputStream.close();
			fileInputStream = null;
			if (isDelete) {
				file.delete();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
