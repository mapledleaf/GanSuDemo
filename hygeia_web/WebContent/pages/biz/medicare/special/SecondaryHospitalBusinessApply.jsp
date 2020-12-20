<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%
	String path = request.getContextPath();
%>


<powersi:html>
<body>
	<powersi:head title="二次返院业务申请"/>
	<powersi:panelbox title="查询条件">
		<powersi:editorlayout cols="6">
			<tr>
				<powersi:textfield label="个人电脑号" id="aac001" name="dto.aac001" key="aac.001" required="true"/>
				<td colspan="2" align="center"><powersi:button
						id="button_query" key="button_query" onclick="queryPresonInfo()" />
				</td>
				
			</tr>
		</powersi:editorlayout>
	</powersi:panelbox>
	
	<powersi:panelbox title="人员信息">
		<powersi:editorlayout cols = "6">
			<tr>
				<powersi:textfield id="aac003" name="dto.aac003" key="aac003" readonly="true"/>
				<powersi:textfield label="性别" id="aac004" name="dto.aac004" key="aac004" readonly="true"/>
		
				<powersi:textfield label="人员类别" id="aac012" name="aac012" key="aac012" readonly="true" colspan="2"/>
			</tr>
		
			<tr>
				<powersi:textfield label="身份证号" id="aac002" name="dto.aac002" key="aac002" readonly="true"/>
				<powersi:textfield label="出生日期" id="aac006" name="dto.aac006" key="aac006" readonly="true"/>
				<powersi:textfield label="工作日期" id="aac007" name="dto.aac007" key="aac007" readonly="true"/>
			</tr>
			<tr>
				<powersi:textfield label="所属单位" id="aab069" name="dto.aab069" key="aab069" readonly="true" colspan="3"/>
				<td colspan="3"></td>
			</tr>
			</powersi:editorlayout>
	</powersi:panelbox>
	
	<powersi:panelbox title="详细信息">
		<powersi:editorlayout cols="6">
			<tr>
				<powersi:textfield label="申请医院" id="akb021" name="dto.akb021" key="akb021" readonly="true" colspan="3"/>
				<td colspan="3"></td>
			</tr>
			<tr>
				<powersi:textfield label="科室" id="pka020" name="dto.pka020" key="pka020" readonly="true" />
				<powersi:textfield label="床位号" id="pka023" name="dto.pka023" key="pka023" readonly="true"/>
				<powersi:textfield label="住院号" id="pka025" name="dto.pka025" key="pka025" readonly="true"/>
			</tr>
			<tr>
				<powersi:textfield label="本次入院时间" id="pka017" name="dto.pka017" key="pka017" readonly="true"/>
				<powersi:textfield label="上次出院时间" id="pka032" name="dto.pka032" key="pka032" readonly="true"/>
				<powersi:textfield label="上次入院诊断" id="aka121" name="dto.aka121" key="aka121" readonly="true"/>
			</tr>
			<tr>
				<powersi:textarea label="病情摘要" id="pke009" name="dto.pke009" key="pke009" required="true" rows="3" colspan="6" />
			</tr>
			<tr>
				<powersi:textarea label="医务科意见" id="pke008" name="dto.pke008" key="pke008" required="true" rows="2" colspan="6"/>
			</tr>
			<tr>
				<powersi:textfield label="申请医师" id="pke017" name="dto.pke017" key="pke017" required="true"/>
				<powersi:textfield label="科主任" id="pke018" name="dto.pke018" key="pke017" required="true"/>
				<powersi:textfield label="医院申批人" id="pke036" name="dto.pke036" key="pke036" required="true"/>
			</tr>
			<tr>
				<powersi:textfield label="医院审批时间" mask="date" format="dateFmt:yyyy-MM-dd" id="pke047" name="dto.pke047" key="pke047" required="true"/>
				<powersi:textfield label="录入人" id="aae011" name="dto.aae011" key="aae011" readonly="true"/>
				<powersi:textfield label="录入时间" id="aae127" name="aae127" key="aae127" readonly="true" />
			</tr>
			<tr>
				<powersi:textarea label="备注" id="aae013" name="dto.aae013" key="aae013" readonly="false" rows="3" colspan="6"/>
			</tr>
		</powersi:editorlayout>
		
		<div class="div_center">
			<powersi:button key="button_save" onclick="saveInfo()"></powersi:button>
			<powersi:button cssClass="button" key="button_reset" onclick="clearall()"></powersi:button>
		</div>
	</powersi:panelbox>
	<script type="text/javascript">
	
	function queryPresonInfo(){
		var aac001 = $("#aac001").val();
		   if(aac001 == null || aac001 ==""){
			   popupAlert("请输入查询条件!");
			   return;
		   }else{
			   document.getElementById("aac001").value = $("#aac001").val();
			   document.getElementById("aac002").value = $("#aac002").val();
			   document.getElementById("aac003").value = $("#aac003").val();
			   document.getElementById("aac004").value = $("#aac004").val();
			   document.getElementById("aac006").value = $("#aac006").val();
			   document.getElementById("aac007").value = $("#aac007").val();
			   document.getElementById("aac012").value = $("#aac012").val();
			   document.getElementById("aab069").vlaue = $("#aab069").val();
		   }
		   postJSON("<%=path%>/medicare/specialApplyAction!queryMediPersonInfo.action",{"mediSpecDto.aac001":$("#aac001").val()},function(json){
			   if(!checkJSONResult(json)){
				   return;
			   }			   
				if (json.errortype==0) {
					$.each(json.data,function(key,value){
					    $("#"+key).val(value);
					   
					});
				}else{
					popupInfo(json.message);
					return;
				}
		   });
	}
</script>
	</body>
</powersi:html>
