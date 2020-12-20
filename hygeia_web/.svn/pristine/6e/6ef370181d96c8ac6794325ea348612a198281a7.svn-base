/*
 * Copyright 2016 Powersi. All rights reserved.
 */
package com.powersi.test;

import com.powersi.hygeia.framework.GlobeContext;
import com.powersi.hygeia.framework.SysMenu;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.SerializationHelper;

public class TestSerialization {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		try {
			GlobeContext.init();

			SysMenu sysMenu = DBHelper.executeBean(SysMenu.class,
					"select * from sys_menu where rownum < 2");

			//推荐使用kryo，速度最快，其次推荐java，兼容性最好，fst必须jdk1.7支持
			{
				String name = SerializationHelper.KRYO;
				System.out.println(SerializationHelper.use(name)
						.serializeToBase64String(sysMenu));

				System.out.println(SerializationHelper.use(name)
						.serializeToHexString(sysMenu));

				System.out.println("");
				System.out.println("");
				System.out.println("");
				
				byte[] bytes = SerializationHelper.use(name).serialize(sysMenu);
				SysMenu sm = (SysMenu) SerializationHelper.use(name)
						.deserialize(bytes);
				System.out.println(name + ":" + sm);
			}

			{
				String name = SerializationHelper.JDK;

				byte[] bytes = SerializationHelper.use(name).serialize(sysMenu);
				SysMenu sm = (SysMenu) SerializationHelper.use(name)
						.deserialize(bytes);
				System.out.println(name + ":" + sm);
			}

			{
				String name = SerializationHelper.HESSIAN;

				byte[] bytes = SerializationHelper.use(name).serialize(sysMenu);
				SysMenu sm = (SysMenu) SerializationHelper.use(name)
						.deserialize(bytes);
				System.out.println(name + ":" + sm);
			}

			{
				// 需要jdk1.7，低于1.7的框架自动改为kryo实现
				String name = SerializationHelper.FST;

				byte[] bytes = SerializationHelper.use(name).serialize(sysMenu);
				SysMenu sm = (SysMenu) SerializationHelper.use(name)
						.deserialize(bytes);
				System.out.println(name + ":" + sm);
			}

			{
				String name = SerializationHelper.FASTJSON;

				byte[] bytes = SerializationHelper.use(name).serialize(sysMenu);
				SysMenu sm = SerializationHelper.use(name).deserialize(bytes,
						SysMenu.class);
				System.out.println(name + ":" + sm);
			}
			
			{
				String name = SerializationHelper.JSON;

				byte[] bytes = SerializationHelper.use(name).serialize(sysMenu);
				SysMenu sm = SerializationHelper.use(name).deserialize(bytes,
						SysMenu.class);
				System.out.println(name + ":" + sm);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBHelper.close();
		}
	}

}
