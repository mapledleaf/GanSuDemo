<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="表单向导" />
<style type="text/css">
.tab-content {
	margin-top: 15px;
}

.tab-pane {
	height: 200px;
	overflow-x: hidden;
	overflow-y: auto;
}

.tab-content p {
	font-size: 120px;
	text-align: center;
	vertical-align: middle;
}

#panel2 h5 {
	margin-top: 30px;
}

#panel2 h5:first-child {
	margin-top: 5px;
}

#panel4 .ystep {
	margin-top: 75px;
}

#panel4 .ystep:first-child {
	margin-top: 35px;
}

.wizard-modal p {
	margin: 0 0 10px;
	padding: 0;
}

#wizard-ns-detail-servers, .wizard-additional-servers {
	font-size: 12px;
	margin-top: 10px;
	margin-left: 15px;
}

#wizard-ns-detail-servers>li, .wizard-additional-servers li {
	line-height: 20px;
	list-style-type: none;
}

#wizard-ns-detail-servers>li>img {
	padding-right: 5px;
}

.wizard-modal .chzn-container .chzn-results {
	max-height: 150px;
}

.wizard-addl-subsection {
	margin-bottom: 40px;
}

.create-server-agent-key {
	margin-left: 15px;
	width: 90%;
}

.wizard-input-section p{
	margin-bottom: 15px;
}
</style>
</head>
<body>
	<powersi:panelbox title="表单向导(arrow模式)" id="panel1">
		<powersi:panelbox-toolbar>
			<powersi:button id="btnApp" value="应用程序向导"></powersi:button>
			<powersi:button id="btnOpen" onclick="openWizard()" value="弹窗水平显示"></powersi:button>
			<powersi:button id="btnReset" onclick="resetWizard()" value="重置向导"></powersi:button>
		</powersi:panelbox-toolbar>
		<div id="wizard1">
			<powersi:form id="form1" disable="true" validate="fasle">
				<ul>
					<li class="active"><a href="#tab1" data-toggle="tab" aria-expanded="true">操作一</a></li>
					<li><a href="#tab2" data-toggle="tab">操作二</a></li>
					<li><a href="#tab3" data-toggle="tab">操作三</a></li>
					<li><a href="#tab4" data-toggle="tab">操作四</a></li>
					<li><a href="#tab5" data-toggle="tab">操作五</a></li>
				</ul>
	
				<div class="tab-content">
					<div class="tab-pane active" id="tab1">
						<p>1</p>
					</div>
					<div class="tab-pane" id="tab2">
						<p>2</p>
					</div>
					<div class="tab-pane" id="tab3">
						<p>3</p>
					</div>
					<div class="tab-pane" id="tab4">
						<p>4</p>
					</div>
					<div class="tab-pane" id="tab5">
						<p>5</p>
					</div>
					<ul class="pager wizard">
						<li class="previous first disabled" style="display:none;"><a href="javascript:;">开始</a></li>
						<li class="previous disabled"><a href="javascript:;">上一步</a></li>
						<li class="next last" style="display:none;"><a href="javascript:;">最后</a></li>
						<li class="next"><a href="javascript:;">下一步</a></li>
						<li class="finish"><a href="javascript:;">完成</a></li>
					</ul>
				</div>
			</powersi:form>
		</div>
		
		<div id="wizard2">
			<powersi:form id="form2" disable="true" validate="fasle">
				<ul>
					<li class="active"><a href="#tab11" data-toggle="tab" aria-expanded="true">操作一</a></li>
					<li><a href="#tab12" data-toggle="tab">操作二</a></li>
					<li><a href="#tab13" data-toggle="tab">操作三</a></li>
					<li><a href="#tab14" data-toggle="tab">操作四</a></li>
					<li><a href="#tab15" data-toggle="tab">操作五</a></li>
				</ul>
	
				<div class="tab-content">
					<div class="tab-pane active" id="tab11">
						<p>1</p>
					</div>
					<div class="tab-pane" id="tab12">
						<p>2</p>
					</div>
					<div class="tab-pane" id="tab13">
						<p>3</p>
					</div>
					<div class="tab-pane" id="tab14">
						<p>4</p>
					</div>
					<div class="tab-pane" id="tab15">
						<p>5</p>
					</div>
					<ul class="pager wizard">
						<li class="previous first disabled" style="display:none;"><a href="javascript:;">开始</a></li>
						<li class="previous disabled"><a href="javascript:;">上一步</a></li>
						<li class="next last" style="display:none;"><a href="javascript:;">最后</a></li>
						<li class="next"><a href="javascript:;">下一步</a></li>
						<li class="finish"><a href="javascript:;">完成</a></li>
					</ul>
				</div>
			</powersi:form>
		</div>
	</powersi:panelbox>
	
	<powersi:panelbox title="表单向导(水平模式)" id="panel2">
		<h5>arrow模式</h5>
		<ul class="nav nav-pills nav-justified step step-arrow">
			<li class="active"><a>step1</a></li>
			<li><a>step2</a></li>
			<li><a>step3</a></li>
			<li><a>step4</a></li>
			<li><a>step5</a></li>
		</ul>
		<h5>square模式</h5>
		<ul class="nav nav-pills nav-justified step step-square">
			<li class="active"><a>step1</a></li>
			<li class="active"><a>step2</a></li>
			<li><a>step3</a></li>
			<li><a>step4</a></li>
			<li><a>step5</a></li>
		</ul>
		<h5>round模式</h5>
		<ul class="nav nav-pills nav-justified step step-round">
			<li class="active"><a>step1</a></li>
			<li class="active"><a>step2</a></li>
			<li class="active"><a>step3</a></li>
			<li><a>step4</a></li>
			<li><a>step5</a></li>
		</ul>
		<h5>progress模式</h5>
		<ul class="nav nav-pills nav-justified step step-progress">
			<li class="active"><a>step1<span class="caret"></span></a></li>
			<li class="active"><a>step2<span class="caret"></span></a></li>
			<li class="active"><a>step3<span class="caret"></span></a></li>
			<li class="active"><a>step4<span class="caret"></span></a></li>
			<li><a>step5<span class="caret"></span></a></li>
		</ul>
	</powersi:panelbox>

	<div class="row">
		<div class="col-6">
			<powersi:panelbox title="表单向导(垂直模式，IE8不支持)" id="panel3">
				<div class="row">
					<div class="col-6">
						<ul class="nav nav-stacked step step-square vertical">
							<li class="active"><a>我是进度一</a></li>
							<li><a>我是进度二</a></li>
							<li><a>我是进度三</a></li>
							<li><a>我是进度四</a></li>
							<li><a>我是进度五</a></li>
						</ul>
					</div>
					<div class="col-6">
						<ul class="nav nav-stacked step step-round vertical">
							<li class="active"><a>我是进度一</a></li>
							<li><a>我是进度二</a></li>
							<li><a>我是进度三</a></li>
							<li><a>我是进度四</a></li>
							<li><a>我是进度五</a></li>
						</ul>
					</div>
				</div>
			</powersi:panelbox>
		</div>
		<div class="col-6">
			<powersi:panelbox title="流程展示(仅做显示)" id="panel4">
				  <div id="ystep1" class="ystep"></div>
				  <div id="ystep2" class="ystep"></div>
				  <div id="ystep3" class="ystep"></div>
				  <div id="ystep4" class="ystep"></div>
			</powersi:panelbox>
		</div>
	</div>
		
	<!-- 应用程序向导 -->
	<div class="wizard-box" id="wizard3" data-title="创建服务器">
		<!-- Step 1 Name & FQDN -->
		<div class="wizard-card" data-cardname="name">
			<h3>名称 & 域名</h3>
			<div class="wizard-input-section">
				<div class="form-group">
					<div class="col-sm-6">
						<input type="text" class="form-control" id="label" name="label" placeholder="Server label" data-validate="validateServerLabel">
					</div>
				</div>
			</div>

			<div class="wizard-input-section">
				<p>
					全称域名
				</p>

				<div class="form-group">
					<div class="col-sm-8">
						<div class="input-group">
							<input type="text" class="form-control" id="fqdn" name="fqdn" placeholder="FQDN" data-validate="validateFQDN" data-is-valid="0" data-lookup="0" />
							<span class="input-group-btn" id="btn-fqdn">
								<button class="btn btn-default" type="button" onclick='lookup();'>
									查找
								</button>
							</span>
						</div>
					</div>
				</div>
			</div>

			<div class="wizard-input-section">
				<p>
					服务器地址.
				</p>

				<div class="form-group">
					<div class="col-sm-8">
						<input type="text" class="form-control" id="ip" name="ip" placeholder="IP" data-serialize="1" />
					</div>
				</div>
			</div>
		</div>

		<div class="wizard-card" data-cardname="group">
			<h3>服务器分组</h3>

			<div class="wizard-input-section">
				<p>
					Where would you like server <strong class="create-server-name"></strong>
					to go?
				</p>
			</div>
		</div>

		<div class="wizard-card wizard-card-overlay" data-cardname="services">
			<h3>选择服务</h3>

			<div class="alert hide">
				It's recommended that you select at least one
				service, like ping.
			</div>

			<div class="wizard-input-section">
				<p>
					Please choose the services you'd like Panopta to
					monitor.  Any service you select will be given a default
					check frequency of 1 minute.
				</p>

				<select name="services" data-placeholder="Service List" style="width:350px;" class="chzn-select create-server-service-list form-control" multiple>
					<option value=""></option>
					<optgroup label="Basic">
						<option selected value="icmp.ping">Ping</option>
						<option selected value="tcp.ssh">SSH</option>
						<option value="tcp.ftp">FTP</option>
					</optgroup>
					<optgroup label="Web">
						<option selected value="tcp.http">HTTP</option>
						<option value="tcp.https">HTTP (Secure)</option>
						<option value="tcp.dns">DNS</option>
					</optgroup>
					<optgroup label="Email">
						<option value="tcp.pop">POP</option>
						<option value="tcp.imap">IMAP</option>
						<option value="tcp.smtp">SMTP</option>
						<option value="tcp.pops">POP (Secure)</option>
						<option value="tcp.imaps">IMAP (Secure)</option>
						<option value="tcp.smtps">SMTP (Secure)</option>
						<option value="tcp.http.exchange">Microsoft Exchange</option>
					</optgroup>
					<optgroup label="Databases">
						<option value="tcp.mysql">MySQL</option>
						<option value="tcp.postgres">PostgreSQL</option>
						<option value="tcp.mssql">Microsoft SQL Server</option>
					</optgroup>
				</select>
			</div>
		</div>

		<div class="wizard-card wizard-card-overlay" data-cardname="location">
			<h3>监控位置</h3>

			<div class="wizard-input-section">
				<p>
					We determined <strong>Chicago</strong> to be
					the closest location to monitor
					<strong class="create-server-name"></strong>
					If you would like to change this, or you think this is
					incorrect, please select a different
					monitoring location.
				</p>

				<select name="location" data-placeholder="Monitor nodes" style="width:350px;" class="chzn-select form-control">
					<option value=""></option>
					<optgroup label="North America">
						<option>Atlanta</option>
						<option selected>Chicago</option>
						<option>Dallas</option>
						<option>Denver</option>
						<option>Fremont, CA</option>
						<option>Los Angeles</option>
						<option>Miami</option>
						<option>Newark, NJ</option>
						<option>Phoenix</option>
						<option>Seattle</option>
						<option>Washington, DC</option>
					</optgroup>

					<optgroup label="Europe">
						<option>Amsterdam, NL</option>
						<option>Berlin</option>
						<option>London</option>
						<option>Milan, Italy</option>
						<option>Nurnberg, Germany</option>
						<option>Paris</option>
						<option>Stockholm</option>
						<option>Vienna</option>
					</optgroup>

					<optgroup label="Asia/Africa">
						<option>Cairo</option>
						<option>Jakarta</option>
						<option>Johannesburg</option>
						<option>Hong Kong</option>
						<option>Singapore</option>
						<option>Sydney</option>
						<option>Tokyo</option>
					</optgroup>

				</select>

			</div>
		</div>

		<div class="wizard-card wizard-card-overlay">
			<h3>通知计划</h3>

			<div class="wizard-input-section">
				<p>
					Select the notification schedule to be used for outages.
				</p>

				<select name="notification" class="wizard-ns-select chzn-select form-control" data-placeholder="Notification schedule" style="width:350px;">
					<option value=""></option>
					<option>ALIS Production</option>
					<option>ALIS Development &amp; Staging</option>
					<option>Panopta Development &amp; Staging</option>
					<option>Jira</option>
					<option>QSC Enterprise Production</option>
					<option>QSC Enterprise Development &amp; Staging</option>
					<option>Panopta Production</option>
					<option>Panopta Monitoring Nodes</option>
					<option>Common</option>
				</select>
			</div>

			<div class="wizard-ns-detail hide">
				Also using <strong>ALIS Production</strong>:

				<ul id="wizard-ns-detail-servers">
					<li>Corporate sites</li>
					<li>dt01.sat.medtelligent.com</li>
					<li>alisonline.com</li>
					<li>circa-db04.sat.medtelligent.com</li>
					<li>circa-services01.sat.medtelligent.com</li>
					<li>circa-web01.sat.medtelligent.com</li>
					<li>heartbeat.alisonline.com</li>
					<li>medtelligent.com</li>
					<li>dt02.fre.medtelligent.com</li>
					<li>dev03.lin.medtelligent.com</li>
				</ul>img
			</div>
		</div>
		
		<div class="wizard-card">
			<h3>代理设置</h3>

			<div class="wizard-input-section">
				<p>The <a target="_blank" href="http://www.panopta.com/support/knowledgebase/support-questions/how-do-i-install-the-panopta-monitoring-agent/">Panopta Agent</a> allows
					you to monitor local resources (disk usage, cpu usage, etc).
					If you would like to set that up now, please download
					and follow the <a target="_blank" href="http://www.panopta.com/support/knowledgebase/support-questions/how-do-i-install-the-panopta-monitoring-agent/">install instructions.</a>
				</p>

				<div class="btn-group">
					<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">Download<span class="caret"></span></button>
					<ul class="dropdown-menu">
						<li><a href="#">.rpm</a></li>
						<li><a href="#">.deb</a></li>
						<li><a href="#">.tar.gz</a></li>
					</ul>
				</div>
			</div>


			<div class="wizard-input-section">
				<p>You will be given a server key after you install the Agent
					on <strong class="create-server-name"></strong>.
					If you know your server key now, please enter it
					below.</p>

				<div class="form-group">
					<input type="text" class="create-server-agent-key form-control" placeholder="Server key (optional)" data-validate="">
				</div>
			</div>


			<div class="wizard-error">
				<div class="alert alert-error">
					<strong>There was a problem</strong> with your submission.
					Please correct the errors and re-submit.
				</div>
			</div>

			<div class="wizard-failure">
				<div class="alert alert-error">
					<strong>There was a problem</strong> submitting the form.
					Please try again in a minute.
				</div>
			</div>

			<div class="wizard-success">
				<div class="alert alert-success">
					<span class="create-server-name"></span>服务器创建 <strong>成功</strong>.
				</div>

				<a class="btn btn-default create-another-server">新建新服务器</a>
				<span style="padding:0 10px">或</span>
				<a class="btn btn-success im-done">完成</a>
			</div>
		</div>
	</div>
	
	<!-- bootstrap表单向导(ie8不支持step样式，使用原生bootstrap tab样式渲染 -->
	<!--[if lt IE 9]>
	    <link href="${strutsPath }/js/plugins/bootstrap-wizard/css/jquery.bootstrap.wizard-ie8.css" charset="utf-8" rel="stylesheet" media="all" />
	<![endif]-->
	<!--[if gte IE 9]><!-->
	    <link href="${strutsPath }/js/plugins/bootstrap-wizard/css/jquery.bootstrap.wizard.css" charset="utf-8" rel="stylesheet" media="all" />
	<!--<![endif]-->
	<script src="${strutsPath }/js/plugins/bootstrap-wizard/js/jquery.bootstrap.wizard.min.js" charset="utf-8" type="text/javascript"></script>
	<script>
		$(function() {
			/*表单向导，修改tabClass实现多种样式*/
			/*非弹窗withVisible设置true*/
			$('#wizard1').bootstrapWizard({
				'tabClass' : 'nav nav-pills nav-justified step step-arrow',
				withVisible: true,
				onInit : function(tab, navigation, index) {
					popupMessage('onInit:' + index);
				},
				onShow : function(tab, navigation, index) {
					popupMessage('onShow:' + index);
				},
				onFirst : function(tab, navigation, index) {
					popupMessage('onFirst:' + index);
				},
				onNext : function(tab, navigation, index) {
					popupMessage('onNext:' + index);
				},
				onPrevious : function(tab, navigation, index) {
					popupMessage('onPrevious:' + index);
				},
				onLast : function(tab, navigation, index) {
					popupMessage('onLast:' + index);
				},
				onFinish : function(tab, navigation, index) {
					popupMessage('onFinish:' + index);
				},
				onTabClick : function(activeTab, navigation, currentIndex, clickedIndex, clickedTab) {
					/*允许向前点击回退*/
					if(clickedIndex < currentIndex){
						return true;
					} else {
						return false;
					}
				},
				onTabShow : function(tab, navigation, index) {
					popupMessage('onTabShow:' + index);
				},
				onTabChange: function(activeTab, navigation, currentIndex, nextTab) {
					popupMessage('onTabChange:' + currentIndex);
				}
			});
			
			/*表单向导，修改tabClass实现多种样式*/
			/*弹窗withVisible需要设置false，初始化后需要显式hide*/
			$('#wizard2').bootstrapWizard({
				'tabClass' : 'nav nav-pills nav-justified step step-square',
				withVisible: false,
				onInit : function(tab, navigation, index) {
					popupMessage('onInit:' + index);
				},
				onShow : function(tab, navigation, index) {
					popupMessage('onShow:' + index);
				},
				onFirst : function(tab, navigation, index) {
					popupMessage('onFirst:' + index);
				},
				onNext : function(tab, navigation, index) {
					popupMessage('onNext:' + index);
				},
				onPrevious : function(tab, navigation, index) {
					popupMessage('onPrevious:' + index);
				},
				onLast : function(tab, navigation, index) {
					popupMessage('onLast:' + index);
				},
				onFinish : function(tab, navigation, index) {
					if(dlg){
						dlg.hide();
					}
					
					resetForm('#form2');
					$('#wizard2').bootstrapWizard('show', 0);
					
					popupMessage('onFinish:' + index);				
				},
				onTabClick : function(activeTab, navigation, currentIndex, clickedIndex, clickedTab) {
					/*允许向前点击回退*/
					if(clickedIndex < currentIndex){
						return true;
					} else {
						return false;
					}
				},
				onTabShow : function(tab, navigation, index) {
					popupMessage('onTabShow:' + index);
				},
				onTabChange: function(activeTab, navigation, currentIndex, nextTab) {
					popupMessage('onTabChange:' + currentIndex);
				}
			}).hide();
		});
		
		var dlg = null;
		function openWizard() {
			if(dlg){
				dlg.show();
			} else {
				dlg = popupDiv("#wizard2", "弹窗向导", 800);
			}
		}
		
		function resetWizard() {
			resetForm('#form1');
			$('#wizard1').bootstrapWizard('show', 0);
			
			resetForm('#form2');
			$('#wizard2').bootstrapWizard('show', 0);
			
			popupMessage("向导已经重置");
		}
	</script>
	
	<!-- 流程步骤显示 -->
	<link href="${strutsPath }/js/plugins/ystep/css/ystep.css" charset="utf-8" rel="stylesheet" media="all" />
	<script src="${strutsPath }/js/plugins/ystep/js/ystep.min.js" charset="utf-8" type="text/javascript"></script>
	<script type="text/javascript">
		$(function() {
			//流程步骤显示
			var steps = [ {
					title : "申请",
					content : "用户发起报账申请"
				}, {
					title : "审核",
					content : "部门经理进行费用审核"
				}, {
					title : "审签",
					content : "总经理进行费用审签"
				}, {
					title : "拨付",
					content : "财务拨付费用款项"
				}, {
					title : "终结",
					content : "用户报账流程终结"
				}];

				/*绿色可以作为流程完成显示，蓝色作为待完成任务显示*/
				$("#ystep1").loadStep({
					//ystep的外观大小
					//可选值：small,large
					size : "small",
					//ystep配色方案
					//可选值：green,blue
					color : "green",
					//ystep中包含的步骤
					steps : steps
				});

				$("#ystep2").loadStep({
					size : "small",
					color : "blue",
					steps : steps
				});

				$("#ystep3").loadStep({
					size : "large",
					color : "green",
					steps : steps
				});

				$("#ystep4").loadStep({
					size : "large",
					color : "blue",
					steps : steps
				});

				$("#ystep1").setStep(5);
				$("#ystep2").setStep(2);
				$("#ystep3").setStep(5);
				$("#ystep3").setStep(2, 4);
		});
	</script>
	
	<!-- 应用程序向导 -->
	<link href="${strutsPath }/js/plugins/bootstrap-wizard/css/bootstrap-wizard.css" charset="utf-8" rel="stylesheet" media="all" />
	<script src="${strutsPath }/js/plugins/bootstrap-wizard/js/bootstrap-wizard.min.js" charset="utf-8" type="text/javascript"></script>
	<script type="text/javascript">
		$(function() {
			$.fn.wizard.logging = true;
			var wizard = $('#wizard3').wizard({
				keyboard : false,
				contentHeight : 400,
				contentWidth : 800,
				backdrop: 'static',
				buttons: {
	                cancelText: "取消",
	                nextText: "下一步",
	                backText: "上一步",
	                submitText: "保存",
	                submittingText: "保存中...",
	            }
			});
			
			$(".chzn-select").select2();
	
			$('#fqdn').on('input', function() {
				if ($(this).val().length != 0) {
					$('#ip').val('').attr('disabled', 'disabled');
					$('#fqdn, #ip').parents('.form-group').removeClass('has-error has-success');
				} else {
					$('#ip').val('').removeAttr('disabled');
				}
			});
	
			var pattern = /\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\b/;
			x = 46;
	
			$('#ip').on('input', function() {
				if ($(this).val().length != 0) {
					$('#fqdn').val('').attr('disabled', 'disabled');
				} else {
					$('#fqdn').val('').removeAttr('disabled');
				}
			}).keypress(function(e) {
				if (e.which != 8 && e.which != 0 && e.which != x && (e.which < 48 || e.which > 57)) {
					console.log(e.which);
					return false;
				}
			}).keyup(function() {
				var $this = $(this);
				if (!pattern.test($this.val())) {
					//$('#validate_ip').text('Not Valid IP');
					console.log('Not Valid IP');
					$this.parents('.form-group').removeClass('has-error has-success').addClass('has-error');
					while ($this.val().indexOf("..") !== -1) {
						$this.val($this.val().replace('..', '.'));
					}
					x = 46;
				} else {
					x = 0;
					var lastChar = $this.val().substr($this.val().length - 1);
					if (lastChar == '.') {
						$this.val($this.val().slice(0, -1));
					}
					var ip = $this.val().split('.');
					if (ip.length == 4) {
						//$('#validate_ip').text('Valid IP');
						console.log('Valid IP');
						$this.parents('.form-group').removeClass('has-error').addClass('has-success');
					}
				}
			});
	
			wizard.on('closed', function() {
				wizard.reset();
			});
	
			wizard.on("reset", function() {
				wizard.modal.find(':input').val('').removeAttr('disabled');
				wizard.modal.find('.form-group').removeClass('has-error').removeClass('has-succes');
				wizard.modal.find('#fqdn').data('is-valid', 0).data('lookup', 0);
			});
	
			wizard.on("submit", function(wizard) {
				var submit = {
					"hostname": $("#new-server-fqdn").val()
				};
				
				this.log('seralize()');
				this.log(this.serialize());
				this.log('serializeArray()');
				this.log(this.serializeArray());
		
				setTimeout(function() {
					wizard.trigger("success");
					wizard.hideButtons();
					wizard._submitting = false;
					wizard.showSubmitCard("success");
					wizard.updateProgressBar(0);
				}, 2000);
			});
			
			wizard.el.find(".wizard-success .im-done").click(function() {
				wizard.hide();
				setTimeout(function() {
					wizard.reset();	
				}, 250);
				
			});
		
			wizard.el.find(".wizard-success .create-another-server").click(function() {
				wizard.reset();
			});
		
			$(".wizard-group-list").click(function() {
				alert("Disabled for demo.");
			});
	
			$('#btnApp').click(function(e) {
				e.preventDefault();
				wizard.show();
			});
		});
	
		function validateServerLabel(el) {
			var name = el.val();
			var retValue = {};
	
			if (name == "") {
				retValue.status = false;
				retValue.msg = "Please enter a label";
			} else {
				retValue.status = true;
			}
	
			return retValue;
		};
	
		function validateFQDN(el) {
			var $this = $(el);
			var retValue = {};
	
			if ($this.is(':disabled')) {
				// FQDN Disabled
				retValue.status = true;
			} else {
				if ($this.data('lookup') === 0) {
					retValue.status = false;
					retValue.msg = "Preform lookup first";
				} else {
					if ($this.data('is-valid') === 0) {
						retValue.status = false;
						retValue.msg = "Lookup Failed";
					} else {
						retValue.status = true;
					}
				}
			}
	
			return retValue;
		};
	
		function lookup() {
			// Normally a ajax call to the server to preform a lookup
			$('#fqdn').data('lookup', 1);
			$('#fqdn').data('is-valid', 1);
			$('#ip').val('127.0.0.1');
		};
	</script>
	<powersi:errors />
</body>
</powersi:html>