<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="datagrid选项卡"/>
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
		<div id="tabbable" class="tabbable">
			<ul class="nav nav-tabs" id="tabs" name="navtabs">
				<li class="active">
					<a data-toggle="tab" href="#tab1-pane" id="tab1" name="tab1">
						<i class="icon-th green"></i>
						代码详情
					</a>
				</li>

				<li>
					<a data-toggle="tab" href="#tab2-pane" id="tab2" name="tab2">
						<i class="icon-list red"></i>
						代码明细
					</a>
				</li>
			</ul>
			<div class="tab-content">
				<div id="tab1-pane" class="tab-pane active" name="tab1-pane">
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

				<div id="tab2-pane" class="tab-pane" name="tab2-pane">
					<div style="margin:10px">
						<div id="gridDetail" name="grid"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<powersi:script>
		function showDetail(row, detailPanel,callback) {
			try{
				var codeType = row['code_type'] || '_new_';
				var suffix = '_' + codeType;
				var rowIndex = gridList.getRowIndex(row);
				
				var tabs = $('#tabbable').clone(true).attr('id', 'tabbable' + suffix);
				
				//生成form
				var form = tabs.find('form');
				
				//复制data
				json2form(form, row);
				
				//复制id
				tabs.find("[id]").each(function(){
					var id = $(this).attr('id');
					var nid = $(this).attr('name') + suffix;
					form.find('label[for="' + id + '"]').attr('for', nid);
					$(this).attr("id", nid);
					
					if($(this).attr('href')){
						$(this).attr('href', '#' + $(this).attr('name') + "-pane" + suffix);
					}
				});
				
				//处理css和click
				var $panel = $(detailPanel);
				tabs.css({width: $panel.width() - 20, margin: '10px 0 10px 10px'});
				
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
				
				//显示选项卡
				$panel.append(tabs);
				
				//创建datagrid
				//frozen=false		不能冻结，显示可能异常
				//isScroll=false	不需要纵向滚动条，否则与页面滚动条重复，尽量只保留一个滚动条
			    <powersi:datagrid id="gridOption" frozen="false" isScroll="false"
			    	showExportExcel="true" pageSize="10" showReload="false" showPageSize="false">
					<powersi:datagrid-column display="数据值" name="data_value" width="40%" minWidth="100" />
					<powersi:datagrid-column display="显示值" name="display_value" width="40%" minWidth="100" />
					<powersi:datagrid-column display="显示序号" name="dis_order" width="20%" minWidth="50" />
				</powersi:datagrid>
			    
			    gridOption['url'] = rootPath + "/sample/Sample!queryCodetableDetail.action";
			    gridOption['width'] = tabs.width() - 20;
			    gridOption['parms'] = {'type': codeType};
			    gridOption['exportFileName'] = '业务代码明细_' + codeType;
			    
			    var grid = tabs.find('[name="grid"]').ligerGrid(gridOption);
			    grid.bindCondition(getCondition(grid, codeType))
			   	grid.refreshSize();
			}catch(ex){
				alert(ex.message);
			}
		}
		
		function getCondition(grid, codeType){
			<powersi:combobox id="cb" valueFieldID="val" selectBoxHeight="200" selectBoxWidth="148" codeType="aaa027_list">
			</powersi:combobox>
			
			//根据代码类型设置前缀，保证id不冲突
			cb["valueFieldID"] = codeType + '_center_id';
			
			<powersi:dataform id="cond" inputWidth="120" showClose="true" closeText="清除">
				<powersi:dataform-field name="center_id" display="选择中心" type="combobox" options="cb"></powersi:dataform-field>
				<powersi:dataform-field name="data_value" display="数据值"></powersi:dataform-field>
				<powersi:dataform-field name="display_value" display="显示值"></powersi:dataform-field>
			</powersi:dataform>
			
			//根据代码类型设置前缀，保证id不冲突
			cond['prefixID'] = 'search_' + codeType + '_';
			
			return cond;
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