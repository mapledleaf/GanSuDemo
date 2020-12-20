var ip = "10.136.2.127";//电子凭证接口的ip地址
var port = "9800";//电子凭证接口的端口
var url ="http://"+ip+":"+port+"/localcfc/api/hsecfc/";//电子凭证接口的url
var transTypes = ["ec.query","iot.gain.ec.qrcode","iot.gain.ec.qrcode","iot.medical.settle.notify"];//请求的具体方法

//【湖南医保卡电子凭证】  用于唤醒扫码机===>并接收返回的数据 钟声 2020/04/30
function machineScanning(transType,operatorId,operatorName,orgId,businessType,outBizNo,bka021,aaa027) {
	//NTS20102600223 电子凭证机具设备管控  陈诗豪 20201027
	var checkStatus = "0";
	var sn_code = GetSn_COM();
	 $.ajaxSettings.async = false;//同步请求
	postJSON("/hygeia_web/medicare/DiagnoseRegisterAction!checkAkb020.action",
			{"organName":bka021,"organCode":orgId,"poolareaCode":aaa027,"toolType":transType,"sn_code":sn_code}
	, function(json){
		errortype = json.errortype;
		if("0" === errortype){
			checkStatus = "1"
		}else{
			alert(json.message);
			checkStatus = "0"
		}
		
		
	});
	 $.ajaxSettings.async = true;//改回异步
	 
		if(checkStatus==="1"){
			//成功
			var data ={};
		    var inparam ={};
		    var resultMap = {};

		    data["operatorId"]=operatorId;
		    data["operatorName"]=operatorName;
		    data["orgId"]=orgId;
		    data["officeId"]="01";
		    data["officeName"]="内科";
		    data["businessType"]=businessType;
		    //【NTS20052500228】使用支付宝设备点击二维码获取人员信息时，
		    // 只需要操作人员选择二维码还是刷脸的形式直接唤醒设备进行扫二维码或刷脸操作。
		    // 不需要参保人员在支付宝设备上进行选择。 龚喜洋  2020/05/26
		    if(transType==="0"){//微信解码
		        data["extra"]={};
		        inparam["extra"]={};
		    } else if (transType === '1') {// 支付宝解码
		        data["gainWay"]="MOBILE";
		        data["outBizNo"]=outBizNo;
		    }else if (transType === '2') {// 支付宝刷脸
		        data["gainWay"]="FACE";
		        data["outBizNo"]=outBizNo;
		    }
		    inparam["transType"]=transTypes[transType];
		    inparam["orgId"]=orgId;
		    inparam["data"]=data;
		    //唤醒扫码机
		    console.log(JSON.stringify(inparam));

		    var multiplyOcx=document.getElementById("NationECCode_ocx");
		    if (multiplyOcx)
		    {
		        var productResult = multiplyOcx.NationEcTrans(url+"localQrCodeQuery",JSON.stringify(inparam));
		        console.log(productResult);
		        resultMap =  JSON.parse(productResult);
		        return resultMap;
		    } else {
		        alert("控件不存在");
		    }
		    return null;
		}else{
			return;
		}
	
    
}

//【湖南医保卡电子凭证】 获取唯一流水号，用于支付宝扫码机  钟声 2020/05/05
function getOutBizNo() {
    var date = new Date();
    var month = date.getMonth() + 1;//获取月份
    var strDate = date.getDate();//获取具体的日期
    var strHour = date.getHours();//获取...钟点
    var strMinute = date.getMinutes();//获取分钟数
    var strSeconde = date.getSeconds();//获取秒钟数
    //判断获取月份 、 具体的日期 、...钟点、分钟数、秒钟数 是否在1~9
    //如果是则在前面加“0”
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 1 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    if (strHour >= 1 && strHour <=9) {
        strHour = "0" + strHour
    }
    if (strMinute >= 1 && strMinute <= 9) {
        strMinute = "0" + strMinute;
    }

    if (strSeconde >= 1 && strSeconde <= 9) {
        strSeconde = "0" + strSeconde;
    }
    //时间日期字符串拼接
    var outBizNo = date.getFullYear()+month+strDate+
        strHour + strMinute  + strSeconde;
    return outBizNo;
}

//【湖南医保卡电子凭证】 用于对支付宝扫码机发送就诊结果通知 总费用-totalFee 自付-selfFee 医保报销-medicalFee  钟声 2020/05/05
function sendMessage(bizType,medicalSettleState,operatorId,operatorName,totalFee,selfFee,medicalFee) {
    var data ={};
    var inparam ={};
    data["bizType"]=bizType;//"register"
    if(true){//medicalSettleState
        data["medicalSettleState"]="SUCCESS";
    }else {
        data["medicalSettleState"]="FAIL";
    }
    data["operatorId"]=operatorId;
    data["operatorName"]=operatorName;
    data["outBizNo"]=  $("#outBizNo").val();
    data["totalFee"]= totalFee;
    data["selfFee"]= selfFee;
    data["medicalFee"]=medicalFee;
    inparam["data"]=data;
    inparam["transType"]="iot.medical.settle.notify";

    var multiplyOcx=document.getElementById("NationECCode_ocx");
    if (multiplyOcx)
    {
        console.log("message"+JSON.stringify(inparam));
        var productResult = multiplyOcx.NationEcTrans(url+"localQrCodeQuery",JSON.stringify(inparam));
        var resultMap =  JSON.parse(productResult);
        console.log("message->result"+productResult);
        if(resultMap.code === 0){

        }else {
            //alert(resultMap.message);
            return true;
        }
    } else {
        //alert("NationECCode_ocx不存在");
        return false;
    }
}

//【湖南医保卡电子凭证】 用于通过token验证电子凭证是否失效   钟声 2020/05/05
function checkVoucher() {
    var multiplyOcx=document.getElementById("NationECCode_ocx");
    if (multiplyOcx)
    {
        var data = {};
        data["ecToken"]=electronicVoucher.ecToken;
        data["idType"]=electronicVoucher.idType;
        data["idNo"]=electronicVoucher.idNo;
        data["userName"]=electronicVoucher.userName;
        console.log("check"+JSON.stringify(data));
        var productResult = multiplyOcx.NationEcTrans(url+"localEcTokenCheck",JSON.stringify(data));
        var resultMap =  JSON.parse(productResult);
        console.log("check->result"+productResult);
        if(resultMap.code === 0){
            if(resultMap.data.flag === 0 ){//0 失败 1成功
                alert(resultMap.data.userName+"电子凭证校验失败");
                return true;
            }else {//校验通过
                return false;
            }
        }else {
            alert(resultMap.message);
            return true;
        }
    } else {
        alert("NationECCode_ocx不存在");
        return false;
    }
}

// NTS20120101270 获取SN码  陈诗豪
	function GetSn_COM(){
		var idNldata=document.getElementById("idNldata");
		var sn_code = idNldata.GetSn();
		$("#sn_code").val(sn_code);
		//alert( $("#sn_code").val());
		return sn_code;

	}