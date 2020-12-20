<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%
	Object cameraInfo = session.getAttribute("cameraInfo");
%>
<powersi:html>
<head>
<powersi:head title="社保卡驱动选择及读卡测试" />
</head>
<body>
	<powersi:tabbedpanel id="divTabs">
		<powersi:tab id="tab2" target="divTab2" label="卡机管理" />
		<powersi:tab id="tab1" target="divTab1" label="摄像头管理" />
		<powersi:tab id="tab3" target="divTab3" label="指静脉采集" />
		<div id="divTab3">
			<powersi:panelbox key="panel_query" title="操作">
				<powersi:panelbox-toolbar>
					<powersi:button id="btnCommit" label="采 集" buttonIcon="icon-photograph" onclick="fingerVeins()"/>
				</powersi:panelbox-toolbar>
			</powersi:panelbox>
		</div>
		<div id="divTab1">
			<powersi:panelbox key="panel_query" title="操作">
				<powersi:panelbox-toolbar>
					<powersi:button id="btnCommit"	  label="拍 摄" buttonIcon="icon-photograph" onclick="doPhoto()"/>
					<powersi:button id="settlementId" label="保 存" buttonIcon="icon-ticket"     onclick="saveCameraInfo()"/>
				</powersi:panelbox-toolbar>
			</powersi:panelbox>
			<div class="row">
				<div class="col-7">
					<powersi:panelbox title="拍摄内容列表">
						<powersi:datagrid id="grid2" name="grid2" enabledEdit="true">
							<powersi:datagrid-column name="id" display="编号" hide="true"/>
							<powersi:datagrid-column name="status" display="状态" width="10%"/>
							<powersi:datagrid-column name="name" display="名称" width="20%"/>
							<powersi:datagrid-column name="device"  render="_render1" display="拍摄设备" width="30%" 
								editor="{type:'select',data:deviceList,valueField: 'id', textField: 'name'}"/>
							<powersi:datagrid-column name="crop" render="_render1"  display="裁剪模板" width="20%" 
								editor="{type:'select',data:cropList,valueField: 'id', textField: 'name'}"/>
							<powersi:datagrid-column name="rorate" render="_render1"  display="旋转模板" width="20%" 
								editor="{type:'select',data:rorateList,valueField: 'id', textField: 'name'}"/>
						</powersi:datagrid>
					</powersi:panelbox>
				</div>
				<div class="col-5">
					<powersi:panelbox title="当前可用设备列表" >
						<powersi:datagrid id="grid1" name="grid1" isMultiSelect="false" enabledEdit="true">
							<powersi:datagrid-column name="id" display="编码" hide="true"/>
							<powersi:datagrid-column name="name" display="名称" width="40%"/>
							<powersi:datagrid-column name="resolution" display="像素" width="30%" 
								editor="{type:'select',data:resolutionList}"/>
							<powersi:datagrid-column name="model" display="设备型号" width="30%"/>
						</powersi:datagrid>
					</powersi:panelbox>
				</div>
			</div>
			<div id="images"></div>
		</div>
		<div id="divTab2">
			<powersi:panelbox title="当前可选社保卡驱动">
					<div id="CommonSSCardRadio" align="center">
					</div>
					
					<div align="center">
						
						<powersi:button onclick="readCard('300000')" label="读社保卡基本信息"></powersi:button>
						<powersi:button onclick="readCard('300011')" label="社保卡PIN校验"></powersi:button>
						<powersi:button onclick="readCard('300012')" label="社保卡PIN修改"></powersi:button>
						<powersi:button onclick="readPass()" label="获取密码键盘输入的密码"></powersi:button>
					</div>
					
					<div>
						<form id="form1" name="form1">
						<powersi:editorlayout cols="8">
							<tr>
								<powersi:textfield id="item_1" name="item_1" label="发卡地区行政区划代码" disabled="true"></powersi:textfield>
								<powersi:textfield id="item_2" name="item_2" label="社会保障号码" disabled="true"></powersi:textfield>
								<powersi:textfield id="item_3" name="item_3" label="卡号" disabled="true"></powersi:textfield>
								<powersi:textfield id="item_4" name="item_4" label="卡识别码" disabled="true"></powersi:textfield>
							</tr>
							<tr>
								<powersi:textfield id="item_5" name="item_5" label="姓名" disabled="true"></powersi:textfield>
								<powersi:textfield id="item_6" name="item_6" label="卡复位信息" disabled="true"></powersi:textfield>
								<powersi:textfield id="item_7" name="item_7" label="规范版本" disabled="true"></powersi:textfield>
								<powersi:textfield id="item_8" name="item_8" label="发卡日期" disabled="true"></powersi:textfield>
							</tr>
							<tr>
								<powersi:textfield id="item_9" name="item_9" label="卡有效期" disabled="true"></powersi:textfield>
								<powersi:textfield id="item_10" name="item_10" label="终端机编号" disabled="true"></powersi:textfield>
								<powersi:textfield id="item_11" name="item_11" label="终端设备号" disabled="true"></powersi:textfield>
								
							</tr>	
							<tr>
								<powersi:textfield id="success_flag" name="success_flag" label="成功标志" disabled="true"></powersi:textfield>
								<powersi:textarea id="tip_info" name="tip_info" label="提示信息" disabled="true" rows="1" colspan="6"></powersi:textarea>
							</tr>
							
						
						</powersi:editorlayout>
						</form>
					</div>
		</powersi:panelbox>
		</div>
	</powersi:tabbedpanel>
	<powersi:errors />
