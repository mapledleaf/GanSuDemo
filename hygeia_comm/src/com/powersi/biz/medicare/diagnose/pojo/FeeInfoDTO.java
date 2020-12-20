package com.powersi.biz.medicare.diagnose.pojo;

import java.util.Date;

import com.powersi.comm.bean.BaseBean;

public class FeeInfoDTO extends BaseBean {
	private static final long serialVersionUID = 1L;
	
    private String kc22id;

    private String kc21id;
    
    private String kcd2id;

    private String kcd1id;

    private String akb020;

    private String aaz217;

    private Long bka001;

    private Long aaz213;

    private String aka063;

    private String ake003;

    private String ake001;

    private String ake002;

    private String ake005;

    private String ake006;

    private Long aaz267;

    private String ake007;

    private String aka070;

    private String bka073;
    
    private String aka074;

    private String aka067;

    private Double bka040;

    private Double akc226;

    private Double aae019;

    private Double bka059;

    private String bka060;

    private Long bka061;

    private Long bka062;

    private String bka063;

    private String bka064;

    private java.util.Date bka065;

    private String bka066;

    private String bka067;

    private Long bka068;

    private java.util.Date bka069;

    private String bka070;

    private String bka071;

    private String bka074;

    private String bka075;

    private String bka076;

    private String bka044;

    private String bka511;

    private String bka512;

    private Double aka057;
    
    private Double bkc106;
    
    private String aaa027;
    
    private String akc194;
    
    private Long aaz231;
    
    private Long aaz277;
    
    private String ake105;
    
    private String aka065;
    
    private String bkz103;
    
    private String bkm001;
    
    private String bkm002;
    
    private String aae100;
    
    private String bkc109;
    
    
    
