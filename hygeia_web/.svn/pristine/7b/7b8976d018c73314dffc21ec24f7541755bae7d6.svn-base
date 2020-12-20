<%@page import="java.util.LinkedHashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<%
	java.util.Map map60 = new LinkedHashMap();
	for(int i = 0; i < 60; i ++){
		map60.put(Integer.valueOf(i), (i < 10 ? "0" : "") + String.valueOf(i));
	}
	request.setAttribute("map60", map60);
	
	java.util.Map mapHour = new LinkedHashMap();
	for(int i = 0; i < 24; i ++){
		mapHour.put(Integer.valueOf(i), (i < 10 ? "0" : "") + String.valueOf(i));
	}
	request.setAttribute("mapHour", mapHour);
	
	java.util.Map mapDay = new LinkedHashMap();
	for(int i = 1; i <= 31; i ++){
		mapDay.put(Integer.valueOf(i), (i < 10 ? "0" : "") + String.valueOf(i));
	}
	request.setAttribute("mapDay", mapDay);
	
	java.util.Map mapMonth = new LinkedHashMap();
	for(int i = 1; i <= 12; i ++){
		mapMonth.put(Integer.valueOf(i), (i < 10 ? "0" : "") + String.valueOf(i));
	}
	request.setAttribute("mapMonth", mapMonth);
	
	String[] dayOfWeekName = new String[]{"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"};
	String[] dayOfWeekValue = new String[]{"2","3","4","5","6","7","1"};
	java.util.Map mapWeek = new LinkedHashMap();
	for(int i = 0; i < 7; i ++){
		mapWeek.put(dayOfWeekValue[i], dayOfWeekName[i]);
	}
	request.setAttribute("mapWeek", mapWeek);
	
	String[] weekOfMonthName = new String[]{"第一周", "第二周", "第三周", "第四周", "第五周", "最后一周"};
	String[] weekOfMonthValue = new String[]{"1", "2", "3", "4", "5", "L"};
	java.util.Map mapWeekOfMonth = new LinkedHashMap();
	for(int i = 0; i < 5; i ++){
		mapWeekOfMonth.put(weekOfMonthValue[i], weekOfMonthName[i]);
	}
	request.setAttribute("mapWeekOfMonth", mapWeekOfMonth);
%>
<powersi:html>
<head>
<powersi:head title="表达式生成器" />
<style type="text/css">
.tab-pane{
	padding-top: 0;
	text-align: left;
}
.lbSpinner{
	float: left;
	margin-left: 15px;
}
.divSpinner{
	width: 55px;
	float: left;
	margin-right: 15px;
	margin-left: 5px;
}
.divSel{
	text-align: left;
	margin: 0 20px;
}
.linkButton{
	margin-top: 4px;
	margin-left: 12px;
}
#cbxDayLast{
	margin-left: 20px;
}

.divRange {
	width: auto;
	float: left;
	margin-left: 15px;
}

.divRange .select{
	width: 100px;
}

.divRange .input-group-addon{
	width: 30px;
	border-left: 0;
	border-right: 0;
}

