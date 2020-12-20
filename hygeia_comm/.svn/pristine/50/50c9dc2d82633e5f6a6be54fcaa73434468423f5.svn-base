package com.powersi.hygeia.comm.license;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.powersi.comm.bean.BaseBean;
import com.powersi.comm.exception.ApusException;
import com.powersi.hygeia.comm.license.service.Base64;

/**
 * 许可证信息
 * 
 * @author 李志钢
 *
 */
public class LicenseBean extends BaseBean {
	private static final long serialVersionUID = 1L;
	private String sLicense; // 许可证完整字符串
	private Date beginDate; // 生效开始日期
	private Date endDate; // 有效期截止
	private String tcqbm; // 统筹区编码
	private String yljgbm; // 医疗机构编码
	private String gndm; // 功能代码字符串
	// 2018-11-21 huangyao 许可证中添加终端限制数
	private int limit_terminal_number;// 终端限制数
	// 人群的控制标识(许可证增加1个标识位 0不控制人群 1只能居民)_刘勇_20181220
	private String crowdcontrolidentity;

	public String getsLicense() {
		return sLicense;
	}

	public void setsLicense(String sLicense) {
		this.sLicense = sLicense;
		// 在这里解析许可证字符串，赋值到各个成员
		String param = Base64.getFromBASE64(sLicense);
		String[] licenseParam = param.split("-");
		if (licenseParam.length < 7) {
			throw new ApusException("许可证格式错误，应至少包含7个横杠拼接的域");
		}
		setTcqbm(licenseParam[0]);
		setYljgbm(licenseParam[1]);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			setBeginDate(sdf.parse(licenseParam[2].trim()));
			setEndDate(sdf.parse(licenseParam[3].trim()));
		} catch (ParseException e) {
			throw new ApusException("许可证日期转换异常", e);
		}
		// setTcqbm(licenseParam[2]);
		// setYljgbm(licenseParam[3]);
		setGndm(licenseParam[4]);
		// 2018-11-21 huangyao 将许可证中的终端限制数，放到入参中进行传递
		setLimit_terminal_number(Integer.parseInt(licenseParam[5]));
		setCrowdcontrolidentity(licenseParam[6]);
	}

	public String getCrowdcontrolidentity() {
		return crowdcontrolidentity;
	}

	public void setCrowdcontrolidentity(String crowdcontrolidentity) {
		this.crowdcontrolidentity = crowdcontrolidentity;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getTcqbm() {
		return tcqbm;
	}

	public void setTcqbm(String tcqbm) {
		this.tcqbm = tcqbm;
	}

	public String getYljgbm() {
		return yljgbm;
	}

	public void setYljgbm(String yljgbm) {
		this.yljgbm = yljgbm;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getGndm() {
		return gndm;
	}

	public void setGndm(String gndm) {
		this.gndm = gndm;
	}

	public int getLimit_terminal_number() {
		return limit_terminal_number;
	}

	public void setLimit_terminal_number(int limit_terminal_number) {
		this.limit_terminal_number = limit_terminal_number;
	}

	/**
	 * 根据模块编号得到对应的功能代码许可 1 / 0 0表示不允许用 1表示允许用，但需校验日期 2表示允许用，无需校验日期
	 * 
	 * @param nModuleID
	 *            模块编号，从1开始，分别表示hygeia_web, hygeia_api
	 * @return
	 */
	public String getGndmByModuleID(int nModuleID) {
		if (nModuleID > this.gndm.length()) {
			return "0"; // 超过了功能代码的范围
		}
		return this.gndm.substring(nModuleID - 1, nModuleID);
	}

}
