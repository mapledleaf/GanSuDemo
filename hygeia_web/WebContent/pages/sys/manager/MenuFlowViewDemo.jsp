<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<%
	String key = request.getParameter("key");
	if(key == null){
		key = "";
	}
%>
<powersi:html>
<head>
<powersi:head title="菜单流程查看器(demo)" />
<%@include file="MenuFlowInclude.jsp" %>
</head>
<body>
	<div id="myflow"></div>
<powersi:errors />
<script type="text/javascript">
	$(function() {
		var key = "<%=key%>";
		var data = {};
		var hisrects = [];
		var accrects = [];
		var hispaths = [];
		var accpaths = [];
		if(key == "demo1"){
			data = {states:{rect1:{type:"start",text:{"text":""},attr:{x:280,y:5,width:50,height:50},props:{"key":{value:""},"desc":{value:"流程开始"}}},rect2:{type:"state",text:{"text":"菜单1\n查询服务器日志"},attr:{x:245,y:102,width:120,height:50},props:{"key":{value:""},"desc":{value:"查询服务器日志"},"url":{value:"/manager/QuerySystemLog.action"},"param":{value:""}}},rect3:{type:"fork",text:{"text":"分支"},attr:{x:280,y:203,width:50,height:50},props:{"key":{value:""},"desc":{value:""}}},rect4:{type:"state",text:{"text":"菜单2\n登录日志查询"},attr:{x:71,y:301,width:120,height:50},props:{"key":{value:""},"desc":{value:"登录日志查询"},"url":{value:"/manager/QueryLoginLog.action"},"param":{value:""}}},rect5:{type:"state",text:{"text":"菜单3\n操作日志查询"},attr:{x:244,y:301,width:120,height:50},props:{"key":{value:""},"desc":{value:"操作日志查询"},"url":{value:"/manager/QueryOperateLog.action"},"param":{value:""}}},rect6:{type:"state",text:{"text":"菜单4\n查询系统日志"},attr:{x:414,y:301,width:120,height:50},props:{"key":{value:""},"desc":{value:"查询系统日志"},"url":{value:"/manager/QuerySystemLog!query.action"},"param":{value:""}}},rect7:{type:"join",text:{"text":"合并"},attr:{x:280,y:397,width:50,height:50},props:{"key":{value:""},"desc":{value:""}}},rect8:{type:"end",text:{"text":""},attr:{x:281,y:579,width:50,height:50},props:{"key":{value:""},"desc":{value:"流程结束"}}},rect9:{type:"state",text:{"text":"菜单5\n登录日志统计"},attr:{x:245,y:490,width:120,height:50},props:{"key":{value:""},"desc":{value:"登录日志统计"},"url":{value:"/manager/LoginLogStatis.action"},"param":{value:""}}},rect10:{type:"end-cancel",text:{"text":"取消"},attr:{x:564,y:102,width:50,height:50},props:{"key":{value:""},"desc":{value:""}}},rect11:{type:"end-error",text:{"text":"错误"},attr:{x:564,y:490,width:50,height:50},props:{"key":{value:""},"desc":{value:""}}},rect12:{type:"text",text:{"text":"系\n统\n日\n志\n管\n理\n流\n程\n图","font-size":"18","font-weight":"700"},attr:{x:9,y:75,width:51,height:401},props:{"key":{value:""},"desc":{value:""},"url":{value:""},"param":{value:""}}},rect13:{type:"caption",text:{"text":"需要管理员身份"},attr:{x:71,y:102,width:120,height:50},props:{"key":{value:""},"desc":{value:""},"url":{value:""},"param":{value:""}}},rect14:{type:"task",text:{"text":"登录时间段统计"},attr:{x:74,y:490,width:120,height:50},props:{"key":{value:""},"desc":{value:""},"url":{value:""},"param":{value:""}}}},paths:{path15:{from:"rect1",to:"rect2",dots:[],text:{"text":"TO 菜单1"},textPos:{x:37,y:-1}, props:{"key":{value:""}}},path16:{from:"rect2",to:"rect3",dots:[],text:{"text":"TO 分支"},textPos:{x:33,y:-1}, props:{"key":{value:""}}},path17:{from:"rect5",to:"rect7",dots:[],text:{"text":"TO 合并"},textPos:{x:33,y:0}, props:{"key":{value:""}}},path18:{from:"rect7",to:"rect9",dots:[],text:{"text":"TO 菜单5"},textPos:{x:36,y:-5}, props:{"key":{value:""}}},path19:{from:"rect9",to:"rect8",dots:[],text:{"text":"TO 结束"},textPos:{x:32,y:0}, props:{"key":{value:""}}},path20:{from:"rect3",to:"rect4",dots:[{x:131,y:228}],text:{"text":"TO 菜单2"},textPos:{x:26,y:-15}, props:{"key":{value:""}}},path21:{from:"rect4",to:"rect7",dots:[{x:131,y:422}],text:{"text":"TO 合并"},textPos:{x:22,y:13}, props:{"key":{value:""}}},path22:{from:"rect3",to:"rect6",dots:[{x:474,y:228}],text:{"text":"TO 菜单4"},textPos:{x:-31,y:-14}, props:{"key":{value:""}}},path23:{from:"rect6",to:"rect7",dots:[{x:474,y:423}],text:{"text":"TO 合并"},textPos:{x:-24,y:15}, props:{"key":{value:""}}},path24:{from:"rect10",to:"rect2",dots:[],text:{"text":"TO 菜单1"},textPos:{x:-6,y:-14}, props:{"key":{value:""}}},path25:{from:"rect9",to:"rect11",dots:[],text:{"text":"TO 错误"},textPos:{x:-2,y:-14}, props:{"key":{value:""}}},path26:{from:"rect6",to:"rect10",dots:[{x:589,y:327}],text:{"text":"TO 取消"},textPos:{x:37,y:-98}, props:{"key":{value:""}}},path27:{from:"rect11",to:"rect6",dots:[{x:589,y:327}],text:{"text":"TO 菜单4\n查询系统日志"},textPos:{x:47,y:107}, props:{"key":{value:""}}},path28:{from:"rect3",to:"rect5",dots:[],text:{"text":"TO 菜单3"},textPos:{x:37,y:0}, props:{"key":{value:""}}},path29:{from:"rect13",to:"rect2",dots:[],text:{"text":""},textPos:{x:-1,y:-67}, props:{"key":{value:""}}},path30:{from:"rect14",to:"rect9",dots:[],text:{"text":""},textPos:{x:-18,y:-55}, props:{"key":{value:""}}}},props:{props:{key:{value:"menu-demo1"},name:{value:"菜单流程演示1"},desc:{value:"演示菜单流程的设计和显示"},width:{value:"700"},height:{value:"650"}}}};
			hisrects = [{"id": "rect2"}, {"id": "rect9"}];
			//hispaths = [{"id": "path13"}, {"id": "path17"}, {"id": "path19"}];
			hispaths = [{"from": "rect3", "to": "rect3" }];
			
			accrects = [{"id": "rect6"}];
			accpaths = [{"to": "rect10"}, {"from":"rect11"}];
		} else if(key == "demo2"){
			data = {states:{rect1:{type:"start",text:{"text":""},attr:{x:11,y:86,width:50,height:50},props:{"key":{value:""},"desc":{value:""}}},rect2:{type:"fork",text:{"text":""},attr:{x:260,y:86,width:50,height:50},props:{"key":{value:""},"desc":{value:""}}},rect3:{type:"state",text:{"text":"公告管理"},attr:{x:109,y:86,width:100,height:50},props:{"key":{value:""},"desc":{value:"新建、编辑公告"},"url":{value:"/message/BulletinManagerAction.action"},"param":{value:""}}},rect4:{type:"task",text:{"text":"新建中心公告"},attr:{x:327,y:9,width:100,height:50},props:{"key":{value:""},"desc":{value:""},"url":{value:""},"param":{value:""}}},rect5:{type:"task",text:{"text":"新建医院公告"},attr:{x:324,y:168,width:100,height:50},props:{"key":{value:""},"desc":{value:""},"url":{value:""},"param":{value:""}}},rect6:{type:"state",text:{"text":"公告审核"},attr:{x:537,y:87,width:100,height:50},props:{"key":{value:""},"desc":{value:"审核公告"},"url":{value:"/message/BulletinManagerAction!auditList.action"},"param":{value:""}}},rect7:{type:"state",text:{"text":"公告审签"},attr:{x:697,y:87,width:100,height:50},props:{"key":{value:""},"desc":{value:"审签公告"},"url":{value:"/message/BulletinManagerAction!querySecondAuditList.action"},"param":{value:""}}},rect8:{type:"end",text:{"text":""},attr:{x:845,y:88,width:50,height:50},props:{"key":{value:""},"desc":{value:""}}},rect9:{type:"text",text:{"text":"系统公告管理流程图","font-size":18,"font-weight":"700"},attr:{x:33,y:243,width:844,height:30},props:{"key":{value:""},"desc":{value:""},"url":{value:""},"param":{value:""}}},rect10:{type:"join",text:{"text":""},attr:{x:433,y:87,width:50,height:50},props:{"key":{value:""},"desc":{value:""}}}},paths:{path11:{from:"rect4",to:"rect10",dots:[{x:458,y:36}],text:{"text":""},textPos:{x:0,y:-10}, props:{"key":{value:""}}},path12:{from:"rect5",to:"rect10",dots:[{x:458,y:194}],text:{"text":""},textPos:{x:45,y:1}, props:{"key":{value:""}}},path13:{from:"rect1",to:"rect3",dots:[],text:{"text":""},textPos:{x:0,y:-10}, props:{"key":{value:""}}},path14:{from:"rect3",to:"rect2",dots:[],text:{"text":""},textPos:{x:0,y:-10}, props:{"key":{value:""}}},path15:{from:"rect2",to:"rect4",dots:[{x:285,y:33}],text:{"text":""},textPos:{x:37,y:12}, props:{"key":{value:""}}},path16:{from:"rect2",to:"rect5",dots:[{x:285,y:192}],text:{"text":""},textPos:{x:0,y:-10}, props:{"key":{value:""}}},path17:{from:"rect10",to:"rect6",dots:[],text:{"text":""},textPos:{x:0,y:-10}, props:{"key":{value:""}}},path18:{from:"rect6",to:"rect7",dots:[],text:{"text":""},textPos:{x:0,y:-10}, props:{"key":{value:""}}},path19:{from:"rect7",to:"rect8",dots:[],text:{"text":""},textPos:{x:0,y:-10}, props:{"key":{value:""}}}},props:{props:{key:{value:"menu-demo2"},name:{value:"菜单流程演示2"},desc:{value:"演示菜单流程的设计和显示"},width:{value:"900"},height:{value:"300"}}}};
		}
		
		if(data && data.states){
			$.each(data.states, function(key){
				var state = data.states[key];
				if(state){
					var disabled = false;
					$.each(hisrects, function(i, rect){
						if(rect["id"] === key){
							disabled = true;
							return false;
						}
					});
					
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
			"historyRects": {"rects": hisrects, "paths": hispaths}, 
			"activeRects":{"rects":accrects, "paths": accpaths}
			}
		));
	});
	
	function clickPoint(props, node){
		if(!props || !props["text"] || !props["url"]) return;
		
		if(props["disabled"]) return;
		if(powersi.length(props["url"]["value"]) == 0) return;
		//alert(powersi.tostring(props["url"]["value"]));
		alert(node.toJson());
	}
</script>
</body>
</powersi:html>