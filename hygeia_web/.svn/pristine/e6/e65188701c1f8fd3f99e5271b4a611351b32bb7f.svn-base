<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	String param = request.getParameter("param");
	if(param == null){
		param = "";
	}
%>
<powersi:html>
<head>
<powersi:head title="弹窗样例" target="_self"/>
</head>
<body class="page">
	<div class="tabbable">
		<ul class="nav nav-tabs" id="tabs">
			<li class="active">
				<a data-toggle="tab" href="#tab-pane1" id="tab1">
					<i class="icon-reorder red"></i>
					菜单
				</a>
			</li>

			<li>
				<a data-toggle="tab" href="#tab-pane2" id="tab2">
					<i class=" icon-external-link green"></i>
					对话框
				</a>
			</li>

			<li>
				<a data-toggle="tab" href="#tab-pane3" id="tab3">
					<i class="icon-external-link-sign yellow"></i>
					弹窗
				</a>
			</li>
		</ul>
	</div>
	<div class="tab-content">
		<div id="tab-pane1" class="tab-pane active">
			<powersi:panelbox title="静态菜单（在菜单管理中维护）" >
				<input type="button" id="btn1" value="打开菜单(无参数 无刷新)" class="button"
					onclick="open1()" />
				<input type="button" id="btn2" value="打开菜单(带参数 无刷新)" class="button"
					onclick="open2()" />
				<input type="button" id="btn3" value="打开菜单(有参数 有刷新)" class="button"
					onclick="open3()" />
				<input type="button" id="btn4" value="打开菜单(无参数 有刷新)" class="button"
					onclick="open4()" />
			</powersi:panelbox>
			
			<powersi:panelbox title="动态菜单(公司浏览器2.0.1.6以上版本支持）" >
				<input type="button" id="btn5" value="动态菜单(一个实例)" class="btn btn-success"
					onclick="open5()" />
				<input type="button" id="btn5" value="动态菜单(多个实例 )" class="btn btn-success"
					onclick="open6()" />
			</powersi:panelbox>
			
			<powersi:panelbox title="刷新菜单">
				<input type="button" id="btn11" value="刷新欢迎页(无参数)" class="button"
					onclick="refreshMenu1()" />
				<input type="button" id="btn12" value="刷新菜单(带参数)" class="button"
					onclick="refreshMenu2()" />
			</powersi:panelbox>
			
			<powersi:panelbox title="关闭菜单">
				<input type="button" id="btn5" value="关闭菜单(指定菜单)" class="button"
					onclick="close1()" />
				<input type="button" id="btn6" value="关闭菜单(当前菜单)" class="button"
					onclick="close2()" />
			</powersi:panelbox>
		</div>

		<div id="tab-pane2" class="tab-pane">
			<powersi:panelbox>
				<powersi:panelbox-title>
					<span class="textWarning" data-action="toggle" style="cursor:pointer;"><i class="icon-info-sign"></i>注意事项</span>
				</powersi:panelbox-title>
				<ol class="textSuccess text110" id="olHelp">
					<li>如果在弹出对话框(openDialog)中调用菜单函数，必须把window.top作为参数传递给对话框然后调用setTopWindow函数</li>
					<li>为了保证各种弹出对话框函数之间兼容，请使用以下函数操作
						<ul class="textError">
							<li>获取参数 getDialogParam </li>
							<li>设置返回 setDialogReturn </li>
							<li>关闭弹窗 closeDialog </li>
							<li>使用回调函数callback实现对话框返回参数处理</li>
						</ul>
					</li>
					<li>popupDialog支持以下方式获取弹窗内容对象
						<ul class="textError">
							<li>获取iframe的jquery对象 dialog.getFrame </li>
							<li>获取iframw的window对象 dialog.getFrameWindow </li>
							<li>获取iframw的document对象 dialog.getFrameDocument </li>
							<li>查找iframe的#id对象 $('#id', dialog.getFrameDocument())</li>
							<li>调用iframe的func函数 dialog.getFrameWindow().func()</li>
						</ul>
					</li>
				</ol>
			</powersi:panelbox>
			
			<powersi:panelbox title="弹出对话框">
				<input type="button" id="btn7" value="弹出对话框(带参数)" class="button" onclick="dialog1()" />
				<input type="button" id="btn8" value="弹出对话框(无参数)" class="button" onclick="dialog2()" />
				<input type="button" id="btn9" value="弹出对话框(最大化)" class="button" onclick="dialog3()" />
			</powersi:panelbox>
			
			<powersi:panelbox title="返回值处理" iconClass="icon-external-link">
				<input type="button" id="btn41" value="openDialog" class="button"
					onclick="dlgCallback1()" />
				<input type="button" id="btn42" value="openDialogWithParam" class="button"
					onclick="dlgCallback2()" />
				<input type="button" id="btn43" value="popupDialog" class="button"
					onclick="dlgCallback3()" />
				<input type="button" id="btn44" value="popupDialogWithParam" class="button"
					onclick="dlgCallback4()" />
				<input type="button" id="btn45" value="topDialog" class="button"
					onclick="dlgCallback5()" />
				<input type="button" id="btn46" value="topDialogWithParam" class="button"
					onclick="dlgCallback6()" />
			</powersi:panelbox>
		</div>

		<div id="tab-pane3" class="tab-pane">
			<powersi:panelbox title="提示窗口" iconClass="icon-star-empty">
				<powersi:button type="button" id="btn21" value="默认提示" buttonIcon="icon-warning-sign" cssClass="btn btn-warning"
					onclick="popupAlert('目前，创智和宇的民生行业软件产品和服务覆盖了我国约1亿人口，成为我国最具竞争力和影响力的民生信息服务提供商之一。为政府民生工程提供最有价值的信息化服务是创智和宇的企业使命，成为民生信息化领域的常青企业是创智和宇的企业愿景。秉承“自强不息，厚德载物，健康生活，快乐工作”的核心价值观，创智和宇正以长期永续的经营战略，领先的人才与技术，专业的产品与服务，为我国民生信息化事业快速和长远发展提供稳定可靠的技术支撑。')" />
				<powersi:button type="button" id="btn22" value="成功提示" buttonIcon="icon-ok-sign" cssClass="btn btn-success"
					onclick="popupSuccess('成功信息')" />
				<powersi:button type="button" id="btn24" value="错误提示" buttonIcon="icon-remove-sign" cssClass="btn btn-danger"
					onclick="popupError('错误信息')" />
				<powersi:button type="button" id="btn23" value="警告提示" buttonIcon="icon-warning-sign" cssClass="btn btn-warning"
					onclick="popupWarn('警告信息')" />
				<powersi:button type="button" id="btn25" value="信息提示" buttonIcon="icon-info-sign" cssClass="btn btn-info"
					onclick="popupInfo('提示信息')" />
				<powersi:button type="button" id="btn26" value="疑问提示" buttonIcon="icon-question-sign" cssClass="btn btn-primary"
					onclick="popupQuestion('疑问信息')" />
				<powersi:button type="button" id="btn27" value="无图标提示" buttonIcon="icon-circle-blank" cssClass="btn btn-default"
					onclick="popupNone('没有图标')" />
				<powersi:button type="button" id="btn28" value="确认提示" buttonIcon="icon-question" cssClass="btn btn-default"
					onclick="confirm1()" />
			</powersi:panelbox>
			
			<powersi:panelbox title="提示窗口(5秒自动关闭)" iconClass="icon-star-empty">
				<powersi:button type="button" id="btn21" value="默认提示" buttonIcon="icon-warning-sign" cssClass="btn btn-warning"
					onclick="popupAlert('弹窗信息', 600000)" />
				<powersi:button type="button" id="btn22" value="成功提示" buttonIcon="icon-ok-sign" cssClass="btn btn-success"
					onclick="popupSuccess('成功信息', 5000)" />
				<powersi:button type="button" id="btn24" value="错误提示" buttonIcon="icon-remove-sign" cssClass="btn btn-danger"
					onclick="popupError('错误信息', 5000)" />
				<powersi:button type="button" id="btn23" value="警告提示" buttonIcon="icon-warning-sign" cssClass="btn btn-warning"
					onclick="popupWarn('警告信息', 5000)" />
				<powersi:button type="button" id="btn25" value="信息提示" buttonIcon="icon-info-sign" cssClass="btn btn-info"
					onclick="popupInfo('提示信息', 5000)" />
				<powersi:button type="button" id="btn26" value="疑问提示" buttonIcon="icon-question-sign" cssClass="btn btn-primary"
					onclick="popupQuestion('疑问信息', 5000)" />
				<powersi:button type="button" id="btn27" value="无图标提示" buttonIcon="icon-circle-blank" cssClass="btn btn-default"
					onclick="popupNone('没有图标', 5000)" />
				<powersi:button type="button" id="btn30" value="消息提示" buttonIcon="icon-ok-sign" cssClass="btn btn-danger"
					onclick="popupMessage('消息提示')" />
				<powersi:button type="button" id="btn29" value="3秒后自动关闭" buttonIcon="icon-terminal" cssClass="btn btn-link"
					onclick="popupClose()" />
			</powersi:panelbox>
			
			<powersi:panelbox title="页面弹窗" iconClass="icon-star-half-empty">
				<input type="button" id="btn31" value="弹出DIV(缺省模式)" class="button"
					onclick="popup1()" />
				<input type="button" id="btn32" value="弹出DIV(自定义)" class="button"
					onclick="popup2()" />
				<input type="button" id="btn33" value="弹出URL(无参数)" class="button"
					onclick="popup3()" />
				<input type="button" id="btn34" value="弹出URL(带参数)" class="button"
					onclick="popup4()" />
				<input type="button" id="btn35" value="弹出DIV(自定义按钮)" class="button"
					onclick="popup5()" />
			</powersi:panelbox>
			
			<powersi:panelbox title="顶层弹窗" iconClass="icon-star">
				<input type="button" id="btn41" value="顶层弹窗(无参数)" class="button"
					onclick="popup11()" />
				<input type="button" id="btn42" value="顶层弹窗(最大化 带参数)" class="button"
					onclick="popup12()" />
				<input type="button" id="btn43" value="页面弹窗返回参数" class="button"
					onclick="popup13()" />
				<input type="button" id="btn44" value="顶层弹窗返回参数" class="button"
					onclick="popup14()" />
				<input type="button" id="btn45" value="顶层弹窗(自定义按钮)" class="button"
					onclick="popup15()" />
			</powersi:panelbox>
		</div>
	</div>
	
	<!-- 隐藏区 -->
	<div class="hidden">
		<div id="dlgGrid1">
			<powersi:datagrid id="grid1" frozen="false" url="${rootPath }/sample/Sample!queryCodetable.action" height="350"
				showExportExcel="true" exportFileName="'业务代码'">
				<powersi:datagrid-column display="代码类型" name="code_type" width="30%" minWidth="100" />
				<powersi:datagrid-column display="代码名称" name="code_name" width="30%" minWidth="100" />
				<powersi:datagrid-column display="代码SQL" name="code_sql" width="40%" minWidth="120" align="left"/>
			</powersi:datagrid>
			<div class="space-y"></div>
			<div class="divButton">
				<powersi:button id="btnDlgOk" key="button_ok" onclick="closePopup()"></powersi:button>
				<powersi:button id="btnDlgCancel" key="button_cancel" onclick="closePopup()"></powersi:button>
				<powersi:button id="btnDlgRunning" key="button_save" onclick="testRunning()"></powersi:button>
			</div>
		</div>
		
		<div id="dlgGrid2">
			<powersi:datagrid id="grid2" frozen="false" url="${rootPath }/sample/Sample!queryCodetable.action" height="350"
				showExportExcel="true" exportFileName="'业务代码'">
				<powersi:datagrid-column display="代码类型" name="code_type" width="30%" minWidth="100" />
				<powersi:datagrid-column display="代码名称" name="code_name" width="30%" minWidth="100" />
				<powersi:datagrid-column display="代码SQL" name="code_sql" width="40%" minWidth="120" align="left"/>
			</powersi:datagrid>
			<div class="space-y"></div>
			<div class="divButton">
				<powersi:button id="btnDlgOk" key="button_ok" onclick="closePopup()"></powersi:button>
				<powersi:button id="btnDlgCancel" key="button_cancel" onclick="closePopup()"></powersi:button>
			</div>
		</div>
		
		<div id="dlgGrid3">
			<powersi:datagrid id="grid3" frozen="false" url="${rootPath }/sample/Sample!queryCodetable.action" height="350"
				showExportExcel="true" exportFileName="'业务代码'">
				<powersi:datagrid-column display="代码类型" name="code_type" width="30%" minWidth="100" />
				<powersi:datagrid-column display="代码名称" name="code_name" width="30%" minWidth="100" />
				<powersi:datagrid-column display="代码SQL" name="code_sql" width="40%" minWidth="120" align="left"/>
			</powersi:datagrid>
		</div>
	</div>
	<powersi:errors />
	<script type="text/javascript">
	$(function(){
		try{
			//对话框需要获取主窗口句柄
			var param = getDialogParam();
			
			if(!param){
				param = '<%=param%>';
				if(param == ''){
					param = null;
				}
			}
			
			if(param){
				if($.isWindow(param)){
					//如果参数是窗口则认为是顶级窗口
					setTopWindow(param);	
				} else {
					//在初始化时设置对话框返回参数
					if(param == 'param1'){
						setDialogReturn('页面弹出对话框返回值，请使用setDialogReturn设置');	
					} else if(param == 'param2') {
						setDialogReturn('顶层弹出对话框返回值，请使用setDialogReturn设置');
					} else {
						setDialogReturn(powersi.tostring(param));
					}
				}
			}
		} catch(ex){
			alert(ex.message);
		}
	});
	
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
		openMenu('/manager/QueryLoginLog.action', 'beginDate=2018-05-01 00:00:00&endDate=2018-12-30 00:00:00&loginUser=csx&userName=长沙县', {
			reload : true
		});
	}
	function open4() {
		//无参数 有刷新
		openMenu('/manager/QueryLoginLog.action', null, {
			reload : true
		});
	}
	
	function open5() {
		//动态菜单(一个实例)
		openMenu('/pages/sample/webui/datagrid.jsp');
	}
	
	function open6() {
		//动态菜单(多个实例，使用时间戳保证每次强制新开窗口
		var tm = (new Date()).getTime();
		openMenu('/pages/sample/webui/popup.jsp?tm=' + tm, null, {title: '动态菜单' + tm});
	}

	function close1() {
		//关闭指定的菜单
		closeMenu('/manager/QueryLoginLog.action');
	}

	function close2() {
		//关闭自己
		closeMenu();
	}

	function dialog1() {
		openDialogWithParam(rootPath + "/pages/sample/webui/popup.jsp", getTopWindow(), getPageHeight(), getPageWidth());
	}
	
	function dialog2() {
		openDialog(rootPath + "/pages/sample/webui/datagrid-detail.jsp", 600, getPageWidth());
	}
	
	function dialog3() {
		openDialog(rootPath + "/pages/sample/webui/datagrid-detail.jsp");
	}
	
	function refreshMenu1(){
		//刷新欢迎页 无参数
		refreshMenu("WELCOME");
	}
	
	function refreshMenu2(){
		//刷新菜单 带参数
		refreshMenu("/manager/QueryLoginLog.action", "beginDate=2014-05-01 00:00:00&endDate=2099-12-30 00:00:00");
	}
	
	function confirm1() {
		popupConfirm('您确认继续吗？', '提示', function (yes) { 
			popupNone('是否选择了yes：' + yes); 
		});
	}
	
	var dlgType = 1;
	var dlg = null;
	function popup1(){
		if(dlg){
			dlg.show();
		} else {
			dlg = popupDiv("#dlgGrid1", '弹出div1', 600);
		}
		
		dlgType = 1;
	}
	
	function closePopup(){
		if(dlgType == 1){
			if(dlg){
				dlg.hide();
			}	
		} else if(dlgType == 2){
			if(dlg2){
				dlg2.hide();
			}
		}
	}
	
	var dlg2 = null;
	function popup2() {
		dlgType = 2;
		
		if(dlg2){
			dlg2.show();
		} else {
			dlg2 = popupDiv("#dlgGrid2", '弹出div2', 600, {
				showMax: true
			});	
		}
	}
	
	function popup3() {
		popupDialog(rootPath + "/pages/sample/webui/popup.jsp", 600, 800);
	}
	
	function popup4() {
		popupDialogWithParam(rootPath + "/manager/QueryLoginLog.action", "beginDate=2014-05-01 00:00:00&endDate=2099-12-30 00:00:00", 600, 800);
	}
	
	var dlg5 = null;
	function popup5() {
		if(dlg5){
			dlg5.show();
		} else {
			dlg5 = popupDiv("#dlgGrid3", '自定义弹出按钮', 600, {
				showMax: true,
				//自定义按钮
				buttons: [{"text": "按钮1", "id": "button1", onclick: function(button, dialog, index){
					popupInfo(button.id + ":" + button.text);
				}}, {"text": "按钮2", "id": "button2", onclick: function(button, dialog, index){
					popupInfo(button.id + ":" + button.text);
				}}, {"text": "关闭", onclick: function(button, dialog, index){
					dialog.hide();
				}}]
			});	
		}
	}
	
	function popup11() {
		topDialog(rootPath + "/pages/sample/webui/popup.jsp", 700, 1000);
	}
	
	function popup12() {
		//最大化显示
		topDialogWithParam(rootPath + "/manager/QueryLoginLog.action", "beginDate=2014-05-01 00:00:00&endDate=2099-12-30 00:00:00");
	}
	
	function popup13() {
		//使用onClosed事件接收对话框返回参数
		popupDialogWithParam({
  			url: rootPath + "/pages/sample/webui/popup.jsp",
  			onClosed: function(){
  				var retVal = this.returnValue;
  				if(retVal){
  					popupInfo(powersi.tostring(retVal));  					
  				}
  			}
  		}, 'param1', 600, 800);
	}
	
	function popup14() {
		//使用callback事件接收对话框返回参数
		topDialogWithParam(rootPath + "/pages/sample/webui/popup.jsp", 'param2', 600, 800, function(retVal){
			if(retVal) {
  				popupInfo(powersi.tostring(retVal));  					
  			}
		});
	}
	
	function popup15() {
		topDialog({
  			url: rootPath + "/pages/sample/webui/popup.jsp",
  			onClosed: function(){
  				//顶级窗口必须判断2个返回值
  				var retVal = this.returnValue || window.returnValue;
  				if(retVal){
  					popupInfo(powersi.tostring(retVal));  					
  				}
  			},
  			//自定义按钮
			buttons: [{"text": "访问函数", "id": "button1", onclick: function(button, dialog, index){
				alert(dialog.getFrameWindow().hello());
			}}, {"text": "访问对象", "id": "button2", onclick: function(button, dialog, index){
				alert($('#olHelp', dialog.getFrameDocument()).text());
			}}, {"text": "关闭", onclick: function(button, dialog, index){
				dialog.hide();
			}}]
  		}, 600, 800);
	}
	
	function hello() {
		return '创智和宇';
	}
	
	function popupClose() {
		//使用setTimeout实现自动关闭
		var tip = popupSuccess('保存成功');
		setTimeout(function(){
			if(tip)
				tip.close();
		}, 3000);
	}
	
	function dlgCallback1() {
		openDialog(rootPath + "/pages/sample/webui/popup.jsp?param=diag1", function(ret){
			if(ret){
				alert(ret);
			}
		});
	}
	
	function dlgCallback2() {
		openDialogWithParam(rootPath + "/pages/sample/webui/popup.jsp", "diag2", 600, 800, function(ret){
			if(ret){
				alert(ret);
			}
		});
	}
	
	function dlgCallback3() {
		popupDialog(rootPath + "/pages/sample/webui/popup.jsp?param=diag3", 600, 800, function(ret){
			if(ret){
				alert(ret);
			}
		});
	}
	
	function dlgCallback4() {
		popupDialogWithParam(rootPath + "/pages/sample/webui/popup.jsp", "diag4", 0, 800, function(ret){
			if(ret){
				alert(ret);
			}
		});
	}
	
	function dlgCallback5() {
		topDialog(rootPath + "/pages/sample/webui/popup.jsp?param=diag5", 600, 0, function(ret){
			if(ret){
				alert(ret);
			}
		});
	}
	
	function dlgCallback6() {
		topDialogWithParam(rootPath + "/pages/sample/webui/popup.jsp", "diag6", function(ret){
			if(ret){
				alert(ret);
			}
		});
	}
	
	function testRunning() {
		showRunning(true);
		setTimeout(showRunning(false), 5000);
	}
</script>
</body>
</powersi:html>