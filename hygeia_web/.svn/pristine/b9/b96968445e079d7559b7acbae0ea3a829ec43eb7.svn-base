package com.powersi.test.base;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.powersi.biz.medicare.inhospital.service.util.CommunalService;

/**
 * 基础测试的父类，所有测试从本类继承
 * 
 * @author 李志钢
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext*.xml", "classpath*:spring/apus-memorydb.xml",
		"classpath*:spring/apus-comm.xml", "classpath*:spring/apus-mq-redis.xml",
		"classpath*:spring/pcloud_log4jdbc.xml", "classpath*:spring/apus-moniter-redis.xml" })
public abstract class TestBase {

	private long ilbegindate = 0;
	private long ilenddate = 0;
	@Autowired
	private CommunalService communalService;

	@Before
	public void before() {
		System.out.println("---------开始" + this.getClass() + "测试---------");
		ilbegindate = new Date().getTime();
	}

	@After
	public void after() {
		ilenddate = new Date().getTime();
		long llusetime = ilenddate - ilbegindate;
		System.out.println("---------完成" + this.getClass() + "测试---------");
		System.out.println("用时：" + llusetime + "毫秒，折算成：" + llusetime / 1000 + "秒，又折算成："
				+ this.communalService.getRound(llusetime / (1000 * 60 * 1.00d), 2) + "分钟");
	}

}
