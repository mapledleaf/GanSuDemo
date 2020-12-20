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
	<powersi:head title="新增大病特药申请" />
<body>
	<powersi:form id="mainForm" action="specialManager!addSpeAppInf.action" namespace="/medicarespecial">
		<!-- 隐藏元素层 -->
		<div style="display: none">
			<powersi:textfield id="pageFlag" name="dto.pageFlag" label="页面标志"/>
			<powersi:textfield id="aae140" name="dto.aae140" key="aae140" label="险种类型"/>
			<powersi:textfield id="aka083" name="dto.aka083" key="aka083" value="180" label="申请类型"/>
			<powersi:textfield id="aaa027" name="dto.aaa027" key="aaa027" value="<%= aaa027  %>"  label="统筹区编码"/>
			<powersi:textfield id="aka130" name="dto.aka130" key="aka130" value="18" label="业务类型"/>
			<powersi:textfield id="bka006" name="dto.bka006" key="bka006" value="180" label="待遇类型"/>
			<powersi:textfield id="aae016" name="dto.aae016" key="aae016" value="0" label="审核状态"/>
			<powersi:textfield id="acceptId" name="dto.acceptId" key="acceptId" label="柜员制受理ID"/>
			<powersi:textfield id="bka100" name="dto.bka100" key="bka100" label="社会保障号"/>
			<powersi:textfield id="max_drug_num" name="dto.bizpolicys.max_drug_num" key="max_drug_num"  label="大病特药药品最大数量"/>
		</div>
		
		<powersi:panelbox title="人员信息">
			<powersi:panelbox-toolbar>
				<powersi:button key="button_save" value="保 存" data-hotkey='alt+S' onclick="saveinfo();" />	
				<powersi:button key="button_clear"  value="清 屏" data-hotkey='alt+C' onclick="resetData();"/>
				<!-- comFlag:21是综合页面传输 ，隐藏关闭按钮-->
				<% if(null == request.getAttribute("dto.comFlag")||!"21".equals(request.getAttribute("dto.comFlag").toString())){ %>
					<powersi:button key="button_close" value="关 闭" data-hotkey='alt+B' onclick="closeThis();" />	
				<% } %>			
			</powersi:panelbox-toolbar>
			
			<powersi:editorlayout cols="8">
				<tr>
					<powersi:codeselect id="argName" name ="dto.argName" list="%{#{'aac002':'社会保障号'}}" label="查询类型" />
					<powersi:textfield id="querystring" name="querystring"
						title="请输入信息回车" placeholder="请输入信息回车!" readonly="false"
						onkeyup="findAac001()" buttonText="读卡" buttonId="readic_button"
						buttonDisabled="false"  label="查询信息" />
					<powersi:textfield id="aac001" name="dto.aac001" key="aac001" readonly="true" label="个人电脑号" />
					<powersi:textfield id="aac003" name="dto.aac003" key="aac003" disabled="true" label="姓名" />
				</tr>	
				<tr>
					<powersi:codeselect id="aac004" name="dto.aac004" key="aac004" disabled="true" codeType="aac004" headerValue=" " headerKey="" label="性别" />
					<powersi:textfield id="aac002" name="dto.aac002" key="aac002" readonly="true" label="身份证号" />
					<powersi:textfield id="aac006" name="dto.aac006" key="aac006" disabled="true" label="出生日期"/>
					<powersi:codeselect id="bka035" name="dto.bka035" key="bka035" disabled="true" codeType="bka035" headerValue=" " headerKey="" label="人员类别"/>
				</tr>
			</powersi:editorlayout>
		</powersi:panelbox>
		
		<!-- 用于初始化设置不可用的div --><div id="divshield"><powersi:groupbox title="基本信息">
			<powersi:editorlayout cols="8">
				<tr>
					<powersi:textfield id="akb021" name="dto.akb021" key="akb021" readonly="true" value="<%= hospital_name %>" label="申请医院"/>
					<powersi:hidden id="akb020" name="dto.akb020" value="<%= hospital_id %>" label="申请医院编码" />
					
					<powersi:textfield id="aae127" name="dto.aae127" required="true" disabled="true" key="aae127" mask="date" label="申请日期" />
					<powersi:textfield id="aae030" name="dto.aae030" required="true" key="aae030" mask="date" validate="funcCall[checkFunc]" label="生效日期" />
					<powersi:textfield id="aae031" name="dto.aae031" required="true" key="aae031" mask="date" validate="funcCall[checkFunc]" label="失效日期" />
					
				</tr>
				<tr>
					<powersi:textfield id="bke033" name="dto.bke033" key="bke033" validate="funcCall[checkFunc]" label="联系电话" />
					<td colspan="6"/>
				</tr>
			</powersi:editorlayout>
		</powersi:groupbox>
		
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
							
							<powersi:datagrid id="gridDrug" formId="" 
								height="200" pageSize="100" enabledSort="true" enabledEdit="true" clickToEdit="true"
								pageSizeOptions="[100]" delayLoad="true">
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
							
							<powersi:datagrid id="gridHosp" formId="" 
								height="200" pageSize="3" enabledSort="true"
								pageSizeOptions="[3,10,20,50,100]" delayLoad="true" >
								<powersi:datagrid-column name="akb020" display="定点医疗机构编码" width="30%" />
								<powersi:datagrid-column name="akb021" display="定点医疗机构名称" width="60%" />
								<powersi:datagrid-column display="操作" key="operate" render="renderOperate3" width=""></powersi:datagrid-column>
							</powersi:datagrid>
						</powersi:panelbox>
					</td>
				</tr>
			</tbody>
		</table>
	
	<powersi:errors />
	</div></powersi:form>
