<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%
	request.setAttribute("path", request.getContextPath() + "/medicarespecial/specialManager");

%>
<link rel="stylesheet" href="${path}/resource/css/viewer.min.css">
<script type="text/javascript" src="${path}/resource/js/viewer.min.js"></script>
<powersi:html>
	<base target="_self">
	<powersi:head title="资料上传" />
<body>
	<div style="display: none">
	<powersi:form id="fileFrom" disabled="true" method="post"  enctype="multipart/form-data">
		<powersi:file id="imgFile" name="imgFile"/>
		<powersi:textfield id="kcc601" name="dto.kcc601" />
		<powersi:textfield id="aac001" name="dto.aac001" />
		<powersi:textfield id="kcc602" name="dto.kcc602" />
		<powersi:textfield id="kcc604" name="dto.kcc604" />
        <powersi:textfield id="comFlag" name="dto.comFlag"/>
	</powersi:form>
		<powersi:hidden id="imgMap" name="imgMap" />
	</div>
	<powersi:form id="mainForm" action="applyAction!modifySpeAppInf.action" namespace="/apply">
		<div style="display: none">
		<powersi:panelbox title="当前可用设备列表(可点击展开查看)" isCollapse="true">
			<powersi:datagrid id="grid1" name="grid1" enabledExportExcel="true"
				height="100%" width="100%" delayLoad="true"
				exportFileName="'当前可用设备列表'" isMultiSelect="false" enabledEdit="true">
				<powersi:datagrid-column name="id" display="编码" width="20%"/>
				<powersi:datagrid-column name="name" display="名称" width="40%"/>
				<powersi:datagrid-column name="model" display="设备型号" width="20%"/>
				<powersi:datagrid-column name="resolution" display="像素" width="20%"
					editor="{type:'select',data:resolutionList}"/>
			</powersi:datagrid>
			</powersi:panelbox>
		<powersi:panelbox title="拍摄内容列表">
		<powersi:datagrid id="grid2" name="grid2" enabledExportExcel="true"
			height="100%" width="100%" delayLoad="true" exportFileName="'拍摄内容列表'" enabledEdit="true">
			<powersi:datagrid-column name="status" display="状态" width="10%"/>
			<powersi:datagrid-column name="id" display="编号" width="10%"/>
			<powersi:datagrid-column name="name" display="名称" width="30%"/>
			<powersi:datagrid-column name="device"   display="拍摄设备" width="20%" />
			<powersi:datagrid-column name="crop"  display="裁剪模板" width="20%" />
			<powersi:datagrid-column name="rorate"  display="旋转模板" width="20%" />
		</powersi:datagrid>
		</powersi:panelbox>
		</div>
		
		<powersi:panelbox title="上传列表" allowCollapse="false">
			<powersi:panelbox-toolbar>
				<powersi:button key="button_close" value="关 闭" data-hotkey='alt+B' onclick="closeDialog();" />		
			</powersi:panelbox-toolbar>
			
			<powersi:editorlayout cols="8">
				<powersi:hidden id="kc41id" name="dto.kc41id"/>
				<powersi:hidden id="aze007" name="dto.aze007"/>
				<powersi:hidden id="aze017" name="dto.aze017"/>
				<powersi:hidden id="aae100" name="dto.aae100" value="1"/>
				<powersi:hidden id="index" name="index"/><!-- 用于浏览得到index -->
			</powersi:editorlayout>
		</powersi:panelbox>

	<div id ="zlzs">
	<!-- 资料上传 -->	
	<powersi:panelbox title="出院记录">
			<powersi:panelbox-toolbar>
				<powersi:button key="button_close" value="浏 览"  onclick="browseImg('dischargeRecords')" />
				<powersi:button key="button_close" value="拍 摄"  onclick="takePhoto('dischargeRecords')" />
			</powersi:panelbox-toolbar>
		<div id="dischargeRecordsDiv">
			<table id="dischargeRecordsTable"><tr id="dischargeRecordsShow"></tr></table>
		</div>
	</powersi:panelbox>
	
	<powersi:panelbox title="检查单">
			<powersi:panelbox-toolbar>
				<powersi:button key="button_close" value="浏 览"  onclick="browseImg('checkList')" />
				<powersi:button key="button_close" value="拍 摄"  onclick="takePhoto('checkList')" />
			</powersi:panelbox-toolbar>
		<div id="checkListDiv" >
			<table id="checkListTable"><tr id="checkListShow"></tr></table>
		</div>
	</powersi:panelbox>

	<powersi:panelbox title="化验单">
			<powersi:panelbox-toolbar>
				<powersi:button key="button_close" value="浏 览"  onclick="browseImg('testSheet')" />
				<powersi:button key="button_close" value="拍 摄"  onclick="takePhoto('testSheet')" />
			</powersi:panelbox-toolbar>
		<div id="testSheetDiv" >
			<table id="testSheetTable"><tr id="testSheetShow"></tr></table>
		</div>
	</powersi:panelbox>

	</div>
	<powersi:errors />
	</powersi:form>