#dlgHelp .tdLabel{
	text-align: left;
}
</style>
</head>
<body class="grid">
<div style="width:550px;" class="divCenter">
<powersi:panelbox title="Cron条件">
	<form id="formCond">
	<div class="tabbable">
		<ul class="nav nav-tabs nav-fixed" id="tabs1">
			<li class="active">
				<a data-toggle="tab" href="#tab-pane1" id="tab1">
					<i class="icon-time red"></i>
					秒
				</a>
			</li>

			<li>
				<a data-toggle="tab" href="#tab-pane2" id="tab2">
					<i class="icon-time orange"></i>
					分钟
				</a>
			</li>

			<li>
				<a data-toggle="tab" href="#tab-pane3" id="tab3">
					<i class="icon-time yellow"></i>
					小时
				</a>
			</li>
			
			<li>
				<a data-toggle="tab" href="#tab-pane4" id="tab3">
					<i class="icon-time green"></i>
					天
				</a>
			</li>
			
			<li>
				<a data-toggle="tab" href="#tab-pane5" id="tab3">
					<i class="icon-time blue"></i>
					月
				</a>
			</li>
			
			<li>
				<a data-toggle="tab" href="#tab-pane6" id="tab3">
					<i class="icon-time pink"></i>
					周
				</a>
			</li>
		</ul>
	</div>
	<div class="tab-content">
		<div id="tab-pane1" class="tab-pane active">
			<div class="row">
				<div class="col-12">
					<input type="radio" name="secondSel" id="rbSecondSel0" value="0" class="radio" checked="checked"/>
					<label for="rbSecondSel0" class="textLabel textInfo">缺省(0)</label>
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<div class="floatLeft">
						<input type="radio" name="secondSel" id="rbSecondSel1" value="1" class="radio" />
						<label for="rbSecondSel1" class="textLabel textInfo">周期</label>
					</div>
					
					<label for="spSecondStart" class="textLabel lbSpinner">
						开始秒数
					</label>
					<div class="divSpinner">
						<powersi:spinner name="secondStart" id="spSecondStart" min="0" max="59" step="1" mouseWheel="true" value="0" />
					</div>
					
					<label for="spSecondEnd" class="textLabel lbSpinner">
						结束秒数
					</label>
					<div class="divSpinner">
						<powersi:spinner name="secondEnd" id="spSecondEnd" min="-1" max="59" step="1" mouseWheel="true" value="-1" />
					</div>
					
					<label for="secondRepeat" class="textLabel lbSpinner">
						间隔秒数
					</label>
					<div class="divSpinner">
						<powersi:spinner name="secondRepeat" id="spSecondRepeat" min="0" max="60" step="1" mouseWheel="true" value="0" />
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<input type="radio" name="secondSel" id="rbSecondSel2" value="2" class="radio" />
					<label for="rbSecondSel2" class="textLabel textInfo">指定</label>
					
					<input type="button" value="清除" class="linkButton" onclick="clearSel('second')" />
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<div class="divSel">
						<powersi:checkboxlist name="seconds" list="#request.map60" listKey="key" listValue="value" repeatColumns="12" />					
					</div>
				</div>
			</div>
		</div>
		<div id="tab-pane2" class="tab-pane">
			<div class="row">
				<div class="col-12">
					<input type="radio" name="minuteSel" id="rbMinuteSel0" value="0" class="radio" checked="checked"/>
					<label for="rbMinuteSel0" class="textLabel textInfo">缺省(0)</label>
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<div class="floatLeft">
						<input type="radio" name="minuteSel" id="rbMinuteSel1" value="1" class="radio" />
						<label for="rbMinuteSel1" class="textLabel textInfo">周期</label>
					</div>
					
					<label for="spMinuteStart" class="textLabel lbSpinner">
						开始分钟
					</label>
					<div class="divSpinner">
						<powersi:spinner name="minuteStart" id="spMinuteStart" min="0" max="59" step="1" mouseWheel="true" value="0" />
					</div>
					
					<label for="spMinuteEnd" class="textLabel lbSpinner">
						结束分钟
					</label>
					<div class="divSpinner">
						<powersi:spinner name="minuteEnd" id="spMinuteEnd" min="-1" max="59" step="1" mouseWheel="true" value="-1" />
					</div>
					
					<label for="minuteRepeat" class="textLabel lbSpinner">
						间隔分钟
					</label>
					<div class="divSpinner">
						<powersi:spinner name="minuteRepeat" id="spMinuteRepeat" min="0" max="59" step="1" mouseWheel="true" value="0" />
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<input type="radio" name="minuteSel" id="rbMinuteSel2" value="2" class="radio" />
					<label for="rbMinuteSel2" class="textLabel textInfo">指定</label>
					
					<input type="button" value="清除" class="linkButton" onclick="clearSel('minute')" />
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<div class="divSel">
						<powersi:checkboxlist name="minutes" list="#request.map60" listKey="key" listValue="value" repeatColumns="12" />
					</div>
				</div>
			</div>
		</div>
		<div id="tab-pane3" class="tab-pane">
			<div class="row">
				<div class="col-12">
					<input type="radio" name="hourSel" id="rbHourSel0" value="0" class="radio" checked="checked"/>
					<label for="rbHourSel0" class="textLabel textInfo">缺省(0)</label>
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<div class="floatLeft">
						<input type="radio" name="hourSel" id="rbHourSel1" value="1" class="radio" />
						<label for="rbHourSel1" class="textLabel textInfo">周期</label>
					</div>
					
					<label for="spHourStart" class="textLabel lbSpinner">
						开始小时
					</label>
					<div class="divSpinner">
						<powersi:spinner name="hourStart" id="spHourStart" min="0" max="23" step="1" mouseWheel="true" value="0" />
					</div>
					
					<label for="spHourEnd" class="textLabel lbSpinner">
						结束小时
					</label>
					<div class="divSpinner">
						<powersi:spinner name="hourEnd" id="spHourEnd" min="-1" max="23" step="1" mouseWheel="true" value="-1" />
					</div>
					
					<label for="hourRepeat" class="textLabel lbSpinner">
						间隔小时
					</label>
					<div class="divSpinner">
						<powersi:spinner name="hourRepeat" id="spHourRepeat" min="0" max="23" step="1" mouseWheel="true" value="0" />
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<input type="radio" name="hourSel" id="rbHourSel2" value="2" class="radio" />
					<label for="rbHourSel2" class="textLabel textInfo">指定</label>
					
					<input type="button" value="清除" class="linkButton" onclick="clearSel('hour')" />
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<div class="divSel">
						<powersi:checkboxlist name="hours" list="#request.mapHour" listKey="key" listValue="value" repeatColumns="12" />
					</div>
				</div>
			</div>
		</div>
		<div id="tab-pane4" class="tab-pane">
			<div class="row">
				<div class="col-12">
					<input type="radio" name="daySel" id="rbDaySel0" value="0" class="radio" checked="checked"/>
					<label for="rbDaySel0" class="textLabel textInfo">缺省(每天)</label>
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<div class="floatLeft">
						<input type="radio" name="daySel" id="rbDaySel1" value="1" class="radio" />
						<label for="rbDaySel1" class="textLabel textInfo">周期</label>
					</div>
					
					<label for="spDayStart" class="textLabel lbSpinner">
						开始天数
					</label>
					<div class="divSpinner">
						<powersi:spinner name="dayStart" id="spDayStart" min="0" max="31" step="1" mouseWheel="true" value="0" />
					</div>
					
					<label for="spDayEnd" class="textLabel lbSpinner">
						结束天数
					</label>
					<div class="divSpinner">
						<powersi:spinner name="dayEnd" id="spDayEnd" min="-1" max="31" step="1" mouseWheel="true" value="-1" />
					</div>
					
					<label for="dayRepeat" class="textLabel lbSpinner">
						间隔天数
					</label>
					<div class="divSpinner">
						<powersi:spinner name="dayRepeat" id="spDayRepeat" min="0" max="31" step="1" mouseWheel="true" value="0" />
					</div>
				</div>
			</div>
			
			<div class="row" style="margin-top: 3px;">
				<div class="col-3">
					<div class="floatLeft">
						<input type="radio" name="daySel" id="rbDaySel3" value="3" class="radio" />
						<label for="rbDaySel3" class="textLabel textInfo">本月最后一天</label>
					</div>
				</div>
				
				<div class="col-3">
					<div class="floatLeft">
						<input type="radio" name="daySel" id="rbDaySel4" value="4" class="radio" />
						<label for="rbDaySel4" class="textLabel textInfo">本月最后工作日</label>
					</div>
				</div>
				
				<div class="col-6">
					<div class="floatLeft">
						<input type="radio" name="daySel" id="rbDaySel5" value="5" class="radio" />
						<label for="rbDaySel5" class="textLabel textInfo">最近的工作日</label>
					</div>
					
					<div class="divSpinner">
						<powersi:spinner name="dayWork" id="spDayWork" min="1" max="31" step="1" mouseWheel="true" value="1" />
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<input type="radio" name="daySel" id="rbDaySel2" value="2" class="radio" />
					<label for="rbDaySel2" class="textLabel textInfo">指定</label>
					
					<input type="button" value="清除" class="linkButton" onclick="clearSel('day')" />
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<div class="divSel">
						<powersi:checkboxlist name="days" list="#request.mapDay" listKey="key" listValue="value" repeatColumns="12" />
					</div>
				</div>
			</div>
		</div>
		<div id="tab-pane5" class="tab-pane">
			<div class="row">
				<div class="col-12">
					<input type="radio" name="monthSel" id="rbMonthSel0" value="0" class="radio" checked="checked"/>
					<label for="rbMonthSel0" class="textLabel textInfo">缺省(每月)</label>
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<input type="radio" name="monthSel" id="rbMonthSel2" value="2" class="radio" />
					<label for="rbMonthSel2" class="textLabel textInfo">指定</label>
					
					<input type="button" value="清除" class="linkButton" onclick="clearSel('month')" />
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<div class="divSel">
						<powersi:checkboxlist name="months" list="#request.mapMonth" listKey="key" listValue="value" repeatColumns="12" />
					</div>
				</div>
			</div>
		</div>
		<div id="tab-pane6" class="tab-pane">
			<div class="row">
				<div class="col-12">
					<input type="radio" name="weekSel" id="rbWeekSel0" value="0" class="radio" checked="checked"/>
					<label for="rbWeekSel0" class="textLabel textInfo">缺省(无)</label>
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<div class="floatLeft">
						<input type="radio" name="weekSel" id="rbWeekSel1" value="1" class="radio" />
						<label for="rbWeekSel1" class="textLabel textInfo">周期</label>
					</div>
					
					<div class="divRange">
						<div class="input-group">
							<powersi:codeselect id="spWeekStart" name="weekStart" list="#request.mapWeek" listKey="key" listValue="value" />
							<span class="input-group-addon">-</span>
							<powersi:codeselect id="spWeekEnd" name="weekEnd" list="#request.mapWeek" listKey="key" listValue="value" />
						</div>
					</div>
				</div>
			</div>
			
			<div class="row" style="margin-top: 3px">
				<div class="col-12">
					<div class="floatLeft">
						<input type="radio" name="weekSel" id="rbWeekSel3" value="3" class="radio" />
						<label for="rbWeekSel3" class="textLabel textInfo">周次</label>
					</div>
					
					<div class="divRange">
						<div class="input-group">
							<powersi:codeselect id="spWeekNum" name="weekNum" list="#request.mapWeekOfMonth" listKey="key" listValue="value" />
							<span class="input-group-addon">&</span>
							<powersi:codeselect id="spWeekDay" name="weekDay" list="#request.mapWeek" listKey="key" listValue="value" />
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<input type="radio" name="weekSel" id="rbWeekSel2" value="2" class="radio" />
					<label for="rbWeekSel2" class="textLabel textInfo">指定</label>
					
					<input type="button" value="清除" class="linkButton" onclick="clearSel('week')" />
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<div class="divSel">
						<powersi:checkboxlist name="weeks" list="#request.mapWeek" listKey="key" listValue="value" repeatColumns="12" />
						<span class="textError">
							注：周条件不为空，自动覆盖天条件设置(即周条件和天条件互斥，优先周条件设置)
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="divButton">
		<powersi:button id="btnGen" buttonIcon="icon-building" value="生成表达式" onclick="buildCronExp()"></powersi:button>
		<powersi:button id="btnReset"  key="button_reset" onclick="resetCronExp()"></powersi:button>
		<powersi:button id="btnHelp" buttonIcon="icon-question" value="常用表达式" onclick="showHelp()"></powersi:button>
	</div>
