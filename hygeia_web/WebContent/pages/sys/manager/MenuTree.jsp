<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<%
	String systemName = com.powersi.hygeia.framework.ParameterMapping.getSystemName();
%>
<powersi:html>
<head>
<powersi:head title="查看菜单权限" target="_self"/>
<script src="${rootPath}/resource/js/jquery.quickfilter.js" type="text/javascript"></script>
<script src="${strutsPath}/include/ztree.js" type="text/javascript"></script>
<style type="text/css">
html,body{overflow: hidden;}
#divTree{overflow-x: auto;overflow-y: scroll;text-align:left;white-space:nowrap;}
</style>
<script type="text/javascript">
var SYSTEM_NAME = "<%=systemName%>";

var _menuList = [];

var _menuFilter = null;
var _lastFind = null;
var _nodeList = [];

var userName;
var loginUser;
var roles;
var treeCheck;
$(document).ready(function() {
	fitHeight('divTree', 120);
	
	$('#tvMenu').quickfilter({
    	srcElement: '',
        attached: '#divFilter',
        stripeRowClass: null,
        inputText: '查找 菜单',
        inputTitle: '输入关键字后，按回车键查找符合条件的数据',
        labelText: '',
        filter: function(i){
        	return "";
        },
        onAfter: function(){
        	try{
        		if(_menuFilter){
            		var txt = powersi.trim(_menuFilter.val());
            		var zTree = $.fn.zTree.getZTreeObj("tvMenu");
            		var len = powersi.length(_nodeList);
            		if(_lastFind !== txt) {
            			_lastFind = txt;
            			
            			for( var i = 0; i < len; i++) {
            				_nodeList[i].highlight = false;
            				zTree.updateNode(_nodeList[i]);
            			}
                		_nodeList.length = 0;
                		
                		if(txt.length > 0){
                			_nodeList = zTree.getNodesByParamFuzzy("name", txt, null);
                		}
                		
                		len = powersi.length(_nodeList);
                		for( var i = 0; i < len; i ++) {
                			_nodeList[i].highlight = true;
	            			zTree.updateNode(_nodeList[i]);
	            		}
            		}
            		
            		if(txt.length == 0){
            			return;
            		}
            		
               		if(len > 0){
               			var sel = 0;
               			if(len > 1){
               				var curNodes = zTree.getSelectedNodes();
                   			if(curNodes.length > 0){
                   				var curIdx =  getTreeOrder("tvMenu", curNodes[0]);
                   				for(var i = 0; i < len; i ++){
                   					var idx = getTreeOrder("tvMenu", _nodeList[i]);
                   					if(idx > curIdx){
                   						sel = i;
                   						break;
                   					}
                   				}
                   			}
               			}
                   		
               			var node = _nodeList[sel];
       					if(node.isParent){
       						zTree.expandNode(node, true, false, true, false);	
       					} else {
	            			zTree.expandNode(node.getParentNode(), true, false, true, false);
	            		}
       					
       					zTree.selectNode(node, false);
	                		
	                	_menuFilter.focus();
               		} else {
               			alert('没有找到"' + txt + '"对应的菜单。');
               		}
            	}
        	} catch(e){
        		alert(e.message);
        	}
		}
    });
	
	$('#divFilter input').each(function(){
		_menuFilter = $(this);
		$(this).css({width: "150px"});
		return false;
	});
	
	var param = getDialogParam();
	if(param == null || !powersi.isvalue(param.roles)){
		var rsMap = '<%=request.getAttribute("rsMap")%>';
		if(powersi.isvalue(rsMap)){
			param = powersi.tojson(rsMap) ;
		} else {
			param = {'roles' : '-1', 'rolenames': ''};
		}
	} else {
		if(param.tip){
			$('#divOnlyMenu').html(param.tip);
		}
		
		if(param.check){
			treeCheck = param.check;
			$('#btExport').hide();
			$('#btSelect').show();
		}
		
		if(param.title){
			document.title = param.title;
		}
	}
	
	$('#roleName').val(param.rolenames || '');
	$('#userName').text(param.staff_name || '');

	userName = param.staff_name || '';
	loginUser = param.login_user || '';
	roles = param.roles || '';
	
	$('#btClose').focus();
	
	postJSON(rootPath + "/manager/MenuManager!getMenuTreeByRoles.action", {'roles' : param.roles}, showMenuTree);
});

function showMenuTree(json){
    if(!checkJSONResult(json)){
    	closeDialog();
	    return;
    }

    _menuList.length = 0;
    _menuList = json.data;
    
    buildeMenuTree(_menuList);
}

