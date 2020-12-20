function checkJSONResultNew(json) {
	checkJSONResultNew(json, false);
}
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
			popupAlert(json.message.substring(2), "错误", errType == 1 ? "warn" : "error");
		} else {
			popupAlert(json.message, "错误", errType == 1 ? "warn" : "error");
		}
	}

	return errType === "0" ? true : false;
}

$(function() {
	$.fn.extend({
		getTab : function(idx) {
			var tabNav, navid, tarid, dgrid;
			if (idx)
				tabNav = $(this).find(".ui-tabs-nav li[aria-labelledby=ui-id-" + idx + "]");
			else
				tabNav = $(this).find(".ui-tabs-nav li[aria-selected=true]");
			navid = tabNav.attr("id");
			tarid = tabNav.attr("aria-controls");
			dgrid = $("#" + tarid).find("div").attr("id");
			return {
				navid : navid,
				tarid : tarid,
				dgrid : dgrid,
				tabidx : tabNav.attr('aria-labelledby').replace(/^ui-id-/g, '')
			};
		},
		selTab : function(idx) {
			$(this).tabs("select", idx);
			var onselect = window[$(this).attr("onselect")];
			if ($.isFunction(onselect))
				onselect.call(onselect, idx);
		}
	});

	$("div.frame_div iframe").each(function(i, iframe) { // 报表在iframe中显示优化辅助代码lwyao
		iframe = $(iframe);
		if (!iframe.attr("src"))
			iframe.attr("src", rootPath + "/pages/base.jsp");
		iframe.load(function() {
			var compatible = screen.height >= 1080 ? 345 : 345;
			compatible = $.browser.version == '8.0' ? compatible + 30 : compatible;
			var height = screen.height - ($('div.panelBox').height() + compatible);
			$("iframe#" + iframe.attr("id")).height(height);
		});
	});

	Date.prototype.format = function(fmt) { // 日期格式化扩展lwyao
		var o = {
			"M+" : this.getMonth() + 1, // 月份
			"d+" : this.getDate(), // 日
			"h+" : this.getHours(), // 小时
			"m+" : this.getMinutes(), // 分
			"s+" : this.getSeconds(), // 秒 // 毫秒
			"S" : this.getMilliseconds()
		};
		if (/(y+)/.test(fmt))
			fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		for ( var k in o)
			if (new RegExp("(" + k + ")").test(fmt))
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		return fmt;
	}
	Date.prototype.setMsec = function(ms) { // 设置日期毫秒时间
		this.setTime(ms);
		return this;
	}
	Date.prototype.getPreMonth = function(mun) { // 获取当前日期的前几(mun)个月
		var dayNum = 0, month = this.format('M');
		for (var i = 1; i <= mun; i++) {
			switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				dayNum += 31;
				break;
			case 2:
				dayNum += (this.format('yyyy') % 4 == 0 ? 29 : 28);
				break;
			default:
				dayNum += 30;
				break;
			}
			month -= 1;
		}
		return new Date().setMsec(this - (60 * 60 * 24 * 1000 * dayNum));
	}

	$("._hide").parents("td.tdInput").prev("td.tdLabel").hide(); // lwyao不显示字段标签

	$(document).bind('keydown', function(event) { // lwyao回车关闭提示弹出框
		if (event.keyCode == "13" && $(".l-dialog-alert").length > 0) {
			var focus = $(':focus');
			$("button,input").blur();
			setTimeout(function() {
				$(".l-dialog-alert .l-dialog-btn-inner:contains(确定)").click();
				focus.focus();
			}, 500);
		}
	});

	//if ($.browser.msie) {// 读卡控件 lwyao=================================================================================================
		var readic = {
			_objCard : document.getElementById("cardControl"),
			cardclientversion_url : "http://10.138.0.204:20001/hygeia_web/getcardclientversion.shtml",/* 读卡版本验证地址 */
			readic_url : rootPath + "/medicarecommon/iccard!checkCard.action"/* 读卡后台验证地址 */
		};
		_getReadicObj = function() { // 获取读卡控件OCX-2018.10.28-lwyao
			if ($("#ic_i_psw").length == 0)
				$(document.body).append("<div id='ic_i_psw' style='display:none;'></div>");
			readic._objCard = readic._objCard ? readic._objCard : document.getElementById("cardControl");
			if (!readic._objCard) {
				readic._objCard = document.createElement("OBJECT");
				readic._objCard.setAttribute("id", "cardControl");
				readic._objCard.setAttribute("classid", "clsid:962275D3-CB9F-4CF2-AC8A-33D2D8EFC5C5");
				readic._objCard.setAttribute("onerror", "setupSICardExe();");
				document.body.appendChild(readic._objCard);
			}
			try {
				readic._objCard.setNeedPin($('#isInpPsw').val() == 'false' ? 'noneed' : '');
			} catch (e) {
				var err = '读卡控件初始化失败，请确认读卡控件是否已安装、卡机是否已连接。';
				setTimeout(function() {
					if ($(".l-dialog:visible").length == 0)
						popupAlert(err, '提示', 'error');
				}, 100);
				throw err;
			}
			return readic._objCard;
		}
		_checkCardClientVersion = function() { // 检查读卡控件版本lwyao
			var objCard = _getReadicObj();
			if (objCard && objCard.Version()) {
				postJSON(readic.cardclientversion_url, null, function(json) {
					if (!json)
						return;
					var ver = objCard.Version();
					if (ver < json.version) {
						popupConfirm("是否下载最新读卡控件？（服务器版本[" + json.version + "]，本地版本[" + ver + "]）", "提示", function(yes) {
							if (yes) {
								if (json.file_url) {
									window.open(json.file_url);
								} else {
									popupError("无效的控件下载地址", "提示");
								}
							}
						});
					}
				});
			}
		}
		_readic = function(suc_func, mode) { // mode:1-省内卡,2-省外卡;读卡方法2018.10.28-lwyao
			mode = mode ? mode : 1;
			var _objCard = _getReadicObj(), _bke548, _icinfo = {};
			var _ic_i_psw_dialog = popupDiv("#ic_i_psw", "正在输入密码，请稍候。。。", 300, {
				showToggle : false,
				isDrag : false
			});
			$(".l-dialog #ic_i_psw").parents("tr").hide();
			setTimeout(function() {
				if (mode == 1)
					setTimeout(function() {
						if (!_bke548) {
							_ic_i_psw_dialog.hide();
						}
					}, 100);
				try {
					var icFieldStr = "issuer_code|sscard_no|card_no|identifier_code|name|card_reset_info|"
							+ "spec_version|issue_date|card_validity|terminal_no|equipment_no|password";
					_objCard.setMode(mode);
					_bke548 = _objCard.ReadCardBase().split('|');
					if (_bke548.length < 10)
						throw _bke548[1] ? _bke548[1] : '读卡失败，请检查卡机是否正常连接或卡是否插好';
					if (_bke548[0] == 0)
						throw _bke548[1] ? _bke548[1] : '读卡失败，请检查卡机是否正常连接或卡是否插好';
					$.each(icFieldStr.split("|"), function(i, col) {
						_icinfo[col] = _bke548[i + 1];
					});
				} catch (e) {
					_ic_i_psw_dialog.hide();
					if ($(".l-dialog-alert").length == 0)
						popupAlert(e + "", "提示", "error");
					return;
				}
				_ic_i_psw_dialog.hide();

				$("#argName").val('aac002');
				$("#argName").change();
				$("#querystring").val(_bke548[2]);
				readIcCard();
			}, 50);
		}

		_changeSicardPwd = function() { // 修改社保卡密码2018.10.28-lwyao
			var _objCard = _getReadicObj(), _ic_i_psw_dialog = popupDiv("#ic_i_psw", "正在输入密码，请稍候。。。", 300, {
				showToggle : false,
				isDrag : false
			});
			$(".l-dialog #ic_i_psw").parents("tr").hide();
			setTimeout(function() {
				var retMsg = _objCard.ChangePIN().split("\|");
				if (retMsg[0] == '0') {
					popupAlert("修改密码成功。", "提示", "info");
				} else {
					popupAlert("修改卡密码失败：" + retMsg[1], "提示", "error");
				}
				_ic_i_psw_dialog.hide();
			}, 100);
		}
		_inputPsw = function(suc_func) { // 调用卡机密码键盘2018.10.28-lwyao
			var _objCard = _getReadicObj(), _ic_i_psw_dialog = popupDiv("#ic_i_psw", "正在输入密码，请稍候。。。", 300, {
				showToggle : false,
				isDrag : false
			});
			$(".l-dialog #ic_i_psw").parents("tr").hide();
			setTimeout(function() {
				var _bke548;
				try {
					_bke548 = _objCard.GetPassword(30, 1).split("|");
					if (_bke548[0] != 0)
						throw _bke548[1] ? _bke548[1] : '读卡失败，请检查卡机是否正常连接或卡是否插好';
				} catch (e) {
					_ic_i_psw_dialog.hide();
					popupAlert(e, "提示", "error");
					return;
				}
				_ic_i_psw_dialog.hide();
				suc_func.call(readic, _bke548[2]);
			}, 50);
		}
	//}

	/**
	 * 读卡控件
	 * 
	 * @author lwyao
	 */
	var readicButtonSelector = "button:not([onclick])[id^=readic],input:not([onclick])[id^=readic]";
	var readicButtons = $(readicButtonSelector);
	$(document).on("click", readicButtonSelector, function() { // 读卡事件监听lwyao
			//读卡前先刷新所有界面
			if(typeof resetpage === "function")resetpage();
			__readIcCardByClient(window[$(this).attr("id") + "Click"]); // 改用王总新的客户端读卡程序
	});

	// 身份证鉴权
	_VerifyIDCardWithDB = function(caa027, callback) {
		_objCard = document.getElementById("cardControl"); // 获取社保卡控件对象
		var idcard = _objCard.ReadIDCardBase();
		postJSON(rootPath + "/common/CommonManagerAction!VerifyIDCard.action", {
			caa027 : caa027,
			idcard : idcard
		}, function(json) {
			if (callback) {
				callback.call(callback, json);
			}
		});
	}
	
	$(document).on("click", "button:not([onclick])[id^=readele],input:not([onclick])[id^=readele]", function() { // 拍摄二维码事件监听 xiexiao
		__readEleCard(window[$(this).attr("id") + "Click"]);
	});

});

