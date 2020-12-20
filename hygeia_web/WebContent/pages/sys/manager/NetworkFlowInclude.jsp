<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="${strutsPath}/js/plugins/myflow/raphael.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${strutsPath}/js/plugins/myflow/myflow.min.js?v=20141030" type="text/javascript" charset="utf-8"></script>
<script src="${strutsPath}/js/plugins/myflow/myflow.network.min.js?v=20141030" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
function setFlowState(state) {
	var n = state % 3;
	var pathAttr = $.myflow.pathAttr(n);
	var rectAttr = $.myflow.rectAttr(n);
	$.each($.myflow.config.pathArray, function(id, path){
		path.attr(pathAttr);
	});
	
	var folder = '/48/';
	if(n == 1){
		folder = '/48/history/';
	} else if(n == 2){
		folder = '/48/active/';
	}
	$.each($.myflow.config.rectArray, function(id, rect){
		var type = rect.getNode().type;
		if(type.indexOf("server") == 0){
			rect.attr(rectAttr);
			var src = $.myflow.config.tools.states[type].img.src;
			rect.getImageObj().attr({'src': $.myflow.config.basePath + src.replace('/48/', folder)});	
		}
	});
}

function checkNetwork(){
	setFlowState(0);
	
	$.each($.myflow.config.rectArray, function(id, rect){
		var type = rect.getNode().type;
		var props = rect.getNode().props;
		if(type.indexOf("server") == 0 && powersi.length(props['url'].value) > 0){
			checkServer(rect);
		}
	});
}

function checkServer(rect) {
	var url = rect.getNode().props['url'].value;
	if(url == null || url.length == 0){
		return;
	}
	
	if(url.charAt(0) == '/'){
		url = rootPath + url;
	}
	
	rect.getImageObj().attr({'src': $.myflow.config.basePath + 'img/48/loading.gif'});
	
	$.ajax({
        url: url,
        dataType: "json",
        processData: true,
        type: "POST",
        global: false,
        cache: false,
        timeout: 10000,
        error: function (jqXHR, textStatus, errorThrown) {
        	setServerState(rect, false);
        },
        success: function (json, textStatus, jqXHR) {
        	setServerState(rect, checkResult(json, false));
        }
    });
}

function setServerState(rect, flag){
	var n = flag ? 1: 2;
	var pathAttr = $.myflow.pathAttr(n);
	var rectAttr = $.myflow.rectAttr(n);
	
	var folder = '/48/';
	if(n == 1){
		folder = '/48/history/';
	} else if(n == 2){
		folder = '/48/active/';
	}
	
	rect.attr(rectAttr);
	
	var imgAttr = $.extend({}, $.myflow.config.tools.states[rect.getNode().type].img);
	imgAttr.src = $.myflow.config.basePath + imgAttr['src'].replace('/48/', folder);
	rect.getImageObj().attr(imgAttr);
	
	var r = rect.getId();
	$.each($.myflow.config.pathArray, function(id, obj){
		if(obj.to().getId() == r){
			obj.attr(pathAttr);
		}
	});
}

