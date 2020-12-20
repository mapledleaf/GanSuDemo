<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="公告审核" />
</head>
<body>
	<powersi:panelbox key="panel_query" allowAddition="true">
		<powersi:form id="queryForm" namespace="/message" action="BulletinManagerAction!auditList">
			<powersi:editorlayout cols="8%,25%,8%,25%,8%,26%">
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
					<powersi:checkboxlist id="auditFlag" name="searchBulletinDto.auditFlag"  key="audit_flag" codeType="audit_flag" required="true" />
					<powersi:textfield id="bulletinTitle" name="searchBulletinDto.bulletinTitle" key="bulletin_title" />
					<powersi:textfield id="bulletinContent" name="searchBulletinDto.bulletinContent" key="bulletin_content" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:form>
	</powersi:panelbox>
	
	<powersi:panelbox key="panel_result" title="公告列表">
		<powersi:datagrid id="grid" formId="queryForm" rowAttrRender="gridRowRender">
			<powersi:datagrid-column key="operate" render="renderOperate" isExport="false" isSort="false" width="150" frozen="true" />
			<powersi:datagrid-column name="dis_order" key="dis_order" width="80" frozen="true" />
			<powersi:datagrid-column name="bulletin_title" key="bulletin_title" render="renderTitle" width="100%" minWidth="200" frozen="true" />
			<powersi:datagrid-column name="sender_man" key="sender_man" width="80" />
			<powersi:datagrid-column name="send_date" key="send_date" width="150" />
			<powersi:datagrid-column name="user_kind" key="bulletin_user_kind" width="80" code="bulletin_user_kind" />
			<powersi:datagrid-column name="effect_date" key="effect_date" width="150" />
			<powersi:datagrid-column name="expire_date" key="expire_date" width="150" />
			<%-- <powersi:datagrid-column name="reply_flag1" key="reply_flag1" data="reply_flag" render="renderReply1" width="80" />
			<powersi:datagrid-column name="reply_flag2" key="reply_flag2" data="reply_flag" render="renderReply2" width="100" /> --%>
			<powersi:datagrid-column name="audit_flag_name" key="audit_flag" code="audit_flag" data="audit_flag" width="80" />
			<powersi:datagrid-column name="audit_man" key="audit_man" width="80" />
			<powersi:datagrid-column name="audit_opinion" key="audit_opinion" width="150" />
			<%-- <powersi:datagrid-column name="second_audit_flag_name" key="second_audit_flag" code="second_audit_flag" data="second_audit_flag" width="80" />
			<powersi:datagrid-column name="second_audit_man" key="second_audit_man" width="80" />
			<powersi:datagrid-column name="second_audit_opinion" key="second_audit_opinion" width="150" /> --%>
		</powersi:datagrid>
	</powersi:panelbox>
	<!-- 隐藏区 -->
	<div class="hidden">
		<div id="dlg">
			<div class="textLeft"><span class="textInfo"><i class="icon-info-sign"></i> 请填写审核意见</span></div>
				<div style="margin: 5px 0 15px 0;">
					<powersi:textarea id="opinion" name="opinion" rows="5" maxlength="500"></powersi:textarea>
				</div>
				<div class="textRight">
					<powersi:button id="btAudit" onclick="saveAudit(true)" value="审核通过" cssClass="btn btn-success" buttonIcon="icon-check" />
					<powersi:button id="btNotPass" onclick="saveAudit(false)" value="审核不通过" cssClass="btn btn-danger" buttonIcon="icon-ban-circle" />
					<powersi:button id="btCancel" onclick="closeDlg()" key="button_close" />
				</div>
		</div>
	</div>
	<powersi:errors />
