<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>

<powersi:html>
	<head>
		<powersi:head title="电子处方查询" />
	</head>
	<body>
		<powersi:form id="mainForm" namespace="/medicare"
			action="MtmmSpecialApplyAction!queryPersonChooseHosp.action">
			<powersi:panelbox title="查询条件">	
				<powersi:panelbox-toolbar>
					<powersi:button key="button_query" value="查询" onclick="queryAllInfo()"/>
					<powersi:button key="button_reset" value="重置" onclick="clearall()"/>
				</powersi:panelbox-toolbar>
				<powersi:editorlayout >
				    <powersi:editorlayout-row>
				    	<powersi:hidden id="akb020" name="mediSpecDto.akb020"/>
				    	<powersi:hidden id="aae100" name="kad5DTO.aae100" value="all"/>
				    </powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="aac001" label="电脑号" name="kad5DTO.aac001" key="aac001"  />
						<powersi:textfield id="aaz216" label="申请单号" name="kad5DTO.aaz216" key="aaz267"  />
						<powersi:textfield id="ake1id" label="处方流水号" name="kad5DTO.ake1id" key="ake1id"  />
						<td colspan="4"></td>
					</powersi:editorlayout-row>
				</powersi:editorlayout>
			</powersi:panelbox>
			<powersi:groupbox title="电子处方列表"> 
				<powersi:datagrid id="querygrid"  delayLoad="true"  onDblClickRow="doubleClick"  >
				   <powersi:datagrid-column name="bka006" display=""  width="50" hide="true"/> 
				   <powersi:datagrid-column name="bka026" display=""  width="50" hide="true"/> 
				   <powersi:datagrid-column name="ake1id"  display="处方流水号" width="150" />
					<powersi:datagrid-column name="akb020" display="医院编号" width="60" />
					<powersi:datagrid-column name="aac001" display="电脑号" width="150" />
					<powersi:datagrid-column name="aaz216" display="特殊业务申请号"  width="150" />
					<powersi:datagrid-column name="bka006_name" display="待遇类别"  width="100" /> 
					<powersi:datagrid-column name="bka026_name" display="入院诊断"  width="100"/>
					<powersi:datagrid-column name="bka020" display="科室"  width="100" />
					<powersi:datagrid-column name="bka022" display="病区"  width="100" />
					<powersi:datagrid-column name="bka504" display="医保医师"  width="100" />
					<powersi:datagrid-column name="bka063" display="录入人工号"  width="70" />
					<powersi:datagrid-column name="bka065" display="录入时间"  width="150" format="datefmt:'yyyy-mm-dd'"/>
					<powersi:datagrid-column name="aae030" display="生效日期" width="150" format="datefmt:'yyyy-mm-dd'"/>
					<powersi:datagrid-column name="aae031" display="失效日期" width="150" format="datefmt:'yyyy-mm-dd'"/>
				</powersi:datagrid>
			</powersi:groupbox>		
		</powersi:form>
	<powersi:errors />	
<script type="text/javascript">	




//查询待修改的全部人员信息
function queryAllInfo(){
		querygrid.reset();
       	var saveItemData = $("#mainForm").serialize();
		postJSON("${rootPath}/medicare/HospElectpresAction!electPresQuery.action",saveItemData,afterQuery);
}

function afterQuery(json){
	if(!checkJSONResult(json)){
	    return;
    }
	if(json.message !=""){
		popupInfo(json.message);
	}
	//加载结果集到datagrid
	querygrid.loadData(json.data.kad5List);
}

function doubleClick(index){//双击弹窗
	var row = querygrid.getRow(index);
	var aaz267 = row['aaz216'];
	var aac001 = row['aac001'];
	var ake1id = row['ake1id'];
	var bka025 = row['bka025'];
	var bka043 = row['bka043'];
	var bka065 = row['bka065'];
	var bka020 = row['bka020'];
	var bka022 = row['bka022'];
	var bka504 = row['bka504'];
	popupDialog({
		url:"${rootPath}/medicare/HospElectpresAction!electPresQuery.action?kad5DTO.ake1id="+ake1id
				+"&diagnoseInfoDTO.aac001="+aac001
				+"&diagnoseInfoDTO.bka025="+bka025
				+"&diagnoseInfoDTO.bka043="+bka043
				+"&diagnoseInfoDTO.aae030="+bka065
				+"&diagnoseInfoDTO.aaz267="+aaz267
				+"&diagnoseInfoDTO.bka019="+bka020
				+"&diagnoseInfoDTO.bka020="+bka022
				+"&diagnoseInfoDTO.bka503="+bka504,
		onClosed:function(){
			var ret = this.returnValue;
		}
	}, 500, 1050);
}

function clearall(){
	$("input").val("");
	querygrid.reset();
}

</script>
</body>
</powersi:html>