// 身份证效验18岁以下80岁以上可输入身份证
_VerifyIDCardWithDBCheckPerson = function(caa027, aac002, callback) {
	var idcard = aac002;
	postJSON(rootPath + "/common/CommonManagerAction!VerifyIDCardCheckPerson.action", {
		caa027 : caa027,
		idcard : idcard
	}, function(json) {
		if (callback) {
			callback.call(callback, json);
		}
	});
}

/**
 * 获取页面查询参数
 * 
 * @author lwyao
 * @returns
 */
function _getParams() {
	var params = {};
	var sel = $(".tableEditor input[type=text],.tableEditor input[type=hidden],select[id]");
	$.each(sel, function(i, n) {
		var id = $(n).attr('id');
		var v = $(n).attr('ligeruiid') ? liger.get(id).getValue() : $(n).val();
		v = v == '' || v == '请选择...' ? null : v;
		params[id] = v;
	});
	return params;
}

/**
 * 滚动到元素位置(datagrid中的元素)
 * 
 * @param element
 * @author lubin
 * @returns
 */
function _datagridScrollTo(element) {
	if (!element)
		return;
	var parentDiv = $(element).parents("div.l-scroll");
	if (parentDiv && parentDiv.length == 1) {
		var width = parentDiv.width(), height = parentDiv.height(), cuTop = $(element).position().top, cuLeft = $(element).position().left;
		cuLeft = cuLeft > 0 ? cuLeft + $(element).width() : cuLeft;
		cuTop = cuTop > 0 ? cuTop + $(element).height() : cuTop;
		if (cuLeft > width) {
			parentDiv.scrollLeft(parentDiv.scrollLeft() + cuLeft - width + 30);
		} else if (cuLeft < 0) {
			parentDiv.scrollLeft(parentDiv.scrollLeft() + cuLeft);
		}
		if (cuTop > height) {
			parentDiv.scrollTop(parentDiv.scrollTop() + cuTop - height + 30);
		} else if (cuTop < 0) {
			parentDiv.scrollTop(parentDiv.scrollTop() + cuTop);
		}
	}
}

/**
 * 数据表格编辑字段验证
 * 
 * @param datagrid
 *            ligerui数据表格对象
 * @param validators
 *            格式(回调函数必须有return,返回true或false或者错误提示信息)：{columnname:function(row,col){...}}
 * @param sucFunc
 *            函数格式：function(changeDatas){..}
 * @author lwyao
 * @returns
 */
function _datagridEditValidation(datagrid, validators, sucFunc) {
	try {
		$("#" + datagrid.id).click();
		var focusErr = null, cols = datagrid.getColumns(), changes = [];
		$.each(datagrid.getChangeRows(true), function(i, d) { // 忽略删除的数据
			if ("delete" != d.__status)
				changes.push(d);
		});
		$.each(changes, function(i, d) {
			$.each(cols, function(idx, col) {
				if ((!col.editor) || "delete" == d.__status)
					return true;
				var validator = validators ? validators[col.columnname] : null, msg = '';
				if (validator && $.isFunction(validator)) {
					if ((msg = validator.call(datagrid, d, col)) == false) {
						msg = '[' + col.display + '] 输入有误，请重新输入。';
					} else if (msg == true) {
						msg = null;
					}
				} else if (validator == true) {
					return true;
				} else if (col.columnname && !d[col.columnname]) {
					msg = '请输入 [' + col.display + "]。";
				} else {
					return true;
				}
				if (msg) {
					focusErr = $("#" + datagrid.id + " td[id$=" + d.__id + "\\|" + col.__id + "]");
					throw msg;
				}
			});
		});
		if ($.isFunction(sucFunc))
			sucFunc.call(datagrid, changes);
		return true;
	} catch (e) {
		_datagridScrollTo(focusErr);
		popupAlert(e.toString(), "提示", "error", function(x) {
			x = focusErr ? focusErr.click() : '';
		});
		return false;
	}
}