</form>
</powersi:panelbox>
<powersi:panelbox title="Cron表达式">
	<powersi:form id="form1" disabled="true">
		<powersi:editorlayout cols="4">
			<powersi:editorlayout-row>
				<powersi:textfield id="txtCronExp" name="cron" label="Cron表达式" colspan="2" placeholder="可以手工输入符合Quartz Cron规范的表达式" required="true"></powersi:textfield>
				<powersi:editorlayout-button>
					<powersi:button id="btnCronExp" key="button_query" value="查询表达式结果" cssClass="btn btn-success" onclick="queryCropnExp()"></powersi:button>
				</powersi:editorlayout-button>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="txtCronDate" name="date" label="结果查询日期" mask="datetime"></powersi:textfield>
				<powersi:spinner id="txtCronCount" name="count" min="1" max="10000" step="100" mouseWheel="true" value="100" label="结果记录数" required="true" />
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textarea id="txtCronResult" label="表达式结果" cssClass="bordered" colspan="3" readonly="true"></powersi:textarea>
			</powersi:editorlayout-row>
		</powersi:editorlayout>
	</powersi:form>
</powersi:panelbox>
</div>
<div class="hidden">
	<div id="dlgHelp">
		<powersi:editorlayout cols="35%,65%">
			<powersi:editorlayout-head>
				<powersi:editorlayout-row>
					<powersi:editorlayout-cell>
						表达式
					</powersi:editorlayout-cell>
					<powersi:editorlayout-cell>
						说明
					</powersi:editorlayout-cell>
				</powersi:editorlayout-row>
			</powersi:editorlayout-head>
			<powersi:editorlayout-row>
				<powersi:editorlayout-label>
					*/30 * * * * ?
				</powersi:editorlayout-label>
				<powersi:editorlayout-input>
					每隔30秒
				</powersi:editorlayout-input>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:editorlayout-label>
					* */10 * * * ?
				</powersi:editorlayout-label>
				<powersi:editorlayout-input>
					每隔10分钟
				</powersi:editorlayout-input>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:editorlayout-label>
					0 0 10,14,16 * * ?
				</powersi:editorlayout-label>
				<powersi:editorlayout-input>
					每天上午10点，下午2点，4点
				</powersi:editorlayout-input>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:editorlayout-label>
					0 0/30 9-17 * * 2-6
				</powersi:editorlayout-label>
				<powersi:editorlayout-input>
					星期一到星期五每天9点到17点每30分钟
				</powersi:editorlayout-input>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:editorlayout-label>
					0 0 12 ? * WED
				</powersi:editorlayout-label>
				<powersi:editorlayout-input>
					每个星期三中午12点
				</powersi:editorlayout-input>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:editorlayout-label>
					0 0 12 * * ?
				</powersi:editorlayout-label>
				<powersi:editorlayout-input>
					每天中午12点
				</powersi:editorlayout-input>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:editorlayout-label>
					0 15 10 ? * *
				</powersi:editorlayout-label>
				<powersi:editorlayout-input>
					每天上午10点15分
				</powersi:editorlayout-input>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:editorlayout-label>
					0 * 14 * * ?
				</powersi:editorlayout-label>
				<powersi:editorlayout-input>
					每天下午2点每1分钟
				</powersi:editorlayout-input>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:editorlayout-label>
					0 0/5 14 * * ?
				</powersi:editorlayout-label>
				<powersi:editorlayout-input>
					每天下午2点每5分钟
				</powersi:editorlayout-input>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:editorlayout-label>
					0 0/5 14,18 * * ?
				</powersi:editorlayout-label>
				<powersi:editorlayout-input>
					每天下午2点和6点每5分钟
				</powersi:editorlayout-input>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:editorlayout-label>
					0 0-5 14 * * ?
				</powersi:editorlayout-label>
				<powersi:editorlayout-input>
					每天下午2点到2点5分期间每1分钟
				</powersi:editorlayout-input>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:editorlayout-label>
					0 8,38 14 ? 3 WED
				</powersi:editorlayout-label>
				<powersi:editorlayout-input>
					每年三月的星期三的下午2:08和2:38
				</powersi:editorlayout-input>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:editorlayout-label>
					0 15 10 ? * MON-FRI
				</powersi:editorlayout-label>
				<powersi:editorlayout-input>
					周一至周五的上午10:15
				</powersi:editorlayout-input>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:editorlayout-label>
					0 15 10 15 * ?
				</powersi:editorlayout-label>
				<powersi:editorlayout-input>
					每月15日上午10:15
				</powersi:editorlayout-input>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:editorlayout-label>
					0 15 10 L * ?
				</powersi:editorlayout-label>
				<powersi:editorlayout-input>
					每月最后一日的上午10:15
				</powersi:editorlayout-input>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:editorlayout-label>
					0 15 10 LW * ?
				</powersi:editorlayout-label>
				<powersi:editorlayout-input>
					每月最后一个工作日的上午10:15
				</powersi:editorlayout-input>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:editorlayout-label>
					0 15 10 ? * 6L
				</powersi:editorlayout-label>
				<powersi:editorlayout-input>
					每月最后一个星期五上午10:15触发 
				</powersi:editorlayout-input>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:editorlayout-label>
					0 15 10 ? * 6#3
				</powersi:editorlayout-label>
				<powersi:editorlayout-input>
					每月第三个星期五上午10:15触发
				</powersi:editorlayout-input>
			</powersi:editorlayout-row>
		</powersi:editorlayout>
	</div>
