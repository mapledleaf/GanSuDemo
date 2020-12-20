<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%
	request.setAttribute("path", request.getContextPath() + "/medicarespecial/specialManager");
	String aka083LisStr = request.getAttribute("dto.aka083LisStr")==null?"":(String)request.getAttribute("dto.aka083LisStr");
	String aka083Filter = null;
	if(null != aka083LisStr && !"".equals(aka083LisStr)) {
		aka083LisStr="'"+aka083LisStr+"'";
		aka083LisStr=aka083LisStr.replace(",", "','");//转成字符串输入
		aka083LisStr=aka083LisStr.replace("'',''", "','");//兼容参数格式问题('123','131')
		aka083LisStr=aka083LisStr.replace("''", "'");
		aka083Filter = "aaa102 in (" + aka083LisStr + ")";
	}
%>

<powersi:html>
	<base target="_self">
	<powersi:head title="新增申请" />
<body>
		<!-- 隐藏元素层 -->
		<div style="display: none">
			<powersi:textfield id="pageFlag" name="dto.pageFlag" label="页面标志"/>
			<powersi:textfield id="aae140" name="dto.aae140" key="aae140" label="险种类型"/>
		</div>
		
		<powersi:editorlayout cols="8">
			<tr>
				<powersi:codeselect id="aka083" name="dto.aka083" codeType="aka083" cols="4" codeFilter="<%=aka083Filter %>" label="业务选择" onselect="" onchange="showAttendLoad(this)"/>
			</tr>	
		</powersi:editorlayout>
		
		<powersi:panelbox title="业务选择" id="target1" style="display:none">
			<powersi:editorlayout cols="4">
				<table class="table_none" id="target2">
				</table>
			</powersi:editorlayout>	
		</powersi:panelbox>
		
		<iframe id="actframe" name="actframe" src="" width="100%" height="1000"></iframe>
</body>
<script type="text/javascript"> 
	var pheight=500
	var initFormInf;
	var initaka083='';
	$(document).ready(function() {
		setClientScreenWH();
		$("#actframe").attr('height', pheight);
		showApplyType();
		//actframe加载完后获取值
		$("#actframe").load(function(){  
			if($(document.getElementById('actframe').contentWindow.document).find('#mainForm').length > 0){  
				initFormInf=$("#mainForm",document.getElementById('actframe').contentWindow.document).serialize();
				initaka083=$("#aka083").val();
			}
		});
	});
	
	
	function setClientScreenWH() {
		pheight=0.9*$(window).height();
	}

	function  showAttendLoad (handle){
		if('' != $("#aka083").val()) {
			if($(document.getElementById('actframe').contentWindow.document).find('#mainForm').length > 0){ 
				if(initFormInf != $("#mainForm",document.getElementById('actframe').contentWindow.document).serialize()) {
					popupConfirm('已经录入了数据，确定放弃吗？','提示', function(yes){
						if(yes){
							showAttendLoadByAka083($("#aka083").val());
						}else{
							$("#aka083").val(initaka083);
							return;
						}
					});
				}else{
					showAttendLoadByAka083($("#aka083").val());
				}
				 
			}else{
				showAttendLoadByAka083($("#aka083").val());
			}
			
		} else {
			$('#actframe').attr("src", '');
			$("#target1").show();
		}
	}
	
	
	function showApplyType() {
		var table = [];
		if($("#aka083 option[value!='']").length > 1) {
			var tr = '';
			
			table.push('<tr>');
			$("#aka083 option[value!='']").each(function(index, element) {
				if('' != $(this).val()) {
					tr += '<td align="center">';
					tr += '	<a onclick="showAttendLoadByAka083(\'' + $(this).val() + '\')">';
					tr += $(this).text();
					tr += 	'</a>';
					tr += '</td>';
					
					if(index+1 != $("#aka083 option[value!='']").length) {
						if((index+1) % 4 == 0) {
							table.push(tr);
							table.push('</tr>');
							table.push('<tr>');
							tr = '';
						}
					} else { //最后一个
						table.push(tr);
						table.push('</tr>');
						tr = '';
					}
				}
			});
			$("#target2").append(table.join(''));
			$("#target1").show();
		}
	}
	
	function showAttendLoadByAka083 (aka083){
		$("#target1").hide();
		$("#aka083").val(aka083);
		if('' != aka083) {
			src = "${path}!getApplyAddPage.action?dto.aae140=" + $("#aae140").val() + "&dto.pageFlag=" + aka083 + "&dto.aka083=" + aka083 + "&dto.comFlag=21";
			$('#actframe').attr("src", src);
		}
	}
</script>	
</powersi:html>
<%@include file="../commonJsForSpecialApply.jsp"%> <!-- 引入特殊业务管理公共js方法 -->