/**
 * 日期范围选择控件
 * 
 * @param ele
 *            日期范围选择控件元素
 * @param opts
 *            控件参数
 * 
 * @author lwyao
 * @returns
 */
function _daterange(ele, opts, date) {
	date = date ? date : new Date();
	opts = opts ? opts : {};
	opts.name = opts.name ? opts.name : '';
	opts.type = /^date$|^month$/.test(opts.type) ? opts.type : 'date';
	opts.format = opts.format ? opts.format : 'date' == opts.type ? null : 'YYYY-MM';
	opts.ranges = opts.ranges ? opts.ranges : 'date' == opts.type ? {
		'今天' : [ date, date ],
		'昨天' : [ new Date().setMsec(date - 86400000), new Date().setMsec(date - 86400000) ],
		'最近三天' : [ new Date().setMsec(date - (86400000 * 2)), date ],
		'最近一周' : [ new Date().setMsec(date - (86400000 * 6)), date ],
		'最近一月' : [ date.getPreMonth(1), date ],
		'本月' : [ new Date().setMsec(date - (86400000 * (date.getUTCDate() - 1))), date ],
		'上个月' : [ new Date().setMsec(date - (86400000 * (date.getUTCDate() - 1))).getPreMonth(1),
				new Date().setMsec(date - (86400000 * date.getUTCDate() + 1)) ]
	} : {
		'本月' : [ date, date ],
		'最近两月' : [ date.getPreMonth(1), date ],
		'最近三月' : [ date.getPreMonth(2), date ],
		'最近半年' : [ date.getPreMonth(5), date ],
		'最近一年' : [ date.getPreMonth(11), date ]
	};
	opts.locale = opts.locale ? opts.locale : {
		format : opts.format,
		separator : '~'
	};
	if ($('._input-value').length == 0)
		$(ele).parent().append('<div style="display:none;" class="_input-value"></div>');
	$('._input-value').empty();
	$.each(opts.name.split(","), function(i, name) {
		$('._input-value').append('<input type="hidden" name="' + name + '" value="' + (i == 0 ? opts.startDate : opts.endDate) + '"/>');
	});
	$(ele).daterangepicker(opts, function(start, end, label) {
		$('._input-value input:hidden').each(function(i, e) {
			$(e).val(i == 0 ? start.format(opts.format) : end.format(opts.format));
		});
	});
	$(ele).on('hide.daterangepicker', function(ev, picker) {
		var sDate = picker.startDate.format(opts.format), eDate = picker.endDate.format(opts.format), oldVal = $(this).val(), newVal = null;
		setTimeout(function() {
			$(ele).parents('.input-icon').next('._input-value').find('input:hidden').each(function(i, e) {
				$(e).val(i == 0 ? sDate : eDate);
			});
			_x = (newVal = sDate + opts.locale.separator + eDate) != oldVal ? $(ele).val(newVal) : null;
		}, 50);
	});
}

/**
 * code中文显示渲染
 * 
 * @author lwyao
 * @param rowdata
 * @param index
 * @param value
 * @param column
 * @returns
 */
function _render(rowdata, index, value, column, display) {
	if (!value)
		return;
	if (/^\d{4}[-/\\]?\d{2}[-/\\]?\d{2}[\s]?00[:]?00[:]?00.*$/.test(value))
		return value.split(/\s/)[0];
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
						value = n.text;
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
	return value;
}

/**
 * 统计函数
 * 
 * @author lwyao
 * @param suminf
 * @param column
 * @param data
 * @returns
 */
function _totalSummary(suminf, column, data) {
	var colName = column.columnname;
	if (colName == "opt" || colName == "opt") {
		return "合计";
	}
	var sum = 0, regex = /^.*\.\d{2}$/, isToFixed2 = false;
	for ( var key in data) {
		var v = data[key][colName] ? data[key][colName] : '0';
		sum += parseFloat(v.toString().replace(/,/g, ''));
		isToFixed2 = isToFixed2 ? true : regex.test(v);
	}
	if (isToFixed2) {
		sum = _toDecimal(sum).toFixed(2).split('.');
		return sum[0].replace(new RegExp('(\\d)(?=(\\d{3})+$)', 'ig'), "$1,") + "." + sum[1];
	}
	return _toDecimal(sum);
}

/**
 * 表单验证
 * 
 * @author lwyao
 * @param element
 * @returns
 */
function _formValidation(element) {
	var isOk = true;
	$.each(element.find(".tableEditor *[class^=validate]"), function(i, n) {
		var required = $(n).parents('.tdInput').prev().find(".required");
		if (required.length > 0 && required.is(":hidden")) {
			return;
		}
		if ($(n).validationEngine("validate")) {
			isOk = false;
			return false;
		}
	});
	return isOk;
}

/**
 * 报表显示
 * 
 * @author lwyao
 * @param selector
 * @param reportID
 * @param bizID
 * @returns
 */
function _showReport(selector, reportID, bizID, centerPath) {
	if (selector) {
		selector.attr(reportID ? "reportID" : "bizID", reportID ? reportID : bizID);
		selector.attr("centerPath", centerPath);
	}
	selector = selector ? selector : $("body");
	if (selector.get(0).tagName.toLocaleLowerCase() == 'iframe') {
		selector.prop('contentWindow')._showReport(null, reportID, bizID, centerPath);
	} else {
		var param = reportID ? "reportID=" + reportID : bizID ? "bizID=" + bizID : null;
		if (centerPath)
			param += "&centerPath=" + centerPath;
		if (param) {
			var url = rootPath + "/downloadReportHtmlServlet.download?" + param;
			selector.load(url, function(response, status, xhr) {
				if (status != "success") {
					selector.html("加载失败");
					popupAlert("加载失败", "提示", "error");
					return;
				}
			});
		} else {
			selector.text("报表未生成");
		}
	}
}

/**
 * 报表导出方法
 * 
 * @param selector
 * @returns
 */
function _export(selector) {
	var bizID = selector.attr("bizID");
	var reportID = selector.attr("reportID");
	var centerPath = selector.attr("centerpath");
	if ((bizID && "none" != bizID) || (reportID && "none" != reportID)) {
		location.href = (centerPath ? centerPath : rootPath) + "/downloadReportFileServlet.download?"
				+ (bizID ? "bizID=" + bizID : "reportID=" + reportID);
	} else {
		popupAlert("无导出内容。", "提示", "warn");
	}
}

