<%@page import="com.lbs.leaf.util.DateUtil"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%
	String curDate=DateUtil.dateToString(new Date(), "yyyy-MM-dd");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<powersi:html>
<powersi:head title="普通门诊查询" />
<%@ include file="/pages/util/header/combogrid.jsp"%>
		<powersi:form id="bizForm">
			<powersi:panelbox key="查询条件">
				<powersi:editorlayout>
				<powersi:editorlayout-row>
					<powersi:textfield id="aae030_start" name="kcd1_Mz_HisDTO.aae030" mask="date" key="开始时间"  
					 required="false" value="<%=curDate%>" readonly="false" format="dateFmt:'yyyy-MM-dd'"/>
					<!-- 当前时间之前 -->
					<powersi:textfield id="aae031_end" name="kcd1_Mz_HisDTO.aae031" mask="date" key="结束时间"  
					 required="false" value="<%=curDate%>" readonly="false" format="dateFmt:'yyyy-MM-dd'"/>
					<!-- 查询按钮 -->
					<input type="hidden"  id="akc190_hidden" value=""/>
					<input type="hidden" id="aac001_hidden" value="">
					<powersi:editorlayout-button>
						<powersi:button onclick="queryByNumberAndEndtime();" id="btSubmit" key="button_query" value="查询"
				disabled="false" />
					</powersi:editorlayout-button>
					<!-- 险种 -->
				<input type="hidden" id="aae140" value=""/>
				</powersi:editorlayout-row>
				</powersi:editorlayout>
			</powersi:panelbox>
		</powersi:form> 
		<!-- 流水号选择 -->
		<powersi:datagrid id="grid" name="rsList" height="50%"  width="100%" exportFileName="'首诊医院参保人信息'" 
       		showExportExcel="true" showExportExcel2007="true"  showExportPdf="true"  enabledSort="true" delayLoad="true" onSelectRow="queryNumberDetails">
       			<powersi:datagrid-column name="aac003" display="姓名" align="center" width="25%" />
       			<powersi:datagrid-column name="aaz217" display="门诊就医号" align="center" width="25%" />
       			<powersi:datagrid-column name="aae036" display="费用时间" align="center" width="50%"/>
		</powersi:datagrid>
		<!-- 详情 -->
		<powersi:datagrid id="numberdetail"  name="Kcd2FeeHisDTOList" height="25%"  width="100%" exportFileName="'首诊医院参保人信息'" 
       		showExportExcel="true" showExportExcel2007="true"  showExportPdf="true"  enabledSort="true" delayLoad="true" >
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
		<!-- 按钮 -->
		<powersi:buttons>
			<powersi:button id="btnCalc0" key="" value="结转费用" onclick="checkFee();"
				disabled="true" />
			<powersi:button id="btnCalc1" key="" value="确认收费" onclick="confirmationFee();"
				disabled="true" />
			<powersi:button id="btnReset" key="" value="退出" onclick="resetpage();"
				disabled="false" />
		</powersi:buttons>
		<!-- 详细信息 -->
		<powersi:datagrid id="feeMatch"  name="rsList" height="25%"  width="100%" exportFileName="'首诊医院参保人信息'" 
       		showExportExcel="true" showExportExcel2007="true"  showExportPdf="true"  enabledSort="true" delayLoad="true" >
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
		
<script type="text/javascript"> 
var aae140 = ${aae140List};
$(function(){
	$("#aae140").val(aae140[0]);
}); 

//根据流水号和时间查询购药信息
function queryByNumberAndEndtime(){
	var akc190 = $("#akc190_hidden").val();
	var aac001 = $("#aac001_hidden").val();
	var aae031 = $("#aae031_end").val();
	var aae030 = $("#aae030_start").val();
	if(akc190 == null || akc190 == ''){
		popupWarn("门诊号(akc190)不能为空！");
		return;
	}
	
	var sURL = "${rootPath}/universal/DiagnoseQueryAction_HIS!queryNumberDetailsByTimeOrNumber.action?aae031="+aae031+"&aae030="+aae030+"&aac001="+encodeURI(encodeURI(aac001,"UTF-8"));
	postJSON(sURL,null,function(ret){
		grid.reset();
		grid.loadData(ret.data);
	});
}



//查询流水号详情
function queryNumberDetails(data,rowid,rowdata){
	//alert("data:"+data.akc190);
	//console.log(data);
	var deData = numberdetail.getData();
	for(var index in deData){
		numberdetail.deleteRow(0);
	}
	var sURL = "${rootPath}/universal/DiagnoseQueryAction_HIS!queryDiagnoseByNumber.action";
	postJSON(sURL,data,function(ret){
		if(ret.data != null && ret.data != ''){
			numberdetail.loadData(ret.data);
			$("#btnCalc0").attr("disabled", false);
		}
	});
}


//结转费用
function checkFee(){
	//获取数据
	var feeData = numberdetail.getAllData();
	//feeData = feeData.toJson();
	var sURL = "${rootPath}/universal/DiagnoseQueryAction_HIS!checkFee.action?aae140="+$("#aae140").val();
	//console.log(feeData);
	//alert("feeData:"+feeData[0].aaa027);
	for(var index in feeData){
		if(feeData[index].ake007 == null || feeData[index].ake007 == ''){
			var ind = parseInt(index)+1;
			popupWarn("第"+ind+"条费用时间为空，校验失败！");
			return;
		}
	}
	postJSON(sURL,{datas:JSON.stringify(feeData)},function(ret){
		if(ret.message == '-1'){
			popupWarn("费用结转校验失败，存在未匹配数据，请重新上传！");
			feeMatch.loadData(ret.data);
			for(var index in ret.data){
				feeMatch.setRowColor(index,"red");
			}
		}else if(ret.message == '-2'){
			popupWarn(ret.data);
		}else{
			if (!checkJSONResult(ret)) {
				return;
			}
			popupSuccess("校验成功！");
			numberdetail.loadData(ret.data);
			$("#btnCalc1").attr("disabled", false);
		}
	});
}

//确认收费
function confirmationFee(){
	closeDialog(numberdetail.getAllData());
}

//关闭页面
function resetpage(){
	closeDialog();
}

//加载页面时，查询携带过来的人员信息
var s=${mzList}; 
$(function(){
	if(s != null && s != ''){
		$("#akc190_hidden").val(s[0].akc190);
		$("#aac001_hidden").val(s[0].aac001);
	}
	grid.loadData(s);
}); 
</script>		
			
</powersi:html>