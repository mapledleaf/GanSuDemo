<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<powersi:html>
<powersi:head title="医院登记信息抓取" />
<%@ include file="/pages/util/header/combogrid.jsp"%>
		<powersi:editorlayout cols="15%,70%,15%" style="margin-top:60px;">
    		<powersi:editorlayout-row>
    			<powersi:textfield id="akc190"
			name="akc190" label="住院号" readonly="false" />
			<powersi:buttons>
				<powersi:button id="send" label="提交" onclick="getInhospInfo();"></powersi:button>
			</powersi:buttons>
    		</powersi:editorlayout-row>
   		</powersi:editorlayout>
<script type="text/javascript">

function getInhospInfo(){
	if($("#akc190").val() == null || $("#akc190").val() == ''){
		popupAlert("住院号不能为空！");
		return;
	}
	var url = "${rootPath}/universal/DiagnoseQueryAction_HIS!regInfoAgain.action?akc190="+$("#akc190").val()+"&type=ZYDJ";
	postJSON(url,{},importCalcInfo);
}

//回掉函数
function importCalcInfo(ret){
	if(ret.message == '-1'){
		popupAlert("存在正在重传的数据，请稍后重试！", "提示", "info");
		return;
	}else if(ret.message == '0'){
		popupSuccess("设置重传成功！");
		closePage();
	}
}

//关闭页面
function closePage(){
	closeDialog();
}
 
</script>
</powersi:html>