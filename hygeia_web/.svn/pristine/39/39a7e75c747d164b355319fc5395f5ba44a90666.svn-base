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
	<base target="_self">
	<powersi:head title="生育特殊情况申请详情" />
<body>
	<powersi:form id="appHisForm" name="appHisForm" action="specialManager!getOtherAppInfList.action" namespace="/medicarespecial">
		<powersi:hidden name="dto.aaz267"/>
		<powersi:hidden name="dto.aac001"/>
		<powersi:hidden id="aka083His" name="dto.aka083"/>
		<powersi:hidden name="dto.aaa027"/>
		<powersi:hidden name="dto.caa027"/>
		<powersi:hidden name="dto.comFlag" value="4"/>
	</powersi:form>
	<powersi:form id="assBizForm" name="assBizForm" action="specialManager!getOtherAppInfList.action" namespace="/medicarespecial">
		<powersi:hidden name="dto.aaz217"/>
		<powersi:hidden name="dto.comFlag" value="5"/>
		<powersi:hidden name="dto.pageFlag" value="500"/>
	</powersi:form>
	<powersi:form id="mainForm" action="specialManager!getQueryDetailPage.action" namespace="/medicarespecial">
		<!-- 隐藏元素层 -->
		<div style="display: none">
			<powersi:textfield id="pageFlag" name="dto.pageFlag" label="页面标志"/>
			<powersi:textfield id="aae140" name="dto.aae140" key="aae140" label="险种类型"/>
			<powersi:textfield id="aka083" name="dto.aka083" key="aka083" value="500" label="申请类型"/>
			<powersi:textfield id="aaa027" name="dto.aaa027" key="aaa027" value="<%= aaa027  %>"  label="统筹区编码"/>
			<powersi:textfield id="aka130" name="dto.aka130" key="aka130" value="" label="业务类型"/>
			<powersi:textfield id="bka006" name="dto.bka006" key="bka006" value="" label="待遇类型"/>
			<powersi:textfield id="aaz267" name="dto.aaz267" key="aaz267" label="特殊业务申请ID"/>
			<powersi:textfield id="comFlag" name="dto.comFlag" key="comFlag" label="通用标志，没有特定用途，根据实际情况传递参数进行判断"/>
		</div>
		
		<powersi:panelbox title="基本信息">
			<powersi:panelbox-toolbar>
					<powersi:button key="button_close" data-hotkey='alt+B' value="关 闭" onclick="closeDialog();" />				
			</powersi:panelbox-toolbar>
			
			<powersi:editorlayout cols="8">
				<tr>
					<powersi:textfield id="aac001" name="dto.aac001" key="aac001" readonly="true" label="个人电脑号" />
					<powersi:textfield id="aac003" name="dto.aac003" key="aac003" disabled="true" label="姓名" />
					<powersi:codeselect id="aac004" name="dto.aac004" key="aac004" disabled="true" codeType="aac004" headerValue=" " headerKey="" label="性别" />
					<powersi:textfield id="aac002" name="dto.aac002" key="aac002" disabled="true" label="身份证号" />
				</tr>
				<tr>
					<powersi:textfield id="aac006" name="dto.aac006" key="aac006" disabled="true" label="出生日期"/>
					<powersi:codeselect id="bka035" name="dto.bka035" key="bka035" disabled="true" codeType="bka035" headerValue=" " headerKey="" label="人员类别"/>
					<powersi:textfield id="aab069" name="dto.aab069" key="aab069" disabled="true" label="单位名称" colspan="3"/>
				</tr>
				<tr>
					<powersi:textfield id="akb021" name="dto.akb021" required="true" disabled="true" label="申请医院"/>
					<powersi:textfield id="aae127" name="dto.aae127" required="true" key="aae127" disabled="true" label="申请日期" />
					<powersi:textfield id="aae030" name="dto.aae030" required="true" key="aae030" disabled="true" label="生效日期" />
					<powersi:textfield id="aae031" name="dto.aae031" required="true" key="aae031" disabled="true" label="失效日期" />
				</tr>
				<tr>
					<powersi:textfield id="bke033" name="dto.bke033" key="bke033" disabled="true" label="联系电话" />
					<powersi:textfield id="bke001" name="dto.bke001" disabled="true" label="详细地址" />
					<powersi:textfield id="aae011" name="dto.aae011" disabled="true" label="经办人"/>
					<powersi:textfield id="aaz217" name="dto.aaz217" readonly="true" label="医疗就诊序号"/>
				</tr>
			</powersi:editorlayout>
		</powersi:panelbox>
		
		<powersi:groupbox title="详细信息">
			<powersi:editorlayout cols="8">
				<tr>
					<powersi:textfield id="aka121" name="dto.aka121" required="true" disabled="true" label="申请疾病" />
					<powersi:hidden id="aka120" name="dto.aka120" disabled="true" label="疾病编码" />
					<powersi:textfield id="aae019" name="dto.aae019" validate="money" maxlength="12" key="aae019" disabled="true" label="申请限额"/>
					<powersi:textfield id="bke015" name="dto.bke015" key="bke015" disabled="true" label="就诊科室" />
					<powersi:textfield id="bke014" name="dto.bke014" key="bke014" disabled="true" label="科室电话" />
				</tr>
				<tr>
					<powersi:textfield id="bke017" name="dto.bke017" key="bke017" disabled="true" label="申请医师" />
					<powersi:textfield id="bke020" name="dto.bke020" key="bke020" mask="date" disabled="true" label="医师意见时间" />
					<powersi:textfield id="bke018" name="dto.bke018" key="bke018" disabled="true" label="主任医师" />
					<powersi:textfield id="bke019" name="dto.bke019" key="bke019" mask="date" disabled="true" label="主任意见时间" />
				</tr>
				<tr>
					<powersi:textarea id="bke011" rows='1' name="dto.bke011" key="bke011" colspan="3" disabled="true" label="病情摘要" />
					<powersi:textarea id="aae013" rows='1' name="dto.aae013" key="aae013" colspan="3" disabled="true" label="备注" />
				</tr>
			</powersi:editorlayout>
		</powersi:groupbox>
		
		<powersi:groupbox title="审核意见">
			<powersi:editorlayout cols="8">
				<tr>
					<powersi:textfield id="akb081" name="dto.akb081" validate="money" maxlength="12" key="akb081" disabled="true" label="审批限额"/>
					<powersi:textfield id="aae014" name="dto.aae014" disabled="true" label="审核人"/>
					<powersi:textfield id="aae015" name="dto.aae015" required="true" key="aae015" disabled="true" label="审核日期" />
					<powersi:codeselect id="aae016" name ="dto.aae016" list="%{#{'':'全部','0':'未审核','1':'审核通过','2':'审核未通过'}}" disabled="true" label="审核状态" />
				</tr>
				<tr>
					<powersi:textarea id="aae189" rows='1' name="dto.aae189" key="aae189" colspan="8" disabled="true" label="审核意见" /> 
				</tr>
			</powersi:editorlayout>
		</powersi:groupbox>
		
		<powersi:groupbox title="关联业务信息（单击展开）"  isCollapse="true">
			<powersi:datagrid id="gridAssBiz" formId="assBizForm" 
				height="30%" pageSize="5" enabledSort="true" pageSizeOptions="[5, 10, 20, 100]" delayLoad="true"
				onLoadedData="function(dg){if(dg.getTotal()>0){dg.setParm('dto.totalResult',dg.getTotal());}}"
				pageParmName="dto.currentPage" pagesizeParmName="dto.pageSize">
				<powersi:datagrid-column name="aka130" display="业务类型" data="aka130List" render="renderDictionary" width="8%"/>
				<powersi:datagrid-column name="bka006" display="待遇类型" data="bka006List" render="renderDictionary" width="8%"/>
				<powersi:datagrid-column name="akb021" display="医疗机构" width="14%"/>
				<powersi:datagrid-column name="bkz101" display="入院疾病诊断" width="14%"/>
				<powersi:datagrid-column name="bkz102" display="出院疾病诊断" width="14%"/>
				<powersi:datagrid-column name="aae036" display="登记日期" width="8%"/>
				<powersi:datagrid-column name="aae030" display="入院日期" width="8%"/>
				<powersi:datagrid-column name="aae031" display="出院日期" width="8%"/>
				<powersi:datagrid-column name="akc194" display="结算日期" width="8%"/>
				<powersi:datagrid-column name="biz_flag" display="在院状态" width="3%"/>
				<powersi:datagrid-column name="aae100" display="有效状态" data="aae100List" render="renderDictionary" width="3%"/>
			</powersi:datagrid>
		</powersi:groupbox>
		
		<powersi:groupbox title="申请历史记录（单击展开）"  isCollapse="true">
			<powersi:datagrid id="gridAppHis" formId="appHisForm" 
				height="30%" pageSize="5" enabledSort="true" pageSizeOptions="[5, 10, 20, 100]" delayLoad="true"
				onLoadedData="function(dg){if(dg.getTotal()>0){dg.setParm('dto.totalResult',dg.getTotal());}}"
				pageParmName="dto.currentPage" pagesizeParmName="dto.pageSize">
				<powersi:datagrid-column name="aka083" hide="true" isExport="false"/><powersi:datagrid-column name="aka083_name" display="申请类型" data="aka083" code="aka083"  isSort="false" width="15%"/>
				<powersi:datagrid-column name="akb021" display="申请医疗机构" width="35%"/>
				<powersi:datagrid-column name="aae127" display="申请日期" width="10%"/>
				<powersi:datagrid-column name="aae030" display="生效日期" width="10%"/>
				<powersi:datagrid-column name="aae031" display="失效日期" width="10%"/>
				<powersi:datagrid-column name="aae016" display="审核状态" data="aae016List" render="renderDictionaryStatic" width="10%"/>
				<powersi:datagrid-column name="aae100" display="有效状态" data="aae100List" render="renderDictionary" width="10%"/>
				<powersi:datagrid-column name="aaz267" display="申请号" hide="true" isExport="false"  width=""/>
			</powersi:datagrid>
		</powersi:groupbox>
		
			<powersi:codetable id="aka130List" codeType="aka130"></powersi:codetable>
			<powersi:codetable id="bka006List" codeType="bka006"></powersi:codetable>
			<powersi:codetable id="aka083List" codeType="aka083"></powersi:codetable>
			<powersi:codetable id="aae016List" codeType="aae016_spapp"></powersi:codetable>
			<powersi:codetable id="aae100List" codeType="aae100"></powersi:codetable>
		
		<powersi:errors />
	</powersi:form>