</body>
</powersi:html>
<script type="text/javascript">	
	$(function(){
		__isPing(__device_info);
	});
	
	
	var __device_info=function(){ //获取接口服务模块支持的设备驱动模块信息  
		var req={"func_id":"device_info","data":{"service_code":"CommonSSCard"}};
		call_agent_manage(req,function(obj){
			if (!obj.success_flag || obj.data == undefined || obj.data.result == undefined)
			{
				alert(obj.error_info);
				return;
			}
			
			if (obj.data.result.length > 0){
				var appendHtml="";
				$.each( obj.data.result, function( index, val ) {
					appendHtml+="<span><input type='radio' name='card' id='"+val.code+"' onclick='setCurr(this);' value='"+val.code
								+"'/> <label for='"+val.code+"' >"+val.name+"</label></span>";
					appendHtml+="&nbsp;&nbsp;&nbsp;"
				} );
				$("#CommonSSCardRadio").html(appendHtml);
			}
		});
	}
	
	var setCurr=function(t){  //设置接口服务模块当前的设备驱动模块信息
		var req = {};
		req.func_id = "device_config";
		req.data = {};
		req.data.service_code = "CommonSSCard";
		req.data.cur_device = $(t).val();
		call_agent_manage(req,service_save_callback);
	};
	
	function service_save_callback(obj) {
		if (!obj.success_flag) {
			alert(obj.error_info);
		}
		
	}
    
	//TS20021800012   【需求开发】湘潭核三结算云支持社保卡三代卡读卡改造 陈洁  20200304
	var readCard=function(func_id){
		var req = {"func_id":func_id};
		
		if(func_id === '300000'){
			call_agent_service({
				"func_id": func_id
			},function(obj) {
				if(null == obj || !obj.success_flag) { // 失败
					popupAlert(obj.error_info + "", "提示", "error");
				} else { // 成功
					if(null != obj.data.need_encryptor && obj.data.need_encryptor) { // 无PSAM卡或权限不够
						// TS20032000025 - 【需求开发】湘潭市三代卡读卡需求  提供esb接口，修改控件调用地址   add by xiexiao 20200320
						var encryptor_url = "http://10.137.67.246:20001/hygeia_esb/api/call_api.action";// 加密前置服务地址
						call_agent_service({
							"func_id": func_id,
							"data": {
								"encryptor_url": encryptor_url
							}
						}, callBack);
					}else {
						callBack(obj);
					}
				}
			});
		}else {
			call_agent_service(req, callBack);
		}
	};
	
	function callBack(obj){
		$("#form1 :input").val("");
		if (!obj.success_flag) {
			alert(obj.error_info);
			$("#success_flag").val("失败！");
			$("#tip_info").val(obj.error_info);
		}else{
			if(null!=obj.data){
				editReversal(obj.data);
			}
			$("#success_flag").val("成功！");
		}
	}
	
	var readPass=function(){//获取密码键盘输入的密码
		var req = {"func_id":"310000","data":{"timeout":20,"voice_type":1}};
		call_agent_service(req,function(obj){
			$("#form1 :input").val("");
			if (!obj.success_flag)
			{
				alert(obj.error_info);
				$("#success_flag").val("失败！");
				$("#tip_info").val(obj.error_info);
			}else{
				if(null!=obj.data){
					$("#tip_info").val(obj.data.out_info);
				}
				$("#success_flag").val("成功！");	
			}
		});
	};
	
	function editReversal(json){
		   for (var p in json) {
		        // 方法，不作处理
		        if (typeof (json[p]) == " function ") {
		 
		        } else {
		        	if($("#" + p).length>0 ){
		        		$("#" + p).val(json[p]);
		        	}
		        }
		    }
		
	}
