package com.powersi.pcloud.checker.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用于云平台监测本工程是否启动成功，读取到ok即成功
 * @author 李志钢
 *
 */
@Controller
public class Mvc_checker {
	@ResponseBody
	@RequestMapping("/check.shtml")
	public String checker(){
		return "ok";
	}
}
