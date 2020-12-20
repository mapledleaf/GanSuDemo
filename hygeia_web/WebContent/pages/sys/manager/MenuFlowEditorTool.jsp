<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="MenuFlowInclude.jsp" %>
<style type="text/css">
.grid {
	background: url(${strutsPath}/js/plugins/myflow/img/bg.gif) 1px 1px repeat;
}
hr {
	margin: 5px 0;
	clear: both;
}

.node {
	text-align: left;
	vertical-align: middle;
	border: 1px solid #fff;
	width: 49%;
	float: left;
	position: relative;
	margin: 0 0 5px 0;
}

.mover {
	border: 1px solid #ddd;
	background-color: #ddd;
}

.selected {
	background-color: #ddd;
}

.state {
	
}

#myflow {
	z-index: 0;
}

#myflow_tools {
	position: absolute;
	top: 10px;
	left: 10px;
	cursor: default;
	padding: 3px;
	font-size: 13px;
	color: #333;
	z-index: 100;
	width: 120px;
}

#myflow_tools > div {
	height: 30px;
	line-height: 30px;
}

#myflow_tools_handle, #myflow_props_handle, #myflow_data_handle {
	text-align: center;
	font-weight: bold;
	color: #333;
	font-size: 14px;
	cursor: move;
	padding: 5px 0;
}

#myflow_tools, #myflow_props, #myflow_data {
	box-shadow: 0 0 6px #999;
	border: 1px solid #aaa;
	background: #fff;
}

#myflow_props {
	top: 10px;
	right: 10px;
	cursor: default;
	padding: 0 10px;
	font-size: 13px;
	color: #333;
	z-index: 100;
	position: absolute;
	width: 300px;
}

#myflow_props_table {
	border: 1px solid #ddd;
	width: 99%;
}

#myflow_props_table th {
	letter-spacing: 2px;
	text-align: right;
	padding: 1px 6px;
	background: #f8f8f8;
	border: 1px solid #ddd;
}

#myflow_props_table td {
	background: #fff;
	padding: 1px 6px;
	border: 0;
	/*border: 1px solid #ddd;*/
}

#myflow_props_attr{
	color: #f00;
	text-align: center;
}
#myflow_props_tip {
	color: #00f;
	font-size: 12px;
}

#myflow_data {
	bottom: 10px;
	right: 10px;
	cursor: default;
	padding: 0 10px;
	font-size: 13px;
	color: #333;
	z-index: 100;
	position: absolute;
	width: 300px;
}

#myflow_data_textarea{
	border: 1px solid #ddd !important;
	height: 100px;
	margin-bottom:10px;
}

#myflow_data_btn {
	text-align:center;
	padding-bottom: 5px;
}
#pointer {
	background-repeat: no-repeat;
	background-position: center;
}

#path {
	background-repeat: no-repeat;
	background-position: center;
}

#task {
	background-repeat: no-repeat;
	background-position: center;
}

#state {
	background-repeat: no-repeat;
	background-position: center;
}