</div>
<powersi:errors />
<script type="text/javascript">
$(function(){
	addHighlyAdaptive('txtCronResult');
	
	$(".tab-pane").height($('#tab-pane1').height());
	$(window).size(function(){
		$(".tab-pane").height($('#tab-pane1').height());
	});
	
	$('[name="secondSel"]').click(function(){
		setSecondStatus($('[name="secondSel"]:checked').val());
	});
	
	$('[name="minuteSel"]').click(function(){
		setMinuteStatus($('[name="minuteSel"]:checked').val());
	});
	
	$('[name="hourSel"]').click(function(){
		setHourStatus($('[name="hourSel"]:checked').val());
	});
	
	$('[name="daySel"]').click(function(){
		setDayStatus($('[name="daySel"]:checked').val());
	});
	
	$('[name="monthSel"]').click(function(){
		setMonthStatus($('[name="monthSel"]:checked').val());
	});
	
	$('[name="weekSel"]').click(function(){
		setWeekStatus($('[name="weekSel"]:checked').val());
	});
	
	init();
	
	var param = getDialogParam();
	if(param && param != ''){
		$('#txtCronExp').val(param);
	}
});

function init(){
	setSecondStatus('0');
	setMinuteStatus('0');
	setHourStatus('0');
	setDayStatus('0');
	setMonthStatus('0');
	setWeekStatus('0');
	
	$('#spWeekEnd').val('6');
	
	buildCronExp();
}

