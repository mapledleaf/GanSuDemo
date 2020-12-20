<%@ tag language="java" pageEncoding="UTF-8" 
display-name="历史待遇信息" description="历史待遇信息" %>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="struts" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%
	String path = request.getContextPath();
%>
<powersi:groupbox title="审核记录信息">
	<powersi:datagrid id="auditGrid"  name = "auditList"   height="280px" width="100%" >
		<powersi:datagrid-column name="aae016" label="审核状态"  width="150px"/>
		<powersi:datagrid-column name="aae014" label="审核人"  width="150px"/>
		<powersi:datagrid-column name="aae015" label="审核时间"  width="150px"/>
		<powersi:datagrid-column name="aae017" label="审核意见"  width="250px"/>
		<powersi:datagrid-column name="aaz170" hide="true" />
	</powersi:datagrid>
</powersi:groupbox>