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
	<powersi:head title="修改大病特药申请" />
<body>
	<powersi:form id="drugForm" name="diseaseForm" action="specialManager!getOtherAppInfList.action" namespace="/medicarespecial">
		<powersi:hidden name="dto.aaz267"/>
		<powersi:hidden name="dto.aaa027"/>
		<powersi:hidden name="dto.caa027"/>
		<powersi:hidden name="dto.comFlag" value="3"/>
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
			<powersi:textfield id="aka083" name="dto.aka083" key="aka083" value="180" label="申请类型"/>
			<powersi:textfield id="aaa027" name="dto.aaa027" key="aaa027"   label="统筹区编码"/>
			<powersi:textfield id="aka130" name="dto.aka130" key="aka130" value="18" label="业务类型"/>
			<powersi:textfield id="bka006" name="dto.bka006" key="bka006" value="180" label="待遇类型"/>
			<powersi:textfield id="aae016" name="dto.aae016" key="aae016" label="审核状态"/>
			<powersi:textfield id="aaz267" name="dto.aaz267" key="aaz267" label="特殊业务申请ID"/>
			<powersi:textfield id="caa027" name="dto.caa027" key="caa027" label="中心系统"/>
			<powersi:textfield id="comFlag" name="dto.comFlag" key="comFlag" label="公共标志"/>
			<powersi:textfield id="max_drug_num" name="dto.bizpolicys.max_drug_num" key="max_drug_num"  label="大病特药药品最大数量"/>
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
					<powersi:textfield id="akb021" name="dto.akb021" required="true" disabled="true" label="申请医院"/>
					<powersi:textfield id="aae127" name="dto.aae127" required="true" disabled="true" key="aae127" mask="date" label="申请日期" />
					<powersi:textfield id="aae030" name="dto.aae030" required="true" key="aae030" mask="date" validate="funcCall[checkFunc]" label="生效日期" />
					<powersi:textfield id="aae031" name="dto.aae031" required="true" key="aae031" mask="date" validate="funcCall[checkFunc]" label="失效日期" />
					
				</tr>
				<tr>
					<powersi:textfield id="bke033" name="dto.bke033" key="bke033" validate="funcCall[checkFunc]" label="联系电话" />
					<powersi:textfield id="aae011" name="dto.aae011" disabled="true" label="经办人"/>
					<td colspan="4"/>
				</tr>
			</powersi:editorlayout>
		</powersi:groupbox>
		<% if(null == request.getAttribute("dto.aae016")||"0".equals(request.getAttribute("dto.aae016").toString())){//未审核数据，可以随便修改 %>		
		
		<powersi:groupbox title="详细信息">
			<powersi:editorlayout cols="8">
				<tr>
					<powersi:textfield id="aka121" name="dto.aka121" required="true" readonly="true"
						buttonId="di" onbuttonclick="chooseDis(10, aka120,aka121)" label="申请病种" />
					<powersi:hidden id="aka120" name="dto.aka120" label="疾病编码" />
					<powersi:textfield id="bkb023" name="dto.bkb023" key="bke025" buttonId="dis" 
						required="true" onbuttonclick="chooseDis(10, bke025,bkb023)" readonly="true" />
					<powersi:hidden id="bke025" name="dto.bke025" label="疾病编码" />
					<powersi:textfield id="aae019" name="dto.aae019" validate="money" maxlength="12" key="aae019" label="申请限额"/>
					<td colspan="2"/>
				</tr>
				<tr>
					<powersi:textarea id="bke002" rows='3' name="dto.bke002" key="bke002" maxlength="165" colspan="3" label="诊断依据" />
					<powersi:textarea id="aae013" rows='3' name="dto.aae013" key="aae013" maxlength="165" colspan="3" label="备注" />
				</tr>
			</powersi:editorlayout>
		</powersi:groupbox>
		
		<table class="table_none" >
			<tbody>
				<tr>
					<td valign="top" width="50%" >
						<powersi:panelbox title="特殊药品">
							<powersi:panelbox-toolbar>
								<powersi:button cssClass="button" value="增加"  onclick="addDrug();" key="button_add"/>
								<powersi:hidden id="druglist" name="dto.druglist" />
							</powersi:panelbox-toolbar>
							
							<powersi:datagrid id="gridDrug" formId="drugForm"   
								height="200" pageSize="100" enabledSort="true" enabledEdit="true" clickToEdit="true"
								pageSizeOptions="[100]" delayLoad="true"
								onLoadedData="function(dg){if(dg.getTotal()>0){dg.setParm('dto.totalResult',dg.getTotal());}}"
								pageParmName="dto.currentPage" pagesizeParmName="dto.pageSize">
								<powersi:datagrid-column name="ake001" display="药品项目编码" width="20%" />
								<powersi:datagrid-column name="ake002" display="药品项目名称" width="50%" />
								<powersi:datagrid-column name="aae019" display="限额" editor="{type: 'float'}" width="20%" />
								<powersi:datagrid-column display="操作" key="operate" render="renderOperate2" width=""></powersi:datagrid-column>
							</powersi:datagrid>
						</powersi:panelbox>
					</td>
					
					<td valign="top" width="50%" >
						<powersi:panelbox title="定点医疗机构">
							<powersi:panelbox-toolbar>
								<powersi:button cssClass="button" value="增加"  onclick="addHosp();" key="button_add"/>
								<powersi:hidden id="hosplist" name="dto.hosplist" />
							</powersi:panelbox-toolbar>
							
							<powersi:datagrid id="gridHosp" formId="hospForm" 
								height="200" pageSize="3" enabledSort="true"
								pageSizeOptions="[3,10,20,50,100]" delayLoad="true"
								onLoadedData="function(dg){if(dg.getTotal()>0){dg.setParm('dto.totalResult',dg.getTotal());}}"
								pageParmName="dto.currentPage" pagesizeParmName="dto.pageSize">
								<powersi:datagrid-column name="akb020" display="定点医疗机构编码" width="30%" />
								<powersi:datagrid-column name="akb021" display="定点医疗机构名称" width="60%" />
								<powersi:datagrid-column display="操作" key="operate" render="renderOperate3" width=""></powersi:datagrid-column>
							</powersi:datagrid>
						</powersi:panelbox>
					</td>
				</tr>
			</tbody>
		</table>
		<%}else{//已审核数据只能修改某些指标项%>
			<powersi:groupbox title="基本信息">
			<powersi:editorlayout cols="8">
				<tr>
					<powersi:textfield id="akb021" name="dto.akb021" required="true" disabled="true" label="申请医院"/>
					<powersi:textfield id="aae127" name="dto.aae127" required="true" key="aae127" disabled="true" label="申请日期" />
					<powersi:textfield id="aae030" name="dto.aae030" required="true" key="aae030" disabled="true" label="生效日期" />
					<powersi:textfield id="aae031" name="dto.aae031" required="true" key="aae031" disabled="true" label="失效日期" />
					
				</tr>
				<tr>
					<powersi:textfield id="bke033" name="dto.bke033" key="bke033" disabled="true" label="联系电话" />
					<powersi:textfield id="aae011" name="dto.aae011" disabled="true" label="经办人"/>
					<powersi:codeselect id="aae016" name ="dto.aae016" codeType="aae016_spapp" disabled="true" label="审核状态" />
					<td colspan="2"/>
				</tr>
			</powersi:editorlayout>
		</powersi:groupbox>
		
		<powersi:groupbox title="详细信息">
			<powersi:editorlayout cols="8">
				<tr>
					<powersi:textfield id="aka121" name="dto.aka121" required="true" disabled="true" label="申请病种" />
					<powersi:hidden id="aka120" name="dto.aka120" disabled="true" label="疾病编码" />
					<powersi:textfield id="bkb023" name="dto.bkb023" required="true" disabled="true" label="专家鉴定病种" />
					<powersi:hidden id="bke025" name="dto.bke025" disabled="true" label="疾病编码" />
					<powersi:textfield id="aae019" name="dto.aae019" validate="money" maxlength="12" key="aae019" disabled="true" label="申请限额"/>
					<td colspan="2"/>
				</tr>
				<tr>
					<powersi:textarea id="bke002" rows='3' name="dto.bke002" key="bke002" colspan="3" disabled="true" label="诊断依据" />  
					<powersi:textarea id="aae013" rows='3' name="dto.aae013" key="aae013" maxlength="165" colspan="3" disabled="true" label="备注" />  
				</tr>
			</powersi:editorlayout>
		</powersi:groupbox>
		<table class="table_none" >
			<tbody>
				<tr>
					<td valign="top" width="50%" >
						<powersi:panelbox title="特殊药品">
							<powersi:panelbox-toolbar>
								<powersi:button cssClass="button" value="增加"  onclick="addDrug();" key="button_add"/>
								<powersi:hidden id="druglist" name="dto.druglist" />
							</powersi:panelbox-toolbar>
							
							<powersi:datagrid id="gridDrug" formId="drugForm"   
								height="200" pageSize="100" enabledSort="true" enabledEdit="true" clickToEdit="true"
								pageSizeOptions="[100]" delayLoad="true"
								onLoadedData="function(dg){if(dg.getTotal()>0){dg.setParm('dto.totalResult',dg.getTotal());}}"
								pageParmName="dto.currentPage" pagesizeParmName="dto.pageSize"  onBeforeEdit="onBeforeEdit_d">
								<powersi:datagrid-column name="ake001" display="药品项目编码" width="20%" />
								<powersi:datagrid-column name="ake002" display="药品项目名称" width="50%" />
								<powersi:datagrid-column name="aae019" display="限额" editor="{type: 'float'}" width="20%" />
							</powersi:datagrid>
						</powersi:panelbox>
					</td>
					
					<td valign="top" width="50%" >
						<powersi:panelbox title="定点医疗机构">
							<powersi:panelbox-toolbar>
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
		<%} %>
	<powersi:errors />
	</powersi:form>
