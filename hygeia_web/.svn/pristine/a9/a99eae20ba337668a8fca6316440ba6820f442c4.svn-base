<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<powersi:html>
<head>
<powersi:head title="选项卡" />
<style type="text/css">
.content{
	text-align: center;
	border: 1px solid #ddd;
	background: #fff;
	height: 150px;
	line-height: 150px;
	font-size: 24px;
	font-weight: bold;
}
</style>
</head>
<body class="grid">
	<div class="row divButton">
		<div class="col-6">
			<powersi:button value="选择1-2" onclick="selectTab('#tabs1', 1);" />
			<powersi:button value="禁用1-2" onclick="disableTab('#tabs1', 1);" /> 
			<powersi:button value="启用1-2" onclick="enableTab('#tabs1', 1);" />
			<powersi:button value="禁用1-3" onclick="disableTab('#tabs1', 2);" /> 
			<powersi:button value="选择4-3" onclick="selectTab('#divTabs', 2);" />
			<powersi:button value="禁用4-3" onclick="disableTab('#divTabs', 2);" /> 
			<powersi:button value="启用4-3" onclick="enableTab('#divTabs', 2);" /> 
		</div>
		<div class="col-6">
			<div id="tab-event" class="red">
				tab选择后事件
			</div>
		</div>
	</div>
	
	<powersi:panelbox title="bootstrap tabs">
	<div class="row">
		<div class="col-6">
			<div class="tabbable">
				<ul class="nav nav-tabs" id="tabs1">
					<li class="active">
						<a data-toggle="tab" href="#tabs1-tab1" id="tab1-1">
							<i class="icon-desktop green"></i>
							选项卡1
							<span class="label labelSuccess">6</span>
						</a>
					</li>

					<li>
						<a data-toggle="tab" href="#tabs1-tab2" id="tab1-2">
							<i class="icon-thumbs-up-alt red"></i>
							选项卡2
							<span class="badge badgeDanger">8</span>
						</a>
					</li>

					<li class="dropdown">
						<a data-toggle="dropdown" class="dropdown-toggle" data-hover="dropdown" href="#">
							<i class="icon-sun blue"></i>
							下拉菜单
							<i class="icon-caret-down"></i>
						</a>

						<ul class="dropdown-menu dropdown-info">
							<li>
								<a data-toggle="tab" href="#tabs1-dropdown1" id="tab1-3">下拉菜单1</a>
							</li>

							<li>
								<a data-toggle="tab" href="#tabs1-dropdown2" id="tab1-4">下拉菜单2</a>
							</li>
							<li class="divider"></li>
							<li>
								<a data-toggle="tab" href="#tabs1-dropdown3" id="tab1-5">下拉菜单3</a>
							</li>
						</ul>
					</li>
					
					<li class="pull-right">
						<button href="#" onclick="alert($(this).attr('title'))" title="刷新" class="btn"><i class="icon-refresh"></i></button>
					</li>
				</ul>

				<div class="tab-content content">
					<div id="tabs1-tab1" class="tab-pane active">
						<p>选项卡1-1</p>
					</div>

					<div id="tabs1-tab2" class="tab-pane">
						<p>选项卡1-2</p>
					</div>

					<div id="tabs1-dropdown1" class="tab-pane">
						<p>下拉菜单1</p>
					</div>

					<div id="tabs1-dropdown2" class="tab-pane">
						<p>下拉菜单2</p>
					</div>
					
					<div id="tabs1-dropdown3" class="tab-pane">
						<p>下拉菜单3</p>
					</div>
				</div>
			</div>
		</div>

		<div class="col-6">
			<div class="tabbable tabs-bottom">
				<div class="tab-content content">
					<div id="tabs2-tab1" class="tab-pane active">
						<p>选项卡2-1</p>
					</div>

					<div id="tabs2-tab2" class="tab-pane">
						<p>选项卡2-2</p>
					</div>

					<div id="tabs2-tab3" class="tab-pane">
						<p>选项卡2-3</p>
					</div>
				</div>
				<ul class="nav nav-tabs" id="tabs2">
					<li class="active">
						<a data-toggle="tab" href="#tabs2-tab1">选项卡1</a>
					</li>

					<li>
						<a data-toggle="tab" href="#tabs2-tab2">选项卡2</a>
					</li>

					<li>
						<a data-toggle="tab" href="#tabs2-tab3">选项卡3</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
	
	<div class="space-y"></div>
	
	<div class="row">
		<div class="col-6">
			<div class="tabbable tabs-left">
				<ul class="nav nav-tabs" id="tabs3">
					<li class="active">
						<a data-toggle="tab" href="#tabs3-tab1">
							<i class="pink icon-dashboard"></i>
							选项卡1
						</a>
					</li>

					<li>
						<a data-toggle="tab" href="#tabs3-tab2">
							<i class="blue icon-user"></i>
							选项卡2
						</a>
					</li>

					<li>
						<a data-toggle="tab" href="#tabs3-tab3">
							<i class="icon-rocket"></i>
							选项卡3
						</a>
					</li>
				</ul>

				<div class="tab-content content">
					<div id="tabs3-tab1" class="tab-pane active">
						<p>选项卡3-1</p>
					</div>

					<div id="tabs3-tab2" class="tab-pane">
						<p>选项卡3-2</p>
					</div>

					<div id="tabs3-tab3" class="tab-pane">
						<p>选项卡3-3</p>
					</div>
				</div>
			</div>
		</div>
		<div class="col-6">
			<div class="tabbable tabs-right">
				<ul class="nav nav-tabs" id="tabs4">
					<li class="active">
						<a data-toggle="tab" href="#tabs4-tab1">
							<i class="pink icon-dashboard"></i>
							选项卡1
						</a>
					</li>

					<li>
						<a data-toggle="tab" href="#tabs4-tab2">
							<i class="blue icon-user"></i>
							选项卡2
						</a>
					</li>

					<li>
						<a data-toggle="tab" href="#tabs4-tab3">
							<i class="icon-rocket"></i>
							选项卡3
						</a>
					</li>
				</ul>

				<div class="tab-content content">
					<div id="tabs4-tab1" class="tab-pane active">
						<p>选项卡4-1</p>
					</div>

					<div id="tabs4-tab2" class="tab-pane">
						<p>选项卡4-2</p>
					</div>

					<div id="tabs4-tab3" class="tab-pane">
						<p>选项卡4-3</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="space-y"></div>
	
	<div class="row">
		<div class="col-6">
			<div class="tabbable tabs-center">
				<ul class="nav nav-tabs" id="tabs6">
					<li class="active">
						<a data-toggle="tab" href="#tabs6-tab1" id="tab6-1">
							<i class="icon-desktop green"></i>
							选项卡1
						</a>
					</li>

					<li>
						<a data-toggle="tab" href="#tabs6-tab2" id="tab6-2">
							<i class="icon-laptop red"></i>
							选项卡2
						</a>
					</li>

					<li>
						<a data-toggle="tab" href="#tabs6-tab3" id="tab6-3">
							<i class="icon-tablet yellow"></i>
							选项卡3
						</a>
					</li>
				</ul>
			</div>
			<div class="tab-content content">
				<div id="tabs6-tab1" class="tab-pane active">
					<p>选项卡6-1</p>
				</div>

				<div id="tabs6-tab2" class="tab-pane">
					<p>选项卡6-2</p>
				</div>

				<div id="tabs6-tab3" class="tab-pane">
					<p>选项卡6-3</p>
				</div>
			</div>
		</div>

		<div class="col-6">
			<div class="tabbable tabs-center">
				<ul class="nav nav-pills" id="tabs7">
					<li class="active">
						<a data-toggle="tab" href="#tabs7-tab1" id="tab7-1">
							<i class="icon-desktop green"></i>
							选项卡1
						</a>
					</li>

					<li>
						<a data-toggle="tab" href="#tabs7-tab2" id="tab7-2">
							<i class="icon-laptop red"></i>
							选项卡2
						</a>
					</li>

					<li>
						<a data-toggle="tab" href="#tabs7-tab3" id="tab7-3">
							<i class="icon-tablet yellow"></i>
							选项卡3
						</a>
					</li>
				</ul>
			</div>
			<div class="tab-content content">
				<div id="tabs7-tab1" class="tab-pane active">
					<p>选项卡7-1</p>
				</div>

				<div id="tabs7-tab2" class="tab-pane">
					<p>选项卡7-2</p>
				</div>

				<div id="tabs7-tab3" class="tab-pane">
					<p>选项卡7-3</p>
				</div>
			</div>
		</div>
	</div>
	
	<div class="space-y"></div>
	
	<div class="row">
		<div class="col-6">
			<div class="tabbable">
				<ul class="nav nav-tabs nav-fixed" id="tabs8">
					<li class="active">
						<a data-toggle="tab" href="#tabs8-tab1" id="tab8-1">
							<i class="icon-desktop green"></i>
							选项卡1
						</a>
					</li>

					<li>
						<a data-toggle="tab" href="#tabs8-tab2" id="tab8-2">
							<i class="icon-laptop red"></i>
							选项卡2
						</a>
					</li>

					<li>
						<a data-toggle="tab" href="#tabs8-tab3" id="tab8-3">
							<i class="icon-tablet yellow"></i>
							选项卡3
						</a>
					</li>
				</ul>
			</div>
			<div class="tab-content content">
				<div id="tabs8-tab1" class="tab-pane active">
					<p>选项卡8-1</p>
				</div>

				<div id="tabs8-tab2" class="tab-pane">
					<p>选项卡8-2</p>
				</div>

				<div id="tabs8-tab3" class="tab-pane">
					<p>选项卡8-3</p>
				</div>
			</div>
		</div>

		<div class="col-6">
			<div class="tabbable">
				<ul class="nav nav-pills nav-fixed" id="tabs5">
					<li class="active">
						<a data-toggle="tab" href="#tabs5-tab1" id="tab5-1">
							<i class="icon-desktop green"></i>
							选项卡1
						</a>
					</li>

					<li>
						<a data-toggle="tab" href="#tabs5-tab2" id="tab5-2">
							<i class="icon-laptop red"></i>
							选项卡2
						</a>
					</li>

					<li>
						<a data-toggle="tab" href="#tabs5-tab3" id="tab5-3">
							<i class="icon-tablet yellow"></i>
							选项卡3
						</a>
					</li>
				</ul>
			</div>
			<div class="tab-content content">
				<div id="tabs5-tab1" class="tab-pane active">
					<p>选项卡5-1</p>
				</div>

				<div id="tabs5-tab2" class="tab-pane">
					<p>选项卡5-2</p>
				</div>

				<div id="tabs5-tab3" class="tab-pane">
					<p>选项卡5-3</p>
				</div>
			</div>
		</div>
	</div>
	</powersi:panelbox>
	
	<powersi:panelbox title="jqueryui tabs">
	<div class="row">
		<div class="col-12">
			<powersi:tabbedpanel id="divTabs">
				<powersi:tab id="tab1" target="divTab1" label="选项卡1" />
				<powersi:tab id="tab2" target="divTab2" label="选项卡2" />
				<powersi:tab id="tab3" target="divTab3" label="选项卡3" />
				<powersi:tab id="tab4" target="divTab4" label="选项卡4" />
				<powersi:tab id="tabBtn">
					<powersi:button id="btnRefresh" key="button_refresh" onclick="alert('刷新')" />
				</powersi:tab>
				<div id="divTab1">
					<div class="content">
						<p>选项卡-1</p>
					</div>
				</div>
				<div id="divTab2">
					<div class="content">
						<p>选项卡-2</p>
					</div>
				</div>
				<div id="divTab3">
					<div class="content">
						<p>选项卡-3</p>
					</div>
				</div>
				<div id="divTab4">
					<div class="content">
						<p>选项卡-4</p>
					</div>
				</div>
			</powersi:tabbedpanel>
		</div>
	</div>
	</powersi:panelbox>
<powersi:errors />
<script type="text/javascript">
	$(function(){
		$('.nav').each(function(){
			bindAfterSelectTab($(this), showTabsEvent);
		});
		
		bindAfterSelectTab('#divTabs', showTabsEvent);
	});
	
	function showTabsEvent(newTabId, oldTabId){
		$('#tab-event').text('newTabId:' + newTabId + ' oldTabId:' + oldTabId);
	}
</script>
</body>
</powersi:html>