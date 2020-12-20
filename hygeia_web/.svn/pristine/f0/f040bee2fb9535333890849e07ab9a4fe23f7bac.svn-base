<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="combogrid" />
<style type="text/css">
.ui-autocomplete {
    max-height: 200px;
    overflow-y: auto;
    /* prevent horizontal scrollbar */
    overflow-x: hidden;
}
</style>
<script type="text/javascript" src="${strutsPath}/include/combogrid.js"></script>
<script type="text/javascript">
var centerId = '440100';
var actionUrl = rootPath + '/sample/Sample!queryCatalog.action?centerid=' + centerId + '&searchType=py';
jQuery(document).ready(function() {
	$("#stext").combogrid({
		minLength : 1,
		autoChoose : false,
		searchIcon : true,
		alternate : true,
		width : '500px',
		url : actionUrl,
		colModel : [ 
					 {'columnName' : 'rownumber', 'width' : '10', 'label' : '#'},
		             {'columnName' : 'ake001', 'width' : '20', 'label' : '编码'}, 
		             {'columnName' : 'ake002', 'width' : '50', 'label' : '名称'}, 
		             {'columnName' : 'aae013', 'width' : '20', 'label' : '备注'} 
		             ],
		select : function(event, ui) {
			//$("#stext").val(ui.item.ake002);

			//alert(powersi.tostring(ui.item));
			var data = ui.item;
			var a = [];
			a.push("<table class='tableFrame'>");
			a.push("<tobdy>");
			for ( var k in data) {
				a.push("<tr>");
				a.push("<td class='tdLabel'>");
				a.push(k);
				a.push("</td>");
				a.push("<td class='tdInput'>");
				a.push(data[k]);
				a.push("</td>");
				a.push("</tr>");
			}
			a.push("</tobdy>");
			a.push("</table>");
			$('#sel').html(a.join(""));

			return false;
		}
	});
	
	$( "#atext" ).autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	          url: rootPath + '/sample/Sample!queryCatalog.action?centerid=' + centerId + '&searchType=code',
	          data:{
	        	  searchTerm: request.term,
	        	  pagesize: 10
	          },
	          dataType: "json",
	          type:'POST',
	          success: function( data ) {
	            response( $.map( data.rows, function( item ) {
	              return {
	                label: item.ake001 + ' ' + item.ake002,
	                value: item.ake001
	              };
	            }));
	          }
	        });
	   	},
	   	minLength: 1
	});
});

function changeSearchType(sel) {
	actionUrl = rootPath + '/sample/Sample!queryCatalog.action?centerid=' + centerId + '&searchType=' + sel.value;
	if(sel.value == 'code'){
		$("#stext").combogrid( "option", "numChoose", false);
	} else {
		$("#stext").combogrid( "option", "numChoose", true);
	}
	$("#stext" ).combogrid( "option", "url", actionUrl);
}
</script>
</head>
<body class="grid">
	<div class="space-y"></div>
	<div class="row">
		<div class="col-4"></div>
		<div class="col-4">
			<div class="input-group">
				<span class="input-group-btn">
					<select id="sidx" onchange='changeSearchType(this)' style="width:100px;" class="select2" data-show-search="false">
					<option value="py">首拼码</option>
					<option value="wb">五笔码</option>
					<option value="name">名称</option>
					<option value="code">编码</option>
				</select>
				</span>
				<input type="text" class="text" id="stext" value="" size="30" maxlength="30" placeholder="拼音/五笔/名称/编码" />
			</div>
		</div>
		<div class="col-4"></div>
	</div>
	<div class="space-y"></div>
	<div class="row">
		<div class="col-4"></div>
		<div class="col-4">
			<input type="text" class="text" id="atext" value="" size="30" maxlenght="30" placeholder="药品编码" />
		</div>
		<div class="col-4"></div>
	</div>
	<div class="space-y"></div>
	<div class="row">
		<div class="col-4"></div>
		<div class="col-4">
			<div id="sel"></div>
		</div>
		<div class="col-4"></div>
	</div>
	
	<powersi:errors />
</body>
</powersi:html>