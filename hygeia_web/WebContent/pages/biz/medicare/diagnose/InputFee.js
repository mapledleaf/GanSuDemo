function selKzh04(data)
{
	
		if(data['bkh015']==null || data['bkh015']=='' || data['bkh015']==undefined || data['bkh015']=="undefined")
		{
			return true;
		}
		 
		loadPsTemplateFee(data['bkh015']);
		return false;	
}
		
		
function loadPsTemplateFee(bkh015) {
		 var params =''; 
	 		params += "type=2&bkh015="+bkh015;
		 postJSON(rootPath+"/medicare/HospManageAction!loadPsTemplateSel.action",params,callbackloadPsTemplateFee);
}
		

function callbackloadPsTemplateFee(json)
{
	    if(!checkJSONResult(json)){
		 return;
	    } 
	    
		if (json.errortype == 0) {
	  
		var suss=json.data.suss;
		 if(suss==1)
		 {
		     var rowsize = json.data.feeinfo.length;
			if(rowsize>0){
			    var feeinfot=json.data.feeinfo;
			  for(var i=0;i<feeinfot.length;i++){
				feeinfot[i].bka051=$("#bka051").val();
				gridFeeDetail.add(feeinfot[i]);
			 }
			//grid.loadData(feeinfot);   
			  
			}else{
				alert("获取费用信息失败！");
			}
		 
		 }
		 else
		 {
		 alert(json.data.message);
		 }
		}
		else{
			var mes=json.message;
			alert(mes);
		}
}

