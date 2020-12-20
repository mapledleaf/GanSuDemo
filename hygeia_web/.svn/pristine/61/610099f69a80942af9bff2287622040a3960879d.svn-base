/*
 * Copyright 2015 Powersi. All rights reserved.
 */
package com.powersi.test;

import com.powersi.hygeia.framework.BusiContext;
import com.powersi.hygeia.framework.BusiService;
import com.powersi.hygeia.framework.GlobeContext;
import com.powersi.hygeia.framework.IParameterObj;
import com.powersi.hygeia.framework.IResultObj;
import com.powersi.hygeia.framework.TaskContext;
import com.powersi.hygeia.framework.util.UtilFunc;

/**
 * The Class TestTaskCtrl.
 */
public class TestTaskCtrl extends BusiService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.hygeia.framework.IBusiService#execute(com.powersi.hygeia.
	 * framework.IParameterObj, com.powersi.hygeia.framework.IResultObj)
	 */
	public int execute(IParameterObj param, IResultObj result) throws Exception {
		// 获取任务上下文
		TaskContext context = BusiContext.getTaskContext();

		String taskName = "";
		String taskDesc = "";
		if(context != null) {
			taskName = context.getTaskName();
			taskDesc = context.getTaskDesc();
		}

		GlobeContext.printConsole(taskName + " begin");
		int sleep = UtilFunc.stringToInt(
				(String) param.getParameter("sleep_seconds"), 0);
		if (sleep > 0) {
			try {
				Thread.sleep(sleep * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		GlobeContext.printConsole(taskName + " end");

		// 设置返回结果
		result.setResult(param.getAllParameters());
		result.setRetMsg(taskDesc + "延迟 "
				+ String.valueOf(sleep) + " 秒");

		return SUCCESS;
	}
}
