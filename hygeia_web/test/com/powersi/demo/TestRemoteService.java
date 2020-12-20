package com.powersi.demo;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.demo.pojo.DemoDTO;
import com.powersi.demo.service.DemoService;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.test.base.TestBase;

public class TestRemoteService extends TestBase {
	@Autowired
	HygeiaServiceManager hygeiaServiceManager;

	@Test
	public void testCallDemoService() {
		DemoService demoService = (DemoService) hygeiaServiceManager.getBean("demoService", "default");
		DemoService demoService2 = (DemoService) hygeiaServiceManager.getBean("demoService", "default");
		DemoService demoService3 = (DemoService) hygeiaServiceManager.getBean("demoService", "default");
		DemoDTO demoDto = new DemoDTO();
		demoDto.setName("测试人员");
		demoDto.setSalary((float) 1234.56);
		demoDto.setSex("1");
		demoDto.setSize(9901);

		Date dateBegin = new Date();
		for (int i = 0; i < 100; i++) {
			demoService.save(demoDto);
		}
		System.out.println("耗费时间：" + (new Date().getTime() - dateBegin.getTime()));
		demoService2.save(demoDto);
		demoService3.save(demoDto);
	}
}
