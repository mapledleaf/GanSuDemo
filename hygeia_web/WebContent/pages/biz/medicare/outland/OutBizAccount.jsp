<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>


<powersi:html>
<head>
<powersi:head title="跨省异地日对账功能" />
</head>
<body>
	<powersi:form id="queryForm" namespace="/medicare"
		action="OutAccountBizAction!queryOutAccount.action">
		<powersi:panelbox key="条件设置">
			<powersi:panelbox-toolbar>
			    <powersi:submit id="btSubmit" label="查 询" />
				<powersi:button id="btGetDetail" label="对账"  onclick="accountData()"/>
				<powersi:reset id="btReset" value="清空" key="button_reset" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="6">
				<tr>
				 <powersi:codeselect id="ykc705" required="true"  name="outAccountBizDTO.ykc705" label="对账类型"  
					 list="#{'0':'参保地','1':'就医地'}" />
				 <powersi:textfield id="ykc706" required="true"  name="outAccountBizDTO.ykc706" label="对账日期" validate = "integer,max[209912],min[190001],maxSize[6],minSize[6]"
				 onclick="WdatePicker({dateFmt:'yyyyMM'})" />
				  <td colspan="4"></td>
				</tr>
			</powersi:editorlayout>
		</powersi:panelbox>
		 
	</powersi:form>
	

	<powersi:panelbox >
       <powersi:datagrid id="accountGrid" formId="queryForm"  checkbox="true"  
			delayLoad="true" enabledEdit="true" clickToEdit="true" 
		    enabledSort="false" showReload="false">
			<powersi:datagrid-column name="aab299" display="行政区划代码（就医地）" width="150" />
			<powersi:datagrid-column name="aab301" display="行政区划代码（参保地）"  width="150" />
			<powersi:datagrid-column name="akc264" display="医疗费总额"  width="120" />
			<%-- <powersi:datagrid-column name="bae007" display="就诊登记号"  width="100" /> --%>
			<powersi:datagrid-column name="ake149" display="医保基金支付总额"  width="120" />
			<powersi:datagrid-column name="akc194" display="就诊结算时间"  width="120" />
			<%-- <powersi:datagrid-column name="bae008" display="全额垫付标志"  width="80" /> --%>
			<powersi:datagrid-column name="aaa113" display="交易类型"  width="80" />
			<powersi:datagrid-column name="ykc700" display="就诊登记号"  width="80" />
			<powersi:datagrid-column name="aaz216" display="结算流水号"  width="100" />
			<powersi:datagrid-column name="otransid" display="原交易流水号"  width="100" />
			<powersi:datagrid-column name="totalrow" hide="true"/>
			<powersi:datagrid-column name="ykc706" hide="true"/>
			<powersi:datagrid-column name="ykc705" hide="true"/>
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
	<script type="text/javascript">


	/*日对账操作*/
	function accountData(){
		var url="${rootPath}/medicare/OutAccountBizAction!OutAccountData.action";
		var params=form2json("#queryForm");
		var data = accountGrid.getSelectedRows();
		if(powersi.isempty(data)){
			popupAlert("请选择一行记录确认！");
			return;
		}
		params.outAccountDtoList=powersi.tostring(data);
		postJSON(url, params, function(json){
			if(!checkJSONResult(json)){
			    return;
		    }
			popupInfo(json.data);  
			accountGrid.reset();
		});
	}
	
	
	</script>
</body>
</powersi:html>