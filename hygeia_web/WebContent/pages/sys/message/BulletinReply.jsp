<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<powersi:html>
<head>
<powersi:head title="公告查询" />
</head>
<body>
	<powersi:panelbox key="panel_query" allowAddition="true">
		<powersi:form id="queryForm" namespace="/message" action="BulletinManagerAction!queryReplyList">
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
					<powersi:checkboxlist id="userKind" name="searchBulletinDto.userKind" key="bulletin_user_kind" codeType="bulletin_user_kind" required="true" ></powersi:checkboxlist>	
					<powersi:checkboxlist id="replyFlag" name="searchBulletinDto.replyFlag" key="reply_flag" codeType="bulletin_reply_flag" required="true"  colspan="3"></powersi:checkboxlist>
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
			<powersi:datagrid-column key="operate" render="renderOperate" isExport="false" isSort="false" width="150" frozen="true" />
			<powersi:datagrid-column name="dis_order" key="dis_order" width="80" frozen="true" />
			<powersi:datagrid-column name="bulletin_title" key="bulletin_title" render="renderTitle" width="100%" minWidth="200" frozen="true" />
			<powersi:datagrid-column name="reply_flag1" display="需要回执" data="reply_flag" render="renderReply1" width="80" />
			<powersi:datagrid-column name="reply_flag2" display="需要回执意见" data="reply_flag" render="renderReply2" width="100" />
			<powersi:datagrid-column name="reply_count" key="reply_count" width="80" />
			<powersi:datagrid-column name="second_audit_date" display="发布时间" width="150" />
			<powersi:datagrid-column name="sender_man" display="发布人" width="80" />
		</powersi:datagrid>
	</powersi:panelbox>
	
	<!-- 隐藏区 -->
	<div class="hidden">
		<div id="orderDlg">
			<form id="dlgForm">
				<div class="row">
					<div class="col-4">
						<powersi:spinner name="dis_order" id="disOrder" maxlength="6" required="true" validate="integer,min[0],max[999999]" 
											min="0" max="999999" mouseWheel="true" />
						<input type="hidden" id="bulletinId" name="bulletin_id" />
					</div>
					<div class="col-8">
						<div class="textPrimary">数字越小，显示越靠前(缺省1000，最小0，最大999999)</div>
					</div>
				</div>
				<div class="space-y"></div>
				<div class="space-y"></div>
				<div class="divCenter">
					<powersi:button id="btDlgSave" key="button_save" cssClass="btn btn-success" onclick="saveDisOrder()" />
					<powersi:button id="btDlgClose" key="button_close" onclick="closeDlg()" />
				</div>
			</form>
		</div>
	</div>
	
	<powersi:errors />
<script type="text/javascript">
	function renderOperate(row, index, value){
		var a = [];
		a.push('<input type="button" value="查看回执" class="linkButton"');
		a.push(' onclick="doViewReplyInfo(');
		a.push(row['bulletin_id']);
		a.push(')"');
		if(row['reply_flag'] == '0'){
			a.push(' disabled="disabled"');
		}
		a.push(" />");
		
		a.push("&nbsp;&nbsp;");
		
		a.push('<input type="button" value="修改序号" class="linkButton"');
		a.push(' onclick="doChangeOrder(');
		a.push(index);
		a.push(')"');
		a.push(" />");
		
		return a.join('');
	}
	
	function renderTitle(row, index, value) {
		return '<a href="#" onclick="javascript:doView(\'' + row['bulletin_url'] + '\')" title="' + value + '">' + value + '</a>';
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
	
	function doViewReplyInfo(bulletinId) {
		popupDialog(rootPath + "/message/BulletinEditAction!staticsReplyInfo.action?bulletinId=" + bulletinId , 600, 800);
	};
	
	var dlg = null;
	function closeDlg() {
		if(dlg){
			dlg.hide();
		}
	}
	
	var selectIndex = null;
	function doChangeOrder(index) {
		var row = grid.getRow(index);
		if(!row)
			return;
		
		selectIndex = index;
		
		json2form('#dlgForm', row);
		
		if(dlg) {
			dlg.show();
		} else {
			dlg = popupDiv('#orderDlg', '修改显示序号', 350);
		}
		
		$('#disOrder').focus();
	}
	
	function saveDisOrder() {
		var disOrder = $('#disOrder').val();
		if(disOrder == '' || parseInt(disOrder) > 999999 || parseInt(disOrder) < 0) {
			alert('显示序号必须在0到99999之间');
			return;
		}
		
		var data = form2json('#dlgForm');
		var row = grid.getRow(selectIndex);
		if(row['dis_order'] == data['dis_order']){
			alert('没有修改，无需保存');
			return;
		}
		
		postAjax(rootPath + '/message/BulletinEditAction!saveDisOrder.action', data, function(res){
			if(!checkResult(res)){
				return;
			}
			
			alert(res.message);
			
			grid.updateRow(selectIndex, data);
			if(dlg){
				dlg.hide();
			}
		});
	}
</script>
</body>
</powersi:html>