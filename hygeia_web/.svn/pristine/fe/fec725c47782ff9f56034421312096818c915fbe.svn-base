<%@page import="com.powersi.hygeia.framework.BusiContext"%>
<%@page import="com.powersi.hygeia.framework.UserInfo"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.powersi.com.cn/tags" prefix="powersi"%>
<%
	String path = request.getContextPath();

	String userName = "";
	UserInfo user = BusiContext.getUserInfo();
	if (user != null) {
		userName = user.getUserName();
	}
	String centerName = "城镇居民";
	request.setAttribute("centerName", "城镇居民");

	{
		String sql = "select *\n"
				+ "  from (select code_type as aaa100,\n"
				+ "               code_name as aaa101,\n"
				+ "               0         as aaa104,\n"
				+ "               0         as aaz094\n"
				+ "          from sys_code_table order by code_type)";

		request.setAttribute("aa09",
				com.powersi.hygeia.framework.util.DBHelper
						.executeList(sql));
	}

	request.setAttribute(
			"ab01",
			com.powersi.hygeia.framework.util.DBHelper
					.executeList("select * from sys_char_code where rownum <= 20"));

	{
		String sql = "select *\n"
				+ "  from (select code_type     as aaa100,\n"
				+ "               data_value    as aaa102,\n"
				+ "               display_value as aaa103,\n"
				+ "               199405        as aae030,\n"
				+ "               null          as aae031,\n"
				+ "               0             as aaz093,\n"
				+ "               0             as aaz094,\n"
				+ "               null          as aaa104,\n"
				+ "               null          as aaa101\n"
				+ "          from sys_code_table_detail\n"
				+ "         order by code_type, dis_order)";

		request.setAttribute("data1",
				com.powersi.hygeia.framework.util.DBHelper
						.executeList(sql));
		request.setAttribute("data2",
				com.powersi.hygeia.framework.util.DBHelper
						.executeList(sql));
	}
	
	{
		java.util.Set<String> keys = com.powersi.hygeia.web.util.TextHelper.keySet();
		java.util.List lst = new java.util.ArrayList(keys.size());
		for(String key : keys){
			Map map = new java.util.HashMap();
			if(key.length() > 11){
				map.put("text_code", key.substring(0, 11) + "\r\n" + key.substring(11));
			} else{
				map.put("text_code", key);
			}
			map.put("text_value", com.powersi.hygeia.web.util.TextHelper.getText(key, key));
			lst.add(map);
		}
		request.setAttribute("texts", lst);
	}
	
	{
		String sql = "select a.code_type, a.code_name, b.data_value, b.display_value, b.dis_order\n" + 
				"  from sys_code_table a, sys_code_table_detail b\n" + 
				" where a.code_type = b.code_type\n" + 
				"   and a.code_sql is null\n" + 
				" order by b.code_type, b.dis_order";
		
		request.setAttribute("codes",
				com.powersi.hygeia.framework.util.DBHelper
						.executeList(sql));
	}
