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
<powersi:head title="医院病床管理" />
</head>
<body>
	<powersi:form id="bedForm" namespace="/medicare"
		action="HospManageAction!queryHospBedInfo.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="btSubmit" label="查 询"  buttonIcon="icon-search" onclick="doQuery()"/>
				<powersi:button name="btDel" label="删 除" id="btDel" buttonIcon="icon-minus-sign"
					onclick="doDel()" />
			</powersi:panelbox-toolbar>

			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="bkc153" label="病区编码"
						name="hospInfoDto.bkc153" />
					<powersi:textfield id="bkc154" label="病区名称"
						name="hospInfoDto.bkc154" />
					<powersi:hidden id="bkc156" label="科室编码"
						name="hospInfoDto.bkc156" />
					<%-- <powersi:codeselect id="aae100" name="hospInfoDto.aae100"
						key="aae100" codeType="valid_flag" label="有效标志" /> --%>
					<powersi:hidden id="aae016" name="hospInfoDto.aae016"
						key="aae016" codeType="audit_flag" label="审核标志" />
				</powersi:editorlayout-row>
				
				<powersi:editorlayout-row>
					<powersi:hidden id="akb020" name="hospInfoDto.akb020" />
					<powersi:hidden id="aae100" name="hospInfoDto.aae100" value="1" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>

		</powersi:panelbox>
	</powersi:form>

	<div class="row">
		<div class="col-5">
			<powersi:panelbox key="panel_result" title="病区列表"
				allowCollapse="false">
				<a>双击单条查询病床信息。</a>
				<powersi:form id="areaForm" namespace="/medicare"
					action="HospManageAction!queryHospAreaInfo.action">
					<powersi:hidden id="akb020_area" name="hospInfoDto.akb020" />
					<powersi:hidden id="aae016_area" name="hospInfoDto.aae016" value="1" />
					<powersi:hidden id="aae100_area" name="hospInfoDto.aae100" value="1" />
					<powersi:datagrid id="areaid" formId="areaForm" delayLoad="true" enabledSort="true"
						showReload="false" onDblClickRow="dbSelectRow">
						<powersi:datagrid-column label="操作" key="operate" render="renderOperate"
							isExport="true" isSort="false" width="80" frozen="true" />
						<powersi:datagrid-column name="bkc157" key="bkc157" label="科室名称" />
						<powersi:datagrid-column name="bkc154" key="bkc154" label="病区名称" />
						<powersi:datagrid-column name="bkc153" key="bkc153" label="病区编码" />
						<powersi:datagrid-column name="aae016" key="aae016" label="审核状态"
							code="audit_flag" />
						<powersi:datagrid-column name="bkc156" key="bkc156" label="科室编码" />
						<powersi:datagrid-column name="bkc027" key="bkc027" label="维护人" />
						<powersi:datagrid-column name="bkc028" key="bkc028" label="维护人工号" />
						<powersi:datagrid-column name="bkc029" key="bkc029" label="维护时间"
							format="{0,date,yyyy-MM-dd}" />
					</powersi:datagrid>
				</powersi:form>
			</powersi:panelbox>
		</div>

		<div class="col-7">
			<powersi:panelbox iconClass="icon-cog" title="病床维护"
				allowCollapse="false">
				<a>双击单条病床信息，进行修改；选择病床信息，进行删除。</a>
				<powersi:form id="areaForm" namespace="/medicare"
					action="HospManageAction!queryHospBedInfo.action">
					<powersi:datagrid  id="bedid" formId="bedForm" delayLoad="true" enabledSort="true"
						showReload="false" checkbox="true" onDblClickRow="dbSelectRowEdit"
						showExportExcel="true" showExportExcel2007="true"
						showExportPdf="true" exportFileName="'医院病床信息'">
						<powersi:datagrid-column name="bkc157" key="bkc157" label="科室名称" />
						<powersi:datagrid-column name="bkc154" key="bkc154" label="病区名称" />
						<powersi:datagrid-column name="bkc161" key="bkc161" label="病床编号" />
						<powersi:datagrid-column name="bkc162" key="bkc162" label="病床房间" />
						<powersi:datagrid-column name="bkc163" key="bkc163" label="床位类型"
							code="bkc163" />
						<powersi:datagrid-column name="bkc164" key="bkc164" label="床位单价" />
						<powersi:datagrid-column name="bkc171" key="bkc171" label="占用标志"
							code="bkc171" />
						<powersi:datagrid-column name="bkc027" key="bkc027" label="维护人" />
						<powersi:datagrid-column name="bkc028" key="bkc028" label="维护人工号" />
						<powersi:datagrid-column name="bkc029" key="bkc029" label="维护时间"
							format="{0,date,yyyy-MM-dd}" />
					</powersi:datagrid>
				</powersi:form>
			</powersi:panelbox>
		</div>
	</div>

	<powersi:errors />

	<script type="text/javascript">

	window.onload = function(){
		$('#akb020').val("<%=hospital_id%>");
		$('#akb020_area').val("<%=hospital_id%>");
		
		if($("#akb020").val() == '' || $("#akb020").val() == null ){
			alert("医院编码不能为空，请检查登陆信息！");
	    	return;
		}
		
		//刷新
		areaid.reload();
	}
	
	function renderOperate(row, index, value){
		var a = [];
		
		a.push('<input type="button" value="新增" class="linkButton"');
		a.push(' onclick="doAdd(');
		a.push(index);
		a.push(')"');
		if(row['aae016'] != '审核通过'){
			a.push(' disabled="disabled"');
		}
		a.push(" />");
		
		a.push("&nbsp;&nbsp;");
		
		a.push('<input type="button" value="导入" class="linkButton"');
		a.push(' onclick="openDr(');
		a.push(index);
		a.push(')"');
		if(row['aae016'] != '审核通过'){
			a.push(' disabled="disabled"');
		}
		
		a.push(" />");
		
		return a.join('');
	}
	
	   //双击单条病区信息，查询下属的病床
  	function dbSelectRow(rowdata, rowid, rowobj){
  		
  		var row = areaid.getRow(rowid);
  		var bkc153 = row['bkc153'];
  		var akb020 = row['akb020'];
  		var bkc156 = row['bkc156'];
  		if(akb020 == '' || akb020 == null ){
			alert("医院编码不能为空，请检查信息！");
	    	return;
		}
  		
  		if(bkc153 == '' || bkc153 == null ){
			alert("病区编码不能为空，请检查信息！");
	    	return;
		}
  		if(bkc156 == '' || bkc156 == null ){
			alert("病区编码不能为空，请检查信息！");
	    	return;
		}
  		$('#akb020').val(akb020);
  		$('#bkc153').val(bkc153);
  		$('#bkc156').val(bkc156);
  		$("#bedForm").submit();
  		
  	}
	   
    //双击单条病床信息，修改
  	function dbSelectRowEdit(rowdata, rowid, rowobj){
  		var row = bedid.getRow(rowid);
  		var kac9id = row['kac9id'];
  			
  		popupDialog({
			url: "<%=path%>/medicare/HospManageAction!queryHospBedEdit.action?hospInfoDto.kac9id="+kac9id,
			onClosed: function() {
				var ret = this.returnValue;
				 bedid.reload(true);
			}
		}, 300, 600);
  	}	
	
  	function doQuery(){
  		$("#areaForm").submit();
  		$("#bedForm").submit();
  	}

	
	function doDel() {
 		
  	//获取多选，全部勾选的数据
  		var rows = bedid.getSelectedRows();
  		
  		if(powersi.isempty(rows)){
			alert("请选择病床信息！");
			return;
		}
  		
  		var invalid = false;
  		//判断结果集是否为空，为空下面循环取值会抛异常
		$.each(rows, function(i, row){
			if(row['bkc171'] == "占用"){
				invalid = true;
				bedid.select(row);
				alert('占用的床位信息不能删除！');
				bedid.reload(true);
				return false;
			}
		});
  		
		if(invalid){
  			return;
  		}
		
  		if (!confirm("您确认删除选择的病床吗?")) {
            return;
        }
  		
  		var data = powersi.tostring(rows);
  		
  		var akb020 = $("#akb020").val();
  		
  		postJSON("<%=path%>/medicare/HospManageAction!delHospBed.action",{"data":data,"akb020":akb020}, function(json){
			if(!checkJSONResult(json)){
			    return;
		    }
		    alert(json.message);
		    bedid.reload(true);
		   // location.reload(true);
  		}); 
  		
	}
	
	function doAdd(index){
		var row = areaid.getRow(index);
  		var bkc153 = row['bkc153'];
  		var bkc156 = row['bkc156'];
  		var aae016 = row['aae016'];
  		if( aae016 != '审核通过'){
 			alert("审核过的病区才能新增床位！");
 			return;
 		}
  			
  		popupDialog({
			url: "<%=path%>/medicare/HospManageAction!queryHospAreaForBed.action?bkc153="+encodeURI(encodeURI(bkc153))+"&aae016=<%=aae016%>&bkc156="+encodeURI(encodeURI(bkc156)),
			onClosed: function() {
				var ret = this.returnValue;
				 dbSelectRow("", index, "");
			}
		}, 300, 600);

	}
	
	
	function openDr(index){
		
		var row = areaid.getRow(index);
  		var bkc153 = row['bkc153'];
  		var bkc156 = row['bkc156'];
  		var aae016 = row['aae016'];
  		if( aae016 != '审核通过'){
 			alert("审核过的病区才能新增床位！");
 			return;
 		}
  			
  		popupDialog({
			url: "<%=path%>/medicare/HospManageAction!openHospDr.action?bkc153="
								+ encodeURI(encodeURI(bkc153))+"&aae016=<%=aae016%>&bkc156="
								+encodeURI(encodeURI(bkc156)),
						onClosed : function() {
							var ret = this.returnValue;
							dbSelectRow("", index, "");
						}
					}, 500, 700);

		}
	</script>
</body>
</powersi:html>