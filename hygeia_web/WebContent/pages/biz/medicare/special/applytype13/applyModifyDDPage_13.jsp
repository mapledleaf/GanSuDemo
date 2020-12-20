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
	<powersi:head title="修改门特定点医院" />
	<head>
	<script type="text/javascript" src="${rootPath}/resource/report/js/powerprint.min.js"></script>
	<script type="text/javascript" src="${rootPath}/resource/report/js/LodopFuncs.js"></script>
	</head>
<body>
	<powersi:form id="diseaseForm" name="diseaseForm" action="specialManager!getOtherAppInfList.action" namespace="/medicarespecial">
		<powersi:hidden name="dto.aaz267"/>
		<powersi:hidden name="dto.aaa027"/>
		<powersi:hidden name="dto.caa027"/>
		<powersi:hidden name="dto.comFlag" value="2"/>
	</powersi:form>
	<powersi:form id="hospForm" name="hospForm" action="specialManager!getOtherAppInfList.action" namespace="/medicarespecial">
		<powersi:hidden name="dto.aaz267"/>
		<powersi:hidden name="dto.aaa027"/>
		<powersi:hidden name="dto.caa027"/>
		<powersi:hidden name="dto.comFlag" value="1"/>
	</powersi:form>
	<powersi:form id="mainForm" action="specialManager!modifySpeAppInf.action" namespace="/medicarespecial">
		<!-- 隐藏元素层 -->
		<div style="display: none">
			<powersi:textfield id="pageFlag" name="dto.pageFlag" value="13" label="页面标志"/>
			<powersi:textfield id="aae140" name="dto.aae140" key="aae140" label="险种类型"/>
			<powersi:textfield id="aaa027" name="dto.aaa027" key="aaa027"   label="统筹区编码"/>
			<powersi:textfield id="aka130" name="dto.aka130" key="aka130" value="13" label="业务类型"/>
			<powersi:textfield id="bka006" name="dto.bka006" key="bka006" value="131" label="待遇类型"/>
			<powersi:textfield id="aae016" name="dto.aae016" key="aae016" label="审核状态"/>
			<powersi:textfield id="aaz267" name="dto.aaz267" key="aaz267" label="特殊业务申请ID"/>
			<powersi:textfield id="caa027" name="dto.caa027" key="caa027" label="中心系统"/>
			<powersi:textfield id="bkb055" name="dto.bkb055" value="<%=userName %>" label="经办人"/>
		</div>
		
		<powersi:panelbox title="人员信息">
			<powersi:panelbox-toolbar>
					<powersi:button key="button_save" data-hotkey='alt+S' onclick="saveinfo();" />
					<powersi:button key="button_print" value="打印" data-hotkey='alt+P' onclick="doPrint();" />
					<powersi:button key="button_close" data-hotkey='alt+B' value="关 闭" onclick="closeThis();" />				
			</powersi:panelbox-toolbar>
			
			<powersi:editorlayout cols="8">
				<tr>
					<powersi:textfield id="aac001" name="dto.aac001" key="aac001" readonly="true" label="个人电脑号" />
					<powersi:textfield id="aac003" name="dto.aac003" key="aac003" readonly="true" label="姓名" />
					<powersi:codeselect id="aac004" name="dto.aac004" key="aac004" disabled="true" codeType="aac004" headerValue=" " headerKey="" label="性别" />
					<powersi:textfield id="aac002" name="dto.aac002" key="aac002" readonly="true" label="身份证号" />
				</tr>
				<tr>
					<powersi:textfield id="aac006" name="dto.aac006" key="aac006" disabled="true" label="出生日期"/>
					<powersi:codeselect id="bka035" name="dto.bka035" key="bka035" disabled="true" codeType="bka035" headerValue=" " headerKey="" label="人员类别"/>
					<powersi:textfield id="aab069" name="dto.aab069" key="aab069" disabled="true" label="单位名称"/>
					<td colspan="2"/>
				</tr>
			</powersi:editorlayout>
		</powersi:panelbox>

		<powersi:groupbox title="基本信息">
			<powersi:editorlayout cols="8">
				<tr>
					<powersi:textfield id="akb021" name="dto.akb021" required="true" readonly="true" label="申请医院"/>
					<powersi:hidden id="akb020" name="dto.akb020" required="true" readonly="true" label="医院编码"/>
					<powersi:textfield id="aae127" name="dto.aae127" required="true" key="aae127" disabled="true" label="申请日期" />
					<powersi:textfield id="aae030" name="dto.aae030" required="true" key="aae030" disabled="true" label="生效日期" />
					<powersi:textfield id="aae031" name="dto.aae031" required="true" key="aae031" disabled="true" label="失效日期" />
					
				</tr>
				<tr>
					<powersi:textfield id="bke033" name="dto.bke033" key="bke033" disabled="true" label="联系电话" />
					<powersi:codeselect id="aka083" name="dto.aka083" codeType="aka083" codeFilter="aaa102 in ('131','132','133')" required="true" displayonly="true" label="申请类型"/>
					<powersi:textfield id="aae011" name="dto.aae011" disabled="true" label="经办人"/>
					<powersi:codeselect id="aae016" name ="dto.aae016" codeType="aae016_spapp" disabled="true" label="审核状态" />
				</tr>
			</powersi:editorlayout>
		</powersi:groupbox>
		<%-- 
		<powersi:groupbox title="医院医保办意见">
			<powersi:editorlayout cols="8">
				<tr>
					<powersi:textfield id="aka121" name="dto.aka121" required="true" disabled="true" label="申请病种" />
					<powersi:codeselect id="bke005" name="dto.bke005" key="bke005" codeType="bke401" disabled="true" label="分型" />
					<powersi:codeselect id="bke006" name="dto.bke006" key="bke006" codeType="bke402" disabled="true" label="严重等级" />
					<td colspan="2"/>
				</tr>
				<tr>
					<powersi:textarea id="bke002" rows='3' name="dto.bke002" key="bke002" colspan="8" disabled="true" label="诊断依据" />  
				</tr>
			</powersi:editorlayout>
		</powersi:groupbox>
		
		<powersi:groupbox title="鉴定专家意见">
			<powersi:editorlayout cols="8">
				<tr>
					<powersi:textfield id="bkb023" name="dto.bkb023" required="true" disabled="true" label="专家鉴定病种" />
					<powersi:codeselect id="bke027" name="dto.bke027" key="bke027" codeType="bke401" disabled="true" label="分型" />
					<powersi:codeselect id="bke028" name="dto.bke028" key="bke028" codeType="bke402" disabled="true" label="严重等级" />
					<powersi:textfield id="aae019" name="dto.aae019" validate="money" maxlength="12" key="aae019" disabled="true" label="申请限额"/>
				</tr>
				<tr>
					<powersi:textarea id="aae013" rows='3' name="dto.aae013" key="aae013" colspan="8" disabled="true" label="备注" />  
				</tr>
			</powersi:editorlayout>
		</powersi:groupbox>
		<powersi:groupbox title="审核意见">
					<powersi:editorlayout cols="8">
						<tr>
							<powersi:textfield id="bkz202" name="dto.bkz202" required="true" disabled="true" label="审核病种" />
							<powersi:codeselect id="bke031" name="dto.bke031" key="bke031" codeType="bke401" disabled="true" label="分型" />
							<powersi:codeselect id="bke032" name="dto.bke032" key="bke032" codeType="bke402" disabled="true" label="严重等级" />
							<powersi:textfield id="akb081" name="dto.akb081" validate="money" maxlength="12" key="akb081" disabled="true" label="复审限额"/>
						</tr>
						<tr>
							<powersi:textfield id="aae014" name="dto.aae014" disabled="true" label="审核人"/>
							<powersi:textfield id="aae015" name="dto.aae015" required="true" key="aae015" disabled="true" label="审核日期" />
							<powersi:textarea id="aae189" rows='1' name="dto.aae189" key="aae189" colspan="4" disabled="true" label="审核意见" />  
						</tr>
					</powersi:editorlayout>
		</powersi:groupbox>	 --%>
		<powersi:groupbox title="择点信息">
			<powersi:editorlayout cols="8">
				<tr>
					<powersi:textfield id="bkb051" name="dto.bkb051" key="bkb051" label="联系电话" />
					<powersi:textfield id="bkb052" name="dto.bkb052" key="bkb052" colspan="6" label="详细地址" />
				</tr>
				<tr>
					<powersi:textfield id="bkb054" name="dto.bkb054" key="bkb054" label="代办人" />
					<powersi:textfield id="bkb053" name="dto.bkb053" key="bkb053" label="代办人身份证" />	
					<powersi:codeselect id="bkb050" name ="dto.bkb050" required="true" value="1" list="%{#{'0':'次年选定','1':'首次选定'}}" onchange="changeFixHosp();" label="择点类型" />
				</tr>
			</powersi:editorlayout>
		</powersi:groupbox>	
		
		<table class="table_none" >
			<tbody>
				<tr>
					<td valign="top" width="50%" >
						<powersi:panelbox title="并发症">
							<powersi:datagrid id="gridDisease" formId="diseaseForm" 
								height="200" pageSize="3" enabledSort="true"
								pageSizeOptions="[3,10,20,50,100]" delayLoad="true"
								onLoadedData="function(dg){if(dg.getTotal()>0){dg.setParm('dto.totalResult',dg.getTotal());}}"
								pageParmName="dto.currentPage" pagesizeParmName="dto.pageSize">
								<powersi:datagrid-column name="bkb029" display="疾病编码" width="30%" />
								<powersi:datagrid-column name="bkb030" display="疾病名称" width="60%" />
							</powersi:datagrid>
						</powersi:panelbox>
					</td>
					
					<td valign="top" width="50%" >
						<powersi:panelbox title="定点医疗机构">
							<powersi:panelbox-toolbar>
								<powersi:button cssClass="button" value="增加" onclick="addHosp();"  key="button_add"/>
								<powersi:hidden id="hosplist" name="dto.hosplist" />
							</powersi:panelbox-toolbar>
							<powersi:datagrid id="gridHosp" formId="hospForm" 
								height="200" pageSize="3" enabledSort="true"
								pageSizeOptions="[3,10,20,50,100]" delayLoad="true"
								onLoadedData="function(dg){if(dg.getTotal()>0){dg.setParm('dto.totalResult',dg.getTotal());}}"
								pageParmName="dto.currentPage" pagesizeParmName="dto.pageSize">
								<powersi:datagrid-column name="akb020" display="定点医疗机构编码" width="30%" />
								<powersi:datagrid-column name="akb021" display="定点医疗机构名称" width="60%" />
							</powersi:datagrid>
						</powersi:panelbox>
					</td>
				</tr>
			</tbody>
		</table>
	
	<powersi:errors />
	</powersi:form>
	<div style="display: none">
		<div id="reportIframe"></div>
	</div>
