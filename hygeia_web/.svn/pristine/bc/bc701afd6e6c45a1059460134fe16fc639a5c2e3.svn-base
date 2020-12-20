/// <summary>
/// Hygeia服务客户端（需要引入jquery.js)
/// </summary>
/// <example>
///  var hc = new HygeiaClient();
///  if(hc.run("BIZC130101", {biz_type: '11', indi_id: 123456}) > 0) {//成功
///		var result = hc.getresult();//获取结果参数
///		var key, value, row;
///		for(k in result) {
///			value = result[k];
///		}
///
///		var resultset = hc.getresultset();//获取第一个结果集
///		for(var i = 0; i < resultset.length; i ++) {
///			var row = resultset[i];
///			for(key in row) {
///				value = row[key];
///			}
///		}
///		
///		result = null;
///		resultset = null;
///	  }
///</example>
function HygeiaClient() {
    //// <summary>
    //// 初始化设置
    //// </summary>
    var settings = {
        /// <summary>
        /// 相对路径
        /// </summary>
        pathName: "../../",
        /// <summary>
        /// 出错显示对话框
        /// </summary>
        showError: true,
        /// <summary>
        /// 显示等待对话框（firefox,opera正常，在ie,chrome,safari下实现有bug，暂时关闭此功能）
        /// </summary>
        showRun: false
    }

    /// <summary>
    /// 初始化函数(不修改缺省值，不需要调用)
    /// </summary>
    /// <param name="options" type="object">选项</param>
    this.init = function(options) {
        jQuery.extend(settings, options);
    }

    /// <summary>
    /// 运行函数
    /// </summary>
    /// <param name="funcid" type="string">功能号（必填）</param>
    /// <param name="inparam" type="object">
    /// 1.入参对象
    /// 2.json规范的入参字符串
    /// </param>
    /// <param name="inset" type="object">
    /// 1.入参集对象(参数集名称作为名，参数行使用Array作为值)
    /// 2.符合json规范的入参集字符串
    /// </param>
    /// <returns type="int">成功返回1，出错返回-1</returns>
    this.run = function(funcid, inparam, inset) {
        if (settings.showRun) {
            showRunDialog(true);
        }
        var ret = runHygeiaClient(funcid, inparam, inset);

        if (settings.showRun) {
            showRunDialog(false);
        }

        if (ret < 0 && settings.showError) {
            this.showmessage();
        }

        return ret;
    },

    /// <summary>
    /// 显示错误信息
    /// </summary>
    this.showmessage = function() {
        alert(this.getmessage());
    },

    /// <summary>
    /// 获取完整结果对象
    /// </summary>
    /// <returns type="object">结果集对象，包括错误信息、结果参数、结果集</returns>
    this.getresultobj = function() {
        return resultset || {};
    },

    /// <summary>
    /// 获取结果集参数
    /// </summary>
    /// <returns type="object">结果参数</returns>
    this.getresult = function() {
        return resultset["parameters"] || {};
    },

    /// <summary>
    /// 获取结果集列表
    /// </summary>
    /// <returns type="array">结果集列表（字符串数组）</returns>
    this.getresultsetlist = function() {/// array(string)
        return resultsetlist || [];
    },

    /// <summary>
    /// 获取结果集行数
    /// </summary>
    /// <param name="arg" type="string">
    /// 1.结果集名称
    /// 2.结果集索引号（0开始）
    /// 3.不填(返回第一个结果集）
    /// </param>
    /// <returns type="int">无法找到结果集返回-1，否则返回结果集行数</returns>
    this.getrowscount = function(arg) {
        if (typeof (arg) === 'undefined' || arg == null) {
            arg = 0;
        }

        if (typeof arg == 'number') {
            if (arg >= 0 && arg < resultsetlist.length) {
                arg = resultsetlist[arg] || "";
            } else {
                arg = "";
            }
        }
        arg = String(arg).toLowerCase();

        var len = -1;
        if (arg.length > 0) {
            var a = resultset[arg]; //pointer
            if (typeof (a) === 'undefined') {
                len = -1;
            } else if (Object.prototype.toString.apply(a) === '[object Array]') {
                len = a.length;
            } else {
                len = 0;
            }
        }

        return len;
    },

    /// <summary>
    /// 获取结果集
    /// </summary>
    /// <param name="arg" type="string">
    /// 1.结果集名称
    /// 2.结果集索引号（0开始）
    /// 3.不填(返回第一个结果集）
    /// </param>
    /// <returns type="array">结果集（对象数组）</returns>
    this.getresultset = function(arg) {
        var ret = [];

        if (typeof (arg) === 'undefined' || arg == null) {
            arg = 0;
        }

        if (typeof arg == 'number') {
            if (arg >= 0 && arg < resultsetlist.length) {
                arg = resultsetlist[arg] || "";
            } else {
                arg = "";
            }
        }
        arg = String(arg).toLowerCase();

        if (arg.length > 0) {
            ret = resultset[arg] || [];
        }

        return ret;
    },

    /// <summary>
    /// 获取错误号
    /// </summary>
    /// <returns type="string">错误号</returns>
    this.geterrorno = function() {
        return resultset["ErrorNo"] || '';
    },

    /// <summary>
    /// 获取错误信息
    /// </summary>
    /// <returns type="string">错误信息</returns>
    this.getmessage = function() {
        return resultset["ErrorMessage"] || '';
    },

    /// <summary>
    /// 获取异常信息
    /// </summary>
    /// <returns type="string">异常信息</returns>
    this.getexception = function() {
        return resultset["Exception"] || '';
    },

    /// <summary>
    /// 获取错误类型
    /// </summary>
    /// <returns type="string">错误类型</returns>
    this.geterrortype = function() {
        return resultset["ErrorType"] || '';
    },

    /// <summary>
    /// 把结果集对象转换成字符串（此函数仅用于功能测试或者调试，业务程序请使用powersi.tostring）
    /// </summary>
    /// <returns type="string">结果集字符串</returns>
    this.resulttostring = function() {/// string
        var a = [];
        a.push('ErrorNo:' + this.geterrorno() + '\r\n');
        a.push('ErrorMessage:' + this.getmessage() + '\r\n');
        a.push('Exception:' + this.getexception() + '\r\n');
        a.push('ErrorType:' + this.geterrortype() + '\r\n');

        a.push('parameters:');
        var param = this.getresult();
        for (var k in param) {
            a.push(k + ":" + param[k] + " ");
        }
        a.push("\r\n");

        var rslist = this.getresultsetlist(); /// array
        for (var rsi = 0; rsi < rslist.length; rsi++) {
            var rsn = rslist[rsi];
            var rs = this.getresultset(rsn); /// array
            a.push(rsn + ":" + this.getrowscount(rsn) + "\r\n");

            for (var i = 0; i < rs.length; i++) {
                a.push("row" + (i + 1) + ":");
                var row = rs[i]; /// map
                for (var k in row) {
                    a.push(k + ":" + row[k] + " ");
                }
                a.push("\r\n");
            }
        }

        var str = a.join("");
        a.length = 0;
        a = null;

        return str;
    }

    //以下是私有变量和方法
    var resultset = {
        ErrorType: "",
        ErrorNo: "",
        ErrorMessage: "",
        Exception: ""
    };

    var resultsetlist = [];

    function showRunDialog(show) {
        try {
            showRunning(show);
        } catch (e) {

        }
    }

    function handleError(errtype, errno, errmsg, exception) {
        resultset.ErrorType = errtype || "";
        resultset.ErrorNo = errno || "";
        resultset.ErrorMessage = errmsg || "";
        resultset.Exception = exception || "";
    }

    function checkResult() {
        return resultset.ErrorType === "0";
    }

    function runHygeiaClient(funcid, inparam, inset) {
        handleError("0");
        resultsetlist.length = 0;

        if (typeof (funcid) !== 'string' || funcid.length == 0) {
            handleError("9", "ParamError", "功能号不能为空");
            return;
        }

        var dat = "";
        var a = [];
        try {
            a.push("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            a.push("<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ");
            a.push(" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" ");
            a.push(" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
            a.push("<soap:Body>");
            a.push("<Hygeia xmlns=\"http://powerise.com.cn/webservices/\">");
            a.push("<strFuncID>");
            a.push(funcid);
            a.push("</strFuncID>");
            a.push("<strXml>");
            a.push(packParam(funcid, inparam, inset));
            a.push("</strXml>");
            a.push("</Hygeia>");
            a.push("</soap:Body>");
            a.push("</soap:Envelope>");
            dat = a.join("");
        } catch (ex) {
            handleError("9", "XMLPackError", "打包请求串出现异常", ex + "");
        }
        a.length = 0;
        a = null;

        if (!checkResult()) {
            return -1;
        }

        var resp = {};
        try {
            resp = jQuery.ajax({
                url: settings.pathName + "WebService/ProcessAll.asmx",
                contentType: "text/xml; charset=utf-8",
                type: "POST",
                data: dat,
                dataType: "xml",
                processData: false,
                async: false,
                global: false,
                beforeSend: function(xhr) {
                    xhr.setRequestHeader("SOAPAction", "http://powerise.com.cn/webservices/Hygeia");
                },
                error: function(xhr, st, e) {
                    var msg = "发送请求出现错误";
                    if (xhr.status != "200") {
                        msg = msg + ":\r\nstatus-code:" + xhr.status
								+ " status-text:" + xhr.statusText || "";
                    }
                    handleError("9", "XMLHttpRequestError", msg, (st || e) + "");
                }
            });
        } catch (ex) {
            handleError("9", "XMLHttpRequestError", "发送请求出现异常", ex + "");
        }
        dat = "";
        dat = null;

        if (checkResult()) {
            try {
                unpackResult(resp.responseText);
            } catch (ex) {
                handleError("9", "XMLParseError", "解包结果串出现异常", ex + "");
            }
        }

        resp = null;
        return checkResult() ? 1 : -1;
    }

    function packParam(funcid, inparam, inset) {
        var a = [];

        a.push("<Program>");
        a.push("<FunctionID>");
        a.push(funcid);
        a.push("</FunctionID>");

        a.push("<parameters>");
        if (typeof (inparam) != 'undefined' && inparam != null) {
            if (typeof (inparam) == 'string') {
                inparam = parseParam(inparam);
            }
            for (var name in inparam) {
                var key = name.toLowerCase();
                if (key.length == 0) {
                    continue;
                }

                var value = inparam[key];
                if (Object.prototype.toString.apply(value) === '[object Array]') {
                    if (inset === undefined || inset === null) {
                        inset = {};
                    }
                    inset[key] = value;
                    continue;
                }

                a.push("<" + key + ">");
                a.push(xmlEncode(value));
                a.push("</" + key + ">");
            }
        }
        a.push("</parameters>");

        if (typeof (inset) != 'undefined' && inset != null) {
            if (typeof (inset) == 'string') {
                inset = parseParam(inset);
            }
            for (var rsname in inset) {
                var rskey = rsname.toLowerCase();
                if (rskey.length == 0) {
                    continue;
                }

                var value = inset[rsname];
                a.push("<" + rskey + ">");
                if (Object.prototype.toString.apply(value) === '[object Array]') {
                    for (var i = 0; i < value.length; i++) {
                        var row = value[i];
                        if (typeof (row) != "object") {
                            continue;
                        }

                        a.push("<row" + (i + 1) + ">");
                        for (var name in row) {
                            var key = name.toLowerCase();
                            if (key.length == 0) {
                                continue;
                            }
                            a.push("<" + key + ">");
                            a.push(xmlEncode(row[name]));
                            a.push("</" + key + ">");
                        }
                        a.push("</row" + (i + 1) + ">");
                    }
                }
                a.push("</" + rskey + ">");
            }
        }

        a.push("</Program>");

        var str = xmlEncode(a.join(""));
        a.length = 0;
        a = null
        return str;
    }

    function parseParam(text) {
        try {
            return eval('(' + text + ')');
        } catch (ex) {
            throw new Error('参数语法错误：\r\n' + ex || '');
        }
    }

    function unpackResult(resultstr) {
        resultset.Parameters = {};

        if (!resultstr) {
            handleError("9", "XMLParseError", "返回结果为空");
            return;
        }

        var start = -1, end = -1, starttag = '<HygeiaResult>', endtag = '</HygeiaResult>';
        start = resultstr.indexOf(starttag);
        end = resultstr.indexOf(endtag);
        var str = "";
        if (start >= 0 && end >= 0) {
            str = resultstr.substring(start + starttag.length, end);
            str = xmlDecode(str);

            parseResult(str);

            str = "";
        }
        str = null;

        if (resultset == null || resultset.length == 0) {
            handleError("9", "XMLParseError", "返回结果语法错误", resultstr);
        }

        resultstr = "";
        resultstr = null;

        return;
    }

    function parseResult(xml) {
        if (!xml) {
            return;
        }

        var parser = false;
        var doc = null;
        if (typeof xml == 'string') {
            try {
                parser = (jQuery.browser.msie)
						? new ActiveXObject("Microsoft.XMLDOM")
						: new DOMParser();
                parser.async = false;
            } catch (e) {
                throw new Error("XML Parser could not be instantiated")
            }
            try {
                if (jQuery.browser.msie) {
                    doc = parser.loadXML(xml) ? parser : false;
                } else {
                    doc = parser.parseFromString(xml, "text/xml");
                }
            } catch (e) {
                throw new Error("Error parsing XML string");
            }
        } else {
            doc = xml;
        }

        if (!doc.nodeType) {
            return;
        }

        if (doc.nodeType == 3 || doc.nodeType == 4) {
            return;
        }

        var root = (doc.nodeType == 9) ? doc.documentElement : doc;

        parseResultSet(root);

        root = null;
        doc = null;
        parser = null;

        return;
    }

    function parseResultSet(node) {
        if (node && node.childNodes && node.childNodes.length > 0) {
            for (var i = 0; i < node.childNodes.length; i++) {
                var cn = node.childNodes[i];
                var cnn = cn.localName || cn.nodeName || '';
                var cnnlower = cnn.toLowerCase();

                switch (cnnlower) {
                    case "errortype":
                    case "errorno":
                    case "errormessage":
                    case "exception":
                        resultset[cnn] = parseResultValue(cn);
                        break;
                    case "parameters":
                        resultset[cnnlower] = parseResultRow(cn);
                        break;
                    default:
                        resultset[cnnlower] = parseResultTable(cn);
                        resultsetlist.push(cnnlower);
                        break;
                }
            }
        }
    }

    function parseResultValue(node) {
        var nv = "";
        if (node && node.childNodes && node.childNodes.length > 0) {
            nv = node.childNodes[0].nodeValue || '';
        }
        return nv;
    }

    function parseResultRow(node) {
        var nv = {};
        if (node && node.childNodes && node.childNodes.length > 0) {
            for (var i = 0; i < node.childNodes.length; i++) {
                var cn = node.childNodes[i];
                var cnn = cn.localName || cn.nodeName || '';
                nv[cnn] = parseResultValue(cn);
            }
        }
        return nv;
    }

    function parseResultTable(node) {
        var nv = [];
        if (node && node.childNodes && node.childNodes.length > 0) {
            for (var i = 0; i < node.childNodes.length; i++) {
                var cn = node.childNodes[i];
                nv.push(parseResultRow(cn));
            }
        }
        return nv;
    }

    function xmlDecode(xml) {
        if (!xml) {
            return "";
        }

        xml = xml.replace(/&lt;/g, '<');
        xml = xml.replace(/&gt;/g, '>');

        xml = xml.replace(/&quot;/g, '"');
        xml = xml.replace(/&apos;/g, '\'');

        xml = xml.replace(/&amp;/g, '&');

        return xml;
    }

    function xmlEncode(text) {
        if (!text) {
            return "";
        }
        var str = text + '';
        var c;
        var a = [];
        for (var i = 0; i < str.length; i++) {
            c = str.charAt(i);
            switch (c) {
                case '&':
                    a.push("&amp;");
                    break;
                case '<':
                    a.push("&lt;");
                    break;
                case '>':
                    a.push("&gt;");
                    break;
                case '"':
                    a.push("&quot;");
                    break;
                case '\'':
                    a.push("&apos;");
                    break;
                default:
                    a.push(c);
                    break;
            }
        }

        str = a.join("");
        a.length = 0;
        a = null;
        return str;
    }
}