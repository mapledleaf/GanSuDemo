<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<powersi:html>
<head>
	<s:if test="#parameters.isContained == null">
        <powersi:head title="病案首页-正面"></powersi:head>
    </s:if>
	<SCRIPT type="text/javascript" src="<%=request.getContextPath()%>/resource/js/basy.js" charset="gbk"></SCRIPT>
	<link href="<%=request.getContextPath() %>/resource/css/basy.css" type="text/css" charset="utf-8" rel="stylesheet" >
	<script type="text/javascript">
	
	$(function(){
		//zsy("#containdiv")
	});
	
	        function zsy(id)
{
  var d = document.body.scrollWidth;
var c = document.body.clientWidth;
if(d>c)
{
	var a = (c/d+"").substr(0,3);
	$(id).css("zoom",a)
}
}
	</script>
</head>
<body style="padding:0px" id="containdiv">
<div class="containDiv" >
	<div class="mainDiv">
		<div align="center" class="tableWidth">
				<h1>
					${dto.jgmc }
				</h1>
				<div style=padding-left:240px;">
					<b style="font-size:1.5em">住　院　病　案　首　页</b>
					(卫生机构(组织)代码：<input value="${dto.hospital_id }" class="inputUnderLine" style="width:100px" />)
				</div>
		</div>
		<table class="tableWidth" cellspacing="0" cellpadding="0" border="0px">
			<colgroup>
				<col width="12%"> 
				<col width="21%"> 
				<col width="12%"> 
				<col width="21%"> 
				<col width="12%"> 
				<col width="21%"> 
			</colgroup>
			<tr>
				<td	align="left" colspan="6">
					医疗付费方式：
					<span style="border:black solid thin;">&nbsp;<c:if test="${empty dto.fkfsbh|| dto.fkfsbh.length==0}">&nbsp;</c:if>${dto.ylfffs_name}&nbsp;</span>
				</td>
			</tr>
			<tr>
				<td		align="left" colspan="2" class="rowPaddingBottom" >
				健康卡号：
					<powersi:textfield name="#dto.jkkh" value="${dto.jkkh}" readonly="true"  cssClass="inputUnderLine" cssStyle="width:180px"/>
				</td>
				<td	colspan="2" align="center">
					<span>第</span>
					<powersi:textfield name="dto.zycs" value="${dto.zycs }" readonly="true" cssClass="inputUnderLine" cssStyle="width:80px;text-align:center"/>
					<span>次住院</span>
				</td>
				<td align="right">
				病案号：
				</td>
				<td>
					<powersi:textfield name="dto.bah" value="${ dto.bah}" readonly="true" cssClass="inputUnderLine" />
				</td>
			</tr>
		</table>
		
		<table class="tableWidth" cellspacing="0" cellpadding="0" border="1px">
		<colgroup>
			<col width="8%"> 
			<col width="16%"> 
			<col width="9%"> 
			<col width="16%"> 
			<col width="10%"> 
			<col width="16%"> 
			<col width="10%"> 
			<col width="15%"> 
		</colgroup>
			<tbody class="noTdBorder">
			<tr>
				<td colspan="8">
					<div class="inputWidth5 basyParentDiv">
						<div class="basyChildOne">　姓名</div>
						<div class="basyChildTwo">
							<powersi:textfield name="#request.dto.xm" value="${dto.xm }" readonly="true" cssClass="inputUnderLine" cssStyle="width:100%"/>
						</div>
					</div>
					
					<div class="inputWidth4 basyParentDiv">
						<div class="basyChildOne">　性别</div>
						<div class="basyChildTwo">
							<span style="border:black solid thin;">&nbsp;<c:if test="${empty dto.xb}">&nbsp;</c:if>${dto.xb}&nbsp;</span>
							<span style="padding-left:0.2cm">1.男&nbsp;&nbsp;2.女</span>
						</div>
					</div>
					
					<div class="inputWidth5 basyParentDiv">
						<div class="basyChildOne">　出生日期</div>
						<div class="basyChildTwo">
							<powersi:textfield id="csrq" format="yyyy年MM月dd日" name="#request.dto.csny" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
					
					<div class="inputWidth5 basyParentDiv">
						<div class="basyChildOne">　年龄</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.nl" value="${dto.nl }" readonly="true" cssClass="inputUnderLine" />
						</div>	
					</div>
				</td>
			</tr>
			<tr>
			  <td colspan="8">
			  	<div class="inputWidth5 basyParentDiv">
					<div class="basyChildOne">　国籍</div>
					<div class="basyChildTwo">
						<powersi:textfield name="dto.guoj" value="${dto.gj }" readonly="true" cssClass="inputUnderLine" />
					</div>
				</div>
				<div class="inputWidth4 basyParentDiv">
					<div class="basyChildOne">　民族</div>
					<div class="basyChildTwo">
						<powersi:textfield name="dto.mz" value="${dto.mz_name }" readonly="true" cssClass="inputUnderLine" />
					</div>
				</div>
				<div class="inputWidth5 basyParentDiv">
					<div class="basyChildOne">　新生儿出生体重</div>
					<div class="basyChildThree">
						克
					</div>
					<div class="basyChildTwo">
						<powersi:textfield name="dto.cstz" value="${dto.xsecstz}" readonly="true" cssClass="inputUnderLine" />
					</div>
				</div>
				<div class="inputWidth5 basyParentDiv">
					<div class="basyChildOne">　新生儿入院体重</div>
					<div class="basyChildThree">
						克
					</div>
					<div class="basyChildTwo">
						<powersi:textfield name="dto.fytz" value="${dto.xserytz }" readonly="true" cssClass="inputUnderLine" />
					</div>
				</div>
			</td>
			</tr>
			<tr>
				<td colspan="8">
					<div class="inputWidth12 basyParentDiv">
						<div class=" basyChildOne">　出生地</div>
						<div class=" basyChildTwo">
							<powersi:textfield name="dto.csd" value="${dto.csd }" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth7 basyParentDiv">
						<div class="basyChildOne">籍贯</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.guanj" value="${dto.jg}" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
				</td>
			</tr>
			<tr>	
				<td colspan="8">
					<div class="inputWidth6 basyParentDiv">
						<div class="basyChildOne">　身份证号</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.sfzh" value="${dto.sfz}" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth4 basyParentDiv">
						<div class="basyChildOne">　职业</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.zy" value="${dto.zybm_name }" cssClass="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth9 basyParentDiv">
						<div class="basyChildOne">　婚姻情况</div>
						<div class="basyChildTwo">
							<span style="border:black solid thin;">&nbsp;<c:if test="${empty dto.hyzk }">&nbsp;</c:if>${dto.hyzk}&nbsp;</span>
							<span style="padding-left:0.2cm">10.未婚&nbsp;&nbsp;20.已婚&nbsp;&nbsp;30.丧偶&nbsp;&nbsp;40.离婚&nbsp;&nbsp;90.其他</span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<div class="inputWidth11 basyParentDiv">
						<div class="basyChildOne">　现住址</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.xzz" value="${dto.jzd }" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth4 basyParentDiv">
						<div class="basyChildOne">电话</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.xdh" value="${dto.xzzdh }" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth4 basyParentDiv">
						<div class="basyChildOne">　邮编</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.xyb" value="${dto.xzzyb }" readonly="true" cssClass="inputUnderLine"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<div class="inputWidth15 basyParentDiv">
						<div class="basyChildOne">　户口地址</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.hkdz" value="${dto.hkdz }" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth4 basyParentDiv">
						<div class="basyChildOne">　邮编</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.hkyb" value="${dto.hkyb }" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<div class="inputWidth5 basyParentDiv">
						<div class="basyChildOne">　单位名称</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.dwmc" value="${dto.czdw }" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth6 basyParentDiv">
						<div class="basyChildOne">&nbsp;地址</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.dwdz" value="" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth4 basyParentDiv">
						<div class="basyChildOne">　电话</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.dwdh" value="${dto.gzdwdh }" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth4 basyParentDiv">
						<div class="basyChildOne">　邮编</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.dwyb" value="${dto.gzdwyb }" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<div class="inputWidth5 basyParentDiv">
						<div class="basyChildOne">　联系人姓名</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.lxr" value="${dto.lxrxm }" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth4 basyParentDiv">
						<div class="basyChildOne">&nbsp;关系</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.lxrgx" value="${dto.lxxgx_name }" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth6 basyParentDiv">
						<div class="basyChildOne">&nbsp;地址</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.lxrdz" value="${dto.lxrdz }" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth4 basyParentDiv">
						<div class="basyChildOne">　电话</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.lxrdh" value="${dto.lxrdh }" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<div class="inputWidth19 basyParentDiv">
						<div class="basyChildOne">　入院途径</div>
						<div class="basyChildTwo" >
