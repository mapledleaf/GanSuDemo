<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<powersi:html>
<head>
<powersi:head title="dubbo测试" />
<script type="text/javascript">
	function callChuangZhi(method){
	
		postAjax(rootPath + "/sample/Sample!callChuangZhi.action", {
			"method": method,
			"text": $("#okText1").val()
		}, function(ret){
			if(checkResult(ret)) {
				//alert(powersi.tostring(ret.data));
				if(ret.data.hasOwnProperty("aac001")){
					  $.each(ret.data,
				    	      function(key, value) {
				    	        if($("#"+key).length > 0){ 
				    	           $("#"+ key).val(value);
				    	         }
					         } 
				     
				      );
				}else{
				 	var i=0;
				 	var str="<tr>";
				     $.each(ret.data,
				    	      function(key, value) {
				    	         $("#txtab>tbody tr").remove();
				    	         str =  str+"<td class=\"tdLabel\"><label class=\"textLabel\"><span>"+ key +"</span></label></td>" 
				    	        		      + "<td class=\"tdInput\"><input type=\"text\" value="+value+" class=\"text\"/></td>";
				                  i++;
				    	         if(i%2==0){
				    	        	 str =  str+"</tr>";
				    	         }
				    	      }
				     
				     );
			         $('#txtab>tbody').append(str+"</tr>");
				}
				
				callInsure();
			}
		});
	}
	
	function callInsure() {
			var	aae140 = '职工社会医疗保险';
			var	aac049 = '2016-03-20';
			var	aac066 = '在职人员 ';
			var	aac031 = '正常参保';
			var	aaa027 = '广州市本级';
			var	aab069 = '广州曼伯特机械设备有限公司';
			var	aae100 = '有效';
   	    $("#row>tbody tr").remove();
		$('#row tr').remove('.empty');
		$('#row>tbody').append("<tr><td>"+ aae140 +"</td><td>"
				+ aac049+ "</td><td>"
				+ aac066 + "</td><td>"
				+ aac031 + "</td><td>"
				+ aaa027 + "</td><td>"
				+ aab069 + "</td><td>"
				+ aae100 + "</td></tr>");
	}
	
