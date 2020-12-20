package com.powersi.biz.medicare.pay.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 汤亮
 *	支付信息
 */
public class PayInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
	private String yybm; //医院编码
	private String orderno; //外部订单号（及就医登记号）
	private String aaz217;
	private int dealamount; //消费金额
	private String pay_mode; //支付方式* 10 医保统筹+医保个账 * 11 医保统筹+现金 * 12 医保统筹+医保个账+现金 * 13 非医保类
	private String pay_state; //支付状态 1 支付结果通知 2支付撤销通知
	private String pay_result; //支付结果 0 成功 其他值 失败
	private String pay_result_message; //结果提示信息，失败时就是错误信息
	private Date paytime; //支付交易处理时间 及撤销时间与交易时间yyyy-MM-dd HH:mm:ss
	private String dealingjournalno; //交易订单号
	private String paychanneldeal; //银联交易流水号
	private Date create_date; //创建时间及支付通知时间  当前时间yyyy-MM-dd HH:mm:ss
	private String return_code; //支付结果通知返回情况 1 医院返回成功 -1医院返回失败
	private String return_code_msg; //支付结果通知返回情况，失败记录错误信息
	
	private int coordination_pay;//交易总金额
	private int personal_pay;//医保个账金额
	private int cash_pay;//现金金额
	private String channel;//1041 ：银联 、1042 ：支付宝 、1043 ：财付通(微信)、paymentmethod不为10时传入
	
	public String getAaz217() {
		return aaz217;
	}

	public void setAaz217(String aaz217) {
		this.aaz217 = aaz217;
	}

	public int getCoordination_pay() {
		return coordination_pay;
	}

	public void setCoordination_pay(int coordination_pay) {
		this.coordination_pay = coordination_pay;
	}

	public int getPersonal_pay() {
		return personal_pay;
	}

	public void setPersonal_pay(int personal_pay) {
		this.personal_pay = personal_pay;
	}

	public int getCash_pay() {
		return cash_pay;
	}

	public void setCash_pay(int cash_pay) {
		this.cash_pay = cash_pay;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getReturn_code_msg() {
		return return_code_msg;
	}

	public void setReturn_code_msg(String return_code_msg) {
		this.return_code_msg = return_code_msg;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getYybm() {
		return yybm;
	}

	public void setYybm(String yybm) {
		this.yybm = yybm;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public int getDealamount() {
		return dealamount;
	}

	public void setDealamount(int dealamount) {
		this.dealamount = dealamount;
	}

	public String getPay_mode() {
		return pay_mode;
	}

	public void setPay_mode(String pay_mode) {
		this.pay_mode = pay_mode;
	}

	public String getPay_state() {
		return pay_state;
	}

	public void setPay_state(String pay_state) {
		this.pay_state = pay_state;
	}

	public String getPay_result() {
		return pay_result;
	}

	public void setPay_result(String pay_result) {
		this.pay_result = pay_result;
	}

	public String getPay_result_message() {
		return pay_result_message;
	}

	public void setPay_result_message(String pay_result_message) {
		this.pay_result_message = pay_result_message;
	}

	public Date getPaytime() {
		return paytime;
	}

	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}

	public String getDealingjournalno() {
		return dealingjournalno;
	}

	public void setDealingjournalno(String dealingjournalno) {
		this.dealingjournalno = dealingjournalno;
	}

	public String getPaychanneldeal() {
		return paychanneldeal;
	}

	public void setPaychanneldeal(String paychanneldeal) {
		this.paychanneldeal = paychanneldeal;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	@Override
	public String toString() {
		return "PayInfo [id=" + id + ", yybm=" + yybm + ", orderno=" + orderno + ", dealamount=" + dealamount
				+ ", pay_mode=" + pay_mode + ", pay_state=" + pay_state + ", pay_result=" + pay_result
				+ ", pay_result_message=" + pay_result_message + ", paytime=" + paytime + ", dealingjournalno="
				+ dealingjournalno + ", paychanneldeal=" + paychanneldeal + ", channel=" + channel + ",create_date=" + create_date
				+ ", return_code=" + return_code + ", return_code_msg=" + return_code_msg + "]";
	}
	
	
}
