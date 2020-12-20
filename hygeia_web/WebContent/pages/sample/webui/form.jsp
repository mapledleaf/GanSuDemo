<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="form表单" />
<script type="text/javascript" src="${strutsPath}/include/combogrid.js"></script>
<style type="text/css">
.popover {
    max-width: 400px;
}
/*autocomplete滚动条设置*/
.ui-autocomplete {
    max-height: 200px;
    overflow-y: auto;
    /* prevent horizontal scrollbar */
    overflow-x: hidden;
}
* html .ui-autocomplete {
   height: 200px;/*for ie6*/
}
</style>
</head>
<body class="page">
	<powersi:form id="form1" action="null">
		<div class="grid">
			<div class="row">
				<div class="col-6">
					<div class="row">
						<powersi:panelbox key="panel_query" title="select2单选(支持编码、汉字、首拼、全拼检索)">
							<powersi:panelbox-toolbar>
								<a href="#" rel="popover" data-target="#popover-select2" data-placement="bottom" data-original-title="select2帮助">
          							<i class="icon-question"></i>
        						</a>
							</powersi:panelbox-toolbar>
							<powersi:editorlayout cols="25%,75%">
								<powersi:editorlayout-row>
									<powersi:codeselect name="center_id" id="center_id"
									key="aaa027" codeType="aaa027_list" required="true"
									cssClass="select2" multiple="false" showValue="true" data-hotkey="alt+1"/>
								</powersi:editorlayout-row>
							</powersi:editorlayout>
						</powersi:panelbox>
					</div>
					<div class="row">
						<powersi:panelbox key="panel_query" title="select2多选">
							<powersi:panelbox-toolbar>
								<a href="#" rel="popover" data-target="#popover-select2" data-placement="bottom" data-original-title="select2帮助" data-hotkey="alt+3">
          							<i class="icon-question"></i>
        						</a>
							</powersi:panelbox-toolbar>
							<powersi:editorlayout cols="25%,75%">
								<powersi:editorlayout-row>
									<powersi:codeselect name="staff_level" id="staff_level" headerKey="-1" data-placeholder="请选择用户级别(多选)"
									key="staff_level" codeType="staff_level" required="true"
									cssClass="select2" multiple="true" data-auto-width="true" data-hotkey="alt+2" />
								</powersi:editorlayout-row>
							</powersi:editorlayout>
						</powersi:panelbox>
					</div>
					<div class="row">
						<powersi:panelbox key="panel_query" title="select2初始化">
							<powersi:panelbox-toolbar>
								<a href="#" rel="popover" data-target="#popover-select2" data-placement="bottom" data-original-title="select2帮助">
          							<i class="icon-question"></i>
        						</a>
							</powersi:panelbox-toolbar>
							<powersi:editorlayout cols="25%,75%">
								<powersi:editorlayout-row>
									<powersi:codeselect name="dept_id" id="dept_id"
									key="dept_id" codeType="sys_dept" required="true" value="1" showValue="true" />
								</powersi:editorlayout-row>
							</powersi:editorlayout>
						</powersi:panelbox>
					</div>
					<div class="row">
						<powersi:panelbox key="panel_query" title="combogrid(支持键盘操作，支持数字选择)">
							<powersi:editorlayout cols="25%,75%">
								<powersi:editorlayout-row>
									<powersi:editorlayout-label>
										<powersi:label value="查询目录" for="sidx"></powersi:label>
									</powersi:editorlayout-label>
									<powersi:editorlayout-input>
										<div class="input-group">
											<span class="input-group-btn">
												<select id="sidx" onchange='changeSearchType(this)' style="width:80px;" class="select2" data-show-search="false" data-hotkey="alt+3">
													<option value="py">首拼码</option>
													<option value="wb">五笔码</option>
													<option value="name">名称</option>
													<option value="code">编码</option>
												</select>
											</span>
										<input type="text" class="text" id="stext" value="" size="30" maxlength="30" placeholder="拼音/五笔/名称/编码" />
									</div>
									</powersi:editorlayout-input>
								</powersi:editorlayout-row>
							</powersi:editorlayout>
						</powersi:panelbox>
					</div>
					<div class="row">
						<powersi:script>
							<powersi:codetable codeType="nation" id="nationList"></powersi:codetable>
							var nations = [];
							$.each(nationList, function(){
								nations.push(this.text);
							});
						</powersi:script>
						<powersi:panelbox key="panel_query" title="autocomplete(支持键盘操作)">
							<powersi:editorlayout cols="4">
								<powersi:editorlayout-row>
									<powersi:textfield id="ac1" name="nation_name" placeholder="请输入民族名称" label="本地数据" data-hotkey="alt+4"></powersi:textfield>
									<powersi:textfield id="ac2" name="item_code" placeholder="请输入药品编码" label="远程数据" data-hotkey="alt+5"></powersi:textfield>
								</powersi:editorlayout-row>
							</powersi:editorlayout>
						</powersi:panelbox>
					</div>
					<div class="row">
						<powersi:panelbox key="panel_query" title="combobox">
							<powersi:panelbox-toolbar>
								<a href="#" rel="popover" data-target="#popover-combobox" data-placement="bottom" data-original-title="combobox帮助">
          							<i class="icon-question"></i>
        						</a>
							</powersi:panelbox-toolbar>
							<powersi:editorlayout cols="25%,75%">
								<powersi:editorlayout-row>
									<powersi:combobox id="cbAaa027" valueFieldID="aaa027" codeType="aaa027_list" required="true" key="aaa027" data-hotkey="alt+6">
										<powersi:hidden id="aaa027" name="aaa027" value="" />
									</powersi:combobox>
								</powersi:editorlayout-row>
							</powersi:editorlayout>
						</powersi:panelbox>
					</div>
					<div class="row">
						<powersi:panelbox key="panel_query" title="弹出选择">
							<powersi:editorlayout cols="25%,75%">
								<powersi:textfield label="上级菜单" name="menu_up_name" id="menu_up_name" required="true" readonly="true" 
									buttonId="btnSelectUp" buttonTitle="选择上级菜单" onbuttonclick="$('#menu_up_name').val('上级菜单');popupMessage('点击上级菜单按钮');" data-hotkey="alt+7"></powersi:textfield>
								<powersi:hidden id="menu_up_id" name="menu_up_id" required="true" 
									data-validation-target="menu_up_name" />
							</powersi:editorlayout>
						</powersi:panelbox>
					</div>
					<div class="row">
						<powersi:panelbox key="panel_query" title="checkbox1">
							<powersi:editorlayout cols="25%,75%">
								<powersi:editorlayout-row>
									<powersi:checkboxlist id="cbStaffLevel1" codeType="staff_level" name="staff_level1"  key="staff_level" 
									required="true" validate="minCheckbox[2] maxCheckbox[4]" checkAllButton="true"/>
								</powersi:editorlayout-row>
							</powersi:editorlayout>
						</powersi:panelbox>
					</div>
					<div class="row">
						<powersi:panelbox key="panel_query" title="checkbox2">
							<powersi:editorlayout cols="25%,75%">
								<powersi:editorlayout-row>
									<powersi:checkboxlist id="cbStaffLevel2" codeType="staff_level" name="staff_level2"  key="staff_level" 
									required="true" repeatColumns="3" list="#{'all_value': '全选值'}" checkAllButton="true" />
								</powersi:editorlayout-row>
							</powersi:editorlayout>
						</powersi:panelbox>
					</div>
					<div class="row">
						<powersi:panelbox key="panel_query" title="radio1">
							<powersi:editorlayout cols="25%,75%">
								<powersi:editorlayout-row>
									<powersi:radio id="rbStaffLevel1" codeType="staff_level" name="staff_level3"  key="staff_level" required="true" />
								</powersi:editorlayout-row>
							</powersi:editorlayout>
						</powersi:panelbox>
					</div>
					<div class="row">
						<powersi:panelbox key="panel_query" title="radio2">
							<powersi:editorlayout cols="25%,75%">
								<powersi:editorlayout-row>
									<powersi:radio id="rbStaffLevel2" codeType="staff_level" name="staff_level4"  
									key="staff_level" required="true" repeatColumns="3" list="#{'all': '<span class=\"checkall\">全选</span>'}" />
								</powersi:editorlayout-row>
							</powersi:editorlayout>
						</powersi:panelbox>
					</div>
					<div class="row">
						<powersi:panelbox key="panel_query" title="range1(datetime)">
							<powersi:editorlayout cols="25%,75%">
								<powersi:editorlayout-row>
									<powersi:editorlayout-label>
										<powersi:label>输入时间</powersi:label>
									</powersi:editorlayout-label>
									<powersi:editorlayout-input>
										<div class="input-group">
											<powersi:textfield mask="datetime" id="datetime_min" name="datetime_min" validate="dateTimeRange[timeGroup]" />
											<span class="input-group-addon">-</span>
											<powersi:textfield mask="datetime" id="datetime_max" name="datetime_max" validate="dateTimeRange[timeGroup]" />
										</div>
									</powersi:editorlayout-input>
								</powersi:editorlayout-row>
							</powersi:editorlayout>
						</powersi:panelbox>
					</div>
					<div class="row">
						<powersi:panelbox key="panel_query" title="range1(date)">
							<powersi:editorlayout cols="25%,75%">
								<powersi:editorlayout-row>
									<powersi:editorlayout-label>
										<powersi:label>输入日期</powersi:label>
									</powersi:editorlayout-label>
									<powersi:editorlayout-input>
										<div class="input-group">
											<powersi:textfield mask="date" id="date_min" name="date_min" validate="dateRange[dateGroup]" format="dateFmt:'yyyy-MM-dd'" />
											<span class="input-group-addon">-</span>
											<powersi:textfield mask="date" id="date_max" name="date_max" validate="dateRange[dateGroup]" format="dateFmt:'yyyy-MM-dd'" />
										</div>
									</powersi:editorlayout-input>
								</powersi:editorlayout-row>
							</powersi:editorlayout>
						</powersi:panelbox>
					</div>
					<div class="row">
						<powersi:panelbox key="panel_query" title="range1(period)">
							<powersi:editorlayout cols="25%,75%">
								<powersi:editorlayout-row>
									<powersi:editorlayout-label>
										<powersi:label>输入期间</powersi:label>
									</powersi:editorlayout-label>
									<powersi:editorlayout-input>
										<div class="input-group">
											<powersi:textfield mask="period" id="period_min" name="period_min" validate="dateRange[periodGroup]" />
											<span class="input-group-addon">-</span>
											<powersi:textfield mask="period" id="period_max" name="period_max" validate="dateRange[periodGroup]" />
										</div>
									</powersi:editorlayout-input>
								</powersi:editorlayout-row>
							</powersi:editorlayout>
						</powersi:panelbox>
					</div>
					<div class="row">
						<powersi:panelbox key="panel_query" title="range2(datetime)">
							<powersi:editorlayout cols="25%,75%">
								<powersi:editorlayout-row>
									<powersi:editorlayout-label>
										<powersi:label for="datetimeRange">输入时间</powersi:label>
									</powersi:editorlayout-label>
									<powersi:editorlayout-input>
										<powersi:date id="datetimeRange" drops="up" mask="datetime" singleDatePicker="false" data-hotkey="alt+8">
											<powersi:date-range label="今天"
												startDate="moment().startOf('day')"
												endDate="moment().endOf('day')" />
											<powersi:date-range label="昨天"
												startDate="moment().subtract(1, 'days').startOf('day')"
												endDate="moment().subtract(1, 'days').endOf('day')" />
											<powersi:date-range label="最近三天"
												startDate="moment().subtract(2, 'days').startOf('day')"
												endDate="moment().endOf('day')" />
											<powersi:date-range label="最近一周"
												startDate="moment().subtract(6, 'days').startOf('day')"
												endDate="moment().endOf('day')" />
											<powersi:date-range label="本周"
												startDate="moment().startOf('week').startOf('day')"
												endDate="moment().endOf('day')" />
											<powersi:date-range label="本月"
												startDate="moment().startOf('month').startOf('day')"
												endDate="moment().endOf('day')" />
											<powersi:date-range label="上个月"
												startDate="moment().subtract(1, 'months').startOf('month').startOf('day')"
												endDate="moment().subtract(1, 'months').endOf('month').endOf('day')" />
											<powersi:date-range label="本年"
												startDate="moment().startOf('year').startOf('day')"
												endDate="moment().endOf('day')" />
										</powersi:date>
									</powersi:editorlayout-input>
								</powersi:editorlayout-row>
							</powersi:editorlayout>
						</powersi:panelbox>
					</div>
					<div class="row">
						<powersi:panelbox key="panel_query" title="range2(date)">
							<powersi:editorlayout cols="25%,75%">
								<powersi:editorlayout-row>
									<powersi:editorlayout-label>
										<powersi:label>输入日期</powersi:label>
									</powersi:editorlayout-label>
									<powersi:editorlayout-input>
										<powersi:date id="dateRange" drops="up" mask="date" singleDatePicker="false">
											<powersi:date-range label="今天" startDate="moment()"
												endDate="moment()" />
											<powersi:date-range label="昨天"
												startDate="moment().subtract(1, 'days')"
												endDate="moment().subtract(1, 'days')" />
											<powersi:date-range label="最近三天"
												startDate="moment().subtract(2, 'days')" endDate="moment()" />
											<powersi:date-range label="最近一周"
												startDate="moment().subtract(6, 'days')" endDate="moment()" />
											<powersi:date-range label="本周"
												startDate="moment().startOf('week')" endDate="moment()" />
											<powersi:date-range label="本月"
												startDate="moment().startOf('month')" endDate="moment()" />
											<powersi:date-range label="上个月"
												startDate="moment().subtract(1, 'months').startOf('month')"
												endDate="moment().subtract(1, 'months').endOf('month')" />
										</powersi:date>
									</powersi:editorlayout-input>
								</powersi:editorlayout-row>
							</powersi:editorlayout>
						</powersi:panelbox>
					</div>
					<div class="row">
						<powersi:panelbox key="panel_query" title="range2(period)">
							<powersi:editorlayout cols="25%,75%">
								<powersi:editorlayout-row>
									<powersi:editorlayout-label>
										<powersi:label>输入期间</powersi:label>
									</powersi:editorlayout-label>
									<powersi:editorlayout-input>
										<powersi:date id="periodRange" drops="up" mask="period" singleDatePicker="false">
											<powersi:date-range label="本年"
												startDate="moment().startOf('year')"
												endDate="moment().endOf('year')" />
											<powersi:date-range label="上半年"
												startDate="moment().startOf('year')"
												endDate="moment().quarter(3).subtract(1,'months')" />
											<powersi:date-range label="下半年" startDate="moment().quarter(3)"
												endDate="moment().endOf('year')" />
											<powersi:date-range label="第一季度" startDate="moment().quarter(1)"
												endDate="moment().quarter(2).subtract(1,'months')" />
											<powersi:date-range label="第二季度" startDate="moment().quarter(2)"
												endDate="moment().quarter(3).subtract(1,'months')" />
											<powersi:date-range label="第三季度" startDate="moment().quarter(3)"
												endDate="moment().quarter(4).subtract(1,'months')" />
											<powersi:date-range label="第四季度" startDate="moment().quarter(4)"
												endDate="moment().endOf('year')" />
										</powersi:date>
									</powersi:editorlayout-input>
								</powersi:editorlayout-row>
							</powersi:editorlayout>
						</powersi:panelbox>
					</div>
				</div>
				<div class="col-6">
					<div class="row">
						<div class="col-12">
							<powersi:panelbox key="panel_query" title="工具提示">
								<powersi:panelbox-toolbar>
									<a href="#" rel="popover" data-target="#popover-tooltip" data-placement="bottom" data-original-title="tooltip帮助">
	          							<i class="icon-question"></i>
	        						</a>
								</powersi:panelbox-toolbar>
								<div class="divButton">
									<button type="button" class="btn btn-default" data-toggle="tooltip" title="" data-original-title="Tooltip default">缺省</button>
								    <button type="button" class="btn btn-default tooltip-error" data-toggle="tooltip" data-placement="right" title="" data-original-title="Tooltip on right">右侧</button>
								    <button type="button" class="btn btn-default tooltip-info" data-toggle="tooltip" data-placement="left" title="" data-original-title="Tooltip on left">左侧</button>
								    <button type="button" class="btn btn-default tooltip-warning" data-toggle="tooltip" data-placement="top" title="" data-original-title="Tooltip on top">上方</button>
								    <button type="button" class="btn btn-default tooltip-success" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="Tooltip on bottom">下方</button>
								</div>
							</powersi:panelbox>
						</div>
					</div>
					<div class="row">
						<div class="col-12">
							<powersi:panelbox key="panel_query" title="弹出提示">
								<powersi:panelbox-toolbar>
									<a href="#" rel="popover" data-target="#popover-popover" data-placement="bottom" data-original-title="popover帮助">
	          							<i class="icon-question"></i>
	        						</a>
								</powersi:panelbox-toolbar>
								<div class="divButton">
									<span class="button" data-toggle="popover" title="" data-content="Heads up! This alert needs your attention, but it's not super important." data-original-title="Default">
										缺省
									</span>
									<span class="btn btn-success tooltip-success" data-toggle="popover" data-trigger="hover" data-placement="right" title="" data-content="Well done! You successfully read this important alert message." data-original-title="<i class='icon-ok green'></i> Right Success">
										右部
									</span>
									<span class="btn btn-danger tooltip-error" data-toggle="popover" data-trigger="hover" data-placement="top" data-original-title="<i class='icon-bolt red'></i> Top Danger" data-content="Oh snap! Change a few things up and try submitting again.">
										顶部
									</span>
									<span class="btn btn-info tooltip-info" data-toggle="popover" data-trigger="hover" data-placement="bottom" title="" data-content=" Heads up! This alert needs your attention, but it's not super important." data-original-title="Some Info">
										底部
									</span>
									<span class="btn btn-warning tooltip-warning" data-toggle="popover" data-trigger="focus" data-placement="left" title="" data-content="Warning! Best check yo self, you're not looking too good." data-original-title="<i class='icon-warning-sign orange'></i> Left Warning">
										左部
									</span>
									<span class="btn btn-default popover-notitle" data-toggle="popover" data-trigger="hover" data-placement="bottom" data-content="Popover without a title!" data-original-title="" title="">
										无标题
									</span>
								</div>
							</powersi:panelbox>
						</div>
					</div>
					<div class="row">
						<div class="col-12">
							<powersi:panelbox key="panel_query" title="按钮控件">
								<powersi:panelbox-toolbar>
									<a href="#" rel="popover" data-target="#popover-button" data-placement="bottom" data-original-title="button帮助">
	          							<i class="icon-question"></i>
	        						</a>
								</powersi:panelbox-toolbar>
								<powersi:editorlayout cols="40%,60%">
									<powersi:editorlayout-row>
										<powersi:editorlayout-label>
											按钮状态(data-toggle)
										</powersi:editorlayout-label>
										<powersi:editorlayout-input>
											<button type="button" id="btn-load" data-loading-text="正在加载..." class="btn btn-primary">
												加载状态
											</button>
											
											<button type="button" class="btn btn-default" data-toggle="button">
												状态切换
											</button>
										</powersi:editorlayout-input>
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:editorlayout-label>
											复选框(data-toggle)
										</powersi:editorlayout-label>
										<powersi:editorlayout-input>
											<div class="btn-group" data-toggle="buttons">
											  <label class="btn btn-default">
											    <input type="checkbox"> 选择 1
											  </label>
											  <label class="btn btn-default">
											    <input type="checkbox"> 选择 2
											  </label>
											  <label class="btn btn-default">
											    <input type="checkbox"> 选择 3
											  </label>
											</div>
										</powersi:editorlayout-input>
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:editorlayout-label>
											单选框(data-toggle)
										</powersi:editorlayout-label>
										<powersi:editorlayout-input>
											<div class="btn-group" data-toggle="buttons">
											  <label class="btn btn-default">
											    <input type="radio" name="options" id="option1"> 选择 1
											  </label>
											  <label class="btn btn-default">
											    <input type="radio" name="options" id="option2"> 选择 2
											  </label>
											  <label class="btn btn-default">
											    <input type="radio" name="options" id="option3"> 选择 3
											  </label>
											</div>
										</powersi:editorlayout-input>
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:editorlayout-label>
											按钮组(css)
										</powersi:editorlayout-label>
										<powersi:editorlayout-input>
											<div class="btn-group">
											  <button type="button" class="btn btn-default">左</button>
											  <button type="button" class="btn btn-default">中</button>
											  <button type="button" class="btn btn-default">右</button>
											</div>
										</powersi:editorlayout-input>
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:editorlayout-label>
											工具条1(css)
										</powersi:editorlayout-label>
										<powersi:editorlayout-input>
											<div class="btn-toolbar" role="toolbar">
										        <div class="btn-group">
										          <button type="button" class="btn btn-default">1</button>
										          <button type="button" class="btn btn-default">2</button>
										          <button type="button" class="btn btn-default">3</button>
										          <button type="button" class="btn btn-default">4</button>
										        </div>
										        <div class="btn-group">
										          <button type="button" class="btn btn-default">5</button>
										          <button type="button" class="btn btn-default">6</button>
										          <button type="button" class="btn btn-default">7</button>
										        </div>
										        <div class="btn-group">
										          <button type="button" class="btn btn-default">8</button>
										        </div>
										    </div>
										</powersi:editorlayout-input>
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:editorlayout-label>
											工具条2(css)
										</powersi:editorlayout-label>
										<powersi:editorlayout-input>
											<div class="btn-group">
										        <button type="button" class="btn btn-default">按钮1</button>
										        <button type="button" class="btn btn-default">按钮2</button>
										
										        <div class="btn-group">
										          <button id="btnGroupDrop1" type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
										            下拉菜单
										            <i class="icon-caret-down right-icon"></i>
										          </button>
										          <ul class="dropdown-menu" role="menu" aria-labelledby="btnGroupDrop1">
										            <li><a href="#">下拉菜单1</a></li>
										            <li><a href="#">下拉菜单2</a></li>
										            <li class="divider"></li>
										            <li><a href="#">下拉菜单3</a></li>
										          </ul>
										        </div>
										     </div>
										</powersi:editorlayout-input>
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:editorlayout-label>
											块状按钮(css)
										</powersi:editorlayout-label>
										<powersi:editorlayout-input>
											<button type="button" class="btn btn-warning btn-block" title="高端大气上档次" onclick="alert(this.title)">
												<i class="icon-apple"></i><i class="icon-html5"></i>
												<b>土豪金</b>
												<i class="icon-thumbs-up-alt right-icon"></i><i class="icon-sun right-icon"></i>
											</button>
										</powersi:editorlayout-input>
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:editorlayout-label>
											下拉按钮data-hover(css) 
										</powersi:editorlayout-label>
										<powersi:editorlayout-input>
											<div class="btn-group">
												<button data-toggle="dropdown" data-hover="dropdown" class="btn btn-primary dropdown-toggle">
													下拉按钮1
													<i class="icon-caret-down right-icon"></i>
												</button>

												<ul class="dropdown-menu">
													<li>
														<a href="#">Action</a>
													</li>

													<li>
														<a href="#">Another action</a>
													</li>

													<li>
														<a href="#">Something else here</a>
													</li>

													<li class="divider"></li>

													<li>
														<a href="#">Separated link</a>
													</li>
												</ul>
											</div>
											
											<div class="btn-group">
												<button type="button" class="btn btn-primary">下拉按钮2</button>

												<button data-toggle="dropdown" data-hover="dropdown" class="btn btn-primary dropdown-toggle">
													<span class="icon-caret-down only-icon"></span>
												</button>

												<ul class="dropdown-menu dropdown-info pull-right">
													<li>
														<a href="#">Action</a>
													</li>

													<li>
														<a href="#">Another action</a>
													</li>

													<li>
														<a href="#">Something else here</a>
													</li>

													<li class="divider"></li>

													<li>
														<a href="#">Separated link</a>
													</li>
												</ul>
											</div>
										</powersi:editorlayout-input>
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:editorlayout-label>
											下拉按钮dropup(css)
										</powersi:editorlayout-label>
										<powersi:editorlayout-input>
											<div class="btn-group dropup">
												<button data-toggle="dropdown" class="btn btn-default dropdown-toggle">
													下拉按钮1
													<i class="icon-caret-down right-icon"></i>
												</button>

												<ul class="dropdown-menu">
													<li>
														<a href="#">Action</a>
													</li>

													<li>
														<a href="#">Another action</a>
													</li>

													<li>
														<a href="#">Something else here</a>
													</li>

													<li class="divider"></li>

													<li>
														<a href="#">Separated link</a>
													</li>
												</ul>
											</div>
											
											<div class="btn-group dropup">
												<button type="button" class="btn btn-default">下拉按钮2</button>

												<button data-toggle="dropdown" class="btn btn-default dropdown-toggle">
													<span class="icon-caret-down only-icon"></span>
												</button>

												<ul class="dropdown-menu dropdown-info pull-right">
													<li>
														<a href="#">Action</a>
													</li>

													<li>
														<a href="#">Another action</a>
													</li>

													<li>
														<a href="#">Something else here</a>
													</li>

													<li class="divider"></li>

													<li>
														<a href="#">Separated link</a>
													</li>
												</ul>
											</div>
										</powersi:editorlayout-input>
									</powersi:editorlayout-row>
								</powersi:editorlayout>
							</powersi:panelbox>
						</div>
					</div>
					<div class="row">
						<div class="col-12">
							<powersi:panelbox title="日期控件1" iconClass="icon-calendar">
								<powersi:editorlayout cols="4">
									<powersi:editorlayout-row>
										<powersi:textfield id="time1" name="time" required="true" mask="time" label="时间" />
										<powersi:textfield id="datetime1" name="datetime" required="true" mask="datetime" label="日期时间" />
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:textfield id="period1" name="period" required="true" mask="period" label="期间" />
										<powersi:textfield id="date1" name="date" required="true" mask="date" label="日期" onchange="alert(this.value)" format="minDate: '2016-01-01', maxDate: '2020-12-31'"/>
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:textfield id="year1" name="year" required="true" mask="year" label="年" onchange="alert(this.value);" />
										<powersi:textfield id="date9" name="date" required="true" mask="date" label="自定义格式" data-format="yyyy-MM-dd" />
									</powersi:editorlayout-row>
								</powersi:editorlayout>
							</powersi:panelbox>
							<powersi:panelbox title="日期控件2" iconClass="icon-calendar">
								<powersi:editorlayout cols="4">
									<powersi:editorlayout-row>
										<powersi:date id="time2" name="time" required="true" format="HH:mm" label="时间" drops="up" opens="left">
											<powersi:date-range label="上班" startDate="08:30" />
											<powersi:date-range label="中班" startDate="13:30" />
											<powersi:date-range label="下班" startDate="17:30" />
										</powersi:date>
										<powersi:date id="datetime2" iname="datetime" required="true" mask="datetime" label="日期时间" drops="up" />
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
									<powersi:date id="period2" name="period" required="true" mask="period" label="期间" drops="up" />
									<powersi:date id="date2" name="date" required="true" mask="date" label="日期" drops="up" opens="left" />
								</powersi:editorlayout-row>
								</powersi:editorlayout>
							</powersi:panelbox>
						</div>
					</div>
					<div class="row">
						<div class="col-12">
							<powersi:panelbox key="panel_query" title="spinner">
								<powersi:editorlayout cols="4">
									<powersi:editorlayout-row>
										<powersi:spinner name="spinner1" id="spinner1" min="0" max="999999" step="1" label="整数" mouseWheel="true" required="true" value="10" data-hotkey="alt+9"/>
										<powersi:spinner name="spinner2" id="spinner2" min="0" max="100" numberFormat="n" step="0.11" label="浮点数" mouseWheel="true" required="true" value="8.8" data-hotkey="alt+0"/>
									</powersi:editorlayout-row>
								</powersi:editorlayout>
							</powersi:panelbox>
						</div>
					</div>
					<div class="row">
						<div class="col-12">
							<powersi:panelbox key="panel_query" title="range(spinner)">
								<powersi:editorlayout cols="25%,75%">
									<powersi:editorlayout-row>
										<powersi:editorlayout-label>
											<powersi:label>输入整数<span class="hotkey ctrl">1</span> - <span class="hotkey ctrl">2</span></powersi:label>
										</powersi:editorlayout-label>
										<powersi:editorlayout-input>
											<div class="input-group">
												<powersi:spinner name="spinner_min" id="spinner_min" min="0" max="999999" step="1" mouseWheel="true" required="true"  validate="integer,numberRange[spinnerGroup]" data-hotkey="ctrl+1" />
												<span class="input-group-addon">-</span>
												<powersi:spinner name="spinner_max" id="spinner_max" min="0" max="999999" step="1" mouseWheel="true" required="true"  validate="integer,numberRange[spinnerGroup]" data-hotkey="ctrl+2" />
											</div>
										</powersi:editorlayout-input>
									</powersi:editorlayout-row>
								</powersi:editorlayout>
							</powersi:panelbox>
						</div>
					</div>
					<div class="row">
						<div class="col-12">
							<powersi:panelbox key="panel_query" title="range(integer)">
								<powersi:editorlayout cols="25%,75%">
									<powersi:editorlayout-row>
										<powersi:editorlayout-label>
											<powersi:label>输入整数<span class="hotkey ctrl">3</span> - <span class="hotkey ctrl">4</span></powersi:label>
										</powersi:editorlayout-label>
										<powersi:editorlayout-input>
											<div class="input-group">
												<powersi:textfield id="rowscount_min" name="rowscount_min" validate="integer,numberRange[rowsCount]" data-hotkey="ctrl+3" />
												<span class="input-group-addon">-</span>
												<powersi:textfield id="rowscount_max" name="rowscount_max" validate="integer,numberRange[rowsCount]" data-hotkey="ctrl+4" />
											</div>
										</powersi:editorlayout-input>
									</powersi:editorlayout-row>
								</powersi:editorlayout>
							</powersi:panelbox>
						</div>
					</div>
					<div class="row">
						<div class="col-12">
							<powersi:panelbox key="panel_query" title="range(float)">
								<powersi:editorlayout cols="25%,75%">
									<powersi:editorlayout-row>
										<powersi:editorlayout-label>
											<powersi:label>输入浮点数输入整数<span class="hotkey ctrl">5</span> - <span class="hotkey ctrl">6</span></powersi:label>
										</powersi:editorlayout-label>
										<powersi:editorlayout-input>
											<div class="input-group">
												<powersi:textfield id="float_min" name="float_min" validate="number,numberRange[floatGroup]" data-hotkey="ctrl+5" />
												<span class="input-group-addon">-</span>
												<powersi:textfield id="float_max" name="float_max" validate="number,numberRange[floatGroup]" data-hotkey="ctrl+6" />
											</div>
										</powersi:editorlayout-input>
									</powersi:editorlayout-row>
								</powersi:editorlayout>
							</powersi:panelbox>
						</div>
					</div>
				</div>
			</div>
		</div>
	</powersi:form>
	<div class="space-y"></div>
	<div class="divCenter">
		<powersi:button id="btnSubmit" key="button_ok" label="提交测试" onclick="doSubmit()" cssClass="btn btn-success" data-hotkey="f1"></powersi:button>
		<powersi:button id="btnReset" key="button_reset" label="重置测试" onclick="doReset()" cssClass="btn btn-warning" title="恢复初始界面" data-hotkey="f2"></powersi:button>
		<powersi:button id="btnClear" key="button_clear" label="清屏测试" onclick="doClear()" cssClass="btn btn-danger" title="清空界面显示" data-hotkey="f3"></powersi:button>
		<powersi:button id="btnFormAjax" label="ajax数据" buttonIcon="icon-external-link" onclick="doFormAjax()" cssClass="btn btn-info" title="查看表单ajax对象" data-hotkey="f4"></powersi:button>
		<powersi:button id="btnForm2Json" label="form转换json" buttonIcon="icon-chevron-sign-right" onclick="doForm2Json()" cssClass="btn btn-info" title="查看表单json对象" data-hotkey="f7"></powersi:button>
		<powersi:button id="btnJson2Form" label="json转换form" buttonIcon="icon-chevron-sign-left" onclick="doJson2Form()" cssClass="btn btn-info" title="填充表单json对象" data-hotkey="f8"></powersi:button>
		<powersi:button id="btnDisable" key="button_disable" label="禁用表单" onclick="disableAll()" cssClass="btn btn-danger" data-hotkey="alt+d"></powersi:button>
		<powersi:button id="btnEnable" key="button_enable" label="启用表单" onclick="enableAll()" cssClass="btn btn-success" data-hotkey="alt+e"></powersi:button>
	</div>
