<%@ tag language="java" pageEncoding="UTF-8" 
display-name="生育待遇资格登记信息" description="生育待遇资格登记信息" %>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="struts" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%
	String path = request.getContextPath();
%>
<powersi:editorlayout cols="8">
	<tr>	
	<powersi:textfield id="amc019" name="trDTO.amc019" label="末次月经时间" mask="date" required="true"  />
		<powersi:textfield id="amc021" name="trDTO.amc021"  key="amc021" required="true" onclick="changeAmc021()"/>
		<%-- <powersi:codeselect codeType="amc027" id="amc027" name="trDTO.amc027" key="amc027"   />--%>
 		<%-- <powersi:textfield id="akb021"  label="医院名称" name="trDTO.akb021" buttonIcon="icon-ellipsis-horizontal" 
 			readonly="true" onkeydown="dealDown(this)" buttonId="hospse" onbuttonclick="chooseHosp()" /> --%>
		<!-- <mediTag:ChooseHosp  buttonDisplay="1"  required="false"   colspan="2" displayLabel="医院名称" 
	  			hospFilter="0" empower="2"/>-->
	  	<powersi:textfield id="akb021" name="trDTO.akb021"
						label="医院名称" readonly="true" />
		<powersi:textfield id="bmc013" name="trDTO.bmc013" label="预产期"  mask="date"  required="true" />
	</tr>
	<tr>
		<powersi:textfield id="bmc025" name="trDTO.bmc025" label="委托人姓名"  />
		<powersi:textfield id="bmc026" name="trDTO.bmc026" label="委托人身份证"  />
		<powersi:textfield id="bmc027" name="trDTO.bmc027" label="委托人单位"  />
		<powersi:textfield id="bmc028" name="trDTO.bmc028" label="委托人联系电话"  />
	</tr>
	<tr>
		<powersi:textfield id="amc010" name="trDTO.amc010" label="配偶姓名" required="true" />
		<powersi:textfield id="bmc011" name="trDTO.bmc011" label="配偶身份证号码" required="true"/>
		<powersi:textfield id="bmc012" name="trDTO.bmc012" label="配偶单位/住址" />
		<powersi:textfield id="ylks" name="trDTO.ylks" label="医疗开始时间"  mask="date" value="%{trDTO.ylks}" required="true" />
	</tr>
	<tr>
		<powersi:textfield id="aae005" name="trDTO.aae005" label="联系电话"  />
		<powersi:textfield id="bmc024" name="trDTO.bmc024" label="发证机关"  />
	</tr>
	
	
	<%--
	 <tr id="a" style="display:none">
	         <powersi:codeselect name="taDTO.amc031" id="amc031"
			 codeType="amc031" key="amc031"></powersi:codeselect>
		<td></td><td></td><td></td><td></td>
	</tr>
	--%>
</powersi:editorlayout>