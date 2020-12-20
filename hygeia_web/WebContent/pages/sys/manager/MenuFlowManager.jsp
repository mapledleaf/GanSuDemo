<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	String op = request.getParameter("op");
	if(op == null){
		op = "view";
	}
	String title = null;
	if(op.equals("view")){
		title = "查看菜单流程";
	} else {
		title = "管理菜单流程";
	}
	
	String data = com.powersi.hygeia.web.util.JsonHelper.toJson((java.util.List)com.powersi.sys.manager.util.MenuFlowUtil.query());
	String demo = "true".equals(request.getParameter("demo")) ? "true" : "false";
	
	String subsys = com.powersi.hygeia.web.util.JsonHelper.toJson((java.util.List)com.powersi.sys.manager.util.MenuFlowUtil.getSubSystem());
%>
<powersi:html>
<head>
<powersi:head title="<%=title %>" />
<script src="${strutsPath}/include/fileupload.js" type="text/javascript"></script>
<style type="text/css">
.l-layout-top{
	border: 0;
}
.l-layout-center{
	border-color: #ddd;
}
.input-group-btn > button > i[class *="icon-"] {
	margin-right: 0;
	font-size: 14px;
	color: #333;
}
#cb {
	padding-top: 0;
	padding-bottom: 0;
}
#url{
	height: 26px;
	line-height: 26px;
}
#btnRefresh{
	margin-right: 20px;
}
.l-box-select{
	height: 300px;
}
</style>
<script type="text/javascript">
var subsys = <%=subsys%>;
var flows = <%=data %>;
</script>
</head>
<body class="page">
	<powersi:pagelayout id="layout1" topHeight="30" allowTopResize="false">
	   <powersi:pagelayout-panel position="top">
       		<div class="row">
       			<div class="col-6">
   					<div class="input-group">
   						<powersi:combobox id="cb" isMultiSelect="false" cancelable="false" 
   							selectBoxHeight="300" hideOnLoseFocus="true"
   							textField="flow_name" valueField="flow_key" onSelected="selectFlow"
   							tree="{checkbox: false, isExpand: 1, idFieldName:'flow_key', parentIDFieldName:'pid', textFieldName:'flow_name', topParentIDValue:'0'}">
   						</powersi:combobox>
   						<div class="input-group-btn">
   							<button id="btnRefresh" onclick="doRefresh()" class="btn btn-default" title="刷新">
   								<i class="icon-refresh"></i>
   							</button>
   							
   							<button id="btnSave" onclick="doSave()" class="btn btn-default edit" title="保存流程">
	   							<i class="icon-save textError"></i>
	   						</button>
							<button id="btnAdd" onclick="doAdd()" class="btn btn-default edit" title="新建流程">
								<i class="icon-file-alt"></i>
							</button>
							<button id="btnCopy" onclick="doCopy()" class="btn btn-default edit" title="复制流程">
								<i class="icon-copy"></i>
							</button>
							<button id="btnDel" onclick="doDel()" class="btn btn-default edit" title="删除流程">
								<i class="icon-remove"></i>
							</button>
							<button id="btnUpload" onclick="doUpload()" class="btn btn-default edit" title="导入流程">
								<i class="icon-upload-alt"></i>
							</button>
							<button id="btnDownload" onclick="doDownload()" class="btn btn-default edit" title="导出流程">
								<i class="icon-download-alt"></i>
							</button>
							<!-- 非常诡异，使用icon-question图标聚焦会消失 -->
							<button id="btnHelp" onclick="doHelp()" class="btn btn-default edit" title="帮助">
								<i class="icon-question-sign"></i>
							</button>
   						</div>
   					</div>
       			</div>
       			<div class="col-6">
					<powersi:textfield id="url" name="url" readonly="true"></powersi:textfield>
       			</div>
       		</div>
       </powersi:pagelayout-panel>
       <powersi:pagelayout-panel position="center">
       		<iframe id="main" name="main" frameborder="0" width="100%" height="100%" src="about:blank"></iframe>
       </powersi:pagelayout-panel>
    </powersi:pagelayout>
	<powersi:errors />
	<!-- 隐藏区 -->
	<div class="hidden">
		<div id="flowDlg">
			<powersi:form id="flowForm" name="flowForm" disabled="true">
				<powersi:editorlayout cols="20%,80%">
					<powersi:editorlayout-row>
						<powersi:textfield name="flow_key" id="flow_key" label="流程标识" maxlength="50" 
							required="true" placeholder="使用英文唯一标识流程"></powersi:textfield>
						<powersi:hidden name="flow_type" id="flow_type" value="menu"></powersi:hidden>
						<powersi:hidden name="flow_id" id="flow_id" value=""></powersi:hidden>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:combobox id="cbFlowGroup" label="流程分类" selectBoxHeight="200" 
							valueField="text" textField="text" valueFieldID="flow_group" data="subsys">
							<powersi:hidden id="flow_group" name="flow_group"></powersi:hidden>
						</powersi:combobox>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:spinner key="dis_order" id="dis_order"
							name="dis_order" required="true" validate="integer,min[0],max[999999]" maxlength="6"
							min="0" max="999999" mouseWheel="true" />
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield name="flow_name" id="flow_name" label="流程名称" maxlength="100" required="true"></powersi:textfield>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield name="flow_desc" id="flow_desc" label="流程描述" maxlength="200"></powersi:textfield>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textarea name="flow_data" id="flow_data" rows="10" label="流程数据" />
					</powersi:editorlayout-row>
				</powersi:editorlayout>
			</powersi:form>
			<div class="divButton">
				<powersi:button id="btSave" key="button_save" onclick="saveFlow()" />
				<powersi:button id="btClose" key="button_close" onclick="closeDlg()" />
			</div>
		</div>
		
		<div id="uploadDlg">
			<div class="textLeft"><span class="textInfo"><i class="icon-info-sign"></i> 请选择流程文件(json格式)</span></div>
			<div style="margin: 5px 0 20px 0;">
				<input type="file" id="fileUpload" name="uploads" class="file" />
			</div>
			
			<div class="textRight">
				<powersi:button id="btUpload" key="button_import" />
				<powersi:button id="btClose2" onclick="closeDlg()" key="button_close" />
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var op = "<%=op%>";
		var flowMap = {};
		var flowFirst = null;
		
		var dlg = null;
		var dlgwidth = 500;
		$(function(){
			try{
				//只有本机或者demo访问才显示demo程序
				var hostname = window.location.hostname;
				if(hostname == 'localhost' || hostname.substring(0, 4) == '127.' || '<%=demo%>' == 'true'){
					flows.push({"flow_key":"demo1", "flow_name": "菜单流程样例1"});
					flows.push({"flow_key":"demo2", "flow_name": "菜单流程样例2"});
				} else {
					//非本机访问隐藏url显示
					if(op == "view"){
						$("#url").hide();
					}
				}
				
				if(op == "view"){
					$(".edit").hide();
				}
				
				initSelect();
			} catch(e){
				alert(e.message);
			}
		});
		
		function initSelect(initValue) {
			var list = [];
			$.each(subsys, function(n, sys){
				sys.children = [];
			});
			
			flowMap = {};
			$.each(flows, function(n, flow){
				if(flow.flow_id){
					flowMap[flow.flow_key] = flow;	
				}
				
				var pid = 0;
				if(powersi.length(flow.flow_group) > 0){
					$.each(subsys, function(n, sys){
						if(sys.text == flow.flow_group){
							pid = 'sys' + sys.id;
							
							sys.children.push({
								"flow_key": flow.flow_key,
								"flow_name": flow.flow_name,
								"pid": pid
							});
							return false;
						}
					});
				}
				
				flow.pid = pid;
			});

			$.each(subsys, function(n, sys){
				if(sys.children && powersi.length(sys.children) > 0){
					list.push({
						"flow_key": 'sys' + sys.id,
						"flow_name": sys.text,
						"pid": 0
					});
					
					$.each(sys.children, function(k, flow){
						if(!initValue){
							initValue = flow.flow_key;
						}
						list.push({
								"flow_key": flow.flow_key,
								"flow_name": flow.flow_name,
								"pid": flow.pid
						});
					});
				}
			});

			$.each(flows, function(n, flow){
				if(flow.pid === 0){
					if(!initValue){
						initValue = flow.flow_key;
					}
					
					list.push({
						"flow_key": flow.flow_key,
						"flow_name": flow.flow_name,
						"pid": flow.pid
					});
				}
			});
			
			cb.treeManager.setData(list);
			if(initValue){
				cb.setValue(initValue);
			}
		}
		
		function selectFlow(key){
			if(!key || key == ''){
				return;
			}
			
			var url = "";
			if(key.length >= 4 && key.substring(0, 4) == 'demo'){
				url = (op == 'view') ? '/pages/sys/manager/MenuFlowViewDemo.jsp' : '/pages/sys/manager/MenuFlowEditorDemo.jsp';
			} else {
				url = (op == 'view') ? '/pages/sys/manager/MenuFlowViewPage.jsp' : '/pages/sys/manager/MenuFlowEditorPage.jsp';
			}
			
			url += '?key=' + key;
			if(url == $("#url").val()){
				return;
			}
			$("#main").attr("src", rootPath + url);
			$("#url").val(url);
		}
		
		function doRefresh() {
			$("#main").attr("src", $("#main").attr("src"));
		}
		
		function getData() {
			var win = document.getElementById('main').contentWindow;
			if($.isFunction(win.getFlowData)){
				return win.getFlowData();
			} else {
				return null;
			}
		}
		
		function doHelp() {
			var a = [];
			a.push("1.设计的时候尽量靠左靠上，画板宽度高度尽量最小，这样才能保证视图显示的时候居中");
			a.push("2.先画好所有节点，然后选择连线，根据流程顺序依次点击节点，可以快速实现连接");
			a.push("3.连接线的文本可以随意拖放，请自行调整合适的位置");
			a.push("4.连接线点击箭头可以快速选定，直接点击线可能不能选定");
			
			popupInfo(a.join("\r\n"));
		}
		
		function closeDlg() {
			if(dlg){
				dlg.close();
				dlg = null;
			}
		}
		
		function doAdd() {
			resetForm("#flowForm");
			$('#flow_id').val('');
			
			$('#dis_order').val('1000');
			cbFlowGroup.setValue('');
			
			dlg = popupDiv("#flowDlg", "新建流程", dlgwidth, {
				isHidden: false,
				showMax: true
			});
		}
		
		function doCopy() {
			var str = getData();
			if(!str){
				popupError("当前流程不支持复制!");
				return;
			}
			
			var flowData = powersi.tojson(str);
			if(!flowData || !flowData.props || !flowData.props.props){
				popupError("无效的流程数据!");
				return false;
			}
			
			$('#flow_id').val('');
			$('#flow_key').val(flowData.props.props["key"].value + '-copy');
			$('#flow_name').val(flowData.props.props["name"].value + ' 复制');
			$('#flow_desc').val(flowData.props.props["desc"].value);
			$('#flow_data').val(str);
			
			var flow = flowMap[flowData.props.props["key"].value] || {};
			$('#dis_order').val(flow.dis_order || '1000');
			cbFlowGroup.setValue(flow.flow_group || '');
			
			dlg = popupDiv("#flowDlg", "复制流程", dlgwidth, {
				isHidden: false,
				showMax: true
			});
		}
		
		function doSave() {
			var flow = flowMap[cb.getValue()];
			if(!flow){
				popupAlert("当前流程不支持保存!");
				return;
			}
			
			var str = getData();
			if(!str){
				popupAlert("当前流程不支持保存!");
				return;
			}
			
			var flowData = powersi.tojson(str);
			if(!flowData || !flowData.props || !flowData.props.props){
				popupError("无效的流程数据!");
				return;
			}
			
			resetForm("#flowForm");
			
			$('#flow_id').val(flow.flow_id);
			$('#flow_key').val(flowData.props.props["key"].value);
			$('#flow_name').val(flowData.props.props["name"].value);
			$('#flow_desc').val(flowData.props.props["desc"].value);
			$('#flow_data').val(str);
			
			$('#dis_order').val(flow.dis_order || '1000');
			cbFlowGroup.setValue(flow.flow_group || '');
			//setDisabled("#flowForm :input:not(:button)");
			
			dlg = popupDiv("#flowDlg", "保存流程", dlgwidth, {
				isHidden: false,
				showMax: true
			});
		}
		
		function saveFlow() {
			if(!checkForm('#flowForm')){
				return;
			}
			
			var json = form2json('#flowForm');
			if(json.flow_key.length >= 4 && json.flow_key.substring(0, 4) == 'demo'){
				popupAlert('流程标识不能使用demo开始的字符串');
				return;
			}
			
			//处理流程数据
			var flowData = {};
			if(json.flow_data && powersi.length(json.flow_data) > 0){
				if(!powersi.isjson(json.flow_data)){
					popupAlert('流程数据必须符合json格式规范');
					return;
				}
				
				flowData = powersi.tojson(json.flow_data);
			}
			
			//初始化流程数据
			if(!flowData.states){
				flowData.states = {}; 
			}
			if(!flowData.paths){
				flowData.paths = {}; 
			}
			if(!flowData.props){
				flowData.props = {"props": {}};
			}
			if(!flowData.props.props){
				flowData.props.props = {};
			}
			
			flowData.props.props["key"] = {"value" : json.flow_key};
			flowData.props.props["name"] = {"value" : json.flow_name};
			flowData.props.props["desc"] = {"value" : json.flow_desc};
			
			//不要使用powersi.tostring，会把数字转换成字符串，导致坐标、宽度、高度出错
			json.flow_data = liger.toJSON(flowData);
			
			postAjax(rootPath + '/manager/MenuFlowManager!save.action', json, function(res){
				if(!checkResult(res)){
					return;
				}

				var flowId = $('#flow_id').val();
				if(flowId == ''){
					flows.push(res.data);
				} else {
					$.each(flows, function(n, obj){
						if(obj.flow_id && obj.flow_id == flowId){
							$.extend(obj, res.data);
							return false;
						}
					});
				}
				
				initSelect(json.flow_key);
				
				popupSuccess(res.message);
				closeDlg();
			});
		}
		
		function doDel() {
			var flow = flowMap[cb.getValue()];
			if(!flow){
				popupAlert("当前流程不支持删除!");
				return;
			}
			
			popupConfirm("您确认删除流程" + cb.getText() + "(" + cb.getValue() + ")吗？", function(yes){
				if(yes){
					postAjax(rootPath + '/manager/MenuFlowManager!delete.action', {
						"flow_id": flow.flow_id
					}, function(res){
						if(!checkResult(res)){
							return;
						}
						
						popupSuccess(res.message);
						
						var a = [];
						$.each(flows, function(n, obj){
							if(obj.flow_id && obj.flow_id == flow.flow_id){
								
							} else {
								a.push(obj);
							}
						});
						flows.length = 0;
						flows = a;
						
						initSelect();
					});	
				}
			});
		}
		
		function doDownload() {
			var str = getData();
			if(!str){
				popupError("当前流程不支持导出!");
				return;
			}
			
			postDownload(rootPath + '/manager/MenuFlowManager!download.action', {
				'flow_key': cb.getValue(),
				'flow_name': cb.getText(),
				'flow_data': str
			});
		}
		
		function doUpload() {
			dlg = popupDiv("#uploadDlg", '导入流程', 350, {
				isHidden: false
			});
		}
		
		function initFileUpload(){
			var fileUpload = $("#fileUpload");
			fileUpload.fileupload({
		        url: rootPath + '/manager/MenuFlowManager!upload.action',
		        dataType: 'json',
		        autoUpload: false,
		        replaceFileInput: false,
		        done: function (e, data) {
		        	if(data.result.errortype == "0"){
		        		//成功后重置input file
		        		fileUpload.fileupload("destroy");
		        		fileUpload[0].outerHTML = fileUpload[0].outerHTML;
		        		
		        		initFileUpload();
		        		
		        		//导入流程
		        		var flowData = powersi.tojson(data.result.data.flow_data);
		    			if(!flowData || !flowData.props || !flowData.props.props){
		    				popupError("无效的流程数据!");
		    				return false;
		    			}
		    			
		    			$('#flow_id').val('');
		    			$('#flow_key').val(flowData.props.props["key"].value);
		    			$('#flow_name').val(flowData.props.props["name"].value);
		    			$('#flow_desc').val(flowData.props.props["desc"].value);
		    			$('#flow_data').val(data.result.data.flow_data);
		    			
		    			dlg = popupDiv("#flowDlg", "导入流程", dlgwidth, {
		    				isHidden: false,
		    				showMax: true
		    			});
		        	} else {
		        		showPageError(data.result);	
		        	}
		        },
		        fail: function (e, data) {
		            showPageError(data.message);
		        }
		    });
		}
		
		$(function(){
			initFileUpload();
			$("#btUpload").click(function(){
				var fileName = $('#fileUpload').val();
				if(powersi.isempty(fileName)){
					alert("请选择流程文件");
					return;
				}
				
				popupConfirm("您确认导入文件" + fileName + "吗?", function(yes){
					if(yes){
						closeDlg();
						
						var fileUpload = $('#fileUpload');
						fileUpload.fileupload('send', {
					        fileInput: fileUpload
					    }).complete(function (result, textStatus, jqXHR) {showRunning(false);});
					}	
				});
			});
		});
	</script>
</body>
</powersi:html>