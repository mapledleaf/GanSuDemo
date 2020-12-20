package com.powersi.test;

import com.powersi.hygeia.framework.GlobeContext;
import com.powersi.hygeia.framework.TaskScheduler;

public class TestTaskScheduler {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GlobeContext.init();
			
			System.out.println(GlobeContext.getContext("taskscheduler", ""));
			
			TaskScheduler.setAwaitTerminationSeconds(30);
			
//			TaskScheduler.setCorePoolSize(3);
			
//			TaskScheduler.scheduleAtFixedRate(new Runnable() {
//				public void run() {
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						//e.printStackTrace();
//						return;
//					}
//					System.out.println(DateFunc.datetimeToString(new java.util.Date()) + " scheduleAtFixedRate " + TaskScheduler.getPoolSize());
//				}
//			}, 10*1000);
//			
//			TaskScheduler.scheduleWithFixedDelay(new Runnable() {
//				public void run() {
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						//e.printStackTrace();
//						return;
//					}
//					System.out.println(DateFunc.datetimeToString(new java.util.Date()) + " scheduleWithFixedDelay " + TaskScheduler.getPoolSize());
//				}
//			}, 10*1000);
//			
//			TaskScheduler.schedule(new Runnable() {
//				public void run() {
//					try {
//						Thread.sleep(5000);
//					} catch (InterruptedException e) {
//						//e.printStackTrace();
//						return;
//					}
//					System.out.println(DateFunc.datetimeToString(new java.util.Date()) + " scheduleCron " + TaskScheduler.getPoolSize());
//				}
//			}, "*/3 * * * * ?");
//			
//			TaskScheduler.schedule(new Runnable() {
//				public void run() {
//					try {
//						Thread.sleep(5000);
//					} catch (InterruptedException e) {
//						//e.printStackTrace();
//						return;
//					}
//					System.out.println(DateFunc.datetimeToString(new java.util.Date()) + " scheduleCron " + TaskScheduler.getPoolSize());
//				}
//			}, 3000L);
//			
//			Queue<Runnable> tasks = new LinkedBlockingQueue<Runnable>();
//			tasks.add(new Runnable() {
//				public void run() {
//					DBHelper.getConnection();
//					System.out.println(DateFunc.datetimeToString(new java.util.Date()) + " executeor1 " + TaskController.getPoolSize());
//				}
//			});
//			tasks.add(new Runnable() {
//				public void run() {
//					DBHelper.getConnection();
//					System.out.println(DateFunc.datetimeToString(new java.util.Date()) + " executeor2 " + TaskController.getPoolSize());
//				}
//			});
//			tasks.add(new Runnable() {
//				public void run() {
//					DBHelper.getConnection();
//					System.out.println(DateFunc.datetimeToString(new java.util.Date()) + " executeor3 " + TaskController.getPoolSize());
//				}
//			});
//			TaskController.execute(tasks);
			
			Thread.sleep(90*1000);
			
			System.out.println(TaskScheduler.getStatus());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			GlobeContext.shutdown();
		}
	}

}
