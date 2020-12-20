<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="系统参数管理" />
<script src="${strutsPath}/include/fileupload.js" type="text/javascript"></script>
</head>
<body>
	<powersi:panelbox key="panel_query" allowAddition="false">
		<powersi:form id="queryForm" namespace="/manager" action="ParameterManager!query">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="paramCode" name="code" label="参数代码" />
					<powersi:textfield id="paramValue" name="value" label="参数值" />
					<powersi:textfield id="paramDesc" name="remark" label="参数说明" />
					<powersi:editorlayout-button colspan="2">
						<powersi:submit id="btSubmit" key="button_query" />
						<powersi:reset id="btReset" key="button_reset" />
					</powersi:editorlayout-button>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:form>
	</powersi:panelbox>
	
	<powersi:panelbox key="panel_result" title="参数列表">
		<powersi:datagrid id="grid" formId="queryForm" rowAttrRender="gridRowRender"
			showExportExcel="true" exportFileName="'系统参数'"
			enabledEdit="true" clickToEdit="false" onBeforeSubmitEdit="beforeSubmitEdit">
			<powersi:toolbar>
				<powersi:toolbar-item id="add" text="新增参数" icon="add" click="itemClick" title="新增系统参数" />
				<powersi:toolbar-item id="import" text="导入参数" icon="import" click="itemClick" title="导入系统参数" />
				<powersi:toolbar-item id="export" text="导出参数" icon="export" click="itemClick" title="导出系统参数" />
				<powersi:toolbar-item id="cancel" text="取消修改" icon="cross2" click="itemClick" title="取消系统参数修改" position="right" />
				<powersi:toolbar-item id="save" text="保存修改" icon="tick2" click="itemClick" title="保存系统参数修改" position="right" />
			</powersi:toolbar>
			<powersi:datagrid-column key="operate" width="80" frozen="true" isSort="false" isExport="false" render="renderOperate" />
			<powersi:datagrid-column display="参数代码" name="code" width="25%" minWidth="100" editor="{type: 'string'}" />
			<powersi:datagrid-column display="参数值" name="value" width="25%" minWidth="100" editor="{type: 'string'}" />
			<powersi:datagrid-column display="参数说明" name="remark" width="50%" minWidth="200" editor="{type: 'string'}" />
		</powersi:datagrid>
	</powersi:panelbox>
	
	<!-- 隐藏区 -->
	<div class="hidden">
		<div id="uploadDlg">
			<div class="textLeft"><span class="textInfo"><i class="icon-info-sign"></i> 请选择系统参数文件（使用json格式)</span></div>
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
	function gridRowRender(rowdata, rowid){
		if (grid && grid.hasStatusChanged(rowdata)) {
			return getColorStyle('error');
		}
	}
	
	/*输出操作*/
	function renderOperate(row, index, value) {
		var a = [];
		if (!row._editing){
			a.push('<input type="button" value="编辑" class="linkButton"');
			a.push(' onclick="beginEdit(');
			a.push(index);
			a.push(')"');
			a.push(" />");
			
			a.push("&nbsp;&nbsp;");
			
			a.push('<input type="button" value="删除" class="linkButton"');
			a.push(' onclick="deleteRow(');
			a.push(index);
			a.push(')"');
			a.push(" />");
		} else {
			a.push('<input type="button" value="确定" class="linkButton textError"');
			a.push(' onclick="endEdit(');
			a.push(index);
			a.push(')"');
			a.push(" />");
			
			a.push("&nbsp;&nbsp;");
			
			a.push('<input type="button" value="取消" class="linkButton textError"');
			a.push(' onclick="cancelEdit(');
			a.push(index);
			a.push(')"');
			a.push(" />");
		}
		
		return a.join('');
	}
	
	function beginEdit(index) { 
	    grid.beginEdit(index);
	}
	
	function cancelEdit(index) {
		try{
			grid.cancelEdit(index);
			    
		    var row = grid.getRow(index);
		    if(!row['id']){
		    	grid.deleteRow(index);
		    	grid.setItemEnabled('add');
		    }
		} catch(e){
			alert(e.message);
		}
	}
	
	function endEdit(index) {
	    try{
		    if(grid.endEdit(index) == false){
		    	return;
		    }
		    
		    var row = grid.getRow(index);
	    	if(!row['id']) {
		    	row['id'] = '';
		    	grid.setItemEnabled('add');
		    }
		} catch(e){
			alert(e.message);
		}
	}
	
	function beforeSubmitEdit(editdata) {
		if(!grid.hasEditChanged(editdata)){
			alert('数据没有修改，请取消编辑操作');
			return false;
		}
		
		if($.trim(editdata.newdata['code']) == ''){
	    	alert('参数代码不能为空');
	    	return false;
	    }
		
		grid.setRowColor(editdata.record, powersi.color('error'));
		return true;
	}
	
	function itemClick(item){
		var itemname = item['id'];
		if(itemname == 'add'){
			addRow();
		} else if(itemname == 'save'){
			save();
		} else if(itemname == 'cancel'){
			cancel();
		} else if(itemname == 'import'){
			upload();
		} else if(itemname == 'export'){
			download();
		}
	}
	
	function addRow(){
		grid.addRow({}, grid.getRow(0), true);
		/*为了控制不会新增空行，限制一次只能新增一条，新增确定或者取消后方能再新增*/
		grid.setItemDisabled('add');
		beginEdit(0);
	}
	
	function deleteRow(index) {
		var row = grid.getRow(index);
		
		if (row['id'] && row['id'].length > 0 && !confirm("您确认删除参数[" + row['id'] + "]吗?")) {
	        return;
		}
		
		grid.deleteRow(index);
	}
	
	function save(){
		if(!grid.checkEditing()){
			return;
		}
		
		var data = grid.getChangesJSON();
		try{
			if(powersi.isempty(data)){
				alert("没有修改，无需保存");
				return;
			}
		} catch(ex){
			alert(ex.message);
		}
		
		
		if(!confirm("您确认保存修改吗?")) {
	        return;
		}
		
		postJSON(rootPath + '/manager/ParameterManager!save.action', data, function(json){
			if(!checkJSONResult(json)){
				return;
			}
			
			//更新id（后台是根据id更新数据，以实现主键可以修改)
			var changes = grid.getChangeRows();
			var records = grid.getRows();
			$.each(changes, function(i, row){
				var record = records[grid.getRowId(row)];
				if(record){
					record['id'] = row['code'];
				}
			});
			
			grid.acceptChanges();
			grid.reRender();
			alert(json.message);
		});
	}
	
	function cancel(){
		var editing = grid.hasEditing();
		var changed = grid.hasChanged();
		if(!editing && !changed){
			return;
		}
		
		if(!confirm("您确认取消修改吗?")) {
	        return;
		}
		
		grid.setItemEnabled('add');
		
		if(editing){
			grid.cancelEdit();	
		}
		
		if(changed){
			grid.reload(true);
		}
	}
	
	function download(){
		postDownload(rootPath + '/manager/ParameterManager!download.action', form2json('#queryForm'));
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
		        url: rootPath + '/manager/ParameterManager!upload.action',
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