</body>
<script type="text/javascript">
	var pwidth = 0.1 * $(window).width();
	var pheight = 0.1 * $(window).height();
	var length = 1;
	$(document).ready(function() {
		queryAll();
	});
    
	function queryAll(){
		var kcc601 = $("#kcc601").val();
		postJSON("${path}!uploadFile.action?dto.kcc601="+kcc601, function(json){
			if (!checkJSONResult(json)) {
				popupAlert("获取资料失败！", "提示", "warn");
				return;
			}
			$.each(json.data, function(key, value){
				var showDiv;
				if(key === "imgList_0"){
					showDiv = $("#dischargeRecordsShow");
				}else if(key === "imgList_1"){
					showDiv = $("#checkListShow");
				}else if(key === "imgList_2"){
					showDiv = $("#testSheetShow");
				}
				var imgList = value;
				for(var i = 0; i< imgList.length; i++){
					var imgOriginal = imgList[i];
					var flagIndex = imgOriginal.indexOf("|");
					var imgSrc = imgOriginal.substring(flagIndex + 1);
					var imgIndex = imgOriginal.substring(0, flagIndex);
					var imgType = key.substring(key.length-1);
					showDiv.append("<td align='right' ")
					showDiv.append("<img src='" + imgSrc + "' class='photo' style='height: " + pheight + "px;width: " + pwidth + "px'/>");
					if(imgIndex.startsWith("/uploadFile")){
					    var index = imgIndex.indexOf("+");
					    var kcc605 = imgIndex.substring(0, index);
					    var kcc6id = imgIndex.substring(index + 1);
						showDiv.append("<i onclick=deleteImg('"+kcc605+"','"+kcc6id+"','','') class='icon-remove-sign' style='vertical-align: bottom'></i>");
					}else{
						showDiv.append("<i onclick=deleteImg('','"+kcc601+"','" + imgType+"','"+imgIndex+"') class='icon-remove-sign' style='vertical-align: bottom'></i>");
					}
					showDiv.append("&nbsp;&nbsp;</td>");
				}
			});
		});
	}

	function deleteImg(kcc605, kcc601, imgType, imgIndex) {
		popupConfirm('确认要删除资料吗？', '提示', function (yes) {
			if (yes) {
				var param;
				if(kcc605 === ""){
					param =  "?dto.kcc601=" + kcc601 + "&dto.kcc602=" + imgType + "&dto.kcc603=" + imgIndex;
				}else{
					param =  "?dto.kcc605=" + kcc605 + "&dto.kcc6id=" + kcc601;
				}
				postJSON("${path}!deleteFile.action" + param,
						function (json) {
							if (!checkJSONResult(json)) {
								return;
							} else {
								popupAlert("删除成功", "提示", "success", function () {
									location.reload();
								});
							}
						});
			}
		});

	}
	
	
	function browseImg(imgType){

		if(checkFileLimited(imgType)){
			return;
		}
	    var kcc602 = "";
		if("dischargeRecords" === imgType){
			kcc602 = "0";
		}else if("checkList" === imgType){
			kcc602 = "1";
		}else if("testSheet" === imgType){
			kcc602 = "2";
		}
		$("#kcc602").val(kcc602);
		$("#comFlag").val("0");
		$("#imgFile").val("");
		$("#imgFile").click();
	}

	document.getElementById("imgFile").addEventListener("change", function () {
		if ($("#imgFile").val() == "") {
			return;
		}

		var filePath = $("#imgFile").val();

		var fileName = "";
		var i = filePath.lastIndexOf('\\');
		if (i > 0) {
			fileName = filePath.substring(i + 1);
		}

		popupConfirm('确认要上传选中文件吗？', '提示', function (yes) {
			if (yes) {
				$("#kcc604").val(fileName);
				$("#fileFrom").ajaxSubmit({
					url: "${path}!saveFile.action",
					datatype: "json",
					success: function (json) {
						if (!checkJSONResult(json)) {
							return;
						}

						var imgFile = json.data.imgFile;
						var flagIndex = imgFile.indexOf("|");
						var imgSrc = imgFile.substring(flagIndex + 1);
						var imgIndex = imgFile.substring(0, flagIndex);

						var imgType = json.data.imgType;
						var kcc601 = $("#kcc601").val();
						var showDiv;
						if(imgType === "0"){
							showDiv = $("#dischargeRecordsShow");
						}else if(imgType === "1"){
							showDiv = $("#checkListShow");
						}else if(imgType === "2"){
							showDiv = $("#testSheetShow");
						}
						showDiv.append("<td align='right' ")
						showDiv.append("<img src='" + imgSrc + "' class='photo' style='height: " + pheight + "px;width: " + pwidth + "px'/>");
						showDiv.append("<i onclick=deleteImg('','"+kcc601+"','" + imgType+"','"+imgIndex+"') class='icon-remove-sign' style='vertical-align: bottom'></i>");
						showDiv.append("&nbsp;&nbsp;</td>");
					}
				});
			}
		});
	});
	/**高拍仪*/
	var deviceList=[];//拍摄设备
	var imgUrl;
	var imgUrl_t="http://127.0.0.1:8088";
	var resolutionList =[{"id":"1280,720","text":"1280,720"},
	                     {"id":"1600,1200","text":"1600,1200"},
	                     {"id":"2048,1536","text":"12048,1536"}]; //分辨率   让用户选择具体像素
	
	 var  cropList = [{
			"id": "A4",
				"name": "A4纸",
				"width": 297,
				"height": 210,
				"standard": true
			},
			{
				"id": "IDCARD2",
				"name": "二代身份证",
				"width": 85.6,
				"height": 54,
				"standard": true
			},
			{
				"id": "SICARD",
				"name": "社会保障卡",
				"width": 85.6,
				"height": 54,
				"standard": true
			},
			{
				"id": "CUSTOM",
				"name": "自定义",
				"standard": false
			}
			];//裁剪模板
			
	var rorateList = [
		{
			"id": "0",
			"name": "无",
			"rorate": true,
			"angle": 0,
			"clockwise": false
		},
		{
			"id": "-90",
			"name": "逆时钟转90度",
			"rorate": true,
			"angle": 90,
			"clockwise": false
		},
		{
			"id": "-180",
			"name": "逆时钟转180度",
			"rorate": true,
			"angle": 180,
			"clockwise": false
		},
		{
			"id": "-270",
			"name": "逆时钟转270度",
			"rorate": true,
			"angle": 270,
			"clockwise": false
		},
		{
			"id": "90",
			"name": "顺时钟转90度",
			"rorate": true,
			"angle": 90,
			"clockwise": true
		},
		{
			"id": "180",
			"name": "顺时钟转180度",
			"rorate": true,
			"angle": 180,
			"clockwise": true
		},
		{
			"id": "270",
			"name": "顺时钟转270度",
			"rorate": true,
			"angle": 270,
			"clockwise": true
		}
	]; //旋转角度模板
		
	                     
                     
	
	$(function(){
		__isPing(__device_info);
	});
	
	var __device_info=function(t){  //当前可用设备列表
		var req = {"func_id": "100000"};
		call_agent_service(req,function(obj){
			if (!obj.success_flag || obj.data == undefined)
			{
				alert(obj.error_info);
				return;
			}
			if (obj.data.result.length > 0)
			{
				
				var deviceList_t = obj.data.result;
				for (var i=0;i<deviceList_t.length;i++)
				{
					deviceList_t[i].id = String(deviceList_t[i].index);
					deviceList_t[i].resolution = "1280,720";  //默认像素
					
					deviceList.push(deviceList_t[i]);
				}

				grid1.loadData(deviceList_t);
				grid2.loadData(contentList);
			}
		});

	};
	
	var contentList = [  //默认拍摄内容
	                     {
	 	       				"id": "1",
	 	       				"name": "0度",
	 	       				"device":"0",
	 	       				"crop": "A4",
	 	       				"rorate": "0",
	 	       				"scan_barcode": false,
	 	       				"status":"未拍摄"
		 	       		},{
	 	       				"id": "2",
	 	       				"name": "逆时钟转90度",
	 	       				"crop": "A4",
	 	       				"device":"0",
	 	       				"rorate": "-90",
	 	       				"scan_barcode": false,
	 	       				"status":"未拍摄"
	 	       			},{
	 	       				"id": "3",
	 	       				"name": "顺时钟转90度",
	 	       				"crop": "A4",
	 	       				"device":"0",
	 	       				"rorate": "90",
	 	       				"scan_barcode": false,
	 	       				"status":"未拍摄"
	 	       			}
	 	       			];
	
	//因为该功能暂时没和数据库进行交互，定义内容存放url数据
	var imgUrlArr1=[];
	var imgUrlArr2=[];
	var imgUrlArr3=[];
	var imgUrlArr4=[];

	function takePhoto(imgType){
		
		if(checkFileLimited(imgType)){
			return;
		}
		var kcc602 = "";
		if("dischargeRecords" === imgType){
			kcc602 = "0";
		}else if("checkList" === imgType){
			kcc602 = "1";
		}else if("testSheet" === imgType){
			kcc602 = "2";
		}

		var req = {};
		var id = "1";
		req.func_id = "100001";
		req.data = {};
		req.data.title = "高拍仪拍摄";
		var dataDevice = grid1.getData();
		req.data.device = []; //设备信息数组
		for (var i=0;i<dataDevice.length;i++){
			req.data.device[i] = dataDevice[i];
		}
		req.data.crop = cropList;//裁剪模板数组
		req.data.rorate = rorateList;//图像旋转模板数组
		var dataContent = grid2.getData();//内容模板数组
		req.data.content = [];
		for (var i=0;i<dataContent.length;i++){
			req.data.content[i] = dataContent[i];
		}

		$.ajax({
			type : 'POST',
			url : __nativeAgent.agent_service_url,
			data :JSON.stringify(req),
			dataType : 'json',
			success : function(res) {// 回调函数
				service_callback(res, kcc602);
			},
			error : function() {// 请求出错处理操作
				if (xhr.status != "200") {
					err = "发送请求错误:" + xhr.status + " 错误文本:" + xhr.statusText || "";
				} else {
					err = xhr.responseText;
				}
				alert("发送请求错误" + err);
			}
		});
	}
	
	var service_callback=function(obj, kcc602) {
		if (!obj.success_flag || obj.data == undefined) {
			popupAlert(obj.error_info);
			return;
		}

		if (obj.data.result.length > 0) {
			$.each(obj.data.result, function (i, item) {
				var fileName = "";
				var i = item.file_name.lastIndexOf('\\');
				if (i > 0) {
					fileName = item.file_name.substring(i + 1);
				}
				var req = {};
				req.func_id = "910000";
				req.data = {};
				req.data.url = "http://" + window.location.host + rootPath + "/medicarespecial/specialManager!saveFile.action"
						+ "?dto.comFlag=1"
						+ "&dto.kcc601=" + $("#kcc601").val()
						+ "&dto.kcc602=" + kcc602
				 		+ "&dto.kcc604=" + fileName;
				req.data.file = item.file_name;
				req.data.post_param = item.content_id;
				call_agent_service(req, function (json) {
						if(!checkJSONResult(json,"popup")){
							return;
						} else {
							var imgFile = json.data.imgFile;
							var flagIndex = imgFile.indexOf("|");
							var imgSrc = imgFile.substring(flagIndex + 1);
							var imgIndex = imgFile.substring(0, flagIndex);

							var imgType = json.data.imgType;
							var kcc601 = $("#kcc601").val();
							var showDiv;
							if(imgType === "0"){
								showDiv = $("#dischargeRecordsShow");
							}else if(imgType === "1"){
								showDiv = $("#checkListShow");
							}else if(imgType === "2"){
								showDiv = $("#testSheetShow");
							}
							showDiv.append("<td align='right' ")
							showDiv.append("<img src='" + imgSrc + "' class='photo' style='height: " + pheight + "px;width: " + pwidth + "px'/>");
							showDiv.append("<i onclick=deleteImg('','"+kcc601+"','" + imgType+"','"+imgIndex+"') class='icon-remove-sign' style='vertical-align: bottom'></i>");
							showDiv.append("&nbsp;&nbsp;</td>");
						}
					});
						}
				);
				<%--postJSON("${path}!saveFile.action", param, function(json){--%>
				<%--	if(!checkJSONResult(json,"popup")){--%>
				<%--		return;--%>
				<%--	} else {--%>
				<%--		var imgFile = json.data.imgFile;--%>
				<%--		var flagIndex = imgFile.indexOf("|");--%>
				<%--		var imgSrc = imgFile.substring(flagIndex + 1);--%>
				<%--		var imgIndex = imgFile.substring(0, flagIndex);--%>

				<%--		var imgType = json.data.imgType;--%>
				<%--		var kcc601 = $("#kcc601").val();--%>
				<%--		var showDiv;--%>
				<%--		if(imgType === "0"){--%>
				<%--			showDiv = $("#dischargeRecordsShow");--%>
				<%--		}else if(imgType === "1"){--%>
				<%--			showDiv = $("#checkListShow");--%>
				<%--		}else if(imgType === "2"){--%>
				<%--			showDiv = $("#testSheetShow");--%>
				<%--		}--%>
				<%--		showDiv.append("<td align='right' ")--%>
				<%--		showDiv.append("<img src='" + imgSrc + "' class='photo' style='height: " + pheight + "px;width: " + pwidth + "px'/>");--%>
				<%--		showDiv.append("<i onclick=deleteImg('"+kcc601+"','" + imgType+"','"+imgIndex+"') class='icon-remove-sign' style='vertical-align: bottom'></i>");--%>
				<%--		showDiv.append("&nbsp;&nbsp;</td>");--%>
				<%--	}--%>
				<%--});--%>
			}
	}

	//渲染码表
	function renderCode(rowdata, index, value, column) {
		var str='';
		var list = window[column.columnname + "List"];
		if(column.columnname == "baa027" || column.columnname == "daa027"){
			list = window["aaa027List"];
		}
		if(!list){
			list = $("#" + column.columnname + " option");
		}
		$.each(list, function(i, n){
			if(value==list[i].id){
				str= list[i].text;
			}
		});
		return str;
	}

	/**
	 * 判断上传资料文件数量
	 * @param imgType
	 * @returns {boolean}
	 */
	function checkFileLimited(imgType) {
		var imgTypeTable = document.getElementById(imgType + "Table");
		var cells = imgTypeTable.rows.item(0).cells.length;
		if (cells >= 4) {
			popupAlert("上传资料不能超过十份！", "提示", "warn");
			return true;
		}
		return false;
	}

</script>
</powersi:html>