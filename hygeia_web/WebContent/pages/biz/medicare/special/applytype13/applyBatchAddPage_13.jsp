<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	request.setAttribute("path", request.getContextPath() + "/medicarespecial/specialManager");
	String aaa027 = BizHelper.getAaa027();
	String hospital_id = BizHelper.getAkb020();
	String hospital_name = BizHelper.getAkb021();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
%>
<powersi:html>
<powersi:head title="门特申请批量导入" />
<script type="text/javascript" src="${rootPath}/resource/js/ymPrompt/jquery.ajaxfileupload.js"></script>

<body>
	<powersi:form id="mainFrom" name="mainFrom" action="specialManagerBatch!uploadBatchAddFile.action" namespace="/medicarespecial" 
		enctype="multipart/form-data" method="POST">
		<!-- 隐藏元素层 -->
		<div style="display: none">
			<powersi:textfield id="pageFlag" name="dto.pageFlag" label="页面标志"/>
			<powersi:textfield id="aae140" name="dto.aae140" key="aae140" label="险种类型"/>
			<powersi:textfield id="aka083" name="dto.aka083" key="aka083" value="131" label="申请类型"/>
			<powersi:textfield id="aaa027" name="dto.aaa027" key="aaa027" value="<%= aaa027  %>"  label="统筹区编码"/>
			<powersi:textfield id="aka130" name="dto.aka130" key="aka130" value="13" label="业务类型"/>
			<powersi:textfield id="bka006" name="dto.bka006" key="bka006" value="131" label="待遇类型"/>
			<powersi:textfield id="aae016" name="dto.aae016" key="aae016" value="0" label="审核状态"/>
		</div>
		<powersi:panelbox title="门特信息">
			<powersi:panelbox-toolbar>
				<powersi:button key="button_upload"  value="申请上传" onclick="submitForm()" />
				<powersi:button key="button_download" value="模板下载" onclick="downloadFile()" />				
			</powersi:panelbox-toolbar>
			<a id="url" href=""></a>
				<powersi:editorlayout cols="8">
					<tr>
						<td class="tdLabel">
							门特批量申请文件
						</td>
						<td colspan="1" align="left" style="border-right-width: 0px">
							<input type="file" id="uploads" name="uploads" size="70" class="file">
						</td>
						<td colspan="1"/>
						<tr>
				</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:groupbox title="门特批量导入错误反馈">
		<powersi:datagrid id="gridError"
			height="100%" exportFileName="'批量申请校验信息'" showExportExcel="true"
			alternatingRow="false" pageSize="30"  showExportExcel2007="false" enabledSort="true"
			pageSizeOptions="[30,50,100,200,500,1000]" delayLoad="true">
			<powersi:datagrid-column name="aac003" display="姓名" width="5%"/>
			<powersi:datagrid-column name="aac002" display="身份证号码" width="15%"/>
			<powersi:datagrid-column name="aab069" display="单位(名称)" width="15%"/>
			<powersi:datagrid-column name="akb020" display="申请医院(编码)" width="10%"/>
			<powersi:datagrid-column name="bke001" display="详细地址" width="15%"/>
			<powersi:datagrid-column name="bke033" display="联系电话" width="10%"/>
			<powersi:datagrid-column name="bke002" display="诊断依据" width="15%"/>
			<powersi:datagrid-column name="aka121" display="申请病种(名称)" width="10%"/>
			<powersi:datagrid-column name="diseaselist" display="并发症(名称)" width="10%"/>
			<powersi:datagrid-column name="bke005" display="分型(编号)" width="10%"/>
			<powersi:datagrid-column name="bke006" display="病情严重等级(编号)" width="10%"/>
			<powersi:datagrid-column name="hosplist" display="定点医院(编号)" width="15%"/>
			<powersi:datagrid-column name="reasons" display="导入失败原因" width="20%"/>
		</powersi:datagrid>
	</powersi:groupbox>
	<powersi:errors />
</body>
<script type="text/javascript">
	
	//模板下载
	function downloadFile(){
		var url = getTemplatePath();
		if (null != url) {
			$("#url").attr("href", rootPath + url);
			document.getElementById("url").click();
		}
	}
	
	
	//获取门特批量上传模板路径
	function getTemplatePath() {
		var url = "${rootPath}/medicarespecial/specialManager!getTemplatePath.action";
		var paramsObj = {"dto.pageFlag":$("#pageFlag").val(), "dto.comFlag":11};
		var res = postSync(url, paramsObj);
		if("0" == res.errortype){
			return res.data;
		} else {
			popupAlert(res.message, "提示", "warn");
			return null;
		}
	}
	
	
	//文件上传
	function submitForm() {
		if ($("#uploads").val() == '') {
			popupAlert("申请文件不能为空", "提示", "warn");
			return;
		}
		
		$.ajaxFileUpload({
	        method:"POST",
	        dataType:"json",
	        url:"${path}!uploadBatchAddFile.action?" + $('#mainFrom').serialize(),
	        secureuri:true,  
	        fileElementId:'uploads',  //文件选择框的id属性 
	        data :null,
	        success: function(json,s, status){
	        	if('success' == json.data.status){
	        		gridError.reset();
	        		gridError.setData(json.data.list);
		        	gridError.loadData();
		        	popupAlert(json.data.message, "提示", "warn");
	        		return;
	          	} else if('error' == json.data.status) {
	          		popupAlert(json.data.message, "提示", "warn");
	        		return;
	          	}
	        }
	    });
	}
</script>
</powersi:html>