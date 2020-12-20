/*
 * Copyright 2016 Powersi. All rights reserved.
 */
package com.powersi.sys.user.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.sys.user.dto.PwdRetrieveDTO;
import com.powersi.sys.user.service.UserService;
import com.powersi.sys.util.PasswordHelper;

/**
 * The Class ChangePasswordAction.
 */
@Action(value = "ChangePassword", results = {
		@Result(name = "success", location = "/pages/sys/user/ChangePassword.jsp"),
		@Result(name = "input", location = "/pages/sys/user/ChangePassword.jsp")
})
public class ChangePasswordAction extends BaseAction {

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The old password. */
    private String oldPassword = null;

    /** The new password. */
    private String newPassword = null;

    /** The confirm password. */
    private String confirmPassword = null;
    
    /** The service. */
    private static UserService service = getBean(UserService.class);
  
    /* (non-Javadoc)
     * @see com.opensymphony.xwork2.ActionSupport#execute()
     */
    @Override
    public String execute() throws Exception {
        try {
            if (!"save".equals(getParameter("method"))) {
                return INPUT;
            }
            
            if(!"1".equals(getParameter("nonePassword"))){

                if (!newPassword.equals(confirmPassword)) {
                	throw new HygeiaException("确认密码与新密码不同");
                }
                
                service.changePassword(oldPassword, newPassword);
            }
            
            if("1".equals(getParameter("saveQuestion"))){
            	Long personId = Long.valueOf(getUserInfo().getUserId());
            	if(personId > 0) {
            		PwdRetrieveDTO dto = service.getRetrieveQuestion(personId);
                	boolean addFlag = false;
                	if(dto == null) {
                		addFlag = true;
                		
                		dto = new PwdRetrieveDTO();
                		dto.setIdcard(UtilFunc.getString(getUserInfo(), "login_user"));
                		dto.setPerson_id(personId);
                	}
                	
                	dto.setPwd_retrieve_state("1");
                	dto.setQuestion_1(getParameter("question_1"));
                	dto.setQuestion_2(getParameter("question_2"));
                	dto.setQuestion_3(getParameter("question_3"));
                	
                	dto.setAnswer_1(PasswordHelper.createPassword(getParameter("answer_1")));
                	dto.setAnswer_2(PasswordHelper.createPassword(getParameter("answer_2")));
                	dto.setAnswer_3(PasswordHelper.createPassword(getParameter("answer_3")));
                	
                	if(isEmpty(dto.getAnswer_1()) || isEmpty(dto.getAnswer_2()) || isEmpty(dto.getAnswer_3())) {
                		throw new HygeiaException("密保三个问题都必须输入");
                	}
                	
                	if(addFlag){
                		service.saveRetrieveQuestion(dto);
                	} else {
                		service.updateRetrieveQuestion(dto);
                	}
            	}
            }
            
            return renderMessage("保存成功");
        } catch (Exception ex) {
        	renderError(ex);
        	
        	if(isAjaxRequest()){
        		return NONE;
        	} else{
        		return INPUT;
        	}
        }
    }

    /**
     * Sets the old password.
     * 
     * @param oldPassword
     *            the new old password
     */
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    /**
     * Gets the old password.
     * 
     * @return the old password
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * Sets the new password.
     * 
     * @param newPassword
     *            the new new password
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * Gets the new password.
     * 
     * @return the new password
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * Sets the confirm password.
     * 
     * @param confirmPassword
     *            the new confirm password
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     * Gets the confirm password.
     * 
     * @return the confirm password
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }
}
