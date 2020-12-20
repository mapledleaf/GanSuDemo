function createWb(elementid, systemname)
{
	var divobj = document.getElementById(elementid);
	
	try{
		if (window.external.runCall) {
			divobj.innerHTML = "<font color=\"red\" size=\"3px\"><br>&nbsp;&nbsp;登录失效，请重新登录系统。</font>";
			return;
		}
	}catch(e){}
	
	//判断浏览器
	var ua = navigator.userAgent.toLowerCase();
	var is_ie = (
  		ua.indexOf("msie") != -1 &&
		ua.indexOf("opera") == -1 &&
		ua.indexOf("webtv") == -1
	);
	if(is_ie == false)
	{
		divobj.innerHTML = "<font color=\"red\" size=\"3px\"><br>程序不支持您所使用的浏览器!<br>请使用基于Internet Explorer6.0及更高版本的浏览器打开程序。</font>";
		return;
	}
	
	
	try
	{
		var obj = new ActiveXObject("HYGEIAWEBCLIENT.MainUICtrl.1");
		obj = null;
	}
	catch(e)
	{
		var setupurl = document.URL;
		setupurl = setupurl.substring(0, setupurl.lastIndexOf("/") + 1);
		if(setupurl.charAt(setupurl.length - 1) != "/")
		{
			setupurl += "/";
		}
		setupurl += "resource/setup/setupMainUI.exe";
	
		var divtip = "";
		divtip += '<table width="480" cellpadding="3" cellspacing="5" ID="Table1">';
		divtip += '<tr>';
		divtip += '<td align="left" valign="middle" width="360">';
		divtip += '<br><h1 style="COLOR:red; FONT: 12pt/15pt 宋体">无法加载程序</h1>';
		divtip += '</td>';
		divtip += '</tr>';
		divtip += '<tr>';
		divtip += '<td width="480" colspan="2">';
		divtip += '<font style="COLOR:000000; FONT-SIZE: 14px">';
		divtip += '<p>请尝试执行下列操作：</p>';
		divtip += '<ul>';
		divtip += '<li>程序没有安装或者文件已经损坏，请单击<a id="ButtonSetup" href="' + setupurl + '"><b>下载安装</b></a>按钮，下载成功后执行安装程序</li>';
		divtip += '<li>您的浏览器安全设置限制了程序运行，请打开Internet选项，在安全设置中恢复浏览器的安全级别为默认或者启用下列选项：';
		divtip += '<ul>';
		divtip += '<li>对标记为可安全执行脚本的ActiveX控件执行脚本(选择启用)</li>';
		divtip += '<li>运行ActiveX控件和插件(选择启用)</li>';
		divtip += '</ul></li>';
		divtip += '</ul></font>';
		divtip += '</td></tr>';
		divtip += '</table>';
		
		divobj.innerHTML = divtip;
		return;
	}
	
	try {
		var divhtml = '<object id="wb" codeBase="common/cab/HygeiaClientCab.cab#version=2,0,0,1" height="100%" hspace="0" width="100%" align="center" border="0" classid="clsid:C4CC570D-70A8-402F-94F6-8D8AEE2A286A" VIEWASTEXT></object>';
		divhtml += '<script type="text/javascript" event="Close()" for="wb">setTimeout("window.opener=null;window.open(\'\', \'_self\');window.close();", 0);</script>';
		divhtml += '<script type="text/javascript" event="Refresh()" for="wb">window.location.reload();</script>';
		divhtml += '<script type="text/javascript" event="StatusTextChange(StatusText)" for="wb">setStatusText(StatusText);</script>';
		divobj.innerHTML = divhtml;
		var objwb = document.getElementById("wb");
		if(objwb)
		{
			objwb.focus();
		}
    } catch(e) {
    
    }
    window.status = "";
}

var _title = null;
function setStatusText(statusText) 
{
	if (statusText === undefined || statusText == null) {
		return;
	}
	
	window.status = statusText;
	if (window.status != statusText) {
		//ie7以上版本安全限制了修改状态条
	}
	
	if(_title == null){
		_title = document.title;
	}
	
	var userDefine = "用户名："; 
	var posLeft = statusText.indexOf(userDefine);
	if(posLeft >= 0) {
		var posRight = statusText.indexOf("】", posLeft);
		if (posRight <= 0) {
			posRight = statusText.length;
		}
		
		var userName = statusText.substring(posLeft + userDefine.length, posRight);
		if (userName.length > 0) {
			//检查登录名是否包含用户名
			var loginUser = getLoginUser();
			if (loginUser.length > 0 && loginUser.indexOf(userName) >= 0) {
				userName = loginUser.toUpperCase();
			}
			
			document.title = userName + " - " + _title;
		}
	}
}

