package com.powersi.biz.medicare.cumulative.pojo;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.powersi.biz.medicare.comm.pojo.IPOJO;

/**
 * 个人医疗累计要素类别配置集合
 * 
 * @author zhos
 *
 */
public class PersonalCumulativeConfigDTO implements IPOJO {

	private static final long serialVersionUID = 1L;

	private String aaa027;
	private String aka130;
	private String bka006;
	private String aae140;
	private String aka037;//累计类别代码
	private String aka050;//累计类别名称
	private String aae100;//有效标志
	
	public String getAaa027() {
		return aaa027;
	}

	public void setAaa027(String aaa027) {
		this.aaa027 = aaa027;
	}

	public String getAka130() {
		return aka130;
	}

	public void setAka130(String aka130) {
		this.aka130 = aka130;
	}

	public String getBka006() {
		return bka006;
	}

	public void setBka006(String bka006) {
		this.bka006 = bka006;
	}

	public String getAae140() {
		return aae140;
	}

	public void setAae140(String aae140) {
		this.aae140 = aae140;
	}

	public String getAka037() {
		return aka037;
	}

	public void setAka037(String aka037) {
		this.aka037 = aka037;
	}

	public String getAka050() {
		return aka050;
	}

	public void setAka050(String aka050) {
		this.aka050 = aka050;
	}

	public String getAae100() {
		return aae100;
	}

	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}

	@Override
	public String toString() {
		return this.toJson();
	}

	@Override
	public String toJson() {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(this);
	}

	@Override
	public boolean compareTo(Object obj) {
		return this.toString().equals(obj.toString());
	}

}
