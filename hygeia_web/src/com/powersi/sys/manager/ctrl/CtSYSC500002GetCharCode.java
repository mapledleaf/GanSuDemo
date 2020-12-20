package com.powersi.sys.manager.ctrl;

import com.powersi.hygeia.framework.BusiService;
import com.powersi.hygeia.framework.IParameterObj;
import com.powersi.hygeia.framework.IResultObj;
import com.powersi.hygeia.framework.exception.HygeiaException;

/**
 * 处理汉字转换成拼音、五笔助记符的查询的控制类.
 */
public class CtSYSC500002GetCharCode extends BusiService {

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
//            EnCharCodeInfo theEnCharCodeInfo = new EnCharCodeInfo();
//            theEnCharCodeInfo.setConnection(getConnection());
//
//            String str = "";
//            if (!UtilFunc.isEmptyString(param.getAllParameters().get("c_char"))) {
//                str = param.getAllParameters().get("c_char").toString();
//            }
//
//            if (theEnCharCodeInfo.getCharCodeInfo(str) >= 0) {
//                setResultSet(theEnCharCodeInfo.getResult());
//            }

            setRetMsg("获取字符编码成功");
            return SUCCESS;
        } catch (HygeiaException ex) {
            errorHandle(8, ex);
            return FAILURE;
        } catch (Exception ex) {
            errorHandle(9, getActionName() + "出现异常", ex);
            return FAILURE;
        } 
    }
}