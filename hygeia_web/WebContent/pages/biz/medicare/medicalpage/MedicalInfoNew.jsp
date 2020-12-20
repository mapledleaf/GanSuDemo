<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	//List<Map> diagnoseList = (List<Map>) request.getAttribute("diagnoseList");
%>
<powersi:html>
<head>
<powersi:head title="病案首页信息录入" target="_self" />
</head>
<body>
	<powersi:form id="mainForm" disabled="true">
		<powersi:panelbox key="panel_query" title="基础信息">
			<powersi:panelbox-toolbar>
				<%-- <powersi:button id="btSave" label="保 存" onclick="save()" buttonIcon="icon-save"/> --%>
				<powersi:button id="btClose" label="取 消" buttonIcon="icon-remove" onclick="javascript:closeDialog();" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="10">
				<powersi:editorlayout-row >
					<powersi:codeselect id="caa027" label="中心系统" codeType="caa027"
						name="medicalBasisDto.caa027"  headerKey="0" />
					<powersi:codeselect id="aaa027" name="medicalBasisDto.aaa027" label="统筹区"
						 codeType="aaa027" displayonly="true"/>
					<powersi:textfield id="akb021" name="medicalBasisDto.akb021" label="医院名称"   readonly="true" />
					<powersi:textfield id="aaz217" name="medicalBasisDto.aaz217" label="就医登记号" readonly="true" />
					<powersi:textfield id="bkm930" name="medicalBasisDto.bkm930" label="病案号"   />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:tabbedpanel id="divTabs">
			<powersi:tab id="tab1" target="divTab1" label="病人信息" />
			<powersi:tab id="tab2" target="divTab2" label="费用信息" />
			<powersi:tab id="tab3" target="divTab3" label="诊断信息" />
			<powersi:tab id="tab4" target="divTab4" label="手术信息" />
			<powersi:tab id="tab5" target="divTab5" label="产科分娩婴儿记录" />
			<div id="divTab1">
				<powersi:editorlayout cols="10">
					<powersi:editorlayout-button colspan="10">
						<powersi:button id="btDel"  label="重 置" onclick="" buttonIcon="icon-remove"  />
						<powersi:button id="btSave" label="保 存" onclick="saveBasisInfo()" buttonIcon="icon-save"/>
					</powersi:editorlayout-button>
					<powersi:editorlayout-row>
						<powersi:textfield id="aac003" name="medicalBasisDto.aac003" label="姓名" />
						<powersi:codeselect id="aac004" name="medicalBasisDto.aac004" label="性别"
							codeType="aac004" displayonly="true"/>
						<powersi:textfield id="bkm902" name="medicalBasisDto.bkm902" label="年龄"  />
						<powersi:textfield id="aac006" name="medicalBasisDto.aac006" label="出生日期" />
						<powersi:textfield id="bkm903" name="medicalBasisDto.bkm903" label="医疗付费方式" />
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="bkm904" name="medicalBasisDto.bkm904" label="国籍" />
						<powersi:textfield id="aac005" name="medicalBasisDto.aac005" label="民族" />
						<powersi:textfield id="bkm317" name="medicalBasisDto.bkm317" label="出生地"  />
						<powersi:textfield id="aac010" name="medicalBasisDto.aac010" label="籍贯" />
						<powersi:textfield id="aac002" name="medicalBasisDto.aac002" label="身份证号" />
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="bkm371" name="medicalBasisDto.bkm371" label="血型"  />
						<powersi:textfield id="bkm372" name="medicalBasisDto.bkm372" label="ＲＨ" />
						<powersi:textfield id="aac017" name="medicalBasisDto.aac017" label="婚姻情况 " />
						<powersi:textfield id="bkm905" name="medicalBasisDto.bkm905" label="新生儿出生体重克 " />
						<powersi:textfield id="bkm906" name="medicalBasisDto.bkm906" label="新生儿入院体重克 " />
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="bkm907" name="medicalBasisDto.bkm907" label="现住址"  />
						<powersi:textfield id="bkm908" name="medicalBasisDto.bkm908" label="电话" />
						<powersi:textfield id="bkm909" name="medicalBasisDto.bkm909" label="住址邮编" />
						<powersi:textfield id="aac010" name="medicalBasisDto.aac010" label="户口地址" />
						<powersi:textfield id="aab401" name="medicalBasisDto.aab401" label="户口地邮编" />
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="aab069" name="medicalBasisDto.aab069" label="单位名称"  />
						<powersi:textfield id="bkm910" name="medicalBasisDto.bkm910" label="单位地址" />
						<powersi:textfield id="bkm911" name="medicalBasisDto.bkm911" label="单位电话" />
						<powersi:textfield id="aab001" name="medicalBasisDto.aab001" label="单位邮编" />
						<powersi:textfield id="bkm316" name="medicalBasisDto.bkm316" label="职业 " />
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="aae004" name="medicalBasisDto.aae004" label="联系人姓名"  />
						<powersi:textfield id="bkm319" name="medicalBasisDto.bkm319" label="关系" />
						<powersi:textfield id="aae006" name="medicalBasisDto.aae006" label="联系人地址" />
						<powersi:textfield id="aae005" name="medicalBasisDto.aae005" label="联系人电话" />
						<powersi:textfield id="bkm931" name="medicalBasisDto.bkm931" label="健康卡号" />
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="bkm912" name="medicalBasisDto.bkm912" label="住院次数" />
						<powersi:textfield id="bke552" name="medicalBasisDto.bke552" label="入院途径" />
						<powersi:textfield id="aae030" name="medicalBasisDto.aae030" label="入院日期"  />
						<powersi:textfield id="akf001" name="medicalBasisDto.akf001" label="入院科别" />
						<powersi:textfield id="bka021" name="medicalBasisDto.bka021" label="入院病室" />
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="aae031" name="medicalBasisDto.aae031" label="出院日期 " />
						<powersi:textfield id="bkm314" name="medicalBasisDto.bkm314" label="出院科别"  />
						<powersi:textfield id="bkm315" name="medicalBasisDto.bkm315" label="出院病室" />
						<powersi:textfield id="bkm913" name="medicalBasisDto.bkm913" label="实际住院天 " />
						<powersi:textfield id="" name="" label="转科" />
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="bkm363" name="medicalBasisDto.bkm363" label="门(急)诊诊断" />
						<powersi:textfield id="bkm914" name="medicalBasisDto.bkm914" label="疾病编码 " />
						<powersi:textfield id="bke550" name="medicalBasisDto.bke550" label="病例分型" />
						<powersi:textfield id="bkm349" name="medicalBasisDto.bkm349" label="抢救次数 " />
						<powersi:textfield id="bkm350" name="medicalBasisDto.bkm350" label="抢救成功次数 " />
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="bkm915" name="medicalBasisDto." label="病理号"  />
						<powersi:textfield id="bkm337" name="medicalBasisDto.bkm337" label="病理诊断 " />
						<powersi:textfield id="bkm339" name="medicalBasisDto.bkm339" label="药物过敏" />
						<powersi:textfield id="bkm340" name="medicalBasisDto.bkm340" label="过敏药物" />
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="bkm916" name="medicalBasisDto.bkm916" label="离院方式 " />
						<powersi:textfield id="bkm917" name="medicalBasisDto.bkm917" label="是否有出院30天内再住院计划"  />
						<powersi:textfield id="bkm918" name="medicalBasisDto.bkm918" label="颅脑损伤患者昏迷时间 入院前" />
						<powersi:textfield id="bkm919" name="medicalBasisDto.bkm919" label="颅脑损伤患者昏迷时间 入院后" />
						<powersi:textfield id="bkm366" name="medicalBasisDto.bkm366" label="死亡患者尸检 " />
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="bkm351" name="medicalBasisDto.bkm351" label="科主任" />
						<powersi:textfield id="bkm352" name="medicalBasisDto.bkm352" label="(副)主任医师" />
						<powersi:textfield id="bkm353" name="medicalBasisDto.bkm353" label="主治医师" />
						<powersi:textfield id="bkm354" name="medicalBasisDto.bkm354" label="住院医师"  />
						<powersi:textfield id="bkm920" name="medicalBasisDto.bkm920" label="责任护士" />
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="bkm355" name="medicalBasisDto.bkm355" label="进修医师" />
						<powersi:textfield id="bkm357" name="medicalBasisDto.bkm357" label="实习医师" />
						<powersi:textfield id="bkm921" name="medicalBasisDto.bkm921" label="编码员" />
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="bkm360" name="medicalBasisDto.bkm360" label="病案质量"  />
						<powersi:textfield id="bkm358" name="medicalBasisDto.bkm358" label="质控医师" />
						<powersi:textfield id="bkm359" name="medicalBasisDto.bkm359" label="质控护士" />
						<powersi:textfield id="bkm922" name="medicalBasisDto.bkm922" label="质控日期" />
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:hidden id="akb020" name="medicalBasisDto.akb020" />
						<powersi:hidden id="aae100" name="medicalBasisDto.aae100" value="1"/>
					</powersi:editorlayout-row>
				</powersi:editorlayout>
			</div>
			<div id="divTab2">
				<powersi:editorlayout cols="10">
					<powersi:editorlayout-button colspan="10">
						<powersi:button id="btDel"  label="重 置" onclick="" buttonIcon="icon-remove"  />
						<powersi:button id="btSave" label="保 存" onclick="saveFeeInfo()" buttonIcon="icon-save"/>
					</powersi:editorlayout-button>
					<powersi:editorlayout-row>
						<powersi:textfield id="bkm802" name="km03Dto.bkm802" label="总费用: " />
						<powersi:textfield id="bkm803" name="km03Dto.bkm803" label="自付金额： " />
						<powersi:textfield id="bkm804" name="km03Dto.bkm804" label="一般医疗服务费: "  />
						<powersi:textfield id="bkm805" name="km03Dto.bkm805" label="一般治疗操作费" />
						<powersi:textfield id="bkm806" name="km03Dto.bkm806" label="护理费: " />
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="bkm807" name="km03Dto.bkm807" label="病理诊断费 " />
						<powersi:textfield id="bkm808" name="km03Dto.bkm808" label="实验室诊断费 " />
						<powersi:textfield id="bkm809" name="km03Dto.bkm809" label="影像学诊断费 "  />
						<powersi:textfield id="bkm810" name="km03Dto.bkm810" label="临床诊断项目费 " />
						<powersi:textfield id="bkm811" name="km03Dto.bkm811" label="非手术治疗项目费" />
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="bkm812" name="km03Dto.bkm812" label="临床物理治疗费" />
						<powersi:textfield id="bkm813" name="km03Dto.bkm813" label="手术治疗费" />
						<powersi:textfield id="bkm814" name="km03Dto.bkm814" label="麻醉费 "  />
						<powersi:textfield id="bkm815" name="km03Dto.bkm815" label="手术费" />
						<powersi:textfield id="bkm816" name="km03Dto.bkm816" label="康复费" />
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="bkm817" name="km03Dto.bkm817" label="中医治疗费" />
						<powersi:textfield id="bkm818" name="km03Dto.bkm818" label="西药费 " />
						<powersi:textfield id="bkm819" name="km03Dto.bkm819" label="抗菌药物费用"  />
						<powersi:textfield id="bkm820" name="km03Dto.bkm820" label="中成药费" />
						<powersi:textfield id="bkm821" name="km03Dto.bkm821" label="中草药费" />
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="bkm822" name="km03Dto.bkm822" label="血费" />
						<powersi:textfield id="bkm823" name="km03Dto.bkm823" label="血蛋白类制品费" />
						<powersi:textfield id="bkm824" name="km03Dto.bkm824" label="球蛋白类制品费"  />
						<powersi:textfield id="bkm825" name="km03Dto.bkm825" label="凝血因子类制品费" />
						<powersi:textfield id="bkm826" name="km03Dto.bkm826" label="细胞因子类制品费" />
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="bkm827" name="km03Dto.bkm827" label="检查用一次性医用材料费" />
						<powersi:textfield id="bkm828" name="km03Dto.bkm828" label="治疗用一次性医用材料费" />
						<powersi:textfield id="bkm829" name="km03Dto.bkm829" label="手术用一次性医用材料费"  />
						<powersi:textfield id="bkm830" name="km03Dto.bkm830" label="其他费" />
					</powersi:editorlayout-row>
				</powersi:editorlayout>
			</div>
			<div id="divTab3">
				<powersi:editorlayout cols="10">
					<powersi:editorlayout-button colspan="10">
						<powersi:button id="btOk"   label="确 定" onclick="addDiagnose()" buttonIcon="icon-add"/>
						<powersi:button id="btDel"  label="删 除" onclick="javascript:closeDialog();" buttonIcon="icon-remove"  />
						<powersi:button id="btSave" label="保 存" onclick="saveDiagnoseInfo()" buttonIcon="icon-save"/>
					</powersi:editorlayout-button>
					<powersi:editorlayout-row>
						<powersi:textfield id="bke558" name="kcg4Dto.bke558" label="诊断序号" required="true"/>
						<powersi:hidden id="aka120"/>
						<powersi:textfield id="aka121"
							name="kcg4Dto.aka121" label="疾病名称" title="请输入疾病名称"
							onkeydown="keydown(this)" required="true"
							readonly="true" buttonText="选择" buttonId="aka120_button"
							buttonDisabled="false" onbuttonclick="chooseDisease('aka121', 'aka120', 'bkm801')" />
						<powersi:codeselect id="bkm801" name="kcg4Dto.bkm801"  label="入院病情"
						    list="#{'':'请选择...','01':'有','02':'临床不确定','03':'情况不明','04':'无'}" />
					</powersi:editorlayout-row>
				</powersi:editorlayout>
				<powersi:datagrid id="diagnoseGrid" >
					<powersi:datagrid-column name="bke558" label="诊断序号" width="75"  />
					<powersi:datagrid-column name="aka121" label="疾病名称" width="40%" />
					<powersi:datagrid-column name="aka120" label="疾病编码" width="40%" />
					<powersi:datagrid-column name="bkm801" label="入院病情" width="10%" render="renderBkm801"/>
				</powersi:datagrid>
			</div>
			<div id="divTab4">
				<powersi:editorlayout cols="8">
					<powersi:editorlayout-button colspan="8">
						<powersi:button id="btOk"   label="确 定"	onclick="addOperation()" buttonIcon="icon-save"/>
						<powersi:button id="btDel"  label="删 除"	onclick="javascript:closeDialog();" buttonIcon="icon-remove"  />
						<powersi:button id="btSave" label="保 存"    onclick="saveOperationInfo()" buttonIcon="icon-save"/>
					</powersi:editorlayout-button>
					<powersi:editorlayout-row>
						<powersi:textfield id="bkm306" name="kcg5Dto.bkm306" label="手术操作编码" required="true"/>
						<powersi:textfield id="bkm311" name="kcg5Dto.bkm311" label="手术操作名称" required="true"/>
						<powersi:textfield id="bkm304" name="kcg5Dto.bkm304" label="手术级别"  />
						<powersi:textfield id="bkm307" name="kcg5Dto.bkm307" label="手术操作日期" 
							mask="date" format="dateFmt:'yyyy-MM-dd'" />
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="bkm813" name="kcg5Dto.bkm813" label="切口/愈合"  />
						<powersi:textfield id="bkm308" name="kcg5Dto.bkm308" label="术者"  />
						<powersi:textfield id="bkm309" name="kcg5Dto.bkm309" label="I助"  />
						<powersi:textfield id="bkm310" name="kcg5Dto.bkm310" label="II助"  />
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="bkm305" name="kcg5Dto.bkm305" label="麻醉方式 	"  />
						<powersi:textfield id="bkm312" name="kcg5Dto.bkm312" label="麻醉医生"  />
					</powersi:editorlayout-row>
				</powersi:editorlayout>
				<powersi:datagrid id="operationGrid" >
					<powersi:datagrid-column name="bkm311" label="手术操作名称" width="10%" />
					<powersi:datagrid-column name="bkm306" label="手术操作编码" width="10%" />
					<powersi:datagrid-column name="bkm304" label="手术级别" width="10%" />
					<powersi:datagrid-column name="bkm307" label="手术操作日期" width="10%" />
					<powersi:datagrid-column name="bkm813" label="切口/愈合" width="10%" />
					<powersi:datagrid-column name="bkm308" label="术者" width="10%" />
					<powersi:datagrid-column name="bkm309" label="I助" width="10%" />
					<powersi:datagrid-column name="bkm310" label="II助" width="10%" />
					<powersi:datagrid-column name="bkm305" label="麻醉方式" width="10%" />
					<powersi:datagrid-column name="bkm312" label="麻醉医生" width="10%" />
				</powersi:datagrid>
			</div>
			<div id="divTab5">
				<powersi:editorlayout cols="8">
					<powersi:editorlayout-button colspan="8">
						<powersi:button id="btOk"   label="确 定"	onclick="addBaby()" buttonIcon="icon-save"/>
						<powersi:button id="btDel"  label="删 除"	onclick="javascript:closeDialog();" buttonIcon="icon-remove"  />
						<powersi:button id="btSave" label="保 存" 	onclick="saveBabyInfo()" buttonIcon="icon-save"/>
					</powersi:editorlayout-button>
					<powersi:editorlayout-row>
						<powersi:textfield id="bkm865" name="km04Dto.bkm865" label="婴儿序号" required="true"/>
						<powersi:codeselect id="bkm866" name="km04Dto.bkm866" label="性别"
							codeType="aac004"/>
						<powersi:textfield id="bkm867" name="km04Dto.bkm867" label="婴儿体重(g)" validate="number"/>
						<powersi:codeselect id="bkm868" name="km04Dto.bkm868" label="婴儿转归" 
							list="#{'01':'出院','02':'转科','03':'死亡 ' }"/>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:codeselect id="bkm869" name="km04Dto.bkm869" label="呼吸" 
							list="#{'01':'自然','02':'I度窒息','03':'II度窒息 ' }"/>
						<powersi:codeselect id="bkm872" name="km04Dto.bkm872" label="分娩结果" 
							list="#{'01':'活产','02':'死产','03':'死胎' }"/>
						<powersi:textfield id="bkm870" name="km04Dto.bkm870" label="抢救次数" validate="number"/>
						<powersi:textfield id="bkm871" name="km04Dto.bkm871" label="抢救成功次数" validate="number"/>
					</powersi:editorlayout-row>
				</powersi:editorlayout>
				<powersi:datagrid id="babyGrid" >
					<powersi:datagrid-column name="bkm865" label="婴儿序号" width="75" />
					<powersi:datagrid-column name="bkm866" label="性别" code="aac004" width="10%" />
					<powersi:datagrid-column name="bkm867" label="婴儿体重（g）" width="10%" />
					<powersi:datagrid-column name="bkm869" label="呼吸" 	   render="renderBkm869" width="10%" />
					<powersi:datagrid-column name="bkm872" label="分娩结果" render="renderBkm872" width="10%" />
					<powersi:datagrid-column name="bkm868" label="婴儿转归" render="renderBkm868" width="10%" />
					<powersi:datagrid-column name="bkm870" label="抢救次数" width="10%" />
					<powersi:datagrid-column name="bkm871" label="抢救成功次数" width="10%" />
				</powersi:datagrid>
			</div>
		</powersi:tabbedpanel>
		<powersi:hidden id="diagnoseinfo" 	name="diagnoseinfo" />
		<powersi:hidden id="operationinfo"  name="operationinfo" />
		<powersi:hidden id="babyinfo" 		name="babyinfo" />
	</powersi:form>
	<powersi:errors />
