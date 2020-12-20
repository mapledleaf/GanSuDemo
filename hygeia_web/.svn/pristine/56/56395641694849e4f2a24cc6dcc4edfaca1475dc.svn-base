<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<powersi:html>
<head>
<powersi:head title="二维码生成" />
<script src="${strutsPath}/js/plugins/qrcode/jquery.qrcode.min.js" type="text/javascript"></script>
<style type="text/css">
body {
    margin: 0;
    padding: 0;
    text-align: center;
    background: url(${strutsPath}/js/plugins/qrcode/img/back.png) repeat;
}

.banner {
    display: block;
    text-decoration: none;
    color: rgb(29, 119, 194);
    padding: 10px 0 0;
    text-align: center;
    font-size: 1.1em;
}

#container {
    display: inline-block;
    margin: 50px auto;
    box-shadow: 0 0 16px rgba(0,0,0,0.5);
}

#container > * {
    display: block;
}

.control {
    position: absolute;
    background-color: #f8f8f8;
    top: 0;
    width: 200px;
    box-shadow: 0 0 32px rgba(0,0,0,0.5);
    overflow: hidden;
    text-align: left;
}

.control.left {
    left: 0;
}

.control.right {
    right: 0;
}

hr {
    margin: 10px 0 0;
    padding: 0;
    border: none;
    height: 1px;
    background-color: rgba(0,0,0,0.1);
}

label {
    font-size: 10px;
    color: #999;
    padding: 4px 4px 0 4px;
}

#download {
    text-align: center;
    font-weight: bold;
    text-decoration: none;
    display: block;
    color: #555;
    background-color: #ddd;
    margin: 4px;
    padding: 8px 0;
    border: 1px solid #ddd;
    cursor: pointer;
}

input, textarea, select {
    display: block;
    background-color: #fff;
    margin: 2px 5px;
    padding: 0;
    border: 1px solid #ddd;
    width: 190px !important;
    height: 22px;
}

input[type='range'] {
    -webkit-appearance: none;
    cursor: pointer;
}

input::-webkit-slider-thumb {
    -webkit-appearance: none;
    width: 16px;
    height: 16px;
    border-radius: 3px;
    background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #aaa), color-stop(1, #aaa));
}

#text {
    height: 112px;
}

#options {
	height: 90px;
}

#img-buffer {
    display: none;
}

#tip {
	position: absolute;
	bottom: 0;
	left: 0;
	width: 100%;
	text-align: center;
	font-size: 1.1em;
	color: #d44950;
}
#tip p {
	margin: 5px auto;
}
</style>
</head>
<body>
<div id="container">
	<canvas width="300" height="300">
    </canvas>
</div>
<div id="tip">
	<p>
		$('#qrcode').empty().qrcode(options);
	</p>
	<p title="IE不支持颜色选择器，IE9不支持Image文件选择，IE8只支持Div渲染且不支持Label显示">
		推荐使用Chrome，Firefox，Safari浏览器
	</p>
</div>
<div class="control left">
    <label for="render">
        RENDER MODE
    </label>
    <select id="render">
        <option value="canvas">
            canvas
        </option>
        <option value="image" selected="selected">
            image
        </option>
        <option value="div">
            div
        </option>
    </select>
    <hr>
    <label for="size">
        SIZE: 300px
    </label>
    <input id="size" type="range" value="300" min="100" max="1000" step="50">
    <label for="fill">
        FILL
    </label>
    <input id="fill" type="color" value="#000000">
    <label for="background">
        BACKGROUND
    </label>
    <input id="background" type="color" value="#ffffff">
    <label for="minversion">
        MIN VERSION: 6
    </label>
    <input id="minversion" type="range" value="6" min="1" max="10" step="1">
    <label for="eclevel">
        ERROR CORRECTION LEVEL
    </label>
    <select id="eclevel">
        <option value="L">
            L - Low (7%)
        </option>
        <option value="M">
            M - Medium (15%)
        </option>
        <option value="Q">
            Q - Quartile (25%)
        </option>
        <option value="H" selected="selected">
            H - High (30%)
        </option>
    </select>
    <label for="quiet">
        QUIET ZONE: 1 modules
    </label>
    <input id="quiet" type="range" value="1" min="0" max="4" step="1">
    <label for="radius">
        CORNER RADIUS: 50%
    </label>
    <input id="radius" type="range" value="50" min="0" max="50" step="10">
    <label for="text">
        TEXT
    </label>
    <textarea id="text">http://www.powersi.com.cn</textarea>
