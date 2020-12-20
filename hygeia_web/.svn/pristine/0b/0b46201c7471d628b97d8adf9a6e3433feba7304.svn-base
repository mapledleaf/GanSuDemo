<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%
	/*获取数据，演示在jsp中实现，实际代码必须在action实现*/
	com.powersi.sys.message.dao.BulletinDAO dao = com.powersi.hygeia.framework.util.BeanHelper
			.getBean(com.powersi.sys.message.dao.BulletinDAO.class);

	com.powersi.sys.message.dto.SearchBulletinDTO dto = new com.powersi.sys.message.dto.SearchBulletinDTO();
	dto.setBeginDate("2010-01-01 00:00:00");
	dto.setEndDate("2020-01-01 00:00:00");
	dto.setQueryType("0");
	String title = request.getParameter("bulletinTitle");
	if(title == null){
		title = "";
	} else {
		dto.setBulletinTitle(title);
	}
	request.setAttribute("bulletinTitle", title);
	
	com.powersi.sys.util.PagerHelper.initPagination(request);
	request.setAttribute("rsList", com.powersi.sys.util.PagerHelper
			.getPaginatedList(dao.queryList(dto)));

	/*表格修饰*/
	request.setAttribute("dyndecorator",
			new org.displaytag.decorator.TableDecorator() {
				public String addRowId() {
					return "r" + evaluate("bulletin_id");
				}
			});
