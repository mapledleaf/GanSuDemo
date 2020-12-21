/*
 * 主界面js
 */
var menuMap = {};
var urlMap = {};
var menuCount = 0;
var urlCount = 0;
var menuTree = [];
function initMenuTree() {
	try {
		if (menuRender == null || menuRender.length == 0) {
			return;
		}

		menuCount = (menuRender == null) ? 0 : menuRender.length;
		if (menuCount < 1) {
			return;
		}

		for (var i = 0; i < menuCount; i++) {
			var menu = menuRender[i];
			menuMap[menu.id] = menu;
			if (menu.data && menu.data.length > 0) {
				urlMap[menu.data] = menu;
				urlCount++;
			}
		}
		// $('#menu-count').html(urlCount);

		urlMap['WELCOME'] = {
			id : 0,
			pId : null,
			name : '欢迎页',
			data : welcomeurl
		};

		renderMenuTree({
			'id' : 0
		});

		document.getElementById('LAY-system-side-menu').innerHTML = menuTree
				.join('');

		menuTree.length = 0;
		menuTree = null;
	} catch (e) {
		layer.alert(e.message);
	}
}

function renderMenuTree(pMenu) {
	for (var i = 0; i < menuCount; i++) {
		var menu = menuRender[i];
		if (!menu || menu.pId != pMenu.id) {
			continue;
		}

		if (!menu.data || menu.data.length == 0) {
			if (menu.pId == 0) {
				menuTree.push('<li data-name="m' + menu.id
						+ '" class="layui-nav-item">');
				menuTree.push('<a href="javascript:;" lay-tips="' + menu.name
						+ '" lay-direction="2">');
				menuTree.push('<i class="layui-icon layui-icon-app"></i>');
				menuTree.push('<span>' + menu.name + '</span>');
				menuTree.push('</a>');
			} else {
				menuTree.push('<dd data-name="m' + menu.id + '">');
				menuTree.push('<a href="javascript:;" lay-tips="' + menu.name
						+ '" lay-direction="2">');
				// menuTree.push('<i class="layui-icon
				// layui-icon-component"></i>');
				menuTree.push('<span>' + menu.name + '</span>');
				menuTree.push('</a>');
			}

			menuTree.push('<dl class="layui-nav-child">');
			renderMenuTree(menu);
			menuTree.push('</dl>');

			if (menu.pId == 0) {
				menuTree.push('</li>');
			} else {
				menuTree.push('</dd>');
			}
		} else {
			menuTree.push('<dd>');

			menuTree.push('<a lay-href="' + menu.data + '" lay-tips="' + menu.name
					+ '" lay-direction="2" data-tabid="' + menu.id + '">' + menu.name + '</a>');
			menuTree.push('</dd>');
		}
	}
}

function getMenuDesc(menu){
	if(!menu.desc){
		var a = [];
        var pMenu = menu;
        while (pMenu) {
            a.push(pMenu.name);
            pMenu = menuMap[pMenu.pId];
        }
        menu.desc = a.reverse().join(' - ');
        a.length = 0;
	}
	
	return menu.desc;
}

function setConfig(key, value, expires) {
	$.cookie(key, value, {
		'expires' : expires,
		'path' : rootPath + '/login/'
	});
}

function getConfig(key) {
	return $.cookie(key);
}

function saveUserConfig(code, value, callback){
	try{
		setRunning(false);
		postAjax(rootPath + "/login/settings!saveConfig.action", {
			"code": code,
			"value": value
		}, callback);
		setRunning(true);
	} catch(ex){
		
	}
}

function getUserConfig(code, value, callback){
	// try{
	// 	setRunning(false);
	// 	postAjax(rootPath + "/login/settings!findConfig.action", {
	// 		"code": code,
	// 		"value": value
	// 	}, callback);
	// 	setRunning(true);
	// } catch(ex){
	//
	// }
}