function resetCronExp() {
	resetForm('#formCond');
	
	init();
}

function setSecondStatus(flag){
	if(flag == '1'){
		$('[name="seconds"]').prop('disabled', true);
		$('#spSecondStart').prop('disabled', false);
		$('#spSecondEnd').prop('disabled', false);
		$('#spSecondRepeat').prop('disabled', false);
	} else if(flag == '2'){
		$('[name="seconds"]').prop('disabled', false);
		$('#spSecondStart').prop('disabled', true);
		$('#spSecondEnd').prop('disabled', true);
		$('#spSecondRepeat').prop('disabled', true);
	} else{
		$('[name="seconds"]').prop('disabled', true);
		$('#spSecondStart').prop('disabled', true);
		$('#spSecondEnd').prop('disabled', true);
		$('#spSecondRepeat').prop('disabled', true);
	}
}

function setMinuteStatus(flag){
	if(flag == '1'){
		$('[name="minutes"]').prop('disabled', true);
		$('#spMinuteStart').prop('disabled', false);
		$('#spMinuteEnd').prop('disabled', false);
		$('#spMinuteRepeat').prop('disabled', false);
	} else if(flag == '2'){
		$('[name="minutes"]').prop('disabled', false);
		$('#spMinuteStart').prop('disabled', true);
		$('#spMinuteEnd').prop('disabled', true);
		$('#spMinuteRepeat').prop('disabled', true);
	} else{
		$('[name="minutes"]').prop('disabled', true);
		$('#spMinuteStart').prop('disabled', true);
		$('#spMinuteEnd').prop('disabled', true);
		$('#spMinuteRepeat').prop('disabled', true);
	}
}