</script>
</head>
<body>
	<powersi:groupbox title="查询条件">
			<powersi:editorlayout cols="6">
				<td class="tdInput" colspan="4"><input type="text" id="okText1"  style="" placeholder="输入文本" /></td>
				<td class="tdInput" colspan="1"><input type="button" value="查询neusoft" class="button" onclick="callChuangZhi('neusoft')" /></td>
			</powersi:editorlayout>
	</powersi:groupbox>
	
	<br />
	 <powersi:tabbedpanel id="divTabs">
			<powersi:tab id="tab1" target="divTab1" label="基本信息" />
			<powersi:tab id="tab2" target="divTab2" label="参保信息" />
			<div id="divTab1">	
			   <powersi:groupbox title="人员信息">
			
				<powersi:editorlayout cols="4" id="txtab">
					<tr>
						<powersi:textfield name="personBaseInfo.aac001" key="个人电脑号" id="aac001"></powersi:textfield>
						<powersi:textfield name="personBaseInfo.aac012gz" id="aac012" key="个人身份"></powersi:textfield>
					</tr>
					<tr>
						<powersi:textfield name="personBaseInfo.aab001" key="单位电脑号" id="aab001"></powersi:textfield>
						<powersi:textfield name="personBaseInfo.aab069" key="单位名称" id="aab069"></powersi:textfield>
					</tr>
					<tr>
						<powersi:textfield name="personBaseInfo.aac002" id="aac002"
							required="true" key="社会保障号码"></powersi:textfield>
						<powersi:textfield name="personBaseInfo.aac058" required="true"
							 id="aac058" key="证件类型"></powersi:textfield>
					</tr>
					<tr>
						<powersi:textfield name="personBaseInfo.aac003" id="aac003"
							required="true" key="姓名"></powersi:textfield>
						<powersi:textfield name="personBaseInfo.aac006" id="aac006" onblur="calcAge()"
							required="true" maxlength="40" key="出生日期" mask="date"></powersi:textfield>
					</tr>
					<tr>
						<powersi:textfield name="personBaseInfo.aac004" id="aac004"
							required="true" key="性别"></powersi:textfield>
						<powersi:textfield name="personBaseInfo.aac009" required="true"
							id="aac009"  key="户口性质"></powersi:textfield>
					</tr>
		
					<tr>
		
		
						<powersi:textfield name="personBaseInfo.aac010" required="true"
							id="aac010" maxlength="40" key="户口所在地址  "></powersi:textfield>
						<powersi:textfield name="personBaseInfo.aab401" 
							id="aab401" key="户口簿编号 "></powersi:textfield>
					</tr>
					<tr>
		
						<powersi:textfield name="personBaseInfo.aae004" required="true"
							id="aae004" key="联系人姓名 "></powersi:textfield>
						<powersi:textfield name="personBaseInfo.aae005" 
							id="aae005" key="联系电话 " validate="phone"></powersi:textfield>
					</tr>
					<tr>
		
						<powersi:textfield name="personBaseInfo.aae006" key="地址" id="aae006"></powersi:textfield>
						<powersi:textfield name="personBaseInfo.aae007" key="邮政编码  " id="aae007"
							maxlength="6"></powersi:textfield>
					</tr>
					
					<tr>
						<powersi:textfield name="personBaseInfo.aac016" 
							id="aac016" key="就业状态  "></powersi:textfield>
						<powersi:textfield mask="date" name="personBaseInfo.aac007" id="aac007"
							key="参加工作日期  "></powersi:textfield>
					</tr>
		
				
					<tr>
		
						<powersi:textfield name="personBaseInfo.aac084s" 
							id="aac084s" key="离退休标志"></powersi:textfield>
		
						<powersi:textfield mask="date" name="personBaseInfo.bac002" id="aic162"
							key="退休日期"></powersi:textfield>
					</tr>
				
					<tr>
						<powersi:textfield name="personBaseInfo.aac005" 
							id="aac005"  key="民族"></powersi:textfield>
						<powersi:textfield name="personBaseInfo.aac020" 
							id="aac020" key="行政职务(级别)"></powersi:textfield>
						
		
		
		
					</tr>
					<tr>
						<powersi:textfield name="personBaseInfo.aaa027" required="true"
							id="aaa027" key="统筹区编码  "></powersi:textfield>
						<powersi:textfield name="personBaseInfo.aae013" key="备注  " 
							maxlength="40" id="aae013"></powersi:textfield>
						<powersi:hidden name="personBaseInfo.aab301" key="aab301" id="aab301"></powersi:hidden>
					</tr>
					<tr>
						<powersi:textfield name="personBaseInfo.akc021s" required="true"
							id="akc021s" label="医疗补充分类"></powersi:textfield>
						<powersi:textfield name="personBaseInfo.bac032s" required="true"
							id="bac032s"  label="困难人员标志"></powersi:textfield>
					</tr>
					<tr>
						<powersi:textfield name="personBaseInfo.bac021_n" key="bac021_n" 
							maxlength="40" id="bac021_n" label="军龄视同缴费年限"></powersi:textfield>
					</tr>
					
				</powersi:editorlayout>
		
		
	          </powersi:groupbox>
		   </div>
		   <div id="divTab2">
		   <powersi:groupbox title="参保险种信息">
				<display:table export="true" name="perInsuredList" id="row" requestURI="personaIntegratedQueryAction!perInsuredQuery.action">
						<display:column property="aae140"  titleKey="险种类型" sortable="true"/>
						<display:column property="aac049"  title="首次参保年月"  format="{0,date,yyyy-MM-dd }"  sortable="true"/>
						<display:column property="aac066"   titleKey="参保身份" sortable="true" />
						<display:column property="aac031"    titleKey="个人缴费状态" sortable="true" />
						<display:column property="aaa027"    titleKey="统筹区编码" sortable="true" />
						<display:column property="aab069"  titleKey="单位名称"  /> 
						<display:column  property="aae100" value="有效" title="数据状态"  sortable="true" />
				</display:table>
			</powersi:groupbox>
		   </div>
	  </powersi:tabbedpanel>
	<powersi:errors />
</body>
</powersi:html>