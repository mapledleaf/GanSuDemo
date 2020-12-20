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
	<powersi:panelbox key="panel_query" title="查询条件">
		<powersi:form id="queryForm" namespace="/medicare"
			action="DeptManager!getDeptGrid.action">
				<powersi:editorlayout cols="10">
					<powersi:editorlayout-row>
						<powersi:textfield id="sval" name="sval" placeholder="请输入部门编码或部门名称"
							cssStyle="width:180px;" label="部门信息" />
						<powersi:editorlayout-button colspan="2">
							<powersi:submit id="btSubmit" key="button_query" />
							<powersi:reset id="btReset" key="button_reset" />
						</powersi:editorlayout-button>
					</powersi:editorlayout-row>
				</powersi:editorlayout>
		</powersi:form>
	</powersi:panelbox>
			
	<powersi:pagelayout id="layout" allowLeftResize="false" leftWidth="600" onEndResize="setSize" space="-1">
		<powersi:pagelayout-panel position="left">
			<powersi:pagelayout-panel-title>
				<i class="icon-sitemap"></i><span>部门机构列表</span>
			</powersi:pagelayout-panel-title>
			
			<powersi:datagrid id="grid" formId="queryForm" frozen="false" 
			showReload="false" onSelectRow="onSelectRow" showExportExcel2007="true" exportFileName="'部门信息'"> 
				<powersi:datagrid-column name="dept_id" key="dept_id" width="80px" label="部门编码" frozen="true" />
				<powersi:datagrid-column name="dept_up_name" key="dept_up_name" minWidth="450" label="部门名称"	align="left" />
				<%-- <powersi:datagrid-column name="dept_name" key="dept_name" minWidth="450" label="部门名称"	align="left" /> --%>
		<%-- 		<powersi:datagrid-column name="dept_fid" key="dept_fid" minWidth="150" label="部门父级编码" align="left"/>
				<powersi:datagrid-column name="last_date" key="last_date" minWidth="150" label="维护时间" format="{0,date,yyyy-MM-dd}" /> --%>
			</powersi:datagrid>
			
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
									<%-- <powersi:textfield label="上级部门" name="dept_up_name" id="dept_up_name" required="true" readonly="true" 
									buttonId="btnSelectUp" buttonTitle="选择上级部门" onbuttonclick="showUpDept(1)" buttonDisabled="true">
									</powersi:textfield> --%>
									<%-- <powersi:textfield id="dept_up_name" name="dept_up_name" placeholder="请输入部门编码或部门名称" label="上级部门">
									</powersi:textfield> --%>
									<%-- <powersi:codeselect name="select2_dept_id" id="select2_dept_id"
									key="上级部门" codeType="sys_dept" value="1" showValue="true" /> --%>
									<%-- <powersi:textfield id="select2_dept_id" name="dept_up_name" placeholder="请输入部门编码或部门名称" label="上级部门">
									</powersi:textfield> --%>
									
									<powersi:textfield id="dept_up_name" required="true" name="dept_up_name" placeholder="请输入部门编码或部门名称(最多显示30条数据)" label="上级部门">
									</powersi:textfield>
								</powersi:editorlayout-row>
								<powersi:editorlayout-row>
									<powersi:textfield label="部门名称" id="dept_name" name="dept_name" required="true" />
								</powersi:editorlayout-row>
								<powersi:editorlayout-row cssClass="hidden">
									<powersi:textfield label="部门简称" id="short_name" name="short_name" maxlength="20"/>
								</powersi:editorlayout-row>
								<powersi:editorlayout-row cssClass="hidden">
									<powersi:radio id="dept_type" name="dept_type" label="部门类型" list="#{'1': '机构', '2':'部门'}"  />
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
							<!-- <div id="unifiedTip" style="dispaly:none;" class="textLeft red">
								<span>该部门由统一门户维护</span>
							</div> -->
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
    
    var _selectGridData = null;
    
    var select2_sval = null;
    
    var $s_select2Dept= null;
    
    function setSize() {
    	var winheight = $(window).height();
    	
    	$("#divTree").height(winheight - 100);
    	$("#divTabs").height(winheight - 90);
        $("#divDept").height(winheight - 137);
        
        $('#divRole').width($('#divTabs').width() - 2).height(winheight - 130);
        if(gridRole){
        	gridRole.refreshSize();
        }
        $('#divUser').width($('#divTabs').width() - 2).height(winheight - 130);
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

		//刷新
		//grid.reload(true);
		
		$s_select2Dept = $("#dept_up_name").select2({
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
		    	$('#dept_up_id').val(repo.dept_id);
		    	return repo.dept_name;
		    }, 
		    dropdownCssClass: "bigdrop", // 设定SELECT2下拉框样式，bigdrop样式并不在CSS里定义，暂时没深入研究
            escapeMarkup: function (m) {
                return m;
            },
		    
		});
		
	  	//初始化远程自动完成
		/* $( "#dept_up_name" ).autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		          url: rootPath + '/manager/DeptManager!getDeptGrid.action',
		          data:{
		        	  sval: request.term,
		        	  pagesize: 10
		          },
		          dataType: "json",
		          type:'POST',
		          success: function( data ) {
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
			        	array = array.concat(data.rows);
			        	//添加最上场机构
			            response( $.map( array, function( item ) {
				              return {
				                label: item.dept_up_name,
				                value: item.dept_name,
				                code: item.dept_id
				              };
			            }));
		          }
		        });
		   	},
		   	minLength: 1,
		   	open: function() {
		   		//设置宽度
		        //$('#dept_up_name').autocomplete("widget").width(450);
		    },
		    select : function(data, i, n) {
			  	//选择触发事件
			  	$('#dept_up_id').val(i.item.code);
		    }
		}); */
	    
    });

	function onSelectRow(rowdata, rowid, rowobj){
		grid.selectRow(rowid,"select");
		_selectGridData = rowdata;
		
		$('#dept_up_id').val(rowdata.dept_up_id);
        $('#dept_id').val(rowdata.dept_id);
		
		getDeptInfo(rowdata.dept_id);
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
	
    function showUpDeptName(deptId,flag){
    	var upDeptName = "";
        if(deptId != SYSTEM_ID){
        	var upDept = findDept(deptId,flag);
	        if(upDept){
	        	upDeptName = upDept;
	        }
        } else {
        	upDeptName = SYSTEM_NAME;
        }
		$("#dept_up_name").val(deptId);
        if(flag != "add") {
			$("#s2id_dept_up_name").children('a').removeClass("select2-default");
			$("#s2id_dept_up_name").find('#select2-chosen-2').html(upDeptName);
        }
    }
    
    function findDept(deptId,flag) {
        var dept = null;
        if (!powersi.isvalue(_selectGridData)) {
            return dept;
        }

        var tmp_arr = _selectGridData.dept_up_name.split("-");
        
        if("add" == flag) {
	        return tmp_arr[(tmp_arr.length - 1)];
        }else {
	        return tmp_arr.length == 1 ? tmp_arr[(tmp_arr.length - 1)] : tmp_arr[(tmp_arr.length - 2)];
        }
        
    }
    
    function doAdd() {
        $('#deptForm').resetForm();
        $('#deptForm :checkbox').removeProp("checked");
        $('#deptForm :radio').removeProp("checked");
        $('#dept_type2').prop('checked', true);
        
        $('#dept_up_id').val(_curItemId);
        $('#dept_id').val("-1");
        showUpDeptName(_curItemId,'add');
        
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
    
    function getDeptTree() {
        postJSON(rootPath + "/manager/DeptManager!getDeptTree.action", {"root_dept_id": ROOT_DEPTID}, showDeptTee);
    }

    function showDeptTee(json) {
    	if (!checkJSONResult(json)) {
            return;
        }

    	_deptList.length = 0;
        _deptList = json.data;
        
    }

    function clickTree(itemid) {
        if (itemid == _curItemId) {
            return;
        }

        getDeptInfo(itemid);
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
        
        if(!checkForm('#deptForm')){
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
	    
   		//刷新
		grid.reload(true);
   		
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