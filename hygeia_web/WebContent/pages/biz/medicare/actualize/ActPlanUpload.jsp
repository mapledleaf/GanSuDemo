<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<powersi:head title="实施j进度上报" />
<body>
	<powersi:panelbox key="panel_query">
		<powersi:form id="queryForm">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:hidden id="bkf307" label="医院计划id"/>
					<powersi:textfield id="bkf318" label="计划名称" name="kab3Dto.bkf318" readonly="true" 
						buttonText="选择" buttonId="plan" onbuttonclick="chooseHospPlan()"/>
					<powersi:textfield id="aae100" label="医院计划状态" readonly="true"/>
					<powersi:textfield id="bkf320" label="计划开始时间" readonly="true" />
					<powersi:textfield id="bkf321" label="预计结束时间" readonly="true"/>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:form>
	</powersi:panelbox>
	<div class="row">
		<div class="col-8">
			<powersi:panelbox key="panel_result" title="实施计划信息">
				<a>双击单条查看日志信息</a>
				<powersi:datagrid id="gridList" frozen="false" onDblClickRow="queryLog"
					detail="{onShowDetail: showDetail, height:'auto'}">
					<powersi:datagrid-column display="实施计划id" 	name="bkf306" hide="true"/>
					<powersi:datagrid-column display="实施计划医院表id" name="bkf307" hide="true"/>
					<powersi:datagrid-column display="实施计划明细id" 	name="bkf309" hide="true"/>
					<powersi:datagrid-column display="业务子类" 		name="bkf303" width="13%"/>
					<powersi:datagrid-column display="业务子类描述" 	name="bkf304" width="35%"/>
					<powersi:datagrid-column display="是否需要测试" 	name="bkf310" width="15%" render="render_bkf310"/>
					<powersi:datagrid-column display="his完成情况"  	name="bkf311" width="15%" render="wc_flags"/>
					<powersi:datagrid-column display="联调情况"  		name="bkf312" width="12%" render="wc_flags"/>
					<powersi:datagrid-column display="联调次数" 		name="bkf313" width="10%" />
				</powersi:datagrid>
			</powersi:panelbox>
		</div>
		<div class="col-4">
			<powersi:panelbox iconClass="icon-cog" title="明细日志信息"
				allowCollapse="false">
				<br/>
				<powersi:datagrid id="logFrom" delayLoad="true" enabledSort="false"
					showReload="false">
					<powersi:datagrid-column name="bkf316"  label="实施信息记录" width="65%"/>
					<powersi:datagrid-column name="bkf317"  label="记录时间"    width="35%" format="{0,date,yyyy-MM-dd}"/>
				</powersi:datagrid>
			</powersi:panelbox>
		</div>
	</div>
	<powersi:errors />
	<!-- 隐藏区 -->
	<div class="hidden">
		<form id="detailForm">
			<powersi:editorlayout cols="4">
				<powersi:editorlayout-row>
					<powersi:radio id="bkf311" codeType="finish_flag"  key="bkf311" label="his完成情况" />
					<powersi:radio id="bkf312" codeType="finish_flag"  key="bkf312" label="联调情况" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea name="bkf316" label="实施信息记录" rows="5" colspan="3" required="true"/>
					<powersi:hidden name="bkf306" label="实施计划id"/>
					<powersi:hidden name="bkf307" label="实施计划医院表id"/>
					<powersi:hidden name="bkf309" label="实施计划明细id"/>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
			<div class="divButton">
				<powersi:button id="detail_btn_save" label="保存" name="detail_btn_save" cssClass="btn btn-info"/>
			</div>
		</form>
	</div>
</body>
</powersi:html>
<script>
	/* window.onload = function(){
		PlanDetails();
	} */
	function chooseHospPlan(){
		popupDialog({
			url:"${rootPath}/pages/biz/medicare/actualize/ChooseHospPlan.jsp",
			onClosed:function(){
				var ret = this.returnValue;
				$("#bkf307").val(ret.bkf307);$("#aae100").val(ret.aae100);
				$("#bkf320").val(ret.bkf320);$("#bkf321").val(ret.bkf321);
				$("#bkf318").val(ret.bkf318);
				PlanDetails();
			}
		},500,600);
	}
	
	function PlanDetails() {
		var bkf307 = $("#bkf307").val();
		var url = "${rootPath}/actualize/ActualizeManageAction!queryPlanDetails.action"
	     postJSON(url,{
	    	 	"bkf307":bkf307
			},function(json) {
				 if(!checkJSONResult(json)){
			          return;
			     }
				 gridList.loadData(json.data);
			});
	}
	
	//获取日志明细
	function queryLog(rowdata, rowid, rowobj){
		var row = gridList.getRow(rowid);
  		var bkf309 = row['bkf309'];
        postJSON("${rootPath}/actualize/ActualizeManageAction!queryLogList.action",{
    	 	"bkf309":bkf309
		},function(json) {
			 if(!checkJSONResult(json)){
		          return;
		     }
			 logFrom.loadData(json.data);
		});
	}
	
	function showDetail(row, detailPanel,callback) {
		try{
			var suffix = '_' + (row['bkf303'] || '_new_');
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
			//初始化表单验证(需要指定grid容器)
			initFormValidtion(form, {
				container: gridList.gridbody
			});
			//显示form
			$panel.append(form);
		}catch(ex){
			popupInfo(ex.message, 3000);
		}
	}
	
	function saveDetailForm(form, idx) {
		try{
			//校验表单
			if(!checkForm(form)){
				return;
			}
			var aae100 = $("#aae100").val();
			if(aae100 == "已申请"){
				popupInfo("已申请的计划不能再进行修改！", 3000);
				return;
			}
			if(aae100 == "已通过"){
				popupInfo("已通过审核的计划不能再进行修改！", 3000);
				return;
			}
			//获取data
			var data = form.serialize();
			//更新datagrid显示
			var row = form2json(form);
			var logInfo = powersi.tostring(row);
			postJSON("${rootPath}/actualize/ActualizeManageAction!savePlanLog.action",
					{
						"logInfo" : logInfo
					}, function(json) {
						popupSuccess("进度上报成功！",3000);
						PlanDetails();
					});
		} catch(ex){
			popupInfo(ex.message,3000);
		}
	}
	
	function wc_flags(rowdata,index,value){
		if(value=="1"){
			 return "已完成";
		}else if(value=="0"){
			return "未完成";
		}else{
			return " ";
		}
	}
	
	function render_bkf310(rowdata,index,value){
		if(value=="1"){
			 return "是";
		}else if(value=="0"){
			 return "否";
		}else{
			return " ";
		}
	}
	
	function renderDate(rowdata,index,value) {
        if(value!=null&&value !=""){
        	 var date = new Date(value);
             return date.getFullYear() + '-'+ (date.getMonth() + 1) + '-' + date.getDate();
        }else{
             return " ";
        }
    }
</script>