function setHourStatus(flag){
	if(flag == '1'){
		$('[name="hours"]').prop('disabled', true);
		$('#spHourStart').prop('disabled', false);
		$('#spHourEnd').prop('disabled', false);
		$('#spHourRepeat').prop('disabled', false);
	} else if(flag == '2'){
		$('[name="hours"]').prop('disabled', false);
		$('#spHourStart').prop('disabled', true);
		$('#spHourEnd').prop('disabled', true);
		$('#spHourRepeat').prop('disabled', true);
	} else{
		$('[name="hours"]').prop('disabled', true);
		$('#spHourStart').prop('disabled', true);
		$('#spHourEnd').prop('disabled', true);
		$('#spHourRepeat').prop('disabled', true);
	}
}

function setDayStatus(flag){
	if(flag == '1'){
		$('[name="hours"]').prop('disabled', true);
		$('#spDayStart').prop('disabled', false);
		$('#spDayEnd').prop('disabled', false);
		$('#spDayRepeat').prop('disabled', false);
		$('#spDayWork').prop('disabled', true);
	} else if(flag == '2'){
		$('[name="days"]').prop('disabled', false);
		$('#spDayStart').prop('disabled', true);
		$('#spDayEnd').prop('disabled', true);
		$('#spDayRepeat').prop('disabled', true);
		$('#spDayWork').prop('disabled', true);
	} else{
		$('[name="days"]').prop('disabled', true);
		$('#spDayStart').prop('disabled', true);
		$('#spDayEnd').prop('disabled', true);
		$('#spDayRepeat').prop('disabled', true);
		
		$('#spDayWork').prop('disabled', flag != '5');
	}
}

