<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<%@ page import="com.powersi.hygeia.framework.util.UtilFunc" %>
<%@ page import="com.powersi.hygeia.web.util.HtmlHelper"%>
<%@ page import="org.owasp.validator.html.*"%>
<%
	String error = "";
	String input = request.getParameter("input");
	if(UtilFunc.isEmpty(input) || !"POST".equals(request.getMethod())){
		input = "<h3>XSS开始</h3>\n" + 
				"<script>alert('XSS');</script>\n" + 
				"<iframe src=\"http://www.baidu.com\" width=\"100%\" height=\"200\" border=\"0\" style=\"padding:10px;\"></iframe>\n" + 
				"<a herf=\"https://www.baidu.com\" target=\"_blank\">外部页面</a>\n" + 
				"<a herf=\"xss.jsp\" target=\"_blank\">内部页面</a>\n" + 
				"<a href=\"javascript:alert('123');\">脚本页面</a>\n" + 
				"<h3>XSS结束</h3>";

		request.setAttribute("output", "");
	} else if("scan".equals(request.getParameter("type"))){
		CleanResults result = null;
		//扫描输入
		if(UtilFunc.hasText(request.getParameter("policy"))){
			result = HtmlHelper.scan(input, request.getParameter("policy"));
		} else{
			result = HtmlHelper.scan(input);
		}
		
		//检查结果
		if(result.getNumberOfErrors() > 0){
			//获取错误结果
			 error = UtilFunc.join(result.getErrorMessages(), "<br />");
			 //记录错误日志
			 HtmlHelper.writeScanLog(input, result);
		 }
		
		//输出结果
		String output = result.getCleanHTML();
		request.setAttribute("output", output);
	} else if("clean".equals(request.getParameter("type"))){
		//输出结果
		String output = null;
		if(UtilFunc.hasText(request.getParameter("policy"))){
			output = HtmlHelper.clean(input, request.getParameter("policy"));
		} else{
			output = HtmlHelper.clean(input);
		}
		request.setAttribute("output", output);
	}  else if("escape".equals(request.getParameter("type"))){
		//输出结果
		String output = HtmlHelper.escape(input);
		request.setAttribute("output", output);
	}  else if("unescape".equals(request.getParameter("type"))){
		//输出结果
		String output = HtmlHelper.unescape(input);
		request.setAttribute("output", output);
	}  else {
		 request.setAttribute("output", input);
	}
	
	request.setAttribute("error", error);
	
	System.out.println(UtilFunc.rightPad("/*", 100, "*"));
	System.out.println("input:");
	System.out.println(input);
	System.out.println("");
	System.out.println("output:");
	System.out.println(request.getAttribute("output"));
	System.out.println(UtilFunc.leftPad("*/", 100, "*"));
	System.out.println("");
%>
<powersi:html>
<head>
<powersi:head title="XSS示例" />
<style type="text/css">
textarea{
	height: 500px;
}
#output{
	height: 360px;
}
#error{
	height: 99px;
}
</style>
</head>
<body class="grid">
<div class="row">
	<div class="col-6">
		<powersi:form id="form1">
			<powersi:panelbox title="输入">
				<powersi:textarea id="input" name="input" maxlength="50000" value="<%=input %>" required="true"></powersi:textarea>
				<powersi:hidden id="type" name="type"></powersi:hidden>
				<powersi:hidden id="policy" name="policy"></powersi:hidden>
			</powersi:panelbox>
		</powersi:form>
	</div>
	<div class="col-6">
		<powersi:panelbox title="输出">
			<div id="output" class="divBorder">
				${output}
			</div>
		</powersi:panelbox>
		
		<powersi:panelbox title="错误(只有扫描才会显示)">
			<div id="error" class="divBorder textError">
				${error }
			</div>
		</powersi:panelbox>
	</div>
