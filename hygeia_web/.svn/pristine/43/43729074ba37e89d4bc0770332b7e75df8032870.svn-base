<%@ tag pageEncoding="GBK" body-content="empty" small-icon=""
	display-name="取人员信息" description="取人员信息"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="s" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%
%>
<script type="text/javascript">
	function getPerson(para) {
		if (event.keyCode == '13') {
			try {
				var e = window.event || event;
				if (window.event) {
					e.cancelBubble = true;
				} else {
					e.stopPropagation();
				}
				event.returnValue = false;
			} catch (ex) {
			}
	
			var filed_name = para.id;
			if ('querystring' == filed_name) {
				if (isNotBlank($('#querystring').attr("value"))) {
					var queryExpr = $('#querystring').val();
					var queryUrl = rootPath + "/medicarebiz/ChoosePersonAction!getPersonInfo.action?queryExpr="+queryExpr;
					var indi_id = "";
					if (queryExpr != null && queryExpr != "") {

						postJSON(rootPath + "/medicarebiz/ChoosePersonAction!getPersonInfo.action?queryExpr="+queryExpr,{"querystring" : queryExpr},function(json){
							if (!checkJSONResult(json)) {
								return;
							}
							
							if(json.data.length>1){
								var personinfo = powersi.tostring(json.data);
								
								popupDialog({
									url: rootPath + "/pages/biz/medicare/comm/ChoosePerson.jsp?personinfo="+encodeURI(encodeURI(personinfo)),
									onClosed: function() {
										var ret = this.returnValue;
										if(ret!=null){
											indi_id = ret.aac001;
											$("#indi_id").val(ret.aac001);
											$("#idcard").val(ret.aac002);
											$("#name").val(ret.aac003);
											
											afterChoosePerson(indi_id);
										}
									}
								}, 400, 500);
							}else{
								indi_id = json.data[0].aac001;
								$("#indi_id").val(json.data[0].aac001);
								$("#idcard").val(json.data[0].aac002);
								$("#name").val(json.data[0].aac003);
								
								afterChoosePerson(indi_id);							
							}
						});

					} else {
						alert("请录入正确的查询条件");
					}
				}
			}
		}
	}
	
	function readic(){
		try{ 
			var objCard = document.getElementById("cardControl");
	
			if (objCard.object == null) {
			    alert("请先安装读卡插件！");
			}
		}
		catch(ex){
			alert("请先安装读卡插件！");
		}
	}
	
	function begingInput(){
		if($("#querystring").val()=='请输入信息回车！'){
			document.getElementById("querystring").value = '';
		}
	}
	
	//请在主页面用取到的电脑号，调用相应的中心控制类取值
	/* function afterChoosePerson(indi_id){
		alert("选择的人员为："+indi_id);
	} */

</script>


<s:editorlayout cols="8">
	<tr>
		<s:textfield id="querystring" key="查询条件" value='请输入信息回车！'
				buttonText="读卡" buttonId="btnreadic" onKeyDown="getPerson(this)" onclick="begingInput();" 
				onbuttonclick="readic()" />
		<s:textfield id="indi_id" key="电脑号" readonly="true" />
		<s:textfield id="name" key="姓名" readonly="true" />
		<s:textfield id="idcard" key="身份证号" readonly="true" />
	</tr>
</s:editorlayout>