%>
<powersi:html>
<head>
<powersi:head title="displaytag示例" />
<style type="text/css">
#rowFilter {
	width: 200px;
	display: inline-block;
}
</style>
<script src="${rootPath}/resource/js/jquery.quickfilter.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#row tbody tr').quickfilter({
			srcElement : '#row tbody tr',
			attached : '#rowFilter',
			labelText : null,
			inputText : '搜索 公告',
			onAfter : null
		});
	});

	function doView(bulletinUrl) {
		//alert('view:' + bulletinUrl);
		var ret = openDialog(rootPath + "/sample/Sample!queryAa10.action?mspDto.aae140Type=1&mspDto.aac001=123&mspDto.aac003=" + encodeURIComponent("中文"),420,450);

	}
	function doAdd() {
		alert('add');
	}
	function doEdit(rowObj, bulletinId) {
		alert('bulletinId:' + bulletinId + "\r\nrowIndex:" + rowObj.rowIndex
				+ "\r\njson:" + powersi.tostring(getRowForArray(rowObj)));
	}
	function doDel(bulletinId, bulletinTitle) {
		alert('delete:' + bulletinId);
	}

	function sel1() {
		alert(powersi.tostring(getTableForArray('row')));
	}

	function sel2() {
		alert(powersi.tostring(getTableForArray('row', {
			checkboxCol : 'cbxSel',
			rowIndex : true
		})));
	}

	function sel3() {
		alert(powersi.tostring(getTableForArray('row',
				{
					checkboxCol : 'rbSel',
					tabCols : [ 'bulletin_id', 'bulletin_title', 'sender_man',
							'abc123' ]
				})));
	}

	function sel4() {
		alert(powersi.tostring(getTableForArray('row', {
			tabRows : [ '*' ]
		})));
	}

	function del1() {
		alert(powersi.tostring(deleteTable('row')));
	}

	function del2() {
		alert(powersi.tostring(deleteTable('row', {
			checkboxCol : 'cbxSel'
		})));
	}

	function del3() {
		alert(powersi.tostring(deleteTable('row', {
			checkboxCol : 'rbSel'
		})));
	}

	function count() {
		alert(getTableRowsCount('row'));
	}

	function update() {
		var rows = getTableForArray('row', {
			rowIndex : true
		});
		if (rows == null || rows.length < 1) {
			alert("请选择需要更新的行");
			return;
		}

		var name = prompt("请输入公告标题", "");
		if (powersi.isvalue(name)) {
			alert(updateTable('row', rows[0].rowIndex, {
				"bulletin_title" : "<a href=\"javascript:doView('"
						+ rows[0].bulletin_id + "')\">" + name + "</a>"
			}));
		} else {
			alert("公告标题不能为空");
		}
	}

	function insert() {
		var data = {
			"bulletin_title" : '新增标题',
			"bulletin_id" : "-1"
		};

		alert(insertTable('row', data));
	}

	function setGroupHeaders() {
		var actionUrl = rootPath + "/pages/sample/displaytag.jsp";
		var headerHtml = "<tr>\n"
				+ "    <th rowspan=\"2\">\n"
				+ "        序号\n"
				+ "    </th>\n"
				+ "    <th rowspan=\"2\">\n"
				+ "        <input type='checkbox' id='cbxAll' name='cbxAll' onclick='selectAll(this, \"cbxSel\")'></input>\n"
				+ "    </th>\n" + "    <th rowspan=\"2\">\n" + "        选择\n"
				+ "    </th>\n" + "    <th class=\"hidden\" rowspan=\"2\">\n"
				+ "        公告编号\n" + "    </th>\n" + "    <th rowspan=\"2\">\n"
				+ "        标题\n" + "    </th>\n" + "    <th colspan=\"2\">\n"
				+ "        发送信息\n" + "    </th>\n" + "    <th colspan=\"2\">\n"
				+ "        有效时限\n" + "    </th>\n" + "    <th rowspan=\"2\">\n"
				+ "        审核标志\n" + "    </th>\n"
				+ "    <th class=\"sortable\" rowspan=\"2\">\n"
				+ "        <a href=\""
				+ actionUrl
				+ "?sort=audit_man&amp;dir=asc\">审核人</a>\n"
				+ "    </th>\n"
				+ "    <th rowspan=\"2\">\n"
				+ "        操作\n"
				+ "    </th>\n"
				+ "    <th rowspan=\"2\">\n"
				+ "        操作\n"
				+ "    </th>\n"
				+ "    <th rowspan=\"2\">\n"
				+ "        业务类型\n"
				+ "    </th>\n"
				+ "</tr>\n"
				+ "<tr>\n"
				+ "<th class=\"sortable\">\n"
				+ "        <a href=\""
				+ actionUrl
				+ "?sort=sender_man&amp;dir=asc\">发送者名称</a>\n"
				+ "    </th>\n"
				+ "    <th class=\"sortable\">\n"
				+ "        <a href=\""
				+ actionUrl
				+ "?sort=send_date&amp;dir=asc\">发送时间</a>\n"
				+ "    </th>\n"
				+ "    <th class=\"sortable\">\n"
				+ "        <a href=\""
				+ actionUrl
				+ "?sort=effect_date&amp;dir=asc\">生效日期</a>\n"
				+ "    </th>\n"
				+ "    <th class=\"sortable\">\n"
				+ "        <a href=\""
				+ actionUrl
				+ "?sort=expire_date&amp;dir=asc\">失效日期</a>\n"
				+ "    </th>\n"
				+ "</tr>";

		$('#row thead').html(headerHtml);
	}
