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
	<powersi:head title="修改门特申请并发症" />
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
			<powersi:textfield id="pageFlag" name="dto.pageFlag" label="页面标志"/>
			<powersi:textfield id="aae140" name="dto.aae140" key="aae140" label="险种类型"/>
			<powersi:textfield id="aaa027" name="dto.aaa027" key="aaa027"   label="统筹区编码"/>
			<powersi:textfield id="aka130" name="dto.aka130" key="aka130" value="13" label="业务类型"/>
			<powersi:textfield id="bka006" name="dto.bka006" key="bka006" value="131" label="待遇类型"/>
			<powersi:textfield id="aae016" name="dto.aae016" key="aae016" label="审核状态"/>
			<powersi:textfield id="aaz267" name="dto.aaz267" key="aaz267" label="特殊业务申请ID"/>
			<powersi:textfield id="caa027" name="dto.caa027" key="caa027" label="中心系统"/>
		</div>
		
		<powersi:panelbox title="人员信息">
			<powersi:panelbox-toolbar>
					<powersi:button key="button_save" data-hotkey='alt+S' onclick="saveinfo();" />
					<powersi:button key="button_close" data-hotkey='alt+B' value="关 闭" onclick="closeThis();" />				
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
		
		<table class="table_none" >
			<tbody>
				<tr>
					<td valign="top" width="100%" >
						<powersi:panelbox title="并发症">
							<powersi:panelbox-toolbar>
								<powersi:button cssClass="button" value="增加"  onclick="addDis('');" key="button_add"/>
								<powersi:hidden id="diseaselist" name="dto.diseaselist" />
							</powersi:panelbox-toolbar>
							
							<powersi:datagrid id="gridDisease" formId="diseaseForm" 
								height="200" pageSize="3" enabledSort="true"
								pageSizeOptions="[3,10,20,50,100]" delayLoad="true"
								onLoadedData="function(dg){if(dg.getTotal()>0){dg.setParm('dto.totalResult',dg.getTotal());}}"
								pageParmName="dto.currentPage" pagesizeParmName="dto.pageSize">
								<powersi:datagrid-column name="bkb029" display="疾病编码" width="30%" />
								<powersi:datagrid-column name="bkb030" display="疾病名称" width="60%" />
								<powersi:datagrid-column display="操作" key="operate" render="renderOperate2" width=""></powersi:datagrid-column>
							</powersi:datagrid>
						</powersi:panelbox>
					</td>
				</tr>
				<tr>
					<td style="display:none" >
						<powersi:panelbox title="定点医疗机构">
							<powersi:panelbox-toolbar>
								<%-- <powersi:button cssClass="button" value="增加"   onclick="addHosp();" key="button_add"/> --%>
								<powersi:hidden id="hosplist" name="dto.hosplist" />
							</powersi:panelbox-toolbar>
							
							<powersi:datagrid id="gridHosp" formId="hospForm" 
								height="200" pageSize="3" enabledSort="true"
								pageSizeOptions="[3,10,20,50,100]" delayLoad="true"
								onLoadedData="function(dg){if(dg.getTotal()>0){dg.setParm('dto.totalResult',dg.getTotal());}}"
								pageParmName="dto.currentPage" pagesizeParmName="dto.pageSize">
								<powersi:datagrid-column name="akb020" display="定点医疗机构编码" width="30%" />
								<powersi:datagrid-column name="akb021" display="定点医疗机构名称" width="60%" />
								<%-- <powersi:datagrid-column display="操作" key="operate" render="renderOperate3" width=""></powersi:datagrid-column> --%>
							</powersi:datagrid>
						</powersi:panelbox>
					</td>
				</tr>
			</tbody>
		</table>
	
	<powersi:errors />
	</powersi:form>
</body>
<script type="text/javascript">
	var pwidth=1000,pheight=500
	var initFormInf;
	
	$(document).ready(function() {
		gridDisease.reset();
		gridDisease.loadForm(diseaseForm);
		gridHosp.reset();
		gridHosp.loadForm(hospForm);
		initForm();
		setClientScreenWH();
		initFormInf = $("#mainForm").serialize();
	});
	
	
	function setClientScreenWH() {
		pwidth=0.9*$(window).width();
	}

	function saveinfo() {
		
		if(initFormInf == $("#mainForm").serialize()) {
			popupAlert("数据未作修改，无需保存！", "提示", "warn");
			return
		}
		
		if (!checkForm("#mainForm")) {
			return false;
		}
		
		var param = $("#mainForm").serialize();
		param+="&dto.comFlag=102";//是并发症修改
	    postJSON("${path}!modifySpeAppInf.action", param, function(json){
	    	if(!checkJSONResult(json,"popup")){
			    return;
		    } else {
		    	popupAlert("保存成功", "提示", "success",function(){
			    	closeDialog(json.data);
			    });
		    }
	    });
	}
	
	
	//选择疾病
	function chooseDis(aka035, objindex, objname) {
		parent.popupDialogWithParam({
			url: "${rootPath}/pages/biz/medicare/special/diseaseSelect.jsp\?dto.aka035=" + aka035 + "&dto.aka083=131&dto.aaa027=" +$("#aaa027").val(),
			onClosed: function(){
				var ret = this.returnValue;
				if(ret){
					objindex.value = ret.aka120;
			   	  	objname.value = ret.aka121;
			   	 	if($(objindex).attr('id')=='aka120'){//申请分型
			   	  		//initDiseaseDetail('31',cbbke005,objindex.value);
			   	  		//initDiseaseDetail('32',cbbke006,objindex.value);
				   	 	$('#bke025').val(ret.aka120);
			   	 		$('#bkb023').val(ret.aka121);
			   	 		$('#bke005').val(ret.bke401);
			   	 		$('#bke006').val(ret.bke402);
				   	 	$('#bke027').val(ret.bke401);
			   	 		$('#bke028').val(ret.bke402);
			   	 		$('#aae019').val(ret.aae019);
			   	  	}else if($(objindex).attr('id')=='bke025'){//专家分型
			   	  		//initDiseaseDetail('31',cbbke027,objindex.value);
			   	  		//initDiseaseDetail('32',cbbke028,objindex.value);
				   	  	$('#bke027').val(ret.bke401);
			   	 		$('#bke028').val(ret.bke402);
			   	 		$('#aae019').val(ret.aae019);
			   	  	}
				}		
			}
		}, null,500,pwidth);
	}
	function changeBke005(){
		$('#bke027').val($('#bke005').val());
	}
	function changeBke006(){
		$('#bke028').val($('#bke006').val());
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
			url: "${rootPath}/pages/biz/medicare/special/hospitalSelect.jsp\?isDesignated=1&dto.aka130=13" + "&dto.aaa027=" +$("#aaa027").val(),
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
		/* var hosplist = document.getElementById("hosplist").value.substring(0);
		parent.popupDialogWithParam({
			url: "${rootPath}/pages/biz/medicare/special/hospitalSelect.jsp?checkbox=true&isDesignated=1&dto.aka130=13&akb020_not=" + hosplist.substring(0, hosplist.length -1) + "&dto.aaa027=" +$("#aaa027").val(),
			onClosed: function(){
				var ret = this.returnValue;
				if(ret){
					for(var i = 0 ; i < ret.length ; i++) {
						if(hosplist.toString().indexOf(ret[i].akb020 + ',') == -1){
							gridHosp.addRow(ret[i], gridHosp.getRow(0), true);
							document.getElementById("hosplist").value += ret[i].akb020+",";
						}		   	
					}
				}
			}
		}, null,500,pwidth); */
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
</script>
</powersi:html>