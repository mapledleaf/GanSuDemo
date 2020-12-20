<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<powersi:html>
<head>
<powersi:head title="datagrid进度" />
<script src="${rootPath }/pages/sample/webui/data-dept.js" charset="utf-8"></script>
<script type="text/javascript">
var statdata = [{
    "stat_type": "001",
    "stat_name": "床位费",
    "stat_per": "3.72464021408086",
},
{
    "stat_type": "002",
    "stat_name": "西药费",
    "stat_per": "20.8812498746515",
},
{
    "stat_type": "003",
    "stat_name": "中药费",
    "stat_per": "0.164342586676675",
},
{
    "stat_type": "004",
    "stat_name": "中成药",
    "stat_per": "28.2743168866592",
},
{
    "stat_type": "006",
    "stat_name": "检查费",
    "stat_per": "13.9817263420881",
},
{
    "stat_type": "007",
    "stat_name": "治疗费",
    "stat_per": "26.6956997596175",
},
{
    "stat_type": "010",
    "stat_name": "化验费",
    "stat_per": "5.16063227200188",
},
{
    "stat_type": "013",
    "stat_name": "其它费",
    "stat_per": "1.11739206422426",
}];
</script>
<style type="text/css">
</style>
</head>
<body>
<body>
	<div class="tabbable">
		<ul class="nav nav-tabs" id="tabs1">
			<li class="active">
				<a data-toggle="tab" href="#tab-pane1" id="tab1"> 
					<i class="icon-fa fa-line-chart red"></i> Progress DataGrid
				</a>
			</li>
		</ul>
		
		<div class="tab-content content">
			<div id="tab-pane1" class="tab-pane active">
				<powersi:datagrid id="grid1" data="statdata">
					<powersi:datagrid-column id="stat_name" name="stat_name" display="费用分类名称" width="100" frozen="true" />
					<powersi:datagrid-column id="progress1" name="stat_per" display="进度条1" minWidth="150" width="25%" render="renderProgress1" />
					<powersi:datagrid-column id="progress2" name="stat_per" display="进度条2" minWidth="150" width="25%" render="renderProgress2" />
					<powersi:datagrid-column id="progress3" name="stat_per" display="进度条3" width="150" render="renderProgress3" />
					<powersi:datagrid-column id="progress4" name="stat_per" display="进度条4" minWidth="150" width="25%" render="renderProgress4" />
					<powersi:datagrid-column id="progress5" name="stat_per" display="进度条5" minWidth="150" width="25%" render="renderProgress5" />
					<powersi:datagrid-column id="icons" name="stat_per" display="图标显示" width="150" render="renderIcons" />
					<powersi:datagrid-column id="stars1" name="stat_per" display="星标显示1" width="120" render="renderStars1" />
					<powersi:datagrid-column id="stars2" name="stat_per" display="星标显示2" width="120" render="renderStars2" />
				</powersi:datagrid>
			</div>
		</div>
	</div>
<powersi:errors />
<script type="text/javascript">
function renderProgress1(row, index, value){
	var per = parseFloat(value).toFixed(0);
	var a = [];
	a.push('<div class="progress">');
	a.push('<div class="progress-bar" role="progressbar" aria-valuenow="' + per + '" aria-valuemin="0" aria-valuemax="100" style="width: ' + per + '%;">');
	a.push('<span class="sr-only">' + per + '%</span>');
	a.push('</div>');
	a.push('</div>');
	
	return a.join('');
}

function renderProgress2(row, index, value){
	var per = parseFloat(value).toFixed(0);
	var a = [];
	
	a.push('<div class="progress">');
	a.push('<div class="progress-bar textLeft" role="progressbar" aria-valuenow="' + per + '" aria-valuemin="0" aria-valuemax="100" style="width: ' + per + '%;">');
	a.push('<span>' + per + '%</span>');
	a.push('</div>');
	a.push('</div>');
	
	return a.join('');
}

function renderProgress3(row, index, value){
	var per = parseFloat(value).toFixed(0);
	var a = [];
	a.push('<div class="grid">');
	a.push('<div class="row">');
	a.push('<div class="col-3 noPadding textCenter">');
	a.push('<span class="progress-text">' + per + '%</span>');
	a.push('</div>');
	a.push('<div class="col-9 noPadding">');
	a.push('<div class="progress">');
	a.push('<div class="progress-bar" role="progressbar" aria-valuenow="' + per + '" aria-valuemin="0" aria-valuemax="100" style="width: ' + per + '%;">');
	a.push('</div>');
	a.push('</div>');
	a.push('</div>');
	a.push('</div>');
	a.push('</div>');
	
	return a.join('');
}

