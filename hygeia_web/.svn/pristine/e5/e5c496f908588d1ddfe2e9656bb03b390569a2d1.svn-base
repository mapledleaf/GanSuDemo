<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	String serialApply = request.getParameter("serialApply");
%>
<powersi:html>
<head>
<powersi:head title="备案详细信息" target="_self" />
</head>
<body>
	<powersi:form id="mainform" disabled="true">
		<powersi:editorlayout cols="8">
			<powersi:editorlayout-row>
				<powersi:editorlayout-button colspan="8">
					<powersi:button id="btClose" label="关 闭" buttonIcon="icon-remove"
						onclick="javascript:closeDialog();" />
				</powersi:editorlayout-button>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="name"    label="姓名" readonly="true" />
				<powersi:textfield id="indi_id" label="电脑号" readonly="true" />
				<powersi:textfield id="idcard"  label="身份证" readonly="true" />
				<powersi:textfield id="serial_apply" label="申请序号" readonly="true" />
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="birthday" label="出生年月" readonly="true"/>
				<powersi:codeselect id="sex" key="性别" list="%{#{'1':'男','2':'女','0':'女','9':'未知'}}"  disabled="true" />
				<powersi:textfield id="official_name" label="公务员级别" readonly="true" />
				<powersi:codeselect id="treatment_type" key="待遇类别" headerKey="0" headerValue=""  
					codeType="bka006" disabled="true" />
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="pers_name"     label="人员类别" readonly="true" />
				<powersi:textfield id="corp_name" 	  label="单位名称" readonly="true" />
				<powersi:textfield id="apply_man_tel" label="联系电话" readonly="true" />
				<powersi:textfield id="apply_count"   label="就诊次数" readonly="true" />
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="patient_name" label="申请人" readonly="true" />
				<powersi:textfield id="apply_date" 	 label="申请日期" readonly="true" />
				<powersi:textfield id="admit_date" 	 label="生效时间" readonly="true" />
				<powersi:textfield id="admit_effect" label="失效时间" readonly="true" />
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="apply_opinion" label="申请理由" readonly="true" />
				<powersi:textfield id="patient_opinion" label="病人意见" readonly="true" />
				<powersi:codeselect id="apply_type" 	  label="异地就医类型" list="%{#{'2':'异地安置退休','3':'异地长期居住','4':'异地转诊','5':'其他情况异地就医','6':'常驻异地工作','8':'不符合规定的异地就医转诊'}}"	 disabled="true" />
				<powersi:textfield id="jbjg_name" label="参保地统筹区" readonly="true" />
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="director_name" label="主治医师" readonly="true" />
				<powersi:textfield id="disease_desc" label="疾病描述" readonly="true" />
				<powersi:textfield id="dept_name" label="科室" readonly="true" />
				<powersi:textfield id="dept_name" label="医保联系电话" readonly="true" />
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="remark" label="备注信息" readonly="true" />
				<powersi:textfield id="center_name" label="就医地统筹区" readonly="true" />
				<powersi:textfield id="apply_icd" label="诊断" readonly="true" />
				<powersi:textfield id="disease" label="诊断名" readonly="true" />
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="outhosp_id" label="转出医院编码" readonly="true" />
				<powersi:textfield id="outhosp_name" label="转出医院名称" readonly="true" />
				<powersi:textfield id="outhosp_audit_man" label="转出医院意见人" readonly="true" />
				<powersi:textfield id="outhosp_opinion"   label="转出医院意见" readonly="true" />
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="apply_flag" label="申请类型" readonly="true" />
				<powersi:textfield id="admit_count" label="审核有效次数" readonly="true" />
				<powersi:textfield id="input_man"  label="录入人" readonly="true" />
				<powersi:textfield id="input_date" label="录入时间" readonly="true" />
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:hidden id="corp_id" label="单位ID" />
				<powersi:hidden id="center_id" label="就医地统筹区编码" /> 
				<powersi:hidden id="dept_link_tel" label="科室" />
				<powersi:hidden id="pers_type" label="人员类别码表值" />
				<powersi:hidden id="district_code" label="参保地统筹区编码" />
				<powersi:hidden id="official_code" label="公务员编码" />
			</powersi:editorlayout-row>
		</powersi:editorlayout>
		
<powersi:groupbox title="异地医疗机构">	
	<powersi:datagrid id="gridHosp" height="30%" alternatingRow="false" pageSize="10" 
		pageSizeOptions="[10]" delayLoad="true" checkbox="false" showPageIndex="false"
		showPageButton="false" showReload="false" showPageSize="false">
		<powersi:datagrid-column name="hospital_id" display="医疗机构编码" width="30%"/>
		<powersi:datagrid-column name="hospital_name" display="医疗结构名称" width="60%"/>
		<powersi:datagrid-column name="district_code"  hide="true" isExport="false" />
	</powersi:datagrid>
</powersi:groupbox>
	</powersi:form>
	<powersi:errors />
</body>
</powersi:html>
<script type="text/javascript">
	$(document).ready(
		function() {
			postJSON("${rootPath}/query/BizQueryAction!queryOutRecordDetail.action?bizQueryDto.serialApply="+<%=serialApply%>,
					function(json) {
						$.each(json.data, function(key, value) {
							if (!powersi.isnull(value)) {
								if(key == "hospital"){
									gridHosp.setData(json.data.hospital);
									gridHosp.loadData();
								}else{
									$("#" + key).val(value);									
								}
							}
						});
					});
	});
</script>
