/**
 * Copyright 2009 Powersi. All right reserved.
 * 
 */

package com.powersi.sys.message.ctrl;

import java.util.Map;

import com.powersi.hygeia.framework.BusiService;
import com.powersi.hygeia.framework.GlobalContext;
import com.powersi.hygeia.framework.IParameterObj;
import com.powersi.hygeia.framework.IResultObj;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.sys.message.dao.BulletinDAO;

/**
 * 修改消息标记控制类.
 */
public class CtSYSC320002UpdateMessage extends BusiService {
	
	/** The CLAS s_ name. */
	private static String CLASS_NAME = "SYSC320002";
	
	/** The m param. */
	private Map mParam = null;

	/** The str ms id. */
	private String strMsId = "";

	/** The str center id. */
	private String strCenterId = "";
	
	/** The str org. */
	private String strOrg = "";
	
	/** The str staff. */
	private String strStaff = "";
	
	/** The str man. */
	private String strMan = "";
	
	/**
	 * 公共入口方法.
	 *
	 * @param param IParameterObj 参数集
	 * @param result IResultObj 结果集
	 * @return int 大于0表示成功，小于0表示出错
	 */
	public int execute(IParameterObj param, IResultObj result) {

		try {
			int iReturn = 0;
			// 获取参数
			if (this.getPara(param, result) < 0) {
				return FAILURE;
			}

			// 更新通知信息
			iReturn = this.updateMessageInfo(result);
			if (iReturn < 0) {
				return FAILURE;
			}

			result.setRetMsg("修改消息标记成功");
			return SUCCESS;
		} catch (HygeiaException ex) {
			errorHandle(1, ex);
			return FAILURE;
		} catch (Exception ex) {
			errorHandle(ERROR, 2, getActionName() + "出现异常", ex);
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
			if (mParam.get("ms_id") == null
					|| mParam.get("ms_id").toString().length() == 0) {
				errorHandle(result, INFO, CLASS_NAME + "0101", "入参消息编号不能为空");
				return -1;
			}
			this.strMsId = UtilFunc.replace(mParam.get("ms_id").toString(), "'", "");

			if(mParam.containsKey("aaa027")){
            	this.strCenterId = mParam.get("aaa027").toString();
            } else if(mParam.containsKey("center_id")){
            	this.strCenterId = mParam.get("center_id").toString();
            } else {
            	errorHandle(result, INFO, CLASS_NAME + "0101", "入参中心编号不能为空");
                return -1;
            }

			if (mParam.get("receive_org") == null
					|| mParam.get("receive_org").toString().length() == 0) {
				errorHandle(result, INFO, CLASS_NAME + "0103", "入参查询组织不能为空");
				return -1;
			}
			this.strOrg = mParam.get("receive_org").toString();

			if (mParam.get("receive_staff") == null
					|| mParam.get("receive_staff").toString().length() == 0) {
				errorHandle(result, INFO, CLASS_NAME + "0104", "入参查询人姓名不能为空");
				return -1;
			}
			this.strStaff = mParam.get("receive_staff").toString();

			if (mParam.get("receive_man") == null
					|| mParam.get("receive_man").toString().length() == 0) {
				errorHandle(result, INFO, CLASS_NAME + "0104", "入参查询人姓名不能为空");
				return -1;
			}
			this.strMan = mParam.get("receive_man").toString();

			  // 省结算中心
            if ("100000".equals(GlobalContext.getContext("service_centerid", ""))) {
                this.strCenterId = "100000";
                this.strOrg = "100000";
            }
		} catch (Exception ex) {
			errorHandle(ERROR, 3, getActionName() + "校验参数时出现异常", ex);
			return FAILURE;
		}
		return 1;
	}

	/**
	 * 更新通知信息.
	 *
	 * @param result 结果集
	 * @return 大于0表示成功，小于0表示出错
	 */

	public int updateMessageInfo(IResultObj result) {
		if (this.strMsId == null || this.strMsId.length() == 0)
			return 0;

		try {
			BulletinDAO dao = getBean(BulletinDAO.class);
			dao.updateQueryMessage(this.strCenterId, this.strOrg,
					this.strStaff, this.strMan, this.strMsId.toString());

			return SUCCESS;
		} catch (HygeiaException ex) {
			errorHandle(6, ex);
			return FAILURE;
		} catch (Exception ex) {
			errorHandle(result, 7, getActionName() + "更新通知信息时出现异常", ex);
			return FAILURE;
		}
	}
}
