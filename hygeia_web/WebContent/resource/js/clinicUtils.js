function chooseDisease(aka121, aka120, next) {
	chooseDisease(aka121, aka120, next, "");
}

/**
 * 选择疾病，aka121:疾病编码，aka120：疾病名称, next：下一个输入项, isMaternity：是否是生育疾病
 */
function chooseDisease(aka121, aka120, next, isMaternity) {
	var url = "";
	if ("m" == isMaternity) {
		url = "/hygeia_web/common/CommonManagerAction!chooseDisease.action?ka06dto.aka035=6";
	} else {
		url = "/hygeia_web/common/CommonManagerAction!chooseDisease.action";
	}
	popupDialog({
		url : url,
		onClosed : function() {
			var retValue = this.returnValue;
			if (retValue) {
				$("#" + aka121).val(retValue.aka121);
				$("#" + aka120).val(retValue.aka120);
				return $("#" + next).focus();
			} else {
				$("#" + aka121).val("");
				$("#" + aka120).val("");
			}
			return $("#" + aka121).focus();
		}
	}, 500, 600);
}

/**
 * 规范页面信息提示框
 * 
 * @param json
 * @returns
 */
function checkJSONResultNew(json) {
	checkJSONResultNew(json, false);
}
/**
 * 检查JSON结果 返回值true为成功 false为失败 quietly为true不显示错误信息
 * 
 * @param json
 * @param quietly
 * @returns
 */
function checkJSONResultNew(json, quietly) {
	var errType = "9";
	var errMsg = "未确认的错误";
	if (typeof (json) !== 'undefined' && json !== null) {
		if (typeof (json.errortype) !== 'undefined' && json.errortype !== null) {
			errType = json.errortype;
			if (errType != "0") {
				errMsg = json.message;
			}
		}
	}

	if (errType != "0" && quietly !== false) {
		var beginStr = json.message.substring(0, 2);
		if ("-1" == beginStr || "-2" == beginStr) {
			popupAlert(json.message.substring(2), "错误", "error");
		} else {
			popupAlert(json.message, "错误", "error");
		}
	}

	return errType === "0" ? true : false;
}

var upFile;
var upPostParam;
var deviceList=[];//拍摄设备
var deviceList_t;
var imgUrl_t="http://127.0.0.1:8088";

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

var initPhoto=function(t){  //当前可用设备列表
	var req = {"func_id": "100000"};
	call_agent_service(req,function(obj){
		if (!obj.success_flag || obj.data == undefined)
		{
			alert(obj.error_info);
			return;
		}
		if (obj.data.result.length > 0)
		{
			deviceList_t = obj.data.result;
			for (var i=0;i<deviceList_t.length;i++)
			{
				deviceList_t[i].id = String(deviceList_t[i].index);
				deviceList_t[i].resolution = "1280,720";  //默认像素
				deviceList.push(deviceList_t[i]);
			}
		}
	});
};

function doPhoto(){ //拍摄
	var req = {};
	if(cameraInfo==null||cameraInfo=='null'){
		postJSON(rootPath + "/hosp/EquipmentAction!getConfig.action",
			function(json) {
				if (!checkJSONResult(json)) {
					return;
				}
				cameraInfo = json.data.camera_data;
				req = JSON.parse(cameraInfo);
				req.data.crop = cropList;
				req.data.rorate = rorateList;//图像旋转模板数组
				call_agent_service(req,doPhotoCallback);
			});
	}else{
		req = JSON.parse(cameraInfo);
		req.data.crop = cropList;
		req.data.rorate = rorateList;//图像旋转模板数组
		call_agent_service(req,doPhotoCallback);
	}
};

