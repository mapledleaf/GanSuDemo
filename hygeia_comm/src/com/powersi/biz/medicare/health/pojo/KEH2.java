package com.powersi.biz.medicare.health.pojo;

import com.powersi.comm.bean.BaseBean;
/**
 * 体检结论项目表
 * @author hasee
 *
 */
public class KEH2 extends BaseBean{
	private static final long serialVersionUID = 1L;
	/**
	 * 指标编码
	 */
	private String  bkh049;//指标编码
	/**
	 * 指标名称
	 */
	private String  bkh050;
	/**
	 * 统筹区编码
	 */
	private String  aaa027;
	/**
	 * 五笔码
	 */
	private String  aka021;
	/**
	 * 首拼码
	 */
	private String  aka020;
	/**
	 * 数值类型 ：00，数值；11，码表
	 */
	private String  bkh037;
	/**
	 * 叶子标识 ：0，叶子；1，目录
	 */
	private String  bkh053;
	/**
	 * 统计类别
	 */
	private String  bkh046;
	/**
	 * 序号
	 */
	private int  bkh068;
	/**
	 * 所属指标
	 */
	private int  bkh054;
	/**
	 * 人员类型
	 */
	private String  bka035;
	/**
	 * 参考范围上限
	 */
	private String  bkh051;

	/**
	 * 参考范围下限
	 */
	private String  bkh052;
	/**
	 * 参考范围指标
	 */
	private String  bkh056;
	/**
	 * 单位控制指标
	 */
	private String  bkh057;
	/**
	 * 疾病控制指标
	 */
	private String  bkh058;
	/**
	 * 生效时间
	 */
	private int  bkh103;
	/**
	 * 失效时间
	 */
	private int  bkh104;
	/**
	 * 排序
	 */
	private float  bkh055;
	/**
	 * 版本号
	 */
	private int  bkh047;
	/**
	 * 备注
	 */
	private String  aae013;
	public String getBkh049() {
		return bkh049;
	}
	public void setBkh049(String bkh049) {
		this.bkh049 = bkh049;
	}
	public String getBkh050() {
		return bkh050;
	}
	public void setBkh050(String bkh050) {
		this.bkh050 = bkh050;
	}
	public String getAaa027() {
		return aaa027;
	}
	public void setAaa027(String aaa027) {
		this.aaa027 = aaa027;
	}
	public String getAka021() {
		return aka021;
	}
	public void setAka021(String aka021) {
		this.aka021 = aka021;
	}
	public String getAka020() {
		return aka020;
	}
	public void setAka020(String aka020) {
		this.aka020 = aka020;
	}
	public String getBkh037() {
		return bkh037;
	}
	public void setBkh037(String bkh037) {
		this.bkh037 = bkh037;
	}
	public String getBkh053() {
		return bkh053;
	}
	public void setBkh053(String bkh053) {
		this.bkh053 = bkh053;
	}
	public String getBkh046() {
		return bkh046;
	}
	public void setBkh046(String bkh046) {
		this.bkh046 = bkh046;
	}
	public int getBkh068() {
		return bkh068;
	}
	public void setBkh068(int bkh068) {
		this.bkh068 = bkh068;
	}
	public int getBkh054() {
		return bkh054;
	}
	public void setBkh054(int bkh054) {
		this.bkh054 = bkh054;
	}
	public String getBka035() {
		return bka035;
	}
	public void setBka035(String bka035) {
		this.bka035 = bka035;
	}
	public String getBkh051() {
		return bkh051;
	}
	public void setBkh051(String bkh051) {
		this.bkh051 = bkh051;
	}
	public String getBkh052() {
		return bkh052;
	}
	public void setBkh052(String bkh052) {
		this.bkh052 = bkh052;
	}
	public String getBkh056() {
		return bkh056;
	}
	public void setBkh056(String bkh056) {
		this.bkh056 = bkh056;
	}
	public String getBkh057() {
		return bkh057;
	}
	public void setBkh057(String bkh057) {
		this.bkh057 = bkh057;
	}
	public String getBkh058() {
		return bkh058;
	}
	public void setBkh058(String bkh058) {
		this.bkh058 = bkh058;
	}
	public int getBkh103() {
		return bkh103;
	}
	public void setBkh103(int bkh103) {
		this.bkh103 = bkh103;
	}
	public int getBkh104() {
		return bkh104;
	}
	public void setBkh104(int bkh104) {
		this.bkh104 = bkh104;
	}
	public float getBkh055() {
		return bkh055;
	}
	public void setBkh055(float bkh055) {
		this.bkh055 = bkh055;
	}
	public int getBkh047() {
		return bkh047;
	}
	public void setBkh047(int bkh047) {
		this.bkh047 = bkh047;
	}
	public String getAae013() {
		return aae013;
	}
	public void setAae013(String aae013) {
		this.aae013 = aae013;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
