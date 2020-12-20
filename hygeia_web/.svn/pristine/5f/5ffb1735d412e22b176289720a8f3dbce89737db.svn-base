<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>

<%
	String path = request.getContextPath();

	String hospital_id = BizHelper.getAkb020();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
	
	String aae016=request.getParameter("aae016");
%>
<powersi:html>
<head>
<powersi:head title="医院病区管理" />
</head>
<body>
	<powersi:form id="queryForm" namespace="/medicare"
		action="HospManageAction!queryHospAreaInfo.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="btSubmit" label="查 询" buttonIcon="icon-search" onclick="doQuery()"/>
				<powersi:button name="btDel" label="删 除" id="btDel" buttonIcon="icon-minus-sign"
					onclick="doDel()" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="6">
				<powersi:editorlayout-row>
					<powersi:hidden id="akb020" name="hospInfoDto.akb020" />
					<powersi:hidden id="aae100" name="hospInfoDto.aae100" value="1"/>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bkc156" name="hospInfoDto.bkc156"
						key="bkc156" label="科室编码" />
					<powersi:textfield id="bkc157" name="hospInfoDto.bkc157"
						key="bkc157" label="科室名称" />
					<%-- <powersi:codeselect id="aae100" name="hospInfoDto.aae100"
						key="aae100" codeType="valid_flag" label="有效标志" /> --%>
					<powersi:codeselect id="aae016" name="hospInfoDto.aae016"
						key="aae016" codeType="audit_flag" label="审核标志" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>

		</powersi:panelbox>
	</powersi:form>
	<div class="row">
		<div class="col-5">
			<powersi:panelbox key="panel_result" title="科室列表"
				allowCollapse="false">
				<a>双击单条查询病区信息。</a>
				<powersi:form id="deptForm" namespace="/medicare"
					action="HospManageAction!queryHospDeptInfo.action">
					<powersi:hidden id="akb020_dept" name="hospInfoDto.akb020" />
					<powersi:hidden id="aae016_dept" name="hospInfoDto.aae016"
						value="1" />
					<powersi:hidden id="aae100_dept" name="hospInfoDto.aae100"
						value="1" />
					<powersi:datagrid id="deptid" formId="deptForm" delayLoad="true" enabledSort="true"
						showReload="false" onDblClickRow="dbSelectRow">
						<powersi:datagrid-column key="operate" render="renderDept"
							isExport="true" isSort="false" width="80" frozen="true" />
						<powersi:datagrid-column name="bkc157" key="bkc157" label="科室名称" />
						<powersi:datagrid-column name="bkc156" key="bkc156" label="科室编码" />
						<powersi:datagrid-column name="bke510" key="bke510" label="科室分类"
							code="stat_type" />
						<powersi:datagrid-column name="aae016" key="aae016" label="审核状态"
							code="audit_flag" />
						<powersi:datagrid-column name="bke505" key="bke505" label="床位数" />
						<powersi:datagrid-column name="bke504" key="bke504" label="服务范围" />
						<powersi:datagrid-column name="bkc027" key="bkc027" label="维护人" />
						<powersi:datagrid-column name="bkc028" key="bkc028" label="维护人工号" />
						<powersi:datagrid-column name="bkc029" key="bkc029" label="维护时间" />
						<%-- <powersi:datagrid-column name="bae101" key="bae101" label="审核人" />
						<powersi:datagrid-column name="aae014" key="aae014" label="审核人工号" />
						<powersi:datagrid-column name="aae015" key="aae015" label="审核时间" /> --%>
						<powersi:datagrid-column name="aae013" key="aae013" label="备注" />
					</powersi:datagrid>
				</powersi:form>
			</powersi:panelbox>
		</div>
		<div class="col-7">
			<powersi:panelbox iconClass="icon-cog" title="病区维护"
				allowCollapse="false">
				<a>选择病区信息进行删除操作。</a>
				<powersi:datagrid id="areaid" formId="queryForm" delayLoad="true" enabledSort="true"
					showReload="false" checkbox="true" showExportExcel="true"
					showExportExcel2007="true" showExportPdf="true"
					exportFileName="'医院病区信息'">
					<powersi:datagrid-column name="bkc157" key="bkc157" label="科室名称" />
					<powersi:datagrid-column name="bkc154" key="bkc154" label="病区名称" />
					<powersi:datagrid-column name="bkc153" key="bkc153" label="病区编码" />
					<powersi:datagrid-column name="aae016" key="aae016" label="审核状态"
						code="audit_flag" />
					<powersi:datagrid-column name="bkc027" key="bkc027" label="维护人" />
					<powersi:datagrid-column name="bkc028" key="bkc028" label="维护人工号" />
					<powersi:datagrid-column name="bkc029" key="bkc029" label="维护时间"
						format="{0,date,yyyy-MM-dd}" />
					<%-- <powersi:datagrid-column name="bae101" key="bae101" label="审核人" />
					<powersi:datagrid-column name="aae014" key="aae014" label="审核人工号" />
					<powersi:datagrid-column name="aae015" key="aae015" label="审核时间"
						format="{0,date,yyyy-MM-dd}" /> --%>
				</powersi:datagrid>
			</powersi:panelbox>
		</div>
	</div>

	<powersi:errors />

	<script type="text/javascript">

	window.onload = function(){
	 	$('#akb020').val("<%=hospital_id%>");
	 	$('#akb020_dept').val("<%=hospital_id%>");
	 	var hospital_id = $('#akb020').val();

	 	
	 	if($("#akb020").val() == '' || $("#akb020").val() == null ){
			alert("医院编码不能为空，请检查登陆信息！");
	    	return;
		}
	 	 $("#deptForm").submit();
		<%-- postJSON("<%=path%>/medicare/HospManageAction!verifyCenterInfo.action",{"akb020":hospital_id,"verify_flag":"area"}, function(json){
			if(!checkJSONResult(json)){
			    return;
		    }
		    alert(json.message);
		  //刷新
		  $("#deptForm").submit();
			//deptid.reload(true);
  		});   --%>
	}
	
		
   //双击单条科室信息，查询下属的病区
  	function dbSelectRow(rowdata, rowid, rowobj){
  		
  		var row = deptid.getRow(rowid);
  		var bkc156 = row['bkc156'];
  		var akb020 = row['akb020'];
  		
  		if(akb020 == '' || akb020 == null ){
			alert("医院编码不能为空，请检查信息！");
	    	return;
		}
  		
  		if(bkc156 == '' || bkc156 == null ){
			alert("科室编码不能为空，请检查信息！");
	    	return;
		}
  		
  		$('#akb020').val(akb020);
  		$('#bkc156').val(bkc156);
  		$("#queryForm").submit();
  	}
   
  	function renderDept(row, index, value){
		var a = [];
		
		a.push('<input type="button" value="新增" class="linkButton"');
		a.push(' onclick="doAdd(');
		a.push(index);
		a.push(')"');
		if(row['aae016'] == '未审核' || row['aae016'] == '审核不通过'){
			a.push(' disabled="disabled"');
		}
		a.push(" />");
		
		return a.join('');
	}
  	
  	function doAdd(index){
		var row = deptid.getRow(index);
  		var bkc156 = row['bkc156'];
  		var akb020 = row['akb020'];
  		var aae016 = row['aae016'];
  		if( aae016 != '审核通过'){
 			alert("审核过的科室才能新增病区！");
 			return;
 		}
  			
  		popupDialog({
			url: "<%=path%>/medicare/HospManageAction!queryDeptForArea.action?bkc156="+encodeURI(encodeURI(bkc156))+"&aae016="+<%=aae016 %>,
			onClosed: function() {
				var ret = this.returnValue;
				dbSelectRow("", index, "");
			}
		}, 260, 680);

	}
  	function doQuery(){
  		$("#deptForm").submit();
  		$("#queryForm").submit();
  	}
  	
  	function doDel() {
  		
	  	//获取多选，全部勾选的数据
	  		var rows = areaid.getSelectedRows();
	  		/* alert(rows); */
	  		
	  		if(powersi.isempty(rows)){
				alert("请选择病区信息！");
				return;
			}
	  		
	  		var invalid = false;
	  		//判断结果集是否为空，为空下面循环取值会抛异常
			/* $.each(rows, function(i, row){
				if(row['aae016'] == "审核通过"){
					invalid = true;
					areaid.select(row);
					alert('审核通过后的信息不能删除！');
					areaid.reload(true);
					return false;
				}
			}); */
	  		
			if(invalid){
	  			return;
	  		}
			
	  		if (!confirm("您确认删除选择的病区吗?")) {
	            return;
	        }
	  		
	  		var data = powersi.tostring(rows);
	  		
	  		var akb020 = $("#akb020").val();
	  		
	  		postJSON("<%=path%>/medicare/HospManageAction!delHospArea.action",
					{
						"data" : data,
						"akb020" : akb020
					}, function(json) {
						if (!checkJSONResult(json)) {
							return;
						}
						if (json.data=='请先删除该病区下的所有病床！') {
							alert(json.data);
						}else{
							alert(json.message);
						}
						areaid.reload(true);
						// location.reload(true);
					});

		}
	</script>
</body>
</powersi:html>