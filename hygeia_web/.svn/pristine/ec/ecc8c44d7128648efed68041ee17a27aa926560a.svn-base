function procVerify(userid){
	if(userid == curUserid && curUserid != '101'){
		alert("您不能审核自己申请（登记）的业务，经办人与审核人不能相同!");
		return false;
	}else{
		return true;
	}
	
}

$(function(){
	if($('#curUserid').val() != '101'){
		$(':checkbox[userid='+$('#curUserid').val()+']').prop('disabled',true);
	}
});
