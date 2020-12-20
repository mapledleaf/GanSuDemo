<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();

	String hospital_id = BizHelper.getAkb020();
	String hospital_name = BizHelper.getAkb021();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
	String aaa027 = BizHelper.getAaa027();
	String aaa027Name = BizHelper.getAaa027Name();
%>
<powersi:html>
<head>
<powersi:head title="异地就医申请" />
</head>
<body>
	<powersi:form id="mainForm">
		<powersi:panelbox key="panel_result" title="个人基本资料">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:hidden id="querystring" />
					<powersi:hidden id="aac001" name="outBizDTO.aac001"/>
					<powersi:hidden id="baa027" name="outBizDTO.baa027"/>
					<powersi:hidden id="akb020" name="outBizDTO.akb020"/>
					<powersi:hidden id="bke084" name="outBizDTO.bke084" />
					<powersi:hidden id="bke085" name="outBizDTO.bke085" />
					<powersi:hidden id="bke086" name="outBizDTO.bke086" />
					<powersi:hidden id="aae100" name="outBizDTO.aae100" value="1" />
					<powersi:hidden id="bke253" name="outBizDTO.bke253" value="1" />
					<powersi:hidden id="aac004" name="outBizDTO.aac004" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="tracestring" name="tracestring" label="查询条件"
						title="请输入信息回车" placeholder="请输入信息回车" readonly="false"
						onkeydown="findAac001()" buttonDisabled="false"/>

					<powersi:textfield label="姓名" id="aac003" name="outBizDTO.aac003"
						key="aac003" readonly="true" />
					<powersi:textfield label="社会保障号" id="aac002" name="outBizDTO.aac002"
						key="aac002" readonly="true" />
					<powersi:textfield label="性别" id="aac004_name" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield label="出生日期" id="aac006" name="outBizDTO.aac006"
						key="aac006" readonly="true" />
					<powersi:textfield label="参加工作日期" id="aac007"
						name="outBizDTO.aac007" key="aac007" mask="date"
						format="dateFmt:'yyyy-MM-dd'" readonly="true" />
					<powersi:textfield label="单位管理码" id="aab001"
						name="outBizDTO.aab001" key="aab001" readonly="true" />
					<powersi:textfield label="单位名称" id="aab069" name="outBizDTO.aab069"
						key="aab069" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield name="outBizDTO.aac066" id="aac066"
						readonly="true" label="人员类别" />
					<powersi:codeselect name="outBizDTO.aac066_t" id="aac066_t"
						list="#{'0': '请选择…','1': '驻外施工', '2':'海员','3':'其他'}"
						onchange="onchangeaac066_t(this.value)" label="特殊人员类别" />
					<powersi:textfield name="outBizDTO.aac066_o" id="aac066_o"
						readonly="true" label="其他人员类别" />
					<td colspan="2"></td>
				</powersi:editorlayout-row>

			</powersi:editorlayout>
		</powersi:panelbox>

		<powersi:panelbox key="panel_result" title="异地申请信息"
			allowCollapse="false">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:hidden name="outBizDTO.kch800" id="kch800" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:hidden label="单位名称" name="outBizDTO.aab004" id="paab004" />
					<powersi:hidden label="统筹区" name="outBizDTO.aaa027" id="aaa027" />
				</powersi:editorlayout-row>

				<powersi:editorlayout-row>
					<powersi:textfield name="outBizDTO.bke088" id="bke088" required="false" 
							label="异地备案号" readonly="true" />
					<powersi:codeselect name="outBizDTO.bke083" id="bke083"
						codeType="bke083" disabled="true" label="处理状态" />

					<powersi:hidden name="outBizDTO.bke087" id="bke087" />
					<powersi:textfield name="aaa027Name" id="aaa027Name" label="受理部门"
						readonly="true" />
					<powersi:textfield name="outBizDTO.bke077" id="bke077"
						required="true" label="与联系人关系" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield name="outBizDTO.bke076" id="bke076"
						required="true" label="联系人" />
					<powersi:textfield name="outBizDTO.bke069" id="bke069"
						required="true" label="联系电话" validate="minSize[7]" maxlength="12"></powersi:textfield>
					<powersi:textfield name="outBizDTO.bke606" id="bke606"
						required="true" colspan="5" label="联系地址"></powersi:textfield>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield name="outBizDTO.akc030" id="akc030"
						colspan="7" label="申请原因"></powersi:textfield>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>

		<powersi:panelbox key="panel_result" title="邮寄信息"
			allowCollapse="false">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:codeselect name="outBizDTO.bke075" id="bke075"
						codeType="bke075" required="false" label="邮寄标志"
						onchange="setRequire()" />
					<td colspan="6" style="width: 100%"></td>
				</powersi:editorlayout-row>
				<tr id="trDisplay1" style="display: none">
					<powersi:codeselect name="outBizDTO.bke078" id="bke078"
						required="false" codeType="bke078" label="邮寄方式" />
					<powersi:textfield name="outBizDTO.bke073" id="bke073"
						required="false" label="邮政帐户" />
					<powersi:textfield name="outBizDTO.bke100" id="bke100"
						required="false" label="邮寄收款人" />
					<powersi:textfield name="outBizDTO.bke070" id="bke070"
						required="false" label="开户局邮政编码" validate="zipcode" maxlength="6" />
				</tr>
				<tr id="trDisplay2" style="display: none">
					<powersi:textfield name="outBizDTO.bke071" id="bke071"
						required="false" label="开户局邮政储蓄所名" />
					<powersi:textfield name="outBizDTO.aae007" id="aae007"
						required="false" label="邮政编码" validate="zipcode" maxlength="6"/>
					<powersi:textfield name="outBizDTO.bke072" id="bke072"
						required="false" colspan="6" label="邮寄详细地址"/>
				</tr>
			</powersi:editorlayout>
		</powersi:panelbox>

		<powersi:panelbox key="panel_result" title="异地就医信息"
			allowCollapse="false">
			
			<powersi:editorlayout cols="8" id="infoperson">
				<tr>
					<powersi:textfield name="outBizDTO.yzz014" id="yzz014"
						label="省内异地就医申请号" readonly="true"></powersi:textfield>
					<powersi:codeselect name="outBizDTO.bka003" id="bka003"
						codeType="bka003_kc45" required="false" label="异地就医待遇类型" />
					<powersi:codeselect name="outBizDTO.yzz013" id="yzz013"
						codeType="yzz013" label="省内异地就医类别" />
					<powersi:codeselect label="医疗保险类型" key="aae140" codeType="aae140"
						codeFilter="aae140 in ('31A','310','391')" id="aae140"
						name="outBizDTO.aae140" required="true" />
				</tr>
				<tr>
					<powersi:textfield name="outBizDTO.bke098" id="bke098"
						required="true" label="开始时间" mask="date" />
					<powersi:textfield name="outBizDTO.bke099" id="bke099" label="结束时间"
						mask="date" />
					<powersi:codeselect name="outBizDTO.bke095" id="bke095"
						codeType="bke095" required="true" label="异地就医类型"
						codeFilter="data_value in ('1','2','3')" />
					<powersi:textfield name="outBizDTO.bke096" id="bke096"
						required="false" label="有效期内可记账次数" />
				</tr>
				<tr>
					<powersi:codeselect name="outBizDTO.bke074" id="bke074"
						codeType="bke074" label="异地类型"
						codeFilter="data_value in ('1','4')" />
				</tr>
			</powersi:editorlayout>
		</powersi:panelbox>

		<powersi:panelbox key="panel_result" title="异地申请安置地信息"
			allowCollapse="false">
			<powersi:editorlayout cols="8" id="infoperson">
				<tr>
					<powersi:codeselect label="所属省、自治区或直辖市" id="bke079"
						name="outBizDTO.bke079" codeType="baf305_medi"
						value="outBizDTO.bke079"
						required="true" onchange="changeCity(this.value) "/>
					<td class="tdLabel"><label for="pke080" class="label"><span
							class="required">*</span>所属地级市</label></td>
					<td class="tdInput"><select id="bke080"
						name="outBizDTO.bke080" class="validate[required] select"
						onchange="changeDept(this.value)">
							<s:if test="{#request.bke080==null}">
								<option value="0"></option>
							</s:if>
							<s:if test="{#request.bke080!=null}">
								<option value="${bke080_value }">${bke080}</option>
							</s:if>
					</select></td>
					<td class="tdLabel"><label for="bke081" class="label">所属县或区</label></td>
					<td class="tdInput"><select id="bke081"
						name="outBizDTO.bke081" class="select">
							<s:if test="{#request.bke081==null}">
								<option value="0"></option>
							</s:if>
							<s:if test="{#request.bke081!=null}">
								<option value="${bke081_value }">${bke081}</option>
							</s:if>
					</select></td>
				</tr>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:panelbox key="panel_result" title="异地选定医疗机构信息"
			allowCollapse="false">
			<powersi:panelbox-toolbar>
				<powersi:button value="选择医院" id="queryBtn" onclick="gethosp()" />
				<powersi:button value="删除医院" id="delBtn" onclick="delHosp()" />
				<powersi:button value="保存" id="addBtn" onclick="saveAdd()" />
				<powersi:button value="清屏" id="epx" onclick="clean()" />
			</powersi:panelbox-toolbar>
			<powersi:datagrid id="grid_out_hosp_input" delayLoad="true"
				height="300px" checkbox="true" rownumbers="false">
				<powersi:datagrid-column name="akb020" display="医院编码" width="200" />
				<powersi:datagrid-column name="aab069" display="医药机构名称" width="200" />
				<powersi:datagrid-column name="aka101" display="等级" width="150" />
				<powersi:datagrid-column name="bkc110" display="机构级别" code="" />
				<powersi:datagrid-column name="akb023" display="机构类别" />
				<powersi:datagrid-column name="bkc111" display="异地类型" />
				<powersi:datagrid-column name="aaz107" display="定点医疗机构ID" />
				<%-- <powersi:datagrid-column name="bfc001" display="异地医药机构序列号" />
				<powersi:datagrid-column name="bke301" display="医药机构级别" />
				<powersi:datagrid-column name="bke309" display="医疗机构类型" />
				<powersi:datagrid-column name="aae005" display="联系电话" />
				<powersi:datagrid-column name="aae004" display="联系人姓名" />
				<powersi:datagrid-column name="aae007" display="邮政编码" />
				<powersi:datagrid-column name="aae100" display="有效标志" />
				<powersi:datagrid-column name="bke081" display="异地安置县/区" />
				<powersi:datagrid-column name="bkc016" display="变更序列号" />
				<powersi:datagrid-column name="bke074" display="异地类型" />
				<powersi:datagrid-column name="aae013" display="备注" />
				<powersi:datagrid-column name="aae016" display="可编辑标志" />
				<powersi:datagrid-column name="aae030" display="起效时间" />
				<powersi:datagrid-column name="aae031" display="失效时间" /> --%>
			</powersi:datagrid>
		</powersi:panelbox>

		<powersi:editorlayout cols="6">
			<powersi:buttons>
				
			</powersi:buttons>
			<powersi:hidden id="isRight" name="isRight"></powersi:hidden>
		</powersi:editorlayout>
	</powersi:form>



	<powersi:errors />

	<script type="text/javascript">

	window.onload = function(){
		 var myDate = new Date();
		 var year = myDate.getFullYear();
		 var month = (myDate.getMonth()+1).toString().length==1?"0"+(myDate.getMonth()+1).toString():(myDate.getMonth()+1).toString();
		 var day = myDate.getDate().toString().length==1?"0"+myDate.getDate().toString():myDate.getDate().toString();
		 

		$('#akb020').val("<%=hospital_id%>");
		$('#akb021').val("<%=hospital_name%>");
		$('#bke087').val("<%=aaa027%>");
		$('#aaa027Name').val("<%=aaa027Name%>");
		
		$('#bke084').val("<%=userName%>");
		$('#bke085').val(year+"-"+month+"-"+day);
		$('#bke086').val("<%=loginUser%>");
		
		if ($("#akb020").val() == '' || $("#akb020").val() == null) {
			popupAlert("医院编码不能为空，请检查登陆信息！");
			return;
		}

	}
	
	function saveAdd() {
	    if (!checkFormValidtion()) {
	        return;
	    }
	    var dto = $("#mainForm").serialize();

	    if (grid_out_hosp_input.getRowsCount() < 1) {
	        popupAlert("请添加异地定点医疗机构！");
	        return;
	    }
	    if (grid_out_hosp_input.getRowsCount() > 3) {
	        popupAlert("异地定点医疗机构超过3家，请减少！");
	        return;
	    }
	    
	    var hospList = grid_out_hosp_input.getSelectedRows();
	    
	    if(powersi.isempty(hospList)){
			popupAlert("请选择异地医疗机构信息！");
			return;
		}
	    
	   for ( var i in hospList) {
			if (powersi.tostring(hospList[i].akb020) == "" || powersi.tostring(hospList[i].akb020) == null) {
	            popupAlert("医疗机构编码不能为空！");
	            return;
	        }
		} 
	   var bke079 = $("#bke079").val();
	   
	   var bke080 = $("#bke080").val();
	   var hospList = grid_out_hosp_input.getAllData();
	    for(var i=0;i<hospList.length;i++){
	    	popupAlert(bke079);
	    	popupAlert(hospList[i].baf305);
	    	popupAlert(bke080);
	    	popupAlert(hospList[i].baf306);
	    	if(bke079 != hospList[i].baf305){
				popupAlert("选择的异地医院 "+hospList[i].akb020+hospList[i].akb021+" 与安置地的所属省不一致");
				return;
	    	}
	    	if(bke080 != hospList[i].baf306){
				popupAlert("选择的异地医院 "+hospList[i].akb020+hospList[i].akb021+" 与安置地的所属市不一致");
				return;
	    	}
	    	
	    }

	    
	    var obj= powersi.tostring(hospList);
	   
	    postJSON("<%=path%>/medicare/OutBizAction!saveOutAreaInfo.action?"+dto, 
	    		{"objList" : obj}, function(json) {
			if (!checkJSONResult(json)) {
				return;
			}
		});

	}
	
