///*
// * Copyright 2016 Powersi. All rights reserved.
// */
//package com.powersi.test;
//
//import java.util.Set;
//
//import org.apache.commons.lang.math.RandomUtils;
//
//import redis.clients.jedis.Pipeline;
//import redis.clients.jedis.Transaction;
//
//import com.powersi.hygeia.framework.GlobeContext;
//import com.powersi.hygeia.framework.redis.Redis;
//import com.powersi.hygeia.framework.util.DBHelper;
//import com.powersi.hygeia.framework.util.RedisHelper;
//
///**
// * The Class TestRedis.
// */
//public class TestRedis {
//	private static final int KEY_COUNT = 10000;
//	private static final int FIELD_COUNT = 10;
//
//	/**
//	 * The main method.
//	 * 
//	 * @param args
//	 *            the arguments
//	 */
//	public static void main(String[] args) {
//		try {
//			GlobeContext.init();
//
//			//String redisName = "sessionRedis";
//			String redisName = "cacheRedis";
//			
//			// use使用短连接，方法执行完成即自动关闭
//			{
//				Set<String> keys = RedisHelper.use(redisName).keys("*");
//				System.out.println("keys:" + keys.size());
//			}
//
//			// use使用短连接，方法执行完成即自动关闭
//			{
//				Redis redis = RedisHelper.use(redisName);
//				System.out.println("redis:" + redis.getName());
//				System.out.println("batch:"
//						+ redis.keys("powersi:test:batch:*"));
//				System.out.println("trans:"
//						+ redis.keys("powersi:test:trans:*"));
//			}
//
//			// open使用长连接，必须手动close
//			try {
//				// 组合处理(保持长连接，进行多次操作）
//				Redis redis = RedisHelper.open(redisName);
//				for (String key : redis.keys("spring:session:*")) {
//					String type = redis.type(key);
//					if ("hash".equals(type)) {
//						System.out.println(redis.hgetAll(key));
//					}
//				}
//			} finally {
//				// 关闭连接
//				RedisHelper.close(redisName);
//			}
//
//			// open使用长连接，必须手动close
//			try {
//				// 批量处理(必须使用长连接）
//				long start = System.currentTimeMillis();
//				Redis redis = RedisHelper.open(redisName);
//				// 管道对象没有做序列化封装，请自行调用keyToBytes,hashKeyToBytes,valueToBytes
//				Pipeline p = redis.pipelined();
//				int index = 0;
//				for (int i = 0; i < KEY_COUNT; i++) {
//					byte[] key = redis.keyToBytes("powersi:test:batch:"
//							+ String.valueOf(RandomUtils.nextInt(5)));
//					for (int j = 0; j < FIELD_COUNT; j++) {
//						p.hset(key, redis.hashKeyToBytes(String.valueOf(j)),
//								redis.valueToBytes(String.valueOf(i + j)));
//						p.expire(key, 3600);
//					}
//					// 每隔1000行提交一次
//					if (++index % 1000 == 0) {
//						// p.sync();
//					}
//				}
//				p.sync();
//				System.out.printf("batch use %d millis \n",
//						System.currentTimeMillis() - start);
//			} finally {
//				// 关闭连接
//				RedisHelper.close(redisName);
//			}
//
//			// open使用长连接，必须手动close
//			try {
//				// 事务处理(必须使用长连接）
//				long start = System.currentTimeMillis();
//				Redis redis = RedisHelper.open(redisName);
//				// 事务对象没有做序列化封装，请自行调用keyToBytes,hashKeyToBytes,valueToBytes
//				Transaction p = redis.multi();
//				for (int i = 0; i < KEY_COUNT; i++) {
//					byte[] key = redis.keyToBytes("powersi:test:trans:"
//							+ String.valueOf(RandomUtils.nextInt(5)));
//					for (int j = 0; j < FIELD_COUNT; j++) {
//						p.hset(key, redis.hashKeyToBytes(String.valueOf(j)),
//								redis.valueToBytes(String.valueOf(i + j)));
//						p.expire(key, 3600);
//					}
//				}
//				// 提交事务
//				p.exec();
//				System.out.printf("trans use %d millis \n",
//						System.currentTimeMillis() - start);
//			} finally {
//				// 关闭连接
//				RedisHelper.close(redisName);
//			}
//
//			// open使用长连接，必须手动close
//			try {
//				// 批量事务处理(必须使用长连接）
//				long start = System.currentTimeMillis();
//				Redis redis = RedisHelper.open(redisName);
//				// 管道对象没有做序列化封装，请自行调用keyToBytes,hashKeyToBytes,valueToBytes
//				Pipeline p = redis.pipelined();
//				// 启动事务
//				p.multi();
//				for (int i = 0; i < KEY_COUNT; i++) {
//					byte[] key = redis.keyToBytes("powersi:test:batch-trans:"
//							+ String.valueOf(RandomUtils.nextInt(5)));
//					for (int j = 0; j < FIELD_COUNT; j++) {
//						p.hset(key, redis.hashKeyToBytes(String.valueOf(j)),
//								redis.valueToBytes(String.valueOf(i + j)));
//						p.expire(key, 3600);
//					}
//				}
//				// 提交事务
//				p.exec();
//
//				System.out.printf("batch use %d millis \n",
//						System.currentTimeMillis() - start);
//			} finally {
//				// 关闭连接
//				RedisHelper.close(redisName);
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			RedisHelper.close();
//
//			DBHelper.close();
//		}
//	}
//}
