/*
 * Copyright 2014 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;

import com.powersi.hygeia.framework.GlobalContext;
import com.powersi.hygeia.framework.util.CollectionHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.action.UploadAction;
import com.powersi.hygeia.web.util.WebHelper;
import com.powersi.sys.manager.util.MenuFlowUtil;

/**
 * The Class MenuFlowManagerAction.
 */
@Action("MenuFlowManager")
public class MenuFlowManagerAction extends UploadAction {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Save.
	 * 
	 * @return the string
	 */
	public String save() {
		try {
			Map params = getAllParameters();
			String[] keys = new String[] { "flow_key", "flow_name", "flow_data" };
			String[] names = new String[] { "流程标识", "流程名称", "流程数据" };
			for (int i = 0; i < keys.length; i++) {
				if (UtilFunc.isEmptyString(params.get(keys[i]))) {
					saveJSONError(names[i] + "不能为空");
					return NONE;
				}
			}

			String flowKey = UtilFunc.getString(params, "flow_key");
			String flowId = UtilFunc.getString(params, "flow_id");
			if (UtilFunc.isEmpty(flowId)) {
				if (MenuFlowUtil.checkRepeat(flowKey)) {
					saveJSONError("流程标识" + flowKey + "已经存在，不允许重复");
					return NONE;
				}

				flowId = UtilFunc.getUUID();
				params.put("flow_id", flowId);
				MenuFlowUtil.insert(params);
			} else {
				if (MenuFlowUtil.checkRepeat(flowKey, flowId)) {
					saveJSONError("流程标识" + flowKey + "已经存在，不允许重复");
					return NONE;
				}

				MenuFlowUtil.update(params);
			}

			params.remove("flow_data");
			setJSONReturn("保存流程成功", params);
		} catch (Exception ex) {
			saveJSONError("保存流程出错", ex);
		}

		return NONE;
	}

	/**
	 * Delete.
	 * 
	 * @return the string
	 */
	public String delete() {
		try {
			String flowId = getParameter("flow_id");
			if (UtilFunc.isEmpty(flowId)) {
				saveJSONError("流程编号不能为空");
				return NONE;
			}

			MenuFlowUtil.delete(flowId);
			saveJSONMessage("删除流程成功");
		} catch (Exception ex) {
			saveJSONError("删除流程出错", ex);
		}

		return NONE;
	}

	/**
	 * Download.
	 * 
	 * @return the string
	 */
	public String download() {
		try {
			Map params = getAllParameters();
			String[] keys = new String[] { "flow_key", "flow_name", "flow_data" };
			String[] names = new String[] { "流程标识", "流程名称", "流程数据" };
			for (int i = 0; i < keys.length; i++) {
				if (UtilFunc.isEmptyString(params.get(keys[i]))) {
					saveJSONError(names[i] + "不能为空");
					return NONE;
				}
			}

			String filename = String.format("%1$s(%2$s).json",
					params.get("flow_name"), params.get("flow_key"));
			getResponse().setContentType("application/text");
			getResponse().setHeader("Content-disposition", String.format(
					"attachment; filename=\"%1$s\"",
					new String(filename.getBytes(), "ISO-8859-1")));
			getResponse().getOutputStream().write(
					UtilFunc.getString(params, "flow_data").getBytes(
							GlobalContext.getCharset()));
		} catch (Exception ex) {
			// 忽略客户端取消异常
			if (!WebHelper.isClientAbortException(ex)) {
				saveJSONError("导出流程文件出错", ex);
			}
		}
		return NONE;
	}

	/**
	 * Upload.
	 * 
	 * @return the string
	 */
	public String upload() {
		try {
			if (CollectionHelper.isEmpty(uploads)) {
				saveJSONError("导入文件不能为空");
				return NONE;
			}

			if (!UtilFunc.endsWithIgnoreCase(uploadsFileName.get(0),
					".json")) {
				saveJSONError("导入文件只支持json格式的文件");
				return NONE;
			}

			Map map = new HashMap();
			map.put("flow_data", new String(MenuFlowUtil.readFile(uploads.get(0)),
					GlobalContext.getCharset()));

			setJSONReturn(map);
		} catch (Exception ex) {
			saveJSONError("导入流程文件出错", ex);
		}

		return NONE;
	}
}
