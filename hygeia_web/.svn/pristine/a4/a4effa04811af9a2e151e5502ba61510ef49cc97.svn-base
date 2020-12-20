package com.powersi.powerreport.service.store;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.powersi.comm.exception.ApusException;
import com.powersi.comm.utils.SpringUtil;
import com.powersi.comm.utils.ToolUtil;
import com.powersi.pcloud.dict.service.DictService;

/**
 * 基于sftp进行报表数据存储
 * 
 * @author 李志钢
 *
 */
public class PowerReportStoreImpl_SFTP implements PowerReportStore {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public String store(InputStream ips, String filePath) {
		// 产生上传路径
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyyMM");
		String dateStr = format.format(date);
		DictService dictService = (DictService) SpringUtil.getInstance().getBean("dictService");
		String sftpDoFileDir = dictService.getValue("OSS_PARAM", "SFTP_DO_FILE_DIR", "/uploadFile");
		String destFilePath = sftpDoFileDir + "/biz/report/" + dateStr;// 文件上传后路径
		// 本地目录下获取到本地文件名称
		String fileName = null;
		if (filePath.lastIndexOf("/") != -1 && filePath.lastIndexOf("\\") == -1) {
			fileName = filePath.substring(filePath.lastIndexOf("/") + 1);

		} else if (filePath.lastIndexOf("\\") != -1 && filePath.lastIndexOf("/") == -1) {
			fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
		}
		// 创建FTPClient对象
		Session session = this.loginSsh();
		ChannelSftp sshSftp = this.sshSftp(session);
		try {
			try {
				sshSftp.cd(destFilePath);
			} catch (SftpException e) {
				this.logger.warn("SFTP工作目录：" + destFilePath + "不存在，正在创建并赋权775!");
				String path = "";
				if (destFilePath.endsWith("/")) {
					path = destFilePath.substring(1, destFilePath.length() - 1);
				} else {
					path = destFilePath.substring(1);
				}
				String[] dirs = path.split("/");
				// 一层一层，判断没有此层目录则创建
				String mkPath = "";
				for (String current_dir_name : dirs) {
					try {
						mkPath = mkPath + "/" + current_dir_name;
						sshSftp.cd(mkPath);
					} catch (SftpException ex) {
						sshSftp.mkdir(mkPath);
						sshSftp.chmod(Integer.parseInt("775", 8), mkPath);
						sshSftp.cd(mkPath);
					}
				}
				if (destFilePath.indexOf(mkPath) == -1) {
					throw new ApusException("逐一创建目录" + destFilePath + "失败!");
				}
				sshSftp.cd(destFilePath);
			}
			sshSftp.put(ips, fileName);
			this.logger.info("SFTP进行文件上传" + fileName + "，上传成功");
		} catch (Exception e) {
			throw new ApusException("SFTP进行文件上传失败，上传文件为：" + fileName, e);
		} finally {
			this.closeSftpCon(sshSftp, session);
			if (ips != null) {
				try {
					ips.close();
				} catch (IOException e) {

				}
			}
		}
		return destFilePath + "/" + fileName;
	}

	@Override
	public void get(String filePath, OutputStream ops) {
		BufferedInputStream input = null;
		try {
			DictService dictService = (DictService) SpringUtil.getInstance().getBean("dictService");
			String httpBaseUrl = dictService.getValue("OSS_PARAM", "HTTP_BASE_URL_INNER");// 访问上传的文件的基础地址
			URL url = new URL(httpBaseUrl + filePath);
			input = new BufferedInputStream(url.openStream());
			int ch = 0;
			while ((ch = input.read()) != -1) {
				ops.write(ch);
			}
		} catch (Exception e) {
			throw new ApusException("获取报表文件流处理异常", e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {

				}
			}
		}
	}

