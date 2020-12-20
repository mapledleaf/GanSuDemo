<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	request.setAttribute("clusters", com.powersi.hygeia.framework.ClusterMapping.getCodetable());
%>
<powersi:html>
<head>
<powersi:head title="系统任务管理" />
<script src="${strutsPath}/include/fileupload.js" type="text/javascript"></script>
</head>
<body>
	<powersi:form id="queryForm" namespace="/manager" action="TaskManager!query">
		<powersi:panelbox key="panel_query" allowAddition="true">
			<powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit" key="button_query" />
				<powersi:reset id="btReset" key="button_reset" />
				<powersi:button id="btViewStatus" value="任务调度器状态" onclick="viewStauts()" buttonIcon="icon-tasks"></powersi:button>
				<powersi:button id="btViewCron" value="表达式生成器" onclick="viewCron()" buttonIcon="icon-code"></powersi:button>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="6">
				<powersi:editorlayout-row>
					<powersi:codeselect id="serverId" name="serverId" label="服务器"
						list="#request.clusters" listKey="key" listValue="value" onchange="selectServer()" cssClass="select2">
					</powersi:codeselect>
					<powersi:textfield id="instanceName" label="当前实例" disabled="true" />
					<powersi:textfield id="taskNameDesc" name="task_nameordesc" label="任务名称和描述" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row addition="true">
					<powersi:textfield id="triigerInstance" name="trigger_instance" label="实例名称" />
					<powersi:textfield id="taskClass" name="task_class" label="任务类名" />
					<powersi:checkboxlist id="validFlag" key="valid_flag" codeType="valid_flag"></powersi:checkboxlist>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>

	<powersi:panelbox key="panel_result" title="任务列表">
		<powersi:datagrid id="grid" formId="queryForm" rowAttrRender="gridRowRender" delayLoad="true"
			detail="{onShowDetail: showDetail, height:'auto'}" onDblClickRow="dblClickRow"
			exportFileName="'系统任务'" showExportExcel="true" showExportExcel2007="true" showExportPdf="true">
			<powersi:toolbar>
				<powersi:toolbar-item id="add" text="新增任务" icon="add" click="itemClick" title="新增系统任务" />
				<powersi:toolbar-item id="copy" text="复制任务" icon="copy" click="itemClick" title="复制系统任务" />
				<powersi:toolbar-item id="edit" text="编辑任务" icon="edit" click="itemClick" title="编辑系统任务" />
				<powersi:toolbar-item id="delete" text="删除任务" icon="delete" click="itemClick" title="删除系统任务" />
				<powersi:toolbar-item id="import" text="导入任务" icon="import" click="itemClick" title="导入系统任务" position="right"/>
				<powersi:toolbar-item id="export" text="导出任务" icon="export" click="itemClick" title="导出系统任务" position="right"/>
			</powersi:toolbar>
			<powersi:datagrid-column key="operate" width="60" frozen="true" isSort="false" isExport="false" render="renderOperate" />
			<powersi:datagrid-column display="任务名称" name="task_name" width="150" frozen="true" />
			<powersi:datagrid-column display="任务描述" name="task_desc" width="150" frozen="true" />
			<powersi:datagrid-column display="任务配置">
				<powersi:datagrid-column display="任务类名" name="task_class" width="100" />
				<powersi:datagrid-column display="任务参数" name="task_param" width="100" />
				<powersi:datagrid-column display="触发器类型" name="trigger_type_name" width="100" data="trigger_type" code="task_trigger" />
				<powersi:datagrid-column display="触发器值" name="trigger_value" width="100" />
				<powersi:datagrid-column display="触发器实例" name="trigger_instance" width="150" />
				<powersi:datagrid-column display="锁定标志" name="lock_flag_name" width="60" code="task_lock" data="lock_flag" render="renderLock" />
				<powersi:datagrid-column display="有效标志" name="valid_flag_name" width="100" code="valid_flag" data="valid_flag" render="renderFlag"/>
				<powersi:datagrid-column display="生效时间" name="effect_date" width="150" />
				<powersi:datagrid-column display="失效时间" name="expire_date" width="150" />
			</powersi:datagrid-column>
			<powersi:datagrid-column display="任务状态">
				<powersi:datagrid-column display="最后执行时间" name="last_date" width="150" />
				<powersi:datagrid-column display="最后执行实例" name="last_instance" width="150" />
				<powersi:datagrid-column display="本次执行时间" name="this_date" width="150" />
				<powersi:datagrid-column display="本次执行实例" name="this_instance" width="150" />
				<powersi:datagrid-column display="下次执行时间" name="next_date" width="150" />
				<powersi:datagrid-column display="最后运行耗时" name="last_runtime" width="100" />
				<powersi:datagrid-column display="合计执行次数" name="total_count" width="100" />
				<powersi:datagrid-column display="成功执行次数" name="success_count" width="100" />
				<powersi:datagrid-column display="出错执行次数" name="error_count" width="100" />
			</powersi:datagrid-column>
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:script>
		function createDetailGridOption(){
				<powersi:datagrid id="gridOption" url="${rootPath }/manager/TaskManager!queryLog.action"
					isScroll="false" pageSize="10" frozen="false" delayLoad="true" useCount="false"
					rowAttrRender="gridRowRender"
					exportFileName="'tasklog'" showExportExcel="true" showExportExcel2007="true" showExportPdf="true">
					<powersi:datagrid-column name="instance_name" display="实例名称" width="200" frozen="true" />
					<powersi:datagrid-column name="task_name" display="任务名称" width="150" frozen="true" />
					<powersi:datagrid-column name="task_desc" display="任务描述" width="150" />
					<powersi:datagrid-column name="task_date" display="任务时间"  width="150" />
					<powersi:datagrid-column name="begin_date" display="开始时间"  width="150" />
					<powersi:datagrid-column name="end_date" display="结束时间"  width="150" />
					<powersi:datagrid-column name="task_runtime" display="运行耗时(秒)"  width="100" />
					<powersi:datagrid-column name="task_state_name" display="任务状态" width="80" code="task_state" data="task_state" />
					<powersi:datagrid-column name="task_param" display="任务参数" width="150" />
					<powersi:datagrid-column name="task_result" display="任务结果" width="150" />
					<powersi:datagrid-column name="task_remark" display="任务备注" minWidth="150" width="100%" exportWidth="150" />
				</powersi:datagrid>
				
				return gridOption;
			}
			
			function showDetail(row, detailPanel, callback) {
			    var div = document.createElement('div');
			    var width = $(detailPanel).append(div).width() - 15;
	
				var gridOption = createDetailGridOption();
			    gridOption['width'] = width;
			    
			   	gridOption['parms'] = {'beginDate': '2000-01-01 00:00:00', 'endDate': '2099-12-31 00:00:00', 'task_name': '"' + row['task_name'] + '"'};
			   	gridOption['exportFileName'] = row['task_desc'] || '';
			    
			    var gridDetail = $(div).ligerGrid(gridOption);
			    $(div).wrap('<div style="margin:10px"></div>');
	
			    gridDetail.refreshSize();
			    gridDetail.reload(true);
			}
				
			function dblClickRow(data, rowid, rowdata){
				grid.toggleDetail(rowdata);
			}
		</powersi:script>
	<!-- 隐藏区 -->
	<div class="hidden">
		<div id="detailDlg">
			<form id="detailForm">
				<powersi:hidden id="detail_id" name="id" />
				<powersi:editorlayout cols="4">
					<powersi:editorlayout-row>
						<powersi:textfield id="detail_task_name" name="task_name"
							label="任务名称" required="true" maxlength="50" placeholder="使用英文标识"></powersi:textfield>
						<powersi:textfield id="detail_task_desc" name="task_desc"
							label="任务描述" required="true" maxlength="50" placeholder="使用中文标识"></powersi:textfield>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="detail_task_class" name="task_class"
							label="任务类名" colspan="3" required="true" maxlength="500"></powersi:textfield>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textarea id="detail_task_param" name="task_param"
							label="任务参数" colspan="3" maxlength="4000" rows="5"></powersi:textarea>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:radio id="detail_trigger_type" name="trigger_type" codeType="task_trigger"
							label="触发器类型" colspan="3" required="true" maxlength="500"></powersi:radio>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="detail_trigger_value" name="trigger_value"
							label="触发器值" colspan="3"
							required="true" maxlength="100" buttonId="btnTriggerValue" onbuttonclick="editTrigger()"></powersi:textfield>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="detail_trigger_instance" name="trigger_instance" 
							title="*表示全部，多实例逗号分隔" placeholder="*表示全部，多实例逗号分隔"
							label="触发器实例" colspan="3" required="true" maxlength="1000" ></powersi:textfield>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:radio id="detail_lock_flag" name="lock_flag" label="锁定标志" codeType="task_lock" required="true"></powersi:radio>
						<powersi:radio id="detail_valid_flag" name="valid_flag" label="有效标志" codeType="valid_flag" required="true"></powersi:radio>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="detail_effect_date" name="effect_date" label="生效时间" mask="datetime" placeholder="为空当前时间"></powersi:textfield>
						<powersi:textfield id="detail_expire_date" name="expire_date" label="失效时间" mask="datetime" placeholder="为空永远有效"></powersi:textfield>
					</powersi:editorlayout-row>
				</powersi:editorlayout>
				<div class="divButton">
					<powersi:button id="btnDetailOk" key="button_save" onclick="save()" />
					<powersi:button id="btnDtailClose" key="button_close" onclick="closeDlg()" />
				</div>
			</form>
		</div>
		<div id="statusDlg">
			<powersi:datagrid id="statusGrid" dataAction="local" dataType="local"
				usePager="false" showReload="false" height="400">
				<powersi:datagrid-column display="状态名" name="status_name" width="50%" />
				<powersi:datagrid-column display="状态值" name="status_value" width="50%" />
			</powersi:datagrid>
		</div>
		<div id="uploadDlg">
			<div class="textLeft"><span class="textInfo"><i class="icon-info-sign"></i> 请选择系统任务文件（使用json格式)</span></div>
			<div style="margin: 5px 0 20px 0;">
				<input type="file" id="fileUpload" name="uploads" class="file" />
			</div>
			
			<div class="floatLeft">
				<input id="forceUpdate" name="forceUpdate" class="checkbox" type="checkbox">
				<label class="checkboxLabel textError" for="forceUpdate">覆盖已存在的数据</label>
			</div>
			
			<div class="floatRight">
				<powersi:button id="btUpload" key="button_import" cssClass="btn btn-success" />
				<powersi:button id="btCloseUpload" onclick="closeUploadDlg()" key="button_close" />
			</div>
		</div>
	</div>

	<powersi:errors />
	<script type="text/javascript">
		$(function(){
			selectServer();
			
			$(':radio[name="trigger_type"]').click(function(){
				selectTrigger();
			});
			
			$(':checkbox[name="valid_flag"]').eq(0).prop('checked', true);
			
			grid.reload(true);
		});
		
		function gridRowRender(row, rowid) {
			if (row['valid_flag'] == '0') {
				return getColorStyle('default');
			} else if(powersi.length(row['this_instance']) > 0 || row['task_state'] == '0'){
				return getColorStyle('orange');
			} else if(row['task_state'] == '2'){
				return getColorStyle('error');
			}
		}

		function renderOperate(row, index, value) {
			var a = [];
			
			if(row['valid_flag'] == '1'){
				a.push('<input type="button" value="执行" class="linkButton"');
				a.push(' onclick="runRow(');
				a.push(index);
				a.push(')"');

				if (powersi.length(row['this_instance']) > 0) {
					a.push(' disabled="disabled" title="任务已经锁定，不能并发执行"');
					//开始监控任务执行
					setTimeout(function(){checkTask(row['task_name']);}, 5000);					
				}
			
				a.push(" />");
			}
			
			return a.join('');
		}

		function renderFlag(rowdata, index, value) {
			if (rowdata['valid_flag'] === '0') {
				return '<span title="无效"><i class="icon-remove textError"></i></span>';
			} else {
				return '<span title="有效"><i class="icon-ok textSuccess"></i></span>';
			}
		}
		
		function renderLock(rowdata, index, value) {
			if (rowdata['lock_flag'] === '0') {
				return '';
			} else {
				return '<span title="锁定"><i class="icon-lock textSuccess"></i></span>';
			}
		}

		function itemClick(item) {
			var itemname = item['id'];
			if (itemname == 'add') {
				addRow();
			} else if(itemname == 'copy') {
				copyRow();
			} else if(itemname == 'delete') {
				deleteRow();
			} else if(itemname == 'edit') {
				editRow();
			} else if(itemname == 'import'){
				upload();
			} else if(itemname == 'export'){
				download();
			}
		}

		var serverUrl = "";
		function createProxy(url){
			if(serverUrl == ""){
				return url;
			} else {
				return rootPath + "/ProcessCluster?url=" + encode64(encodeURI(serverUrl + url));	
			}
		}
		
		function selectServer(){
			serverUrl = $('#serverId').val();
			
			$('#instanceName').val('').prop('title', '');
			postAjax(createProxy(rootPath + '/manager/TaskManager!queryStatus.action'), function(res){
				if(res.data){
					$('#instanceName').val(res.data["instance_name"] || '').prop("title", res.data['is_running'] ? '正在运行中' : '');
				}
			});
		}
		
		var statusDlg = null;
		function viewStauts(){
			postAjax(createProxy(rootPath + '/manager/TaskManager!queryStatus.action'), function(res){
				if(res.data){
					var rows = [];
					$.each(res.data, function(key, value){
						rows.push({
							'status_name': key,
							'status_value': value
						});
					});
					if(statusDlg){
						statusDlg.show();
					} else{
						statusDlg = popupDiv('#statusDlg', '任务调度器状态', 600);
					}
					statusGrid.reload(rows);
				}
			});
		}
		
		function viewCron(){
			popupDialog(rootPath + '/pages/sys/manager/CronExpBuilder.jsp', 600, 600);
		}
		
		function selectTrigger(){
			var type = $(':radio[name="trigger_type"]:checked').val();
			var label = $('label[for="detail_trigger_value"] > span').eq(0);
			
			if(type == "1" || type == "2"){
				label.text("时间间隔(秒)");
			} else if(type == "3"){
				label.text("触发器类名");
			} else{
				label.text("Cron表达式");
			}
		}
		
		function editTrigger() {
			var type = $(':radio[name="trigger_type"]:checked').val();
			
			if(type == "1"){
				popupInfo("输入时间间隔(秒)\n实际任务执行间隔=时间间隔");
			} else if(type == "2"){
				popupInfo("输入时间间隔(秒)\n实际任务执行间隔=任务执行时间+时间间隔");
			} else if(type == 3) {
				popupInfo("输入自定义触发器类名\n必须实现com.powersi.hygeia.framework.scheduling.Trigger接口");			
			} else{
				popupDialog({
					height: 550,
					width: 600,
					url:rootPath + '/pages/sys/manager/CronExpBuilder.jsp',
					data:$('#detail_trigger_value').val(),
					buttons: [{"text": "确定", "id": "btnOK", onclick: function(button, dialog, index){
						var exp = $('#txtCronExp', dialog.getFrameDocument()).val();
						if(exp)
							$('#detail_trigger_value').val(exp);	
						
						dialog.close();
					}}, {"text": "取消", onclick: function(button, dialog, index){
						dialog.close();
					}}]
				});
			}
		}
		
		function save() {
			if (!checkForm('#detailForm')) {
				return;
			}

			var id = $('#detail_id').val();
			var name = $('#detail_task_name').val();
			
			if(id != '' && id != name){
				popupConfirm('确认把任务名称 <b class="textError">' + id + '</b> 修改成 <b class="textError">' + name + '</b> 吗？\n\n请注意：任务日志不会同步修改。', 
						'提示', 
						function(yes){
							if(yes){
								postSave();
						}
				});
			} else{
				postSave();	
			}
		}
		
		function postSave(){
			var data = form2json('#detailForm');
			postAjax(rootPath + '/manager/TaskManager!save.action', 
					data,
					function(res) {
						if (!checkResult(res, 'popup')) {
							return;
						}

						popupSuccess(res.message, '保存', function(){
							grid.reload(true);
							closeDlg();
						},5000);
					}
			);
		}

		var dlg = null;
		function addRow() {
			clearForm('#detailForm');
			json2form('#detailForm', {
				'id' : '',
				'task_class' : 'com.powersi.hygeia.framework.scheduling.BusiTask',
				'task_param' : '<Program><FunctionID></FunctionID><parameters></parameters></Program>',
				'trigger_type' : '0',
				'trigger_instance' : $('#instanceName').val(),
				'lock_flag': '1',
				'log_flag': '1',
				'valid_flag' : '1'
			});

			if (dlg) {
				dlg.show();
			} else {
				dlg = popupDiv("#detailDlg", '', 600);
			}
			dlg.set('title', '新增任务');
			
			selectTrigger();
		}

		function closeDlg() {
			if (dlg) {
				dlg.hide();
			}
		}

		function editRow() {
			var row = grid.getSelectedRow();
			if (row) {
				clearForm('#detailForm');
				json2form('#detailForm', row);
				$('#detail_id').val(row['task_name']);
				if (dlg) {
					dlg.show();
				} else {
					dlg = popupDiv("#detailDlg", '', 600);
				}
				dlg.set('title', '编辑任务');
				selectTrigger();
			} else {
				popupAlert("请选择任务");
			}
		}
		
		function copyRow() {
			var row = grid.getSelectedRow();
			if (row) {
				clearForm('#detailForm');
				json2form('#detailForm', row);
				$('#detail_id').val('');
				$('#detail_task_name').val(row['task_name'] + '_copy');
				if (dlg) {
					dlg.show();
				} else {
					dlg = popupDiv("#detailDlg", '', 600);
				}
				dlg.set('title', '复制任务');
				selectTrigger();
			} else {
				popupAlert("请选择任务");
			}
		}

		function deleteRow() {
			var row = grid.getSelectedRow();
			if (row) {
				popupConfirm('确认删除任务' + row['task_name'] + '[' + row['task_desc']
						+ ']吗？\n\n<b class="textError">删除后无法恢复，建议设置任务的有效标志为无效。</b>', 
					"删除",
					function(yes){
					if(yes){
						postAjax(rootPath + '/manager/TaskManager!delete.action', grid
								.formatRow(row), function(res) {
							if (!checkResult(res, 'popup')) {
								return;
							}

							popupSuccess(res.message, '删除', function(){
								grid.deleteRow(row);								
							}, 5000);
						});
					}
				});
			} else {
				popupAlert("请选择任务");
			}
		}
		
		function runRow(index) {
			var row = grid.getRow(index);
			if (row) {
				var instanceName = $('#instanceName').val();
				popupConfirm('确认在 <b class="textError">' + instanceName + '</b> 上执行任务<b class="textError">' + row['task_name'] + '[' + row['task_desc']
						+ '] </b>吗？', 
					"执行",
					function(yes){
					if(yes){
						postAjax(createProxy(rootPath + '/manager/TaskManager!run.action'), {
							'task_name': row['task_name']
						}, function(res) {
							if (!checkResult(res, 'popup')) {
								return;
							}

							popupSuccess(row['task_name'] + '[' + row['task_desc'] + ']执行开始', '开始', 5000);

							grid.reload(true);
						});
					}
				});
			}
		}
		
		function checkTask(name){
			postAjax(rootPath + '/manager/TaskManager!queryTask.action', {
				'task_name': '"' + name + '"', 'this_instance': '""'
			}, function(res) {
				if(res && res.data) {
					$.each(grid.getRows(), function(key, row){
						if(row['task_name'] == name){
							grid.updateRow(row, res.data);
							grid.reRender();
							return false;
						}
					});
					var msg = res.data['task_name'] + '[' + res.data['task_desc'] + ']执行结束';
					popupSuccess(msg, '结束', 5000);	
						
					return;
				}
				
				setTimeout(function(){checkTask(name);}, 5000);
			});
		}
		
		function download(){
			postDownload(rootPath + '/manager/TaskManager!download.action', form2json('#queryForm'));
		}
		
		var dlgUpload = null;
		function closeUploadDlg(){
	    	if(dlgUpload){
	    		dlgUpload.hide();
	    	}
	    }
		
		function upload(){
			if(dlgUpload){
				dlgUpload.show();
			} else{
				dlgUpload = popupDiv("#uploadDlg", '导入文件', 350);
			}
		}
		
		//ajax文件上传
		function initFileUpload(){
			var fileUpload = $("#fileUpload");
			try{
				fileUpload.fileupload({
			        url: rootPath + '/manager/TaskManager!upload.action',
			        dataType: 'json',
			        autoUpload: false,
			        replaceFileInput: false,
			        done: function (e, data) {
			        	if(data.result.errortype == "0"){
			        		//成功后重置input file
			        		fileUpload.fileupload("destroy");
			        		fileUpload[0].outerHTML = fileUpload[0].outerHTML;
			        		
			        		grid.reload(true);
			        		
			        		initFileUpload();
			        	}
			        	
		                showPageError(data.result);
			        },
			        fail: function (e, data) {
			            showPageError(data.message);
			        }
			    });
			}
			catch(ex){
				alert(ex.message + '\r\n' + '请检查是否包含头文件fileupload.js');
			}
		}
		
		//初始化文件上传
		$(function(){
			initFileUpload();
			
			$("#btUpload").click(function(){
				var fileName = $('#fileUpload').val();
				if(powersi.isempty(fileName)){
					popupAlert("请选择文件");
					return;
				}
				
				var forceUpdate = $('#forceUpdate').prop('checked');
				var tip = "您确认导入[" + fileName + "]吗?";
				if(forceUpdate){
					tip += '\r\n\r\n\r\n' + '<b class="textError">请注意：操作会覆盖已存在的数据，建议您最好导出数据作为备份。</b>';
				}
				
				popupConfirm(tip, '导入', function(yes){
					if(yes){
						closeUploadDlg();
						
						showRunning(true);
						
						var data = {
							'forceUpdate': forceUpdate ? 'true' : 'false'	
						};
						
						var fileUpload = $('#fileUpload');
						fileUpload.fileupload('send', {
					        fileInput: fileUpload,
					        formData: data
					    }).complete(function (result, textStatus, jqXHR) {showRunning(false);});
					}
				});
			});
		});
	</script>
</body>
</powersi:html>