<powersi:errors />
<div class="hidden">
	<div id="popover-select2">
		<div class="list-group">
		  <a class="list-group-item" href="#">
		  	<h5 class="textDanger">multiple</h5>
    		<p class="list-group-item-text">是否多选</p>
		  </a>
		  <a class="list-group-item" href="#">
		  	<h5 class="textDanger">data-show-search</h5>
    		<p class="list-group-item-text">是否显示搜索栏(单选有效)</p>
		  </a>
		  <a class="list-group-item" href="#">
		  	<h5 class="textDanger">data-show-clear</h5>
    		<p class="list-group-item-text">是否显示清除(存在placeholder时有效)</p>
		  </a>
		  <a class="list-group-item" href="#">
		  	<h5 class="textDanger">data-auto-width</h5>
    		<p class="list-group-item-text">是否下拉框宽度自适应(根据内容调整宽度)</p>
		  </a>
		  <a class="list-group-item" href="#">
		  	<h5 class="textDanger">data-placeholder</h5>
    		<p class="list-group-item-text">输入提示信息(多选有效)</p>
		  </a>
		  <a class="list-group-item" href="#">
		  	<h5 class="textDanger">其他说明</h5>
    		<p class="list-group-item-text">
    			<p>1.初始化设置class="select2"或使用initSelect2($el,options)</p>
    			<p>2.使用js赋值需显式调用$(el).change()，刷新select2值</p>
    		</p>
		  </a>
		</div>
	</div>
	<div id="popover-combobox">
		<div class="list-group">
			<li class="list-group-item">
				<p>参见API接口文档</p>
			</li>
		</div>
	</div>
	<div id="popover-tooltip">
		<div class="list-group">
			<li class="list-group-item">
				<p>出于性能方面的考虑，工具提示和弹框组件的data属性api是选择性加入的，也就是说你必须自己初始化他们</p>
			</li>
		</div>
	</div>
	<div id="popover-popover">
		<div class="list-group">
		  <a class="list-group-item" href="#">
		  	<h5 class="textDanger">data-html</h5>
    		<p class="list-group-item-text">是否显示html格式的标题和内容</p>
		  </a>
		  <a class="list-group-item" href="#">
		  	<h5 class="textDanger">data-placement</h5>
    		<p class="list-group-item-text">显示位置(top | bottom | left | right | auto)</p>
		  </a>
		  <a class="list-group-item" href="#">
		  	<h5 class="textDanger">data-trigger</h5>
    		<p class="list-group-item-text">触发方式(click | hover | focus | manual)</p>
		  </a>
		  <a class="list-group-item" href="#">
		  	<h5 class="textDanger">data-original-title</h5>
    		<p class="list-group-item-text">标题</p>
		  </a>
		   <a class="list-group-item" href="#">
		  	<h5 class="textDanger">data-content</h5>
    		<p class="list-group-item-text">内容</p>
		  </a>
		  <a class="list-group-item" href="#">
		  	<h5 class="textDanger">其他说明</h5>
    		<p class="list-group-item-text">
    			<p>1.调用$(el).popover()初始化</p>
    			<p>2.初始化参数在html属性维护或在js代码中指定</p>
    		</p>
		  </a>
		</div>
	</div>
	
	<div id="popover-button">
		<div class="list-group">
			<a class="list-group-item" href="#">
		  		<h5 class="textDanger">data-toggle</h5>
    			<p class="list-group-item-text">
    				<p>标注data-toggle的需要初始化$(el).button()</p>
    				<p>有button和buttons两种</p>
    			</p>
		 	</a>
		 	<a class="list-group-item" href="#">
		  		<h5 class="textDanger">css</h5>
    			<p class="list-group-item-text">
    				<p class="textSuccess">按钮</p><p> btn</p>
    				<p class="textSuccess">颜色</p><p>btn-default(白)<br/>btn-primary(深蓝)<br/>btn-success(绿)<br/>btn-info(天蓝)<br/>btn-warning(黄)<br/>btn-danger(红)</p> 
    				<p class="textSuccess">大小</p><p>btn-minier btn-xs btn-sm btn-lg</p>
    				<p class="textSuccess">块状</p><p>btn-block</p>
    				<p class="textSuccess">图标</p><p>图标在文字右边必须显式添加right-icon</p>
    			</p>
		 	</a>
		</div>
	</div>