function setMonthStatus(flag){
	if(flag == '2'){
		$('[name="months"]').prop('disabled', false);
	} else{
		$('[name="months"]').prop('disabled', true);
	}
}

function setWeekStatus(flag){
	if(flag == '1'){
		$('[name="weeks"]').prop('disabled', true);
		//$('#spWeekStart').prop('disabled', false);
		//$('#spWeekEnd').prop('disabled', false);
	} else if(flag == '2'){
		$('[name="weeks"]').prop('disabled', false);
		//$('#spWeekStart').prop('disabled', true);
		//$('#spWeekEnd').prop('disabled', true);
	} else{
		$('[name="weeks"]').prop('disabled', true);
		//$('#spWeekStart').prop('disabled', true);
		//$('#spWeekEnd').prop('disabled', true);
	}
}

function clearSel(name) {
	$('[name="' + name + 's"]:checked').prop('checked', false);
}

function buildCronExp() {
	var cron = ['0', '0', '0', '*', '*', '?'];
	var sel = '0';
	//second
	sel = $('[name="secondSel"]:checked').val();
	if(sel == '1'){
		var a1 = powersi.tonumber($('#spSecondStart').val());
		var a2 = powersi.tonumber($('#spSecondEnd').val());
		var a3 = powersi.tonumber($('#spSecondRepeat').val());
		if(a2 >= a1){
			cron[0] = a1.toString() + '-' + a2.toString();
		} else{
			cron[0] = a1.toString();
		}
		if(a3 > 0){
			cron[0] = cron[0] + '/' + a3.toString();
		}
	} else if(sel == '2'){
		var a = [];
		$('[name="seconds"]:checked').each(function(){
			a.push($(this).val());
		});
		if(a.length == 0){
			cron[0] = '0';
		} else{
			cron[0] = a.join(',');
		}
	} else{
		cron[0] = '0';
	}
	
	//minute
	sel = $('[name="minuteSel"]:checked').val();
	if(sel == '1'){
		var a1 = powersi.tonumber($('#spMinuteStart').val());
		var a2 = powersi.tonumber($('#spMinuteEnd').val());
		var a3 = powersi.tonumber($('#spMinuteRepeat').val());
		if(a2 >= a1){
			cron[1] = a1.toString() + '-' + a2.toString();
		} else{
			cron[1] = a1.toString();
		}
		if(a3 > 0){
			cron[1] = cron[1] + '/' + a3.toString();
		}
	} else if(sel == '2'){
		var a = [];
		$('[name="minutes"]:checked').each(function(){
			a.push($(this).val());
		});
		if(a.length == 0){
			cron[1] = '0';
		} else{
			cron[1] = a.join(',');
		}
	} else{
		cron[1] = '0';
	}
	
	//hour
	sel = $('[name="hourSel"]:checked').val();
	if(sel == '1'){
		var a1 = powersi.tonumber($('#spHourStart').val());
		var a2 = powersi.tonumber($('#spHourEnd').val());
		var a3 = powersi.tonumber($('#spHourRepeat').val());
		if(a2 >= a1){
			cron[2] = a1.toString() + '-' + a2.toString();
		} else{
			cron[2] = a1.toString();
		}
		if(a3 > 0){
			cron[2] = cron[2] + '/' + a3.toString();
		}
	} else if(sel == '2'){
		var a = [];
		$('[name="hours"]:checked').each(function(){
			a.push($(this).val());
		});
		if(a.length == 0){
			cron[2] = '0';
		} else{
			cron[2] = a.join(',');
		}
	} else{
		cron[2] = '0';
	}
	
	//day
	sel = $('[name="daySel"]:checked').val();
	if(sel == '1'){
		var a1 = powersi.tonumber($('#spDayStart').val());
		var a2 = powersi.tonumber($('#spDayEnd').val());
		var a3 = powersi.tonumber($('#spDayRepeat').val());
		if(a2 >= a1){
			cron[3] = a1.toString() + '-' + a2.toString();
		} else{
			cron[3] = a1.toString();
		}
		if(a3 > 0){
			cron[3] = cron[3] + '/' + a3.toString();
		}
	} else if(sel == '2'){
		var a = [];
		$('[name="days"]:checked').each(function(){
			a.push($(this).val());
		});
		if(a.length == 0){
			cron[3] = '*';
		} else{
			cron[3] = a.join(',');
		}
	} else if(sel == '3'){
		cron[3] = 'L';
	} else if(sel == '4'){
		cron[3] = 'LW';
	} else if(sel == '5'){
		cron[3] = $('#spDayWork').val() + 'W';
	} else{
		cron[3] = '*';
	}
	
	//month
	sel = $('[name="monthSel"]:checked').val();
	if(sel == '2'){
		var a = [];
		$('[name="months"]:checked').each(function(){
			a.push($(this).val());
		});
		if(a.length == 0){
			cron[4] = '*';
		} else{
			cron[4] = a.join(',');
		}
	} else{
		cron[4] = '*';
	}
	
	//week
	sel = $('[name="weekSel"]:checked').val();
	if(sel == '1') {
		cron[5] = $('#spWeekStart').val() + '-' + $('#spWeekEnd').val();
	} else if(sel == '2'){
		var a = [];
		$('[name="weeks"]:checked').each(function(){
			a.push($(this).val());
		});
		if(a.length == 0){
			cron[5] = '?';
		} else{
			cron[5] = a.join(',');
		}
	} else if(sel == '3'){
		var num = $('#spWeekNum').val();
		var day = $('#spWeekDay').val();
		if(num == 'L'){
			cron[5] = day + num;
		} else{
			cron[5] = day + "#" + num;
		}
	} else{
		cron[5] = '?';
	}
	
	//天和周互斥处理(周优先)
	if(cron[5] != '?'){
		cron[3] = '?';
	}
	if(cron[3] != '?'){
		cron[5] = '?';
	}
	
	$('#txtCronExp').val(cron.join(' '));
}

function queryCropnExp() {
	if(!checkForm('#form1')){
		return;
	}
	
	postAjax(rootPath + '/manager/TaskManager!queryCronExp.action', form2json('#form1'), function(res){
		if(!checkResult(res)){
			return;
		}
		
		$('#txtCronResult').val(res.data || '');
	});
}

var dlg = null;
function showHelp(){
	if(dlg){
		dlg.show();
	} else{
		dlg = popupDiv('#dlgHelp', '常用表达式', 400);
	}
}
</script>
</body>
</powersi:html>