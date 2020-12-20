<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="业务缓存管理" />
</head>
<body>
	<powersi:panelbox key="panel_result" title="缓存列表">
		<powersi:datagrid id="grid"
			enabledEdit="true" clickToEdit="false" >
			<%-- <powersi:toolbar>
				<powersi:toolbar-item id="save" text="全部刷新" icon="tick2" click="refresh" title="" position="right" />
			</powersi:toolbar> --%>
			<powersi:datagrid-column key="operate" width="80" frozen="true" isSort="false" isExport="false" render="renderOperate" />
			<powersi:datagrid-column display="缓存代码" name="code" data="code" width="50%" minWidth="100" editor="{type: 'string'}" />
			<powersi:datagrid-column display="对应缓存名称" name="value" data="value" width="50%" minWidth="100" editor="{type: 'string'}" />
		</powersi:datagrid>
	</powersi:panelbox>
	
	<powersi:errors />
<script type="text/javascript">
	$(function() {
		$(document).ready(function() {
			 postJSON(rootPath + "/manager/BizCacheManager!queryRefreshList.action",null,showBizCache);
		});
	});
	
	function showBizCache(json) {
        if (!checkJSONResult(json)) {
            return;
        }
       var retdata = powersi.tostring(json);
       var trHtml="";
       var data = json.data;
       grid.reset();
	     if(data==""){
	    	 alert("没有符合条件的信息");
	     }else{
	    	 grid.loadData(json.data);
	     }
    }
	
	/*操作*/
	function renderOperate(row, index, value) {
		var a = [];
		a.push('<input type="button" value="刷新" class="linkButton"');
		a.push(' onclick="refresh(');
		a.push(index);
		a.push(')"');
		a.push(" />");
		return a.join('');
	}
	
	function refresh(i){
		var row = grid.getRow(i);
  		var bizFlag = row['code'];
  		//如果是YYML需要弹出对话框选择医院
  		if(bizFlag == "YYML") {
  			popupDialog(rootPath + "/pages/sys/manager/ShowBizYyInfo.jsp", 500, 700);		
  		}else if(bizFlag == "POLICY"){
  			popupDialog(rootPath + "/pages/sys/manager/ShowPolicyTcqInfo.jsp", 500, 700);	
  		}else{
	  		//postJSON(rootPath + "/manager/BizCacheManager!refreshCache.action", {"bizFlag": bizFlag}, showMsg);
	  		popupDialog(rootPath +"/pages/sys/manager/ShowJzorEjInfo.jsp?bizFlag="+bizFlag,500,700);
  		}
		
	}
	
	function showMsg(json) {
         if (!checkJSONResult(json)) {
            return;
        }
        alert(json.message);
	}
	
</script>
</body>
</powersi:html>