<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false" %>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.powersi.hygeia.framework.ParameterMapping"%>
<%@page import="com.powersi.hygeia.framework.util.LogHelper"%>
<%@page import="com.powersi.hygeia.web.util.JsonHelper"%>
<%@page import="com.powersi.sys.util.MessageHelper"%>
<%
	int VIEW_SIZE = 5;
	String messageEnable = com.powersi.hygeia.framework.ParameterMapping.getStringByCode("message_flag", "0");
	String messageInterval = com.powersi.hygeia.framework.ParameterMapping.getStringByCode("message_query_interval", "180");

	try {
		request.setAttribute("bulletinList", JsonHelper
				.toJson(MessageHelper.getBulletinlList(VIEW_SIZE)));
	} catch (Exception ex) {
		LogHelper.getLogger().warn("获取公告列表出错", ex);
	}
	
	String serverDate = com.powersi.hygeia.framework.util.DateFunc.datetimeToString(new java.util.Date());
	
%>
<powersi:html>
<head>
<powersi:head title="欢迎页" />
<style type="text/css">
body {
	margin: 0;
	overflow: hidden;
	padding: 0;
}

#container {
	background: url(../resource/images/welcome-bg.jpg);
}

#main {
	background: url(../resource/images/welcome.jpg) no-repeat fixed top left;
	height: 600px;
	width: 1024px;
}

#bulletin {
	float: left;
	height: 175px;
	margin-left: 15px;
	margin-top: 145px;
	width: 330px;
}

#task {
	clear: both;
	height: 175px;
	margin-left: 15px;
	margin-top: 145px;
	width: 330px;
}

#info {
	text-align: center;
	color: #dd4b39;
	border-bottom: #dedede 1px solid;
	font-size: 1.2em;
}

.title {
	-o-text-overflow: ellipsis;
	overflow: hidden;
	text-align: left;
	text-overflow: ellipsis;
	white-space: nowrap;
	width: 100%;
	font-size: 1.1em;
}

.date {
	color: #999;
	text-align: right;
	font-size: 1em;
}

table {
	width: 100%;
	table-layout: fixed;
}

tr {
	height: 24px;
	line-height: 24px;
}
td a,td a:link,td a:visited {
	color: #039;
	cursor: pointer;
	text-decoration: none;
}

td a:hover,td a:active {
	text-decoration: underline;
}

td a.new:hover,td a.new:active {
	color: #c30;
}

td a.new:link,td a.new:visited,.new {
	color: #f60;
}
</style>
</head>
<body>
	<div id="container">
		<div id="main">
			<div id="bulletin">
				<div id="info">最新公告</div>
				<table id="news">
					<colgroup>
						<col width="240px"></col>
						<col width="90px"></col>
					</colgroup>
					<tbody>
					</tbody>
				</table>
			</div>
			<div id="task">
				<div id="info">待办事宜</div>
				<table id="tasks">
					<colgroup>
						<col width="240px"></col>
						<col width="90px"></col>
					</colgroup>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>
<script type="text/javascript">
//显示公告
function showBulletin(){
	postJSON("${rootPath}/message/BulletinManagerAction!getBulltin.action", null, 
			function(json) {
					if (!checkJSONResult(json)) {
						return;
					}														
				});
}





var _messageEnable = '<%=messageEnable%>' == '1';
var _messageInterval = parseFloat('<%=messageInterval%>');
//欢迎页不显示等待对话框
showRunningDelay = -1;

function viewBulletin(bulletinUrl){
	topDialog(rootPath + "/message/BulletinManagerAction!bulletinInfo.action?bulletinId=" + bulletinUrl, 600, 600);
}

