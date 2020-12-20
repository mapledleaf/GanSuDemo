package com.powersi.biz.medicare.comm.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 校验重复提交收费DTO
 * 
 * @author bruce
 *
 */
public class CheckBizDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String BKA006; // 待遇类型
	private String BKA026; // 诊断
	private Double AAE019; // 总费用
	private int BKA057; // 明细总条数
	private Date BKA013; // 就诊时间
	private String AAZ217; // 就医登记号
	
	// 【TS19030700278】门诊、购药限制10分钟重复购药优化
	private List<String> ake005List; // 收费药品/项目编码与费用链表

	/**
	 * @return ake005List 收费药品/项目编码链表
	 */
	public List<String> getAke005List() {
		return ake005List;
	}

	/**
	 * @param 收费药品/项目编码链表
	 */
	public void setAke005List(List<String> ake005List) {
		this.ake005List = ake005List;
	}

	public String getBKA006() {
		return BKA006;
	}

	public void setBKA006(String bKA006) {
		BKA006 = bKA006;
	}

	public String getBKA026() {
		return BKA026;
	}

	public void setBKA026(String bKA026) {
		BKA026 = bKA026;
	}

	public Double getAAE019() {
		return AAE019;
	}

	public void setAAE019(Double aAE019) {
		AAE019 = aAE019;
	}

	public int getBKA057() {
		return BKA057;
	}

	public void setBKA057(int bKA057) {
		BKA057 = bKA057;
	}

	public Date getBKA013() {
		return BKA013;
	}

	public void setBKA013(Date bKA013) {
		BKA013 = bKA013;
	}

	public String getAaz217() {
		return AAZ217;
	}

	public void setAaz217(String aaz217) {
		AAZ217 = aaz217;
	}

}
