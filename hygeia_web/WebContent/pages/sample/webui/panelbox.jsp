<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="panelbox面板盒" />
<style type="text/css">
.content{
	text-align: center;
	border: 1px solid #ddd;
	background: #fafafa;
	height: 170px;
	line-height: 170px;
	font-size: 60px;
	font-weight: bold;
}
</style>
</head>
<body class="grid">
	<div class="row">
		<div class="col-4">
			<powersi:panelbox title="初始化隐藏" iconClass="icon-cog"
				isCollapse="true">
				<div class="content">1</div>
			</powersi:panelbox>
		</div>
		<div class="col-4">
			<powersi:panelbox title="定义显示更多" iconClass="icon-asterisk"
				allowAddition="true">
				<div class="content">2</div>
			</powersi:panelbox>
		</div>
		<div class="col-4">
			<powersi:panelbox title="禁止收起面板" iconClass="icon-ban-circle textError"
				allowCollapse="false">
				<div class="content">3</div>
			</powersi:panelbox>
		</div>
	</div>
	<powersi:panelbox key="panel_query" title="嵌套面板" iconClass="icon-th-list red">
	<div class="row">
		<div class="col-4">
			<powersi:panelbox title="工具栏1" iconClass="icon-cogs" allowAddition="true">
				<powersi:panelbox-toolbar>
					<div>
						<a href="#" class="textWarning dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" id="drop1">
							更多选项1 <i class="icon-caret-down"></i>
						</a>
						<ul class="dropdown-menu" aria-labelledby="drop1">
							<li><a href="#" onclick="alert($(this).text())" title="设置">设置1</a></li>
							<li><a href="#" onclick="alert($(this).text())" title="刷新">刷新1</a></li>
							<li><a href="#" onclick="alert($(this).text())" title="新增">新增1</i></a></li>
							<li class="divider"></li>
							<li><a href="#" onclick="alert($(this).text())" title="删除">删除1</a></li>
						</ul>
					</div>
					<div>
						<a href="#" class="textWarning dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" id="drop2">
							更多选项2 <i class="icon-caret-down"></i>
						</a>
						<ul class="dropdown-menu pull-right" aria-labelledby="drop2">
							<li><a href="#" onclick="alert($(this).text())" title="设置">设置2</a></li>
							<li><a href="#" onclick="alert($(this).text())" title="刷新">刷新2</a></li>
							<li><a href="#" onclick="alert($(this).text())" title="新增">新增2</i></a></li>
							<li class="divider"></li>
							<li><a href="#" onclick="alert($(this).text())" title="删除">删除2</a></li>
						</ul>
					</div>
				</powersi:panelbox-toolbar>
				<div class="content">4</div>
			</powersi:panelbox>
		</div>
		<div class="col-4">
			<powersi:panelbox title="工具栏2" iconClass="icon-asterisk"
				allowAddition="true">
				<powersi:panelbox-toolbar>
					<a href="#" onclick="alert($(this).attr('title'))" title="设置"><i class="icon-cog"></i></a>
					<a href="#" onclick="alert($(this).attr('title'))" title="刷新"><i class="icon-refresh"></i></a>
					<a href="#" onclick="alert($(this).attr('title'))" title="新增"><i class="icon-plus-sign"></i></a>
					<a href="#" onclick="alert($(this).attr('title'))" title="删除"><i class="icon-remove-sign"></i></a>
				</powersi:panelbox-toolbar>
				<div class="content">5</div>
			</powersi:panelbox>
		</div>
		<div class="col-4">
			<powersi:panelbox key="panel_result" title="工具栏3" allowCollapse="false">
				<powersi:panelbox-toolbar>
					<span class="red">
						8.8% <i class="icon-arrow-up"></i>
					</span>
					<span class="green">
						1.2% <i class="icon-arrow-down"></i>
					</span>
					<span class="label labelWarning">
						9% <i class="icon-ok"></i>
					</span>
					<span class="badge badgeInfo">
						1
					</span>
				</powersi:panelbox-toolbar>
				<div class="content">6</div>
			</powersi:panelbox>
		</div>
	</div>
	</powersi:panelbox>
	<div class="row">
		<div class="col-4">
			<powersi:panelbox key="panel_result" title="自定义标题1">
				<powersi:panelbox-title>
					<a onclick="alert($(this).attr('title'))" title="图形统计明细"><i class='icon-bar-chart'></i>图形统计明细</a>
				</powersi:panelbox-title>
				<div class="content">7</div>
			</powersi:panelbox>
		</div>
		<div class="col-4">
			<powersi:panelbox>
				<powersi:panelbox-title>
					<a onclick="alert($(this).attr('title'))" title="图形统计明细"><i class='icon-bar-chart'></i>图形统计明细</a>
					<a onclick="alert($(this).attr('title'))" title="全部费用明细"><i class='icon-list'></i>全部费用明细</a>
				</powersi:panelbox-title>
				<div class="content">8</div>
			</powersi:panelbox>
		</div>
		<div class="col-4">
			<powersi:panelbox iconClass="icon-reorder orange" title="支持标签页" allowCollapse="false">
				<powersi:panelbox-toolbar>
					<ul class="nav nav-tabs" id="tabs">
						<li class="active"><a data-toggle="tab" href="#tab1">标签1</a></li>
						<li><a data-toggle="tab" href="#tab2">标签2</a></li>
						<li><a data-toggle="tab" href="#tab3">标签3</a></li>
					</ul>
				</powersi:panelbox-toolbar>
				<div class="tab-content content">
					<div class="tab-pane active" id="tab1">9<span class="badge badgeDanger">1</span></div>
					<div class="tab-pane" id="tab2">9<span class="badge badgeDanger">2</span></div>
					<div class="tab-pane" id="tab3">9<span class="badge badgeDanger">3</span></div>
				</div>
			</powersi:panelbox>
		</div>
	</div>
	<div class="space-y"></div>
	<powersi:errors />
<script type="text/javascript">
	$(function(){
		 $('.col-4').sortable({
	        connectWith: '.col-4',
			items:'> .panelBox',
			opacity:0.8,
			revert:true,
			forceHelperSize:true,
			forcePlaceholderSize:true,
			tolerance:'pointer'
		});
	});
</script>
</body>
</powersi:html>