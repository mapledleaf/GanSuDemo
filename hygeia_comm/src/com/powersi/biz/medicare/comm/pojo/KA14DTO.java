package com.powersi.biz.medicare.comm.pojo;

public class KA14DTO extends RemotingDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8528584873076527082L;

	private String akb020;
	
	private String aaz127;

	private String aka127;

	private String aka128;

	private String bkm127;

	
	public String getAkb020() {
		return akb020;
	}

	public void setAkb020(String akb020) {
		this.akb020 = akb020;
	}

	/**
	 * 主键
	 * 
	 * @return
	 */
	public String getAaz127() {
		return aaz127;
	}

	public void setAaz127(String aaz127) {
		this.aaz127 = aaz127;
	}

	/**
	 * 手术治疗方式编码
	 * 
	 * @return
	 */
	public String getAka127() {
		return aka127;
	}

	public void setAka127(String aka127) {
		this.aka127 = aka127;
	}

	/**
	 * 手术治疗方式名称
	 * 
	 * @return
	 */
	public String getAka128() {
		return aka128;
	}

	public void setAka128(String aka128) {
		this.aka128 = aka128;
	}

	/**
	 * 操作属性
	 * 
	 * @return
	 */
	public String getBkm127() {
		return bkm127;
	}

	public void setBkm127(String bkm127) {
		this.bkm127 = bkm127;
	}

}
