<%@ tag pageEncoding="UTF-8" body-content="empty" small-icon=""
	display-name="转换码值" description="转换码值"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<%
	String path = request.getContextPath(); 
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
		
<script type="text/javascript">
	/* 字典项渲染函数  解决loadData不能自动加载字典问题*/
	function renderDictionary(rowdata, index, value,arguments) {

		if(value!=null&&value !=""){
			var list = window[arguments.code];
			for(var i in list){
				if(list[i].id == value  ){
					return list[i].text;
				}
			}
			return '<span title="未匹配到字典项">'+value+'</span>';
		}else{
			return "";
		}
	}
</script>

		<!-- ------------字典项的值---------- -->
		<!--   -->
		<powersi:codetable id="akb023" codeType="akb023"></powersi:codetable>
		<!--   -->
		<powersi:codetable id="bka941" codeType="bka941"></powersi:codetable>
		<!--   -->
		<powersi:codetable id="bkc233" codeType="bkc233"></powersi:codetable>
		<!--   -->
		<powersi:codetable id="bke931" codeType="bke931"></powersi:codetable>
		<!--   -->
		<powersi:codetable id="akb012" codeType="akb012"></powersi:codetable>
		<!--   -->
		<powersi:codetable id="bkc110" codeType="bkc110"></powersi:codetable>
		<!--   -->
		<powersi:codetable id="aka101" codeType="aka101"></powersi:codetable>
		<!--   -->
		<powersi:codetable id="aae100" codeType="aae100"></powersi:codetable>
		<!--   -->
		<powersi:codetable id="aae016_hosparea" codeType="aae016_hosparea"></powersi:codetable>
		<!--   -->
		<powersi:codetable id="aaa027" codeType="aaa027"></powersi:codetable>
		<!--   -->
		<powersi:codetable id="bke074" codeType="bke074"></powersi:codetable>
		<!--   -->
		<powersi:codetable id="aab020" codeType="aab020"></powersi:codetable>
		<!--   -->
		<powersi:codetable id="bkc021" codeType="bkc021"></powersi:codetable>
		<!--   -->
		<powersi:codetable id="bkc111" codeType="bkc111"></powersi:codetable>
		<!--   -->
		<powersi:codetable id="bkc270" codeType="bkc270"></powersi:codetable>
		<!--   -->
		<powersi:codetable id="bke947" codeType="bke947"></powersi:codetable>
		<!--   -->
		<powersi:codetable id="bkc272" codeType="bkc272"></powersi:codetable>
		<!--   -->
		<powersi:codetable id="bke952" codeType="bke952"></powersi:codetable>
		<powersi:codetable id="bke953" codeType="bke953"></powersi:codetable>
		<powersi:codetable id="bke954" codeType="bke954"></powersi:codetable>
		<powersi:codetable id="bac001" codeType="bac001"></powersi:codetable>
		<!-- 业务类型 -->
		<powersi:codetable id="aka130" codeType="aka130"></powersi:codetable>
		<powersi:codetable id="bka154" codeType="bka154"></powersi:codetable>
		
		<!-- 出纳确认状态0 1 2  -->
		<powersi:codetable id="aae016_cashier" codeType="aae016_cashier"></powersi:codetable>
		<!-- 拨付对象类型H C P  -->
		<powersi:codetable id="bka087" codeType="bka087"></powersi:codetable>
		
		<powersi:codetable id="bka122List" codeType="bka122"></powersi:codetable>
		<powersi:codetable id="sexList" codeType="sex"></powersi:codetable>
		<!-- 		人员类别 -->
		<powersi:codetable id="bka004List" codeType="bka004"></powersi:codetable>
		<powersi:codetable id="bka212_displayList" codeType="bka212_display"></powersi:codetable>
		<powersi:codetable id="bkc110List" codeType="bkc110"></powersi:codetable>
		<powersi:codetable id="bka127List" codeType="bka127"></powersi:codetable>
		<powersi:codetable id="bka006List" codeType="bka006"></powersi:codetable>
		<powersi:codetable id="aka130List" codeType="aka130"></powersi:codetable>
		<powersi:codetable id="bka109List" codeType="bka109"></powersi:codetable>
		<powersi:codetable id="aka063List" codeType="aka063"></powersi:codetable>
		
		
		<!-- 性别 -->
		<powersi:codetable id="aac004" codeType="aac004"></powersi:codetable>
		<!-- 申请机构 -->
		<powersi:codetable id="bke035" codeType="bke035"></powersi:codetable>
		<!-- 人员类别 -->
		<powersi:codetable id="aac066" codeType="aac066_all"></powersi:codetable>
		<!-- 待遇类型 -->
		<powersi:codetable id="bka006" codeType="bka006"></powersi:codetable>
		<!-- 审核状态拨付yanliu -->
		<powersi:codetable id="aae016" codeType="aae016"></powersi:codetable>
		
		<powersi:codetable id="bab040" codeType="bab040"></powersi:codetable>
		<powersi:codetable id="aaa007" codeType="aaa007"></powersi:codetable>
		<powersi:codetable id="blc569" codeType="blc569"></powersi:codetable>
		<powersi:codetable id="bka035" codeType="bka035"></powersi:codetable>
		<powersi:codetable id="aaa027jc" codeType="aaa027jc"></powersi:codetable>
		<powersi:codetable id="decltype" codeType="decltype"></powersi:codetable>
		<powersi:codetable id="bkb161" codeType="bkb161"></powersi:codetable>
		<powersi:codetable id="aab019" codeType="aab019"></powersi:codetable>
		<powersi:codetable id="aka078" codeType="aka078"></powersi:codetable>
		<powersi:codetable id="bka004" codeType="bka004"></powersi:codetable>
		<powersi:codetable id="aaa157_medi" codeType="aaa157_medi"></powersi:codetable>
		
		<powersi:codetable id="bka037List" codeType="bka037"></powersi:codetable>
		
		<!-- 预约挂号类别 -->
		<powersi:codetable id="bka504" codeType="bka504"></powersi:codetable>
		<!-- 医院接入方式 -->
		<powersi:codetable id="bae301" codeType="bae301"></powersi:codetable>
		<!-- 医院接入进度情况 -->
		<powersi:codetable id="bae309" codeType="bae309"></powersi:codetable>