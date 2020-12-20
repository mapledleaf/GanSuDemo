<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<powersi:html>
<powersi:head title="住院数据查询" />
<%@ include file="/pages/util/header/combogrid.jsp"%>
	<powersi:form id="bizForm" method="post" action="/universal/DiagnoseQueryAction_HIS!queryInHospitalPersonInfo_page.action">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:submit id="btSaveRegister" label="业务查询"/>
				<powersi:button id="btFundStatusQuery" label="医院费用抓取"
					onclick="feeUploadAgain();" />
				<powersi:button id="btFundStatusGet" label="医院登记抓取"
					onclick="getRegInfo();" />
			</powersi:panelbox-toolbar>
			
			<powersi:editorlayout cols="6">
				<powersi:editorlayout-row>
				<td class="tdLabel">结算状态：</td>  
				<td class="tdInput"><select id="bka044" name="kcd1_Hosp_HisDTO.bka044"  class="select" onchange="">
						<option value="0">未上传</option>
						<option value="1">未结算</option>
						<!-- <option value="2">已结算</option> -->
						<!-- <option value="3">重传 </option> -->
				</select>
				<powersi:textfield id="akc190" name="kcd1_Hosp_HisDTO.akc190"  key="住院号"  
					onKeyDown="" onchange="" required="false" />
				<powersi:textfield id="aae036" name="kcd1_Hosp_HisDTO.aae036" mask="date" key="入院时间"  format="dateFmt:'yyyy-MM-dd'"
					onKeyDown="" onchange="" required="false" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
			
		</powersi:panelbox>
	</powersi:form>
	
	<div class="col-5" >
		<powersi:panelbox key="panel_result" title="住院人员信息" >
			<powersi:datagrid id="grid" formId="bizForm" name="kcd1_Hosp_HisDTO" height="100%"  width="100%"  enabledSort="true" delayLoad="true"
	       		 checkbox="false" onSelectRow="queryFeeInfo">
	       			<powersi:datagrid-column name="aac003" display="姓名" align="center" />
	       			<powersi:datagrid-column name="akc190" display="住院号" align="center" />
	       			<powersi:datagrid-column name="kcd1id" display="住院登记序列号" align="center" />
	       			<powersi:datagrid-column name="aac001" display="个人编号" align="center"/>
	       			<powersi:datagrid-column name="aac002" display="身份证号码" align="center" />
	       			<powersi:datagrid-column name="aae030" display="入院时间" align="center"/>
	       			<powersi:datagrid-column name="akf001" display="入院科室编码" align="center" />
	       			<powersi:datagrid-column name="bka020" display="入院科室名称" align="center"/>
	       			<powersi:datagrid-column name="bka021" display="病区编码" align="center" />
	       			<powersi:datagrid-column name="bka022" display="病区名称" align="center" />
	       			<powersi:datagrid-column name="bka023" display="床位号" align="center" />
	       			<powersi:datagrid-column name="aaz217" display="就医登记号" align="center"/>
	       			<powersi:datagrid-column name="bka035" display="人员类别" align="center" />
	       			<powersi:datagrid-column name="aae036" display="登记时间" align="center" />
			</powersi:datagrid>
		</powersi:panelbox>
	</div>
	<!-- 费用明细 -->
	<powersi:form id="bizForm2" method="post" action="/universal/DiagnoseQueryAction_HIS!queryInHospitalPersonFee_page.action">
		<powersi:hidden id="akc190hidden" name="kcd1_Hosp_HisDTO.akc190" value=""></powersi:hidden>
	</powersi:form>
	<div class="col-7">
		<powersi:panelbox key="panel_result2" title="费用明细" >
			<powersi:datagrid id="gridFee" formId="bizForm2" name="rsList" height="100%"  width="100%"
	       		checkbox="false"  enabledSort="true" delayLoad="true" >
       				<powersi:datagrid-column name="kcd2id" display="人员医疗费用明细ID" align="center" hide="true"/>
	       			<powersi:datagrid-column name="kcd1id" display="结算云的费用序列号" align="center" hide="true"/>
	       			<powersi:datagrid-column name="akc190" display="住院号" align="center"/>
	       			<powersi:datagrid-column name="akb020" display="医院编号" align="center" />
	       			<powersi:datagrid-column name="aaz217" display="就医登记号" align="center" />
	       			<powersi:datagrid-column name="bka001" display="费用批次" align="center"/>
	       			<powersi:datagrid-column name="aaz213" display="费用序列号" align="center" />
	       			<powersi:datagrid-column name="aaa027" display="统筹区编码（分区用）" align="center" code="ake003" hide="true"/>
	       			<powersi:datagrid-column name="akc194" display="结算时间（分区用）" align="center" hide="true"/>
	       			<powersi:datagrid-column name="ake001" display="社保三大目录编码(对应kae8.bkc144)" align="center" hide="true"/>
	       			<powersi:datagrid-column name="ake002" display="社保三大目录名称(对应kae8.bkc143)" align="center" hide="true"/>
	       			<powersi:datagrid-column name="aaz277" display="医疗机构三大目录ID(暂不确定)" align="center" hide="true"/>
	       			<powersi:datagrid-column name="aaz231" display="社保三大目录ID(暂不确定)" align="center" hide="true"/>
	       			<powersi:datagrid-column name="ake105" display="药监局药品编码(暂不确定)" align="center" hide="true"/>
	       			<powersi:datagrid-column name="bka511" display="自付比例支付类型" align="center" />
	       			<powersi:datagrid-column name="ake005" display="医疗机构三大目录编码" align="center" hide="true"/>
	       			<powersi:datagrid-column name="ake006" display="医疗机构三大目录名称" align="center"/>
	       			<powersi:datagrid-column name="ake003" display="三大目录类别" align="center"/>
	       			<powersi:datagrid-column name="aka063" display="医疗发票项目类别" align="center" />
	       			<powersi:datagrid-column name="aka065" display="收费项目等级" align="center"/>
	       			<powersi:datagrid-column name="bka071" display="医疗机构序列号" align="center" hide="true"/>
	       			<powersi:datagrid-column name="aka070" display="剂型" align="center" />
	       			<powersi:datagrid-column name="aka067" display="药品剂量单位" align="center" />
	       			<powersi:datagrid-column name="aka074" display="规格" align="center" />
	       			<powersi:datagrid-column name="bka073" display="厂家" align="center"/>
	       			<powersi:datagrid-column name="akc226" display="数量" align="center" />
	       			<powersi:datagrid-column name="bka040" display="标准单价" align="center"/>
	       			<powersi:datagrid-column name="aae019" display="金额" align="center" />
	       			<powersi:datagrid-column name="bkz103" display="限制用药标识" align="center" hide="true"/>
	       			<powersi:datagrid-column name="bka067" display="费用冻结标志，用来表识参保人所在单位的基本医疗保险被冻结期间录入的费用。0：未冻结；1：已冻结；2：冻结已处理" align="center" hide="true"/>
	       			<powersi:datagrid-column name="bka062" display="退费对应的费用序列号" align="center" hide="true"/>
	       			<powersi:datagrid-column name="bka059" display="退费金额" align="center" hide="true" />
	       			<powersi:datagrid-column name="bka069" display="费用上传时间" align="center" />
	       			<powersi:datagrid-column name="ake007" display="费用发生时间" align="center" />
	       			<powersi:datagrid-column name="bka063" display="录入人工号" align="center"/>
	       			<powersi:datagrid-column name="bka064" display="录入人名称" align="center" />
	       			<powersi:datagrid-column name="bka065" display="录入时间" align="center" />
	       			<powersi:datagrid-column name="bka070" display="处方号" align="center" />
	       			<powersi:datagrid-column name="bka074" display="处方医师编号" align="center"/>
	       			<powersi:datagrid-column name="bka075" display="处方医师名称" align="center" />
	       			<powersi:datagrid-column name="bkh015" display="费用套餐id" align="center" hide="true"/>
	       			<powersi:datagrid-column name="bka080" display="优惠金" align="center" />
	       			<powersi:datagrid-column name="bkm001" display="是否在岗医师标识：0，非在岗；1，在岗" align="center" hide="true"/>
	       			<powersi:datagrid-column name="zxxmdj" display="" align="center" hide="true" />
	       			<powersi:datagrid-column name="bz1" display="" align="center" hide="true"/>
	       			<powersi:datagrid-column name="bz2" display="" align="center" hide="true"/>
	       			<powersi:datagrid-column name="bz3" display="" align="center" hide="true"/>
	       			<powersi:datagrid-column name="drbz" display="" align="center" hide="true" />
	  
			</powersi:datagrid>
		</powersi:panelbox>
	</div>
	
