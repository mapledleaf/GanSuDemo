<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%@ page import="com.powersi.hygeia.framework.util.DateFunc"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%
	String akb021 = BizHelper.getAkb021();
%>
<html>
<powersi:head title="医院月度结算台账查询" />
<style type="text/css">
#frame_div {
	margin: 20px 0;
	width: 100%;
	height: auto;
	border: 1px solid #00F;
	overflow: auto;
}
</style>
<body>
	<powersi:form id="queryForm" namespace="/queryAccount">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="query" label="检 索" onclick="doQuery()" />
				<%-- <powersi:button label="打 印" onclick=""/>  --%>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row cols="8">
					<powersi:textfield id="akb021" label="医院名称" readonly="true" value="<%=akb021 %>"/>
					<powersi:codeselect id="type" label="结算类型"  name="accountDTO.type"
					 list="#{'3':'住院','2':'门诊','1':'门特'}"/>
					<powersi:textfield id="year" required="true"  name="accountDTO.year" label="结算所属年月" validate = "integer,max[209912],min[190001],maxSize[6],minSize[6]"
					 	onclick="WdatePicker({dateFmt:'yyyyMM'})" />
					<td align="right" class="tdLabel">险种类别</td>
					<powersi:hidden id="aae140_name" name="accountDTO.aae140_name" />	
					<td><select id="aae140" name="accountDTO.aae140">
							<option value='391'>居民</option>
							<option value='310' >职工</option>
					</select></td>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>	
	</powersi:form>
		<powersi:panelbox key="panel_result" title="台账列表">
			<powersi:tabbedpanel id="divTabs">
				<powersi:tab id="tab1" target="divTab1" label="月度结算台账"  />
				<powersi:tab id="tab2" target="divTab2" label="月度结算台账明细" />
				<powersi:tab id="tab3" target="divTab3" label="月度零报明细" />
				<powersi:tab id="tab4" target="divTab4" label="血透汇总表" />
				<div id="divTab1">
					<div id="zy_div" style="display:'';height: 430px;" class="zy_div">
						<powersi:datagrid id="accountInfo_zy" showExportExcel="true" 
							delayLoad="true"  showExportExcel2007="true"
							exportFileName="'月度结算台账'" >
							<powersi:datagrid-column name="bkb092" display="集团编码"/>
							<powersi:datagrid-column name="bae010" display="业务交接号"/>
							<powersi:datagrid-column name="aaa027" display="医院所属区"/>
							<powersi:datagrid-column name="akb021" display="医院名称"/>
							<powersi:datagrid-column name="aae140_name" display="险种"/>
							<powersi:datagrid-column name="aae140" display="险种id" hide="true" />
							<powersi:datagrid-column name="bka045" display="所属年月"/>
							<powersi:datagrid-column name="bkc046" display="当期总费用"/>
							<powersi:datagrid-column name="bkc047" display="当期住院人次"/>
							<powersi:datagrid-column name="bkc048" display="当期住院人数"/>
							<powersi:datagrid-column name="bkc049" display="住院人数"/>
							<powersi:datagrid-column name="bkc063" display="住院人次"/>
							<powersi:datagrid-column name="bkc068" display="零报纳入人次"/>
							<powersi:datagrid-column name="bkc050" display="上年次均费用"/>
							<powersi:datagrid-column name="bkc051" display="当期跨区住院人次"/>
							<powersi:datagrid-column name="bkc052" display="跨区住院率"/>
							<powersi:datagrid-column name="bkc053" display="重复住院率"/>
							<powersi:datagrid-column name="bkc054" display="次均增长率"/>
							<powersi:datagrid-column name="bkc055" display="医院预付比例"/>
							<powersi:datagrid-column name="bkb097" display="医院系数"/>
							<powersi:datagrid-column name="bkc056" display="本月预付比例"/>
							<powersi:datagrid-column name="bkc057" display="当月得分"/>
							<powersi:datagrid-column name="bkc069" display="违规分数"/>
							<powersi:datagrid-column name="bkc070" display="实际结算分数"/>
							<powersi:datagrid-column name="bkc058" display="当期单价"/>
							<powersi:datagrid-column name="bkc067" display="总控额"/>
							<powersi:datagrid-column name="bkc059" display="当月总费用"/>
							<powersi:datagrid-column name="bkc064" display="统筹基金应支付"/>
							<powersi:datagrid-column name="bkc065" display="补充保险支付"/>
							<powersi:datagrid-column name="bkc066" display="参保人自负"/>
							<powersi:datagrid-column name="bkc060" display="当月统筹支付金额(含暂扣的钱)"/>
							<powersi:datagrid-column name="bkc074" display="精神病床日结算总金额"/>
							<powersi:datagrid-column name="bkc073" display="精神病床日结算标准"/>
							<powersi:datagrid-column name="bkc072" display="精神病床日"/>
							<powersi:datagrid-column name="bkc061" display="大病单议金额"/>
							<powersi:datagrid-column name="bkc062" display="实际结算金额"/>
						</powersi:datagrid>
					</div>
					<div id="Mz_div" style="display:none;height: 430px;" class="Mz_div">
						<powersi:datagrid id="accountInfo_mz" showExportExcel="true"
							delayLoad="true"  showExportExcel2007="true" 
							exportFileName="'月度结算台账'" >
							<powersi:datagrid-column name="aaa027_name" display="统筹区"/>
							<powersi:datagrid-column name="v23" display="城镇居民人数"/>
							<powersi:datagrid-column name="v22" display="城镇职工人数"/>
							<powersi:datagrid-column name="v24" display="登记人数"/>
							<powersi:datagrid-column name="hjrs" display="合计享受待遇人数"/>
							<powersi:datagrid-column name="bka590hej" display="包干金额"/>
							<powersi:datagrid-column name="v12" display="当期就诊人次"/>
							<powersi:datagrid-column name="v4" display="当期医疗总费用"/>
							<powersi:datagrid-column name="v3" display="当期超包干金额"/>
							<powersi:datagrid-column name="v9" display="当期统筹支付金额"/>
							<powersi:datagrid-column name="v18" display="当期统筹基金应支付金额"/>
							<powersi:datagrid-column name="v21" display="补划人数"/>
							<powersi:datagrid-column name="bfje" display="补划金额"/>
							<powersi:datagrid-column name="yfje" display="当期预付金额"/>
							<powersi:datagrid-column name="v8" display="退回金额"/>
						</powersi:datagrid>
					</div>
					<div id="Mt_div" style="display:none;height: 430px;" class="Mt_div">
						<powersi:datagrid id="accountInfo_mt" showExportExcel="true"
							delayLoad="true"  showExportExcel2007="true" 
							exportFileName="'月度结算台账'" >
							<powersi:datagrid-column name="aae140_name" display="医疗类别"/>
							<powersi:datagrid-column name="v12" display="已核定人次"/>
							<powersi:datagrid-column name="v13" display="其中零报纳入"/>
							<powersi:datagrid-column name="v15" display="急诊人次"/>
							<powersi:datagrid-column name="v4" display="医疗总费用"/>
							<powersi:datagrid-column name="v5" display="零报纳入"/>
							<powersi:datagrid-column name="v16" display="急诊费用"/>
							<powersi:datagrid-column name="v9" display="统筹基金应支付"/>
							<powersi:datagrid-column name="v8" display="扣除门特费用"/>
							<powersi:datagrid-column name="v17" display="血透未超定额结算"/>
							<powersi:datagrid-column name="v18" display="实际统筹基金应支付"/>
							<powersi:datagrid-column name="v10" display="补充总支付"/>
							<powersi:datagrid-column name="v6" display="参保人已支付"/>
							<powersi:datagrid-column name="v11" display="二次补充支付"/>
						</powersi:datagrid>
					</div>
				</div>
				<div id="divTab2">
					<div id="deitalzy_div" style="display:'';height: 430px;" class="zy_div">
						<powersi:datagrid id="accountDeitalInfo_zy" showExportExcel="true" 
							delayLoad="true"  showExportExcel2007="true" statusName="delete"
							exportFileName="'月度结算台账明细'">
							<powersi:datagrid-column name="rownum" display="序号" hide="true"/>
							<powersi:datagrid-column name="aac003" display="姓名" />
							<powersi:datagrid-column name="aac002"	 display="身份证" 	 />
							<powersi:datagrid-column name="aac001"	 display="个人编码"	 />
							<powersi:datagrid-column name="aac066_name"	 display="参保类别"	 />
							<powersi:datagrid-column name="bka004_name"	 display="人员类别"	 />
							<powersi:datagrid-column name="bka008"	 display="单位名称"	 />
							<powersi:datagrid-column name="aka130_name"	 display="医疗类别"	 />  
							<powersi:datagrid-column name="akb021"	 display="医疗机构名称"	 />
							<powersi:datagrid-column name="begin_date" display="入院日期"	 />
							<powersi:datagrid-column name="fin_date"	 display="出院日期" />
							<powersi:datagrid-column name="zdmc"		 display="诊断名称" /> 
							<powersi:datagrid-column name="ysfz"		 display="原始分值" />
							<powersi:datagrid-column name="cyks"		 display="出院科室" />
							<powersi:datagrid-column name="total_fee"	 display="医疗总费用"	 />
							<powersi:datagrid-column name="self_pay"	 display="自费费用"	 />
							<powersi:datagrid-column name="qfx_pay"	 display="起伏线"	 />
							<powersi:datagrid-column name="jbylf" 	 display="基本医疗费用"	 />  
							<powersi:datagrid-column name="fdzfje"	 display="分段自付金额"	 />
							<powersi:datagrid-column name="medi_acc_pay" display="统筹支付"	 />
							<powersi:datagrid-column name="zdjbbzgfd"	 display="超限额"	 />
							<powersi:datagrid-column name="bcbxze"	 display="补充报销总额	" />  
							<powersi:datagrid-column name="ecbxze"	 display="二次补充报销总额" />
							<powersi:datagrid-column name="sup_acc_pay" display="公务员补助" />
							<powersi:datagrid-column name="self_sum_pay" display="个人支付总额	" />
							<powersi:datagrid-column name="aaa027_name" display="地区	" />
							<powersi:datagrid-column name="aaz217"	 display="住院门诊登记号"	 />  
							<powersi:datagrid-column name="akb020"	 display="定点医疗机构编码	" />
							<powersi:datagrid-column name="bdyd"	 	 display="本地、异地"	 />
							<powersi:datagrid-column name="bka124" 	 display="ICD码" />
							<powersi:datagrid-column name="bka164_name" display="诊治代码" />
							<powersi:datagrid-column name="bka174_a" 	 display="月度计算得分" />
							<powersi:datagrid-column name="bka200" 	 display="年终重算大病单议" />
							<powersi:datagrid-column name="bkb115" 	 display="年终重算上年单价" />
							<powersi:datagrid-column name="bka164" 	 display="年终重算诊治代码" />
							<powersi:datagrid-column name="bka046" 	 display="年终重算参考比例" />
							<powersi:datagrid-column name="bka199" 	 display="年终重算病种分值" />
							<powersi:datagrid-column name="bkb097" 	 display="年终重算医院系数" />
							<powersi:datagrid-column name="bka125" 	 display="年终重算医院系数" />
							<powersi:datagrid-column name="bka047" 	 display="年终重算分值类型" render="renderBka047"/>
							<powersi:datagrid-column name="bka174" 	 display="年终重算得分" />
							<powersi:datagrid-column name="bka280" 	 display="重复住院标志" render="renderbka280"/>
							<powersi:datagrid-column name="bka122" 	 display="审核标志" render="renderBka122"/>
						</powersi:datagrid>
					</div>
					<div id="daitelOther_div" style="display:none;height: 430px;" class="other_div">
						<powersi:datagrid id="accountDeitalInfo_other" showExportExcel="true"
							delayLoad="true"  showExportExcel2007="true" 
							exportFileName="'月度结算台账明细'">
							<powersi:datagrid-column name="rownum" display="序号" hide="true"/>
							<powersi:datagrid-column name="aac003" display="姓名" />
							<powersi:datagrid-column name="aac002"	 display="身份证" 	 />
							<powersi:datagrid-column name="aac001"	 display="个人编码"	 />
							<powersi:datagrid-column name="aac066_name"	 display="参保类别"	 />
							<powersi:datagrid-column name="bka004_name"	 display="人员类别"	 />
							<powersi:datagrid-column name="bka008"	 display="单位名称"	 />
							<powersi:datagrid-column name="aka130_name"	 display="医疗类别"	 />  
							<powersi:datagrid-column name="akb021"	 display="医疗机构名称"	 />
							<powersi:datagrid-column name="begin_date" display="入院日期"	 />
							<powersi:datagrid-column name="fin_date"	 display="出院日期" />
							<powersi:datagrid-column name="zdmc"		 display="诊断名称" /> 
							<powersi:datagrid-column name="ysfz"		 display="原始分值" />
							<powersi:datagrid-column name="cyks"		 display="出院科室" />
							<powersi:datagrid-column name="total_fee"	 display="医疗总费用"	 />
							<powersi:datagrid-column name="self_pay"	 display="自费费用"	 />
							<powersi:datagrid-column name="qfx_pay"	 display="起伏线"	 />
							<powersi:datagrid-column name="jbylf" 	 display="基本医疗费用"	 />  
							<powersi:datagrid-column name="fdzfje"	 display="分段自付金额"	 />
							<powersi:datagrid-column name="medi_acc_pay" display="统筹支付"	 />
							<powersi:datagrid-column name="zdjbbzgfd"	 display="超限额"	 />
							<powersi:datagrid-column name="bcbxze"	 display="补充报销总额	" />  
							<powersi:datagrid-column name="ecbxze"	 display="二次补充报销总额" />
							<powersi:datagrid-column name="sup_acc_pay" display="公务员补助" />
							<powersi:datagrid-column name="self_sum_pay" display="个人支付总额	" />
							<powersi:datagrid-column name="aaa027_name" display="地区	" />
							<powersi:datagrid-column name="aaz217"	 display="住院门诊登记号"	 />  
							<powersi:datagrid-column name="akb020"	 display="定点医疗机构编码	" />
							<powersi:datagrid-column name="bdyd"	 	 display="本地、异地"	 />
							<powersi:datagrid-column name="bka124" 	 display="ICD码" />
							<powersi:datagrid-column name="bka164_name" 	 display="诊治代码" />
						</powersi:datagrid>
					</div>
					</div>					
					<div id="divTab3">
					<div id="deitallb_div" style="display:'';height: 430px;" class="lb_div">
						<powersi:datagrid id="accountDeitalInfo_lb" showExportExcel="true" 
							delayLoad="true"  showExportExcel2007="true" statusName="delete"
							exportFileName="'月度零报明细'">
							<powersi:datagrid-column name="rownum" display="序号" hide="true"/>
							<powersi:datagrid-column name="aac003" display="姓名" />
							<powersi:datagrid-column name="aac002"	 display="身份证" 	 />
							<powersi:datagrid-column name="aac001"	 display="个人编码"	 />
							<powersi:datagrid-column name="aac066_name"	 display="参保类别"	 />
							<powersi:datagrid-column name="bka004_name"	 display="人员类别"	 />
							<powersi:datagrid-column name="bka008"	 display="单位名称"	 />
							<powersi:datagrid-column name="aka130_name"	 display="医疗类别"	 />  
							<powersi:datagrid-column name="akb021"	 display="医疗机构名称"	 />
							<powersi:datagrid-column name="begin_date" display="入院日期"	 />
							<powersi:datagrid-column name="fin_date"	 display="出院日期" />
							<powersi:datagrid-column name="zdmc"		 display="诊断名称" /> 
							<powersi:datagrid-column name="ysfz"		 display="原始分值" />
							<powersi:datagrid-column name="cyks"		 display="出院科室" />
							<powersi:datagrid-column name="total_fee"	 display="医疗总费用"	 />
							<powersi:datagrid-column name="self_pay"	 display="自费费用"	 />
							<powersi:datagrid-column name="qfx_pay"	 display="起伏线"	 />
							<powersi:datagrid-column name="jbylf" 	 display="基本医疗费用"	 />  
							<powersi:datagrid-column name="fdzfje"	 display="分段自付金额"	 />
							<powersi:datagrid-column name="medi_acc_pay" display="统筹支付"	 />
							<powersi:datagrid-column name="zdjbbzgfd"	 display="超限额"	 />
							<powersi:datagrid-column name="bcbxze"	 display="补充报销总额	" />  
							<powersi:datagrid-column name="ecbxze"	 display="二次补充报销总额" />
							<powersi:datagrid-column name="sup_acc_pay" display="公务员补助" />
							<powersi:datagrid-column name="self_sum_pay" display="个人支付总额	" />
							<powersi:datagrid-column name="aaa027_name" display="地区	" />
							<powersi:datagrid-column name="aaz217"	 display="住院门诊登记号"	 />  
							<powersi:datagrid-column name="akb020"	 display="定点医疗机构编码	" />
							<powersi:datagrid-column name="bdyd"	 	 display="本地、异地"	 />
							<powersi:datagrid-column name="bka124" 	 display="ICD码" />
							<powersi:datagrid-column name="bka164_name" display="诊治代码" />
							<powersi:datagrid-column name="bka174_a" 	 display="月度计算得分" />
							<powersi:datagrid-column name="bka200" 	 display="年终重算大病单议" />
							<powersi:datagrid-column name="bkb115" 	 display="年终重算上年单价" />
							<powersi:datagrid-column name="bka164" 	 display="年终重算诊治代码" />
							<powersi:datagrid-column name="bka046" 	 display="年终重算参考比例" />
							<powersi:datagrid-column name="bka199" 	 display="年终重算病种分值" />
							<powersi:datagrid-column name="bkb097" 	 display="年终重算医院系数" />
							<powersi:datagrid-column name="bka125" 	 display="年终重算医院系数" />
							<powersi:datagrid-column name="bka047" 	 display="年终重算分值类型" render="renderBka047"/>
							<powersi:datagrid-column name="bka174" 	 display="年终重算得分" />
							<powersi:datagrid-column name="bka280" 	 display="重复住院标志" render="renderbka280"/>
							<powersi:datagrid-column name="bka122" 	 display="审核标志" render="renderBka122"/>
						</powersi:datagrid>
					</div>					
				</div>	
				<div id="divTab4">
					<div id="xt_div" style="display:'';height: 430px;" class="xt_div">
						<powersi:datagrid id="accountDeitalInfo_xt" showExportExcel="true" 
							delayLoad="true"  showExportExcel2007="true" showExportPdf="true"
							exportFileName="'血透汇总表'" >
							<powersi:datagrid-column name="rownum" display="序号" hide="true"/>
							<powersi:datagrid-column name="akb020"	 display="医院编码" />
							<powersi:datagrid-column name="bkb005"	 display="医院名称" />
							<powersi:datagrid-column name="v19"	 	 display="实际就诊人数"	 />
							<powersi:datagrid-column name="v4" 	 display="病种实际医疗总费用" />
							<powersi:datagrid-column name="bka590" display="定额标准" />
							<powersi:datagrid-column name="adejs" 	 display="按定额结算" />
							<powersi:datagrid-column name="cz" 	 display="按定额结算与实际总费用差值（定额-总费用）" />
							<powersi:datagrid-column name="sjrjde" 	 display="实际人均定额" />
							<powersi:datagrid-column name="cjfy" 	 display="次均费用" />
							<powersi:datagrid-column name="rjci" 	 display="人均血透次数" />
						</powersi:datagrid>
					</div>					
				</div>	
			</powersi:tabbedpanel>
			
			
			
		</powersi:panelbox>
	<powersi:errors />