function renderProgress4(row, index, value){
	var per = parseFloat(value).toFixed(0);
	var color = '';
	if(per > 27){
		color = 'progress-bar-danger';
	} else if(per > 25){
		color = 'progress-bar-warning';
	} else if(per > 15){
		color = 'progress-bar-info';
	} else if(per < 5) {
		color = 'progress-bar-success';
	} else {
		color = 'progress-bar-primary';
	}
	
	var a = [];
	
	a.push('<div class="progress">');
	a.push('<div class="progress-bar textLeft ' + color + '" role="progressbar" aria-valuenow="' + per + '" aria-valuemin="0" aria-valuemax="100" style="width: ' + per + '%;">');
	a.push('<span>' + per + '%</span>');
	a.push('</div>');
	a.push('</div>');
	
	return a.join('');
}

function renderProgress5(row, index, value){
	var per = parseFloat(value).toFixed(0);
	var color = '';
	if(per > 27){
		color = 'progress-bar-danger';
	} else if(per > 25){
		color = 'progress-bar-warning';
	} else if(per > 15){
		color = 'progress-bar-info';
	} else if(per < 5) {
		color = 'progress-bar-success';
	} else {
		color = 'progress-bar-primary';
	}
	
	//为了测试效果，故意加50
	per = (parseFloat(per) + 50).toFixed(0);
	
	var a = [];
	
	a.push('<div class="progress">');
	a.push('<div class="progress-bar progress-bar-striped ' + color + '" role="progressbar" aria-valuenow="' + per + '" aria-valuemin="0" aria-valuemax="100" style="width: ' + per + '%;">');
	a.push('</div>');
	a.push('</div>');
	
	return a.join('');
}

function renderIcons(row, index, value){
	var per = parseFloat(value).toFixed(0);
	var color = '';
	if(per > 27){
		color = 'textDanger';
	} else if(per > 25){
		color = 'textWarning';
	} else if(per > 15){
		color = 'textInfo';
	} else if(per < 5) {
		color = 'textSuccess';
	} else {
		color = 'textPrimary';
	}
	
	var a = [];
	a.push('<div class="' + color + '">');
	a.push('<i class="icon-arrow-down"></i>');
	a.push('&nbsp;');
	a.push('<i class="icon-arrow-up"></i>');
	a.push('&nbsp;');
	a.push('<i class="icon-arrow-left"></i>');
	a.push('&nbsp;');
	a.push('<i class="icon-arrow-right"></i>');
	a.push('&nbsp;');
	a.push('<i class="icon-circle-arrow-down"></i>');
	a.push('&nbsp;');
	a.push('<i class="icon-circle-arrow-up"></i>');
	a.push('&nbsp;');
	a.push('<i class="icon-circle-arrow-left"></i>');
	a.push('&nbsp;');
	a.push('<i class="icon-circle-arrow-right"></i>');
	a.push('</div>');
	
	return a.join('');
}

function renderStars1(row, index, value){
	var cnt = (index % 5) + 1;
	var a = [];
	a.push('<div class="yellow">');
	for(var i = 0; i < cnt; i ++){
		if(i){
			a.push('&nbsp;');
		}
		a.push('<i class="icon-star"></i>');
	}
	a.push('</div>');
	
	return a.join('');
}

function renderStars2(row, index, value){
	var cnt = (index % 5) + 1;
	var a = [];
	a.push('<div class="red">');
	for(var i = 0; i < cnt; i ++){
		if(i){
			a.push('&nbsp;');
		}
		if(i == 4){
			a.push('<i class="icon-star-half-empty"></i>');
		} else{
			a.push('<i class="icon-star"></i>');	
		}
	}
	for(var i = cnt; i < 5; i ++){
		if(i){
			a.push('&nbsp;');
		}
		a.push('<i class="icon-star-empty"></i>');
	}
	a.push('</div>');
	
	return a.join('');
}
</script>
</body>
</powersi:html>