    public String getBkc109() {
		return bkc109;
	}
	public void setBkc109(String bkc109) {
		this.bkc109 = bkc109;
	}
	public Double getAka057() {
		return aka057;
	}
	public void setAka057(Double aka057) {
		this.aka057 = aka057;
	}
	public Double getBkc106() {
		return bkc106;
	}
	public void setBkc106(Double bkc106) {
		this.bkc106 = bkc106;
	}
	/**
     * 唯一索引
     * @return
     */
    public String getKc22id() {
        return kc22id;
    }
    /**
     * 唯一索引
     * @return
     */
    public void setKc22id(String kc22id) {
        this.kc22id = kc22id == null ? null : kc22id.trim();
    }
    /**
     * 主业务表唯一索引
     * @return
     */
    public String getKc21id() {
        return kc21id;
    }
    /**
     * 主业务表唯一索引
     * @return
     */
    public void setKc21id(String kc21id) {
        this.kc21id = kc21id == null ? null : kc21id.trim();
    }
    
    
    public String getKcd2id() {
		return kcd2id;
	}
	public void setKcd2id(String kcd2id) {
		this.kcd2id = kcd2id;
	}
	public String getKcd1id() {
		return kcd1id;
	}
	public void setKcd1id(String kcd1id) {
		this.kcd1id = kcd1id;
	}
	/**
     * 医院编号
     * @return
     */
    public String getAkb020() {
        return akb020;
    }
    /**
     * 医院编号
     * @return
     */
    public void setAkb020(String akb020) {
        this.akb020 = akb020 == null ? null : akb020.trim();
    }
    /**
     * 业务序列号
     * @return
     */
    public String getAaz217() {
        return aaz217;
    }
    /**
     * 业务序列号
     * @return
     */
    public void setAaz217(String aaz217) {
        this.aaz217 = aaz217 == null ? null : aaz217.trim();
    }
    /**
     * 费用批次
     * @return
     */
    public Long getBka001() {
        return bka001;
    }
    /**
     * 费用批次
     * @return
     */
    public void setBka001(Long bka001) {
        this.bka001 = bka001;
    }
    /**
     * 费用序列号
     * @return
     */
    public Long getAaz213() {
        return aaz213;
    }
    /**
     * 费用序列号
     * @return
     */
    public void setAaz213(Long aaz213) {
        this.aaz213 = aaz213;
    }
    /**
     * 统计类别
     * @return
     */
    public String getAka063() {
        return aka063;
    }
    /**
     * 统计类别
     * @return
     */
    public void setAka063(String aka063) {
        this.aka063 = aka063 == null ? null : aka063.trim();
    }
    /**
     * 项目药品类型(0:项目，1：西药，2：中成药，3：中草药)
     * @return
     */
    public String getAke003() {
        return ake003;
    }
    /**
     * 项目药品类型(0:项目，1：西药，2：中成药，3：中草药)
     * @return
     */
    public void setAke003(String ake003) {
        this.ake003 = ake003 == null ? null : ake003.trim();
    }
    /**
     * 中心药品项目编码
     * @return
     */
    public String getAke001() {
        return ake001;
    }
    /**
     * 中心药品项目编码
     * @return
     */
    public void setAke001(String ake001) {
        this.ake001 = ake001 == null ? null : ake001.trim();
    }
    /**
     * 中心药品项目名称
     * @return
     */
    public String getAke002() {
        return ake002;
    }
    /**
     * 中心药品项目名称
     * @return
     */
    public void setAke002(String ake002) {
        this.ake002 = ake002 == null ? null : ake002.trim();
    }
    /**
     * 医院药品项目编码
     * @return
     */
    public String getAke005() {
        return ake005;
    }
    /**
     * 医院药品项目编码
     * @return
     */
    public void setAke005(String ake005) {
        this.ake005 = ake005 == null ? null : ake005.trim();
    }
    /**
     * 医院药品项目名称
     * @return
     */
    public String getAke006() {
        return ake006;
    }
    /**
     * 医院药品项目名称
     * @return
     */
    public void setAke006(String ake006) {
        this.ake006 = ake006 == null ? null : ake006.trim();
    }
    /**
     * 申请序列号
     * @return
     */
    public Long getAaz267() {
        return aaz267;
    }
    /**
     * 申请序列号
     * @return
     */
    public void setAaz267(Long aaz267) {
        this.aaz267 = aaz267;
    }
    /**
     * 费用发生时间
     * @return
     */
    public String getAke007() {
        return ake007;
    }
    /**
     * 费用发生时间
     * @return
     */
    public void setAke007(String ake007) {
        this.ake007 = ake007;
    }
    /**
     * 剂型
     * @return
     */
    public String getAka070() {
        return aka070;
    }
    /**
     * 剂型
     * @return
     */
    public void setAka070(String aka070) {
        this.aka070 = aka070 == null ? null : aka070.trim();
    }
    /**
     * 厂家
     * @return
     */
    public String getBka073() {
        return bka073;
    }
    /**
     * 厂家
     * @return
     */
    public void setBka073(String bka073) {
        this.bka073 = bka073 == null ? null : bka073.trim();
    }
    /**
     * 规格
     * @return
     */
    public String getAka074() {
        return aka074;
    }
    /**
     * 规格
     * @return
     */
    public void setAka074(String aka074) {
        this.aka074 = aka074 == null ? null : aka074.trim();
    }
    /**
     * 计量单位
     * @return
     */
    public String getAka067() {
        return aka067;
    }
    /**
     * 计量单位
     * @return
     */
    public void setAka067(String aka067) {
        this.aka067 = aka067 == null ? null : aka067.trim();
    }
    /**
     * 单价
     * @return
     */
    public Double getBka040() {
        return bka040;
    }
    /**
     * 单价
     * @return
     */
    public void setBka040(Double bka040) {
        this.bka040 = bka040;
    }
    /**
     * 用量
     * @return
     */
    public Double getAkc226() {
        return akc226;
    }
    /**
     * 用量
     * @return
     */
    public void setAkc226(Double akc226) {
        this.akc226 = akc226;
    }
    /**
     * 金额
     * @return
     */
    public Double getAae019() {
        return aae019;
    }
    /**
     * 金额
     * @return
     */
    public void setAae019(Double aae019) {
        this.aae019 = aae019;
    }
    /**
     * 冲减金额（主要为计算方便使用）
     * @return
     */
    public Double getBka059() {
        return bka059;
    }
    /**
     * 冲减金额（主要为计算方便使用）
     * @return
     */
    public void setBka059(Double bka059) {
        this.bka059 = bka059;
    }
    /**
     * 使用标志（1：出院带药 2：抢救用药 3：急诊）
     * @return
     */
    public String getBka060() {
        return bka060;
    }
    /**
     * 使用标志（1：出院带药 2：抢救用药 3：急诊）
     * @return
     */
    public void setBka060(String bka060) {
        this.bka060 = bka060 == null ? null : bka060.trim();
    }
    /**
     * 出院带药天数
     * @return
     */
    public Long getBka061() {
        return bka061;
    }
    /**
     * 出院带药天数
     * @return
     */
    public void setBka061(Long bka061) {
        this.bka061 = bka061;
    }
    /**
     * 对应费用序列号
     * @return
     */
    public Long getBka062() {
        return bka062;
    }
    /**
     * 对应费用序列号
     * @return
     */
    public void setBka062(Long bka062) {
        this.bka062 = bka062;
    }
    /**
     * 录入人工号
     * @return
     */
    public String getBka063() {
        return bka063;
    }
    /**
     * 录入人工号
     * @return
     */
    public void setBka063(String bka063) {
        this.bka063 = bka063 == null ? null : bka063.trim();
    }
    /**
     * 录入人
     * @return
     */
    public String getBka064() {
        return bka064;
    }
    /**
     * 录入人
     * @return
     */
    public void setBka064(String bka064) {
        this.bka064 = bka064 == null ? null : bka064.trim();
    }
    /**
     * 录入时间
     * @return
     */
    public Date getBka065() {
        return bka065;
    }
    /**
     * 录入时间
     * @return
     */
    public void setBka065(Date bka065) {
        this.bka065 = bka065;
    }
    /**
     * 计算标志
     * @return
     */
    public String getBka066() {
        return bka066;
    }
    /**
     * 计算标志
     * @return
     */
    public void setBka066(String bka066) {
        this.bka066 = bka066 == null ? null : bka066.trim();
    }
    /**
     * 费用冻结标志，用来表识参保人所在单位的基本医疗保险被冻结期间录入的费用。0：未冻结；1：已冻结；2：冻结已处理
     * @return
     */
    public String getBka067() {
        return bka067;
    }
    /**
     * 费用冻结标志，用来表识参保人所在单位的基本医疗保险被冻结期间录入的费用。0：未冻结；1：已冻结；2：冻结已处理
     * @return
     */
    public void setBka067(String bka067) {
        this.bka067 = bka067 == null ? null : bka067.trim();
    }
    /**
     * 对应冻结的费用序列号
     * @return
     */
    public Long getBka068() {
        return bka068;
    }
    /**
     * 对应冻结的费用序列号
     * @return
     */
    public void setBka068(Long bka068) {
        this.bka068 = bka068;
    }
    /**
     * 费用上传时间
     * @return
     */
    public Date getBka069() {
        return bka069;
    }
    /**
     * 费用上传时间
     * @return
     */
    public void setBka069(Date bka069) {
        this.bka069 = bka069;
    }
    /**
     * 处方号
     * @return
     */
    public String getBka070() {
        return bka070;
    }
    /**
     * 处方号
     * @return
     */
    public void setBka070(String bka070) {
        this.bka070 = bka070 == null ? null : bka070.trim();
    }
    /**
     * 对应医院费用号
     * @return
     */
    public String getBka071() {
        return bka071;
    }
    /**
     * 对应医院费用号
     * @return
     */
    public void setBka071(String bka071) {
        this.bka071 = bka071 == null ? null : bka071.trim();
    }

