/*
 * Copyright 2015 Powersi. All rights reserved.
 */
package com.powersi.sys.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.comm.mybatis.Page;
import com.powersi.hygeia.framework.PaginationObj;
import com.powersi.hygeia.framework.util.CollectionHelper;
import com.powersi.hygeia.framework.util.PaginationHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.util.DataGridHelper;

/**
 * 分页辅助类(请改用PageRequestHelper).
 */
public abstract class PagerHelper {

	/** The table export. */
	private static String TABLE_EXPORT = "exporting";

	/** The grid export. */
	private static String GRID_EXPORT = "gridexport";

	/**
	 * Page helper.
	 */
	private PagerHelper() {

	}

	/**
	 * 是否导出请求.
	 *
	 * @param request the request
	 * @return true, if is export request
	 */
	public static boolean isExportRequest(HttpServletRequest request) {
		return UtilFunc.hasLength(request.getParameter(TABLE_EXPORT)) || UtilFunc.hasLength(request.getParameter(GRID_EXPORT));
	}

	/**
	 * 获取分页大小.
	 * 
	 * @param request the request
	 * @return the page size
	 */
	public static int getPageSize(HttpServletRequest request) {
		int size = 0;
		if (UtilFunc.hasText(request.getParameter("pagesize"))) {
			size = Integer.parseInt(request.getParameter("pagesize"));
		}
		if (size < 1) {
			size = PaginationHelper.PAGE_SIZE;
		}

		return size;
	}

	/**
	 * 获取分页索引.
	 * 
	 * @param request the request
	 * @return the page index
	 */
	public static int getPageIndex(HttpServletRequest request) {
		int index = 0;
		if (UtilFunc.hasText(request.getParameter("page"))) {
			index = Integer.parseInt(request.getParameter("page"));
		}
		if (index < 1) {
			index = 1;
		}

		return index;
	}

	/**
	 * 初始化分页.
	 * 
	 * @param request 请求对象
	 */
	public static void initPagination(HttpServletRequest request) {
		initPagination(request, getPageSize(request));
	}

	/**
	 * 初始化分页.
	 * 
	 * @param request  请求对象
	 * @param pageSize 页大小
	 */
	public static void initPagination(HttpServletRequest request, int pageSize) {
		if (request == null)
			return;

		String pagestr = request.getParameter("page");
		String sort = request.getParameter("sort");
		String dir = request.getParameter("dir");
		String summary = request.getParameter("summary");

		PaginationObj page = PaginationHelper.init();
		if (isExportRequest(request)) {
			page.setNeedCount(false);

			String filetype = request.getParameter("filetype");
			if ("xlsx".equals(filetype) || "pdf".equals(filetype)) {
				page.setPageSize(PaginationHelper.LIMIT_EXPORT_ROWS);
			} else {
				page.setPageSize(65000);
			}
			page.setPageIndex(1);
		} else {
			int pageIndex = UtilFunc.stringToInt(pagestr, 1);
			if ("false".equals(request.getParameter("countenable"))) {
				page.setNeedCount(false);
			} else {
				page.setNeedCount(true);
			}
			page.setPageSize(pageSize);
			page.setPageIndex(pageIndex);
			if (UtilFunc.hasText(summary)) {
				page.setSummaryExp(summary);
			}
		}

		if (UtilFunc.hasText(sort)) {
			String sortText = sort;
			if (UtilFunc.hasText(dir)) {
				sortText += " " + dir;
			}

			page.setSort(sortText);
		}
	}

	/**
	 * 获取分页对象.
	 * 
	 * @return 分页对象
	 */
	public static PaginationObj getPaginationObj() {
		return PaginationHelper.getPaginationObj();
	}

	/**
	 * 获取分页列表.
	 * 
	 * @param data 数据
	 * @return the paginated list
	 */
	public static DBPaginatedList getPaginatedList(List data) {
		DBPaginatedList pageList = new DBPaginatedList();
		pageList.init(PaginationHelper.getPaginationObj(), data);

		return pageList;
	}

	/**
	 * 获取分页列表.
	 * 
	 * @param data       数据
	 * @param pageNumber 页码
	 * @return the paginated list
	 */
	public static DBPaginatedList getPaginatedList(List data, int pageNumber) {
		return getPaginatedList(data, PaginationHelper.PAGE_SIZE, pageNumber);
	}

