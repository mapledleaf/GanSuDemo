<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	request.setAttribute(
			"roleList",
			com.powersi.hygeia.framework.util.DBHelper
					.executeList("select * from sys_roles order by dis_order"));
%>
<powersi:html>
<head>
<powersi:head title="多选页面" />
<script src="${strutsPath}/include/multiselect.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		setSize();
		$(window).resize(setSize);
		
		$(".multiselect").multiselect();
	});

	function viewSelect(id) {
		alert($(id).val());
	}

	function clearSelect(id) {
		$(id).multiselect('clear');

		alert($(id).val());
	}

	function progSelect() {
		$("#countries").multiselect('clear');

		var sels = [ 'CHN', 'USA', 'RUS', 'FRA', 'GBR' ];
		$('#countries').multiselect('select', sels);
	}

	function lenSelect(id) {
		alert($(id).multiselect('length'));
	}

	function textSelect(id) {
		alert($(id).multiselect('text').join(','));
	}

	function dataSelect(id) {
		alert($(id).multiselect('data').join('\n'));
	}

	function setSize() {
		try{
			//自适应宽度(因为可能出现纵向滚动条，导致宽度变小，所以需要先设置高度)
			$(".multiselect").height(350).width($("#divTabs").width());

			$(".multiselect").each(function() {
				//检查对象是否初始化
				if ($(this).is(":ui-multiselect")) {
					$(this).multiselect('resize');
				}
			});
		} catch(ex){}
	}
</script>
</head>
<body class="page">
	<powersi:tabbedpanel id="divTabs">
		<powersi:tab id="tab1" target="divTab1" label="html标签" />
		<powersi:tab id="tab2" target="divTab2" label="struts标签" />
		<div id="divTab1">
			<select id="countries" class="multiselect" multiple="multiple"
				name="countries">
				<option value="AFG">Afghanistan</option>
				<option value="ALB">Albania</option>
				<option value="DZA">Algeria</option>
				<option value="AND">Andorra</option>
				<option value="ARG">Argentina</option>
				<option value="ARM">Armenia</option>
				<option value="ABW">Aruba</option>
				<option value="AUS">Australia</option>
				<option value="AUT" selected="selected">Austria</option>

				<option value="AZE">Azerbaijan</option>
				<option value="BGD">Bangladesh</option>
				<option value="BLR">Belarus</option>
				<option value="BEL">Belgium</option>
				<option value="BIH">Bosnia and Herzegovina</option>
				<option value="BRA">Brazil</option>
				<option value="BRN">Brunei</option>
				<option value="BGR">Bulgaria</option>
				<option value="CAN">Canada</option>

				<option value="CHN">China</option>
				<option value="COL">Colombia</option>
				<option value="HRV">Croatia</option>
				<option value="CYP">Cyprus</option>
				<option value="CZE">Czech Republic</option>
				<option value="DNK">Denmark</option>
				<option value="EGY">Egypt</option>
				<option value="EST">Estonia</option>
				<option value="FIN">Finland</option>

				<option value="FRA">France</option>
				<option value="GEO">Georgia</option>
				<option value="DEU" selected="selected">Germany</option>
				<option value="GRC">Greece</option>
				<option value="HKG">Hong Kong</option>
				<option value="HUN">Hungary</option>
				<option value="ISL">Iceland</option>
				<option value="IND">India</option>
				<option value="IDN">Indonesia</option>

				<option value="IRN">Iran</option>
				<option value="IRL">Ireland</option>
				<option value="ISR">Israel</option>
				<option value="ITA">Italy</option>
				<option value="JPN">Japan</option>
				<option value="JOR">Jordan</option>
				<option value="KAZ">Kazakhstan</option>
				<option value="KWT">Kuwait</option>
				<option value="KGZ">Kyrgyzstan</option>

				<option value="LVA">Latvia</option>
				<option value="LBN">Lebanon</option>
				<option value="LIE">Liechtenstein</option>
				<option value="LTU">Lithuania</option>
				<option value="LUX">Luxembourg</option>
				<option value="MAC">Macau</option>
				<option value="MKD">Macedonia</option>
				<option value="MYS">Malaysia</option>
				<option value="MLT">Malta</option>

				<option value="MEX">Mexico</option>
				<option value="MDA">Moldova</option>
				<option value="MNG">Mongolia</option>
				<option value="NLD" selected="selected">Netherlands</option>
				<option value="NZL">New Zealand</option>
				<option value="NGA">Nigeria</option>
				<option value="NOR">Norway</option>
				<option value="PER">Peru</option>
				<option value="PHL">Philippines</option>

				<option value="POL">Poland</option>
				<option value="PRT">Portugal</option>
				<option value="QAT">Qatar</option>
				<option value="ROU">Romania</option>
				<option value="RUS">Russia</option>
				<option value="SMR">San Marino</option>
				<option value="SAU">Saudi Arabia</option>
				<option value="CSG">Serbia and Montenegro</option>
				<option value="SGP">Singapore</option>

				<option value="SVK">Slovakia</option>
				<option value="SVN">Slovenia</option>
				<option value="ZAF">South Africa</option>
				<option value="KOR">South Korea</option>
				<option value="ESP">Spain</option>
				<option value="LKA">Sri Lanka</option>
				<option value="SWE">Sweden</option>
				<option value="CHE">Switzerland</option>
				<option value="SYR">Syria</option>

				<option value="TWN">Taiwan</option>
				<option value="TJK">Tajikistan</option>
				<option value="THA">Thailand</option>
				<option value="TUR">Turkey</option>
				<option value="TKM">Turkmenistan</option>
				<option value="UKR">Ukraine</option>
				<option value="ARE">United Arab Emirates</option>
				<option value="GBR">United Kingdom</option>
				<option value="USA" selected="selected">United States</option>

				<option value="UZB">Uzbekistan</option>
				<option value="VAT">Vatican City</option>
				<option value="VNM">Vietnam</option>
			</select>
			<div class="divButton">
				<input type="button" value="查看选择" onclick="viewSelect('#countries')"
					class="button" /> <input type="button" value="清空选择"
					onclick="clearSelect('#countries')" class="button" /> <input
					type="button" value="显示长度" onclick="lenSelect('#countries')"
					class="button" /> <input type="button" value="显示文本"
					onclick="textSelect('#countries')" class="button" /> <input
					type="button" value="显示数据" onclick="dataSelect('#countries')"
					class="button" /> <input type="button" value="程序选择"
					onclick="progSelect()" class="button" />
			</div>
		</div>
		<div id="divTab2">
			<powersi:codeselect id="roles" cssClass="multiselect" multiple="true"
				name="roles" list="#request.roleList" listKey="role_id"
				listValue="role_name" />
			<div class="divButton">
				<input type="button" value="查看选择" onclick="viewSelect('#roles')"
					class="button" /> <input type="button" value="清空选择"
					onclick="clearSelect('#roles')" class="button" /> <input
					type="button" value="显示长度" onclick="lenSelect('#roles')"
					class="button" /> <input type="button" value="显示文本"
					onclick="textSelect('#roles')" class="button" /> <input
					type="button" value="显示数据" onclick="dataSelect('#roles')"
					class="button" />
			</div>
		</div>
	</powersi:tabbedpanel>
	<powersi:errors />
</body>
</powersi:html>