</div>
<script type="text/javascript">
$(function() {
	//初始化帮助按钮
	$('[rel=popover]').popover({
		html: true,
		trigger: 'click',
		content: function() {
			return $($(this).data('target')).html();
		},
		container: 'body'
	});
	//初始化popover
	$('[data-toggle="popover"]').popover({
		html: true
	});
	//初始化tooltip
	$('[data-toggle="tooltip"]').tooltip();
	
	//初始化button
	$(':button.btn').button();
	
	$('#btn-load').click(function(){
		var btn = $(this);
        btn.button('loading');
        setTimeout(function () {
            btn.button('reset');
        }, 3000);
	});
	
	//手动初始化部门
	initSelect2('#dept_id', {
   		formatSelection: function(state){
   			return $.trim(state.text);
   		}
   	});
	
	//初始化本地自动完成
	$('#ac1').autocomplete({
      source: nations,
      minLength: 0,
      autoFocus: false,
      position: { my : "left bottom", at: "left top" }/*向上显示*/
    });
	//初始化隐藏自动弹出的菜单
	setTimeout(function(){$( "#ac1" ).autocomplete( "close");}, 500);
	
	//初始化远程自动完成
	$( "#ac2" ).autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	          url: rootPath + '/sample/Sample!queryCatalog.action?centerid=440100&searchType=code',
	          data:{
	        	  searchTerm: request.term,
	        	  pagesize: 10
	          },
	          dataType: "json",
	          type:'POST',
	          success: function( data ) {
	            response( $.map( data.rows, function( item ) {
	              return {
	                label: item.ake001 + ' ' + item.ake002,
	                value: item.ake001
	              };
	            }));
	          }
	        });
	   	},
	   	minLength: 1,
	   	open: function() {
	   		/*设置宽度*/
	        $('#ac2').autocomplete("widget").width(300);
	    } 
	});
});

