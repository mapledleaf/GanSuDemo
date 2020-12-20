<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="公告列表" />
</head>
<body>
	<powersi:panelbox key="panel_query" allowAddition="true">
		<powersi:form id="queryForm" namespace="/message" action="BulletinManagerAction!list">
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
					<powersi:hidden id="beginDate" name="searchBulletinDto.beginDate" key="begin_date" required="true" mask="datetime" />
					<powersi:hidden id="endDate" name="searchBulletinDto.endDate" key="end_date" required="true" mask="datetime" />
					<powersi:editorlayout-button colspan="2">
						<powersi:submit id="btSubmit" key="button_query" />
						<powersi:reset id="btReset" key="button_reset" />
					</powersi:editorlayout-button>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row addition="true">
					<powersi:textfield id="bulletinTitle" name="searchBulletinDto.bulletinTitle" key="bulletin_title" />
					<powersi:textfield id="bulletinContent" name="searchBulletinDto.bulletinContent" key="bulletin_content" />
					<powersi:textfield id="senderMan" name="searchBulletinDto.senderMan" label="发布人" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:form>
	</powersi:panelbox>
	
	<powersi:panelbox key="panel_result" title="公告列表">
		<powersi:datagrid id="grid" formId="queryForm">
			<powersi:datagrid-column name="bulletin_title" key="bulletin_title" render="renderTitle"  minWidth="200" width="60%" frozen="true" />
			<powersi:datagrid-column name="audit_date" display="发布时间"  width="20%" />
			<powersi:datagrid-column name="sender_man" display="发布人"  width="20%" />
		</powersi:datagrid>
	</powersi:panelbox>
	
	<powersi:errors />
<script type="text/javascript">
	function renderTitle(row, index, value) {
		return '<a href="#" onclick="javascript:doView(\'' + row['bulletin_id'] + '\')" title="' + value + '">' + value + '</a>';
	}
	
	function renderReply1(rowdata, index, value) {
		if (rowdata['reply_flag'] != '0') {
			return '<span title="需要回执"><i class="icon-ok textSuccess"></i></span>';
		}
	}
	
	function renderReply2(rowdata, index, value) {
		if (rowdata['reply_flag'] == '2') {
			return '<span title="需要回执意见"><i class="icon-ok textSuccess"></i></span>';
		}
	}
	
	function renderStatus(rowdata, index, value) {
		if (rowdata['reply_status'] == '0') {
			return '<span title="不需要回执">&nbsp;</span>';
		} else if(rowdata['reply_status'] == '1') {
			return '<span title="已回执"><i class="icon-flag textSuccess"></i></span>';
		} else if(rowdata['reply_status'] == '2') {
			return '<span title="未回执"><i class="icon-flag textError"></i></span>';
		} else{
			return '<span></span>';
		}
	}

	function doView(bulletinUrl){
		if(isNaN(bulletinUrl)){
			popupDialog(bulletinUrl);
		} else {
			popupDialog(rootPath + "/message/BulletinManagerAction!bulletinInfo.action?bulletinId=" + bulletinUrl, 520, 600);
		}
	}
</script>
</body>
</powersi:html>