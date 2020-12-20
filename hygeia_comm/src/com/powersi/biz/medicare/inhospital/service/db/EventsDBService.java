package com.powersi.biz.medicare.inhospital.service.db;

import java.io.Serializable;
import java.util.List;

import com.powersi.events.KE67;
import com.powersi.events.KE68;

/**
 * 事件
 * 
 * @author 刘勇
 *
 */
public interface EventsDBService extends Serializable {

	/**
	 * 保存事件或事件明细
	 * 
	 * @param ke67
	 * @param ke68Rows
	 */
	public void addEvents(KE67 ke67, List<KE68> ke68Rows);

}
