<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	String hasLocalId = String.valueOf(com.powersi.hygeia.framework.util.DBHelper.existColumn("SYS_CODE_TABLE_DETAIL", "LOCAL_ID"));
	request.setAttribute("hasLocalId", hasLocalId);
%>
<powersi:html>
<head>
<powersi:head title="码表维护" />
<script src="${strutsPath}/include/fileupload.js" type="text/javascript"></script>
</head>
<body class="container-fluid">
	<powersi:panelbox key="panel_query">
		<powersi:form id="queryForm" namespace="/manager" action="CodetableManager!query">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="codeType" name="code_type"  label="码表类型" />
					<powersi:textfield id="codeName" name="code_name"  label="码表名称" />
					<powersi:textfield id="codeSQL" name="code_sql"  label="码表SQL" />
					<powersi:editorlayout-button colspan="2">
						<powersi:submit id="btSubmit" key="button_query" />
						<powersi:reset id="btReset" key="button_reset" />
					</powersi:editorlayout-button>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:form>
	</powersi:panelbox>
	<div class="row">
		<div class="col-5">
			<powersi:panelbox key="panel_result" title="码表列表" allowCollapse="false">
				<powersi:datagrid id="gridList" formId="queryForm"
					onSelectRow="selectRow" frozen="false" showReload="false" onLoadData="loadData" rowAttrRender="gridRowRender"
					showExportExcel="true" exportFileName="'系统码表'" >
					<powersi:toolbar>
						<powersi:toolbar-item id="import" text="导入码表" icon="import" click="itemClick" title="导入系统码表" />
						<powersi:toolbar-item id="export" text="导出码表" icon="export" click="itemClick" title="导出系统码表" />
						<powersi:toolbar-item id="clean" text="清理查询" icon="clean" click="itemClick" title="清理明细查询和排序条件" position="right" />
						<powersi:toolbar-item id="search" text="显示查询" icon="search2" click="itemClick" title="显示或隐藏查询面板" position="right" />
					</powersi:toolbar>
					<powersi:datagrid-column display="码表类型" name="code_type" width="50%" />
					<powersi:datagrid-column display="码表名称" name="code_name" width="50%" />
					<powersi:datagrid-column display="有效标志" name="valid_flag" width="60" isExport="false" render="renderFlag" />
					<powersi:datagrid-column display="码表SQL" name="code_sql" width="300" align="left" hide="true" isAllowHide="true" isExport="true" />
				</powersi:datagrid>
			</powersi:panelbox>
		</div>
		<div class="col-7">
			<powersi:codetable id="validList" codeType="valid_flag">
			</powersi:codetable>
			
			<powersi:panelbox iconClass="icon-cog" title="码表维护" allowCollapse="false">
					<powersi:panelbox-toolbar>
						<ul class="nav nav-tabs" id="tabs">
							<li class="active">
								<a data-toggle="tab" href="#tab1">
									<i class="icon-th green"></i>
									码表详情
								</a>
							</li>
							<li>
								<a data-toggle="tab" href="#tab2">
									<i class="icon-th-list red"></i>
									码表明细
								</a>
							</li>
						</ul>
					</powersi:panelbox-toolbar>
					<div class="tab-content">
						<div class="tab-pane active" id="tab1">
							<powersi:form id="detailForm">
								<input type="hidden" id="id" name="id" />
								<powersi:editorlayout cols="20%,80%">
									<powersi:editorlayout-row>
										<powersi:textfield id="detail_code_type" name="code_type" label="码表类型" required="true" maxlength="50"></powersi:textfield>
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:textfield id="detail_code_name" name="code_name" label="码表名称" required="true"></powersi:textfield>
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:textarea name="code_sql" label="码表SQL" rows="15" maxlength="2000" placeholder="select data_value, display_value from sys_code_table_detail where code_type = ? order by dis_order"></powersi:textarea>
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:radio codeType="valid_flag" key="valid_flag" name="valid_flag" id="valid_flag" required="false" />
									</powersi:editorlayout-row>
								</powersi:editorlayout>
								<div class="space-y"></div>
								<div class="divButton">
									<powersi:button id="btnAdd" key="button_add" onclick="doAdd()"></powersi:button>
									<powersi:button id="btnSave" key="button_save" onclick="doSave()"></powersi:button>
									<powersi:button id="btnDel" key="button_delete" onclick="doDel()"></powersi:button>
									<powersi:button id="btnCancel" key="button_cancel" onclick="doCancel()"></powersi:button>
								</div>
							</powersi:form>
						</div>
						<div class="tab-pane" id="tab2">
							<powersi:datagrid id="gridDetail" frozen="false" showExportExcel="true" showReload="false" 
								rowAttrRender="gridRowRender" widthDiff="-5"
								allowUnSelectRow="true" rowDraggable="true">
								<powersi:toolbar>
									<powersi:toolbar-item id="add" text="新增" icon="add2" click="itemClick" title="新增系统码表明细"/>
									<powersi:toolbar-item id="del" text="删除" icon="delete2" click="itemClick" title="删除系统码表明细(按住ctrl键可多选)"/>
									<powersi:toolbar-item id="up" text="上移" icon="up" click="itemClick" title="上移明细(按住行号可以拖拽)"/>
									<powersi:toolbar-item id="down" text="下移" icon="down" click="itemClick" title="下移明细(按住行号可以拖拽)"/>
									<powersi:toolbar-item id="sort" text="序号" icon="sort2" click="itemClick" title="自动生成显示序号"/>
									<powersi:toolbar-item id="cancel" text="取消" icon="cross2" click="itemClick" title="取消修改" position="right" />
									<powersi:toolbar-item id="save" text="保存" icon="tick2" click="itemClick" title="保存修改" position="right" />
								</powersi:toolbar>
								<s:if test="#request.hasLocalId=='true'">
									<powersi:datagrid-column display="本地化标识" name="local_id" width="80" editor="{type: 'string'}" />
								</s:if>
								<powersi:datagrid-column display="显示序号" name="dis_order" width="60" editor="{type: 'number'}"/>
								<powersi:datagrid-column display="数据值" name="data_value" width="50%" editor="{type: 'string'}"/>
								<powersi:datagrid-column display="显示值" name="display_value" width="50%" editor="{type: 'string'}"/>
								<powersi:datagrid-column display="有效标志" name="valid_flag" width="60" render="renderFlag" editor="{type: 'select', data:validList}"/>
							</powersi:datagrid>
						</div>
					</div>
			</powersi:panelbox>
		</div>
	</div>
	<!-- 隐藏区 -->
	<div class="hidden">
		<div id="uploadDlg">
			<div class="textLeft"><span class="textInfo"><i class="icon-info-sign"></i> 请选择系统码表文件（使用json格式)</span></div>
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
	var hasLocalId = <%=hasLocalId%>;
	
	/*使用图标显示有效或者无效*/
	function renderFlag(rowdata, index, value) {
		if (value === '0') {
			return '<span title="无效"><i class="icon-remove textError"></i></span>';
		} else {
			return '<span title="有效"><i class="icon-ok textSuccess"></i></span>';
		}
	}
	
	/*红色显示错误记录*/
	function gridRowRender(rowdata, rowid){
		if (rowdata['valid_flag'] === '0') {
			return getColorStyle('error');
		}
	}
	
	$(function(){
		gridDetail.setItemDisabled();
		
		bindAfterSelectTab('#tabs', function(newTabId){
			if(newTabId == 'tab2'){
				//必须在可见的情况下绑定，否则datagrid高度计算有误
				if(!gridDetail.condition){
					gridDetail.bindCondition({prefixID: 'search_', validate: false, labelWidth: 60, showClose: true,
						fields: [
						  { name: 'data_value', label: '数据值',  width:100, newline: false, validate:{required:false}},
						  { name: 'display_value', label: '显示值', width:100, newline: false}
					]});
					
					gridDetail.toggleCondition(false);
				}
			}
		});
	});
	
	function selectRow(rowdata, rowid, rowobj){
		showDetail(rowdata);
	}
	
	function loadData() {
		if(gridDetail){
			showDetail();
		}
	}
	
	function showDetail(rowdata){
		//ipad因为性能原因无法同时渲染多个datagrid
		if(!gridDetail || $.isFunction(gridDetail.endEdit) == false){
			return;
		}
		
		//结束编辑状态
		gridDetail.endEdit();				
		
		if(rowdata){
			json2form("#detailForm", rowdata);
			
			var codeType = rowdata['code_type'];
			
			gridDetail.set("url", rootPath + '/manager/CodetableManager!queryDetail.action?type=' + codeType);
			gridDetail.set('exportFileName', '系统码表明细_' + codeType);
			
			if(powersi.isempty(rowdata['code_sql'])){
				gridDetail.setItemEnabled();
				gridDetail.set('enabledEdit', true);
				gridDetail.set('rowDraggable', true);
			} else{
				gridDetail.setItemDisabled();
				gridDetail.set('enabledEdit', false);
				gridDetail.set('rowDraggable', false);
			}
		} else{
			clearForm("#detailForm");
			$('#id').val('');
			
			gridDetail.reset();
			
			gridDetail.set("url", "");
			gridDetail.set('exportFileName', '');
			
			gridDetail.setItemDisabled();
			gridDetail.set('enabledEdit', false);
		}
	}
	
	function itemClick(item){
		var itemid = item["id"];
		
		//结束编辑状态
		gridDetail.endEdit();
		
		if(itemid == "add"){
			gridDetail.addEditRow({'code_type': $('#id').val()});
		} else if(itemid == "del"){
			gridDetail.deleteSelectedRow();
		} else if(itemid == "up"){
			gridDetail.up(gridDetail.getSelected());
		} else if(itemid == "down"){
			gridDetail.down(gridDetail.getSelected());
		} else if(itemid == "sort"){
			sortDetail();
		} else if(itemid == "cancel"){
			gridDetail.reload(true);
		} else if(itemid == "save"){
			saveDetial();
		} else if(itemid == "search"){
			gridDetail.toggleCondition();
		} else if(itemid == "clean"){
			gridDetail.clearCondition();
			gridDetail.clearSort();
		} else if(itemid == 'import'){
			upload();
		} else if(itemid == 'export'){
			download();
		}
	}
	
	function sortDetail() {
		var rows = gridDetail.getRows();
		var i = 0;
		var colidx = gridDetail.getColumnIndex('dis_order');
		$.each(rows, function(rowid, row){
			i = i + 1;
			if(row['dis_order'] != i){
				gridDetail.updateCell(colidx, i, rowid);
				gridDetail.setCellEdited(row, colidx, true);	
			}
		});
	}
	
	function saveDetial() {
		var data = gridDetail.getChangesJSON();
		if(powersi.isempty(data)){
			alert("没有修改，无需保存");
			return;
		}
		
		var invalid = false;
		var rows = gridDetail.getChangeRows();
		$.each(rows, function(i, row){
			if(powersi.isempty(row['data_value'])){
				invalid = true;
				
				gridDetail.select(row);
				alert('数据值不能为空');
				return false;
			}
		});
		
		if(invalid){
			return;
		}
		
		if(!confirm("您确认保存明细修改吗?")){
			return;
		}
		
		postAjax(rootPath + "/manager/CodetableManager!saveDetail.action", data, function(json){
			if(!checkResult(json)){
				return;
			}
			
			//更新id（后台是根据id更新数据，以实现主键可以修改)
			var records = gridDetail.getRows();
			$.each(rows, function(i, row){
				var record = records[gridDetail.getRowId(row)];
				if(record){
					if(hasLocalId){
						record['id'] = row['local_id'] || '0' + '|' + row['data_value'];
					} else {
						record['id'] = row['data_value'];						
					}
				}
			});
			
			gridDetail.acceptChanges();
		});
	}
	
	function doAdd(){
		showDetail();

		$("#btnAdd").hide();
		$("#btnDel").hide();
	}
	
	function doCancel() {
		showDetail(gridList.getSelectedRow());
		
		$("#btnAdd").show();
		$("#btnDel").show();
	}
	
	function doSave() {
		var form = $("#detailForm");
		if(!checkForm(form)){
			return;
		}
		
		var data = form2json(form);
		var oper = powersi.isempty(data['id']) ? 'add' : 'update';
		
		if(data['id'] != data['code_type']){
			//使用同步方法检查码表类型是否重复
			var json = postSync(rootPath + '/manager/CodetableManager!query.action',  {"code_type": data['code_type']});
			var exist = false;
			if(json.rows && json.rows.length > 0){
				alert(powersi.tostring(json.rows));
				//后台使用的模糊查询
				$.each(json.rows, function(i, row){
					if(row['code_type'] == data['code_type']){
						exist = true;
						return false;
					}
				});
			}
			if(exist){
				alert('码表' + data['code_type'] + "已经存在，请检查");
				return;
			}
		}
		
		if(!confirm("您确认" + (oper == 'add' ? '新增' : '修改') + data['code_name'] + '吗?')){
			return;
		}
		
		postAjax(rootPath + "/manager/CodetableManager!save.action?oper=" + oper, data, function(json){
			if(!checkResult(json)){
				return;
			}
			
			//更新id（后台是根据id更新数据，以实现主键可以修改)
			data['id'] = data['code_type'];
			if(oper == 'add'){
				var row = gridList.addRow(data);
				
				gridList.select(row);
				
				$("#btnAdd").show();
				$("#btnDel").show();
			} else{
				gridList.updateRow(gridList.getSelectedRow(), data);
			}
			
			showDetail(data);
		});
	}
	
	function doDel() {
		var id = $('#id').val();
		if(powersi.isempty(id)){
			return;
		}
		
		if(!confirm("您确认删除" + id + '吗?')){
			return;
		}
		
		postAjax(rootPath + "/manager/CodetableManager!save.action?oper=del", {"id": id}, function(json){
			if(!checkResult(json)){
				return;
			}
			
			gridList.deleteSelectedRow();
			showDetail();
		});
	}
	
	function download(){
		postDownload(rootPath + '/manager/CodetableManager!download.action', form2json('#queryForm'));
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
		        url: rootPath + '/manager/CodetableManager!upload.action',
		        dataType: 'json',
		        autoUpload: false,
		        replaceFileInput: false,
		        done: function (e, data) {
		        	if(data.result.errortype == "0"){
		        		//成功后重置input file
		        		fileUpload.fileupload("destroy");
		        		fileUpload[0].outerHTML = fileUpload[0].outerHTML;
		        		
		        		initFileUpload();
		        	}
	                showPageError(data.result);
		        },
		        fail: function (e, data) {
		            showPageError(data.message);
		        }
		    });
		} catch(ex) {
			alert(ex.message + '\r\n' + '请检查是否包含头文件fileupload.js');
		}
	}
	//初始化文件上传
	$(function(){
		initFileUpload();
		
		$("#btUpload").click(function(){
			var fileName = $('#fileUpload').val();
			if(powersi.isempty(fileName)){
				alert("请选择文件");
				return;
			}
			
			var forceUpdate = $('#forceUpdate').prop('checked');
			var tip = "您确认导入[" + fileName + "]吗?";
			if(forceUpdate){
				tip += '\r\n\r\n\r\n' + '请注意：操作会覆盖已存在的数据，建议您最好导出数据作为备份。';
			}
			
			if(confirm(tip) != true){
				return;
			}
			
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
		});
	});
</script>
</body>
</powersi:html>