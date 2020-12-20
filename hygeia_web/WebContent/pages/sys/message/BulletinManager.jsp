<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="公告管理" />
</head>
<body>
	<powersi:panelbox key="panel_query" allowAddition="true" isCollapse="false">
		<powersi:form id="queryForm" namespace="/message" action="BulletinManagerAction">
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
					<powersi:textfield id="bulletinTitle" name="searchBulletinDto.bulletinTitle" key="bulletin_title" />
					<powersi:textfield id="bulletinContent" name="searchBulletinDto.bulletinContent" key="bulletin_content" />
					<powersi:textfield id="senderMan" name="searchBulletinDto.senderMan" key="sender_man" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row addition="true">
					<powersi:checkboxlist codeType="audit_flag" name="searchBulletinDto.auditFlag"  key="audit_flag"  />
					<%-- <powersi:checkboxlist codeType="second_audit_flag" name="searchBulletinDto.secondAuditFlag"  key="second_audit_flag" required="true" /> --%>
					<%-- <powersi:checkboxlist codeType="bulletin_user_kind" name="searchBulletinDto.userKind" key="bulletin_user_kind" required="true" /> --%>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:form>
	</powersi:panelbox>
	<powersi:panelbox key="panel_result" title="公告列表">
		<powersi:datagrid id="grid" formId="queryForm" rowAttrRender="gridRowRender">
			<powersi:toolbar>
				
			</powersi:toolbar>
			<powersi:datagrid-column key="operate" render="renderOperate" isExport="false" isSort="false" width="80" frozen="true" />
			<powersi:datagrid-column name="dis_order" key="dis_order" width="80" frozen="true" />
			<powersi:datagrid-column name="bulletin_title" key="bulletin_title" render="renderTitle" width="100%" minWidth="200" frozen="true" />
			<powersi:datagrid-column name="sender_man" key="sender_man" width="80" />
			<powersi:datagrid-column name="send_date" key="send_date" width="150" editor="{type:'date', showTime: true}" />
			<powersi:datagrid-column name="user_kind" key="bulletin_user_kind" width="80" code="bulletin_user_kind" />
			<powersi:datagrid-column name="effect_date" key="effect_date" width="150" editor="{type:'date', showTime: true}"/>
			<powersi:datagrid-column name="expire_date" key="expire_date" width="150" editor="{type:'date', showTime: true}"/>
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
	<powersi:errors />
	<powersi:codetable codeType="bulletin_user_kind" id="bulletinUserKind"></powersi:codetable>
<script type="text/javascript">
	$(function(){
		//动态生成工具栏
		var toolbar = grid.toolbarManager;
		$.each(bulletinUserKind, function(i, value){
			/* if(value['id'] == '0'){
				toolbar.addItem({
					'id': 'public',
					'text': '新增' + value['text'] + '公告',
					'icon': 'add1',
					'click': itemClick
				});
			}
			if(value['id'] == '9'){
				toolbar.addItem({
					'id': 'center',
					'text': '新增' + value['text'] + '公告',
					'icon': 'add2',
					'click': itemClick
				});
			}
			if(value['id'] == '2'){
				toolbar.addItem({
					'id': 'corp',
					'text': '新增' + value['text'] + '公告',
					'icon': 'add3',
					'click': itemClick
				});
			} */
			if(value['id'] == '3'){
				toolbar.addItem({
					'id': 'hosp',
					'text': '新增' + value['text'] + '公告',
					'icon': 'add4',
					'click': itemClick
				});
			}
		});
	});
	
	function gridRowRender(rowdata, rowid){
		if(rowdata['audit_flag'] == '2' || rowdata['second_audit_flag'] == '2') {
			return getColorStyle('error');
		}
		
		if (rowdata['audit_flag'] == '1' || rowdata['second_audit_flag'] == '1') {
			return getColorStyle('success');
		}
	}
	
	function renderOperate(row, index, value){
		var a = [];
		a.push('<input type="button" value="编辑" class="linkButton"');
		a.push(' onclick="doEdit(');
		a.push(index);
		a.push(')"');
		if(row['audit_flag'] == '1'){
			a.push(' disabled="disabled"');
		}
		a.push(" />");
		
		a.push("&nbsp;&nbsp;");
		
		a.push('<input type="button" value="删除" class="linkButton"');
		a.push(' onclick="doDel(');
		a.push(index);
		a.push(')"');
		if(row['audit_flag'] == '1'){
			a.push(' disabled="disabled"');
		}
		a.push(" />");
		
		return a.join('');
	}
	
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
	
	function itemClick(item){
		if(item.id == 'center'){
			doAdd('9');
		} else if(item.id == 'hosp'){
			doAdd('3');
		} else if(item.id == 'corp'){
			doAdd('2');
		} else if(item.id  == 'public'){
			doAdd('0');
		}
	}

  	function doView(bulletinUrl){
  		if(isNaN(bulletinUrl)){
  			popupDialog(bulletinUrl);
  		} else {
  			popupDialog(rootPath + "/message/BulletinManagerAction!view.action?bulletinId=" + bulletinUrl, 600, 600);
  		}
  	}
  	
  	function doAdd(userKind) {
		popupDialog({
			url: rootPath + "/message/BulletinEditAction.action?bulletinId=0&userKind=" + userKind,
			onClosed: function() {
				var ret = this.returnValue;
				if(ret){
					if(ret === true){
						grid.reload();				
					} else{
						grid.getServerData({
							'data': {
								"searchBulletinDto.bulletinId" : ret	
							},
							'callback': function(data){
								//新增记录放最前面(如果放最后，后2个参数不输入即可)
								grid.addRows(data, grid.getRow(0), true);
						  		grid.select(0);
							}
						});
					}
				}
			}
		}, 600, 600);
	}
  	
  	function doEdit(index) {
  		var bulletinId = grid.getRow(index)["bulletin_id"];
  		
		popupDialog({
			url: rootPath + "/message/BulletinEditAction.action?bulletinId=" + bulletinId,
			onClosed: function() {
				var ret = this.returnValue;
				if (ret != null) {
					grid.getServerData({
						'data': {
							"searchBulletinDto.bulletinId" : bulletinId	
						},
						'callback': function(data){
							grid.updateRow(index, data[0]);
							grid.select(index);
						}
					});
				}
			}
		}, 520, 600);
	}
  	
  	function doDel(index) {
  		var row = grid.getRow(index);
  		var bulletinId = row['bulletin_id'];
  		var bulletinTitle = row['bulletin_title'];
  		
  		if (!confirm("您确认删除公告[" + bulletinTitle + "]吗?")) {
            return;
        }
  		
  		postJSON(rootPath + "/message/BulletinManagerAction!delete.action?bulletinId=" + bulletinId, function(json){
			if(!checkJSONResult(json)){
			    return;
		    }

		    alert(json.message);
		    
		    grid.deleteRow(index);
  		});
	}
</script>
</body>
</powersi:html>