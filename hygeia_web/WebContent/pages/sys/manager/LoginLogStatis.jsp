<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="登录统计查询" />
<script src="${strutsPath}/include/highcharts.js" type="text/javascript"></script>
</head>
<body>
	<powersi:panelbox title="统计条件" key="panel_query" allowAddition="true">
		<powersi:form id="queryForm" action="LoginLogStatis" namespace="/manager">
			<powersi:editorlayout cols="6">
				<powersi:editorlayout-row>
					<powersi:date id="datetimeRange" startField="beginDate" endField="endDate"
							 mask="datetime"  label="选择时间" required="true" colspan="3">
						<powersi:date-range label="今天" startDate="moment().startOf('day')" endDate="moment().endOf('day')" />
						<powersi:date-range label="昨天" startDate="moment().subtract(1, 'days').startOf('day')" endDate="moment().subtract(1, 'days').endOf('day')" />
						<powersi:date-range label="最近三天" startDate="moment().subtract(2, 'days').startOf('day')" endDate="moment().endOf('day')" />
						<powersi:date-range label="最近一周" startDate="moment().subtract(6, 'days').startOf('day')" endDate="moment().endOf('day')" />
						<powersi:date-range label="本周" startDate="moment().startOf('week').startOf('day')" endDate="moment().endOf('day')" />
						<powersi:date-range label="本月" startDate="moment().startOf('month').startOf('day')" endDate="moment().endOf('day')" />
						<powersi:date-range label="上个月" 
								startDate="moment().subtract(1, 'months').startOf('month').startOf('day')" 
								endDate="moment().subtract(1, 'months').endOf('month').endOf('day')" />
					</powersi:date>
					<powersi:hidden id="beginDate" name="beginDate" key="begin_date"
						mask="datetime" required="true" />
					<powersi:hidden id="endDate" name="endDate" key="end_date"
						mask="datetime" required="true" />
					<powersi:editorlayout-button colspan="2">
						<powersi:submit id="btSubmit" key="button_stat" />
						<powersi:reset id="btReset" key="button_reset" />
					</powersi:editorlayout-button>
					<powersi:editorlayout-row addition="true">
						<powersi:checkboxlist list="#{'1': '登录', '2': '注销'}" listKey="key" listValue="value" 
						value="#request.statType" name="statType" label="统计类型" required="true" />
						<powersi:checkboxlist list="#request.codeUserKind" name="userKind"
						value="#request.userKind" key="user_kind" required="true" checkAllButton="true" />
						<powersi:radio list="#{'all':'全部','1':'成功','0':'失败'}"
						name="loginFlag" key="login_flag" required="true" />
					</powersi:editorlayout-row>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:form>
	</powersi:panelbox>
	<powersi:panelbox key="panel_chart">
		<div id="chart" style="min-width: 400px; height: 350px; margin: 0 auto"></div>
	</powersi:panelbox>
	<powersi:errors />
<script type="text/javascript">
	$(function() {
		$(document).ready(function() {
			try{
				var series = [];
				var loginData = powersi.tojson('<%=request.getAttribute("loginStat")%>') || [];
				var logoutData = powersi.tojson('<%=request.getAttribute("logoutStat")%>') || [];
				if(loginData != null && loginData.length > 0){
					series.push({
						name: '登录统计'
					});
				}
				if(logoutData != null && logoutData.length > 0){
					series.push({
						name: '注销统计'
					});
				}
				var chartOptions = {
					chart : {
						renderTo : 'chart'
						,type : 'spline'
						//,marginRight : 90
						//,marginBottom: 25
					},
					exporting : {
						filename : encodeURIComponent('登录统计'),
						width : 800
					},
					title : {
						text : '用户登录统计',
						x : -20,
						style : {
							fontSize: '14px',
							fontWeight: 'bold' 
						}
					},
					subtitle : {
						x : -20
					},
					xAxis : {
						title: {
							text: '登录时段'
						},
						categories : [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23]
					},
					yAxis : {
						title : {
							text : '用户人次'
						},
						plotLines : [ {
							value : 0,
							width : 1,
							color : '#808080'
						} ],
						min: 0
					},
					tooltip : {
						crosshairs: true,
		                shared: true
					},
					
					legend : {
						layout : 'vertical',
						align : 'right',
						verticalAlign : 'top',
						//x : -10,
						y : 50,
						borderWidth : 0
					},
					series : series
				};
				
				chartOptions.subtitle.text = $('#beginDate').val() + " 到 " + $("#endDate").val();
				if(series.length == 2){
					chartOptions.series[0].data = loginData;
					chartOptions.series[1].data = logoutData;
				} else if(series.length == 1){
					if(loginData != null && loginData.length > 0){
						chartOptions.series[0].data = loginData;
					} else{
						chartOptions.series[0].data = logoutData;
					}
				}
				
	
				new Highcharts.Chart(chartOptions);
			} catch(ex){
				alert(ex.message);
			}
		});
	});
</script>
</body>
</powersi:html>