<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="测试打开菜单" target="_self"/>
<style type="text/css">
.ui-progressbar-value { background-image: url(${rootPath }/resource/images/pbar-ani.gif); }
.ui-dialog .ui-dialog-titlebar-close:before,.ui-jqdialog .ui-dialog-titlebar-close:before,.ui-dialog .ui-jqdialog-titlebar-close:before,.ui-jqdialog .ui-jqdialog-titlebar-close:before {
	content:"\f00d";
	display:inline;
	font-family:FontAwesome;
	font-size:16px
}
</style>
<script type="text/javascript">
	/*url必须与菜单维护的完全一样*/
	function open1() {
		//无参数 无刷新
		openMenu('/manager/QueryLoginLog.action');
	}
	function open2() {
		//带参数(使用js对象) 无刷新
		var param = {
			beginDate : '2011-10-01 00:00:00',
			endDate : '2011-10-25 00:00:00',
			loginUser : 'admin',
			userName : '系统管理员'
		};
		openMenu('/manager/QueryLoginLog.action', param);
	}
	function open3() {
		//有参数(使用字符串) 有刷新
		openMenu('/manager/QueryLoginLog.action', 'loginUser=csx&userName=长沙县', {
			reload : true
		});
	}
	function open4() {
		//无参数 有刷新
		openMenu('/manager/QueryLoginLog.action', null, {
			reload : true
		});
	}

	function close1() {
		//关闭指定的菜单
		closeMenu('/manager/QueryLoginLog.action');
	}

	function close2() {
		//关闭自己
		closeMenu();
	}

	function popup1() {
		//测试弹出窗口
		openDialog(rootPath + "/pages/sample/openmenu.jsp", getPageHeight(), getPageWidth());
	}
	
	function popup2() {
		openDialog(rootPath + "/pages/sample/webui/datagrid-detail.jsp", getPageHeight(), getPageWidth());
	}
	
	function downloadFile(fileName){
		try{
			postDownload(rootPath + '/manager/ViewSystemLog.action', {
				'fileName': fileName
				});
		}catch(ex){
			alert(ex.message);
		}
	}
	
	function alertPageError(frmId){
		showRunning(false);
		
		var errorInfo = $(document.getElementById(frmId).contentWindow.document.body).find('.error').text();
		if(errorInfo != null && errorInfo.length > 0){
			alert($.trim(errorInfo));
		}
	}
	
	function refreshMenu1(){
		try{
			if(isActivex()){
				 window.external.refreshMenu("WELCOME", "", "0");
			}
		} catch(e){}
		
	}
	
	function refreshMenu2(){
		try{
			if(isActivex()){
				 window.external.refreshMenu("/manager/QueryLoginLog.action", "", "0");
			}
		} catch(e){}
	}
</script>
</head>
<body>
	<powersi:groupbox title="打开菜单">
		<input type="button" id="btn1" value="打开菜单1" class="button"
			onclick="open1()" />
		<input type="button" id="btn2" value="打开菜单2" class="button"
			onclick="open2()" />
		<input type="button" id="btn3" value="打开菜单3" class="button"
			onclick="open3()" />
		<input type="button" id="btn4" value="打开菜单4" class="button"
			onclick="open4()" />
	</powersi:groupbox>
	
	<powersi:groupbox title="刷新菜单">
		<input type="button" id="btn1" value="刷新菜单1" class="button"
			onclick="refreshMenu1()" />
		<input type="button" id="btn2" value="刷新菜单2" class="button"
			onclick="refreshMenu2()" />
	</powersi:groupbox>

	<powersi:groupbox title="关闭菜单">
		<input type="button" id="btn5" value="关闭菜单1" class="button"
			onclick="close1()" />
		<input type="button" id="btn6" value="关闭菜单2" class="button"
			onclick="close2()" />
	</powersi:groupbox>
	
	<powersi:groupbox title="弹出窗口">
		<input type="button" id="btn7" value="弹出窗口1" class="button"
			onclick="popup1()" />
			<input type="button" id="btn8" value="弹出窗口2" class="button"
			onclick="popup2()" />
	</powersi:groupbox>
	
	<powersi:groupbox title="POST下载">
		<input type="button" id="btDownload1" value="下 载1" onclick="downloadFile('jdbclogger.log')" class="button" />
		<input type="button" id="btDownload2" value="下 载2" onclick="downloadFile('baseaction.log')" class="button" />
		<input type="button" id="btDownloa3" value="下 载3" onclick="downloadFile('log.log')" class="button" />
	</powersi:groupbox>
	
	<powersi:errors />
</body>
</powersi:html>