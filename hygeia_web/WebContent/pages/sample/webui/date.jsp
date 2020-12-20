<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="日期选择器" />
<style type="text/css">
</style>
</head>
<body class="page">
	<div class="container-fluid">
		<h3 style="margin: 0 0 20px 0">配置生成器</h3>

		<div class="well configurator">

			<powersi:form id="form1" disabled="true">
				<div class="row">

					<div class="col-md-4">

						<div class="form-group">
							<label for="parentEl">parentEl(父容器)</label> <input type="text"
								class="form-control" id="parentEl" value="" placeholder="body">
						</div>

						<div class="form-group">
							<label for="startDate">startDate(开始时间,moment对象或者字符串)</label> <input
								type="text" class="form-control" id="startDate"
								value="2016-08-01 00:00:00">
						</div>

						<div class="form-group">
							<label for="endDate">endDate(结束时间,moment对象或者字符串)</label> <input
								type="text" class="form-control" id="endDate"
								value="2016-08-08 23:59:59">
						</div>

						<div class="form-group">
							<label for="startDate">startField(开始字段,hidden或者input)</label> <input
								type="text" class="form-control" id="startField"
								placeholder="开始时间对应字段id">
						</div>

						<div class="form-group">
							<label for="endDate">endField(结束字段,hidden或者input)</label> <input
								type="text" class="form-control" id="endField"
								placeholder="结束时间对应字段id">
						</div>

						<div class="form-group">
							<label for="minDate">minDate(最小时间,moment对象或者字符串)</label> <input
								type="text" class="form-control" id="minDate" value=""
								placeholder="YYYY-MM-DD HH:mm:ss">
						</div>

						<div class="form-group">
							<label for="maxDate">maxDate(最大时间,moment对象或者字符串)</label> <input
								type="text" class="form-control" id="maxDate" value=""
								placeholder="YYYY-MM-DD HH:mm:ss">
						</div>

					</div>
					<div class="col-md-4">

						<div class="checkbox">
							<label> <input type="checkbox" id="autoApply" checked="checked">
								autoApply 自动应用选择(不显示确定和取消按钮，包含时间选择器除外)
							</label>
						</div>

						<div class="checkbox">
							<label style="color: red;"> <input type="checkbox"
								id="singleDatePicker"> singleDatePicker 是否单日期选择(默认单日期，配置endDate或endField则默认双日期，可以自行指定)
							</label>
						</div>

						<div class="checkbox">
							<label> <input type="checkbox" id="showDropdowns">
								showDropdowns 是否显示年月的下拉选择框
							</label>
						</div>

						<div class="checkbox">
							<label> <input type="checkbox" id="showWeekNumbers">
								showWeekNumbers 是否显示周
							</label>
						</div>

						<div class="checkbox">
							<label> <input type="checkbox" id="showISOWeekNumbers">
								showISOWeekNumbers 是否显示iso标准的周
							</label>
						</div>

						<div class="checkbox">
							<label> <input type="checkbox" id="timePicker">
								timePicker 是否显示时间选择器
							</label>
						</div>

						<div class="checkbox">
							<label> <input type="checkbox" id="timePicker24Hour"
								checked="checked"> timePicker24Hour 是否显示24小时制
							</label>
						</div>

						<div class="form-group">
							<label for="timePickerIncrement">timePickerIncrement (in
								minutes) 时间选择器增长单位(0到30)</label> <input type="text" class="form-control"
								id="timePickerIncrement" value="1">
						</div>

						<div class="checkbox">
							<label> <input type="checkbox" id="timePickerSeconds"
								checked="checked"> timePickerSeconds 是否显示秒选择器
							</label>
						</div>

						<div class="checkbox">
							<label style="color: red;"> <input type="checkbox"
								id="dateLimit"> dateLimit 日期选择限制({'days': 7} or
								{'months': 3})
							</label>
						</div>

						<div class="checkbox">
							<label style="color: red;"> <input type="checkbox"
								id="ranges"> ranges 日期选择列表({"今天":[startDate, endDate]})
							</label>
						</div>

						<div class="checkbox">
							<label> <input type="checkbox" id="locale">
								locale 是否启用本地化(框架已经处理)
							</label>
						</div>

						<div class="checkbox">
							<label style="color: red;"> <input type="checkbox"
								id="linkedCalendars"> linkedCalendars
								是否联动日期(左右两个日期同时改变，否则左右日期改变互不影响，如果左边日期大于右边日期，会自动左右日期换位)
							</label>
						</div>

						<div class="checkbox">
							<label> <input type="checkbox" id="autoUpdateInput"
								checked="checked"> autoUpdateInput 是否自动更新文本框显示
							</label>
						</div>

						<div class="checkbox">
							<label> <input type="checkbox" id="alwaysShowCalendars"
								checked="checked"> alwaysShowCalendars 是否总是显示日期面板
							</label>
						</div>

					</div>
					<div class="col-md-4">

						<div class="form-group">
							<label for="opens">opens 打开方向(左 居中 右)</label> <select id="opens"
								class="form-control">
								<option value="right" selected>right</option>
								<option value="left">left</option>
								<option value="center">center</option>
							</select>
						</div>

						<div class="form-group">
							<label for="drops">drops 弹出方向(上 下)</label> <select id="drops"
								class="form-control">
								<option value="down" selected>down</option>
								<option value="up">up</option>
							</select>
						</div>

						<div class="form-group">
							<label for="buttonClasses">buttonClasses 按钮基本样式</label> <input
								type="text" class="form-control" id="buttonClasses"
								value="btn btn-sm">
						</div>

						<div class="form-group">
							<label for="applyClass">applyClass 确定按钮样式</label> <input
								type="text" class="form-control" id="applyClass"
								value="btn-success">
						</div>

						<div class="form-group">
							<label for="cancelClass">cancelClass 取消按钮样式</label> <input
								type="text" class="form-control" id="cancelClass"
								value="btn-default">
						</div>

					</div>

				</div>
			</powersi:form>

		</div>

		<div class="row">
			<div class="col-md-4">
				<div class="row">
					<div class="col-12">
						<h5>日期标签(datetime)</h5>
						<div class="space"></div>
						<powersi:date id="datetimeRange" drops="up" mask="datetime"
							startField="startDate" endField="endDate"
							dateLimit="{'months': 12}">
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
					</div>
				</div>
				<div class="space-y"></div>
				<div class="row">
					<div class="col-12">
						<h5>日期标签(date)</h5>
						<div class="space"></div>
						<powersi:date id="dateRange" drops="up" mask="date"
							startField="startDate" endField="endDate">
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
					</div>
				</div>
				<div class="space-y"></div>
				<div class="row">
					<div class="col-12">
						<h5>日期标签(period)</h5>
						<div class="space"></div>
						<powersi:date id="periodRange" drops="up" mask="period"
							startField="startDate" endField="endDate">
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
					</div>
				</div>
				<div class="space-y"></div>
				<div class="row">
					<div class="col-12">
						<h5>日期标签(single date)</h5>
						<div class="space"></div>
						<powersi:date id="singleDate" drops="up" format="YYYY-MM-DD">
							<powersi:date-range label="今天" startDate="moment()" />
							<powersi:date-range label="昨天"
								startDate="moment().subtract(1, 'days')" />
							<powersi:date-range label="前天"
								startDate="moment().subtract(2, 'days')" />
							<powersi:date-range label="上周"
								startDate="moment().subtract(6, 'days')" />
							<powersi:date-range label="本周"
								startDate="moment().startOf('week')" />
							<powersi:date-range label="本月"
								startDate="moment().startOf('month')" />
							<powersi:date-range label="上个月"
								startDate="moment().subtract(1, 'months').startOf('month')" />
							<powersi:date-range label="本年"
								startDate="moment().startOf('year')" />
						</powersi:date>
					</div>
				</div>
				<div class="space-y"></div>
				<div class="row">
					<div class="col-12">
						<h5>日期标签(single period)</h5>
						<div class="space"></div>
						<powersi:date id="singlePeriod" drops="up" format="YYYYMM">
							<powersi:date-range label="今天" startDate="moment()" />
							<powersi:date-range label="昨天"
								startDate="moment().subtract(1, 'days')" />
							<powersi:date-range label="前天"
								startDate="moment().subtract(2, 'days')" />
							<powersi:date-range label="上周"
								startDate="moment().subtract(6, 'days')" />
							<powersi:date-range label="本周"
								startDate="moment().startOf('week')" />
							<powersi:date-range label="本月"
								startDate="moment().startOf('month')" />
							<powersi:date-range label="上个月"
								startDate="moment().subtract(1, 'months').startOf('month')" />
							<powersi:date-range label="本年"
								startDate="moment().startOf('year')" />
						</powersi:date>
					</div>
				</div>
				<div class="space-y"></div>
				<div class="row">
					<div class="col-12">
						<h5>日期标签(single time)</h5>
						<div class="space"></div>
						<powersi:date id="singleTime" drops="up" format="HH:mm">
							<powersi:date-range label="上班" startDate="08:30" />
							<powersi:date-range label="中班" startDate="13:30" />
							<powersi:date-range label="下班" startDate="17:30" />
						</powersi:date>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="row">
					<div class="col-12">
						<h5>日期js</h5>
						<div class="space"></div>
						<div class="input-icon">
							<input type="text" id="config-demo" class="form-control">
							<i class="icon-fa fa-calendar"></i>
						</div>
					</div>
				</div>
				<div class="space-y"></div>
				<div class="row">
					<div class="col-12">
						<h5>日期js</h5>
						<div class="space"></div>
						<div id="div-demo" class="input-group input-daterange">
							<span class="form-control text" data-name="start"></span> 
							<span class="input-group-addon"><i class="icon-calendar"></i></span> 
							<span class="form-control text" data-name="end"></span>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<h5>配置js</h5>
				<div class="space"></div>
				<div class="well">
					<textarea id="config-text"
						style="height: 300px; width: 100%; padding: 10px"></textarea>
				</div>
			</div>
		</div>

	</div>

	<script type="text/javascript">
		$(document).ready(
						function() {
							$('#div-demo').daterangepicker({
								"locale": {
							        "format": "YYYY-MM-DD HH:mm:ss"
								}
							});
							
							$('.input-icon > i').click(function() {
								$(this).parent().find("input").click();
							}).css('cursor', 'pointer');

							$('#config-text').keyup(function() {
								eval($(this).val());
							});

							$('.configurator input, .configurator select')
									.change(function() {
										updateConfig();
									});

							$('.demo i').click(function() {
								$(this).parent().find('input').click();
							});

							/*
							$('#startDate').daterangepicker({
								singleDatePicker : true,
								startDate : moment().subtract(6, 'days')
							});

							$('#endDate').daterangepicker({
								singleDatePicker : true,
								startDate : moment()
							});
							 */

							updateConfig();

							function updateConfig() {
								var options = {};

								options.singleDatePicker = $(
										'#singleDatePicker').is(':checked');

								options.showDropdowns = $('#showDropdowns').is(
										':checked');

								options.showWeekNumbers = $('#showWeekNumbers')
										.is(':checked');

								options.showISOWeekNumbers = $(
										'#showISOWeekNumbers').is(':checked');

								options.timePicker = $('#timePicker').is(
										':checked');

								options.timePicker24Hour = $(
										'#timePicker24Hour').is(':checked');

								if ($('#timePickerIncrement').val().length
										&& $('#timePickerIncrement').val() != 1)
									options.timePickerIncrement = parseInt($(
											'#timePickerIncrement').val(), 10);

								options.timePickerSeconds = $(
										'#timePickerSeconds').is(':checked');
								options.autoApply = $('#autoApply').is(
										':checked');

								if ($('#dateLimit').is(':checked'))
									options.dateLimit = {
										days : 7
									};

								if ($('#ranges').is(':checked')) {
									options.ranges = {
										'今天' : [ moment(), moment() ],
										'昨天' : [ moment().subtract(1, 'days'),
												moment().subtract(1, 'days') ],
										'最近三天' : [
												moment().subtract(2, 'days'),
												moment() ],
										'最近一周' : [
												moment().subtract(6, 'days'),
												moment() ],
										'最近一月' : [
												moment().subtract(1, 'months'),
												moment() ],
										'本月' : [ moment().startOf('month'),
												moment().endOf('month') ],
										'上个月' : [
												moment().subtract(1, 'months')
														.startOf('month'),
												moment().subtract(1, 'months')
														.endOf('month') ]
									/*,
																			'本月第一天' : [ moment().startOf('month') ],
																			'本月最后一天' : [ moment().endOf('month') ],
																			'上月第一天' : [ moment().subtract(1,
																					'months').startOf('month') ],
																			'上月最后一天' : [ moment().subtract(1,
																					'months').endOf('month') ]*/
									};
								}

								if ($('#locale').is(':checked')) {
									options.locale = {
										format : 'YYYY-MM',
										separator : ' - ',
										applyLabel : '确定',
										cancelLabel : '取消',
										fromLabel : '从',
										toLabel : '到',
										customRangeLabel : '自定义',
										daysOfWeek : [ '日', '一', '二', '三', '四',
												'五', '六' ],
										monthNames : [ '一月', '二月', '三月', '四月',
												'五月', '六月', '七月', '八月', '九月',
												'十月', '十一', '十二' ],
										firstDay : 0
									};
								}

								if (!$('#linkedCalendars').is(':checked'))
									options.linkedCalendars = false;

								if (!$('#autoUpdateInput').is(':checked'))
									options.autoUpdateInput = false;

								if ($('#alwaysShowCalendars').is(':checked'))
									options.alwaysShowCalendars = true;

								if ($('#parentEl').val().length)
									options.parentEl = $('#parentEl').val();

								if ($('#startDate').val().length)
									options.startDate = $('#startDate').val();

								if ($('#endDate').val().length)
									options.endDate = $('#endDate').val();

								if ($('#minDate').val().length)
									options.minDate = $('#minDate').val();

								if ($('#maxDate').val().length)
									options.maxDate = $('#maxDate').val();

								if ($('#opens').val().length
										&& $('#opens').val() != 'right')
									options.opens = $('#opens').val();

								if ($('#drops').val().length
										&& $('#drops').val() != 'down')
									options.drops = $('#drops').val();

								if ($('#buttonClasses').val().length
										&& $('#buttonClasses').val() != 'btn btn-sm')
									options.buttonClasses = $('#buttonClasses')
											.val();

								if ($('#applyClass').val().length
										&& $('#applyClass').val() != 'btn-success')
									options.applyClass = $('#applyClass').val();

								if ($('#cancelClass').val().length
										&& $('#cancelClass').val() != 'btn-default')
									options.cancelClass = $('#cancelClass')
											.val();

								$('#config-text')
										.val(
												"$('#demo').daterangepicker("
														+ JSON.stringify(
																options, null,
																'    ')
														+ ", function(start, end, label) {\n  console.log(\"New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')\");\n});");

								$('#config-demo')
										.daterangepicker(
												options,
												function(start, end, label) {
													console
															.log('New date range selected: '
																	+ start
																			.format('YYYY-MM-DD')
																	+ ' to '
																	+ end
																			.format('YYYY-MM-DD')
																	+ ' (predefined range: '
																	+ label
																	+ ')');
												});
							}

						});
	</script>

	<powersi:errors />
</body>
</powersi:html>