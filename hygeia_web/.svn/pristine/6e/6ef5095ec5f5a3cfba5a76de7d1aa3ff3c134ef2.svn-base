function selKzh04(data) {
	if (data['bkh015'] == null || data['bkh015'] == ''
			|| data['bkh015'] == undefined || data['bkh015'] == "undefined") {
		return true;
	}
	loadPsTemplateFee(data['bkh015']);
	return false;
}

function loadPsTemplateFee(bkh015) {
	var params = '';
	params += "type=2&bkh015=" + bkh015;
	postJSON(rootPath + "/medicare/HospManageAction!loadPsTemplateSel.action",
			params, callbackloadPsTemplateFee);
}

function callbackloadPsTemplateFee(json) {
	if (!checkJSONResult(json)) {
		return;
	}
	if (json.errortype == 0) {
		var suss = json.data.suss;
		if (suss == 1) {
			var rowsize = json.data.feeinfo.length;
			if (rowsize > 0) {
				var feeinfot = json.data.feeinfo;
				for (var i = 0; i < feeinfot.length; i++) {
					feeinfot[i].ake007 = $("#ake007").val();
					grid.add(feeinfot[i]);
				}
			} else {
				alert("获取费用信息失败！");
			}
		} else {
			alert(json.data.message);
		}
	} else {
		alert(json.message);
	}
}

var copyObj = function(obj) {
	var str, newobj = obj.constructor === Array ? [] : {};
	if (typeof obj !== 'object') {
		return;
	} else if (window.JSON) {
		str = JSON.stringify(obj), // 系列化对象
		newobj = JSON.parse(str); // 还原
	} else {
		for ( var i in obj) {
			newobj[i] = typeof obj[i] === 'object' ? cloneObj(obj[i]) : obj[i];
		}
	}
	return newobj;
};

// 不是套餐
function valideDel(rows) {
	$.each(rows, function(i, row) {
//		if (row['bkh015'] == undefined || row['bkh015'] == null
//				|| row['bkh015'] == "") {
			valideDelFee(row)
//		}
	});
}

function valideDelFeeSetMeal(rows) {
	var invalid = false;
	var mealFeeRow = null;
	$.each(rows, function(i, row) {
		if (row['bkh015'] != undefined && row['bkh015'] != null
				&& row['bkh015'] != "") {
			invalid = true;
			mealFeeRow = row;
			return invalid;
		}
	});

	if (invalid && mealFeeRow != null) {
		popupConfirm("您选择的费用包含套餐，[是]删除相应的套餐,[否]删除此条费用!", "提示", function(isOK) {
			if (isOK) {
				var rowsTemp = grid.getRows();
				$.each(rowsTemp, function(i, row) {
					if (row['bkh015'] == mealFeeRow['bkh015']) {
						if (valideDelFee(row)) {
							invalid = true;
						}
					}
				});
				return false;
			} else {
				if (valideDelFee(mealFeeRow)) {
					invalid = true;
					return true;
				}
			}
		});
	}
}

function valideDelFee(row) {
	if (row['bka892'] != undefined && row['bka892'] != '0') {
		var aae019Temp = row['aae019'];
		if (aae019Temp != undefined && aae019Temp != null) {
			aae019Temp = parseFloat(aae019Temp);
			if (aae019Temp < 0) {
				popupAlert("[" + row['ake006'] + "]已退费用不允许删除!", "提示", "info");
				return;
			}
			if (row['bka059'] != null && row['bka059'] != undefined
					&& row['bka059'] != "") {
				var bka059Temp = parseFloat(row['bka059']);
				if (bka059Temp > 0) {
					popupAlert("[" + row['ake006'] + "]已退费用不允许删除!", "提示",
							"info");
					return;
				}
			}
		}
		var delRow = copyObj(row);
		insertffy(delRow, row);
	} else {
		var rowid = grid.getRowId(row);
		var bka062 = row['bka062'];
		if (bka062 != "") {// 清除删除标记
			var rows = grid.getRows();
			$.each(rows, function(i, row) {
				if (row['aaz213'] == bka062) {
					row['bka059'] = "";
				}
			});
		}
		grid.deleteRow(rowid);
	}
}

function insertffy(rowPar, oldRow) {
	rowPar['bka059'] = oldRow['aae019'];
	rowPar['bka892'] = '0';
	rowPar['bka062'] = oldRow['aaz213'];
	rowPar['aaz213'] = '';
	rowPar['akc226'] = -parseFloat(rowPar['akc226']);
	rowPar['aae019'] = -parseFloat(rowPar['aae019']);
	grid.add(rowPar);
	setColorError();
}

function setColorError() {
	var rows = grid.getRows();
	$.each(rows, function(i, row) {
		var aae019Temp = row['aae019'];
		if (aae019Temp != undefined && aae019Temp != null) {
			aae019Temp = parseFloat(aae019Temp);
			if (aae019Temp < 0) {
				var rowid = grid.getRowId(row);
				grid.setRowColor(rowid, powersi.color('error'));
			}
		}
	});
}