</body>
</powersi:html>
<script type="text/javascript" src="${rootPath}/resource/js/clinicUtils.js"></script>
<script type="text/javascript">
	$(function(){
		<%-- alert(<%=diagnoseList%>) --%>
	});
	
	function saveBasisInfo(){
		var saveItemData = $("#mainForm").serialize();
		postJSON("${rootPath}/medicalpage/MedicalPageAction!saveBasisInfo.action",
				saveItemData, function(json) {
					if (!checkJSONResultNew(json,false)) 
						return;
					alert(json.data);
				});
	}
	
	function saveFeeInfo(){
		var saveItemData = $("#mainForm").serialize();
		postJSON("${rootPath}/medicalpage/MedicalPageAction!saveFeeInfo.action",
				saveItemData, function(json) {
					if (!checkJSONResultNew(json,false)) 
						return;
					alert(json.data);
				});
	}
	
	function saveDiagnoseInfo(){
		var diagnoseInfo = diagnoseGrid.getAdded();
		diagnoseInfo = powersi.tostring(diagnoseInfo);
		if(diagnoseInfo=="[]"){
			alert("未录入诊断信息!");
			return;
		}
		$("#diagnoseinfo").val(encodeURI(diagnoseInfo));
		var saveItemData = $("#mainForm").serialize();
		postJSON("${rootPath}/medicalpage/MedicalPageAction!saveDiagnoseInfo.action",
				saveItemData, function(json) {
					if (!checkJSONResultNew(json,false)) 
						return;
					alert(json.data);
				});
	}
	
	function saveOperationInfo(){
		var operationInfo = operationGrid.getAdded();
		operationInfo = powersi.tostring(operationInfo);
		if(operationInfo=="[]"){
			alert("未录入手术信息!");
			return;
		}
		$("#operationinfo").val(encodeURI(operationInfo));
		var saveItemData = $("#mainForm").serialize();
		postJSON("${rootPath}/medicalpage/MedicalPageAction!saveOperationInfo.action",
				saveItemData, function(json) {
					if (!checkJSONResultNew(json,false)) 
						return;
					alert(json.data);
				});
	}
	
	function saveBabyInfo(){
		var babyInfo = babyGrid.getAdded();
		babyInfo = powersi.tostring(babyInfo);
		if(babyInfo=="[]"){
			alert("未录入产科婴儿分娩信息!");
			return;
		}
		$("#babyinfo").val(encodeURI(babyInfo));
		var saveItemData = $("#mainForm").serialize();
		postJSON("${rootPath}/medicalpage/MedicalPageAction!saveBabyInfo.action",
				saveItemData, function(json) {
					if (!checkJSONResultNew(json,false)) 
						return;
					alert(json.data);
				});
	}
	
	function addDiagnose(){
		var bke558 = $("#bke558").val().trim();
		var aka121 = $("#aka121").val().trim();
		var aka120 = $("#aka120").val().trim();
		var bkm801 = $("#bkm801").val().trim();
		if(aka120==null || aka120 == ""){
			alert("未录入诊断信息!");
			return;
		}
		var jsonDiagnose = {
				"bke558" : bke558,
				"aka121" : aka121,
				"aka120" : aka120,
				"bkm801" : bkm801
		}
		diagnoseGrid.add(jsonDiagnose);
		$("#bke558").val("");
		$("#aka121").val("");
		$("#aka120").val("");
		$("#bkm801").val("");
	}
	
 	function addOperation(){
 		var bkm307 = $("#bkm307").val().trim();
 		var bkm311 = $("#bkm311").val().trim();
 		var bkm306 = $("#bkm306").val().trim();
 		var bkm304 = $("#bkm304").val().trim();
 		var bkm813 = $("#bkm813").val().trim();
 		var bkm308 = $("#bkm308").val().trim();
 		var bkm309 = $("#bkm309").val().trim();
 		var bkm310 = $("#bkm310").val().trim();
 		var bkm305 = $("#bkm305").val().trim();
 		var bkm312 = $("#bkm312").val().trim();
 		if(bkm306=="" || bkm311==""){
 			alert("未录入手术信息！");
 			return;
 		}
 		var jsonOperation = {
 				"bkm307" : bkm307,
 				"bkm311" : bkm311,
 				"bkm306" : bkm306,
 				"bkm304" : bkm304,
 				"bkm813" : bkm813,
 				"bkm308" : bkm308,
 				"bkm309" : bkm309,
 				"bkm310" : bkm310,
 				"bkm305" : bkm305,
 				"bkm312" : bkm312
 		}
 		operationGrid.add(jsonOperation);
 		$("#bkm307").val("");
 		$("#bkm311").val("");
 		$("#bkm306").val("");
 		$("#bkm304").val("");
 		$("#bkm813").val("");
 		$("#bkm308").val("");
 		$("#bkm309").val("");
 		$("#bkm310").val("");
 		$("#bkm305").val("");
 		$("#bkm312").val("");
 	}
 	
 	function addBaby(){
 		var bkm865 = $("#bkm865").val();
 		var bkm866 = $("#bkm866").val();
 		var bkm872 = $("#bkm872").val();
 		var bkm867 = $("#bkm867").val();
 		var bkm868 = $("#bkm868").val();
 		var bkm869 = $("#bkm869").val();
 		var bkm870 = $("#bkm870").val();
 		var bkm871 = $("#bkm871").val();
 		if(bkm865==""){
 			alert("未录入产科婴儿分娩信息！");
 			return;
 		}
 		var jsonBaby = {
 				"bkm865" : bkm865,
 				"bkm866" : bkm866,
 				"bkm872" : bkm872,
 				"bkm867" : bkm867,
 				"bkm868" : bkm868,
 				"bkm869" : bkm869,
 				"bkm870" : bkm870,
 				"bkm871" : bkm871
 		}
 		babyGrid.add(jsonBaby);
 		$("#bkm865").val("");
 		$("#bkm866").val("");
 		$("#bkm872").val("01");
 		$("#bkm867").val("");
 		$("#bkm868").val("01");
 		$("#bkm869").val("01");
 		$("#bkm870").val("");
 		$("#bkm871").val("");
 	}
 	
 	function renderBkm801(rowdata, index, value){
 		if("01"==value)
			return "有";
		else if("02"==value)
			return "临床不确定";
		else if("03"==value)
			return "情况不明";
		else
			return "无";
 	}
 	
 	function renderBkm872(rowdata, index, value){
 		if("02"==value)
			return "死产";
		else if("03"==value)
			return "死胎";
		else
			return "活产";
 	}
 	
 	function renderBkm869(rowdata, index, value){
 			if("02"==value)
 				return "I度窒息";
 			else if("03"==value)
 				return "II度窒息";
 			else
 				return "自然";
 	}
 	
 	function renderBkm868(rowdata, index, value){
 			if("02"==value)
 				return "转科";
 			else if("03"==value)
 				return "死亡";
 			else
 				return "出院";
 	}
</script>