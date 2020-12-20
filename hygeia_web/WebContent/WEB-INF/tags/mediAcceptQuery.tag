<%@ tag pageEncoding="UTF-8" body-content="empty" small-icon=""
	display-name="申报受理" description="申报受理"%>

<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>

<powersi:tabbedpanel id="divTabs">
	<powersi:tab id="tab1" target="divTab1" label="申报基本信息"  />
	<powersi:tab id="tab2" target="divTab2" label="结算申报一级表" />
	<powersi:tab id="tab3" target="divTab3" label="结算申报二级表" />
	<powersi:tab id="tab4" target="divTab4" label="结算申报三级表" />

	<div id="divTab1">
		<a>双击显示报表详细信息</a>
		<powersi:datagrid id="declGrid" showReload="false" enabledSort="false"
			onDblClickRow="dbSelectRow">
			<powersi:datagrid-column name="bae008" label="业务交接号" />
			<powersi:datagrid-column name="aae140" label="申报类型"  />
			<powersi:datagrid-column name="bka090" label="申报起始日期" />
			<powersi:datagrid-column name="bka091" label="申报终止日期" />
			<powersi:datagrid-column name="bka096" label="申报说明" />
			<powersi:datagrid-column name="bka098" label="申报人" />
			<powersi:datagrid-column name="bka105" label="受理说明" />
			<powersi:datagrid-column name="bka107" label="受理人" />
			<powersi:datagrid-column name="bka108" label="受理时间" />
			<powersi:datagrid-column name="bka109" label="处理状态" />
		</powersi:datagrid>
	</div>

	<div id="divTab2">
		 <powersi:button label="打 印"
			onclick="printDW(1)" />
		<div id="frame_div">
			<div id="reportFirst" style="margin-left: 2%; margin-right: -20%;"></div>
		</div>
	</div>

	<div id="divTab3">
	 	<powersi:button label="打 印"
			onclick="printDW(2)" />
		<iframe name="secondframe" id="secondframe" 
			src="${rootPath}/pages/biz/medicare/declare/month_decl_report_second.jsp"
			 width="100%" height="450"   allowTransparency="true"></iframe>
	</div>

	<div id="divTab4">
	     <powersi:button label="打 印"
			onclick="printDW(3)" />
		<iframe name="thirdframe" id="thirdframe" 
			src="${rootPath}/pages/biz/medicare/declare/month_decl_report_third.jsp" 
			 width="100%" height="450"   allowTransparency="true"></iframe>
	</div>
	
	

</powersi:tabbedpanel>