function doSubmit(){
	if(!checkForm($('#form1'))){
		return;
	}
	alert(powersi.tostring($("#form1").formSerialize()));
}

function doReset(){
	resetForm($('#form1'));
}

function doClear(){
	clearForm($('#form1'));
}

function doFormAjax(){
	var data = $('#form1').serialize();
	alert(powersi.tostring(data));
}

var json;
function doForm2Json(){
	try{
		json = form2json('#form1');
		alert(powersi.tostring(json));
	} catch(ex){
		alert(ex.message);
	}
}

function doJson2Form(){
	try{
		if(!json){
			alert("请先点击form转换json，才能执行json转换form。");
			return;
		}
		
		//先清屏
		clearForm('#form1');
		
		//再填充
		json2form('#form1', json);
		alert(powersi.tostring(json));
	} catch(ex){
		alert(ex.message);
	}
}

/*combogrid初始化*/
var centerId = '440100';
var actionUrl = rootPath + '/sample/Sample!queryCatalog.action?centerid=' + centerId + '&searchType=py';
$(function() {
	$("#stext").combogrid({
		rows: 10,
		minLength : 2,
		autoChoose : false,
		searchIcon : true,
		alternate : true,
		width : '500px',
		url : actionUrl,
		colModel : [ 
					 {'columnName' : 'rownumber', 'width' : '10', 'label' : '#'},
		             {'columnName' : 'ake001', 'width' : '20', 'label' : '编码'}, 
		             {'columnName' : 'ake002', 'width' : '50', 'label' : '名称'}, 
		             {'columnName' : 'aae013', 'width' : '20', 'label' : '备注'} 
		             ],
		select : function(event, ui) {
			alert(powersi.tostring(ui.item));
			return false;
		}
	});

	//绑定快捷键
	$(document).bind("keydown", 'ctrl+s',function (evt){
        popupMessage("Ctrl+S");
        return false;
    });
});

function changeSearchType(sel) {
	actionUrl = rootPath + '/sample/Sample!queryCatalog.action?centerid=' + centerId + '&searchType=' + sel.value;
	$("#stext" ).combogrid( "option", "url", actionUrl);
}

function disableAll(){
	//排除启用表达按钮
	setDisabled(":input:not('#btnEnable')");
	
	//禁用日期控件
	setDisabled(".input-daterange");
}

function enableAll(){
	setEnabled(":input");
	
	//启用日期控件
	setEnabled(".input-daterange");
}
</script>
</body>
</powersi:html>