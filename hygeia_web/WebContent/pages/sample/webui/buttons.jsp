<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="按钮样例" />
<script type="text/javascript">
$(function(){
	$('button[data-loading-text]').click(function () {
	    $(this).button('loading');
	});
});
</script>
</head>
<body class="grid">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<div class="col-xs-12">
						<h3 class="header smaller lighter blue">标签按钮</h3>
						<p>
							<powersi:submit id="btn-save" key="button_save" buttonIcon="icon-save" disabled="true" />
							<powersi:button id="btn-query" key="button_query" buttonIcon="icon-search" />
							<powersi:button id="btn-delete" key="button_delete" buttonIcon="icon-trash" />
							<powersi:button id="btn-close" key="button_close" buttonIcon="icon-remove" />
							<powersi:button id="btn-return" key="button_return"  buttonIcon="icon-arrow-right" buttonIconSecondary="icon-arrow-left" />
							<powersi:button id="btn-ok" key="button_ok" buttonIcon="icon-ok" />
							<powersi:button id="btn-cancel" key="button_cancel" buttonIcon="icon-repeat" />
							<input type="reset"	id="btReset" name="button_reset" value="重 置" class="button">
								<i class="icon-undo"></i>
							</input>
						</p>
					</div>
				</div>
				
				<div class="space"></div>
				
				<div class="row">
					<div class="col-xs-12">
						<h3 class="header smaller lighter green">Application Buttons</h3>
						<p>
							<a href="#" class="btn btn-default btn-app radius-4"> <i
								class="icon-cog bigger-230"></i> Default <span
								class="badge badge-pink">+3</span>
							</a> <a href="#" class="btn btn-app btn-primary no-radius"> <i
								class="icon-edit bigger-230"></i> Edit <span
								class="badge badge-warning badge-left">11</span>
							</a> <a href="#" class="btn btn-app btn-success"> <i
								class="icon-refresh bigger-230"></i> Reload
							</a>

							<button class="btn btn-app btn-warning">
								<i class="icon-undo bigger-230"></i> Undo
							</button>

							<a href="#" class="btn btn-app btn-info btn-sm no-radius"> <i
								class="icon-envelope bigger-200"></i> Mailbox <span
								class="label label-inverse arrowed-in">6+</span>
							</a>

							<button class="btn btn-app btn-danger btn-sm">
								<i class="icon-trash bigger-200"></i> Delete
							</button>

							<button class="btn btn-app btn-purple btn-sm">
								<i class="icon-cloud-upload bigger-200"></i> Upload
							</button>

							<button class="btn btn-app btn-pink btn-sm">
								<i class="icon-share-alt bigger-200"></i> Share
							</button>

							<button class="btn btn-app btn-inverse btn-xs">
								<i class="icon-lock bigger-160"></i> Lock
							</button>

							<button class="btn btn-app btn-grey btn-xs radius-4">
								<i class="icon-save bigger-160"></i> Save <span
									class="badge badge-transparent"> <i
									class="light-red icon-asterisk"></i>
								</span>
							</button>

							<button class="btn btn-app btn-light btn-xs">
								<i class="icon-print bigger-160"></i> Print
							</button>

							<a href="#" class="btn btn-app btn-yellow btn-xs"> <i
								class="icon-shopping-cart bigger-160"></i> Shop
							</a>
						</p>
					</div>
				</div>

				<div class="space"></div>

				<div class="row">
					<div class="col-sm-6" id="default-buttons">
						<h3 class="row-fluid header smaller lighter purple">
							<span class="span7"> Default Buttons </span>
							<!-- /span -->

							<span class="span5"> <label class="pull-right inline">
									<small class="muted">Border:</small> <input
									id="id-button-borders" checked="" type="checkbox"
									class="ace ace-switch ace-switch-5" /> <span class="lbl"></span>
							</label>
							</span>
							<!-- /span -->
						</h3>

						<p>
							<button class="btn">Default</button>
							<button class="btn btn-primary">Primary</button>
							<button class="btn btn-info">Info</button>
							<button class="btn btn-success">Success</button>
						</p>

						<p>
							<button class="btn btn-warning">Warning</button>
							<button class="btn btn-danger">Danger</button>
							<button class="btn btn-inverse">Inverse</button>
							<button class="btn btn-pink">Pink</button>
						</p>

						<p>
							<button class="btn btn-purple">Purple</button>
							<button class="btn btn-yellow">Yellow</button>
							<button class="btn btn-grey">Grey</button>
							<button class="btn btn-light">Light</button>
							<button class="btn btn-white">White</button>
							<button class="btn btn-link">Link</button>
						</p>
						<h3 class="header smaller lighter green">Button with Icons</h3>

						<p>
							<button class="btn">
								<i class="icon-pencil align-top bigger-125"></i> Default
							</button>

							<button class="btn btn-primary">
								<i class="icon-beaker align-top bigger-125"></i> Primary
							</button>

							<button class="btn btn-info">
								Info <i class="icon-print  align-top bigger-125 icon-on-right"></i>
							</button>
						</p>

						<p>
							<button class="btn btn-lg btn-success">
								<i class="icon-ok"></i> Success
							</button>

							<button class="btn btn-sm btn-warning">
								<i class="icon-fire bigger-110"></i> <span
									class="bigger-110 no-text-shadow">Warning</span>
							</button>

							<button class="btn btn-xs btn-danger">
								<i class="icon-bolt bigger-110"></i> Danger <i
									class="icon-arrow-right icon-on-right"></i>
							</button>
						</p>

						<p>
							<button class="btn btn-info">
								<i class="icon-signal icon-only bigger-150"></i>
							</button>

							<button class="btn btn-warning btn-xs">
								<i class="icon-wrench  bigger-110 icon-only"></i>
							</button>

							<button class="btn btn-danger btn-sm">
								<i class="icon-reply icon-only"></i>
							</button>

							<button class="btn btn-grey btn-lg">
								<i class="icon-trash icon-2x icon-only"></i>
							</button>
						</p>
					</div>
					<!-- /span -->

					<div class="col-sm-6">
						<h3 class="header smaller lighter red">Button Sizing</h3>

						<p>
							<button class="btn btn-minier btn-yellow">Minier</button>
							<button class="btn btn-xs">Mini</button>
							<button class="btn btn-sm btn-primary">Small</button>
							<button class="btn btn-info">Default</button>
							<button class="btn btn-lg btn-success">Large Size</button>
						</p>

						<p>
							<button class="btn btn-warning btn-lg">Large Size</button>
							<button class="btn btn-danger">Default</button>
							<button class="btn btn-sm btn-inverse">Small</button>
							<button class="btn btn-xs btn-pink">Pink</button>
							<button class="btn btn-minier btn-purple">Purple</button>
						</p>
						<h3 class="header smaller lighter grey">Disabled Buttons</h3>

						<p>
							<button class="btn disabled">Default</button>
							<button class="btn disabled btn-primary">Primary</button>
							<button class="btn disabled btn-info">Info</button>
							<button class="btn disabled btn-success">Success</button>
						</p>

						<hr />
						<p>
							<button class="btn btn-danger btn-block">Block Button</button>
						</p>
					</div>
					<!-- /span -->
				</div>
				<!-- /row -->

				<hr />
				<div class="row">
					<div class="col-sm-6">
						<h3 class="header smaller lighter blue">Button Groups</h3>

						<p></p>
						<div class="btn-group">
							<button class="btn">1</button>
							<button class="btn">2</button>
							<button class="btn">3</button>
						</div>


						<p></p>
						<div class="btn-toolbar">
							<div class="btn-group">
								<button class="btn btn-light">1</button>
								<button class="btn btn-light">2</button>
								<button class="btn btn-light">3</button>
								<button class="btn btn-light">4</button>
							</div>

							<div class="btn-group">
								<button class="btn">5</button>
								<button class="btn">6</button>
								<button class="btn">7</button>
							</div>

							<div class="btn-group">
								<button class="btn btn-grey">8</button>
							</div>
						</div>


						<p></p>
						<div class="btn-group btn-group-vertical">
							<button class="btn" type="button">
								<i class="icon-only icon-align-left"></i>
							</button>

							<button class="btn" type="button">
								<i class="icon-only icon-align-center"></i>
							</button>

							<button class="btn" type="button">
								<i class="icon-only icon-align-right"></i>
							</button>

							<button class="btn" type="button">
								<i class="icon-only icon-align-justify"></i>
							</button>
						</div>

						<div class="space-6"></div>


						<p>
							<button id="loading-btn" type="button" class="btn btn-success"
								data-loading-text="Loading...">Loading state</button>
							<button type="button" class="btn btn-primary"
								data-toggle="button">Single Toggle</button>
							<button type="button" class="btn btn-sm btn-danger"
								data-toggle="button">Small</button>
							<button type="button" class="btn btn-xs btn-info"
								data-toggle="button">Mini</button>
						</p>

						<p></p>
						<div>
							<span>Checkbox: &nbsp;</span>

							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-sm"> <input type="checkbox"
									value="1" /> <i class="icon-only icon-bold bigger-110"></i>
								</label> <label class="btn btn-sm"> <input type="checkbox"
									value="2" /> <i class="icon-only icon-italic bigger-110"></i>
								</label> <label class="btn btn-sm"> <input type="checkbox"
									value="3" /> <i class="icon-only icon-underline bigger-110"></i>
								</label>
							</div>
						</div>


						<p></p>
						<div>
							<span>Radio: &nbsp;</span>

							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-info"> <input type="radio"
									value="1" /> <i class="icon-only icon-align-left"></i>
								</label> <label class="btn btn-info"> <input type="radio"
									value="2" /> <i class="icon-only icon-align-center"></i>
								</label> <label class="btn btn-info"> <input type="radio"
									value="3" /> <i class="icon-only icon-align-right"></i>
								</label>
							</div>

							<label class="btn btn-info" data-toggle="button"> <i
								class="icon-only icon-align-justify"></i>
							</label>
						</div>

					</div>
					<!-- /span -->

					<div class="col-sm-6">
						<h3 class="header smaller lighter green">Button Dropdown</h3>

						<p></p>
						<div class="btn-toolbar">
							<div class="btn-group">
								<button data-toggle="dropdown" class="btn dropdown-toggle">
									Action <span class="icon-caret-down icon-on-right"></span>
								</button>

								<ul class="dropdown-menu dropdown-default">
									<li><a href="#">Action</a></li>

									<li><a href="#">Another action</a></li>

									<li><a href="#">Something else here</a></li>

									<li class="divider"></li>

									<li><a href="#">Separated link</a></li>
								</ul>
							</div>
							<!-- /btn-group -->

							<div class="btn-group">
								<button data-toggle="dropdown"
									class="btn btn-primary dropdown-toggle">
									Action <i class="icon-angle-down icon-on-right"></i>
								</button>

								<ul class="dropdown-menu">
									<li><a href="#">Action</a></li>

									<li><a href="#">Another action</a></li>

									<li><a href="#">Something else here</a></li>

									<li class="divider"></li>

									<li><a href="#">Separated link</a></li>
								</ul>
							</div>
							<!-- /btn-group -->

							<div class="btn-group">
								<button data-toggle="dropdown"
									class="btn btn-sm btn-danger dropdown-toggle">
									Danger <i class="icon-angle-down icon-on-right"></i>
								</button>

								<ul class="dropdown-menu dropdown-danger">
									<li><a href="#">Action</a></li>

									<li><a href="#">Another action</a></li>

									<li><a href="#">Something else here</a></li>

									<li class="divider"></li>

									<li><a href="#">Separated link</a></li>
								</ul>
							</div>
							<!-- /btn-group -->
						</div>

						<div class="space-4"></div>

						<div class="btn-toolbar">
							<div class="btn-group">
								<button data-toggle="dropdown"
									class="btn btn-warning dropdown-toggle">
									Warning <span class="icon-caret-down icon-on-right"></span>
								</button>

								<ul class="dropdown-menu dropdown-warning">
									<li><a href="#">Action</a></li>

									<li><a href="#">Another action</a></li>

									<li><a href="#">Something else here</a></li>

									<li class="divider"></li>

									<li><a href="#">Separated link</a></li>
								</ul>
							</div>
							<!-- /btn-group -->

							<div class="btn-group">
								<button data-toggle="dropdown"
									class="btn btn-success btn-lg dropdown-toggle">
									Success large <i class="icon-angle-down icon-on-right"></i>
								</button>

								<ul class="dropdown-menu dropdown-success pull-right">
									<li><a href="#">Action</a></li>

									<li><a href="#">Another action</a></li>

									<li><a href="#">Something else here</a></li>

									<li class="divider"></li>

									<li><a href="#">Separated link</a></li>
								</ul>
							</div>
							<!-- /btn-group -->
						</div>

						<div class="space-4"></div>

						<div class="btn-toolbar">
							<div class="btn-group">
								<button data-toggle="dropdown"
									class="btn btn-info btn-sm dropdown-toggle">
									Info small <span class="icon-caret-down icon-on-right"></span>
								</button>

								<ul class="dropdown-menu dropdown-info pull-right">
									<li><a href="#">Action</a></li>

									<li><a href="#">Another action</a></li>

									<li><a href="#">Something else here</a></li>

									<li class="divider"></li>

									<li><a href="#">Separated link</a></li>
								</ul>
							</div>
							<!-- /btn-group -->

							<div class="btn-group">
								<button data-toggle="dropdown"
									class="btn btn-inverse btn-xs dropdown-toggle">
									Inverse mini <span class="icon-caret-down icon-on-right"></span>
								</button>

								<ul class="dropdown-menu dropdown-inverse">
									<li><a href="#">Action</a></li>

									<li><a href="#">Another action</a></li>

									<li><a href="#">Something else here</a></li>

									<li class="divider"></li>

									<li><a href="#">Separated link</a></li>
								</ul>
							</div>
							<!-- /btn-group -->
						</div>

						<hr />


						<p></p>
						<div class="btn-toolbar">
							<div class="btn-group">
								<button class="btn">Action</button>

								<button data-toggle="dropdown" class="btn dropdown-toggle">
									<span class="icon-caret-down icon-only"></span>
								</button>

								<ul class="dropdown-menu">
									<li><a href="#">Action</a></li>

									<li><a href="#">Another action</a></li>

									<li><a href="#">Something else here</a></li>

									<li class="divider"></li>

									<li><a href="#">Separated link</a></li>
								</ul>
							</div>
							<!-- /btn-group -->

							<div class="btn-group">
								<button class="btn btn-sm btn-yellow">Some Action</button>

								<button data-toggle="dropdown"
									class="btn btn-sm btn-yellow dropdown-toggle">
									<i class="icon-angle-down icon-only"></i>
								</button>

								<ul class="dropdown-menu dropdown-yellow">
									<li><a href="#">Action</a></li>

									<li><a href="#">Another action</a></li>

									<li><a href="#">Something else here</a></li>

									<li class="divider"></li>

									<li><a href="#">Separated link</a></li>
								</ul>
							</div>
							<!-- /btn-group -->

							<div class="btn-group dropup">
								<button class="btn btn-xs btn-danger">Danger</button>

								<button data-toggle="dropdown"
									class="btn btn-xs btn-danger dropdown-toggle">
									<span class="icon-caret-down icon-only"></span>
								</button>

								<ul class="dropdown-menu dropdown-danger">
									<li><a href="#">Action</a></li>

									<li><a href="#">Another action</a></li>

									<li><a href="#">Something else here</a></li>

									<li class="divider"></li>

									<li><a href="#">Separated link</a></li>
								</ul>
							</div>
							<!-- /btn-group -->
						</div>

						<div class="space-2"></div>

						<div class="btn-toolbar">
							<div class="btn-group">
								<button class="btn btn-lg btn-warning">Warning</button>

								<button data-toggle="dropdown"
									class="btn btn-lg btn-warning dropdown-toggle">
									<span class="icon-caret-down icon-only smaller-90"></span>
								</button>

								<ul class="dropdown-menu dropdown-warning">
									<li><a href="#">Action</a></li>

									<li><a href="#">Another action</a></li>

									<li><a href="#">Something else here</a></li>

									<li class="divider"></li>

									<li><a href="#">Separated link</a></li>
								</ul>
							</div>
							<!-- /btn-group -->

							<div class="btn-group">
								<button class="btn btn-success">Success</button>

								<button data-toggle="dropdown"
									class="btn btn-success dropdown-toggle">
									<span class="icon-caret-down icon-only"></span>
								</button>

								<ul class="dropdown-menu dropdown-success">
									<li><a href="#">Action</a></li>

									<li><a href="#">Another action</a></li>

									<li><a href="#">Something else here</a></li>

									<li class="divider"></li>

									<li><a href="#">Separated link</a></li>
								</ul>
							</div>
							<!-- /btn-group -->

							<div class="btn-group">
								<button class="btn btn-info">Info</button>

								<button data-toggle="dropdown"
									class="btn btn-info dropdown-toggle">
									<span class="icon-caret-down icon-only"></span>
								</button>

								<ul class="dropdown-menu dropdown-info pull-right">
									<li><a href="#">Action</a></li>

									<li><a href="#">Another action</a></li>

									<li><a href="#">Something else here</a></li>

									<li class="divider"></li>

									<li><a href="#">Separated link</a></li>
								</ul>
							</div>
							<!-- /btn-group -->
						</div>

						<div class="space-2"></div>

						<div class="btn-toolbar">
							<div class="btn-group">
								<button class="btn btn-inverse">Inverse</button>

								<button data-toggle="dropdown"
									class="btn btn-inverse dropdown-toggle">
									<span class="icon-caret-down icon-only"></span>
								</button>

								<ul class="dropdown-menu dropdown-inverse">
									<li><a href="#">Action</a></li>

									<li><a href="#">Another action</a></li>

									<li><a href="#">Something else here</a></li>

									<li class="divider"></li>

									<li><a href="#">Separated link</a></li>
								</ul>
							</div>
							<!-- /btn-group -->
						</div>

					</div>
					<!-- /span -->
				</div>
				<!-- /row -->

				<hr />
				<div class="row">
					<div class="col-sm-6">
						<h3 class="header smaller lighter green">Lables & Badges</h3>

						<p>
							<span class="label">Default</span> <span
								class="label label-success arrowed">Success</span> <span
								class="label label-warning"> <i
								class="icon-warning-sign bigger-120"></i> Warning
							</span> <span class="label label-danger arrowed-in">Danger</span> <span
								class="label label-info arrowed-in-right arrowed">Info</span> <span
								class="label label-inverse">Inverse</span>
						</p>

						<p>
							<span class="badge">1</span> <span class="badge badge-grey">1</span>
							<span class="badge badge-success">2</span> <span
								class="badge badge-warning">4</span> <span
								class="badge badge-danger">6</span> <span
								class="badge badge-info">8</span> <span
								class="badge badge-purple">3</span> <span
								class="badge badge-inverse">10</span> <span
								class="badge badge-pink">11</span> <span
								class="badge badge-yellow">2</span>
						</p>

						<p>
							<span class="label label-lg label-pink arrowed-right">Large</span>
							<span class="label label-lg label-yellow arrowed-in">Yellow</span>
							<span class="label label-lg label-purple arrowed">Purple</span>
						</p>

						<p>
							<span
								class="label label-xlg label-primary arrowed arrowed-right">Larger</span>
							<span
								class="label label-xlg label-grey arrowed-in-right arrowed-in">Grey</span>
							<span class="label label-xlg label-light arrowed-in-right">Light</span>
						</p>

						<p>
							<span class="label label-sm label-primary arrowed arrowed-right">Smaller</span>
						</p>
					</div>
					<!-- /span -->

					<div class="col-sm-6">
						<h3 class="header smaller lighter red">Pagination</h3>

						<div>
							<ul class="pagination">
								<li class="disabled"><a href="#"> <i
										class="icon-double-angle-left"></i>
								</a></li>

								<li class="active"><a href="#">1</a></li>

								<li><a href="#">2</a></li>

								<li><a href="#">3</a></li>

								<li><a href="#">4</a></li>

								<li><a href="#">5</a></li>

								<li><a href="#"> <i class="icon-double-angle-right"></i>
								</a></li>
							</ul>
						</div>

						<p></p>
						<ul class="pager">
							<li class="previous"><a href="#">&larr; Older</a></li>

							<li class="next"><a href="#">Newer &rarr;</a></li>
						</ul>

					</div>
					<!-- /span -->
				</div>
				<!-- /row -->
			<!-- /.col -->
		</div>
		<!-- /.row -->
	</div>
	<powersi:errors />
</body>
</powersi:html>