<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.powersi.hygeia.framework.ParameterMapping" %>
<%@ page import="com.powersi.hygeia.framework.util.UtilFunc" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%
	String systemName = ParameterMapping.getStringByCode("center_org_name", ParameterMapping.getSystemName());

	com.powersi.hygeia.framework.UserInfo user = com.powersi.hygeia.framework.BusiContext.getUserInfo();
	String centerFilter = "1 = 0";
	String levelFilter = "1 = 0";
	String staffLevel = UtilFunc.getString(user, "staff_level", "9");
	if(staffLevel.equals("1") || user.isSuperUser()){
		centerFilter = "";
	} else {
		if(staffLevel.equals("2")){
			centerFilter = "aaa027 in (" + UtilFunc.getString(user, "center_list", "0") + ")";	
		} else {
			String center_id = UtilFunc.getString(user, "center_id", "0");
			center_id = StringUtils.isBlank(center_id) ? "0":center_id;
			centerFilter = "aaa027 = " + center_id;
		}
	}
	
	String deptId = "0";
	if(Integer.parseInt(UtilFunc.getString(user, "grade_id", "0")) < 4 && !user.isSuperUser()){
		deptId = UtilFunc.getString(user, "dept_id", "-1");
	}
	
	String upServerFlag = ParameterMapping.getStringByCode("up_server_flag", "0");
