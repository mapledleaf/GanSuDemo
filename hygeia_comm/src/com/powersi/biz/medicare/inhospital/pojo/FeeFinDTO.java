package com.powersi.biz.medicare.inhospital.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.powersi.biz.medicare.comm.pojo.RemotingDTO;

/**
 * 
 * @author 刘勇
 *
 */
public class FeeFinDTO extends RemotingDTO {

	private static final long serialVersionUID = 1L;

	private String kc22id;	//人员医疗费用明细ID

	private String kc21id;	//人员医疗费用明细ID

	private String akb020;	//医疗服务机构编号

	private String aaz217;	//人员医疗就诊事件ID

	private Long bka001;	//费用批次

	private Long aaz213;	//人员医疗费用明细ID
	
	private String aaa027;	//统筹区编码（分区用）
	
	private Date akc194;	//医患最终结算日期
	
	private Long aaz231;	//社保三大目录ID
	/**
	 * 中心药品项目编码
	 */
	private String ake001;
	
	/**
	 * 中心药品项目名称
	 */
	private String ake002;

	private Long aaz277;	//医疗机构三大目录ID
	/**
	 * 医院药品项目编码
	 */
	private String ake005;
	
	/**
	 * 医院药品项目名称
	 */
	private String ake006;
	
	private String ake105;	//药监局药品编码
	
	private String ake003;	//三大目录类别

	private String aka063;	//医疗发票项目类别
	
	private String aka065;	//收费项目等级
	
	private String bka511;	//城职对应待遇值（自付比例支付类型）
	
	private String bka071;	//医疗机构序列号
	
	private String aka070;	//剂型
	
	private String aka067;	//药品剂量单位
	
	private String aka074;	//药品剂量单位
	
	private String bka073;	//厂家
	
	private BigDecimal akc226;
	
	private BigDecimal bka040;	//标准单价
	
	private BigDecimal aae019;	//金额
	
	private String bkz103;	//限制用药标识
	
	private String bka067;	//费用冻结标志，用来表识参保人所在单位的基本医疗保险被冻结期间录入的费用。0：未冻结；1：已冻结；2：冻结已处理
	
	/**
	 * 对应费用序列号
	 */
	private Long bka062;
	
	/**
	 * 冲减金额（主要为计算方便使用）
	 */
	private BigDecimal bka059;
	
	private Date bka069;	//费用上传时间
	
	private Date ake007;	//费用发生日期

	private String bka063;	//录入人工号

	private String bka064;	//录入人名称
	
	private Date bka065;	//录入时间
	
	private String bka070;	//处方号
	
	private String bka074;	//处方医师编号
	
	private String bka075;	//处方医师名称
	
	private String bkm001;	//是否在岗医师标识：0，非在岗；1，在岗
	
	private String bkm002;	//超时标志，0未超时，1超时上传未申诉，2超时上传正在申诉，3超时上传申诉审核同意，4超时上传申诉审核不同意
	
	private String bka044;	//传输标志(0:未传输 1:已成功传输 2:未成功传输）
	
	private String aae100;	//当前有效标志
	
	public String getAaa027() {
		return aaa027;
	}

	public void setAaa027(String aaa027) {
		this.aaa027 = aaa027;
	}

	public Date getAkc194() {
		return akc194;
	}

	public void setAkc194(Date akc194) {
		this.akc194 = akc194;
	}

	public String getAke105() {
		return ake105;
	}

	public void setAke105(String ake105) {
		this.ake105 = ake105;
	}

	public String getAka070() {
		return aka070;
	}

	public void setAka070(String aka070) {
		this.aka070 = aka070;
	}

	public String getAka067() {
		return aka067;
	}

	public void setAka067(String aka067) {
		this.aka067 = aka067;
	}

	public String getAka074() {
		return aka074;
	}

	public void setAka074(String aka074) {
		this.aka074 = aka074;
	}

	/**
	 * 
	 * @return 用量
	 */
	public BigDecimal getAkc226() {
		return akc226;
	}

	/**
	 * 
	 * @param akc226
	 *            用量
	 */
	public void setAkc226(BigDecimal akc226) {
		this.akc226 = akc226;
	}

	public BigDecimal getBka040() {
		return bka040;
	}

	public void setBka040(BigDecimal bka040) {
		this.bka040 = bka040;
	}

	public BigDecimal getAae019() {
		return aae019;
	}

	public void setAae019(BigDecimal aae019) {
		this.aae019 = aae019;
	}

	public String getBkz103() {
		return bkz103;
	}

	public void setBkz103(String bkz103) {
		this.bkz103 = bkz103;
	}

	public String getBkm001() {
		return bkm001;
	}

	public void setBkm001(String bkm001) {
		this.bkm001 = bkm001;
	}

	public String getBkm002() {
		return bkm002;
	}

	public void setBkm002(String bkm002) {
		this.bkm002 = bkm002;
	}

	public Date getAke007() {
		return ake007;
	}

	public void setAke007(Date ake007) {
		this.ake007 = ake007;
	}
	
	public String getAka065() {
		return aka065;
	}

	public void setAka065(String aka065) {
		this.aka065 = aka065;
	}
	public Long getAaz277() {
		return aaz277;
	}