var loadingIndex = null;
function showLoading(show, deplay) {
	try {
		if (show === undefined || show === null) {
			show = true;
		}

		/*
		 * if(lockscreen){ return; }
		 */

		if (show == true) {
			if (loadingIndex == null) {
				loadingIndex = layer.msg('系统正在处理，请稍候...', {
					icon : 16,
					shade : 0.01,
					time : 0
				});
			}
		} else {
			if (loadingIndex != null) {
				layer.close(loadingIndex);
				loadingIndex = null;
			}
		}
	} catch (e) {
		layer.alert(e.message);
	}
}

function parseMenuUrl(menuUrl) {
	var url = null;
	if (menuUrl != null && menuUrl.charAt(0) != '/' && menuUrl.length >= 7) {
		var s = menuUrl.substring(0, 7).toLowerCase();
		if (s == "http://" || s == "https:/" || s == "file://"
				|| s.substring(0, 6) == "ftp://") {
			url = menuUrl;
		}
	}

	if (url == null) {
		url = basepath + menuUrl;
	}

	return url;
}

function parseMenuParam(menuUrl, param) {
	var url = menuUrl;
	if (param && param != '') {
		if (url.indexOf("?") > 0) {
			url += "&";
		} else {
			url += "?";
		}
		url += param;
	}

	return url;
}

function doMenu(menuId, menuName, menuUrl) {
	layui.index.openTabsPage(menuUrl, menuName, menuId);
}

var newMenuId = -1000;
function __openMenu__(url, param, options) {
	try {
		var menu = urlMap[url];
		if (!menu) {
			newMenuId--;
			menu = {
				id : newMenuId,
				pId : null,
				name : (options && options.title) || '新标签页',
				data : url
			};

			urlMap[url] = menu;
		}
		
		var isReload = (options && (options.reload == true)) ? true : false;
		var isExist = layui.index.isTabsPageExist(menu.id);

		var menuUrl = parseMenuParam(url, param);
		doMenu(menu.id, menu.name, menuUrl);
		if (isExist && isReload) {
			var frm = $('iframe[data-tabid="' + menu.id + '"]');
			if (frm) {
				frm.attr('src', parseMenuUrl(menuUrl));
			}
		}
	} catch (e) {
		layer.alert(e.message);
	}
}

function __closeMenu__(url) {
	try {
		if (url == null || url == "") {
			layui.admin.closeThisTabs();
		} else {
			var menu = urlMap[url];
			if (!menu) {
				return;
			}
			layui.admin.closeSelectTabs(menu.id);
		}
	} catch (e) {
		layer.alert(e.message);
	}
}

function __openWindow__(url, param, options) {
	try {
		var winTitle = null;
		if (options && options.title) {
			winTitle = options.title;
		}

		var winId = 0 - (new Date()).getTime();

		var menuUrl = parseMenuParam(url, param);

		doMenu(winId, winTitle || "新标签页", menuUrl);
	} catch (e) {
		layer.alert(e.message);
	}
}

function __closeWindow__(url) {
	try {
		if (url == null || url == "") {
			layui.admin.closeThisTabs();
		} else {
			layui.admin.closeSelectTabs(url);
		}
	} catch (e) {
		layer.alert(e.message);
	}
}

function __refreshMenu__(url, param) {
	try {
		var menu = urlMap[url];
		if (!menu) {
			return;
		}

		var isExist = (menu.id == "0") ? true : layui.index
				.isTabsPageExist(menu.id);
		if (!isExist) {
			return;
		}

		var menuUrl = parseMenuParam(parseMenuUrl(menu.data), param);

		var frm = $('iframe[data-tabid="' + menu.id + '"]');
		if (frm) {
			frm.attr('src', menuUrl);
		}
	} catch (e) {
		layer.alert(e.message);
	}
}

