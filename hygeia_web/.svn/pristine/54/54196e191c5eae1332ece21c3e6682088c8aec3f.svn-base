<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<powersi:head title="医院疾病目录管理" />
</head>
<body>
	<powersi:form id="queryForm" namespace="/medicare"
		action="HospCataAction!queryHospDiseasePage.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit" label="查 询" buttonIcon="icon-search"/>
				<powersi:button id="btAdd"    label="新 增" buttonIcon="icon-plus-sign" onclick="doAdd()" />
				<powersi:button id="btDr"     label="导 入" buttonIcon="icon-signin" onclick="openDr()" />
				<powersi:button id="btDel"    label="删 除" buttonIcon="icon-minus-sign" onclick="doDel()" />
				<powersi:button id="btCopy"   label="生成目录" buttonIcon="icon-search" onclick="doCopy()" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:codeselect id="caa027" name="diseaseDto.caa027" key="caa027" label="中心系统" 
						codeType="caa027" headerKey="00"/>
					<powersi:textfield  id="aka420" name="diseaseDto.aka420" key="aka420" label="医院疾病编码" />
					<powersi:textfield  id="aka421" name="diseaseDto.aka421" key="aka421" label="医院疾病名称" />
					<powersi:codeselect id="aae100" name="diseaseDto.aae100" key="aae100" label="有效标志" 
						codeType="valid_flag" value="1" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox key="panel_result" title="目录列表">
		<a>双击单条修改详细信息。</a>
		<powersi:datagrid id="grid" formId="queryForm" delayLoad="false" checkbox="true" 
			onDblClickRow="dbSelectRow" showExportExcel="true" showExportExcel2007="true" 
			showExportPdf="true" enabledSort="false" exportFileName="'医院疾病目录信息'">
			<powersi:datagrid-column name="aka420" key="aka420" label="医院疾病编码" frozen="true" />
			<powersi:datagrid-column name="aka421" key="aka421" label="医院疾病名称" frozen="true" />
			<powersi:datagrid-column name="aae016_ke06" key="aae016_ke06" label="匹配状态" render="renderAae016" frozen="true"/>
			<powersi:datagrid-column name="aka122" key="aka122" label="疾病分类" />
			<powersi:datagrid-column name="aka062" key="aka062" label="英文化学名" />
			<powersi:datagrid-column name="aka020" key="aka020" label="拼音码" />
			<powersi:datagrid-column name="aka021" key="aka021" label="五笔码" />
			<powersi:datagrid-column name="aae013" key="aae013" label="备注" />
			<powersi:datagrid-column name="aae100" key="aae100" label="有效标志" code="aae100"/>
			<powersi:datagrid-column name="bkc002" key="bkc002" label="经办人" />
			<powersi:datagrid-column name="bkc003" key="bkc003" label="经办人工号" />
			<powersi:datagrid-column name="aae036" key="aae036" label="经办时间" render="renderAae036" />
			<powersi:datagrid-column name="ka60id" key="ka60id" label="主键id" hide="true"/>
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
</body>
<script type="text/javascript">	
	function doCopy() {
		var caa027 = $("#caa027").val();
		postJSON("${rootPath}/medicare/HospCataAction!copyHospDisease.action", {
			'caa027' : caa027
		}, function(json) {
			if (!checkJSONResult(json)) {
				return;
			}
			alert(json.message);
			grid.reload(true);
		});
	}
  	
  	function doAdd() {
		popupDialog({
			url : "${rootPath}/pages/biz/medicare/catalog/HospDiseaseNew.jsp?caa027=" + $("#caa027").val(),
			onClosed : function() {
				grid.reload(true);
			}
		}, 400, 900);
	}

  	function openDr() {
		popupDialog({
			url : "${rootPath}/pages/biz/medicare/catalog/HospDiseaseDr.jsp?caa027=" + $("#caa027").val(),
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
			if (row['aae016_ke06'] != -1) {
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
		postJSON("${rootPath}/medicare/HospCataAction!delHospDisease.action", {
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
		if ("1" == rowdata['aae016_ke06']) {
			popupAlert("审核通过的目录不能修改", "提示", "error");
			return;
		}
		if ("3" == rowdata['aae016_ke06']) {
			popupAlert("待中心审核的目录不能修改", "提示", "error");
			return;
		}
		popupDialog({
			url : "${rootPath}/medicare/HospCataAction!queryHospDiseaseEdit.action?diseaseDto.ka60id=" + 
					rowdata['ka60id'] + "&diseaseDto.caa027=" + $("#caa027").val(),
			onClosed : function() {
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
	
	function renderAae036(rowdata, index, value){
		return value;
	}
	
</script>
</html>