<script type="text/javascript">
//费用上传
function feeUpload(){
	//打开窗口，通过住院号
	var url = "${rootPath}/pages/biz/medicare/universal/chargeUpload_dialog.jsp";
	var dlg = popupDialog({
		url: url,
		onClosed: function(){
			var ret = this.returnValue;
			gridFee2.loadData(ret);
			//popupSuccess("ret:"+ret);
			//popupSuccess("试算成功！","");
		}
	}, "800", "700");
}


//查询个人信息
function queryOnePersonInfo(){
	//var saveItemData = $("#bizForm").serialize();
	var url = "${rootPath}/universal/DiagnoseQueryAction_HIS_HIS!queryInHospitalPersonInfo.action";
	//获取结算状态
	var bka044 = $("#bka044").val();
	var aae036 = $("#aae036").val();
	//住院号
	var akc190 = $("#akc190").val();
	//url = url+"?bka044="+bka044+"&"+businessType+"="+businessTypeValue+"&bka019="+bka019+"&bka020="+bka020+"&aae036="+aae036;
	url = url+"?bka044="+bka044+"&akc190="+akc190+"&aae036="+aae036;
	postJSON(url,
		{},
  		importCalcInfo);
}

//回掉函数
function importCalcInfo(ret){
	grid.loadData(ret.data);
}

//单击选择查询费用明细
function queryFeeInfo(data,rowid,rowdata){
 	gridFee.reset();
	$("#akc190hidden").val(data.akc190);
	$("#bizForm2").submit(); 
}
//费用重传,修改费用是否上传标记
function feeUploadAgain(){
	//获取被选中的行
	var data = grid.getSelectedRows();
	if(powersi.isempty(data)){
		popupAlert("请选择一行需要上传的住院信息");
		return;
	}
	
	
	var url = "${rootPath}/universal/DiagnoseQueryAction_HIS!inhospitalfeeUploadAgain.action";
	postJSON(url,{feeUpload:JSON.stringify(data)},function(ret){
		if(ret != null){
			if(ret.message == '-1'){
				popupWarn("其他数据正在重传，请稍等！","提示信息");
			}else{
				popupSuccess("设置重传成功","提示信息");
			}
		}else{
			popupWarn("未选择需要重传的数据","提示信息");
		}
	});
}

//住院登记信息抓取
function getRegInfo(){
	var url = "${rootPath}/pages/biz/medicare/diagnose_his/inhosp_dialog.jsp";
	var dlg = popupDialog({
		url: url,
		onClosed: function(){
			//var ret = this.returnValue;
			//gridFee2.loadData(ret);
			//popupSuccess("ret:"+ret);
			//popupSuccess("试算成功！","");
		}
	}, 180, 500);
}

</script>	
</powersi:html>