/*浮动菜单*/
var menuPopup = [];
var menuLevel = 0;
function initPopupMenu() {
    try {
        if (menuRender == null || menuRender.length == 0) {
            return;
        }
        
        renderPopupMenu({
            'id': 0
        });

        document.getElementById('LAY-system-popup-menu').innerHTML = menuPopup.join('');
       
        menuPopup.length = 0;
        menuPopup = null;
        

//		$(".nav li").on("click", function() {
//			$(this).addClass("active").siblings().removeClass("active");
//		})

		$(".root > li > a").on(
				"click",
				function() {
					$(this).siblings("ul.gan").toggle(300).find("i").toggleClass("layui-icon-down");
					$(this).find("> i").toggleClass("layui-icon-down");
//					$(this).parent().siblings().find(".gan").hide(300);
//					$(this).parent().siblings().children("a").find("i")
//							.removeClass("layui-icon-down");
				});

		$(".gan > li > a").on(
				"click",
				function() {
					$(this).siblings("ul.zhi").toggle(300).find("i").toggleClass("layui-icon-down");
					$(this).find("> i").toggleClass("layui-icon-down");
//					$(this).parent().siblings().find(".zhi").hide(300);
//					$(this).parent().siblings().children("a").find("i")
//							.removeClass("layui-icon-down");
				});

		$(".zhi > li > a").on(
				"click",
				function() {
					$(this).siblings("ul.ye").toggle(300).find("i").toggleClass("layui-icon-down");
					$(this).find("> i").toggleClass("layui-icon-down");
//					$(this).parent().siblings().find(".ye").hide(300);
//					$(this).parent().siblings().children("a").find("i")
//							.removeClass("layui-icon-down");
				});

		filterList($(".root"));
    } catch (e) {
    	layer.alert(e.message);
    }
}

function renderPopupMenu(pMenu) {
	menuLevel ++;
    for (var i = 0; i < menuCount; i++) {
        var menu = menuRender[i];
        if (!menu || menu.pId != pMenu.id) {
            continue;
        }

        if (!menu.data || menu.data.length == 0) {
        	var ul = "";
        	if(menuLevel == 1) {
        		ul = "list-unstyled root";
        	} else if(menuLevel == 2){
	        	ul = "gan";
	        } else if (menuLevel == 3){
	        	ul = "zhi";
	        } else if(menuLevel >= 4){
	        	ul = "ye";
	        }
        	
        	if(menu.pId == 0){
        		menuPopup.push('<div class="col-xs-3">');
        		menuPopup.push('<div class="item">');
        		menuPopup.push('<h5 class="title" id="p' + menu.id + '">' + menu.name + '</h5>');
        	}
	        else{
	        	menuPopup.push('<li class="' + ul + '-li">');
		        menuPopup.push('<a href="javascript:;" title="' + menu.name + '">');
		        menuPopup.push('<i class="layui-icon layui-icon-right"></i>');
		        menuPopup.push('' + menu.name + '');
		        menuPopup.push('</a>');
	        }
          	
        	
        	menuPopup.push('<ul class="' + ul + '">');
            renderPopupMenu(menu);
            menuPopup.push('</ul>');
            
            if(menu.pId == 0){
            	menuPopup.push('</div>');
            	menuPopup.push('</div>');
            } else {
            	menuPopup.push('</li>');
            }
        } else {
            menuPopup.push('<li>');
            menuPopup.push('<a class="cai" lay-href="' +  menu.data + '" title="' + menu.name + '" data-tabid="' + menu.id + '">' + menu.name + '</a>');
            menuPopup.push('</li>');
        }
    }
    
    menuLevel --;
}

function resizePopupMenu() {
	$(".menu .sidebar").height($(window).height() - 90);
}