</body>
<script type="text/javascript">
	var pwidth=1000,pheight=500
	var initFormInf;
	
	
	$(document).ready(function() {
		initForm();
		setClientScreenWH();
		initFormInf = $("#mainForm").serialize();
		//如果有人员id,不屏蔽后续操作
		if(''=="${dto.aac001}"){
			changeShield(1);
		}
		//全键盘操作
		initTabKey();
		//重置相关
		initReset();
	});
	
	
	function setClientScreenWH() {
		pwidth=0.9*$(window).width();
	}
	
	
	function afterGetPerson(param) {
		showRunning(false);
		var param = $("#mainForm").serialize();
	    postJSON("${path}!verifyAfterQuery.action", param, function(json){
	    	if(!checkJSONResult(json,"popup")){
	    		resetData();
			    return;
		    } else {

		    	/* 人员详细信息 */
		    	var assiKey = getPageInitParamByApplyType('default', 'assiKey');
		    	for (var i = 0,len = assiKey.length; i < len; i ++) {
		    		if (!powersi.isnull(json.data[assiKey[i]])) {
		    			$("#" + assiKey[i]).val(json.data[assiKey[i]]);
					}
		    	}
				/* 人员详细信息 */
				
				changeShield(0);
				//全键盘操作
				firstTabFocus();
		    }
	    });
		showRunning(true);
	}
	
	//设置按钮批量可用
	function changeShield(value) {
		if(1==value){
			if($("#myshield").length==0){//判断divshield是否存在
				$("#divshield").after("<div id='myshield'></div>");
				$('#myshield').css({
					"width":"100%",
					"height":$("#divshield").height(),
					"position":"absolute",
					"z-index":"1000",
					"background": "rgba(0, 0, 0, 0)",
					"top": $('#divshield').offset().top,
					"left": "0"});
				$("#myshield").click(function() {//点击屏蔽区域
					popupAlert("请先选择人员！", "提示", "warn",function(){
						$("#queryString").val('');
						$("#queryString").focus();
						});
				});
			}
			$('#myshield').show();
		}else{
			$('#myshield').hide();
		}
	}

	function saveinfo() {
		
		if('' == $("#aac001").val()) {
			popupAlert("请选择人员！", "提示", "warn");
			return
			
		}
		
		if (!checkForm("#mainForm")) {
			return false;
		}
		
		
		
		/* 其他校验 start */
		var checkMessage = '';
		
		if('' == $("#druglist").val()) {
			checkMessage += "请选择特殊药品！\n";
			popupAlert(checkMessage, "提示", "warn");
			return
		}
		/* if('' == $("#hosplist").val()) {
			checkMessage += "请选择定点医疗机构！\n";
			popupAlert(checkMessage, "提示", "warn");
			return
		} */
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
		
		var param = $("#mainForm").serialize() + "&dto.drugarray=" + getDrugList();
	    postJSON("${path}!addSpeAppInf.action", param, function(json){
	    	if(!checkJSONResult(json,"popup")){
			    return;
		    } else {
		    	resetData();
		    	popupAlert("保存成功", "提示", "success",function(){
			    	closeDialog(1);
			    });
		    }
	    });
	}
	
	//选择疾病
	function chooseDis(aka035, objindex, objname) {
		parent.popupDialogWithParam({
			url: "${rootPath}/pages/biz/medicare/special/diseaseSelect.jsp\?dto.aka035=" + aka035 + "&dto.aaa027=" +$("#aaa027").val(),
			onClosed: function(){
				$(objname).focus();
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
				$(objname).focus();
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
							gridDrug.addRow({ake001 : ret[i].ake001, ake002: ret[i].ake002, aae019: ret[i].aae019}, gridDrug.getRow(0), true);
							druglist +=ret[i].ake001 + ",";
						}	
						document.getElementById("druglist").value =druglist;	   	
					}
				}
				selectGridRow(gridDrug,0);//设置选中行
			}
		}, null,500,pwidth);
	}
	
	
	//删除特殊药品
	function doGrid2Del(index) {
		var row = gridDrug.getRow(index);
		document.getElementById("druglist").value = document.getElementById("druglist").value.replace(new RegExp(row.ake001 + ",", "g"), "");
		gridDrug.deleteRow(row);
		selectGridRow(gridDrug,index);//设置选中行
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
		} --%>
		var hosplist = document.getElementById("hosplist").value.substring(0);
		parent.popupDialogWithParam({
			url: "${rootPath}/pages/biz/medicare/special/hospitalSelect.jsp?checkbox=true&isDesignated=1&dto.aka130=18&akb020_not=" + hosplist.substring(0, hosplist.length -1) + "&dto.aaa027=" +$("#aaa027").val(),
			onClosed: function(){
				var ret = this.returnValue;
				if(ret){
					for(var i = 0 ; i < ret.length ; i++) {
						if(hosplist.toString().indexOf(ret[i].akb020 + ',') == -1){
							gridHosp.addRow(ret[i], gridHosp.getRow(0), true);
							hosplist += ret[i].akb020+",";
						}
						document.getElementById("hosplist").value =hosplist;		   	
					}
				}
				selectGridRow(gridHosp,0);//设置选中行
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
		selectGridRow(index,0);//设置选中行
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
		$("#aae127").val(getdateString());
		$("#aae030").val(getdateString());
		$("#aae031").val(getdateString().substring(0,4) + "1231");
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
	//************************************全键盘操作相关  begin*****************************************************
	/* 全键盘操作，包括enter：输入数据，自动跳到下一个输入框；
				按钮类：alt+s:保存，alt+q:取消
		【tabKeyConf】数组元素为需要键盘操作的元素，有顺序，元素是id:param1,param2,...;
		 1、关于元素配置规则id:param1,param2,param3:
		  param1=1表示：required=1。可以不设置 
			  eg：akb021:1     表示id=akb021,required=1;
			      如果元素是param1=1，那元素必须有值，按enter键，才会自动跳到下一个元素 ，按tab键可以直接到下一个元素;
		  param2:表示输入框类型,1:textfield;其中textfield必须设置，不然会有操作问题
		  	  eg：bke002:0,1   表示id=eg：bke002,required=0,为textfield类型;
		 2、其他说明：
		 queryString:表示人员选择id，暂时使用,由于是公共控件，目前不参与顺序，它在afterGetPerson事件中，自动将tabKey数组第一个元素获取焦点
	*/
	var tabKeyConf=["akb021:1","aae030:1","aae031:1","bke033",
	            	"aka121:1","bkb023:1","bke002:0,1","aae013:0,1"]; 

	//配置特殊的事件
	function configSpecialKey(keyid){
		var val=0;
		if ("akb021" == keyid) {//选择申请医院
			chooseHosp($("#akb020")[0], $("#akb021")[0]);
			val++;
		}
		if ("aka121" == keyid) {//选择申请疾病
			chooseDis(10,$("#aka120")[0], $("#aka121")[0]);
			val++;
		}
		if ("bkb023" == keyid) {//选择专家疾病
			chooseDis(10,$("#bke025")[0], $("#bkb023")[0]);
			val++;
		}
		return val;
	}
	
	//【tabKey】数组中所有元素绑定事件
  	function initTabKey(){
  		//输入框事件处理
  		var tabKey=dealConfigKey(tabKeyConf,0);
  		var tabkeyRequired=dealConfigKey(tabKeyConf,1);
  		var tabkeyType=dealConfigKey(tabKeyConf,2);
  		$(arrayToStr(tabKey,",#","")).each(function() {
  			$(this).keyup(function() {
  				dealKeyEvent(this,tabKey,tabkeyRequired,tabkeyType);
  			})
  		}); 
  		//窗口事件处理
  		/* $(document).keydown(function() {
  			dealWindowEvent();
  		}) */
  	}
  	
  	function dealWindowEvent(){
  		//alt+s:保存
		 if (window.event.keyCode=="83"&&window.event.altKey) {
			saveinfo();
			return false;
		} 
		//alt+R:取消
		 if (window.event.keyCode=="82"&&window.event.altKey) {
			closeThis();
			return false;
		}
		//alt+Q:清屏
		 if (window.event.keyCode=="81"&&window.event.altKey) {
			 resetData();
			return false;
		}
  	  }
	  
	//配置enter键
	function dealKeyEvent(para,tabKey,tabKeyRequired,tabKeyType) {
		//enter键，可以弹出框
		if (window.event.keyCode == "13") {
			//判断人员：
			if('' == $("#aac001").val()) {
				$("#queryString").val('');
			    $("#queryString").focus();
			     return false;
			}
			var filed_name = para.id;
			for(var i=0;i<tabKey.length;i++){
				if (tabKey[i] == filed_name) {
					if(!window.event.ctrlKey){//按ctrl与enter键可以选择下一个
						if(tabKeyType[i]=="1"){//textFiled不执行
							break;
						}
					}
					//当前输入框判断
					if("1"==tabKeyRequired[i]&&powersi.isnull($("#"+filed_name).val())){
						var dealnum=configSpecialKey(filed_name);
					}else{//下一个tabkey获取焦点
						var nextId=nextKeyId(tabKey,i);
						$("#"+tabKey[nextId]).focus();
						$("#"+tabKey[nextId]).val($("#"+tabKey[nextId]).val());//将光标移至最后
						$("#"+tabKey[nextId]).click();
					} 
					break;
				}
			}	
		}
	}
	//第一个元素获取焦点
	function firstTabFocus(){
		$("#"+dealConfigKey(tabKeyConf,0)[0]).focus();
	}
	//查找下一个需要获取焦点的key
	function nextKeyId(array,begin){
		for(var i=(begin+1);i<array.length;i++){
			if ($("#"+array[i]).length>0) {//判断元素是否存在
				return i;
			}
		}
		return begin;
	}
	//查找上一个需要获取焦点的key
	function lastKeyId(array,begin){
		for(var i=(begin-1);i>=0;i--){
			if ($("#"+array[i]).length>0) {//判断元素是否存在
				return i;
			}
		}
		return begin;
	}
	 //解析数组，oper：0：获取id，1：获取required
	function dealConfigKey(array,oper){
        var ts=[];
        var index=0;
	   	 for(var i=0;i<array.length;i++){
	   	   	var strvalue=converKey(array[i],oper);
	   	   	if(strvalue==""){//找不到id的处理
	   	   	
	   	   	 }else{
	   	   		ts[index]=strvalue;
	   	   		index++;
	   	   	  }
	   	 }
	   	 return ts;
     }

   //解析数组中的元素
   function converKey(key,oper){
  	   var value="";
  	   value= key.split(":")[0];
		if ($("#"+value).length>0) {//判断元素是否存在
	 		 if(oper==0){
	 	  	  	 
	 	  	 }else{
	 	  		 value= key.split(":")[1];
	 	  		 if(value==null||value==''){
	 	          	 value="0";
	 	         }
	 	         var ii=value.split(",");
	 	         value=ii[oper-1];
	 			 if(value==null||value==''){
	 		        value="0";
	 		     } 
	 	     }
		}else{
          return "";
		}
     return value;
   } 
	 //数组转字符串：
	 function arrayToStr(array,beforeStr,afterStr){
		if(array==null){
			return null;
		}
		var str="";
		for(var i=0;i<array.length;i++){
		 str+=beforeStr+array[i]+afterStr;
		}
		if(beforeStr!=""&&str!=""){
			str=str.substr(1);
		}
		if(afterStr!=""&&str!=""){
			str=str.substr(0,str.length-1);
		}
		return 	str;
	}
    //grid选择行，
	function selectGridRow(grid,index){
		var i=0;
		var objId=$(grid).attr("id");
		/* if("+1"==index){
			i=grid.getRowIndex(grid.getSelectedRow())+1;
		}else if("-1"==index){
			i=grid.getRowIndex(grid.getSelectedRow())-1;
		}else{
			i=index;
		}
		if(i<0){
			i=0;
		}
		if(i>(grid.getRowsCount()-1)){
			i=grid.getRowsCount()-1;
		}
		grid.select(i); */
		$("#"+objId).focus();
	}
	
	//************************************全键盘操作相关  end*****************************************************
	
	//***重置相关*****
	var initParam = {};
	var sel = $(".panelBox input[type=hidden],.tableEditor input[type=text],.tableEditor input[type=hidden],.tableEditor textarea,select,.select2-chosen");
	function initReset(){
		$.each(sel, function(i, n) {
			initParam[$(n).attr('id')] = $(n).is("div") || $(n).is("span") ? $(n).text() : $(n).val();
		});
	}
	function resetData(){
		//input相关
		$.each(sel, function(i, n) {
			var id = $(n).attr('id');
			if ($(n).attr('ligeruiid')) {
				liger.get(id).updateStyle();
			} else if ($(n).is("div") || $(n).is("span")) {
				$(n).text(initParam[id]);
			} else {
				$(n).val(initParam[id]);
			}
		});
		
		
		//grid相关
		gridDrug.reset();
		gridHosp.reset();
		//不能再下面输入数据
		changeShield(1);
		
	}
	
	
	/*查询参保信息*/
	function readIcCard() {findAac002("bka100", $("#querystring").val());} function findAac001() {
		if (window.event.keyCode == 13) {
			var querystring = powersi.trim($("#querystring").val());
			var argName = powersi.trim($("#argName").val());
			if (powersi.isnull(querystring)) {
				popupAlert("请输入有效查询条件!");
				return;
			}
			$("#querystring").val(querystring);
			$("#argName").val(argName);
			$("#querystring").prop("disabled", true);
			findAac002(argName,querystring);
			$("#querystring").prop("disabled", false);
			
		}
	}

	/*住院登记界面要支持：身份证号码、电脑号、社保卡号*/
	function findAac002(argName,argValue) {
		$("#aac001").val("");
		$("#aac002").val("");
		$("#aac003").val("");
		
		$("#"+argName).val(argValue);
		var param =$("#mainForm").serialize();
		$("#"+argName).val("");
		postJSON("${path}!getPersonInfo.action", param, function(json){
	    	if(!checkJSONResult(json,"popup")){
			    return;
		    } 
	    	if (json.data != "no" && json.data.length > 1) {
	    		chooseAac002(param);
				return;
			}
	    	/* 人员详细信息 */
	    	if (json.data != "no") {
	    	 	/* $.each(json.data[0], function(key, value) {
								if (!powersi.isnull(value)) {
									$("#" + key).val(value);
								}
				});  */
				var assiKey = getPageInitParamByApplyType('default', 'assiKey');
		    	for (var i = 0,len = assiKey.length; i < len; i ++) {
		    		if (!powersi.isnull(json.data[0][assiKey[i]])) {
		    			$("#" + assiKey[i]).val(json.data[0][assiKey[i]]);
					}
		    	} 
		    	changeShield(0);
				//全键盘操作
				firstTabFocus();
			}
	    });
	}

	/*查询并选择参保信息*/
	function chooseAac002(param) {
		 popupDialog(
				{
					url : "${path}!getChoosePersonPage.action?"+param,
					onClosed : function() {
						var retValue = this.returnValue;
						if (retValue) {
							findAac002("aac001",retValue.aac001);
						}
					}
				}, 500, 600); 
	}
</script>
</powersi:html>
<%@include file="../commonJsForSpecialApply.jsp"%> <!-- 引入特殊业务管理公共js方法 -->