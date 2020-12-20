<%@page import="com.powersi.sys.login.action.LoginAction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.hygeia.framework.MenuRightMapping,com.powersi.hygeia.framework.BusiContext"%>
<%
	String menuRender = LoginAction.getMenuRender(BusiContext.getUserInfo());
%>
<powersi:html>
<head>
<powersi:head title="菜单导航图" />
<style type="text/css">
#searchbox{
	padding: 5px;
	text-align: center;
	border-bottom: 1px dotted #ddd;
}
#main, #search {
	overflow: auto;
	height: 100%;
}
.main-header{
	margin: 10px 0 10px 0;
	border-bottom: 1px solid #eee;
	color: #ff8600;
	padding-bottom: 10px;
	font-size: 1.6em;
	font-weight: 500;
}
.sub-header {
	margin: 0 0 10px 0;
	color: #000;
	font-size: 1.4em;
	font-weight: 500;
}
.sub-content > .sub-content > .sub-header{
	font-size: 1.3em;
}
.sub-content > .sub-content > .sub-content .sub-header{
	font-size: 1.2em;
}
.sub-content{
	margin: 0 0 20px 0;
}
.sub-content > .sub-content{
	margin-left: 30px;
	margin-top: 10px;
}
.sub-content > .sub-content > .sub-content{
	margin-left: 60px;
	margin-top: 10px;
}
.sub-content > .sub-content > .sub-content > .sub-content{
	margin-left: 90px;
	margin-top: 10px;
}
.sub-content > .sub-content > .sub-content > .sub-content > .sub-content{
	margin-left: 120px;
	margin-top: 10px;
}
.sub-content > .sub-content > .sub-content > .sub-content > .sub-content > .sub-content{
	margin-left: 150px;
	margin-top: 10px;
}

.menu {
	-o-text-overflow: ellipsis;
	overflow: hidden;
	text-align: left;
	text-overflow: ellipsis;
	white-space: nowrap;
}
.menu > a, .menu > div{
	line-height: 150%;
	font-size: 1.1em;
}
</style>
</head>
<body class="page noScroll">
	<powersi:pagelayout id="layout1" leftWidth="200" space="-1">
		<powersi:pagelayout-panel position="left" cssClass="noPadding">
			<powersi:pagelayout-panel-title>
				<i class="icon-list"></i>
				菜单索引
			</powersi:pagelayout-panel-title>
			<div id="searchbox">
				<input id="search-input" type="text" class="search" placeholder="搜索菜单" title="输入关键字,按回车键显示符合条件的数据"></input>
			</div>
			<powersi:listbox id="listbox" isNav="true" height="100%" width="100%"
				onSelected="selectedListbox">
			</powersi:listbox>
		</powersi:pagelayout-panel>
		<powersi:pagelayout-panel position="center">
			<powersi:pagelayout-panel-title>
				<div class="l-layout-header-inner">
					<i class="icon-reorder"></i>
					菜单地图
				</div>
			</powersi:pagelayout-panel-title>
			<div id="main" class="container-fluid">
			</div>
			<div id="search" class="container-fluid">
			</div>
		</powersi:pagelayout-panel>
	</powersi:pagelayout>
<script type="text/javascript">
var menuRender = '<%=menuRender %>';
var menuList = null;
var menuCount = 0;
var menuTree = [];
var navData = [];
var rowIndex = 0;
var sysIndex = 0;
var menuMap = {};
function renderMenu(){
	if (menuRender == null || menuRender.length == 0) {
        return;
    }
    menuList = powersi.tojson(menuRender);
    menuRender.length = 0;
    menuRender = null;

    menuCount = (menuList == null) ? 0 : menuList.length;
    if (menuCount < 1) {
        return;
    }
    
    for(var i = 0; i < menuCount; i ++){
    	var menu = menuList[i];
    	menuMap[menu.id] = menu;
    }
    
	renderMenuTree({
        'id': 0
    });
	
	if(rowIndex > 0){
    	rowIndex = 0;
    	menuTree.push('</div>');
    }
	
	listbox.setData(navData);
	
	var html = menuTree.join("");
	$('#main').html(html);	
	html = null;
	
	navData.length = 0;
	navData = null;
	
	menuTree.length = 0;
	menutree = null;
}

