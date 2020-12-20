var _dwIdcardVer = "3.0.0.5";
var _dwIdcardSetup = "/resource/setup/setupIdcard.exe";
var _dwIdcardExe = "%tool%idcard\\sdtapi.dll";

/*
 读取身份证信息
 */
function readIdcardInfo() {
	try {
		var cardstr = null;
		if(isActivex()){
			cardstr = window.external.readIdcard(_dwIdcardExe,
					_dwIdcardVer, _dwIdcardSetup);
		} else{
			var hwc = getHwc();
			if(hwc != null){
				cardstr = getHwc().ReadIdcard(_dwIdcardExe,
					_dwIdcardVer, _dwIdcardSetup);
			} else {
				return {
					"errortype": "2",
					"errormsg": "控件加载失败"
				};
			}
		}
		
		if (cardstr == undefined || cardstr == null || cardstr.length == 0) {
			return {
				"errortype": "2",
				"errormsg": '读取失败'
			};
		}

		var cardinfo = eval("(" + cardstr + ")");
		cardinfo.data = cardstr;

		return cardinfo;
	} catch (ex) {
		return {
			"errortype": "2",
			"errormsg": ex.message
		};
	}
}

/*
 读取模块序列号
 */
function readSAMIDInfo() {
	try {
		var cardstr = null;
		if(isActivex()){
			cardstr = window.external.readSAMID(_dwIdcardExe, _dwIdcardVer,
					_dwIdcardSetup);
		} else{
			var hwc = getHwc();
			if(hwc != null){
				cardstr = getHwc().ReadSAMID(_dwIdcardExe,
						_dwIdcardVer, _dwIdcardSetup);
			} else{
				return {
						"errortype": "2",
						"errormsg": "控件加载失败"
				};
			}
		}
		
		if (cardstr == undefined || cardstr == null || cardstr.length == 0) {
			return {
				"errortype": "2",
				"errormsg": '读取失败'
			};
		}

		var cardinfo = eval("(" + cardstr + ")");
		return cardinfo;
	} catch (ex) {
		return {
			"errortype": "2",
			"errormsg": ex.message
		};
	}
}

var _hwcObj = null;
function getHwc() {
    if(_hwcObj == null) {
    	try {
    		var controlObj = null;
    	    var divObj = null;
    	    var objName = "_objcontrol";
    	    var divName = "_objcontent";
    	    var exefileVer = "";
    	    
	        divObj = document.getElementById(divName);
	        if (divObj == null) {
	            divobj = document.createElement('div');
	            divobj.id = divName;
	            divobj.style.visibility = "hidden";

	            document.body.appendChild(divobj);
	        }

	        var divhtml = '<object id="' + objName + '" style="VISIBILITY: hidden; display: none;" codeBase="HygeiaWebControl.ocx#version=1,0,0,1" classid="clsid:3B8879AF-211F-46F3-AC41-041793C5060B"></object>';
	        document.getElementById(divName).innerHTML = divhtml;

	        controlObj = document.getElementById(objName);
	        if (controlObj != null) {
	        	if(controlObj.GetFileSize(_dwIdcardExe) > 0){
	        		exefileVer = controlObj.GetFileVersion(_dwIdcardExe);
	        	} else {
	        		setupIdcard();
	        		return null;
	        	}
	        }
	    } catch(e) {
	    	setupIdcard();
	        //alert(_dwToolName + "安装失败。" + "\r\n错误号：" + e.number + "\r\n错误信息:" + e.message);
	        return null;
	    }
	 
	    if (controlObj == null) {
	        document.getElementById(divName).innerHTML = "";
	        alert("控件加载失败，请使用IE浏览器操作");
	        return null;
	    }

	    _hwcObj = controlObj;
    }
    
    return _hwcObj;
}

