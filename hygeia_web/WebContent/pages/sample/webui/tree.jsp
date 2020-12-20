<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<%@ page import="java.util.List,java.util.Map,java.util.HashMap,java.util.LinkedHashMap" %>
<%@ page import="com.powersi.hygeia.framework.util.UtilFunc,com.powersi.hygeia.framework.util.DBHelper,com.powersi.hygeia.framework.ParameterMapping" %>
<%@ page import="com.powersi.hygeia.web.util.JsonHelper,com.powersi.hygeia.web.util.TreeSerializer" %>
<%
	//为了安全，不要输出菜单url
	String sql = "select menu_id, menu_up_id, menu_name, menu_type, decode(length(win_name), null, '1', '0') as isfolder\n" + 
	"  from sys_menu\n" + 
	" where valid_flag = '1'\n" + 
	"	and	menu_type in ('1')\n" +
	" order by menu_up_id, menu_order";
	List menus = DBHelper.executeList(sql);
	//设置菜单样式
	for(int i = 0; i < menus.size(); i ++){
		Map map = (Map)menus.get(i);
		map.put("iconSkin", "1".equals(UtilFunc.getString(map, "menu_type")) ? "menu" : "right");
	}
	//创建根菜单
	Map rootMenuFont = new HashMap();
	rootMenuFont.put("color", "#f00");
	Map rootMenuNode = new LinkedHashMap();
	rootMenuNode.put("menu_id", 0);
	rootMenuNode.put("menu_up_id", "root");
	rootMenuNode.put("menu_name", ParameterMapping.getSystemName());
	rootMenuNode.put("isfolder", "1");
	
	rootMenuNode.put("iconSkin", "system");
	rootMenuNode.put("font", rootMenuFont);
	rootMenuNode.put("open", true);
	
	//生成简单菜单
	String menuRootJson = JsonHelper.toJson(rootMenuNode);
	String menuListJson = JsonHelper.toJson(menus);
		
	//生成树菜单
	TreeSerializer menuTreeSerializer = new TreeSerializer(menus);
	menuTreeSerializer.setNameOfId("menu_id");
	menuTreeSerializer.setNameOfParentId("menu_up_id");
	menuTreeSerializer.setNameOfText("menu_name");
	menuTreeSerializer.setRootNode(rootMenuNode);
	
	//缺省输出全部字段
	String menuTreeJson = menuTreeSerializer.toJson();
	
	//生成html菜单
	menuTreeSerializer.setAttrs(new String[]{"menu_name"});//指定输出字段
	String menuTreeHtml = menuTreeSerializer.toHtml();
	
	//生成xml菜单
	menuTreeSerializer.setXmlRoot("tree");
	menuTreeSerializer.setXmlNode("node");
	menuTreeSerializer.setXmlFormat(true);
	
	//在html中显示xml需要转义处理
	menuTreeSerializer.setAttrs(new String[]{"menu_id", "menu_name", "menu_up_id"});//指定输出字段
	String menuTreeXml = UtilFunc.encodeXml(menuTreeSerializer.toXml());
	
	sql = "select dept_id as id, dept_up_id as pid, dept_name as name\n" + 
			"  from sys_dept\n" + 
			" where valid_flag = '1'\n" + 
			" order by dis_order";
	List depts = DBHelper.executeList(sql);
	//创建根部门
	String systemName = ParameterMapping.getStringByCode("center_org_name");
	Map rootDeptNode = new LinkedHashMap();
	rootDeptNode.put("id", "0");
	rootDeptNode.put("pid", "root");
	rootDeptNode.put("name", systemName);
	TreeSerializer deptTreeSerializer = new TreeSerializer(depts);
	deptTreeSerializer.setRootNode(rootDeptNode);
	deptTreeSerializer.setAttrs(new String[]{"name"});//指定输出字段
	String deptTreeJson = deptTreeSerializer.toJson();