</body>
</html>
<script type="text/javascript">
 $(document).ready(function() {
	verrideGridExport("accountDeitalInfo_zy",accountDeitalInfo_zy.options.exportFileName);
	verrideGridExport("accountDeitalInfo_other",accountDeitalInfo_other.options.exportFileName);
	verrideGridExport("accountDeitalInfo_lb",accountDeitalInfo_lb.options.exportFileName);
	verrideGridExport("accountDeitalInfo_xt",accountDeitalInfo_xt.options.exportFileName);
}); 

	function doQuery() {
		if(!checkFormValidtion())
	  		return;
		var type = $("#type").val();
		var saveItemData = $("#queryForm").serialize();
		postJSON("${rootPath}/queryAccount/AccountManageAction!queryAccountInfo.action",
				saveItemData,function(json) {
			if(!checkJSONResult(json)){
		         return;
		    }
			if(type == "3"){
				$(".zy_div").attr("style","display:'';height: 430px;");
				$(".other_div").attr("style","display:none;height: 430px;");
				$("#Mt_div").attr("style","display:none;height: 430px;");
				$("#Mz_div").attr("style","display:none;height: 430px;");
				$(".lb_div").attr("style","display:'';height: 430px;");
				$("#tab3").attr("style","display:'';");
				$("#tab4").attr("style","display:none;");
				$(".xt_div").attr("style","display:none;height: 430px;");
				accountInfo_zy.loadData(json.data.info);
				accountDeitalInfo_zy.loadData(json.data.detail);
				accountDeitalInfo_lb.loadData(json.data.lbdata);
			}else if(type == "2"){
				$("#Mz_div").attr("style","display:'';height: 430px;");
				$(".other_div").attr("style","display:'';height: 430px;");
				$(".zy_div").attr("style","display:none;height: 430px;");
				$("#Mt_div").attr("style","display:none;height: 430px;");
				$(".lb_div").attr("style","display:none;height: 430px;");
				$("#tab3").attr("style","display:none;");
				$("#tab4").attr("style","display:none;");
				$(".xt_div").attr("style","display:none;");
				accountInfo_mz.loadData(json.data.info)
				accountDeitalInfo_other.loadData(json.data.detail);
			}else{
				$("#Mt_div").attr("style","display:'';height: 430px;");
				$(".other_div").attr("style","display:'';height: 430px;");
				$(".zy_div").attr("style","display:none;height: 430px;");
				$("#Mz_div").attr("style","display:none;height: 430px;");
				$(".lb_div").attr("style","display:none;height: 430px;");
				$("#tab3").attr("style","display:none;");
				$("#tab4").attr("style","display:'';");
				$(".xt_div").attr("style","display:'';height: 430px;");
				accountInfo_mt.loadData(json.data.info)
				accountDeitalInfo_other.loadData(json.data.detail);
				accountDeitalInfo_xt.loadData(json.data.xtdata);
			}
		});
	}
	
	
	var verrideGridExport=function(gridId,exportFileName){
		var filename=exportFileName;
		var excelTxt=$("#"+gridId+" .l-bar-btnexcel").attr("title");
		var excelA="<a onclick=verrideGridExportClick('"+gridId+"','"+filename+".xls','xls')>"+excelTxt+"</a>"
		$("#"+gridId+" .l-bar-btnexcel").after(excelA);
		$("#"+gridId+" .l-bar-btnexcel").remove();
		
		$("#"+gridId+" .l-bar-btnexcel2007").remove();
		$("#"+gridId+" .l-bar-btnpdf").remove();
	}

	var verrideGridExportClick=function(gridId,filename,filetype){
		if(!checkFormValidtion()){return};
		
		var gridexport=[];
		var gridexportJson="";
		$("#"+gridId+" .l-grid-header td[id^='"+gridId+"|hcell']").each(function(i,o){
			var columnname=$(o).attr("columnname");
			var title=$(o).find("span").html();
			if(columnname&&columnname!="undefined"){
				//var json={columnname:title};
				//gridexport.push(json);
				if(gridexportJson==""){
					gridexportJson=gridexportJson+"'"+columnname+"':'"+title+"'";
				}else{
					gridexportJson=gridexportJson+",'"+columnname+"':'"+title+"'";
				}
				
			}
			
		});
		
		var type = $("#type").val()==null?"":$("#type").val();
		var aae140=$("#aae140").val()==null?"":$("#aae140").val();
		var year=$("#year").val()==null?"":$("#year").val();
		gridexportJson="{"+gridexportJson+"}";
		var saveItemData = $("#queryForm").serialize();
	    location.href="${rootPath}/queryAccount/AccountManageAction!printAccountReport.action?"
	    	+"accountDTO.columns="+encodeURI(gridexportJson)+"&accountDTO.type="+type
	    	+"&accountDTO.year="+year+"&accountDTO.aae140="+aae140
	    	+"&accountDTO.filename="+filename+"&accountDTO.gridId="+gridId; 
	    	
	  
	}
	
	
	function changButDis() {
		var type = $("#type option:selected").val();
		if (type == "1") {
			$("#aae140").empty();
			$("#aae140").append(
					"<option value='391' checked >" + "居民" + "</option>");
			$("#aae140")
					.append("<option value='310' >" + "职工" + "</option>");
		}else{
			$("#aae140").empty();
		}
	}
	
	function renderBka122(rowdata,index,value){
		if(value=="9"){
			return "审核不通过";
		}else{
			return "审核通过";
		}
	}
	
	function renderbka280(rowdata,index,value){
		if(value=="1"){
			return "7天重复住院不计算分值";
		}else if(value=="2"){
			return "15天重复住院 ";
		}else{
			return "正常住院";
		}
	}
	
	function renderBka047(rowdata,index,value){
		if(value=="0")
			return "零报得分"
			else if(value=="1")
				return "常见病种得分"
				else if(value=="2")
					return "40%以下得分"
					else if(value=="3")
						return "250%以上得分"
						else if(value=="4")
							return "非常见病种得分"
							else
								return value;
	}
	
	 function checkRow(rowdata, rowid, rowobj){
	 	var aa=	rowdata['aae140'];	
	 	$("#aae140_name").val(aa); 
	 	var type = $("#type option:selected").val();
		var saveItemData = $("#queryForm").serialize();
		postJSON("${rootPath}/queryAccount/AccountManageAction!queryAccountInfo.action",
				saveItemData,function(json) {
			if(!checkJSONResult(json)){
		         return;
		    }
			
			$("#aae140_name").val(""); 
			if(type == "3"){
				$(".zy_div").attr("style","display:'';height: 430px;");
				$(".other_div").attr("style","display:none;height: 430px;");
				$("#Mt_div").attr("style","display:none;height: 430px;");
				$("#Mz_div").attr("style","display:none;height: 430px;");
				$(".lb_div").attr("style","display:'';height: 430px;");
				$("#tab3").attr("style","display:'';");
								
			//	accountInfo_zy.loadData(json.data.info);
				accountDeitalInfo_zy.loadData(json.data.detail);
				accountDeitalInfo_lb.loadData(json.data.lbdata);
				
			}
		});
	} 
</script>