	public void setAaz277(Long aaz277) {
		this.aaz277 = aaz277;
	}
	public Long getAaz231() {
		return aaz231;
	}

	public void setAaz231(Long aaz231) {
		this.aaz231 = aaz231;
	}
	public String getKc22id() {
		return kc22id;
	}

	public void setKc22id(String kc22id) {
		this.kc22id = kc22id == null ? null : kc22id.trim();
	}

	public String getKc21id() {
		return kc21id;
	}

	public void setKc21id(String kc21id) {
		this.kc21id = kc21id == null ? null : kc21id.trim();
	}

	public String getAkb020() {
		return akb020;
	}

	public void setAkb020(String akb020) {
		this.akb020 = akb020 == null ? null : akb020.trim();
	}

	public String getAaz217() {
		return aaz217;
	}

	public void setAaz217(String aaz217) {
		this.aaz217 = aaz217 == null ? null : aaz217.trim();
	}

	public Long getBka001() {
		return bka001;
	}

	public void setBka001(Long bka001) {
		this.bka001 = bka001;
	}

	public Long getAaz213() {
		return aaz213;
	}

	public void setAaz213(Long aaz213) {
		this.aaz213 = aaz213;
	}

	public String getAka063() {
		return aka063;
	}

	public void setAka063(String aka063) {
		this.aka063 = aka063 == null ? null : aka063.trim();
	}

	public String getAke003() {
		return ake003;
	}

	public void setAke003(String ake003) {
		this.ake003 = ake003 == null ? null : ake003.trim();
	}

	/**
	 * 
	 * @return 中心药品项目编码
	 */
	public String getAke001() {
		return ake001;
	}

	/**
	 * 
	 * @param ake001
	 *            中心药品项目编码
	 */
	public void setAke001(String ake001) {
		this.ake001 = ake001 == null ? null : ake001.trim();
	}

	/**
	 * 
	 * @return 中心药品项目名称
	 */
	public String getAke002() {
		return ake002;
	}

	/**
	 * 
	 * @param ake002
	 *            中心药品项目名称
	 */
	public void setAke002(String ake002) {
		this.ake002 = ake002 == null ? null : ake002.trim();
	}

	/**
	 * 
	 * @return 医院药品项目编码
	 */
	public String getAke005() {
		return ake005;
	}

	/**
	 * 
	 * @param ake005
	 *            医院药品项目编码
	 */
	public void setAke005(String ake005) {
		this.ake005 = ake005 == null ? null : ake005.trim();
	}

	/**
	 * 
	 * @return 医院药品项目名称
	 */
	public String getAke006() {
		return ake006;
	}

	/**
	 * 
	 * @param ake006
	 *            医院药品项目名称
	 */
	public void setAke006(String ake006) {
		this.ake006 = ake006 == null ? null : ake006.trim();
	}


	public Long getBka062() {
		return bka062;
	}

	public void setBka062(Long bka062) {
		this.bka062 = bka062;
	}

	public String getBka063() {
		return bka063;
	}

	public void setBka063(String bka063) {
		this.bka063 = bka063 == null ? null : bka063.trim();
	}

	public String getBka064() {
		return bka064;
	}

	public void setBka064(String bka064) {
		this.bka064 = bka064 == null ? null : bka064.trim();
	}

	public Date getBka065() {
		return bka065;
	}

	public void setBka065(Date bka065) {
		this.bka065 = bka065;
	}

	public String getBka067() {
		return bka067;
	}

	public void setBka067(String bka067) {
		this.bka067 = bka067 == null ? null : bka067.trim();
	}

	public Date getBka069() {
		return bka069;
	}

	public void setBka069(Date bka069) {
		this.bka069 = bka069;
	}

	public String getBka070() {
		return bka070;
	}

	public void setBka070(String bka070) {
		this.bka070 = bka070 == null ? null : bka070.trim();
	}

	public String getBka071() {
		return bka071;
	}

	public void setBka071(String bka071) {
		this.bka071 = bka071 == null ? null : bka071.trim();
	}

	public String getBka073() {
		return bka073;
	}

	public void setBka073(String bka073) {
		this.bka073 = bka073 == null ? null : bka073.trim();
	}

	public String getBka074() {
		return bka074;
	}

	public void setBka074(String bka074) {
		this.bka074 = bka074 == null ? null : bka074.trim();
	}

	public String getBka075() {
		return bka075;
	}

	public void setBka075(String bka075) {
		this.bka075 = bka075 == null ? null : bka075.trim();
	}

	public String getBka044() {
		return bka044;
	}

	public void setBka044(String bka044) {
		this.bka044 = bka044 == null ? null : bka044.trim();
	}

	/**
	 * @return the bka511
	 */
	public String getBka511() {
		return bka511;
	}

	/**
	 * @param bka511
	 *            the bka511 to set
	 */
	public void setBka511(String bka511) {
		this.bka511 = bka511;
	}

	public String getAae100() {
		return aae100;
	}

	public void setAae100(String aae100) {
		this.aae100 = aae100 == null ? null : aae100.trim();
	}

	public BigDecimal getBka059() {
		return bka059;
	}

	public void setBka059(BigDecimal bka059) {
		this.bka059 = bka059;
	}

}
