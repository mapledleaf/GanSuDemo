package com.powersi.sys.message.ctrl;

import com.powersi.hygeia.framework.BusiService;
import com.powersi.hygeia.framework.IParameterObj;
import com.powersi.hygeia.framework.IResultObj;

/**
 * 发送消息控制类
 */
public class CtSYSC320004SendMessage extends BusiService {
    private static String CLASS_NAME = "SYSC320004";

    /**
     * 公共入口方法[0]
     * 
     * @param parm
     *            参数集
     * @param result
     *            结果集
     * @return 大于0表示成功，小于0表示出错
     */
    public int execute(IParameterObj param, IResultObj result) {

        try {
        	errorHandle(result, INFO, CLASS_NAME + "0000", "功能正在建设中");
        	return FAILURE;
        } catch (Exception e) {
            errorHandle(result, ERROR, CLASS_NAME + "0009", "执行execute方法时出现异常", e);
            return FAILURE;
        }
    }
}
