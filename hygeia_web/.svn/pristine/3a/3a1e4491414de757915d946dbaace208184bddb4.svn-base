<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<powersi:html>
<head>
<powersi:head title="实施计划申请" />
</head>
<body>
	<powersi:form id="queryForm" namespace="/actualize"
		action="ActualizeManageAction!queryActualizePlan.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit" label="查 询" />
				<powersi:button id="btAdd" label="新增计划" onclick="doAdd()" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bkf318" name="kfd6Dto.bkf318"
						key="bkf318" label="计划名称" />
					<powersi:textfield id="bae101" name="kfd6Dto.bae101"
						key="bae101" label="计划建立人" />
					<powersi:codeselect id="aae100" name="kfd6Dto.aae100"
						key="aae100" list="#{'':'请选择...','1':'激活','0':'关闭' }" label="计划总体状态" />
					<powersi:textfield id="bkf320" label="计划开始时间"
						name="kfd6Dto.bkf320" mask="date"
						format="dateFmt:'yyyy-MM-dd'" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<div class="row">
		<div class="col-9">
			<powersi:panelbox key="panel_result" title="实施计划信息"
				allowCollapse="false">
				<a>双击单条查询参与该计划的医院。</a>
					<powersi:datagrid id="planid" formId="queryForm"  enabledSort="false"
						onDblClickRow="queryHosp" showReload="false"
						detail="{onShowDetail: showDetail, height:'auto'}">
						<powersi:datagrid-column name="bkf306" label="实施计划id " hide="true" />
						<powersi:datagrid-column name="bkf318"  label="实施计划名称" width="25%"/>
						<powersi:datagrid-column name="aae014"  label="创建人人工号 " hide="true" />
						<powersi:datagrid-column name="bae101"  label="创建人" width="15%"/>
						<powersi:datagrid-column name="aae100" label="计划总体状态" width="15%" render="renderAae100"/>
						<powersi:datagrid-column name="bkf319" label="计划创建时间" width="15%" format="{0,date,yyyy-MM-dd}"/>
						<powersi:datagrid-column name="bkf320" label="计划开始时间" width="15%" format="{0,date,yyyy-MM-dd}"/>
						<powersi:datagrid-column name="bkf321" label="计划预计结束时间" width="15%" format="{0,date,yyyy-MM-dd}"/>
					</powersi:datagrid>
			</powersi:panelbox>
		</div>
		<div class="col-3">
			<powersi:panelbox iconClass="icon-cog" title="参与的医院"
				allowCollapse="false">
				<br/>
				<powersi:datagrid id="hisFrom" enabledSort="false"
					showReload="false">
					<powersi:datagrid-column name="akb020" key="akb020" label="医院编码" hide="true"/>
					<powersi:datagrid-column name="akb021" key="akb021" label="医院名称" width="50%"/>
					<powersi:datagrid-column name="bkf305" key="bkf305" label="his开发商" width="50%"/>
				</powersi:datagrid>
			</powersi:panelbox>
		</div>
	</div>
	<powersi:errors />
</body>
</powersi:html>
<powersi:script>
		function showDetail(row, detailPanel,callback) {
		    var div = document.createElement('div');
		    var width = $(detailPanel).append(div).width() - 15;
			//创建datagrid		   
		   	//frozen=false		不能冻结，显示可能异常
			//isScroll=false	不需要纵向滚动条，否则与页面滚动条重复，尽量只保留一个滚动条
			var rows = planid.getRow(row);
  			var bkf306 = row['bkf306'];
		    <powersi:datagrid id="gridOption" frozen="false"
		    	 isScroll="false" showPageSize="false" usePager="false">
				<powersi:datagrid-column display="业务子类编码" name="bkf303" width="40%" />
				<powersi:datagrid-column display="业务子类描述" name="bkf304" width="40%"/>
				<powersi:datagrid-column display="是否需要测试" name="bkf310" width="20%" code="yes_no" />
			</powersi:datagrid>
		    gridOption['width'] = width;
		    gridOption['url'] = "${rootPath}/actualize/ActualizeManageAction!queryPlanModel.action?bkf306="+bkf306;
		    var grid = $(div).ligerGrid(gridOption);
		    $(div).wrap('<div style="margin:10px"></div>');
		    grid.refreshSize();
		}
		
</powersi:script>
<script type="text/javascript">
	function  doAdd() {
		popupDialog({
			url: "${rootPath}/pages/biz/medicare/actualize/NewPlan.jsp",
			onClosed: function() { 
				planid.reload(true);
			}
		}, screen.height, screen.width);
	}
	function queryHosp(rowdata, rowid, rowobj){
		var row = planid.getRow(rowid);
  		var bkf306 = row['bkf306'];
        postJSON("${rootPath}/actualize/ActualizeManageAction!queryHospBybkf306.action",{
    	 	"bkf306":bkf306
		},function(json) {
			 if(!checkJSONResult(json)){
		          return;
		     }
			 hisFrom.loadData(json.data);
		});
	}
	function query() {
		hisFrom.reset();
	}
	
	function renderDate(rowdata,index,value) {
        if(value!=null&&value !=""){
        	 var date = new Date(value);
             return date.getFullYear() + '-'+ (date.getMonth() + 1) + '-' + date.getDate();
        }else{
             return " ";
        }
    }
	
	function renderAae100(rowdata,index,value){
		if(value=="1"){
			 return "激活";
		}else if(value=="0"){
			return "关闭";
		}else{
			return " ";
		}
	}
</script>