<%@ taglib uri="/struts-tags" prefix="s"%>
<s:if test="hasFieldErrors()">
	<s:iterator value="fieldErrors">
		<s:iterator value="value" status="statu">
			<s:set var="index" value="#statu.index" />
			<s:set var="msg"
				value="(#msg==null?'':#msg)+value.get(#request.index).toString()+'\n'" />
		</s:iterator>
	</s:iterator>
</s:if>

<s:if test="#request.hygeiaErrors != null">
	<s:set var="msg" value="(#msg == null ? '' : #msg) + #request.hygeiaErrors" />
</s:if>
<s:else>
	<s:if test="hasActionErrors()">
		<s:iterator value="actionErrors" var="actionerror">
			<s:set var="msg" value="(#msg == null ? '' : #msg) + #actionerror +'\n'" />
		</s:iterator>
	</s:if>
	
	<s:if test="hasActionMessages()">
		<s:iterator value="actionMessages" var="actionmsg">
			<s:set var="msg" value="(#msg == null ? '' : #msg) + #actionmsg +'\n'" />
		</s:iterator>
	</s:if>
</s:else>

<s:if test="#msg.length()>0">
	<script type="text/javascript">
		var alertMsg = function() {
			alert("<s:property escape='false' value='#msg.replaceAll(\'\\\"\', \'\\\\\\\\\\\"\').replaceAll(\'\t\', \'\\\\\\\t\').replaceAll(\'\r\', \'\\\\\\\r\').replaceAll(\'\n\', \'\\\\\\\n\')' />");
		};
		
		if (window.attachEvent) {
			window.attachEvent("onload", alertMsg);
		} else {
			window.addEventListener("load", alertMsg, false);
		}
    </script>
</s:if>