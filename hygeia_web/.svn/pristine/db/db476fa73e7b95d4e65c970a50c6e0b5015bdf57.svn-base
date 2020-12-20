/**
 * Copyright 2012 Powersi. All right reserved.
 * 
 */
package com.powersi.sys.message.ctrl;

import java.util.List;
import java.util.Map;

import com.powersi.hygeia.framework.BusiService;
import com.powersi.hygeia.framework.GlobalContext;
import com.powersi.hygeia.framework.IParameterObj;
import com.powersi.hygeia.framework.IResultObj;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.CollectionHelper;
import com.powersi.sys.message.dao.BulletinDAO;

/**
 * 获取消息控制类.
 */
public class CtSYSC320003QueryMessage extends BusiService {
    
    /** The CLAS s_ name. */
    private static String CLASS_NAME = "SYSC320003";
    
    /** The m param. */
    private Map mParam = null;
    
    /** The str center id. */
    private String strCenterId = "";
    
    /** The str org. */
    private String strOrg = "";
    
    /** The str staff. */
    private String strStaff = "";

    /**
     * 公共入口方法.
     *
     * @param param IParameterObj 参数集
     * @param result IResultObj 结果集
     * @return int 大于0表示成功，小于0表示出错
     */
    public int execute(IParameterObj param, IResultObj result) {
        try {

            // 获取参数
            if (getPara(param, result) < 0) {
                return FAILURE;
            }

            // 获取消息信息
            if (getMessageInfo() < 0) {
                return FAILURE;
            }

            result.setRetMsg("获取消息信息成功");
            return SUCCESS;
        } catch (Exception ex) {
            errorHandle(ERROR, 1, getActionName() + "出现异常", ex);
            return FAILURE;
        }
    }

    /**
     * 效验并获取参数[1].
     *
     * @param param the param
     * @param result 结果集
     * @return 大于0表示成功，小于0表示出错
     */
    private int getPara(IParameterObj param, IResultObj result) {
        try {
            mParam = param.getAllParameters();
            
            if(mParam.containsKey("aaa027")){
            	this.strCenterId = mParam.get("aaa027").toString();
            } else if(mParam.containsKey("center_id")){
            	this.strCenterId = mParam.get("center_id").toString();
            } else {
            	errorHandle(result, INFO, CLASS_NAME + "0101", "入参中心编号不能为空");
                return -1;
            }
         
            if (mParam.get("receive_org") == null || mParam.get("receive_org").toString().length() == 0) {
                errorHandle(result, INFO, CLASS_NAME + "0102", "入参查询组织不能为空");
                return -1;
            }
            this.strOrg = mParam.get("receive_org").toString();

            if (mParam.get("receive_staff") == null || mParam.get("receive_staff").toString().length() == 0) {
                errorHandle(result, INFO, CLASS_NAME + "0103", "入参查询人工号不能为空");
                return -1;
            }
            this.strStaff = mParam.get("receive_staff").toString();

            // 省结算中心
            if ("100000".equals(GlobalContext.getContext("service_centerid", ""))) {
                this.strCenterId = "100000";
                this.strOrg = "100000";
            }
            
            return 1;
        } catch (Exception ex) {
            errorHandle(ERROR, 3, getActionName() + "校验参数时出现异常", ex);
            return FAILURE;
        }
    }

    /**
     * 获取消息信息.
     *
     * @return 大于0表示成功，小于0表示出错
     */
    public int getMessageInfo() {
        int iReturn = 0;

        try {
            BulletinDAO dao = getBean(BulletinDAO.class);
        	
        	List lst = dao.hasNewMessage(this.strCenterId, this.strOrg, this.strStaff);
        	iReturn = CollectionHelper.size(lst);
            if (iReturn > 0) {
            	setResultSet(lst);
            }
            
            return iReturn;
        } catch (HygeiaException ex) {
            errorHandle(4, ex);
            return -1;
        } catch (Exception ex) {
            errorHandle(5, getActionName() + "获取消息信息时出现异常", ex);
            return -1;
        }
    }
}
