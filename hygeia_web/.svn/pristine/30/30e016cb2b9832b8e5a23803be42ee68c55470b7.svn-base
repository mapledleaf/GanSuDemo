<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.powersi.hygeia.framework.ParameterMapping"%>
<%
	String systemName = ParameterMapping.getStringByCode("center_org_name", ParameterMapping.getSystemName());
	Object attr = null;
	attr = request.getAttribute("closeFlag");
	String closeFlag = (attr == null) ? "false" : attr.toString();
	
	attr = request.getAttribute("receiveMan");
	String receiveMan = (attr == null) ? "" : attr.toString();
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="发送消息" />
<script src="${strutsPath}/include/ztree.js" type="text/javascript"></script>
<script src="${rootPath}/resource/js/jquery.quickfilter.js" type="text/javascript"></script>
<style type="text/css">
.delete {background-image: url(${strutsPath}/icon/user--minus.png);}
.ztree li span.button.user_ico_open{margin-right:2px; background: url(${strutsPath}/icon/user--arrow.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li span.button.user_ico_close{margin-right:2px; background: url(${strutsPath}/icon/user.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li span.button.user_ico_docu{margin-right:2px; background: url(${strutsPath}/icon/user.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
</style>
</head>
<body>
	<div class="divCenter" style="width: 560px">
		<powersi:form namespace="/message" action="MessageAction!sendMessage" id="messageForm" method="post">
			<powersi:editorlayout cols="25%,75%">
				<powersi:editorlayout-head title="发送信息"></powersi:editorlayout-head>
				<powersi:editorlayout-row>
						<powersi:textfield label="接收人" id="staff_up_name" readonly="true" buttonId="btnSelUser" onbuttonclick="selPersons()"></powersi:textfield>
						<s:hidden id="sysMessagereveiveMan" name="sysMessage.reveiveMan"></s:hidden>
						<s:hidden id="sysMessagereveiveManNames" name="sysMessage.reveiveManNames"></s:hidden>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="msTitle" label="消息标题" name="sysMessage.msTitle" required="true" maxlength="200" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea id="msContent" label="消息内容" name="sysMessage.msContent" required="false" cols="10" rows="18" ></powersi:textarea>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:editorlayout-label>
						<powersi:label value="选 项"></powersi:label>
					</powersi:editorlayout-label>
					<powersi:editorlayout-input>
						<powersi:checkbox value="0" label="发送的同时保存到发件箱" checked="checked" name="sysMessage.saveFlag"></powersi:checkbox>
					</powersi:editorlayout-input>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
			<div class="divButton">
				<powersi:button id="btChooseRetriveStaff" key="button_send" onclick="sendMessage()"></powersi:button>
				<c:if test="${closeFlag == 'true'}">
					<powersi:button id="btClose" key="button_close" onclick="closeDialog()"></powersi:button>
				</c:if>
			</div>
		</powersi:form>
	</div>
	<!-- 隐藏区 -->
	<div class="hidden">
		<div id="divUpStaff" style="height: 450px; overflow:auto;">
			<table class="tableFrame" style="table-layout: fixed">
				<thead>
					<tr>
						<th align="left">
							操作员(<span id="staffNum">0</span>)
						</th>
						<th align="left">
							接收人(<span id="selNum">0</span>)
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td width="50%" height="370px" valign="top">
							<div style="height:340px;overflow:auto;">
								<ul id="tvUp" class="ztree"></ul>
							</div>
							<div id="divStaffFilter"></div>
						</td>
						<td width="50%" height="370px" valign="top">
							<table id="tabUser" class="tablePanel">
								<colgroup>
					                <col />
					                <col width="20px" />
					                <col class="hidden" />
					       	 	</colgroup>
					       	 	<tbody>
					       	 	</tbody>
							</table>
						</td>
					</tr>
				</tbody>
			</table>
		
			<div class="divButton" style="clear:both;">
				<powersi:button id="btnSelOk" key="button_ok" onclick="onSelOk()"></powersi:button>
				<powersi:button id="btnSelCancel" key="button_cancel" onclick="onSelCancel()"></powersi:button>
			</div>
		</div>
	</div>
	<powersi:errors />
<script type="text/javascript">
	var system_name = "<%=systemName%>";
    var _staffTree = null;
    var _staffFilter = null;
    var _lastFind = null;
    var _nodeList = [];
    
    var _receiveMan = '<%=receiveMan%>';
	$(document).ready(function(){
		getStaffTree();
		
		$('#tvUp').quickfilter({
        	srcElement: '',
            attached: '#divStaffFilter',
            stripeRowClass: null,
            inputText: '查找 部门、用户',
            inputTitle: '输入关键字后，按回车键查找符合条件的数据',
            labelText: '',
            filter: function(i){
            	return "";
            },
            onAfter: function(){
            	try{
            		if(_staffTree && _staffFilter){
                		var txt = powersi.trim(_staffFilter.val());
                		var len = powersi.length(_nodeList);
                		if(_lastFind !== txt) {
                			_lastFind = txt;
                			
                			for( var i = 0; i < len; i++) {
                				_nodeList[i].highlight = false;
                				_staffTree.updateNode(_nodeList[i]);
                			}
                    		_nodeList.length = 0;
                    		
                    		if(txt.length > 0){
                    			_nodeList = _staffTree.getNodesByParamFuzzy("name", txt, null);
                    		}
                    		
                    		len = powersi.length(_nodeList);
                    		for( var i = 0; i < len; i ++) {
                    			_nodeList[i].highlight = true;
                    			_staffTree.updateNode(_nodeList[i]);
   	            			}
                		}
                		
                		if(txt.length == 0){
                			return;
                		}
                		
                   		if(len > 0){
                   			var sel = 0;
                   			if(len > 1){
                   				var curNodes =  _staffTree.getSelectedNodes();
                       			if(curNodes.length > 0){
                       				var curIdx =  getTreeOrder("tvUp", curNodes[0]);
                       				for(var i = 0; i < len; i ++){
                       					var idx = getTreeOrder("tvUp", _nodeList[i]);
                       					if(idx > curIdx){
                       						sel = i;
                       						break;
                       					}
                       				}
                       			}
                   			}
                       		
                   			var node = _nodeList[sel];
           					if(node.isParent){
           						_staffTree.expandNode(node, true, false, true, false);	
           					} else {
           						_staffTree.expandNode(node.getParentNode(), true, false, true, false);
  	            			}
           					
           					_staffTree.selectNode(node, false);
           					
   	                		_staffFilter.focus();
                   		} else {
                   			alert('没有找到"' + txt + '"对应的项。');
                   		}
                	}
            	} catch(e){
            		alert(e.message);
            	}
			}
        });
		
		$('#divStaffFilter input').each(function(){
			_staffFilter = $(this);
			$(this).css({width: "50%"});
			return false;
		});
		
		$('#btnSelUser').focus();
	});
	
	function getStaffTree() {
       postJSON(rootPath + "/message/MessageAction!getPersons.action",  showStaffTee);
    }
    
    function showStaffTee(json) {
    	if (!checkJSONResult(json)) {
            return;
        }
		
        buildeStaffTree(json.data.staffList);
        
        initReceiveMan();
    }
    
    function initReceiveMan() {
    	if(_receiveMan == null || _receiveMan.length == 0){
    		return;
    	}
    	
    	if(_staffTree == null){
    		return;
    	}
    	
    	var mans = _receiveMan.split(",");
    	for(var idx in mans){
    		var man = mans[idx];
    		var treeNode = _staffTree.getNodeByParam("staff_id", man);
        	if(treeNode){
        		addSel(treeNode);
        	}
    	}
    	
    	onSelOk();
    }
	
	function buildeStaffTree(staffs){
	    try{
	    	var root = -1;
	    	var trees = [];
	    	var staffnum = 0;
	    	trees.push({"id": 0, "pId": root, "name": system_name, "open": true, "font": {"color": "#f00"}, "iconSkin" : "system"});
	        if (powersi.isarray(staffs)) {
	            for (var idx in staffs) {
	                var staff = staffs[idx];
	                var treeNode = {"id": staff.dept_id, "pId": staff.dept_up_id, "isstaff": staff.isstaff,"staff_id":staff.staff_id};
	                if(staff.isstaff == "1"){
	                	treeNode["font"] = {"color": "#00f", "background-color":"transparent"};
	                	treeNode["iconSkin"] = "user";
	                	treeNode["name"] = staff.dept_name + '(' + staff.short_name + ')';
	                	treeNode["text"] = staff.dept_name;
	                	staffnum ++;
	                } else {
	                	treeNode["iconSkin"] = "dept";
	                	treeNode["name"] = staff.dept_name;
	                	treeNode["text"] = staff.dept_name;
	                }
	                trees.push(treeNode);
	            }//end for menus
	        }
	        
	        var upSetting = {
    			view: {
    				fontCss: getTreeFont,
    				autoCancelSelected: false,
    				selectedMulti: false,
    				dblClickExpand: false,
    				showLine: false,
    				expandSpeed: 0
    			},
    			data: {
    				simpleData: {
   					enable: true,
   					rootPId: root
    				}
    			},
				callback: {
					onClick: onTreeClick
				}
	    	};
	        $.fn.zTree.init($("#tvUp"), upSetting, trees);
	        trees.length = 0;
	        
	        _staffTree = $.fn.zTree.getZTreeObj("tvUp");
	        $('#staffNum').text(staffnum);
	    } catch(e){
	    	alert(e.message);
	    }
	}
	
    function onTreeClick(event, treeId, treeNode, clickFlag){
    	if(treeNode.isstaff == "1"){
    		addSel(treeNode);
    	} else {
    		_staffTree.expandNode(treeNode);
    	}
    }
    
    function addSel(treeNode) {
    	if(treeNode == null){
    		return;
    	}
    	
    	$("#tabUser").find("tbody:last").append("<tr id='r" + treeNode.staff_id + "'><td class='textLeft textInfo'>" + treeNode.text + "</td><td class='icon delete' title='删除' onclick='removeSel(\"" + treeNode.staff_id + "\")'></td><td class='hidden'>" + treeNode.pId + "</td></tr>");
		_staffTree.hideNode(treeNode);
		updateUserCount();
    }
    
    function removeSel(userId){
    	var treeNode = _staffTree.getNodeByParam("staff_id", userId);
    	if(treeNode){
    		_staffTree.showNode(treeNode);
    	} else {
    		alert("找不到" + userId);
    		return;
    	}
    	
    	 $("#r" + userId).remove();
    	 updateUserCount();
    }
    
    function removeAllSel(){
    	$('#tabUser tbody').html('');
    	_staffTree.showNodes(_staffTree.getNodesByParam("isHidden", true));
    	updateUserCount();
    }
    
    function updateUserCount(){
    	var size = $('#tabUser tbody tr').size();
    	$('#selNum').text(size);
    }
    
    var dlg;
	function selPersons(){
   		clearFormValidtion($('#messageForm'));
   		
   		if(dlg){
			dlg.show();
		} else{
			dlg = popupDiv("#divUpStaff", '选择收件人', 580);
		}
	}

	function getSelectUser() {
		var sels = new Array();

		$('#tabUser tbody tr').each(function() {
			var staff = {};
			staff.id = $(this).attr('id').substring(1);
			staff.name = $(this).find("td").eq(0).text();
			staff.pid = $(this).find("td").eq(2).text();

			sels.push(staff);
		});

		return sels;
	}

	function onSelCancel() {
		hideSelUser();
	}

	function hideSelUser() {
		if(dlg){
			dlg.hide();
		}
	}

	function sendMessage() {
		if (!checkForm()) {
			return false;
		}

		if($('#sysMessagereveiveMan').val().length == 0){
			alert('请选择接收人');
			$('#btnSelUser').focus();
			return false;
		}
		
		if ($('#msTitle').val().length > 100) {
			alert('消息标题最多100个字');
			$('#msTitle').focus();
			return false;
		}
		
		if ($('#msContent').val().length > 1000) {
			alert('消息内容最多1000个字');
			$('#msContent').focus();
			return false;
		}

		if (!confirm("您确认发送消息吗?")) {
			return false;
		}

		postJSON(rootPath + "/message/MessageAction!sendMessage.action", $(
				"#messageForm").serializeArray(), sendBack);
		return true;
	}

	function sendBack(json) {
		if (!checkJSONResult(json))
			return;

		alert("发送成功");

		removeAllSel();

		$("input[type='text'], textarea, input[type='hidden']").each(
				function() {
					$(this).val('');
				});

		$('#msTitle').focus();
		
		if('<%=closeFlag%>' === 'true'){
			window.close();
		}
	}

	function onSelOk() {
		var sels = getSelectUser();
		if (sels.length > 50) {
			alert('最多选择50个接收人');
			return;
		}

		var showname = [];
		for ( var idx in sels) {
			showname.push(sels[idx].name);
		}

		var names = showname.join(",");

		$("#staff_up_name").val(names);

		$("#sysMessagereveiveMan").val(powersi.tostring(sels));
		$("#sysMessagereveiveManNames").val(names);

		hideSelUser();
		$('#msTitle').focus();
	}
</script>
</body>
</powersi:html>