	@Override
	public void delte(String filePath) {
		// 删除路径传递为空则不进行操作，抛出异常
		if (StringUtils.isBlank(filePath)) {
			throw new ApusException("删除的文件路径为空!");
		}
		// 为适应之前报表产生的老数据，特作此判断
		if (filePath.indexOf("/") == -1) {
			return;
		}
		// 通过ssh登录sftp
		Session session = this.loginSsh();
		ChannelSftp sshSftp = this.sshSftp(session);
		try {
			String fileName = "";
			String fileDir = "";
			// 获取到文件名称和文件存放路径
			fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
			fileDir = filePath.substring(0, filePath.lastIndexOf("/") + 1);
			sshSftp.cd(fileDir);
			boolean needDel = true;
			try {
				// 通过查看文件的真实路径，判断文件是否存在，报错则不存在
				sshSftp.realpath(fileName);
			} catch (Exception e) {
				needDel = false;
				this.logger.info(fileDir + "目录下，不存在文件" + fileName + ",无法进行删除!");
			}
			if (needDel) {
				try {
					sshSftp.rm(fileName);
				} catch (Exception e) {
					// sftp删除可能出现，缓存数据等其他原因，导致realpath检测一直存在该文件，所有删除文件只记录提示警告日志
					this.logger.warn(fileDir + "目录下，删除" + fileName + "失败!" + ToolUtil.getExceptionInfo(e));
				}
			}
		} catch (Exception e) {
			throw new ApusException("登录SFTP删除文件" + filePath + ",失败", e);
		} finally {
			this.closeSftpCon(sshSftp, session);
		}
	}

	/**
	 * SSH登录服务器
	 * 
	 * @return Session
	 */
	private Session loginSsh() {
		DictService dictService = (DictService) SpringUtil.getInstance().getBean("dictService");
		String ip = dictService.getValue("OSS_PARAM", "FTP_IP");
		String psw = dictService.getValue("OSS_PARAM", "FTP_PSWD");
		String user = dictService.getValue("OSS_PARAM", "FTP_USERNAME");
		int port = dictService.getValue_int("OSS_PARAM", "FTP_PORT");
		Session session = null;
		JSch jsch = new JSch();
		try {
			if (port <= 0) {
				// 连接服务器，采用默认端口
				session = jsch.getSession(user, ip);
			} else {
				// 采用指定的端口连接服务器
				session = jsch.getSession(user, ip, port);
			}
			// 如果服务器连接不上，则抛出异常
			if (session == null) {
				throw new ApusException("SSH服务器连接不上,请检查服务器地址与端口是否正确!");
			}
			// 设置登陆主机的密码
			session.setPassword(psw);
			// 设置第一次登陆的时候提示，可选值：(ask | yes | no)
			session.setConfig("StrictHostKeyChecking", "no");
			// 设置session登陆超时时间
			session.connect(30000);
			return session;
		} catch (Exception e) {
			// 关闭ssh的session连接
			if (session != null) {
				session.disconnect();
			}
			throw new ApusException("SSH服务器登录失败", e);
		}
	}

	/**
	 * 登录sftp服务器
	 * 
	 * @return ChannelSftp
	 */
	private ChannelSftp sshSftp(Session session) {
		ChannelSftp channelSftp = null;
		try {
			// 获取sftp连接，设置对应参数
			channelSftp = (ChannelSftp) session.openChannel("sftp");
			channelSftp.connect(30000);
			channelSftp.setFilenameEncoding("UTF-8");
			return channelSftp;
		} catch (Exception e) {
			// 关闭sftp连接
			if (channelSftp != null) {
				channelSftp.disconnect();
			}
			// 关闭ssh的session连接
			if (session != null) {
				session.disconnect();
			}
			throw new ApusException("SFTP服务器登录失败", e);
		}
	}

	/**
	 * 销毁sftp连接
	 * 
	 * @param channelSftp
	 * @param session
	 */
	private void closeSftpCon(ChannelSftp channelSftp, Session session) {
		try {
			// 如果还连接着sftp服务器
			if (channelSftp != null) {
				// 从sftp服务器登出
				channelSftp.exit();
			}
		} catch (Exception e) {
			throw new ApusException("注销ftp登录异常，错误信息为：", e);
		} finally {
			if (channelSftp != null && channelSftp.isConnected()) {
				try {
					// 断开SFTP连接
					channelSftp.disconnect();
				} catch (Exception ioe) {
					throw new ApusException("断开SFTP连接异常，错误信息为：", ioe);
				}
			}
			if (session != null && session.isConnected()) {
				try {
					// 断开SSH连接
					session.disconnect();
				} catch (Exception ioe) {
					throw new ApusException("断开SSH连接异常，错误信息为：", ioe);
				}
			}
		}
	}

}
