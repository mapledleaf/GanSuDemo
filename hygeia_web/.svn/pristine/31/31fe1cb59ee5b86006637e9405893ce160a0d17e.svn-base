/*
 * Copyright 2016 Powersi. All rights reserved.
 */
package com.powersi.sys.login.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.powersi.hygeia.framework.BusiContext;
import com.powersi.hygeia.framework.GlobalContext;
import com.powersi.hygeia.framework.MenuRightMapping;
import com.powersi.hygeia.framework.ParameterMapping;
import com.powersi.hygeia.framework.UserInfo;
import com.powersi.hygeia.framework.entity.SysUserKind;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.CollectionHelper;
import com.powersi.hygeia.framework.util.LogHelper;
import com.powersi.hygeia.framework.util.LoginHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.hygeia.web.useragent.Browser;
import com.powersi.hygeia.web.useragent.UserAgent;
import com.powersi.hygeia.web.util.SessionHelper;
import com.powersi.sys.user.service.UserService;
import com.powersi.sys.util.MenuHelper;
import com.powersi.sys.util.MessageHelper;
import com.powersi.sys.util.SingleSignOnHelper;
import com.powersi.sys.util.UserKindHelper;
//import com.powersi.sys.util.VerifyCodeHelper;
import com.wf.captcha.Captcha;
import com.wf.captcha.GifCaptcha;

/**
 * The Class LoginAction.
 */
