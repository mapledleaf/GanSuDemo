  var verrideGridExport=function(gridId,exportFileName,queryForm,url){
		var filename=exportFileName;
		var excelTxt=$("#"+gridId+" .l-bar-btnexcel").attr("title");
		var excelA="<a onclick=verrideGridExportClick('"+gridId+"','"+filename+".xls','"+queryForm+"','"+url+"')>"+excelTxt+"</a>"
		$("#"+gridId+" .l-bar-btnexcel").after(excelA);
		$("#"+gridId+" .l-bar-btnexcel").remove();
		
		$("#"+gridId+" .l-bar-btnexcel2007").remove();
		$("#"+gridId+" .l-bar-btnpdf").remove();
	}

  var verrideGridExportClick=function(gridId,filename,queryForm,url){
		var saveItemData = "";
		if(queryForm){
			if(!checkFormValidtion())return;
			saveItemData=$("#"+queryForm).serialize();
			
		}
		var gridexport=[];
		var gridexportJson="";
		$("#"+gridId+" .l-grid-header td[id^='"+gridId+"|hcell']").each(function(i,o){
			var columnname=$(o).attr("columnname");
			var title=$(o).find("span").html();
			if(columnname&&columnname!="undefined"){
				if(gridexportJson==""){
					gridexportJson=gridexportJson+"'"+columnname+"':'"+title+"'";
				}else{
					gridexportJson=gridexportJson+",'"+columnname+"':'"+title+"'";
				}
				
			}
			
		});
		gridexportJson="{"+gridexportJson+"}";
	    location.href=url+"?"
	    	+powersi.tostring(saveItemData)
	    	+"&columns="+encodeURI(gridexportJson)
	    	+"&filename="+filename; 
	   /* location.href="${rootPath}/ExportGrid?"+powersi.tostring(saveItemData)+"&columns="+encodeURI(gridexportJson)
    	+"&accountDTO.filename="+filename;*/ 
	}