package com.powersi.biz.medicare.recallcenter.pojo;

import java.util.Date;
import java.util.List;

import com.powersi.biz.medicare.inhospital.pojo.PayFinDTO;
import com.powersi.biz.medicare.inhospital.pojo.BizFinDTO;
import com.powersi.comm.bean.BaseBean;

public class NeedRecallCenterDataDTO extends BaseBean {

	private static final long serialVersionUID = 1L;

	private String strFunc_id = ""; // 调用中心的服务名
	private int iCount = 0; // 尝试次数
	private BizFinDTO bizFinDTO; // 业务实体
	private List<PayFinDTO> payFinDTO; // 支付实体
	private Date dtPassTime = new Date(); // 间隔时间
	private String recall_status = "2"; // 是否冲正成功，1成功，2失败

	public int getiCount() {
		return iCount;
	}

	public void setiCount(int iCount) {
		this.iCount = iCount;
	}

	public Date getDtPassTime() {
		return dtPassTime;
	}

	public void setDtPassTime(Date dtPassTime) {
		this.dtPassTime = dtPassTime;
	}

	public BizFinDTO getBizFinDTO() {
		return bizFinDTO;
	}

	public void setBizFinDTO(BizFinDTO bizFinDTO) {
		this.bizFinDTO = bizFinDTO;
	}

	public List<PayFinDTO> getPayFinDTO() {
		return payFinDTO;
	}

	public void setPayFinDTO(List<PayFinDTO> payFinDTO) {
		this.payFinDTO = payFinDTO;
	}

	public String getRecall_status() {
		return recall_status;
	}

	public void setRecall_status(String recall_status) {
		this.recall_status = recall_status;
	}

	public String getStrFunc_id() {
		return strFunc_id;
	}

	public void setStrFunc_id(String strFunc_id) {
		this.strFunc_id = strFunc_id;
	}

}
