<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	String path = request.getContextPath(); 

	String hospital_id = BizHelper.getAkb020();
	String hospital_name = BizHelper.getAkb021();
	String aaa027 = BizHelper.getAaa027();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();	
	
	/* request.setAttribute(
			"roleList",
			com.powersi.hygeia.framework.util.DBHelper
					.executeList("select * from sys_roles order by dis_order")); */
%>

<powersi:html>
<powersi:head title="数据表单" />
<head>
<script type="text/javascript"
	src="${rootPath}/resource/report/js/powerprint.min.js"></script>
<script type="text/javascript"
	src="${rootPath}/resource/report/js/LodopFuncs.js"></script>
<script src="${strutsPath}/include/multiselect.js" type="text/javascript"></script>	
<script type="text/javascript">
	$(function() {
		setSize();
		$(window).resize(setSize);
		
		$(".multiselect").multiselect();
	});
	
	function setSize() {
		try{
			//自适应宽度(因为可能出现纵向滚动条，导致宽度变小，所以需要先设置高度)
			$(".multiselect").height(170).width($("#divTabs").width());
	
			$(".multiselect").each(function() {
				//检查对象是否初始化
				if ($(this).is(":ui-multiselect")) {
					$(this).multiselect('resize');
				}
			});
		} catch(ex){}
	}
