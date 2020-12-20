<%@page import="com.powersi.hygeia.framework.util.DBHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	//以下只是为了演示，正式程序不建议在jsp中使用sql语句
	request.setAttribute("nationList", DBHelper.executeList("select data_value, display_value from sys_code_table_detail where code_type = 'nation' order by dis_order"));

String initValue = "login_flag,log_flag";
String initText = "生育门诊申请目的1,生育申请住院目的2";
%>
<powersi:html>
<head>
<powersi:head title="combobox组合框" />
<script type="text/javascript">
//客户端数据放最前面，避免非ready加载的函数报变量不存在
var citys = [
             {"aaa027":"430600","aaa129":"岳阳市医保处"},
             {"aaa027":"430602","aaa129":"楼区分中心"},
             {"aaa027":"430603","aaa129":"云溪区分中心"}
    		];
var citys2 = [];
var sel = [];
citys2.push({
	'pid': '0',
	'id': "'000000'",
	'text': '统筹地区'
});
$.each(citys, function(i, center){
		citys2.push({
		'pid': "'000000'",
		'id': "'" + center['aaa027'] + "'",
		'text': center['aaa129']
	});
	sel.push("'" + center['aaa027'] + "'");
});
$('#centerList2').val(sel.join(','));

var centerList = [
	     {"id": "440000", "text": "广东省", pid: "0"}, 
	     {"id": "440100", "text": "广州市", pid: "440000"}, 
	     {"id": "440103", "text": "荔湾区", pid: "440100"}, 
	     {"id": "440104", "text": "越秀区", pid: "440100"}, 
	     {"id": "440105", "text": "海珠区", pid: "440100"}, 
	     {"id": "440106", "text": "天河区", pid: "440100"}, 
	     {"id": "440111", "text": "白云区", pid: "440100"}, 
	     {"id": "440112", "text": "黄埔区", pid: "440100"}, 
	     {"id": "440113", "text": "番禺区", pid: "440100"}, 
	     {"id": "440114", "text": "花都区", pid: "440100"}, 
	     {"id": "440115", "text": "南沙区", pid: "440100"}, 
	     {"id": "440116", "text": "萝岗区", pid: "440100"}, 
	     {"id": "440183", "text": "增城市", pid: "440100"}, 
	     {"id": "440184", "text": "从化市", pid: "440100"}
   		];
//ligerTree会修改原始data，如果复用data的话，必须使用深度复制克隆数据，否则数据会翻倍
//不复用数据不需要这么恶心处理
var centerTree1 = powersi.tojson(powersi.tostring(centerList));
var centerTree2 = powersi.tojson(powersi.tostring(centerList));
var searchCenterTree = powersi.tojson(powersi.tostring(centerList));

