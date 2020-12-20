<%@page import="java.util.Map"%>
<%@page import="com.powersi.biz.medicare.hosp.pojo.HospInfoDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	String path = request.getContextPath();
	String hospital_name = BizHelper.getAkb021();

	String aaa027 = BizHelper.getAaa027();
	String aaa027_name = BizHelper.getAaa027Name();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
	String bkc269=(String)request.getAttribute("bkc269");
%>
<powersi:html>
<head>
<powersi:head title="修改医师信息" target="_self" />
</head>
<body>
	<powersi:form id="mainform" disabled="true">
		<powersi:editorlayout cols="8">
			<powersi:editorlayout-row>
				<powersi:hidden id="bkc269" name="hospInfoDto.bkc269" />
				<powersi:hidden id="akb020" name="hospInfoDto.akb020" />
				<powersi:hidden id="aaa027" name="hospInfoDto.aaa027" />
				<powersi:hidden id="aae100" name="hospInfoDto.aae100" value="1" />
				<powersi:hidden id="aae016" name="hospInfoDto.aae016"/>
				<%-- <powersi:hidden id="bkc027" name="hospInfoDto.bkc027" />
				<powersi:hidden id="bkc028" name="hospInfoDto.bkc028" /> --%>
				<powersi:hidden id="bkc029" name="hospInfoDto.bkc029" />
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:editorlayout-button colspan="8">
					<powersi:button id="btSave" label="保 存" onclick="save()" />
					<powersi:button id="btClose" label="取 消"
						onclick="javascript:closeDialog();" />
				</powersi:editorlayout-button>
			</powersi:editorlayout-row>


			<powersi:editorlayout-row>
				<powersi:textfield id="bkc274" name="hospInfoDto.bkc274"
					key="bkc274" label="编号" frozen="true" required="true" validate="maxSize[8]" />
				<powersi:textfield id="bkc275" name="hospInfoDto.bkc275"
					key="bkc275" label="医师姓名" required="true" validate="maxSize[20]" onblur="PyAndWb()"/>
				<powersi:textfield id="bkc280" name="hospInfoDto.bkc280" colspan="2"
					key="bkc280" label="移动电话号码" validate="mobilephone" />
				<td rowspan="4" align="center" style="border: 1px solid #d5e4f1;" >
					<img id="bkc292" alt="照片预览" width="100" height="auto" 
					src="<%=path%>/medicare/HospManageAction!getImage.action?bkc269=<%=bkc269 %>"> 
				</td>
			</powersi:editorlayout-row>
			
			<powersi:editorlayout-row>
					<powersi:textfield id="aka020" name="hospInfoDto.aka020"
						key="aka020" label="拼音助记码" />
					<powersi:textfield id="aka021" name="hospInfoDto.aka021"
						key="aka021" label="五笔助记码"/>
					<powersi:textfield id="bkc279" name="hospInfoDto.bkc279" colspan="2"	
						key="bkc279" label="社会保障号" validate="idcard" />
			</powersi:editorlayout-row>
			
			<powersi:editorlayout-row>
				<powersi:codeselect id="bkc277" name="hospInfoDto.bkc277"
					key="bkc277" codeType="aac004" label="医师性别" required="true" />
				<powersi:codeselect id="bkc276" name="hospInfoDto.bkc276"
					key="bkc276" codeType="bkc276" label="医师执业情况" required="true" />
				<powersi:textfield id="bkc278" name="hospInfoDto.bkc278" colspan="2"	
					key="bkc278" label="出生日期" mask="date" required="true"
					format="dateFmt:'yyyy-MM-dd'" /> 
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:codeselect id="bkc156" name="hospInfoDto.bkc156"
					label="医师所属科室" cssClass="select1" list="#request.deptList"
					headerKey="" headerValue="请选择..." required="true" showValue="true" />
				<powersi:codeselect id="bke952" name="hospInfoDto.bke952"
					key="bke952" label="门特责任医师种类" codeType="special_type" />
				<powersi:codeselect id="bkc282" name="hospInfoDto.bkc282" colspan="2"	
					key="bkc282" label="申报专家库成员" codeType="expert_flag" />
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:codeselect id="bkc270" name="hospInfoDto.bkc270"
					key="bkc270" label="医师职务" codeType="bkc270" required="true" />
				<powersi:codeselect id="bkc272" name="hospInfoDto.bkc272"
					key="bkc272" label="医师职称" codeType="bkc272" required="true" />
				<powersi:codeselect id="bkc289" name="hospInfoDto.bkc289"
					key="bkc289" label="进修标志" codeType="yes_no" />
				<powersi:file id="imgFile" name="imgFile" label="头像照片"
					colspan="6" onchange="setImgSrc(this)" accept="image/*"/>
			</powersi:editorlayout-row>



			<powersi:editorlayout-row>
				<powersi:codeselect id="bkc283" name="hospInfoDto.bkc283"
					key="bkc283" label="许可医疗业务" codeType="yes_no" />
				<powersi:codeselect id="bkc284" name="hospInfoDto.bkc284"
					key="bkc284" label="许可生育业务" codeType="yes_no" />
				<powersi:codeselect id="bkc285" name="hospInfoDto.bkc285"
					key="bkc285" label="许可工商业务" codeType="yes_no" />
				<powersi:textfield id="bkc281" name="hospInfoDto.bkc281"
					key="bkc281" label="专业" validate="maxSize[50]" />
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:codeselect id="bkc286" name="hospInfoDto.bkc286"
					key="bkc286" label="处方权" codeType="yes_no" />
				<powersi:codeselect id="bke953" name="hospInfoDto.bke953"
					key="bke953" label="特殊技术资质" codeType="special_lisence" />
				<powersi:textfield id="bke955" name="hospInfoDto.bke955"
					key="bke955" label="医师资格证号" validate="maxSize[40]" />
				<powersi:codeselect id="bke954" name="hospInfoDto.bke954"
					key="bke954" label="特殊项目资质" codeType="special_item" />
			</powersi:editorlayout-row>
			
			<powersi:editorlayout-row>
				<powersi:codeselect id="bko003" name="hospInfoDto.bko003"
					key="bko003" label="定岗标志" codeType="yes_no"/>
				<powersi:textfield id="bkc028" name="hospInfoDto.bkc028"
					label="维护工号" required="true" readonly="true" />
				<powersi:textfield id="bkc027" name="hospInfoDto.bkc027"
					label="维护人姓名" required="true" readonly="true" />
				<powersi:textfield id="bkc029" name="hospInfoDto.bkc029"
					label="维护时间" mask="date" required="true" readonly="true" />
			</powersi:editorlayout-row>
			
			<powersi:editorlayout-row>
				<powersi:textarea id="bkc291" name="hospInfoDto.bkc291" key="bkc291"
					label="简历" cols="2" colspan="7" validate="maxSize[1100]" />
			</powersi:editorlayout-row>

			<%-- <powersi:editorlayout-row>
				<powersi:textfield id="bkc274" name="hospInfoDto.bkc274"
					key="bkc274" label="编号" frozen="true" readonly="true"
					validate="integer,maxSize[8]" />
				<powersi:textfield id="bkc275" name="hospInfoDto.bkc275"
					key="bkc275" label="医师姓名" readonly="true" validate="maxSize[20]" />
				<powersi:codeselect id="bkc277" name="hospInfoDto.bkc277"
					key="bkc277" codeType="aac004" label="医师性别" required="true"
					readonly="true" />
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:codeselect id="bkc276" name="hospInfoDto.bkc276"
					key="bkc276" codeType="bkc276" label="医师执业情况" required="true" />
				<powersi:textfield id="bkc279" name="hospInfoDto.bkc279"
					key="bkc279" label="社会保障号" required="true" validate="idcard" />
				<powersi:textfield id="bkc278" name="hospInfoDto.bkc278"
					key="bkc278" label="出生日期" mask="date" required="true"  format="dateFmt:'yyyy-MM-dd'" />
			</powersi:editorlayout-row>


			<powersi:editorlayout-row>
				<powersi:codeselect id="bkc156" name="hospInfoDto.bkc156"
					label="医师所属科室" cssClass="select2" list="#request.deptList"
					headerKey="" headerValue="请选择..." required="true" showValue="true" />
				<powersi:codeselect id="bke952" name="hospInfoDto.bke952"
					key="bke952" label="门特责任医师种类" codeType="special_type" />
				<powersi:codeselect id="bkc282" name="hospInfoDto.bkc282"
					key="bkc282" label="是否申报为本市社会保险医疗专家库成员" codeType="expert_flag" />
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:textfield id="bkc280" name="hospInfoDto.bkc280"
					key="bkc280" label="移动电话号码" validate="mobilephone" />
				<powersi:codeselect id="bkc270" name="hospInfoDto.bkc270"
					key="bkc270" label="医师职务" codeType="bkc270" required="true" />
				<powersi:codeselect id="bkc272" name="hospInfoDto.bkc272"
					key="bkc272" label="医师职称" codeType="bkc272" required="true" />
			</powersi:editorlayout-row>
			
			<powersi:editorlayout-row>
				<powersi:textfield id="bkc281" name="hospInfoDto.bkc281"
					key="bkc281" label="专业"  validate="maxSize[50]" />
				<powersi:textfield id="aka020" name="hospInfoDto.aka020"
					key="aka020" label="拼音助记码" readonly="true" />
				<powersi:textfield id="aka021" name="hospInfoDto.aka021"
					key="aka021" label="五笔助记码" readonly="true" />
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:codeselect id="bke953" name="hospInfoDto.bke953"
					key="bke953" label="特殊技术资质" codeType="special_lisence" />
				<powersi:textfield id="bke955" name="hospInfoDto.bke955"
					key="bke955" label="执业医师资格证编号" validate="maxSize[40]" />
				<powersi:codeselect id="bke954" name="hospInfoDto.bke954"
					key="bke954" label="特殊项目资质" codeType="special_item" />
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:textarea id="bkc291" name="hospInfoDto.bkc291" key="bkc291"
					label="简历" cols="2" colspan="5" validate="maxSize[1100]" />
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:textfield id="aae011" name="hospInfoDto.aae011"
					label="维护工号" required="true" readonly="true" />
				<powersi:textfield id="bae100" name="hospInfoDto.bae100"
					label="维护人姓名" required="true" readonly="true" />
				<powersi:textfield id="aae036" name="hospInfoDto.aae036"
					label="维护时间" mask="date" required="true" readonly="true" />
			</powersi:editorlayout-row> --%>

		</powersi:editorlayout>

	</powersi:form>

	<powersi:errors />
	<script type="text/javascript">
       		var curItemData = "";
	   	     $(document).ready(function(){
	   	         curItemData = $("#mainform").serialize();
	   	      	 bkc292Str = $("#bkc292").val();
	   	         
	   	        var myDate = new Date();
	      		var year = myDate.getFullYear();
	      		var month = (myDate.getMonth()+1).toString().length==1?"0"+(myDate.getMonth()+1).toString():(myDate.getMonth()+1).toString();
	      		var day = myDate.getDate().toString().length==1?"0"+myDate.getDate().toString():myDate.getDate().toString();

     			
     		  	$('#bae100').val("<%=userName%>");
            	$('#aae011').val("<%=loginUser%>");
            	$('#aae036').val(year+"-"+month+"-"+day);
            	
            	
	   		 	$('#akb021').val("<%=hospital_name%>");
	          	
	          	if($("#akb020").val() == '' || $("#akb020").val() == null ){
	    			alert("医院编码不能为空，请检查登陆信息！");
	    	    	return;
	    		}
	          	
          		
	   	     });
	   	//判断上传文件是否为图片以及图片预览	
        	function setImgSrc(obj){
        		var filepath=obj.value;
        		var url;
        	    var fileName = filepath.substring(filepath.lastIndexOf(".")+1).toLowerCase();
        		if(fileName!="jpeg" && fileName!="jpg" && fileName!="png"&&fileName!="gif"&&fileName!="svg"){
        			obj.value="";
        			alert("请上传jpeg,jpg,png,gif,svg格式的图片！");
        			return
        		}else{
        			if (navigator.userAgent.indexOf("MSIE")>=1) { // IE
        				url = document.getElementById("imgFile").value;
        			} else if(navigator.userAgent.indexOf("Firefox")>0) { // Firefox
        				url = window.URL.createObjectURL(document.getElementById("imgFile").files.item(0));
        			} else if(navigator.userAgent.indexOf("Chrome")>0) { // Chrome
        				url = window.URL.createObjectURL(document.getElementById("imgFile").files.item(0));
        			}
        			$("#bkc292").attr("src",url);
        		}
       	}
        	function PyAndWb() {
       			/* var strInput=$("#bkc275").val();
       		    var str= encodeURIComponent(strInput); */
       			var saveItemData = $("#mainform").serialize();
       			postJSON("<%=path%>/medicare/HospManageAction!getPyAndWb.action",saveItemData,
       					function(json) {
       						if (!checkJSONResult(json)) {
       							return;
       						}
       						$("#aka020").val(json.data.aka020);
       						$("#aka021").val(json.data.aka021);
       			});
    		}	
	    function save(){
	    	 //校验必填项
	     	if(!checkFormValidtion())
	     	{
		  		return;
			}
	         var saveItemData = $("#mainform").serialize();
	         if(saveItemData == curItemData && bkc292Str == $("#bkc292")[0].src){
			    alert("没有修改，无需保存！");
			    return;
			 }
	        <%--  postJSON("<%=path%>/medicare/HospManageAction!updateHospDoctor.action",
					saveItemData, saveSuccess); --%>
		 $("#mainform").ajaxSubmit( {   
	 	        url : "<%=path%>/medicare/HospManageAction!updateHospDoctor.action",
	 			datatype : "json",
	 			 success : function(json){ 
	        		var jsonData = jQuery.parseJSON(json);   
	        		var data = jsonData.data;
	        		alert(jsonData.data);
	        		if(jsonData.data=="修改医师信息成功！"){
	        			closeDialog();
	        		}
	            },  
	       		error:function(json){
	       			if (!powersi.isnull(json.message)) {
						alert(json.message);
					}
	            }   
	 		}); 
       
		}

		function saveSuccess(json) {
			if (!checkJSONResult(json)) {
				return;
			}
			alert(json.data);
			closeDialog();
		}
	</script>
</body>
</powersi:html>
