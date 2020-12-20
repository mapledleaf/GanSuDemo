<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<%
	String key = request.getParameter("key");
	if(key == null){
		key = "";
	}
	
	String data = com.powersi.sys.manager.util.MenuFlowUtil.getDataByKey(key);
	if(data == null || data.length() == 0){
		data = "{}";
	} else {
		data = data.trim();
	}
	
	String rects = com.powersi.sys.manager.util.MenuFlowUtil.getHistoryRectsByUrl(data);
	if(rects == null || rects.length() == 0){
		data = "[]";
	}
%>
<powersi:html>
<head>
<powersi:head title="菜单流程查看器" />
<%@include file="MenuFlowInclude.jsp" %>
</head>
<body>
	<div id="myflow"></div>
<powersi:errors />
<script type="text/javascript">
	$(function() {
		var key = "<%=key%>";
		var data = <%=data%>;
		var rects = <%=rects%>;
		
		var hisrects = [];
		if(rects && $.isArray(rects)){
			$.each(rects, function(n, obj){
				hisrects.push({'id': obj});
			});
		}
		
		if(data && data.states){
			$.each(data.states, function(key){
				var state = data.states[key];
				if(state){
					var disabled = false;
					if(!state.props || !state.props['url'] || !state.props["url"]["value"]){
						disabled = true;
					} else {
						$.each(hisrects, function(i, rect){
							if(rect["id"] === key){
								disabled = true;
								return false;
							}
						});
					}
					
					if(disabled){
						state.props['disabled'] = {value: true};
					}	
				}
			});
		}
		$('#myflow').myflow($.extend(true,{
			restore : data,
			editable: false,
			click: clickPoint
		}, {
			"historyRects": {"rects": hisrects}
		}
		));
	});
	
	function clickPoint(props, node){
		if(!props || !props["text"] || !props["url"]) return;
		
		if(props["disabled"]) return;
		if(powersi.length(props["url"]["value"]) == 0) return;
		
		openMenu(props["url"]["value"], props["param"]["value"]);
	}
</script>
</body>
</powersi:html>