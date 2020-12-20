<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<powersi:html>
<head>
<powersi:head title="表单提交" />
<script src="${strutsPath}/include/fileupload.js" type="text/javascript"></script>
</head>
<body class="grid">
	<div class="row">
		<div class="col-6">
			<powersi:panelbox key="panel_query" title="异步AJAX(支持跨域jsonp调用)">
				<powersi:form id="form1" disabled="true" onsubmit="ajax1()">
					<powersi:editorlayout cols="25%,75%">
						<powersi:editorlayout-row>
							<powersi:editorlayout-group>
								http://www.powersi.com.cn:7019/webui
							</powersi:editorlayout-group>
						</powersi:editorlayout-row>
						<powersi:editorlayout-row>
							<powersi:textfield id="callUrl1" name="call_url" label="访问地址" placeholder="为空访问当前网站，框架自动识别jsonp参数作为回调函数名" />
						</powersi:editorlayout-row>
						<powersi:editorlayout-row>
							<powersi:textfield id="loginUser1" name="login_user" key="login_user" required="true"/>
						</powersi:editorlayout-row>
						<powersi:editorlayout-row>
							<powersi:textarea rows="10" id="result1" readonly="true" cssClass="bordered" label="返回结果"></powersi:textarea>
						</powersi:editorlayout-row>
					</powersi:editorlayout>
				</powersi:form>
				<div class="divButton">
					<powersi:button id="btn1" key="button_ok" onclick="ajax1()"></powersi:button>
				</div>
			</powersi:panelbox>
		</div>
		<div class="col-6">
			<powersi:panelbox key="panel_query" title="同步AJAX">
				<powersi:form id="form2" disabled="true" onsubmit="ajax2()">
					<powersi:editorlayout cols="25%,75%">
						<powersi:editorlayout-row>
							<powersi:editorlayout-group>
								ajax不支持同步跨域调用
							</powersi:editorlayout-group>
						</powersi:editorlayout-row>
						<powersi:editorlayout-row>
							<powersi:textfield id="callUrl2" name="call_url" label="访问地址" cssClass="textError" readonly="true" />
						</powersi:editorlayout-row>
						<powersi:editorlayout-row>
							<powersi:textfield id="loginUser2" name="login_user" key="login_user" required="true" />
						</powersi:editorlayout-row>
						<powersi:editorlayout-row>
							<powersi:textarea rows="10" id="result2" readonly="true" cssClass="bordered" label="返回结果"></powersi:textarea>
						</powersi:editorlayout-row>
					</powersi:editorlayout>
				</powersi:form>
				<div class="divButton">
					<powersi:button id="btn2" key="button_ok" onclick="ajax2()"></powersi:button>
				</div>
			</powersi:panelbox>
		</div>
		<div class="col-6">
			<powersi:panelbox key="panel_query" title="文件下载">
				<powersi:form id="dwnForm" disabled="true">
					<powersi:editorlayout cols="4">
						<powersi:editorlayout-row>
							<powersi:textfield id="textCode" name="text_code" label="文本代码" />
							<powersi:textfield id="textValue" name="text_value" label="文本值" />
						</powersi:editorlayout-row>
					</powersi:editorlayout>
					<div class="divButton">
						<powersi:submit id="btn3" key="button_download" onclick="download()"></powersi:submit>
					</div>
				</powersi:form>
			</powersi:panelbox>
		</div>
		<div class="col-6">
			<powersi:panelbox key="panel_query" title="文件上传">
				<powersi:editorlayout cols="25%,75%">
					<powersi:editorlayout-row>
						<powersi:file id="fileUpload" name="uploads" label="选择文件" />
					</powersi:editorlayout-row>
				</powersi:editorlayout>
				<div class="divButton">
					<powersi:button id="btnUpload" key="button_upload"></powersi:button>
				</div>
			</powersi:panelbox>
		</div>
		<div class="col-6">
			<powersi:panelbox key="panel_query" title="批量文件上传">
				<powersi:editorlayout cols="25%,75%" id="divFiles">
					<powersi:editorlayout-row>
						<powersi:file id="fileUpload1" name="uploads" label="选择文件" multiple="multiple"/>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:file id="fileUpload2" name="uploads" label="选择文件" multiple="multiple"/>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:file id="fileUpload3" name="uploads" label="选择文件" multiple="multiple"/>
					</powersi:editorlayout-row>
				</powersi:editorlayout>
				<div class="divButton">
					<powersi:button id="btnUploadFiles" key="button_upload"></powersi:button>
				</div>
			</powersi:panelbox>
		</div>
		<div class="col-6">
			<powersi:panelbox key="panel_query" title="大数据提交">
				<powersi:form id="form3" disabled="true">
					<powersi:editorlayout cols="25%,75%" id="divFiles">
						<powersi:editorlayout-row>
							<powersi:textarea id="bigData" name="data" label="大数据" cssStyle="height: 92px;"
							placeholder="Tomcat缺省最大POST大小为2M，请修改server.xml的Connector项，添加maxPostSize=\"0\";Weblogic缺省无限制，无需修改"
							></powersi:textarea>
						</powersi:editorlayout-row>
					</powersi:editorlayout>
					<div class="divButton">
						<powersi:submit id="btnBigData" key="button_upload" onclick="ajax3()"></powersi:submit>
					</div>
				</powersi:form>
			</powersi:panelbox>
		</div>
	</div><!-- end of row -->