function filterList(list) {
	$("#LAY-system-popup-search").keydown(function(e){
		if(e.keyCode == 13) {
			var filter = $(this).val();
			if (filter) {
				$matches = $(list).find(
						"a:Contains(" + filter + ")").parents();
				$("li", list).not($matches).slideUp(0);
				$matches.slideDown().parent().siblings().children(
						"a").find("i").addClass("layui-icon-down");
			} else {
				$(list).find("li").slideDown(0);
				$(".col-xs-3").show();
				$(".root").find("ul").hide();
				$(".root i").removeClass("layui-icon-down");
			}
			
			$(".root").each(function() {
				var $this = $(this);
				if ($this.find("li:visible").length > 0) {
					$this.prev("h5").slideDown(0);
				} else {
					$this.prev("h5").slideUp(0);
					$this.parents(".col-xs-4").hide();
				}
			});
			return false;
		}
	});
}

/*收藏夹*/
function initFavorite() {
	renderFavorite();
}

var dlgFavorite;
function openFavorite(){
	initFavoriteSel();
	
	clearFavoriteSel();
	
	$.each(favoriteList, function(index, value) { 
		addFavoriteSel(value);
	}); 
	
	//显示对话框
	if(dlgFavorite){
		dlgFavorite.show();
	} else{
		dlgFavorite = popupDiv('#favorite-dialog', '收藏夹管理器', 620);
	}
}

function renderFavorite(){
	try{
		var len = favoriteList.length;
		var pad = len.toString().length;
		var a = [];
		var menu = null;
		a.push('<dd layadmin-event="addfavorite"><a class="red" lay-direction="2" lay-tips="添加当前标签页到收藏夹">添加收藏夹</a></dd>');
		a.push('<dd layadmin-event="openfavorite"><a class="red" lay-direction="2" lay-tips="打开收藏夹管理器">管理收藏夹</a></dd>');
		var j = 0;
		for(var i = 0; i < len; i ++){
			menu = menuMap[favoriteList[i]];
			if(menu){
				if(j % 5 == 0){
					a.push("<hr />");
				}
				j ++;
				
				a.push('<dd>');
				a.push('<a lay-href="' + menu.data + '" lay-direction="2" lay-tips="' + getMenuDesc(menu)
						+ '" data-tabid="' + menu.id + '">' + menu.name + '</a>');
				a.push('</dd>');
			}
		}
		
		//$('#favorite-count').text(len);
		$('#LAY_app_favorite_list').html(a.join(''));
	} catch(e){
		layer.alert(e.message);
	}
}

function saveFavorite(){
	var a = [];
	$('#favorite-list li').each(function(){
		a.push($(this).attr('id').substring(1));	
	});
	
	if(powersi.tostring(a) == powersi.tostring(favoriteList)){
		layer.alert('没有修改，无需保存');
		return;
	}
	
	favoriteList = a.slice();
	renderFavorite();
	setTimeout("storeFavorite()", 0);
	
	closeFavorite();
	
	layui.layer.msg("保存收藏夹成功");
}

function closeFavorite(){
	if(dlgFavorite){
		dlgFavorite.hide();
	}
}

function addFavorite(menuId) {
	if(menuId < 0){
		return;
	}
	
	var len = favoriteList.length;
	for(var i = 0; i < len; i ++){
		if(favoriteList[i] == menuId){
			return;
		}
	}
	
	layui.layer.msg("添加收藏夹成功");
	
	favoriteList.push(menuId);
	renderFavorite();
	setTimeout("storeFavorite()", 0);
}

function storeFavorite(){
	var str = favoriteList.join(',');
	if(bookmarkEnabled == 'true'){
		postJSON(rootPath + "/login/settings!saveBookmark.action", {
			"bookmark": str
		});
	} else {
		setConfig('bookmark', str, 365);
	}
}

