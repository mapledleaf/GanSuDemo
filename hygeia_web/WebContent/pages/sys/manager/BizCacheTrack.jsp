<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="业务缓存跟踪管理" />
</head>
<body>
	<powersi:form id="queryForm" namespace="/manager"
		action="CacheTrackManager!queryMainTrackInfo.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="query" label="查询" onclick="queryMainTrackInfo()"
					onchange="" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout>
					<powersi:codeselect id="aaa027" name="aaa027" key="aaa027"
						codeType="gds_policy_tcq" label="统筹区" headerKey="441800" headerValue="清远市"/>
					<powersi:codeselect id="bizFlag" label="缓存对象模块"
						name="bizFlag"
						list="#{'POLICY':'政策信息','JZML':'基准目录','EJML':'二级目录','KAE8':'匹配信息','KA06':'疾病目录'}" />	
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	
	<powersi:panelbox key="panel_result" title="缓存列表">
	<a>双击红色标记行查看中心下发失败或未刷新缓存的数据详情</a>
		<powersi:datagrid id="grid" formId="queryForm" delayLoad="true" rowAttrRender="gridRowRender"  onDblClickRow="refresh"
			usePager="false" enabledEdit="true" clickToEdit="false" >
			<%-- <powersi:datagrid-column key="operate" width="80" frozen="true" isSort="false" isExport="false" render="renderOperate" /> --%>
			<powersi:datagrid-column display="缓存代码" name="key_code" data="key_code" width="25%" minWidth="100" editor="{type: 'string'}" />
			<powersi:datagrid-column display="中心最新修改时间" name="zxTime" data="zxTime" width="25%" minWidth="100" editor="{type: 'string'}" />
			<powersi:datagrid-column display="结算云最新修改时间" name="jsyTime" data="jsyTime" width="25%" minWidth="100" editor="{type: 'string'}" />
			<powersi:datagrid-column display="缓存最新修改时间" name="redisTime" data="redisTime" width="25%" minWidth="100" editor="{type: 'string'}" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
<script type="text/javascript">
	function queryMainTrackInfo(){
		 $("#queryForm").submit();
	}
	
	/*操作*/
	function renderOperate(row, index, value) {
		var a = [];
		a.push('<input type="button" value="异常数据" class="linkButton"');
		a.push(' onclick="refresh(');
		a.push(index);
		a.push(')"');
		a.push(" />");
		return a.join('');
	}
	
	function refresh(i){
		var row = grid.getRow(i);
		
		var bizFlag = $("#bizFlag").val();
  		var key_code = row['key_code'];
  		var policyType = key_code.substring(0,key_code.length-7);
  		var zxTime = row['zxTime'];
  		var jsyTime = row['jsyTime'];
  		var redisTime = row['redisTime'];
  		var aaa027 = key_code.substr(key_code.length-6);
  		var url;
  		if(zxTime == jsyTime && zxTime == redisTime){
  			alert("没有下发失败或未刷新缓存的数据");
  		}else{
  			if( bizFlag == "POLICY"){
  			//url = rootPath +"/manager/CacheTrackManager!queryMissInfoDetail.action?bizFlag="+bizFlag
  				var jspPath = "";
  				if(policyType=="MAP_SILL"){
  					jspPath = rootPath +"/pages/sys/manager/BizCacheTrackDetail_sill.jsp";
  				}else if(policyType=="MAP_SEGMENT"){
  					jspPath = rootPath +"/pages/sys/manager/BizCacheTrackDetail_seg.jsp";
  				}else if(policyType=="MAP_SEGMENT_PAY"){
  					jspPath = rootPath +"/pages/sys/manager/BizCacheTrackDetail_pay.jsp";
  				}else if(policyType=="MAP_SELF_SCALE"){
  					jspPath = rootPath +"/pages/sys/manager/BizCacheTrackDetail_self.jsp";
  				}else if(policyType=="MAP_SPECIAL_TREAT_LIMIT"){
  					jspPath = rootPath +"/pages/sys/manager/BizCacheTrackDetail_spec.jsp";
  				}else if(policyType=="MAP_BIZ_LIMIT_LINE"){
  					jspPath = rootPath +"/pages/sys/manager/BizCacheTrackDetail_limit.jsp";
  				}
  				
  				url = jspPath+"?bizFlag="+bizFlag
	  				+"&policyType="+policyType
	  				+"&zxTime="+zxTime
	  				+"&jsyTime="+jsyTime
	  				+"&redisTime="+redisTime
	  				+"&aaa027="+aaa027;
  			}
	  		popupDialog(url,500,1000);
  		}
  		
	}
	
	function gridRowRender(rowdata, rowid){
		
		var zxTime = rowdata['zxTime'];
  		var jsyTime = rowdata['jsyTime'];
  		var redisTime = rowdata['redisTime'];
		if (!(zxTime == jsyTime && zxTime == redisTime)) {
			return getColorStyle('error');
		}
	}
</script>
</body>
</powersi:html>