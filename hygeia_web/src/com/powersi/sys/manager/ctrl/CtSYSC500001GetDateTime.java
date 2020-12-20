package com.powersi.sys.manager.ctrl;

import com.powersi.hygeia.framework.BusiService;
import com.powersi.hygeia.framework.IParameterObj;
import com.powersi.hygeia.framework.IResultObj;
import com.powersi.hygeia.framework.ResultHelper;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.DBHelper;

/**
 * 获取数据库服务器时间.
 */
public class CtSYSC500001GetDateTime extends BusiService {

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
            Object obj = DBHelper.executeScale("select to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss') from dual");
            result.setResult("sys_date", obj);
            setResultSet("sysdate", ResultHelper.addToList("sys_date", obj.toString()));
            result.setRetMsg("获取数据库服务器时间成功");
            return SUCCESS;
        } catch (HygeiaException ex) {
            errorHandle(8, ex);
            return FAILURE;
        } catch (Exception e) {
            errorHandle(9, getActionName() + "出现异常", e);
            return FAILURE;
        }
    }
}
