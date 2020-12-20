<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.powersi.hygeia.framework.ParameterMapping"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.hygeia.framework.util.UtilFunc" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%
	com.powersi.hygeia.framework.UserInfo user = com.powersi.hygeia.framework.BusiContext.getUserInfo();
	String centerFilter = "1 = 0";
	String levelFilter = "1 = 0";
	String staffLevel = UtilFunc.getString(user, "staff_level", "9");
	if(user.isSuperUser() || staffLevel.equals("1")){
		centerFilter = "";
		levelFilter = "";
	} else {
		if(staffLevel.equals("2")){
			centerFilter = "aaa027 in (" + UtilFunc.getString(user, "center_list", "0") + ")";	
		} else {
			String center_id = UtilFunc.getString(user, "center_id", "0");
			center_id = StringUtils.isBlank(center_id) ? "0":center_id;
			centerFilter = "aaa027 = " + center_id;
		}
		
		levelFilter = "data_value >= '" + staffLevel + "'";
	}
	request.setAttribute("staffLevel", staffLevel);
	
	int gradeId = Integer.parseInt(UtilFunc.getString(user, "grade_id", "0"));
	if(user.isSuperUser()){
		gradeId = 9;
	}
	
	String deptRequired = gradeId < 4 ? "true" : "false";
	String centerRequired = "false";//gradeId < 4 ? "true" : "false";
	request.setAttribute("gradeId", gradeId);
	
	String systemName = ParameterMapping.getStringByCode("center_org_name", ParameterMapping.getSystemName());

	String deptId = "0";
	if(gradeId < 4){
		deptId = UtilFunc.getString(user, "dept_id", "-1");
	}
	
	String upServerFlag = ParameterMapping.getStringByCode("up_server_flag", "0");
%>
<powersi:html>
<head>
<powersi:head title="操作员管理" />
<style type="text/css">
html,body{overflow: hidden;}
#staffFilter{clear: both;vertical-align: middle;text-align: center;}
#grid{overflow-x: auto;overflow-y:scroll;text-align:left;white-space:nowrap;width:100%;margin-bottom:10px;}
#divRight{overflow: hidden;}
#divOrgTree{overflow: hidden;}
#divOrgFilter{clear: both; vertical-align: middle; text-align: center;}
#divUser{overflow-y: auto;}

