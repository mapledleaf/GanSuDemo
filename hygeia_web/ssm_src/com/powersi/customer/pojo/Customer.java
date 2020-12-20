package com.powersi.customer.pojo;

import java.util.Date;

import com.powersi.comm.bean.BaseBean;
import com.powersi.pcloud.dict.service.DictService;

/**
 * 云平台访问账户 pojo实体类
 * @author "lingang"
 * @time 2017年2月15日上午10:50:08
 *
 */
public class Customer extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private String acc_code;
	private String acc_name;
	private String acc_type;
	private String acc_type_name;
	private String acc_subtype;
	private String acc_subtype_name;
	private String passwd;
	private String acc_state;
	private String acc_state_name;
	private String acc_yljgbm;
	private Date create_date = new Date();//default
	private String acc_subcode;
	private String license_info;
	private int concurrency_number = 0;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAcc_code() {
		return acc_code;
	}
	public void setAcc_code(String acc_code) {
		this.acc_code = acc_code;
	}
	public String getAcc_name() {
		return acc_name;
	}
	public void setAcc_name(String acc_name) {
		this.acc_name = acc_name;
	}
	public String getAcc_type() {
		return acc_type;
	}
	public void setAcc_type(String acc_type) {
		this.acc_type = acc_type;
	}
	public String getAcc_subtype() {
		return acc_subtype;
	}
	public void setAcc_subtype(String acc_subtype) {
		this.acc_subtype = acc_subtype;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getAcc_state() {
		return acc_state;
	}
	public void setAcc_state(String acc_state) {
		this.acc_state = acc_state;
	}
	public String getAcc_yljgbm() {
		return acc_yljgbm;
	}
	public void setAcc_yljgbm(String acc_yljgbm) {
		this.acc_yljgbm = acc_yljgbm;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getAcc_subcode() {
		return acc_subcode;
	}
	public void setAcc_subcode(String acc_subcode) {
		this.acc_subcode = acc_subcode;
	}
	public String getAcc_type_name() {
		return acc_type_name;
	}
	public void setAcc_type_name(String acc_type_name) {
		this.acc_type_name = acc_type_name;
	}
	public String getAcc_state_name() {
		return acc_state_name;
	}
	public void setAcc_state_name(String acc_state_name) {
		this.acc_state_name = acc_state_name;
	}
	public String getAcc_subtype_name() {
		return acc_subtype_name;
	}
	public void setAcc_subtype_name(String acc_subtype_name) {
		this.acc_subtype_name = acc_subtype_name;
	}
	public String getLicense_info() {
		return license_info;
	}
	public void setLicense_info(String license_info) {
		this.license_info = license_info;
	}
	public int getConcurrency_number() {
		return concurrency_number;
	}
	public void setConcurrency_number(int concurrency_number) {
		this.concurrency_number = concurrency_number;
	}
	/**
	 * 从dict服务翻译本对象内的代码
	 * @param dictService
	 */
	public void getValueFromDict( DictService dictService ){
		this.setAcc_type_name( dictService.getValue("ACC_TYPE", this.getAcc_type(),"") );
		this.setAcc_state_name( dictService.getValue("ACC_STATE", this.getAcc_state(),"") );
		this.setAcc_subtype_name( dictService.getValue("ACC_SUBTYPE", this.getAcc_subtype() == null ?"":this.getAcc_subtype(),"") );
	}
}
