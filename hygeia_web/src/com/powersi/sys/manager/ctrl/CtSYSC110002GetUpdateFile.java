/**
 * Copyright 2012 Powersi. All rights reserved.
 * 
 */
package com.powersi.sys.manager.ctrl;

import java.util.List;
import java.util.Map;

import com.powersi.hygeia.framework.BusiService;
import com.powersi.hygeia.framework.IParameterObj;
import com.powersi.hygeia.framework.IResultObj;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.CollectionHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.sys.manager.dao.VersionDAO;

/**
 *获取更新文件信息服务
 */
public class CtSYSC110002GetUpdateFile extends BusiService {

    private String strFileType = ""; // 文件类型(C:中心程序 H:医院程序 D:药店程序)
    private String strFileName = ""; // 文件名称
    private String strSN = ""; // 文件序列号

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
            // 获取参数
            if (this.getPara() != SUCCESS) {
                return FAILURE;
            }

            // 获取版本文件信息
            if (this.getVersionInfo() != SUCCESS) {
                return FAILURE;
            }

            setRetMsg("获取文件信息成功");
            return SUCCESS;
        } catch (Exception e) {
            errorHandle(9, getActionName() + "出现异常", e);
            return FAILURE;
        }
    }

    /**
     * 效验并获取参数.
     * 
     * @return 大于0表示成功，小于0表示出错
     */
    private int getPara() {
        try {
            Map mParam = getAllParameters();

            if (UtilFunc.isEmptyString(mParam.get("file_type"))) {
                errorHandle(101, "入参文件类型不能为空");
                return FAILURE;
            }
            this.strFileType = mParam.get("file_type").toString();

            if (UtilFunc.isEmptyString(mParam.get("sn"))) {
                errorHandle(101, "入参文件序号不能为空");
                return FAILURE;
            }
            this.strSN = mParam.get("sn").toString();

            if (UtilFunc.isEmptyString(mParam.get("file_name"))) {
                errorHandle(101, "入参文件名称不能为空");
                return FAILURE;
            }
            this.strFileName = mParam.get("file_name").toString();

            return SUCCESS;
        } catch (Exception e) {
            errorHandle(109, "效验参数时出现异常", e);
            return FAILURE;
        }
    }

    /**
     * 获取版本信息.
     * 
     * @return 大于0表示成功，小于0表示出错
     */
    private int getVersionInfo() {
        try {
        	VersionDAO dao = getBean(VersionDAO.class);
        	
        	List lst = dao.getFile(this.strSN, this.strFileName, this.strFileType);
            if (CollectionHelper.isEmpty(lst)) {
                errorHandle(201, "无法获取指定升级文件");
                return FAILURE;
            }

            setResultSet("versioninfo", lst);
            return SUCCESS;
        } catch (HygeiaException e) {
            errorHandle(208, e);
            return FAILURE;
        } catch (Exception e) {
            errorHandle(209, "获取版本信息时出现异常", e);
            return FAILURE;
        }
    }
}