.ztree li span.button.user_ico_open{margin-right:2px; background: url(${strutsPath}/icon/user--arrow.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li span.button.user_ico_close{margin-right:2px; background: url(${strutsPath}/icon/user.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li span.button.user_ico_docu{margin-right:2px; background: url(${strutsPath}/icon/user.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
</style>
<script src="${rootPath}/resource/js/md5.js" type="text/javascript"></script>
<script src="${rootPath}/resource/js/jquery.quickfilter.js" type="text/javascript"></script>
<script src="${strutsPath}/include/ztree.js" type="text/javascript"></script>
<script src="${strutsPath}/include/multiselect.js" type="text/javascript"></script>
</head>
<body class="page">
	<powersi:panelbox key="panel_query" title="查询条件">
		<powersi:form id="queryForm" namespace="/medicare"
			action="StaffManager!getStaffGrid.action">
				<powersi:editorlayout cols="10">
					<powersi:editorlayout-row>
						<powersi:textfield id="sval" name="sval" placeholder="请输入用户信息"
							cssStyle="width:180px;" label="关键字" />
						
						<powersi:codeselect name="valid_flag" id="cbxInvalid" key="用户状态" codeType="aae100" 
							cssClass="select2" cssStyle="width:100px;" value="1" />
						<powersi:editorlayout-button colspan="2">
							<powersi:submit id="btSubmit" key="button_query" />
							<powersi:reset id="btReset" key="button_reset" />
						</powersi:editorlayout-button>
					</powersi:editorlayout-row>
				</powersi:editorlayout>
		</powersi:form>
	</powersi:panelbox>

	<powersi:pagelayout id="layout" allowLeftResize="true" leftWidth="580" onEndResize="setSize" space="-1">
		<powersi:pagelayout-panel position="left">
			<powersi:pagelayout-panel-title>
				<i class="icon-sitemap"></i><span>部门用户列表</span>
			</powersi:pagelayout-panel-title>
			
			<powersi:datagrid id="grid" formId="queryForm" delayLoad="true" enabledSort="false"
				showReload="true" checkbox="false" onSelectRow="onSelectRow"
				showExportExcel2007="true" >
				<powersi:datagrid-column name="login_user" key="login_user" width="90" label="登陆用户代码" />
				<powersi:datagrid-column name="short_name" key="short_name" minWidth="165" label="用户名称"	align="left" />
				<powersi:datagrid-column name="dept_up_name" key="dept_up_name" minWidth="300" label="所属部门" align="left" />
			</powersi:datagrid>
			
		</powersi:pagelayout-panel>
		<powersi:pagelayout-panel position="center">
			<powersi:form id="userForm" disabled="true">
				<powersi:tabbedpanel id="divTabs">
					<powersi:tab id="tab1" target="divTab1" label="用户信息" />
					<powersi:tab id="tab2" target="divTab2" label="角色信息" />
					<powersi:tab id="tab3" target="divTab3" label="数据权限" cssClass="hidden" />
					<powersi:tab id="tabBtn">
						<input type="button" name="btAdd" value="新 增" id="btAdd" class="button" onclick="doAdd()" title="新增用户" /> 
						<input type="button" name="btCopy" value="复 制" id="btCopy" class="button" onclick="doCopy()" title="复制用户" style="display:none;" />
						<input type="button" name="btSave" value="保 存" id="btSave" class="button" onclick="doSave()" title="保存用户" style="display:none;" /> 
						<s:if test='#request.gradeId>=4'>
	                       <input type="button" name="btDel" value="删 除" id="btDel" class="button" onclick="doDel()" title="删除用户" style="display:none;" />
	                   </s:if>
						<input type="button" name="btCancel" value="取 消" id="btCancel" class="button" onclick="doCancel()" title="取消修改" style="display:none;" />
						<input type="button" name="btMenuTree" value="查看菜单权限" id="btMenuTree" class="button" onclick="doMenuTree()" title="查看菜单权限" style="display:none;" />
					</powersi:tab>
					<div id="divTab1">
						<div id="divUser">
								<input type="hidden" name="staff_id" id="staff_id" />
								<input type="hidden" name="his_roles" id="his_roles" value="" />
								<input type="hidden" name="his_orgs" id="his_orgs" value="" />
								<powersi:editorlayout cols="15%,35%,15%,35%">
									<powersi:editorlayout-row>
										<powersi:editorlayout-group>
											用户基本信息
										</powersi:editorlayout-group>
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:textfield name="login_user" id="login_user"
											key="login_user" maxlength="10" required="true" />
										<powersi:password name="password" id="password" label="登录密码"
											maxlength="20" title="不修改登录密码，请保留此字段为空" placeholder="不修改登录密码，请勿输入" />
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:textfield name="staff_name" id="staff_name"
											key="user_name" maxlength="25" required="true" onchange="changeUserName(this)" />
										<powersi:spinner name="dis_order" id="dis_order" maxlength="6"
											key="dis_order" required="true" validate="integer,min[0],max[999999]"
											min="0" max="999999" mouseWheel="true" />
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:radio name="staff_sta" id="staff_sta" label="用户状态" codeType="staff_sta" required="true"></powersi:radio>
										<powersi:codeselect codeType="staff_type" id="staff_type" name="staff_type" label="用户类型" required="false" 
											cssClass="select2" data-show-search="false" />
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:codeselect list="#request.gradeList" id="grade_id"
											name="grade_id" label="用户级别" required="false" 
											cssClass="select2" data-show-search="false" />
										<powersi:codeselect codeType="staff_role" id="staff_role" name="staff_role" label="操作岗位" required="false" 
											cssClass="select2" data-show-search="false" />
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:codeselect name="center_id" id="center_id"
											label="所属中心" codeType="aaa027_list" codeFilter="<%=centerFilter %>" required="<%=centerRequired %>" 
											cssClass="select2" />
										<powersi:codeselect name="staff_level" id="staff_level"
											label="操作级别" codeType="staff_level" codeFilter="<%=levelFilter %>" required="false" 
											cssClass="select2" data-show-search="false" />
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
											<input type="hidden" name="dept_id" id="dept_id" value="" />
											<powersi:textfield id="dept_id_name" required="true" name="dept_id_name" cssStyle="width:350px;" 
											placeholder="请输入部门编码或部门名称(最多显示30条数据)" label="所属部门">
											</powersi:textfield>
										<%-- <powersi:codeselect name="dept_id" id="dept_id" key="dept_id" 
											list="#request.deptList" headerKey="" headerValue="请选择..." required="<%=deptRequired %>" colspan="3" /> --%>
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:editorlayout-group>
											用户附加信息
										</powersi:editorlayout-group>
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:textfield name="postal_code" id="postal_code"
											key="postal_code" maxlength="6" validate="zipcode" />
										<powersi:textfield name="address" id="address" key="address"
											maxlength="100" />
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:textfield name="office_phone" id="office_phone"
											key="office_phone" maxlength="20" validate="telephone" />
										<powersi:textfield name="fax" id="fax" key="fax"
											maxlength="20" />
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:textfield name="mobile_phone" id="mobile_phone"
											key="mobile_phone" maxlength="11" validate="mobilephone" />
										<powersi:textfield name="home_phone" id="home_phone"
											key="home_phone" maxlength="20" validate="telephone" />
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:textfield name="email" id="email" key="email"
											maxlength="100" colspan="3" validate="email" />
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:textarea name="remark" id="remark" maxlength="100"
											rows="4" key="remark" colspan="3" />
									</powersi:editorlayout-row>
								</powersi:editorlayout>
						</div>
					</div>
					<div id="divTab2">
						<powersi:codeselect id="selRoles" cssClass="multiselect" multiple="true" cssStyle="width:100%"
								name="roles" list="#request.roleList" listKey="role_id"
								listValue="role_text" />
					</div>	
					<div id="divTab3" class="hidden">
						<div id="divRight">
							<table class="tableFrame">
								<colgroup>
									<col width="30%" />
									<col width="70%" />
								</colgroup>
								<tbody>
									<tr class="tableHeader">
										<td align="center" colspan="2">数据权限信息</td>
									</tr>
									<tr id="trOrg">
										<td colspan="2" align="left" title="红色统筹中心，黑色乡镇街道，蓝色村组社区">
											 <div id="divOrgTree">
	                                			<ul id="tvOrg" class="ztree"></ul>
	                            			</div>
										</td>
									</tr>
									<tr>
										<td>
											<div id="divOrgFilter">
	                                     			</div>
										</td>
										<td>
											<input type="button" class="button" onclick="viewOrgs()" value="查看数据权限" title="查看已勾选的数据权限" />
											<input type="button" value="全部展开" id="btExpandAll"
												class="button" onclick="expandAll(true)" title="全部展开" />
											<input type="button" value="全部收缩" id="btCollapseAll"
											class="button" onclick="expandAll(false)" title="全部收缩" />
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</powersi:tabbedpanel>
				<div style="margin-top:15px;">
					<div id="unifiedTip" class="fixedLeft textError" style="display:none;">
						该用户由统一门户维护
					</div>
					<div class="fixedRight textError">
						<label id="lbUserName"></label>
					</div>
				</div>
			</powersi:form>
		</powersi:pagelayout-panel>
	</powersi:pagelayout>
	<powersi:errors />
<script type="text/javascript">
	var SYSTEM_NAME = "<%=systemName%>";
    var SYSTEM_ID = "0";
    var ROOT_DEPTID = <%=deptId%>;
    
	var NEW_USERID = "-1";
   	
    var _curItemId = "-1";
    var _curItemData = "";
    var _curTreeId = "-1";
    
    var _orgTree = null;
    var _orgNodes = {};
    
    var _staffTree = null;
    var _staffList = {};
    
    var _orgFilter = null;
    var _lastFind = null;
    var _nodeList = [];
    
    var _staffFilter = null;
    var _stafflastFind = null;
    var _staffnodeList = [];
    
    var _staffLevels = {};
    
    var $s_select2Dept= null;
    
    var _selectGridData = null;
    
    function resizeMultiselect(r, w){
   		try{
   			if(!r.is(':ui-multiselect')){
   				return;
   			}
   			if(w < 100){
        		r.width(10);
	        	r.multiselect('resize');
        		r.width("99%");
	        } else {
	        	r.width(w);
	        }
	        r.multiselect('resize');
   		} catch(ex){}
   	}
   	
    function setSize() {
    	try{
    		var winheight = $(window).height();
    	    $("#grid").height(winheight - 100);
    	    $("#divTabs").height(winheight - 100);
    	    
            $("#divUser").height(winheight - 105);
            $("#divOrgTree").height(winheight - 260);
             
            var r = $('#selRoles');
            if(r){
            	r.height(winheight - 165);
            	r.width($('#divUser').width());
            	
            	resizeMultiselect(r, $('#divUser').width());
            }
    	} catch(ex){
    		alert(ex.message);
    	}
    }
    $(document).ready(function() {
        setSize();
        
        //手动初始化部门下拉框
       	/* initSelect2('#dept_id', {
       		formatSelection: function(state){
       			return $.trim(state.text).replace(/&nbsp;/g, '');
       		}
       	});
         */
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
	        	  /* 	if(SYSTEM_ID == ROOT_DEPTID) {
			        	var obj = new Object();
			        	obj.dept_up_name = SYSTEM_NAME;
			        	obj.dept_name = SYSTEM_NAME;
			        	obj.dept_id = SYSTEM_ID;
			        	obj.dept_fid = "-"+SYSTEM_ID+"-";
			        	obj.dept_up_id = SYSTEM_ID;
			        	array[0] = obj;
	  	    	    } */
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
       	
		try{
			var orgTree = '<%=request.getAttribute("orgTree")%>';
			if(!powersi.isvalue(orgTree)){
				orgTree = 'null';
			}
			
			buildeOrgTree(powersi.tojson(orgTree));
		} catch(e){
			alert('获取机构树出错：' + e.message);
		}
		
		$('#tvOrg').quickfilter({
	    	srcElement: '',
	        attached: '#divOrgFilter',
	        stripeRowClass: null,
	        inputText: '查找 数据权限',
	        inputTitle: '输入关键字后，按回车键查找符合条件的数据',
	        labelText: '',
	        filter: function(i){
	        	return "";
	        },
	        onAfter: function(){
	        	try{
	        		if(_orgFilter){
	            		var txt = powersi.trim(_orgFilter.val());
	            		var zTree = _orgTree;
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
	               				var curNodes = zTree.getSelectedNodes();
	                   			if(curNodes.length > 0){
	                   				var curIdx =  getTreeOrder("tvOrg", curNodes[0]);
	                   				for(var i = 0; i < len; i ++){
	                   					var idx = getTreeOrder("tvOrg", _nodeList[i]);
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
		                		
		                	_orgFilter.focus();
	               		} else {
	               			alert('没有找到"' + txt + '"对应的项。');
	               		}
	            	}
	        	} catch(e){
	        		alert(e.message);
	        	}
			}
	    });
		
		//初始化角色列表
		$("#selRoles").multiselect();
		$("#staff_id").val(NEW_USERID);
		
		$("#dept_id").val(ROOT_DEPTID);
		/*$("#s2id_dept_id").children('a').removeClass("select2-default");
		$("#s2id_dept_id").find('#select2-chosen-7').html(SYSTEM_NAME); */
		
		//刷新
		grid.reload();
    });
    
/*     function search() {
		 $("#queryForm").submit();
	} */
   
	function onSelectRow(rowdata, rowid, rowobj){
		grid.selectRow(rowid,"select");
		
		_selectGridData = rowdata;
		
		if(rowdata.isstaff == '1'){
   			clearFormValidtion($('#userForm'));
   			getUserInfo(rowdata.staff_id);
   		}
	}
    
   	function buildeOrgTree(orgs) {
		var trees = [];
    	
        if (orgs != null && powersi.isarray(orgs) && orgs.length > 0) {
            for (var idx in orgs) {
                var org = orgs[idx];

                var treeNode = {"id": org.org_id, "pId": org.org_up_id, "name": org.org_name, "orgNo" : (org.org_type + "_" + org.org_id)};
                
                if(org.org_type == "3"){
                	treeNode["font"] = {"color": "#00f", "background-color":"transparent"};
                	treeNode["iconSkin"] = "blue";
                } else if(org.org_type == "1"){
                	treeNode["font"] = {"color": "#f00", "background-color":"transparent"};
                	treeNode["iconSkin"] = "red";
                } else {
                	treeNode["iconSkin"] = "green";
                }
                
                trees.push(treeNode);
            }//end for orgs
            
            if(trees.length > 0){
            	trees[0].open = true;	
            }
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
    					enable: true
    				}
    			},
				callback: {
					onClick: onTreeClick
				},
				check: {
					enable: true,
					chkStyle: "checkbox",
					chkboxType: {"Y": "", "N": ""},
					autoCheckTrigger: false
				}
    	};
		$.fn.zTree.init($('#tvOrg'), setting, trees);
		
		_orgNodes = {};
		_orgTree = $.fn.zTree.getZTreeObj("tvOrg");
		
		//初始化机构id和机构树id的对应关系
		if(trees.length > 0){
			 var nodes = _orgTree.transformToArray(_orgTree.getNodes());
			 for(var idx in nodes){
		    	var node = nodes[idx];
		    	_orgNodes[node.id.toString()] = node.tId;
		    }
		    nodes.length = 0;
		}
    }
   	
   	function onTreeClick(event, treeId, treeNode, clickFlag) {
   		expandNode(treeId, treeNode);
   	}
   	
   	function expandNode(treeId, treeNode){
   		var zTree = $.fn.zTree.getZTreeObj(treeId);
   		zTree.expandNode(treeNode);
   	}
   	
   	function expandAll(expandFlag){
   		if(_orgTree) {
   			_orgTree.expandAll(expandFlag);
   		}
   	}

    //传入的是staff_id
    function getUserInfo(staff_id){
    	$('#userForm :button').hide();
    	
        doReset();
        
        _curItemId = staff_id;
        
        var staff = findStaff(_curItemId);
        
        $("#dept_id").val(_curItemId);
        $("#dept_id_name").val(_curItemId);
        $("#s2id_dept_id_name").children('a').removeClass("select2-default");
		$("#s2id_dept_id_name").find('#select2-chosen-7').html(staff.dept_name);
		
        postJSON(rootPath + "/user/GetStaffInfo.action", {"staff_id": staff_id}, showUserInfo);
    }

    function showUserInfo(json) {
        if (!checkJSONResult(json)) {
            return;
        }

        var data = json.data;
        if(!powersi.isvalue(data)){
	        alert('获取用户信息失败');
	        return;
        }
        
        $('#userForm :button').show();

        /*
        for(var k in data) {
        	$('#' + k).val(data[k]);
        }
        $("#staff_sta" + data['staff_sta']).prop("checked",true);
        
        //触发select2
        $("#userForm select").change();
        */
        json2form('#userForm', data);
        
        $('#lbUserName').text(data["staff_name"]);
        
        resetPassword();
        
        if(powersi.isvalue(json.data.roles) && json.data.roles.length > 0){
	    	var s = json.data.roles.split(",");
	    	$('#selRoles').multiselect('select', s);
	    	
	    	$('#his_roles').val(json.data.roles);
	    } else {
	    	$('#his_roles').val('');
	    }
        
        var o = [];
        if(powersi.isvalue(json.data.orgs) && json.data.orgs.length > 0){
	    	var s = json.data.orgs.split(",");
	    	for(var i = 0; i < s.length; i ++) {
	    		var tId = _orgNodes[s[i]];
	    		if(tId){
              		var node = _orgTree.getNodeByTId(tId);
              		if(node){
              			if(node.isParent){
              				_orgTree.expandNode(node, true, false, true, false);	
          					} else {
          						_orgTree.expandNode(node.getParentNode(), true, false, true, false);
 	            			}
              			node.checked = true;
              			_orgTree.updateNode(node, false);
              			
              			o.push(node.orgNo);
              		}
              	}
	    	}
	    	
	    	$('#his_orgs').val(o.join(','));
	    } else {
	    	$('#his_orgs').val('');	
	    }
        
        //unifiedCtrl(data['unified_flag']);
        unifiedCtrl(('<%=upServerFlag%>' == '1') ? '0' : data['unified_flag']);
        
        _curItemData = $('#userForm').serialize() + "&orgs=" + $('#his_orgs').val();
    }

    function doAdd() {
    	doReset();
    	
        $('#staff_id').val("-1");
        $('#dis_order').val("0");

        var staff = findStaff(_curTreeId);
		if(staff) {
			if(staff.isstaff == '1'){
				$("#dept_id").val(staff.dept_up_id);	
				$("#dept_id_name").val(staff.dept_up_id);
				$("#s2id_dept_id_name").children('a').removeClass("select2-default");
				$("#s2id_dept_id_name").find('#select2-chosen-7').html(staff.dept_name);
				
			} else {
				$("#dept_id").val(staff.dept_id);
				$("#dept_id_name").val(staff.dept_up_id);
				$("#s2id_dept_id_name").children('a').removeClass("select2-default");
				$("#s2id_dept_id_name").find('#select2-chosen-7').html(staff.dept_name);
			}
			
			$("#center_id").val(staff.center_id);
		}
        
        _curItemId = NEW_USERID;

        $('#btAdd').hide();
        $('#btCopy').hide();
        $('#btCancel').hide();
        $('#btDel').hide();
        $('#btSave').show();
        $('#btMenuTree').show();
        
        unifiedCtrl('0');
        
        $('#login_user').focus();
    }
    
    function doReset() {
    	$('#lbUserName').text('');
    	resetPassword();
    	
    	$('#userForm').resetForm();
        $('#userForm :checkbox').removeAttr("checked");
        $('#selRoles').multiselect('clear');
        
        if(_orgTree){
        	_orgTree.checkAllNodes(false);	
        }
        
        //触发select2
        $('#userForm select').change();
    }
    
    function doCopy() {
        $('#staff_id').val("-1");
        
        $('#login_user').val($('#login_user').val() + " 复制");
        $('#staff_name').val("");
        
        _curItemId = NEW_USERID;

        $('#btAdd').hide();
        $('#btCopy').hide();
        $('#btCancel').hide();
        $('#btDel').hide();
        $('#btSave').show();
        $('#btMenuTree').show();
        
        unifiedCtrl('0');
        
        $('#login_user').focus();
    }
    
    function doSave() {
    	var action = null;
    	var orgs = getSelectOrgs();
        var saveItemData = $("#userForm").serialize() + "&orgs=" + orgs;
        
        
        if (_curItemId != NEW_USERID) {
            if (saveItemData == _curItemData) {
                alert("没有修改，不需保存");
                return;
            }

            action = "ModifyStaff.action";

            if(powersi.check("password", "str")){
		       if(!confirm('您确认修改用户登录密码吗?')){
		        return;
		       }
	        }
        } else {
	        action = "AddStaff.action";
        }
       
       	if(!checkForm('#userForm')){
        	return;
        } 
       	
        if(!checkForm()){
        	//$("#divTabs").tabs("select", 0);
        	$("#divTabs").tabs("option", "active", 0);
        	return;
        }
        
        var dept_id = $('#dept_id').val();
        if(pwd == '' || dept_id == null || dept_id == 0 ) {
        	alert("所属部门不能为空.");
            return;
        }
        
        var pwd = $('#password').val();
        if(pwd != ''){
        	$('#password').val(hex_md5(pwd).toUpperCase());
        	 saveItemData = $("#userForm").serialize() + "&orgs=" + orgs;
        	 resetPassword();
        }
        postJSON(rootPath + "/user/" + action, saveItemData+"&dept_id="+$('#dept_id').val(), saveSuccess);
    }

    function resetPassword(){
    	$('#password').focus().blur();
		$('#password').val('');
		
		$('#login_user').focus();
    }
    
    function saveSuccess(json){
	    if(!checkJSONResult(json)){
		    return;
	    }

	    alert(json.message);
	    
	   	var staffLevel = $('#staff_level').val();
	   	if(staffLevel != ""){
	   		staffLevel = _staffLevels[staffLevel];
	   	}
	   	
	    var userId = json.data.staff_id;
	    _curItemId = userId;
	  
	    $('#staff_id').val(userId);
		//getStaffTree();
		//刷新
		grid.reload();
		
	    var orgs = getSelectOrgs();
	    _curItemData = $("#userForm").serialize() + "&orgs=" + orgs;
	    
	    
	    $('#his_roles').val($('#selRoles').val());
        $('#his_orgs').val(orgs);
        
	    $('#btAdd').show();
	    $('#btCopy').show();
        $('#btCancel').show();
        $('#btDel').show();
        $('#btMenuTree').show();
    }
    
    function doDel() {
    	if (_curItemId == NEW_USERID) {
            alert("没有保存，不能删除");
            return;
        }
        
        if (!confirm("您确认删除用户[" + $('#login_user').val() + "]吗?")) {
            return;
        }
	        
        postJSON(rootPath + "/user/DelStaff.action", _curItemData, delSuccess);
    }

    function delSuccess(json){
	    if(!checkJSONResult(json)){
		    return;
	    }

	    alert(json.message);
	    
	    var staff = findStaff(_curItemId);
	    if(powersi.isvalue(staff)){
	    	_curItemId = staff.dept_up_id;
	    } else {
	    	_curItemId = "-1";
	    }
	    
	    //getStaffTree();
	    //刷新
		grid.reload();
	    
	    doReset();
	    
	    $('#btAdd').show();
	    $('#btCopy').hide();
        $('#btCancel').hide();
        $('#btDel').hide();
        $('#btSave').hide();
        $('#btMenuTree').hide();
        
	    $('#staff_id').val(NEW_USERID);
	    $('#dis_order').val("0");
    }
    
   	function doCancel(){
   		var saveItemData = $("#userForm").serialize() + "&orgs=" + getSelectOrgs();
   		if(_curItemData == saveItemData){
	   		return;
   		}
   		getUserInfo(_curItemId);
   	}
   	
   	function viewOrgs(){
   		var o = [];
   		var nodes = _orgTree.getCheckedNodes(true);
		for(var idx in nodes){
			o.push(nodes[idx].name);
		}
   	  	
   	  	if(powersi.length(o) == 0){
   	  		alert("没有选择数据权限。");
   	  	} else {
   	  		alert("已经选择下列数据权限：\r\n" + o.join("\r\n"));	
   	  	}
   	}
   	
   	function getSelectOrgs(){
		var a=[];
		var nodes = _orgTree.getCheckedNodes(true);
		for(var idx in nodes){
			a.push(nodes[idx].orgNo);
		}
		
        var str = a.join(",");
        a.length = 0;
        return str;
	}
   	
  	function changeUserName(obj){
   		$('#lbUserName').text(obj.value);
   	}
  	
	function doMenuTree(){
		var a = [];
		var b = [];
		$('#selRoles').find("option:selected").each(function(){
			a.push($(this).val());
			b.push($(this).text());
		});
		
		var param = {
			'roles': a.join(','),
			'rolenames': b.join(','),
			'staff_name' : $('#staff_name').val(),
			'login_user' : $('#login_user').val(),
			'userid': $('#staff_id').val()
		};
		
		popupDialogWithParam(rootPath + "/pages/sys/manager/MenuTree.jsp", param, 550, 600);
	}
	
	function getStaffTree(){
    	 postJSON(rootPath + "/user/StaffManager!getStaffTree.action", {"root_dept_id": -1}, showStaffTree);
    }
    
    function showStaffTree(json) {
    	if (!checkJSONResult(json)) {
            return;
        }
    	
    	//刷新
		grid.reload();
    }
    
     function selectStaff(staffId){
    	var zTree = $.fn.zTree.getZTreeObj("tvStaff");
    	var node = zTree.getNodeByParam("id", staffId, null);
    	if(node){
    		if(node.isParent){
				zTree.expandNode(node, true, false, true, false);	
			} else {
				zTree.expandNode(node.getParentNode(), true, false, true, false);
			}
    		zTree.selectNode(node, false);
    	}
    }
     
    function findStaff(staffId){
    	var data = null;
        if (!powersi.isvalue(_selectGridData)) {
            return data;
        }
        return _selectGridData;
    }
    
    var staff_cols = ["login_user", "staff_name", "staff_sta", "lock_state", "sex", "birthdate", "idcardno", "email", "mobile_phone", "home_phone", "office_phone", "dept_id", "dis_order"];
    function unifiedCtrl(unifiedFlag){
    	if(unifiedFlag == "1"){
    		$("#unifiedTip").show();
    		//允许管理员删除禁用的统一门户用户
    		if(<%=gradeId%> >= 4 && $('#staff_sta').val() == '0'){
    			$("#btDel").removeProp("disabled");
    		} else {
    			$("#btDel").prop("disabled", true);	    			
    		}
    		
    		$.each(staff_cols, function(n, id){
    			setDisabled("#" + id);
    		});
    		$("#dept_id").prop("disabled", true);
    	} else {
			$("#unifiedTip").hide();
    		
    		$("#btDel").removeProp("disabled");
    		
    		$.each(staff_cols, function(n, id){
    			setEnabled("#" + id);
    		});
    	}
    }
    
</script>
</body>
</powersi:html>