%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="部门管理" />
<script src="${strutsPath}/include/ztree.js" type="text/javascript"></script>
<script src="${rootPath}/resource/js/jquery.quickfilter.js" type="text/javascript"></script>
<style type="text/css">
html,body{overflow: hidden;}
#divFilter{clear: both;vertical-align: middle;text-align: center;}
#divTree{overflow-x: auto;overflow-y:scroll;text-align:left;white-space:nowrap;width:100%;margin-bottom:10px;}
</style>
</head>
<body class="page">
	<powersi:pagelayout id="layout" allowLeftResize="false" leftWidth="300" onEndResize="setSize" space="-1">
		<powersi:pagelayout-panel position="left">
			<powersi:pagelayout-panel-title>
				<i class="icon-sitemap"></i><span>部门机构树</span>
			</powersi:pagelayout-panel-title>
			<div id="divTree">
				<ul id="tvDept" class="ztree">
				</ul>
			</div>
			<div id="divFilter"></div>
		</powersi:pagelayout-panel>
		<powersi:pagelayout-panel position="center">
			<powersi:tabbedpanel id="divTabs">
				<powersi:tab id="tab1" target="divTab1" label="部门信息" />
				<powersi:tab id="tab2" target="divTab2" label="角色信息" />
				<powersi:tab id="tab3" target="divTab3" label="用户信息" />
				<powersi:tab id="tabBtn">
					<div id="tab1_btn">
						<input type="button" value="新 增" id="btAdd" 
							class="button" onclick="doAdd()" title="新增部门" />
						<input type="button" value="复 制" id="btCopy"
							class="button" onclick="doCopy()" title="复制部门" />
						<input type="button" value="保 存" id="btSave"
							class="button" onclick="doSave()" title="保存部门" />
						<input type="button" value="删 除" id="btDel"
							class="button" onclick="doDel()" title="删除部门" />
					</div>
					<div id="tab2_btn" style="display:none;">
						<input type="button" value="批量修改角色对应的部门" id="btModifyRoles" 
							class="button" onclick="doModifyRoles()" title="修改勾选的角色对应部门" />
					</div>
					<div id="tab3_btn" style="display:none;">
						<input type="button" value="批量修改用户对应的部门" id="btModifyUsers" 
								class="button" onclick="doModifyUsers()" title="修改勾选的用户对应部门" />
					</div>
				</powersi:tab>
				<div id="divTab1">
					<div id="divDept">
						<powersi:form id="deptForm" disabled="true">
							<input name="dept_up_id" type="hidden" id="dept_up_id" />
							<input name="dept_id" type="hidden" id="dept_id" />
							<powersi:editorlayout cols="25%,75%">
								<powersi:editorlayout-head title="部门基本信息"></powersi:editorlayout-head>
								<powersi:editorlayout-row>
									<powersi:textfield label="上级部门" name="dept_up_name" id="dept_up_name" required="true" readonly="true" 
									buttonId="btnSelectUp" buttonTitle="选择上级部门" onbuttonclick="showUpDept(1)" buttonDisabled="true">
									</powersi:textfield>
								</powersi:editorlayout-row>
								<powersi:editorlayout-row>
									<powersi:textfield label="部门名称" id="dept_name" name="dept_name" required="true" />
								</powersi:editorlayout-row>
								<powersi:editorlayout-row cssClass="hidden">
									<powersi:textfield label="部门简称" id="short_name" name="short_name" maxlength="20"/>
								</powersi:editorlayout-row>
								<powersi:editorlayout-row cssClass="hidden">
									<powersi:radio id="dept_type" name="dept_type" label="部门类型" list="#{'1': '机构', '2':'部门'}" required="true" />
								</powersi:editorlayout-row>
								<powersi:editorlayout-row>
									<powersi:codeselect name="center_id" id="center_id"
										label="所属中心" codeType="aaa027_list" codeFilter="<%=centerFilter %>" 
										cssClass="select2" />
								</powersi:editorlayout-row>
								<powersi:editorlayout-row>
									<powersi:spinner key="dis_order" id="dis_order" name="dis_order" required="true" validate="integer" 
										maxlength="6" min="0" max="999999" mouseWheel="true" />
								</powersi:editorlayout-row>
								<powersi:editorlayout-row>
									<powersi:textfield label="地址" id="address" name="address" maxlength="100"/>
								</powersi:editorlayout-row>
								<powersi:editorlayout-row>
									<powersi:textfield label="负责人" id="principal" name="principal" maxlength="15" />
								</powersi:editorlayout-row>
								<powersi:editorlayout-row>
									<powersi:textfield label="联系人" id="linkman" name="linkman" maxlength="15" />
								</powersi:editorlayout-row>
								<powersi:editorlayout-row>
									<powersi:textfield label="联系电话" id="tel" name="tel" maxlength="30"/>
								</powersi:editorlayout-row>
							</powersi:editorlayout>
							<div class="space-y"></div>
							<div id="unifiedTip" style="dispaly:none;" class="textLeft red">
								<span>该部门由统一门户维护</span>
							</div>
						</powersi:form>
					</div>
				</div>
				<div id="divTab2">
					<div id="divRole">
						<powersi:datagrid id="gridRole" usePager="false" checkbox="true"
               				frozen="false" selectRowButtonOnly="true" exportFileName="exportRoleFile"
               				inWindow="false" widthDiff="-2">
	               			<powersi:toolbar>
	               				<powersi:toolbar-item id="list" text="角色列表" title="该部门拥有的角色列表" icon="info" />
	               				<powersi:toolbar-item id="detail" text="查看已选择角色" title="显示当前勾选的角色列表" icon="view" click="showRoleList" position="right"/>
	               				<powersi:toolbar-item id="export" text="导出角色列表" title="导出该部门的角色列表" icon="excel" click="exportRoleList" position="right"/>
							</powersi:toolbar>
							<powersi:datagrid-column display="角色名称" name="role_name" width="30%" minWidth="100" />
							<powersi:datagrid-column display="角色描述" name="role_desc" width="30%" minWidth="100" />
							<powersi:datagrid-column display="所属部门" name="dept_name" width="40%" minWidth="150" />
               			</powersi:datagrid>
					</div>
				</div>
				<div id="divTab3">
					<div id="divUser">
						<powersi:datagrid id="gridUser" usePager="false" checkbox="true"
              				frozen="false" selectRowButtonOnly="true" exportFileName="exportUserFile"
              				inWindow="false" widthDiff="-2">
	               			<powersi:toolbar>
	               				<powersi:toolbar-item id="list" text="用户列表" title="该部门拥有的用户列表" icon="info" />
	               				<powersi:toolbar-item id="detail" text="查看已选择用户" title="显示当前勾选的用户列表" icon="view" click="showUserList" position="right"/>
	               				<powersi:toolbar-item id="export" text="导出用户列表" title="导出该部门的用户列表" icon="excel" click="exportUserList" position="right"/>
							</powersi:toolbar>
							<powersi:datagrid-column key="login_user" name="login_user" width="25%" minWidth="90" render="renderLoginUser"/>
							<powersi:datagrid-column key="user_name" name="staff_name" width="25%" minWidth="90" />
							<powersi:datagrid-column display="所属部门" name="dept_name" width="30%" minWidth="90" />
							<powersi:datagrid-column display="状态" name="staff_sta" width="20%" minWidth="50" render="renderStaffSta" code="staff_sta"/>
              			</powersi:datagrid>
					</div>
				</div>
			</powersi:tabbedpanel>
		</powersi:pagelayout-panel>
	</powersi:pagelayout>
	<powersi:errors />
	<!-- 隐藏区 -->
	<div class="hidden">
		<div id="divUpDept">
			<div class="textSuccess textLeft"><span>当前选择的</span><span id="lbUpText"></span><span id="lbUpName"></span></div>
			<div style="height: 390px; overflow:auto;border: #d6d6dd 1px solid;;">
				<ul id="tvUp" class="ztree"></ul>
			</div>
			<powersi:buttons>
				<powersi:button id="btnUpOk" key="button_ok" buttonIcon="" onclick="onUpOk()" />
				<powersi:button id="btnUpCancel" key="button_cancel" buttonIcon="" onclick="onUpCancel()" />
			</powersi:buttons>
		</div>
	</div>