</body>
<script type="text/javascript">
	var pwidth=1000,pheight=500
	
	
	$(document).ready(function() {
		gridAssBiz.reset();
		gridAssBiz.loadForm(assBizForm);
		gridAppHis.reset();
		gridAppHis.loadForm(appHisForm);
		initForm();
		setClientScreenWH();
	});
	
	
	function setClientScreenWH() {
		pwidth=0.9*$(window).width();
	}
	
	
	function initForm() {
		$("#aka083His").val($("#aka083").val());
	}
	
	
	/* 字典项渲染函数  解决loadData不能自动加载字典问题*/
	function renderDictionary(rowdata, index, value,arguments) {
		if(value != null && value != "") {
			var list = window[arguments.data];
			for(var i in list){
				if(list[i].id == value  ){
					return list[i].text;
				}
			}
			return '<span title="未匹配到字典项">'+value+'</span>';
		}else{
			return "";
		}
	}
	
	
	function getdateString(){
		var dt = new Date();
		var strDate = dt.getFullYear() ;
		if (dt.getMonth() + 1 <10) strDate += "0";
		strDate += '' + (dt.getMonth()+1);
		if (dt.getDate() <10) strDate += "0";
		strDate += '' + dt.getDate();
		return strDate;
	}
	
	
	/* 字典项渲染函数 固定值*/
	function renderDictionaryStatic(rowdata, index, value,arguments) {
		if(value != null && value != "") {
			
			if('0' == value) {
				return "未审核"
			} else if('1' == value) {
				return "审核通过"
			} else if('2' == value) {
				return "审核不通过"
			} else {
				return '<span title="未匹配到字典项">'+value+'</span>';
			}
		}else{
			return "";
		}
	}
</script>
</powersi:html>