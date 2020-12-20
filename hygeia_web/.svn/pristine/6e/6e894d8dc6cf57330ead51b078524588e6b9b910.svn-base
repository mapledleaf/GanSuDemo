<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>


<powersi:html>
<powersi:head title="医院项目目录管理" />

<body>
	<powersi:form id="queryForm" namespace="/medicare" action="HospCataAction!queryHospCata.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<button type="submit" id="btSubmit" class="button">
					<i class="icon-search"></i> 查 询
				</button>
				<button type="button" id="btAdd" name="btAdd" onclick="doAdd()" class="button">
					<i class="icon-plus-sign"></i> 新 增
				</button>
				<button type="button" id="btUpdate" name="btUpdate" onclick="btUpdate()" class="button">
					<i class=""></i> 修改
				</button>
				<button type="button" id="btDel" name="btDel" class="button" onclick="doDel()">
					<i class="icon-minus-sign"></i> 删 除
				</button>
				<button type="button" id="btClear" name="btClear" class="button" onclick="btClear()">
					<i class=""></i> 清屏
				</button>
				<button type="button" id="btDr" class="button" onclick="openDr()">
					<i class="icon-signin"></i> 导 出
				</button>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="6%,10%,10%,10%,10%,10%">
				<powersi:editorlayout-row cols="10">
					<powersi:codeselect id="caa027" name="cataQueryDto.caa027" key="caa027" label="项目类型" headerKey="00" codeType="caa027" />
					<powersi:codeselect id="ake003" name="cataQueryDto.ake003" key="ake003" label="定位方式" codeType="ake003_1" headerKey="00" />
					<powersi:codeselect id="aae100" name="cataQueryDto.aae100" key="aae100" codeType="valid_flag" value="1" label="查找内容" />
					<td colspan="10"></td>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>

	<powersi:panelbox key="panel_result" title="目录列表">
		<powersi:datagrid id="grid" formId="queryForm" delayLoad="true" enabledSort="false" checkbox="true" onDblClickRow="dbSelectRow"
			showExportExcel="true" showExportExcel2007="true" showExportPdf="true" exportFileName="'医院项目目录信息'" height="75%">
			<powersi:datagrid-column name="ake005" key="ake005" label="医院项目编码" frozen="true" />
			<powersi:datagrid-column name="ake006" key="ake006" label="医院项目名称" frozen="true" />
			<powersi:datagrid-column name="ake003" key="ake003" label="目录类别" code="ake003" frozen="true" />
			<powersi:datagrid-column name="bkc139" key="bkc139" label="规格型号" />
			<powersi:datagrid-column name="bkc140" key="bkc140" label="单价" />
			<powersi:datagrid-column name="bkc141" key="bkc141" label="生产单位" />
			<powersi:datagrid-column name="bkm019" key="bkm019" label="产地" />
			<powersi:datagrid-column name="aka020" key="aka020" label="拼音码" />
			<powersi:datagrid-column name="aka021" key="aka021" label="五笔码" />
			<powersi:datagrid-column name="aae100" key="aae100" label="有效标示" code="valid_flag" />
		<%-- 	<powersi:datagrid-column name="aae016_kae8" key="aae016" label="匹配状态" render="_render" /> --%>
			<powersi:datagrid-column name="bkm022" key="bkm022" label="商品名" />
			<powersi:datagrid-column name="bkm021" key="bkm021" label="别名" />
			<powersi:datagrid-column name="bkm025" key="bkm025" label="备注1" />
			<powersi:datagrid-column name="bkm026" key="bkm026" label="备注2" />
			<powersi:datagrid-column name="bkm027" key="bkm027" label="备注3" />
			<powersi:datagrid-column name="bkc002" key="bkc002" label="经办人" />
			<powersi:datagrid-column name="bkc003" key="bkc003" label="经办人工号" />
			<powersi:datagrid-column name="aae036" key="aae036" label="经办时间" format="{0,date,yyyy-MM-dd}" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:panelbox key="本次登记">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="baf313" name="inHospitalDTO.baf313"
						label="医院体检项目编码" readonly="false" />
					<powersi:textfield id="aae005" name="inHospitalDTO.aae005"
						label="医院体检项目名称" readonly="false" />
					<powersi:textfield id="baz113" name="inHospitalDTO.baz113"
						label="统计类别" readonly="false" />
					<powersi:textfield id="aaz218" name="inHospitalDTO.aaz218"
						label="首拼码" readonly="false" />
				</powersi:editorlayout-row>
					<powersi:editorlayout-row>
					<powersi:textfield id="baf313" name="inHospitalDTO.baf313"
						label="单位" readonly="false" />
					<powersi:textfield id="aae005" name="inHospitalDTO.aae005"
						label="五笔码" readonly="false" />
					<powersi:textfield id="baz113" name="inHospitalDTO.baz113"
						label="数值类型" readonly="false" />
					<powersi:textfield id="aaz218" name="inHospitalDTO.aaz218"
						label="补充描述" readonly="false" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
			<powersi:editorlayout cols="0%,40%">
			<powersi:editorlayout-row>
					<powersi:textfield id="baf313" name="inHospitalDTO.baf313"
						label="备注" readonly="false" />
					<td colspan="5"></td>
				</powersi:editorlayout-row>
				</powersi:editorlayout>
		</powersi:panelbox>
	
	<powersi:errors />

	<script type="text/javascript">
		$(function() {
		
		});

		function doAdd() {
			popupDialog({
				url : "${rootPath}/pages/biz/medicare/catalog/HospItemNew.jsp?ake003=${j_ake003}&caa027=" + $("#caa027").val(),
				onClosed : function() {
					var ret = this.returnValue;
					grid.reload(true);
				}
			}, screen.height, screen.width);

		}

		function openDr() {
			popupDialog({
				url : "${rootPath}/pages/biz/medicare/catalog/HospCataDr.jsp",
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
				popupAlert("请选择需要删除目录", "提示", "error");
				return;
			}
			var invalid = false;
			//判断结果集是否为空，为空下面循环取值会抛异常
			$.each(rows, function(i, row) {
				if (row['aae016_kae8']) {
					invalid = true;
					grid.select(row);
					popupAlert("已做匹配的目录不能删除，如需删除，请先删除本条目录的匹配信息", "提示", "error");
					return false;
				}
			});
			if (invalid)
				return;
			if (!confirm("您确认删除选择的目录信息吗?"))
				return;
			var data = powersi.tostring(rows);
			postJSON("${rootPath}/medicare/HospCataAction!delHospCata.action", {
				"data" : data
			}, function(json) {
				if (!checkJSONResult(json))
					return;
				popupAlert(json.message, "提示", "info");
				grid.reload(true);
			});
		}

		function dbSelectRow(rowdata, rowid, rowobj) {
			var row = grid.getRow(rowid);
			var ka40id = row['ka40id'];
			if ("${AAE016_KAE8_1}" == rowdata['aae016_kae8']) {
				popupAlert("审核通过的目录不能修改", "提示", "error");
				return;
			}
			if ("${AAE016_KAE8_3}" == rowdata['aae016_kae8']) {
				popupAlert("待中心审核的目录不能修改", "提示", "error");
				return;
			}
	/* 		popupDialog({
				url : "${rootPath}/medicare/HospCataAction!queryHospCataEdit.action?cataQueryDto.ka40id=" + ka40id
						+ "&cataQueryDto.caa027=" + $("#caa027").val() + "&ake003=${j_ake003}",
				onClosed : function() {
					var ret = this.returnValue;
					grid.reload(true);
				}
			}, screen.height, screen.width); */
		}
	</script>
</body>
</powersi:html>