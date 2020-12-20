<%@tag import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%@ tag pageEncoding="GBK" body-content="empty" small-icon=""
	display-name="参保人信息(点击展开可查看)" description="参保人信息(点击展开可查看)"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<style>
.imgdic {
	height: 100px !important;
	width: 100px !important;
	border: 1px solid #d5e4f1;
	max-width: 100%; 
	max-height: 100%;
}
</style>
<script type="text/javascript">
</script>
<powersi:panelbox title="参保人信息(点击展开可查看)" isCollapse="true">
	<powersi:editorlayout cols="13%,13%,5%,13%,8%,13%,8%,13%">
		<tr>
			<td rowspan="4" colspan="2" align="center" style="border: 1px solid #d5e4f1;" >
				<div style="border: 1px solid #d5e4f1; width: 100px; height: 100px; max-width: 100%; max-height: 100%;">
					<img id="dlrimg" 
						style="max-height : 100%;max-width: 100%;width: auto; height:300px;">
					<powersi:hidden id="bkc290" name="diagnoseInfoDTO.bkc290" />
				</div>
			</td>
			<powersi:codeselect id="bka035" key="人员类别"
				name="diagnoseInfoDTO.bka035_name" codeType="bka035" disabled="true"/>
		    <powersi:hidden id="bka035" name="diagnoseInfoDTO.bka035" />
			<powersi:codeselect id="aac004" key="性别"
				name="diagnoseInfoDTO.aac004_name" codeType="aac004" disabled="true" />
			 <powersi:hidden id="aac004" name="diagnoseInfoDTO.aac004" />
			<powersi:textfield id="aac006" key="出生日期"
				name="diagnoseInfoDTO.aac006" readonly="true" />
		</tr>
		<tr>
			<powersi:textfield id="baa027_name" key="所属中心"
				name="diagnoseInfoDTO.baa027_name" readonly="true"/>
			<powersi:hidden id="baa027" name="diagnoseInfoDTO.baa027" />
			<powersi:codeselect id="bac001" key="公务员级别"
				name="diagnoseInfoDTO.bac001"  codeType="bac001" disabled="true" />
			<powersi:hidden id="bac001" name="diagnoseInfoDTO.bac001" />
			<powersi:textfield id="aac008" key="基金冻结情况" name="diagnoseInfoDTO.aac008"
				readonly="true"/>
		</tr>
		<tr>
			<powersi:textfield id="aac001" name="diagnoseInfoDTO.aac001"
				label="电脑号" readonly="true" />	
			<powersi:textfield id="aae005" key="联系电话"
				name="diagnoseInfoDTO.aae005" readonly="true" />
			<powersi:textfield id="bacu18" key="个人账户余额"
				name="diagnoseInfoDTO.bacu18" readonly="true" />
		</tr>
	</powersi:editorlayout>
</powersi:panelbox>