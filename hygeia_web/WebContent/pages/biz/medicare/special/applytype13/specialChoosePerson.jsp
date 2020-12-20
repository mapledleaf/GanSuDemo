<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="si" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<powersi:html>
<si:head title="人员选择" target="_self" />
<body>
	<display:table name="rsPersonList" id="row" requestURI="/medicaretag/MedicareChoosePersonAction!getPersonList.action">
		<display:column title="选择">
			<input type="radio" name="rowdata"
				value="{'aac001':'${row.aac001}',
						'aac002':'${row.aac002}',
						'aac003':'${row.aac003}',
						'aac004':'${row.aac004}',
						'aac066':'${row.aac066}',
						'baa027':'${row.baa027}'}" />
		</display:column>
		<display:column property="aac001" />
		<display:column property="aac003" />
		<display:column property="aac002" />
		<display:column property="aac004" codetable="aac004" />
		<display:column property="aac066" codetable="aac066"/>
		<display:column property="baa027" codetable="aaa027" title="参保统筹区"/>
	</display:table>
	<script type="text/javascript">
		function chooseone() {
			var retValue = $(':radio[name="rowdata"]:checked').val();
			if (retValue == null || retValue == "") {
				alert('请选择人员!');
				return;
			}
			closeDialog(retValue);
		}
	
		$(function() {
			$('#row>tbody tr').bind('click', function() {
				$(this).find('[type=radio]').attr('checked', true);
				chooseone();
			});
		});
	</script>

</body>
</powersi:html>