/*安装工具程序*/
var hideObjectArray = [];
function setupIdcard() {
    var setupurl = rootPath + _dwIdcardSetup;
    
    var allSelObj = document.getElementsByTagName("select");
    for (var i = 0; i < allSelObj.length; i++) {
        if (allSelObj[i].getAttribute("hide") != "true") {
            hideObjectArray.push(allSelObj[i]);
            allSelObj[i].style.display = "none";
        }
    }
    var allObj = document.getElementsByTagName("object");
    for (var i = 0; i < allObj.length; i++) {
        if (allObj[i].getAttribute("hide") != "true") {
            hideObjectArray.push(allObj[i]);
            allObj[i].style.display = "none";
        }
    }

    var popUp = document.getElementById("_popupcontent");
    if (popUp == null) {
        popUp = document.createElement('div');
        popUp.id = "_popupcontent";

        popUp.style.position = "absolute";
        popUp.style.visibility = "hidden";
        popUp.style.overflow = "hidden";

        popUp.style.background = "#eeeeee";
        popUp.style.border = "1px solid #aaccee";
        popUp.style.padding = "10px";

        document.body.appendChild(popUp);
    }

    var w = 500,
    h = 320;
    var t = (document.body.clientHeight - h) / 2;
    var l = (document.body.clientWidth - w) / 2;
    if (t <= 0) {
        t = 5;
    }
    if (l <= 0) {
        l = 5;
    }
    popUp.style.top = t + "px";
    popUp.style.left = l + "px";
    popUp.style.width = w + "px";
    popUp.style.height = h + "px";

    var divtip = [];
    divtip.push('<table width="480" cellpadding="3" cellspacing="5" ID="tableSetup">');
    divtip.push('<tr>');
    divtip.push('<td align="left" valign="middle" width="360">');
    divtip.push('<br/><h3 style="color:red;font-size:14px;font-weight: bold;">身份证读卡程序安装</h3>');
    divtip.push('</td>');
    divtip.push('</tr>');
    divtip.push('<tr>');
    divtip.push('<td width="480" colspan="2" style="text-align:left;">');
    divtip.push('<font style="color:000000; font-size: 13px;">');
    divtip.push('<b>请尝试执行下列操作：</b><br/>');
    divtip.push('<ul>');
    divtip.push('<li style="margin-top:6px;">1.控件没有安装或者文件已经损坏，请点击<a id="btnSetup" target="_blank" href="' + setupurl + '" onclick="setTimeout(\'hideSetupIdcard()\', 500);"><b>下载安装</b></a>。（如果您使用Windows Vista及以上版本的操作系统，请保存安装程序到本地，然后以管理员身份运行安装程序）</li>');
    divtip.push('<li style="margin-top:6px;">2.控件安装后，请检查输入地址栏下方是否看到黄色的字样“此网站需要运行以下加载项...”，在点击运行加载项后，控件才能使用');
    divtip.push('<li style="margin-top:6px;">3.浏览器安全级别太高，请调整到中或者默认级别，或者启用下列选项：');
    divtip.push('<ul>');
    divtip.push('<li style="margin-top:6px;">3.1对标记为可安全执行脚本的ActiveX控件执行脚本(选择启用)</li>');
    divtip.push('<li style="margin-top:6px;">3.2运行ActiveX控件和插件(选择启用)</li>');
    divtip.push('</ul></li>');
    divtip.push('</ul></font>');
    divtip.push('</td></tr>');
    divtip.push('<tr><td align="center" valign="middle"><button onclick=\"hideSetupIdcard();\" style="margin-top:20px;">关 闭</button></td></tr>');
    divtip.push('</table>');

    popUp.innerHTML = divtip.join("");
    popUp.style.visibility = "visible";

    popUp.focus();
}

function hideSetupIdcard() {
    var popUp = document.getElementById("_popupcontent");
    if (popUp == null) {
        return;
    }
    popUp.style.visibility = "hidden";

    for (var i = 0; i < hideObjectArray.length; i++) {
        hideObjectArray[i].style.display = "";
    }
    hideObjectArray.length = 0;
}