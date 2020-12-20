<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>


<powersi:html>
<head>
<powersi:head title="卫生站就诊信息批量导入（重校验）" />
</head>
<body>
	<powersi:form id="batchSeacherForm">
		<powersi:panelbox key="条件设置">
			<powersi:panelbox-toolbar>
			    <powersi:button id="btQuery" label="查询"  onclick="queryData(0)"/>
				<%-- <powersi:button id="btDetail" label="批量导入"  onclick="batchLead()"/>
				<powersi:button value="模版下载" onclick="downloadFile()" /> --%>
				<powersi:button value="重校验" onclick="velidDate()" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="10">
				<tr>
				  <powersi:textfield id="bkc032" required="true"  name="kf01DTO.bkc032" label="导入年月" validate = "integer,max[209912],min[190001],maxSize[6],minSize[6]"
				 onclick="WdatePicker({dateFmt:'yyyyMM'})" />
				 <powersi:hidden id="kf01_id" name="kf02DTO.kf01id" label="批次号" />
				 <powersi:hidden id="akb020_id" name="kf02DTO.akb020" label="医院编码" />
				 <td colspan="8"></td>
				</tr>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:tabbedpanel id="divTabs">
	<powersi:tab id="tab1" target="paneltab1" label="导入批次列表" />
	<powersi:tab id="tab2" target="paneltab2" label="导入费用明细" />
	<div id="paneltab1">
	  <powersi:datagrid id="kf01Grid" 
				delayLoad="false" enabledEdit="true" enabledSort="false" showReload="false" page="1">
				<powersi:datagrid-column name="oper" display="操作" width="130" render="renderOper"/>
				<powersi:datagrid-column name="kf01id" display="导入批次号" width="110" />
				<powersi:datagrid-column name="akb020" display="医院编码" width="60" />
				<powersi:datagrid-column name="bkc030" display="导入人姓名"  width="80" />
				<powersi:datagrid-column name="bkc031" display="导入人工号"  width="70" />
				<powersi:datagrid-column name="bkc032" display="导入时间"  width="140" format="dateFmt:'yyyy-MM-dd HH:mm:ss'" />
				<powersi:datagrid-column name="bkc033" display="文件名称"  width="200" />
				<powersi:datagrid-column name="bkc034" display="文件类型"  width="55" />
				<powersi:datagrid-column name="bkc040" display="导入状态标志"  width="120" render="renderStatus"/>
				<powersi:datagrid-column name="progress" display="校验进度" width="80" render="renderProgress"/>
	  </powersi:datagrid> 
	
	</div>
	<div id="paneltab2">
	   <powersi:datagrid id="kf02Grid" enabledExportExcel="true" exportFileName="'卫生院批量费用导入校验结果'">
		        <%-- <powersi:datagrid-column name="kf01id" display="导入批次" hide="true"/> --%>
		        <powersi:datagrid-column name="rownum" display="序号*" hide="true" width="10"/>
		        <powersi:datagrid-column name="akb020" display="卫生站编号*" width="80" />
				<powersi:datagrid-column name="aac002" display="身份证号*" width="150" />
				<powersi:datagrid-column name="aac003" display="姓名*" width="70" />
				<powersi:datagrid-column name="bke036" display="就诊日期" width="100" format="dateFmt:'yyyy-MM-dd'"/>
				<powersi:datagrid-column name="bke030" display="总费用" width="60" />
				<powersi:datagrid-column name="bke034" display="甲类费用" width="60" />
				<powersi:datagrid-column name="bke035" display="丙类费用" width="60" />
				<powersi:datagrid-column name="ake039" display="统筹支付金额"  width="80" />
				<%-- <powersi:datagrid-column name="bke037" display="状态"  width="80" /> --%>
				<powersi:datagrid-column name="bke038" display="失败原因" width="400" />
				
	   </powersi:datagrid> 

	</div>
	</powersi:tabbedpanel>
	<powersi:hidden id="kf01info" name="kf01info" />
	<powersi:errors />
	<script type="text/javascript">
	
	window.onload=function(){
		queryData(0);
		/* var timer = setInterval(function() {
			console.log("timer");
			url="${rootPath}/diagnose/DiagnoseBatchChargeAction!queryDetatilCharge.action?kf02DTO.bke037=0";
			var params=form2json("#batchSeacherForm");
			postJSON(url, params, function(json){
				if(json&&json.rows&&json.rows.length>0){
					velidDate();
				}else{
					clearInterval(timer);
				}
					
			});
	    }, 180000); */
		
		
	}
	
	function renderOper(rowdata, index, value){
		return "<a href='javascript:void(0);' onclick='dbSelectRow("+powersi.tostring(rowdata)+","+index+","+1+")'>校验成功</a>"+
		       "&nbsp;&nbsp;"+
		       "<a href='javascript:void(0)' onclick='dbSelectRow("+powersi.tostring(rowdata)+","+index+","+2+")'>校验失败</a>";
	}
	
	function showStatusDate(rowdata, index ,status){
		popupAlert(rowdata+"="+index+"="+status);
	}
	
	function renderStatus(rowdata, index, value) {
		if (value === '0') {
			return '<span title="导入校验未完成">导入校验未完成</span>';
		} else if(value === '1') {
			return '<span title="导入校验全部完成">导入校验全部完成</span>';
		}
	}
	function renderFlag(rowdata, index, value) {
		if (value === '0') {
			return '<span title="未校验">未校验</span>';
		} else if(value === '1') {
			return '<span title="成功">成功</span>';
		}else{
			return '<span title="失败">失败</span>';
		}
	}
	
	var velidDate=function(){
		var url="${rootPath}/diagnose/DiagnoseBatchChargeAction!velidDate.action";
		var kf01info = kf01Grid.getData();
		kf01info = powersi.tostring(kf01info);
		$('#kf01info').val(encodeURI(feeinfo));
		var saveItemData = $("#batchSeacherForm").serialize();
		postJSON(url, saveItemData, function(json){
			if (!checkJSONResult(json)) {
				return;
			}
			if(json.data){
				popupInfo(json.data);
			}else{
				popupInfo(json.message);
			}
		});
	}

	var timers=[];
	var batchLead=function(){
		
        var url ="${rootPath}/pages/biz/medicare/diagnose/diagnoseBatchCharge_lead.jsp";
		openDialogWithParam(url, "", 100, 500, function(kf01id){
		    if(kf01id){
				var num=0;
				popupSuccess("上传成功！");
				/* timers.push(setInterval(function() {
			            num++;
			           if(num==1){
			        	   kf01Grid.reset(); 
			        	   kf01Grid.clearPager();
			           }
			           //var datas=kf01Grid.getData();
			           // kf01Grid.clearPager();
			            queryData(kf01id);
			            
			           
			            
			            
			          
			    }, 1000)); */
				 
		    }
		});
	}
	
	
	/*模板下载*/
	var downloadFile=function()
	{
		location.href="${rootPath}/diagnose/DiagnoseBatchChargeAction!downLoadExampleXls.action?bzc001=6";
	}
	
	//校验进度条
	function renderProgress(row, index, value){
		var per = parseFloat(value).toFixed(0);
		var color = '';
		if(per==100) {
			color = 'progress-bar-success';
		} else if(per > 65){
			color = 'progress-bar-info';
		} else if(per > 35){
			color = 'progress-bar-warning';
		} else if(per < 15) {
			color = 'progress-bar-danger';
		} else{
			color = 'progress-bar-primary';
		}
		
		var a = [];
		
		a.push('<div class="progress">');
		a.push('<div class="progress-bar textLeft ' + color + '" role="progressbar" aria-valuenow="' + per + '" aria-valuemin="0" aria-valuemax="100" style="width: ' + per + '%;">');
		a.push('<span>' + per + '%</span>');
		a.push('</div>');
		a.push('</div>');
		
		return a.join('');
	}
	
	/*清分明细提取和查询操作*/
	function queryData(flag){
		
		if(flag===0){
			if(!checkFormValidtion())
	     	{
		  		return;
			}
			selectTab('#divTabs', 0);
			kf01Grid.reset();
			kf02Grid.reset();
			var url="${rootPath}/diagnose/DiagnoseBatchChargeAction!queryBatchCharge.action";
			$("#kf01_id").val("");
			var params=form2json("#batchSeacherForm");
			kf01Grid.setParms(params); 
			kf01Grid.set("url",url );
			kf01Grid.refreshSize();  
		}else{
			selectTab('#divTabs', 0);
			var url="${rootPath}/diagnose/DiagnoseBatchChargeAction!queryBatchCharge.action?kf01DTO.bkc040=0&page=1";
			/* kf01Grid.setParms(null); 
			kf01Grid.set("url",url ); */
			$.ajax({ 
				type: "POST", 
				url: url,
				data: null, 
				success: function(data) { 
					if(data&&data.rows){
						kf01Grid.loadData(data.rows)
						if(!data.rows||data.rows.length<=0){
							for(var i=0;i<timers.length;i++){
			            		clearInterval(timers[i]);
			            	}
			            	
						}
					}
					
				}
			});
			kf01Grid.refreshSize();  
		}
		

	}
	
	
	function dbSelectRow(rowdata, rowid, rowobj){
		selectTab('#divTabs', 1);
  		//var row = kf02Grid.getRow(rowid);
  		$("#kf01_id").val(rowdata.kf01id);
  		$("#akb020_id").val(rowdata.akb020);
  		kf02Grid.reset();
  		var url="${rootPath}/diagnose/DiagnoseBatchChargeAction!queryDetatilCharge.action";
  		if(rowobj===1||rowobj===2){
  			url="${rootPath}/diagnose/DiagnoseBatchChargeAction!queryDetatilCharge.action?kf02DTO.bke037="+rowobj;
  		}
  		var params=form2json("#batchSeacherForm");
  		kf02Grid.setParms(params); 
		kf02Grid.set("url",url );
		kf02Grid.refreshSize();  
	}
  		
	
	
	
	</script>
</body>
</powersi:html>