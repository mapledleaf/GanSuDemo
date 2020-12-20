<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="用户管理" />
</head>
<body>
	<!-- key都是对应message.properties中的描述等.. -->
	<!-- 收缩盒子panelbox  isCollapse 初始化时 是否收缩-->
	<powersi:panelbox key="panel_query" allowAddition="true"
		isCollapse="false">
	<!-- form表单 进入页面会默认执行表单action的 execute方法（） namespace(访问路径)--> 
		<powersi:form id="queryForm"   namespace="/user" action="XakUserManagerAction">
		<!-- 编辑器布局 cols 列的宽度 默认6个格子（类似table）-->
			<powersi:editorlayout cols="8%,25%,8%,25%,8%,13%,13%">
				<powersi:editorlayout-row>
					<!-- 	input的text （自动匹配td） mask="掩码 like url,datetime" -->
					<powersi:textfield id="user_idcard" name="searchUserDto.user_idcard" key="user_idcard" />
					<powersi:textfield id="user_name" name="searchUserDto.user_name" key="user_name"  />
					<!--table按钮 -->
					<powersi:editorlayout-button colspan="4">
						<powersi:submit id="btSubmit" key="button_query" />
						
						<powersi:submit id="TestBtn" key="Test" onclick="doTest()" />
						<powersi:submit id="btAdd" key="button_add"  onclick="doAdd()"/>
						<powersi:reset id="btReset" key="button_reset" />
					</powersi:editorlayout-button>
				</powersi:editorlayout-row>
					<powersi:editorlayout-row addition="true">
					<powersi:radio codeType="sex" name="searchUserDto.user_sex"  key="sex"  colspan="1" />
					<powersi:codeselect name="searchUserDto.user_age" id="user_age"
									key="age" codeType="user_age"  value="0" showValue="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:form>
	</powersi:panelbox>
	
	<powersi:panelbox key="panel_result" title="用户列表">
 	<!-- 数据表格控件 （用来循环数据的套路）-->
	<!-- datagrid加载数据事件顺序 -->
	<!-- loadData->loading(ajax)->loaded(ajax)->beforeShowData->AfterShowData-loadedData-->
	<!-- formId匹配 form组件 rowAttrRender 添加操作 -->
		<powersi:datagrid id="grid" formId="queryForm" >
			<powersi:datagrid-column key="operate" render="renderOperate" isExport="false" isSort="false" width="80" />
			<powersi:datagrid-column name="user_idcard" key="user_idcard"    width="240"   />
			<powersi:datagrid-column name="user_name" key="user_name"  width="240" />
			<powersi:datagrid-column name="user_sex" key="sex"   render="renderSex"  width="240"   />
			<powersi:datagrid-column name="user_age" key="age" width="240" minWidth="100" />
		</powersi:datagrid> 
	</powersi:panelbox>
	<powersi:errors />
	<script type="text/javascript">
	//doTest
	function doTest(){
		
			
				grid.getServerData({
					'data': {"searchUserDto.id" : Id	},
					'callback': function(data){
						grid.updateRow(index, data[0]);
						grid.select(index);
					}
				});
			
		
	}
	
	
	
	// 性别转换
	function renderSex(rowdata,index,value){
		if(rowdata['user_sex']== '1'){
			return "<span >男</span>";
		}else{
			return "<span >女</span>";
		}
	}
	// 编辑&删除 (按钮)
	function renderOperate(row, index, value){
		var a = [];
		a.push('<input type="button" value="编辑" class="linkButton"');
		a.push(' onclick="doEdit(');
		a.push(index);
		a.push(')"');
		if(row['audit_flag'] == '1'){
			a.push(' disabled="disabled"');
		}
		a.push(" />");
		
		a.push("&nbsp;&nbsp;");
		
		a.push('<input type="button" value="删除" class="linkButton"');
		a.push(' onclick="doDel(');
		a.push(index);
		a.push(')"');
		if(row['audit_flag'] == '1'){
			a.push(' disabled="disabled"');
		}
		a.push(" />");
		
		return a.join('');
	}	

  	function doAdd() {
		popupDialog({
			url: rootPath + "/user/XakUserEditAction.action",
			onClosed: function() {
				var ret = this.returnValue;
				if(ret){//如果返回有值
					if(ret === true){
						grid.reload();				
					} else{//否则没有返回值 
						grid.getServerData({
							'data': {
								"searchUserDto.id" : ret	
							},
							'callback': function(data){
								//新增记录放最前面(如果放最后，后2个参数不输入即可)
								grid.addRows(data, grid.getRow(0), true);
						  		grid.select(0);
							}
						});
					}
				}
			}
		},300, 400);
	}
  	
  	function doEdit(index) {
  		var Id = grid.getRow(index)["id"];
		popupDialog({
			url: rootPath + "/user/XakUserEditAction.action?id=" + Id,
			onClosed: function() {
				var ret = this.returnValue;
				if (ret != null) {
					grid.getServerData({
						'data': {"searchUserDto.id" : Id	},
						'callback': function(data){
							grid.updateRow(index, data[0]);
							grid.select(index);
						}
					});
				}
			}
		}, 300, 400);
	}

  	function doDel(index) {
  		var row = grid.getRow(index);

  		var Id = row['id'];
  		var Title = row['user_name'];
  		
  		if (!confirm("您确认删除用户[" + Title + "]吗?")) {
            return;
        }
  		
  		postJSON(rootPath + "/user/XakUserManagerAction!delete.action?id=" + Id, function(json){
			if(!checkJSONResult(json)){
			    return;
		    }
			//弹出删除成功消息
		    alert(json.message);
		    //删除该行
		    grid.deleteRow(index);
  		});
  	} 

</script>
</body>
</powersi:html>