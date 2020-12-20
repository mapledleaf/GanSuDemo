<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<powersi:html>
<head>
<powersi:head title="富文本编辑" />
<script src="${strutsPath}/include/summernote.js" type="text/javascript"></script>
<style type="text/css">
.note-fontname > .btn{
	min-width: 100px;
}
</style>
</head>
<body class="page">
<div id="content" class="summernote">
</div>
<powersi:errors />
<script type="text/javascript">
$(function(){
	//禁止屏蔽退格键
	allowBackspace(false);
	
	//初始化summernote
	var maxHeight = getPageHeight() - 90;
	$('#content').summernote({
		lang:'zh-CN',
		
		toolbar: [
           ['style', ['style']],
           ['font', ['bold', 'italic', 'underline', 'clear']],
           ['fontname', ['fontname']],
           ['fontsize', ['fontsize']],
           ['color', ['color']],
           ['para', ['ul', 'ol', 'paragraph']],
           ['height', ['height']],
           ['table', ['table']],
           ['insert', ['link', 'picture', 'hr']],
           ['view', ['fullscreen', 'codeview']]
		],

		defaultFontName: 'Helvetica Neue',
		fontNames: [
	        '宋体', '微软雅黑', '楷体', '黑体', '隶书', 'Arial', 'Arial Black', 'Comic Sans MS', 'Courier New',
	        'Helvetica Neue', 'Helvetica', 'Impact', 'Lucida Grande',
	        'Tahoma', 'Times New Roman', 'Verdana'
	    ],
	  	
	    fontSizes: ['10', '11', '12', '13', '14', '15', '16', '18', '20', '24', '28', '32', '36', '48', '72'],
	      
		height: 300,
		minHeight: 300,
		maxHeight: maxHeight,
		focus: true,
		airMode: false
	});
});
</script>
</body>
</powersi:html>