%>
<powersi:html>
<head>
<powersi:head title="数据窗口示例" />
</head>
<body onload="init()" class="grid">
	<div class="row">
		<div class="col-4">
			<powersi:groupbox title="打 印">
				<input type="button" value="一般打印" onclick="print()" id="btnPrint" class="linkButton" /> 
				<input type="button" value="控件打印" onclick="print0()" id="btnPrint0" class="linkButton" /> 
				<input type="button" value="选择打印" onclick="print1()" id="btnPrint1" class="linkButton" /> 
				<input type="button" value="复合打印" onclick="print2()" id="btnPrint2" class="linkButton" /> 
				<input type="button" value="缩放打印" onclick="print3()" id="btnPrint3" class="linkButton" /> 
				<input type="button" value="打印设置" onclick="printSetup()" id="btnPrintSetup" class="linkButton" />
				<br />
				<div class="floatLeft">
					<input type="checkbox" onclick="selAll(this)" id="cbxAll" class="checkbox" />
					<label for="cbxAll" class="linkButton">全选</label>
					<label class="textSuccess">建议直接在列标题实现全选</label>
					<input type="button" value="帮助" onclick="popup()" id="btnPopup" class="linkButton" style="vertical-align: middle;"/>
				</div>
			</powersi:groupbox>
		</div>
		<div class="col-4">
			<powersi:groupbox title="导 出">
				<input type="button" value="类型0(2)" onclick="exp0()" id="btnExport0" class="linkButton" />
				<input type="button" value="类型0(3)" onclick="exp2()" id="btnExport2" class="linkButton" /> 
				<input type="button" value="类型0(5)" onclick="exp9()" id="btnExport9" class="linkButton" />
				<input type="button" value="类型1(1)" onclick="exp1()" id="btnExport1" class="linkButton" /> 
				<input type="button" value="类型4(1)" onclick="exp4()" id="btnExport4" class="linkButton" />
				<input type="button" value="类型4(3)" onclick="exp3()" id="btnExport3" class="linkButton" />
				<br />
				<input type="button" value="字符串导出" onclick="exp5()" id="btnImport5" class="linkButton" /> 
				<input type="button" value="自定义导出" onclick="exp6()" id="btnImport6" class="linkButton" /> 
				<input type="button" value="带条件导出" onclick="exp7()" id="btnImport6" class="linkButton" />
				<input type="button" value="复合导出" onclick="exp8()" id="btnExport8" class="linkButton" /> 
				<input type="button" value="分栏导出" onclick="exp10()" id="btnExport10" class="linkButton" /> 
			</powersi:groupbox>
		</div>
		<div class="col-4">
			<powersi:groupbox title="其 它">
				<input type="button" value="批量更新" onclick="update1()" id="btnUpdate1" class="linkButton" />
				<input type="button" value="批量删除1" onclick="del4()" id="btnDelete4" class="linkButton" />
				<input type="button" value="批量删除2" onclick="del5()" id="btnDelete5" class="linkButton" />
				
				<input type="button" value="删除选择行" onclick="del1()" id="btnDelete1" class="linkButton" /> 
				<input type="button" value="删除指定行" onclick="del2()" id="btnDelete2" class="linkButton" /> 
				<input type="button" value="删除全部行" onclick="del3()" id="btnDelete3" class="linkButton" />

				<input type="button" value="数组导入" onclick="imp1()" id="btnImport1" class="linkButton" />
				<input type="button" value="大数据上传" onclick="bigData()" id="btnBigData" class="linkButton" />
				<input type="button" value="分页" onclick="pager1()" id="btnBigData" class="linkButton" />
				<input type="button" value="动态" onclick="dyn1()" id="btnBigData" class="linkButton" />
				<input type="button" value="复合报表隐藏" onclick="hide1()" id="btnRpHide" class="linkButton" />
			</powersi:groupbox>
		</div>
	</div>
	<powersi:groupbox title="数据窗口">
	<div class="row">
		<div class="col-12">
			<powersi:tabbedpanel id="divTabs">
				<powersi:tab id="tab1" target="divTab1" label="Grid(网格)" />
				<powersi:tab id="tab2" target="divTab2" label="Group(分组)" />
				<powersi:tab id="tab3" target="divTab3" label="Composite(复合)" />
				<powersi:tab id="tab4" target="divTab4" label="N-Up(分栏)" />
				<powersi:tab id="tab5" target="divTab5" label="TreeView(树视图)" />
				<div id="divTab1">
					<div class="row">
						<div class="col-6">
							<div class="row">
								<div class="col-12" id="findPanel_dw1">
									<div class="input-group pull-left">
										<select id="SelectFind_dw1" style="width: 120px;" class="pull-left textLeft"></select>
										<input id="TextFind_dw1" type="text" class="pull-left" autocomplete="off" style="width: 150px;border-left:0;" placeholder="输入关键字"/>
										<span class="input-group-btn pull-left">
											<input id="ButtonFind_dw1" type="button" name="button_find" class="btn btn-default" value="查 找" />
										</span>
									</div>
								</div>
							</div>
							<div class="space"></div>
							<div class="row">
								<div class="col-12">
									<powersi:datawindow id="dw_1" value="data1" height="100%"
									sort="aaa100 asc, long(aaa102) asc"
									clicked="sortClicked;selectClicked;viewClicked;checkAllClicked('cbxall', 'flag');"
									rowFocusChanged="selectRowFocusChanged" findPanel="findPanel_dw1"
									importEnd="importEnd" fileName="datawindow.pbd"
									objectName="dw_aa10"
									cols="aaa100,aaa102,aaa103,aae030,aae031,aaz093,aaz094,aaa104,aaa101,flag,cbxall">
									<powersi:datawindowcolumn name="aaa100" type="ddlb" value="aa09"
										ddlbData="aaa100" ddlbDisplay="aaa101" />
									<powersi:datawindowcolumn name="aaz094" type="dddw" value="aa09"
										dddwName="dw_aa10" filter="aaa100='AAA007'" />
								</powersi:datawindow>
								</div>
							</div>
						</div>
						<div class="col-6">
							<div class="row">
								<div class="col-12" id="findPanel_dw2">
									<div class="input-group pull-left">
										<select id="SelectFind_dw2" style="width: 120px;" class="pull-left textLeft"></select>
										<input id="TextFind_dw2" type="text" class="pull-left" autocomplete="off" style="width: 150px;border-left:0;" placeholder="输入关键字"/>
										<span class="input-group-btn pull-left">
											<span class="input-group-btn pull-left">
												<input id="ButtonFind_dw2" type="button" name="button_find" class="btn btn-default" value="查 找" />
											</span>
										</span>
									</div>
								</div>
							</div>
							<div class="space"></div>
							<div class="row">
								<div class="col-12">
									<powersi:datawindow id="dw_2" value="data2" height="100%"
									sort="aaa100 asc, long(aaa102) asc"
									filter="aaa100 = 'grade_id' "
									clicked="sortClicked;viewClicked;checkAllClicked('cbxall', 'flag');"
									findPanel="findPanel_dw2" fileName="datawindow.pbd"
									objectName="dw_aa10"
									cols="aaa100,aaa102,aaa103,aae030,aae031,aaz093,aaz094,aaa104,aaa101,flag,cbxall">
									<powersi:datawindowcolumn name="aaa100" type="ddlb" value="aa09"
										ddlbData="aaa100" ddlbDisplay="aaa101" />
									<powersi:datawindowcolumn name="aaa102" type="codetable"
										value="aka130" />
									<powersi:datawindowcolumn name="aaz094" type="dddw" value="aa09"
										dddwName="dddw_aa09" />
								</powersi:datawindow>
								</div>
							</div>
						</div>	
					</div>
				</div>
				<div id="divTab2">
					<div class="divButton">
						<powersi:datawindow id="dw_3" value="data3" height="100%" printPreview="true" heightDiff="-30"
							groupCalc="true" name="dw_hospreport" />
					</div>
					<div class="divButton">
						<powersi:button id="btnPrint3" key="button_print" onclick="printDataWindow('dw_3')"></powersi:button>
						<powersi:button id="btnExport3" key="button_export" onclick="exp3()"></powersi:button>
					</div>
				</div>
				<div id="divTab3">
					<div class="divButton">
						<powersi:datawindow id="dw_4" value="data4" height="100%" width="815" heightDiff="-30"
							name="dw_urban_topaybill_liuyang" printPreview="yes">
							<powersi:datawindowcolumn name="dw_header" type="dddw" value=""
								dddwName="dw_urban_topaybill_header_liuyan" />
							<powersi:datawindowcolumn name="dw_detail" type="dddw" value="ab01"
								dddwName="dw_urban_topaybill_detail_liuyan" />
						</powersi:datawindow>
					</div>
					<div class="divButton">
						<powersi:button id="btnPrint4" key="button_print" onclick="print2()"></powersi:button>
						<powersi:button id="btnExport4" key="button_export" onclick="exp8()"></powersi:button>
					</div>
				</div>
				<div id="divTab4">
					<div class="divButton">
						<powersi:datawindow id="dw_5" value="texts" height="100%" width="760" heightDiff="-30"
							fileName="datawindow.pbd" objectName="dw_texts" cols="text_code,text_value">
						</powersi:datawindow>
					</div>
					<div class="divButton">
						<powersi:button id="btnPrint5" key="button_print" onclick="printDataWindow('dw_5')"></powersi:button>
						<powersi:button id="btnExport5" key="button_export" onclick="exp10()"></powersi:button>
					</div>
				</div>
				<div id="divTab5">
					<div class="divButton">
						<powersi:datawindow id="dw_6" value="codes" height="100%" width="815" printPreview="true"
							fileName="datawindow.pbd" objectName="dw_tree" cols="code_type,code_name,data_value,display_value,dis_order">
						</powersi:datawindow>
					</div>
				</div>
			</powersi:tabbedpanel>
		</div>
	</div>
	</powersi:groupbox>
	<div class="hidden">
		<div id="dlgHelp">
			<div class="col-12">
				<div class="text110 textSuccess">
					<ol style="padding-left: 10px;">
						<li>初始化函数请在body设置onload属性，不要使用jquery的ready函数调用</li>
						<li>分组数据窗口请设置groupCalc="true"</li>
						<li>复合数据窗口请设置printPreview="true"</li>
						<li>数据窗口控件增删改函数性能极差，批量操作建议获取数据、处理数据、删除数据、导入数据，具体参见批量更新代码</li>
						<li>
						数据窗口导出使用类型0或者类型4，小数据量用类型0，大数据量用类型4
						（不需要导出的对象，请设置对象的tag属性为sys_notexport，类型4不需要黑色边框的话，可以把任意一个非列对象的name改为sys_exportdata）
						</li>
					</ol>
				</div>
			</div>
		</div>
	</div>
	<powersi:errors />
