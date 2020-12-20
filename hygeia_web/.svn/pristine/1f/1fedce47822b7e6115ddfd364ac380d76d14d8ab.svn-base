<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<powersi:html>
<head>
	<s:if test="#parameters.isContained == null">
        <powersi:head title="病案首页-反面"></powersi:head>
    </s:if>
	<SCRIPT type="text/javascript" src="<%=request.getContextPath()%>/resource/js/basy.js"></SCRIPT>
	<link href="<%=request.getContextPath() %>/resource/css/basy.css" type="text/css" charset="utf-8" rel="stylesheet" >
</head>
<body style="padding:0px">
<div class="containDiv">
	<div class="mainDiv">
		<table class="tableWidth"  cellspacing="0" cellpadding="0" border="1px">
		<colgroup>
			<col width="14%"> 
			<col width="21%"> 
			<col width="7%"> 
			<col width="7%">
			<col width="7%"> 
			<col width="7%"> 
			<col width="7%"> 
			<col width="7%"> 
			<col width="7%"> 
			<col width="7%">  
		</colgroup>
			<tbody>
				   <tr>
					    <td rowspan="2" align="center">手术及<br/>操作日期</td>
					    <td rowspan="2" align="center">手术及<br/>操作名称</td>
					    <td rowspan="2" align="center">手术级别</td>
					    <td colspan="3" align="center">手术及<br/>操作医师</td>
					    <td rowspan="2" align="center">切口/愈合</td>
					    <td rowspan="2" align="center">麻醉方式</td>
					    <td rowspan="2" align="center">麻醉医生</td>
					    <td rowspan="2" align="center">手术及<br/>操作编码</td>
					  </tr>
					  <tr>
					    <td align="center">术者</td>
					    <td align="center">I助</td>
					    <td align="center">II助</td>
					  </tr>
					  <s:iterator value="#request.ssList" var="ss" status="ssStatus">
					    <s:set var="ssLength" value="#ssStatus.count"></s:set>
					  	<tr>
						    <td align="center" class="dateFmt">${ss.sskssj }</td>
						    <td>${ss.ssqzdmc }</td>
						    <td align="center">${ss.ssjb }</td>
						    <td align="center">${ss.ssysxm }</td>
						    <td align="center">${ss.ssysz1xm }</td>
						    <td align="center">${ss.ssysz2xm }</td>
						    <td align="center">${ss.qkyhdj }</td>
						    <td align="center">${ss.mzfs }</td>
						    <td align="center">${ss.mzysxm }</td>
						    <td align="center">${ss.ssczbm }</td>
					  	</tr>
					  </s:iterator>
					  <s:iterator begin="1" end="6-#ssLength" step="1">
					  	 <tr>
						    <td>&nbsp;</td>
						    <td>&nbsp;</td>
						    <td>&nbsp;</td>
						    <td>&nbsp;</td>
						    <td>&nbsp;</td>
						    <td>&nbsp;</td>
						    <td>&nbsp;</td>
						    <td>&nbsp;</td>
						    <td>&nbsp;</td>
						    <td>&nbsp;</td>
						  </tr>
					  </s:iterator>
			</table>
			<table class="tableWidth"  cellspacing="0" cellpadding="0" border="1px" style="border-top-width: 0px">
				<colgroup>
					<col width="15%"> 
					<col width="75%"> 
				</colgroup>
					  <tr>
					    <td colspan="2" class="rowPaddingBottom" style="border-top-width: 0px">
					    	<div class="inputWidth19 basyParentDiv">
					    		<div class="basyChildOne">　离院方式
						    		<span style="border:black solid thin;">&nbsp;<c:if test="${empty dto.lyfs }">&nbsp;&nbsp;</c:if>${dto.lyfs }&nbsp;</span>
						    		1.医嘱离院&nbsp;&nbsp;2.医嘱转院，拟接收医院名称：
						    	</div>
						    	<div class="basyChildTwo">
									<powersi:textfield name="dto.yznjsyljgmc" value="${dto.njsyymc }"  cssClass="inputUnderLine" />
								</div>
						    </div>
					    	<div class="inputWidth19 basyParentDiv">
					    		<div class="basyChildOne">　3.医嘱转社区卫生服务机构/医嘱转院，拟接收医院名称：</div>
					    		<div class="basyChildThree">4.非医嘱离院&nbsp;&nbsp;5.死亡&nbsp;&nbsp;9.其他</div>
								<div class="basyChildTwo">
									<powersi:textfield name="dto.sqnjsyljgmc" cssClass="inputUnderLine" />
								</div>
					    	</div>
					    </td>
					  </tr>
					  <tr>
					  	<td colspan="2" class="rowPaddingBottom">
					  		<div class="inputWidth19 basyParentDiv">
					  			<div class="basyChildOne">
						    		　是否有出院30天内再住院计划
						    		<span style="border:black solid thin;">&nbsp;<c:if test="${empty dto.zzyjh_31 }">&nbsp;&nbsp;</c:if>${dto.zzyjh_31 }&nbsp;</span>
						    		1.无&nbsp;&nbsp;2.有，目的：
						    	</div>
						    	<div class="basyChildTwo">
									<powersi:textfield name="#request.dto.zzymd"  cssClass="inputUnderLine" />
								</div>
					  		</div>
					    </td>
					  </tr>
					  <tr>
					  	<td colspan="2" class="rowPaddingBottom">
					    	<div class="inputWidth7 basyParentDiv">
					    		<div class="basyChildOne">
					    		　颅脑损伤患者昏迷时间：入院前
					    		</div>
					    		<!-- <div class="basyChildThree">
					    			天
					    		</div> -->
					    		<div class="basyChildTwo">
									<powersi:textfield name="dto.lshmdaysryq" value="${dto.ryqhmsj }"  cssClass="inputUnderLine" />
								</div>
					    	</div>
							<%-- <div class="inputWidth2 basyParentDiv">
								<div class="basyChildOne"></div>
								<div class="basyChildThree">
					    			小时
					    		</div>
					    		<div class="basyChildTwo">
									<powersi:textfield name="dto.lshmhoursryq"  cssClass="inputUnderLine" />
								</div>
							</div>
					    	<div class="inputWidth3 basyParentDiv">
					    		<div class="basyChildOne"></div>
					    		<div class="basyChildThree">
					    			分钟
					    		</div>
					    		<div class="basyChildTwo">
									<powersi:textfield name="dto.lshmminsryq"  cssClass="inputUnderLine" />
								</div>
							</div> --%>
							
							<div class="inputWidth3 basyParentDiv">
					    		<div class="basyChildOne">　入院后</div>
					    		<!-- <div class="basyChildThree">
						    		天
						    	</div> -->
					    		<div class="basyChildTwo">
					    			<powersi:textfield name="#request.dto.ryhhmsj" cssClass="inputUnderLine" />
					    		</div>
					    	</div>
							<%-- <div class="inputWidth2 basyParentDiv">
								<div class="basyChildOne"></div>
								<div class="basyChildThree">
						    		小时
						    	</div>
					    		<div class="basyChildTwo">
					    			<powersi:textfield name="dto.lshmhoursryh" cssClass="inputUnderLine" />
					    		</div>
							</div>
							<div class="inputWidth2 basyParentDiv">
								<div class="basyChildOne"></div>
								<div class="basyChildThree">
									分钟
						    	</div>
					    		<div class="basyChildTwo">
					    			<powersi:textfield name="dto.lshmminsryh" cssClass="inputUnderLine" />
					    		</div>
						    		
							</div> --%>
					    </td>
					  </tr>
					  <tr>
					  	<td colspan="2" class="rowPaddingBottom">
					    	<div>　住院费用(元)：总费用
					    	</div>
					    	<div class="inputWidth2">
								<powersi:textfield name="#request.dto.zfy"  cssStyle="text-align:right"  cssClass="inputUnderLine" />
							</div>
							<div>
					    		（自付金额：
					    	</div>
					    	<div class="inputWidth2">
								<powersi:textfield name="#request.dto.zfje"  cssStyle="text-align:right"  cssClass="inputUnderLine" />
							</div>
							<div>
					    		）
					    	</div>
					    </td>
					  </tr>
					  <tr>
					    <td align="left">1.综合医疗服务类：</td>
					    <td class="rowPaddingBottom">
					    	<div class="inputWidth8 basyParentDiv">
						    	<div class="basyChildOne">
						    		(1)一般医疗服务费:
						    	</div>
						    	<div class="basyChildTwo">
									<powersi:textfield name="#request.dto.ylfwf"  cssStyle="text-align:right"  cssClass="inputUnderLine" />
								</div>
							</div>
							<div class="inputWidth8 basyParentDiv">
								<div class="basyChildOne">
						    		(2)一般治疗操作费:
						    	</div>
						    	<div class="basyChildTwo">
									<powersi:textfield name="#request.dto.zlczf"  cssStyle="text-align:right"  cssClass="inputUnderLine" />
								</div>
							</div>
					    	<div class="inputWidth8 basyParentDiv">
								<div class="basyChildOne">
						    		(3)护理费:
						    	</div>
						    	<div class="basyChildTwo">
									<powersi:textfield name="#request.dto.hlf"  cssStyle="text-align:right"  cssClass="inputUnderLine" />
								</div>
					   		</div>
					   		<div class="inputWidth8 basyParentDiv">
					   			<div class="basyChildOne">
						    		(4)其他费用:
						    	</div>
						    	<div class="basyChildTwo">
									<powersi:textfield name="#request.dto.qtf"  cssStyle="text-align:right"  cssClass="inputUnderLine" />
								</div>
					   		</div>
					    </td>
					  </tr>
					  <tr>
					  	<td align="left">2.诊断类：</td>
					    <td class="rowPaddingBottom">
					    	<div class="inputWidth8 basyParentDiv">
						    	<div class="basyChildOne">
						    		(5)病理诊断费
						    	</div>
						    	<div class="basyChildTwo">
									<powersi:textfield name="#request.dto.blzdf"  cssStyle="text-align:right"  cssClass="inputUnderLine" />
								</div>
							</div>
							<div class="inputWidth8 basyParentDiv">
								<div class="basyChildOne">
						    		(6)实验室诊断费
						    	</div>
						    	<div class="basyChildTwo">
									<powersi:textfield name="#request.dto.syszdf"  cssStyle="text-align:right"  cssClass="inputUnderLine" />
								</div>
							</div>
					    	<div class="inputWidth8 basyParentDiv">
								<div class="basyChildOne">
						    		(7)影像学诊断费
						    	</div>
						    	<div class="basyChildTwo">
									<powersi:textfield name="#request.dto.yxxzdf"  cssStyle="text-align:right"  cssClass="inputUnderLine" />
								</div>
					   		</div>
					   		<div class="inputWidth8 basyParentDiv">
					   			<div class="basyChildOne">
						    		(8)临床诊断项目费
						    	</div>
						    	<div class="basyChildTwo">
									<powersi:textfield name="#request.dto.lczdxmf"  cssStyle="text-align:right"  cssClass="inputUnderLine" />
								</div>
					   		</div>
					    </td>
					   </tr>
					  <tr>
					  	<td align="left">3.治疗类：</td>
					    <td class="rowPaddingBottom">
					    	<div class="inputWidth8 basyParentDiv">
						    	<div class="basyChildOne">
						    		(9)非手术治疗项目费：
						    	</div>
						    	<div class="basyChildTwo">
									<powersi:textfield name="#request.dto.fsszlxmf"  cssStyle="text-align:right"  cssClass="inputUnderLine" />
								</div>
							</div>
							<div class="inputWidth8 basyParentDiv">
								<div class="basyChildOne">
						    		(临床物理治疗费：
						    	</div>
						    	<div class="basyChildThree">
						    	）
						    	</div>
						    	<div class="basyChildTwo">
									<powersi:textfield name="#request.dto.lcwlzlf"  cssStyle="text-align:right"  cssClass="inputUnderLine" />
								</div>
						    		
					    	</div>
							<div class="inputWidth8 basyParentDiv">
								<div class="basyChildOne">
						    		(10)手术治疗费：
						    	</div>
						    	<div class="basyChildTwo">
									<powersi:textfield name="#request.dto.sszlf"  cssStyle="text-align:right"  cssClass="inputUnderLine" />
								</div>
							</div>
							<div class="inputWidth4 basyParentDiv">
								<div class="basyChildOne">
					    			(麻醉费：
						    	</div>
						    	<div class="basyChildTwo">
									<powersi:textfield name="#request.dto.mzf"  cssStyle="text-align:right"  cssClass="inputUnderLine" />
								</div>
							</div>
							<div class="inputWidth4 basyParentDiv">
								<div class="basyChildOne">
						    		手术费：
						    	</div>
						    	<div class="basyChildThree" >
						    		）
						    	</div>
						    	<div class="basyChildTwo">
									<powersi:textfield name="#request.dto.ssf"  cssStyle="text-align:right"  cssClass="inputUnderLine" />
								</div>
							</div>
					    </td>
					  </tr>
					  <tr>
					    <td align="left">4.康复类：</td>
					    <td class="rowPaddingBottom">
					    	<div class="inputWidth8 basyParentDiv">
					    		<div class="basyChildOne">(11)康复费：</div>
					    		<div class="basyChildTwo">
									<powersi:textfield name="#request.dto.kff"  cssStyle="text-align:right"  cssClass="inputUnderLine" />
								</div>
					    	</div>
					    </td>
					  </tr>
					  <tr>
					    <td  align="left">5.中医类：</td>
					    <td class="rowPaddingBottom">
					    	<div class="inputWidth8 basyParentDiv">
					    		<div class="basyChildOne">(12)中医治疗费：</div>
					    		<div class="basyChildTwo">
									<powersi:textfield name="#request.dto.zyzlf"  cssStyle="text-align:right"  cssClass="inputUnderLine" />
								</div>
					    	</div>
					    </td>
					  </tr>
					  <tr>
					    <td align="left">6.西药类：</td>
					    <td class="rowPaddingBottom">
					    	<div class="inputWidth8 basyParentDiv">
					    		<div class="basyChildOne">(13)西药费：</div>
					    		<div class="basyChildTwo">
									<powersi:textfield name="#request.dto.xyf"  cssStyle="text-align:right"  cssClass="inputUnderLine" />
								</div>
					    	</div>
					    	<div class="inputWidth8 basyParentDiv" >
					    		<div class="basyChildOne">（抗菌药物费用：</div>
					    		<div class="basyChildThree">
								）
								</div>
					    		<div class="basyChildTwo">
									<powersi:textfield name="#request.dto.kjywfy"  cssStyle="text-align:right"  cssClass="inputUnderLine" />
								</div>
					    	</div>
					    </td>
					  </tr>
					  <tr>
					    <td align="left">7.中药类：</td>
					    <td class="rowPaddingBottom">
					    	<div class="inputWidth8 basyParentDiv">
					    		<div class="basyChildOne">(14)中成药费:</div>
					    		<div class="basyChildTwo">
									<powersi:textfield name="#request.dto.zcyf"  cssStyle="text-align:right"  cssClass="inputUnderLine" />
								</div>
					    	</div>
					    	<div class="inputWidth8 basyParentDiv">
					    		<div class="basyChildOne">(15)中草药费</div>
					    		<div class="basyChildTwo">
									<powersi:textfield name="#request.dto.zcaf"  cssStyle="text-align:right"  cssClass="inputUnderLine" />
								</div>
					    	</div>
					    </td>
					  </tr>
					  <tr>
					    <td  align="left">8.血液和血液制品类：</td>
					    <td class="rowPaddingBottom">
					    	<div class="inputWidth5 basyParentDiv">
					    		<div class="basyChildOne">(16)血费:</div>
					    		<div class="basyChildTwo">
									<powersi:textfield name="#request.dto.xf"  cssStyle="text-align:right"  cssClass="inputUnderLine" />
								</div>
					    	</div>
					    	<div class="inputWidth5 basyParentDiv">
					    		<div class="basyChildOne">(17)血蛋白类制品费:</div>
					    		<div class="basyChildTwo">
									<powersi:textfield name="#request.dto.bdbzpf"  cssStyle="text-align:right"  cssClass="inputUnderLine" />
								</div>
					    	</div>
					    	<div class="inputWidth6 basyParentDiv">
					    		<div class="basyChildOne">(18)球蛋白类制品费:</div>
					    		<div class="basyChildTwo">
									<powersi:textfield name="#request.dto.qdbzpf"  cssStyle="text-align:right"  cssClass="inputUnderLine" />
								</div>
					    	</div>
							
							<div class="inputWidth8 basyParentDiv">
					    		<div class="basyChildOne">(19)凝血因子类制品费:</div>
					    		<div class="basyChildTwo">
									<powersi:textfield name="#request.dto.nxyzzpf"  cssStyle="text-align:right"  cssClass="inputUnderLine" />
								</div>
					    	</div>
					    	<div class="inputWidth8 basyParentDiv">
					    		<div class="basyChildOne">(20)细胞因子类制品费:</div>
					    		<div class="basyChildTwo">
									<powersi:textfield name="#request.dto.xbyzzpf"  cssStyle="text-align:right"  cssClass="inputUnderLine" />
								</div>
					    	</div>
					    </td>
					  </tr>
					  <tr>
					    <td align="left">9.耗材类：</td>
					    <td class="rowPaddingBottom">
					    	<div class="inputWidth8 basyParentDiv">
					    		<div class="basyChildOne">(21)检查用一次性医用材料费:</div>
					    		<div class="basyChildTwo">
									<powersi:textfield name="#request.dto.jcyyclf"  cssStyle="text-align:right"  cssClass="inputUnderLine" />
								</div>
					    	</div>
					    	<div class="inputWidth8 basyParentDiv">
					    		<div class="basyChildOne">(22)治疗用一次性医用材料费:</div>
					    		<div class="basyChildTwo">
									<powersi:textfield name="#request.dto.zlyyclf"  cssStyle="text-align:right" cssClass="inputUnderLine" />
								</div>
					    	</div>
					    	<div class="inputWidth8 basyParentDiv">
					    		<div class="basyChildOne">(23)手术用一次性医用材料费:</div>
					    		<div class="basyChildTwo">
									<powersi:textfield name="#request.dto.ssyyclf"  cssStyle="text-align:right"  cssClass="inputUnderLine" />
								</div>
					    	</div>
						</td>
					  </tr>
					  <tr>
					    <td align="left">10.其他类：</td>
					    <td class="rowPaddingBottom">
					    	<div class="inputWidth8 basyParentDiv">
					    		<div class="basyChildOne">
					    		(24)其他费
						    	</div>
						    	<div class="basyChildTwo">
									<powersi:textfield name="#request.dto.qtf_1" cssStyle="text-align:right"  cssClass="inputUnderLine" />
								</div>
					    	</div>
					    </td>
					  </tr>
		  </tbody>
		</table>
		<p class="tableWidth" align="left">
			说明：（一）医疗付费方式　1.城镇职工基本医疗保险　2.城镇居民基本医疗保险　3.新型农村合作医疗
			4.贫困救助　5商业医疗保险　6.全公费　7.全自费　8.其他社会保险　9.其他
			（二）凡可由医院信息系统提供住院费用清单的，住院病案首页中可不填写”住院费用“，但必须按首页的
			费用分类提供电子数据。
		</p>
		<div >
			<p class="tableWidth" align="left">
				<b>附：产科分娩婴儿记录表：（选填）</b>
			</p>
			
			<table class="tableWidth" border="1" cellspacing="0" cellpadding="0">
			  <tr>
			    <td rowspan="2" align="center">婴儿<br/>序号</td>
			    <td colspan="2" align="center">性别</td>
			    <td colspan="3" align="center">分娩结果</td>
			    <td rowspan="2" align="center">婴儿<br/>体重<br/>（g）</td>
			    <td colspan="3" align="center">婴儿转归</td>
			    <td colspan="3" align="center">呼吸</td>
			    <td rowspan="2" align="center">抢救<br/>次数</td>
			    <td rowspan="2" align="center">成功<br/>抢救<br/>次数</td>
			  </tr>
			  <tr>
			    <td align="center">男性</td>
			    <td align="center">女性</td>
			    <td align="center">活产</td>
			    <td align="center">死产</td>
			    <td align="center">死胎</td>
			    <td align="center">死亡</td>
			    <td align="center">转科</td>
			    <td align="center">出院</td>
			    <td align="center">自然</td>
			    <td align="center">I度窒息</td>
			    <td align="center">II度窒息</td>
			  </tr>
			  <s:iterator value="#request.fykList" var="fyk" status="fykStatus">
			    <s:set var="fykLength" value="#fykStatus.count"></s:set>
			  	<tr>
				    <td align="center">${fyk.babynum }</td>
				    <td align="center">&nbsp;</td>
				    <td align="center">&nbsp;</td>
				    <s:if test='"1".equals(#fyk.fmjgbh)'>
				    	<td align="center">√</td>
					    <td align="center">&nbsp;</td>
					    <td align="center">&nbsp;</td>
				    </s:if>
				    <s:elseif test='"2".equals(#fyk.fmjgbh)'>
				    	<td align="center">&nbsp;</td>
				    	<td align="center">√</td>
					    <td align="center">&nbsp;</td>
				    </s:elseif>
				    <s:else>
				    	<td align="center">&nbsp;</td>
				    	<td align="center">&nbsp;</td>
				    	<td align="center">√</td>
				    </s:else>
				    <td>${fyk.babytz }</td>
				    <s:if test='"1".equals(#fyk.zgbh)'>
				    	<td align="center">√</td>
					    <td align="center">&nbsp;</td>
					    <td align="center">&nbsp;</td>
				    </s:if>
				    <s:elseif test='"2".equals(#fyk.zgbh)'>
				    	<td align="center">&nbsp;</td>
				    	<td align="center">√</td>
					    <td align="center">&nbsp;</td>
				    </s:elseif>
				    <s:else>
				    	<td align="center">&nbsp;</td>
				    	<td align="center">&nbsp;</td>
				    	<td align="center">√</td>
				    </s:else>
				    <s:if test='"1".equals(#fyk.hxbh)'>
				    	<td align="center">√</td>
					    <td align="center">&nbsp;</td>
					    <td align="center">&nbsp;</td>
				    </s:if>
				    <s:elseif test='"2".equals(#fyk.hxbh)'>
				    	<td align="center">&nbsp;</td>
				    	<td align="center">√</td>
					    <td align="center">&nbsp;</td>
				    </s:elseif>
				    <s:else>
				    	<td align="center">&nbsp;</td>
				    	<td align="center">&nbsp;</td>
				    	<td align="center">√</td>
				    </s:else>
				    <td align="center">${fyk.babyqjcs }</td>
				    <td align="center">${fyk.babyqjcgcs }</td>
			  	</tr>
			  </s:iterator>
			  <s:iterator begin="1" end="4-#fykLength" step="1">
			  	 <tr>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				  </tr>
			  </s:iterator>
			</table>
		</div>
	</div>
</div>
</body>
</powersi:html>