function doPhotoCallback(obj){
	if (!obj.success_flag || obj.data == undefined) {
		alert(obj.error_info);
		return;
	}
	var akb020 = $("#akb020").val();
	var aac002 = $("#aac002").val();
	if (obj.data.result.length > 0) {
		$.each(obj.data.result, function(i,item) {
			$("#dlrimg").attr("src", imgUrl_t+item.url);
			var req = {};
			req.func_id = "910000";
			req.data = {};
			req.data.url  = "http://"+window.location.host+rootPath+
				"/hosp/EquipmentAction!savePhoto.action?akb020="+akb020+"&aac002="+aac002;
			req.data.file = item.file_name;
			req.data.post_param = item.content_id;
			call_agent_service(req,function(t){
				var json = JSON.parse(t.data.server_response);
				$("#kc90id").val(json.data);
				if("13"==$("#aka130").val() && "1"==$("#bka975").val())
					fingerVeins();
			});
		});
	}
};


function fingerVeins(){
	var aac001 = $("#aac001").val();
	var aac002 = $("#aac002").val();
	if(powersi.isnull(aac001) || powersi.isnull(aac002)){
		alert("请先获取人员信息！")
		return;
	}
	if ($("#ic_i_psw").length == 0) {
		$(document.body).append("<div id='ic_i_psw' style='display:none;'></div>");
	}
	var _ic_i_psw_dialog = popupDiv("#ic_i_psw", "正在录入指静脉,请稍后。。。", 300, {
		showToggle : false,
		isDrag : false
	});
	$(".l-dialog #ic_i_psw").parents("tr").hide();
	setTimeout(function() {
		call_agent_service({
			"func_id" : "500000"
		}, function(obj) {
			if(!obj.success_flag){
				_ic_i_psw_dialog.hide();
				alert("请检查设备是否连接正确,错误信息："+obj.error_info);
				return;
			}
			if(powersi.isempty(obj.data)){
				_ic_i_psw_dialog.hide();
				alert("未采集到指纹信息，请重试。");
				return;
			}
			var bpd596 = obj.data.feature;
			var bpd597 = obj.data.image;
			postJSON(rootPath + "/hosp/EquipmentAction!fingerVeins.action",{
				"aac001" : aac001,
				"aac002" : aac002,
				"bpd596" : bpd596,
				"bpd597" : bpd597,
				},function(json) {
					_ic_i_psw_dialog.hide();
					if(json.message=="请先进行建模！"){
						var aac003= encodeURI($("#aac003").val());
						popupDialog({
			       			url: rootPath + "/pages/biz/medicare/hosp/CollectFingerVeins.jsp?aac001="+aac001+
			       			"&aac002="+aac002+"&aac003="+aac003,
			       			onClosed: function() {
			       				fingerVeins();
			       			}
			       		}, 300, 600);
					}else{
						var bpd591 = json.data;
						if(bpd591=="2"){
							popupConfirm("指静脉比对不通过，是否重新采集比对？", "提示", function(isOk) {
								if (isOk) {
									fingerVeins();
								}
							});
						}
					}
			});
		}); // 调用读取密码
	}, 100);
}


function takePhoto(){ //拍摄
	var req = {};
	if(cameraInfo==null||cameraInfo=='null'){
		postJSON(rootPath + "/hosp/EquipmentAction!getConfig.action",
			function(json) {
				if (!checkJSONResult(json)) {
					return;
				}
				cameraInfo = json.data.camera_data;
				req = JSON.parse(cameraInfo);
				req.data.crop = cropList;
				req.data.rorate = rorateList;//图像旋转模板数组
				call_agent_service(req,takePhotoCallback);
			});
	}
};

function takePhotoCallback(obj){
	if (!obj.success_flag || obj.data == undefined) {
		alert(obj.error_info);
		return;
	}
	var akb020 = $("#akb020").val();
	var aac002 = $("#querystring").val();
	if (obj.data.result.length > 0) {
		$.each(obj.data.result, function(i,item) {
			$("#imgPhone").val(imgUrl_t+item.url);
			var req = {};
			req.func_id = "910000";
			req.data = {};
			req.data.url  = "http://"+window.location.host+rootPath+
				"/hosp/EquipmentAction!savePhoto.action?akb020="+akb020+"&aac002="+aac002+"&type=1";
			req.data.file = item.file_name;
			req.data.post_param = item.content_id;
			call_agent_service(req,function(t){
				alert("重置成功");
			});
		});
	}
};