    /**
     * 处方医生编号
     * @return
     */
    public String getBka074() {
        return bka074;
    }
    /**
     * 处方医生编号
     * @return
     */
    public void setBka074(String bka074) {
        this.bka074 = bka074 == null ? null : bka074.trim();
    }
    /**
     * 处方医生姓名
     * @return
     */
    public String getBka075() {
        return bka075;
    }
    /**
     * 处方医生姓名
     * @return
     */
    public void setBka075(String bka075) {
        this.bka075 = bka075 == null ? null : bka075.trim();
    }
    /**
     * 审核标志
     * @return
     */
    public String getBka076() {
        return bka076;
    }
    /**
     * 审核标志
     * @return
     */
    public void setBka076(String bka076) {
        this.bka076 = bka076 == null ? null : bka076.trim();
    }
    /**
     * 传输标志(0:未传输 1:已成功传输 2:未成功传输）
     * @return
     */
    public String getBka044() {
        return bka044;
    }
    /**
     * 传输标志(0:未传输 1:已成功传输 2:未成功传输）
     * @return
     */
    public void setBka044(String bka044) {
        this.bka044 = bka044 == null ? null : bka044.trim();
    }
    /**
     * 城职对应待遇值
     * @return
     */
    public String getBka511() {
        return bka511;
    }
    /**
     * 城职对应待遇值
     * @return
     */
    public void setBka511(String bka511) {
        this.bka511 = bka511 == null ? null : bka511.trim();
    }
    /**
     * 城乡对应待遇值
     * @return
     */
    public String getBka512() {
        return bka512;
    }
    /**
     * 城乡对应待遇值
     * @return
     */
    public void setBka512(String bka512) {
        this.bka512 = bka512 == null ? null : bka512.trim();
    }
    