function getLoginUser() {
	var loginUser = "";
	try
	{
		var objwb = document.getElementById("wb");
		if(objwb){
			loginUser = objwb.GetProfileString("_SysParameter", "LoginUser", "");
			
			if (loginUser.length == 0) {
				loginUser = objwb.GetProfileString("LoginInfo", "UserName", "");
			}
		}
	} catch(ex) {
		loginUser = "";
	}
	
	return loginUser;
}

var loginflag = "0";
function destroyWb(elementid)
{
    var divobj = document.getElementById(elementid);
    if(divobj)
    {
        divobj.innerHTML = "";
        if (loginflag == "1") {
			logoutSys();
        }
    }
    clearCookie();
}

function exitWb()
{
	var objwb = document.getElementById("wb");
	if(objwb)
	{
		loginflag = "0";
		if (window.status.indexOf("用户名") >= 0) 
		{
			loginflag = "1";
		} 
		else //ie7状态条可能为空
		{
			try
			{
				loginflag = objwb.GetProfileString("_SysParameter", "LoginFlag", "0");
			}
			catch(e)
			{
				//alert(e.name + ": " + e.message);
			}
		}
		
		if (loginflag == "1")//login flag
		{
			var confirmflag = "1";
			try
			{
				confirmflag = objwb.GetProfileString("MainUI", "ExitConfirm", "");
			}
			catch(e)
			{
				//alert(e.name + ": " + e.message);
			}
			
			if (confirmflag != "0")//confirm flag
			{
				var tip = "";
				tip += "-----------------------------------------";
				tip += "\n\n" + document.title + "\n即将退出，请确认。\n\n";
				tip += "-----------------------------------------";
				
				window.event.returnValue = tip;
			}
		}
	}
}

function logoutSys() {
	try{
		var url = document.URL;
		url = url.substring(0, url.lastIndexOf("/") + 1);
		if(url.charAt(url.length - 1) != "/")
		{
			url += "/";
		}
		url += "WebService/ProcessAll.asmx";
		
		var dat = "";
        var a = [];
        
        a.push("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        a.push("<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ");
        a.push(" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" ");
        a.push(" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
        a.push("<soap:Body>");
        a.push("<Hygeia xmlns=\"http://powerise.com.cn/webservices/\">");
        a.push("<strFuncID>");
        a.push("SysLogout");
        a.push("</strFuncID>");
        a.push("<strXml>");
        a.push("&lt;Program&gt;");
		a.push("&lt;FunctionID&gt;");
		a.push("SysLogout");
		a.push("&lt;/FunctionID&gt;");
		a.push("&lt;parameters /&gt;");
		a.push("&lt;/Program&gt;");
        a.push("</strXml>");
        a.push("</Hygeia>");
        a.push("</soap:Body>");
        a.push("</soap:Envelope>");
        dat = a.join("");
       
        a.length = 0;
        a = null;
		
		var xhr = window.ActiveXObject ? new ActiveXObject("Microsoft.XMLHTTP") : new XMLHttpRequest();
		xhr.open("POST", url, false);
		xhr.setRequestHeader("Content-Type", "text/xml; charset=utf-8");
		xhr.setRequestHeader("SOAPAction", "http://powerise.com.cn/webservices/Hygeia");
		
		xhr.send(dat);
		
		xhr = null;
	}catch(e){}
}

function clearCookie() {
	try
	{
		var cookieName = "powersi.hygeia";
		var pathName = document.location.pathname.toLowerCase();
		
		if (pathName.length > 1) {
			if (pathName[0] = '/') {
				pathName = pathName.substring(1);
			}
			
			var pos = pathName.indexOf("/");
			if (pos >= 0) {
				pathName = pathName.substring(0, pos);
			}
			cookieName += "." + pathName;
		}
		
		var expdate = new Date();
		expdate.setTime(expdate.getTime() - 86400 * 1000 * 1);
		var arrCookie = document.cookie.split(";");
		for(var i=0;i<arrCookie.length;i++)
		{
			var name = arrCookie[i].split("=")[0].replace(/(^\s*)|(\s*$)/g, "");
			
			if (name == cookieName) {
				document.cookie =  name + "=;path=/;expires=" + expdate.toGMTString();
			}
		}
	}
	catch(e)
	{
	}
}

function createCb(elementid, cbid, cbwidth, cbheight, cbcodebase)
{
    var d = document.getElementById(elementid);
    var s = '<OBJECT id="' + cbid + '" name="' + cbid + '" codeBase="' + cbcodebase + 'HygeiaCab.cab#version=1,0,0,4" height="' + cbheight + '" width="' + cbwidth+ '" data="data:application/x-oleobject;base64,T5HSYFfwpUuAs1lmpmrkYhAHAABwDQAAEQIAAA==" classid="CLSID:60D2914F-F057-4BA5-80B3-5966A66AE462">'
    +'</OBJECT>';
    d.innerHTML = s;
}