</body>
<script type="text/javascript">
	var pwidth=1000,pheight=500
	var initFormInf,initHospInf,initDrugInf
	
	
	$(document).ready(function() {
		gridDrug.reset();
		gridDrug.loadForm(drugForm);
		gridHosp.reset();
		gridHosp.loadForm(hospForm);
		initForm();
		setClientScreenWH();
		initFormInf = $("#mainForm").serialize();
		initHospInf=$("#hosplist").val();
		initDrugInf=$("#druglist").val();
	});

	onBeforeEdit_d = function(edit) {
		if('1'!=edit.record.isnew){
			return false;
		}
	}
	
	function setClientScreenWH() {
		pwidth=0.9*$(window).width();
	}


	function saveinfo() {
		gridDrug.endEdit(gridDrug.getSelected());//解决修改了只限额，点击保存，修改没有生效问题
		if(!gridDrug.hasChanged()&&(initFormInf == $("#mainForm").serialize())) {
			popupAlert("数据未作修改，无需保存！", "提示", "warn");
			return
		}
		
		if (!checkForm("#mainForm")) {
			return false;
		}
		if(''!=$("#max_drug_num").val()&&'-1'!=$("#max_drug_num").val()){
			var maxnum=parseInt($("#max_drug_num").val());
			var druglist=$("#druglist").val();
			if('' != druglist&&druglist.lastIndexOf(",")==(druglist.length-1)){
				druglist=druglist.substr(0,druglist.length-1);
			}
			if('' != druglist&&druglist.split(",").length>maxnum){
				popupAlert("药品数量最多不能超过"+maxnum+"个", "提示", "warn");
				return
			}
		}
		
		/* 其他校验 start */
		var checkStatus = false;
		var checkMessage = '';
		
		if('' == $("#druglist").val()) {
			checkMessage += "请选择特殊药品！\n";
			checkStatus = true;
		}
		/* if('' == $("#hosplist").val()) {
			checkMessage += "请选择定点医疗机构！\n";
			checkStatus = true;
		} */
		if(checkStatus) {
			popupAlert(checkMessage, "提示", "warn");
			return
		}
		/* 其他校验 start */
		/**优化效率部分 begin*/
		var hosps=$("#hosplist").val();
		var drugs=$("#druglist").val();
		$("#hosplist").val(initHospInf);
		$("#druglist").val(initDrugInf);
		if($("#mainForm").serialize()==initFormInf){
			$("#comFlag").val($("#comFlag").val()+",00");
		}
		$("#hosplist").val(hosps);
		$("#druglist").val(drugs);
		if(initHospInf==$("#hosplist").val()){//定点医院没有修改
			$("#comFlag").val($("#comFlag").val()+",01");
		}
		if(!gridDrug.hasChanged()){//药品没有修改
			$("#comFlag").val($("#comFlag").val()+",03");
		}
		
		/**优化效率部分 end*/
		<% if(null == request.getAttribute("dto.aae016")||"0".equals(request.getAttribute("dto.aae016").toString())){//未审核数据，comFlag不能设置成102,102:不会做校验 %>		
			$("#comFlag").val($("#comFlag").val().replace(/102/,""));
		<%}%>
		
		var param = $("#mainForm").serialize() + "&dto.drugarray=" + getDrugList();
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
			url: "${rootPath}/pages/biz/medicare/special/diseaseSelect.jsp\?dto.aka035=" + aka035 + "&dto.aaa027=" +$("#aaa027").val(),
			onClosed: function(){
				var ret = this.returnValue;
				if(ret){
					if('bke025' == objindex.id && objindex.value != ret.aka120) { //修改了疾病，清除药品信息
						
			   	 	}else{
						$("#bke025").val(ret.aka120);
						$("#bkb023").val(ret.aka121);
					}
					document.getElementById("druglist").value = '';
					gridDrug.reset();
					objindex.value = ret.aka120;
			   	  	objname.value = ret.aka121;
				}		
			}
		}, null,500,pwidth);
	}
	
	//选择医疗机构
	function chooseHosp(objindex, objname) {
		parent.popupDialogWithParam({
			url: "${rootPath}/pages/biz/medicare/special/hospitalSelect.jsp\?isDesignated=1&dto.aka130=18" + "&dto.aaa027=" +$("#aaa027").val(),
			onClosed: function(){
				var ret = this.returnValue;
				if(ret){
					objindex.value = ret.akb020;
			   	  	objname.value = ret.aab069;
				}		
			}
		}, null,500,pwidth);
		
	}
	
	
	//删除特殊药品按钮
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
	
	
	//添加药品/项目
	function addDrug() {
		if('' == $("#bke025").val()) {
			popupAlert("请选择专家鉴定病种！", "提示", "warn");
			return
		}
		
		var druglist = document.getElementById("druglist").value;
		parent.popupDialogWithParam({
			url: "${rootPath}/pages/biz/medicare/special/drugSelect.jsp?checkbox=true&aka120=" + $("#bke025").val() + "&aae140=" +$("#aae140").val() + "&dto.aaa027=" +$("#aaa027").val()+"&ake001_not=" + druglist.substring(0, druglist.length -1),
			onClosed: function(){
				var ret = this.returnValue;
				if(ret){
					for(var i = 0 ; i < ret.length ; i++) {
						if(druglist.toString().indexOf(ret[i].ake001 + ',') == -1){
							gridDrug.addRow({ake001 : ret[i].ake001, ake002: ret[i].ake002, aae019: ret[i].aae019,isnew:1}, gridDrug.getRow(0), true);
							druglist +=ret[i].ake001 + ",";
						}	
						document.getElementById("druglist").value =druglist;	   	
					}
				}
			}
		}, null,500,pwidth);
	}
	
	
	//删除特殊药品
	function doGrid2Del(index) {
		var row = gridDrug.getRow(index);
		document.getElementById("druglist").value = document.getElementById("druglist").value.replace(new RegExp(row.ake001 + ",", "g"), "");
		gridDrug.deleteRow(row);
	}
	
	
	//添加定点医疗机构
	function addHosp() {
		<%-- var hosplist = document.getElementById("hosplist").value.substring(0);
		var ob=new Object();
		ob.akb020="<%=hospital_id%>";
		ob.akb021="<%=hospital_name%>";
		if(hosplist.toString().indexOf(ob.akb020 + ',') == -1){
			gridHosp.addRow(ob, gridHosp.getRow(0), true);
			document.getElementById("hosplist").value += ob.akb020+",";
		}  --%>
		var hosplist = document.getElementById("hosplist").value.substring(0);
		parent.popupDialogWithParam({
			url: "${rootPath}/pages/biz/medicare/special/hospitalSelect.jsp?checkbox=true&isDesignated=1&dto.aka130=18&akb020_not=" + hosplist.substring(0, hosplist.length -1) + "&dto.aaa027=" +$("#aaa027").val(),
			onClosed: function(){
				var ret = this.returnValue;
				if(ret){
					for(var i = 0 ; i < ret.length ; i++) {
						if(hosplist.indexOf(ret[i].akb020 + ',') == -1){
							gridHosp.addRow(ret[i], gridHosp.getRow(0), true);
							hosplist += ret[i].akb020+",";
						}
						document.getElementById("hosplist").value = hosplist;
					}
				}
			}
		}, null,500,pwidth);
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
	
	
	//将药品信息转成字符串
	function getDrugList() {
		var rows = gridDrug.getRows();
		var list = new Array();
		
		$.each(rows, function(rowid, row){
			ob = new Object();
			ob.ake001 = row.ake001;//药品项目编码
			ob.ake002 = row.ake002;//药品项目名称
			ob.aae019 = row.aae019;//限额
			list.push(ob);
		});
		
		return powersi.tostring(list);
	}
	
	
	function initForm() {
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