function initBulletin() {
	try{
    	var a = [];
    	var str = '<%=request.getAttribute("bulletinList")%>';
    	var len = 0;
    	var isnew = false;
    	var date = null;
    	var dateFmt = "YYYY-MM-DD HH:mm:ss";
    	var serverdate = moment('<%=serverDate%>', dateFmt);
    	//注意不要直接操作serverdate，应新建以后操作
    	var newdate = moment(serverdate).add(-3, 'days').format(dateFmt);
    	var recentdate = moment(serverdate).add(-7, 'days').format(dateFmt);
		if(str !== 'undefined' && str !== "null" && str !== null && str.length > 0){
			var bulletins = eval("(" + str + ")");
			if(bulletins !== 'undefined' && bulletins !== null){
				 for (var idx in bulletins) {
		            var bulletin = bulletins[idx];
		            a.push("<tr>");
		            
		            date = moment(bulletin.audit_date, dateFmt);
		            isnew = date.format(dateFmt) >= newdate;
		            
		            a.push("<td class=\"title\">");
		            if(bulletin.bulletin_type == "1"){
		            	a.push("<a href=\"");
		            	a.push(bulletin.bulletin_url);
		            	a.push("\" target=\"_blank\" title=\"");
		            	a.push(bulletin.bulletin_title);
		            	a.push("\"");
		            	if(isnew){
		            		a.push(" class=\"new\"");
		            	}
		            	a.push(">");
		            	a.push(bulletin.bulletin_title);
		            	a.push("</a>");
		            } else {
		            	a.push("<a href=\"javascript:viewBulletin('");
		            	a.push(bulletin.bulletin_url);
		            	a.push("');\" title=\"");
		            	a.push(bulletin.bulletin_title);
		            	a.push("\"");
		            	if(isnew){
		            		a.push(" class=\"new\"");
		            	}
		            	a.push(">");
		            	a.push(bulletin.bulletin_title);
		            	a.push("</a>");
		            }
		            a.push("</td>");
		            
		            a.push("<td class=\"date\">");
		            
		            if(bulletin.audit_date >= recentdate){
		            	a.push('<span title="' + bulletin.audit_date + '"');
		            	 if(isnew){
		            		a.push(" class=\"new\"");
		            	} 
		            	a.push(">");
		            	a.push(date.from(serverdate));
		            	a.push("</span>");
		            } else{
		            	a.push('<span title="' + bulletin.audit_date + '">');
		            	a.push(date.format("YYYY/MM/DD"));
		            	a.push("</span>");
		            }
		           
		            a.push("</td>");
		            
		            a.push("</tr>");
		            
		            len ++;
		            if(len >= <%=VIEW_SIZE%>){
						break;
					}
				 }
			}
		}
		
		for(var i = (<%=VIEW_SIZE%> - len); i > 0; i --){
			a.push("<tr><td>&nbsp;</td><td>&nbsp;</td></tr>");
		}
		
		a.push("<tr><td class=\"title\">&nbsp;</td><td class=\"date\"><a href=\"javascript:openBulletinList();\"");
		a.push(">更多...</a></td></tr>");
    	$('#news tbody').html(a.join(""));
	} catch(e){
		alert(e.message);
	}
}

function getNewDate(n){
	var d = new Date(new Date() - 0 + n*86400000);
	var m = d.getMonth() + 1;
	var t = d.getDate();
	return d.getFullYear() + "/" + (m < 10 ? "0" + m : m) + "/" + (t < 10 ? "0" + t : t);
}

function openBulletinList() {
	openMenu('/message/BulletinManagerAction!list.action');
}

function initTask() {
	try{
		$.ajax({
	        url: rootPath + '/message/MessageAction!pollingTask.action?size=5',
	        data: null,
	        dataType: "json",
	        processData: true,
	        type: "POST",
	        global: false,
	        error: function(jqXHR, textStatus, errorThrown) {
	        	
	        },
	        success: function(ret, textStatus, jqXHR) {
	        	if(!checkResult(ret, false)){
					return;
				}
				
				var data = ret.data;
				var a = [];
				
				for(var i=0;i<5 && i<data.length;++i){
					a.push("<tr>");
		            
		            a.push("<td class=\"title\">");
	            	a.push('<a href="javascript:openMenu(\''+data[i].task_url+'\');">');
	            	a.push(data[i].task_name);
	            	a.push("</a>");
		            a.push("</td>");
		            
		            a.push("<td class=\"date\">");
		            
		            a.push(data[i].task_count+'条');	
		            
		            a.push("</td>");
		            
		            a.push("</tr>");
				}
				
				for(var i = (5 - data.length); i > 0; i --){
					a.push("<tr><td>&nbsp;</td><td>&nbsp;</td></tr>");
				}
				
				a.push("<tr><td class=\"title\">&nbsp;</td><td class=\"date\"><a href=\"javascript:openTaskList();\"");
				a.push(">更多...</a></td></tr>");
				
				$('#tasks tbody').html(a.join(""));
				
				 if(_messageEnable){
					if(_messageInterval < 10){
						_messageInterval = 10;
					}
					setTimeout("initTask()", _messageInterval * 1000);	
				} 
	        }
	  });
	} catch(e){
		alert(e.message);
	}
}

function openTaskList(){
	openMenu("/pages/sys/message/TaskList.jsp");
}

$(function() {
	//initBulletin();
	//initTask();
	//showBulletin();
});
</script>
</body>
</powersi:html>