/**
 * 隐藏或显示字段
 * 
 * @author lwyao
 * @param field
 * @param toggle
 *            [hide,show]
 * @param isRemoveName
 *            (是否删除name属性)
 * @returns
 */
function _fieldToggle(field, toggle, isRemoveName) {
	$.each(field.split(","), function(i, id) {
		var v = $("#" + id);
		var vp = v.parents("td");
		var l = $("label[for=" + id + "]").parent();
		if ("hide" == toggle) {
			v.hide();
			l.hide();
			if (isRemoveName == true) {
				_name_toggle_cache[id] = v.attr("name");
				v.removeAttr("name");
				vp.hide();
			}
		} else {
			v.show();
			if (!v.hasClass("_hide"))
				l.show();
			if (_name_toggle_cache[id]) {
				v.attr("name", _name_toggle_cache[id]);
				vp.show();
			}
		}
	});
}
var _name_toggle_cache = {};

/**
 * 删除select选项
 * 
 * @author lwyao
 * @param selId
 * @param values
 *            多个值用逗号,分隔
 * @returns
 */
function _removeSelectItems(selId, values) {
	selId = $("#" + selId);
	if (values) {
		$.each(values.split(","), function(i, value) {
			selId.find("option[value=" + value + "]").remove();
		});
	} else {
		selId.find("option").each(function(i, option) {
			if (option.value)
				$(option).remove();
		});
	}

}

/**
 * 新增select选项
 * 
 * @param selId
 * @param items
 *            map{value:text}
 * @returns
 */
function _addSelectItems(selId, items) {
	selId = $("#" + selId);
	$.each(items, function(value, text) {
		if (selId.find("option[value=" + value + "]").length == 0)
			selId.append('<option value="' + value + '">' + text + '</option>');
	});
}

/**
 * 将浮点数四舍五入，取小数点后2位
 * 
 * @author lwyao
 * @param x
 * @returns
 */
function _toDecimal(x) {
	if ((x + '').indexOf(',') > 0)
		x = (x + '').replace(/,/g, '');
	var f = parseFloat(x);
	if (isNaN(f)) {
		return;
	}
	f = Math.round(x * 100) / 100;
	return f;
}

// 读参数
f_getSysParamTips = function(CenterId, policyCode, CenterOrHosp, defaultValue) {

	var json = postSync(rootPath + '/common/CommonManagerAction!getSysParamTips.action', {
		CenterId : CenterId,
		policyCode : policyCode,
		CenterOrHosp : CenterOrHosp,
		defaultValue : defaultValue
	});

	if (!checkJSONResult(json)) {
		return;
	}

	return json.data;
}

function parseISO8601(str) {
	return (new Date(str.replace(/-/g, "/")));
}

/**
 * ***************************************************************************************客户端读卡相关
 * xiexiao start
 * ****************************************************************
 */

var __nativeAgentPath = {
	down_load_url : "/resource/setup/native/setup/NativeAgentSetup.exe",// 客户端下载路径
																		// 相对于项目路径
	readic_url : "/medicaretag/MedicareChoosePersonAction!checkCard.action"/*
																			 * 读卡后台验证地址
																			 * 相对于项目路径
																			 */
};

var __funcIds = {
	func_pin_verify : "300011", // PIN检验接口功能号
	func_card_information : "300000", // 读取社保卡基本信息
	func_card_change : "300012" // 社保卡PIN修改
};

var __nativeAgent = (function() {
	var agent_service_url = 'http://127.0.0.1:8088/service';
	var agent_manage_url = 'http://127.0.0.1:8088/manage';
	if (document.location.protocol == 'https:') {
		agent_service_url = 'https://127.0.0.1:8089/service';
		agent_manage_url = 'https://127.0.0.1:8089/manage';
	}
	var call = function(url, param, callback) {
		var paramStr = JSON.stringify(param);
		var bAsync = false;
		if (navigator.userAgent.indexOf("MSIE 8.0") > 0 || navigator.userAgent.indexOf("MSIE 9.0") > 0) {
			bAsync = true;
			eval(" var paramStr = '" + paramStr + "';");
		}
		if(param.func_id=="100001" || param.func_id=="910000")
			bAsync = true;
		var ajaxObj = $.ajax({
			url : url + '?time=' + Math.random(),
			data : paramStr,
			dataType : "json",
			type : 'POST',
			async : bAsync,
			error : function(xhr, st, e) {
				if (xhr.status != "200") {
					err = "发送请求错误:" + xhr.status + " 错误文本:" + xhr.statusText || "";
				} else {
					err = xhr.responseText;
				}
				alert("发送请求错误" + err);
			}
		});
		if (typeof callback === 'function') {
			return ajaxObj.then(function(resp) {
				callback(resp);
			});
		}
		return ajaxObj;
	};
	var service = function(param, callback) {
		return call(agent_service_url, param, callback);
	};
	var manage = function(param, callback) {
		return call(agent_manage_url, param, callback);
	};
	return {
		agent_service_url : agent_service_url,
		agent_manage_url : agent_manage_url,
		service : service,
		manage : manage
	};
})();

function call_agent_service(param, callback) {
	__nativeAgent.service(param, callback);
}

function call_agent_manage(param, callback) {
	__nativeAgent.manage(param, callback);
}



var __isPing = function(callback) {
	// 1、判断当前window程序有没有安装或打开
	$.ajax({
		type : 'POST',
		url : __nativeAgent.agent_manage_url,
		data : {
			"func_id" : "ping",
			"data" : {}
		},
		dataType : 'json',
		success : function(res) {// 回调函数
			if(getdate()<=20190111)
			{
				updateDownLoad();//修改地址
			}
			
			if (typeof callback === 'function') {
				callback(res);
			}

		},
		error : function() {// 请求出错处理操作
			setupSICardExe_client();
			return;
		}
	});
};


//获取时间
function getdate(){
	 var date = new Date();
	 var year = date.getFullYear()+""; // 年
	 var month = date.getMonth() + 1+""; // 月
	 var day  = date.getDate()+""; // 日
	 if (month >= 1 && month <= 9) {
		  month = "0" + month;
	}
	if (day >= 0 && day <= 9) {
		 day = "0" + day;
	}
	 return year+month+day;
}

/**
 * 修改C盘资源下载路径
 */
var updateDownLoad=function(){
	//修改地址
	var auto_update=true;
	var version_url="http://10.137.67.246:20000/hygeia_web/resource/setup/native/config/version.xml";
	var download_url="http://10.137.67.246:20000/hygeia_web/resource/setup/native/resource/";
	
	call_agent_manage({"func_id":"version_info"},function(res){
		if(res.success_flag && null!=res.data){
			if(res.data.version_url !=version_url){
				call_agent_manage({"func_id":"version_config","data":{"auto_update":auto_update,"version_url":version_url,"download_url":download_url}},function(t){});
			}
		}
	});

	
}

