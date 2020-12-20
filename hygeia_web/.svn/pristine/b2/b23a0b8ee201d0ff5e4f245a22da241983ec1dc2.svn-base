
function restCorp(){
	document.getElementById("aab001").value = '';
	document.getElementById("aab069").value = '';
	document.getElementById("aab999").value = '';
}

function begingInputCorp(){
	//if(document.getElementById("aab001").value=="-1"){
		document.getElementById("queryStringCorp").value = '';
	//}
}

function getChooseCorp(){
	
	var queryStringCorp = document.getElementById("queryStringCorp").value.replace(/(^\s*)|(\s*$)/g, "");
	var queryUrl = rootPath+"/commbiz/ChooseCorpAction!getCorpInfo.action";
	var urlParam = "?chooseCorp.queryStringCorp="+queryStringCorp+"&chooseCorp.empower="+empower+"&chooseCorp.limitaae140="+limitaae140+"&chooseCorp.limitaab051="+limitaab051+"&chooseCorp.queryDAOClass="+queryDAOClass;
	var dto = {"chooseCorp.queryStringCorp":queryStringCorp,"chooseCorp.empower":empower,"chooseCorp.limitaae140":limitaae140,"chooseCorp.limitaab051":limitaab051,"chooseCorp.queryDAOClass":queryDAOClass};
	if (queryStringCorp != null && queryStringCorp != "" ){
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
								//弹出选单位窗口；
								urlParam = urlParam.replace(/%/g,"%25");
								queryUrl = rootPath+"/commbiz/ChooseCorpAction!getCorpList.action"+urlParam;
								//alert(queryUrl);
								returnData = openDialog(queryUrl,600,800);
								//alert(returnData==null);
								if(returnData==null){
									return;
								}
								returnData = powersi.tojson(returnData);
								//;
							}
							//alert(returnData.aab069+"；");
							//alert(returnData.aab999+"；");
							
							document.getElementById("aab001").value = returnData.aab001;
							document.getElementById("aab069").value = returnData.aab069;
							document.getElementById("aab999").value = returnData.aab999;
							afterChooseCorp(returnData);
						}else{
							alert(decodeURI('%E7%B3%BB%E7%BB%9F%E4%B8%AD%E6%B2%A1%E6%9C%89%E6%89%BE%E5%88%B0%E7%AC%A6%E5%90%88%E8%AF%A5%E6%9D%A1%E4%BB%B6%E7%9A%84%E5%8D%95%E4%BD%8D%EF%BC%8C%E8%AF%B7%E8%AE%BE%E7%BD%AE%E6%AD%A3%E7%A1%AE%E7%9A%84%E6%9F%A5%E8%AF%A2%E6%9D%A1%E4%BB%B6%EF%BC%81'));
						}							
					}
				});
	}else{
		alert(decodeURI("%E8%AF%B7%E5%BD%95%E5%85%A5%E6%AD%A3%E7%A1%AE%E7%9A%84%E6%9F%A5%E8%AF%A2%E6%9D%A1%E4%BB%B6"));
	}
}

function ChooseCorpkeyEntered(e){
	/*if(event.keyCode == 13){
		return getChooseCorp();
	}*/
}

$(document).ready(function(){
	$(':text[name="chooseCorp.queryStringCorp"]').unbind("keydown");
	$(':text[name="chooseCorp.queryStringCorp"]').keydown(function(event){
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
			
			return getChooseCorp();
		}
	});
});