function getMenuDesc(menu){
	if(!menu.desc){
		var a = [];
        var pMenu = menu;
        while (pMenu) {
            a.push(pMenu.name);
            pMenu = menuMap[pMenu.pId];
        }
        menu.desc = a.reverse().join(' - ');
        a.length = 0;
	}
	
	return menu.desc;
}

function renderMenuTree(pMenu) {
    for (var i = 0; i < menuCount; i++) {
        var menu = menuList[i];
        if (menu.pId != pMenu.id) {
            continue;
        }

        if (!menu.data || menu.data.length == 0) {
        	if(rowIndex > 0){
            	rowIndex = 0;
            	menuTree.push('</div>');
            }
        	if(menu.pId == 0){
        		menuTree.push('<div class="main-content">');
        		menuTree.push('<div id="m' + menu.id + '" class="main-header">' + menu.name + '</div>');
        		
        		navData.push({text : '<i class="icon-double-angle-right"></i> ' + menu.name, id: menu.id});
        	} else {
        		menuTree.push('<div class="sub-content">');
        		menuTree.push('<div id="m' + menu.id + '" class="sub-header">' + menu.name + '</div>');
        	}
        	
        	renderMenuTree(menu);
        	if(rowIndex > 0){
            	rowIndex = 0;
            	menuTree.push('</div>');
            }
        	
        	menuTree.push('</div>');
        } else {
            if(rowIndex >= 3){
            	rowIndex = 0;
            	menuTree.push('</div>');
            }
            
            if(rowIndex == 0){
            	menuTree.push('<div class="row">');
            }
            rowIndex ++;
            menuTree.push("<div class='col-4 menu'>");
           
            menuTree.push('<a href="javascript:openMenu(\'' + menu.data + '\')" title="' + getMenuDesc(menu) + '">');
            menuTree.push(menu.name);
            menuTree.push('</a>');

            menuTree.push("</div>");
        }
    }
}

function selectedListbox(value, text, data){
	var el = document.getElementById('m' + value);
	if(el) {
		el.scrollIntoView(true);
	}
}

$(function(){
	renderMenu();
	
	$('#search').hide();
	$("#search-input").keydown(function (e) {
        if (e.keyCode != 13) {
            return;
        }

        try{
        	var filter = $.trim($(this).val()), count = 0;
	          
            var cnt = 0;
            if (filter == '') {
            	$('#search').html("");
                $('#search').hide();
                $('#main').show();
            } else {
            	$('#main').hide();
            	$('#search').show();
            	
            	var a = [];
            	var reg = new RegExp(filter, "i");
            	
            	a.push('<div class="main-content">');
        		a.push('<div class="main-header">搜索结果</div>');
        	
        		var b = [];
            	$.each(menuMap, function(id, menu){
            		if (menu.data && menu.data.length > 0 && menu.name.search(reg) >= 0) {
            			b.push('<div class="row"><div class="col-12 menu">');
                        b.push('<a href="javascript:openMenu(\'' + menu.data + '\')">');
                        b.push(getMenuDesc(menu));
                        b.push('</a>');
                        b.push('</div></div>');

						cnt ++;
                    }
            	});
            	
            	if(cnt == 0){
            		a.push('<div class="row"><div class="col-12 menu">');
                    a.push('<div class="textError">');
                    a.push('找不到 "' + filter + '" 的菜单。');
                    a.push('</div');
                    a.push('</div></div>');
            	} else {
            		a.push('<div class="row"><div class="col-12 menu">');
                    a.push('<div class="textSuccess">');
                    a.push('共找到 ' + cnt.toString() + ' 个 "' + filter + '" 的菜单。');
                    a.push('</div');
                    a.push('</div></div>');
            	}
            	
            	b.push('</div>');
            	
            	$('#search').html(a.concat(b).join(""));
            	
            	a.length = 0;
            	b.length = 0;
            }
        } catch(ex){
        	popupAlert(ex.message);
        }
      
        e.returnValue = false;
        return false;
    });
});
</script>
</body>
</powersi:html>