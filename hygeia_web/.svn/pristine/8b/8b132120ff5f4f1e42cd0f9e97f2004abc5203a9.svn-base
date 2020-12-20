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
<powersi:head title="体检结论导入" target="_self" />
</head>
<body>
	<powersi:form id="mainForm" name="mainForm" namespace="/medicare"
			action="HealthAction!queryAllImportItem.action?healthDTO.flag=2">
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
				format="{0,date,yyyyMMdd}" />
			<powersi:datagrid-column name="bkc030" label="导入人" />
		</powersi:datagrid>

	</powersi:panelbox>

	<powersi:panelbox key="panel_query" title="导入文件信息"
		allowCollapse="false">
		<powersi:form id="queryForm" name="queryForm" namespace="/medicare"
			action="HealthAction!queryAllImportSion.action">
			<powersi:hidden id="bkc133" name="healthDTO.bkc133" />
			<powersi:hidden id="bkc252" name="healthDTO.bkc252" />
			<powersi:datagrid id="grid" formId="queryForm" delayLoad="true"
				showReload="false">
				<powersi:datagrid-column name="aae111" key="aae111" label="验证情况"
					frozen="true" />
					<powersi:datagrid-column name="bkh049" label="结论项目编码" />
				<powersi:datagrid-column name="bkh050" label="结论项目名称"/>
				<powersi:datagrid-column name="aaa027" label="统筹区编码"/>
				<powersi:datagrid-column name="aka021" label="五笔码"/>
				<powersi:datagrid-column name="aka020" label="首拼码" />
				<powersi:datagrid-column name="bkh037" label="数值类型" hide="true"/>
				<powersi:datagrid-column name="bkh053" label="叶子标识" hide="true"/>
				<powersi:datagrid-column name="bkh046" label="统计类别" hide="true"/>
				<powersi:datagrid-column name="bkh068" label="序号" hide="true"/>
				<powersi:datagrid-column name="bkh054" label="所属指标" />
				<powersi:datagrid-column name="bka035" label="人员类型" />
				<powersi:datagrid-column name="bkh051" label="参考范围上限" />
				<powersi:datagrid-column name="bkh052" label="参考范围下线" />
				<powersi:datagrid-column name="bkh056" label="参考范围指标" />
				<powersi:datagrid-column name="bkh057" label="单位控制指标" />
				<powersi:datagrid-column name="bkh058 " label="疾病控制指标" />
				<powersi:datagrid-column name="bkh103" label="生效时间" />
				<powersi:datagrid-column name="bkh104" label="失效时间"/>
				<powersi:datagrid-column name="bkh055" label="排序" hide="true"/>
				<powersi:datagrid-column name="bkh047" label="版本号" hide="true"/>
				<powersi:datagrid-column name="aae013" label="备注" />
				<powersi:datagrid-column name="akb020" label="医疗机构编码 " />
				<powersi:datagrid-column name="aae016" label="审核状态" hide="true" />
	
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
		location.href="<%=path%>/medicare/HospManageAction!downLoadExampleXls.action?bzc001=6";
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
	        url : "<%=path%>/medicare/HealthAction!uploadFileConclusion.action",
			datatype : "json",
			success : function(json) {
				fileupload.attr('disabled', false);
				var v= $.browser.version;
				if(v=='8.0'||v=='9.0'){
					json = json.match(/message":.*"/g);
					json = (json + "").replace(/message":"|"$/g,'');
					alert(json);
					$("#btSubmit").click();
				}else{
					var jsonData = $.parseJSON(json);
					var data = jsonData.data;
					alert(jsonData.message);
					fileGrid.loadData(data);	
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
		
		postJSON("<%=path%>/medicare/HealthAction!saveConCluSionInfo.action",{"bkc133":bkc133}, function(json){
			if(!checkJSONResult(json)){
			    return;
		    }
			popupAlert(json.data, "提示", "info");
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
		
		postJSON("<%=path%>/medicare/HealthAction!delConCluSionInfo.action", {
				"bkc133" : bkc133
			}, function(json) {
				if (!checkJSONResult(json)) {
					return;
				}
				alert(json.message);
				fileGrid.deleteRow(index);

				grid.reset();
				$("#mainForm").submit();
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