<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<powersi:head title="选择医疗机构信息" target="_self" />
<%
	String path = request.getContextPath();
	String bke079 = request.getParameter("bke079");
	String bke080 = request.getParameter("bke080");

%>
<powersi:html>

<powersi:form name="mainForm"
	action="MedOrgComQueryAction!queryHospInfo.action"
	namespace="/common">

	<powersi:groupbox title="查询条件">
		<powersi:editorlayout cols="6">
			<powersi:editorlayout-row>
				<powersi:hidden id="baf305" name="hospDTO.baf305" />
				<powersi:hidden id="baf306" name="hospDTO.baf306" />
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="akb020" name="hospDTO.akb020" label="机构编码" />
				<powersi:textfield id="akb021" name="hospDTO.akb021" label="机构名称" />
				<powersi:editorlayout-button colspan="2">
					<powersi:submit id="btSubmit" key="button_query" />
					<powersi:button cssClass="button" value="确定" onclick="getHospInfo();" />
					<powersi:button cssClass="button" value="取消" onclick="javascript:closeDialog();"/>
				</powersi:editorlayout-button>
			</powersi:editorlayout-row>
		</powersi:editorlayout>
	</powersi:groupbox>
	<powersi:groupbox title="查询结果">

		<powersi:datagrid id="grid" formId="mainForm" delayLoad="true"
			showReload="false" checkbox="true" enabledSort="false" >
			<powersi:datagrid-column name="akb020" display="医疗机构编号" />
			<powersi:datagrid-column name="akb021" display="机构名称" />
			<powersi:datagrid-column name="bkc110" display="机构级别" code=""/>
			<powersi:datagrid-column name="akb023" display="机构类别" />
			<powersi:datagrid-column name="aka101" display="机构等级" />
			<powersi:datagrid-column name="bkc111" display="异地类型" />
			<powersi:datagrid-column name="aka020" display="拼音码" />
			<powersi:datagrid-column name="aka021" display="五笔码" />
		</powersi:datagrid>

	</powersi:groupbox>
</powersi:form>
<script type="text/javascript">

window.onload = function(){
	$("#baf305").val("<%=bke079%>");
	$("#baf306").val("<%=bke080%>");
}

function getHospInfo()
{

	var rows = grid.getSelectedRows();
	
	if(powersi.isempty(rows)){
		popupAlert("没有选择定点医疗机构！");
		return;
	}

	var hosps = new Array();

	for ( var i = 0; i < rows.length; i++) {
		var obj = {};
	  	obj.akb020 = rows[i]["akb020"];//医院编码
	  	obj.aab069 = rows[i]["akb021"];//医院名称
	  	obj.aka101 = rows[i]["aka101"];//医院等级
	  	obj.bkc110 = rows[i]["bkc110"];//医院级别
	  	obj.bkc111 = rows[i]["bkc111"];//异地类型
	  	obj.akb023 = rows[i]["akb023"];//医院类别
	  	obj.baf305 = rows[i]["baf305"];//医院类别
	  	obj.baf306 = rows[i]["baf306"];//医院类别
		hosps[i] = obj;
	}
	setDialogReturn(hosps);
	setTimeout("closeDialog();", 500);
}

function changeCity(sel){
    postJSON("${rootPath}/medicare/OutBizAction!getCityAndDept.action",{"outBizDTO.bkd326":sel,"outBizDTO.bkd327":1},function(json){
			if(!checkJSONResult(json)){
			    return;
		    }
		 	var citys = json.data;
	      var select = document.getElementById('bke080');
	      document.getElementById('bke081').options.length = 0;
	      select.options.length = 0;
	      var option = new Option("请选择...","0");
	      select.add(option);
	      for(i=0;i<citys.length;i++){
	         var city_value = citys[i].bkd325;
	         var vity_name = citys[i].bkd324;
	         var option = new Option(vity_name,city_value);
	         select.add(option);
	      }
		});  
}

function changeDept(sel){
    postJSON("${rootPath}/medicare/OutBizAction!getCityAndDept.action",{"outBizDTO.bkd326":sel,"outBizDTO.bkd327":2}, function(json){
		if(!checkJSONResult(json)){
		    return;
	    }
		var citys = json.data;
	      var select = document.getElementById('bke081');
	      select.options.length = 0;
	      var option = new Option("请选择...","0");
	      select.add(option);
	      for(i=0;i<citys.length;i++){
	         var city_value = citys[i].bkd325;
	         var vity_name = citys[i].bkd324;
	         var option = new Option(vity_name,city_value);
	         select.add(option);
	      }
		});  
}

</script>
</powersi:html>