.node>span {
	padding-left: 6px;
	color: #333;
}
.node.selectable{
	cursor: pointer;
}
.node.ui-draggable{
	cursor: move;
}
</style>
<div id="myflow_tools">
	<div id="myflow_tools_handle"><i class="icon-cogs"></i> 工具</div>
	<div class="node" id="myflow_grid">
		<img src="${strutsPath}/js/plugins/myflow/img/grid.png" /><span>网格</span>
	</div>
	<div class="node" id="myflow_save">
		<img src="${strutsPath}/js/plugins/myflow/img/save.png" /><span>数据</span>
	</div>
	<hr />
	<div class="node selectable" id="pointer">
		<img src="${strutsPath}/js/plugins/myflow/img/select16.gif" /><span>选择</span>
	</div>
	<div class="node selectable" id="path">
		<img src="${strutsPath}/js/plugins/myflow/img/16/flow_sequence.png" /><span>连线</span>
	</div>
	<hr />
	<div class="node state" id="start" type="start">
		<img
			src="${strutsPath}/js/plugins/myflow/img/16/start_event_empty.png" /><span>开始</span>
	</div>
	<div class="node state" id="end" type="end">
		<img src="${strutsPath}/js/plugins/myflow/img/16/end_event_terminate.png" /><span>结束</span>
	</div>
	<div class="node state" id="state" type="state">
		<img src="${strutsPath}/js/plugins/myflow/img/16/task_empty.png" /><span>菜单</span>
	</div>
	<div class="node state" id="task" type="task">
		<img src="${strutsPath}/js/plugins/myflow/img/16/task_empty.png" /><span>按钮</span>
	</div>
	<div class="node state" id="caption" type="caption">
		<img src="${strutsPath}/js/plugins/myflow/img/16/task_java.png" /><span>说明</span>
	</div>
	<div class="node state" id="text" type="text">
		<img src="${strutsPath}/js/plugins/myflow/img/16/text.png" /><span>文本</span>
	</div>
	<div class="node state" id="box-solid" type="box-solid">
		<img src="${strutsPath}/js/plugins/myflow/img/16/box_solid.png" /><span>实线</span>
	</div>
	<div class="node state" id="box-dashed" type="box-dashed">
		<img src="${strutsPath}/js/plugins/myflow/img/16/box_dashed.png" /><span>虚线</span>
	</div>
	<div class="node state" id="fork" type="fork">
		<img src="${strutsPath}/js/plugins/myflow/img/16/gateway_exclusive.png" /><span>分支</span>
	</div>
	<div class="node state" id="join" type="join">
		<img src="${strutsPath}/js/plugins/myflow/img/16/gateway_parallel.png" /><span>合并</span>
	</div>
	<div class="node state" id="end-cancel" type="end-cancel">
		<img src="${strutsPath}/js/plugins/myflow/img/16/end_event_cancel.png" /><span>取消</span>
	</div>
	<div class="node state" id="end-error" type="end-error">
		<img src="${strutsPath}/js/plugins/myflow/img/16/end_event_error.png" /><span>错误</span>
	</div>
</div>

<div id="myflow_props">
	<div id="myflow_props_handle"><i class="icon-edit"></i> 属性</div>
	<div id="myflow_props_attr"></div>
	<table id="myflow_props_table" cellpadding="0" cellspacing="0">
	</table>
	<div id="myflow_props_tip"><i class="icon-info-sign"></i> 标识根据需要维护，换行符输入\n</div>
</div>

<div id="myflow_data">
	<div id="myflow_data_handle"><i class="icon-share"></i> 数据</div>
	<div id="myflow_data_btn" class="input-group">
		<input type="text" class="form-control" id="myflow_move_step" value="10" title="每次移动的偏移量" maxlength="2" style="text-align:center;"/>
		<span class="input-group-btn">
			<button type="button" class="btn btn-default" onclick="moveFlow('left')"><i class="icon-circle-arrow-left"></i>左移</button>
			<button type="button" class="btn btn-default" onclick="moveFlow('right')"><i class="icon-circle-arrow-right"></i>右移</button>
			<button type="button" class="btn btn-default" onclick="moveFlow('up')"><i class="icon-circle-arrow-up"></i>上移</button>
			<button type="button" class="btn btn-default" onclick="moveFlow('down')"><i class="icon-circle-arrow-down"></i>下移</button>
		</span>
	</div>
	<div>
		<textarea id="myflow_data_textarea" readonly="readonly"></textarea>
	</div>
</div>
<div id="myflow" class="grid"></div>
<script type="text/javascript">
var top = 10;
var left = 150;
$(function() {
	$("#myflow_grid").click(function(){
		$("#myflow").toggleClass('grid');
	});
	
	$("#myflow_data").draggable({
		handle : "#myflow_data_handle"
	}).resizable().bind("click", function() {
		return false;
	});
});

function getFlowData(){
	return $.myflow.toJson();
}

function moveFlow(dir){
	var x = 0; y = 0;
	var step = parseInt($('#myflow_move_step').val());
	if(step <= 0 || step >= 100){
		alert("移动偏移量必须大于0且小于100");
		$('#myflow_move_step').focus();
		return;
	}
	
	if(dir == 'left'){
		x = -1 * step;
	} else if(dir == 'right') {
		x = step;
	} else if(dir == 'up') {
		y = -1 * step;
	} else if(dir == 'down'){
		y = step;
	} else {
		return false;
	}
	
	$.myflow.move(x, y);
}
</script>