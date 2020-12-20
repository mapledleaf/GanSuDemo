<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	String path = request.getContextPath();	
	String hospital_id = BizHelper.getAkb020();
	String hospital_name = BizHelper.getAkb021();
	String fromdate = request.getParameter("bizQueryDto.fromdate");
	fromdate = fromdate.substring(0, 10);
	String aaz217 = request.getParameter("bizQueryDto.aaz217");
	String one = request.getParameter("bizQueryDto.one");
	String secfalg = request.getParameter("bizQueryDto.secfalg");
%>
<powersi:html>
<powersi:head title="省内异地费用明细窗口" />
<powersi:form id="feeDetailForm" action="BizQueryAction!queryFeeDetailTable.action" namespace="/query">
	<powersi:groupbox title="查询条件">
		<table>
			<tr>
				<td><powersi:checkbox label="费用时间段设置" id="ckb1" name="ckb1"
						onclick="ckb1_fun()" /></td>
				<td>&nbsp;&nbsp;</td>
				<td><powersi:textfield id="fromdate" label="开始时间"
						name="bizQueryDto.fromdate" mask="date"
						format="dateFmt:'yyyy-MM-dd'" disabled="true" /></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><powersi:textfield id="todate" label="结束时间"
						name="bizQueryDto.todate" mask="date"
						format="dateFmt:'yyyy-MM-dd'" disabled="true" /></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td align="right"><powersi:button cssClass="button" value="设置"
						name="but1" id="but1" disabled="true" onclick="btn_fun()" /></td>
			</tr>
		</table>
	</powersi:groupbox>
	<powersi:groupbox title="费用明细清单">
		<powersi:datagrid id="feeDetailGrid" height="410" delayLoad="true" formId="feeDetailForm"
			totalRender="feeDetailList" showExportExcel="true"
			showExportExcel2007="true" showExportPdf="true"
			eName="'广州市医疗保险(医嘱)明细清单'">
			<powersi:datagrid-column id="aac003" name="name" key="name"
				label="姓名" width="60" />
			<powersi:datagrid-column id="aac004" name="sex" key="sex" label="性别"
				width="60" />
			<powersi:datagrid-column id="akb021" name="hospital_name"
				key="hospital_name" label="医疗机构名称" width="80" />
			<powersi:datagrid-column id="aaz217" name="serial_no" key="serial_no"
				label="就医登记号" width="80" />
			<powersi:datagrid-column id="aka063" name="stat_type" key="stat_type"
				label="费用类别 " code="aka063List" render="renderDictionary" width="60" />
			<powersi:datagrid-column id="ake002" name="item_name" key="item_name"
				label="名称 " width="110" />
			<powersi:datagrid-column id="ake006" name="his_item_name"
				key="his_item_name" label="可报销基本药物" />
			<powersi:datagrid-column id="bka052" name="model" key="model"
				label="剂型 " width="80" />
			<powersi:datagrid-column id="bka054" name="standard" key="standard"
				label="规格" width="80" />
			<powersi:datagrid-column id="bka056" name="price" key="price"
				label="单价" width="60" />
			<powersi:datagrid-column id="bka057" name="dosage" key="dosage"
				label="数量" width="60" />
			<powersi:datagrid-column id="bka058" name="money" key="money"
				label="金额 " width="100" />
			<powersi:datagrid-column id="" name="pay_first" key="pay_first"
				label="自负金额 " width="90" />
			<powersi:datagrid-column id="aka057" name="self_scale"
				key="self_scale" label="自负比例" width="90" />
		</powersi:datagrid>
		<powersi:hidden id="aaz217" name="bizQueryDto.aaz217"/>
		<powersi:hidden id="akb020" name="bizQueryDto.akb020"/>
		<powersi:hidden id="bka017" />
		<powersi:hidden id="one" name="one"/>
		<powersi:hidden id="secfalg" name="bizQueryDto.secfalg"/>
	</powersi:groupbox>
</powersi:form>
<tags:transCode />
<powersi:errors />
<script type="text/javascript">
		window.onload = function(){
			$('#akb020').val("<%=hospital_id%>");
			$('#aaz217').val("<%=aaz217%>");
			$('#fromdate').val("<%=fromdate%>");
			$('#one').val("<%=one%>");
			$('#secfalg').val("<%=secfalg%>");
			var akb020 = $('#akb020').val();
			var aaz217 = $('#aaz217').val();
			var fromdate = $('#fromdate').val();
			var one =$('#one').val();
			var secfalg =$('#secfalg').val();
			$("#todate").val(fromdate);
			$("#feeDetailForm").submit();
			feeDetailGrid.reset();
		}
		
		function ckb1_fun() {
			var ckbValue = $(':checkbox[name="ckb1"]:checked').val();
			if (ckbValue) {
				document.all('fromdate').disabled = false;
				document.all('todate').disabled = false;
				document.all('but1').disabled = false;
			} else {
				document.all('fromdate').disabled = true;
				document.all('todate').disabled = true;
				document.all('but1').disabled = true;
			}
		}
		
		//按时间段设置重查一遍
		function btn_fun() {
			if (!checkForm) {
				return;
			}
			if ($("#fromdate").val() > $("#todate").val()) {
				popupAlert("开始时间必须小于结束时间!");
				return;
			}
			feeDetailGrid.reset();
			var fromdate = $("#fromdate").val();
			var secfalg = $('#secfalg').val();
			var todate = $("#todate").val();
			var aaz217 = $("#aaz217").val();
			var one = $('#one').val();
			$("#feeDetailForm").submit();
			 feeDetailGrid.reset();
		}

		function afterInHospBizFee(json) {
			if (!checkJSONResult(json)) {
				return;
			}
			//加载结果集到datagrid
			feeDetailGrid.loadData(json.data);
		}
		
		//浮点数转换
		function FloatAdd(arg1, arg2) {
			var r1, r2, m;
			try {
				r1 = arg1.toString().split(".")[1].length;
			} catch (e) {
				r1 = 0;
			}
			try {
				r2 = arg2.toString().split(".")[1].length;
			} catch (e) {
				r2 = 0;
			}
			m = Math.pow(10, Math.max(r1, r2));
			return to2bits((arg1 * m + arg2 * m) / m, 2);
		}

		function to2bits(flt, pos) {
			var rd = 1;
			for (var i = 1; i <= parseInt(pos); i++) {
				rd = rd * 10;
			}
			if (parseFloat(flt) == flt)
				return Math.round(flt * rd) / rd;
			else
				return 0;
		}
		
		//计算数量及金额总数
		function feeDetailList(allData, currentData) {
			//计算总和
			var amount = 0;
			sum = 0;
			$.each(allData['rows'], function(n, row) {
				if (feeDetailGrid.getRowStatus(row) != 'delete') {
					amount = FloatAdd(amount, row.dosage);
					sum = FloatAdd(sum, row.money);
				}
			});
			var a = [];
			a.push('<div class="textCenter">');
			a.push('总数量：</b>');
			a.push(amount);
			a.push('</b> ');
			a.push('</span>');
			a.push('&nbsp;&nbsp;&nbsp;&nbsp;');
			a.push('总金额：</b>');
			a.push(sum);
			a.push(' 元');
			a.push('</b> ');
			a.push('</span>');
			a.push('</div>');
			return a.join('');
		}
	</script>
</powersi:html>