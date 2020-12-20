package com.powersi.biz.medicare.diagnose.pojo;

import java.util.Date;

import com.powersi.comm.bean.BaseBean;

public class PayInfoDTO extends BaseBean {
	private static final long serialVersionUID = 1L;
	
    private String kcd7id;

    private String kcd1id;
    
    private String kcd2id;
    
    private String kc27id;

    private String kc21id;
    
    private String kc22id;

    private String akb020;

    private String aaz217;

    private Long bka001;

    private Long aaz215;

    private Long bka077;

    private Long aaz213;

    private String aka002;

    private String aaa157;

    private Double aae019;

    private Date akc194;

    private String bka432;

    private String aae100;
    
    private String aaa027;
    
    private String bka044;

    public String getBka044() {
		return bka044;
	}

	public void setBka044(String bka044) {
		this.bka044 = bka044;
	}

	public String getKcd7id() {
        return kcd7id;
    }

    public void setKcd7id(String kcd7id) {
        this.kcd7id = kcd7id == null ? null : kcd7id.trim();
    }

    public String getKcd1id() {
        return kcd1id;
    }

    public void setKcd1id(String kcd1id) {
        this.kcd1id = kcd1id == null ? null : kcd1id.trim();
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

    public Long getAaz215() {
        return aaz215;
    }

    public void setAaz215(Long aaz215) {
        this.aaz215 = aaz215;
    }

    public Long getBka077() {
        return bka077;
    }

    public void setBka077(Long bka077) {
        this.bka077 = bka077;
    }

    public Long getAaz213() {
        return aaz213;
    }

    public void setAaz213(Long aaz213) {
        this.aaz213 = aaz213;
    }

    public String getAka002() {
        return aka002;
    }

    public void setAka002(String aka002) {
        this.aka002 = aka002 == null ? null : aka002.trim();
    }

    public String getAaa157() {
        return aaa157;
    }

    public void setAaa157(String aaa157) {
        this.aaa157 = aaa157 == null ? null : aaa157.trim();
    }

    public Double getAae019() {
        return aae019;
    }

    public void setAae019(Double aae019) {
        this.aae019 = aae019;
    }

    public Date getAkc194() {
        return akc194;
    }

    public void setAkc194(Date akc194) {
        this.akc194 = akc194;
    }

    public String getBka432() {
        return bka432;
    }

    public void setBka432(String bka432) {
        this.bka432 = bka432 == null ? null : bka432.trim();
    }

    public String getAae100() {
        return aae100;
    }

    public void setAae100(String aae100) {
        this.aae100 = aae100 == null ? null : aae100.trim();
    }

	public String getKcd2id() {
		return kcd2id;
	}

	public void setKcd2id(String kcd2id) {
		this.kcd2id = kcd2id;
	}

	public String getAaa027() {
		return aaa027;
	}

	public void setAaa027(String aaa027) {
		this.aaa027 = aaa027;
	}

	public String getKc27id() {
		return kc27id;
	}

	public void setKc27id(String kc27id) {
		this.kc27id = kc27id;
	}

	public String getKc21id() {
		return kc21id;
	}

	public void setKc21id(String kc21id) {
		this.kc21id = kc21id;
	}

	public String getKc22id() {
		return kc22id;
	}

	public void setKc22id(String kc22id) {
		this.kc22id = kc22id;
	}
}
