<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%@ page import="com.powersi.hygeia.framework.util.DateFunc"%>
<%
	String path = request.getContextPath();
%>

<powersi:html>
<head>
<powersi:head title="结论选择" target="_self" />
</head>
<body>
	<powersi:form id="mainform" namespace="/health"
		action="ExaminationAction!chooseJielun.action">
		<powersi:panelbox key="查询条件" cssStyle="display:none;">
			<powersi:textfield id="bkh027" name="setMealDTO.bkh027" />
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox iconClass="icon-cog" allowCollapse="true"
		alternatingRow="true">
		<powersi:datagrid id="jielunGridId" formId="mainform" 
			showReload="false" isMultiSelect="true" delayLoad="false"
			height="85%" autoWidth="true" checkbox="true" enabledEdit="true">
			<powersi:datagrid-column name="bkh149" display="结论编码" hide="true" />
			<powersi:datagrid-column name="bkh150" display="结论名称" width="20%" />
			<powersi:datagrid-column name="bkh151" display="结论内容" width="80%"
				  editor="{type:'popup', onButtonClick: popupSql, autoOpen: true}" />
		</powersi:datagrid>
	</powersi:panelbox>
	<div class="div_center">
		<powersi:button cssClass="button" label="保 存" onclick="save()" />
		<powersi:button cssClass="button" label="取消"
			onclick="javascript:closeDialog();" />
	</div>
	 <div id="sqlDlg" style="display: none">
			<div class="divCentet">
				<powersi:textarea id="sqlArea" name="sqlArea" rows="10" cols="10" maxlength="1000"></powersi:textarea>
			</div>
			<div class="space-y"></div>
			<div class="divButton">
				<powersi:button id="btnSqlOk" key="button_ok" onclick="saveSql()" />
				<powersi:button id="btnSqlClose" key="button_cancel" onclick="closeDlg()" />
			</div>
		</div>
	
	
	<powersi:errors />
	<script type="text/javascript">
	var sqleditor = null;
	function popupSql(editor) {
		if(!editor){
			return;
		}
		//编辑器参数
		//editor.editParm
		//alert(powersi.tostring(editor.editParm.record));
		
		//保存编辑器
		sqleditor = editor;
		
		$('#sqlArea').val(sqleditor.getValue());	
		
		dlg = popupDiv('#sqlDlg', '结论填写', 400, {
			showMax: true, 
			isHidden: false
		});
	}
	
	//保存sql编辑
	function saveSql() {
		var data = $('#sqlArea').val();
		if(sqleditor){
			sqleditor.setValue(data);
			sqleditor.setText(data);
			jielunGridId.endEdit();
		}
		
		closeDlg();
	}
	//所有popupDiv使用close模式，显式设置isHidden为false
	function closeDlg() {
		if(dlg){
			dlg.close();
			dlg = null;
		}
	}
	
	
		function save() {
			var rows = jielunGridId.getSelectedRows();
			var list = new Array();
			if (typeof (rows) == "undefined" || rows == null || rows == "") {
				popupAlert("未选择结论，不能保存！");
				return;
			}
			var counts = 0;
			$.each(rows, function(i, row) {
				if (powersi.isnull(row['bkh151']) || undefined == row['bkh151']) {
					popupAlert("所选体检结论(" + row['bkh150'] + ")未填写结论内容！", "提示",
							"info");
					return false;
				}
				counts+=1;
				var jsonFee = {
					bkh149 : row['bkh149'],
					bkh150 : row['bkh150'],
					bkh151 : row['bkh151']
				}
				list.push(jsonFee);
				
			});
			if(rows.length == counts){
				closeDialog(powersi.tostring(list),500);
			}
		}
	</script>
</body>
</powersi:html>
