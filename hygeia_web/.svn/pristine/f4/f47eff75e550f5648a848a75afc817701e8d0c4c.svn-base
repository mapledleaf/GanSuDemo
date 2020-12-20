/*
 * 欢迎页js

 */

/*快捷方式*/
//title菜单完整名(不配置使用text) text显示名 icon图标class url菜单url edit是否可以编辑
function buildShortcut(data){
	var a = [];
	if(data != null && data.length > 0){
		for(var i = 0; i < data.length; i++){
			var shortcut = data[i];
			if(i % 6 == 0){
				if(i > 0){
					a.push("</ul>");
				}
				a.push('<ul class="layui-row layui-col-space10">');
			}
			
			a.push('<li class="layui-col-xs4">');
			a.push('<a href="javascript:openShortcut(' + i + ');"');
			a.push(' title="' + (shortcut.title || shortcut.text || "") + '">');
			a.push('<i class="layui-icon ' + shortcut.icon + '"></i>');
			a.push('<cite>' + shortcut.text + '</cite>');
			a.push('</a>');
			if(shortcutEditable && shortcut.edit){
				a.push('<button class="btn" title="修改快捷方式" onclick="editShortcut(' + i + ');"><i class="fa fa-ellipsis-v"></i></button>');
			}
			a.push('</li>');
		}
		
		if(shortcutEditable) {
			i ++;
			if(i % 6 == 0){
				if(i > 0) {
					a.push("</ul>");
				}
				a.push('<ul class="layui-row layui-col-space10">');
			}
			
			a.push('<li class="layui-col-xs4">');
			a.push('<a href="javascript:addShortcut();">');
			a.push('<i class="layui-icon layui-icon-add-circle"></i>');
			a.push('<cite>添加快捷方式</cite>');
			a.push('</a>');
			a.push('</li>');
		}
		if(i > 0) {
			a.push('</ul>');
		}
	} else {
		a.push('<div>&nbsp;&nbsp;</div>');
	}
	
	$('#LAY-index-shortcut').html(a.join("")).show();
	
	$('#LAY-index-shortcut > :first').show();
}

function openShortcut(idx) {
	var shortcut = shortcuts[idx];
	openMenu(shortcut.url, '', {title: shortcut.title || shortcut.text});
}

function addShortcut() {
	popupMessage("添加快捷方式");
}

function editShortcut(idx) {
	popupMessage("编辑快捷方式");
}

/*待办事项*/
//title完整名 text显示名 num待办数 cls待办数class url菜单url(不配置不跳转)
function buildBacklog(data){
	var a = [];
	if(data != null && data.length > 0){
		for(var i = 0; i < data.length; i++){
			var backlog = data[i];
			if(i % 6 == 0){
				if(i > 0){
					a.push("</ul>");
				}
				a.push('<ul class="layui-row layui-col-space10">');
			}
			
			a.push('<li class="layui-col-xs4">');
			a.push('<a class="layadmin-backlog-body" href="javascript:void(0);"');
			if(backlog.url && powersi.length(backlog.url) > 0){
				a.push(' onclick="openBacklog(' + i + ');"');
			} else {
				a.push(' onclick="layer.tips(\'不跳转\', this, {tips: 3});"');
			}
			a.push(' title="' + (backlog.title || backlog.text || "") + '">');
			a.push('<h3>' + backlog.text + '</h3>');
			a.push('<p><cite' + (backlog.cls ? (' class="' + backlog.cls + '">') : '>') + backlog.num + '</cite></p>');
			a.push('</a>');
			a.push('</li>');
		}
		
		if(i > 0) {
			a.push('</ul>');
		}
	} else {
		a.push('<div>&nbsp;&nbsp;</div>');
	}
	
	$('#LAY-index-backlog').html(a.join("")).show();
	$('#LAY-index-backlog > :first').show();
}

function openBacklog(idx) {
	var backlog = backlogs[idx];
	if(backlog && backlog.url && powersi.length(backlog.url) > 0) {
		openMenu(backlog.url, '', {title: backlog.title || backlog.text});
	}
}

function initBacklog() {
	try{
		$.ajax({
	        url: rootPath + '/message/MessageAction!pollingTask.action?size=20',
	        data: null,
	        dataType: "json",
	        processData: true,
	        type: "POST",
	        global: false,
	        error: function(jqXHR, textStatus, errorThrown) {
	        	
	        },
	        success: function(ret, textStatus, jqXHR) {
	        	if(_messageEnable){
					if(_messageInterval < 10){
						_messageInterval = 10;
					}
					setTimeout("initBacklog()", _messageInterval * 1000);	
				}
	        	
	        	if(!checkResult(ret, false)){
					return;
				}
				
				var data = ret.data;
				var a = [];
				
				for(var i = 0;i < data.length;++i){
					a.push({
						'url': data[i].task_url,
						'title': data[i].task_name,
						'text': data[i].taks_desc || data[i].task_name,
						'num': data[i].task_count,
						'cls': data[i].task_count > 100 ? 'orange' : ''
					});
				}
				
				backlogs.length = 0;
				$.each(a, function(idx, value){
					backlogs.push(value);
				});
				
				a.length = 0;
	        }
	  }).done(function() {
		  buildBacklog(backlogs);
	  });
		
	} catch(e){
		alert(e.message);
	}
}

function openBacklogs(){
	openMenu("/pages/sys/message/TaskList.jsp");
}

/*公告列表*/
function viewBulletin(bulletinUrl){
	topDialog(rootPath + "/message/BulletinManagerAction!bulletinInfo.action?bulletinId=" + bulletinUrl, 600, 600);
}