<script type="text/javascript">
function print(){
	var dw = document.getElementById("dw_2");
	if(dw != null){
		//选择打印机
		if(setDataWindowPrinter(dw) == 2){
			return;
		}
		
		dw.Print(true);
	}
}

function print0(){
	printDataWindow("dw_2");
}

function print1(){
	printDataWindowSelect("dw_2");
}

function print2(){
	printDataWindow("dw_4");
}

function print3() {
	//打印选项 zoom缩放比例 copies打印份数 orientation打印方向（1横向 2纵向)
	printDataWindow('dw_3', {
		'zoom': 75,
		'copies': 2,
		'orientation': 2
	});
}

//类型0导出
function exp0() {
	exportDataWindowToExcel("dw_2", "0", "aa10数据导出0");
}

//类型1导出
function exp1() {
    exportDataWindowToExcel("dw_1", "1", "aa10数据导出1");
}

//类型0导出
function exp2() {
    exportDataWindowToExcel("dw_3", "0", "统筹医疗费用报表0");
}

//类型4导出
function exp3() {
    exportDataWindowToExcel("dw_3", "4", "统筹医疗费用报表4");
}

//类型4导出
function exp4() {
    exportDataWindowToExcel("dw_1", "4", "aa10数据导出4");
}

//缺省导出
function exp5() {
	var data = getDataWindowForString("dw_1");
    exportDataWindowToExcel("dw_1", "3", "测试文件导出(缺省).txt", "", data);
}
//自定义导出
function exp6() {
    var options = {
			prefix: "\"",
			suffix: "\"",
			colSeparator: ",",
			rowSeparator: "\r\n",
			dwCols:["compute_1", "aaa100", "aaa103", "aaa102"],
			/*dwRows:[100, 101, 102, 103, 104, 105]*/
			checkboxCol: "flag",
			headerText: "\"序号\",\"代码类别\",\"代码名称\",\"代码值\""
	};
    var data = getDataWindowForString("dw_1", options);
    exportDataWindowToExcel("dw_1", "3", "测试文件导出(自定义).txt", "", data);
}
//带条件导出
function exp7(){
	var options = {
			dwCols:["compute_1", "aaa100", "aaa103", "aaa102"],
			/*dwRows:[100, 101, 102, 103, 104, 105]*/
			checkboxCol: "flag"
	};
    var json = getDataWindowForArray("dw_1", options);
    alert(powersi.tostring(json));
}

