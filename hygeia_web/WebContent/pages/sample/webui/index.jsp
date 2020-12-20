<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<powersi:html>
<head>
<powersi:head title="webui标签库" />
<style type="text/css">
#layout-title a {
	text-decoration: none !important;
	margin-right: 5px;
	color: inherit;
}

#layout-toolbar {
	margin-right: 2px;
}

#layout-toolbar a {
	padding-left: 5px;
	padding-right: 5px;
}
</style>
<script type="text/javascript">
var navdata = [
       { text: '布局示例' },
       { text: '<i class="icon-columns"></i> 页面布局', id: 'layout' },
       { text: '<i class="icon-th"></i> 网格布局', id: 'grid' },
       { text: '<i class="icon-th-large"></i> 面板盒', id: 'panelbox' },
       { text: '<i class="icon-reorder"></i> 标签页', id: 'tabs' },
       { text: '组件示例' },
       { text: '<i class="icon-edit"></i> 表单组件', id: 'form' },
       { text: '<i class="icon-check"></i> 表单验证', id: 'form-validation' },
       { text: '<i class="icon-fa fa-wpforms"></i> 表单向导', id:'form-wizard'},
       { text: '<i class="icon-fa fa-calendar"></i> 日期控件', id: 'date'},
       { text: '<i class="icon-chevron-sign-down"></i> 组合框', id: 'combobox' },
       //{ text: '<i class="icon-play-sign"></i> 按钮控件', id: 'buttons' },
       { text: '<i class="icon-external-link"></i> 弹窗控件', id: 'popup' },
       { text: '<i class="icon-fa fa-tree"></i> 树控件', id: 'tree'},
       { text: '<i class="icon-fa fa-calendar"></i> 日期控件', id: 'date'},
       { text: '表格示例' },
       { text: '<i class="icon-table"></i> 表格组件', id: 'datagrid' },
       { text: '<i class="icon-table"></i> 分页无计数', id: 'datagrid-noncount' },
       { text: '<i class="icon-table"></i> 多表头表格', id: 'datagrid-multiple' },
       { text: '<i class="icon-list-ul"></i> 明细表格', id: 'datagrid-detail' },
       { text: '<i class="icon-list-ul"></i> 明细表单', id: 'datagrid-form' },
       { text: '<i class="icon-list-ul"></i> 明细选项卡', id: 'datagrid-tabs' },
       { text: '<i class="icon-list-ol"></i> 提交表格', id: 'datagrid-submit' },
       { text: '<i class="icon-edit"></i> 编辑表格', id: 'datagrid-edit' },
       { text: '<i class="icon-th"></i> 选择表格', id: 'datagrid-select' },
       { text: '<i class="icon-list"></i> 汇总表格', id: 'datagrid-summary' },
       { text: '<i class="icon-tasks"></i> 树表格', id: 'datagrid-tree' },
       { text: '<i class="icon-filter"></i> 过滤表格', id: 'datagrid-filter' },
       { text: '<i class="icon-fa fa-line-chart"></i> 进度表格', id: 'datagrid-progress' },
       { text: '<i class="icon-fa fa-venus-double"></i> 动态表头', id: 'datagrid-dynamic' },
       { text: '图形示例' },
       { text: '<i class="icon-flag-checkered"></i> 图标控件', id: 'icons' },
       { text: '<i class="icon-bar-chart"></i> 图表Highcharts', id: 'highcharts' },
       { text: '<i class="icon-fa fa-area-chart"></i> 图表ECharts', id: 'echarts' },
       { text: '<i class="icon-qrcode"></i> 二维码', id: 'qrcode' },
       { text: '安全示例' },
       { text: '<i class="icon-fa fa-shield"></i> XSS安全', id: 'xss' },
       { text: '其他示例' },
       { text: '<i class="icon-desktop"></i> 浏览器', id: 'browser' },
       { text: '<i class="icon-sitemap"></i> 网站导航', id: 'sitemap' }
   ];
</script>
</head>
<body class="page noScroll">
	<powersi:pagelayout id="layout1" leftWidth="180" allowLeftResize="false" space="-1">
       <powersi:pagelayout-panel position="left">
	       	<powersi:pagelayout-panel-title>
				<i class="icon-list"></i>功能导航
			</powersi:pagelayout-panel-title>
			<powersi:listbox id="listbox" isNav="true" height="100%" width="100%" 
				data="navdata" value="layout" 
				onSelected="selectedListbox">
			</powersi:listbox>
       </powersi:pagelayout-panel>
       <powersi:pagelayout-panel position="center">
       		<powersi:pagelayout-panel-title>
       			<div id="layout-title"></div>
       		</powersi:pagelayout-panel-title>
       		<iframe id="main" name="main" frameborder="0" width="100%" height="100%"></iframe>
       </powersi:pagelayout-panel>
    </powersi:pagelayout>
    <div class="hidden">
    	<div id="qrcode-dlg">
    		<div id="qrcode-img"></div>
    	</div>
    </div>
