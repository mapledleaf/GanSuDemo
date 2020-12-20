<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="用户信息变更查询" />
</head>
<body>
	<powersi:panelbox key="panel_query" allowAddition="true">
		<powersi:form id="queryForm" namespace="/user" action="UserInfoAction!queryUserChangeInfo">
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
					<powersi:hidden id="beginDate" name="searchChangeDto.startTime" key="begin_date" required="true" mask="datetime"/>
					<powersi:hidden id="endDate" name="searchChangeDto.endTime" key="end_date" required="true" mask="datetime" />
					<powersi:editorlayout-button colspan="2">
						<powersi:submit id="btSubmit" key="button_query" />
						<powersi:reset id="btReset" key="button_reset" />
					</powersi:editorlayout-button>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row addition="true">
					<powersi:radio list="#{0:'全部',1:'增加',2:'删除','3':'更新'}" id="searchChangeDtoeventType" name="searchChangeDto.eventType" label="变更类型" />
					<powersi:textfield id="searchChangeDtooldValue" name="searchChangeDto.oldValue" label="变更前值" />
					<powersi:textfield id="searchChangeDtonewValue" name="searchChangeDto.newValue" label="变更后值" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row addition="true">
					<powersi:textfield id="searchChangeDtouserName" name="searchChangeDto.userName" label="用户名" />
					<powersi:textfield id="searchChangeDtologinUser" name="searchChangeDto.loginUser" label="经办人登录名" />
					<powersi:textfield id="searchChangeDtostaffName" name="searchChangeDto.staffName" label="经办人用户名" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:form>
	</powersi:panelbox>
	<powersi:panelbox key="panel_result" title="变更列表">
		<powersi:datagrid id="grid" formId="queryForm" 
			showExportExcel="true" exportFileName="getExportFileName">
			<powersi:datagrid-column display="用户名" name="user_name" width="100" />
			<powersi:datagrid-column display="变更类型" name="item_event" width="80" />
			<powersi:datagrid-column display="变更信息" name="item_name" width="80" />
			<powersi:datagrid-column display="变更时间" name="log_date" width="150" />
			<powersi:datagrid-column display="变更前值" name="old_value" width="50%" minWidth="150" />
			<powersi:datagrid-column display="变更后值" name="new_value" width="50%" minWidth="150" />
			<powersi:datagrid-column display="经办人登录名" name="login_user" width="100" />
			<powersi:datagrid-column display="经办人用户名" name="staff_name" width="100" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
<script type="text/javascript">
	/*根据参数生成导出文件名*/
	function getExportFileName() {
		var filename = '用户变更(' + $('#beginDate').val().substring(0, 10)
				+ '_' + $('#endDate').val().substring(0, 10) + ')';
		return filename.replace(/-/g, "");
	}
</script>
</body>
</powersi:html>