//复合导出
function exp8() {
    exportDataWindowToExcel("dw_4", "0", "城镇居民基本医疗保险缴费通知单");
}

//n-up导出
function exp9() {
    exportDataWindowToExcel("dw_5", "0", "文本列表0");
}

//n-up导出
function exp10() {
    exportDataWindowToExcel("dw_5", "4", "文本列表4");
}

function del1(){
	//删除选择行
	var options = {
			checkboxCol: "flag"
	};
    alert(deleteDataWindow("dw_1", options));
}

function del2(){
	//删除指定行
	var options = {
			dwRows:[1, 3, 4, 5, 2]
	};
    alert(deleteDataWindow("dw_1", options));
}

function del3(){
	//删除全部数据
    //alert(deleteDataWindow("dw_2"));
	alert(resetDataWindow("dw_2", 0));
}

//批量删除1
function del4() {
	var dw_1 = S('dw_1');
	dw_1.SetFilter('isnull(flag) or flag = 0');
	dw_1.Filter();
	
	//var cnt = dw_1.FilteredCount();
	//dw_1.RowsDiscard(1, cnt, 2);
	var cnt = resetDataWindow(dw_1, 2);
	
	dw_1.SetFilter('1 = 1');
	dw_1.Filter();
	
	alert(cnt);
}

