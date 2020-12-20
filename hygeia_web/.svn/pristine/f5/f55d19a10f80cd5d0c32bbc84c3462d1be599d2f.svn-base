function rest(){
	document.getElementById("aac001").value = '';
	document.getElementById("aac002").value = '';
	document.getElementById("aac003").value = '';
	if(document.getElementById("aac004")){
		document.getElementById("aac004").value = '';
		document.getElementById("aac006").value = '';
		document.getElementById("aac007").value = '';
	}
	if(document.getElementById("col_aab999")){
		document.getElementById("col_aab999").value = '';
		document.getElementById("col_aab069").value = '';
	}
}

function begingInput(){
	//if(document.getElementById("aac001").value == -1
	//	||document.getElementById("queryString").value == '请在此处输入姓名或身份证，回车！'
	//	|| document.getElementById("queryString").value == document.getElementById("queryString").emptyString){
		document.getElementById("queryString").value = '';
	//}
	/*
	if(document.getElementById("aac001").value=="-1"){
		document.getElementById("queryString").value = '';
	}
	*/
}

function getChoosePerson(){
	var queryString = document.getElementById("queryString").value.replace(/(^\s*)|(\s*$)/g, "");
	var queryUrl = rootPath+"/commbiz/ChoosePersonAction!getPersonInfo.action";
	var dto = {"choosePerson.queryString":queryString,"choosePerson.localFileName":localFileName,"choosePerson.empower":p_empower,"choosePerson.limitaae140":p_limitaae140,"choosePerson.limitaac060":p_limitaac060,"choosePerson.queryPersonDAOClass":queryPersonDAOClass};
	if (queryString != null && queryString != "" ){
		$.post(queryUrl,dto,
				function(jsonData) {
					var result = eval("(" + jsonData + ")");						
					if (result.errortype == 0) {
						var returnData = result.data;
						var rowsize = returnData.rowsize;
						//alert(rowsize);
						if(rowsize>0){
							if(rowsize == 1){																	
							}else{
								//弹出选人窗口b
								var urlParam = "?choosePerson.queryString="+queryString+"&choosePerson.localFileName="+localFileName+"&choosePerson.empower="+p_empower+"&choosePerson.limitaae140="+p_limitaae140+"&choosePerson.limitaac060="+p_limitaac060+"&choosePerson.queryPersonDAOClass="+queryPersonDAOClass;
								queryUrl = rootPath+"/commbiz/ChoosePersonAction!getPersonList.action"+urlParam;

								returnData = openDialog(queryUrl, 600, 800);
								if(returnData==null){
									return;
								}
								returnData = powersi.tojson(returnData);
								//alert(returnData);
							}
							document.getElementById("aac001").value = returnData.aac001;
							document.getElementById("aac002").value = returnData.aac002;
							document.getElementById("aac003").value = returnData.aac003;
							if(document.getElementById("paa027")){
								//alert(returnData.paa027);
								document.getElementById("paa027").value = returnData.paa027;
							}
							if(document.getElementById("aac004")){
								document.getElementById("aac004").value = returnData.aac004;
								document.getElementById("aac006").value = returnData.aac006;
								document.getElementById("aac007").value = returnData.aac007;
							}
							if(document.getElementById("col_aab999")){
								document.getElementById("col_aab999").value = returnData.aab999;
								document.getElementById("col_aab069").value = returnData.aab069;
							}
							//alert(returnData.aab069);
							afterChoosePerson(returnData);
						}else{
							afterNotChoosePerson(); 
							//alert("系统中没有找到符合该条件的人员，请设置正确的查询条件！");
						}							
					}
				});
	}else{
		alert("请录入正确的查询条件");
	}
}

function keyEntered(e){
	/*if(event.keyCode == 13){
		return getChoosePerson();
	}*/
}

function readCard(){
	var objCard = document.getElementById("cardControl");

	if (objCard.object == null) {
	    alert("请先安装读卡插件！");
	}
	else {
	    if(cardControl.IsHasCard()){
	    	/*alert("证件号："+cardControl.GetPersionName()+"证件号："+cardControl.GetPersionId());*/
	    	var idcard = cardControl.GetPersionId();
		  
			var queryString = idcard;
			var queryUrl = rootPath+"/commbiz/ChoosePersonAction!getPersonInfo.action";
			var dto = {"choosePerson.queryString":queryString,"choosePerson.localFileName":localFileName,"choosePerson.empower":p_empower,"choosePerson.limitaae140":p_limitaae140,"choosePerson.limitaac060":p_limitaac060,"choosePerson.queryPersonDAOClass":queryPersonDAOClass};
			if (queryString != null && queryString != "" ){
				$.post(queryUrl,dto,
						function(jsonData) {
							var result = eval("(" + jsonData + ")");						
							if (result.errortype == 0) {
								var returnData = result.data;
								var rowsize = returnData.rowsize;
								if(rowsize>0){
									if(rowsize == 1){																	
									}else{
										//弹出选人窗口b
										var urlParam = "?choosePerson.queryString="+queryString+"&choosePerson.localFileName="+localFileName+"&choosePerson.empower="+p_empower+"&choosePerson.limitaae140="+p_limitaae140+"&choosePerson.limitaac060="+p_limitaac060+"&choosePerson.queryPersonDAOClass="+queryPersonDAOClass;
										queryUrl = rootPath+"/commbiz/ChoosePersonAction!getPersonList.action"+urlParam;
	
										returnData = openDialog(queryUrl, 600, 800);
										if(returnData==null){
											return;
										}
										returnData = powersi.tojson(returnData);
									}
									document.getElementById("aac001").value = returnData.aac001;
									document.getElementById("aac002").value = returnData.aac002;
									document.getElementById("aac003").value = returnData.aac003;
									if(document.getElementById("paa027")){
										document.getElementById("paa027").value = returnData.paa027;
									}
									if(document.getElementById("aac004")){
										document.getElementById("aac004").value = returnData.aac004;
										document.getElementById("aac006").value = returnData.aac006;
										document.getElementById("aac007").value = returnData.aac007;
									}
									if(document.getElementById("col_aab999")){
										document.getElementById("col_aab999").value = returnData.aab999;
										document.getElementById("col_aab069").value = returnData.aab069;
									}
									afterChoosePerson(returnData);
								}else{
									afterNotChoosePerson(); 
								}							
							}
						});
			}else{
				alert("社保卡中的身份信息无法查询到符合该条件的人员！");
			}
	    }else{
	    	alert("请插入社保卡！");
	    }
	}
}

$(document).ready(function(){
	$(':text[name="choosePerson.queryString"]').each(function() {
		var txt = $(this);
		if(txt && txt.prop("__init__") != true) {
			txt.prop("__init__", true);
			
			txt.keydown(function(event){
			if (event.keyCode == '13') {
				try{
					var e = window.event || event;
					if (window.event) { 
						e.cancelBubble=true; 
					} else {
						e.stopPropagation(); 
					}
					event.returnValue=false;
				}catch(ex){}
				
				return getChoosePerson();
			}});
		}
	});
});