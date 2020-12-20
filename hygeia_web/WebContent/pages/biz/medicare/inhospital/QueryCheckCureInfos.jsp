<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>

<powersi:html>
<head>
<powersi:head title="查询检查诊疗结果" />
<%@ include file="/pages/util/header/combogrid.jsp"%>
</head>
<body>

<script type="text/javascript">
function query()
{
	var aaz217 = $("#aaz217").val();
	var bka509 = $("#bka509").val();
	var bka510 = $("#bka510").val();
	postJSON("${rootPath}/inhospital/CheckCureInfoManagerAction!queryCheckCureInfos.action",
			{"checkCureInfoDTO.aaz217": aaz217,"checkCureInfoDTO.bka509":bka509,"checkCureInfoDTO.bka510":bka510}, 
			function(json){
				if(!checkJSONResult(json)){
				    return;
			    }
	    	//加载结果集到datagrid
			grid_CheckCure_Query.loadData(json.data);
	});
}

function clearall(){
	$('#aaz217').val("");
	$('#bka509').val("");
	$('#bka510').val("");
}

function renderOperate(row, index, value) {
	var a = [];
	a.push('<input type="button" value="查看影像图片" class="linkButton"');
	a.push(' onclick="doViewPic(');
	a.push(index);
	a.push(')"');
	a.push(" />");
	return a.join('');
}

function doViewPic(index){
	var row = grid_CheckCure_Query.getRow(index);
	var ke14id = row['ke14id'];
	if (powersi.isnull(ke14id)) {
		return;
	}
	var akb020 = row['akb020'];
	if (powersi.isnull(akb020)) {
		return;
	}
	
	if (!confirm("您确认要下载查看该影像图片吗?")) {
		return;
	}
	
	window.location.href="${rootPath}/inhospital/CheckCureInfoManagerAction!downLoadBka511.action?checkCureInfoDTO.ke14id="
		+ ke14id+"&checkCureInfoDTO.akb020="+akb020;
	
/* 	postJSON(
			"${rootPath}/inhospital/CheckCureInfoManagerAction!downLoadBka511.action?checkCureInfoDTO.ke14id="
					+ ke14id+"&checkCureInfoDTO.akb020="+akb020,
			function(json) {
				if (!checkJSONResult(json)) {
					return;
				}
				alert(json.message);
			}); */
}
</script>

<powersi:groupbox title="查询条件">
<powersi:editorlayout>
	<tr>
		<powersi:textfield id="aaz217" name="aaz217"  key="aaz217" placeholder="请输入就医登记号查询"  label="就医登记号"/>
		<powersi:textfield id="bka509" name="bka509"  key="bka509" placeholder="请输入影像编号查询"  label="影像编号"/>
		<powersi:textfield id="bka510" name="bka510"  key="bka510" placeholder="请输入影像名称查询"  label="影像名称"/>
	</tr>
	<powersi:buttons>						
		<input type="button" name="query" value="查询" class="button" onclick="query();" />
		<input type="button" name="clear" value="重置" class="button" onclick="clearall();" />
	</powersi:buttons>	
</powersi:editorlayout>
</powersi:groupbox>

<powersi:groupbox title="查询结果">
<powersi:datagrid id="grid_CheckCure_Query" fromId="mainForm" exportFileName="'检查诊疗结果查询'"  height="100%"  useCount="false" 
		showExportExcel="true" showExportExcel2007="true"  showExportPdf="true"  enabledSort="true" delayLoad="true"  >
	  <powersi:datagrid-column name="akb020"  display="医院编号" frozen="true" width="100" minWidth="100"/>
	  <powersi:datagrid-column name="aaz217"  display="就医登记号" frozen="true" width="150" minWidth="100"/>
	  <powersi:datagrid-column name="bka509"  display="影像编号" width="100" minWidth="50"/>
	  <powersi:datagrid-column name="bka510"  display="影像名称" width="150" minWidth="100"/>
	  <powersi:datagrid-column name="bka511"  display="影像图片" width="150" minWidth="100" hide="true"/>
	  <powersi:datagrid-column name="bae013"  display="说明" width="200" minWidth="50" />
	  <powersi:datagrid-column key="operate" render="renderOperate" width="100" frozen="true" />
</powersi:datagrid>
</powersi:groupbox>


</body>
</powersi:html>