</div>
<div class="control right">
    <label for="mode">
        MODE
    </label>
    <select id="mode">
        <option value="0">
            0 - Normal
        </option>
        <option value="1">
            1 - Label-Strip
        </option>
        <option value="2">
            2 - Label-Box
        </option>
        <option value="3">
            3 - Image-Strip
        </option>
        <option value="4" selected="selected">
            4 - Image-Box
        </option>
    </select>
    <hr>
    <label for="msize">
        SIZE: 8%
    </label>
    <input id="msize" type="range" value="8" min="0" max="40" step="1">
    <label for="mposx">
        POS X: 50%
    </label>
    <input id="mposx" type="range" value="50" min="0" max="100" step="1">
    <label for="mposy">
        POS Y: 50%
    </label>
    <input id="mposy" type="range" value="50" min="0" max="100" step="1">
    <hr>
    <label for="label">
        LABEL
    </label>
    <input id="label" type="text" value="PowerSI">
    <label for="font">
        FONT NAME
    </label>
    <input id="font" type="text" value="Helvetica">
    <label for="fontcolor">
        FONT COLOR
    </label>
    <input id="fontcolor" type="color" value="#ff892a">
    <hr>
    <label for="image">
        IMAGE
    </label>
    <input id="image" type="file">
    <label for="options">
        OPTIONS
    </label>
    <textarea id="options"></textarea>
</div>
<img id="img-buffer" src="${strutsPath}/js/plugins/qrcode/img/logo.png">
<script type="text/javascript">
(function () {
	'use strict';
	var $ = jQuery;
	var guiValuePairs = [
	        ['size', 'px'],
	        ['minversion', ''],
	        ['quiet', ' modules'],
	        ['radius', '%'],
	        ['msize', '%'],
	        ['mposx', '%'],
	        ['mposy', '%']
	    ];

	function updateGui() {
	    $.each(guiValuePairs, function (idx, pair) {

	        var $label = $('label[for="' + pair[0] + '"]');
	        $label.text($label.text().replace(/:.*/, ': ' + $('#' + pair[0]).val() + pair[1]));
	    });
	    
	    var size = parseInt($('#size').val());
	    if(size >= 600){
	    	$('#tip').hide();
	    } else {
	    	$('#tip').show();
	    }
	}

	function updateQrCode() {
	    var options = {
	            render: $('#render').val(),
	            ecLevel: $('#eclevel').val(),
	            minVersion: parseInt($('#minversion').val(), 10),

	            fill: $('#fill').val(),
	            background: $('#background').val(),
	            // fill: $('#img-buffer')[0],

	            text: $('#text').val(),
	            size: parseInt($('#size').val(), 10),
	            radius: parseInt($('#radius').val(), 10) * 0.01,
	            quiet: parseInt($('#quiet').val(), 10),

	            mode: parseInt($('#mode').val(), 10),

	            mSize: parseInt($('#msize').val(), 10) * 0.01,
	            mPosX: parseInt($('#mposx').val(), 10) * 0.01,
	            mPosY: parseInt($('#mposy').val(), 10) * 0.01,

	            label: $('#label').val(),
	            fontname: $('#font').val(),
	            fontcolor: $('#fontcolor').val(),

	            image: $('#img-buffer')[0]
	        };

	    $('#container').empty().qrcode(options);
	    
	    options["image"] = null;
	    if($.isFunction(JSON.stringify))
	    	$('#options').val(JSON.stringify(options));
	}

	function update() {
	    updateGui();
	    updateQrCode();
	}

	function onImageInput() {
	    var input = $('#image')[0];
	    if (input.files && input.files[0]) {
	        var reader = new FileReader();
	        reader.onload = function (event) {
	            $('#img-buffer').attr('src', event.target.result);
	            $('#mode').val('4');
	            setTimeout(update, 250);
	        };
	        reader.readAsDataURL(input.files[0]);
	    }
	}

	function download() {
	    $('#download').attr('href', $('#container canvas')[0].toDataURL('image/png'));
	}


	$(function () {
	    $('#download').on('click', download);
	    $('#image').on('change', onImageInput);
	    $('input, textarea, select').on('input change', update);
	    $(window).load(update);
	    update();
	});

	}());

</script>
</body>
</powersi:html>