<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>


<powersi:html>
<body>
    <div style="display: none;">
		<object id="cardControl"
			classid="clsid:962275D3-CB9F-4CF2-AC8A-33D2D8EFC5C5" width="0"
			height="0" border="0" onerror="popupAlert('社保卡控件初始化失败!')"> </object>
	</div>
	<powersi:head title="电子处方" />
	<powersi:form id="bizForm" namespace="/medicare"
		action="MtmmSpecialApplyAction!queryMediPersonInfo.action">
		<div id="div1" style="display: none">
			<powersi:hidden id="akb020" name="diagnoseInfoDTO.akb020" />
			<powersi:hidden id="akb021" name="diagnoseInfoDTO.akb021" />
			<powersi:hidden id="aae140" name="diagnoseInfoDTO.aae140" value="310" />
			<powersi:hidden id="aae030" name="diagnoseInfoDTO.aae030" />
			<powersi:hidden id="aka130" name="diagnoseInfoDTO.aka130" key="aka130" value="13" />
			<powersi:hidden id="aaz267" key="选点序列号" name="diagnoseInfoDTO.aaz267" />
		</div>

		<powersi:panelbox title="查询条件">
			<powersi:panelbox-toolbar>
			    <powersi:button cssClass="button" id="btn_return" key="撤销" onclick="returnElect()"></powersi:button>
				<powersi:button cssClass="button" id="btn_clear" key="重置" onclick="clearall()"></powersi:button>
				<powersi:button cssClass="button" id="btn_print" key="打印" onclick="printreport()"></powersi:button>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="querystring" name="querystring" label="查询条件"
						title="请输入信息回车" placeholder="请输入信息回车" readonly="false"
						onkeydown="queryPresonInfo(this)" buttonText="读卡"
						buttonId="readic_button" buttonDisabled="false"
						onbuttonclick="readic()" />
					<powersi:textfield id="ake1id" label="电子处方流水号" name="kad5DTO.ake1id" key="ake1id" readonly="true" />
					<powersi:textfield id="aac001" label="电脑号" name="diagnoseInfoDTO.aac001" key="aac001" readonly="true"/>
					<powersi:textfield label="姓名" id="aac003" name="diagnoseInfoDTO.aac003"
						key="aac003" readonly="true" />
					
				
					<powersi:textfield label="身份证号" id="aac002"
						name="diagnoseInfoDTO.aac002" key="aac002" readonly="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>

		<powersi:groupbox title="人员信息">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
				    <powersi:hidden id="aac005" name="diagnoseInfoDTO.aac005" />
					<powersi:codeselect id="aac004" name="diagnoseInfoDTO.aac004"
							label="性别"  codeType="aac004" disabled="true" />
					<powersi:codeselect id="bka004" key="人员类别" name="diagnoseInfoDTO.bka004" codeType="bka004" disabled="true" />
					<powersi:textfield label="出生日期" format="dateFmt:yyyy-MM-dd"
						id="aac006" name="diagnoseInfoDTO.aac006" key="aac006" readonly="true" />
					<%--<powersi:textfield label="工作日期" format="dateFmt:yyyy-MM-dd"
						id="aac007" name="diagnoseInfoDTO.aac007" key="aac007" readonly="true" /> --%>
					 <powersi:textfield label="单位名称" id="bka008"
						name="diagnoseInfoDTO.bka008" key="aab069" readonly="true" />
					
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
				    <powersi:codeselect id="baa027" key="人员所属统筹区" name="diagnoseInfoDTO.baa027" codeType="aaa027" disabled="true" />
					<powersi:textfield id="bka026_name" key="疾病诊断" name="bka026_name" disabled="true"/>
					<powersi:textfield id="bka025" key="挂号" name="diagnoseInfoDTO.bka025" disabled="true"/>
				    <powersi:textfield id="bka043" key="医院处方号" name="diagnoseInfoDTO.bka043" disabled="true"/>
					<powersi:hidden id="bka006" name="diagnoseInfoDTO.bka006" />
					<powersi:hidden id="bka006_name" name="bka006_name" />
				    <powersi:hidden id="bka019" name="diagnoseInfoDTO.bka019" />
				    <powersi:hidden id="bka021" name="diagnoseInfoDTO.bka021" />
				    <powersi:hidden id="bka503" name="diagnoseInfoDTO.bka503" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:groupbox>
        <powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
		<powersi:hidden id="arg_name" name="arg_name" />
		<powersi:hidden id="bka017" name="diagnoseInfoDTO.bka017" />
		<powersi:hidden id="personData" name="personData" />
	</powersi:form>
	<powersi:panelbox title="电子处方信息">
		<powersi:tabbedpanel id="divTabs" >
		<powersi:tab id="tab1" target="paneltab1" label="电子处方列表" />
		<powersi:tab id="tab2" target="paneltab2" label="处方明细列表" />
		<div id="paneltab1">
		<powersi:datagrid id="ake1Grid" checkbox="true"  onDblClickRow="dbSelectRow" usePager="false"
					delayLoad="false" enabledEdit="true" enabledSort="false" showReload="false" >
					<powersi:datagrid-column name="bka006" display=""  width="50" hide="true"/> 
				    <powersi:datagrid-column name="bka026" display=""  width="50" hide="true"/> 
					<powersi:datagrid-column name="ake2"    hide="true"/>
					<powersi:datagrid-column name="ake1id"  display="电子处方流水号" width="110"/>
					<powersi:datagrid-column name="akb020" display="医院编号" width="110" />
					<%-- <powersi:datagrid-column name="aac001" display="电脑号" width="100" /> --%>
					<powersi:datagrid-column name="aaz216" display="特殊业务申请号"  width="120" />
					 <powersi:datagrid-column name="bka006_name" display="待遇类别"  width="100" /> 
					 <powersi:datagrid-column name="bka026_name" display="入院诊断"  width="140" /> 
					<powersi:datagrid-column name="bka025" display="医院业务号"  width="70" />
					<powersi:datagrid-column name="bka043" display="医院处方号"  width="120" />
					<powersi:datagrid-column name="bka020" display="科室"  width="100" />
					<powersi:datagrid-column name="bka022" display="病区"  width="100" />
					<powersi:datagrid-column name="bka504" display="医保医师"  width="100" />
					<powersi:datagrid-column name="bka063" display="录入人工号"  width="60" />
					<powersi:datagrid-column name="bka065" display="录入时间"  width="120" />
					<powersi:datagrid-column name="aae030" display="生效日期" width="120" format="datefmt:'yyyy-mm-dd'"/>
					<powersi:datagrid-column name="aae031" display="失效日期" width="120" format="datefmt:'yyyy-mm-dd'"/>
		 </powersi:datagrid> 
		
		</div>
		<div id="paneltab2">
		   <powersi:datagrid id="ake2Grid" enabledExportExcel="true" usePager="false">
			        <powersi:datagrid-column name="ake2id" hide="true" />
			        <powersi:datagrid-column name="ake001" display="基准库编码" width="80" />
					<powersi:datagrid-column name="bkm022" display="药品名称" width="150" />
					<powersi:datagrid-column name="aka070" display="剂型" width="70" />
					<powersi:datagrid-column name="bkc014" display="厂家" width="150" />
					<powersi:datagrid-column name="bkc103" display="规格" width="80" />
					<powersi:datagrid-column name="bkm012" display="计量单位" width="60" />
					<powersi:datagrid-column name="bka056" display="单价" width="60" />
					<powersi:datagrid-column name="bka057" display="用量"  width="80" />
					<powersi:datagrid-column name="bka058" display="金额" width="80" />
					<powersi:datagrid-column name="bka065" display="录入日期" width="100" format="datefmt:'yyyy-mm-dd'"/>
					<powersi:datagrid-column name="ake004" display="使用频率" width="150" />
					<powersi:datagrid-column name="ake005" display="使用途径" width="150" />
		   </powersi:datagrid> 
	
		</div>
		</powersi:tabbedpanel>
    </powersi:panelbox>
	<script type="text/javascript">
	window.onload=function() {
      if($("#aac001").val()!=''&&$("#aac001").val()!=null&&$("#aac001").val()!='undefined'&&$("#aac001").val()!='null'){
			$("#querystring").val($("#aac001").val());
			queryPresonInfo("readic");
			$("#arg_name").val("");
			$("#querystring").parent().parent().hide();
			$("#querystring").parent().parent().prev().hide();
			$("input").attr("disabled",true);
			$("textarea").attr("disabled",true);
			$("#ake1id").show();
			$("#btn_return").hide();
			$("#btn_clear").hide();
			$("#btn_print").show();
			dbSelectRow({ake1id:$("#ake1id").val()},null,null);
			$("#ake1Grid").hide();
			$("#paneltab1").hide();
			$("#tab1").hide();
			$("#tab2").hide();
			
			
		}else{
			var curDate = new Date();
			var month = curDate.getMonth() + 1 < 10 ? "0" + (curDate.getMonth() + 1)
					: curDate.getMonth() + 1 ;
			var day = curDate.getDate() < 10 ? "0" + (curDate.getDate()) : curDate
					.getDate();
			var hours = curDate.getHours() < 10 ? "0" + (curDate.getHours())
					: curDate.getHours();
			var min = curDate.getMinutes() < 10 ? "0" + (curDate.getMinutes())
					: curDate.getMinutes();
			var seconds = curDate.getSeconds() < 10 ? "0" + (curDate.getSeconds())
					: curDate.getSeconds();
			var curDateStr = curDate.getFullYear() +"-"+ month +"-"+ day +" "+ hours +":" + min +":" + seconds;
			$("#bka017").val(curDateStr);  
			$("#ake1id").parent().hide();
			$("#ake1id").parent().prev().hide();
			$("#btn_print").hide();
			$("#bka025").hide();
			$("#bka043").hide();
			$("#bka026_name").hide();
			$("#bka025").parent().prev().find("span").hide();
			$("#bka043").parent().prev().find("span").hide();
			$("#bka026_name").parent().prev().find("span").hide();
		}
		
	};
	
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
		$("#querystring").val("");
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
		var bizFormData = $("#bizForm").serialize();
		postJSON(
				"${rootPath}/inhospital/InhospitalManagerAction!readic.action",
				bizFormData, function(json) {
					if (!checkJSONResult(json)) {
						return;
					}
					if (json.data != "no") {
						if (!powersi.isnull(json.data.bka100)) {
							$("#arg_name").val("bka100");
							$("#querystring").val(json.data.bka100);
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


	function clearall() {
		$(":text").val("");
		$("select").val("");
		ake1Grid.reset();
		ake2Grid.reset();
	}
	
	//查询业务申请人员基本信息
    function queryPresonInfo(para){
    	if (para == "readic" || event.keyCode == '13') {
			var querystring = powersi.trim($("#querystring").val());
			if (powersi.isnull(querystring)) {
				popupAlert("请输入有效查询条件");
				return;
			}
			if(para == "readic"){
				
			}else{
				$("#bke548").val('');
			}
			var indi_id = "0";
			postJSON(
					"${rootPath}/diagnose/GetPersonInfoAction!getPersonSpInfo.action?diagnoseInfoDTO.arg_name="
							+ $("#arg_name").val()
							+ "&diagnoseInfoDTO.arg_value="
							+ $("#querystring").val()
							+ "&diagnoseInfoDTO.bka006=131"
							+ "&diagnoseInfoDTO.bka017="
							+ $("#bka017").val()
							+ "&diagnoseInfoDTO.aka130="
							+ $("#aka130").val()
							+ "&diagnoseInfoDTO.aae140="
							+ $("#aae140").val()
							+ "&diagnoseInfoDTO.bke548="
							+ $("#bke548").val(),
					function(json) {
						$("#bke548").val('');
						if (!checkJSONResult(json)) {
							return;
						}
						if (json.data.personinfo.length > 1) {
							choosepersonlist(querystring);
						} else {
							$.each(json.data.personinfo[0], function(key,
									value) {
								if (!powersi.isnull(value)) {
									$("#" + key).val(value);
								}
								personinfo=json.data.personinfo[0];
							});
							
							$("#aae140").val(json.data.factorinfo[0].aae140);
							$("[name='diagnoseInfoDTO.baa027']").val(json.data.personinfo[0].baa027);
							$("[name='diagnoseInfoDTO.bka004']").val(json.data.personinfo[0].bka004);
							
							 if($("#ake1id").val()){
								
								 $.each(json.data.spinfo,function(i,o){
									 if(o.aaz267==$("#aaz267").val()){
										 $("#bka026").val(json.data.spinfo[0].bka026);
											$("#bka026_name").val(json.data.spinfo[0].aka121);
											$("#bka006").val(json.data.spinfo[0].bka006);
											$("#bka006_name").val(json.data.spinfo[0].bka155);
											
											json.data.personinfo[0].bka026=json.data.spinfo[0].bka026;
											json.data.personinfo[0].bka026_name=json.data.spinfo[0].aka121;
											json.data.personinfo[0].bka006=json.data.spinfo[0].bka006;
											json.data.personinfo[0].bka006_name=json.data.spinfo[0].bka155;
									 }
								 });
								 $("#personData").val(powersi.tostring(json.data.personinfo[0]));
								 return ;
							 }
							if (!powersi.isnull($("#aac001").val())) {
								if (json.data.spinfo&&json.data.spinfo.length > 1) {
									var personinfo = powersi.tostring(json.data.spinfo);
									popupDialog(
											{
												url : "${rootPath}/pages/biz/medicare/diagnose/chooseTreatmentInfo.jsp?personinfo="
														+ encodeURI(encodeURI(personinfo)),
												onClosed : function() {
													var ret = this.returnValue;
													if (ret != null) {
														$("#aaz267").val(ret.aaz267);
														$("#bka026_name").val(ret.aka121);
														$("#bka006").val(ret.bka006);
														$("#bka006_name").val(ret.bka155);
														$("#bka026").val(ret.bka026);
														queryElectronicPres($("#aaz267").val(),$("#aac001").val());
													} else {
														$("#aac001").val('');
														$("#aac003").val('');
														$("#aac002").val('');
														$("#aac006").val('');
														$("#bka008").val('');
														$("#aab001").val('');
														$("#aac004").val('');
														$("#baa027").val('');
														$("#bka004").val('');
													}
												}
											}, 500, 800);
								} else if(json.data.spinfo&&json.data.spinfo.length==1){
									$("#aaz267").val(json.data.spinfo[0].aaz267);
									$("#bka026_name").val(json.data.spinfo[0].aka121);
									$("#bka006").val(json.data.spinfo[0].bka006);
									$("#bka006_name").val(json.data.spinfo[0].bka155);
									$("#bka026").val(json.data.spinfo[0].bka026);
									$("#bka025").focus();
									queryElectronicPres($("#aaz267").val(),$("#aac001").val());
								}else{
									popupAlert("参保人未申请门特门慢业务.");
								}
							}
						}						
						if(json.data.msg!=null){
					 		popupAlert("参保人基金已冻结，原因是："+json.data.msg);
					 	}
					});
		}
	}
    function choosepersonlist(querystring){
		popupDialog(
				{
					url : "${rootPath}/diagnose/GetPersonInfoAction!chooseperson.action?diagnoseInfoDTO.arg_value="
						+ querystring,
					onClosed : function() {
						var ret = this.returnValue;
						if (ret != null) {
							indi_id = ret.aac001;
							postJSON(
									"${rootPath}/diagnose/GetPersonInfoAction!getPersonInfo.action?diagnoseInfoDTO.arg_name=indi_id&diagnoseInfoDTO.arg_value="
											+ indi_id
											+ "&diagnoseInfoDTO.bka006=131"
											+ "&diagnoseInfoDTO.bka017="
											+ $("#bka017").val()
											+ "&diagnoseInfoDTO.aka130="
											+ $("#aka130").val()
											+ "&diagnoseInfoDTO.aae140="
											+ $("#aae140").val(),
									function(json) {
										if (!checkJSONResult(json)) {
											return;
										}
										$.each(json.data.personinfo[0], function(key,value) {
											if (!powersi.isnull(value)) {
												$("#" + key).val(value);
											}
											
										});
										$("#aae140").val(json.data.factorinfo[0].aae140);
										$("[name='diagnoseInfoDTO.baa027']").val(json.data.personinfo[0].baa027);
										if(json.data.spinfo[0]){
											$("#aaz267").val(json.data.spinfo[0].serial_apply);
										}
										$("[name='diagnoseInfoDTO.bka004']").val(json.data.personinfo[0].bka004);
										if($("#ake1id").val()){
											 $.each(json.data.spinfo,function(i,o){
												 if(o.aaz267==$("#aaz267").val()){
													$("#bka026").val(json.data.spinfo[0].bka026);
													$("#bka026_name").val(json.data.spinfo[0].aka121);
													$("#bka006").val(json.data.spinfo[0].bka006);
													$("#bka006_name").val(json.data.spinfo[0].bka155);
													
													json.data.personinfo[0].bka026=json.data.spinfo[0].bka026;
													json.data.personinfo[0].bka026_name=json.data.spinfo[0].aka121;
													json.data.personinfo[0].bka006=json.data.spinfo[0].bka006;
													json.data.personinfo[0].bka006_name=json.data.spinfo[0].bka155;
													
												 }
											 });
											 $("#personData").val(powersi.tostring(json.data.personinfo[0]));
											 return ;
										 }
										if (!powersi.isnull($("#aac001").val())) {
											if (json.data.spinfo&&json.data.spinfo.length > 1) {
												var personinfo = powersi.tostring(json.data.spinfo);
												popupDialog(
														{
															url : "${rootPath}/pages/biz/medicare/diagnose/chooseTreatmentInfo.jsp?personinfo="
																	+ encodeURI(encodeURI(personinfo)),
															onClosed : function() {
																var ret = this.returnValue;
																if (ret != null) {
																	$("#aaz267").val(ret.aaz267);
																	$("#bka026_name").val(ret.aka121);
																	$("#bka006").val(ret.bka006);
																	$("#bka006_name").val(ret.bka155);
																	$("#bka026").val(ret.bka026);
																	$("#bka025").focus();
																	queryElectronicPres($("#aaz267").val(),$("#aac001").val());
																} else {
																	$("#aac001").val('');
																	$("#aac003").val('');
																	$("#aac002").val('');
																	$("#aac006").val('');
																	$("#bka008").val('');
																	$("#aab001").val('');
																	$("#aac004").val('');
																	$("#baa027").val('');
																	$("#bka004").val('');
																}
															}
														}, 500, 800);
											} else if(json.data.spinfo&&json.data.spinfo.length==1) {
												if(json.data.spinfo[0]){
													$("#aaz267").val(json.data.spinfo[0].aaz267);
												}
												$("#bka026_name").val(json.data.spinfo[0].aka121);
												$("#bka006").val(json.data.spinfo[0].bka006);
												$("#bka006_name").val(json.data.spinfo[0].bka155);
												$("#bka026").val(json.data.spinfo[0].bka026);
												
												queryElectronicPres($("#aaz267").val(),$("#aac001").val());
											}else{
												popupAlert("参保人未申请门特门慢业务!");
											}
										}
										if(json.data.msg!=null){
									 		popupAlert("参保人基金已冻结，原因是："+json.data.msg);
									 	} 
									});
						}
					}
				}, 500, 600);
	
	}

    function queryElectronicPres(aaz276,aac001){
    	if(!aaz276&&!aac001){alert("请检查申请号与参保人电脑号是否为空!"); return; }
    	selectTab('#divTabs', 0);
    	var ake1id=$("#ake1id").val();
    	postJSON("${rootPath}/medicare/HospElectpresAction!electPresQuery.action",
			{"kad5DTO.aaz216":aaz276,"kad5DTO.aac001":aac001,"kad5DTO.ake1id":ake1id},
			function(json){
				
				if (!checkJSONResult(json)) {
					return;
				}
				ake1Grid.loadData(json.data.kad5List);
    		
    	});
    }
    /*撤销操作*/
    function returnElect(){
		var datas = ake1Grid.getSelectedRows();
		if(powersi.isempty(datas)){
			popupAlert("请选择一行记录撤销！");
			return;
		}
		var url="${rootPath}/medicare/HospElectpresAction!returnElect.action";
		var ake1Datas=powersi.tostring(datas);
		postJSON(url, {ake1Datas:ake1Datas}, function(json){
			if (!checkJSONResult(json)) {
				return;
			}
			if(json.data){
				popupInfo(json.data);
				ake1Grid.deleteSelectedRow();
				return;
			}
			popupInfo(json.message);
		});
    }
    
    function printreport(){
        var saveItemData = $("#bizForm").serialize();
        var electDetailData= powersi.tostring(ake2Grid.getData());
        var personData=$("#personData").val();
    	 popupDialog(
		{
			url : "${rootPath}/medicare/HospElectpresAction!setElectronicReport.action?kad5DTO.ake1id="+$("#ake1id").val()
			        +"&diagnoseInfoDTO.bka019="+$("#bka019").val()
			        +"&diagnoseInfoDTO.bka025="+$("#bka025").val()
					+"&diagnoseInfoDTO.bka026="+$("#bka026_name").val()
					+"&diagnoseInfoDTO.bka006="+$("#bka006_name").val()
					+"&diagnoseInfoDTO.bka043="+$("#bka043").val()
			        +"&personData="+personData
					+"&electDetailData="+encodeURI(encodeURI(electDetailData)),
			onClosed : function() {

			}
		}, 600, 800); 
    }
    
    
    
    function dbSelectRow(rowdata, rowid, rowobj){
    	selectTab('#divTabs', 1);
    	postJSON("${rootPath}/medicare/HospElectpresAction!electPresDetailQuery.action",
    			{"kad5DTO.ake1id":rowdata.ake1id},
    			function(json){
    				if (!checkJSONResult(json)) {
    					return;
    				}
    				ake2Grid.loadData(json.data.detailList);
    				
        		
        });
    }
</script>
</body>
</powersi:html>