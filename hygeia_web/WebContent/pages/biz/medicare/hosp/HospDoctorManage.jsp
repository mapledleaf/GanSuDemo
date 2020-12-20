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
<powersi:head title="医师信息维护" />
</head>
<body>
	<powersi:form id="queryForm" namespace="/medicare"
		action="HospManageAction!queryHospDoctorInfo.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit" label="查 询" buttonIcon="icon-search"/>
				<powersi:button id="btAdd"    label="新 增"     onclick="doAdd()" buttonIcon="icon-plus-sign"/>
				<powersi:button id="btChang"  label="异动申请"  onclick="doChangApply()" />
				<powersi:button id="btDr"     label="导入" 	   onclick="openDr()" buttonIcon="icon-signin" />
				<powersi:button id="btDel"    label="删 除"     onclick="doDel()"  buttonIcon="icon-minus-sign"  />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="bkc275" name="hospInfoDto.bkc275"
						key="bkc275" label="医师姓名" />
					<powersi:textfield id="bkc157" name="hospInfoDto.bkc157"
						key="bkc157" label="科室名称" />
					<%-- <powersi:codeselect id="aae100" name="hospInfoDto.aae100"
						key="aae100" codeType="valid_flag" label="有效标志" /> --%>
					<powersi:codeselect id="aae016" name="hospInfoDto.aae016"
						key="aae016" codeType="audit_flag" label="审核标志" />

				</powersi:editorlayout-row>

				<powersi:editorlayout-row>
					<powersi:hidden id="akb020" name="hospInfoDto.akb020" />
					<powersi:hidden id="aae100" name="hospInfoDto.aae100" value="1"/>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>

	<powersi:panelbox key="panel_result" title="医师列表">
		<a>双击单条修改详细信息。</a>
		<powersi:datagrid id="grid" formId="queryForm" delayLoad="true" enabledSort="true"
			showReload="false" checkbox="true" onDblClickRow="dbSelectRow"
			showExportExcel="true" showExportExcel2007="true"
			showExportPdf="true" exportFileName="'医院医师信息'">
			<powersi:datagrid-column name="bkc275" key="bkc275" label="医师姓名"
				frozen="true" />
			<powersi:datagrid-column name="bkc274" key="bkc274" label="编号"
				frozen="true" />
			<powersi:datagrid-column name="bkc276" key="bkc276" label="医师执业情况"
				frozen="true" code="bkc276" />
			<powersi:datagrid-column name="bkc157" key="bkc157" label="医师所属科室"
				frozen="true" />
			<powersi:datagrid-column name="bkc270" key="bkc270" label="医师职务"
				code="bkc270" />
			<powersi:datagrid-column name="bkc272" key="bkc272" label="医师职称"
				code="bkc272" />
			<powersi:datagrid-column name="bkc277" key="bkc277" label="医师性别"
				code="aac004" />
			<powersi:datagrid-column name="aka020" key="aka020" label="拼音助记码" />
			<powersi:datagrid-column name="aka021" key="aka021" label="五笔助记码" />
			<powersi:datagrid-column name="bkc280" key="bkc280" label="移动电话号码" />
			<powersi:datagrid-column name="bkc278" key="bkc278" label="出生日期"
				format="{0,date,yyyy-MM-dd}" />
			<powersi:datagrid-column name="bkc279" key="bkc279" label="社会保障号" />
			<powersi:datagrid-column name="bkc288" key="bkc288" label="违规医师处理类别"
				code="blacklist" />
			<powersi:datagrid-column name="aae100" key="aae100" label="有效标志"
				code="valid_flag" />
			<powersi:datagrid-column name="bkc028" key="bkc028" label="维护工号" />
			<powersi:datagrid-column name="bkc027" key="bkc027" label="维护人姓名" />
			<powersi:datagrid-column name="bkc029" key="bkc029" label="维护时间"
				format="{0,date,yyyy-MM-dd}" />
			<%-- <powersi:datagrid-column name="aae015" key="aae015" label="审核时间"
				format="{0,date,yyyy-MM-dd}" /> --%>
			
			<powersi:datagrid-column name="aae016" key="aae016" label="审核标志" code="audit_flag"/>
			<powersi:datagrid-column name="bkc281" key="bkc281" label="专业" />
			<powersi:datagrid-column name="bkc291" key="bkc291" label="简历" />
			<powersi:datagrid-column name="bke952" key="bke952" label="门特责任医师种类"
				code="special_type" />
			<powersi:datagrid-column name="bke953" key="bke953" label="特殊技术资质"
				code="special_lisence" />
			<powersi:datagrid-column name="bke954" key="bke954" label="特殊项目资质"
				code="special_item" />
			<powersi:datagrid-column name="bke955" key="bke955" label="执业医师资格证编号" />
		</powersi:datagrid>
	</powersi:panelbox>

	<powersi:errors />

	<script type="text/javascript">

	window.onload = function(){
		$('#akb020').val("<%=hospital_id%>");  
		
		if($("#akb020").val() == '' || $("#akb020").val() == null ){
			alert("医院编码不能为空，请检查登陆信息！");
	    	return;
		}
		
		var hospital_id = $("#akb020").val();
		grid.reload(true);
		<%-- postJSON("<%=path%>/medicare/HospManageAction!verifyCenterInfo.action",{"akb020":hospital_id,"verify_flag":"doctor"}, function(json){
			if(!checkJSONResult(json)){
			    return;
		    }
		    alert(json.message);
		  //刷新
			grid.reload(true);
  		});  
		 --%>
	}
	
	
	function doDel() {
  		
	  	//获取多选，全部勾选的数据
	  		var rows = grid.getSelectedRows();
	  		/* alert(rows); */
	  		
	  		if(powersi.isempty(rows)){
				alert("请选择医师信息！");
				return;
			}
	  		
	  		var invalid = false;
	  		//判断结果集是否为空，为空下面循环取值会抛异常
			$.each(rows, function(i, row){
				/* if(row['aae016'] == "审核通过"){
					invalid = true;
					grid.select(row);
					alert('审核后的信息不能删除！');
					grid.reload(true);
					return false;
				} */
				if(row['aae100'] == "无效"){
					invalid = true;
					grid.select(row);
					alert('无效的信息不需要删除！');
					grid.reload(true);
					return false;
				}
			});
	  		
			if(invalid){
	  			return;
	  		}
			
	  		if (!confirm("您确认删除选择的医师信息吗?")) {
	            return;
	        }
	  		
	  		var data = powersi.tostring(rows);
	  		
	  		var akb020 = $("#akb020").val();
	  		
	  		postJSON("<%=path%>/medicare/HospManageAction!delHospDoctor.action",{"data":data,"akb020":akb020}, function(json){
				if(!checkJSONResult(json)){
				    return;
			    }
			    alert(json.message);
			    grid.reload(true);
	  		}); 
	  		
		}
	

  	function doAdd() {
  		var akb020 = $("#akb020").val();
  		popupDialog({
			url: "<%=path%>/medicare/HospManageAction!openNewDoctor.action?hospInfoDto.akb020="+akb020+"&flag=add&aae016=<%=aae016%>",
			onClosed: function() {            
				var ret = this.returnValue;
				 grid.reload(true);
			}
		}, screen.height, screen.width);


	}
  	
  	function dbSelectRow(rowdata, rowid, rowobj){
  		var row = grid.getRow(rowid);
  		var bkc269 = row['bkc269'];
  		var aae016 = row['aae016'];
  		/* if(aae016 == "审核通过"){
  			alert("审核过的医师不能修改！");
 			return;
		} */
  		popupDialog({
			url: "<%=path%>/medicare/HospManageAction!queryHospDoctorEdit.action?hospInfoDto.bkc269="+bkc269,
			onClosed: function() {
				var ret = this.returnValue;
				grid.reload(true);
			}
		}, screen.height, screen.width);
  	}

  	function openDr(){
  		var akb020 = $("#akb020").val();
  		popupDialog({
			url: "<%=path%>/medicare/HospManageAction!openNewDoctor.action?hospInfoDto.akb020="
								+ akb020 + "&flag=dr",
						onClosed : function() {
							var ret = this.returnValue;
							grid.reload(true);
						}
					}, screen.height, screen.width);
	}
  	
  	function doChangApply(){
  		var row = grid.getSelectedRow();
  		if(powersi.isempty(row)){
			alert("请选择医师信息！");
			return;
		}
  		var bkc269 = row['bkc269'];
  		popupDialog({
			url: "<%=path%>/medicare/HospManageAction!queryHospDoctorChang.action?hospInfoDto.bkc269="+bkc269,
			onClosed: function() {
				var ret = this.returnValue;
				grid.reload(true);
			}
		}, 300, 900);
  	}
</script>
</body>
</powersi:html>