var favoriteTree = null;
var _lastFind = null;
var _nodeList = [];
function initFavoriteSel(){
	if(favoriteTree){
		return;
	}
    try{
    	//初始化收藏夹布局
        $("#favorite-layout").width(597).ligerLayout({
        	height: 360,
        	leftWidth: 300,
        	allowLeftResize: false
        });
        $('#favorite-tree').height(300);
        
      	//初始化收藏夹搜索
        $("#favorite-filter").keydown(function (e) {
            if (e.keyCode != 13) {
                return;
            }

            try{
            	var that = $(this);
            	that.addClass('loader');
            	
            	if(favoriteTree){
            		var txt = powersi.trim(that.val());
            		var len = powersi.length(_nodeList);
            		if(_lastFind !== txt) {
            			_lastFind = txt;
            			
            			for( var i = 0; i < len; i++) {
            				_nodeList[i].highlight = false;
            				favoriteTree.updateNode(_nodeList[i]);
            			}
                		_nodeList.length = 0;
                		
                		if(txt.length > 0){
                			_nodeList = favoriteTree.getNodesByParamFuzzy("name", txt, null);
                		}
                		
                		len = powersi.length(_nodeList);
                		for( var i = 0; i < len; i ++) {
                			_nodeList[i].highlight = true;
                			favoriteTree.updateNode(_nodeList[i]);
	            			}
            		}
            		
            		if(txt.length > 0){
                   		if(len > 0){
                   			var sel = 0;
                   			if(len > 1){
                   				var curNodes =  favoriteTree.getSelectedNodes();
                       			if(curNodes.length > 0){
                       				var curIdx =  getTreeOrder("favorite-tree", curNodes[0]);
                       				for(var i = 0; i < len; i ++){
                       					var idx = getTreeOrder("favorite-tree", _nodeList[i]);
                       					if(idx > curIdx){
                       						sel = i;
                       						break;
                       					}
                       				}
                       			}
                   			}
                       		
                   			var node = _nodeList[sel];
           					if(node.isParent){
           						favoriteTree.expandNode(node, true, false, true, false);
           					} else {
           						favoriteTree.expandNode(node.getParentNode(), true, false, true, false);
  	            			}
           					
           					favoriteTree.selectNode(node, false);
                   		} else {
                   			layer.alert('没有找到"' + txt + '"对应的项。');
                   		}
            		}
            	}
            	
 	            that.removeClass('loader').focus();
            } catch(e){
            	layer.alert(e.message);
            }

            e.returnValue = false;
            return false;
        });
      
    	var root = -1;
    	var trees = [];
    	trees.push({"id": 0, "pId": root, "name": systemname, "open": true, "font": {"color": "#f00"}, "iconSkin" : "system"});
        if (powersi.isarray(menuRender)) {
            $.each(menuRender, function(idx, menu){
                var treeNode = menu;
                if(menu.data && menu.data.length > 0){
                	treeNode["font"] = {"color": "#00f", "background-color":"transparent"};
                	treeNode["ismenu"] = "1";
                }
                treeNode["iconSkin"] = "right";
                
                trees.push(treeNode);
            });//end for menus
        }
        
        var upSetting = {
			view: {
				fontCss: getTreeFont,
				autoCancelSelected: false,
				selectedMulti: false,
				dblClickExpand: true,
				showLine: false,
				expandSpeed: 0
			},
			data: {
				simpleData: {
					enable: true,
					rootPId: root
				}
			},
			callback: {
				onClick: onFavoriteTreeClick
			}
    	};
        $.fn.zTree.init($("#favorite-tree"), upSetting, trees);
        trees.length = 0;
        
        favoriteTree = $.fn.zTree.getZTreeObj("favorite-tree");
        
        //允许拖放排序
        $("#favorite-list").sortable();
    } catch(e){
    	layer.alert(e.message);
    }
}

function onFavoriteTreeClick(event, treeId, treeNode, clickFlag){
	if(treeNode.ismenu == "1"){
		addFavoriteSelByNode(treeNode);
	} else {
		//设置focus为true时，滚动条乱跳，应该是弹窗对话框的问题导致的，暂时屏蔽自动聚焦的功能
		//expandNode(treeNode, expandFlag, sonSign, focus, callbackFlag)
		favoriteTree.expandNode(treeNode, !treeNode.open, false, false, false);
	}
}