//批量删除2
function del5() {
	var rows = getDataWindowForArray("dw_1");
	var data = [];
	$.each(rows, function(n, row){
		if(row['flag'] != '1'){
			data.push(row);
		}
	});
	var cnt = rows.length - data.length;
	if(cnt > 0)
		importDataWindowWithArray("dw_1", data, true);
	
	alert(cnt);
}


function imp1() {
	var rows = [];
	for ( var i = 0; i < 3; i++) {
		var row = {};
		row.aaa100 = "BKB029";
		row.Aaa102 = i;
		row.AAA103 = "字段说明" + i;
		row.aae030 = "199001";

		rows.push(row);
	}
	alert(importDataWindowWithArray("dw_2", rows));

	alert(importDataWindowWithArray("dw_2", rows, false));

	var persons = [];
	for ( var i = 0; i < 10; i++) {
		var row = {};
		row.indi_id = i;
		row.name = "姓名" + i;
		row.sex = "男";
		row.pers_type = "在职";
		row.pay_money = 9.28;

		persons.push(row);
	}
	alert(importDataWindowWithArray("dw_4|dw_detail", persons));
}
//全选
function selAll(cbx) {
	var s = new Date();
	selectDataWindow('dw_1', 'flag', cbx.checked ? 1 : 0);
	alert(powersi.benchmark("selectall:", s));
}

//批量更新
function update1(){
	var s = new Date();
	var data = getDataWindowForArray('dw_1');
	try{
		//0.获取数据库对象
		var dwObj = getDataWindow('dw_1');
		
		//测试过滤
		dwObj.SetFilter('aaa100 = "nation"');
		dwObj.Filter();
	
		//1.获取数据
		var rows = getDataWindowForArray(dwObj);
		
		//2.数据处理（数组从0开始，数据窗口行号从1开始，需注意）
		var cnt = rows.length;
		for(var i = 0; i < cnt; i ++){
			rows[i]['flag'] = i % 2;
		}
		
		/////////////////////////////////////////////////////////////////////////////
		//提示：如果没有删除和过滤操作可以省略第3步，第4步的重置标志false改为true  //
		/////////////////////////////////////////////////////////////////////////////
		
		//3.删除主数据，保留删除和过滤数据(0:主数据 1:删除数据 2:过滤数据 其他:全部数据)
		resetDataWindow(dwObj, 0);
		
		//4.导入数据(重置标志置为false)
		importDataWindowWithArray(dwObj, rows, false);
		
		//还原过滤
		dwObj.SetFilter('1 = 1');
		dwObj.Filter();
	} catch(ex){
		alert(ex.message);
	}
	
	alert(powersi.benchmark("batchupdate:", s));
}