	/**
	 * 获取分页列表.
	 * 
	 * @param data       数据
	 * @param pageSize   页大小
	 * @param pageNumber 页码
	 * @return the paginated list
	 */
	public static DBPaginatedList getPaginatedList(List data, int pageSize, int pageNumber) {
		int size = CollectionHelper.size(data);

		int pageIndex = pageNumber < 1 ? 1 : pageNumber;

		DBPaginatedList pageList = new DBPaginatedList();
		pageList.setObjectsPerPage(pageSize);
		pageList.setPageNumber(pageIndex);
		pageList.setFullListSize(size);
		int start = getStartIndex(size, pageSize, pageIndex);
		int end = getEndIndex(size, pageSize, pageIndex);

		if (start >= 0 && end >= 0 && end >= start) {
			pageList.setList(data.subList(start, end));
		} else {
			pageList.setList(Collections.EMPTY_LIST);
		}

		return pageList;
	}

	/**
	 * 获取开始行号.
	 * 
	 * @param listSize   数据大小
	 * @param pageSize   页大小
	 * @param pageNumber 页码
	 * @return 起始索引
	 */
	public static int getStartIndex(int listSize, int pageSize, int pageNumber) {
		if (listSize < 1) {
			return -1;
		}

		int start = ((pageNumber < 1 ? 1 : pageNumber) - 1) * pageSize;

		if (start >= listSize) {
			start = -1;
		}

		return start;
	}

	/**
	 * 获取结束行号.
	 * 
	 * @param listSize   数据大小
	 * @param pageSize   页大小
	 * @param pageNumber 页码
	 * @return 结束索引
	 */
	public static int getEndIndex(int listSize, int pageSize, int pageNumber) {
		if (listSize < 1) {
			return -1;
		}

		int end = (pageNumber < 1 ? 1 : pageNumber) * pageSize;
		if (end > listSize) {
			end = listSize;
		}

		return end;
	}

	/**
	 * 获取页数.
	 * 
	 * @param listSize 数据大小
	 * @param pageSize 页大小
	 * @return 页数
	 */
	public static int getPageCount(int listSize, int pageSize) {
		if (listSize < 1) {
			return 0;
		}

		int pageCount = listSize / pageSize;
		if ((listSize % pageSize) > 0) {
			pageCount++;
		}

		return pageCount;
	}

	/**
	 * Paginated list to map.
	 * 
	 * @param paginatedList the paginated list
	 * @return the map
	 */
	public static Map paginatedListToMap(DBPaginatedList paginatedList) {
		Map map = new HashMap<String, Object>();

		map.put("page", paginatedList.getPageNumber());
		map.put("total", paginatedList.getFullListSize());
		map.put("records", CollectionHelper.size(paginatedList.getList()));
		map.put("rows", paginatedList.getList());

		if (paginatedList.getSummaryResult() != null) {
			map.put("summary", paginatedList.getSummaryResult());
		}

		return map;
	}

	/**
	 * 初始化页面参数
	 * 
	 * @author lwyao
	 * @param request
	 * @param page
	 */
	public static void setPageParam(HttpServletRequest request, Page page) {
		if (page != null) {
			initPagination(request);
			page.setSortOrder(getPaginationObj().getSort());
		}
	}

	/**
	 * 渲染数据表格
	 * 
	 * @author lwyao
	 * @param request
	 * @param response
	 * @param result
	 */
	public static void renderDataGrid(HttpServletRequest request, HttpServletResponse response, ListResult result) {
		DataGridHelper.render(request, response, getPaginatedList(result.getList(), new int[] { result.getCount() }));
	}

	/**
	 * 获取分页列表.
	 * 
	 * @param data 数据
	 * @return the paginated list
	 */
	public static DBPaginatedList getPaginatedList(List data, int... count) {
		if (count != null && count.length > 0)
			PaginationHelper.getPaginationObj().setCount(count[0]);
		DBPaginatedList pageList = new DBPaginatedList();
		pageList.init(PaginationHelper.getPaginationObj(), data);
		return pageList;
	}
}