/**
 * 读卡统一入口
 */
var __readIcCardByClient = function(suc_func) {
	__isPing(function() {
		__readCardInfomation(suc_func,0); // 读取社保卡信息
	});

};

/**
 * 读取社保卡基本信息(不要单独调用，目前是给社保卡统一入口调用，如果要调用，请先调用ping接口，判断客户端是否已安装或已打开)
 */
var __readCardInfomation = function(suc_func,indi) {
	call_agent_service({
		"func_id" : __funcIds.func_card_information
	}, function(obj) {
		if (null == obj || !obj.success_flag) { // 失败
			popupAlert(obj.error_info + "", "提示", "error");
		} else { // 成功
			if (null != obj.data.need_encryptor && obj.data.need_encryptor) { // 无PSAM卡或权限不够
				// 带上加密机前置服务地址继续获取信息
				// TS20032000025 - 【需求开发】湘潭市三代卡读卡需求  提供esb接口，修改控件调用地址   add by xiexiao 20200320
				var encryptor_url = "http://10.137.67.246:20001/hygeia_esb/api/call_api.action";// 加密前置服务地址
				call_agent_service({
					"func_id" : __funcIds.func_card_information,
					"data" : {
						"encryptor_url" : encryptor_url
					}
				}, function(obj) {
					if (null == obj || !obj.success_flag) { // 失败
						popupAlert(obj.error_info + "", "提示", "error");
					} else {
						__getCardInfomationCallBack(obj, suc_func,indi);
					}

				});
			} else {
				__getCardInfomationCallBack(obj, suc_func,indi);
				
			}

		}
	});

};

/**
 * 
 * 获取社保卡基本信息后续处理
 */
var __getCardInfomationCallBack = function(obj, suc_func,indi) {
	var _icinfo = {};
	if (null == obj.data.out_info || obj.data.out_info.length <= 0) {
		throw '读卡失败，请检查卡机是否正常连接或卡是否插好！';
	}
	try {
		var icFieldStr = "issuer_code|sscard_no|card_no|identifier_code|name|card_reset_info|"
				+ "spec_version|issue_date|card_validity|terminal_no|equipment_no|";

		var _bke548 = obj.data.out_info.split('|');
		if (_bke548.length < 10)
			throw _bke548[1] ? _bke548[1] : '读卡失败，请检查卡机是否正常连接或卡是否插好';
		if (_bke548[0] == 0)
			throw _bke548[1] ? _bke548[1] : '读卡失败，请检查卡机是否正常连接或卡是否插好';

		$.each(icFieldStr.split("|"), function(i, col) {
			_icinfo[col] = _bke548[i];
		});	
		$("#bke548").val(obj.data.out_info);
		//给界面传值
		var outCharge=$("#outchargeid").val();
		var operFlag = $(':radio[name="operFlag"]:checked').val();
		if('1'== outCharge || '2' == operFlag){
			$("#argName option:selected").val('aac002');
			$("#argName").change();
			$("#argName").val('aac002');
			$("#argName").change();
			$("#querystring").val(_bke548[1]);
			$("#bka100").val(_bke548[2]);
		}else{
			$("#argName option:selected").val('bka100');
			$("#argName").change();
			$("#argName").val('bka100');
			$("#argName").change();
			$("#querystring").val(_bke548[2]);
		}
		//判断是否异地卡
		var identifier_code = _bke548[3];//社会保障号码 
		if (powersi.isnull(identifier_code)) {
			popupAlert("没有获取到读卡信息！");
			return;
		}
		if(identifier_code.length>2){
			if("43"!= identifier_code.substring(0,2)){
				commonAuth(_bke548,indi);
				//readIcCard();
				return;
			}
			if("1" == $("#sbkjh").val()){
				readIcCard();
				return;
			}
		}
		if ($("#ic_i_psw").length == 0) // 没有弹框div 则增加一个在页面上
		{
			$(document.body).append("<div id='ic_i_psw' style='display:none;'></div>");
		}
		var _ic_i_psw_dialog = popupDiv("#ic_i_psw", "正在输入密码，请稍后。。。", 300, {
			showToggle : false,
			isDrag : false
		});
		$(".l-dialog #ic_i_psw").parents("tr").hide();
		setTimeout(function() {
			call_agent_service({
				"func_id" : __funcIds.func_pin_verify
			}, function(obj) { // 读取社保卡密码
				if (null == obj || !obj.success_flag) { // 校验密码失败
					_ic_i_psw_dialog.hide(); // 隐藏输入密码弹出框
					popupAlert(obj.error_info + "", "提示", "error");
					init();
				} else { // 校验密码成功
					$("#bkz300").val(obj.data.sign);//密码签名   TS19073100042 - 【需求开发】核三系统安全测评相关漏洞修复  add by xiexiao 20190731
					if("1" == $("#sbkjh").val()){
						readIcCard();
						_ic_i_psw_dialog.hide();// 隐藏输入密码弹出框
						return;
					}
					commonAuth(_bke548,indi);
					_ic_i_psw_dialog.hide();// 隐藏输入密码弹出框
				}
			}); // 调用读取密码
		}, 100);	
		return;
	} catch (e) {
		if ($(".l-dialog-alert").length == 0)
			popupAlert(e + "", "提示", "error");
		init();
		return;
	}
};

/**
 * 卡鉴权方法
 * @param para
 */
function commonAuth(_bke548,indi){
	var issuer_code = _bke548[0];// 发卡地区行政区划代码（卡识别码前6位） 
	var sscard_no = _bke548[1];//社会保障号码 
	var card_no = _bke548[2];// 卡号 
	var identifier_code = _bke548[3];// 识别码 
	var name = _bke548[4];// 持卡人姓名 
	var terminal_no = _bke548[9];// 终端机编号
	postJSON(rootPath +"/common/CommonManagerAction!commonAuth.action?inHospitalDTO.aaz500="+ card_no
		+ "&inHospitalDTO.bke550="+ identifier_code 
		+ "&inHospitalDTO.aac002="+ sscard_no
		+ "&inHospitalDTO.aac003="+ encodeURI(encodeURI(name,"UTF-8"))
		+ "&inHospitalDTO.baa027="+ issuer_code
		+ "&inHospitalDTO.bkz001="+ terminal_no,
	function(json){
		if (!checkJSONResult(json)) {
			return;
		}	
		if(indi == '1'){ //门诊结算收费判断是否是同一张卡
			$("#argName").val('aac002');
			$("#argName").change();
			if ($("#aac002").val() != _bke548[1]) {
				popupAlert("请使用同一张卡办理该业务!" + "", "提示", "info");
				return;
			}
			calc(1);
		}else{//获取人员信息bke548[2] ，后续修改成bka100，住院取业务信息在下面添加一种方式aac002
			readIcCard();
		}
	});
}

