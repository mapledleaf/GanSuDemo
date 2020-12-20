<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<powersi:head title="选择医院计划" target="_self" />
<body>
	<powersi:form id="Form" namespace="/actualize"
		action="ActualizeManageAction!queryHospPlan.action">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit" key="button_query" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="4">
				<powersi:editorlayout-row>
					<powersi:textfield id="bkf318" name="kfd3Dto.bkf318"  label="计划名称"/>
					<powersi:codeselect id="aae100" name="kfd3Dto.aae100" key="aae100"
						codeType="plan_flag" label="计划状态" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox key="查询结果">
		<powersi:datagrid id="row" formId="Form" delayLoad="false"
			showReload="false" checkbox="true" isMultiSelect="false"
			enabledSort="false" onSelectRow="choosePlan" pageSize="10">
			<powersi:datagrid-column name="bkf307" label="计划名称id" hide="true"/>
			<powersi:datagrid-column name="bkf318" width="35%" label="计划名称"/>
			<powersi:datagrid-column name="aae100" width="15%" label="计划状态" code="plan_flag"/>
			<powersi:datagrid-column name="bkf320" width="25%" label="计划开始时间" format="{0,date,yyyy-MM-dd}"/>
			<powersi:datagrid-column name="bkf321" width="25%" label="预计结束时间" format="{0,date,yyyy-MM-dd}"/>
		</powersi:datagrid>
	</powersi:panelbox>
</body>
</powersi:html>
<script type="text/javascript">
	$(document).ready(function() {
		$("#bkf318").focus();
	});
	
	function choosePlan(rowdata, rowid, rowobj) {
		var disease = new Object();
		disease.bkf307 = rowdata['bkf307'];
		disease.bkf318 = rowdata['bkf318'];
		disease.aae100 = rowdata['aae100'];
		disease.bkf320 = rowdata['bkf320'].substring(0,10);
		disease.bkf321 = rowdata['bkf321'].substring(0,10);
		if (disease) {
			setDialogReturn(disease);
		}
		setTimeout("closeDialog();", 500);
	}
	
	function renderAae100(rowdata,index,value){
		if(value=="1"){
			 return "激活";
		}else if(value=="0"){
			return "关闭";
		}else{
			return " ";
		}
	}
	
	function renderDate(rowdata,index,value) {
        if(value!=null&&value !=""){
        	 var date = new Date(value);
             return date.getFullYear() + '-'+ (date.getMonth() + 1) + '-' + date.getDate();
        }else{
             return " ";
        }
    }
</script>