function buildeMenuTree(menus) {
    try{
    	var root = -1;
    	var trees = [];
    	
    	trees.push({"id": 0, "pId": root, "name": SYSTEM_NAME, "open": true, "font": {"color": "#f00"}, "iconSkin" : "system"});
    	
    	var onlyMenu = $('#cbxOnlyMenu').prop('checked');
    	
        if (powersi.isarray(menus)) {
            for (var idx in menus) {
                var menu = menus[idx];
				
                if(onlyMenu && menu.menu_type != "1"){
                	continue;
                }
                
                var treeNode = {"id": menu.menu_id, "pId": menu.menu_up_id, "name": menu.menu_name};
                if(menu.menu_type != "1"){
                	treeNode["font"] = {"color": "#00f", "background-color":"transparent"};
                	treeNode["iconSkin"] = "right";
                } else {
                	treeNode["iconSkin"] = "menu";
                }
                trees.push(treeNode);
            }//end for menus
        }
        
        var setting = {
    			view: {
    				fontCss: getTreeFont,
    				autoCancelSelected: false,
    				selectedMulti: false,
    				dblClickExpand: false,
    				showLine: false
    			},
    			data: {
    				simpleData: {
    					enable: true,
    					rootPId: root
    				}
    			},
				callback: {
					onClick: onTreeClick
				}
    	};
        
        if(treeCheck){
        	setting['check'] = treeCheck;
        }
        
        $.fn.zTree.init($("#tvMenu"), setting, trees);
        trees.length = 0;
    } catch(e){
    	alert(e.message);
    }
}

function onTreeClick(event, treeId, treeNode, clickFlag) {
	expandNode(treeId, treeNode);
}

function expandNode(treeId, treeNode){
	var zTree = $.fn.zTree.getZTreeObj(treeId);
	zTree.expandNode(treeNode);
}

function expandAll(expandFlag){
	var zTree = $.fn.zTree.getZTreeObj("tvMenu");
	zTree.expandAll(expandFlag);
}

function hideNodes(hideFlag) {
	var zTree = $.fn.zTree.getZTreeObj("tvMenu");
	if(hideFlag){
		var nodes = zTree.getNodesByParam("iconSkin", "right", null);
		zTree.hideNodes(nodes);
	} else {
		var nodes = zTree.getNodesByParam("isHidden", true);
		zTree.showNodes(nodes);
	}
}

function doOnlyMenu(){
	//仅仅隐藏不能解决图标显示问题
	//hideNodes(cbx.checked);
	
	_nodeList.length = 0;
	$.fn.zTree.destroy("tvMenu");
	
	buildeMenuTree(_menuList);
}

function doSelect() {
	var nodes = $.fn.zTree.getZTreeObj("tvMenu").getCheckedNodes(true);
	closeDialog(powersi.tostring(nodes));
}

function doClose(){
	closeDialog();
}

function exportXls() {
	postDownload(rootPath + "/manager/MenuManager!exportMenuTreeByRoles.action", {
		'loginUser': loginUser,
		'userName': userName,
		'roles': roles
	});
}
</script>
</head>
<body>
	<div class="row">
		<div class="col-12" style="margin: 10px 0 5px 0;">
			<input type="text" id="roleName" title="角色列表" class="text textInfo" readonly="readonly"></input>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<div id="divTree" title="蓝色表示权限，黑色表示菜单" class="divBorder">
				<ul id="tvMenu" class="ztree">
				</ul>
			</div>
		</div>
	</div>
	<div class="space"></div>
	<div class="row">
		<div class="col-12">
			<div class="floatLeft">
				<div id="divFilter">
				</div>
			</div>
			<div class="floatRight" id="divOnlyMenu">
				<input type="checkbox" id="cbxOnlyMenu" class="checkbox" onclick="doOnlyMenu()"> </input>
				<label class="checkboxLabel textError" for="cbxOnlyMenu" title="隐藏权限">只显示菜单</label>
			</div>
		</div>
	</div>
	<div class="space"></div>
	<div class="divButton">
		<div class="fixedLeft">
			<span id="userName" title="用户名" class="textInfo"></span>
		</div>
		<input type="button" value="全部展开" id="btExpandAll" class="button" onclick="expandAll(true)" title="全部展开" /> 
		<input type="button" value="全部收缩" id="btCollapseAll" class="button" onclick="expandAll(false)" title="全部收缩" />
		<input type="button" value="导 出" id="btExport" class="button" onclick="exportXls()" title="导出到Excel" />
		<input type="button" value="选 择" id="btSelect" class="btn btn-success" onclick="doSelect()" title="选择菜单" style="display:none;"/>
		<input type="button" value="关 闭" id="btClose"class="button" onclick="doClose()" title="关闭窗口" />
	</div>
</body>
</powersi:html>