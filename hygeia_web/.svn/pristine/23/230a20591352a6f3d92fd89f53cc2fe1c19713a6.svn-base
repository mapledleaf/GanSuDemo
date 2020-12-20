<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
	<head>
		<base href="<%=basePath%>">

		<title>打开功能错误</title>

		<style>
			.box{
				width:500px;
				height:250px;
				border:1px solid #ccc; 
				margin:100px auto;
				font-family: "Microsoft Yahei";
				font-size: 14px;
				border-radius: 5px;
				background:#fafafa;
				-webkit-box-shadow: #CCC 1px 2px 5px 0px;
				box-shadow:2px 2px 5px #ccc;/*opera或ie9*/
				filter: progid:DXImageTransform.Microsoft.Shadow(color='#cccccc', Direction=135, Strength=5);
			}
			.box .error{
				width:100%;
				text-align: center;
				height:50px;
				color:red;
				font-size:28px;
				font-family: "黑体";
				margin-top:20px;
			}
			.box span{
				line-height:30px;
				marign:0 0 0 100px;
				display:block;
				padding-left:100px;
			}
			.box ul{
				line-height:30px;
				margin-top:10px;
			}
			.box ul li{
				margin:0 0 0 100px;
			}
			.box a{
				margin:20px 0 0 20px;
				font-size: 14px;
			}
			.box a:hover{
				color:#0080c0;
			}
		</style>
	</head>

	<body>
		<div class="box">
			<div class="error">ERROR!</div>
			<span>抱歉，本医院没有被授权操作该菜单。</span>
		</div>
	</body>
</html>
