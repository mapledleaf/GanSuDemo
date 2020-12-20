<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<powersi:head title="体检项目目录查询" />

<body>
	<powersi:form id="queryForm" namespace="/medicare" action="HealthAction!queryItemCatalog.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<button type="submit" id="btSubmit" class="button">
					<i class="icon-search"></i> 查 询
				</button>
				
	<!--   <button type="button" id="btAdd" name="btAdd" onclick="download()" class="button">
				<i class="icon-search"></i> 中心目录下载
				</button>	 -->
				<%-- <powersi:button id="print" buttonIcon="icon-print" label="打 印" /> --%>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8%,10%,10%,11%">
				<powersi:editorlayout-row>
				    <powersi:codeselect id="caa027" name="healthDTO.caa027" key="caa027" label="中心系统" headerKey="00" codeType="caa027" />
					<powersi:codeselect id="flag" name="healthDTO.flag" key="flag" label="选择查询方式" headerKey="00" list="#{'1':'从结算云获取','2':'从中心获取'}" />
					<powersi:codeselect id="aae100" name="healthDTO.aae100" key="aae100" list="#{'1':'有效','0':'无效'}" label="有效标志" />
					<td colspan="8"></td>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox key="panel_result" title="目录列表">
		<powersi:datagrid id="grid" formId="queryForm" delayLoad="true" checkbox="false"  showExportExcel="true"
			showExportExcel2007="true" showExportPdf="true" enabledSort="false" exportFileName="'体检项目目录信息'">
			<powersi:datagrid-column name="bkh027" key="bkh027" label="医保项目编码" frozen="true" />
			<powersi:datagrid-column name="bkh028" key="bkh028" label="医保项目名称" frozen="true" />
			<powersi:datagrid-column name="bkh046_name" key="bkh046_name" label="统计类别"  frozen="true"/>
			<powersi:datagrid-column name="bkh046" key="bkh046" label="统计类别" hide="true" isExport="false"/>
			<powersi:datagrid-column name="aka021" key="aka021" label="五笔码" />
			<powersi:datagrid-column name="aka020" key="aka020" label="拼音码" />
			<powersi:datagrid-column name="bkh044" key="bkh044" label="价格" />
			<powersi:datagrid-column name="bkh045" key="bkh045" label="标准单位" />
			<powersi:datagrid-column name="bke204" key="bke204" label="修订时间" />
			<powersi:datagrid-column name="bke205" key="bke205" label="修订人工号" />
		</powersi:datagrid>
	</powersi:panelbox>
	<script type="text/javascript">
	$(function() {
	
	});


	function openDr() {
		popupDialog({
			url : "${rootPath}/pages/biz/medicare/catalog/HospCataDr.jsp",
			onClosed : function() {
				var ret = this.returnValue;
				grid.reload(true);
			}
		}, screen.height, screen.width);
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
		popupDialog({
			url : "${rootPath}/medicare/HospCataAction!queryHospCataEdit.action?cataQueryDto.ka40id=" + ka40id
					+ "&cataQueryDto.caa027=" + $("#caa027").val() + "&ake003=${j_ake003}",
			onClosed : function() {
				var ret = this.returnValue;
				grid.reload(true);
			}
		}, screen.height, screen.width);
	}
	//打印按钮
	$("#print").click(function() {
		var div = $("div[id^=divTab][aria-hidden=false]");
		var name = $("a[href=#" + div.attr("id") + "]").attr("title");
		var iframe = div.find("iframe");
		var reportID = iframe.attr("reportID");
		if (reportID && "none" != reportID) {
			if (resultData.bke403s) {
				popupAlert("包含未申报数据无法打印", "提示", "warn");
			} else {
				$(iframe.prop('contentWindow').document).find("body").PowerPrint({
					name : name
				}).preview();
			}
		} else {
			popupAlert("无打印内容。", "提示", "warn");
		}
	});
	
	/* 中心下载目录到结算云 */
	function download(){
		postJSON("${rootPath}/medicare/HealthAction!healthDownloadOnCenter.action",function(json){
			if (!powersi.isnull(json.data)) {
				popupAlert(json.data,"提示", "info");
				$("#queryForm").submit();
			}
		});
	} 

	</script>
</body>
</html>