function changeCity(sel){
	    postJSON("<%=path%>/medicare/OutBizAction!getCityAndDept.action",{"outBizDTO.bkd326":sel,"outBizDTO.bkd327":1},function(json){
				if(!checkJSONResult(json)){
				    return;
			    }
			 	var citys = json.data;
		      var select = document.getElementById('bke080');
		      document.getElementById('bke081').options.length = 0;
		      select.options.length = 0;
		      var option = new Option("请选择...","0");
		      select.add(option);
		      for(i=0;i<citys.length;i++){
		         var city_value = citys[i].bkd325;
		         var vity_name = citys[i].bkd324;
		         var option = new Option(vity_name,city_value);
		         select.add(option);
		      }
  		});  
	}

	function changeDept(sel){
	    postJSON("<%=path%>/medicare/OutBizAction!getCityAndDept.action",{"outBizDTO.bkd326":sel,"outBizDTO.bkd327":2}, function(json){
			if(!checkJSONResult(json)){
			    return;
		    }
			var citys = json.data;
		      var select = document.getElementById('bke081');
		      select.options.length = 0;
		      var option = new Option("请选择...","0");
		      select.add(option);
		      for(i=0;i<citys.length;i++){
		         var city_value = citys[i].bkd325;
		         var vity_name = citys[i].bkd324;
		         var option = new Option(vity_name,city_value);
		         select.add(option);
		      }
  		});  
	}
	
	 function onchangeaac066_t(aac066_t) {
		if (aac066_t =='3') {
			$("#aac066_o").removeAttr("readonly");
			$("#aac066_o").attr('class', 'text')
		} else {
			$("#aac066_o").prop('readonly','readonly');
			$("#aac066_o").attr('class', 'text textReadonly')
		}
	    if (aac066_t =='1' || aac066_t =='2' || aac066_t =='3'){
	       $("#bke0740-1").prop("checked", true);
	    }	
	 }
	 
	 function setRequire() {

		    if ($("#bke075").val() == 1) {

		        $('input:text[name^="outdto.bke078"]').attr('class', 'validate[required] text');
		        $('input:text[name^="outdto.bke072"]').attr('class', 'validate[required] text');
		        $('input:text[name^="outdto.aae007"]').attr('class', 'validate[required] text');
		        $('input:text[name^="outdto.bke073"]').attr('class', 'validate[required] text');
		        $('input:text[name^="outdto.bke100"]').attr('class', 'validate[required] text');
		        $('input:text[name^="outdto.bke070"]').attr('class', 'validate[required] text');
		        $('input:text[name^="outdto.bke071"]').attr('class', 'validate[required] text');

		        document.getElementById("trDisplay1").style.display = "";
		        document.getElementById("trDisplay2").style.display = "";
		    } else {
		        $('input:text[name^="outdto.bke078"]').attr('class', 'text');
		        $('input:text[name^="outdto.bke072"]').attr('class', 'text');
		        $('input:text[name^="outdto.aae007"]').attr('class', 'text');
		        $('input:text[name^="outdto.bke073"]').attr('class', 'text');
		        $('input:text[name^="outdto.bke100"]').attr('class', 'text');
		        $('input:text[name^="outdto.bke070"]').attr('class', 'text');
		        $('input:text[name^="outdto.bke071"]').attr('class', 'text');

		        document.getElementById("trDisplay1").style.display = "none";
		        document.getElementById("trDisplay2").style.display = "none";
		    }
		}
	 
	 function gethosp(){
		 var bke079 = $("#bke079").val();
		 if ($("#bke079").val() == '0') {
			popupAlert("请选择异地申请安置地所属省！");
			return;
		}
		 var bke080 = $("#bke080").val();
		 if ($("#bke080").val() == '0') {
			popupAlert("请选择异地申请安置地所属地级市！");
			return;
		}
		 popupDialog({
				url: "<%=path%>/pages/biz/medicare/comm/ChooseHospInfo.jsp?bke080="+bke080+"&bke079="+bke079,
				onClosed : function() {
					var ret = this.returnValue;
					if (ret == null) {
						return;
					}

					var hospList = grid_out_hosp_input.getAllData();

					for (var i = 0; i < ret.length; i++) {
						for (var n = 0; n < hospList.length; n++) {
							if (powersi.tostring(hospList[n]).indexOf(
									ret[i].akb020) != -1) {
								popupAlert("已存在相同医院编码的医院！");
								return;
							}
						}
						if (grid_out_hosp_input.getRowsCount() > 2) {
							popupAlert("异地定点医疗机构超过3家，最多只能添加3家医疗机构！");
							return;
						}

						grid_out_hosp_input.addEditRow({
							"akb020" : ret[i].akb020,
							"aab069" : ret[i].aab069,
							"aka101" : ret[i].aka101,
							"bkc110" : ret[i].bkc110,
							"akb023" : ret[i].akb023,
							"baf305" : ret[i].baf305,
							"baf306" : ret[i].baf306,
							"bkc111" : ret[i].bkc111
						});

						//结束编辑状态
						grid_out_hosp_input.endEdit();
					}
				}
			}, screen.height,screen.width);
		}
	 
	 /*查询参保信息*/
		function findAac001() {
			if (window.event.keyCode == 13) {
				var tracestring = powersi.trim($("#tracestring").val());
				if (powersi.isnull(tracestring)) {
					popupAlert("请输入信息回车！");
					return;
				}
				$("#tracestring").attr("disabled", "disabled");
			
				$("#querystring").val(tracestring);
				
				var argName = "indi_id";
		        var argValue = $("#querystring").val();

		        if(argValue.length  > 10){
					argName = "idcard";
				}else{
					argName = "indi_id";
				}
				
				postJSON(rootPath +"/medicare/OutBizAction!getPersonInfo.action?outBizDTO.arg_name="+argName+"&outBizDTO.arg_value="+$("#querystring").val(),
				  		function(json){
							if(!checkJSONResult(json)){
							    return;
						    }
							
							if (json.data != "no") {
								$.each(json.data, function(key, value) {
									if (!powersi.isnull(value)) {
										$("#" + key).val(value);
									}
								});
							}else{
								popupAlert("没有获取到人员信息！");
							}
				});
				
				$("#tracestring").removeAttr("disabled");
			}
		}
		
		function delHosp() {
		    if(grid_out_hosp_input.getRowsCount() < 1){
		    	return;
		    }
		    if(grid_out_hosp_input.getSelectedRow()==null){
		    	popupAlert("请选择要删除的数据！");
		    	return;
		    }
		    grid_out_hosp_input.deleteSelectedRow();
		}
	</script>
</body>
</powersi:html>