<powersi:errors />
<script type="text/javascript">
var url = null;
var navid = null;
function selectedListbox(value, text, data){
	if(value == null || value == ''){
		return;
	}

	url = rootPath + '/pages/sample/webui/' + value + ".jsp";
	navid = data.id || '';
	$('#main').attr("src", url);
	//$('#layout-title').text(htmlTrim(text));
	var a = [];
	a.push('<div style="float:left">' + text + '</div>');
	a.push('<div id="layout-toolbar" style="float:right;font-size:15px;">');
	a.push('<a onclick="doPdfHelp()" title="打开PDF帮助"><i class="icon-fa fa-file-pdf-o"></i></a>');
	a.push('<a onclick="doDocHelp()" title="打开WORD帮助"><i class="icon-fa fa-file-word-o"></i></a>');
	a.push('<a onclick="doQRCode()" title="显示二维码""><i class="icon-qrcode" style="font-size: 15px;"></i></a>');
	a.push('<a onclick="doOpen()" title="在新窗口打开"><i class="icon-fa fa-external-link"></i></a>');
	a.push('<a onclick="doReload()" title="刷新页面"><i class="icon-fa fa-refresh"></i></a>');
	a.push('</div>');
	$('#layout-title').html(a.join(""));
}

function doReload(){
	if(url){
		//ie12设置相同url不刷新
		var tmp = url;
		if(tmp.indexOf('?') < 0){
			tmp += '?';
		}
		tmp += ("tm=" + (new Date()).getTime());
		$('#main').attr("src", tmp);
	}
}

function doOpen() {
	if(url){
		window.open(url);
	}
}

function doPdfHelp() {
	window.open(rootPath + '/struts/doc/powersitags.pdf' + (POWERSI_VERSION ? ('?v=' + POWERSI_VERSION) : ''), 'pdfhelp');
}

function doDocHelp() {
	window.open(rootPath + '/struts/doc/powersitags.docx' + (POWERSI_VERSION ? ('?v=' + POWERSI_VERSION) : ''), 'dochelp');
}

$(function(){
	//初始化选择
	var sel = listbox.getSelectedItems();
	if(sel != null && sel.length > 0){
		selectedListbox(sel[0].id, sel[0].text, sel[0]);
	}
});

//二维码输出
var dlg;
function doQRCode() {
	if(!url){
		return;
	}
	//二维码文本
	var text = getRootUrl() + url;
	//二维码标签
	var a = [];
	a.push("PowerSI WebUI");
	var s = true;
	for(var i = 0; i < navid.length; i++){
		var c = navid.charAt(i);
		if(c == '-'){
			s = true;
		} else{
			if(s){
				a.push(' ');
				a.push(c.toUpperCase());
			} else{
				a.push(c);
			}
			
			s = false;
		}
	}
	var label = a.join("");
	var fontName = 'Helvetica';
	var fontColor = powersi.color('orange');
	//二维码选项
	var options = {
		    // render method: 'canvas', 'image' or 'div'(canvas不能另存为图片（ie8输出div）)
		    render: 'image',
		    // version range somewhere in 1 .. 40
		    minVersion: 6,
		    maxVersion: 40,
		    // error correction level: 'L', 'M', 'Q' or 'H'
		    ecLevel: 'H',
		    // offset in pixel if drawn onto existing canvas
		    left: 0,
		    top: 0,
		    // size in pixel
		    size: 300,
		    // code color or image element
		    fill: '#000',
		    // background color or image element, null for transparent background
		    background: null,
		    // content
		    text: text,
		    // corner radius relative to module width: 0.0 .. 0.5
		    radius: 0.5,
		    // quiet zone in modules
		    quiet: 1,
		    // modes
		    // 0: normal
		    // 1: label strip
		    // 2: label box
		    // 3: image strip
		    // 4: image box
		    mode: 2,
		    mSize: 0.05,
		    mPosX: 0.5,
		    mPosY: 0.5,
		    label: label,
		    fontname: fontName,
		    fontcolor: fontColor,
		    
		    image: null
	};
	
	$('#qrcode-img').empty().qrcode(options);
	
	if(dlg){
		dlg.show();
	} else{
		dlg = popupDiv("#qrcode-dlg", "二维码");
	}
}
</script>
<script src="${strutsPath}/js/plugins/qrcode/jquery.qrcode.min.js" type="text/javascript"></script>
</body>
</powersi:html>