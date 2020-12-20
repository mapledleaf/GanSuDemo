<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="消息管理" />
<style type="text/css">
.yes {
	background-image: url(${strutsPath}/icon/tick.png);
}
.receive_statue0{
	background-image: url(${strutsPath}/icon/mail--minus.png);
}
.receive_statue1{
	background-image: url(${strutsPath}/icon/mail--arrow.png);
}
.receive_statue2{
	background-image: url(${strutsPath}/icon/mail-send-receive.png);
}
</style>
</head>
<body>
	<div class="space"></div>
	<powersi:tabbedpanel id="divTabs">
		<powersi:tab id="tab1" target="divTab1" label="收件箱" />
		<powersi:tab id="tab2" target="divTab2" label="发件箱" />
		<div id="divTab1">
			<powersi:panelbox allowAddition="true" iconClass="icon-inbox" title="收件箱">
				<powersi:form id="form1" namespace="/message" action="MessageAction!managerMessage?searchMessageVo.msFloder=1">
					<powersi:editorlayout cols="6">
						<powersi:editorlayout-row>
							<powersi:date id="datetimeRange1" startField="beginDate1" endField="endDate1"
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
							<powersi:hidden id="beginDate1" name="searchMessageVo.beginDate" key="begin_date" required="true" mask="datetime" />
							<powersi:hidden id="endDate1" name="searchMessageVo.endDate" key="end_date" required="true" mask="datetime" />
							<powersi:editorlayout-button colspan="2">
								<powersi:submit id="btSubmit1" key="button_query" />
								<powersi:reset id="btReset1" key="button_reset" />
							</powersi:editorlayout-button>
						</powersi:editorlayout-row>
						<powersi:editorlayout-row addition="true">
							<powersi:textfield id="msTitle1" name="searchMessageVo.msTitle" label="消息标题" />
							<powersi:textfield id="msMan1" name="searchMessageVo.man" label="发送人" />
							<powersi:radio list="#{'all':'全部', '0':'未读','1':'已读'}" id="msStatus" name="searchMessageVo.status" label="阅读状态" required="true" />
						</powersi:editorlayout-row>
					</powersi:editorlayout>
				</powersi:form>
				<div class="space"></div>
				<powersi:datagrid id="grid1" formId="form1" rowAttrRender="gridRowRender" heightDiff="-12" checkbox="true">
					<powersi:toolbar>
						<powersi:toolbar-item id="add" text="新建消息" icon="add" click="itemClick" title="新建消息" />
						<powersi:toolbar-item id="del1" text="删除消息" icon="delete" click="itemClick" title="删除勾选的消息" position="right"/>
						<powersi:toolbar-item id="mark" text="标记为已读" icon="ok" click="itemClick" title="标记勾选的消息状态为已读状态" position="right"/>
					</powersi:toolbar>
					<powersi:datagrid-column key="operate" render="renderOperate" isExport="false" isSort="false" width="80" frozen="true" />
					<powersi:datagrid-column name="ms_title" display="消息标题" width="100%" minWidth="150" frozen="true" />
					<powersi:datagrid-column name="sender_man" display="发送人" width="80" />
					<powersi:datagrid-column name="send_date" display="发送时间" width="150" />
					<powersi:datagrid-column name="receive_statue" display="阅读状态" width="80" render="renderStatus" />
					<powersi:datagrid-column name="ms_type" display="是否系统消息" render="renderType" width="100" />
				</powersi:datagrid>
			</powersi:panelbox>
		</div>
		<div id="divTab2">
			<powersi:panelbox allowAddition="true" iconClass="icon-location-arrow" title="发件箱">
				<powersi:form id="form2" namespace="/message" action="MessageAction!managerMessage?searchMessageVo.msFloder=2">
					<powersi:editorlayout cols="6">
						<powersi:editorlayout-row>
							<powersi:date id="datetimeRange2" startField="beginDate2" endField="endDate2"
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
							<powersi:hidden id="beginDate2" name="searchMessageVo.beginDate" key="begin_date" required="true" mask="datetime" />
							<powersi:hidden id="endDate2" name="searchMessageVo.endDate" key="end_date" required="true" mask="datetime" />
							<powersi:editorlayout-button colspan="2">
								<powersi:submit id="btSubmit2" key="button_query" />
								<powersi:reset id="btReset2" key="button_reset" />
							</powersi:editorlayout-button>
						</powersi:editorlayout-row>
						<powersi:editorlayout-row addition="true">
							<powersi:textfield id="msTitle2" name="searchMessageVo.msTitle" label="消息标题" />
							<powersi:textfield id="msMan2" name="searchMessageVo.man" label="接收人" colspan="3" />
						</powersi:editorlayout-row>
					</powersi:editorlayout>
				</powersi:form>
				<div class="space"></div>
				<powersi:datagrid id="grid2" formId="form2" heightDiff="-12" checkbox="true">
					<powersi:toolbar>
						<powersi:toolbar-item id="add" text="新建消息" icon="add" click="itemClick" title="新建消息"/>
						<powersi:toolbar-item id="del2" text="删除消息" icon="delete" click="itemClick" title="删除勾选的消息" position="right"/>
					</powersi:toolbar>
					<powersi:datagrid-column key="operate" render="renderOperate" isExport="false" isSort="false" width="80" frozen="true" />
					<powersi:datagrid-column name="ms_title" display="消息标题" width="50%" minWidth="150" frozen="true" />
					<powersi:datagrid-column name="receive_mans" display="接收人" width="50%" minWidth="150" />
					<powersi:datagrid-column name="send_date" display="发送时间" width="150" />
				</powersi:datagrid>
			</powersi:panelbox>
		</div>
	</powersi:tabbedpanel>		
	<powersi:errors />
