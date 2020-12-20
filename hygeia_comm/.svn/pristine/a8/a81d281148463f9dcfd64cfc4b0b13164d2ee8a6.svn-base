/**
 * 
 */
package com.powersi.biz.medicare.inhospital.service.cert;

import org.springframework.stereotype.Service;

/**
 * @author LiuYong
 * @version 1.0 com.powersi.biz.medicare.inhospital.service.cert.
 *          ExportCertFormKeystoreServiceImpl 2016年5月11日 下午7:52:40
 */
@Service
public class ExportCertFormKeystoreServiceImpl implements ExportCertFormKeystoreService {

	/**
	 * @author LiuYong
	 * long
	 * com.powersi.biz.medicare.inhospital.service.cert.serialVersionUID
	 * 2016年5月15日
	 * 下午10:22:04
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.biz.medicare.inhospital.service.cert.ExportCertFormKeystore#
	 * execCommand(java.lang.String[])
	 */
	@Override
	public void execCommand(String[] arstringCommand) {
		// TODO Auto-generated method stub
		for (int i = 0; i < arstringCommand.length; i++) {
			System.out.print(arstringCommand[i] + " ");
		}
		try {
			Runtime.getRuntime().exec(arstringCommand);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.biz.medicare.inhospital.service.cert.ExportCertFormKeystore#
	 * execCommand(java.lang.String)
	 */
	@Override
	public void execCommand(String arstringCommand) {
		// TODO Auto-generated method stub
		try {
			Runtime.getRuntime().exec(arstringCommand);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.biz.medicare.inhospital.service.cert.ExportCertFormKeystore#
	 * genkey(java.lang.String)
	 */
	@Override
	public void genkey(String path) {
		// TODO Auto-generated method stub
		String[] arstringCommand = new String[] { "cmd ", "/k", "start", // cmd
																			// Shell命令
				"keytool", "-genkey", // -genkey表示生成密钥
				"-validity", // -validity指定证书有效期(单位：天)，这里是36000天
				"36500", "-keysize", // 指定密钥长度
				"1024", "-alias", // -alias指定别名，这里是ss
				// "ss",
				"ukey", "-keyalg", // -keyalg 指定密钥的算法 (如 RSA DSA（如果不指定默认采用DSA）)
				"RSA", "-keystore", // -keystore指定存储位置，这里是d:/demo.keystore
				// "d:/demo.keystore",
				// path,
				"C:/Users/Administrator/Downloads/ukey.keystore", "-dname", // CN=(名字与姓氏),
																			// OU=(组织单位名称),
																			// O=(组织名称),
																			// L=(城市或区域名称),
																			// ST=(州或省份名称),
																			// C=(单位的两字母国家代码)"
				"CN=(LiuYong), OU=(JSON), O=(JSON), L=(ChangSha), ST=(HuNan), C=(CN)", "-storepass", // 指定密钥库的密码(获取keystore信息所需的密码)
				// "123456",
				"ukey", "-keypass", // 指定别名条目的密码(私钥的密码)
				// "123456",
				"ukeyder", "-v"// -v 显示密钥库中的证书详细信息
		};
		execCommand(arstringCommand);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.biz.medicare.inhospital.service.cert.ExportCertFormKeystore#
	 * export(java.lang.String)
	 */
	@Override
	public void export(String path) {
		// TODO Auto-generated method stub
		String[] arstringCommand = new String[] {

				"cmd ", "/k", "start", // cmd Shell命令

				"keytool", "-export", // - export指定为导出操作
				"-keystore", // -keystore指定keystore文件，这里是d:/demo.keystore
				// "d:/demo.keystore",
				// path,
				"C:/Users/Administrator/Downloads/ukey.keystore", "-alias", // -alias指定别名，这里是ss
				// "ss",
				"ukey", "-file", // -file指向导出路径
				// "d:/demo.cer",
				"C:/Users/Administrator/Downloads/ukey.cer", "-storepass", // 指定密钥库的密码
				// "123456"
				"ukey"

		};
		execCommand(arstringCommand);
	}

}
