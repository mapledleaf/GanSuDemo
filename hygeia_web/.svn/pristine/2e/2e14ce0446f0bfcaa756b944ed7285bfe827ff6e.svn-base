<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String hospital_id = BizHelper.getAkb020();
	String hospital_name = BizHelper.getAkb021();
	String aaa027 = BizHelper.getAaa027();
	String aaa027_name = BizHelper.getAaa027Name();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
	String bkc406 = (String) request.getAttribute("bkc406");
	String bka006Filter = " data_value like '13C%' ";
%>
<powersi:html>
<head>
<powersi:head title="专家信息编辑" target="_self" />
<%@ include file="/pages/util/header/combogrid.jsp"%>
</head>
<body>
	<powersi:form id="mainform" disabled="true">
		<powersi:panelbox key="专家信息">
			<powersi:editorlayout cols="6">
				<powersi:editorlayout-row>
					<powersi:hidden id="bkc406" name="expertInfoDto.bkc406"
						value="${expertInfoDto.bkc406}" />
					<powersi:hidden id="akb020" name="expertInfoDto.akb020"
						value="${expertInfoDto.akb020}" />
					<powersi:hidden id="aae100" name="expertInfoDto.aae100" value="1" />
					<powersi:hidden id="aae016" name="expertInfoDto.aae016" value="${expertInfoDto.aae016}"/>
					<powersi:hidden id="feeinfo" name="feeinfo" />
				</powersi:editorlayout-row>

				<powersi:editorlayout-row>
					<powersi:editorlayout-button colspan="6">
						<powersi:button id="btSave" label="保 存" onclick="save()" />
						<powersi:button id="btClose" label="取 消"
							onclick="javascript:closeDialog();" />
					</powersi:editorlayout-button>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bkc274" name="expertInfoDto.bkc274"
						key="bkc274" label="编号" frozen="true" required="true"
						validate="maxSize[8]" value="${expertInfoDto.bkc274}" />
					<powersi:textfield id="bkc275" name="expertInfoDto.bkc275"
						key="bkc275" label="专家姓名" required="true"
						value="${expertInfoDto.bkc275}" validate="maxSize[20]" />
					<td rowspan="4" align="center" style="border: 1px solid #d5e4f1;">
						<img id="bkc292" alt="照片预览" width="100" height="auto">
					</td>
				</powersi:editorlayout-row>

				<powersi:editorlayout-row>
					<powersi:codeselect id="bkc272" name="expertInfoDto.bkc272"
						key="bkc272" codeType="bkc272" label="专家职称"
						value="${expertInfoDto.bkc272}" required="true" />
					<powersi:textfield id="bkc279" name="expertInfoDto.bkc279"
						key="bkc279" label="身份证号" required="true"
						value="${expertInfoDto.bkc279}" validate="idcard" />
				</powersi:editorlayout-row>

				<powersi:editorlayout-row>
					<powersi:codeselect id="bkc156" name="expertInfoDto.bkc156"
						label="专家所属科室" cssClass="select2" list="#request.deptList"
						headerKey="" required="true" value="${expertInfoDto.bkc156}"
						showValue="true" />
					<powersi:textfield id="bkc280" name="expertInfoDto.bkc280"
						key="bkc280" label="联系电话" required="true"
						value="${expertInfoDto.bkc280}" validate="maxSize[20]" />
				</powersi:editorlayout-row>

				<powersi:editorlayout-row>
					<powersi:codeselect id="bkc277" name="expertInfoDto.bkc277"
						key="bkc277" codeType="aac004" label="专家性别"
						value="${expertInfoDto.bkc277}" required="true" />
					<powersi:file id="imgFile" name="imgFile" label="头像照片"
						onchange="setImgSrc(this)" accept="image/*" />
				</powersi:editorlayout-row>

				<powersi:editorlayout-row>
					<powersi:textfield id="bkc412" name="expertInfoDto.bkc412"
						key="bkc412" label="专家级别" required="true"
						value="${expertInfoDto.bkc412}" />
					<powersi:textfield id="bkc408" name="expertInfoDto.bkc408"
						key="bkc408" label="专业" value="${expertInfoDto.bkc408}"
						validate="maxSize[50]" required="true" />				
				</powersi:editorlayout-row>

				<powersi:editorlayout-row>
					<powersi:textfield id="bkc409" name="expertInfoDto.bkc409"
						label="开始时间" mask="date" value="${expertInfoDto.bkc409}"
						format="dateFmt:'yyyy-MM-dd'" required="true" />
					<powersi:textfield id="bkc410" name="expertInfoDto.bkc410"
						label="截止时间" mask="date" value="${expertInfoDto.bkc410}"
						format="dateFmt:'yyyy-MM-dd'" required="true" />
				</powersi:editorlayout-row>

				<powersi:editorlayout-row>
					<powersi:textfield id="aae014" name="expertInfoDto.aae014"
						label="维护工号" value="${expertInfoDto.aae014}" required="true"
						readonly="true" />
					<powersi:textfield id="bae101" name="expertInfoDto.bae101"
						label="维护人姓名" value="${expertInfoDto.bae101}" required="true"
						readonly="true" />
				</powersi:editorlayout-row>
				
				<powersi:editorlayout-row>
					<powersi:textfield id="bkc407" name="expertInfoDto.bkc407"
						key="bkc407" label="固定电话" value="${expertInfoDto.bkc407}" />
					
						<%-- <powersi:textfield id="aae140" name="expertInfoDto.aae140"
						key="aae140" label="管辖的险种" value="${expertInfoDto.aae140}" /> --%>
						<powersi:hidden id="aae140" name="expertInfoDto.aae140" value="${expertInfoDto.aae140}" />
						
				</powersi:editorlayout-row>

				
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox key="待遇类型">
		<powersi:panelbox-toolbar>
			<powersi:button id="btn_enter" key="button_ok" onclick="insertfee()" />
			<powersi:button id="btn_delete" key="button_delete" onclick="deletefee()" />
		</powersi:panelbox-toolbar>
		<powersi:editorlayout cols="8">
			<powersi:editorlayout-row>
				<powersi:codeselect id="bka006"  name="bka006"
					codeType="bka006" label="待遇类型" value="" headerKey=""
					codeFilter="<%=bka006Filter%>" codeLocal="<%=aaa027%>" required="true" />
			</powersi:editorlayout-row>
		</powersi:editorlayout>
		<powersi:datagrid id="grid" delayLoad="true" checkbox="true"
			pageSize="10">
			<powersi:datagrid-column name="bka006" display="待遇编码" width="10%" />
			<powersi:datagrid-column name="bka006_name" display="待遇名称"
				width="10%" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
	<script type="text/javascript">
	window.onload = function(){		
	 	var myDate = new Date();
		var year = myDate.getFullYear();
		var month = (myDate.getMonth()+1).toString().length==1?"0"+(myDate.getMonth()+1).toString():(myDate.getMonth()+1).toString();
		var day = myDate.getDate().toString().length==1?"0"+myDate.getDate().toString():myDate.getDate().toString();
		var today= year+"-"+month+"-"+day; 
		$("#bkc409").val(today);
		$("#bkc410").val("2099-12-31");
	}
	
	$(function(){
		if($("#bkc406").val()!="")
		 {
			var bkc406 = $("#bkc406").val();
			loadPsTemplateTreat(bkc406);
			//加载图片信息
			$("#bkc292").attr("src","<%=path%>/medicare/HospManageAction!getImage.action?bkc406="+bkc406);
		 }
	});
	
	function loadPsTemplateTreat(bkc406) {
		 var params = "bkc406="+bkc406; 
		 postJSON("${rootPath}/medicare/HospManageAction!chooseTreatment.action",params,function (json)
			{
			    if(!checkJSONResult(json)){
				 return;
			    }else{
			    }
				if (json.errortype == 0) {
				var suss=json.data.suss;
				 if(suss==1)
				 {
				     var rowsize = json.data.feeinfo.length;
				if(rowsize>0){
				    var feeinfot=json.data.feeinfo;
					grid.loadData(feeinfot);   
					    
				}else{
					popupAlert("获取待遇信息失败！");
				}
				 
				 }
				 else
				 {
				 popupAlert(json.data.message);
				 }
				}
				else{
					 
					var mes=json.message;
					popupAlert(mes);
				}
			});
	}
	

	
	function insertfee() {
		var bka006_name = $("#bka006 option:selected").text();
		var bka006 = $("#bka006").val();
		if(bka006==null||bka006==""){
			popupAlert("请先选择待遇类型！");
			return;
		}	
		//start : 2018-01-05 lhy 为了不重复选择待遇类型
		var rows = grid.getAllData();
		var invalid = false;
  		//判断结果集
		$.each(rows, function(i, row){
			if (row['bka006'] == bka006) {
				invalid = true;
				popupAlert("请选择不一样的待遇类型！！");
				return false;
			}
		});	
		if(invalid){
  			return;
  		}
		// end 
		var jsonFee = {
			"bka006_name" : bka006_name,
			"bka006" : bka006,
		};
		grid.add(jsonFee);
		$("#stext").focus();
		$("#stext").val("");
		$("#bka006_name").val("");
	}
	
	
	function save(){
   	 //校验必填项
   	 	if($("#aae016").val()!="0"){
   	 		alert('只能修改未审核的数据！');
			return;
   	 	}
    	if(!checkFormValidtion()){
	  		return;
		}
    	var feeinfo = grid.getData();
		feeinfo = powersi.tostring(feeinfo);
		$("#feeinfo").val(encodeURI(feeinfo));
		if(feeinfo == '[]'){
			popupAlert("待遇类型不能为空！");
			return;
		}
       $("#mainform").ajaxSubmit( {   
	        url : "<%=path%>/medicare/HospManageAction!expertDetail.action?flag=2",
			datatype : "json",
			 success : function(json){ 
				     var json = JSON.parse(json);
					 if(!checkJSONResult(json)){
						 return;
					    }else{
					    }
	   				if (json.errortype == 0) {
		   				var len = powersi.length(json.data);
		   				if(len < 0){
		   					popupAlert("失败="+json.data.message);
		   				    return;
		   				}
		   				var suss=json.data.suss;
			   			 if(suss==1)
			   			 {
			   				 popupAlert("保存成功");
			   				 setDialogReturn("1");	
			   				 closeDialog();
			   			 }
			   			 else
			   			 {
			   				 popupAlert(json.data.message);
			   			 }
		   			
	   				} else {
	   					var mes=json.message;
	   					 popupAlert(mes);
	   				 }
           },
      		error:function(json){
      			if (!powersi.isnull(json.message)) {
					alert(json.message);
				}
           }   
		}); 
	}
	
	function setImgSrc(obj){
        var filepath=obj.value;
        var fileName = filepath.substring(filepath.lastIndexOf(".")+1).toLowerCase();//获取上传文件的后缀名
        if(fileName!="jpeg" && fileName!="jpg" && fileName!="png"&&fileName!="gif"&&fileName!="svg"){//判断上传文件的格式
            obj.value="";
            alert("请上传jpeg,jpg,png,gif,svg格式的图片！");
            return
        }else{//判断用户使用的浏览器以及取得图片地址
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
	
	function deletefee() {
		if (typeof (grid.getSelectedRows()) == "undefined"
				|| grid.getSelectedRows() == null
				|| grid.getSelectedRows() == "") {
			popupAlert("请选择要删除的待遇类型");
			return;
		}
		if (confirm("是否删除已选择的待遇类型?") == true) {
			grid.deleteSelectedRow();
		}
	}
	</script>
</body>
</powersi:html>
