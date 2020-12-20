function rest() {
	document.getElementById("queryString").value = '';
	document.getElementById("aac001").value = '';
	document.getElementById("aac002").value = '';
	if (document.getElementById("aac003")) {
		document.getElementById("aac003").value = '';
	}
}

function begingInput() {
	document.getElementById("queryString").value = '';
}

function loadPersonValue(returnData) {
	document.getElementById("queryString").value = returnData.aac003;
	document.getElementById("aac001").value = returnData.aac001;
	document.getElementById("aac002").value = returnData.aac002;
	if (document.getElementById("aac003")) {
		document.getElementById("aac003").value = returnData.aac003;
	}
	afterChoosePerson(returnData);
}

function getChoosePerson() {
	// 获取业务时间
	var p_aae031 = null;
	if (document.getElementById("p_aae031")) {
		p_aae031 = document.getElementById("p_aae031").value;
	}
	var queryString = document.getElementById("queryString").value.replace(/(^\s*)|(\s*$)/g, "");
	var queryUrl = rootPath + "/medicaretag/MedicareChoosePersonAction!getPersonInfo.action";
	var dto = {
		"queryString" : queryString,
		"choosePerson.localFileName" : localFileName,
		"empower" : p_empower,
		"aae140" : p_aae140,
		"aka130" : p_aka130,
		"aae031" : p_aae031
	};
	if (queryString != null && queryString != "") {
		postJSON(queryUrl, dto, function(result) {
			if (result.errortype == 0) {
				var returnData = result.data;
				var rowsize = returnData.rowsize;
				if (rowsize > 0) {
					if (rowsize == 1) {
						loadPersonValue(returnData);
					} else {
						// 弹出选人窗口b
						var urlParam = "?queryString=" + encodeURI(encodeURI(queryString)) + "&localFileName=" + localFileName + "&empower="
								+ p_empower + "&aae140=" + p_aae140 + "&aka130=" + p_aka130 + "&aae031=" + p_aae031;
						queryUrl = rootPath + "/medicaretag/MedicareChoosePersonAction!getPersonList.action" + urlParam;
						popupDialog(queryUrl, 600, 800, function(ret) {
							if (ret) {
								returnData = powersi.tojson(ret);
								loadPersonValue(returnData);
							}
						});
					}
				} else {
					afterNotChoosePerson();// alert("系统中没有找到符合该条件的人员，请设置正确的查询条件！");
				}
			} else {
				popupAlert(result.message);
			}
		});
	} else {
		alert("请录入正确的查询条件");
	}
}

/** 身份证号码正则 */
var idcardRegx = /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$|^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}[0-9Xx]$/;
$(document).ready(function() {
	//弹出正在输入密码框
	if ($("#ic_i_psw").length == 0){  //没有弹框div   则增加一个在页面上
		$(document.body).append("<div id='ic_i_psw' style='display:none;'></div>");
	}
	$(':text[name="queryString"]').each(function() {
		document.getElementById("aac001").value = "";
		document.getElementById("aac002").value = "";
		document.getElementById("aac003").value = "";
		var txt = $(this), checkPass = $('#checkPass').val() == 'true'/* 是否需要密码验证 */;
		if (txt && txt.prop("__init__") != true) {
			txt.prop("__init__", true);
			txt.keydown(function(event) {
				if (event.keyCode == '13') {
					try {
						var e = window.event || event;
						if (window.event) {
							e.cancelBubble = true;
						} else {
							e.stopPropagation();
						}
						event.returnValue = false;
					} catch (ex) {
					}
					if (checkPass) {
						if (!idcardRegx.test(txt.val())) {
							popupAlert("输入身份证号码错误。", "提示", "error");
							return;
						}
						var _ic_i_psw_password=popupDiv("#ic_i_psw", "正在输入密码，请稍后。。。", 300, {
							showToggle : false,
							isDrag : false
						});
						
						$(".l-dialog #ic_i_psw").parents("tr").hide();
						setTimeout(function(){
							try {
								call_agent_service({"func_id":"310000","data":{"timeout":30,"voice_type":1}},function(obj){ //获取密码键盘输入的密码
									if(null==obj || !obj.success_flag){ //失败
										_ic_i_psw_password.hide(); //隐藏输入密码弹出框
										popupAlert(obj.error_info + "", "提示", "error");
									}else{ //成功
										var psw = obj.data.out_info;
										postJSON(rootPath + __nativeAgentPath.readic_url, {
											sscard_no : txt.val(),
											password : psw
										},function(result) {
											if (result.errortype == 0) {
												var returnData = result.data;
												var rowsize = returnData.rowsize;
												if (rowsize > 0) {
													if (rowsize == 1) {
														loadPersonValue(returnData);
													} else {// 弹出选人窗口b
														var urlParam = "?queryString=" + encodeURI(encodeURI(queryString));
														urlParam += "&localFileName=" + localFileName + "&empower=" + p_empower;
														urlParam += "&aae140=" + p_aae140 + "&aka130=" + p_aka130 + "&aae031=" + p_aae031;
														queryUrl = rootPath + "/medicaretag/MedicareChoosePersonAction!getPersonList.action" + urlParam;
														popupDialog(queryUrl, 600, 800, function(ret) {
															if (ret) {
																returnData = powersi.tojson(ret);
																loadPersonValue(returnData);
															}
														});
													}
												} else {
													afterNotChoosePerson();// alert("系统中没有找到符合该条件的人员，请设置正确的查询条件！");
												}
											} else {
												popupAlert(result.message);
											}
											_ic_i_psw_password.hide();
										});
									}
								});
							} catch (e) {
								_ic_i_psw_password.hide();
								popupAlert(e, "提示", "error");
								return;
							}
						},100);
					}else {
						return getChoosePerson();
					}
				}
			});
		}
	});
});

function clearPersonValue() {
	document.getElementById("queryString").value = "";
	document.getElementById("aac001").value = "";
	document.getElementById("aac002").value = "";
	document.getElementById("aac003").value = "";
}

function _readIcCard(){
	document.getElementById("aac001").value = "";
	document.getElementById("aac002").value = "";
	document.getElementById("aac003").value = "";
	__readIcCardByClient();
}