    /**
     * 统筹区编码（分区用）
     * @return
     */
	public String getAaa027() {
		return aaa027;
	}
	/**
	 * 统筹区编码（分区用）
	 * @param aaa027
	 */
	public void setAaa027(String aaa027) {
		this.aaa027 = aaa027;
	}
	/**
	 * 医患最终结算日期
	 * @return
	 */
	public String getAkc194() {
		return akc194;
	}
	/**
	 * 医患最终结算日期
	 * @param akc194
	 */
	public void setAkc194(String akc194) {
		this.akc194 = akc194;
	}
	/**
	 * 社保三大目录ID
	 * @return
	 */
	public Long getAaz231() {
		return aaz231;
	}
	/**
	 * 社保三大目录ID
	 * @param aaz231
	 */
	public void setAaz231(Long aaz231) {
		this.aaz231 = aaz231;
	}
	/**
	 * 医疗机构三大目录ID
	 * @return
	 */
	public Long getAaz277() {
		return aaz277;
	}
	/**
	 * 医疗机构三大目录ID
	 * @param aaz277
	 */
	public void setAaz277(Long aaz277) {
		this.aaz277 = aaz277;
	}
	/**
	 * 药监局药品编码
	 * @return
	 */
	public String getAke105() {
		return ake105;
	}
	/**
	 * 药监局药品编码
	 * @param ake105
	 */
	public void setAke105(String ake105) {
		this.ake105 = ake105;
	}
	/**
	 * 收费项目等级
	 * @return
	 */
	public String getAka065() {
		return aka065;
	}
	/**
	 * 收费项目等级
	 * @param aka065
	 */
	public void setAka065(String aka065) {
		this.aka065 = aka065;
	}
	/**
	 * 限制用药标识
	 * @return
	 */
	public String getBkz103() {
		return bkz103;
	}
	/**
	 * 限制用药标识
	 * @param bkz103
	 */
	public void setBkz103(String bkz103) {
		this.bkz103 = bkz103;
	}
	/**
	 * 是否在岗医师标识：0，非在岗；1，在岗
	 * @return
	 */
	public String getBkm001() {
		return bkm001;
	}
	/**
	 * 是否在岗医师标识：0，非在岗；1，在岗
	 * @param bkm001
	 */
	public void setBkm001(String bkm001) {
		this.bkm001 = bkm001;
	}
	/**
	 * 超时标志，0未超时，1超时上传未申诉，2超时上传正在申诉，3超时上传申诉审核同意，4超时上传申诉审核不同意
	 * @return
	 */
	public String getBkm002() {
		return bkm002;
	}
	/**
	 * 超时标志，0未超时，1超时上传未申诉，2超时上传正在申诉，3超时上传申诉审核同意，4超时上传申诉审核不同意
	 * @param bkm002
	 */
	public void setBkm002(String bkm002) {
		this.bkm002 = bkm002;
	}
	/**
	 * 有效标志
	 * @return
	 */
	public String getAae100() {
		return aae100;
	}
	/**
	 * 有效标志
	 * @param aae100
	 */
	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}
    
    
}
