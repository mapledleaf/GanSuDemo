<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<powersi:html>
<powersi:head title="特定重大疾病自费项目补偿查询" />
<body>
	<powersi:form id="queryForm" namespace="/medicare"
		action="MzchoHospitalBusApplyAction!queryDiseaseComps.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit" key="button_query" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="aac003" name="mediSpecZHDto.aac002"
						key="aac003" label="姓名" />
					<powersi:textfield id="aac002" name="mediSpecZHDto.aac002"
						key="aac002" label="身份证号" />
					<powersi:codeselect id="aae140" key="参保险种"
						name="mediSpecZHDto.aae140" codeType="aae140" cssClass="select2" />
					<powersi:textfield id="aae037" name="mediSpecZHDto.aae037"
						label="经办开始时间" mask="date" format="dateFmt:'yyyy-MM-dd'" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:codeselect id="bka004" key="人员类别"
						name="mediSpecZHDto.bka004" codeType="bka004" cssClass="select2" />
					<powersi:codeselect id="aae016" label="审核标志"
						name="mediSpecZHDto.aae016"
						list="#{'':'请选择...','1':'未审核','2':'初审通过','3':'初审不通过','4':'复审通过','5':'复审不通过'}" />
					<td colspan="2"></td>
					<powersi:textfield id="aae038" name="mediSpecZHDto.aae038"
						label="经办结束时间" mask="date" format="dateFmt:'yyyy-MM-dd'" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:hidden id="akb020" name="expertInfoDto.akb020" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>

	<div class="row">
		<div class="col-8">
			<div id="yyDiv">
				<powersi:panelbox key="panel_result" title="病种登记信息"
					allowCollapse="false">
					<powersi:datagrid id="diseaseComps" formId="queryForm"
						delayLoad="true" usePager="true" selectRowButtonOnly="true"
						onDblClickRow="onDblClickRowQuery">
						<powersi:datagrid-column display="id" name="ace001" hide="true" />
						<powersi:datagrid-column display="姓名" name="aac003" width="10%"
							frozen="true" />
						<powersi:datagrid-column display="身份证号" name="aac002" width="20%"
							frozen="true" />
						<powersi:datagrid-column display="电脑号" name="aac001" width="10%"
							frozen="true" />
						<powersi:datagrid-column display="人员类别" name="bka004" width="10%" />
						<powersi:datagrid-column display="参保险种" name="aae140" width="10%" />
						<powersi:datagrid-column display="出生日期" name="aac006" width="15%" />
						<powersi:datagrid-column display="所属单位" name="bka008" width="20%" />
						<powersi:datagrid-column display="联系人" name="aae004" width="10%" />
						<powersi:datagrid-column display="手机号码" name="aae005" width="15%" />
						<powersi:datagrid-column display="病种名称" name="aka120" width="10%" />
						<powersi:datagrid-column display="确诊时间" name="bka028" width="10%" />
						<powersi:datagrid-column display="医院专家" name="aaa001" width="10%" />
						<powersi:datagrid-column display="医院专家2" name="aaa002" width="10%" />
						<powersi:datagrid-column display="鉴定标准" name="bka013" width="10%" />
						<powersi:datagrid-column display="审核标志" name="aae016" width="10%" />
						<powersi:datagrid-column display="备注" name="aae013" width="25%" />
						<powersi:datagrid-column display="操作人" name="bae100" width="10%" />
						<powersi:datagrid-column display="操作时间" name="aae036" width="15%" />
					</powersi:datagrid>
				</powersi:panelbox>
			</div>
		</div>
		<div class="col-4">
			<powersi:panelbox iconClass="icon-cog" title="自费项目"
				allowCollapse="false">
				<powersi:datagrid id="detailGrid" delayLoad="true"
					showReload="false">
					<powersi:datagrid-column display="药品名称" name="ake002" width="50%" />
					<powersi:datagrid-column display="药品编码" name="ake001" width="25%" />
					<powersi:datagrid-column display="审核标志" name="aae016" width="20%"
						render="renderAae016" />
				</powersi:datagrid>
			</powersi:panelbox>
		</div>
	</div>
	<powersi:errors />
</body>
</powersi:html>
<script type="text/javascript">
	function onDblClickRowQuery(index) {
		var row = detailGrid.getRow(index);
		var ace001 = row["ace001"];
		postJSON("${rootPath}/medicare/MzchoHospitalBusApplyAction!queryDiseaseCompsDetail.action",
				{
					"mediSpecZHDto.ace001" : ace001
				}, function(json) {
					if (!checkJSONResult(json)) {
						return;
					}
					detailGrid.loadData(json.data);
				});
	}

	function renderAae016(rowdata, index, value) {
		if (value == "2") {
			return "初审通过";
		} else if (value == "3") {
			return "初审不通过";
		} else if (value == "4") {
			return "复审通过";
		} else if (value == "5") {
			return "复审不通过";
		} else {
			return "未审核";
		}
	}
</script>