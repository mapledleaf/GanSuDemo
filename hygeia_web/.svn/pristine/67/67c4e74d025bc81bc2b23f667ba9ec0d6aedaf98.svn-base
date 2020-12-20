<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="身份证读卡测试" />
<script type="text/javascript" src="${rootPath }/resource/js/idcard.js" charset="utf-8"></script>
<style type="text/css">
#previewPhoto {
	filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);
	text-align: center;
	width: 102px;
	height: 126px;
	border: solid 1px #ddd;
	font-size: 12px;
	margin-top: 5px;
}
</style>
<script type="text/javascript">
	
</script>
<script type="text/javascript">
	function readIdcard() {
		try {
			resetIdcard();

			var cardinfo = readIdcardInfo();
			if (cardinfo == undefined || cardinfo == null) {
				return;
			}

			if (cardinfo.errortype !== "0") {
				alert(cardinfo.errormsg);
				return;
			}

			document.getElementById("txtContext").innerText = cardinfo.data;

			var obj, k;
			for (k in cardinfo) {
				obj = document.getElementById(k);
				if (obj) {
					obj.value = cardinfo[k];
				}
			}

			var timestamp = new Date().getTime();
			if(cardinfo["photo"].length > 0){
				document.getElementById("photoimg").src = "data:image/gif;base64," + cardinfo["photo"];	
			} else{
				document.getElementById("photoimg").src = cardinfo.photourl
				+ "?timestamp=" + timestamp;
			}
			
			$("#previewPhoto").css("filter", "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=\'scale\',src=\'"
					+ cardinfo.photourl + "?timestamp=" + timestamp + "\')");
			//document.getElementById("previewPhoto").filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = cardinfo.photourl + "?timestamp=" + timestamp;
			document.getElementById("previewText").innerText = "";
			
			alert("身份证读取成功");
		} catch (ex) {
			alert(ex.message);
		}
	}

	function resetIdcard() {
		document.getElementById("form1").reset();
		document.getElementById("photoimg").src = "";

		$("#previewPhoto").css("filter", "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=\'scale\',src=\'\')");

		$("#previewText").text("相片");
	}

	function readSAMID() {
		var cardinfo = readSAMIDInfo();
		if (cardinfo == undefined || cardinfo == null) {
			return;
		}

		if (cardinfo.errortype !== "0") {
			alert(cardinfo.errormsg);
			return;
		}

		alert(cardinfo.data);
	}
	
	function readRepeat() {
		var d = new Date();
		var c = 10;
		var t = 0;
		var cardinfo;
		for(var i = 0; i < c; i ++){
			cardinfo = readIdcardInfo();
			if(cardinfo.errortype != "0"){
				t += 1;
			}
		}
		alert("重复读卡测试(重复" + c + "次 失败" + t + "次 " + (new Date().getTime() - d.getTime()) + "毫秒)");
	}
</script>
</head>
<body class="page">
	<div class="divCenter">
		<form id="form1">
		    <input type="button" id="btnRead" value="读卡" onclick="readIdcard()" class="button" />
		    <input type="button" id="btnSam" value="读模块序列号" onclick="readSAMID()" class="button" />   
		    <input type="button" id="btnReset" value="清屏" onclick="resetIdcard()" class="button" />
		    <input type="button" id="btnRepeat" value="重复读卡" onclick="readRepeat()" class="button" />   
		    <div class="space"></div>
		    <powersi:editorlayout cols="4">
		    	<powersi:editorlayout-row>
					 <powersi:textarea rows="8" id="txtContext" name="txtContext" colspan="3"></powersi:textarea>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:editorlayout-label>
						相片(img)
					</powersi:editorlayout-label>
					<powersi:editorlayout-input>
						<img id="photoimg" width="102" height="126" alt="相片" style="border: none" />
					</powersi:editorlayout-input>
					<powersi:editorlayout-label>
						相片(url)
					</powersi:editorlayout-label>
					<powersi:editorlayout-input>
						<div id="previewPhoto">
		        			<p id="previewText">相片</p>
		    			</div>
					</powersi:editorlayout-input>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="name" label="name" name="name" />
					<powersi:textfield id="gender" label="gender" name="gender" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="folk" label="folk" name="folk" />
					<powersi:textfield id="birthday" label="birthday" name="birthday" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="code" label="code" name="code" />
					<powersi:textfield id="address" label="address" name="address" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="agency" label="agency" name="agency" />
					<powersi:textfield id="photo" label="photo" name="photo" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="expirestart" label="expirestart" name="expirestart" />
					<powersi:textfield id="expireend" label="expireend" name="expireend" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
	   </form>
	</div>
</body>
</powersi:html>