/*
 * Copyright 2015 Powersi. All rights reserved.
 */
package com.powersi.sys.util;

import javax.servlet.http.HttpServletResponse;

/**
 * The Class DataGridHelper.
 */
public abstract class DataGridHelper extends com.powersi.hygeia.web.util.DataGridHelper{
	
	/**
	 * 输出datagrid.
	 *
	 * @param response 响应对象
	 * @param data 数据集
	 */
	@Deprecated
	public static void render(HttpServletResponse response, Object data) {
		render(null, response, data);
	}
}