var insurList = [{"pid":"0","aae140_code":"-1","aae140_name":"险种类型"},{"pid":"-1","aae140_code":"310","aae140_name":"城镇职工医疗保险"},{"pid":"310","aae140_code":"1","aae140_name":"在职"},{"pid":"310","aae140_code":"2","aae140_name":"退休"},{"pid":"-1","aae140_code":"350","aae140_name":"公费医疗"},{"pid":"350","aae140_code":"61","aae140_name":"公医在职"},{"pid":"350","aae140_code":"62","aae140_name":"公医退休"},{"pid":"350","aae140_code":"63","aae140_name":"公医家属"},{"pid":"350","aae140_code":"64","aae140_name":"公医在职残军"},{"pid":"350","aae140_code":"65","aae140_name":"公医退休残军"},{"pid":"350","aae140_code":"60","aae140_name":"离休"},{"pid":"350","aae140_code":"66","aae140_name":"企业离休"},{"pid":"-1","aae140_code":"391","aae140_name":"城镇居民基本医疗保险"},{"pid":"391","aae140_code":"70","aae140_name":"学龄前儿童111111111"},{"pid":"391","aae140_code":"71","aae140_name":"中小学生"},{"pid":"391","aae140_code":"72","aae140_name":"大中专学生"},{"pid":"391","aae140_code":"73","aae140_name":"其他未成年人"},{"pid":"391","aae140_code":"74","aae140_name":"非从业人员"},{"pid":"391","aae140_code":"75","aae140_name":"老年人"},{"pid":"-1","aae140_code":"410","aae140_name":"工伤保险"},{"pid":"410","aae140_code":"1","aae140_name":"在职"},{"pid":"410","aae140_code":"2","aae140_name":"退休"},{"pid":"-1","aae140_code":"510","aae140_name":"生育保险"},{"pid":"510","aae140_code":"1","aae140_name":"在职"},{"pid":"510","aae140_code":"2","aae140_name":"退休"}];
</script>
</head>
<body class="grid">
	<div class="row">
		<div class="col-6">
			<powersi:panelbox key="panel_query" title="使用客户端数据">
				<powersi:form id="form1">
					<div class="row">
						<div class="col-6">
							<powersi:combobox id="cb1" valueFieldID="val1" data="centerList">
								<powersi:hidden id="val1" name="val1" value="440100"/>
							</powersi:combobox>
						</div>
						<div class="col-6">
							<powersi:combobox id="cb2" valueFieldID="val2" data="centerList" isMultiSelect="true">
								<powersi:hidden id="val2" name="val2" value="440106,440116"/>
							</powersi:combobox>
						</div>
					</div>
					<div class="divButton">
						<powersi:button id="btn1" key="button_view" onclick="view1()"></powersi:button>
					</div>
				</powersi:form>
			</powersi:panelbox>
		</div>
		<div class="col-6">
			<powersi:panelbox key="panel_query" title="使用服务端数据">
				<powersi:form id="form2">
					<div class="row">
						<div class="col-6">
							<powersi:combobox id="cb3" valueFieldID="val3" name="nationList" valueField="data_value" textField="display_value">
								<powersi:hidden id="val3" name="val3" value="1"/>
							</powersi:combobox>
						</div>
						<div class="col-6">
							<powersi:combobox id="cb4" valueFieldID="val4" name="nationList" valueField="data_value" textField="display_value" isMultiSelect="true">
								<powersi:hidden id="val4" name="val4" value="1,2,3" />
							</powersi:combobox>
						</div>
					</div>
					<div class="divButton">
						<powersi:button id="btn2" key="button_view" onclick="view2()"></powersi:button>
					</div>
				</powersi:form>
			</powersi:panelbox>
		</div>
	</div>
	<div class="row">
		<div class="col-6">
			<powersi:panelbox key="panel_query" title="使用码表数据">
				<powersi:form id="form3">
					<div class="row">
						<div class="col-6">
							<powersi:combobox id="cb5" valueFieldID="val5" codeType="aaa027_list">
								<powersi:hidden id="val5" name="val5" value="440106"/>
							</powersi:combobox>
						</div>
						<div class="col-6">
							<powersi:combobox id="cb6" valueFieldID="val6" codeType="aaa027_list" isMultiSelect="true">
								<powersi:hidden id="val6" name="val6" value="440106,440116"/>
							</powersi:combobox>
						</div>
					</div>
					<div class="divButton">
						<powersi:button id="btn3" key="button_view" onclick="view3()"></powersi:button>
						<powersi:button id="btn31" key="button_select" onclick="sel3()"></powersi:button>
					</div>
				</powersi:form>
			</powersi:panelbox>
		</div>
		<div class="col-6">
			<powersi:panelbox key="panel_query" title="使用Ajax联动">
				<powersi:form id="form6">
					<div class="row">
						<div class="col-6">
							<powersi:combobox id="cb9" valueFieldID="val9" onSelected="selectDetail" 
								valueField="code_type" textField="code_name" url="${rootPath }/sample/Sample!queryCodetable.action">
								<powersi:hidden id="val9" name="val9" />
							</powersi:combobox>
						</div>
						<div class="col-6">
							<powersi:combobox id="cb10" valueFieldID="val10" isMultiSelect="true"
								valueField="data_value" textField="display_value">
								<powersi:hidden id="val10" name="val10" />
							</powersi:combobox>
						</div>
					</div>
					<div class="divButton">
						<powersi:button id="btn6" key="button_view" onclick="view6()"></powersi:button>
					</div>
				</powersi:form>
			</powersi:panelbox>
		</div>
	</div>
	<div class="row">
		<div class="col-6">
			<!-- 使用powersi:script处理标签输出（id为返回值，即js变量名） -->
			<!-- 如果使用了客户端数据，请务必保证客户端数据变量在此段代码之前定义，否则会报变量不存在 -->
			<powersi:script>
				/*cbx为true表示多选，false表示单选,所有参数都可以使用js赋值(id['参数名']=参数值)*/
				function getGridOptions(cbx){
					<powersi:datagrid id="gridOptions" frozen="false" selectRowButtonOnly="false"
						url="${rootPath }/sample/Sample!queryCodetable.action" pageSize="10" showPageSize="false" showReload="false">
						<powersi:datagrid-column display="代码类型" name="code_type" width="30%" minWidth="80" />
						<powersi:datagrid-column display="代码名称" name="code_name" width="30%" minWidth="80" />
						<powersi:datagrid-column display="代码SQL"  name="code_sql"  width="40%" minWidth="100" align="left"/>
					</powersi:datagrid>
					gridOptions["checkbox"] = cbx;
					
					return gridOptions;
				}
				
				function selectCenter(newvalue){
					if(cb11 && cb11.grid){
						cb11.grid.searchGrid();
					}
				}
				
				/*获取combobox配置*/
				function getSearchCenter(){
					<powersi:combobox id="cb" treeLeafOnly="true" onSelected="selectCenter"
						selectBoxHeight="200" selectBoxWidth="300"
						valueField="id" textField="text"
						tree="{data:searchCenterTree, checkbox: true, idFieldName:'id', parentIDFieldName:'pid', textFieldName:'text', topParentIDValue:'0'}">
					</powersi:combobox>
					/*初始化选择440103,440104*/
					cb["initValue"] = "440103,440104";
					return cb;
				}
				
				try{
					/*自定义grid查询面板，参数与ligerForm相同
					  支持hidden，text, date, number, digits, combobox(select)
					  支持基于jQuery Validation Plugin(http://jqueryvalidation.org/)的表单验证
					  1.newline为false不换行，否则换行
					  2.prefixID可以保证不同面板相同name的id不会重复(实际返回后台的name是没有前缀的name)
					  3.使用combobox的时候，comboboxName请维护成与name相同值，否则验证的时候会出现显示问题
					  4.showSarch显示查询按钮,showClose显示关闭按钮,validate验证查询条件
					*/
					<powersi:dataform id="condition1" prefixID="search1_" inputWidth="120"  showSearch="true" showClose="true">
						<powersi:dataform-field name="center_id" label="选择中心" width="508"  type="combobox" options="getSearchCenter()" required="true" />
						<powersi:dataform-field name="query_date" label="查询日期" type="date" options="{format:'yyyyMMdd'}" newline="true" />
						<powersi:dataform-field key="code_type" />
						<powersi:dataform-field key="code_name" />
					</powersi:dataform>
					
					<powersi:dataform id="condition2" prefixID="search2_" inputWidth="90">
						<powersi:dataform-field key="code_type" />
						<powersi:dataform-field key="code_name" />
						<powersi:dataform-field key="code_sql" display="代码SQL" />
					</powersi:dataform>
				} catch(ex){
					alert(ex.message);
				}
			</powersi:script>
			<powersi:panelbox key="panel_query" title="使用分页表格1">
				<powersi:form id="form7">
					<powersi:editorlayout cols="25%,75%">
						<powersi:editorlayout-row>
							<powersi:combobox id="cb11" valueFieldID="val11" label="业务码表" isMultiSelect="true"
								selectBoxWidth="700" selectBoxHeight="300" hideGridOnLoseFocus="false"
								valueField="code_type" textField="code_name"
								initValue="<%=initValue %>" initText="<%=initText %>" title="datagrid因分页原因无法获取初始化文本，请自行处理"
								grid="getGridOptions(true)" condition="condition1">
								<powersi:hidden id="val11" name="val11" />
							</powersi:combobox>
						</powersi:editorlayout-row>
					</powersi:editorlayout>
					<div class="divButton">
						<powersi:button id="btn7" key="button_view" onclick="view7()"></powersi:button>
					</div>
				</powersi:form>
			</powersi:panelbox>
		</div>
		<div class="col-6">
			<powersi:panelbox key="panel_query" title="使用分页表格2">
				<powersi:form id="form8">
					<powersi:editorlayout cols="4">
						<powersi:editorlayout-row>
							<powersi:combobox id="cb12" valueFieldID="val12" label="业务码表" isMultiSelect="true" colspan="3"
								selectBoxWidth="600" selectBoxHeight="300" hideGridOnLoseFocus="true"
								valueField="code_type" textField="code_name"
								initValue="pmc003" initText="生育门诊申请目的" title="datagrid因分页原因无法获取初始化文本，请自行处理"
								grid="getGridOptions(false)" condition="condition2">
								<powersi:hidden id="val12" name="val12" />
							</powersi:combobox>
						</powersi:editorlayout-row>
					</powersi:editorlayout>
					<div class="divButton">
						<powersi:button id="btn8" key="button_view" onclick="view8()"></powersi:button>
					</div>
				</powersi:form>
			</powersi:panelbox>
		</div>
	</div>
	<div class="row">
		<div class="col-6">
			<powersi:panelbox key="panel_query" title="使用树控件1">
				<powersi:form id="form9">
					<powersi:editorlayout cols="25%,75%">
						<powersi:editorlayout-row>
							<powersi:combobox id="cb13" valueFieldID="val13" label="选择中心" required="true" title="多选节点，不允许选择非叶子节点"
								selectBoxHeight="200" treeLeafOnly="true" isMultiSelect="true" hideOnLoseFocus="true"
								valueField="id" textField="text" onSelected="selectedTree"
								tree="{data:centerTree1, checkbox: true, idFieldName:'id', parentIDFieldName:'pid', textFieldName:'text', topParentIDValue:'0'}">
								<powersi:hidden id="val13" name="val13" value="440114,440106,440116" />
							</powersi:combobox>
						</powersi:editorlayout-row>
						<powersi:editorlayout-row>
							<powersi:combobox id="cb15" valueFieldID="centerList1" label="选择险种" required="true" title="选择险种"
								selectBoxHeight="200" treeLeafOnly="true" hideOnLoseFocus="true"
								valueField="aae140_code" textField="aae140_name"
								tree="{nodeWidth: 150, data:insurList, checkbox: true, idFieldName:'aae140_code', parentIDFieldName:'pid', textFieldName:'aae140_name', topParentIDValue:'0'}">
								<powersi:hidden id="centerList1" name="centerList1"  />
							</powersi:combobox>
						</powersi:editorlayout-row>
					</powersi:editorlayout>
					<div class="divButton">
						<powersi:button id="btn9" key="button_view" onclick="view9()"></powersi:button>
						<powersi:button id="btn39" key="button_select" onclick="sel9()"></powersi:button>
					</div>
				</powersi:form>
			</powersi:panelbox>
		</div>
		<div class="col-6">
			<powersi:panelbox key="panel_query" title="使用树控件2">
				<powersi:form id="form10">
					<powersi:editorlayout cols="4">
						<powersi:editorlayout-row>
							<powersi:combobox id="cb14" valueFieldID="val14" label="选择中心" required="true" title="单选节点，允许选择非叶子节点"
								selectBoxHeight="200" treeLeafOnly="false" colspan="3"
								valueField="id" textField="text" onSelected="selectedTree"
								tree="{data:centerTree2, checkbox: false, idFieldName:'id', parentIDFieldName:'pid', textFieldName:'text', topParentIDValue:'0'}">
								<powersi:hidden id="val14" name="val14" value="440106" />
							</powersi:combobox>
						</powersi:editorlayout-row>
						<powersi:editorlayout-row>
							<powersi:combobox id="cb16" valueFieldID="centerList2" label="选择中心" required="true" title="选择中心"
								selectBoxHeight="200" treeLeafOnly="true" colspan="3"
								valueField="id" textField="text"
								tree="{data:citys2, checkbox: true, idFieldName:'id', parentIDFieldName:'pid', textFieldName:'text', topParentIDValue:'0'}">
								<powersi:hidden id="centerList2" name="centerList2"  />
							</powersi:combobox>
						</powersi:editorlayout-row>
					</powersi:editorlayout>
					<div class="divButton">
						<powersi:button id="btn10" key="button_view" onclick="view10()"></powersi:button>
					</div>
				</powersi:form>
			</powersi:panelbox>
		</div>
	</div>
	<div class="row">
		<div class="col-6">
			<powersi:panelbox key="panel_query" title="使用表单样式1">
				<powersi:form id="form4">
					<powersi:editorlayout cols="25%,75%">
						<powersi:editorlayout-row>
							<powersi:combobox id="cb7" valueFieldID="val7" data="centerList" key="aaa027" title="使用440100作为初始化值">
								<powersi:hidden id="val7" name="val7" value="440100"/>
							</powersi:combobox>
						</powersi:editorlayout-row>
					</powersi:editorlayout>
					<div class="divButton">
						<powersi:button id="btn4" key="button_view" onclick="view4()"></powersi:button>
					</div>
				</powersi:form>
			</powersi:panelbox>
		</div>
		<div class="col-6">
			<powersi:panelbox key="panel_query" title="使用表单样式2" allowAddition="true">
				<powersi:form id="form5">
					<powersi:editorlayout cols="4">
						<powersi:editorlayout-row addition="true">
							<powersi:combobox id="cb8" valueFieldID="val8" data="centerList" key="aaa027" colspan="3" required="true" isMultiSelect="true">
								<powersi:hidden id="val8" name="val8" />
							</powersi:combobox>
						</powersi:editorlayout-row>
					</powersi:editorlayout>
					<div class="divButton">
						<powersi:button id="btn5" key="button_view" onclick="view5()"></powersi:button>
					</div>
				</powersi:form>
			</powersi:panelbox>
		</div>
	</div>
	<div class="divButton">
		<powersi:button label="设置只读" key="button_disable" onclick="readonlyTrue()"></powersi:button>
		<powersi:button label="取消只读" key="button_enable" onclick="readonlyFalse()"></powersi:button>
		<powersi:button label="设置禁用" key="button_disable" onclick="disabledTrue()"></powersi:button>
		<powersi:button label="取消禁用" key="button_enable" onclick="disabledFalse()"></powersi:button>
	</div>
