<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	request.setAttribute("caa027", request.getParameter("caa027"));
%>
<powersi:html>
<head>
<powersi:head title="医院项目目录导入" target="_self" />
</head>
<body>
	<powersi:form id="mainForm" name="mainForm" namespace="/medicare"
		action="HospCataAction!queryAllImportItem.action">
		<powersi:panelbox key="panel_query" title="目录文件导入" allowCollapse="false">
			<powersi:panelbox-toolbar>
				<powersi:button id="uploadId"   label="上 传 "    buttonIcon="icon-upload-alt" onclick="upFile()" />
				<powersi:button id="downloadId" label="模版下载" buttonIcon="icon-download-alt" onclick="downloadFile()" />
				<powersi:submit id="btSubmit"   label="查 询"    buttonIcon="icon-search"  />
			</powersi:panelbox-toolbar>
				<powersi:editorlayout cols="10">
					<powersi:editorlayout-row>
						<powersi:file   id="imgFile"     name="imgFile" size="70" label="选择导入文件" colspan="5" />
						<powersi:hidden id="imgFileName" name="imgFileName" />
					</powersi:editorlayout-row>
				</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:form id="queryForm" name="queryForm" namespace="/medicare"
			action="HospCataAction!queryImportCata.action" >
			<powersi:hidden id="bkc133" name="hospCataDto.bkc133" />
			<powersi:hidden id="bkc252" name="hospCataDto.bkc252" />
	</powersi:form>
	<powersi:hidden id="caa027" name="hospCataDto.caa027" value="${caa027}"/>
	<powersi:tabbedpanel id="divTabs">
		<powersi:tab id="tab2" target="divTab1" label="导入的文件" />
		<powersi:tab id="tab1" target="divTab2" label="导入文件的详细信息" />
		<div id="divTab1">
			<powersi:datagrid id="fileGrid" formId="mainForm" delayLoad="true" showReload="false">
				<powersi:datagrid-column key="operate" render="renderOperate"
					isExport="true" isSort="false" width="200" frozen="true" />
				<powersi:datagrid-column name="bkc033" label="导入文件名" frozen="true" />
				<powersi:datagrid-column name="num" key="num" label="总条数" />
				<powersi:datagrid-column name="bkc032" label="导入时间" format="{0,date,yyyyMMdd}" />
				<powersi:datagrid-column name="bkc030" label="导入人" />
			</powersi:datagrid>
		</div>
		<div id="divTab2">
			<powersi:datagrid id="grid" formId="queryForm" delayLoad="true"
				showReload="false" showExportExcel="true" 
				showExportExcel2007="true" 	exportFileName="'导入文件信息'">
				<powersi:datagrid-column name="aae111" key="aae111" label="验证情况"  frozen="true" render="renderAae111"/>
				<powersi:datagrid-column name="ake005" key="ake005" label="项目编码*" frozen="true" />
				<powersi:datagrid-column name="ake006" key="ake006" label="项目名称*" frozen="true" />
				<powersi:datagrid-column name="ake003" key="ake003" label="目录类别*" frozen="true" code="ake003"/>
				<powersi:datagrid-column name="bkc140" key="bkc140" label="单价*" />
				<powersi:datagrid-column name="aka062" key="aka062" label="英文名称" />
				<powersi:datagrid-column name="bkc141" key="bkc141" label="生产单位" />
				<powersi:datagrid-column name="bkm019" key="bkm019" label="产地" />
				<powersi:datagrid-column name="bkc139" key="bkc139" label="规格型号" />
				<powersi:datagrid-column name="bkm022" key="bkm022" label="商品名" />
				<powersi:datagrid-column name="bkm021" key="bkm021" label="别名" />
				<powersi:datagrid-column name="bkm025" key="bkm025" label="备注1" />
				<powersi:datagrid-column name="bkm026" key="bkm026" label="备注2" />
				<powersi:datagrid-column name="bkm027" key="bkm027" label="备注3" />
			</powersi:datagrid>
		</div>
	</powersi:tabbedpanel>
</body>
<script type="text/javascript">
	//下载模板,所有地方都用同一个模板下载方法
	function downloadFile() {
		location.href="${rootPath}/medicare/HospManageAction!downLoadExampleXls.action?bzc001=8";
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
		var fileupload = $("#fileupload");
		fileupload.attr('disabled', true);
	 	$("#mainForm").ajaxSubmit({
	        url : "${rootPath}/medicare/HospCataAction!uploadFileCata.action?pageFlag=item&hospCataDto.caa027=" + 
	        		$("#caa027").val(),
			datatype : "json",
			success : function(json) {
				fileupload.attr('disabled', false);
				var str = $.trim(json.toString());
        		if (str.substring(0,1)=="<") {//公司浏览器会出现<pre>标签，用于去除该标签
	        		var s = str.substring(5,str.length-6);
	        		var jsonData = jQuery.parseJSON(s);   
	        		var data = jsonData.data;
		        	alert(data);
		        	$("#btSubmit").click();
				}else{
					if(json.errortype==1){
						alert(json.message);
					}else{
						alert(json.data);
					}
					fileGrid.reload();
				}
			},
			error : function(json) {
				alert(json.message);
			}
		});
	}
	
	function renderOperate(row, index, value) {
		var a = [];
		a.push('<input type="button" value="验证通过" class="linkButton"');
		a.push(' onclick="queryOK(');
		a.push(index);
		a.push(')"');
		a.push(" />");
		
		a.push("&nbsp;&nbsp;");
	
		a.push('<input type="button" value="验证不通过" class="linkButton"');
		a.push(' onclick="queryPass(');
		a.push(index);
		a.push(')"');
		a.push(" />");
	
		a.push("&nbsp;&nbsp;");
	
		a.push('<input type="button" value="保存" class="linkButton"');
		a.push(' onclick="save_item(');
		a.push(index);
		a.push(')"');
		a.push(" />");
	
		a.push("&nbsp;&nbsp;");
	
		a.push('<input type="button" value="删除" class="linkButton"');
		a.push(' onclick="delImport(');
		a.push(index);
		a.push(')"');
		a.push(" />");
	
		return a.join('');
	}
	
	function save_item(index) {
		var row = fileGrid.getRow(index);
		postJSON("${rootPath}/medicare/HospCataAction!saveCatalogInfo.action",
				{"hospCataDto.bkc133":row['bkc133']}, function(json){
					if(!checkJSONResult(json))
					    return;
					popupInfo(json.data);  
					fileGrid.reload();
 		   		});
	}
	
	function delImport(index){
		var row = fileGrid.getRow(index);
		if (!confirm("您确认要删除导入信息吗?")) 
	        return;
		
		postJSON("${rootPath}/medicare/HospCataAction!deleItemInfo.action", {
				"hospCataDto.bkc133" : row['bkc133']
			}, function(json) {
				if (!checkJSONResult(json)) 
					return;
				popupInfo(json.data);
				fileGrid.reload();
			});
		}

	function queryOK(index) {
		var row = fileGrid.getRow(index);
		$('#bkc133').val(row['bkc133']);
		$('#bkc252').val("1");
		$("#queryForm").submit();
		$("#ui-id-2").click();
	}
		
	function queryPass(index) {
		var row = fileGrid.getRow(index);
		$('#bkc133').val(row['bkc133']);
		$('#bkc252').val("-1");
		$("#queryForm").submit();
		$("#ui-id-2").click();
	}
	
	function renderAae111(rowdata, index, value){
		if(""==value || null==value)
			return "验证通过";
		else 
			return "验证失败,信息："+value;
	}
</script>
</powersi:html>