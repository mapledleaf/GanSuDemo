<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<powersi:html>
<head>
<powersi:head title="医院项目目录管理" />
</head>
<body>
	<powersi:form id="queryForm" namespace="/medicare" action="HospCataAction!queryHospCata.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit" label="查 询" buttonIcon="icon-search"/>
				<powersi:button id="btAdd"    label="新 增" buttonIcon="icon-plus-sign" onclick="doAdd()" />
				<powersi:button id="btDr"     label="导 入" buttonIcon="icon-signin" onclick="openDr()" />
				<powersi:button id="btDel"    label="删 除" buttonIcon="icon-minus-sign" onclick="doDel()" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="10">
				<powersi:editorlayout-row>
					<powersi:codeselect id="caa027" name="hospCataDto.caa027" key="caa027" label="中心系统" headerKey="00" codeType="caa027" />
					<powersi:textfield  id="ake005" name="hospCataDto.ake005" key="ake005" label="医院项目编码" />
					<powersi:textfield  id="ake006" name="hospCataDto.ake006" key="ake006" label="医院项目名称" />
					<powersi:codeselect id="ake003" name="hospCataDto.ake003" key="ake003" label="目录类别" 
						codeType="ake003" headerValue="请选择.." headerKey="2,3" codeFilter="data_value in ('2','3') "/>
					<powersi:codeselect id="aae016_kae8" label="匹配状态"  name="hospCataDto.aae016_kae8"
						list="#{'':'请选择...','-1':'未匹配','0':'未提交审核','3':'待中心审核','1':'审核通过','2':'审核不通过'}" />
					<powersi:hidden id="aae100" name="hospCataDto.aae100" value="1"/>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox key="panel_result" title="目录列表">
		<a>双击单条修改详细信息。</a>
		<powersi:datagrid id="grid" formId="queryForm" delayLoad="false" enabledSort="false"
			checkbox="true" onDblClickRow="dbSelectRow" showExportExcel="true"
			showExportExcel2007="true" showExportPdf="true" exportFileName="'医院项目目录信息'">
			<powersi:datagrid-column name="ake005" key="ake005" label="医院项目编码" frozen="true" width="100"/>
			<powersi:datagrid-column name="ake006" key="ake006" label="医院项目名称" frozen="true" width="100"/>
			<powersi:datagrid-column name="ake003" key="ake003" label="目录类别" code="ake003" frozen="true" width="70"/>
			<powersi:datagrid-column name="aae016_kae8" key="aae016_kae8" label="匹配状态" render="renderAae016" frozen="true" width="70"/>
			<powersi:datagrid-column name="bkc140" key="bkc140" label="单价" width="70"/>
			<powersi:datagrid-column name="aka020" key="aka020" label="拼音码" />
			<powersi:datagrid-column name="aka021" key="aka021" label="五笔码" />
			<powersi:datagrid-column name="bkc139" key="bkc139" label="规格型号" width="70"/>
			<powersi:datagrid-column name="bkc141" key="bkc141" label="生产单位" width="200"/>
			<powersi:datagrid-column name="bkm019" key="bkm019" label="产地" />
			<powersi:datagrid-column name="bkm021" key="bkm021" label="别名" />
			<powersi:datagrid-column name="bkm025" key="bkm025" label="备注" />
			<powersi:datagrid-column name="aae100" key="aae100" label="有效标示" code="valid_flag" />
			<powersi:datagrid-column name="bkc002" key="bkc002" label="经办人" />
			<powersi:datagrid-column name="bkc003" key="bkc003" label="经办人工号" />
			<powersi:datagrid-column name="aae036" key="aae036" label="经办时间"/>
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
</body>
<script type="text/javascript">
  	function doAdd() {
		popupDialog({
			url : "${rootPath}/pages/biz/medicare/catalog/HospItemNew.jsp?caa027=" + $("#caa027").val(),
			onClosed : function() {
				var ret = this.returnValue;
				grid.reload(true);
			}
		}, 400, 900);
	}
  	
	function openDr() {
		popupDialog({
			url : "${rootPath}/pages/biz/medicare/catalog/HospItemDr.jsp?caa027=" + $("#caa027").val(),
			onClosed : function() {
				var ret = this.returnValue;
				grid.reload(true);
			}
		}, screen.height, screen.width);
	}
	
	function doDel() {
		//获取多选，全部勾选的数据
		var rows = grid.getSelectedRows();
		if (powersi.isempty(rows)) {
			popupAlert("请选择需要删除目录", "提示", "error");
			return;
		}
		var invalid = false;
		//判断结果集是否为空，为空下面循环取值会抛异常
		$.each(rows, function(i, row) {
			if (row['aae016_kae8'] != "-1") {
				invalid = true;
				popupAlert("已做匹配的目录不能删除，如需删除，请先删除本条目录的匹配信息", "提示", "error");
				return false;
			}
		});
		if (invalid)
			return;
		if (!confirm("您确认删除选择的目录信息吗?"))
			return;
		var data = powersi.tostring(rows);
		postJSON("${rootPath}/medicare/HospCataAction!delHospCata.action", {
			"data" : data,
			"caa027" : $("#caa027").val()
		}, function(json) {
			if (!checkJSONResult(json))
				return;
			popupAlert(json.message, "提示", "info");
			grid.reload(true);
		});
	}
  	
	function dbSelectRow(rowdata, rowid, rowobj) {
		if ("1" == rowdata['aae016_kae8']) {
			popupAlert("审核通过的目录不能修改", "提示", "error");
			return;
		}
		if ("3" == rowdata['aae016_kae8']) {
			popupAlert("待中心审核的目录不能修改", "提示", "error");
			return;
		}
		popupDialog({
			url : "${rootPath}/medicare/HospCataAction!queryHospCataEdit.action?hospCataDto.ka40id=" + rowdata['ka40id']
					+ "&hospCataDto.caa027=" + $("#caa027").val(),
			onClosed : function() {
				var ret = this.returnValue;
				grid.reload(true);
			}
		}, 400, 900);
	}
  	
   function renderAae016(rowdata, index, value){
		if("-1"==value)
			return "未匹配";
		else if("0"==value)
			return "未提交审核";
		else if("1"==value)
			return "审核通过";
		else if("2"==value)
			return "审核不通过";
		else if("3"==value)
			return "待中心审核";
	}
</script>
</powersi:html>