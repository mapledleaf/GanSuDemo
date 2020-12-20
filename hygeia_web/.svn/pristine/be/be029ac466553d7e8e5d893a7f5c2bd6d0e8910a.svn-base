<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<!-- 多种疾病选择诊断弹窗 -->
<powersi:head title="多选择疾病诊断" target="_self" />
</head>
<body>
	<powersi:form id="chooseDiseasesForm" namespace="/common"
		action="CommonManagerAction!chooseDiseases.action">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit" key="button_query" />
				<powersi:button cssClass="button" value="确定" onclick="getDiseases()" />
				<powersi:reset id="btReset" value="重 置" onclick="initInfo()" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="13%,27%,13%,47%">
				<powersi:editorlayout-row>
					<powersi:textfield id="aka120" name="ka06dto.aka120" key="aka120" />
					<powersi:textfield id="aka121" name="ka06dto.aka121" key="aka121" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="aka020" name="ka06dto.aka020" key="aka020" />
					<powersi:textfield id="aka021" name="ka06dto.aka021" key="aka021" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
			<powersi:editorlayout cols="13%,87">
			      <powersi:editorlayout-row>
					<powersi:textfield label="选中疾病" id="bka018" readonly="true" />
				  </powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox key="查询结果">
		<powersi:datagrid id="grid" formId="chooseDiseasesForm" delayLoad="true"
			showReload="false" checkbox="true" isMultiSelect="true"
			enabledSort="false" onSelectRow="chooseDisease" 
			onUnSelectRow="unChooseDisease" pageSize="10">
			<powersi:datagrid-column name="aka120" key="aka120" width="40%" />
			<powersi:datagrid-column name="aka121" key="aka121" width="60%" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
	<script type="text/javascript">
		$(document).ready(function() {
			$("#aka020").focus();
		});
		
		function initInfo(){
			$("#aka120").val("");
			$("#aka121").val("");
			$("#aka020").val("");
			$("#aka021").val("");
			$("#bka018").val("");
			grid.reset();
		}
		
		//单击选中疾病选中状态的触发事件
		function chooseDisease(rowdata, rowid, rowobj){
			//方式1：getSelectedRows这种写法会存在翻页时只会保留当前页的数据   不予采用
			//方式2：每次点击时都触发对bka018 的变更
			var diseaseStr = rowdata["aka120"] + "," + rowdata["aka121"];
			var diseasesStr = $("#bka018").val();
			//需要避免重复添加 翻页选中时会有该问题
			if(diseasesStr.indexOf(diseaseStr+"|")>=0){
			}else if(diseasesStr.indexOf("|"+diseaseStr)>=0){
			}else if(diseasesStr.indexOf(diseaseStr)>=0){
			}else{
				if(!powersi.isnull(diseasesStr)){
					diseasesStr = diseasesStr + "|" + diseaseStr;
				}else{
					diseasesStr = diseaseStr;
				}
				$("#bka018").val(diseasesStr);
			}	
		}
		
		//单击取消选中疾病选中状态的触发事件
		function unChooseDisease(rowdata, rowid, rowobj){
			var diseaseStr = rowdata["aka120"] + "," + rowdata["aka121"];
			var diseasesStr = $("#bka018").val();
			if(diseasesStr.indexOf(diseaseStr+"|")>=0){
				diseasesStr = diseasesStr.replace(diseaseStr+"|","");
			}
			if(diseasesStr.indexOf("|"+diseaseStr)>=0){
				diseasesStr = diseasesStr.replace("|"+diseaseStr,"");
			}
			if(diseasesStr.indexOf(diseaseStr)>=0){
				diseasesStr = diseasesStr.replace(diseaseStr,"");
			}
			$("#bka018").val(diseasesStr);
		}
		
		//确定后的返回
		function getDiseases(){
			var diseases = new Object();
			diseases.bka018 = $("#bka018").val();
			if(powersi.isnull(diseases.bka018)){
			  popupConfirm('没有选择疾病，您确认继续吗？', '提示', function (yes) { 
				if(yes){
					setBackInfo(diseases);
				}
			  });
			}else{
				setBackInfo(diseases);
			}
		}
		
		function setBackInfo(diseases){
			//该字段受表结构里的长度控制，暂时先设置为100，也可修改为疾病个数控制（通过'|'获取个数）
			if(!powersi.isnull(diseases.bka018)&&diseases.bka018.length>100){
				popupAlert("选中疾病过多，请重新选择");
				return;
			}
			setDialogReturn(diseases);
			setTimeout("closeDialog();", 500);
		}
	</script>
</body>
</powersi:html>