function addFavoriteSel(menuId){
	if(favoriteTree) {
		var treeNode = favoriteTree.getNodeByParam("id", menuId);
		if(treeNode){
			addFavoriteSelByNode(treeNode);
		}
	}
}

function addFavoriteSelByNode(treeNode) {
	if(treeNode == null){
		return;
	}
	
	var a = [];
	a.push('<li id="r' + treeNode.id + '" class="ui-state-default ui-element ui-draggable">');
	a.push('<i class="icon-sort blue" title="按住已选择项拖动，可以调整顺序"></i>');
	a.push('<span class="ui-text">' + treeNode.name + '</span>');
	a.push('<a href="javascript:void(0);" onclick="removeFavoriteSel(' + treeNode.id + ')"><i class="icon-minus blue" title="取消选择"></i></a>');
	a.push('</li>');
	
	$("#favorite-list").append(a.join(''));
	
	$("#r" + treeNode.id ).hover(function() {
		$( this ).addClass("ui-state-hover" );
	}, function() {
		$( this ).removeClass("ui-state-hover" );
	}).dblclick(function(){
		$(this).find('a:first').click();
	});
	
	favoriteTree.hideNode(treeNode);
	updateFavoriteCount();
}

function removeFavoriteSel(menuId){
	var treeNode = favoriteTree.getNodeByParam("id", menuId);
	if(treeNode){
		favoriteTree.showNode(treeNode);
		
		 $("#r" + menuId).remove();
		 updateFavoriteCount();
	}
}

function clearFavoriteSel(){
	$('#favorite-list').html('');
	if(favoriteTree){
		favoriteTree.showNodes(favoriteTree.getNodesByParam("isHidden", true));
		updateFavoriteCount();
	}
}

function updateFavoriteCount(){
	var size = $('#favorite-list li').size();
	$('#favorite-count-dlg').text(size);
}

function resizePopupMessage() {
	$("#LAY_app_messagelist").height($(window).height() - 110);
}

var messageDlgs = {};
function initMessage() {
	try{
		if(messageList !== 'undefined' && messageList !== null && messageList.length > 0){
			popupMessageList(powersi.tojson(messageList));
		}
		
	  	if(messageEnable){
	  		if(messageInterval < 10){
	  			messageInterval = 60;
	  		}
	  		
	  		setTimeout("pollingMessage()", messageInterval * 1000);
	  	}
	} catch(ex){alert(ex.message);}
}

function openMsgDialog(url, height, width) {
	var dlg = messageDlgs[url];
	if(dlg){
		dlg.close();
	}
	
	//layer.closeAll('page');
	$('#LAY_app_messagelist ul li[data-id="' + url + '"]').remove();
	togglePopupMessage();
	
	popupDialog(rootPath + "/" + url, height, width);
}

function togglePopupMessage(){
	var size = $('#LAY_app_messagelist ul li').size();
	$('#LAY-app-message-dot').html(size);
	if(size > 0){
		$('#LAY_app_messagenone').hide();
		$('#LAY_app_messagelist').show();
		$('#LAY-app-message-dot').show();
	} else {
		$('#LAY_app_messagenone').show();
		$('#LAY_app_messagelist').hide();
		$('#LAY-app-message-dot').hide();
	}
}

