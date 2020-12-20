<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="菜单列表" />
<script src="${strutsPath}/include/fileupload.js" type="text/javascript"></script>
</head>
<body>
	<powersi:panelbox key="panel_query" allowAddition="true">
		<powersi:form id="queryForm" namespace="/manager" action="MenuManager!query">
			<powersi:editorlayout cols="6">
				<powersi:editorlayout-row>
					<powersi:textfield id="menu_name" name="menu_name" label="菜单名称" placeholder="支持表达式" title="空格( )表示AND，竖线(|)表示OR，减号(-)表示NOT，双引号(\"x\")表示=x，双引号(\"\")表示NULL" />
					<powersi:textfield id="win_name" name="win_name" label="菜单地址" placeholder="支持表达式" title="空格( )表示AND，竖线(|)表示OR，减号(-)表示NOT，双引号(\"x\")表示=x，双引号(\"\")表示NULL" />
					<powersi:editorlayout-label>
						<input id="include_child" name="include_child" type="checkbox" class="checkbox" value="true" />
						<label for="include_child" class="checkboxLabel textError" title="支持菜单名称、地址、编号">包含下级菜单</label>
					</powersi:editorlayout-label>
					<powersi:editorlayout-button>
						<powersi:submit id="btSubmit" key="button_query" />
						<powersi:reset id="btReset" key="button_reset" />
					</powersi:editorlayout-button>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row addition="true">
					<powersi:editorlayout-label>
						<powersi:label>菜单编号</powersi:label>
					</powersi:editorlayout-label>
					<powersi:editorlayout-input>
						<div class="input-group">
							<powersi:textfield id="menu_id_min" name="menu_id_min" validate="integer" title="最小值" placeholder="最小值" />
							<span class="input-group-addon">-</span>
							<powersi:textfield id="menu_id_max" name="menu_id_max" validate="integer" title="最大值" placeholder="最大值"/>
						</div>
					</powersi:editorlayout-input>
					<powersi:checkboxlist id="right_flag" name="right_flag" label="权限标志" codeType="right_flag" />
					<powersi:checkboxlist id="log_flag" name="log_flag" label="日志标志" codeType="log_flag" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row addition="true">
					<powersi:checkboxlist id="menu_type" name="menu_type" label="菜单类型" codeType="menu_type" />
					<powersi:checkboxlist id="grade_id" name="grade_id" label="菜单级别" codeType="grade_id" />
					<powersi:checkboxlist id="valid_flag" key="valid_flag" codeType="valid_flag"></powersi:checkboxlist>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:form>
	</powersi:panelbox>

	<powersi:panelbox key="panel_result" title="菜单列表">
		<powersi:datagrid id="grid" formId="queryForm" delayLoad="true" 
			allowUnSelectRow="true"
			onDblClickRow="dblClickRow" rowAttrRender="gridRowRender"
			exportFileName="'系统菜单'" showExportExcel="true" showExportExcel2007="true" showExportPdf="true">
			<powersi:toolbar>
				<powersi:toolbar-item id="add" text="新增菜单" icon="add" click="itemClick" title="新增系统菜单" />
				<powersi:toolbar-item id="copy" text="复制菜单" icon="copy" click="itemClick" title="复制系统菜单" />
				<powersi:toolbar-item id="edit" text="编辑菜单" icon="edit" click="itemClick" title="编辑系统菜单" />
				<powersi:toolbar-item id="delete" text="删除菜单" icon="delete" click="itemClick" title="删除系统菜单" />
				<powersi:toolbar-item id="import" text="导入菜单" icon="import" click="itemClick" title="导入系统菜单" position="right"/>
				<powersi:toolbar-item id="export" text="导出菜单" icon="export" click="itemClick" title="导出系统菜单（按住ctrl键可以多选）" position="right"/>
				<powersi:toolbar-item id="select" text="查看选择" icon="view" click="itemClick" title="查看选择导出系统菜单" position="right"/>
			</powersi:toolbar>
			<powersi:datagrid-column key="operate" display="导出" width="80" frozen="true" isSort="false" isExport="false" render="renderOperate" />
			<powersi:datagrid-column display="菜单编号" name="menu_id" width="100" frozen="true"/>
			<powersi:datagrid-column display="菜单名称" name="menu_name" width="150" frozen="true"/>
			<powersi:datagrid-column display="菜单描述" name="menu_desc" width="150" />
			<powersi:datagrid-column display="上级菜单编号" name="menu_up_id" width="100" />
			<powersi:datagrid-column display="上级菜单名称" name="menu_up_name" width="150" />
			<powersi:datagrid-column display="显示序号" name="menu_order" width="80" />
			<powersi:datagrid-column display="菜单地址" name="win_name" minWidth="200" width="100%" />
			<powersi:datagrid-column display="菜单参数" name="menu_parm" width="100" />
			<powersi:datagrid-column display="菜单标识" name="menu_option" width="100" />
			<powersi:datagrid-column display="图片地址" name="pic_name" width="100" />
			<powersi:datagrid-column display="菜单类型" name="menu_type_name" width="100" code="menu_type" data="menu_type" />
			<powersi:datagrid-column display="菜单级别" name="grade_id_name" width="100" code="grade_id" data="grade_name" />
			<powersi:datagrid-column display="日志标志" name="log_flag_name" width="120" code="log_flag" data="log_flag"/>
			<powersi:datagrid-column display="权限标志" name="right_flag_name" width="100" code="right_flag" data="right_data"/>
			<powersi:datagrid-column display="有效标志" name="valid_flag_name" width="100" code="valid_flag" data="valid_flag" render="renderFlag"/>
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:script>
		<powersi:codetable id="ctMenutype" codeType="menu_type"></powersi:codetable>
		
		<powersi:combobox id="cbMenuType" onSelected="selectMenuType"
			valueField="id" textField="text" 
			data="ctMenutype">
		</powersi:combobox>
					
		<powersi:datagrid id="menuGrid" frozen="false" selectRowButtonOnly="false" rowAttrRender="gridRowRender"
			url="${rootPath }/manager/MenuManager!query.action" pageSize="10" showPageSize="false" showReload="false">
			<powersi:datagrid-column display="菜单编号" name="menu_id" width="100" />
			<powersi:datagrid-column display="菜单名称" name="menu_name" width="30%" minWidth="100" />
			<powersi:datagrid-column display="菜单地址"  name="win_name"  width="40%" minWidth="100" align="left"/>
			<powersi:datagrid-column display="上级菜单名称"  name="menu_up_name"  width="30%" minWidth="100" />
		</powersi:datagrid>
		
		<powersi:dataform id="menuSearch" prefixID="search_" inputWidth="90">
			<powersi:dataform-field name="menu_type" display="菜单类型" type="combobox" options="cbMenuType" />
			<powersi:dataform-field name="menu_id" display="菜单编号"/>
			<powersi:dataform-field name="menu_name" display="菜单名称"/>
			<powersi:dataform-field name="win_name" display="菜单地址" />
		</powersi:dataform>
		
		function gridRowRender(row, rowid) {
			if (row['valid_flag'] == '0') {
				return getColorStyle('default');
			} else if(row['menu_type'] == '2'){
				return getColorStyle('primary');
			} else if(row['menu_type'] == '1'){
				return getColorStyle('success');
			}
		}
		
		function selectMenuUp(newvalue, newtext){
			$('#detail_menu_up_id').val(newvalue);
		}
		
		function selectMenuType(newvalue){
			if(cbMenuUp && cbMenuUp.grid){
				cbMenuUp.grid.searchGrid();
			}
		}
	</powersi:script>
	<!-- 隐藏区 -->
	<div class="hidden">
		<div id="detailDlg">
			<form id="detailForm">
				<powersi:hidden id="detail_id" name="id" />
				<powersi:editorlayout cols="4">
					<powersi:editorlayout-row>
						<powersi:textfield id="detail_menu_id" name="menu_id"
							label="菜单编号" required="true" maxlength="12"></powersi:textfield>
						<powersi:textfield id="detail_menu_name" name="menu_name"
							label="菜单名称" required="true" maxlength="100"></powersi:textfield>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="detail_menu_up_id" name="menu_up_id"
							label="上级菜单编号" required="true" maxlength="12"></powersi:textfield>
						<powersi:combobox id="cbMenuUp" label="上级菜单名称" isMultiSelect="false"
							selectBoxWidth="765" selectBoxHeight="285" hideGridOnLoseFocus="false"
							valueField="menu_id" textField="menu_name" onSelected="selectMenuUp"
							grid="menuGrid" condition="menuSearch">
						</powersi:combobox>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="detail_menu_option" name="menu_option"
							label="菜单标识" required="false" maxlength="30"></powersi:textfield>
						<powersi:textfield id="detail_menu_parm" name="menu_parm"
							label="菜单参数" required="false" maxlength="60"></powersi:textfield>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="detail_menu_desc" name="menu_desc"
							label="菜单描述" colspan="3" required="alse" maxlength="150"></powersi:textfield>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="detail_win_name" name="win_name" label="菜单地址" colspan="3" maxlength="200"></powersi:textfield>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="detail_pic_name" name="pic_name" label="图片地址" colspan="3" maxlength="200"></powersi:textfield>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:radio id="detail_menu_type" name="menu_type" label="菜单类型" codeType="menu_type" required="true"></powersi:radio>
						<powersi:radio id="detail_grade_id" name="grade_id" label="菜单级别" codeType="grade_id" required="true"></powersi:radio>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:codeselect id="detail_menu_type" name="log_flag" label="日志标志" codeType="log_flag" required="true" cssClass="select2"></powersi:codeselect>
						<powersi:codeselect id="detail_grade_id" name="right_flag" label="权限标志" codeType="right_flag" required="true" cssClass="select2"></powersi:codeselect>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:spinner key="dis_order" id="detail_menu_order"
							name="menu_order" required="true" validate="integer,min[0],max[999999]" maxlength="6"
							min="0" max="999999" mouseWheel="true" />
						<powersi:radio id="detail_valid_flag" name="valid_flag" label="有效标志" codeType="valid_flag" required="true"></powersi:radio>
					</powersi:editorlayout-row>
				</powersi:editorlayout>
				<div id="menuIdTip" class="textLeft textError">
					菜单编号维护0，后台自动生成
				</div>
				<div class="divButton">
					<powersi:button id="btnDetailOk" key="button_save" onclick="save()" />
					<powersi:button id="btnDtailClose" key="button_close" onclick="closeDlg()" />
				</div>
			</form>
		</div>
		<div id="uploadDlg">
			<div class="textLeft"><span class="textInfo"><i class="icon-info-sign"></i> 请选择系统菜单文件（使用json格式)</span></div>
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
		<div id="downloadDlg">
			<div class="row">
				<div class="col-3 textRight">导出范围：</div>
				<div class="col-9">
					<input type="radio" checked="checked" name="down_export_range" id="down_export_range1" value="1" class="radio">
					<label for="down_export_range1" class="radioLabel">选择菜单</label>
					(<span id="spDownSelect" class="textError"></span> 项)
					<br/>
					<input type="radio" name="down_export_range" id="down_export_range2" value="2" class="radio">
					<label for="down_export_range2" class="radioLabel">全部菜单</label>
					(<span id="spDownAll" class="textError"></span> 项)
				</div>
			</div>
			<div class="space-y"></div>
			<div class="row">
				<div class="col-3 textRight">角色范围：</div>
				<div class="col-9">
					<input type="radio" checked="checked" name="down_export_role" id="down_export_role1" value="1" class="radio"><label for="down_export_role1" class="radioLabel">全部角色</label>    
					<br />
					<input type="radio" name="down_export_role" id="down_export_role2" value="2" class="radio"><label for="down_export_role2" class="radioLabel">系统管理员</label>
					<br />
					<input type="radio" name="down_export_role" id="down_export_role3" value="3" class="radio"><label for="down_export_role3" class="radioLabel">无</label>
				</div>
			</div>
			<div class="space-y"></div>
			<div class="row">
				<div class="col-3 textRight">导出类型：</div>
				<div class="col-9">
					<input type="radio" checked="checked" name="down_export_type" id="down_export_type1" value="1" class="radio"><label for="down_export_type1" class="radioLabel">JSON字符串</label>
					<br />    
					<input type="radio" name="down_export_type" id="down_export_type2" value="2" class="radio"><label for="down_export_type2" class="radioLabel">SQL语句（不支持界面导入）</label>
				</div>
			</div>
			
			<div class="space-y"></div>
			<div class="floatRight">
				<powersi:button id="btDownload" onclick="downloadMenu()" key="button_export" cssClass="btn btn-success" />
				<powersi:button id="btCloseDownload" onclick="closeDownloadDlg()" key="button_close" />
			</div>
		</div>
		<div id="selectDlg">
			<powersi:datagrid id="gridSelect" checkbox="true" height="300" pageSize="200" showReload="false">
				<powersi:datagrid-column display="菜单编号" name="menu_id" width="100" frozen="true"/>
				<powersi:datagrid-column display="菜单名称" name="menu_name" width="150" frozen="true"/>
				<powersi:datagrid-column display="菜单描述" name="menu_desc" width="150" />
				<powersi:datagrid-column display="上级菜单编号" name="menu_up_id" width="100" />
				<powersi:datagrid-column display="上级菜单名称" name="menu_up_name" width="150" />
				<powersi:datagrid-column display="菜单地址" name="win_name" minWidth="200" width="100%" />
			</powersi:datagrid>
			<div class="divButton">
				<powersi:button id="btnSelect1" key="button_clean" label="移除选择" onclick="removeSelectExport()" />
				<powersi:button id="btnSelect2" key="button_reset" label="移除全部" onclick="removeAllExport()" />
				<powersi:button id="btnSelectClose" key="button_close" onclick="closeSelectDlg()" />
			</div>
		</div>
	</div>

	<powersi:errors />
	<script type="text/javascript">
		$(function(){
			$(':checkbox[name="valid_flag"]').eq(0).prop('checked', true);
			grid.reload(true);
		});
		
		var sels = {};
		function renderOperate(row, index, value) {
			var a = [];
			var id = row['menu_id'] || '';
			var exist = powersi.isvalid(sels[id]);
			a.push('<input type="button" value="添加" class="linkButton" id="btn-put-' + id + '"');
			a.push(' onclick="putExport(');
			a.push(index);
			a.push(')"');
			
			if (exist) {
				a.push(' disabled="disabled"');
			}
			a.push(" />");
			
			a.push("&nbsp;&nbsp;");
			
			a.push('<input type="button" value="移除" class="linkButton" id="btn-remove-' + id + '"');
			a.push(' onclick="removeExport(');
			a.push(index);
			a.push(')"');
			if (!exist) {
				a.push(' disabled="disabled"');
			}
			a.push(" />");
			
			return a.join('');
		}
		
		function renderFlag(rowdata, index, value) {
			if (rowdata['valid_flag'] === '0') {
				return '<span title="无效"><i class="icon-remove textError"></i></span>';
			} else {
				return '<span title="有效"><i class="icon-ok textSuccess"></i></span>';
			}
		}
		
		function dblClickRow(data, rowid, rowdata){
			editRow(rowid);
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
			} else if(itemname == 'select'){
				selectExport();
			}
		}
		
		function save() {
			if (!checkForm('#detailForm')) {
				return;
			}

			var id = $('#detail_id').val();
			var name = $('#detail_menu_id').val();
			if(id != '' && id != name){
				popupConfirm('确认把菜单编号 <b class="textError">' + id + '</b> 修改成 <b class="textError">' + name + '</b> 吗？', '提示', function(yes){
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
			postAjax(rootPath + '/manager/MenuManager!save.action', 
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
		
		function deleteRow() {
			var row = grid.getSelectedRow();
			if (row) {
				popupConfirm('确认删除菜单' + row['menu_id'] + '[' + row['menu_name']
						+ ']吗？\n\n<b class="textError">删除后无法恢复，建议设置菜单的有效标志为无效。</b>', 
					"删除",
					function(yes){
					if(yes){
						postAjax(rootPath + '/manager/MenuManager!delete.action', grid
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
				popupAlert("请选择菜单");
			}
		}
		
		var dlg = null;
		function addRow() {
			var id = '';
			var name = '';
			var row = grid.getSelectedRow();
			if(row){
				id = row['menu_id'];
				name = row['menu_name'];
			}
			clearForm('#detailForm');
			json2form('#detailForm', {
				'id' : '',
				'menu_id' : '0',
				'menu_up_id': id,
				'menu_up_name': name,
				'menu_order': '1000',
				'grade_id': '1',
				'menu_type': '1',
				'right_flag': '1',
				'log_flag': '0',
				'valid_flag' : '1'
			});
			
			cbMenuUp.setText(name);
			$('#menuIdTip').removeClass('hidden');
			
			if (dlg) {
				dlg.show();
			} else {
				dlg = popupDiv("#detailDlg", '', 800);
			}
			dlg.set('title', '新增菜单');
		}

		function closeDlg() {
			if (dlg) {
				dlg.hide();
			}
		}

		function editRow(rowid) {
			var row = null;
			if(rowid){
				row = grid.getRow(rowid);
			} else {
				row = grid.getSelectedRow();
			}
			
			if (row) {
				clearForm('#detailForm');
				json2form('#detailForm', row);
				$('#detail_id').val(row['menu_id']);
				
				cbMenuUp.setText(row['menu_up_name']);
				$('#menuIdTip').addClass('hidden');
				
				if (dlg) {
					dlg.show();
				} else {
					dlg = popupDiv("#detailDlg", '', 800);
				}
				dlg.set('title', '编辑菜单');
			} else {
				popupAlert("请选择菜单");
			}
		}
		
		function copyRow() {
			var row = grid.getSelectedRow();
			if (row) {
				clearForm('#detailForm');
				json2form('#detailForm', row);
				$('#detail_id').val('');
				$('#detail_menu_id').val('0');
				$('#detail_menu_order').val(parseInt(row['menu_order']) + 1);
				
				cbMenuUp.setText(row['menu_up_name']);
				$('#menuIdTip').removeClass('hidden');
				
				if (dlg) {
					dlg.show();
				} else {
					dlg = popupDiv("#detailDlg", '', 800);
				}
				dlg.set('title', '复制菜单');
			} else {
				popupAlert("请选择菜单");
			}
		}

		var dlgDownload = null;
		function closeDownloadDlg(){
	    	if(dlgDownload){
	    		dlgDownload.hide();
	    	}
	    }
		
		function download(){
			$('#spDownAll').text(grid.getTotal());
			$('#spDownSelect').text(powersi.length(sels));
			
			if(dlgDownload){
				dlgDownload.show();
			} else{
				dlgDownload = popupDiv("#downloadDlg", '导出文件', 350);
			}
		}
		
		function downloadMenu(){
			var exportRange = $(':radio[name="down_export_range"]:checked').val();
			var roleRange = $(':radio[name="down_export_role"]:checked').val();
			var exportType = $(':radio[name="down_export_type"]:checked').val();
			
			var len = 0;
			if(exportRange == "1"){
				len = powersi.length(sels);
			} else{
				len = parseInt(grid.getTotal());
			}
			if(len < 1){
				popupAlert("请选择需要导出的菜单");
				return;
			}
			
			popupConfirm('确认导出 ' + len + ' 项菜单吗？', '导出', function(yes){
				if(yes){
					var data = {};
					if(exportRange == "1"){
						var ids = [];
						$.each(sels, function(id, row){
							ids.push(id);
						});
						data['menus'] = ids.join(',');
						data['range'] = 'select';
					} else {
						data = form2json('#queryForm');
						data['range'] = 'all';
					}
					
					if("1" == roleRange){
						data['roles'] = 'all';
					} else if("2" == roleRange){
						data['roles'] = 'admin';
					} else {
						data['roles'] = 'none';
					}
					
					if("2" == exportType){
						data['type'] = 'sql';
					} else{
						data['type'] = 'json';
					}
					
					postDownload(rootPath + '/manager/MenuManager!download.action', data);
					
					closeDownloadDlg();
				}
			});
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
			        url: rootPath + '/manager/MenuManager!upload.action',
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
		
		//export
		function putExport(index){
			var row = grid.getRow(index);
			if(row){
				var id = row['menu_id'];
				sels[id] = grid.formatRow(row);
				
				$('#btn-put-' + id).prop('disabled', true);
				$('#btn-remove-' + id).removeProp('disabled');
			}
		}
		
		function removeExport(index){
			var row  = null;
			if($.isPlainObject(index)){
				row = index;
			} else {
				row = grid.getRow(index);
			}
			if(row){
				var id = row['menu_id'];
				delete sels[id];
				
				$('#btn-put-' + id).removeProp('disabled');
				$('#btn-remove-' + id).prop('disabled', true);
			}
		}
		
		var dlgSelect = null;
		function closeSelectDlg(){
			if(dlgSelect){
				dlgSelect.hide();
			}
		}
		
		function selectExport() {
			var data = [];
			$.each(sels, function(id, row){
				data.push(row);
			});
			
			gridSelect.setData(data);
			gridSelect.reload();
			
			if(dlgSelect){
				dlgSelect.show();
			} else{
				dlgSelect = popupDiv('#selectDlg', '查看导出选择', 800);
			}
		}
		
		function removeSelectExport() {
			var rows = gridSelect.getSelectedRows();
			var len = powersi.length(rows);
			if(len > 0){
				popupConfirm("确认移除已选择 " + len + " 项导出菜单吗？", function(yes){
					if(yes){
						$.each(rows, function(n, row){
							removeExport(row);
						});
						
						gridSelect.deleteRange(rows);
					}
				});
			}
		}
		
		function removeAllExport() {
			var len = powersi.length(sels);
			if(len > 0){
				popupConfirm("确认移除已选择 " + len + " 项导出菜单吗？", function(yes){
					if(yes){
						$.each(sels, function(id, row){
							removeExport(row);
						});
						
						gridSelect.reset();
					}
				});
			}
		}
	</script>
</body>
</powersi:html>