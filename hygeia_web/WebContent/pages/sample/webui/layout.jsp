<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="布局样例" />
<style type="text/css">
.l-layout-top, .l-layout-bottom, .l-layout-centerbottom {
	border-width: 1px;
}
</style>
</head>
<body class="page">
	<powersi:pagelayout id="layout1" leftWidth="200" allowRightCollapse="true" centerBottomHeight="50" allowLeftCollapse="true">
       <powersi:pagelayout-panel position="left">
       	<powersi:pagelayout-panel-title>
       		<i class="icon-list"></i> <span>导航区</span>
       	</powersi:pagelayout-panel-title>
       	<div class="scrollable">
			<ul class="list inset">
			  <li class="list-item">
			    <a href="javascript:void(0);" class="list-left">
			      <span class="w-40 circle i-purple">
			        <i class="fa fa-envelope"></i>
			      </span>
			    </a>
			    <div class="list-body">
			      <div>
			        <a href="javascript:void(0);">创智和宇</a></div>
			      	<small class="text-muted text-ellipsis">2018-08-08</small>
			      </div>
			  </li>
			  <li class="list-item">
			    <a href="javascript:void(0);" class="list-left">
			      <span class="w-40 circle i-green">
			        <i class="fa fa-comment"></i>
			      </span>
			    </a>
			    <div class="list-body">
			      <div>
			        <a href="javascript:void(0);">创智和宇</a></div>
			      <small class="text-muted text-ellipsis">2018-10-01</small></div>
			  </li>
			  <li class="list-item">
			    <a href="javascript:void(0);" class="list-left">
			      <span class="w-40 circle i-cyan avatar">
			        <span>甲</span>
			        <i class="on b-white"></i>
			      </span>
			    </a>
			    <div class="list-body">
			      <div>
			        <a href="javascript:void(0);">创智和宇</a></div>
			      <small class="text-muted text-ellipsis">2018-11-11</small></div>
			  </li>
			  <li class="list-item">
			    <a href="javascript:void(0);" class="list-left">
			      <span class="w-40 circle i-red avatar">
			        <span>乙</span>
			        <i class="off b-red"></i>
			      </span>
			    </a>
			    <div class="list-body">
			      <div>
			        <a href="javascript:void(0);">创智和宇</a></div>
			      <small class="text-muted text-ellipsis">欣盛路159号</small></div>
			  </li>
			  <li class="list-item">
			    <a href="javascript:void(0);" class="list-left">
			      <span class="w-40 circle i-yellow avatar">
			        <span>丙</span>
			        <i class="away b-white"></i>
			      </span>
			    </a>
			    <div class="list-body">
			      <div>
			        <a href="javascript:void(0);">创智和宇</a></div>
			      <small class="text-muted text-ellipsis">欣盛路159号</small></div>
			  </li>
			  <li class="list-item">
			    <a href="javascript:void(0);" class="list-left">
			      <span class="w-40 circle i-black avatar">
			        <span>丁</span>
			        <i class="busy b-gray"></i>
			      </span>
			    </a>
			    <div class="list-body">
			      <div>
			        <a href="javascript:void(0);">创智和宇</a></div>
			      <small class="text-muted text-ellipsis">欣盛路159号</small></div>
			  </li>
			  <li class="list-item">
			    <a href="javascript:void(0);" class="list-left">
			      <span class="w-40 circle i-orange avatar">
			        <span>戊</span>
			        <i class="away b-white"></i>
			      </span>
			    </a>
			    <div class="list-body">
			      <div>
			        <a href="javascript:void(0);">创智和宇</a></div>
			      <small class="text-muted text-ellipsis">欣盛路159号</small></div>
			  </li>
			  <li class="list-item">
			    <a href="javascript:void(0);" class="list-left">
			      <span class="w-40 circle i-purple avatar">
			        <span>己</span>
			        <i class="busy b-white"></i>
			      </span>
			    </a>
			    <div class="list-body">
			      <div>
			        <a href="javascript:void(0);">创智和宇</a></div>
			      <small class="text-muted text-ellipsis">欣盛路159号</small></div>
			  </li>
			  <li class="list-item">
			    <a href="javascript:void(0);" class="list-left">
			      <span class="w-40 circle i-blue avatar">
			        <span>庚</span>
			        <i class="busy b-white"></i>
			      </span>
			    </a>
			    <div class="list-body">
			      <div>
			        <a href="javascript:void(0);">创智和宇</a></div>
			      <small class="text-muted text-ellipsis">欣盛路159号</small></div>
			  </li>
			</ul>
		</div>
       </powersi:pagelayout-panel>
       <powersi:pagelayout-panel position="center" title="标题">
       	<div class="space">
       		<powersi:form disabled="true">
       		<powersi:accordion id="accordion" heightStyle="content" animate="true" collapsible="true">
		    	<powersi:accordionItem title="个人基本信息">
		    		<powersi:datagrid id="grid1" height="300"
						exportFileName="'个人基本信息'" showExportExcel="true" showExportExcel2007="true" showExportPdf="true">
						<powersi:datagrid-column key="operate" width="60" frozen="true" isSort="false" isExport="false" />
						<powersi:datagrid-column display="国家" name="Country" width="30%" minWidth="100" />
						<powersi:datagrid-column display="城市" name="City" width="30%" minWidth="100" />
						<powersi:datagrid-column display="总表头1">
							<powersi:datagrid-column display="表头1">
								<powersi:datagrid-column display="公司名" name="CompanyName" minWidth="100" width="40%" />
								<powersi:datagrid-column display="个人信息">
									<powersi:datagrid-column display="主键" name="CustomerID" align="left" width="150" />
									<powersi:datagrid-column display="联系名" name="ContactName" align="left" width="150" />
								</powersi:datagrid-column>
							</powersi:datagrid-column>
						</powersi:datagrid-column>
					</powersi:datagrid> 
		    	</powersi:accordionItem>
		    	<powersi:accordionItem title="单位基本信息">
		    		<powersi:datagrid id="grid2" height="300"
						exportFileName="'单位基本信息'" showExportExcel="true" showExportExcel2007="true" showExportPdf="true">
						<powersi:datagrid-column key="operate" width="60" frozen="true" isSort="false" isExport="false" />
						<powersi:datagrid-column display="国家" name="Country" width="30%" minWidth="100" />
						<powersi:datagrid-column display="城市" name="City" width="30%" minWidth="100" />
						<powersi:datagrid-column display="总表头1">
							<powersi:datagrid-column display="表头1">
								<powersi:datagrid-column display="公司名" name="CompanyName" minWidth="100" width="40%" />
								<powersi:datagrid-column display="个人信息">
									<powersi:datagrid-column display="主键" name="CustomerID" align="left" width="150" />
									<powersi:datagrid-column display="联系名" name="ContactName" align="left" width="150" />
								</powersi:datagrid-column>
							</powersi:datagrid-column>
						</powersi:datagrid-column>
					</powersi:datagrid>
					<div class="space-y"></div>
					<div class="row">
						<div class="col-4">
							<powersi:textfield id="idcard1" name="idcard1" required="true" label="身份证1"></powersi:textfield>
						</div>
						<div class="col-4">
							<powersi:textfield id="idcard2" name="idcard2" required="true" label="身份证2"></powersi:textfield>
						</div>
						<div class="col-4">
							<powersi:textfield id="idcard3" name="idcard3" required="true" label="身份证3"></powersi:textfield>
						</div>
					</div>
		    	</powersi:accordionItem>
		    	<powersi:accordionItem title="个人缴费记录">
		    		<powersi:datagrid id="grid3" height="300"
						exportFileName="'个人缴费记录'" showExportExcel="true" showExportExcel2007="true" showExportPdf="true">
						<powersi:datagrid-column key="operate" width="60" frozen="true" isSort="false" isExport="false" />
						<powersi:datagrid-column display="国家" name="Country" width="30%" minWidth="100" />
						<powersi:datagrid-column display="城市" name="City" width="30%" minWidth="100" />
						<powersi:datagrid-column display="总表头1">
							<powersi:datagrid-column display="表头1">
								<powersi:datagrid-column display="公司名" name="CompanyName" minWidth="100" width="40%" />
								<powersi:datagrid-column display="个人信息">
									<powersi:datagrid-column display="主键" name="CustomerID" align="left" width="150" />
									<powersi:datagrid-column display="联系名" name="ContactName" align="left" width="150" />
								</powersi:datagrid-column>
							</powersi:datagrid-column>
						</powersi:datagrid-column>
					</powersi:datagrid>
		    	</powersi:accordionItem>
		    	<powersi:accordionItem title="单位缴费记录">
		    		<powersi:datagrid id="grid4" height="300"
						exportFileName="'单位缴费记录'" showExportExcel="true" showExportExcel2007="true" showExportPdf="true">
						<powersi:datagrid-column key="operate" width="60" frozen="true" isSort="false" isExport="false" />
						<powersi:datagrid-column display="国家" name="Country" width="30%" minWidth="100" />
						<powersi:datagrid-column display="城市" name="City" width="30%" minWidth="100" />
						<powersi:datagrid-column display="总表头1">
							<powersi:datagrid-column display="表头1">
								<powersi:datagrid-column display="公司名" name="CompanyName" minWidth="100" width="40%" />
								<powersi:datagrid-column display="个人信息">
									<powersi:datagrid-column display="主键" name="CustomerID" align="left" width="150" />
									<powersi:datagrid-column display="联系名" name="ContactName" align="left" width="150" />
								</powersi:datagrid-column>
							</powersi:datagrid-column>
						</powersi:datagrid-column>
					</powersi:datagrid> 
					<div class="space-y"></div>
					<powersi:submit key="button_save"></powersi:submit>
		    	</powersi:accordionItem>
		    </powersi:accordion>
		    </powersi:form>
       	</div>
       </powersi:pagelayout-panel>
       <powersi:pagelayout-panel position="right" hidetitle="false">
	       	<div class=space>
	       		<h3>右部</h3>
	       	</div>
       </powersi:pagelayout-panel>
       <powersi:pagelayout-panel position="top">
       		<div class=space>
       			<h3>头部</h3>
       		</div>
       </powersi:pagelayout-panel>
       <powersi:pagelayout-panel position="bottom">
       		<div class=space>
       			<h3>底部</h3>
       		</div>
       </powersi:pagelayout-panel>
       <powersi:pagelayout-panel position="centerbottom">
       		<div class=space>
       			<h3>中间底部</h3>
       		</div>
       </powersi:pagelayout-panel>
    </powersi:pagelayout> 
	<powersi:errors />
</body>
</powersi:html>