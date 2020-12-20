<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
String path = request.getContextPath();

%>

<powersi:html>
<powersi:head title="医院项目目录管理" />

<body>
	<powersi:form id="queryForm" namespace="/medicare" action="HealthAction!queryItemCatalog.action">
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
		<!-- 	<button type="button" id="btDr" class="button" onclick="downloadFile()">
					<i class="icon-signin"></i> Excel导入格式
				</button> -->
				<button type="button" id="btDel" name="btDel" class="button" onclick="doDel()">
					<i class="icon-minus-sign"></i> 删 除
				</button>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="10">
				<powersi:editorlayout-row cols="10">
					<powersi:codeselect id="caa027" name="healthDTO.caa027" key="caa027" label="中心系统" headerKey="00" codeType="caa027" />
					<powersi:textfield id="ake005" name="healthDTO.bkh027" key="bkh027" label="医院项目编码" />
					<powersi:textfield id="ake006" name="healthDTO.bkh028" key="bkh028" label="医院项目名称" />
					<powersi:hidden id="flag" name="healthDTO.flag" key="flag" value="1"/>
<%-- 					<powersi:codeselect id="ake003" name="healthDTO.ake003" key="ake003" label="目录类别" codeType="ake003_1" headerKey="00" />
<%-- 				<powersi:codeselect id="aae100" name="healthDTO.aae100" key="aae100" codeType="aae100"  label="有效标志" headerKey="00"/> --%>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>

	<powersi:panelbox key="panel_result" title="目录列表">
		<a>双击单条修改详细信息。</a>
		<powersi:datagrid id="grid"  onDblClickRow="dbSelectRow"   formId="queryForm" delayLoad="true" enabledSort="false" checkbox="true" 
			showExportExcel="true" showExportExcel2007="true" showExportPdf="true" exportFileName="'医院项目目录信息'">
			<powersi:datagrid-column name="bkh027" key="bkh027" label="医院项目编码" frozen="true" />
			<powersi:datagrid-column name="bkh028" key="bkh028" label="医院项目名称" frozen="true" />
			<powersi:datagrid-column name="bkh046_name" key="bkh046_name" label="统计类别" frozen="true"/>
			<powersi:datagrid-column name="bkh046" key="bkh046" label="统计类别" hide="true"/>
			<powersi:datagrid-column name="aka021" key="aka021" label="五笔码" />
			<powersi:datagrid-column name="aka020" key="aka020" label="拼音码" />
			<powersi:datagrid-column name="bkh044" key="bkh044" label="价格" />
			<powersi:datagrid-column name="bkh045" key="bkh045" label="标准单位" />
			<powersi:datagrid-column name="bke204" key="bke204" label="修订时间" />
			<powersi:datagrid-column name="bke205" key="bke205" label="修订人工号" />
			<powersi:datagrid-column name="bkh068" key="bkh068" label="序号" hide="true"/>
			<powersi:datagrid-column name="aae016_keh4" key="aae016_keh4" label="审核状态" code="aae016" hide="true"/>
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />

	<script type="text/javascript">
		$(function() {
			//默认查询有效目录
			$("#aae100").val("1");
		});

		function doAdd() {
			popupDialog({
				url : "${rootPath}/pages/biz/medicare/health/HospItemNew.jsp?caa027="+ $("#caa027").val(),
				onClosed : function() {
					var ret = this.returnValue;
					grid.reload(true);
				}
			}, 400,800);

		}
		//下载模板,所有地方都用同一个模板下载方法
		function downloadFile()
		{
			location.href="<%=path%>/medicare/HospManageAction!downLoadExampleXls.action?bzc001=5";
		}
		function openDr() {
			popupDialog({
				url : "${rootPath}/pages/biz/medicare/health/HospCataDr.jsp",
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
				popupAlert("请选择需要删除的体检目录", "提示", "error");
				return;
			}
			if (!confirm("您确认删除选择的目录信息吗?"))
				return;
			var data = powersi.tostring(rows);
			postJSON("${rootPath}/medicare/HealthAction!deleteKeh1.action", {
				"data" : data
			}, function(json) {
				if (!checkJSONResult(json))
					return;
				if(!powersi.isnull(json.data)){
					popupAlert(json.data, "提示", "info");
				}
				if(!powersi.isnull(json.message)){
					popupAlert(json.message, "提示", "info");
				}
				grid.reload(true);
			});
		}
		
		function dbSelectRow(rowdata, rowid, rowobj) {
			var row = grid.getRow(rowid);
			var bkh068 = row['bkh068'];
			
			postJSON("${rootPath}/medicare/HealthAction!queryItemCatalog.action", {
				"healthDTO.bkh068" : bkh068,
				"healthDTO.editflag" : "update",
				"healthDTO.flag" : "1"
			}, function(json) {
				if (!checkJSONResult(json))
					return;
				popupDialog({
					url : "${rootPath}/medicare/HealthAction!queryHospCataEdit.action?healthDTO.bkh068="+ bkh068,
					onClosed : function() {
						var ret = this.returnValue;
						grid.reload(true);
					}
				}, 300, 800);
				
			});

		}

	</script>
</body>
</powersi:html>