</div>
<div class="divButton">
	<powersi:button id="btn1" onclick="doHandle()" value="无安全处理" cssClass="btn btn-danger" buttonIcon="icon-ban-circle"></powersi:button>
	<div class="btn-group dropup">
		<powersi:button id="btn2" onclick="doHandle('scan')" value="自定义扫描" cssClass="btn btn-success" buttonIcon="icon-fa fa-crosshairs"></powersi:button>

		<button class="btn btn-success dropdown-toggle" aria-expanded="false" data-toggle="dropdown">
			<span class="icon-caret-down only-icon"></span>
		</button>
		<ul class="dropdown-menu dropdown-info pull-right">
			<li>
				<a href="javascript:doHandle('scan', 'anythinggoes')">anythinggoes(缺省)</a>
			</li>
			<li class="divider"></li>
			<li>
				<a href="javascript:doHandle('scan', 'ebay')">ebay</a>
			</li>
			<li>
				<a href="javascript:doHandle('scan', 'myspace')">myspace</a>
			</li>
			<li>
				<a href="javascript:doHandle('scan', 'slashdot')">slashdot</a>
			</li>
			<li>
				<a href="javascript:doHandle('scan', 'tinymce')">tinymce</a>
			</li>
		</ul>
	</div>
	<div class="btn-group dropup">
		<powersi:button id="btn2" onclick="doHandle('clean')" value="自定义清理" cssClass="btn btn-primary" buttonIcon="icon-fa fa-recycle"></powersi:button>

		<button class="btn btn-primary dropdown-toggle" aria-expanded="false" data-toggle="dropdown">
			<span class="icon-caret-down only-icon"></span>
		</button>
		<ul class="dropdown-menu dropdown-info pull-right">
			<li>
				<a href="javascript:doHandle('clean', 'anythinggoes')">anythinggoes(缺省)</a>
			</li>
			<li class="divider"></li>
			<li>
				<a href="javascript:doHandle('clean', 'ebay')">ebay</a>
			</li>
			<li>
				<a href="javascript:doHandle('clean', 'myspace')">myspace</a>
			</li>
			<li>
				<a href="javascript:doHandle('clean', 'slashdot')">slashdot</a>
			</li>
			<li>
				<a href="javascript:doHandle('clean', 'tinymce')">tinymce</a>
			</li>
		</ul>
	</div>
	<powersi:button id="btn3" onclick="doHandle('filter')" value="使用过滤器" cssClass="btn btn-info" buttonIcon="icon-filter"></powersi:button>
	<powersi:button id="btn3" onclick="doHandle('escape')" value="html编码" buttonIcon="icon-fa fa-arrow-circle-o-right"></powersi:button>
	<powersi:button id="btn3" onclick="doHandle('unescape')" value="html解码" buttonIcon="icon-fa fa-arrow-circle-o-left"></powersi:button>
</div>
<powersi:errors />
<script type="text/javascript">
function doHandle(type, policy){
	if(!checkForm('#form1')){
		return;
	}
	
	$('#type').val(type || '');
	$('#policy').val(policy || '');
	
	if(type == "filter"){
		var data = form2json('#form1');
		data['username'] = '测试用户p1';
		$.ajax({
            url: rootPath + '/sample/Sample!xssFilter.action',
            data: data,
            beforeSend: function(jqXHR){
            	jqXHR.setRequestHeader('loginuser', '<iframe src="http://www.baidu.com"></iframe>test');
            	jqXHR.setRequestHeader('username', encodeURIComponent('测试用户'));
            },
            dataType: "json",
            processData: true,
            type: "POST",
            global: false,
            error: showAjaxError,
            success: function (json, textStatus, jqXHR) {
            	popupSuccess(htmlEscape(powersi.tostring(json)), function(yes){
            		$('#output').html(json.data);	
            	});
            }
        });
	} else {
		$('#form1').attr('action', rootPath + "/pages/sample/webui/xss.jsp");
		$('#form1').submit();
	}
}
</script>
</body>
</powersi:html>