<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.powersi.hygeia.framework.ParameterMapping"%>
<%@page import="com.powersi.hygeia.framework.util.UtilFunc"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	String systemName = ParameterMapping.getStringByCode(
			"center_org_name", ParameterMapping.getSystemName());
	com.powersi.hygeia.framework.UserInfo user = com.powersi.hygeia.framework.BusiContext
			.getUserInfo();
	String deptId = "0";
	if (Integer.parseInt(UtilFunc.getString(user, "grade_id", "0")) < 4) {
		deptId = UtilFunc.getString(user, "dept_id", "-1");
	}
%>

<powersi:html>
<head>
<powersi:head title="选择接收者" target="_self" />
<style type="text/css">
html,body {
	overflow: hidden;
}

#divDept {
	height: 600px;
	overflow-x: auto;
	overflow-y: scroll;
	text-align: left;
	white-space: nowrap;
}
</style>
<script src="${strutsPath}/include/ztree.js" type="text/javascript"></script>
<script src="${rootPath}/resource/js/jquery.quickfilter.js" type="text/javascript"></script>
<script type="text/javascript">
	var system_name = "<%=systemName%>";
    var _userList = [];
    var SYSTEM_ID = "0";
	var ROOT_DEPTID = <%=deptId%>;
	
	var _deptFilter = null;
	var _lastFind = null;
	var _nodeList = [];
	    
    $(document).ready(function() {
        try{
        	setSize();
	        $(window).resize(setSize);
	        
			$('#tvDept').quickfilter({
	        	srcElement: '',
                attached: '#divFilter',
                stripeRowClass: null,
                inputText: '查找 部门',
                inputTitle: '输入关键字后，按回车键查找符合条件的数据',
                labelText: '',
                filter: function(i){
                	return "";
                },
                onAfter: function(){
                	try{
                		if(_deptFilter){
                    		var txt = powersi.trim(_deptFilter.val());
                    		var zTree = $.fn.zTree.getZTreeObj("tvDept");
                    		var len = powersi.length(_nodeList);
                    		if(_lastFind !== txt) {
                    			_lastFind = txt;
                    			
                    			for( var i = 0; i < len; i++) {
                    				_nodeList[i].highlight = false;
                    				zTree.updateNode(_nodeList[i]);
                    			}
                        		_nodeList.length = 0;
                        		if(txt.length > 0){
                        			_nodeList = zTree.getNodesByParamFuzzy("name", txt, null);
                        		}
                        		
                        		len = powersi.length(_nodeList);
                        		for( var i = 0; i < len; i ++) {
                        			_nodeList[i].highlight = true;
       	            				zTree.updateNode(_nodeList[i]);
       	            			}
                    		}
                    		
                    		if(txt.length == 0){
                    			return;
                    		}
                    		
                       		if(len > 0){
                       			var sel = 0;
                       			if(len > 1){
                       				var curNodes =  zTree.getSelectedNodes();
                           			if(curNodes.length > 0){
                           				var curIdx =  getTreeOrder("tvDept", curNodes[0]);
                           				for(var i = 0; i < len; i ++){
                           					var idx = getTreeOrder("tvDept", _nodeList[i]);
                           					if(idx > curIdx){
                           						sel = i;
                           						break;
                           					}
                           				}
                           			}
                       			}
                           		
                       			var node = _nodeList[sel];
               					if(node.isParent){
               						zTree.expandNode(node, true, false, true, false);	
               					} else {
      	            				zTree.expandNode(node.getParentNode(), true, false, true, false);
      	            			}
               					
               					zTree.selectNode(node, false);
       	                		
       	                		_deptFilter.focus();
                       		} else {
                       			alert('没有找到"' + txt + '"对应的部门。');
                       		}
                    	}
                	} catch(e){
                		alert(e.message);
                	}
				}
            });
			
			$('#divFilter input').each(function(){
				_deptFilter = $(this);
				$(this).css({width: "99%"});
				return false;
			});
			
			var retVal = getDialogParam();
			if(retVal != null){
				$("#receiveString").val(retVal.receive_string || "");
		  		$("#receiveUsers").val(retVal.receive_users || "");
			}
			
			postJSON(rootPath + "/message/BulletinEditAction!getReceiveUsers.action?type=9",  showDeptTree);
		} catch (e) {
			alert('获取机构树出错：' + e.message);
		}
	});
    
    function showDeptTree(json){
    	if (!checkJSONResult(json)) {
    		closeDialog();
    	    return;
        }
    	
    	_userList.length = 0;
    	_userList = json.data;
    	
		buildeReceiveTree(_userList);
    }
    
    function getTreeFont(treeId, node) {
    	return (!!node.highlight) ? {"background-color":"#ffff96"} : (node.font ? node.font : {"background-color":"transparent"});
	}
    
    function getTreeOrder(treeId, node){
    	return parseFloat(node.tId.substring(treeId.length + 1));
    }
    
    function expandNode(treeId, treeNode){
    	var zTree = $.fn.zTree.getZTreeObj(treeId);
		zTree.expandNode(treeNode);
    }

	function buildeReceiveTree(users) {
		try {
			var root = -1;
			var trees = [];
			
			var sels = [];
			var str = $('#receiveString').val();
			if(str != null && str.length > 0){
				sels = powersi.tojson(str);
			}
			
			if (SYSTEM_ID == ROOT_DEPTID) {
				var treeNode = {
						"id" : 0,
						"pId" : root,
						"name" : system_name,
						"open" : true,
						"font" : {
							"color" : "#f00"
						},
						"iconSkin" : "system",
						"isstaff" : "0"
				};
				
				for(var i in sels){
					if(sels[i].receive_org == "0") {
						treeNode["checked"] = true;
						break;
					}
				}
				
				trees.push(treeNode);
			}

			if (powersi.isarray(users)) {
				for (var idx in users) {
					var u = users[idx];
					var treeNode = {
						"id" : u.dept_id,
						"pId" : u.dept_up_id,
						"name" : u.dept_name,
						"isstaff" : u.isstaff
					};
					if (u.isstaff == "1") {
						treeNode["font"] = {
							"color" : "#00f",
							"background-color" : "transparent"
						};
						treeNode["iconSkin"] = "right";
					} else {
						treeNode["iconSkin"] = "menu";
					}
					
					for(var i in sels) {
						if(sels[i].receive_org == u.dept_id){
							treeNode["checked"] = true;
							break;
						}
					}
					
					trees.push(treeNode);
				}
			}

			var setting = {
				view : {
					fontCss : getTreeFont,
					autoCancelSelected : false,
					selectedMulti : false,
					dblClickExpand : false,
					showLine : false,
					expandSpeed : 0
				},
				data : {
					simpleData : {
						enable : true,
						rootPId : root
					}
				},
				callback : {
					onCheck: onCheckTree
				},
				check : {
					enable : true,
					chkStyle : "checkbox",
					chkboxType : {
						"Y" : "",
						"N" : ""
					},
					autoCheckTrigger : false
				}
			};
			$.fn.zTree.init($("#tvDept"), setting, trees);
			trees.length = 0;
		} catch (e) {
			alert(e.message);
		}
	}

	function getTreeFont(treeId, node) {
		return (!!node.highlight) ? {
			"background-color" : "#ffff96"
		} : (node.font ? node.font : {
			"background-color" : "transparent"
		});
	}

	function onCheckTree(e, treeId, treeNode) {
		var a = [];
		var nodes = $.fn.zTree.getZTreeObj(treeId).getCheckedNodes(true);

		for ( var idx in nodes) {
			a.push(nodes[idx].name);
		}

		$('#receiveUsers').val(a.join(","));
	}
	
	function setSize() {
		var winheight = $.browser.opera && $.browser.version > "9.5" ? document.documentElement["clientHeight"] : $(window).height();
		$("#divDept").height(winheight - 90);
	}

	function selPersons() {
		var selArray = new Array();
		var a = [];
		var nodes = $.fn.zTree.getZTreeObj("tvDept").getCheckedNodes(true);

		for ( var idx in nodes) {
			var node = nodes[idx];
			if (node.id == 0) {
				var staffjson = {};
				
				staffjson.center_id = '0';
				staffjson.receive_org = '0';
				staffjson.receive_staff = '0';
				staffjson.receive_man = system_name;

				selArray.length = 0;
				selArray.push(staffjson);
				
				a.push(staffjson.receive_man);
			} else {
				var user = findUser(node.id);
				if(user != null){
					var staffjson = {};
					
					staffjson.center_id = user.center_id;
					staffjson.receive_org = user.dept_id.toString();
					staffjson.receive_staff = "0";
					staffjson.receive_man = user.dept_name;

					/*
					var parnode = node.getParentNode();
					if (parnode == null) {
						staffjson.receive_org_name = system_name;//接受单位名称，默认保存上级节点
					} else {
						staffjson.receive_org_name = parnode.name;
					}*/
					selArray.push(staffjson);
					
					a.push(staffjson.receive_man);
				}
			}
		}

		var retVal = {
			"receive_string": powersi.tostring(selArray),
			"receive_users": a.join(",")
		};
		
		closeDialog(retVal);
	}

	function onUpCancel() {
		closeDialog();
	}

	function findUser(userId) {
		var user = null;
		if (!powersi.isvalue(_userList)) {
			return user;
		}
		for ( var i in _userList) {
			if (_userList[i].dept_id == userId) {
				user = _userList[i];
				break;
			}
		}
		return user;
	}
</script>
</head>
<body>
	<div style="text-align:left;">
		<input type="hidden" value="" id="receiveString" />
		<input type="text" value="" id="receiveUsers" title="公告接收者" readonly="readonly" class="text" style="background-color: #fff; color: #00f; border:0;"></input>
	</div>
	<table class=tableBorder>
		<tr>
			<td valign="top">
				<div id="divDept">
					<ul id="tvDept" class="ztree" title="勾选的部门包含下级部门的操作员">
					</ul>
				</div>
			</td>
		</tr>
	</table>
	<div class="divButton">
		<div style="float:left;width:150px;">
			<div id="divFilter"></div>
		</div>
		<div style="padding-top:5px; text-align:center;">
			<powersi:button id="btnUpOk" type="button" key="button_ok" onclick="selPersons()"></powersi:button>
			<powersi:button id="btnUpCancel" type="button" key="button_cancel" onclick="onUpCancel()"></powersi:button>
		</div>
	</div>
	<powersi:errors />
</body>
</powersi:html>