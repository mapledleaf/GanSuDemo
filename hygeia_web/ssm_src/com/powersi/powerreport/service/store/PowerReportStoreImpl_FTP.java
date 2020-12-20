package com.powersi.powerreport.service.store;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.powersi.comm.exception.ApusException;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.UtilFunc;

/**
 * 基于ftp进行报表数据存储
 * 不推荐使用，推荐使用sftp
 * @author 李志钢
 *
 */
public class PowerReportStoreImpl_FTP implements PowerReportStore {

	@Override
	public String store(InputStream ips,String path) {
		
		//产生上传路径
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyyMM");
		String dateStr = format.format(date);
		String destFilePath ="report/"+dateStr;//文件上传后路径
		
		String fileName = UtilFunc.getUUID()+".xls";//文件名
		
		//创建FTPClient对象
		FTPClient ftpClient = new FTPClient();
		try {
			FTPLogin(ftpClient);
			//处理上传到服务的地址，判断是否存在或者是否在根目录下面，没有则会自动进行创建
			if(!destFilePath.equalsIgnoreCase("/")&&!ftpClient.changeWorkingDirectory(destFilePath)) {
				// 多层目录循环创建，创建目录并指定上传目录
				String[] paths = destFilePath.split("/");
				for (int i = 0; i < paths.length; i++) {
					if (!ftpClient.changeWorkingDirectory(new String(paths[i].getBytes()))) {
						if (ftpClient.makeDirectory(new String(paths[i].getBytes()))) {
							ftpClient.changeWorkingDirectory(new String(paths[i].getBytes()));
						}
					}
				}
			}
			setWorkPathAndFTPParam(destFilePath, ftpClient); 
			//执行文件的上传，上传到目录下的指定文件中
			boolean success = ftpClient.storeFile(fileName, ips);
			//关闭流
			ips.close();

			if(!success) {
				throw new HygeiaException("上传文件失败");
			}
		} catch (IOException e) {
			throw new HygeiaException("向FTP服务器上传文件失败",e);
		} finally {
			closeFtpCon(ftpClient);
		}
		return destFilePath+"/"+fileName;
	}

	@Override
	public void get(String store_id, OutputStream ops) {
		//创建FTPClient对象
		FTPClient ftpClient = new FTPClient();
		//ftp服务器文件地址中获取到文件名称
		String fileName = store_id.substring(store_id.lastIndexOf("/")+1);
		//获取ftp服务器文件地址中的目录路径
		String dirPath =  store_id.substring(0,store_id.lastIndexOf("/")+1);
		try { 
			FTPLogin(ftpClient);
			setWorkPathAndFTPParam(dirPath,ftpClient);
			
			if (ftpClient.listFiles(new String(store_id.getBytes())).length == 0) {  
	            throw new HygeiaException("下载文件不存在,传入的文件路径为："+store_id);  
	        }  
			
	        //得到该目录下的所有文件
	        FTPFile[] ftpFiles = ftpClient.listFiles(); 
	        //遍历整个目录的文件
	        for(FTPFile ftpFile:ftpFiles){ 
	        	//从该目录下的所有文件中找到那个需要下载的文件
	            if(ftpFile.getName().equals(fileName)){
	            	//把该文件以流的方式传输，下载到本地的目标文件中
	                boolean success = ftpClient.retrieveFile(fileName, ops);
	                ops.flush();
	                //关闭流
	                ops.close();
	                if(!success){
	                	throw new HygeiaException("文件下载失败,传入的文件路径为："+store_id); 
	                }
	            } 
	        }
	    } catch (IOException e) { 
	    	throw new HygeiaException("文件处理异常,传入的文件路径为："+store_id,e); 
	    } finally { 
	    	closeFtpCon(ftpClient); 
	    } 
	}

	@Override
	public void delte(String store_id) {
		//创建FTPClient对象
		FTPClient ftpClient = new FTPClient();
		FTPLogin(ftpClient);
		//判断ftp连接是否为空
		if( ftpClient!=null ){  
			try {
				//判断文件是否存在
				if (ftpClient.listFiles(new String(store_id.getBytes())).length == 0) {  
				    throw new ApusException("删除的文件不存在,传入的文件路径为："+store_id);  
				}  
				//不为空则删除指定路径文件
				boolean success = ftpClient.deleteFile(store_id); 
				closeFtpCon(ftpClient);
				if(!success) {
					throw new ApusException("删除文件失败,传入的文件路径为："+store_id);
				}
			} catch (IOException e) {
				throw new ApusException("删除文件出错,io异常,传入的文件路径为："+store_id,e);
			}
        }
		
	}

	/**
	 * 登录ftp服务器
	 * @throws SocketException
	 * @throws IOException
	 */
	public void FTPLogin(FTPClient ftpClient) {
		String ftp_ip = "172.18.100.32";//ftp服务器地址
		int port = 21; //FTP服务器端口
		String username = "ithov";//FTP登录账号
		String password = "123456";//FTP登录密码
		/*String ftp_ip = dictService.getValue("OSS_PARAM", "FTP_IP");
		int port = dictService.getValue_int("OSS_PARAM", "FTP_PORT");
		String username = dictService.getValue("OSS_PARAM", "FTP_USERNAME");
		String password = dictService.getValue("OSS_PARAM", "FTP_PSWD");*/
		try {
			int reply;//响应代码
			// 根据有无端口号选择不同的方式连接FTP服务器 
			if (port > -1){ 
				ftpClient.connect(ftp_ip, port);
			}else{ 
				ftpClient.connect(ftp_ip); 
			} 
			//获取到FTP的响应代码
			reply = ftpClient.getReplyCode();
			//判断是否连接服务器成功
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				throw new HygeiaException("ftp服务器连接失败,请检查服务器地址与端口是否正确");
			}
			//通过用户密码登录到FTP服务器，返回是否登录成功
			if(!ftpClient.login(username, password)) {
				ftpClient.disconnect();
				throw new HygeiaException("ftp服务器登陆验证失败，请检查账号和密码是否正确! ");
			}
		} catch (SocketException e) {
			throw new HygeiaException("ftp服务器连接失败,请检查端口是否正确 ",e);
		} catch (IOException e) {
			throw new HygeiaException("ftp服务器连接失败,请检查主机地址（IP）是否正确",e);
		}
	}
	
	/**
	 * 转换工作目录到指定路径目录下，设置FTP服务器文件处理参数
	 * @param destFilePath 指定目录路径,没有会自动创建
	 * @param ftpClient    FtpClient对象
	 * @throws IOException
	 */
	public void setWorkPathAndFTPParam(String destFilePath, FTPClient ftpClient)
			throws IOException {
		//指定上传路径
		ftpClient.changeWorkingDirectory(destFilePath);
		//设置每次读取文件流时缓存数组的大小
		ftpClient.setBufferSize(1024*2);
		//设置字符集
		//ftpClient.setControlEncoding("iso-8859-1");
		//设置文件类型（二进制） 
		ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
	} 

	/** 
     *销毁ftp连接  
     */  
    public void closeFtpCon(FTPClient ftpClient){  
        if(ftpClient !=null){
        	//如果还连接着ftp服务器
            if(ftpClient.isConnected()){  
                try {  
                	//从ftp服务器登出
                    ftpClient.logout();
                    //断开连接
                    ftpClient.disconnect();  
                } catch (Exception e) {  
                	throw new HygeiaException("销毁ftp连接异常",e);  
                }   
            }  
        }  
    }

}
