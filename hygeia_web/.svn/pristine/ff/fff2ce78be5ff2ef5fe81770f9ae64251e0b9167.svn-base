<%@page import="com.powersi.hygeia.framework.util.UtilFunc"%>
<%@page import="com.powersi.hygeia.framework.util.DBHelper"%>
<%@page import="com.powersi.hygeia.framework.ParameterMapping"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.powersi.com.cn/tags" prefix="powersi"%>
<%
	String systemName = ParameterMapping.getSystemName();
	String orgName = ParameterMapping.getStringByCode("center_org_name", systemName);
	
	com.powersi.hygeia.framework.UserInfo user = com.powersi.hygeia.framework.BusiContext.getUserInfo();
	request.setAttribute("username",user.getLoginUser());
	
	String deptId = "0";
	if(Integer.parseInt(UtilFunc.getString(user, "grade_id", "0")) < 4){
		deptId = UtilFunc.getString(user, "dept_id", "-1");
	}
	
	int gradeId = Integer.parseInt(UtilFunc.getString(user, "grade_id", "0"));
	String deptRequired = gradeId < 4 ? "true" : "false";
	request.setAttribute("gradeId", gradeId);
	
	request.setAttribute("issuperuser", user.isSuperUser() ? "true" : "false");
	
	request.setAttribute("roleuserkind", DBHelper.existTable("SYS_ROLE_USERKIND") ? "true" : "false");
