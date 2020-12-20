<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
String path = request.getContextPath();

%>

<powersi:html>
<powersi:head title="医院结论项目管理" />
<body>
	<powersi:form id="queryForm" namespace="/medicare" action="HealthAction!queryConclusions.action?healthDTO.flag=all">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<button type="submit" id="btSubmit" class="button">
					<i class="icon-search"></i> 查 询
				</button>
				<button type="button" id="btAdd" name="btAdd" onclick="doAdd()" class="button">
					<i class="icon-plus-sign"></i> 新 增
				</button>
			<!-- 	<button type="button" id="btDr" class="button" onclick="openDr()">
					<i class="icon-signin"></i> 导 入
				</button> -->
				</button>
					<button type="button" id="btDr" class="button" onclick="openDr()">
					<i class="icon-signin"></i> 从体检系统导 入
				</button>
				<!-- <button type="button" id="btDr" class="button" onclick="downloadFile()">
					<i class="icon-signin"></i> Excel导入格式
				</button> -->
				<button type="button" id="btDel" name="btDel" class="button" onclick="doDel()">
					<i class="icon-minus-sign"></i> 删 除
				</button>
				
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="10">
				<powersi:editorlayout-row cols="10">
					<powersi:codeselect id="caa027" name="healthDTO.caa027" key="caa027" label="中心系统" headerKey="00" codeType="caa027" />
					<powersi:textfield id="bkh049" name="healthDTO.bkh049" key="bkh049" label="结论项目编码" />
					<powersi:textfield id="bkh050" name="healthDTO.bkh050" key="bkh050" label="结论项目名称" />

				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>

	<powersi:panelbox key="panel_result" title="目录列表">
		<a>双击单条修改详细信息。</a>
		<powersi:datagrid id="grid"  onDblClickRow="dbSelectRow"   formId="queryForm" delayLoad="true" enabledSort="false" checkbox="true" 
			showExportExcel="true" showExportExcel2007="true" showExportPdf="true" exportFileName="'医院项目目录信息'">
				<powersi:datagrid-column name="bkh049" label="结论项目编码" />
				<powersi:datagrid-column name="bkh050" label="结论项目名称"/>
				<powersi:datagrid-column name="aaa027" label="统筹区名称"  hide="true"/>
				<powersi:datagrid-column name="aka021" label="五笔码"/>
				<powersi:datagrid-column name="aka020" label="首拼码" />
				<powersi:datagrid-column name="bkh037" label="数值类型" hide="true"/>
				<powersi:datagrid-column name="bkh053" label="叶子标识" hide="true" />
				<powersi:datagrid-column name="bkh046" label="统计类别" hide="true"/>
				<powersi:datagrid-column name="bkh068" label="序号" hide="true"/>
				<powersi:datagrid-column name="bkh054" label="所属指标" />
				<powersi:datagrid-column name="bka035" label="人员类型" code="bka035"/>
				<powersi:datagrid-column name="bkh051" label="参考范围上限" />
				<powersi:datagrid-column name="bkh052" label="参考范围下限" />
				<powersi:datagrid-column name="bkh056" label="参考范围指标" />
				<powersi:datagrid-column name="bkh057" label="单位控制指标" />
				<powersi:datagrid-column name="bkh058 " label="疾病控制指标" hide="true"/>
				<powersi:datagrid-column name="bkh103" label="生效时间" />
				<powersi:datagrid-column name="bkh104" label="失效时间"/>
				<powersi:datagrid-column name="bkh055" label="排序" hide="true"/>
				<powersi:datagrid-column name="bkh047" label="版本号" hide="true"/>
				<powersi:datagrid-column name="aae013" label="备注" />
				<powersi:datagrid-column name="akb020" label="医疗机构编码 " hide="true"/>
				<powersi:datagrid-column name="aae016" label="审核状态" hide="true"/>
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />

	<script type="text/javascript">
		$(function() {
		
		});

		function doAdd() {
			popupDialog({
				url : "${rootPath}/pages/biz/medicare/health/HospItemNewConcluSion.jsp?caa027="+ $("#caa027").val(),
				onClosed : function() {
					var ret = this.returnValue;
					grid.reload(true);
				}
			}, 400, 800);

		}
		//下载模板,所有地方都用同一个模板下载方法
		function downloadFile()
		{
			location.href="<%=path%>/medicare/HospManageAction!downLoadExampleXls.action?bzc001=5";
		}
		function openDr() {
			popupDialog({
				url : "${rootPath}/pages/biz/medicare/health/conclusionImport.jsp",
				onClosed : function() {
					var ret = this.returnValue;
					grid.reload(true);
				}
			}, screen.height, screen.width);
		}

		function doDel() {
			//获取多选，全部勾选的数据
			var rows = grid.getSelectedRows();
			/* alert(rows); */
			if (powersi.isempty(rows)) {
				popupAlert("请选择需要删除的结论", "提示", "error");
				return;
			}
			if (!confirm("您确认删除选择的结论信息吗?"))
				return;
			var data = powersi.tostring(rows);
			postJSON("${rootPath}/medicare/HealthAction!deleteConclusions.action", {
				"data" : data
			}, function(json) {
				if (!checkJSONResult(json))
					return;
				if(json.errortype == "0"){
					popupAlert(json.data, "提示", "info");
				}else{
					popupAlert(json.message, "提示", "info");
				}
				grid.reload(true);
			});
		}
		
		function dbSelectRow(rowdata, rowid, rowobj) {
			var row = grid.getRow(rowid);
			var bkh049 = row['bkh049'];
			 if ("1" == rowdata['aae016']) {
				popupAlert("审核通过的目录不能修改", "提示", "error");
				return;
			} 
			if ("0" == rowdata['aae016']) {
				popupAlert("待审核的目录不能修改", "提示", "error");
				return;
			}
			popupDialog({
				url : "${rootPath}/medicare/HealthAction!queryHospKeh2Edit.action?healthDTO.bkh049="+ bkh049
						+ "&healthDTO.caa027=" + $("#caa027").val(),
				onClosed : function() {
					var ret = this.returnValue;
					grid.reload(true);
				}
			}, screen.height, screen.width);
		}
		
		function doDownload(){
			postJSON("${rootPath}/medicare/HealthAction!doDownload.action",function(json){
				if("1" == json.errortype){
					popupAlert(json.message, "提示", "info");
					return;
				}
				 if (!powersi.isnull(json.data)) {
					popupAlert(json.data,"提示", "info");
					$("#queryForm").submit();
				} 
			});
		}

	</script>
</body>
</powersi:html>