%>
<powersi:html>
<head>
<powersi:head title="树控件" />
<!-- 引入ztree -->
<script src="${strutsPath}/include/ztree.js" type="text/javascript"></script>
<!-- 引入echarts -->
<script src="${strutsPath}/js/plugins/echarts/echarts-plain-map.js" charset="utf-8"></script>
<script src="${strutsPath}/js/plugins/echarts/echarts-theme.js" charset="utf-8"></script>
<style type="text/css">
pre {
	word-wrap: normal;
	background: #fff;
	border: 0;
	border-radius: 0;
	width: 100%;
	height: 100%;
}
</style>
</head>
<body class="grid">
	<div class="tabbable">
		<ul class="nav nav-tabs" id="tabs">
			<li class="active">
				<a data-toggle="tab" href="#tab-pane1" id="tab1">
					<i class="icon-desktop red"></i>
					zTree
				</a>
			</li>

			<li>
				<a data-toggle="tab" href="#tab-pane2" id="tab2">
					<i class="icon-laptop green"></i>
					ligerTree
				</a>
			</li>

			<li>
				<a data-toggle="tab" href="#tab-pane3" id="tab3">
					<i class="icon-tablet yellow"></i>
					Html&Xml
				</a>
			</li>
			
			<li>
				<a data-toggle="tab" href="#tab-pane4" id="tab4">
					<i class="icon-mobile-phone blue"></i>
					Echarts Tree
				</a>
			</li>
		</ul>
	</div>
	<div class="tab-content">
		<div id="tab-pane1" class="tab-pane active">
			<div class="row">
				<div class="col-6">
					<powersi:panelbox title="使用树数据(性能好，兼容性好)" iconClass="icon-thumbs-up-alt">
						<div class="tree-pane">
							<ul id="ztree1" class="ztree">
							</ul>
						</div>
					</powersi:panelbox>
				</div>
				<div class="col-6">
					<powersi:panelbox title="使用简单数据(性能差，无效节点需自行处理)" iconClass="icon-thumbs-down-alt">
						<div class="tree-pane">
							<ul id="ztree2" class="ztree">
							</ul>
						</div>
					</powersi:panelbox>
				</div>
			</div><!-- end of row -->
		</div><!-- end of tab-pane -->

		<div id="tab-pane2" class="tab-pane">
			<div class="row">
				<div class="col-6">
					<powersi:panelbox title="使用html数据">
						<div class="tree-pane">
							<ul id="tree3">
								<%=menuTreeHtml%>
							</ul>
						</div>
					</powersi:panelbox>
				</div>
				<div class="col-6">
					<powersi:panelbox title="使用json数据">
						<div class="tree-pane">
							<ul id="tree4">
							</ul>
						</div>
					</powersi:panelbox>
				</div>
			</div><!-- end of row -->
		</div><!-- end of tab-pane -->

		<div id="tab-pane3" class="tab-pane">
			<div class="row">
				<div class="col-6">
					<powersi:panelbox title="显示html数据">
						<div class="tree-pane">
							<ul id="html">
								<%=menuTreeHtml%>
							</ul>
						</div>
					</powersi:panelbox>
				</div>
				<div class="col-6">
					<powersi:panelbox title="显示xml数据">
						<div class="tree-pane">
							<pre id="xml"><%= menuTreeXml%></pre>
						</div>
					</powersi:panelbox>
				</div>
			</div><!-- end of row -->
		</div><!-- end of tab-pane -->
		
		<div id="tab-pane4" class="tab-pane">
			<div id="chart">
			</div>
		</div>
	</div>
<powersi:errors />
<script type="text/javascript">
/*为了重用数据，所以单独定义变量*/
var menuTree = <%= menuTreeJson %>;

var menuRoot = <%= menuRootJson %>
var menuList = <%= menuListJson %>;

var treePaneTop = 0;
$(function(){
	//对象必须在可见的情况下获取，隐藏下返回0
	treePaneTop = $(".tree-pane:first").offset().top + 10;
	$(window).resize(setSize);
	setSize();
	
	buildMenuTree1();
	buildMenuTree2();
	
	buildMenuTree3();
	
	buildChartTree();
});

function setSize() {
	$(".tree-pane").height(function(){
		return getPageHeight() - treePaneTop - 20;
	});
	
	$('#chart').width(getPageWidth() - 50).height(getPageHeight() - treePaneTop + 20);
}