</body>
<script type="text/javascript">
	var pwidth=1000,pheight=500
	var initFormInf;
	var initfixhosp;//最初的定点医院字符串
	$(document).ready(function() {
		gridDisease.reset();
		gridDisease.loadForm(diseaseForm);
		initForm();
		setClientScreenWH();
		initfixhosp=$("#hosplist").val();
		if(getHospCount()>1){
			$("#bkb050").val(0);
			$("#bkb050").prop("disabled", true);
			$("#hosplist").val("");
			gridHosp.reset();
			popupConfirm('该申请已存在两个定点医院信息,是否进行次年选定！','提示', function(yes){
				if(yes){
					
				}else{
					closeThis();
				}
			});
		}else{
			gridHosp.reset();
			gridHosp.loadForm(hospForm);
		}
		initFormInf = $("#mainForm").serialize();
	});
	
	function getHospCount(){
		var hospcount=0;
		if(!powersi.isnull($("#hosplist").val())){
			hospcount=$("#hosplist").val().split(',').length;
			if($("#hosplist").val().lastIndexOf(",")==($("#hosplist").val().length-1)){
				hospcount=hospcount-1;
			}
		}
		return hospcount;
	}
	function setClientScreenWH() {
		pwidth=0.9*$(window).width();
	}

	function changeFixHosp(){
		if($("#bkb050").val()=="0"){//次年选定
			$("#hosplist").val("");
			gridHosp.reset();
		}else{//首次选定
			$("#hosplist").val(initfixhosp);
			gridHosp.reset();
			gridHosp.loadForm(hospForm);
		}
	}
	function saveinfo() {
		if(initFormInf == $("#mainForm").serialize()) {
			popupAlert("数据未作修改，无需保存！", "提示", "warn");
			return
		}
		if (!checkForm("#mainForm")) {
			return false;
		}
		if(powersi.isnull($("#hosplist").val())){
			popupAlert("最少输入一家定点医疗机构！", "提示", "warn");
			return
		}
		var hospcount=getHospCount();
		if(hospcount>2){
			popupAlert("最多输入两家定点医疗机构！", "提示", "warn");
			return
		}
		var param=$("#mainForm").serialize();
		if($("#bkb050").prop("disabled")){
			param+="&dto.bkb050=0";
		}
		param+="&dto.comFlag=101";//是择点修改
	    postJSON("${path}!modifySpeAppInf.action", param, function(json){
	    	if(!checkJSONResult(json,"popup")){
			    return;
		    } else {
		    	popupAlert("保存成功", "提示", "success",function(){
		    		initfixhosp=$("#hosplist").val();
			    });
		    }
	    });  
	}
	
	
	//选择疾病
	function chooseDis(aka035, objindex, objname) {
		parent.popupDialogWithParam({
			url: "${rootPath}/pages/biz/medicare/special/diseaseSelect.jsp\?dto.aka035=" + aka035 + "&dto.aaa027=" +$("#aaa027").val(),
			onClosed: function(){
				var ret = this.returnValue;
				if(ret){
					objindex.value = ret.aka120;
			   	  	objname.value = ret.aka121;
			   	 	if($(objindex).attr('id')=='aka120'){//申请分型
			   	  		initDiseaseDetail('31',cbbke005,objindex.value);
			   	  		initDiseaseDetail('32',cbbke006,objindex.value);
			   	  	}else if($(objindex).attr('id')=='bke025'){//专家分型
			   	  		initDiseaseDetail('31',cbbke027,objindex.value);
			   	  		initDiseaseDetail('32',cbbke028,objindex.value);
			   	  	}
				}		
			}
		}, null,500,pwidth);
	}
	//comflag=31,分型，comflag=32,严重等级
	function initDiseaseDetail(comflag,obj,newvalue){
		if(obj){
			obj.clear();
			obj.setParm('dto.aka120', newvalue);
			obj.setParm('dto.comFlag', comflag);
			obj.setUrl("${path}!getDiseaseLimit.action?dto.caa027=" +$("#caa027").val() + "&dto.aaa027=" +$("#aaa027").val());
		}
	}
	
	//选择医疗机构
	function chooseHosp(objindex, objname) {
		parent.popupDialogWithParam({
			url: "${rootPath}/pages/biz/medicare/special/hospitalSelect.jsp\?isDesignated=1" + "&dto.aaa027=" +$("#aaa027").val(),
			onClosed: function(){
				var ret = this.returnValue;
				if(ret){
					objindex.value = ret.akb020;
			   	  	objname.value = ret.aab069;
				}		
			}
		}, null,500,pwidth);
		
	}
	
	
	//删除并发症/副诊断按钮
	function renderOperate2(row, index, value) {
		try {
			var a = [];

			a.push('<input title="单击删除" type="button" value="删除" class="linkButton"');
			a.push(' onclick="doGrid2Del(\'');
			a.push(index);
			a.push('\')"');
			a.push(" />");
			
			return a.join('');
		} catch (ex) {
			popupError(ex.message);
		}
	}
	
	
	//添加并发症/副诊断
	function addDis(aka035) {
		var diseaselist = document.getElementById("diseaselist").value;
		parent.popupDialogWithParam({
			url: "${rootPath}/pages/biz/medicare/special/diseaseSelect.jsp?checkbox=true&dto.aka035=" + aka035 + "&dto.aaa027=" +$("#aaa027").val(),
			onClosed: function(){
				var ret = this.returnValue;
				if(ret){
					for(var i = 0 ; i < ret.length ; i++) {
						if(diseaselist.toString().indexOf(ret[i].aka120 + ',') == -1){
							gridDisease.addRow({bkb029 : ret[i].aka120, bkb030: ret[i].aka121}, gridDisease.getRow(0), true);
							document.getElementById("diseaselist").value +=ret[i].aka120 + ",";
						}		   	
					}
				}
			}
		}, null,500,pwidth);
	}
	
	
	//删除并发症/副诊断
	function doGrid2Del(index) {
		var row = gridDisease.getRow(index);
		document.getElementById("diseaselist").value = document.getElementById("diseaselist").value.replace(new RegExp(row.bkb029 + ",", "g"), "");
		gridDisease.deleteRow(row);
	}
	
	
	//添加定点医疗机构
	function addHosp() {
		var hosplist = document.getElementById("hosplist").value.substring(0);
		var ob=new Object();
		ob.akb020="<%=hospital_id%>";
		ob.akb021="<%=hospital_name%>";
		if(hosplist.toString().indexOf(ob.akb020 + ',') == -1){
			gridHosp.addRow(ob, gridHosp.getRow(0), true);
			document.getElementById("hosplist").value += ob.akb020+",";
		}
	}
	
	
	//删除定点医疗机构按钮
	function renderOperate3(row, index, value) {
		try {
			var a = [];

			a.push('<input title="单击删除" type="button" value="删除" class="linkButton"');
			a.push(' onclick="doGrid3Del(\'');
			a.push(index);
			a.push('\')"');
			a.push(" />");
			
			return a.join('');
		} catch (ex) {
			popupError(ex.message);
		}
	}
	
	
	//删除定点医疗机构
	function doGrid3Del(index) {
		var row = gridHosp.getRow(index);
		document.getElementById("hosplist").value = document.getElementById("hosplist").value.replace(new RegExp(row.akb020 + ",", "g"), "");
		gridHosp.deleteRow(row);
	}
	
	
	function initForm() {
		/* initDiseaseDetail('31',cbbke005,$('#aka120').val());
  		initDiseaseDetail('32',cbbke006,$('#aka120').val());
 		//专家疾病
 		initDiseaseDetail('31',cbbke027,$('#bke025').val());
 		initDiseaseDetail('32',cbbke028,$('#bke025').val()); */
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
	
	
	//输入校验
	function checkFunc(field){

		if('aae030' == field.attr("id")) { //校验生效日期
			var aae030 = $("#aae030").val();
			var aae031 = $("#aae031").val();
			if(aae031 < aae030) {
				return "*生效日期 不能大于失效日期";
			}
		}
		
		
		if('aae031' == field.attr("id")) { //校验失效日期
			var aae030 = $("#aae030").val();
			var aae031 = $("#aae031").val();
			if(aae031 < aae030) {
				return "*失效日期 不能小于生效日期";
			}
		}
		
		
		if('bke033' == field.attr("id")) { //校验联系电话
			var reg = new RegExp('^[0-9]{0,20}$');
			if(!reg.test(field.val())) {
				//return "\*联系电话 只能是0~20位纯数字";
			}
		}
		
		
		if('bke014' == field.attr("id")) { //校验科室电话
			var reg = new RegExp('^[0-9]{0,20}$');
			if(!reg.test(field.val())) {
				return "*科室电话 只能是0~20位纯数字";
			}
		}
	}
	
	
	function closeThis() {
		if(initFormInf == $("#mainForm").serialize()) {
			closeDialog();
			return;
		}
		
		popupConfirm('已经录入了数据，确定关闭吗？','提示', function(yes){
			if(yes){
				closeDialog();
			}
		});
	}

	var powerPrint = null; //打印对象
	var reportID = '';//报表ID
	//导出
	function doExport() {
		if (reportID && "none" != reportID) {
			location.href = rootPath + "/downloadReportFileServlet.download?reportID=" + reportID;
		} else {
			var param = $("#mainForm").serialize() + "&dto.caa027=" +$("#caa027").val();
			param+="&dto.comFlag=101";//是择点修改
		    postJSON("${path}!creatApplyPrintInfo.action", param, function(json){
		    	if(null != json.data){
		    		reportID = json.data;
					location.href = rootPath + "/downloadReportFileServlet.download?reportID=" + reportID;
				} else {
					popupAlert(json.message, "提示", "warn");
					return
				}
		    });
		}
	}
	//打印
	/* function doPrint() {
		 var param = $("#mainForm").serialize() + "&dto.caa027=" +$("#caa027").val();
		param+="&dto.comFlag=101";//是择点修改
	    postJSON("${path}!creatApplyPrintInfo.action", param, function(json){
	    	if(null != json.data){
	    		$("#reportIframe").html(json.data);
	    		powerPrint = $("#reportIframe").PowerPrint({
					name : '申请单'
				});
				powerPrint.preview();
	    		//$("#reportIframe").data(json.data);
	    		//$("#reportIframe").contents().find("body").html(json.data);
	    		 reportID = json.data;
				//loadHtml(); 
			}
	    }); 
	} */
	function doPrint() {
		var param = "dto.comFlag=101&dto.pageFlag=" +$("#pageFlag").val()+"&dto.aaz267=" +$("#aaz267").val()+"&dto.aaa027="+$("#aaa027").val()+"&dto.akb020="+$("#akb020").val();
		/* popupDialog(
				{
					url : "${path}!creatApplyPrintInfo.action?"+param,
					onClosed : function() {

					}
				}, 600, 1000); */
		popupDialogWithParam({
			url : "${path}!creatApplyPrintInfo.action?"+param,
			onClosed: function(){
				
			}
		}, null,500,pwidth);
	}
	
</script>
</powersi:html>