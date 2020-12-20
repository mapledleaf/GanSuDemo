<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>

<%
	String path = request.getContextPath(); 

	String hospital_id = BizHelper.getAkb020();
	String hospital_name = BizHelper.getAkb021();
	String aaa027 = BizHelper.getAaa027() ;
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
	String aaz267value = request.getAttribute("mediSpecDto.aaz267")==null?null:request.getAttribute("mediSpecDto.aaz267").toString();
	String bke003 = request.getAttribute("mediSpecDto.bke003")==null?null:request.getAttribute("mediSpecDto.bke003").toString();
	String bke004 = request.getAttribute("mediSpecDto.bke004")==null?null:request.getAttribute("mediSpecDto.bke004").toString();
	String bke055 = request.getAttribute("mediSpecDto.bke055")==null?null:request.getAttribute("mediSpecDto.bke055").toString();
	String oldAkb020 = request.getAttribute("mediSpecDto.oldAkb020")==null?null:request.getAttribute("mediSpecDto.oldAkb020").toString();
%>


<powersi:html>
<body>
    <div style="display: none;">
		<object id="cardControl"
			classid="clsid:962275D3-CB9F-4CF2-AC8A-33D2D8EFC5C5" width="0"
			height="0" border="0" onerror="popupAlert('社保卡控件初始化失败!')"> </object>
	</div>
	<powersi:head title="门慢选点业务申请" />
	<powersi:form id="mainForm" namespace="/medicare"
		action="MtmmSpecialApplyAction!chooseHospSign.action">
		<div id="div1" style="display: none">
			<powersi:hidden id="aac084" name="mediSpecDto.aac084" />
			<%-- <powersi:hidden id="bka006" name="mediSpecDto.bka006" /> --%>
			<powersi:hidden id="aae140" name="mediSpecDto.aae140" />
			<powersi:hidden id="baa027" name="mediSpecDto.baa027" />
			<powersi:hidden id="aaz267" name="mediSpecDto.aaz267" />
			<powersi:hidden id="aae011" name="mediSpecDto.aae011" />
		</div>

		<powersi:panelbox title="人员信息" id="searchPanel">
			<powersi:panelbox-toolbar>
				
				<powersi:button id="but_save" key="保存" onclick="saveInfo()"></powersi:button>
				<powersi:button id="but_rest" cssClass="button" key="重置" onclick="clearall()"></powersi:button>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="6">
				<powersi:editorlayout-row cols="8">
					<powersi:textfield id="tracestring" name="tracestring" label="查询条件"
						title="请输入信息回车" placeholder="请输入信息回车" readonly="false"
						onkeydown="queryPresonInfo(this)" buttonText="读卡"
						buttonId="readic_button" buttonDisabled="false"
						onbuttonclick="readic()" />
					<powersi:textfield label="身份证号" id="aac002"
						name="mediSpecDto.aac002" key="aac002" readonly="true" />
					<powersi:textfield label="姓名" id="aac003" name="mediSpecDto.aac003"
						key="aac003" readonly="true" />
					<powersi:hidden id="aac004" name="mediSpecDto.aac004" readonly="true" />
					<powersi:textfield label="所属单位" id="aab069"
						name="mediSpecDto.aab069" key="aab069" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
				    <powersi:textfield id="aac001" label="电脑号" name="mediSpecDto.aac001" key="aac001" readonly="true"/>
					<powersi:textfield label="出生日期" format="dateFmt:yyyy-MM-dd"
						id="aac006" name="mediSpecDto.aac006" key="aac006" readonly="true" />
					<%-- <powersi:textfield label="工作日期" format="dateFmt:yyyy-MM-dd"
						id="aac007" name="mediSpecDto.aac007" key="aac007" readonly="true" /> --%>
					<%-- <powersi:textfield label="待遇类型 " id="bka006" name="mediSpecDto.bka006" disabled="true"  codeType="bka006" /> --%>
					<powersi:codeselect id="bka004" label="人员类别" name="mediSpecDto.bka004" codeType="bka004" disabled="true" />
					<td colspan='2'></td>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
        <powersi:groupbox title="选点信息" id="chooseHosp">
		  <powersi:editorlayout cols="6">
		      <powersi:editorlayout-row >
		            <powersi:hidden id="akb020" name="mediSpecDto.akb020" />
					<%-- <powersi:textfield id="akb020" name="mediSpecDto.akb020" key="医院编号"
						readonly="true" required="true" /> --%>
					<powersi:textfield id="akb021" name="mediSpecDto.akb021"
						key="申请医院名称" readonly="true" required="true" />
					<powersi:codeselect id="bke055" name="mediSpecDto.bke055"  required="true" 
					     label="选点变更原因" list="#{'': '','1': '新签约', '2': '年度变更',3:'新增病种变更',4:'单位/住址变更'}"  
					     disabled="true" onchange="funChange(this)" /> 
					<powersi:codeselect id="oldAkb020" name="mediSpecDto.oldAkb020" 
					label="终止签约医院机构" list="#{'': ''}"  disabled="true" /> 
			  </powersi:editorlayout-row>
				
			  <powersi:editorlayout-row >
			        <powersi:hidden id="bka500" name="mediSpecDto.bka500" />
			        <powersi:hidden id="bka501" name="mediSpecDto.bka501" key="疾病诊断" readonly="true" />
			        <powersi:hidden id="bke004" name="mediSpecDto.bke004" />
					<powersi:codeselect id="bke003" name="mediSpecDto.bke003" 
					label="疾病名称" list="#{'': ''}"  required="true" onchange="getBka500(this)" />
					<powersi:textfield label="开始日期" id="aae030"
						name="mediSpecDto.aae030" key="aae030" required="true" readonly="true" />
					<powersi:textfield label="结束日期" mask="date"
						format="dateFmt:'yyyy-MM-dd'" id="aae031" 
						name="mediSpecDto.aae031" key="aae031" required="true" />
					<td colspan='2'></td>
			  </powersi:editorlayout-row>
			  
			  <powersi:editorlayout-row>
				    <powersi:textarea label="备注" id="aae013" name="mediSpecDto.aae013"
						key="aae013" required="false" readonly="false" colspan="8" />
						<td colspan='1'></td>
				</powersi:editorlayout-row>
		  </powersi:editorlayout>
		</powersi:groupbox>
		
        <powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
		<powersi:hidden id="arg_name" name="arg_name" />
	</powersi:form>
	<script type="text/javascript">
	
	window.onload = function(){
		
		//门慢查下页面查看详情
		if($("#aac002").val()!=''&&$("#aac002").val()!=null&&$("#aac002").val()!='undefined'&&$("#aac002").val()!='null'){
			
	      	$('#aae011').val("<%=loginUser%>");
			$('#aaa027').val("<%=aaa027%>");
			$('#aaz267').val("<%=aaz267value%>");
			$("#arg_name").val("aac002");
			$("#tracestring").val($("#aac002").val());
			queryPresonInfo("readic");
			$("#arg_name").val("");
			$("#tracestring").parent().parent().hide();
			$("#tracestring").parent().parent().prev().hide();
			$("input").attr("disabled",true);
			$("textarea").attr("disabled",true);
			
			$("#but_save").hide();
			$("#but_rest").hide();
			var option =" <option value='<%=bke003%>' ><%=bke004%></option>";
			$("#bke003").append(option); 	
			$('#bke003').val("<%=bke003%>");
			$("#bke003").attr("disabled",true);
			$("#bke055").val("<%=bke055%>");
			//$("#c").attr("disabled",true);
			 
			var oldAkb020='<%=oldAkb020%>';
			var akb020='';
			var akb020Name='';
			if(oldAkb020){
				akb020=oldAkb020.split('@')[0];
				akb020Name=oldAkb020.split('@')[1];
			}
			
			
			if('<%=bke055%>'=='1'){
				$('#akb020').val("<%=hospital_id%>");
				$('#akb021').val("<%=hospital_name%>");
				$("#oldAkb020").hide();
				$("#oldAkb020").parent().prev().find("label").hide();
			}else {
				var option =" <option value='<%=hospital_id%>' ><%=hospital_name%></option>";
				$("#oldAkb020").append(option); 
				$('#oldAkb020').val("<%=hospital_id%>");
				$("#oldAkb020").attr("disabled",true);
				$('#akb020').val(akb020);
				$('#akb021').val(akb020Name);
				$("#oldAkb020").show();
				$("#oldAkb020").parent().prev().find("label").show();
				
				
			}	
			
			
		}else{
			$('#akb020').val("<%=hospital_id%>");
			$('#akb021').val("<%=hospital_name%>");
	      	$('#aae011').val("<%=loginUser%>");
			$('#aaa027').val("<%=aaa027%>");
			var myDate = new Date();
			var year = myDate.getFullYear();
			var month = (myDate.getMonth()+1).toString().length==1?"0"+(myDate.getMonth()+1).toString():(myDate.getMonth()+1).toString();
			var day = myDate.getDate().toString().length==1?"0"+myDate.getDate().toString():myDate.getDate().toString();
			var today= year+"-"+month+"-"+day;
			$("#aae030").val(today);
			$("#aae031").val("2099-12-31");
		}
		$("form").append("<div id='openDiv' style='display:none;'><div id='divGrid'></div><div class='space-y'></div></div>");
		
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
		$("#arg_name").val("");
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
							$("#arg_name").val("aac002");
							$("#tracestring").val(json.data.aac002);
							queryPresonInfo("readic");
							$("#arg_name").val("");
						}
					}
				});
	}

	//去除左右两边的空格
	function trim(str){
		return str.replace(/(^\s*)|(\s*$)/g, "");
	}

	
	function saveInfo(){
		var bke004=$("#bke003").find("option[value='"+$("#bke003").val()+"']").html();
		$("#bke004").val(bke004);
		var saveItemData = $("#mainForm").serialize();
		if($("#aac001").val() ==""){
			popupAlert("请先输入申请人信息查询!");
			return;
		}
		if(!checkForm()){
			return;
		}
		if(($("#bke055").val()=='2'||$("#bke055").val()=='3'||$("#bke055").val()=='4')
				&&($("#oldAkb020").val()==''||$("#oldAkb020").val()==null)){
			popupAlert("请选择终止签约医院机构!");
			return;
		}else if($("#bke055").val()=='1'){
			//由于新签约的时候bke055是disable=true，取不到值
			saveItemData=saveItemData+"&"+"mediSpecDto.bke055="+$("#bke055").val();
		}
		var bka500=$("#oldAkb020").find("option[value='"+$("#oldAkb020").val()+"']").attr("data");
		if($("#oldAkb020").val()&&(bka500=='2')){ 
			popupAlert("所选择的终止签约医院已申请月度变更记录,不允许再次变更!");
			return;
		}
		postJSON("<%=path%>/medicare/MtmmSpecialApplyAction!saveSpChooseHosp.action?"
				+"mediSpecDto.bka004="+$("#bka004").val(),
				saveItemData, afterSaveItem);
	}

	function afterSaveItem(json) {

		if (!checkJSONResult(json)) {
			return;
		} else {
			if(json.data){
				popupInfo(json.data);
				$("#but_save").attr("disabled",true);
				return;
			}
			popupInfo(json.message);
			$("#but_save").attr("disabled",true);
			
		}
	}
	
	function getBka500(bke003){
		var bka500=$("#bke003 option:selected").attr("data");
		if(bka500){
			var strArr=bka500.split("@");
			$("#bka500").val(strArr[0]);
			$("#bka501").val(strArr[1]);
		}
		
	}

	function clearall() {
		$("select").val("");
		$("input").val("");
		$("textarea").val("");
		
		window.onload();
		$("#but_save").attr("disabled",false);
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
		  var indi_id = 0;
	      var argValue = tracestring.substr(0);
		   //调用业务申请人员基本信息action
		   postJSON("<%=path%>/medicare/MtmmSpecialApplyAction!queryMediPersonInfo.action",
					{
						"mediSpecDto.arg_name" : indi_id,
						"mediSpecDto.arg_value" : argValue
					}, function(json) {
						if (!checkJSONResult(json)) {
							return;
						}
						if (json.data.personinfo.length >1) {//一个身份证对应多个电脑号，弹出选择窗口
							var personinfo = json.data.personinfo;
						    openGrid(personinfo);
							
						}else{
							$.each(json.data.personinfo[0],
									function(key, value) {
										if (!powersi.isnull(value)) {
											$("#" + key).val(value);
										}
									});
							if(json.data.personinfo[0].aab069 == null || json.data.personinfo[0].aab069 ==''){
								$("#aab069").val(json.data.personinfo[0].bka008);
							}else{
								$("#aab069").val(json.data.personinfo[0].aab069);
							}
							chooseHospInfo(json.data.personinfo[0]);
						}	
						
					});
			/** 
			 //启用门特(门慢)项目下拉列表
			 $('#aka083').attr('disabled',false);	
			 */
			 
			$("#but_save").attr("disabled", false);
			$("#but_rest").attr("disabled", false);
	}
	
	
	function openGrid(datas){
		
		  //初始化标签列表
	    $("#openDiv").show();
	    $("#divGrid").ligerGrid({
            columns: [
                    { display: '姓名', name: 'aac003', width: '60'},
                    { display: '电脑号', name: 'aac001', width: '80'},
	                { display: '社会保障号码', name: 'aac002', width: '100'},
	                { display: '单位编码', name: 'aab001', width: '100' },
	                { display: '单位名称', name: 'bka008', width: '100' }
	                ], 
            height: 360,
            width: '100%',
            rowHeight: 28,
            data:datas,
            headerRowHeight: 29,
            pageSizeOptions: [5, 10, 20],
            pageStatMessage: '显示 {from} - {to}，共 {total} 条',
            checkbox: true,
            isMultiSelect:false,
            rownumbers: true,
            usePager:false,
            showReload: false,
            onSelectRow:function(data,rowid,rowdata){
            	var indi_id=data['aac001'];
            	postJSON(
						rootPath
								+ "/medicare/MtmmSpecialApplyAction!queryMediPersonInfo.action?mediSpecDto.arg_name=indi_id&mediSpecDto.arg_value="
								+ indi_id,
						function(json) {
							if (!checkJSONResult(json)) {
								return;
							}
							
							$.each(json.data.personinfo[0],
									function(key, value) {
										if (!powersi.isnull(value)) {
											$("#" + key).val(value);
										}
							});
							
							if(json.data.personinfo[0].aab069 == null || json.data.personinfo[0].aab069 ==''){
								$("#aab069").val(json.data.personinfo[0].bka008);
							}else{
								$("#aab069").val(json.data.personinfo[0].aab069);
							}
							chooseHospInfo(json.data.personinfo[0]);
							
			  });
        	  dlg.close();
            }
       }); 
	    var dlg =popupDiv('#openDiv', '选择参保人信息 ', 500, {
	  		showMax: true, 
	  		isHidden: false
	  	}); 
	     
	}
	
	function funChange(radio){
		if($(radio).val()!=1){
			$("#oldAkb020").show();
			$("#oldAkb020").parent().prev().find("label").show();
		}else{
			$("#oldAkb020").hide();
			$("#oldAkb020").parent().prev().find("label").hide();
		}
	}
	
	//选点需要查询的信息
	function chooseHospInfo(personinfo){
		//门慢查下页面查看详情，就不需要加载下拉数据
		if($("#aaz267").val()!=''&&$("#aaz267").val()!=null&&$("#aaz267").val()!='undefined'&&$("#aaz267").val()!='null'){
          return;
		}
		var url="${rootPath}/medicare/MtmmSpecialApplyAction!chooseSignType.action?mediSpecDto.aac002="+personinfo.aac002;
		var personinfo=powersi.tostring(personinfo);
		postJSON(url, null, function(json){
			if (!checkJSONResult(json)) {
				return;
			}
			if(json.data){
				if(json.data.akb020List){
					$("#oldAkb020").html("");
					$("#oldAkb020").html("<option value=''/>");
					var count =0;
					var akb020=$("#akb020").val();
					$.each(json.data.akb020List,function(i,obj){
						var option =" <option value="+obj.akb020+" data='"+obj.bke055+"'>"+obj.akb021+"</option>";
						$("#oldAkb020").append(option); 
						if(obj.akb020!=akb020){count++;}
					});
					if(count<3){
						$("select[name='mediSpecDto.bke055']").val(1);
						$("#oldAkb020").hide();
						$("#oldAkb020").parent().prev().find("label").hide();
					}else {
						$("select[name='mediSpecDto.bke055").attr("disabled",false);
						$("select[name='mediSpecDto.bke055']").find("option[value='1']").remove();
						$("#oldAkb020").attr("disabled",false);
						$("#oldAkb020").show();
						$("#oldAkb020").parent().prev().find("label").show();
						
					}
					
				}
				if(json.data.aka121List){
					$("#bke003").html("<option value=''/>");
					$.each(json.data.aka121List,function(i,obj){
						var option =" <option value="+obj.value+" data='"+obj.bka500+"@"+obj.bka501+"'>"+obj.display+"</option>";
						$("#bke003").append(option); 
					});
				
					
				}
				
				return;
			}
			popupInfo(json.message);
		});
		
	} 
	</script>
</body>
</powersi:html>