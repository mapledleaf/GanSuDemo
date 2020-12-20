package com.powersi.test;

import com.powersi.hygeia.framework.BusiContext;
import com.powersi.hygeia.framework.GlobeContext;
import com.powersi.hygeia.framework.TaskContext;
import com.powersi.hygeia.framework.util.UtilFunc;

public class TestTaskRunnable implements Runnable {

	public void run() {
		// 获取任务上下文
		TaskContext context = BusiContext.getTaskContext();

		String taskName = "";
		String taskDesc = "";
		if (context != null) {
			taskName = context.getTaskName();
			taskDesc = context.getTaskDesc();
		}

		GlobeContext.printConsole(taskName + " begin");
		int sleep = UtilFunc.stringToInt(context.getTaskParam(), 0);
		if (sleep > 0) {
			try {
				Thread.sleep(sleep * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		GlobeContext.printConsole(taskName + " end");

		// 设置任务结果
		if (context != null) {
			context.setTaskState(1);
			context.setTaskResult(taskDesc + "延迟 "
					+ String.valueOf(sleep) + " 秒");
		}
	}
}