var __changeSicardPwd = function() { // 修改社保卡密码
	__isPing(function() {
		// 弹出正在输入密码框
		if ($("#ic_i_psw").length == 0) // 没有弹框div 则增加一个在页面上
		{
			$(document.body).append("<div id='ic_i_psw' style='display:none;'></div>");
		}

		var _ic_i_psw_dialog = popupDiv("#ic_i_psw", "正在输入密码，请稍后。。。", 300, {
			showToggle : false,
			isDrag : false
		});
		$(".l-dialog #ic_i_psw").parents("tr").hide();
		setTimeout(function() {
			call_agent_service({
				"func_id" : __funcIds.func_card_change
			}, function(obj) { // 修改PIN密码
				if (null == obj || !obj.success_flag) { // 修改密码失败
					popupAlert("修改卡密码失败：" + obj.error_info, "提示", "error");
				} else { // 修改密码成功
					popupAlert("修改密码成功!", "提示", "success");
				}
				_ic_i_psw_dialog.hide(); // 隐藏输入密码弹出框
			});
		}, 100);
	});

};

var SicSetupTools_client = null;
var _SicCardToolName_client = "应用程序";

function setupSICardExe_client() {
	var id = "divSetupSICard";
	var popUp = document.getElementById(id);
	if (popUp == null) {
		popUp = document.createElement('div');
		popUp.id = id;
		popUp.style.display = "none";
		popUp.style.overflow = "hidden";
		popUp.style.background = "#fff";
		popUp.style.border = "0";
		popUp.style.padding = "0";
		document.body.appendChild(popUp);
		var divtip = [];
		divtip.push('<table width="100%" cellpadding="5" cellspacing="10">');
		divtip.push('<td style="font-size: 14px;color:#575757;">');
		divtip.push('<b>请确认是否已安装并启动客户端程序</b><br/>');
		divtip.push('<ul>');
		divtip.push('<li><a style="color:#dd4b39;" target="_blank" href="' + (rootPath + __nativeAgentPath.down_load_url)
				+ '"><b>如果未安装，请点击下载并安装</b></a></li>');
		divtip.push('<li><a style="color:#dd4b39;" href="powersi://agent"><b>如果已安装，请点击启动程序</b></a></li>');
		divtip.push('<li>操作成功后，请点击<a style="color:#dd4b39;" href="javascript:location.reload();"><b>刷新页面</b></a></li>');
		divtip.push('</ul></td></table><hr/><div style="text-align:center">');
		divtip.push('<button onclick="hideSicSetupTools_client();" class="button"><i class="icon-remove"></i>关 闭</button></div>');
		popUp.innerHTML = divtip.join("");
	}
	if (SicSetupTools_client) {
		SicSetupTools_client.show();
	} else {
		SicSetupTools_client = popupDiv('#' + id, _SicCardToolName_client + '安装', 500);
	}
}

function hideSicSetupTools_client() {
	if (SicSetupTools_client) {
		SicSetupTools_client.hide();
	}
}

/**
 * jq ajax请求ie8、ie9下导致跨域问题
 * 
 * @param factory
 */
(function(factory) {
	if (typeof define === 'function' && define.amd) {
		// AMD. Register as anonymous module.
		define([ 'jquery' ], factory);
	} else if (typeof exports === 'object') {
		// CommonJS
		module.exports = factory(require('jquery'));
	} else {
		// Browser globals.
		factory(jQuery);
	}
}(function($) {

	// Only continue if we're on IE8/IE9 with jQuery 1.5+ (contains the
	// ajaxTransport function)
	if ($.support.cors || !$.ajaxTransport || !window.XDomainRequest) {
		return $;
	}

	var httpRegEx = /^(https?:)?\/\//i;
	var getOrPostRegEx = /^get|post$/i;
	var sameSchemeRegEx = new RegExp('^(\/\/|' + location.protocol + ')', 'i');

	// ajaxTransport exists in jQuery 1.5+
	$.ajaxTransport('* text html xml json', function(options, userOptions, jqXHR) {

		// Only continue if the request is: asynchronous, uses GET or POST
		// method, has HTTP or HTTPS protocol, and has the same scheme as the
		// calling page
		if (!options.crossDomain || !options.async || !getOrPostRegEx.test(options.type) || !httpRegEx.test(options.url)
				|| !sameSchemeRegEx.test(options.url)) {
			return;
		}

		var xdr = null;

		return {
			send : function(headers, complete) {
				var postData = '';
				var userType = (userOptions.dataType || '').toLowerCase();

				xdr = new XDomainRequest();
				if (/^\d+$/.test(userOptions.timeout)) {
					xdr.timeout = userOptions.timeout;
				}

				xdr.ontimeout = function() {
					complete(500, 'timeout');
				};

				xdr.onload = function() {
					var allResponseHeaders = 'Content-Length: ' + xdr.responseText.length + '\r\nContent-Type: ' + xdr.contentType;
					var status = {
						code : 200,
						message : 'success'
					};
					var responses = {
						text : xdr.responseText
					};
					try {
						if (userType === 'html' || /text\/html/i.test(xdr.contentType)) {
							responses.html = xdr.responseText;
						} else if (userType === 'json' || (userType !== 'text' && /\/json/i.test(xdr.contentType))) {
							try {
								responses.json = $.parseJSON(xdr.responseText);
							} catch (e) {
								status.code = 500;
								status.message = 'parseerror';
								// throw 'Invalid JSON: ' + xdr.responseText;
							}
						} else if (userType === 'xml' || (userType !== 'text' && /\/xml/i.test(xdr.contentType))) {
							var doc = new ActiveXObject('Microsoft.XMLDOM');
							doc.async = false;
							try {
								doc.loadXML(xdr.responseText);
							} catch (e) {
								doc = undefined;
							}
							if (!doc || !doc.documentElement || doc.getElementsByTagName('parsererror').length) {
								status.code = 500;
								status.message = 'parseerror';
								throw 'Invalid XML: ' + xdr.responseText;
							}
							responses.xml = doc;
						}
					} catch (parseMessage) {
						throw parseMessage;
					} finally {
						complete(status.code, status.message, responses, allResponseHeaders);
					}
				};

				// set an empty handler for 'onprogress' so requests don't get
				// aborted
				xdr.onprogress = function() {
				};
				xdr.onerror = function() {
					complete(500, 'error', {
						text : xdr.responseText
					});
				};

				if (userOptions.data) {
					postData = ($.type(userOptions.data) === 'string') ? userOptions.data : $.param(userOptions.data);
				}
				xdr.open(options.type, options.url);
				xdr.send(postData);
			},
			abort : function() {
				if (xdr) {
					xdr.abort();
				}
			}
		};
	});

	return $;

}));
/**
 * ***************************************************************************************客户端读卡相关
 * xiexiao end ****************************************************************
 */

