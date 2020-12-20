<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>

<%
	String hospital_id = BizHelper.getAkb020();
%>
<powersi:html>
<head>
<powersi:head title="两病三师备案管理" />
</head>
<body>
	<powersi:form id="queryForm" name="queryForm" namespace="/medicare"
		action="TeamVsSelfPayApplyAction!getTwoDiseaseThreeMasterApply.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit" label="查 询"/>
				<powersi:button id="btAdd" label="申 请"  onclick="doAdd()" />
				<powersi:button name="btDel" label="删 除" id="btDel"
					onclick="doDel()" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="4">
				<powersi:editorlayout-row>
					<powersi:codeselect id="aae100" name="tDisThreeMTeamDto.aae100"
						key="aae100" codeType="valid_flag" label="有效标志" />
					<powersi:codeselect id="aae016" name="tDisThreeMTeamDto.aae016"
						key="aae016" codeType="audit_flag" label="审核标志" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:hidden id="akb020" name="tDisThreeMTeamDto.akb020" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>

		</powersi:panelbox>
	</powersi:form>

	<powersi:panelbox iconClass="icon-cog" title="信息维护"
		allowCollapse="false">
		<a>双击单条信息修改明细。</a>
		<powersi:datagrid id="deptid" formId="queryForm" delayLoad="true" enabledSort="false"
			showReload="false" checkbox="true" onDblClickRow="dbSelectRow"
			showExportExcel="true" showExportExcel2007="true"
			showExportPdf="true" exportFileName="'两病三师团队备案申请信息'">
			<powersi:datagrid-column name="aaz308" key="aaz308" label="三师团队编码" />
			<powersi:datagrid-column name="akb020" key="akb020" label="医院编码" />
			<powersi:datagrid-column name="bka801" key="bka801" label="专科医师ID"/>
			<powersi:datagrid-column name="bka802" key="bka802" label="专科医师姓名"/>
			<powersi:datagrid-column name="bka803" key="bka803" label="全科医师ID"/>
			<powersi:datagrid-column name="bka804" key="bka804" label="全科医师姓名"/>
			<powersi:datagrid-column name="bka805" key="bka805" label="专科医师ID"/>
			<powersi:datagrid-column name="aae036" key="aae036 " label="维护时间" />
			<powersi:datagrid-column name="aae011" key="bkc028" label="维护人工号" />
			<powersi:datagrid-column name="bae100" key="bkc028" label="维护人" />
			<powersi:datagrid-column name="aae015" key="aae015" label="审核时间" />
			<powersi:datagrid-column name="aae014" key="aae014" label="审核人工号" />
			<powersi:datagrid-column name="bae101" key="bae101" label="审核人" />
			<powersi:datagrid-column name="aae016" key="aae016" label="审核状态"
				code="audit_flag" />
			<powersi:datagrid-column name="aae100" key="aae100" label="有效标志"
				code="valid_flag" />
		</powersi:datagrid>
	</powersi:panelbox>

	<powersi:errors />


	<script type="text/javascript">
		window.onload = function(){
			
			$('#akb020').val("<%=hospital_id%>");
		 	var hospital_id = $('#akb020').val();
		 	
		 	if($("#akb020").val() == '' || $("#akb020").val() == null ){
				popupAlert("医院编码不能为空，请检查登陆信息！");
		    	return;
			}
		 	deptid.reload();
		    
		 	//不限制新增操作，避免有审核不通过的信息却无法再次申请
		 	/* var rows = deptid.getRows();
	  		if(!powersi.isempty(rows)){
	  			$("#btAdd").attr("disabled", true);
			}else{
				$("#btAdd").attr("disabled", false);
			} */
		}
	    
      	function doAdd() {
      		var rows = deptid.getRows();
      		var invalid = false;
      		$.each(rows, function(i, row){
				if(row['aae016'] == "审核通过"){
					invalid = true;
					deptid.select(row);
					popupAlert(row['aaz308']+':已存在审核通过的备案申请，无需重复申请！');
					deptid.reload(true);
					return false;
				}
			});
      		if(invalid){
	  			return;
	  		}
      		var url = rootPath+"/medicare/TeamVsSelfPayApplyAction!initTwoDiseaseThreeMasterApply.action";
      		popupDialog({
       			//url: "${rootPath}/pages/biz/medicare/special/TwoDiseaseThreeMasterNew.jsp",
       			url: url,
       			onClosed: function() {
       				var ret = this.returnValue;
       				deptid.reload(true);
       			}
       		}, screen.height, screen.width);
    	}
	  	
	  //双击信息列表，修改信息
	  	function dbSelectRow(rowdata, rowid, rowobj){
	  		var row = deptid.getRow(rowid);
	  		var aaz308 = row['aaz308'];
	  		var aae016 = row['aae016'];
	  		
	  		if(aae016 == "审核通过" || aae016 == "审核不通过"){
	  			popupAlert("已审核的信息不能再修改！");
     			return;
			}
	  		
	  		
	  		popupDialog({
				url: "${rootPath}/medicare/TeamVsSelfPayApplyAction!queryTwoDisThreeMApplyEdit.action?tDisThreeMTeamDto.aaz308="+aaz308,
				onClosed: function() {
					var ret = this.returnValue;
					deptid.reload(true);
				}
			}, screen.height, screen.width);
	  	}
	  
	  	function doDel() {
	  		//获取多选，全部勾选的数据
	  		var rows = deptid.getSelectedRows();
	  		if(powersi.isempty(rows)){
				popupAlert("请选择要删除的信息！");
				return;
			}
	  		
	  		var invalid = false;
	  		//判断结果集是否为空，为空下面循环取值会抛异常
			$.each(rows, function(i, row){
				if(row['aae016'] == "审核通过"){
					invalid = true;
					deptid.select(row);
					popupAlert(row['aaz308']+':审核通过后的信息不能删除！');
					deptid.reload(true);
					return false;
				}
			});
	  		
	  		if(invalid){
	  			return;
	  		}
	  		if (!confirm("您确认删除选择的信息吗?")) {
	            return;
	        }
	  		
	  		var data = powersi.tostring(rows);
	  		
	  		postJSON("${rootPath}/medicare/TeamVsSelfPayApplyAction!delTwoDiseaseThreeMasterApply.action",
					{
						"data" : data
					}, function(json) {
						if (!checkJSONResult(json)) {
							return;
						}
						popupInfo(json.message);
						deptid.reload(true);
					});

		}
	</script>
</body>
</powersi:html>
