<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.hygeia.framework.util.UtilFunc"%>
<%@ page import="com.powersi.hygeia.web.util.JsonHelper" %>
<%
	com.powersi.hygeia.framework.UserInfo user = com.powersi.hygeia.framework.BusiContext
			.getUserInfo();
	String centerFilter = "1 = 0";
	String staffLevel = UtilFunc.getString(user, "staff_level", "9");
	String centerId = "0";
	if (staffLevel.equals("1") || user.isSuperUser()) {
		centerFilter = "";
		centerId = "0";
	} else {
		if (staffLevel.equals("2")) {
			centerFilter = "aaa027 in ("
					+ UtilFunc.getString(user, "center_list", "0")
					+ ") and substr(aaa027, 5, 2) <> '00'";
			centerId = "0";//暂时市级用户也可以发所有中心发全部公告
		} else {
			centerId = UtilFunc.getString(user, "center_id", "999999");
			centerFilter = "aaa027 = " + UtilFunc.getString(user, "center_id", "0");
		}
	}
	
	String recvUsers = com.powersi.hygeia.web.util.TextHelper.getText("bulletin_receive_users", "公告接收者");
%>
<powersi:html>
<head>
<powersi:head title="选择医院用户" target="_self" />
<script src="${strutsPath}/include/multiselect.js" type="text/javascript"></script>
<style type="text/css">
</style>
<script type="text/javascript">
	var _hospList = [];
	var _hospIndex = {};
	
	$(function() {
		//增加全选checkbox
		jQuery.each("center level hosptype role".split(" "), function(i, name) {
			$('label[for="' + name + '"]').each(
					function() {
						var cbxId = "cbxAll_" + name;
						var cbxHtml = $(this).html();
						var cbxText = $(this).text();
						var cbxName = "searchBulletinDto." + name;
						
						$(this).html(
								"<input type='checkbox' id='"
										+ cbxId + "' name='" + cbxName
										+ "' title='全选" + cbxText
										+ "' onclick='selectAll(this, \""
										+ cbxName + "\")' value='all' class='checkbox'></input><label for='" + cbxId + "' class='checkboxLabel'>" + cbxHtml
										+ "</label>");
					});
		});
		
		var retVal = getDialogParam();
		if(retVal != null){
			$("#receiveString").val(retVal.receive_string || "");
	  		$("#receiveUsers").val(retVal.receive_users || "");
		}
		
		//初始化公告接收者
		if($("#receiveUsers").val().length == 0){
  			$("#receiveUsers").val('定点医疗机构');
  		}
		
		try{
			postJSON(rootPath + "/message/BulletinEditAction!getReceiveUsers.action?type=3",  showHospList);
		} catch(e){
			alert('初始化医院列表出错:' + e.message);
		}
		
		$("#receiveUsers").focus();
	});

	//实现勾选全选radio效果
	function selectCbx(cbxObj, cbxName) {
		var id = cbxObj.id;
		if (cbxObj.checked) {
			$(":checkbox[name='" + cbxName + "']").each(function() {
				if ($(this).attr("id") != id) {
					$(this).attr("checked", true).attr("disabled", true);
				}
			});
		} else {
			if ($(this).attr("id") != id) {
				$(":checkbox[name='" + cbxName + "']").each(function() {
					$(this).attr("checked", false).attr("disabled", false);
				});
			}
		}
	}
	
	function showHospList(json){
		if (!checkJSONResult(json)) {
			closeDialog();
    	    return;
        }
    	
    	_hospList.length = 0;
    	_hospList = json.data;
    	
    	var hospsObj = S('hosps');
    	hospsObj.options.length = 0;
    	for(var i = 0; i < _hospList.length; i ++){
    		hospsObj.options.add(new Option());
    		
    		hospsObj.options[i].value = _hospList[i].hospital_id;
    		hospsObj.options[i].text = _hospList[i].hospital_name;
    		
    		//生成医院索引
    		_hospIndex[_hospList[i].hospital_id] = i;
    	}
    	
    	//初始化医院列表
		$("#hosps").multiselect();
    	
    	//初始化已经选择数据
    	var receiveString = $('#receiveString').val();
		if(receiveString != null && receiveString.length > 0){
			var sels = powersi.tojson(receiveString);
			var selHosps = [];
			$.each(sels, function(n, sel){
				//处理医院岗位
				if(n == 0){
					var roles = (sel.receive_staff || "").split(",");
					if(roles.length > 0){
						var val = "";
						$(":checkbox[name='searchBulletinDto.role']").each(function(){
							val = $(this).val();
							for(var i = 0; i < roles.length; i ++){
								if(roles[i] == val){
									$(this).prop("checked", true);
									break;
								}
							}
						});
					}
				}
				
				//全选医院
				if(sel.receive_org == "0"){
					selHosps.length = 0;
					$('#hosps').multiselect('selectAll');
					return false;
				}
				
				selHosps.push(sel.receive_org);
			});
			
			//部分选择
			if(selHosps.length > 0){
				$('#hosps').multiselect('select', selHosps);
			}
			
			selHosps.length = 0;
		}
	}

	function retSelect() {
		var receiveUsers = powersi.trim($("#receiveUsers").val());
		if(receiveUsers.length == 0){
			alert("请输入<%=recvUsers%>");
			$('#receiveUsers').focus();
			return;
		}
		
		var roles = [], roleNames = [];
		$('input[name="searchBulletinDto.role"][value!="all"]:checked').each(function() {
			roles.push($(this).val());
			roleNames.push($(this).next().text());
		});
		
		if (roles.length == 0) {
			alert("请选择医院接收岗位");
			return;
		}
		
		var hosps = $('#hosps').multiselect('data');
		if(hosps.length == 0){
			alert("请选择接收医院");
			$('#hosps').focus();
			return;
		}
		
		var chkjson = roles.join(',');
		var chknamejson = roleNames.join(',');
		var sels = [];
		//医院用户中心编号不处理，一律保存0
		if("0" == "<%=centerId %>" && hosps.length == _hospList.length){//判断是否所有医疗机构的公告
			var hospjson = {};
			
			hospjson.center_id = '0';
			hospjson.receive_org = '0';
			//hospjson.receive_org_name = '所有医疗机构';
			hospjson.receive_staff = chkjson;
			hospjson.receive_man = '所有医疗机构[' + chknamejson + ']';
			
			sels.push(hospjson);
		} else {
			var hosp = null;
			for (var idx in hosps) {
				hosp = findHosp(hosps[idx]);
				if(hosp != null){
					var hospjson = {};
					
					hospjson.center_id = '0';
					hospjson.receive_org = hosp.hospital_id;
					//hospjson.receive_org_name = hosp.hospital_name;
					hospjson.receive_staff = chkjson;
					hospjson.receive_man = hosp.hospital_name + '[' + chknamejson + ']';
					
					sels.push(hospjson);
				}
			}
		}
		
		var retVal = {
			"receive_string": powersi.tostring(sels),
			"receive_users": receiveUsers
		};
		
		closeDialog(retVal);
	}

	function findHosp(hospId){
		var idx = _hospIndex[hospId];
		return (idx >= 0) ? _hospList[idx] : null;
	}
	
	function closeWin() {
		closeDialog();
	}

	//操作类型 1添加 2删除
	function doSelHosp(operaType) {
		var centerAll = true, levelAll = true, hosptypeAll = true;
		var centerList = [], levelList = [], hosptypeList = [];
		var cbxName = null, cbxChecked = false;
		$("#hospForm :checkbox[value!='all']").each(function(){
			cbxName = $(this).prop('name');
			cbxChecked = $(this).prop('checked') || false;
			
			if(cbxName == "searchBulletinDto.center"){
				if(cbxChecked == true){
					centerList.push($(this).val());	
				} else {
					centerAll = false;
				}
			} else if(cbxName == "searchBulletinDto.level"){
				if(cbxChecked == true){
					levelList.push($(this).val());	
				} else {
					levelAll = false;
				}
				
			} else if(cbxName == "searchBulletinDto.hosptype"){
				if(cbxChecked == true){
					hosptypeList.push($(this).val());	
				} else {
					hosptypeAll = false;
				}
			} else {
				
			}
		});
		
		if(centerList.length == 0 && levelList.length == 0 && hosptypeList.length == 0){
			alert('请至少选一个条件');
			return false;
		}
		
		var selLength = 0;
		var selAll = false;
		var selHosps = [];
		if(centerAll && levelAll && hosptypeAll){
			//全选
			selAll = true;
			selLength = _hospList.length;
		} else {
			//部分选择
			var findFlag = false;
			var tmp = null;
			$.each(_hospList, function(n, hosp){
				if(!centerAll && centerList.length > 0){
					findFlag = false;
					tmp = hosp['center_id'] || '';
					$.each(centerList, function(i, v){
						if(v == tmp){
							findFlag = true;
							return false;//break
						}
					});
					
					if(!findFlag){
						return true;//continue;
					}
				}
				
				if(!levelAll && levelList.length > 0){
					findFlag = false;
					tmp = hosp.hosp_level || "";
					$.each(levelList, function(i, v){
						if(v == tmp){
							findFlag = true;
							return false;//break
						}
					});
					
					if(!findFlag){
						return true;//continue;
					}
				}
				
				if(!hosptypeAll && hosptypeList.length > 0){
					findFlag = false;
					tmp = hosp.hosp_kind || "";
					$.each(hosptypeList, function(i, v){
						if(v == tmp){
							findFlag = true;
							return false;//break
						}
					});
					
					if(!findFlag){
						return true;//continue;
					}
				}
				
				selHosps.push(hosp.hospital_id);
			});
			
			selLength = selHosps.length;
		}
		
		if(selLength == 0){
			alert('没有符合条件的医院');
			return false;
		} else {
			if(!confirm('共有 ' + selLength + ' 个医院符合条件。\n您确认' + (operaType == 1 ? '添加' : '删除') + '吗？')){
				return false;
			}
		}
		
		if(selAll){
			$('#hosps').multiselect((operaType == 1) ? 'selectAll' : 'removeAll');	
		} else {
			$('#hosps').multiselect((operaType == 1) ? 'select' : 'remove', selHosps);	
		}
		
		return true;
	}

	function onSelAdd(){
		if(doSelHosp(1)){
			hideSelHosp();
		}
	}
	
	function onSelDel(){
		if(doSelHosp(2)){
			hideSelHosp();
		}
	}
	
	function onSelCancel() {
		hideSelHosp();
	}
	
	function onSelClear() {
		$("#hospForm :checked").prop("checked", false);
	}

	var dlgHosp = null;
	function onSelHosp() {
		$("#hospForm :checked[value='all']").prop("checked", false);
		
		if(dlgHosp){
			dlgHosp.show();
		} else{
			dlgHosp = popupDiv("#divSelHosp", '医院过滤条件', 500);
		}
	}
	
	function hideSelHosp() {
		if(dlgHosp){
			dlgHosp.hide();
		}
	}