/**
 * 保留四位小数
 * 
 * @param valStr
 * @returns
 */
function formatDecimal(valStr) {
	var index = valStr.lastIndexOf(".");
	var lastvalStr = valStr.substring(index + 1, valStr.length);
	if (lastvalStr.length <= 4) {
		valStr = parseFloat(valStr);
	} else {
		valStr = parseFloat(valStr).toFixed(4);
	}
	return valStr
}

/**
 * 只允许输入4位小数
 * 
 * @param obj
 */
function num(obj) {
	obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d\d\d).*$/, '$1$2.$3'); // 只能输入四个小数
}

/**
 * 获取键盘密码
 * 
 * @param num
 * @returns {String}
 */
function readPass(num) {
	var password = "";
	var req = {
		"func_id" : "310000",
		"data" : {
			"timeout" : 20,
			"voice_type" : num
		}
	};
	call_agent_service(req, function(obj) {
		if (obj.success_flag == false) {
			alert(obj.error_info);
			return;
		} else {
			password = powersi.tostring(obj.data.out_info);
			/*if (password != null) {
				password = password.substring(2, password.length - 1);
			}*/
		}
	});
	return password;
};

//身份证信息校验
identify = function(querystring) {
	// 弹出正在输入密码框
	if ($("#ic_i_psw").length == 0) // 没有弹框div 则增加一个在页面上
	{
		$(document.body).append("<div id='ic_i_psw' style='display:none;'></div>");
	}
	var _ic_i_psw_dialog = popupDiv("#ic_i_psw", "正在输入密码，请稍后。。。", 300, {
		showToggle : false,
		isDrag : false
	});
	$(".l-dialog #ic_i_psw").parents("tr").hide();
	$("#querystring").attr("disabled", true);
	setTimeout(function() {
		var password = readPass(1);
		if (powersi.isnull(password)) {
			$("#querystring").attr("disabled", false);
			_ic_i_psw_dialog.hide(); // 隐藏输入密码弹出框
			return;
		}
		var reduceflag = $("#reduceflag").val();
		var aaz509 = $("#aaz509").val();
		postJSON(rootPath + "/common/CommonManagerAction!identifyCard.action?inHospitalDTO.aac002=" + querystring + "&inHospitalDTO.aaz507="
				+ password + "&inHospitalDTO.aaz509=" + aaz509, function(json) {
			if (!checkJSONResult(json)) {
				$("#querystring").attr("disabled", false);
				return;
			}
			var jsonData = json.data.ind;
			if (jsonData == '1') {
				if (confirm("密码错误,是否继续输入密码!") == true) {
					identify(querystring);
				}
				$("#querystring").attr("disabled", false);
				_ic_i_psw_dialog.hide(); // 隐藏输入密码弹出框
			}
			if (jsonData == '2') {
				if (reduceflag == "1")
					afterChoosePerson();
				else
					getPerson(querystring);
				
				_ic_i_psw_dialog.hide(); // 隐藏输入密码弹出框
			}

			if (jsonData == '3'){
				alert(json.data.msg);
				$("#querystring").attr("disabled", false);
				_ic_i_psw_dialog.hide(); // 隐藏输入密码弹出框
			}
				
			if (jsonData == '4') {
				if (reduceflag == "1")
					afterChoosePerson();
				else
					getPerson(querystring);
				
				_ic_i_psw_dialog.hide(); // 隐藏输入密码弹出框
			}
		});
	}, 100);
}

checkIcCard = function(querystring){
		postJSON(rootPath + "/common/CommonManagerAction!checkIcCard.action?inHospitalDTO.aac002=" + querystring, function(json) {
			if (!checkJSONResult(json)) {
				$("#querystring").attr("disabled", false);
				return;
			}
			
			if (json.data.iccardflag == "0"){
				result = true;
				popupAlert(json.data.msg, "提示", "info", function(res){
					__isPing(identify(querystring));	
				});
			}
			if(json.data.iccardflag == "1"){
				result = true;
				popupAlert(json.data.msg + "\t" + json.data.tips, "提示", "info", function(res){
					__isPing(identify(querystring));	
				});
			}
			if(json.data.iccardflag == "2"){
				popupAlert(json.data.msg, "提示", "info");
			}
		});
}

//【NTS20050800118】电子凭证动态库开发   electronicVoucher用于判断是否是电子社保卡,钟声 2020/04/29
var  electronicVoucher = null;
calcReadic = function(mode) {// 收费读卡 mode:1-省内卡,2-省外卡;
	if (isReadCard == "1") {
		__isPing(function() {
			__readCardInfomation("",1); // 读取社保卡信息
				/*var isInpPsw = $('#isInpPsw').val();// 当前是否校验密码标志
				//	if (isInpPsw == 'true') {
						// 弹出正在输入密码框
						if ($("#ic_i_psw").length == 0) // 没有弹框div 则增加一个在页面上
						{
							$(document.body).append("<div id='ic_i_psw' style='display:none;'></div>");
						}
						var _ic_i_psw_dialog = popupDiv("#ic_i_psw", "正在输入密码，请稍后。。。", 300, {
							showToggle : false,
							isDrag : false
						});
						$(".l-dialog #ic_i_psw").parents("tr").hide();
						setTimeout(function() {
							call_agent_service({
								"func_id" : __funcIds.func_pin_verify
							}, function(obj) { // 读取社保卡密码
								if (null == obj || !obj.success_flag) { // 校验密码失败
									_ic_i_psw_dialog.hide(); // 隐藏输入密码弹出框
									popupAlert(obj.error_info + "", "提示", "error");
								} else { // 校验密码成功
								
									_ic_i_psw_dialog.hide();// 隐藏输入密码弹出框								
								}
							}); // 调用读取密码
						}, 100);	*/				
		});
	} else if ($("#argName").val() == "aac002" && isReadCard == "0") {

		//【NTS20050800118】电子凭证动态库开发  判断电子设备卡业务流程,钟声 2020/04/29
		if(electronicVoucher !== null){
			if(checkVoucher()){
				return;
			}else {
				//【NTS20051100306】 【医保】通过电子凭证办理的待遇业务需要有相关记录，确保后续能够统计查询  龚喜洋  2020/06/04
				$("#aka242").val("1"); // 1表示电子凭证办理的业务
				calc(1);
				return;
			}
		}
		var querystring = powersi.trim($("#querystring").val());
		var objCarda = document.getElementById("cardControl");
		var password = readPass(1);
		if (powersi.isnull(password)) {
			return;
		}
		postJSON(rootPath + "/common/CommonManagerAction!identifyCard.action?inHospitalDTO.aac002=" + querystring + "&inHospitalDTO.aaz507="
				+ password, function(json) {
			if (!checkJSONResult(json)) {
				return;
			}
			var jsonData = json.data.ind;
			if (jsonData == '1') {
				if (confirm("密码错误,是否继续输入密码!") == true) {
					calcReadic();
				}
			}
			if (jsonData == '2') {
				calc(1);
			}
			if (jsonData == '3') {
				alert(json.data.msg);
			}
		});
	} else {
		calc(1);
	}

}