var flowData = {
	'demo': {states:{rect1:{type:"server-web",text:{"text":"Web服务器"},attr:{x:148,y:6,width:80,height:120},props:{"key":{value:""},"desc":{value:""},"url":{value:"/manager/DiagnoseManager!checkUrl.action"},"param":{value:""}}},rect2:{type:"server-app",text:{"text":"应用服务器"},attr:{x:359,y:6,width:80,height:120},props:{"key":{value:""},"desc":{value:""},"url":{value:"/manager/DiagnoseManager!checkUrl.action?url=http://www.powersi.com.cn:7009/webui/ProcessAll"},"param":{value:""}}},rect3:{type:"server-app",text:{"text":"应用服务器"},attr:{x:148,y:158,width:80,height:120},props:{"key":{value:""},"desc":{value:""},"url":{value:"/manager/DiagnoseManager!checkServer.action?url=https://www.google.com.hk"},"param":{value:""}}},rect4:{type:"server-print",text:{"text":"打印服务器"},attr:{x:359,y:158,width:80,height:120},props:{"key":{value:""},"desc":{value:""},"url":{value:"/manager/DiagnoseManager!checkServer.action?url=http://www.powersi.com.cn:7008/"},"param":{value:""}}},rect5:{type:"server-realtime",text:{"text":"实时服务器"},attr:{x:148,y:294,width:80,height:120},props:{"key":{value:""},"desc":{value:""},"url":{value:"/manager/DiagnoseManager!checkUrl.action"},"param":{value:""}}},rect6:{type:"server-mail",text:{"text":"邮件服务器"},attr:{x:359,y:293,width:80,height:120},props:{"key":{value:""},"desc":{value:""},"url":{value:"/manager/DiagnoseManager!checkUrl.action"},"param":{value:""}}},rect7:{type:"server-proxy",text:{"text":"代理服务器"},attr:{x:359,y:446,width:80,height:120},props:{"key":{value:""},"desc":{value:""},"url":{value:"/manager/DiagnoseManager!checkUrl.action"},"param":{value:""}}},rect8:{type:"server-ftp",text:{"text":"FTP服务器"},attr:{x:359,y:585,width:80,height:120},props:{"key":{value:""},"desc":{value:""},"url":{value:"/manager/DiagnoseManager!checkUrl.action"},"param":{value:""}}},rect9:{type:"server-db",text:{"text":"数据库服务器"},attr:{x:560,y:6,width:80,height:120},props:{"key":{value:""},"desc":{value:""},"url":{value:"/manager/DiagnoseManager!checkDb.action"},"param":{value:""}}},rect10:{type:"server-moible",text:{"text":"移动服务器"},attr:{x:560,y:293,width:80,height:120},props:{"key":{value:""},"desc":{value:""},"url":{value:""},"param":{value:""}}},rect11:{type:"text",text:{"text":"文本"},attr:{x:904,y:261,width:110,height:30},props:{"key":{value:""},"desc":{value:""},"url":{value:""},"param":{value:""}}},rect12:{type:"text",text:{"text":"市\n级\n平\n台\n架\n构\n图","font-size":"24","font-weight":"600"},attr:{x:698,y:40,width:33,height:229},props:{"key":{value:""},"desc":{value:""},"url":{value:""},"param":{value:""}}},rect13:{type:"app",text:{"text":"应用"},attr:{x:15,y:314,width:80,height:80},props:{"key":{value:""},"desc":{value:""},"url":{value:""},"param":{value:""}}},rect14:{type:"user",text:{"text":"用户"},attr:{x:15,y:26,width:80,height:80},props:{"key":{value:""},"desc":{value:""},"url":{value:""},"param":{value:""}}},rect15:{type:"server-content",text:{"text":"内容服务器"},attr:{x:564,y:446,width:80,height:120},props:{"key":{value:""},"desc":{value:""},"url":{value:""},"param":{value:""}}},rect16:{type:"server-file",text:{"text":"文件服务器"},attr:{x:676,y:585,width:80,height:120},props:{"key":{value:""},"desc":{value:""},"url":{value:""},"param":{value:""}}},rect17:{type:"server-dir",text:{"text":"目录服务器"},attr:{x:560,y:585,width:80,height:120},props:{"key":{value:""},"desc":{value:""},"url":{value:""},"param":{value:""}}},rect18:{type:"server-manager",text:{"text":"管理服务器"},attr:{x:818,y:397,width:80,height:120},props:{"key":{value:""},"desc":{value:""},"url":{value:""},"param":{value:""}}},rect19:{type:"server-manager",text:{"text":"管理服务器"},attr:{x:672,y:293,width:80,height:120},props:{"key":{value:""},"desc":{value:""},"url":{value:""},"param":{value:""}}},rect20:{type:"server",text:{"text":"服务器"},attr:{x:675,y:446,width:80,height:120},props:{"key":{value:""},"desc":{value:""},"url":{value:""},"param":{value:""}}},rect21:{type:"box-solid",text:{"text":"中心机房","font-size":"14","font-weight":"700"},data:{"text-valign":"bottom"},attr:{x:316,y:295,width:166,height:444},props:{"key":{value:""},"desc":{value:""},"url":{value:""},"param":{value:""}}},rect22:{type:"box-dashed",text:{"text":"虚拟网络","font-size":"14","font-weight":"700"},data:{"text-valign":"bottom"},attr:{x:548,y:296,width:223,height:441},props:{"key":{value:""},"desc":{value:""},"url":{value:""},"param":{value:""}}}},paths:{path23:{from:"rect1",to:"rect2",dots:[],text:{"text":""},textPos:{x:0,y:-10}, props:{"key":{value:""}}},path24:{from:"rect2",to:"rect4",dots:[],text:{"text":""},textPos:{x:0,y:-10}, props:{"key":{value:""}}},path25:{from:"rect4",to:"rect5",dots:[],text:{"text":""},textPos:{x:0,y:-10}, props:{"key":{value:""}}},path26:{from:"rect5",to:"rect6",dots:[],text:{"text":""},textPos:{x:0,y:-10}, props:{"key":{value:""}}},path27:{from:"rect5",to:"rect7",dots:[],text:{"text":""},textPos:{x:0,y:-10}, props:{"key":{value:""}}},path28:{from:"rect5",to:"rect8",dots:[{x:187,y:645}],text:{"text":""},textPos:{x:0,y:-10}, props:{"key":{value:""}}},path29:{from:"rect1",to:"rect3",dots:[],text:{"text":""},textPos:{x:0,y:-10}, props:{"key":{value:""}}},path30:{from:"rect14",to:"rect1",dots:[],text:{"text":""},data:{"arrow-radius":"6"},attr:{"stroke-width":"4"},textPos:{x:0,y:-10}, props:{"key":{value:""}}},path31:{from:"rect13",to:"rect5",dots:[],text:{"text":""},data:{"arrow-radius":"6"},attr:{"stroke-width":"4"},textPos:{x:0,y:-10}, props:{"key":{value:""}}},path32:{from:"rect2",to:"rect9",dots:[],text:{"text":""},textPos:{x:0,y:-10}, props:{"key":{value:""}}},path33:{from:"rect21",to:"rect22",dots:[],text:{"text":""},data:{"arrow-radius":"0"},attr:{"stroke-width":"3","stroke-dasharray":". "},textPos:{x:0,y:-10}, props:{"key":{value:""}}}},props:{props:{key:{value:""},name:{value:"市级平台架构图"},desc:{value:""},width:{value:"800"},height:{value:"750"}}}}
};
</script>