%>
<powersi:html>
<head>
<powersi:head title="角色管理" />
<script src="${strutsPath}/include/ztree.js" type="text/javascript"></script>
<script src="${rootPath}/resource/js/jquery.quickfilter.js" type="text/javascript"></script>
<style type="text/css">
html,body{overflow: hidden;}
#divMenuFilter input{width: 130px; float: left;}
#divMenuButton{margin: 0 auto; text-align: center; margin-left: 140px;}
#divMenuTree{overflow-x:hidden;overflow-y:scroll;text-align:left;white-space:nowrap}
#divRoleFilter{overflow: hidden; text-align: center; margin: auto;}
#grid{overflow-x: auto;overflow-y: scroll;text-align:left;white-space:nowrap;width:100%;margin-bottom:10px;}
</style>
</head>
<body class="page">
	<powersi:panelbox key="panel_query" title="查询条件">
		<powersi:form id="queryForm" namespace="/medicare"
			action="RoleManager!getRoleGrid.action">
				<powersi:editorlayout cols="10">
					<powersi:editorlayout-row>
						<powersi:textfield id="sval" name="sval" placeholder="请输入角色信息"
							cssStyle="width:180px;" label="关键字" />
						<powersi:editorlayout-button colspan="2">
							<powersi:submit id="btSubmit" key="button_query" />
							<powersi:reset id="btReset" key="button_reset" />
						</powersi:editorlayout-button>
					</powersi:editorlayout-row>
				</powersi:editorlayout>
		</powersi:form>
	</powersi:panelbox>

	<powersi:pagelayout id="layout" allowLeftResize="false" leftWidth="550" heightDiff="0" onEndResize="setSize" space="-1">
		<powersi:pagelayout-panel position="left">
			<powersi:pagelayout-panel-title>
				<i class="icon-sitemap"></i><span>部门角色信息</span>
			</powersi:pagelayout-panel-title>
			
			<powersi:datagrid id="grid" formId="queryForm" delayLoad="true" enabledSort="false"
				showReload="true" checkbox="false" onSelectRow="onSelectRow"
				showExportExcel2007="true" >
				<powersi:datagrid-column name="role_name" key="role_name" minWidth="130" label="角色名称" />
				<powersi:datagrid-column name="role_desc" key="role_desc" minWidth="150" label="角色描述" />
				<powersi:datagrid-column name="dept_up_name" key="dept_up_name" minWidth="360" label="所属部门"  align="left" />
			</powersi:datagrid>
			
		</powersi:pagelayout-panel>
		<powersi:pagelayout-panel position="center">
			<powersi:tabbedpanel id="divTabs">
				<powersi:tab id="tab1" target="divTab1" label="角色信息" />
				<powersi:tab id="tab2" target="divTab2" label="菜单权限" />
				<powersi:tab id="tab3" target="divTab3" label="角色授权" />
				<powersi:tab id="tab4" target="divTab4" label="用户信息" />
				<powersi:tab id="divRoleButton">
					<input type="button" name="btRoleAdd" value="新 增" id="btRoleAdd" class="button" onclick="doRoleAdd()" title="新增角色" />
					<input type="button" name="btRoleCopy" value="复 制" id="btRoleCopy" class="button" onclick="doRoleCopy()" title="复制角色" />
					<input type="button" name="btRoleSave" value="保 存" id="btRoleSave" class="button" onclick="doRoleSave()" title="保存角色" />
					<input type="button" name="btRoleDel" value="删 除" id="btRoleDel" class="button" onclick="doRoleDel()" title="删除角色" />
					<input type="button" name="btApply1" value="应 用" id="btApply" class="button apply" onclick="doApply()" title="使配置生效" disabled="disabled" />
				</powersi:tab>
				<div id="divTab1">
					<powersi:form id="roleForm">
						<input type="hidden" id="role_id" name="role_id" />
						<input type="hidden" id="dept_name" name="dept_name" />
						<input type="hidden" id="role_grant" name="role_grant" />
						<powersi:editorlayout cols="30%,70%">
							<powersi:editorlayout-head title="角色详细信息"></powersi:editorlayout-head>
							<powersi:editorlayout-row>
								<powersi:textfield key="role_name" id="role_name" name="role_name" maxlength="25" required="true" />
							</powersi:editorlayout-row>
							<powersi:editorlayout-row>
								<powersi:textfield key="role_desc" id="role_desc" name="role_desc" maxlength="100" />
							</powersi:editorlayout-row>
							<powersi:editorlayout-row>
								<powersi:spinner key="dis_order" id="dis_order" name="dis_order" 
									validate="integer,min[0],max[999999]" maxlength="6" 
									min="0" max="999999" mouseWheel="true" />
							</powersi:editorlayout-row>
						    <c:if test="${issuperuser == 'true'}">
								<powersi:editorlayout-row>
									<powersi:radio codeType="role_range" key="role_range" id="role_range" name="role_range" required="true" />
								</powersi:editorlayout-row>
							</c:if>
							<c:if test="${roleuserkind == 'true'}">
								<powersi:editorlayout-row>
									<powersi:checkboxlist codeType="user_kind" key="user_kind" id="role_userkind" name="role_userkind" checkAllButton="true" />
								</powersi:editorlayout-row>
							</c:if>
							<powersi:editorlayout-row>
								<input type="hidden" name="dept_id" id="dept_id" value="" />
								<powersi:textfield id="dept_id_name" required="true" name="dept_id_name" cssStyle="width:350px;" placeholder="请输入部门编码或部门名称(最多显示30条数据)" label="所属部门">
								</powersi:textfield>
							
							<%-- 	<powersi:codeselect name="dept_id" id="dept_id" key="dept_id" 
									list="#request.deptList" headerKey="" headerValue="请选择..."  required="<%=deptRequired %>" /> --%>
							</powersi:editorlayout-row>
						</powersi:editorlayout>
					</powersi:form>
				</div>
				<div id="divTab2">
	                <table class="tableBorder">
	                    <tr class="tableHeader">
	                        <td>菜单列表信息</td>
	                    </tr>
	                    <tr>
	                        <td>
	                            <div id="divMenuTree">
	                                <ul id="tvMenu" class="ztree">
	                                </ul>
	                            </div>
	                        </td>
	                    </tr>
	                </table>
	               <div>
	               		<div id="divMenuFilter"></div>
	               		<powersi:buttons id="divMenuButton">
               			  	<input type="button" value="保 存" id="btSave" class="button" onclick="doSave()" title="保存角色菜单" />
	                        <input type="button" value="取 消" id="btCancel" class="button" onclick="doCancel()" title="取消当前修改" />
	                        <input type="button" value="全 选" id="btChkAll" class="button" onclick="checkTreeNode(true)" title="勾选当前选择的所有子菜单" />
	                        <input type="button" value="全不选" id="btUnChkAll" class="button" onclick="checkTreeNode(false)" title="去除当前选择的所有子菜单" />
	                        <input type="button" value="应 用" id="btApply" class="button apply" onclick="doApply()" title="使配置生效" disabled="disabled" />
	                    </powersi:buttons>
	               </div>
               	</div>
               	<div id="divTab3">
               		<div id="divRoleGrant">
               			<powersi:datagrid id="gridRole" usePager="false" checkbox="true"
               				frozen="false" selectRowButtonOnly="true"
               				groupColumnName="dept_id" groupCheckbox="true" groupRender="gridGroupRender"
               				inWindow="false" widthDiff="-2">
	               			<powersi:toolbar>
	               				<powersi:toolbar-item id="list" text="可授权角色列表" title="该角色在授权时，可以分配给用户的角色列表" icon="info" />
	               				<powersi:toolbar-item id="save" text="保存角色授权" title="保存角色授权" icon="save" click="doRoleSave" position="right"/>
	               				<powersi:toolbar-item id="detail" text="查看已选择角色" title="显示当前勾选的角色列表" icon="view" click="showRoleGrant" position="right"/>
							</powersi:toolbar>
							<powersi:datagrid-column display="角色名称" name="role_name" width="30%" minWidth="100" />
							<powersi:datagrid-column display="角色描述" name="role_desc" width="30%" minWidth="100" />
							<powersi:datagrid-column display="所属部门" name="dept_name" width="40%" minWidth="150" />
               			</powersi:datagrid>
               		</div>
				</div>
				<div id="divTab4">
					<div id="divUser">
						<powersi:datagrid id="gridUser" usePager="false" checkbox="true"
              				frozen="false" selectRowButtonOnly="true" 
              				groupColumnName="dept_id" groupCheckbox="true" groupRender="gridGroupRender"
              				inWindow="false" widthDiff="-2" exportFileName="getFileName">
              				<powersi:toolbar>
	               				<powersi:toolbar-item id="list" text="已授权用户列表" title="拥有该角色的用户列表" icon="info" />
	               				<powersi:toolbar-item id="delete" text="删除用户角色" title="删除用户角色授权" icon="delete" click="doDelUsers" position="right"/>
	               				<powersi:toolbar-item id="detail" text="查看已选择用户" title="显示当前勾选的用户列表" icon="view" click="showUserList" position="right"/>
								<powersi:toolbar-item id="export" text="导出用户列表" title="导出该角色的用户列表" icon="excel" click="exportUserList" position="right"/>
							</powersi:toolbar>
							<powersi:datagrid-column key="login_user" name="login_user" width="25%" minWidth="90" render="renderLoginUser"/>
							<powersi:datagrid-column key="user_name" name="user_name" width="25%" minWidth="90" />
							<powersi:datagrid-column display="所属部门" name="dept_name" width="30%" minWidth="90" />
							<powersi:datagrid-column display="状态" name="staff_sta" width="20%" minWidth="50" code="staff_sta" render="renderStaffSta" />
              			</powersi:datagrid>
              		</div>
				</div>
			</powersi:tabbedpanel>
		</powersi:pagelayout-panel>
	</powersi:pagelayout>
    <powersi:errors />