</script>
</head>
<body>
	<powersi:form id="queryForm" action="null" disabled="true">
		<powersi:editorlayout cols="30%,70%">
			<tr>
				<input type="hidden" value="" id="receiveString" />
				<powersi:textfield id="receiveUsers" name="users"
					label="<%=recvUsers %>" required="true" maxlength="100" />
			</tr>
			<tr>
				<powersi:checkboxlist id="role" name="searchBulletinDto.role" label="医院接收岗位"
					codeType="bulletin_hosp_role" required="true" />
			</tr>
			<tr style="height:360px;">
				<td colspan="2">
					<select id="hosps" class="multiselect" multiple="multiple" name="hosps" 
						style="height:350px;width:95%;display:none;">
					</select>
				</td>
			</tr>
		</powersi:editorlayout>

		<div class="divButton">
			<powersi:button id="btnSel" value="根据条件选择医院" type="button"
				class="button" onclick="onSelHosp()" />
			<powersi:button id="btOk" value="确 认"
				onclick="javascript:retSelect()"></powersi:button>
			<powersi:button id="btCancel" value="取 消"
				onclick="javascript:closeWin()"></powersi:button>
		</div>
	</powersi:form>

	<div class="hidden">
		<div id="divSelHosp">
			<powersi:form id="hospForm" action="null" disabled="true">
				<powersi:editorlayout cols="4">
					<tr>
						<powersi:checkboxlist id="center" name="searchBulletinDto.center"
							key="center_id" codeType="aaa027_list"
							codeFilter="<%=centerFilter %>" repeatColumns="4" colspan="3" />
					</tr>
					<tr>
						<powersi:checkboxlist id="level" name="searchBulletinDto.level"
							label="医院级别" codeType="pkc110_list"
							codeFilter="data_value not in('0', '9')" repeatColumns="1" />
						<powersi:checkboxlist id="hosptype"
							name="searchBulletinDto.hosptype" label="医院类型" codeType="akb023"
							repeatColumns="1" />
					</tr>
				</powersi:editorlayout>
				<div class="divButton">
					<input id="btnSelAdd" value="添 加" type="button" class="button"
						onclick="onSelAdd()" title="根据条件添加选择的医院"/> 
					<input id="btnSelADel" value="删 除" type="button" class="button"
						onclick="onSelDel()" title="根据条件删除选择的医院"/>
					<input id="btnSelClear" value="清 除" type="button" class="button"
						onclick="onSelClear()" title="清除选择条件"/>
					<input id="btnSelCancel" value="取 消"
						type="button" class="button" onclick="onSelCancel()" />
				</div>
			</powersi:form>
		</div>
	</div>
	<powersi:errors />
</body>
</powersi:html>