/** *********************电子社保卡相关 xiexiao start ************* */
var qrcode;//二维码
var deviceList=[];//拍摄设备
var resolutionList =[{"id":"1280,720","text":"1280,720"},
                     {"id":"1600,1200","text":"1600,1200"},
                     {"id":"2048,1536","text":"12048,1536"}]; //分辨率   让用户选择具体像素

 var  cropList_qr = [
	
		{
			"id": "CUSTOM",
			"name": "自定义",
			"standard": false
		}
		];//裁剪模板
		
var rorateList_qr = [
	{
		"id": "0",
		"name": "无",
		"rorate": false,
		"angle": 0,
		"clockwise": false
	}
]; //旋转角度模板
	

var contentList_qr = [  //默认拍摄内容
       			{
       				"id": "1",
       				"name": "二维码",
       				"device":"0",
       				"crop": "SELECT",
       				"rorate": "SELECT",
       				"scan_barcode": true,
       				"status":"未拍摄"
       			}];


/**
 * 拍摄二维码，读取电子社保卡
 */
var __readEleCard = function(rollback) {
	__isPing(function(){
		__device_info(rollback);
	});
};

var __device_info = function(suc_func){  //当前可用设备列表
	var req = {"func_id": "100000"};
	call_agent_service(req,function(obj){
		if (!obj.success_flag || obj.data == undefined){
			alert(obj.error_info);
			return;
		}
		if (obj.data.result.length > 0){
			var deviceList_t = obj.data.result;
			for (var i=0;i<deviceList_t.length;i++){
				deviceList_t[i].id = String(deviceList_t[i].index);
				deviceList_t[i].resolution = "1280,720";  //默认像素
				deviceList.push(deviceList_t[i]);
			}
			__shotQRCode(suc_func);//拍摄
		}
	});
};

var __shotQRCode = function(suc_func){ //拍摄
	var req = {};
	req.func_id = "100001";
	req.data = {};
	req.data.title = "二维码扫描";
	var dataDevice = deviceList;
	req.data.device = []; //设备信息数组
	for (var i=0;i<dataDevice.length;i++)
	{
		req.data.device[i] = dataDevice[i];
	}
	req.data.crop = cropList_qr;//裁剪模板数组
	req.data.rorate = rorateList_qr;//图像旋转模板数组
	var dataContent = contentList_qr;//内容模板数组
	req.data.content = [];
	for (var i=0;i<dataContent.length;i++)
	{
		req.data.content[i] = dataContent[i];
	}
	
	$.ajax({
		type : 'POST',
		url : __nativeAgent.agent_service_url,
		data :JSON.stringify(req),
		dataType : 'json',
		success : function(res) {// 回调函数
			__shotQRCodeCallBack(res, suc_func);
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
	

};

var __shotQRCodeCallBack = function(obj, per_func){
	if (!obj.success_flag || obj.data == undefined){
		alert(obj.error_info);
		return;
	}
	if (obj.data.result.length > 0){
		qrcode = powersi.tostring(obj.data.result[0].bar_code);
		var elePswFlag = $("#elePswFlag").val();
		if(elePswFlag == 'true'){
			__inputPsw(function(obj){
				var _json = {"qrcode":qrcode.split("|")[1], "password":obj};
				if ($.isFunction(per_func)) {
					per_func.call(this, _json);
				} else {
					__readEleCardCallBack(_json);
				}
			});
		}else{
			var _json = {"qrcode":qrcode.split("|")[1], "password":"false"};
			if ($.isFunction(per_func)) {
				per_func.call(this, _json);
			} else {
				__readEleCardCallBack(_json);
			}
		}
	}
};

/**
 * 住院扫码回调函数
 */
var readele_BtnClick = function(obj){
	var qrcode = obj.qrcode;
	var password = obj.password;
	postJSON(rootPath + "/common/CommonManagerAction!queryPersonQrcode.action?inHospitalDTO.qrcode=" + qrcode 
			+ "&inHospitalDTO.aaz507=" + password, function(json) {
		if (!checkJSONResult(json)) {
			$("#querystring").attr("disabled", false);
			return;
		}
		var querystring = json.data.aac002;
		$("#argName").val("aac002");
		$("#argName").change();
		$("#querystring").val(querystring);
		getPerson(querystring);
	});
}

/**
 * 获取密码键盘输入密码
 */
var __inputPsw = function(rollback) {
	__isPing(function() { // 弹出正在输入密码框
		var _ic_i_psw_dialog = __showInputPassDiv();
		setTimeout(function() {
			call_agent_service({
				"func_id" : "310000",
				"data" : {
					"timeout" : 180,
					"voice_type" : 1
				}
			}, function(obj) { // 获取密码键盘输入的密码
				if (null == obj || !obj.success_flag) { 
					popupAlert(obj.error_info + "", "提示", "error");
				} else {
					rollback(obj.data.out_info);
				}
				_ic_i_psw_dialog.hide(); // 隐藏输入密码弹出框
			});
		}, 100);
	});
};

/**
 * 弹出正在输入密码提示框
 */
var __showInputPassDiv = function() {
	if ($("#ic_i_psw").length == 0) {// 没有弹框div 则增加一个在页面上
		$(document.body).append("<div id='ic_i_psw' style='display:none;'></div>");
	}
	var _ic_i_psw_dialog = popupDiv("#ic_i_psw", "正在输入密码，请稍后。。。", 300, {
		showToggle : false,
		isDrag : false
	});
	$(".l-dialog #ic_i_psw").parents("tr").hide();
	return _ic_i_psw_dialog;
};

var __readEleCardCallBack = function(obj){
	var qrcode = obj.qrcode;
	var password = obj.password;
	postJSON(rootPath + "/common/CommonManagerAction!queryPersonQrcode.action?inHospitalDTO.qrcode=" + qrcode 
			+ "&inHospitalDTO.aaz507=" + password, function(json) {
		if (!checkJSONResult(json)) {
			$("#querystring").attr("disabled", false);
			return;
		}
		
		var querystring = json.data.aac002;
		$("#argName").val("aac002");
		$("#argName").change();
		$("#querystring").val(querystring);
		isReadCard = "2";
		if(qrcode.indexOf('9200000002')==0){//支付宝二维码  TS19092700016 - 【需求开发】电子社保卡医保结算业务相关优化改造
			isReadCard = "3";  
		}
		if ("0" == $("#reduceflag").val()) {
			getPerson();
		} else {
			$("#argnames").val('aac002');
			afterChoosePerson();
		}
	});
}
/** *********************电子社保卡相关 xiexiao end ************* */