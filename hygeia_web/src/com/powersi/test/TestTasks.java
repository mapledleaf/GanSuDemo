package com.powersi.test;

import com.powersi.hygeia.framework.GlobeContext;
import com.powersi.hygeia.framework.TaskController;
import com.powersi.hygeia.framework.TaskScheduler;

public class TestTasks {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GlobeContext.init();
			System.out.println(GlobeContext.printString());
			
			//设置shutdown策略（实际运行环境下直接shutdown不等待，kill-9无敌）
			TaskScheduler.setAwaitTerminationSeconds(30);
			TaskScheduler.setWaitForTasksToCompleteOnShutdown(true);
						
			Thread.sleep(90*1000);
			
			//任务控制器(短时间异步任务）
			System.out.println("controler:" + TaskController.getStatus());
			
			//任务调度器(长时间或者周期性异步任务）
			System.out.println("secheduler:" + TaskScheduler.getStatus());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			GlobeContext.shutdown();
		}
	}

}
