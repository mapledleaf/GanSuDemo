package com.powersi.ssm.biz.inhospital;

import com.powersi.biz.medicare.comm.service.HttpPostService;
import com.powersi.ssm.biz.medicare.common.service.HttpPostServiceImpl;

/**
 * 
 * @author 刘勇
 *
 */
public class TestHttpPost {

	public static void httpPost() {
		java.util.Date httpPostBeginDate = new java.util.Date();
		HttpPostService httpPostService = new HttpPostServiceImpl();
		String url = "http://172.18.100.39:8080/hygeia_esb/api/call.action";
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><program><session_id>0a9c782c-d375-4780-a377-ed506ec0cfce</session_id><function_id>bizh120001</function_id><bka895>aac002</bka895><bka896>513901198311295323</bka896><akb020>002002</akb020><aka130>12</aka130></program>";
		String retStr = httpPostService.httpPost(url, xml);
		System.out.println(retStr);
		java.util.Date httpPostEndDate = new java.util.Date();
		System.out.println((httpPostEndDate.getTime() - httpPostBeginDate.getTime()) + "毫秒!");
	}

	public static void httpPostDemo() {
		java.util.Date httpPostBeginDate = new java.util.Date();
		HttpPostService httpPostService = new HttpPostServiceImpl();
		httpPostService.httpPost();
		java.util.Date httpPostEndDate = new java.util.Date();
		System.out.println((httpPostEndDate.getTime() - httpPostBeginDate.getTime()) + "毫秒!");
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			httpPost();
			httpPostDemo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