</script>
</head>
<body class="page">
    <div style="display: none;">
		<object id="cardControl"
			classid="clsid:962275D3-CB9F-4CF2-AC8A-33D2D8EFC5C5" width="0"
			height="0" border="0" onerror="popupAlert('社保卡控件初始化失败!')"> </object>
	</div>
	<powersi:head title="特定重大疾病自费项目补偿录入" />
	<powersi:form id="mainForm" namespace="/medicare" action="MzchoHospitalBusApplyAction!saveModiSpeciaRegister.action">
		<powersi:panelbox title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="button_save" key="保存登记" onclick="saveInfo()" ></powersi:button>
				<powersi:button id="btReset" label="重置"  onclick="clearInfo()"/>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
						<powersi:textfield id="tracestring" name="tracestring" label="查询条件"
						title="请输入信息回车" placeholder="请输入信息回车" readonly="false"
						onkeydown="queryPresonInfo(this)" buttonText="读卡" buttonId="readic_button"
						buttonDisabled="false" onbuttonclick="readic()" />
					<powersi:hidden id="querystring" name="mediSpecZHDto.querystring" />	
					<powersi:textfield  id="aac002" name="mediSpecZHDto.aac002" label="身份证号" readonly="true" required="true" />			
					<powersi:textfield id="aac001" name="mediSpecZHDto.aac001" label="电脑号" readonly="true" />
					<powersi:textfield id="aac003" name="mediSpecZHDto.aac003" label="姓名" readonly="true" />	
				</powersi:editorlayout-row>
				
			</powersi:editorlayout>
		</powersi:panelbox>

		<powersi:groupbox title="病种登记信息">
			<powersi:editorlayout cols="8">
			    <powersi:editorlayout-row>									
					<powersi:textfield label="出生日期" id="aac006" name="mediSpecZHDto.aac006" key="aac006" readonly="true" format="dateFmt:yyyy-MM-dd"/>							    			
					<powersi:textfield label="人员类别" id="bka004_name" name="mediSpecZHDto.bka004_name"   readonly="true" />
					<powersi:hidden id="bka004" name="mediSpecZHDto.bka004" />					
					<powersi:textfield label="所属单位" id="bka008" name="mediSpecZHDto.bka008" key="bka008" readonly="true"  />
					<powersi:textfield label="参保险种" id="aae140_name" name="mediSpecZHDto.aae140_name" key="aae140_name" readonly="true" />	
					<powersi:hidden id="aae140" name="mediSpecZHDto.aae140" />			
				</powersi:editorlayout-row>		
				<powersi:editorlayout-row>
					<powersi:textfield label="联系人" id="aae004" name="mediSpecZHDto.aae004"  readonly="true" />	
					<powersi:textfield label="手机号码" id="aae005" name="mediSpecZHDto.aae005" key="aae005"  required="true"/>
					<powersi:textfield id="aaa001" name="mediSpecZHDto.aaa001" label="医院专家"  readonly="true" buttonText="选     择" 
											onkeydown="keydown(this)" buttonId="aaa001_button" onbuttonclick="chooseDoctor('aaa001')" />
					<powersi:textfield id="aaa002" name="mediSpecZHDto.aaa002" label="医院专家2" readonly="true" buttonText="选     择"
					     onkeydown="keydown(this)"  buttonId="aaa002_button" onbuttonclick="chooseDoctor('aaa002')" />							
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
				    <powersi:codeselect id="aka120" label="病种名称" name="mediSpecZHDto.aka120"   onchange="getaka121(this)" 
				    list="#request.aka120Datas" listKey="aka120"  listValue="aka121" required="true" />	
					<powersi:hidden id="aka121" name="mediSpecZHDto.aka121" />		
					<powersi:textfield label="确诊时间" mask="date" format="dateFmt:'yyyy-MM-dd'" id="bka028" name="mediSpecZHDto.bka028" required="true"/>
					<powersi:codeselect id="aae016" name="mediSpecDto.aae016"  required="true"  label="审核标志" 
					    list="#{'1': '未审核', '2': '初审通过',3:'初审不通过',4:'复审通过',5:'复审不通过'}" disabled="true" />
					<td colspan="2"></td>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea label="鉴定标准" id="bka014" 
						key="bka013" name="mediSpecZHDto.bka013" required="false" readonly="true" colspan="7" onclick="getIdentify()" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea label="备注" id="aae013" name="mediSpecZHDto.aae013" required="false" colspan="7" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
				    <powersi:hidden id="aae011" name="mediSpecZHDto.aae011" />	
					<powersi:textfield label="操作人" id="bae100" name="mediSpecZHDto.bae100"  readonly="true" colspan="3" />	
					<powersi:textfield label="操作时间" id="aae036" name="mediSpecZHDto.aae036" key="aae036"  readonly="true" colspan="3" format="dateFmt:yyyy-MM-dd"/>							
				</powersi:editorlayout-row>
                 
                
			</powersi:editorlayout>
		</powersi:groupbox>
		<powersi:panelbox key="自费项目"  id="divTabs">
			<%-- <powersi:codeselect id="roles" cssClass="multiselect" multiple="true"
				name="roles" list="#request.roleList" listKey="role_id" height="150px"
				listValue="role_name" /> --%>
			 <powersi:codetable id="validList" codeType="valid_flag"></powersi:codetable>
			 <powersi:codetable id="ake001List" codeType="ake001"></powersi:codetable>
			 <powersi:datagrid id="detailGrid" checkbox="true" selectRowButtonOnly="true" height="190"
			          usePager="false" isMultiSelect="true" enabledEdit="true" >
					<powersi:toolbar>
						<powersi:toolbar-item id="add" text="新增" icon="add" click="itemClick" />
						<powersi:toolbar-item id="del" text="删除" icon="delete" click="itemClick" />
					</powersi:toolbar>
					<powersi:datagrid-column display="药品名称" name="ake002" width="30%" editor="{type: 'select', data:ake001List}" textField='ake002'  valueField='ake001'/>
					<powersi:datagrid-column display="药品编码" name="ake001" width="40%" minWidth="120" />
					<powersi:datagrid-column display="审核标志" name="aae016" width="80" render="renderAae016"/>
				</powersi:datagrid>
		</powersi:panelbox> 
		<powersi:hidden id="bke548" name="mediSpecZHDto.bke548" />
		<powersi:hidden id="bka013" name="mediSpecZHDto.bka013" />
		<powersi:hidden id="bizid" name="mediSpecZHDto.bizid" />	
		<powersi:hidden id="bka007" name="mediSpecZHDto.bka007" />	
		<powersi:hidden id="ace001" name="mediSpecZHDto.ace001" />	
		
	</powersi:form>
	<script type="text/javascript">
	window.onload = function(){		
		var myDate = new Date();
		var year = myDate.getFullYear();
		var month = (myDate.getMonth()+1).toString().length==1?"0"+(myDate.getMonth()+1).toString():(myDate.getMonth()+1).toString();
		var day = myDate.getDate().toString().length==1?"0"+myDate.getDate().toString():myDate.getDate().toString();
		var today= year+month+day;
		$("#aae036").val(today);	
		$('#akb020').val("<%=hospital_id%>");
		$('#akb021').val("<%=hospital_name%>");
		$('#bae100').val("<%=userName%>");
      	$('#aae011').val("<%=loginUser%>");
      	$("form").append("<div id='openDiv' style='display:none;'><div id='divGrid'></div><div class='space-y'></div></div>");
	}
	//获取病种数据源
	function getData(aka120){
		postJSON("${rootPath}/medicare/MzchoHospitalBusApplyAction!getAka120Datas.action",null,function(json){
			if (!checkJSONResult(json)) {
				return;
			}
			if(json.data){
				$("#bke003").html("");
				$.each(json.data,function(i,obj){
					var option =" <option value="+obj.aka120+">"+obj.aka121+"</option>";
					$("#bke003").append(option); 
				});
			}
			
		}); 
	}
	//选择病种的时候加载病种名称
	function getaka121(aka120){
		var aka121Hml=$("#aka120").find("option[value='"+$("#aka120").val()+"']").html();
		$("#aka121").val(aka121Hml);
	}
	
	function renderAae016(rowdata,index,value){
		if(value=="2"){
			return "初审通过";
		}else if(value=="3"){
			return "初审不通过";
		}else if(value=="4"){
			return "复审通过";
		}else if(value=="5"){
			return "复审不通过";
		}else{
			return "未审核";
		}
	}
	
	function itemClick(btn){
		if($("#aae016").val()=="4"){return;}
		var id = btn['id'];
		if(id == 'add'){
			detailGrid.addRow({aae100:"1",aae016:"1"}, detailGrid.getRow(0), true);
		} else if(id == 'del'){
			var row = detailGrid.getSelectedRows();
			var isDel=null;
			$.each(row,function(i,o){
				if(o.aae016=="4"){
					isDel="【"+(i+1)+"】行,自费项目复审已通过，不能删除！";
					return;
				}
			});
			if(isDel){
				popupAlert(isDel);
				return;
			}
			if(!row){
				popupAlert('请选择要删除的行');
			} else{
				if(confirm('您确认删除吗？')) {
					detailGrid.deleteSelectedRow();
				}
			}
		}
	}
	
	var objCard = null;
	/*加载控件*/
	function loadCardControl() {
		try {
			if (objCard == null || objCard.object == null) {
				objCard = document.getElementById("cardControl");
				if (objCard.object == null) {
					popupAlert("请先安装社保卡控件!");
				}
			}
		} catch (e) {
			popupAlert("请先安装社保卡控件!");
		}
	}

	/*读卡*/
	function iReadCardBase() {
		$("#bke548").val("");
		$("#tracestring").val("");
		loadCardControl();
		if (objCard.object != null) {
			var bke548 = null;// 读卡返回
			bke548 = objCard.ReadCardBase();
			$("#bke548").val(bke548);
		}
	}

	/*读卡获取后台信息*/
	function readic() {
		iReadCardBase();
		if (powersi.isnull($("#bke548").val())) {
			return;
		}
		var bizFormData = $("#mainForm").serialize();
		postJSON(
				"${rootPath}/inhospital/InhospitalManagerAction!readic.action",
				bizFormData, function(json) {
					if (!checkJSONResult(json)) {
						return;
					}
					if (json.data != "no") {
						if (!powersi.isnull(json.data.aac002)) {
							$("#tracestring").val(json.data.aac002);
							queryPresonInfo("readic");
						}
					}
				});
	}
	
	//查询业务申请人员基本信息
	   function queryPresonInfo(para){
		   if (para == "readic" || window.event.keyCode == 13) {
				var tracestring = powersi.trim($("#tracestring").val());
				if (powersi.isnull(tracestring)) {
					return;
				}
				$("#tracestring").attr("disabled", "disabled");
				findAac002(tracestring);
				$("#tracestring").removeAttr("disabled");
			}
		}
	  function findAac002(tracestring){		  
			var argValue = tracestring.substr(0);
		   //调用业务申请人员基本信息action
		   postJSON("${rootPath}/medicare/MzchoHospitalBusApplyAction!queryMediPersonInfo.action",
					{
						"mediSpecZHDto.querystring" : argValue
					}, function(json) {
						if (!checkJSONResult(json)) {
							return;
						}
						 if (json.errortype == 0) {
							$.each(json.data, function(key, value) {
								$("#" + key).val(value);
							});
							var aac002=$("#aac002").val();
							var aac001=$("#aac001").val();
						    postJSON("${rootPath}/medicare/MzchoHospitalBusApplyAction!getPersonCostComps.action",
							{"mediSpecZHDto.aac002":aac002,"mediSpecZHDto.aac001":aac001},function(json){
								if (!checkJSONResult(json)) {
									return;
								}
								if(json.data&&json.data.kc78Datas&&json.data.kc78Datas.length>0){ 
									openGrid(json.data);
								 }
							}); 
						    
						
						} else {
							popupInfo(json.message);
							return;
						} 	
						$("#button_save").attr("disabled",false);
						$("#button_cancel").attr("disabled",false);
					});
	}
	
   
	function openGrid(datas){
		
		  //初始化标签列表
	    $("#openDiv").show();
	    $("#divGrid").ligerGrid({
              columns: [
					{ display: 'ace001', name: 'ace001', width: '50%',hide:true},
					{ display: 'ace002', name: 'ace002', width: '50%',hide:true},
                    { display: '病种码值', name: 'aka120', width: '50%',hide:true},
                    { display: '病种名称', name: 'aka121', width: '200'},
	                { display: '确诊时间', name: 'bka028', width: '150'},
	                { display: '审核状态', name: 'aae016', width: '100' , render:renderAae016}
	                ], 
              height: 360,
              width: '100%',
              rowHeight: 28,
              data:datas.kc78Datas,
              headerRowHeight: 29,
              pageSizeOptions: [5, 10, 20],
              pageStatMessage: '显示 {from} - {to}，共 {total} 条',
              checkbox: true,
	          isMultiSelect:false,
              rownumbers: true,
              usePager:false,
              showReload: false,
              onSelectRow:function(data,rowid,rowdata){
            	  var kc79=[];
            	  $.each(data, function(key, value) {
						$("#" + key).val(value);
						if(key=="ace001"){
							$.each(datas.kc79All,function(i,o){
								if(value==o.ace001){
									kc79.push(o);
								}
							});
						}
						
						
				  });
            	  detailGrid.loadData(kc79);
            	  
          		 if(data.aae016=="4"){
        			$("#button_save").attr("disabled",true);//病种信息审核通过的话不能修改页面数据
        			$("input[id!='tracestring']").attr("disabled",true);
        			$("button").attr("disabled",true);
        			$("textarea").attr("disabled",true);
        			//detailGrid.isCheckable=false;
        		 }else{
        			 $("#button_save").attr("disabled",false);
         			$("input").attr("disabled",false);
         			$("button").attr("disabled",false);
         			$("textarea").attr("disabled",false);
        		 }
          		 dlg.close();
              }
         }); 
	     var dlg =popupDiv('#openDiv', '选择已申请的病种记录 ', 500, {
	  		showMax: true, 
	  		isHidden: false
	  	   }); 
	     
	}
	  
	//去除左右两边的空格
	function trim(str){
		return str.replace(/(^\s*)|(\s*$)/g, "");
	}

	
	/*选择医生*/
	function chooseDoctor(para) {
		popupDialog(
				{
					url : "${rootPath}/medicare/MzchoHospitalBusApplyAction!chooseDoctor.action",
					onClosed : function() {
						var retValue = this.returnValue;
						
						if (retValue) {
							$("#"+para).val(retValue.bkc275);
						} else {
							$("#"+para).val("");
						}
					}
				}, 500, 600);
		}
	
	
	
	/*保存信息*/
	function saveInfo(){
		var bka007= $("#bka006").find("option:selected").text();
		$("#bka007").val(bka007);
		var saveItemData = $("#mainForm").serialize();	
		if(!checkForm()){
			return;
		} 
		var aac002=$("#aac002").val();
		if(!aac002||aac002==""||aac002==null){
			popupAlert("请输入查询条件！");
			return;
		}
		var gridDatas=detailGrid.getAllData();
		if(!gridDatas||gridDatas.length<=0){
			popupAlert("请填写自费项目数据！");
			return;
		}
		var isvalid = true;
		$.each(gridDatas, function(n, row){
			if(powersi.isempty(row['ake002'])){
				isvalid = false;
				popupAlert('药品名称不能为空！');
				return;
			}
			if(powersi.isempty(row['ake001'])){
				isvalid = false;
				popupAlert('药品编码不能为空！');
				return;
			}	
			
		});
		if(!isvalid) return;
		
		var gridDelRow=detailGrid.getDeleted();
		postJSON("${rootPath}/medicare/MzchoHospitalBusApplyAction!saveDiseaseCostComps.action?gridDatas="+encodeURI(powersi.tostring(gridDatas))
				+"&gridDelRow="+encodeURI(powersi.tostring(gridDelRow)),saveItemData, afterSaveItem);
	}
	function afterSaveItem(json) {
		if (!checkJSONResult(json)) {
			return;
		} else {
			if(json.data){
				popupInfo(json.data);  
				$("#button_save").attr("disabled",true);
        	}else{
        		popupInfo(json.message);  
        	}
		    
		}
	}
	
	function clearInfo(){
		$("input").val("");
		$("textarea").val("");
		$("#detailGrid .l-grid-row").remove();
		//detailGrid.loadData([]);
		detailGrid.reset();
		$("#button_save").attr("disabled",false);
		window.onload();
		
	}
	
	/*获得鉴定标准内容*/
	function getIdentify(){
		var bka014=$("#bka014").val();
		if(bka014!=""){
			return;
		}
		var bin=$("#bka006").val();
		if(bin==null || bin =="undefined" || bin==""){
			return;
		}
		popupDialog(
				{
					url : "${rootPath}/medicare/MzchoHospitalBusApplyAction!getIdentify.action?mediSpecZHDto.bka006="+bin,
					onClosed : function() {
						var retValue = this.returnValue;
						$("#bka014").html(retValue.tem);
						$("#bka013").val(retValue.flag);						
					}
		}, 500, 600);
	}
	
</script>
</body>
</powersi:html>