<script type="text/javascript">
	function gridSelected(data){
		//alert('123');
	}
	
	var cblen = 16;
	function readonlyTrue(){
		for(var i = 1; i <= cblen; i ++){
			setTimeout('cb' + i + ".set('readonly', true);", 0);
		}
	}
	
	function readonlyFalse(){
		for(var i = 1; i <= cblen; i ++){
			setTimeout('cb' + i + ".set('readonly', false);", 0);
		}
	}
	function disabledTrue(){
		for(var i = 1; i <= cblen; i ++){
			setTimeout('cb' + i + ".set('disabled', true);", 0);
		}
	}
	
	function disabledFalse(){
		for(var i = 1; i <= cblen; i ++){
			setTimeout('cb' + i + ".set('disabled', false);", 0);
		}
	}
	
	function selectDetail(newvalue){
		if(cb10){
			cb10.clear();
			cb10.setParm('type', newvalue);
			cb10.setUrl(rootPath + "/sample/Sample!queryCodetableDetail.action");
		}
	}
	
	function view1() {
		alert($('#form1').serialize() + "\r\nval1:" + $('#val1').val() + "\r\nval2:" + $('#val2').val());
	}
	
	function view2() {
		alert($('#form2').serialize() + "\r\nval3:" + $('#val3').val() + "\r\nval4:" + $('#val4').val());
	}
	
	function view3() {
		alert($('#form3').serialize() + "\r\nval5:" + $('#val5').val() + "\r\nval6:" + $('#val6').val());
	}
	
	function sel3() {
		if(cb6){
			cb6.options.initValue = "440111,440112,440113,440114"; 
            cb6._dataInit();
		}
	}
	
	function view4() {
		if(!checkForm('#form4')){
			return;
		}
		
		alert($('#form4').serialize() + "\r\nval7:" + $('#val7').val());
	}
	
	function view5() {
		if(!checkForm('#form5')){
			return;
		}
		
		alert($('#form5').serialize() + "\r\nval8:" + $('#val8').val());
	}
	
	function view6() {
		if(!checkForm('#form6')){
			return;
		}
		
		alert($('#form6').serialize() + "\r\nval9:" + $('#val9').val() + "\r\nval10:" + $('#val10').val());
	}
	
	function view7() {
		alert($('#form7').serialize() + "\r\nval11:" + $('#val11').val());
	}
	
	function view8() {
		alert($('#form8').serialize() + "\r\nval12:" + $('#val12').val());
	}
	
	function view9() {
		if(!checkForm('#form9')){
			return;
		}
		
		alert($('#form9').serialize() + "\r\nval13:" + $('#val13').val());
	}
	
	function sel9() {
		if(cb13){
			cb13.clear();
			cb13.selectValue("440111,440112,440113,440114");
		}
	}
	
	function view10() {
		if(!checkForm('#form10')){
			return;
		}
		
		alert($('#form10').serialize() + "\r\nval14:" + $('#val14').val());
	}
	
	//根据选择获取树的明细数据
	function selectedTree(newvalue, newtext){
		var tree = this.treeManager;
		if(tree){
			var sels = (newvalue || "").split(",");
			var a = [];
			var item = null;
			$.each(sels, function(i, sel){
				item = tree.getDataByID(sel);
				if(item){
					a.push(item);
				}
			});
			
			//alert(powersi.tostring(a));
		}
	}
</script>
</body>
</powersi:html>