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
<powersi:head title="选择单位用户" target="_self" />
<script src="${strutsPath}/include/multiselect.js" type="text/javascript"></script>
<style type="text/css">
</style>
<script type="text/javascript">
	var _corpList = [];
	var _corpIndex = {};
	
	$(function() {
		//增加全选checkbox
		jQuery.each("center level corptype".split(" "), function(i, name) {
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
  			$("#receiveUsers").val('参保单位');
  		}
		
		try{
			postJSON(rootPath + "/message/BulletinEditAction!getReceiveUsers.action?type=2",  showCorpList);
		} catch(e){
			alert('初始化单位列表出错:' + e.message);
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
	
	function showCorpList(json){
		if (!checkJSONResult(json)) {
			closeDialog();
    	    return;
        }
    	
    	_corpList.length = 0;
    	_corpList = json.data;
    	
    	var corpsObj = S('corps');
    	corpsObj.options.length = 0;
    	for(var i = 0; i < _corpList.length; i ++){
    		corpsObj.options.add(new Option());
    		
    		corpsObj.options[i].value = _corpList[i].corp_id;
    		corpsObj.options[i].text = _corpList[i].corp_name;
    		
    		//生成单位索引
    		_corpIndex[_corpList[i].corp_id] = i;
    	}
    	
    	//初始化已经选择数据
    	var receiveString = $('#receiveString').val();
		if(receiveString != null && receiveString.length > 0){
			var sels = powersi.tojson(receiveString);
			$.each(sels, function(n, sel){
				//全选单位
				if(sel.receive_org == "0"){
					$("#corps option").prop("selected",true);
					return false;
				}
				
				$("#corps").find("option[value='" + sel.receive_org + "']").prop("selected", true);
			});
		}
		
		//初始化单位列表
		$("#corps").multiselect();
	}

	function retSelect() {
		var receiveUsers = powersi.trim($("#receiveUsers").val());
		if(receiveUsers.length == 0){
			alert("请输入<%=recvUsers%>");
			$('#receiveUsers').focus();
			return;
		}
		
		var corps = $('#corps').multiselect('data');
		if(corps.length == 0){
			alert("请选择接收单位");
			$('#corps').focus();
			return;
		}
		
		var sels = [];
		//单位用户中心编号不处理，一律保存0
		if("0" == "<%=centerId %>" && corps.length == _corpList.length){//判断是否所有单位的公告
			var corpjson = {};
			
			corpjson.center_id = '0';
			corpjson.receive_org = '0';
			corpjson.receive_staff = '0';
			corpjson.receive_man = '所有参保单位';
			
			sels.push(corpjson);
		} else {
			var corp = null;
			for (var idx in corps) {
				corp = findCorp(corps[idx]);
				if(corp != null){
					var corpjson = {};
					
					corpjson.center_id = '0';
					corpjson.receive_org = corp.corp_id;
					corpjson.receive_staff = '0';
					corpjson.receive_man = corp.corp_name;
					
					sels.push(corpjson);
				}
			}
		}
		
		var retVal = {
			"receive_string": powersi.tostring(sels),
			"receive_users": receiveUsers
		};
		
		closeDialog(retVal);
	}

	function findCorp(corpId){
		var idx = _corpIndex[corpId];
		return (idx >= 0) ? _corpList[idx] : null;
	}
	
	function closeWin() {
		closeDialog();
	}

	//操作类型 1添加 2删除
	function doSelCorp(operaType) {
		var centerAll = true, levelAll = true, corptypeAll = true;
		var centerList = [], levelList = [], corptypeList = [];
		var cbxName = null, cbxChecked = false;
		$("#corpForm :checkbox[value!='all']").each(function(){
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
				
			} else if(cbxName == "searchBulletinDto.corptype"){
				if(cbxChecked == true){
					corptypeList.push($(this).val());	
				} else {
					corptypeAll = false;
				}
			} else {
				
			}
		});
		
		if(centerList.length == 0 && levelList.length == 0 && corptypeList.length == 0){
			alert('请至少选一个条件');
			return false;
		}
		
		var selLength = 0;
		var selAll = false;
		var selCorps = [];
		if(centerAll && levelAll && corptypeAll){
			//全选
			selAll = true;
			selLength = _corpList.length;
		} else {
			//部分选择
			var findFlag = false;
			var tmp = null;
			$.each(_corpList, function(n, corp){
				if(!centerAll && centerList.length > 0){
					findFlag = false;
					tmp = corp['center_id'] || '';
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
					tmp = corp.corp_level || "";
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
				
				if(!corptypeAll && corptypeList.length > 0){
					findFlag = false;
					tmp = corp.corp_kind || "";
					$.each(corptypeList, function(i, v){
						if(v == tmp){
							findFlag = true;
							return false;//break
						}
					});
					
					if(!findFlag){
						return true;//continue;
					}
				}
				
				selCorps.push(corp.corp_id);
			});
			
			selLength = selCorps.length;
		}
		
		if(selLength == 0){
			alert('没有符合条件的单位');
			return false;
		} else {
			if(!confirm('共有 ' + selLength + ' 个单位符合条件。\n您确认' + (operaType == 1 ? '添加' : '删除') + '吗？')){
				return false;
			}
		}
		
		if(selAll){
			$('#corps').multiselect((operaType == 1) ? 'selectAll' : 'removeAll');	
		} else {
			$('#corps').multiselect((operaType == 1) ? 'select' : 'remove', selCorps);	
		}
		
		return true;
	}

	function onSelAdd(){
		if(doSelCorp(1)){
			hideSelCorp();
		}
	}
	
	function onSelDel(){
		if(doSelCorp(2)){
			hideSelCorp();
		}
	}
	
	function onSelCancel() {
		hideSelCorp();
	}
	
	function onSelClear() {
		$("#corpForm :checked").prop("checked", false);
	}

	var dlgCorp = null;
	function onSelCorp() {
		$("#corpForm :checked[value='all']").prop("checked", false);
		
		if(dlgCorp){
			dlgCorp.show();
		} else{
			dlgCorp = popupDiv("#divSelCorp", '单位过滤条件', 500);
		}
	}
	
	function hideSelCorp() {
		if(dlgCorp){
			dlgCorp.hide();
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
			<tr style="height:360px;">
				<td colspan="2">
					<select id="corps" class="multiselect" multiple="multiple" name="corps" 
						style="height:350px;width:95%;display:none;">
					</select>
				</td>
			</tr>
		</powersi:editorlayout>

		<div class="divButton">
			<powersi:button id="btnSel" value="根据条件选择单位"
				onclick="onSelCorp()" cssStyle="display:none" />
			<powersi:button id="btOk" value="确 认"
				onclick="javascript:retSelect()"></powersi:button>
			<powersi:button id="btCancel" value="取 消"
				onclick="javascript:closeWin()"></powersi:button>
		</div>
	</powersi:form>

	<div class="hidden">
		<div id="divSelCorp">
			<powersi:form id="corpForm" action="null" disabled="true">
				<powersi:editorlayout cols="4">
					<tr>
						<powersi:checkboxlist id="center" name="searchBulletinDto.center"
							key="center_id" codeType="aaa027_list"
							codeFilter="<%=centerFilter %>" repeatColumns="4" colspan="3" />
					</tr>
					<tr>
						<powersi:checkboxlist id="level" name="searchBulletinDto.level"
							label="单位级别" codeType="pkc110_list"
							codeFilter="data_value not in('0', '9')" repeatColumns="1" />
						<powersi:checkboxlist id="corptype"
							name="searchBulletinDto.corptype" label="单位类型" codeType="akb023"
							repeatColumns="1" />
					</tr>
				</powersi:editorlayout>
				<div class="divButton">
					<input id="btnSelAdd" value="添 加" type="button" class="button"
						onclick="onSelAdd()" title="根据条件添加选择的单位"/> 
					<input id="btnSelADel" value="删 除" type="button" class="button"
						onclick="onSelDel()" title="根据条件删除选择的单位"/>
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