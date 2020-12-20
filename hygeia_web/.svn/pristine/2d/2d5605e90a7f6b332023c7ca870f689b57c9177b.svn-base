<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page
	import="com.powersi.hygeia.framework.util.UtilFunc,com.powersi.hygeia.web.util.WebHelper,com.powersi.hygeia.framework.BusiContext"%>
<%
	/*
	String showError = "false";
	if ("localhost".equals(request.getServerName()) || "127.0.0.1".equals(request.getServerName())
		|| "1".equals(UtilFunc.getString(BusiContext.getUserInfo(),
				"is_developer"))){
		showError = "true";
	}
	request.setAttribute("showError", showError);
	*/
	request.setAttribute("showError", "true");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>错误执行反馈</title>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<style type="text/css">
.error_body{margin:0; padding:0;font-size:13px;font-family: Tahoma, Verdana, Arial, Helvetica, sans-serif;background: #fff;}
.error_div{padding:15px;text-align:center;}
.error_table{line-height: 26px;border: 1px solid #b7c1ca;text-align: center;color: #555555;margin: auto;width:99%;border-collapse: collapse;table-layout:fixed;}
.error_row{background: #dde6ed;color: #1166bb;border: 1px solid #b7c1ca;}
.error_col{text-align: center;color: #1166bb;}
.error ul {list-style:none;}
</style>
</head>
<body class="error_body">
	<div class="error_div">
		<table class="error_table">
			<tbody>
				<tr class="error_row">
					<td align="left">&nbsp;&nbsp;系统错误</td>
				</tr>
				<tr>
					<td class="error_col error">
						<s:if test="#request.showError==\"true\"">
							<s:actionerror theme="simple" />
						</s:if>
						<s:else>
							<ul>
								<li>
									<span>
										系统出现错误，请稍候重试...
									</span>
								</li>
							</ul>
						</s:else>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>