function appendMsgInfo(msg){
	if(!msg || !msg.diag_url){
		return;
	}
	
	$('#LAY_app_messagelist ul > li[data-id="' + msg.diag_url + '"]').remove();
	
	var a = [];
	a.push('<li class="list-item" data-id="' + msg.diag_url + '">');
	
	a.push('<a href="javascript:void(0);"class="list-left">');
	if(msg.ms_type == "21"){
		a.push('<span class="w-40 circle i-yellow">');
		a.push('<i class="fa fa-bullhorn"></i>');
	} else if(msg.ms_type == "11") {
		a.push('<span class="w-40 circle i-green">');
		a.push('<i class="fa fa-comment"></i>');
	} else {
		a.push('<span class="w-40 circle i-blue">');
		a.push('<i class="fa fa-bolt"></i>');
	}
	
	a.push('</span>');
	a.push("</a>");
	
	a.push('<div class="list-body">');
	a.push('<div>');
	a.push("<a href='javascript:void(0);' class='text-ellipsis' onclick='openMsgDialog(\"");
	a.push(msg.diag_url);
	a.push("\", " + msg.diag_height + "," + msg.diag_width);
	a.push(")'>");
	a.push(msg.ms_content);
	a.push("</a>");
	a.push("</div>");
	a.push('<small class="text-muted text-ellipsis">' + msg.send_date + '</small>');
	a.push('</div>');
	
	a.push('</li>');
	
	$('#LAY_app_messagelist ul').prepend(a.join(''));
}

function showMsgPopup(msg){
	try{
		if(!msg || !msg.diag_url){
    		return;
    	}
    	
    	var a = [];
		a.push("<div class='divMsg'>");
		a.push("<a href='javascript:void(0);' onclick='openMsgDialog(\"");
		a.push(msg.diag_url);
		a.push("\", " + msg.diag_height + "," + msg.diag_width);
		a.push(")'>");
		a.push(msg.ms_content);
		a.push("</a></div>");
		
		var dlg = messageDlgs[msg.diag_url];
		if(dlg){
			dlg.set('content', a.join(''));
			dlg.active();
		} else{
			dlg = $.ligerDialog.open({
				title: (powersi.length(msg.ms_title) > 0) ? msg.ms_title : systemname,
				content: a.join(''),
				showType: 'slide',
	            modal: false,
	            width: 'auto',
	            height: 'auto',
	            fixedType: 'se',
	            type: 'none',
	            isDrag: true,
	            isResize: false,
	            showMax: false,
	            showToggle: false,
	            showMin: false,
	            isHidden: false,
	            onClosed: function(){
	            	delete messageDlgs[msg.diag_url];
	            }
			});
			
			messageDlgs[msg.diag_url] = dlg;
		}
	} catch(ex){
		
	}
}

function popupMessageList(messages){
	if(!powersi.isvalue(messages) || !powersi.isarray(messages) || messages.length == 0){
		return;
	}
		
	for(var idx in messages){
		appendMsgInfo(messages[idx]);
		showMsgPopup(messages[idx]);
	}
	
	togglePopupMessage();
}

function pollingMessage() {
	try{
	    return jQuery.ajax({
	        url: rootPath + "/message/MessageAction!pollingMessage.action",
	        data: null,
	        dataType: "json",
	        processData: true,
	        type: "POST",
	        global: false,
	        error: function(jqXHR, textStatus, errorThrown) {
	        	
	        },
	        success: function(json, textStatus, jqXHR) {
	        	if(checkJSONResult(json, false)){//不显示错误信息
	        		popupMessageList(json.data);
	    	    }

	        	popupMessageList(json.data);
	        	
	        	//只有调用成功才继续轮询消息
	        	setTimeout("pollingMessage()", messageInterval * 1000);
	        }
	  });
	} catch(ajaxException){
		//alert(ajaxExcption.message);
	}
}

