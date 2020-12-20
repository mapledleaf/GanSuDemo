<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="jqwgrid示例" />
<link type="text/css" rel="Stylesheet" href="${strutsPath}/js/plugins/jqwidgets/styles/jqx.base.css" />
<link type="text/css" rel="Stylesheet" href="${strutsPath}/js/plugins/jqwidgets/styles/jqx.light.css" />
<link type="text/css" rel="Stylesheet" href="${strutsPath}/css/jqwidgets.css" />
</head>
<body class="page">
	<powersi:form id="queryForm" disabled="true" validate="false">
		<powersi:panelbox key="panel_query">
				<powersi:panelbox-toolbar>
					<powersi:submit id="btSubmit" key="button_query" onclick="queryData()" />
					<powersi:reset id="btReset" key="button_reset" />
					<powersi:button id="btSelected" label="查看已选择" onclick="showSelectedRows()" cssClass="btn btn-success" />
				</powersi:panelbox-toolbar>
				<powersi:editorlayout cols="6">
					<powersi:editorlayout-row>
						<powersi:textfield id="loadCount" name="load_count"  label="加载数据量" 
							required="true" value="10000" validate="integer,min[0],max[150000]"/>
						<powersi:textfield id="codeType" name="code_type"  label="码表类型" />
						<powersi:textfield id="codeName" name="code_name"  label="码表名称" />
					</powersi:editorlayout-row>
				</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox key="panel_result" title="数据列表">
		<div id="grid"></div>
	</powersi:panelbox>
	<powersi:errors />
<script type="text/javascript" src="${strutsPath}/js/plugins/jqwidgets/jqx-all.js"></script>
<script type="text/javascript" src="${strutsPath}/js/jqwidgets.js"></script>
<script type="text/javascript">
	/*grid定义（就是jquery对象，不是jqxGrid对象需要注意）*/
	var grid = null;
	/*数据源定义*/
	var source = {
     	datatype: 'json',
         datafields: [{
             name: 'page_rowno',
             type: 'number'
         }, {
             name: 'code_type',
             type: 'string'
         },  {
             name: 'code_name',
             type: 'string'
         }, {
             name: 'code_sql',
             type: 'string'
         }, {
             name: 'valid_flag',
             type: 'string'
         }],
         url: rootPath + '/sample/Sample!queryCodetable.action',
         filter: function() {
        	 if(grid){
        		 grid.jqxGrid('updatebounddata', 'filter');
        	 }
         },
         sort: function() {
        	 if(grid) {
        		 grid.jqxGrid('updatebounddata', 'sort');
        	 }
         },
         /*延迟加载数据，先使用本地数据，查询后转远程数据*/
         localdata: []
    };
	
	function queryData() {
		//判断参数是否有效
		if(!checkForm("#queryForm")){
			return;
		}
		
		if(source.localdata){
			//重新设置数据源(把本地数据替换成Ajax数据源)
			delete source["localdata"];
			grid.jqxGrid({
				source: jqwGridAjaxAdapter(source, {
					//绑定参数
	            	formatData: function(data) {
	            		var json = form2json('#queryForm');
	            		return $.extend(data, json);
	            	}
            	})
           });
		} else {
			//刷新数据
			grid.jqxGrid('updatebounddata');
		}
	}
	
	function showSelectedRows() {
		var rows = [];
		var cbxall = grid.jqxGrid('isallselected');
		if(cbxall){
			rows = grid.jqxGrid('getrows');
			popupInfo("全部选择", "共选择 " + rows.length + " 行");
		} else{
			var sels = grid.jqxGrid('getselectedrowindexes');
			$.each(sels, function(i, value){
				//只显示前10行数据
				if(i >= 10){
					return false;
				}
				
				rows.push(grid.jqxGrid('getrowdata', value));
			});
			popupInfo(powersi.tostring(rows), "共选择 " + sels.length + " 行");
		}
	}
	
	$(document).ready(function () {
		//初始化高度自适用
		var height = jqwGridHighlyAdapter($('#grid'));
        
        grid = $("#grid").jqxGrid({
        	/*因为需要延迟加载，所以默认使用本地数据源，如果不需要延迟加载请直接使用ajax数据源*/
            source: source,
            localization: jqwLocalization('zh'),
            selectionmode: 'checkbox',
            enablebrowserselection: true,/*是否允许浏览器选择文字*/
            enabletooltips: true,/*是否显示工具条提示*/
            sortable: true,
            altrows: true,
            enablehover: true,
            adaptive: false,
            width: '100%',
            height: height,
            /*
            pageable: true,
            pagesize: 100,
            pagesizeoptions: ['100', '500', '1000', '5000', '10000'],
            virtualmode: true,
            rendergridrows: function (obj) {
                return obj.data;
            },
            */
            pageable: false,
            pagesize: 150000,
            scrollmode: 'deferred',
            deferreddatafields: ['code_type', 'code_name'],/*滚动条显示字段列表*/
            
            columns: [{
                text: '序号',
                sortable: false,
                exportable: false,
                datafield: 'page_rowno',
                width: 80,
                align: 'center',
                cellsalign: 'center'
            },{
                text: '代码类型',
                datafield: 'code_type',
                width: 150,
                align: 'center',
                cellsalign: 'center'
            }, {
                text: '代码名称',
                datafield: 'code_name',
                width: 150,
                align: 'center',
                cellsalign: 'center'
            }, {
                text: '代码SQL',
                datafield: 'code_sql',
                align: 'center',
                cellsalign: 'center'
            }]
        });
    });
</script>
</body>
</powersi:html>