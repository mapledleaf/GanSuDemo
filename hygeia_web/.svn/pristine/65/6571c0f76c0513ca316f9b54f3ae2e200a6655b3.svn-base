/**
 * Copyright 2016 Powersi. All rights reserved.
 * 
 */
package com.powersi.sys.user.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;

import com.powersi.hygeia.framework.BusiContext;
import com.powersi.hygeia.framework.UserInfo;
import com.powersi.hygeia.framework.entity.SysUserKind;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.hygeia.web.util.SessionHelper;
import com.powersi.hygeia.web.util.WebHelper;
import com.powersi.sys.user.dto.PwdRetrieveDTO;
import com.powersi.sys.user.service.UserService;
import com.powersi.sys.util.PasswordHelper;
import com.powersi.sys.util.UserKindHelper;

/**
 * The Class RetrievePasswordAction.
 */
@Action(value = "/login/openRetrievePassword", results = { @Result(name = "success", location = "/pages/sys/user/RetrievePasswordNext.jsp") })
public class RetrievePasswordAction extends BaseAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The dto. */
	private PwdRetrieveDTO dto;
	/** The card. */
	private String idcard = null;

	/** The name. */
	private String name = null;

	/** The email. */
	private String email = null;

	/** The mobile_phone. */
	private String mobile_phone = null;

	/** The verifycode. */
	private String verifycode = null;

	/** The msg. */
	private String msg = null;
	
	 /** The service. */
    private static UserService service = getBean(UserService.class);
  

	/**
	 * 个人密保信息完善.
	 * 
	 * @return the string
	 */
	@Action(value = "pwdRetrieveComplete", results = { @Result(name = "success", location = "/pages/sys/user/PwdQuestionComplete.jsp") })
	public String pwdRetrieveComplete() {
		String person_id = getUserInfo().getUserId();
		if (isNotEmpty(person_id)) {
			dto = service.getRetrieveQuestion(Long.valueOf(person_id));
			if (dto != null) {
				dto.setAnswer_1("");
				dto.setAnswer_2("");
				dto.setAnswer_3("");
				dto.setAnswer_4("");
				dto.setAnswer_5("");
			}
			return SUCCESS;
		}

		return LOGIN;
	}

	/**
	 * 保存个人密保.
	 * 
	 * @return the string
	 */
	@Actions({
			@Action(value = "/login/pwdRetrieveComplete", results = {
					@Result(name = "success", location = "/pages/sys/user/RetrievePasswordByQuestion.jsp"),
					@Result(name = "input", location = "/pages/sys/user/RetrievePasswordByQuestion.jsp") }),
			@Action(value = "/login/pwdRetrieveByRegister", results = {
					@Result(name = "success", location = "/pages/sys/user/RetrievePassword.jsp"),
					@Result(name = "input", location = "/pages/sys/user/RetrievePassword.jsp") }),
			@Action(value = "saveRetrieveQuestion", results = {
					@Result(name = "success", location = "pwdRetrieveComplete", type = "chain"),
					@Result(name = "input", location = "/pages/sys/user/PwdQuestionComplete.jsp") }),
			@Action(value = "upRetrieveQuestion", results = {
					@Result(name = "success", location = "pwdRetrieveComplete", type = "chain"),
					@Result(name = "input", location = "/pages/sys/user/PwdQuestionComplete.jsp") }) })
	public String saveRetrieveQuestion() {
		try {
			String person_id = getUserInfo().getUserId();

			if (!PasswordHelper.equalsPassword(getParameter("password"),
					getString(getUserInfo(), "password"))) {
				throw new HygeiaException("密码错误");
			}

			dto.setPwd_retrieve_state("1");

			dto.setAnswer_1(PasswordHelper.createPassword(dto.getAnswer_1()));
			dto.setAnswer_2(PasswordHelper.createPassword(dto.getAnswer_2()));
			dto.setAnswer_3(PasswordHelper.createPassword(dto.getAnswer_3()));

			if (isEmpty(dto.getAnswer_1())
					|| isEmpty(dto.getAnswer_2())
					|| isEmpty(dto.getAnswer_3())) {
				throw new HygeiaException("密保三个问题都必须输入");
			}

			if (isNotEmpty(person_id)) {
				if (dto.getPerson_id() != null && isNotEmpty(dto.getPerson_id().toString())) {
					service.updateRetrieveQuestion(dto);
					return renderMessage("更新成功");
				} else {
					dto.setPerson_id(Long.valueOf(person_id));
					dto.setIdcard(getString(getUserInfo(),
							"login_user"));
					service.saveRetrieveQuestion(dto);
					return renderMessage("保存成功");
				}
			}
			return LOGIN;
		} catch (Exception w) {
			renderError(w);
			return INPUT;
		}
	}

	/**
	 * 根据是否完善密保而进入不同的密码找回页面.
	 * 
	 * @return the string
	 */
	@Action(value = "/login/chkPwdRetrieveState", results = {
			@Result(name = "answer", location = "/pages/sys/user/RetrievePasswordByQuestion.jsp"),
			@Result(name = "basic", location = "/pages/sys/user/RetrievePassword.jsp"),
			@Result(name = "input", location = "/pages/sys/user/RetrievePasswordNext.jsp") })
	public String chkPwdRetrieveState() {
		try {
			if (isEmpty(idcard)) {
				return INPUT;
			}

			idcard = idcard.toUpperCase();
			Map map = service.queryPersonUser(idcard);
			if (isEmpty(map)
					|| !getString(map, "name").equals(name)) {
				renderError("用户不存在");
				return INPUT;
			}

			dto = service.getRetrieveQuestion(Long.valueOf(getString(map,
					"person_id")));
			if (dto != null) {
				dto.setAnswer_1("");
				dto.setAnswer_2("");
				dto.setAnswer_3("");
				dto.setAnswer_4("");
				dto.setAnswer_5("");
				return "answer";
			}

			return "basic";
		} catch (Exception ex) {
			renderError(ex);
			return INPUT;
		}
	}

	/**
	 * Save.
	 * 
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public String saveResetPassword() throws Exception {
		try {
			if (dto != null && !isEmpty(dto.getIdcard())) {
				idcard = dto.getIdcard();
			}

			if (isEmpty(idcard)) {
				renderError("请输入身份证号");
				return INPUT;
			}

			// 保证日志记录的是当前修改的用户信息
			SessionHelper.removeUserInfo(getRequest());

			UserInfo memUser = BusiContext.getUserInfo();
			memUser.setLoginId(0L);
			memUser.put("user_id", "0");
			memUser.put("user_kind", "1");
			memUser.put("login_address", WebHelper.getRemoteAddr(getRequest()));
			memUser.put("login_user", idcard);
			memUser.put("user_name", name == null ? "" : name);

			if (!checkVerifyCode()) {
				renderError("输入验证码错误");
				return INPUT;
			}

			idcard = idcard.toUpperCase();

			idcard = idcard.toUpperCase();
			Map map = service.queryPersonUser(idcard);

			if (isEmpty(map)) {
				renderError("用户不存在");
				return INPUT;
			}

			String personId = getString(map, "person_id");
			memUser.put("user_id", personId);

			PwdRetrieveDTO dbDto = service.getRetrieveQuestion(Long
					.valueOf(personId));
			if (dbDto != null) {
				if (!checkQuestion(dbDto)) {
					renderError("输入的密保答案错误");
					return INPUT;
				}
			} else {
				String[] cols = new String[] { "name", "email", "mobile_phone" };
				for (String col : cols) {
					if (!getString(map, col, "").equalsIgnoreCase(
							getParameter(col))) {
						renderError("输入的注册信息错误");
						return INPUT;
					}
				}
			}

			SysUserKind dto = UserKindHelper.get("1");
			if (dto == null) {
				renderError("无效的用户类型");
				return INPUT;
			}

			Map user = service.findUser(dto, personId);
			if (isEmpty(user)) {
				renderError("用户不存在");
				return INPUT;
			}

			service.resetPassword(user);

			StringBuilder sb = new StringBuilder();
			sb.append("用户[").append(user.get("login_user"));
			sb.append(" ").append(user.get("user_name")).append("]");
			sb.append("的密码已经重置为:").append(dto.getPasswordTip());
			sb.append("，请立即登录系统修改密码！");

			msg = sb.toString();
			return SUCCESS;
		} catch (Exception ex) {
			renderError(ex);
			return INPUT;
		}
	}

	/**
	 * Check question.
	 * 
	 * @param dbDto
	 *            the db dto
	 * @return true, if successful
	 */
	private boolean checkQuestion(PwdRetrieveDTO dbDto) {
		if (dbDto == null || dto == null) {
			return false;
		}

		if (dbDto.getAnswer_1() != null) {
			if (!dbDto.getAnswer_1().equals(
					PasswordHelper.createPassword(dto.getAnswer_1()))) {
				return false;
			}
		}

		if (dbDto.getAnswer_2() != null) {
			if (!dbDto.getAnswer_2().equals(
					PasswordHelper.createPassword(dto.getAnswer_2()))) {
				return false;
			}
		}

		if (dbDto.getAnswer_3() != null) {
			if (!dbDto.getAnswer_3().equals(
					PasswordHelper.createPassword(dto.getAnswer_3()))) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Check verify code.
	 * 
	 * @return true, if successful
	 */
	private boolean checkVerifyCode() {
		String inputValue = getParameter("verifycode");
		String saveValue = getSession(SessionHelper.SESSION_VERIFYCODE, "")
				.toString();
		if (isEmpty(inputValue)
				|| !inputValue.equalsIgnoreCase(saveValue)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Gets the card.
	 * 
	 * @return the card
	 */
	public String getIdcard() {
		return idcard;
	}

	/**
	 * Sets the card.
	 * 
	 * @param card
	 *            the new card
	 */
	public void setIdcard(String card) {
		this.idcard = card;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the email.
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 * 
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the mobile_phone.
	 * 
	 * @return the mobile_phone
	 */
	public String getMobile_phone() {
		return mobile_phone;
	}

	/**
	 * Sets the mobile_phone.
	 * 
	 * @param mobile_phone
	 *            the new mobile_phone
	 */
	public void setMobile_phone(String mobile_phone) {
		this.mobile_phone = mobile_phone;
	}

	/**
	 * Gets the verifycode.
	 * 
	 * @return the verifycode
	 */
	public String getVerifycode() {
		return verifycode;
	}

	/**
	 * Sets the verifycode.
	 * 
	 * @param verifycode
	 *            the new verifycode
	 */
	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}

	/**
	 * Sets the msg.
	 * 
	 * @param msg
	 *            the new msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * Gets the msg.
	 * 
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * Sets the dto.
	 * 
	 * @param dto
	 *            the new dto
	 */
	public void setDto(PwdRetrieveDTO dto) {
		this.dto = dto;
	}

	/**
	 * Gets the dto.
	 * 
	 * @return the dto
	 */
	public PwdRetrieveDTO getDto() {
		return dto;
	}
}