<script type="text/javascript">
    var ORG_NAME = "<%=orgName%>";
    var SYSTEM_NAME = "<%=systemName%>";
    var ROOT_DEPTID = <%=deptId%>;
    var SYSTEM_ID = "0";
    
    var NEW_ROLEID = "-1";
    var _curItemId = "-1";
    var _curTreeId = "-1";
    
    var _curMenuData = "";
    var _curRoleData = "";
    
    
    var _menuFilter = null;
    var _lastFind = null;
    var _nodeList = [];
    
    var _selectGridData = null;
    
    var _menuTree = null;
    var _menuNodes = {};
    
    var _applyFlag = false;
    //可被授权列表
    var allGrantRoles = '<%=request.getAttribute("allGrantRoles")%>';
    
    $(document).ready(function() {
        setSize();
        
      	//手动初始化部门下拉框
      /*  	initSelect2('#dept_id', {
       		formatSelection: function(state){
       			return $.trim(state.text);
       		}
       	}); */
      	
     	$s_select2Dept = $("#dept_id_name").select2({
			ajax : {
				url: rootPath+'/manager/DeptManager!loadDeptInfo.action',
				dataType : 'json',
				type : "post",
				delay : 250,// 延迟显示
				data : function(params, page) {
					return {
						 sval : params,
						 //page: page, //当前页
						 //pagesize : 30// 每页显示多少行
					};
				},
				cache : false,
				results : function(data, page) {
					//page = page || 1;
					var array = new Array();
	        	   	if(SYSTEM_ID == ROOT_DEPTID) {
			        	var obj = new Object();
			        	obj.dept_up_name = SYSTEM_NAME;
			        	obj.dept_name = SYSTEM_NAME;
			        	obj.dept_id = SYSTEM_ID;
			        	obj.dept_fid = "-"+SYSTEM_ID+"-";
			        	obj.dept_up_id = SYSTEM_ID;
			        	array[0] = obj;
	  	    	    } 
		        	array = array.concat(data.data.deptList);
					
					return {
						results : array,
						/* pagination: {
		                    more: (page * 30) < data.data.paginatedList.fullListSize
		                }  */
					};
				}
			},
			id : function(rs) {  
		        return rs.dept_id;  
		    },
		    //tags: true,//允许手动添加
		    minimumInputLength: 2,//最少输入多少个字符后开始查询
		    formatResult: function formatRepo(repo){
		    	return repo.dept_up_name;
		    }, // 函数用来渲染结果
		    formatSelection: function formatRepoSelection(repo){
		    	$('#dept_id').val(repo.dept_id);
		    	return repo.dept_name;
		    }, 
		    dropdownCssClass: "bigdrop", // 设定SELECT2下拉框样式，bigdrop样式并不在CSS里定义，暂时没深入研究
            escapeMarkup: function (m) {
                return m;
            },
		    
		});
      	
      
        window.onbeforeunload = function(){
			doApply();
		};

		clearMenu();
		clearGrant();
		
		$("#divRoleButton :button:not(.apply)").hide();
		$('#btRoleAdd').show();
		
		$("#btRoleGrant").hide();
		$("#btDelUsers").hide();
		
		try{
			var allMenus = '<%=request.getAttribute("menutree")%>';
			if(!powersi.isvalue(allMenus)){
				allMenus = 'null';
			}
			buildeMenuTree(powersi.tojson(allMenus));
		} catch(e){
			alert(e.message);
		}
			
		$('#tvMenu').quickfilter({
        	srcElement: '',
            attached: '#divMenuFilter',
            stripeRowClass: null,
            inputText: '查找 菜单、权限',
            inputTitle: '输入关键字后，按回车键查找符合条件的数据',
            labelText: '',
            filter: function(i){
            	return "";
            },
            onAfter: function(){
            	try{
            		if(_menuTree && _menuFilter){
                		var txt = powersi.trim(_menuFilter.val());
                		var len = powersi.length(_nodeList);
                		if(_lastFind !== txt) {
                			_lastFind = txt;
                			
                			for( var i = 0; i < len; i++) {
                				_nodeList[i].highlight = false;
                				_menuTree.updateNode(_nodeList[i]);
                			}
                    		_nodeList.length = 0;
                    		
                    		if(txt.length > 0){
                    			_nodeList = _menuTree.getNodesByParamFuzzy("name", txt, null);
                    		}
                    		
                    		len = powersi.length(_nodeList);
                    		for( var i = 0; i < len; i ++) {
                    			_nodeList[i].highlight = true;
                    			_menuTree.updateNode(_nodeList[i]);
   	            			}
                		}
                		
                		if(txt.length == 0){
                			return;
                		}
                		
                   		if(len > 0){
                   			var sel = 0;
                   			if(len > 1){
                   				var curNodes =  _menuTree.getSelectedNodes();
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
           						_menuTree.expandNode(node, true, false, true, false);	
           					} else {
           						_menuTree.expandNode(node.getParentNode(), true, false, true, false);
  	            			}
           					
           					_menuTree.selectNode(node, false);
           					
   	                		_menuFilter.focus();
                   		} else {
                   			alert('没有找到"' + txt + '"对应的项。');
                   		}
                	}
            	} catch(e){
            		alert(e.message);
            	}
			}
        });
		
		$('#divMenuFilter input').each(function(){
			_menuFilter = $(this);
			return false;
		});
		
		$("#role_id").val(NEW_ROLEID);
		$("#dept_id").val(ROOT_DEPTID);
		//刷新
		grid.reload();
    });
    
    function setSize() {
    	var h = $(window).height();
        $("#divMenuTree").height(h - 220);
        $('#divRoleGrant').width($('#divTabs').width() - 2).height(h - 130);
        if(gridRole){
        	gridRole.refreshSize();
        }
        $('#divUser').width($('#divTabs').width() - 2).height(h - 130);
        if(gridUser){
        	gridUser.refreshSize();
        }
    }
    
	function onSelectRow(rowdata, rowid, rowobj){
		grid.selectRow(rowid,"select");
		_selectGridData = rowdata;
		
	/* 	<input type="hidden" id="role_id" name="role_id" />
		<input type="hidden" id="dept_name" name="dept_name" />
		<input type="hidden" id="role_grant" name="role_grant" />
		$('#dept_up_id').val(rowdata.dept_up_id);
        $('#dept_id').val(rowdata.dept_id); */
		
        getRoleInfo(rowdata.role_id);
	}
    
      function buildeMenuTree(menus) {
	    try{
	    	var root = -1;
	    	var trees = [];
	    	trees.push({"id": 0, ".": root, "name": SYSTEM_NAME, "open": true, "nocheck":true, "font": {"color": "#f00"}, "iconSkin" : "system"});
	    	
	    	if (powersi.isarray(menus)) {
	            for (var idx in menus) {
	                var menu = menus[idx];

	                var treeNode = {"id": menu.menu_id, "pId": menu.menu_up_id, "name": menu.menu_name, "mtype" : menu.menu_type};
	                if(menu.menu_type != "1"){
	                	treeNode["font"] = {"color": "#00f", "background-color":"transparent"};	
	                	treeNode["iconSkin"] = "right";
	                } else {
	                	treeNode["iconSkin"] = "menu";
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
						onClick: onMenuTreeClick,
						onCheck: onTreeCheck
					},
					check: {
						enable: true,
						chkStyle: "checkbox" ,
						chkboxType: {"Y": "ps", "N": "s"},
						autoCheckTrigger: false
					}
			};
		    $.fn.zTree.init($("#tvMenu"), setting, trees);
		    trees.length = 0;
		    
		    _menuNodes = {};
		    _menuTree = $.fn.zTree.getZTreeObj("tvMenu");
		    
		    //删除无用的菜单权限
		    var dels = _menuTree.getNodesByParam("pId", root, null);
		    for(var idx in dels){
		    	if(dels[idx].id != 0){
		    		_menuTree.removeNode(dels[idx], false);
		    	}
		    }
		    
		  	//初始化菜单id和菜单树id的对应关系
		    var nodes = _menuTree.transformToArray(_menuTree.getNodes());
		    for(var idx in nodes){
		    	var node = nodes[idx];
		    	_menuNodes[node.id.toString()] = node.tId;
		    }
		    nodes.length = 0;
	    } catch(e){
	    	alert(e.message);
	    }
    }
    
    function gridGroupRender(groupvalue, groupdata, groupdisplay){
    	try{
    		var deptName = groupdata[0]['dept_name'] || '';
        	if(deptName.length == 0){
        		deptName = '其他';
        	}
        	return deptName + '[' + groupdata.length + ']';
    	} catch(e){
    		alert(e.message);
    	}
    }
    
    //过滤掉当前角色
    function filterRoleGrant(roleId) {
    	var clause = function (rowdata, rowindex) {
       		if(rowdata['role_id'] == roleId){
           		return false;
           	} else {
           		return true;
           	}
        };
        return clause;
    }
   
    function buildRoleGrant() {
    	try{
    		$('#role_grant').val('');
    		
        	gridRole.loadData(powersi.tojson(allGrantRoles));
        	//gridRole.setData(powersi.tojson(allGrantRoles));
        	gridRole.loadData(filterRoleGrant($('#role_id').val()));
    	} catch(e) {
    		alert(e.message);
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
    	return '<a href="#" onclick="viewUserInfo(' + rowdata['user_id'] + ')">' + value + '</a>';
    }
    
    function buildUserList(users){
    	gridUser.loadData(users || []);
    }

    
    function getRoleInfo(roleId){
    	clearMenu();
    	clearRole();
    	
        _curItemId = roleId;
        
        $("#dept_id").val(_curItemId);
        $("#dept_id_name").val(_curItemId);
        $("#s2id_dept_id_name").children('a').removeClass("select2-default");
		$("#s2id_dept_id_name").find('#select2-chosen-1').html(_selectGridData.dept_name);
       
        postJSON(rootPath + "/manager/GetRoleMenu.action", {"role_id": roleId}, showRoleInfo);
    }
    
    function showRoleInfo(json) {
        if (!checkJSONResult(json)) {
            return;
        }

        var data = json.data;
       	var role = data.role_info;
       	
       	json2form('#roleForm', role);
       	
       	var menus = data.role_menu;
       	if (powersi.isarray(menus)) {
            for (var idx in menus) {
	            var menuId = menus[idx].menu_id.toString();
              	var tId = _menuNodes[menuId];
              	
              	if(tId){
              		var node = _menuTree.getNodeByTId(tId);
              		if(node){
              			node.checked = true;
              			_menuTree.updateNode(node, false);
              		}
              	}
            }
        }
        
       	$('#btRoleGrant').show();
       	$("#btDelUsers").show();
       	
       	//userlist
       	buildUserList(data.role_user);
       	
       	//rolegrant
       	allGrantRoles = powersi.tostring(data.all_grant_role);
       	clearGrant();
       	var roleGrantList = powersi.rows_extract(data.role_grant, "grant_role_id");
       	var roleGrantLen = powersi.rows_length(roleGrantList);
       	if(roleGrantLen > 0){
       		var rows = gridRole.getRows();
       		$.each(rows, function(i, row){
       			$.each(roleGrantList, function(j, roleid){
       				if(roleid == row['role_id']){
       					gridRole.select(row);
       					return false;
       				}
       			});
       		});
       	}
       	getRoleGrant();
       
       	saveRole();
    }

    function clearMenu(){
		$('#divMenuButton :button').hide();
    	
		if(_menuTree){
			_menuTree.checkAllNodes(false);			
		}
		
    	_curMenuData = "";
    }
    
    function clearRole(){
    	$('#divRoleButton :button:not(.apply)').hide();
    	$("#roleForm :text").val("").prop("readonly", false).removeClass("textReadonly");
    	$("#dept_id").val("");
    	
    	$('#roleForm select').change();
    	
    	clearFormValidtion('#roleForm');
    	
    	_curRoleData = "";
    }
    
    function clearGrant() {
    	$('#role_grant').val('');
    	
    	buildRoleGrant();
    }
    
     function clearUser() {
    	gridUser.reset();
    }
    
    function saveRole(){
    	_curRoleData = $('#roleForm').serialize();
        _curMenuData = getSelectMenu();
        
        var role = findRole(_curItemId);
        
        if(parseInt(role.role_id) < 100){
        	$("#btRoleDel").prop('disabled', true);
        } else {
        	$("#btRoleDel").prop('disabled', false);
        }
        
        $('#divRoleButton :button').show();
        $('#divMenuButton :button').show();
    }
    
	function getSelectMenu(){
		var a=[];
		var nodes = _menuTree.getCheckedNodes(true);
		for(var idx in nodes){
			a.push(nodes[idx].id);
		}
		
        var str = a.join(",");
        a.length = 0;
        return str;
	}
	
    function onMenuTreeClick(event, treeId, treeNode, clickFlag) {
    	if(_menuTree){
    		_menuTree.expandNode(treeNode);	
    	}
	}
   	
    function onTreeCheck(e, treeId, treeNode) {
		//自动勾选权限
		if(_menuTree){
			var nodes = _menuTree.getNodesByParam("pId", treeNode.id, treeNode);
			var chk = treeNode.checked;
			for(var idx in nodes){
				var node = nodes[idx];
				if(node["mtype"] != "1"){
					node.checked = chk;
					_menuTree.updateNode(node, false);
				}
			}
		}
	}
    
    //勾选当前节点，暂时不公开
    function checkTreeNode(checked) {
    	var sel = _menuTree.getSelectedNodes();
    	if(sel && sel.length > 0){
    		if(!confirm("您确认" + (checked ? "全选" : "全不选") + "[" + sel[0].name + "]吗？")){
    			return false;
    		}
    	} else {
    		alert("请选择需要操作的菜单！");
    		return;
    	}
    	
		var nodes = _menuTree.transformToArray(sel);
		for(var idx in nodes){
			_menuTree.checkNode(nodes[idx], checked, false, false);
		}
	}
	    
    function viewUserInfo(staffid){
    	popupDialog(rootPath + "/user/UserInfoAction!queryUserInfo.action?staff_id="+staffid, 450, 600);
    }
    
    function showRoleGrant() {
    	var rows = gridRole.getSelectedRows();
    	if(powersi.rows_length(rows) == 0){
    		alert("没有选择");
    	} else {
    		alert(powersi.rows_extract(rows, 'role_name').join("\r\n"));
    	}
    }
    
    function getRoleGrant(){
   	  	var rows = gridRole.getSelectedRows();
   	  	var sel = powersi.rows_extract(rows, 'role_id').join(",");
   	  	$('#role_grant').val(sel);
   	  	return sel;
    }

    function doSave() {
    	if(_curItemId == NEW_ROLEID){
    		alert("新增角色，必须先保存角色信息");
        	return;
    	}
    	
        var menu_list = getSelectMenu();
        if (menu_list == _curMenuData) {
        	alert("没有修改，不需保存");
        	return;
        }
        
        var role = findRole(_curItemId);
        postJSON(rootPath + "/manager/SaveRoleMenu.action", "menu_list="+menu_list+"&role_id="+role.role_id, saveSuccess);
    }

    function saveSuccess(json){
	    if(!checkJSONResult(json)){
		    return;
	    }

	    _applyFlag = true;
	     $(':button.apply').removeProp('disabled');
	    
	    _curMenuData = getSelectMenu();
	 
	    alert(json.message);
    }

   	function doCancel(){
   		if(_curMenuData == getSelectMenu()){
	   		return;
   		}

   		getRoleInfo(_curItemId);
   	}
   	
    function doApply(){
    	if(_applyFlag){
    		$(':button.apply').prop('disabled', true).removeClass('buttonOver');
			$.post(rootPath + "/manager/RefreshRoleMapping.action");
			_applyFlag = false;
		}
    }
     
    function doRoleAdd(){
    	clearMenu();
    	clearRole();
    	clearGrant();
    	clearUser();
    	
    	var role = findRole(_curTreeId);
    	if(role){
    		if(role.isrole == '1'){
	    		$("#dept_id").val(role.dept_up_id);	
				$("#dept_id_name").val(role.dept_up_id);
				$("#s2id_dept_id_name").children('a').removeClass("select2-default");
				$("#s2id_dept_id_name").find('#select2-chosen-1').html(role.dept_name);
				
			} else {
				$("#dept_id").val(staff.dept_id);
				$("#dept_id_name").val(role.dept_up_id);
				$("#s2id_dept_id_name").children('a').removeClass("select2-default");
				$("#s2id_dept_id_name").find('#select2-chosen-1').html(role.dept_name);
			}
    	} else{
    		$('#dept_id').val('');
    	}
    	$('#dept_id').trigger("change");
    	
		$('#btRoleGrant').hide();
		$("#btDelUsers").hide();
		$('#btRoleSave').show();
		
    	_curItemId = NEW_ROLEID;
    	$("#role_id").val(NEW_ROLEID);
    	
    	$('#role_name').focus();
    }
    
    function doRoleCopy() {
    	$("#divRoleButton :button:not(.apply)").hide();
    	$('#btRoleSave').show();
    	$("#roleForm :input").prop("readonly", false).removeClass("textReadonly");
    	$('#divMenuButton :button').hide();
    	
    	_curItemId = NEW_ROLEID;
    	
    	$("#role_id").val(NEW_ROLEID);
    	$("#role_name").val("");
    	$("#role_name").focus();
    	$('#btRoleGrant').hide();
    	$("#btDelUsers").hide();
    	
    	return true;
    }
    
    function doRoleSave() {
    	var saveItemData = null;
        if (_curItemId != NEW_ROLEID) {
        	getRoleGrant();
        	
        	var menuList = {};
        	menuList.name = "menu_list";
        	menuList.value = getSelectMenu();
        	
        	saveItemData = $("#roleForm").serialize();
            if (saveItemData == _curRoleData) {
                alert("没有修改，不需保存");
                return;
            }
            
        	var a = $("#roleForm").serializeArray();
        	a.push(menuList);
        	saveItemData = jQuery.param(a);
        } else {
        	//新增角色自动保存菜单
        	getRoleGrant();
        	
        	var menuList = {};
        	menuList.name = "menu_list";
        	menuList.value = getSelectMenu();
        	
        	var a = $("#roleForm").serializeArray();
        	a.push(menuList);
        	saveItemData = jQuery.param(a);
        }
        /*
        if(!powersi.check("dis_order", "num")){
	        alert("显示序号必须为整数");
	        $("#dis_order").focus();
	        return;
        }
        
        if(!powersi.check("role_name", "str")){
	        alert("角色名称必须输入");
	        $("#role_name").focus();
	        return;
        }*/
        if(!checkForm('#roleForm')){
        	return;
        }
        
        postJSON(rootPath + "/manager/SaveRoleInfo.action", saveItemData+"&dept_id="+$("#dept_id").val(), saveRoleSuccess);
    }
    
    function saveRoleSuccess(json){
	    if(!checkJSONResult(json)){
		    return;
	    }
	    alert(json.message);
	    
	    var roleId = json.data.role_id;
	    _curItemId = -1-roleId;
	    
	    $('#role_id').val(roleId);
	    _curRoleData = $('#roleForm').serialize();
	    
	    $("#divRoleButton :button").show();
	    
	    getRoleTree();
    }
    
   	function getRoleTree(){
    	 postJSON(rootPath + "/manager/RoleManager!getRoleTree.action", {"root_dept_id": -1}, showRoleTree);
    }
    
    function showRoleTree(json){
    	if(!checkJSONResult(json)){
		    return;
	    }

    	//刷新
		grid.reload(true);
    }
    
    function doRoleDel() {
    	if (_curItemId == NEW_ROLEID) {
            alert("没有保存，不能删除");
            return;
        }

        if (!confirm("您确认删除角色[" + $('#role_name').val() + "]吗?")) {
            return;
        }
	    
        postJSON(rootPath + "/manager/DelRoleInfo.action", _curRoleData, delRoleSuccess);
    }

    function delRoleSuccess(json){
	    if(!checkJSONResult(json)){
		    return;
	    }

	    alert(json.message);
	    
	    clearMenu();
    	clearRole();
    	
    	allGrantRoles = powersi.tostring(json.data.all_grant_role);
    	clearGrant();
    	
    	var role = findRole(_curItemId);
	    if(powersi.isvalue(role)){
	    	_curItemId = role.dept_up_id;
	    } else {
	    	_curItemId = "-1";
	    }
	    
    	getRoleTree();

    	$("#divRoleButton :button:not(.apply)").hide();
		$('#btRoleAdd').show();
		$("#btRoleGrant").hide();
		$("#btDelUsers").hide();
		
    	$("#role_id").val(NEW_ROLEID);
    	
    	_applyFlag = true;
    	$(':button.apply').removeProp('disabled');
    }
    
  	function findRole(roleId){
    	var roleData = null;
        if (!powersi.isvalue(_selectGridData)) {
            return roleData;
        }
        return _selectGridData;
    }
  	
  	function showUserList() {
    	var rows = gridUser.getSelectedRows();
    	if(powersi.rows_length(rows) == 0){
    		alert("没有选择");
    	} else {
    		alert(powersi.rows_extract(rows, 'user_name').join("\r\n"));
    	}
    }
  	
  	function doDelUsers(){
    	var a = gridUser.getSelectedRows();
    	
    	if(a == null || a.length == 0){
    		alert('请先勾选需要取消角色授权的用户');
    		return;
    	}
    	
    	var roleId = $('#role_id').val() || "";
    	if(roleId.length == 0 || roleId === NEW_ROLEID){
    		alert('新增角色必须先保存');
    		return;
    	}
    	
    	var b = [];
    	var c = [];
    	for(var i in a){
    		b.push(a[i].user_id);
    		if(i < 10){
    			c.push(a[i].login_user + '\t\t\t' + a[i].user_name);	
    		}
    	}
    	if(b.length > 10){
    		c.push('以下省略 ' + (b.length - 10) + ' 个用户......');
    	}
    	
    	if(confirm('您确认将取消下列' + b.length + '个用户\n【' + $('#role_name').val() + '】的角色授权吗？\n' + c.join('\n'))){
    		postJSON(rootPath + "/manager/RoleManager!delRoleUser.action", 
	    			{"role_id" : roleId, "users" : b.join(",")}, 
	    			delUsersSuccess);
    	}
    }
  	
  	function delUsersSuccess(json){
    	if(!checkJSONResult(json)){
		    return;
	    }

    	gridUser.deleteSelectedRow();
    	gridUser.clearCheckbox();
    
    	alert(json.message);
    }
  	
  	function getFileName(){
  		return $('#role_name').val() + "_用户列表";
  	}
  	
  	function exportUserList() {
  		var roleName = $('#role_name').val();
  		if(roleName.length == 0){
  			alert("请先选择角色后再导出");
  			return;
  		}
  		
  		gridUser.exportExcel();
  	}
</script>
</body>
</powersi:html>