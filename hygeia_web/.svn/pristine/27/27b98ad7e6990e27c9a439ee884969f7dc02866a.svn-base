<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="金额大小写转换" />
<script type="text/javascript">
	function chge(m) {
		S('moneyUpper').value = money2cap(m);
		
		var n = powersi.tonumber(m).toFixed(2);
		S('moneyLower').value = powersi.tothousands(n);
	}
	$(function(){
		$("#num").keydown(function(event){
			if ( event.which == 13 ) {
				event.preventDefault();
				chge($(this).val());
			}
		});
	});
</script>
</head>
<body>
	<div class="divCenter" style="width:50%;">
		<powersi:form disabled="true" namespace="/sample" action="Sample">
			<powersi:groupbox title="金额大小写转换">
				<powersi:editorlayout cols="20%,80%">
					<tr>
						<powersi:textfield id="num" name="num" label="输入数字" required="true"
							onchange="chge(this.value)" buttonId="btn" buttonIcon="icon-exchange" onbuttonclick="chge(S('num').value)"></powersi:textfield>
					</tr>
					<tr>
						<powersi:textfield id="moneyUpper" name="moneyUpper" label="大写金额" readonly="true"></powersi:textfield>
					</tr>
					<tr>
						<powersi:textfield id="moneyLower" name="moneyLower" label="小写金额" readonly="true"></powersi:textfield>
					</tr>
				</powersi:editorlayout>
			</powersi:groupbox>
		</powersi:form>
	</div>
	
	<powersi:errors />
</body>
</powersi:html>