public class LoginAction extends BaseAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The input stream. */
	private transient ByteArrayInputStream inputStream = null;

	/** The user kind. */
	private String userKind;

	/** The user id. */
	private String loginUser;

	private String password;

	/** The login type. */
	private String loginType;

	/** The mibao. */
	private String mibao = null;

	/** The url. */
	private String url = null;

	/** The user service. */
	public static final UserService userService = getBean(UserService.class);

	/** Login. */
	private static final int LOGINUSER_EXPIRE = 365 * 24 * 60 * 60;

	/**
	 * Login.
	 * 
	 * @return the string
	 */
	public String login() {
		UserInfo userInfo = null;
		try {
			if (isGetRequest()) {
				return INPUT;
			}

			if (getParameter("password") == null) {
				return INPUT;
			}

			if (isEmpty(loginUser)) {
				if (isNotEmpty(userKind)) {
					renderError("用户名不能为空");
				}
				return INPUT;
			}

			if (isEmpty(userKind)) {
				renderError("用户类别不能为空");
				return INPUT;
			}

			if (ParameterMapping.getUseCheckCode() && !checkVerifyCode()) {
				renderError("输入验证码错误");
				return INPUT;
			}

			userInfo = new UserInfo();

			userInfo.put("user_id", "0");
			userInfo.put("user_kind", userKind);
			userInfo.put("login_user", loginUser);
			userInfo.put("password", password);
			userInfo.put("login_type", loginType);

			userInfo.setLoginAddress(getRemoteAddr());

			// 检查用户令牌
			String userToken = getUserToken();
			if (UtilFunc.hasText(userToken)) {
				if (!userToken.equals(createUserToken(userInfo))) {
					renderError("已经使用其他用户(" + getUserByToken(userToken) + ")登录");
					return INPUT;
				}
			}

			Cookie userCookie = new Cookie("LoginUser", URLEncoder.encode(userInfo.getLoginUser(), "UTF-8"));
			userCookie.setMaxAge("1".equals(getRequest().getParameter("saveLogin")) ? LOGINUSER_EXPIRE : 0);
			// userCookie.setSecure(true);
			//userCookie.setHttpOnly(true);
			userCookie.setPath(getRequest().getContextPath());
			getResponse().addCookie(userCookie);

			// 登录检查
			checkLogin(userInfo);

			SessionHelper.setUserInfo(getSession(), userInfo);
			LoginHelper.login(userInfo, LoginHelper.LOGIN_SUCCESS, "");

			// 单位用户缴费检查
			if (userInfo.isCorpUser() && "1".equals(ParameterMapping.getByCode("check_corppay", "0"))) {
				String serverName = getRequest().getServerName();
				boolean isOut = true;// 是否外网用户(127.0.0.1为了测试，认为是外网登录）
				if (serverName != null) {
					if (serverName.equals("localhost") || serverName.startsWith("192.")
							|| serverName.startsWith("172.")) {
						isOut = false;
					}
				}

				// 外网访问
				if (isOut) {
					boolean isPay = false;
					long nums = 0;
					Map pay = userService.queryCorpUserPay(UtilFunc.getString(userInfo, "user_id", "0"));
					if (isNotEmpty(pay)) {
						isPay = "1".equals(UtilFunc.getString(pay, "payed"));
						nums = Long.valueOf(UtilFunc.getString(pay, "per_number", "0"));
					}

					// 未缴费用户
					if (!isPay) {
						Set set = new HashSet();
						if (nums >= 1000) {
							set.add(Integer.valueOf(3));
							userInfo.put("role_id", "3");
						} else {
							set.add(Integer.valueOf(4));
							userInfo.put("role_id", "4");
						}
						userInfo.put("role_set", set);
					}
				}
			}

			// 初始化用户操作乡镇村社区权限数据配置
			//UserBizInfoUtil.initUserTownVillageRightConfig(userInfo);
						
			BusiContext.setUserInfo(userInfo);
			addUserInfo(userInfo);
			userService.confirmUserExist(userInfo);
			
			userInfo = BusiContext.getUserInfo();
			if (isNotEmpty(url)) {
				getResponse().sendRedirect(getRequest().getContextPath() + UtilFunc.decodeBase64URL(url));
				return NONE;
			} else {
				return ParameterMapping.getStringByCode("main_page", "success");
			}
			
		} catch (Exception ex) {
			if (ex instanceof HygeiaException) {
				renderError(ex.getMessage());
			} else {
				renderError(ex);
			}
			try {
				if (userInfo != null) {
					LoginHelper.login(userInfo, LoginHelper.LOGIN_FAILURE, ex.getMessage());
				}
			} catch (Exception logex) {
				LogHelper.getLogger().warn("保存登录信息出错", ex);
			}
			return INPUT;
		}
	}

	/**
	 * 登录检查.
	 * 
	 * @param userInfo
	 *            the user info
	 */
	protected void checkLogin(UserInfo userInfo) {
		// 登录检查
		userService.verifyLogin(userInfo);
	}

	/**
	 * 主界面.
	 * 
	 * @return the string
	 */
	public String main() {
		try {
			UserInfo user = getUserInfo();
			user.setLogoutDate(null);
			if (isEmpty(user) || !user.isValidUser()) {
				return LOGIN;
			}

			// 刷新菜单缓存
			MenuHelper.refreshCache();

			setAttribute("loginUser", user.get("login_user"));
			setAttribute("loginType", user.get("login_type"));
			setAttribute("loginId", String.valueOf(user.getLoginId()));
			setAttribute("userKind", user.getUserKind());
			setAttribute("userName", user.getUserName());
			setAttribute("menuRender", getMenuRender(user));
			setAttribute("userToken", UtilFunc.toHexString(
					String.format("%1$s|%2$s", user.getUserKind(), user.get("login_user")).toLowerCase().getBytes()));

			int flag = 0;
			String password = UtilFunc.getString(user, "password");
			// 判断密码是否修改
			if (!password.startsWith("{md5}")) {
				setAttribute("passwordReset", "1");
				flag++;
			}

			/*
			 * //密码保护问题 if ("1".equals(user.getUserKind())) { if
			 * (userDAO.getRetrieveQuestion( Long.valueOf(user.getUserId())) == null) {
			 * setAttribute("questionReset", "1"); flag++; } }
			 */

			if (flag > 0) {
				setAttribute("showPopup", "1");
			}

			renderBulletinList(user);

			SysUserKind userKind = UserKindHelper.get(user.getUserKind());
			if (userKind != null && isNotEmpty(userKind.getStartMenu())) {
				setAttribute("startMenu", userKind.getStartMenu());
			}

			boolean enabledBookmark = userService.supportBookmark();
			setAttribute("bookmarkEnabled", String.valueOf(enabledBookmark));
			if (enabledBookmark) {
				setAttribute("bookmarkList", userService.getBookmark());
			} else {
				setAttribute("bookmarkList", "");
			}

			return SUCCESS;
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * 获取菜单输出字符串.
	 * 
	 * @param user
	 *            the user
	 * @return the menu render
	 */
	public static String getMenuRender(UserInfo user) {
		String roleId = user.getRoleId();
		if (isEmpty(roleId)) {
			return "";
		}

		String menuRender = MenuRightMapping.getCacheMenuRender(roleId);
		if (menuRender != null) {
			return menuRender;
		}

		List menuList = userService.getRoleMenus(user.getRoleSet());

		if (isEmpty(menuList)) {
			menuRender = "";
		} else {
			StringBuilder str = new StringBuilder();
			int iSize = menuList.size();

			/*
			 * for (int i = 0; i < iSize; i++) { Map map = (Map) menuList.get(i); if
			 * (!UtilFunc.getString(map, "menu_up_id").equals("0")) continue;
			 * 
			 * 
			 * renderMenuTree(str, menuList, UtilFunc.getString(map, "menu_name"),
			 * UtilFunc.getString(map, "menu_id")); }
			 */
			str.append("[");
			for (int i = 0; i < iSize; i++) {
				Map map = (Map) menuList.get(i);
				if (i > 0) {
					str.append(",");
				}
				str.append("{");
				str.append("\"id\":").append(map.get("menu_id"));
				str.append(",\"pId\":").append(map.get("menu_up_id"));
				str.append(",\"name\":\"")
						.append(UtilFunc.replace(UtilFunc.getString(map, "menu_name", ""), "\"", "\\\\\""))
						.append("\"");
				str.append(",\"data\":\"").append(UtilFunc.replace(UtilFunc.getString(map, "url", ""), "\"", "\\\\\""))
						.append("\"");

				if (UtilFunc.hasText(UtilFunc.getString(map, "pic_name", ""))) {
					str.append(",\"img\":\"")
							.append(UtilFunc.replace(UtilFunc.getString(map, "pic_name", ""), "\"", "\\\\\""))
							.append("\"");
				}

				str.append("}");
			}
			str.append("]");

			menuRender = str.toString();
			if (menuRender.indexOf("'") >= 0) {
				menuRender = UtilFunc.replace(menuRender, "'", "\\'");
			}
		}

		MenuRightMapping.setCacheMenuRender(roleId, menuRender);
		return menuRender;
	}

	/**
	 * Render menu tree.
	 * 
	 * @param user
	 *            the user
	 */
	/*
	 * private void renderMenuTree(StringBuilder str, List menuList, String
	 * menuName, String menuId) { str.append(String
	 * .format("<div class=\"cls\" onclick=\"doSwitch(this,'f%1$s');\">%2$s</div>" ,
	 * menuId, menuName));
	 * str.append(String.format("<ul class=\"collapsed\" id=\"f%1$s\">", menuId));
	 * 
	 * int iSize = menuList.size(); for (int k = 0; k < iSize; k++) { Map map =
	 * (Map) menuList.get(k); if (!UtilFunc.getString(map,
	 * "menu_up_id").equals(menuId)) continue;
	 * 
	 * String url = UtilFunc.getString(map, "menu_url"); if (isEmpty(url)) {
	 * renderMenuTree(str, menuList, UtilFunc.getString(map, "menu_name"),
	 * UtilFunc.getString(map, "menu_id")); } else { str.append(String .format(
	 * "<li><a id=\"m%1$s\" onclick=\"doMenu('m%1$s', '%2$s');\" href=\"javascript:void(0);\">%3$s</a></li>"
	 * , UtilFunc.getString(map, "menu_id"), url, UtilFunc.getString(map,
	 * "menu_name"))); } }
	 * 
	 * str.append("</ul>"); }
	 */
	/*
	 * private void renderMenuTree(StringBuilder str, List menuList, String
	 * menuName, String menuId) {
	 * str.append(String.format("<li><span class=\"folder\">%1$s</span>",
	 * menuName)); str.append(String.format("<ul id=\"f%1$s\">", menuId));
	 * 
	 * int iSize = menuList.size(); for (int k = 0; k < iSize; k++) { Map map =
	 * (Map) menuList.get(k); if (!UtilFunc.getString(map,
	 * "menu_up_id").equals(menuId)) continue;
	 * 
	 * String url = UtilFunc.getString(map, "menu_url"); if (isEmpty(url)) {
	 * renderMenuTree(str, menuList, UtilFunc.getString(map, "menu_name"),
	 * UtilFunc.getString(map, "menu_id")); } else { str.append(String .format(
	 * "<li><a id=\"m%1$s\" onclick=\"javascript:doMenu('%1$s', '%2$s');\" data=\"%2$s\" title=\"%3$s\"><span class=\"file\">%3$s</span></a></li>"
	 * , UtilFunc.getString(map, "menu_id"), url, UtilFunc.getString(map,
	 * "menu_name"))); } }
	 * 
	 * str.append("</ul></li>"); }
	 */

	/**
	 * 获取公告输出字符串.
	 * 
	 * @param user
	 *            the user
	 * @return the menu render
	 */
	private void renderBulletinList(UserInfo user) {
		try {
			setAttribute("bulletinList", toJson(MessageHelper.getBulletinlList(15)));
			List msg = MessageHelper.queryMessageList(true);
			setAttribute("messageList", toJson(msg));
			if (CollectionHelper.isNotEmpty(msg)) {

			}
		} catch (Exception ex) {
			LogHelper.getLogger().warn("获取公告列表出错", ex);
		}
	}

	/**
	 * Login out.
	 * 
	 * @return the string
	 */
	public String logout() {
		try {
			String actionType = getParameter("action");
			String loginId = getParameter("loginid");
			// 注销必须从session获取用户信息
			UserInfo userInfo = SessionHelper.getUserInfo(getRequest());

			boolean removeUserFlag = true;
			if (UtilFunc.hasText(loginId) && !loginId.equals(UtilFunc.getString(userInfo, "login_id"))) {
				userInfo = new UserInfo();
				userInfo.setLoginId(Long.valueOf(loginId));
			}

			String resultType = null;
			if ("relogin".equalsIgnoreCase(actionType)) {// 重登录
				LoginHelper.logout(userInfo, LoginHelper.LOGOUT_RELOGIN);

				userKind = getParameter("userkind");
				if (isEmpty(userKind)) {
					userKind = "1";
				}
				loginUser = new String(getParameter("loginuser").getBytes("ISO-8859-1"), GlobalContext.getCharset());

				loginType = getParameter("logintype");

				resultType = isEmpty(loginType) ? SUCCESS : loginType;
			} else {
				if ("exit".equalsIgnoreCase(actionType)) {// 退出(自动关闭窗口)
					LoginHelper.logout(userInfo, LoginHelper.LOGOUT_USEREXIT);

					UserAgent userAgent = UserAgent.parseUserAgentString(getRequest().getHeader("User-Agent"));
					if (Browser.IE.equals(userAgent.getBrowser().getGroup())) {
						HttpServletResponse response = getResponse();
						response.setContentType("text/html;charset=GBK");
						PrintWriter out = response.getWriter();
						out.println("<html>");
						out.println("<head>");
						out.println("<title>退出 " + ParameterMapping.getSystemName() + "</title>");
						out.println("</head>");
						out.println(
								"<script>setTimeout(\"location.href='about:blank';window.opener=null;window.open('', '_self');window.close();\", 0)</script>");
						out.println("</html>");

						resultType = NONE;
					} else {
						userKind = getParameter("userkind");
						if (isEmpty(userKind)) {
							userKind = "1";
						}
						loginUser = new String(getParameter("loginuser").getBytes("ISO-8859-1"),
								GlobalContext.getCharset());

						loginType = getParameter("logintype");

						resultType = isEmpty(loginType) ? SUCCESS : loginType;
					}

				} else if ("unload".equalsIgnoreCase(actionType)) {// 页面退出
					LoginHelper.logout(userInfo, LoginHelper.LOGOUT_CLIENTCLOSE);
					removeUserFlag = false;

					resultType = NONE;
				} else {// 其他方式
					LoginHelper.logout(userInfo, LoginHelper.LOGOUT_CLIENTCLOSE);

					resultType = NONE;
				}
			}

			if (removeUserFlag) {
				removeUserInfo(userInfo);

				if (SingleSignOnHelper.isUsed() && SingleSignOnHelper.isEnabled()) {

					HttpSession session = getRequest().getSession(false);
					if (session != null) {
						session.invalidate();
					}
					// 单点登录注销处理
					if ("true".equals(getParameter("userbrowser"))) {
						getResponse().sendRedirect(SingleSignOnHelper.getExitUrl() + "?renew=true&service="
								+ URLEncoder.encode(
										SingleSignOnHelper.getServerName() + getRequest().getContextPath()
												+ "/login/login.htm?action=autologin&id=" + UtilFunc.getUUID(),
										"UTF-8"));
					} else {
						if ("exit".equalsIgnoreCase(actionType)) {
							getResponse().sendRedirect(SingleSignOnHelper.getExitUrl());
						} else {
							String mainPage = "/main.action";
							if ("desktop".equals(ParameterMapping.getStringByCode("main_page"))) {
								mainPage = "/pages/desktop.jsp";
							}
							getResponse()
									.sendRedirect(
											SingleSignOnHelper.getExitUrl() + "?renew=true&service="
													+ URLEncoder.encode(
															SingleSignOnHelper.getServerName()
																	+ getRequest().getContextPath() + mainPage,
															"UTF-8"));
						}
					}
					resultType = NONE;
				} else {
					if ("true".equals(getParameter("userbrowser"))) {
						getResponse().sendRedirect(
								getRequest().getContextPath() + "/login/login.htm?id=" + UtilFunc.getUUID());
						resultType = NONE;
					}
				}
			}

			return resultType;
		} catch (HygeiaException he) {
			renderError(he.getMessage(), he);
		} catch (Exception ee) {
			renderError(ee);
		}

		return NONE;
	}

	/**
	 * Creates the user token.
	 * 
	 * @param userInfo
	 *            the user info
	 * @return the string
	 */
	private String createUserToken(UserInfo userInfo) {
		return UtilFunc.toHexString(String.format("%1$s|%2$s",
				// userInfo.getUserKind(),
				"0", userInfo.get("login_user")).toLowerCase().getBytes());
	}

	/**
	 * Gets the user by token.
	 * 
	 * @param token
	 *            the token
	 * @return the user by token
	 */
	private String getUserByToken(String token) {
		String str = new String(UtilFunc.fromHexString(token));
		String[] s = UtilFunc.split(str, "|");
		if (s.length > 1) {
			return s[1];
		} else {
			return "";
		}
	}

	/**
	 * Gets the user token.
	 * 
	 * @return the user token
	 */
	private String getUserToken() {
		Cookie[] cookies = getRequest().getCookies();
		String cookieName = null;
		String cookieValue = null;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookieName = cookies[i].getName();
				if (cookieName == null) {
					continue;
				}

				if (cookieName.toUpperCase().equals("USERTOKEN")) {
					cookieValue = cookies[i].getValue();
					break;
				}
			}
		}

		return cookieValue;
	}

	/**
	 * 添加用户信息.
	 * 
	 * @param userInfo
	 *            the user info
	 */
	private void addUserInfo(UserInfo userInfo) {
		if (userInfo == null || !userInfo.isValidUser()) {
			throw new HygeiaException("无效的用户信息");
		}

		SessionHelper.setUserInfo(getRequest(), userInfo);

		String userToken = createUserToken(userInfo);
		Cookie tokenCookie = new Cookie("UserToken", userToken);
		tokenCookie.setMaxAge(-1);
		//tokenCookie.setSecure(true);
		//tokenCookie.setHttpOnly(true);
		tokenCookie.setPath(getRequest().getContextPath());
		getResponse().addCookie(tokenCookie);

		if (ParameterMapping.getUseLoginSession() && isNotEmpty(userInfo.get("user_indentity"))) {
			Cookie indentityCookie = new Cookie("UserIndentity", UtilFunc.getString(userInfo, "user_indentity"));
			indentityCookie.setMaxAge(-1);
			// indentityCookie.setSecure(true);
			indentityCookie.setPath(getRequest().getContextPath());
			getResponse().addCookie(indentityCookie);
		}
	}

	/**
	 * Removes the user info.
	 * 
	 * @param userInfo
	 *            the user info
	 */
	private void removeUserInfo(UserInfo userInfo) {
		Cookie tokenCookie = new Cookie("UserToken", "");
		tokenCookie.setMaxAge(0);
		//tokenCookie.setSecure(true);
		tokenCookie.setPath(getRequest().getContextPath());
		getResponse().addCookie(tokenCookie);

		Cookie indentityCookie = new Cookie("UserIndentity", "");
		indentityCookie.setMaxAge(0);
		indentityCookie.setPath(getRequest().getContextPath());
		// indentityCookie.setSecure(true);
		getResponse().addCookie(indentityCookie);

		SessionHelper.removeUserInfo(getRequest());
	}

	/**
	 * Verify code.
	 * 
	 * @return the string
	 */
	/*
	@Action(value = "/login/verifyCode", results = { @Result(name = "success", type = "stream", params = {
			"contentType", "image/jpeg", "inputName", "inputStream" }) })
	public String verifyCode() {
		try {
			VerifyCodeHelper helper = VerifyCodeHelper.getInstance();
			String verifyCode = helper.getVerifyCode();
			this.setInputStream(helper.getVerfyImage(verifyCode));
			setSession(SessionHelper.SESSION_VERIFYCODE, verifyCode);
			return SUCCESS;
		} catch (Exception ee) {
			return renderError(ee);
		}
	}
	*/
	
	@Action(value = "/login/verifyCode", results = { @Result(name = "success", type = "stream", params = {
			"contentType", "image/gif", "inputName", "inputStream" }) })
	public String verifyCode() {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
			// 三个参数分别为宽、高、位数
			GifCaptcha gifCaptcha = new GifCaptcha(130, 48, 4);

			// 设置类型，纯数字、纯字母、字母数字混合
			gifCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);

			// 生成的验证码
			String verifyCode = gifCaptcha.text();

			// 保存验证码
			setSession(SessionHelper.SESSION_VERIFYCODE, verifyCode);

			// 输出图片流
			gifCaptcha.out(output);

			// 设置返回
			this.setInputStream(new ByteArrayInputStream(output.toByteArray()));
			return SUCCESS;
		} catch (Exception ee) {
			return renderError(ee);
		} finally {
			UtilFunc.close(output);
		}
	}

	/**
	 * Check verify code.
	 * 
	 * @return true, if successful
	 */
	private boolean checkVerifyCode() {
		try {
			String inputValue = getParameter("verifycode");
			String saveValue = getSession(SessionHelper.SESSION_VERIFYCODE, "").toString();
			if (isEmpty(inputValue) || !inputValue.equalsIgnoreCase(saveValue)) {
				return false;
			} else {
				return true;
			}
		} finally {
			setSession(SessionHelper.SESSION_VERIFYCODE, null);
		}
	}

	/**
	 * Gets the input stream.
	 * 
	 * @return the input stream
	 */
	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}

	/**
	 * Sets the input stream.
	 * 
	 * @param inputStream
	 *            the new input stream
	 */
	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}

	/**
	 * Gets the user kind.
	 * 
	 * @return the user kind
	 */
	public String getUserKind() {
		return userKind;
	}

	/**
	 * Sets the user kind.
	 * 
	 * @param userKind
	 *            the new user kind
	 */
	public void setUserKind(String userKind) {
		this.userKind = userKind;
	}

	/**
	 * Gets the login user.
	 * 
	 * @return the login user
	 */
	public String getLoginUser() {
		return loginUser;
	}

	/**
	 * Sets the login user.
	 * 
	 * @param loginUser
	 *            the new login user
	 */
	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	/**
	 * Gets the password.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 * 
	 * @param password
	 *            the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Sets the login type.
	 * 
	 * @param loginType
	 *            the new login type
	 */
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	/**
	 * Gets the login type.
	 * 
	 * @return the login type
	 */
	public String getLoginType() {
		return loginType;
	}

	/**
	 * Sets the mibao.
	 * 
	 * @param mibao
	 *            the new mibao
	 */
	public void setMibao(String mibao) {
		this.mibao = mibao;
	}

	/**
	 * Gets the mibao.
	 * 
	 * @return the mibao
	 */
	public String getMibao() {
		return mibao;
	}

	/**
	 * Gets the url.
	 * 
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Sets the url.
	 * 
	 * @param url
	 *            the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}
