/**
 * Copyright 2012 Powersi. All rights reserved.
 * 
 */
package com.powersi.sys.manager.ctrl;

import com.powersi.hygeia.framework.BusiService;
import com.powersi.hygeia.framework.IParameterObj;
import com.powersi.hygeia.framework.IResultObj;
import com.powersi.hygeia.framework.ResultHelper;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.SysFunc;
import com.powersi.hygeia.framework.util.UtilFunc;

/**
 * 获取最大序列号服务.
 */
public class CtSYSC500003GetMaxNo extends BusiService {

    /**
     * 公共入口方法.
     * 
     * @param param
     *            入参对象
     * @param result
     *            结果对象
     * 
     * @return 大于0表示成功，小于0表示出错
     */
    public int execute(IParameterObj param, IResultObj result) {
        try {
            if (UtilFunc.isEmptyString(getAllParameters().get("maxno_name"))) {
                errorHandle(1, "序列号类型不能为空");
            }
            if (UtilFunc.isEmptyString(getAllParameters().get("maxno_size"))) {
                errorHandle(1, "序列号个数不能为空");
            }
            long maxNo = SysFunc.getMaxNo(getAllParameters().get("maxno_name").toString(), Integer.parseInt(UtilFunc.getString(getAllParameters(), "maxno_size", "1")));

            setResultSet("maxno", ResultHelper.addToList("count", String.valueOf(maxNo)));

            setRetMsg("获取最大序列号成功");
            return SUCCESS;
        } catch (HygeiaException ex) {
            errorHandle(8, ex);
            return FAILURE;
        } catch (Exception ex) {
            errorHandle(9, getActionName() + "出错", ex);
            return FAILURE;
        } 
    }
}