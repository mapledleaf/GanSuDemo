<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="datagrid表单"/>
</head>
<body>
	<powersi:panelbox key="panel_query">
		<powersi:form id="queryForm" namespace="/sample" action="Sample!queryCodetable">
			<powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit" key="button_query" />
				<powersi:reset id="btReset" key="button_reset" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="6">
				<powersi:editorlayout-row>
					<powersi:textfield id="codeType" name="code_type"  label="代码类型" />
					<powersi:textfield id="codeName" name="code_name"  label="代码名称" />
					<powersi:textfield id="codeSql" name="code_sql"  label="代码SQL" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:form>
	</powersi:panelbox>
	<powersi:panelbox key="panel_result" title="代码列表(双击显示隐藏明细)">
		<!-- 明细显示不能使用冻结列，否则布局乱套 -->
		<powersi:datagrid id="gridList" formId="queryForm" frozen="false"
			showExportExcel="true" exportFileName="'业务代码'" onDblClickRow="dblClickRow"
			detail="{onShowDetail: showDetail, height:'auto'}">
			<powersi:datagrid-column display="代码类型" name="code_type" width="30%" minWidth="100" />
			<powersi:datagrid-column display="代码名称" name="code_name" width="30%" minWidth="100" />
			<powersi:datagrid-column display="代码SQL" name="code_sql" width="40%" minWidth="120" align="left"/>
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
	<!-- 隐藏区 -->
	<div class="hidden">
		<powersi:form id="detailForm" disabled="true">
			<powersi:editorlayout cols="4">
				<powersi:editorlayout-row>
					<powersi:textfield id="detail_code_type" name="code_type" label="代码类型" required="true"></powersi:textfield>
					<powersi:textfield id="detail_code_name" name="code_name" label="代码名称" required="true"></powersi:textfield>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea name="code_sql" label="代码名称" rows="5" colspan="3"></powersi:textarea>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
			<div class="divButton">
				<powersi:button id="detail_btn_save" key="button_save" name="detail_btn_save" cssClass="btn btn-info"></powersi:button>
				<powersi:button id="detail_btn_cancle" key="button_cancel" name="detail_btn_cancel" cssClass="btn btn-info"></powersi:button>
			</div>
		</powersi:form>
	</div>
	<powersi:script>
		function showDetail(row, detailPanel,callback) {
			try{
				var suffix = '_' + (row['code_type'] || '_new_');
				var rowIndex = gridList.getRowIndex(row);
				
				//生成form
				var form = $('#detailForm').clone(true).attr('id', 'detail_form' + suffix);
				
				//复制data
				json2form(form, row);
				
				//复制id
				form.find("[id]").each(function(){
					var id = $(this).attr('id');
					var nid = $(this).attr('name') || id + suffix;
					form.find('label[for="' + id + '"]').attr('for', nid);
					$(this).attr("id", nid);
				});
				
				//处理css和click
				var $panel = $(detailPanel);
				form.find('table').css({width: $panel.width() - 10, margin: '10px 0 10px 10px'});
				form.find('[name="detail_btn_save"]').click(function(){
					saveDetailForm(form, rowIndex);
				});
				form.find('[name="detail_btn_cancel"]').click(function(){
					cancelDetailForm(form, rowIndex);
				});
				
				//初始化表单验证(需要指定grid容器)
				initFormValidtion(form, {
					container: gridList.gridbody
				});
				
				//显示form
				$panel.append(form);
			}catch(ex){
				alert(ex.message);
			}
		}
		
		function saveDetailForm(form, idx) {
			try{
				//校验表单
				if(!checkForm(form)){
					return;
				}
			
				//获取data
				var data = form.serialize();
				alert(data);
				
				//demo程序不能随意修改数据，所以省略ajax后台保存调用
				/*postAjax(url, data, function(json){
					if(!checkResult(json)){
						return;
					}
					
					var row = form2json(form);
					gridList.updateRow(idx, row);
				});*/
				
				//更新datagrid显示
				var row = form2json(form);
				gridList.updateRow(idx, row);
			} catch(ex){
				alert(ex.message);
			}
		}
		
		function cancelDetailForm(form, idx) {
			try{
				//清除表单验证
				clearFormValidtion(form);
				
				//获取原始数据
				var row = gridList.getRow(idx);
				
				//复制data
				json2form(form, row);
			} catch(ex){
				alert(ex.message);
			}
		}
		
		function dblClickRow(data, rowid, rowdata){
			gridList.toggleDetail(rowdata);
		}
	</powersi:script>
</body>
</powersi:html>