function openFirstUsed(){
	layer.closeAll();
	var a = [];
	a.push('<div style="padding: 30px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300; font-size: 14px;">');
	a.push('欢迎您，' + username + '！<br>');
	a.push('<br>如果您是首次使用<span style="color:#FF5722;">' + systemname + '</span>，建议您快速简单学习下基本操作。<br>');
	a.push('<br>快速指南操作支持键盘左右切换，esc键退出，点击灰色小圆点可以快速切换内容。');
	a.push('</div>');
	layer.open({
	   type: 1
	   ,title: false
	   ,closeBtn: false
	   ,offset: '50px'
	   ,area: '300px;'
	   ,shade: 0.8
	   ,id: 'LAY_layuipro'
	   ,btn: ['开始学习', '残忍拒绝']
	   ,yes: function(index, layero){
		 layer.closeAll();
		 startQuickGuide();
		}
	   ,btnAlign: 'c'
	   ,moveType: 1 //拖拽模式，0或者1
	   ,content: a.join('')
	});
}
	
function startQuickGuide() {
	var defineSteps = [ {
		element : ".layui-logo",
		description : "显示应用系统名称",
		position : "right"
	}, {
		element : "#LAY-system-side-menu",
		description : "显示应用系统菜单树",
		position : "floating"
	}, {
		element : "#LAY_app_flexible",
		description : "隐藏或者显示左边侧边栏",
		position : "right"
	}, {
		element : ".layui-icon-more",
		description : "显示应用系统菜单列表，支持菜单搜索",
		position : "right"
	}, {
		element : ".layui-icon-star",
		description : "添加或者管理收藏夹，列表显示收藏的菜单列表",
		position : "right"
	}, {
		element : ".layui-icon-home",
		description : "显示欢迎页",
		position : "right"
	}, {
		element : ".layui-icon-refresh-1",
		description : "重新加载当前打开的标签页",
		position : "right"
	}, {
		element : "#LAY-app-search",
		description : "搜索需要查询的关键字",
		position : "bottom"
	}, {
		element : ".layui-icon-notice",
		description : "显示最新的公告和消息",
		position : "left"
	}, {
		element : ".layui-icon-theme",
		description : "设置应用系统的主题和字体大小",
		position : "left"
	}, {
		element : ".layui-icon-screen-full",
		description : "应用系统进入全屏或者退出全屏",
		position : "left"
	}, {
		element : "#LAY-app-user > cite",
		description : "显示用户名，下拉显示用户相关的操作",
		position : "left"
	}, {
		element : ".layui-icon-close",
		description : "退出应用系统",
		position : "left"
	}, {
		element : ".layui-tab",
		description : "显示打开的菜单列表",
		position : "bottom"
	}, {
		element : ".layui-icon-prev",
		description : "左移菜单标签栏",
		position : "right"
	}, {
		element : ".layui-icon-next",
		description : "右移动菜单标签栏",
		position : "left"
	}, {
		element : ".layui-icon-down",
		description : "下拉显示标签栏相关的操作",
		position : "left"
	}, {
		element : ".layui-body",
		description : "显示业务主界面",
		position : "floating"
	} ];
	//删除不可见对象
	var steps = [];
	for (var i = 0; i < defineSteps.length; i++) {
		var step = defineSteps[i];
		var elem = $(step.element + ":visible");
		if (elem.length > 0 && elem.parent().is(":visible")) {
			steps.push({
				element : elem[0],
				intro : step.description,
				position : step.position
			});
		}
	}
	var intro = introJs();
	intro.setOptions({
		'prevLabel' : '&larr; 上一步',
		'nextLabel' : '下一步 &rarr;',
		'skipLabel' : '跳过',
		'doneLabel' : '完成',
		'showProgress' : false,
		'showStepNumbers' : false,
		'exitOnOverlayClick' : false,
		'keyboardNavigation' : true,
		'steps' : steps
	});

	intro.onbeforechange(function(el) {
		if (el.id == "LAY-system-side-menu") {
			$('#LAY-system-side-menu').css("background", "black");
		} else {
			$('#LAY-system-side-menu').css("background", "0 0");
		}
	});
	
	intro.oncomplete(function() {
	  popupMessage("感谢您的使用，祝您工作顺利！");
	});
	
	intro.onexit(function(){
		$('#LAY-system-side-menu').css("background", "0 0");
	});

	intro.start();
}
