<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="回执状态" target="_self"/>
</head>
<body>
	<powersi:panelbox key="panel_query">
		<powersi:form namespace="/message" action="BulletinEditAction!staticsReplyInfo" id="queryForm">
			<input type="hidden" name="searchBulletinDto.bulletinId"  value="${searchBulletinDto.bulletinId }"/>
			<powersi:editorlayout cols="6">
					<powersi:editorlayout-row>
						<powersi:radio id="reply_status" name="searchBulletinDto.replyStatus" label="回执状态" list="#{1:'已回执',2:'未回执'}" required="true" onchange="$('#btSubmit').click()"/>
						<powersi:textfield id="reply_man" name="searchBulletinDto.replyMan" label="回执人" />
						<powersi:editorlayout-button colspan="2">
							<powersi:submit id="btSubmit" key="button_query" />
							<powersi:reset id="btReset" key="button_reset" />
						</powersi:editorlayout-button>
					</powersi:editorlayout-row>
				</powersi:editorlayout>
		</powersi:form>
	</powersi:panelbox>
	
	<powersi:panelbox key="panel_result" title="回执列表">
		<powersi:datagrid id="grid" formId="queryForm">
			<powersi:datagrid-column key="operate" render="renderOperate" isExport="false" isSort="false" width="50" frozen="true" />
			<powersi:datagrid-column name="org_name" display="回执机构" width="200" />
			<powersi:datagrid-column name="reply_man" display="回执人" width="100" />
			<powersi:datagrid-column name="reply_date" display="回执时间" width="150" />
			<powersi:datagrid-column name="reply_info" display="回执意见内容" width="100%" minWidth="100" />
		</powersi:datagrid>
	</powersi:panelbox>
	
	<div class="hidden">
		<div id="replyInfoDlg">
			<div id="replyInfoHeader" style="color: #ff5200;text-align: center;font-size: 14px;font-weight:bold;padding-bottom:5px;border-bottom:#dedede 1px solid;">
	        	回执详情
	        </div>
	        <div id="replyInfoContent" style="height:350px; overflow:auto; text-align:left; margin-top: 5px;">
	        </div>
			<div id="replyInfoFooter" style="text-align: center;padding-top:5px;border-top:#dedede 1px solid;">
				<powersi:button id="btClose" key="button_close" onclick="closeReply();" />
			</div>
		</div>
	</div>
	<powersi:errors />
<script type="text/javascript">
	function renderOperate(row, index, value){
		var a = [];
		a.push('<input type="button" value="查看" class="linkButton"');
		a.push(' onclick="viewReplyInfo(');
		a.push(index);
		a.push(')"');
		if(powersi.length(row['reply_date']) == 0){
			a.push(' disabled="disabled"');
		}
		a.push(" />");
		
		return a.join('');
	}

	var dlg;
	function viewReplyInfo(index){
		$("#replyInfoContent").html(grid.getRow(index)["reply_info"]);
		
		if(dlg){
			dlg.show();
		} else{
			dlg = popupDiv("#replyInfoDlg", '查看回执详情', 400);
		}
	}
	
    function closeReply() {
       	if(dlg){
       		dlg.hide();
       	}
    }
</script>
</body>
</powersi:html>