function buildMenuTree1() {
	try{
		//ztree设置
		var setting = {
				view: {
					fontCss: getTreeFont,
					autoCancelSelected: false,
					selectedMulti: false,
					dblClickExpand: false,
					showLine: false
				},
				callback: {
					onClick: onTreeClick
				},
				data: {
					key: {
						children: "children",
						name: "menu_name",
						title: "",
						url: ""
					}
				}
		};
	 	//初始化ztree
	    var treeObj = $.fn.zTree.init($("#ztree1"), setting, menuTree);
	 	
	 	//统计子节点个数
		var nodes = treeObj.getNodesByParam("isfolder", "1", null);
	 	$.each(nodes, function(i, node) {
	 		//计算子菜单数
	 		var len = treeObj.getNodesByParam("isfolder", "0", node).length;
	 		if(len > 0){
				node.menu_name = node.menu_name + "[" + len + "]";
				treeObj.updateNode(node);
			}
	 	});
	} catch(ex){
		alert(ex.message);
	}
}

function buildMenuTree2() {
	try{
		var root = <%= menuRootJson %>;
		//ztree设置
		var setting = {
				view: {
					fontCss: getTreeFont,
					autoCancelSelected: false,
					selectedMulti: false,
					dblClickExpand: false,
					showLine: false
				},
				callback: {
					onClick: onTreeClick
				},
				data: {
					key: {
						children: "children",
						name: "menu_name",
						title: "",
						url: ""
					},
					simpleData: {
						enable: true,
						idKey: "menu_id",
						pIdKey: "menu_up_id",
						rootPId: "root"
					}
				}
		};
	 	//初始化ztree
	    var treeObj = $.fn.zTree.init($("#ztree2"), setting, $.merge([menuRoot], menuList));
	 	
	 	//删除无效节点
	    var nodes = treeObj.getNodesByParam("level", "0", null);
	 	if(nodes != null && nodes.length > 0){
	 		$.each(nodes, function(i, node){
	 			if(node.menu_id != '0')
		    		treeObj.removeNode(node);
	 		});
	 	}
	} catch(ex){
		alert(ex.message);
	}
}

function onTreeClick(event, treeId, treeNode, clickFlag) {
	expandNode(treeId, treeNode);
}

function expandNode(treeId, treeNode){
	var zTree = $.fn.zTree.getZTreeObj(treeId);
	zTree.expandNode(treeNode);
}

function buildMenuTree3() {
	try{
		$("#tree3").ligerTree({ checkbox: false });
		var data =  $.merge([menuRoot], menuList);
		
		$("#tree4").ligerTree({ data:data, 
	        idFieldName :'menu_id',
	        parentIDFieldName :'menu_up_id',
	        textFieldName: 'menu_name',
	        topParentIDValue: 'root',
	        checkbox: false,
	        slide : false
	    });
	} catch(ex){
		alert(ex.message);
	}
}

function buildChartTree() {
	try{
		var deptTree = <%= deptTreeJson %>;
		var systemName = '<%= systemName %>组织机构图';
		var a = [];
		for(var i = 0; i < systemName.length; i++){
			a.push(systemName[i]);
		}
		var option = {
			    title : {
			        text: a.join("\n"),
			        x: 'left',
			        y: 'center',
			        padding: 10
			    },
			    toolbox: {
			        show : false
			    },
			    series : [{
			            name:'树图',
			            type:'tree',
			            orient: 'horizontal',  // vertical horizontal radial
			            rootLocation: {x: 50, y: 'center'},//{x: 100, y: 'center'},
			            nodePadding: 0,
			            layerPadding: 200,
			            hoverable: false,
			            roam: true,
			            symbolSize: 8,
			            itemStyle: {
			                normal: {
			                    color: '#4883b4',
			                    label: {
			                        show: true,
			                        position: 'right',
			                        formatter: "{b}",
			                        textStyle: {
			                            color: '#333',
			                            fontSize: 13
			                        }
			                    },
			                    lineStyle: {
			                        color: '#ccc',
			                        type: 'curve' // 'curve'|'broken'|'solid'|'dotted'|'dashed'
			                    }
			                },
			                emphasis: {
			                    color: '#4883b4',
			                    label: {
			                        show: false
			                    },
			                    borderWidth: 0
			                }
			            },
			            
			            data: deptTree
			        }
			    ]
			};
		
		var myChart = echarts.init(S('chart'), ecTheme);
		myChart.setOption(option);
	} catch(ex){
		alert(ex.message);
	}
}
</script>
</powersi:html>