</script>

<script type="text/javascript">	
	var deviceList=[];//拍摄设备
	var imgUrl;
	var imgUrl_t="http://127.0.0.1:8088";
	var resolutionList =[{"id":"1280,720","text":"1280,720"},
	                     {"id":"1600,1200","text":"1600,1200"},
	                     {"id":"2048,1536","text":"12048,1536"}]; //分辨率   让用户选择具体像素
    //默认拍摄内容
	var contentList = [{
			"id": "1",
     		"name": "业务图像采集",
      		"device":"0",
      		"crop": "A4",
      		"rorate": "SELECT",
      		"scan_barcode": false,
      		"status":"未拍摄"
      	}];
	                     
	//裁剪模板            
	var  cropList = [{
			"id": "A4",
			"name": "正常",
			"width": 297,
			"height": 210,
			"standard": true
		},{
			"id": "IDCARD2",
			"name": "二代身份证",
			"width": 85.6,
			"height": 54,
			"standard": true
		},{
			"id": "SICARD",
			"name": "社会保障卡",
			"width": 85.6,
			"height": 54,
			"standard": true
		}];
	
	//旋转角度模板
	var rorateList = [{
			"id": "0",
			"name": "无",
			"rorate": false,
			"angle": 0,
			"clockwise": false
		},{
			"id": "-90",
			"name": "逆时钟转90度",
			"rorate": true,
			"angle": 90,
			"clockwise": false
		},{
			"id": "-180",
			"name": "逆时钟转180度",
			"rorate": true,
			"angle": 180,
			"clockwise": false
		},{
			"id": "-270",
			"name": "逆时钟转270度",
			"rorate": true,
			"angle": 270,
			"clockwise": false
		}];
		
	$(function(){
		__isPing(initPhoto);
	});
	
	function initPhoto(t){
		var req = {"func_id": "100000"};
		call_agent_service(req,function(obj){
			if (!obj.success_flag || obj.data == undefined) {
				alert(obj.error_info);
				return;
			}
			if (obj.data.result.length > 0) {
				var deviceList_t = obj.data.result;
				for (var i=0;i<deviceList_t.length;i++) {
					deviceList_t[i].id = String(deviceList_t[i].index);
					deviceList_t[i].resolution = "1280,720";  //默认像素
					deviceList.push(deviceList_t[i]);
				}
				grid1.loadData(deviceList_t);
				grid2.loadData(contentList);
			}
		});
	};
	
	var imgUrlArr1=[];
	var imgUrlArr2=[];
	var imgUrlArr3=[];
	var imgUrlArr4=[];
	
	/* 拍摄 */
	function doPhoto(){
		var req = {};
		req.func_id = "100001";
		req.data = {};
		req.data.title = "高拍仪拍摄";
		var dataDevice = grid1.getData();
		req.data.device = []; //设备信息数组
		for (var i=0;i<dataDevice.length;i++) {
			req.data.device[i] = dataDevice[i];
		}
		req.data.crop = cropList;//裁剪模板数组
		req.data.rorate = rorateList;//图像旋转模板数组
		var dataContent = grid2.getData();
		req.data.content = [];//内容模板数组
		for (var i=0;i<dataContent.length;i++) {
			req.data.content[i] = dataContent[i];
		}
		call_agent_service(req,service_callback);
	}
	
	/*指静脉采集*/
	function fingerVeins(){
		var req = {};
		req.func_id = "500000";
		call_agent_service(req,function(obj){
			alert(powersi.tostring(obj));
		});
	}
	
	/* 保存摄像头配置 */
	function saveCameraInfo(){
		var req = {};
		req.func_id = "100001";
		req.data = {};
		req.data.title = "高拍仪拍摄";
		var dataDevice = grid1.getData();
		req.data.device = []; //设备信息数组
		for (var i=0;i<dataDevice.length;i++) {
			req.data.device[i] = dataDevice[i];
		}
		req.data.crop = cropList;//裁剪模板数组
		req.data.rorate = rorateList;//图像旋转模板数组
		var dataContent = grid2.getData();
		req.data.content = [];//内容模板数组
		for (var i=0;i<dataContent.length;i++) {
			req.data.content[i] = dataContent[i];
		}
		postJSON("${rootPath}/hosp/EquipmentAction!saveCameraInfo.action",{
					"cameraInfo" : powersi.tostring(req) },
			function (json){
				if (!checkJSONResult(json)) 
					return;
				alert(json.data);
			 });
		
	}
	
	var service_callback=function(obj){
		if (!obj.success_flag || obj.data == undefined) {
			alert(obj.error_info);
			return;
		}
		if (obj.data.result.length > 0) {
			$.each(obj.data.result, function(i,item) {
				grid2.updateCell("status","<a href='javascript:void(0)' onclick='popdiv("+item.content_id+")' >已拍摄</a>",item.content_id-1);
				if("1"==item.content_id){
					imgUrlArr1.push(imgUrl_t+item.url);
				}else if("2"==item.content_id){
					imgUrlArr2.push(imgUrl_t+item.url);
				}else if("3"==item.content_id){
					imgUrlArr3.push(imgUrl_t+item.url);
				}else if("4"==item.content_id){
					imgUrlArr4.push(imgUrl_t+item.url);
				}
			});
		}
	};
	
	function popdiv(t){
		var imgUrlArr;
		if("1"==t){
			imgUrlArr=imgUrlArr1;
		}else if("2"==t){
			imgUrlArr=imgUrlArr2;
		}else if("3"==t){
			imgUrlArr=imgUrlArr3;
		}else if("4"==t){
			imgUrlArr=imgUrlArr4;
		}
		var divs;
		$.each(imgUrlArr, function(i,item){
			divs=divs+"<div><img width='300' height='200' src='"+item+"' /></div>";
		});
		popupDiv(divs, "拍摄图片", 600);
	}
	
	function _render1(rowdata, index, value, column, display) {
			if (!value)
				return;
			var values = (value + "").split(/;|,/);
			if (values.length > 1) {
				$.each(values, function(i, v) {
					if (display) {
						display += ";" + _render(rowdata, index, v, column, display);
					} else {
						display = _render(rowdata, index, v, column, "");
					}
				});
				return display;
			} else {
				var l = window[column.columnname + "List"];
				if (!l)
					l = $("#" + column.columnname + " option");
				if (l) {
					$.each(l, function(i, n) {
						if (n['id']) {
							if (value == n.id) {
								value = n.name;
								return false;
							}
						} else {
							if (value == $(n).val()) {
								if (display) {
									display += ";" + $(n).text();
								} else {
									value = $(n).text();
								}
								return false;
							}
						}
					});
				}
			}
			return value == '00' ? "全部" : value;
		}
</script>
