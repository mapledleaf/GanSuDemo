<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>


<powersi:html>
<head>
<powersi:head title="目录匹配数据批量导入" />
</head>
<body>
	<powersi:form id="CataMatchForm">
		<powersi:panelbox key="条件设置">
			<powersi:panelbox-toolbar>
				  <powersi:button id="btQuery" label="查询"  onclick="queryData(0)"/>
				<powersi:button id="btDetail" label="批量导入" onclick="batchLead()" />
				<powersi:button value="模版下载" onclick="downloadFile()" />
				<%-- <powersi:button value="校验" onclick="velidDate()" />  --%>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout>
				 <powersi:hidden id="kf03_id" name="kf04DTO.kf03id" label="批次号" />
				 <powersi:hidden id="akb020_id" name="kf04DTO.akb020" label="医院编码" />
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:tabbedpanel id="divTabs">
		<powersi:tab id="tab1" target="paneltab1" label="导入批次列表" />
		<powersi:tab id="tab2" target="paneltab2" label="导入费用明细" />
		<div id="paneltab1">
			<powersi:datagrid id="kf03Grid" delayLoad="false" enabledEdit="true"
				enabledSort="false" showReload="false" page="1">
				<powersi:datagrid-column name="oper" display="操作" width="175"
					render="renderOper" />
				<powersi:datagrid-column name="kf03id" display="导入批次号" width="110" />
				<powersi:datagrid-column name="akb020" display="医院编码" width="60" hide="true"/>
				<powersi:datagrid-column name="bkc030" display="导入人姓名" width="70" />
				<powersi:datagrid-column name="bkc031" display="导入人工号" width="70" />
				<powersi:datagrid-column name="bkc032" display="导入时间" width="140" format="dateFmt:'yyyy-MM-dd HH:mm:ss'" />
				<powersi:datagrid-column name="bkc033" display="文件名称" width="200" />
				<powersi:datagrid-column name="bkc034" display="文件类型" width="45" />
				<powersi:datagrid-column name="bkc040" display="导入状态标志" width="105" render="renderStatus" />
				<powersi:datagrid-column name="progress" display="校验进度" width="70" render="renderProgress" />
			</powersi:datagrid>

		</div>
		<div id="paneltab2">
			<powersi:datagrid id="kf04Grid" enabledExportExcel="true"
				exportFileName="'目录匹配数据导入校验结果'">
				<powersi:datagrid-column name="akb020" display="医院编号" width="1%" hide="true" />
				<powersi:datagrid-column name="ake003" display="三大目录类别" code="ake003" width="8%" />
				<powersi:datagrid-column name="ake005" display="收费项目编码" width="10%" />
				<powersi:datagrid-column name="ake006" display="收费项目名称" width="14%" />
				<powersi:datagrid-column name="aae030" display="生效开始时间" width="10%" format="dateFmt:'yyyy-MM-dd'" />
				<powersi:datagrid-column name="aae031" display="生效结束时间" width="10%" format="dateFmt:'yyyy-MM-dd'" />
				<powersi:datagrid-column name="bkm029" display="医保识别码" width="13%" />
				<powersi:datagrid-column name="bkm017" display="药监本位码" width="13%" />
				<powersi:datagrid-column name="bke038" display="失败原因" width="23%" />
				<powersi:datagrid-column name="bkc138" display="剂型" width="1%"
					hide="true" />
				<powersi:datagrid-column name="bkc139" display="规格" width="1%"
					hide="true" />
				<powersi:datagrid-column name="bkc140" display="单价" width="1%"
					hide="true" />
				<powersi:datagrid-column name="bkm020" display="包装规格" width="1%"
					hide="true" />
		</powersi:datagrid>

		</div>
	</powersi:tabbedpanel>
	<powersi:errors />
	<script type="text/javascript">
		window.onload = function() {
			queryData(0);
			/* var timer = setInterval(function() {
				console.log("timer");
				url="${rootPath}/diagnose/DiagnoseBatchChargeAction!queryDetatilCharge.action?kf04DTO.bke037=0";
				var params=form2json("#CataMatchForm");
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
			var oper="<a href='javascript:void(0);' onclick='dbSelectRow("+powersi.tostring(rowdata)+","+index+","+1+")'>校验成功</a>"+
		       "&nbsp;&nbsp;"+
		       "<a href='javascript:void(0)' onclick='dbSelectRow("+powersi.tostring(rowdata)+","+index+","+2+")'>校验失败</a>";
		    if(parseInt(rowdata.progress).toFixed(0)!=100){
		    	oper=oper+"&nbsp;&nbsp;"+
			       "<a href='javascript:void(0)' onclick='velidDate("+rowdata.kf03id+")'>重新校验</a>";
		    }
			return oper;
		}

		function showStatusDate(rowdata, index, status) {
			popupAlert(rowdata + "=" + index + "=" + status);
		}

		function renderStatus(rowdata, index, value) {
			if (value === '0') {
				return '<span title="导入校验未完成">导入校验未完成</span>';
			} else if (value === '1') {
				return '<span title="导入校验全部完成">导入校验全部完成</span>';
			}
		}
		function renderFlag(rowdata, index, value) {
			if (value === '0') {
				return '<span title="未校验">未校验</span>';
			} else if (value === '1') {
				return '<span title="成功">成功</span>';
			} else {
				return '<span title="失败">失败</span>';
			}
		}

		//2017-12-04 重新校验
		var velidDate=function(kf03id){
			var url="${rootPath}/diagnose/CatalogMatchBatchImportAction!velidDate.action?kf03DTO.kf03id="+kf03id;
			var params=form2json("#CataMatchForm");
			postJSON(url, params, function(json){
				queryData(0);	
			});
		}
		//2017-12-04 重新校验
		var timers = [];
		var batchLead = function() {
			$("#btDetail").attr("disabled", true);
			var url = "${rootPath}/pages/biz/medicare/diagnose/CatalogMatchUpload.jsp";
			openDialogWithParam(url, "", 100, 500, function(kf03id) {
				if (kf03id) {
					var num = 0;
					popupSuccess("上传成功！");
					/* timers.push(setInterval(function() {
					        num++;
					       if(num==1){
					    	   kf03Grid.reset(); 
					    	   kf03Grid.clearPager();
					       }
					       //var datas=kf03Grid.getData();
					       // kf03Grid.clearPager();
					        queryData(kf03id);
					        
					       
					        
					        
					      
					}, 1000)); */

				}
			});
		}

		/*模板下载*/
		var downloadFile = function() {
			location.href = "${rootPath}/diagnose/CatalogMatchBatchImportAction!downLoadExampleXls.action?bzc001=7";
		}

		//校验进度条
		function renderProgress(row, index, value) {
			var per = parseFloat(value).toFixed(0);
			var color = '';
			if (per == 100) {
				color = 'progress-bar-success';
			} else if (per > 65) {
				color = 'progress-bar-info';
			} else if (per > 35) {
				color = 'progress-bar-warning';
			} else if (per < 15) {
				color = 'progress-bar-danger';
			} else {
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
		function queryData(flag) {

			if (flag === 0) {
				if (!checkFormValidtion()) {
					return;
				}
				selectTab('#divTabs', 0);
				kf03Grid.reset();
				kf04Grid.reset();
				var url = "${rootPath}/diagnose/CatalogMatchBatchImportAction!queryCatalogMatch.action";
				$("#kf03_id").val("");
				var params = form2json("#CataMatchForm");
				kf03Grid.setParms(params);
				kf03Grid.set("url", url);
				kf03Grid.refreshSize();
			} else {
				selectTab('#divTabs', 0);
				var url = "${rootPath}/diagnose/CatalogMatchBatchImportAction!queryCatalogMatch.action?kf03DTO.bkc040=0&page=1";
				$.ajax({
					type : "POST",
					url : url,
					data : null,
					success : function(data) {
						if (data && data.rows) {
							kf03Grid.loadData(data.rows)
							if (!data.rows || data.rows.length <= 0) {
								for (var i = 0; i < timers.length; i++) {
									clearInterval(timers[i]);
								}

							}
						}

					}
				});
				kf03Grid.refreshSize();
			}

		}

		function dbSelectRow(rowdata, rowid, rowobj) {
			selectTab('#divTabs', 1);
			$("#kf03_id").val(rowdata.kf03id);
	  		$("#akb020_id").val(rowdata.akb020);
			kf04Grid.reset();
			var url = "${rootPath}/diagnose/CatalogMatchBatchImportAction!queryCataMatchDetatil.action";
			if (rowobj === 1 || rowobj === 2) {
				url = "${rootPath}/diagnose/CatalogMatchBatchImportAction!queryCataMatchDetatil.action?kf04DTO.bke037="
						+ rowobj;
			}
			var params = form2json("#CataMatchForm");
			kf04Grid.setParms(params);
			kf04Grid.set("url", url);
			kf04Grid.refreshSize();
		}
	</script>
</body>
</powersi:html>