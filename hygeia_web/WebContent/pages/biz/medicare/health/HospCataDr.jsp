<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>

<%
	String path = request.getContextPath();

	String hospital_id = BizHelper.getAkb020();
	String hospital_name = BizHelper.getAkb021();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
%>


<powersi:html>
<head>
<powersi:head title="体检项目目录导入" target="_self" />
</head>
<body>
	<powersi:form id="mainForm" name="mainForm" namespace="/medicare"
			action="HealthAction!queryAllImportItem.action?healthDTO.flag=1">
		<powersi:panelbox key="panel_query" title="目录文件导入"
			allowCollapse="false">
			<powersi:panelbox-toolbar>
				<button type="button" id="fileupload" name="upload" class="button" onclick="upFile()"><i class="icon-upload-alt"></i> 上 传</button>
				<button type="button" class="button" onclick="downloadFile()"><i class="icon-download-alt"></i> 模板下载</button>
				<button type="submit" id="btSubmit" class="button"><i class="icon-search"></i> 查 询</button>
			</powersi:panelbox-toolbar>
				<powersi:editorlayout cols="10">
					<powersi:editorlayout-row>
						<powersi:hidden id="akb020" name="healthDTO.akb020" />
						<powersi:hidden id="bkc031" name="healthDTO.bkc031" />
						<powersi:hidden id="imgFileName" name="imgFileName" />
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:file id="imgFile" name="imgFile" size="70" label="选择导入文件"
							colspan="5" />
					</powersi:editorlayout-row>
				</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>

	<powersi:panelbox key="panel_query" title="未校验通过信息">
		<powersi:datagrid id="fileGrid" formId="mainForm" delayLoad="true"
			showReload="false" height="160">
			<powersi:datagrid-column key="operate" render="renderOperate"
				isExport="true" isSort="false" width="200" frozen="true" />
			<powersi:datagrid-column name="bkc033" label="导入文件名" frozen="true" />
			<powersi:datagrid-column name="totalPage" key="totalPage" label="总条数" />
			<powersi:datagrid-column name="bkc032" label="导入时间"
				format="dateFmt:'yyyy-MM-dd'" />
			<powersi:datagrid-column name="bkc030" label="导入人" />
		</powersi:datagrid>

	</powersi:panelbox>

	<powersi:panelbox key="panel_query" title="导入文件信息"
		allowCollapse="false">
		<powersi:form id="queryForm" name="queryForm" namespace="/medicare"
			action="HealthAction!queryImportCata.action">
			<powersi:hidden id="bkc133" name="healthDTO.bkc133" />
			<powersi:hidden id="bkc252" name="healthDTO.bkc252" />
			<powersi:datagrid id="grid" formId="queryForm" delayLoad="true"
				showReload="false">
				<powersi:datagrid-column name="aae111" key="aae111" label="验证情况"
					frozen="true" />
				<powersi:datagrid-column name="bkh027" key="bkh027" label="体检项目编码"
					frozen="true" />
				<powersi:datagrid-column name="bkh028" key="bkh028" label="体检项目名称"
					frozen="true" />
				<powersi:datagrid-column name="aka021" key="aka021" label="五笔码"/>
				<powersi:datagrid-column name="aka020" key="aka020" label="首拼码"/>
				<powersi:datagrid-column name="bkh044" key="bkh044" label="标准单价" />
				<powersi:datagrid-column name="bkh045" key="bkh045" label="标准单位" />
				<powersi:datagrid-column name="bkh046" key="bkh046" label="统计类别" />
				<powersi:datagrid-column name="bkh103" key="bkh103" label="生效时间" format="{0,date,yyyyMM-dd}"/>
				<powersi:datagrid-column name="bkh104" key="bkh104" label="失效时间" format="{0,date,yyyyMM-dd}"/>
				<powersi:datagrid-column name="bke204" key="bke204" label="修改时间" format="{0,date,yyyyMM-dd}"/>
				<powersi:datagrid-column name="bke205" key="bke205" label="修改人工号"/>
				<powersi:datagrid-column name="bke206" key="bke206" label="修改人" />
				<powersi:datagrid-column name="aae013" key="aae013" label="备注" />
				<powersi:datagrid-column name="aae100" key="aae100" code="aae100" label="有效标志" />
	
			</powersi:datagrid>
		</powersi:form>
	</powersi:panelbox>



	<script type="text/javascript">
	
	window.onload = function(){

      		$('#bkc031').val("<%=loginUser%>");
      		$('#akb020').val("<%=hospital_id%>");
      	
	      	if($("#akb020").val() == '' || $("#akb020").val() == null ){
				alert("医院编码不能为空，请检查登陆信息！");
		    	return;
			}
		}
	
	//下载模板,所有地方都用同一个模板下载方法
	function downloadFile()
	{
		location.href="<%=path%>/medicare/HospManageAction!downLoadExampleXls.action?bzc001=5";
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
	 	$("#mainForm").ajaxSubmit( {   
	        url : "<%=path%>/medicare/HealthAction!uploadFileCata.action",
			datatype : "json",
			success : function(json) {
				fileupload.attr('disabled', false);
				var str = $.trim(json.toString());
				if (str.substring(0,1)=="<") {//公司浏览器会出现<pre>标签，用于去除该标签
	        		var s = str.substring(5,str.length-6);
	        		var jsonData = jQuery.parseJSON(s);   
	        		var data = jsonData.data;
		        	$("#btSubmit").click();
				}else{
					if(json.errortype==1){
						alert(json.message);
					}else{
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
		var bkc133 = row['bkc133'];
		
		postJSON("<%=path%>/medicare/HealthAction!saveCatalogInfo.action",{"bkc133":bkc133}, function(json){
			if(!checkJSONResult(json)){
			    return;
		    }
			alert(json.data);  
			grid.reset();
			$("#mainForm").submit();
 		});
	}
	
	function delImport(index){
		var row = fileGrid.getRow(index);
		
		var bkc133 = row['bkc133'];
		
		if (!confirm("您确认要删除导入信息吗?")) {
	        return;
	   }
		
		postJSON("<%=path%>/medicare/HealthAction!deleItemInfo.action", {
				"bkc133" : bkc133
			}, function(json) {
				if (!checkJSONResult(json)) {
					return;
				}
				alert(json.message);
				fileGrid.deleteRow(index);

				grid.reset();

			});
		}

		function queryOK(index) {
			var row = fileGrid.getRow(index);

			var bkc133 = row['bkc133'];
			$('#bkc133').val(bkc133);
			$('#bkc252').val("1");

			$("#queryForm").submit();
		}
		function queryPass(index) {
			var row = fileGrid.getRow(index);

			var bkc133 = row['bkc133'];

			$('#bkc133').val(bkc133);
			$('#bkc252').val("-1");

			$("#queryForm").submit();
		}
	</script>
</body>
</powersi:html>