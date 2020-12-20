<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	String path = request.getContextPath();
%>
   
<powersi:html>
	<head>
		<powersi:head title="转诊转院医院选择" target="_self"/>
	</head>
	<body>
		<powersi:form id="hospForm" disabled="true">
			<div>
				<powersi:hidden id="akb020" name="mediSpecDto.akb020"  label="医院编号"  />
				<powersi:hidden id="akb021" name="mediSpecDto.akb021"  label="医院名称"  />
			</div>
			<powersi:editorlayout cols="4">
				<td align="right" class="tdLable" style="background-color:#f4f8fa;">检索方法</td>
						<td>
							<select id="jsz002" name="jsz002" class="select" onchange="">
								<option value="0">医院编号</option>
								<option value="1">医院名称</option>
							</select>
						</td>
				<powersi:textfield id="jsz001" name="jsz001" key="检索值" />
			</powersi:editorlayout>
			<powersi:editorlayout cols="4">
				<tr>
					<td colspan="4" align="center">
						<powersi:button key="button_query" value="检索" onclick="search()"/>	
						<powersi:button key="button_exit" value="取消" onclick="closeDialog()"/>	
						
					</td>
				</tr>
			</powersi:editorlayout>
			<powersi:datagrid id="hospid" formId="hospForm" delayLoad="true" onDblClickRow="doubleClick">
				<powersi:datagrid-column id="akb020" name="hospital_id"  label="医院编号"  width="120" />
				<powersi:datagrid-column id="akb021" name="hospital_name"  label="医院名称"  width="250" />
			</powersi:datagrid>
		</powersi:form>
		<powersi:errors/>
<script type="text/javascript">
	
	function doubleClick(rowdata, rowid, rowobj){
		var ho = new Object();
		var row = hospid.getRow(rowid);
		ho.akb020 = row['hospital_id'];
		ho.akb021 = row['hospital_name'];
	
		if(ho){
			setDialogReturn(ho);
		}
		closeDialog();
	}
	  document.onkeydown=function(event){
		  var e = event || window.event || arguments.callee.caller.arguments[0];
		   if(e && e.keyCode==13){ // enter 键
			   search();
          }
	  }
	function search(){	
		var jsz001 = $("#jsz001").val();
		var jsz002 = $("#jsz001").val();
		if(jsz001 == "" || jsz001 =="null"){
			popupAlert("请输入查询条件!");
			return;
		}
		
		if($("#jsz002").val() == "0" || $("#jsz002").val() != "1"){
			var akb020 = $("#jsz001").val();
		}else{
			var akb021 = $("#jsz001").val();
		}
	//	var saveItemData = $("#hospForm").serialize();
		postJSON("<%=path%>/medicare/ChangeHospitalBusApplyAction!queryChangeHospitalChoose.action",{"mediSpecDto.akb020":akb020,"mediSpecDto.akb021":akb021},function(json){
			if(!checkJSONResult(json)){
				return;
			}
			//加载结果集到datagrid
			hospid.loadData(json.data);
		});
	}
</script>
	</body>
</powersi:html>