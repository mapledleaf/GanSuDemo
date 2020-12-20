<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>友好提示</title>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<style type="text/css">
.error_body {
	margin: 0;
	padding: 0;
	font-size: 13px;
	font-family: Tahoma, Verdana, Arial, Helvetica, sans-serif;
	background: #fff;
}

.error_div {
	padding: 15px;
	text-align: center;
}

.error_table {
	line-height: 26px;
	border: 1px solid #b7c1ca;
	text-align: center;
	color: #555555;
	margin: auto;
	width: 99%;
	border-collapse: collapse;
	table-layout: fixed;
}

.error_row {
	background: #dde6ed;
	color: #1166bb;
	border: 1px solid #b7c1ca;
}

.error_col {
	text-align: center;
	color: #1166bb;
}

.error ul {
	list-style: none;
}
</style>
</head>
<body class="error_body">
	<div class="error_div">
		<table class="error_table">
			<tbody>
				<tr class="error_row">
					<td align="left">&nbsp;&nbsp;友好提示</td>
				</tr>
				<tr>
					<td class="error_col error">
						<%
							if (request.getAttribute("prompt_info") != null) {
								out.println("<ul><li><span>" + request.getAttribute("prompt_info") + "</span></li></ul>");
							} else {
								out.println("<ul><li><span>友好提示...</span></li></ul>");
							}
						%>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>