</script>
</head>
<body>
	<powersi:groupbox title="查询条件">
		<table class="tablePanel">
			<tr>
				<td class="tdButton"><input type="button" id="bt1"
					value="获取选择行根据点击" onclick="sel1();" class="button" /> <input
					type="button" id="bt2" value="获取选择行根据复选框" onclick="sel2();"
					class="button" /> <input type="button" id="bt3" value="获取选择行根据单选框"
					onclick="sel3();" class="button" /> <input type="button" id="bt9"
					value="获取所有行" onclick="sel4();" class="button" /></td>
			</tr>
			<tr>
				<td class="tdButton"><input type="button" id="bt4"
					value="删除选择行根据点击" onclick="del1();" class="button" /> <input
					type="button" id="bt5" value="删除选择行根据复选框" onclick="del2();"
					class="button" /> <input type="button" id="bt6" value="删除选择行根据单选框"
					onclick="del3();" class="button" /></td>
			</tr>
			<tr>
				<td class="tdButton"><input type="button" id="bt7"
					value="获取表格数据行数" onclick="count();" class="button" /> <input
					type="button" id="bt8" value="更新选择行数据" onclick="update();"
					class="button" /> <input type="button" id="bt9" value="设置多表头"
					onclick="setGroupHeaders();" class="button" />
			</tr>
		</table>
	</powersi:groupbox>

	<powersi:groupbox title="查询条件">
		<form id="form1" action="${rootPath }/pages/sample/displaytag.jsp">
			<powersi:editorlayout cols="4">
				<tr>
					<powersi:textfield name="bulletinTitle" id="queryStringCorp"
						label="公共标题" value="${bulletinTitle }"/>
					<powersi:editorlayout-input colspan="1">
						<powersi:submit type="submit" key="button_query" />
					</powersi:editorlayout-input>
					<td colspan="1">
						<div id="rowFilter"></div>
					</td>
				</tr>
			</powersi:editorlayout>
		</form>
	</powersi:groupbox>

	<powersi:groupbox title="查询结果">
		<display:table name="rsList" id="row"
			requestURI="/pages/sample/displaytag.jsp" decorator="dyndecorator"
			export="true">
			<display:setProperty name="paging.banner.placement">both</display:setProperty>
			<display:setProperty name="export.csv.filename">公告列表.csv</display:setProperty>
			<display:setProperty name="export.excel.filename">公告列表.xls</display:setProperty>
			<display:setProperty name="export.pdf.filename">公告列表.pdf</display:setProperty>
			<display:column property="page_rowno" titleKey="page_rowno" />
			<display:column nonExport="true"
				title="<input type='checkbox' id='cbxAll' name='cbxAll' onclick='selectAll(this, \"cbxSel\")'></input>">
				<input type="checkbox" name="cbxSel"></input>
			</display:column>
			<display:column title="选择" nonExport="true">
				<input type="radio" name="rbSel"></input>
			</display:column>
			<display:column property="bulletin_id" titleKey="bulletin_id"
				headerClass="hidden" class="hidden" />
			<display:column title="标题" titleKey="bulletin_title">
				<a href="javascript:doView('${row.bulletin_url}')">${row.bulletin_title}</a>
			</display:column>
			<display:column property="sender_man" titleKey="sender_man"
				sortable="true" />
			<display:column property="send_date" titleKey="send_date"
				format="{0,date,yyyy-MM-dd HH:mm}" sortable="true" />
			<display:column property="effect_date" titleKey="effect_date"
				format="{0,date,yyyy-MM-dd HH:mm}" sortable="true" />
			<display:column property="expire_date" titleKey="expire_date"
				format="{0,date,yyyy-MM-dd HH:mm}" sortable="true" />
			<display:column titleKey="audit_flag" property="audit_flag"
				codetable="audit_flag" />
			<display:column property="audit_man" titleKey="audit_man"
				style="width: 200px;" headerStyle="width:200px;" sortable="true" />
			<display:column titleKey="operate" nonExport="true">
				<input type="button" value="编辑" class="linkButton"
					id="edit${row.bulletin_id}"
					onclick="doEdit(this.parentNode.parentNode, '${row.bulletin_id}')"
					${row.audit_flag== '1' ? "disabled='disabled' " : ""} />
			</display:column>
			<display:column titleKey="operate" nonExport="true">
				<input type="button" value="删除" class="linkButton"
					id="del${row.bulletin_id}"
					onclick="doDel('${row.bulletin_id}', '${row.bulletin_title}')"
					${row.audit_flag== '1' ? "disabled='disabled' " : ""} />
			</display:column>
			<display:column title="业务类型" titleKey="biz_type" nonExport="true">
				<select id="p${row.bulletin_id}" style="width: 160px" class="select">
					<option value="12">住院</option>
					<option value="10">购药</option>
					<option value="11">门诊</option>
					<option value="13">门诊慢性病</option>
					<option value="14">家庭病床</option>
					<option value="16">门特</option>
					<option value="17">急诊留观</option>
					<option value="41">工伤门诊</option>
					<option value="42">工伤住院</option>
					<option value="51">生育门诊</option>
					<option value="52">生育住院</option>
					<option value="61">公医门诊</option>
					<option value="62">公医住院</option>
					<option value="64">公医门诊慢性病</option>
					<option value="66">公医门特</option>
					<option value="67">公医急诊留观</option>
				</select>
			</display:column>
		</display:table>
	</powersi:groupbox>
	<powersi:errors />
</body>
</powersi:html>