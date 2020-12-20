<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	String path = request.getContextPath();

	String hospital_id = BizHelper.getAkb020();
	String hospital_name = BizHelper.getAkb021();
	String aaa027 = BizHelper.getAaa027();
	String aaa027_name = BizHelper.getAaa027Name();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
%>


<powersi:html>
<head>
<powersi:head title="医师信息导入" />
</head>
<body>
	<powersi:form id="mainForm" name="mainForm" method="post">
		<powersi:panelbox key="panel_query" title="医师信息导入"
			allowCollapse="false">
			<powersi:panelbox-toolbar>
				<powersi:button name="upload" value="上 传 " onclick="upFile()" buttonIcon="icon-upload-alt"/>
				<powersi:button value="模版下载" onclick="downloadFile()" />
				<powersi:button cssClass="button" label="保 存" onclick="save()" buttonIcon="icon-save"/>
			</powersi:panelbox-toolbar>
			
			<powersi:editorlayout cols="10">
				<powersi:editorlayout-row>
					<powersi:hidden id="akb020" name="hospInfoDto.akb020" />
					<powersi:hidden id="bkc027" name="hospInfoDto.bkc027" />
					<powersi:hidden id="bkc028" name="hospInfoDto.bkc028" />
					<powersi:hidden id="aaa027" name="hospInfoDto.aaa027" />
					<powersi:hidden id="aae016" name="hospInfoDto.aae016" />
					<powersi:hidden id="imgFileName" name="imgFileName" />
				</powersi:editorlayout-row>

				<powersi:editorlayout-row>
					<powersi:file id="imgFile" name="imgFile" size="70" label="选择导入文件"
						colspan="6" />
					<powersi:codeselect id="bkc156" name="hospInfoDto.bkc156"
						label="科室" cssClass="select1" list="#request.deptList"
						headerKey="" headerValue="请选择..." required="true" showValue="true"
						colspan="2" />

				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>

	<powersi:panelbox key="panel_query" title="导入文件信息"
		allowCollapse="false">
		<powersi:form id="queryForm">
			<powersi:datagrid id="grid" formId="queryForm" delayLoad="true"
				showReload="false">
				<powersi:datagrid-column name="bkc275" key="bkc275" label="医师姓名"
					frozen="true" />
				<powersi:datagrid-column name="bkc274" key="bkc274" label="编号"
					frozen="true" />
				<powersi:datagrid-column name="bkc277" key="bkc277" label="医师性别"/>
				<powersi:datagrid-column name="bkc276" key="bkc276" label="医师执业情况"
					frozen="true" />
				<powersi:datagrid-column name="aka020" key="aka020" label="拼音助记码" />
				<powersi:datagrid-column name="aka021" key="aka021" label="五笔助记码" />
				<powersi:datagrid-column name="bkc280" key="bkc280" label="移动电话号码" />
				<powersi:datagrid-column name="bkc157" key="bkc157" label="医师所属科室"
					frozen="true" />
				<powersi:datagrid-column name="bkc272" key="bkc272" label="医师职称"
					code="title_id" />
				<powersi:datagrid-column name="bkc278" key="bkc278" label="出生日期"
					format="{0,date,yyyy-MM-dd}" />
				<powersi:datagrid-column name="bkc279" key="bkc279" label="社会保障号" />
				<powersi:datagrid-column name="bkc281" key="bkc281" label="专业" />
				<powersi:datagrid-column name="bkc028" key="bkc028" label="维护工号" />
				<powersi:datagrid-column name="bkc027" key="bkc027" label="维护人姓名" />
				<powersi:datagrid-column name="bkc029" key="bkc029" label="维护时间"
					format="{0,date,yyyy-MM-dd}" />
				<powersi:datagrid-column name="bke952" key="bke952" label="门特责任医师种类"
					code="special_type" />
				<powersi:datagrid-column name="bke953" key="bke953" label="特殊技术资质"
					code="special_lisence" />
				<powersi:datagrid-column name="bke954" key="bke954" label="特殊项目资质"
					code="special_item" />
				<powersi:datagrid-column name="bke955" key="bke955"
					label="执业医师资格证编号" />
				<powersi:datagrid-column name="akb020" key="akb020" hide="true" />
				<powersi:datagrid-column name="aae100" key="aae100" hide="true" />
				<powersi:datagrid-column name="aae016" key="aae016" hide="true" />
				<powersi:datagrid-column name="bkc156" key="bkc156" hide="true" />
				<powersi:datagrid-column name="aaa027" key="aaa027" hide="true" />
				<powersi:datagrid-column name="bkc269" key="bkc269" hide="true" />
			</powersi:datagrid>
		</powersi:form>
	</powersi:panelbox>



	<script type="text/javascript">
	
	window.onload = function(){

			$('#bkc027').val("<%=userName%>");
	  		$('#bkc028').val("<%=loginUser%>");
	  		$('#akb020').val("<%=hospital_id%>");
	  		$('#aaa027').val("<%=aaa027%>");
      	
	      	if($("#akb020").val() == '' || $("#akb020").val() == null ){
				alert("医院编码不能为空，请检查登陆信息！");
		    	return;
			}
	      	
		}
	
	//下载模板
	function downloadFile()
	{
		location.href="<%=path%>/medicare/HospManageAction!downLoadExampleXls.action?bzc001=3";
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
	        url : "<%=path%>/medicare/HospManageAction!uploadFileDoctor.action",   
	        datatype : "json",
	        success : function(json){ 
	        	var str = $.trim(json.toString());
        		if (str.substring(0,1)=="<") {//公司浏览器会出现<pre>标签，用于去除该标签
	        		var s = str.substring(5,str.length-6);
	        		var jsonData = jQuery.parseJSON(s);   
	        		var data = jsonData.data;
		        	alert(jsonData.message);
		        	grid.loadData(data);
				}else{
					if(!powersi.isnull(json.message)){
						popupAlert(json.message);
					}
					grid.loadData(json.data); 
					/* var jsonData = jQuery.parseJSON(json);   
					var data = jsonData.data;
		        	alert(jsonData.message);
		        	grid.loadData(data); */
				}
	            },  
	       	error:function(json){ 
	        	json = json.substring(5,json.length-6);//只要print的json结果
	            alert(json.message);  
	             }   
	    });
	}
	
	
	function save(){
		var bkc156 = $("#bkc156").val();
		if(bkc156 == '' || bkc156 == null ){
			alert("科室不能为空，请选择科室信息！");
	    	return;
		}
		
	    	//获取全部数据，整个datagrid查询页
	  		var data = grid.getData();
	    	data = powersi.tostring(data);
	  		if(powersi.isempty(data)){
				alert("没有导入，无需保存");
				return;
			}
	        
	        postJSON("<%=path%>/medicare/HospManageAction!saveHospDoctorDr.action",
					{
						"detail" : data,
						"bkc156" : bkc156
					}, function(json) {
						if (!checkResult(json)) {
							return;
						}
						alert(json.data);

						grid.reset();
						closeDialog();
					});

		}
	</script>
</body>
</powersi:html>