function viewClicked(dwId, dwEvent) {
	if (dwEvent == null) {
		return;
	}

	if (dwEvent.Button != 1 || dwEvent.Row < 1
			|| dwEvent.Name != 'compute_view') {
		return;
	}

	var lst = getDataWindowForArray(dwId, {
		/*dwCols: ['aaa100', 'aaa102', 'aaa103'],*/
		dwRows : [ dwEvent.Row ]
	});

	if (lst != null && lst.length > 0) {
		alert(powersi.param(lst[0]) + "\r\n\r\n" +
		powersi.param(lst[0], {beanName : "dto"})
		+ "\r\n\r\n" + powersi.param(lst[0], {
			props : [ 'aaa100', 'aaa102', 'aaa103', 'aaaa' ]
		}));
		
		alert(powersi.param(lst) + "\r\n\r\n" +
		powersi.param(lst, {
			beanName : "dto",
			props : [ 'aaa100', 'aaa102', 'aaa103' ]
		}));
	}
}
/*
function checkAllClicked(dwId, dwEvent){
	if(dwEvent == null){
		return;
	}
	
	if (dwEvent.Button != 1 || dwEvent.Row != 0 || dwEvent.Name != 'cbxall') {
	    return;
	}
	
	var dw = getDataWindow(dwId);
	if(dw && dw.RowCount() > 0){
		var cbxAllVal = dw.GetItemString(1, "cbxall", 0, false);
		dw.SetItem(1, "cbxall", cbxAllVal == "0" ? 1 : 0);
		selectDataWindow(dwId, 'flag', (cbxAllVal  == "0") ? 1 : 0);
	}
}*/

function importEnd(dwId) {
	//alert(dwId);
}

function printSetup() {
	var setting = getPrintSetting();
	alert(powersi.tostring(setting));
}

var headerY, detailY = null;
function hide1(){
	selectTab('#divTabs', 2);
	
	var dw = getDataWindow("dw_4");
	if(dw){
		if(headerY == null){
			headerY = dw.Describe("dw_header.y");
			detailY = dw.Describe("dw_detail.y");
		}
		
		var visible = dw.Describe("dw_header.visible");
		if(visible == "1"){
			dw.Modify("dw_header.Visible=0");
			dw.Modify("dw_detail.y=" + headerY);
		} else {
			dw.Modify("dw_header.Visible=1");
			dw.Modify("dw_detail.y=" + detailY);
		}
	}
}

function init(){
	if(isDataWindow("dw_1")){
		var dw3 = document.getElementById("dw_3");

		if(dw3 != null){
			//填充数据
			dw3.Reset();
			for(var i = 0; i < 10; i++)
				dw3.ImportString(1, '1	市四医院	3	1	200	200	0	0	0	0	0	0	0	0	0	0	0	200		\n1	区医科大二附院	3	0	0	0	0	0	0	1	1	9000	8000	0	0	0	0	1000		\n1	市六医院	2	1	300	100	1	100	100	0	0	0	0	1	1	6000	5000	0		', 0,0,0,0,0);
			
			//分组计算
			dw3.GroupCalc();
		
			//修改报表人
			dw3.Modify("zbr.text='<%=userName%>'");
			//dw3.Modify("t_title.text='<%=centerName%> " + dw3.Describe("t_title.text") + "'");
			
			//修改报表标题
			dw3.Modify("t_title.text='${centerName}" + dw3.Describe("t_title.text") + "'");
			
			//修改报表日期
			dw3.Modify("t_date.text='" + moment().startOf('month').format("YYYY年MM月DD日") + " - " + moment().endOf('month').format("YYYY年MM月DD日") + "'");
		}

		//alert(money2cap("12,345,678"));
		//alert(money2cap(-98765.43));

		importDataWindowWithArray("dw_4|dw_header", [{
			"corp_name": "长沙创智和宇信息技术有限公司"
		}]);
		var dwHeader = getDataWindowChild("dw_4", "dw_header");
		if (dwHeader != null && dwHeader.RowCount() > 0) {
			dwHeader.Modify("t_rmb.text='" + money2cap("888987654321.88") + "'");
		}
		
		//修改图片大小
		//x轴 1unit=1px*4 y轴 1unit=1px*4.571
		S("dw_2").Modify("compute_photo.width=" + Math.ceil(70*4.571));
		S("dw_2").Modify("compute_photo.height=" + 75*4);
		
		//修改图片路径
		var photoUrl = getRootUrl() + rootPath;
		photoUrl += "/resource/images/zanque.jpg";
		//photoUrl += "/login/verifycode.action";
		try{
			var photoFile = "datwindow_jsp_temp.jpg";
			//下载图片到本地，解决打印无法预览的问题
			if(quietDownload(photoUrl, "C:\\PowerSI\\temp", photoFile) > 0){
				photoUrl = "C:\\PowerSI\\temp\\" + photoFile;
			}
		}catch(e){
		}
		S("dw_2").Modify("compute_photo.Expression='Bitmap(\"" + photoUrl + "\")'");
		
		//批量图片路径
		try{
			var url = "/resource/images/bank/";
			var jpgs = ["95501.jpg",
			        	"95508.jpg",
			        	"95516.jpg",
			        	"95526.jpg",
			        	"95528.jpg",
			        	"95533.jpg",
			        	"95555.jpg",
			        	"95558.jpg",
			        	"95559.jpg",
			        	"95561.jpg",
			        	"95566.jpg",
			        	"95568.jpg",
			        	"95577.jpg",
			        	"95580.jpg",
			        	"95588.jpg",
			        	"95595.jpg",
			        	"95599.jpg"];
			$.each(jpgs, function(n, jpg){
				quietDownload(url + jpg, "C:\\PowerSI\\temp", "bank-" + (n + 1).toString() + '.jpg');
			});
			//可以加个图片文件名的列
			var exp = "compute_photo.Expression=\"Bitmap('C:\\PowerSI\\temp\\bank-' + string(mod(getRow() - 1, 17) + 1) + '.jpg')\"";
			S("dw_1").Modify(exp);
		}catch(e){
		}
		
	}
 
    $("object").css("border", "0");
    
    initSelect2('#SelectFind_dw1');
    initSelect2('#SelectFind_dw2');
}