function initBulletin() {
	try{
    	var a = [];
    	var len = 0;
    	var istoday = false;
    	var isnew = false;
    	var isrecent = false;
    	var date = null;
    	var dateStr = null;
    	//注意不要直接操作serverdate，应新建以后操作
    	var newdate = moment(serverdate).add(-3, 'days').format(dateFmt);
    	var recentdate = moment(serverdate).add(-7, 'days').format(dateFmt);
		if(bulletins !== 'undefined' && bulletins !== null){
			 for (var idx in bulletins) {
	            var bulletin = bulletins[idx];
	            date = moment(bulletin.audit_date, dateFmt);
	            dateStr = date.format(dateFmt);
	            istoday = (dateStr >= serverdate);
	            isnew = (dateStr >= newdate);
	            isrecent = (dateStr >= recentdate);
	            
	            a.push('<a class="list-group-item text-ellipsis');
	            if(istoday) {
	            	a.push(' today"');
	            } else if(isnew){
	            	a.push(' new"');
            	} else if(isrecent) {
            		a.push(' recent"');
            	}else {
            		a.push('"');
            	}
	            
	            if(bulletin.bulletin_type == "1"){
	            	a.push('href="' + bulletin.bulletin_url + '" target=\"_blank\"');
	            } else {
	            	a.push('href="javascript:viewBulletin(\'' + bulletin.bulletin_url + '\');"');
	            }
	            a.push(">");
	            
	            a.push('<div class="row">');
	            a.push('<div class="col-10 text-ellipsis">');
	            
	            a.push('<span class="dot w-8"></span>');
            	
	            a.push('<span class="title" title="' + bulletin.bulletin_title + '">')
	            a.push(bulletin.bulletin_title);
	            a.push('<span>');
	            
	            a.push("</div>");
	            
	            a.push('<div class="col-2">');
	            a.push('<span class="date" title="' + bulletin.audit_date + '">');
	            if(isnew){
	            	a.push(date.from(serverdate));
	            } else{
	            	a.push(date.format("YYYY/MM/DD"));
	            }
	            a.push('</span>');
	            a.push('</div>');
	            
	            a.push('</div>');
	            a.push("</a>");
			 }
		}
		
    	$('#LAY-index-bulletin').html(a.join(""));
	} catch(e){
		alert(e.message);
	}
}

function openBulletinList() {
	openMenu('/message/BulletinManagerAction!list.action');
}
/*日历显示*/
var buildEson = function() {
	$('#LAY-index-calendar').html('<div id="inputtext_n"></div>');
	var width = $('#LAY-index-calendar').width();
	var maps = {};
	Eson('inputtext_n', {
		set_up_date: sdate,
		width : width,
		cell_height : 50,
		onselect : function(y, M, d){
			try{
				/*
				var date = NL(new Date(y, M-1, d)), festival = date.festival();
				if(festival){
					var fess = "", item;
					for(var i=0;i<festival.length;i++){
						item = festival[i];
						if(item) fess += item.value +  "，";
					}
					if(fess) fess = fess.slice(0, -1);
				}
				
				var html = 
					"公历：" + Eson.format(date.oDate, "yyyy-M-d") + "&nbsp;星期" + date.cnDay + "<br />" + 
					"农历：" + date.lunarYear + "年(" + date.animal + ")" + date.lMonth + "月(" + (date.isBigMonth ? "大": "小")  + ")" + date.lDate + (date.term ? '&nbsp;' + date.term : "") + "<br />" + 
					"干支：" + date.gzYear + "年" + date.gzMonth + "月" + date.gzDate + "日<br />" + 
					(fess ? "节日：" + fess : "");
				*/
			}catch(ex){
			}
		},
		before_render : function(){
			maps={};
		},
		after_render : function() {
			$('#LAY-index-calendar .date').hover(function(){
				var that = $(this);
			  	try{
			  		var y = that.data("y");
			  		var M = that.data("m");
			  		var d = that.data("d");
					var date = NL(new Date(y, M-1, d)), festival = date.festival();
					if(festival){
						var fess = "", item;
						for(var i=0;i<festival.length;i++){
							item = festival[i];
							if(item) fess += item.value +  "，";
						}
						if(fess) fess = fess.slice(0, -1);
					}
					
					var html = 
						"公历：" + Eson.format(date.oDate, "yyyy-M-d") + "&nbsp;星期" + date.cnDay + "<br />" + 
						"农历：" + date.lunarYear + "年(" + date.animal + ")" + date.lMonth + "月(" + (date.isBigMonth ? "大": "小")  + ")" + date.lDate + (date.term ? '&nbsp;' + date.term : "") + "<br />" + 
						"干支：" + date.gzYear + "年" + date.gzMonth + "月" + date.gzDate + "日<br />" + 
						(fess ? "节日：" + fess : "");
					
					layui.layer.tips(html, that);
				}catch(ex){ }
			}, function() {layui.layer.closeAll('tips');});
		},
		date_up : function(y, m, d){
			try{
				var date = NL(new Date(y, m-1, d)), festival = date.festival();
				var show = date.lDate, cls_name="";
				if(show == "初一") show = date.lMonth + "月";
				if(festival){
					var fes = "",item;
					for(var i=0;i<festival.length;i++){
						item = festival[i];
						if(!item) continue;
						if(!maps.hasOwnProperty(item.value)){
							fes = item.value;
							maps[fes] = "yes";
							break;
						}
					}
					if(fes){
						show = fes;
						cls_name = "hover";
					}
				}
				if(!fes && date.term){
					show = date.term;
					cls_name = "hover";
				}
				return '<div class="date" data-y="'+ y.toString() + '" data-m="' + m.toString() + '" data-d="' + d.toString() + '"><span class="number">' + d + '</span><br /><span' + (cls_name ? ' class="' + cls_name + '"' : '') + '>' + show + '</span></div>';
			}catch(ex){}
		}
	});
};
	
