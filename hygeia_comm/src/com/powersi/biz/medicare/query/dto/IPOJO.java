package com.powersi.biz.medicare.query.dto;

import java.io.Serializable;

/**
 * @author 刘勇
 */
public interface IPOJO extends Serializable {

	public String toJson();

	public boolean compareTo(Object obj);

}