function bigData() {
	var rows = getDataWindowForArray("dw_1");
	var a = [];
	for(var i = 0; i < 30; i ++){
		$.merge(a, rows);
	}
	var data = powersi.tostring(a);
	if(!confirm('即将上传 ' + data.length + ' 字节数据，是否继续？\r\n(Tomcat需要修改server.xml，增加maxPostSize="0"，否则数据过大无法接收)')){
		return;
	}

	postJSON(rootPath + "/sample/Sample!bigSubmit.action", {"data": data}, function(json){
		alert(json.message + ":"  + powersi.tostring(json.data));
	});
}

function bigData2() {
	var rows = getDataWindowForArray("dw_1");
	
	var a = [];
	for(var i = 0; i < 40; i ++){
		$.merge(a, rows);
	}
	
	if(!confirm('即将上传 ' + a.length + ' 条数据，是否继续？\r\n(Tomcat需要修改server.xml，增加maxPostSize="0"，否则数据过大无法接收)')){
		return;
	}

	//定义数据对象，可以附加其他业务参数
	var data = {
		'size': a.length, 'param1':'11','param2':'测试参数', 'param3':new Date()
	};
	
	var batch = 1000;//定义批量行数
	var arr = [];
	var idx = 0;
	//把大数据拆分成多个指定批量的小数据
	$.each(a, function(i, row){
		if(i > 0 && i % batch == 0){
			data['data' + idx] = powersi.tostring(arr);
			idx ++;
			arr.length = 0;
		}
		arr.push(row);
	});
	
	if(arr.length > 0){
		data['data' + idx] = powersi.tostring(arr);
		idx ++;
		arr.length = 0;
	}
	
	rows.length = 0;
	a.length = 0;
	postJSON(rootPath + "/sample/Sample!bigData.action", data, function(json){
		alert(json.message);
	});
}

function pager1() {
	openDialog(rootPath + '/pages/sample/pagination.jsp');
}

function dyn1() {
	openDialog(rootPath + '/pages/sample/datawindow2.jsp');
}

var dlgHelp = null;
function popup() {
	if(!isDataWindow("dw_1")){
		return;
	}
	
	if(dlgHelp){
		dlgHelp.show();
	} else{
		dlgHelp = popupDiv("#dlgHelp", "帮助", 500, {
			buttons: [{ text: '关闭', onclick: function(button, dialog, index) {
				dialog.hide();
			}}]
		});
	}
}

$(function(){
	setTimeout("popup()", 1000);
});
</script>
</body>
</powersi:html>