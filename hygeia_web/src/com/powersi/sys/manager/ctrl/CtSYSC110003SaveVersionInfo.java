/*
 * Copyright 2012 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.ctrl;

import com.powersi.hygeia.framework.BusiService;
import com.powersi.hygeia.framework.IParameterObj;
import com.powersi.hygeia.framework.IResultObj;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.sys.manager.dao.VersionDAO;

/**
 * 保存版本信息服务.
 */
public class CtSYSC110003SaveVersionInfo extends BusiService {

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
            // 保存版本信息
            if (this.saveVersionInfo() != SUCCESS) {
                return FAILURE;
            }
            setRetMsg("保存版本信息成功");
            return SUCCESS;
        } catch (Exception e) {
            errorHandle(9, getActionName() + "出现异常", e);
            return FAILURE;
        } 
    }

    /**
     * 保存版本信息.
     * 
     * @return 大于0表示成功，小于0表示出错
     */
    private int saveVersionInfo() {
        try {
        	VersionDAO dao = getBean(VersionDAO.class);
        	
        	dao.delete(getParamSet("deleteinfo"));
        	dao.update(getParamSet("updateinfo"));
        	dao.insert(getParamSet("insertinfo"));

        	dao.updateFile(getParamSet("fileinfo"));

            return SUCCESS;
        } catch (HygeiaException e) {
            errorHandle(108, e);
            return FAILURE;
        } catch (Exception e) {
            errorHandle(109, "保存版本信息时出错", e);
            return FAILURE;
        }
    }
}
