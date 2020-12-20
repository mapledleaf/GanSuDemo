package com.powersi.biz.utils;

import java.util.Arrays;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.powersi.biz.medicare.hosp.pojo.HospInfoDTO;
import com.powersi.biz.medicare.inhospital.service.bean.BeanService;
import com.powersi.comm.mybatis.Page;
import com.powersi.comm.utils.UtilFunc;

/**
 * api专用工具类
 * 
 * @author MonkGirl
 *
 */
public class ApiMedicareUtils {

	private ApiMedicareUtils() {

	}

	private static final String CURRENT_PAGE = "currentPage";
	private static final String CURRENT_RESULT = "currentResult";
	private static final String ENTITY_OR_FIELD = "entityOrField";
	private static final String PAGE_SIZE = "pageSize";
	private static final String SORT_NAME = "sortName";
	private static final String SORT_ORDER = "sortOrder";
	private static final String TOTAL_PAGE = "totalPage";
	private static final String TOTAL_RESULT = "totalResult";

	private static final String BKC292 = "bkc292";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void copyProperties(Object sourceObj, Object targetObj, boolean isIgnoreNull) {

		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		BeanService beanService = wac.getBean(BeanService.class);
		// 此段代码为了特殊处理HospInfoDTO中bkc292字段，copyProperties()方法不支持byte[]类型的字段
		if (sourceObj instanceof HospInfoDTO && targetObj instanceof Map) {
			HospInfoDTO sourceDTO = (HospInfoDTO) sourceObj;
			Map targetMap = (Map) targetObj;
			String bkc292 = Arrays.toString(sourceDTO.getBkc292());
			sourceDTO.setBkc292(null);
			beanService.copyProperties(sourceDTO, targetMap, isIgnoreNull);
			targetMap.put(BKC292, bkc292);

			if (sourceObj instanceof Page && targetObj instanceof Map) {
				pagePutValueFromMap((Page) sourceObj, (Map) targetObj);
			}

		} else if (sourceObj instanceof Map && targetObj instanceof HospInfoDTO) {
			Map sourceMap = (Map) sourceObj;
			HospInfoDTO targetDTO = (HospInfoDTO) targetObj;
			String bkc292 = String.valueOf(sourceMap.get(BKC292));
			sourceMap.put(BKC292, null);
			beanService.copyProperties(sourceMap, targetDTO, isIgnoreNull);
			if (StringUtils.isNotBlank(bkc292) && !"null".equals(bkc292)) {
				String byteStr = bkc292.substring(1, bkc292.length() - 1);
				String[] strBkc292 = byteStr.split(",");
				byte[] byteBkc292 = new byte[strBkc292.length];
				for (int j = 0; j < strBkc292.length; j++) {
					byteBkc292[j] = Byte.parseByte(StringUtils.trim(strBkc292[j]));
				}
				targetDTO.setBkc292(byteBkc292);
			}

			if (sourceObj instanceof Map && targetObj instanceof Page) {
				mapPutValueFromPage((Map) sourceObj, (Page) targetObj);
			}
		} else {
			beanService.copyProperties(sourceObj, targetObj, isIgnoreNull);

			if (sourceObj instanceof Page && targetObj instanceof Map) {
				pagePutValueFromMap((Page) sourceObj, (Map) targetObj);
			}

			if (sourceObj instanceof Map && targetObj instanceof Page) {
				mapPutValueFromPage((Map) sourceObj, (Page) targetObj);
			}
		}
	}

	private static void pagePutValueFromMap(Page sourceObj, Map<String, Object> targetObj) {
		targetObj.put(CURRENT_PAGE, sourceObj.getCurrentPage());
		targetObj.put(CURRENT_RESULT, sourceObj.getCurrentResult());
		targetObj.put(ENTITY_OR_FIELD, sourceObj.isEntityOrField());
		targetObj.put(PAGE_SIZE, sourceObj.getPageSize());
		targetObj.put(SORT_NAME, sourceObj.getSortName());
		targetObj.put(SORT_ORDER, sourceObj.getSortOrder());
		targetObj.put(TOTAL_PAGE, sourceObj.getTotalPage());
		targetObj.put(TOTAL_RESULT, sourceObj.getTotalResult());
	}

	private static void mapPutValueFromPage(Map<String, Object> sourceObj, Page targetObj) {
		targetObj.setEntityOrField(Boolean.parseBoolean(UtilFunc.getString(sourceObj, ENTITY_OR_FIELD)));
		targetObj.setSortName(UtilFunc.getString(sourceObj, SORT_NAME));
		targetObj.setSortOrder(UtilFunc.getString(sourceObj, SORT_ORDER));
		
		String currentPageStr = UtilFunc.getString(sourceObj, CURRENT_PAGE);
		targetObj.setCurrentPage(!"".equals(currentPageStr) ? Integer.parseInt(currentPageStr) : 1);
		
		String currentResultStr = UtilFunc.getString(sourceObj, CURRENT_RESULT);
		targetObj.setCurrentResult(!"".equals(currentResultStr) ? Integer.parseInt(currentPageStr) : 0);
		
		String pageSizeStr = UtilFunc.getString(sourceObj, PAGE_SIZE);
		targetObj.setPageSize(!"".equals(pageSizeStr) ? Integer.parseInt(pageSizeStr) : 30);
		
		String totalPageStr = UtilFunc.getString(sourceObj, TOTAL_PAGE);
		targetObj.setTotalPage(!"".equals(totalPageStr) ? Integer.parseInt(totalPageStr) : 0);
		
		String totalResultStr = UtilFunc.getString(sourceObj, TOTAL_RESULT);
		targetObj.setTotalResult(!"".equals(totalResultStr) ? Integer.parseInt(totalResultStr) : 0);
	}
}
