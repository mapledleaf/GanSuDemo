<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%
	String hospital_id = BizHelper.getAkb020();
	String aaa027 = BizHelper.getAaa027();
	String aaa027_name = BizHelper.getAaa027Name();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
%>
<powersi:html>
<head>
<powersi:head title="导入病床信息" target="_self" />
</head>
<body>
	<powersi:form id="mainForm" method="POST" namespace="/medicare">
		<powersi:panelbox key="panel_query" title="病床信息导入"
			allowCollapse="false">
			<powersi:panelbox-toolbar>
				<powersi:button name="upload" value="上 传 " onclick="upFile()"
					buttonIcon="icon-upload-alt" />
				<powersi:button value="模版下载" onclick="downloadFile()" />
				<powersi:button cssClass="button" label="保 存" onclick="save()"
					buttonIcon="icon-save" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="10">
				<powersi:editorlayout-row>
					<powersi:hidden id="akb020" name="hospInfoDto.akb020" />
					<powersi:hidden id="bkc153" name="hospInfoDto.bkc153" />
					<powersi:hidden id="bkc156" name="hospInfoDto.bkc156" />
					<powersi:hidden id="bkc027" name="hospInfoDto.bkc027" />
					<powersi:hidden id="bkc028" name="hospInfoDto.bkc028" />
					<powersi:hidden id="aaa027" name="hospInfoDto.aaa027" />
					<powersi:hidden id="aae016" name="hospInfoDto.aae016" />
					<powersi:hidden id="imgFileName" name="imgFileName" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:file id="imgFile" name="imgFile" size="70" label="选择导入文件"
						colspan="6" required="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox key="panel_query" title="导入文件信息"
		allowCollapse="false">
		<powersi:form id="bedForm">
			<powersi:datagrid id="bedid" formId="bedForm" delayLoad="true"
				showReload="false" pageSize="200">
				<powersi:datagrid-column name="bkc161" key="bkc161" label="病床编号" />
				<powersi:datagrid-column name="bkc162" key="bkc162" label="病床房间" />
				<powersi:datagrid-column name="bkc163" key="bkc163" hide="true"/>
				<powersi:datagrid-column name="bkc165" key="bkc165" label="床位类型" />
				<powersi:datagrid-column name="bkc164" key="bkc164" label="床位单价" />
				<powersi:datagrid-column name="bkc027" key="bkc027" label="维护人" />
				<powersi:datagrid-column name="bkc028" key="bkc028" label="维护人工号" />
				<powersi:datagrid-column name="bkc029" key="bkc029" label="维护时间"
					format="{0,date,yyyy-MM-dd}" />
				<powersi:datagrid-column name="akb020" key="akb020" hide="true" />
				<powersi:datagrid-column name="aae100" key="aae100" hide="true" />
				<powersi:datagrid-column name="aae016" key="aae016" hide="true" />
				<powersi:datagrid-column name="bkc153" key="bkc153" hide="true" />
				<powersi:datagrid-column name="bkc156" key="bkc156" hide="true" />
				<powersi:datagrid-column name="aaa027" key="aaa027" hide="true" />
			</powersi:datagrid>
		</powersi:form>
	</powersi:panelbox>
</body>
</powersi:html>
<script type="text/javascript">
	window.onload = function(){
		  	$('#bkc027').val("<%=userName%>");
      		$('#bkc028').val("<%=loginUser%>");
      		$('#akb020').val("<%=hospital_id%>");
      		$('#aaa027').val("<%=aaa027%>");
	}
	//下载模板
	function downloadFile() {
		location.href="${rootPath}/medicare/HospManageAction!downLoadExampleXls.action?bzc001=2";
	}
	
	//导入的文件上传
	function upFile(){
		var fileName = $('#imgFile').val();
		if(powersi.isempty(fileName)){
			alert("导入文件不能为空！");
			return;
		}
		var fileNames = new Array();
		fileNames = fileName.split("\\");
		var fileName = fileNames[fileNames.length-1];
		$("#imgFileName").val(fileName);
		$("#mainForm").ajaxSubmit( {   
	        url : "${rootPath}/medicare/HospManageAction!uploadFileBed.action",   
	        datatype : "json",
	        success : function(json){ 
	        	var str = $.trim(json.toString());
        		if (str.substring(0,1)=="<") {//公司浏览器会出现<pre>标签，用于去除该标签
	        		var s = str.substring(5,str.length-6);
	        		var jsonData = jQuery.parseJSON(s);   
	        		var data = jsonData.data;
		        	alert(jsonData.message);
		        	bedid.loadData(data);
				}else{
					if(!powersi.isnull(json.message)){
						popupAlert(json.message);
					}
		        	bedid.loadData(json.data);  
				}
	        },  
	       	error:function(json){ 
	        	json = json.substring(5,json.length-6);//只要print的json结果
	            alert(json.message);  
	        }   
	    });   
	}
	
	
	function save(){
   	    var bkc153 = $("#bkc153").val();
    	//获取全部数据，整个datagrid查询页
  		var data = powersi.tostring(bedid.getData());
  		if(powersi.isempty(data)){
			alert("没有导入，无需保存");
			return;
		}
        postJSON("${rootPath}/medicare/HospManageAction!saveHospBedDr.action", 
        		{ "detail" : data, 
        		  "bkc153" : bkc153 }, function(json) {
					if (!checkResult(json))
						return;
					alert(json.data);
					if("保存病床信息成功！"==json.data)
						closeDialog();
					bedid.reset();
				});

	}
</script>