<script type="text/javascript">
	function gridRowRender(rowdata, rowid){
		if(rowdata['audit_flag'] == '2' || rowdata['second_audit_flag'] == '2') {
			return getColorStyle('error');
		}
		
		if (rowdata['audit_flag'] == '1') {
			return getColorStyle('success');
		}
	}
	
	function renderOperate(row, index, value){
		var a = [];
		a.push('<input type="button" value="审核公告" class="linkButton"');
		a.push(' onclick="doAudit(');
		a.push(index);
		a.push(')"');
		if(row['audit_flag'] == '1' ){
			a.push(' disabled="disabled"');
		}
		a.push(" />");
		
		a.push("&nbsp;&nbsp;");
		
		a.push('<input type="button" value="取消审核" class="linkButton"');
		a.push(' onclick="doCancel(');
		a.push(index);
		a.push(')"');
		if(row['audit_flag'] == '0' ){
			a.push(' disabled="disabled"');
		}
		a.push(" />");

		return a.join('');
	}
	
	function renderTitle(row, index, value) {
		if(row['audit_flag'] == '1' ){
			return '<a href="#" onclick="javascript:doView(\'' + row['bulletin_id'] + '\')" title="' + value + '">' + value + '</a>';	
		} else{
			return '<a href="#" onclick="javascript:doEdit(' + index + ')" title="' + value + '">' + value + '</a>';	
		}
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
	
  	function doView(bulletinUrl){
  		if(isNaN(bulletinUrl)){
  			popupDialog(bulletinUrl);
  		} else {
  			popupDialog(rootPath + "/message/BulletinManagerAction!view.action?bulletinId=" + bulletinUrl, 600, 600);
  		}
  	}
  	
  	function doEdit(index) {
  		var bulletinId = grid.getRow(index)["bulletin_id"];
  		
		popupDialog({
			url: rootPath + "/message/BulletinEditAction.action?auditType=1&bulletinId=" + bulletinId,
			onClosed: function() {
				var ret = this.returnValue;
				if (ret != null) {
					grid.reload();
				}
			}
		}, 600, 600);
	}
  	
  	var dlg = null;
  	var selectIndex = null;
  	function closeDlg() {
  		if(dlg){
  			dlg.hide();
  		}
  	}
  	
  	function doAudit(index) {
  		var row = grid.getRow(index);
  		if(!row){
  			return;
  		}
  		
  		selectIndex = index;
  		
  		if(dlg){
  			dlg.show();
  		} else{
  			dlg = popupDiv('#dlg', '审核公告', 350);
  		}
  		
  		$('#opinion').val(row['audit_opinion'] || '').focus();
	}
  	
  	function saveAudit(flag){
  		var row = grid.getRow(selectIndex);
  		if(!row){
  			return;
  		}
  		
  		var bulletinId = row['bulletin_id'];
  		var bulletinTitle = row['bulletin_title'];
  		var opinion = $('#opinion').val();
  		if(!flag && powersi.length(opinion) == 0){
  			alert("审核不通过必须输入审核意见");
  			return;
  		}
  		
  		if (!confirm("您确认审核" + (flag ? "通过" : "不通过") + "公告[" + bulletinTitle + "]吗?")) {
            return;
        }
  		
  		postJSON(rootPath + "/message/BulletinManagerAction!audit.action", {
	  			"bulletinId": bulletinId,
	  			"auditFlag": flag ? "1" : "2",
	  			"auditOpinion": opinion }, function(json){
  			if(!checkJSONResult(json)){
			    return;
		    }

		    alert(json.message);
		    
		    grid.reload();
		    
		    closeDlg();
  		});
  	}
  	
  	function doCancel(index) {
  		var row = grid.getRow(index);
  		var bulletinId = row['bulletin_id'];
  		var bulletinTitle = row['bulletin_title'];
  		
  		if (!confirm("您确认取消审核公告[" + bulletinTitle + "]吗?")) {
            return;
        }
  		
  		postJSON(rootPath + "/message/BulletinManagerAction!cancel.action?bulletinId=" + bulletinId, function(json){
  			if(!checkJSONResult(json)){
			    return;
		    }

		    alert(json.message);
		    
		    grid.reload();
  		});
	}
</script>
</body>
</powersi:html>