<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="datagrid明细"/>
</head>
<body>
	<powersi:panelbox key="panel_result" title="代码列表(主表双击显示sql 单元格自动换行 明细双击显示行数据)">
		<!-- 明细显示不能使用冻结列，否则布局乱套 -->
		<powersi:datagrid id="gridList" frozen="false" fixedCellHeight="false"
			showExportExcel="true" exportFileName="'业务代码'" onDblClickRow="showSql" url="${rootPath }/sample/Sample!queryCodetable.action"
			detail="{onShowDetail: showDetail, height:'auto'}">
			<powersi:toolbar>
				<powersi:toolbar-item id="title" icon="info" text="查询条件面板"></powersi:toolbar-item>
				<powersi:toolbar-item id="search" position="right"></powersi:toolbar-item>
			</powersi:toolbar>
			<powersi:datagrid-column key="code_type" width="30%" minWidth="100" />
			<powersi:datagrid-column key="code_name" width="30%" minWidth="100" />
			<powersi:datagrid-column key="code_sql" display="代码SQL" width="40%" minWidth="120" align="left"/>
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
	<!-- 隐藏区 -->
	<div class="hidden">
		<div id="sqlDlg">
			<div style="margin: 5px 0 10px 0;">
				<powersi:textarea id="sqlArea" readonly="true" rows="10" cols="10" cssClass="bordered"></powersi:textarea>
			</div>
		</div>
	</div>
	<powersi:url id="detailUrl" namespace="/sample" action="Sample" method="queryCodetableDetail" includeParams="all" escapeAmp="false" />
	<powersi:script>
		function showDetail(row, detailPanel,callback) {
		    var div = document.createElement('div');
		    var width = $(detailPanel).append(div).width() - 15;

			//创建datagrid		   
		   	//frozen=false		不能冻结，显示可能异常
			//isScroll=false	不需要纵向滚动条，否则与页面滚动条重复，尽量只保留一个滚动条
		    <powersi:datagrid id="gridOption" frozen="false"
		    	url="${detailUrl}" isScroll="false" showExportExcel="true" pageSize="10" showReload="false" showPageSize="false" onDblClickRow="dblDetail">
				<powersi:datagrid-column display="数据值" name="data_value" width="40%" minWidth="100" />
				<powersi:datagrid-column display="显示值" name="display_value" width="40%" minWidth="100" />
				<powersi:datagrid-column display="显示序号" name="dis_order" width="20%" minWidth="50" />
			</powersi:datagrid>
			
		    gridOption['width'] = width;
		    gridOption['parms'] = {'type': row["code_type"]};
		    gridOption['exportFileName'] = '业务代码明细_' + row['code_type'];
		    
		    var grid = $(div).ligerGrid(gridOption);
		    $(div).wrap('<div style="margin:10px"></div>');
		    grid.bindCondition(getCondition(grid, row["code_type"]));
		    grid.refreshSize();
		}
		
		var dlg;
		function showSql(data,rowid,rowdata) {
			try{
				$('#sqlArea').text(htmlEncode(data['code_sql']));
				if(dlg){
					dlg.show();
				} else {
				    dlg = popupDiv('#sqlDlg', '代码SQL', 400, {showMax: true});
				}
			} catch(ex){
				alert(ex.message);
			}
		}
		
		function dblDetail(data, rowid, rowdata){
			//获取grid的动态id，通过$.ligerui.get(gridId)可以获取grid实例
			var gridId = this.id;
			popupSuccess(powersi.tostring(data), gridId, 5000);
		}
		
		//明细查询面板
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
		
		//主面板查询
		function getListCond(){
			<powersi:dataform id="cond" prefixID="search_toolbar_" inputWidth="100" labelWidth="80">
				<powersi:dataform-field key="code_type" />
				<powersi:dataform-field key="code_name" />
				<powersi:dataform-field key="code_sql" display="代码SQL" />
			</powersi:dataform>
			
			return cond;
		}
	</powersi:script>
<script type="text/javascript">
$(function(){
	//绑定搜索条件到工具栏
	gridList.bindCondition(getListCond(),  'search');
});
</script>
</body>
</powersi:html>