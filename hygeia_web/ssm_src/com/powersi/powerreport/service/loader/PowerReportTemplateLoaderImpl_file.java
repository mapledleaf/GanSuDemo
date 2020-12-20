package com.powersi.powerreport.service.loader;

import java.io.InputStream;

import com.powersi.hygeia.framework.exception.HygeiaException;

/**
 * 基于源代码目录的报表模板加载
 * 
 * @author 李志钢
 *
 */
public class PowerReportTemplateLoaderImpl_file implements PowerReportTemplateLoader {

	@Override
	public InputStream load(String templateID) {
		// templateID: demo/demo.xls
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(templateID);
		if (inputStream == null) {
			throw new HygeiaException("获取报表模版信息失败!,报表模版为：" + templateID);
		}
		return inputStream;
	}

}