<script type="text/javascript">
	function gridRowRender(rowdata, rowid){
		if (rowdata['receive_statue'] == '0') {
			return getColorStyle('success');
		}
	}
	
	function renderOperate(row, index, value){
		try{
			var a = [];
			a.push('<input type="button" value="查看" class="linkButton"');
			a.push(' onclick="viewMsg(');
			a.push(row['ms_folder']);
			a.push(',');
			a.push(index);
			a.push(')"');
			
			a.push(" />");
			
			a.push("&nbsp;&nbsp;");
			
			a.push('<input type="button" value="删除" class="linkButton"');
			a.push(' onclick="delMsg(');
			a.push(row['ms_folder']);
			a.push(',');
			a.push(index);
			a.push(')"');
			
			a.push(" />");
			
			return a.join('');	
		} catch(ex){
			alert(ex.message);
		}
	}
	
	function renderStatus(row, index, value){
		var title = "";
		if(value == "0"){
			title = "未读";
		}else if(value == "1"){
			title = "已读";
		} else if(value == "2"){
			title = "已回复";
		} else{
			return "";
		}
		
		return '<span class="icon receive_statue' + value + '" title="' + title + '"></span>';
	}
	
	function renderType(row, index, value){
		if(value == '0'){
			return '<span title="系统消息" class="icon yes"></span>';
		} else{
			return '<span title="一般消息">&nbsp;</span>';
		}
	}
	
	function itemClick(item){
		if(item.id == 'add'){
			addMsg();
		} else if(item.id == 'del1'){
			delMsg(1);
		} else if(item.id == 'del2'){
			delMsg(2);
		} else if(item.id == 'mark'){
			changeReadStat();
		} else{
			
		}
	}
	
	function addMsg() {
		popupDialog(rootPath + "/message/MessageAction!sendMessage.action?init=flag&close=true", 550, 590);
	}

	function getGrid(msfolder){
		return msfolder == 1 ? grid1 : grid2;
	}
	
	function viewMsg(msfolder, index) {
		try{
			var row = getGrid(msfolder).getRow(index);
			var msid = row['ms_id'];
			
			var param = "msid=" + msid + "&msfolder=" + msfolder;
			popupDialog(rootPath + "/message/MessageAction!viewMessage.action?"
					+ param, 500, 600);

			if (msfolder == 1 && row['receive_statue'] == '0') {
				markRead(index);
			}
		} catch(ex){
			alert(ex.message);
		}
	}

	
	function delMsg(msfolder, index) {
		var msid = "";
		if (!powersi.isvalid(index)) {
			var rows = getGrid(msfolder).getSelectedRows();
			if (powersi.getarraysize(rows) <= 0) {
				alert("请选择需要删除的消息");
				return;
			}
			msid = powersi.tostring(powersi.rows_extract(rows, 'ms_id'));
		} else {
			msid = getGrid(msfolder).getRow(index)['ms_id'];
			msid = '[' + msid + ']';
		}
		
		if (!confirm("您确认删除消息吗?")) {
			return false;
		}
		
		postJSON(rootPath + "/message/MessageAction!delMessage.action", "msfolder=" + msfolder + "&msid=" + msid,  function(json){
			if (!checkJSONResult(json)) {
				return;
			}
			
			if(!powersi.isvalid(index)){
				getGrid(msfolder).deleteSelectedRow();
			} else{
				getGrid(msfolder).deleteRow(index);
			}
			
			alert("删除成功");
		});
	}

	function changeReadStat() {
		var rows = getGrid(1).getSelectedRows();
		if (powersi.getarraysize(rows) <= 0) {
			alert("请选择需要标记的行");
			return;
		}
		
		if (powersi.getarraysize(rows) <= 0) {
			alert("请选择标记行");
			return;
		}

		var a = [], b = [];
		$.each(rows, function(i, row){
			if(row['receive_statue'] == '0'){
				a.push(row['ms_id']);
			}
		});
		
		if(a.length == 0){
			alert('选择的消息都已经是已读');
			return;
		}
		
		var msid = powersi.tostring(a);
		postJSON(rootPath + "/message/MessageAction!changeReadStat.action",
				"msid=" + msid, function(json){
			if (!checkJSONResult(json)) {
				return;
			}
			
			$.each(rows, function(i, row){
				var idx = grid1.getRowIndex(row);
				markRead(idx);
				grid1.unselect(idx);
			});
			grid1.clearCheckbox();
			
			alert("标记成功");
		});
	}
	
	function markRead(index){
		if(grid1.getRow(index)['receive_statue'] == '0'){
			grid1.updateCell('receive_statue', '1', index);
		}
	}
</script>
</body>
</powersi:html>