<powersi:errors />
<script type="text/javascript">
function ajax1(){
	if(!checkForm('#form1')){
		return;
	}
	
	var param = {};
	var url = $.trim($('#callUrl1').val());
	if(url && url.length > 0){
		param.url = url;
		//公司框架后台自动识别jsonp参数，其他系统请根据要求设置
		param.jsonp = 'jsonp';
	} else{
		param.url = rootPath;
	}
	param.url += '/sample/Sample!checkLoginUserExists.action';

	postAjax(param,  $('#form1').serialize(), function(json){
		$("#result1").val(powersi.tostring(json));
		
		if(!checkResult(json)){
			return;
		} 
	});
	
	return;
}

function ajax2(){
	if(!checkForm('#form2')){
		return;
	}
	
	var json = postSync(rootPath + '/sample/Sample!checkLoginUserExists.action', $('#form2').serialize());
	$("#result2").val(powersi.tostring(json));

	if(!checkResult(json)){
		return;
	}
}

function ajax3(){
	if(!checkForm('#form3')){
		return;
	}
	
	//尽量不要使用jquery，性能差，使用原生api
	var data = S('bigData').value;
	alert(powersi.length(data));
	
	postAjax(rootPath + '/sample/Sample!bigSubmit.action',  {"data": data}, function(json){
		popupSuccess(powersi.tostring(json));
	});
}

function download() {
	postDownload(rootPath + '/sample/Sample!download.action', form2json('#dwnForm'));
}


$(function(){
	//初始化文件上传
	$('#btnUpload').fileupload({
        url: rootPath + '/sample/Sample!upload.action',
        dataType: 'json',
        autoUpload: false,
        replaceFileInput: false,
        done: function (e, data) {
        	if(data.result.errortype == "0"){
        		//成功后重置input file
        		var fileUpload = $('#fileUpload');
        		fileUpload[0].outerHTML = fileUpload[0].outerHTML;
        	}
        	
            showPageError(data.result);
        },
        fail: function (e, data) {
            showPageError(data.message);
        }
    }).click(function(){
    	try{
    		var fileName = $('#fileUpload').val();
    		if(powersi.isempty(fileName)){
    			alert("请选择文件");
    			return;
    		}
    		
    		showRunning(true);
    		var fileUpload = $('#fileUpload');
    		$(this).fileupload('send', {
    	        fileInput: fileUpload,
    	        formData: {
    	        	'a': '参数1',
    	        	'b': '参数2'
    	        }
    	    }).complete(function (result, textStatus, jqXHR) {showRunning(false);});
    	} catch(ex){
    		showRunning(false);
    		alert(ex.message);
    	}
    });
	
	//初始化批量上传
	$('#btnUploadFiles').fileupload({
		url: rootPath + '/sample/Sample!upload.action',
        dataType: 'json',
        autoUpload: false,
        replaceFileInput: false,
        done: function (e, data) {
        	if(data.result.errortype == "0"){
        		//成功后重置input file
        		var fileUpload = $('#divFiles');
        		fileUpload[0].outerHTML = fileUpload[0].outerHTML;
        	}
        	
            showPageError(data.result);
        },
        fail: function (e, data) {
            showPageError(data.message);
        }
	}).click(function(){
		try{
			showRunning(true);
			var filesList = $('#divFiles .file');
			$(this).fileupload('send', {
				fileInput: filesList
			}).complete(function (result, textStatus, jqXHR) {showRunning(false);});
		} catch(ex){
			showRunning(false);
			alert(ex.message);
		}
	});
});
</script>
</body>
</powersi:html>