<!--						<powersi:textfield name="" cssStyle="width:30px"/>-->
							<span style="border:black solid thin;">&nbsp;<c:if test="${empty dto.rylx }">&nbsp;</c:if>${dto.rylx}&nbsp;</span>
							<span style="padding-left:0.2cm">1.急诊&nbsp;&nbsp;2.门诊&nbsp;&nbsp;3.其他机构转入&nbsp;&nbsp;9.其他</span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<div class="inputWidth5 basyParentDiv">
						<div class="basyChildOne">　入院日期</div>
						<div class="basyChildTwo">
							<powersi:textfield id="ryrq" value="${dto.rysj }" name="dto.ryrq" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth5 basyParentDiv">
						<div class="basyChildOne">　入院科别</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.rykb" value="${dto.ryksbm }" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth5 basyParentDiv">
						<div class="basyChildOne">　入院病室</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.rybs" value="${dto.rybq }"  readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<div class="inputWidth4 basyParentDiv">
						<div class="basyChildOne">　转科　1.</div>
						<div class="basyChildTwo">
							<input type="text" value='${dto.ryksbm }' class="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth3 basyParentDiv">
						<div class="basyChildOne">转</div>
						<div class="basyChildTwo">
							<input type="text" value='${dto.ryksbm1}' class="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth3 basyParentDiv">
						<div class="basyChildOne">　2.</div>
						<div class="basyChildTwo">
							<input type="text" value='${dto.ryksbm1}' class="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth3 basyParentDiv">
						<div class="basyChildOne">转</div>
						<div class="basyChildTwo">
							<input type="text" value='${dto.ryksbm2}' class="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth3 basyParentDiv">
						<div class="basyChildOne">　3.</div>
						<div class="basyChildTwo">
							<input type="text" value='${dto.ryksbm2}' class="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth3 basyParentDiv">
						<div class="basyChildOne">转</div>
						<div class="basyChildTwo">
							<input type="text" value='${dto.ryksbm3}' class="inputUnderLine" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<div class="inputWidth5 basyParentDiv">
						<div class="basyChildOne">　出院日期</div>
						<div class="basyChildTwo">
							<powersi:textfield id="cyrq" name="dto.cyrq" value="${dto.cysj }" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth5 basyParentDiv">
						<div class="basyChildOne">　出院科别</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.cykb" value="${dto.cyksbm }" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth5 basyParentDiv">
						<div class="basyChildOne">　出院病室</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.cybs" value="${dto.cybq }"  readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth4 basyParentDiv">
						<div class="basyChildOne">　实际住院</div>
						<div class="basyChildThree">
							天
						</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.sjzyts" value="${dto.sjzyts }" readonly="true" cssClass="inputUnderLine" />
						</div>
						
					</div>
				</td>
			</tr>
			<tr >
				<td colspan="8" class="rowPaddingBottom">
					<div class="inputWidth9 basyParentDiv">
						<div class="basyChildOne">　门(急)诊诊断</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.mzzd" value="" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth5 basyParentDiv">
						<div class="basyChildOne">　疾病编码</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.mzzdbh" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth5 basyParentDiv">
						<div class="basyChildOne">　门(急)诊医生</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.mzys" value="${dto.mzysxm }" cssClass="inputUnderLine" />
						</div>
					</div>
				</td>
			</tr>
		</tbody>
		<tbody>	
		  <tr>
		    <td colspan="3" rowspan="2" align="center">出院诊断</td>
		    <td rowspan="2" align="center">疾病编码</td>
		    <td colspan="4"  align="center">入院病情</td>
		  </tr>
		  <tr>
		    <td align="center">有</td>
		    <td align="center">临床不确定</td>
		    <td align="center">情况不明</td>
		    <td align="center">无</td>
		  </tr>
		  <s:iterator value="#request.zdList" var="zd" status="zdStatus">
		    <s:set var="zdLength" value="#zdStatus.count"></s:set>
		  	<tr>
		  		<td colspan="3">${zd.aka121}</td>
			    <td align="center">${zd.zdbm}</td>
			    <s:if test='"1".equals(#zd.rybq)'>
			    	<td align="center">√</td>
				    <td align="center">&nbsp;</td>
				    <td align="center">&nbsp;</td>
				    <td align="center">&nbsp;</td>
			    </s:if>
			    <s:elseif test='"2".equals(#zd.rybq)'>
			    	<td align="center">&nbsp;</td>
			    	<td align="center">√</td>
			    	<td align="center">&nbsp;</td>
				    <td align="center">&nbsp;</td>
			    </s:elseif>
			    <s:elseif test='"3".equals(#zd.rybq)'>
			    	<td align="center">&nbsp;</td>
			    	<td align="center">&nbsp;</td>
			    	<td align="center">√</td>
				    <td align="center">&nbsp;</td>
			    </s:elseif>
			    <s:else>
			    	<td align="center">&nbsp;</td>
			    	<td align="center">&nbsp;</td>
			    	<td align="center">&nbsp;</td>
			    	<td align="center">√</td>
			    </s:else>
		  	</tr>
		  </s:iterator>
		  <s:iterator begin="1" end="6-#zdLength" step="1">
		  	 <tr>
			    <td colspan="3">&nbsp;</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
		  </s:iterator>
		  </tbody>
		  <tbody class="noTdBorder" >
		  	<tr>
		  		<td colspan="8">
		  			<div class="inputWidth8 basyParentDiv">
						<div class="basyChildOne">　病例分型</div>
						<div class="basyChildTwo">
							<span style="border:black solid thin;">&nbsp;<s:if test='dto.jbfxbh==null || dto.jbfxbh.length==0'>&nbsp;</s:if>${dto.jbfxbh }&nbsp;</span>
							<span style="padding-left:0.2cm">1.一般&nbsp;2.急&nbsp;3.疑难&nbsp;4.危重</span>
						</div>
					</div>
					<div class="inputWidth5 basyParentDiv">
						<div class="basyChildOne">临床路径病例</div>
						<div class="basyChildTwo">
							<span style="border:black solid thin;">&nbsp;<s:if test='dto.lcljblbh==null || dto.lcljblbh.length==0'>&nbsp;</s:if>${dto.lcljblbh }&nbsp;</span>
							<span style="padding-left:0.2cm">1.是&nbsp;&nbsp;2.否</span>
						</div>
					</div>
					<div class="inputWidth3 basyParentDiv">
						<div class="basyChildOne">　抢救</div>
						<div class="basyChildThree">
							次
						</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.qjcs" value="${dto.qjcs }" readonly="true" cssClass="inputUnderLine" />
						</div>
						
					</div>
					<div class="inputWidth3 basyParentDiv">
						<div class="basyChildOne">　成功</div>
						<div class="basyChildThree">
							次
						</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.qjcgcs" value="${dto.cgcs }" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
				</td>
			  </tr>
			  <tr>
			  	<td colspan="8">
			  		<div class="inputWidth9 basyParentDiv">
						<div class="basyChildOne">　病理诊断</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.blzd" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth5 basyParentDiv">
						<div class="basyChildOne">　病理号</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.blh" value="${dto.blh }" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth5 basyParentDiv">
						<div class="basyChildOne" >疾病编码</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.blzdbm" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
				</td>
			  </tr>
			  <tr>
			  	<td colspan="8">
			  		<div class="inputWidth5 basyParentDiv">
						<div class="basyChildOne">　药物过敏</div>
						<div class="basyChildTwo">
	<!--						<powersi:textfield name="" cssStyle="width:30px" />-->
							<span style="border:black solid thin;">&nbsp;<c:if test="${empty dto.ywgm }">&nbsp;</c:if>${dto.sfywgm}&nbsp;</span>
							<span>1.无&nbsp;&nbsp;2.有</span>
						</div>
					</div>
					<div class="inputWidth9 basyParentDiv">
						<div class="basyChildOne">　过敏药物</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.gmyw" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth5 basyParentDiv">
						<div class="basyChildOne">死亡患者尸检</div>
						<div class="basyChildTwo">
	<!--						<powersi:textfield name="" cssStyle="width:30px" />-->
							<span style="border:black solid thin;">&nbsp;<s:if test='dto.sfsjbh==null || dto.sfsjbh.length==0'>&nbsp;</s:if>${dto.sfsjbh}&nbsp;</span>
							<span>1.是&nbsp;&nbsp;2.否</span>
						</div>
					</div>
				</td>
			  </tr>
			  <tr>
			  	<td colspan="8">
			  		<div class="inputWidth10 basyParentDiv">
						<div class="basyChildOne">　血型</div>
						<div class="basyChildTwo">
							<span style="border:black solid thin;">&nbsp;<c:if test="${empty dto.xx}">&nbsp;</c:if>${dto.xx}&nbsp;</span>
							<span>　1.A&nbsp;&nbsp;2.B&nbsp;&nbsp;3.O&nbsp;&nbsp;4.AB&nbsp;&nbsp;5.不祥&nbsp;&nbsp;6.未查</span>
						</div>
					</div>
					<div class="inputWidth9 basyParentDiv">
						<div class="basyChildOne">&nbsp;ＲＨ</div>
						<div class="basyChildTwo">
							<span style="border:black solid thin;">&nbsp;<s:if test='dto.rhbh==null || dto.rhbh.length==0'>&nbsp;</s:if>${dto.rhbh}&nbsp;</span>
							<span>1.阴&nbsp;&nbsp;2.阳&nbsp;&nbsp;3.不祥&nbsp;&nbsp;4.未查</span>
						</div>
					</div>
				</td>
			  </tr>
			  <tr>
			  	<td colspan="8">
			  		<div class="inputWidth5 basyParentDiv">
						<div class="basyChildOne">　科主任</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.kzr" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth5 basyParentDiv">
						<div class="basyChildOne">　(副)主任医师</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.fzr" value="${dto.zrysxm }" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth4 basyParentDiv">
						<div class="basyChildOne">　主治医师</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.zzys" value="${dto.zzysxm }" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth5 basyParentDiv">
						<div class="basyChildOne">　住院医师</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.zyys" value="${dto.zyysxm }" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
				</td>
			  </tr>
			  <tr>
			  	<td colspan="8">
			  		<div class="inputWidth5 basyParentDiv">
						<div class="basyChildOne">　责任护士</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.zrhs" value="${dto.zrhsxm }" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth5 basyParentDiv">
						<div class="basyChildOne">　进修医师</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.jxys" value="${dto.jxysxm }" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth4 basyParentDiv">
						<div class="basyChildOne">　实习医师</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.sxys" value="${dto.sxysxm }" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth5 basyParentDiv">
						<div class="basyChildOne">　编码员</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.bmy" value="${dto.bmyxm }" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
				</td>
			  </tr>
			  <tr>
			  	<td colspan="8" class="rowPaddingBottom">
			  		<div class="inputWidth5 basyParentDiv">
						<div class="basyChildOne">　病案质量</div>
						<div class="basyChildTwo">
	<!--						<powersi:textfield name="" cssStyle="width:30px" />-->
							<span style="border:black solid thin;">&nbsp;<c:if test="${empty dto.bazl }">&nbsp;</c:if>${dto.bazl}&nbsp;</span>
							<span>1.甲&nbsp;2.乙&nbsp;3.丙</span>
						</div>
					</div>
					<div class="inputWidth5 basyParentDiv" >
						<div class="basyChildOne">　质控医师</div>
						<div class="basyChildTwo" >
							<powersi:textfield name="dto.zkys" value="${dto.zkysxm }" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth4 basyParentDiv">
						<div class="basyChildOne">　质控护士</div>
						<div class="basyChildTwo">
							<powersi:textfield name="dto.zkhs" value="${dto.zkhsxm }" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
					<div class="inputWidth5 basyParentDiv">
						<div class="basyChildOne">　质控日期</div>
						<div class="basyChildTwo">
							<powersi:textfield id="zkrq" name="dto.zkrq" value="${dto.zkrq }" readonly="true" cssClass="inputUnderLine" />
						</div>
					</div>
				</td>
			  </tr>
		  </tbody>
		</table>
	</div>
</div>
</body>
</powersi:html>