<script type="text/javascript">
    var SYSTEM_NAME = "<%=systemName%>";
    var SYSTEM_ID = "0";
    var ROOT_DEPTID = <%=deptId%>;
    var NEW_DEPTID = "-1";
    var _curItemId = ROOT_DEPTID;
    var _curItemData = "";
    var _deptList = [];
    var _applyFlag = false;

    var _deptFilter = null;
    var _lastFind = null;
    var _nodeList = [];
    
    function setSize() {
    	var winheight = $(window).height();
    	
    	$("#divTree").height(winheight - 100);
    	$("#divTabs").height(winheight - 90);
        $("#divDept").height(winheight - 137);
        
        $('#divRole').width($('#divTabs').width() - 2).height(winheight - 60);
        if(gridRole){
        	gridRole.refreshSize();
        }
        $('#divUser').width($('#divTabs').width() - 2).height(winheight - 60);
        if(gridUser){
        	gridUser.refreshSize();
        }
    }
    
    $(document).ready(function() {
        setSize();
        
        //切换tab按钮
        bindAfterSelectTab('#divTabs', function(newTabId, oldTabId){
        	if(oldTabId){
        		$('#' + oldTabId + "_btn").hide();
        	}
        	if(newTabId){
        		$('#' + newTabId + "_btn").show();
        	}
        });
        
        window.onbeforeunload = function(){
			doApply();
		};

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
      	                		clickTree(node.id);
      	                		
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
			$(this).css({width: "50%"});
			return false;
		});
           
        getDeptTree();
    });

    function getDeptTree() {
        postJSON(rootPath + "/manager/DeptManager!getDeptTree.action", {"root_dept_id": ROOT_DEPTID}, showDeptTee);
    }

    function showDeptTee(json) {
    	if (!checkJSONResult(json)) {
            return;
        }

    	_deptList.length = 0;
        _deptList = json.data;
        
        buildeDeptTree(_deptList);
    }

    function clickTree(itemid) {
        if (itemid == _curItemId) {
            return;
        }

        getDeptInfo(itemid);
    }

    function getDeptInfo(deptId){
    	$('#tab1_btn :button').not("#btAdd").hide();
    	
        _curItemId = deptId;
        
        $('#deptForm').resetForm();
        $('#deptForm :checkbox').removeAttr("checked");
        clearTableData();
        
        postJSON(rootPath + "/manager/DeptManager!getDeptInfo.action", {"dept_id": _curItemId}, showDeptInfo);
    }

    function showDeptInfo(json) {
        if (!checkJSONResult(json)) {
            return;
        }

        $('#tab1_btn :button').show();
		if(_curItemId == SYSTEM_ID) {
			$('#btCopy').hide();
			$('#btSave').hide();
			$('#btDel').hide();
			
			$('#dept_name').val(SYSTEM_NAME);
        }

        var data = json.data;
        for(var k in data) {
        	$('#' + k).val(data[k]);
        }
        if(data["dept_type"]){
        	$('#dept_type' + data['dept_type']).prop('checked', true);
        }
       	
        //触发select2
        $('#deptForm select').change();
       	
        buildRoleList(json.data.roles);
        buildUserList(json.data.users);
        
        showUpDeptName(data["dept_up_id"]);
        
        _curItemData = $('#deptForm').serialize();
        
        $("#divTabs").tabs("enable", 1);
   		$("#divTabs").tabs("enable", 2);
   		
   		//unifiedCtrl(data['unified_flag']);
   		unifiedCtrl(('<%=upServerFlag%>' == '1') ? '0' : data['unified_flag']);
   		
   		if(data["dept_id"] == ROOT_DEPTID){
   			$("#btnSelectUp").prop('disabled', true);
   		}
    }
    
    function showUpDeptName(deptId){
    	var upDeptName = "";
        if(deptId != SYSTEM_ID){
        	var upDept = findDept(deptId);
	        if(upDept){
	        	upDeptName = upDept.dept_name;
	        }
        } else {
        	upDeptName = SYSTEM_NAME;
        }
        
        $('#dept_up_name').val(upDeptName);
    }

    function findDept(deptId) {
        var dept = null;
        if (!powersi.isvalue(_deptList)) {
            return dept;
        }

        for (var i in _deptList) {
            if (_deptList[i].dept_id == deptId) {
                dept = _deptList[i];
                break;
            }
        }

        return dept;
    }
   
    function onTreeClick(event, treeId, treeNode, clickFlag) {
    	expandNode(treeId, treeNode);
		
    	clickTree(treeNode.id);
	}
    
    function expandNode(treeId, treeNode){
    	var zTree = $.fn.zTree.getZTreeObj(treeId);
		zTree.expandNode(treeNode);
    }
    
    function selectDept(deptId){
    	var zTree = $.fn.zTree.getZTreeObj("tvDept");
    	var node = zTree.getNodeByParam("id", deptId, null);
    	if(node){
    		if(node.isParent){
				zTree.expandNode(node, true, false, true, false);	
			} else {
				zTree.expandNode(node.getParentNode(), true, false, true, false);
			}
    		zTree.selectNode(node, false);
    	}
    }
    
    function buildeDeptTree(depts) {
	    try{
	    	var root = -1;
	    	var trees = [];
	    	
	    	if(SYSTEM_ID == ROOT_DEPTID) {
	    		trees.push({"id": 0, "pId": root, "name": SYSTEM_NAME, "open": true, "font": {"color": "#f00"}, "iconSkin" :"home", "isdept": "1"});
	    	}
	    	
	        if (powersi.isarray(depts)) {
	            for (var idx in depts) {
	                var dept = depts[idx];

	                var treeNode = {"id": dept.dept_id, "pId": dept.dept_up_id, "name": dept.dept_name};
	                treeNode["iconSkin"] = "dept";
	            	treeNode["isdept"] = "1";
	            	
	               if(dept.dept_type == "1"){
	                 treeNode["font"] = {"color": "#00f", "background-color":"transparent"};
	               }
	               
	               trees.push(treeNode);
	            }//end for depts
	        }
	        
	        var setting = {
	    			view: {
	    				fontCss: getTreeFont,
	    				autoCancelSelected: false,
	    				selectedMulti: false,
	    				dblClickExpand: false,
	    				showLine: false
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
	        $.fn.zTree.init($("#tvDept"), setting, trees);
	        
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
						onClick: onTreeUpDeptClick,
						onCheck: onSelectUpDept
					},
					check: {
						enable: true,
						chkStyle: "radio",
						radioType: "all",
						autoCheckTrigger: false
					}
	    	};
	        $.fn.zTree.init($("#tvUp"), upSetting, trees);
	        
	      	//统计子节点记录数
	      	if(trees.length > 0){
	      		var deptTree = $.fn.zTree.getZTreeObj("tvDept");
				var depts = deptTree.getNodesByParam("isdept", 1, null);
				for(var idx in depts){
					var dept = depts[idx];
					var len = deptTree.getNodesByParam("isdept", "1", dept).length;
					if(len > 0){
						dept.name = dept.name + "[" + len + "]";
						deptTree.updateNode(dept);
					}
				}
	      	}
	      	
	        trees.length = 0;
	    } catch(e){
	    	alert(e.message);
	    }
       
        getDeptInfo(_curItemId);
        selectDept(_curItemId);
    }

    function buildRoleList(roles){
    	gridRole.loadData(roles || []);
    }
    
    function buildUserList(users){
    	gridUser.loadData(users || []);
    }
    
    function clearTableData(){
    	gridRole.reset();
    	gridUser.reset();
    }
    
    function doAdd() {
        $('#deptForm').resetForm();
        $('#deptForm :checkbox').removeProp("checked");
        $('#deptForm :radio').removeProp("checked");
        $('#dept_type2').prop('checked', true);
        
        $('#dept_up_id').val(_curItemId);
        $('#dept_id').val("-1");
        showUpDeptName(_curItemId);
        
      	//触发select2
        $('#deptForm select').change();
      
        _curItemId = NEW_DEPTID;

        $('#btAdd').hide();
        $('#btCopy').hide();
        $('#btDel').hide();
        
        $('#btnSelectUp').show();
        $('#btSave').show();

        clearTableData();
        $("#divTabs").tabs("disable", 1);
   		$("#divTabs").tabs("disable", 2);
        
   		unifiedCtrl('0');
   		
        $('#dept_name').focus();
    }

    function doCopy() {
        $('#dept_id').val("-1");
        $('#dept_name').val("复制 " + $('#dept_name').val());

        var intValue=parseInt($('#dis_order').val());
        if (!isNaN(intValue)) {
        	$('#dis_order').val(intValue + 1);
        } 

        _curItemId = NEW_DEPTID;

        $('#btAdd').hide();
        $('#btCopy').hide();
        $('#btDel').hide();

        clearTableData();
        $("#divTabs").tabs("disable", 1);
   		$("#divTabs").tabs("disable", 2);
   		
   		unifiedCtrl('0');
   		
        $('#dept_name').focus();
    }

    function doSave() {
        if (_curItemId == SYSTEM_ID) {
            alert("系统部门，不能保存");
            return;
        }
        
        var saveItemData = $("#deptForm").serialize();
        if (_curItemId != NEW_DEPTID) {
            if (saveItemData == _curItemData) {
                alert("没有修改，不需保存");
                return;
            }
        }

        if(!checkForm()){
        	return;
        }

        if($("#dept_id").val() == $("#dept_up_id").val()){
	        alert("上级部门不能选择自己");
	        $("#dept_up_id").focus();
	        return;
        }
        
        postJSON(rootPath + "/manager/DeptManager!saveDept.action", saveItemData, saveSuccess);
    }

    function saveSuccess(json){
	    if(!checkJSONResult(json)){
		    return;
	    }

	    _applyFlag = true;
	    
	    _curItemId = json.data.dept_id;

	    getDeptTree();

	    $("#divTabs").tabs("enable", 1);
   		$("#divTabs").tabs("enable", 2);
   		
	    alert('保存成功');
    }

    function doDel() {
        if (_curItemId == SYSTEM_ID) {
            alert("系统部门，不能删除");
            return;
        }

        if (_curItemId == NEW_DEPTID) {
            alert("没有保存，不能删除");
            return;
        }

        var dept = findDept(_curItemId);
        if (!powersi.isvalue(dept)) {
            alert("无法获取当前部门信息，不能删除");
            return;
        }

        var deptName = dept.dept_name;
        if (!confirm("您确认删除[" + deptName + "]吗?")) {
            return;
        }

        postJSON(rootPath + "/manager/DeptManager!delDept.action", dept, delSuccess);
    }

    function delSuccess(json){
	    if(!checkJSONResult(json)){
		    return;
	    }

	    _applyFlag = true;
		
	    var dept = findDept(_curItemId);
	    if(powersi.isvalue(dept)){
	    	_curItemId = dept.dept_up_id;
	    } else {
	    	_curItemId = ROOT_DEPT_ID;
	    }
	    
	    getDeptTree();
    }
    
    function doApply(){
    	if(_applyFlag){
			_applyFlag = false;
		}
    }
    
	var dlg;
	var showDeptType = 1;
    function showUpDept(showType) {
    	try{
    		showDeptType = showType;
    		
    		var upText = null;
    		var upName = null;
    		var deptUpId = null;
    		if(showDeptType == 1) {
    			upText = "上级部门";
    			upName = $('#dept_up_name').val();
    			deptUpId = $('#dept_up_id').val();
    		}
    		else {
    			upText = "部门";
    			upName = $('#dept_name').val();
    			deptUpId = $('#dept_id').val();
    		}
    		
    		$('#lbUpText').text(upText);
    		$('#lbUpName').text(upName);
    		
    		$('#btnUpOk').show();
    		$('#btnUpCancel').show();
    		
    		var zTree = $.fn.zTree.getZTreeObj("tvUp");
    		zTree.expandAll(false);
    		
    		var node = zTree.getNodeByParam("id", deptUpId, null);
    		if(node == null){
    			alert('无法获取' + upText + '.');
    			return;
    		}
    		
    		zTree.checkNode(node, true, false, false);
    		if(node.isParent){
				zTree.expandNode(node, true, false, true, false);	
			} else {
     				zTree.expandNode(node.getParentNode(), true, false, true, false);
     			}
    		zTree.selectNode(node, false);
    		
    		if(dlg){
    			dlg.show();
    		} else{
    			dlg = popupDiv("#divUpDept", '选择' + upText, 450);
    		}
    		/*
    		$('body').block({ 
    			message: $('#divUpDept'),
    		 	css: { 
                    width: '400px', 
                    top: '10px', 
                    left: '', 
                    right: '10px', 
                    border: 'none', 
                    padding: '5px', 
                    backgroundColor: '#fff', 
                    "-moz-border-radius": '10px',
    				"-khtml-border-radius": '10px',
    				"-webkit-border-radius": '10px',
    				"border-radius": '10px', 
                    color: '#07519a' ,
                    border: '5px solid #cad5eb',
                    cursor: 'auto'
                },
                overlayCSS: {
                	backgroundColor: '#e6e6e6'
                }
    		});
    		*/
    	} catch(e){
    		alert(e.message);
    	}
	}
    
    function hideUpDept() {
    	//$('body').unblock();
    	if(dlg){
   			dlg.hide();
    	}
	}
    
    function onSelectUpDept(e, treeId, treeNode){
    	var zTree = $.fn.zTree.getZTreeObj("tvUp");
		var nodes = zTree.getCheckedNodes(true);
		if(nodes.length == 0){
			return;
		}
		
		$('#lbUpName').text(nodes[0].name);
    }
    
    function onTreeUpDeptClick(event, treeId, treeNode, clickFlag) {
    	expandNode(treeId, treeNode);
	}
    
    function onUpOk(){
    	var zTree = $.fn.zTree.getZTreeObj("tvUp");
		var nodes = zTree.getCheckedNodes(true);
		if(nodes.length == 0){
			return;
		}
		
		if(showDeptType == 1){
			$('#dept_up_id').val(nodes[0].id);
			$('#dept_up_name').val(nodes[0].name);
			
			hideUpDept();
		} else {
			if(nodes[0].id == _curItemId){
				alert('没有修改, 无需保存');
				return;
			}
			
			hideUpDept();
			
			if(showDeptType == 2){
				saveModifyRoles(nodes[0].id, nodes[0].name);
			} else if(showDeptType == 3){
				saveModifyUsers(nodes[0].id, nodes[0].name);
			}
		}
    }
    
    function onUpCancel(){
    	hideUpDept();
    }
    
    function doModifyRoles(){
    	if(powersi.rows_length(gridRole.getSelectedRows()) == 0) {
    		alert('请先勾选需要修改的角色');
    		return;
    	}
    	
    	showUpDept(2);
    }
    
    function saveModifyRoles(deptId, deptName){
    	var a = gridRole.getSelectedRows();
    	
    	if(a == null || a.length == 0){
    		return;
    	}
    	
    	var b = [];
    	var c = [];
    	for(var i in a){
    		b.push(a[i].role_id);
    		if(i < 10){
    			c.push(a[i].role_name + '\t\t\t' + (a[i].role_desc || ''));	
    		}
    	}
    	
    	if(b.length > 10){
    		c.push('以下省略 ' + (b.length - 10) + ' 个角色......');
    	}
    	
    	if(confirm('您确认将下列' + b.length + '个角色\n从部门【' + $('#dept_name').val() + '】转移到部门【' + deptName + '】吗？\n' + c.join('\n'))){
	    	postJSON(rootPath + "/manager/DeptManager!batchModifyDept.action", 
	    			{"dept_id" : deptId, "roles" : b.join(",")}, 
	    			modifyRolesSuccess);
    	}
    }
    
    function modifyRolesSuccess(json){
    	if(!checkJSONResult(json)){
		    return;
	    }
    	
    	gridRole.deleteSelectedRow();
    	gridRole.clearCheckbox();
    }
    
    function doModifyUsers(){
    	if(powersi.rows_length(gridUser.getSelectedRows()) == 0) {
    		alert('请先勾选需要修改的用户');
    		return;
    	}
    	
    	showUpDept(3);
    }
    
    function saveModifyUsers(deptId, deptName){
    	var a = gridUser.getSelectedRows();
    	if(a == null || a.length == 0){
    		return;
    	}
    	
    	var b = [];
    	var c = [];
    	for(var i in a){
    		b.push(a[i].user_id);
    		if(i < 10){
    			c.push(a[i].login_user + '\t\t\t' + (a[i].staff_name || ''));	
    		}
    	}
    	if(b.length > 10){
    		c.push('以下省略 ' + (b.length - 10) + ' 个用户......');
    	}
    	
    	if(confirm('您确认将下列' + b.length + '个用户\n从部门【' + $('#dept_name').val() + '】转移到部门【' + deptName + '】吗？\n' + c.join('\n'))){
    		postJSON(rootPath + "/manager/DeptManager!batchModifyDept.action", 
	    			{"dept_id" : deptId, "users" : b.join(",")}, 
	    			modifyUsersSuccess);
    	}
    }
    
    function modifyUsersSuccess(json){
    	if(!checkJSONResult(json)){
		    return;
	    }

    	gridUser.deleteSelectedRow();
    	gridUser.clearCheckbox();
    }
    
    function viewUserInfo(staffid){
    	popupDialog(rootPath + "/user/UserInfoAction!queryUserInfo.action?staff_id="+staffid, 450, 600);
    }
    
    function unifiedCtrl(unifiedFlag){
    	if(unifiedFlag == "1"){
    		$("#unifiedTip").show();
    		
    		$("#btDel").prop("disabled", true);
    		$("#btModifyUsers").prop("disabled", true);
    		$("#btnSelectUp").prop("disabled", true);
    		
    		$("#center_id").prop("disabled", true);
    		$("#dept_name").prop("readonly", true).addClass("textReadonly");
    	} else {
			$("#unifiedTip").hide();
    		
    		$("#btDel").prop("disabled", false);
    		$("#btModifyUsers").prop("disabled", false);
    		$("#btnSelectUp").prop("disabled", false);
    		
    		$("#center_id").prop("disabled", false);
    		$("#dept_name").prop("readonly", false).removeClass("textReadonly");
    	}
    }
    
    function renderStaffSta(rowdata, index, value){
   		if (value == '0') {
   			return '<span title="禁用"><i class="icon-remove textError"></i></span>';
   		} else {
   			return '<span title="启用"><i class="icon-ok textSuccess"></i></span>';
   		}
    }
    
    function renderLoginUser(rowdata, index, value){
    	return '<a href="#" onclick="viewUserInfo(' + rowdata['staff_id'] + ')">' + value + '</a>';
    }
    
    function showUserList() {
    	var rows = gridUser.getSelectedRows();
    	if(powersi.rows_length(rows) == 0){
    		alert("没有选择");
    	} else {
    		alert(powersi.rows_extract(rows, 'staff_name').join("\r\n"));
    	}
    }
    
    function showRoleList() {
    	var rows = gridRole.getSelectedRows();
    	if(powersi.rows_length(rows) == 0){
    		alert("没有选择");
    	} else {
    		alert(powersi.rows_extract(rows, 'role_name').join("\r\n"));
    	}
    }
    
    function exportRoleFile() {
    	return $('#dept_name').val() + "_角色列表";
    }
    function exportRoleList() {
    	gridRole.exportExcel();
    }
    function exportUserFile() {
    	return $('#dept_name').val() + "_用户列表";
    }
    function exportUserList() {
    	gridUser.exportExcel();
    }
</script>
</body>
</powersi:html>