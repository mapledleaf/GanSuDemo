<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>

<%
	String path = request.getContextPath();

	String hospital_id = BizHelper.getAkb020();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
	String aae016 = request.getParameter("aae016");
%>
<powersi:html>
<head>
<powersi:head title="医院科室管理" />
</head>
<body>
	<powersi:form id="deptForm" name="deptForm" namespace="/medicare"
		action="HospManageAction!queryHospDeptInfo.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit" label="查 询"/>
				<powersi:button id="btAdd" label="新 增"  onclick="doAdd()" />
				<powersi:button name="btDel" label="删 除" id="btDel"
					onclick="doDel()" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="bkc157" label="科室名称"
						name="hospInfoDto.bkc157" />
					<powersi:textfield id="bkc156" label="科室编码"
						name="hospInfoDto.bkc156" />
					 <%-- <powersi:codeselect id="aae100" name="hospInfoDto.aae100"
						key="aae100" codeType="valid_flag" label="有效标志" /> --%>
					<%--<powersi:codeselect id="aae016" name="hospInfoDto.aae016"
						key="aae016" codeType="audit_flag" label="审核标志" /> --%>
						<powersi:codeselect id="bke510" name="hospInfoDto.bke510"
						key="bke510" codeType="stat_type" label="科室类别" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
				     <powersi:hidden id="aae100" name="hospInfoDto.aae100" value="1"/> 
					<powersi:hidden id="akb020" name="hospInfoDto.akb020" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>

		</powersi:panelbox>
	</powersi:form>

	<powersi:panelbox iconClass="icon-cog" title="科室维护"
		allowCollapse="false">
		<a>双击单条科室信息修改明细。</a>
		<powersi:datagrid id="deptid" formId="deptForm" delayLoad="true" enabledSort="false"
			showReload="false" checkbox="true" onDblClickRow="dbSelectRow"
			showExportExcel="true" showExportExcel2007="true"
			showExportPdf="true" exportFileName="'医院科室信息'">
			<powersi:datagrid-column name="bkc156" key="bkc156" label="科室编码" frozen="true"/>
			<powersi:datagrid-column name="bkc157" key="bkc157" label="科室名称" frozen="true"/>
			<powersi:datagrid-column name="bke510" key="bke510" label="科室类别" frozen="true" code="stat_type" />
			<powersi:datagrid-column name="bkf01" key="bkf01" label="诊疗科室" code="bkf01" />
			<powersi:datagrid-column name="bke509" key="bke509" label="是否医技科室" code="cure_flag" />
			<powersi:datagrid-column name="bke506" key="bke506" label="医生或药师人数" />
			<powersi:datagrid-column name="bke507" key="bke507" label="护士数" />
			<powersi:datagrid-column name="bke505" key="bke505" label="床位数" />
			<powersi:datagrid-column name="bke504" key="bke504" label="服务范围" />
			<powersi:datagrid-column name="bkc027" key="bkc027" label="维护人" />
			<powersi:datagrid-column name="bkc028" key="bkc028" label="维护人工号" />
			<powersi:datagrid-column name="bkc029" key="bkc029" label="维护时间" />
			<%-- <powersi:datagrid-column name="bae101" key="bae101" label="审核人" />
			<powersi:datagrid-column name="aae014" key="aae014" label="审核人工号" />
			<powersi:datagrid-column name="aae015" key="aae015" label="审核时间" /> --%>
			<powersi:datagrid-column name="aae016" key="aae016" label="审核状态"
				code="audit_flag" />
			<powersi:datagrid-column name="aae100" key="aae100" label="有效标志"
				code="valid_flag" />
			<powersi:datagrid-column name="aae013" key="aae013" label="备注" />
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
		<%-- postJSON("<%=path%>/medicare/HospManageAction!verifyCenterInfo.action",{"akb020":hospital_id,"verify_flag":"dept"}, function(json){
				if(!checkJSONResult(json)){
				    return;
			    }
			    popupAlert(json.message);
			    //刷新datagrid
			    deptid.reload();
	  		});  --%>
		    
		}
	    
      	function doAdd() {
      		popupDialog({
       			url: "<%=path%>/pages/biz/medicare/hosp/HospDeptNew.jsp?aae016=<%=aae016%>",
       			onClosed: function() {
       				var ret = this.returnValue;
       				deptid.reload(true);
       			}
       		}, screen.height, screen.width);
      		
    	}
	  	
	  //双击科室信息列表，修改科室信息
	  	function dbSelectRow(rowdata, rowid, rowobj){
	  		var row = deptid.getRow(rowid);
	  		var bkc155 = row['bkc155'];
	  		var aae016 = row['aae016'];
	  		
	  		/* if(aae016 == "审核通过"){
	  			popupAlert("审核过的科室不能修改！");
     			return;
			} */
	  		
	  		
	  		popupDialog({
				url: "<%=path%>/medicare/HospManageAction!queryHospDeptEdit.action?hospInfoDto.bkc155="+bkc155+"&hospInfoDto.aae016=<%=aae016%>",
				onClosed: function() {
					var ret = this.returnValue;
					deptid.reload(true);
				}
			}, screen.height, screen.width);
	  	}
	  
	  	function doDel() {
	  		
	  	//获取多选，全部勾选的数据
	  		var rows = deptid.getSelectedRows();
	  		/* popupAlert(rows); */
	  		
	  		if(powersi.isempty(rows)){
				popupAlert("请选择科室信息！");
				return;
			}
	  		
	  		var invalid = false;
	  		//判断结果集是否为空，为空下面循环取值会抛异常
			$.each(rows, function(i, row){
				if(row['aae016'] == "审核通过"){
					invalid = true;
					deptid.select(row);
					popupAlert(row['bkc157']+':审核通过后的信息不能删除！');
					deptid.reload(true);
					return false;
				}
			});
	  		
	  		if(invalid){
	  			return;
	  		}
	  		if (!confirm("您确认删除选择的科室吗?")) {
	            return;
	        }
	  		
	  		var data = powersi.tostring(rows);
	  		
	  		var akb020 = $("#akb020").val();
	  		
	  		postJSON("<%=path%>/medicare/HospManageAction!delHospDept.action",
					{
						"data" : data,
						"akb020" : akb020
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
