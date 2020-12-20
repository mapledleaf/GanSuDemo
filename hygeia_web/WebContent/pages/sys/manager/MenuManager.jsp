<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String systemName = com.powersi.hygeia.framework.ParameterMapping
			.getSystemName();
%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="菜单管理" />
<script src="${rootPath}/resource/js/jquery.quickfilter.js" type="text/javascript"></script>
<script src="${strutsPath}/include/ztree.js" type="text/javascript"></script>
<script src="${strutsPath}/include/multiselect.js" type="text/javascript"></script>
<style type="text/css">
html, body{overflow: hidden;}
#divFilter{clear: both;vertical-align: middle;text-align: center;}
#divTree{overflow-x: auto;overflow-y: scroll;text-align:left;white-space:nowrap;width:100%;margin:0 0 10px 0;}
</style>
</head>
<body class="page">
	<powersi:pagelayout id="layout" allowLeftResize="false" leftWidth="300" heightDiff="0" onEndResize="setSize" space="-1">
		<powersi:pagelayout-panel position="left">
			<powersi:pagelayout-panel-title>
				<i class="icon-sitemap"></i><span>菜单功能树</span>
			</powersi:pagelayout-panel-title>
			<div id="divTree">
				<ul id="tvMenu" class="ztree">
				</ul>
			</div>
			<div id="divFilter"></div>
		</powersi:pagelayout-panel>
		<powersi:pagelayout-panel position="center">
			<powersi:form id="menuForm" disabled="true">
				<powersi:tabbedpanel id="divTabs">
					<powersi:tab id="tab1" target="divTab1" label="菜单信息" />
					<powersi:tab id="tab2" target="divTab2" label="角色信息" />
					<powersi:tab id="tabBtn">
						<label id="lbMenuId" class="red" style="margin-top:3px;"></label>
						<input type="button" value="新 增" id="btAdd"
							class="button" onclick="doAdd()" title="新增菜单" /> 
						<input type="button" value="复 制" id="btCopy"
							class="button" onclick="doCopy()" title="复制菜单" />
						<input type="button" value="保 存" id="btSave"
							class="button" onclick="doSave()" title="保存菜单" />
						<input type="button" value="删 除" id="btDel"
							class="button" onclick="doDel()" title="删除菜单" />
						<input type="button" value="任 务" id="btTask"
							class="button" onclick="doEditTask()" title="编辑任务" />
						<input type="button" value="应 用" id="btApply" 
							class="button apply" onclick="doApply()" title="使配置生效" 
							disabled="disabled" />
					</powersi:tab>
					<div id="divTab1">
						<div id="divMenu">
							<powersi:editorlayout cols="4">
								<powersi:editorlayout-head title="菜单详细信息">
								</powersi:editorlayout-head>
								<powersi:editorlayout-row>
									<powersi:textfield label="上级菜单" name="menu_up_name" id="menu_up_name" colspan="3" required="true" readonly="true" 
										buttonId="btnSelectUp" buttonTitle="选择上级菜单" onbuttonclick="showUpMenu()"></powersi:textfield>
									<input name="menu_up_id" type="hidden" id="menu_up_id" />
									<input name="menu_id" type="hidden" id="menu_id" />
								</powersi:editorlayout-row>
								<powersi:editorlayout-row>
									<powersi:textfield key="menu_name" id="menu_name" name="menu_name" colspan="3" required="true" />
								</powersi:editorlayout-row>
								<powersi:editorlayout-row>
									<powersi:textfield key="menu_desc" id="menu_desc" name="menu_desc" colspan="3" />
								</powersi:editorlayout-row>
								<powersi:editorlayout-row>
									<powersi:textfield label="菜单标识" id="menu_option" name="menu_option" colspan="3" />
								</powersi:editorlayout-row>
								<powersi:editorlayout-row>
									<powersi:textfield label="菜单URL" id="win_name" name="win_name" colspan="3" />
								</powersi:editorlayout-row>
								<powersi:editorlayout-row>
									<powersi:textfield label="图片URL" id="pic_name" name="pic_name" colspan="3" />
								</powersi:editorlayout-row>
								<powersi:editorlayout-row>
									<powersi:spinner key="dis_order" id="menu_order"
										name="menu_order" required="true" validate="integer,min[0],max[999999]" maxlength="6"
										min="0" max="999999" mouseWheel="true" />
									<powersi:radio codeType="menu_type" key="menu_type" name="menu_type" id="menu_type" required="true" />
								</powersi:editorlayout-row>
								<powersi:editorlayout-row>
									<powersi:codeselect codeType="right_flag" headerKey="-1" key="right_flag" name="right_flag" id="right_flag" required="true" onchange="changeRightFlag(this.value)" 
										cssClass="select2" data-show-search="false" />
									<powersi:codeselect codeType="grade_id" headerKey="-1" label="权限级别" name="grade_id" id="grade_id" required="true" 
										cssClass="select2" data-show-search="false" />
								</powersi:editorlayout-row>
								<powersi:editorlayout-row>
									<powersi:codeselect codeType="log_flag" headerKey="-1" key="log_flag" name="log_flag" id="log_flag" required="true" 
										cssClass="select2" data-show-search="false" />
									<powersi:radio codeType="valid_flag" key="valid_flag" name="valid_flag" id="valid_flag" required="true" />
								</powersi:editorlayout-row>
							</powersi:editorlayout>
						</div>
					</div>
					<div id="divTab2">
						<div id="divRole">								
								<powersi:codeselect id="roles" cssClass="multiselect" multiple="true"
									name="roles" list="#request.roleList" listKey="role_id"
									listValue="role_text" />
						</div>
					</div>
				</powersi:tabbedpanel>
			</powersi:form>
		</powersi:pagelayout-panel>
	</powersi:pagelayout>
	<powersi:errors />
	<!-- 隐藏区 -->
	<div class="hidden">
		<div id="divUpMenu">
			<div class="textSuccess textLeft"><span>当前选择的上级菜单：</span><span id="lbUpName"></span></div>
			<div style="height: 390px; overflow:auto;border:1px solid #d6d6dd;margin-top:5px;">
				<ul id="tvUp" class="ztree"></ul>
			</div>
			<div class="divButton">
				<powersi:button id="btnUpOk" key="button_ok" buttonIcon="" onclick="onUpOk()" />
				<powersi:button id="btnUpCancel" key="button_cancel" buttonIcon="" onclick="onUpCancel()" />
			</div>
		</div>
		
		<div id="taskDlg">
			<powersi:form id="taskForm">
				<powersi:editorlayout cols="4">
					<powersi:editorlayout-row>
						<powersi:hidden id="task_menu_id" name="menu_id"></powersi:hidden>
						<powersi:textarea id="task_sql" name="task_sql" label="任务sql" colspan="3"  required="true" maxlength="4000" rows="10" title="任务SQL"
							placeholder=":user_id表示用户编号，:user_kind表示用户类别，:dataRightSql表示数据权限，:roles表示角色列表，:centers表示中心列表，:corps表示单位列表"></powersi:textarea>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="task_desc" name="task_desc" label="任务名称" required="true" maxlength="200"></powersi:textfield>
						<powersi:codeselect id="task_right_type" name="task_right_type" label="权限类型" list="# {'':'无', '0':'统筹区','1':'个人', '2':'单位', '3':'家庭','4':'城职'}" listKey="key" listValue="value" cssClass="select2" data-show-value="true"></powersi:codeselect>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:spinner id="task_order" name="task_order" label="任务优先级" required="true" min="0" max="999999"></powersi:spinner>
						<powersi:radio codeType="valid_flag" key="valid_flag" name="valid_flag" id="edit_valid_flag" required="true" />
					</powersi:editorlayout-row>
				</powersi:editorlayout>
			</powersi:form>
			
			<div class="space-y"></div>
			<div class="divButton">
				<powersi:button id="btTaskSave" key="button_save" onclick="doSaveTask()"></powersi:button>
				<powersi:button id="btTaskDelete" key="button_delete" onclick="doDelTask()"></powersi:button>
				<powersi:button id="btTaskCancel" key="button_close" onclick="closeTaskDlg()"></powersi:button>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	    var SYSTEM_NAME = "<%=systemName%>";
	    var ROOT_MENUID = "0";
	    var NEW_MENUID = "-1";
	    var _curItemId = ROOT_MENUID;
	    var _curItemData = "";
	    var _menuList = [];
	    var _applyFlag = false;
	
	    var _menuFilter = null;
	    var _lastFind = null;
	    var _nodeList = [];
	    $(document).ready(function() {
	        setSize();
	        //$(window).resize(setSize);

	        window.onbeforeunload = function(){
				doApply();
			};

			dg_init("tabRole");
			
			$('#tvMenu').quickfilter({
	        	srcElement: '',
                attached: '#divFilter',
                stripeRowClass: null,
                inputText: '查找 菜单',
                inputTitle: '输入关键字后，按回车键查找符合条件的数据',
                labelText: '',
                filter: function(i){
                	return "";
                },
                onAfter: function(){
                	try{
                		if(_menuFilter){
                    		var txt = powersi.trim(_menuFilter.val());
                    		var zTree = $.fn.zTree.getZTreeObj("tvMenu");
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
                           				var curIdx =  getTreeOrder("tvMenu", curNodes[0]);
                           				for(var i = 0; i < len; i ++){
                           					var idx = getTreeOrder("tvMenu", _nodeList[i]);
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
       	                		
       	                		_menuFilter.focus();
                       		} else {
                       			alert('没有找到"' + txt + '"对应的菜单。');
                       		}
                    	}
                	} catch(e){
                		alert(e.message);
                	}
				}
            });
			
			$('#divFilter input').each(function(){
				_menuFilter = $(this);
				$(this).css({width: "50%"});
				return false;
			});
            
			//初始化角色列表
			$("#roles").multiselect();
			
			//初始化菜单树
	        getMenuTree();
	    });
	
	    function setSize() {
	    	try{
	    		var winheight = $(window).height();
		    	
				$("#divTree").height(winheight - 100);
		    	
		    	$("#divTabs").height(winheight - 60);
		    	
		        $("#divMenu").height(winheight - 127);
		        $("#divRole").height(winheight - 60);
		        
		        var r = $('#roles');
		        if(r){
		        	r.width($("#divTabs").width() - 2);
		        	r.height($("#divRole").height() - 15);
		        	
		        	if(r.is(":ui-multiselect")){
		        		r.multiselect('resize');
		        	}
		        }
		        
	    	} catch(ex){}
	    }

	    function getMenuTree() {
	        postJSON(rootPath + "/manager/GetMenuTree.action", {"root_menu_id": ROOT_MENUID}, showMenuTee);
	    }
	
	    function showMenuTee(json) {
	    	if (!checkJSONResult(json)) {
	            return;
	        }

	    	_menuList.length = 0;
	        _menuList = json.data;
	        buildeMenuTree(_menuList);
	    }
	
	    function clickTree(itemid) {
	        if (itemid == _curItemId) {
	            return;
	        }
	
	        getMenuInfo(itemid);
	    }

	    function getMenuInfo(menuId){
	    	$('#menuForm .button').hide();
	    	$('#menuForm .popupButton').prop('disabled', true);
	    	
	        _curItemId = menuId;
	        
	        $('#menuForm').resetForm();
	        $('#roles').multiselect('clear');
	        
	        $('#lbMenuId').text('');
	        
	        if (_curItemId == ROOT_MENUID) {
	            _curItemData = "";
	            return;
	        }
	
	        postJSON(rootPath + "/manager/GetMenuInfo.action", {"menu_id": _curItemId}, showMenuInfo);
	    }
	
	    function showMenuInfo(json) {
	        if (!checkJSONResult(json)) {
	            return;
	        }
	
	        $('#menuForm .button').show();
	        $('#menuForm .popupButton').prop('disabled', false);
	
	        var data = json.data;
	        for(var k in data) {
	        	$('#' + k).val(data[k]);
	        }
	        
	        $("#menu_type" + data['menu_type']).prop("checked", true);
	        $("#valid_flag" + data['valid_flag']).prop("checked", true);
	        
	        //触发select2
	       	$("#menuForm select").change();
	        
	       	if(powersi.isvalue(json.data.roles) && json.data.roles.length > 0){
		    	var s = json.data.roles.split(",");
		    	$('#roles').multiselect('select', s);
		    }
	        
	       	$('#lbMenuId').text(data["menu_id"]);
	        showUpMenuName(data["menu_up_id"]);
	        changeRightFlag(data.right_flag);
	        
	        _curItemData = $('#menuForm').serialize();
	    }
	    
	    function showUpMenuName(menuId){
	    	var upMenuName = "";
	        if(menuId != ROOT_MENUID){
	        	var upMenu = findMenu(menuId);
		        if(upMenu){
		        	upMenuName = upMenu.menu_name;
		        }
	        } else {
	        	upMenuName = SYSTEM_NAME;
	        }
	        
	        $('#menu_up_name').val(upMenuName);
	    }
	
	    function findMenu(menuId) {
	        var menu = null;
	        if (!powersi.isvalue(_menuList)) {
	            return menu;
	        }
	
	        for (var i in _menuList) {
	            if (_menuList[i].menu_id == menuId) {
	                menu = _menuList[i];
	                break;
	            }
	        }
	
	        return menu;
	    }
	    
	    function getTreeFont(treeId, node) {
	    	return (!!node.highlight) ? {"background-color":"#ffff96"} : (node.font ? node.font : {"background-color":"transparent"});
		}
	    
	    function getTreeOrder(treeId, node){
	    	return parseFloat(node.tId.substring(treeId.length + 1));
	    }
	    
	    function onTreeClick(event, treeId, treeNode, clickFlag) {
	    	expandNode(treeId, treeNode);
			
	    	clickTree(treeNode.id);
		}
	    
	    function expandNode(treeId, treeNode){
	    	var zTree = $.fn.zTree.getZTreeObj(treeId);
			zTree.expandNode(treeNode);
	    }
	    
	    function selectMenu(menuId){
	    	var zTree = $.fn.zTree.getZTreeObj("tvMenu");
	    	var node = zTree.getNodeByParam("id", menuId, null);
	    	if(node){
	    		if(node.isParent){
					zTree.expandNode(node, true, false, true, false);	
				} else {
					zTree.expandNode(node.getParentNode(), true, false, true, false);
				}
	    		zTree.selectNode(node, false);
	    	}
	    }
	    
	    var menuTrees = [];
	    function buildeMenuTree(menus) {
		    try{
		    	var root = 'root';
		    	var trees = [];
		    	
		    	trees.push({"id": 0, "pId": root, "name": SYSTEM_NAME, "flag": "0", "open": true, "font": {"color": "#f00"}, "iconSkin" : "system"});
		    	
		        if (powersi.isarray(menus)) {
		            for (var idx in menus) {
		                var menu = menus[idx];

		                var treeNode = {"id": menu.menu_id, "pId": menu.menu_up_id, "name": menu.menu_name, "flag": menu.menu_flag};
		                var treeFont = null;
		                if(menu.menu_type != "1"){
		                	treeFont = {"color": "#00f", "background-color":"transparent"};
		                	treeNode["iconSkin"] = "right";
		                } else {
		                	treeNode["iconSkin"] = "menu";
		                }
		                
		                if(menu.valid_flag != null && menu.valid_flag != "1"){
		                	treeFont = {"color": "#f00", "background-color":"transparent"};
		                }
		                
		                if(treeFont != null){
		                	treeNode["font"] = treeFont;
		                }
		                trees.push(treeNode);
		            }//end for menus
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
		        var treeObj = $.fn.zTree.init($("#tvMenu"), setting, trees);
		      	//统计子节点个数
				var nodes = treeObj.getNodesByFilter(function(n){return true;}, false);
			 	$.each(nodes, function(i, node) {
			 		//计算子菜单数
			 		var len = treeObj.getNodesByFilter(function(n){return true;}, false, node).length;
			 		if(len > 0){
						node.name = node.name + "[" + len + "]";
						treeObj.updateNode(node);
					}
			 	});
			 	
		        menuTrees.length = 0;
		        menuTrees = trees;
		    } catch(e){
		    	alert(e.message);
		    }
	       
	        getMenuInfo(_curItemId);
	        selectMenu(_curItemId);
	    }
	
	    function doAdd() {
	        $('#menuForm').resetForm();
	        $('#roles').multiselect('clear');
	        
	        $("#menuForm select").change();
	        
	        $('#menu_type2').prop('checked', true);
	        $('#valid_flag1').prop('checked', true);
	        
	        $('#menu_up_id').val(_curItemId);
	        $('#menu_id').val("-1");
	        showUpMenuName(_curItemId);
	        
	        _curItemId = NEW_MENUID;
	
	        $('#btAdd').hide();
	        $('#btCopy').hide();
	        $('#btDel').hide();
	        $('#btTask').hide();
	
	        $('#lbMenuId').text('');
	        $('#menu_order').focus();
	    }
	
	    function doCopy() {
	        $('#menu_id').val("-1");
	        $('#menu_name').val("复制 " + $('#menu_name').val());

	        var intValue=parseInt($('#menu_order').val());
	        if (!isNaN(intValue)) {
	        	$('#menu_order').val(intValue + 1);
	        } 

	        _curItemId = NEW_MENUID;
	
	        $('#btAdd').hide();
	        $('#btCopy').hide();
	        $('#btDel').hide();
	        $('#btTask').hide();
	
	        $('#lbMenuId').text('');
	        $('#menu_name').focus();
	    }
	
	    function doSave() {
	        if (_curItemId == ROOT_MENUID) {
	            alert("系统菜单，不能保存");
	            return;
	        }
	        
	       	var action = null;
	        var saveItemData = $("#menuForm").serialize();
	        if (_curItemId != NEW_MENUID) {
	            if (saveItemData == _curItemData) {
	                alert("没有修改，不需保存");
	                return;
	            }

	            action = "ModifyMenu.action";
	        } else {
		        action = "AddMenu.action";
	        }

	        if(!checkForm()){
	        	return;
	        }

	        if($("#menu_id").val() == $("#menu_up_id").val()){
		        alert("上级菜单不能选择自己");
		        $("#menu_up_id").focus();
		        return;
	        }
	        
	        postJSON(rootPath + "/manager/" + action, saveItemData, saveSuccess);
	    }

	    function saveSuccess(json){
		    if(!checkJSONResult(json)){
			    return;
		    }

		    _applyFlag = true;
		    $("#btApply").removeProp("disabled");
		    
		    _curItemId = json.data.menu_id;

		    getMenuTree();

		    alert('保存成功');
	    }
	
	    function doDel() {
	        if (_curItemId == ROOT_MENUID) {
	            alert("系统菜单，不能删除");
	            return;
	        }
	
	        if (_curItemId == NEW_MENUID) {
	            alert("没有保存，不能删除");
	            return;
	        }
	
	        var menu = findMenu(_curItemId);
	        if (!powersi.isvalue(menu)) {
	            alert("无法获取当前菜单信息，不能删除");
	            return;
	        }
	
	        var menuName = menu.menu_name;
	        if (!confirm("您确认删除[" + menuName + "]吗?")) {
	            return;
	        }
	
	        postJSON(rootPath + "/manager/DelMenu.action", menu, delSuccess);
	    }

	    function delSuccess(json){
		    if(!checkJSONResult(json)){
			    return;
		    }

		    _applyFlag = true;
		    $("#btApply").removeProp("disabled");
			
		    var menu = findMenu(_curItemId);
		    if(powersi.isvalue(menu)){
		    	_curItemId = menu.menu_up_id;
		    } else {
		    	_curItemId = ROOT_MENU_ID;
		    }
		    
		    getMenuTree();
	    }
	    
	    function doApply(){
	    	if(_applyFlag){
	    		$(':button.apply').prop('disabled', true).removeClass('buttonOver');
				$.post(rootPath + "/manager/RefreshMenuMapping.action");
				_applyFlag = false;
			}
	    }
	    
	    function changeRightFlag(rightFlag) {
	    	if(rightFlag == "1"){
	    		$("#divTabs").tabs("enable", 1);
	    	} else {
	    		//$("#divTabs").tabs("select", 0);
	    		$("#divTabs").tabs("option", "active", 0);
	    		
	    		$("#divTabs").tabs("disable", 1);
	    	}
	    }
	    
	    var dlg;
	    var upTree;
	    function showUpMenu() {
	    	try{
	    		$('#lbUpName').text($('#menu_up_name').val());
	    		
	    		var menuUpId = $('#menu_up_id').val();
	    		
	    		if(upTree == null){
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
			    					rootPId: -1
			    				}
			    			},
							callback: {
								onClick: onTreeUpMenuClick,
								onCheck: onSelectUpMenu
							},
							check: {
								enable: true,
								chkStyle: "radio",
								radioType: "all",
								autoCheckTrigger: false
							}
			    	};
	    			upTree = $.fn.zTree.init($("#tvUp"), upSetting, menuTrees);
	    		}
	    		
	    		upTree.expandAll(false);
	    		
	    		var node = upTree.getNodeByParam("id", menuUpId, null);
	    		if(node == null){
	    			alert('无法获取上级菜单');
	    			return;
	    		}
	    		
	    		upTree.checkNode(node, true, false, false);
	    		if(node.isParent){
	    			upTree.expandNode(node, true, false, true, false);	
				} else {
					upTree.expandNode(node.getParentNode(), true, false, true, false);
      			}
	    		upTree.selectNode(node, false);
	    		
	    		if(dlg){
	    			dlg.show();
	    		} else{
	    			dlg = popupDiv("#divUpMenu", '选择上级菜单', 450);
	    		}
	    	} catch(e){
	    		alert(e.message);
	    	}
		}
	    
	    function hideUpMenu() {
	    	if(dlg){
    			dlg.hide();
	    	}
		}
	    
	    function onSelectUpMenu(e, treeId, treeNode){
	    	var zTree = $.fn.zTree.getZTreeObj("tvUp");
			var nodes = zTree.getCheckedNodes(true);
			if(nodes.length == 0){
				return;
			}
			
			$('#lbUpName').text(nodes[0].name);
	    }
	    
	    function onTreeUpMenuClick(event, treeId, treeNode, clickFlag) {
	    	expandNode(treeId, treeNode);
		}
	    
	    function onUpOk(){
	    	var zTree = $.fn.zTree.getZTreeObj("tvUp");
			var nodes = zTree.getCheckedNodes(true);
			if(nodes.length == 0){
				return;
			}
			
			$('#menu_up_id').val(nodes[0].id);
			$('#menu_up_name').val(nodes[0].name);
			
			hideUpMenu();
	    }
	    
	    function onUpCancel(){
	    	hideUpMenu();
	    }
	    
	    var taskDlg = null;
	    function doEditTask() {
	    	var menuId = $('#menu_id').val();
	    	if(powersi.length(menuId) == 0 || menuId == NEW_MENUID){
	    		alert("请保存后再维护任务");
	    		return;
	    	}
	    	
	    	if($('#menu_type').val() != '1' || powersi.length($('#win_name').val()) == 0){
	    		alert("只有菜单才能配置任务");
	    		return;
	    	}
	    	
	    	postJSON(rootPath + "/manager/GetMenuTask.action", {"menu_id": menuId}, function(res){
	    		if(!checkResult(res)){
	    			return;
	    		}
	    		
	    		var task = {
	    			'menu_id': menuId,
	    			'task_sql': '',
	    			'task_desc': $('#menu_name').val(),
	    			'task_order': 1000,
	    			'task_right_type': '',
	    			'valid_flag': '1'
	    		};
	    		json2form('#taskForm', $.extend(task, res.data));
	    		if(powersi.length($('#task_sql').val()) == 0){
	    			$('#btTaskDelete').hide();
	    		} else{
	    			$('#btTaskDelete').show();
	    		}
	    		if(taskDlg){
	    			taskDlg.show();
	    		} else{
	    			taskDlg = popupDiv('#taskDlg', '编辑任务', 600);
	    		}
	    	});
	    }
	    
	    function doSaveTask() {
	    	if(!checkForm('#taskForm')){
	    		return;
	    	}
	    	
	    	postJSON(rootPath + "/manager/SaveMenuTask.action", form2json('#taskForm'), function(res){
	    		if(!checkResult(res)){
	    			return;
	    		}
	    		
	    		alert(res.message);
	    		
	    		closeTaskDlg();
	    	});
	    }
	    
	    function doDelTask() {
	    	if(!confirm('您确认删除任务[' +$('#task_desc').val() + "]吗?")){
	    		return;
	    	}
	    	
	    	postJSON(rootPath + "/manager/DelMenuTask.action", {'menu_id' : $('#menu_id').val()}, function(res){
	    		if(!checkResult(res)){
	    			return;
	    		}
	    		
	    		alert(res.message);
	    		
	    		closeTaskDlg();
	    	});
	    }
	    
	    function closeTaskDlg() {
	    	if(taskDlg){
	    		taskDlg.hide();
	    	}
	    }
	</script>
</body>
</powersi:html>