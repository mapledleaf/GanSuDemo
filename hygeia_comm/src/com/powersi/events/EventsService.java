package com.powersi.events;

import java.io.Serializable;
import java.util.List;

/**
 * 事件
 * 
 * @author 刘勇
 *
 */
public interface EventsService extends Serializable {

	/**
	 * 事件明细集合
	 * 
	 * @param ke67
	 * @param oldObject
	 * @param newObject
	 * @return
	 */
	public List<KE68> eventsDetails(KE67 ke67, Object oldObject, Object newObject);

}
