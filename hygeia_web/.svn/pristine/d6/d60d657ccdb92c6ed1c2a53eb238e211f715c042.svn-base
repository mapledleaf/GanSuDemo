package com.powersi.ssm.biz.medicare.common.util;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.comm.pojo.ListResultDTO;
import com.powersi.biz.utils.ApiMedicareUtils;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.ssm.biz.medicare.api.dto.APIRemoteCallParam;
import com.powersi.ssm.biz.medicare.api.dto.APIRemoteCallResult;
import com.powersi.ssm.biz.medicare.api.service.APIRemoteCallService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

/**
 * <pre>
 * Web通过api方式调用Esb: web项目service调用esb项目service中间处理器
 * 主要作用：根据调用的方法签名，自动处理参数列表中的值。
 * </pre>
 *
 * @author MonkGirl
 */
public class ApiRemoteCallWebProcessor {

    private ApiRemoteCallWebProcessor() {

    }

    private static final String PARAM = "param"; // 定义类常量，减少字面量，提供代码质量和程序执行效率

    /**
     * web调用esb服务，由rpc模式更换为api方式,参数列表中的参数暂时只支持7种基本类型、List<Map>、Map以及实体类形式。
     *
     * @param serviceId  需要调用功能的service bean Id值
     * @param methodName 需要调用功能的方法名
     * @param paramObj   需要调用功能的方法参数列表
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <T, E> E process(String serviceId, String methodName, Object... paramObj) {
        APIRemoteCallParam apiRemoteCallParam = new APIRemoteCallParam();

        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        APIRemoteCallService apiRemoteCallService = wac.getBean(APIRemoteCallService.class);

        int i = 0;
        for (Object obj : paramObj) {
            apiRemoteCallParam.setValue("argClass" + i, obj.getClass().getName());
            if (obj instanceof java.lang.String || obj instanceof java.lang.Float || obj instanceof java.lang.Double
                    || obj instanceof java.lang.Integer || obj instanceof java.lang.Long
                    || obj instanceof java.lang.Boolean) {
                apiRemoteCallParam.setValue(PARAM + i, String.valueOf(obj));
            } else if (obj instanceof java.util.List) {
                apiRemoteCallParam.setValue("argClass" + i, "java.util.List");
                List<Map> tempList = new ArrayList<>();
                List<?> objList = (List<?>) obj;
                for (Object o : objList) {
                    Map tempMap = new HashMap<>();
                    ApiMedicareUtils.copyProperties(o, tempMap, true);
                    tempList.add(tempMap);
                }
                if (!objList.isEmpty()) {
                    apiRemoteCallParam.setValue("argListClass" + i, objList.get(0).getClass().getName());
                }
                apiRemoteCallParam.setList(PARAM + i, tempList);
            } else {
                List<Map> tempList = new ArrayList<>();
                Map tempMap = new HashMap<>();
                ApiMedicareUtils.copyProperties(obj, tempMap, true);
                tempList.add(tempMap);
                apiRemoteCallParam.setList(PARAM + i, tempList);
            }
            i++;
        }

        apiRemoteCallParam.setFunction_id(serviceId);
        apiRemoteCallParam.setValue("methodName", methodName);
        apiRemoteCallParam.setValue("paramNum", String.valueOf(i));
        APIRemoteCallResult apiRemoteCallResult = apiRemoteCallService.execute(apiRemoteCallParam);
        List<Map> responseList = apiRemoteCallResult.getList("responseList");
        String responseValue = apiRemoteCallResult.getValue("responseValue");
        String returnType = apiRemoteCallResult.getValue("returnType");
        List<T> resultList = new ArrayList<>();
        try {
            switch (returnType.toLowerCase()) {
                case "void":
                    return null;
                case "int":
                case "integer":
                    return (E) Integer.valueOf(responseValue);
                case "long":
                    return (E) Long.valueOf(responseValue);
                case "float":
                    return (E) Float.valueOf(responseValue);
                case "double":
                    return (E) Double.valueOf(responseValue);
                case "boolean":
                    return (E) Boolean.valueOf(responseValue);
                default:
                    Class<?> returnClass = Class.forName(returnType);
                    if (returnClass.isAssignableFrom(String.class)) {
                        return (E) String.valueOf(responseValue);
                    } else if (returnClass.isAssignableFrom(ListResult.class)) {
                        ListResult listResult = ListResultDTO.newInstance();
                        String count = apiRemoteCallResult.getValue("listResultCount");
                        listResult.setCount(Integer.parseInt(count));
                        List<Map> listMap = apiRemoteCallResult.getList("listResultList");
                        listResult.setList(listMap);
                        return (E) listResult;
                    } else if (returnClass.isAssignableFrom(List.class)) {
                        String resultListClass = apiRemoteCallResult.getValue("resultListClass");
                        if (StringUtils.isNotBlank(resultListClass)) {
                            for (Map tempMap : responseList) {
                                Class<?> resultListType = Class.forName(resultListClass);
                                T t = (T) resultListType.newInstance();

                                ApiMedicareUtils.copyProperties(tempMap, t, true);
                                resultList.add(t);
                            }
                            return (E) resultList;
                        }
                        return (E) Collections.emptyList();
                    } else {
                        String resultObjClass = apiRemoteCallResult.getValue("resultObjClass");
                        T t = null;
                        if (StringUtils.isNotBlank(resultObjClass) && !responseList.isEmpty()) {
                            Map tempMap = responseList.get(0);
                            Class<?> resultObjType = Class.forName(resultObjClass);
                            t = (T) resultObjType.newInstance();
                            ApiMedicareUtils.copyProperties(tempMap, t, true);
                            return (E) t;
                        }
                        return (E) t;
                    }
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new HygeiaException("Web通过Api调用Esb出错:" + e.getMessage());
        }
    }

    /**
     * 异地就医web医院端与api接口整合，统一使用该方法
     *
     * @param serviceId
     * @param paramMap
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static List<Map> processApi(String serviceId, Object... paramObj) {
        APIRemoteCallParam apiRemoteCallParam = new APIRemoteCallParam();
        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        APIRemoteCallService apiRemoteCallService = wac.getBean(APIRemoteCallService.class);

        apiRemoteCallParam.setFunction_id(serviceId);
        apiRemoteCallParam.setValue("isRemoteWeb", "1");

        int i = 0;
        for (Object obj : paramObj) {
            apiRemoteCallParam.setValue("argClass" + i, obj.getClass().getName());
            if (obj instanceof java.lang.String || obj instanceof java.lang.Float || obj instanceof java.lang.Double
                    || obj instanceof java.lang.Integer || obj instanceof java.lang.Long
                    || obj instanceof java.lang.Boolean) {
                apiRemoteCallParam.setValue(PARAM + i, String.valueOf(obj));
            } else if (obj instanceof java.util.List) {
                apiRemoteCallParam.setValue("argClass" + i, "java.util.List");
                List<Map> tempList = new ArrayList<>();
                List<?> objList = (List<?>) obj;
                for (Object o : objList) {
                    Map tempMap = new HashMap<>();
                    ApiMedicareUtils.copyProperties(o, tempMap, true);
                    tempList.add(tempMap);
                }
                if (!objList.isEmpty()) {
                    apiRemoteCallParam.setValue("argListClass" + i, objList.get(0).getClass().getName());
                }
                apiRemoteCallParam.setList(PARAM + i, tempList);
            } else {
                List<Map> tempList = new ArrayList<>();
                Map tempMap = new HashMap<>();
                ApiMedicareUtils.copyProperties(obj, tempMap, true);
                tempList.add(tempMap);
                apiRemoteCallParam.setList(PARAM + i, tempList);
                if (("Remote_BIZC131255".equals(serviceId) || "Remote_BIZC131256".equals(serviceId))
                        && tempMap.containsKey("searchterm")) {
                    apiRemoteCallParam.setValue("isFeeOrFund", tempMap.get("searchterm").toString());
                }
            }
            i++;
        }
        apiRemoteCallParam.setValue("paramNum", String.valueOf(i));
        APIRemoteCallResult apiRemoteCallResult = apiRemoteCallService.execute(apiRemoteCallParam);
        return apiRemoteCallResult.getList("resultList");
    }

    /**
     * listMap种的map转换为对应class实体类
     *
     * @param listMap
     * @param clazz
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static <T> List<T> convert(List<Map> listMap, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        for (Map m : listMap) {
            T t;
            try {
                t = clazz.newInstance();
                ApiMedicareUtils.copyProperties(m, t, true);
                list.add(t);
            } catch (InstantiationException | IllegalAccessException e) {
                throw new HygeiaException("Web通过Api调用Esb,convert转换方法出错:" + e.getMessage());
            }
        }
        return list;
    }
}
