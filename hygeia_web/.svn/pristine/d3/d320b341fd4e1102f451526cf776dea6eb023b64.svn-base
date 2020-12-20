<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ taglib prefix="mediTag" uri="http://www.powersi.com.cn/medicaretaglib"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Calendar"%>
<%
	String path = request.getContextPath(); 
%>
<powersi:html>
<base target="_self">
<head>
	<powersi:head title="村卫生室定额维护" />
</head>
<powersi:codetable id="aka130List" codeType="aka130" />
<powersi:codetable id="bka006List" codeType="bka006" />
<powersi:codetable id="bka035List" codeType="bka035" />
<powersi:codetable id="aaa157List" codeType="aaa157" />
<powersi:codetable id="aaa027List" codeType="aaa027" />
<body>
	<powersi:form id="mainForm" name="mainForm" action="MedicalVillagePayAction!getRationData.action" namespace="/medicarepay">
		<powersi:panelbox key="panel_query" title="查询条件">
		<powersi:hidden id="aae140" name="rationDto.aae140" value="390"/>
			<powersi:panelbox-toolbar>
				<powersi:button id="btQuery" key="button_query" value="查询" onclick="query()" title="Alt+Q" data-hotkey="Alt+Q"/>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">	
				<tr>
					<powersi:textfield id="aae041" label="起始年月:" name="rationDto.aae041" key="aae041" mask="period" maxlength="6" />
					<td align="right" class=tdLabel>定额类型</td>
					<td>
						<select id="ration_type"  style= "width:80 " name="rationDto.ration_type" onchange="do_change()">
							<option value="" selected="selected">请选择...</option>
							<option value="1">医疗机构定额</option>
							<option value="2">参保人定额</option>
						</select>
					</td>
					<powersi:combobox id="villageinfo" valueFieldID="village_no" name="village_list" valueField="village_no" textField="village_name" 
						isMultiSelect="false"  label="村卫生室" colspan="2">
						<powersi:hidden id="village_no"  value="" name="rationDto.village_no"/>
					</powersi:combobox>
					<td/>
				</tr>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:panelbox key="panel_result" title="查询结果">
			<powersi:panelbox-toolbar>
				<powersi:button id="btnAdd" key="button_add" value="新增医疗机构定额" onclick="addType_yy()" title="Alt+A" data-hotkey="Alt+A" />
				<powersi:button id="btnAdd" key="button_add" value="新增参保人定额" onclick="addType_gr()" title="Alt+S" data-hotkey="Alt+S" />	
			</powersi:panelbox-toolbar>
			<powersi:datagrid id="dataList" name="dataList" showExportExcel="true" showReload="false" widthDiff="-5" 
				 checkbox="false" rowDraggable="false" pageSize="20" exportFileName="'村卫生室定额列表'">
					<powersi:datagrid-column display="操作" name="operate" render="renderOperate"  frozen="true" />
					<powersi:datagrid-column display="起始年月" name="aae041" />
					<powersi:datagrid-column display="终止年月" name="aae042" />
					<powersi:datagrid-column display="村卫生室名称" name="akb021" align="left"/>
					<powersi:datagrid-column display="定额类型" name="ration_type_name" />
					<powersi:datagrid-column display="定额" name="bkb024" />
					<powersi:datagrid-column display="结算方式" name="bkf165" />
					<powersi:datagrid-column display="业务类型" name="biz_name" />
					<powersi:datagrid-column display="待遇类型" name="treatment_name" />
					<powersi:datagrid-column display="基金类型" name="fund_name" align="left"/>
					<powersi:datagrid-column display="险种类型" name="insur_name"/>
					<powersi:datagrid-column display="有效标志" name="aae100"/>
					<powersi:datagrid-column display="业务结算方式序号" name="kct1id" hide="true"/>
					<powersi:datagrid-column display="村卫生室编号" name="village_no" hide="true"/>
					<powersi:datagrid-column display="定额类型" name="ration_type" hide="true" />
			</powersi:datagrid>
		</powersi:panelbox>
	</powersi:form>
</body>
<script type="text/javascript">
//查询
function query(){
	if (!checkFormValidtion()) {//校验必填项
		return;
	}

	dataList.reset();
	
	var bizinfo = $("#mainForm").serialize();
    if(bizinfo == null){
    	return;
    }
    postJSON("<%=path%>/medicarepay/MedicalVillagePayAction!getRationData.action",bizinfo,initPayData);
}

function initPayData(json){
	if(!checkJSONResult(json)){
        return;
	}
	if (json.data=="no"){
		popupAlert("没有找到相关的数据！", "提示", "warn");
	}else{
		dataList.setData(json.data);
		dataList.loadData();
	}  
}

function renderOperate(row, index, value){
	var a = [];
	a.push('<input type="button" value="编辑" class="linkButton"');
	a.push(' onclick="editinfo(');
	a.push(row['kct1id']);
	a.push(')"');
	a.push(" />");
	a.push("&nbsp;&nbsp;");
	a.push('<input type="button" value="删除" class="linkButton"');
	a.push(' onclick="delinfo(');
	a.push(row['kct1id']);
	a.push(',');
	a.push(index);
	a.push(')"');
	a.push(" />");
	
	return a.join('');
}

function addType_yy(){
	var params = "?";
	params += "rationDto.akb020="+$("#akb020").val();
	params += "&rationDto.akb021="+encodeURI($("#akb021").val())+"&rationDto.ration_type=1";
	popupDialog("<%=path%>/medicarepay/MedicalVillagePayAction!queryPayTypeEdit.action"+params,400,1000,function(ret){
		query();
	});
}

function addType_gr(){
	var params = "?";
	params += "rationDto.akb020="+$("#akb020").val();
	params += "&rationDto.akb021="+encodeURI($("#akb021").val())+"&rationDto.ration_type=2";
	popupDialog("<%=path%>/medicarepay/MedicalVillagePayAction!queryPayTypeEdit.action"+params,400,1000,function(ret){
		query();
	});
}
function editinfo(kct1id){
	popupDialog("<%=path%>/medicarepay/MedicalVillagePayAction!queryPayTypeEdit.action?rationDto.kct1id=" + kct1id,400,1000,function(ret){
		query();
	});
}

function delinfo(kct1id, index) {
	popupConfirm('您确认要删除数据吗？', '删除', function(yes){
		if(yes){
			postJSON("<%=path%>/medicarepay/MedicalVillagePayAction!delPayTypeData.action",
				{"rationDto.kct1id":kct1id},
				function(ret) {
					if (ret.errortype == 2) {
						popupAlert(ret.message, "提示", "error");
					} else {
						var row = dataList.getRow(index);
						dataList.deleteRow(row);
						popupAlert("删除成功！", "提示", "success");
					}
				});
		}
	});
}
</script>
</powersi:html>