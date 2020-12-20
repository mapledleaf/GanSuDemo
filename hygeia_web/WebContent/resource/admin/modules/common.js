/**

 @Name：layuiAdmin 公共业务
 @Author：贤心
 @Site：http://www.layui.com/admin/
 @License：LPPL
 */
layui.define(function (exports) {
    var $ = layui.$
        , layer = layui.layer
        , laytpl = layui.laytpl
        , setter = layui.setter
        , view = layui.view
        , admin = layui.admin

    //退出
    admin.events.logout = function () {
        layer.confirm('您确认退出' + (layui.setter.appname || '系统')  + '吗?', {offset: 't', anim: 1, title:'退出'}, function(index){
        	if(layui.setter.reloginurl){
        		setTimeout('window.location = "' + layui.setter.reloginurl + '&action=exit";', 0);
        	} else{
        		 //执行退出接口
                admin.req({
                    url: layui.setter.base + 'json/user/logout.js'
                    , type: 'get'
                    , data: {}
                    , done: function (res) {
                        //这里要说明一下：done 是只有 response 的 code 正常才会执行。而 succese 则是只要 http 为 200 就会执行

                        //清空本地记录的 token，并跳转到登入页
                        admin.exit(function () {
                            location.href = 'user/login.html';
                        });
                    }
                });
        	}
        	
            layer.close(index);
        });
    };
    
    //重登录
    admin.events.relogin = function () {
        layer.confirm('您确认退出' + (layui.setter.appname || '系统')  + '吗?', {offset: 't', anim: 1, title:'重登录'}, function(index){
        	if(layui.setter.reloginurl){
        		setTimeout('window.location = "' + layui.setter.reloginurl + '&action=relogin";', 0);
        	} else{
        		 //执行退出接口
                admin.req({
                    url: layui.setter.base + 'json/user/logout.js'
                    , type: 'get'
                    , data: {}
                    , done: function (res) {
                        //这里要说明一下：done 是只有 response 的 code 正常才会执行。而 succese 则是只要 http 为 200 就会执行

                        //清空本地记录的 token，并跳转到登入页
                        admin.exit(function () {
                            location.href = 'user/login.html';
                        });
                    }
                });
        	}
        	
            layer.close(index);
        });
    };

    //打开新窗口
    admin.events.openThisTabs = function () {
        var ELEM_IFRAME = '.layadmin-iframe'
            , length = $('.layadmin-tabsbody-item').length;

        if (admin.tabsPage.index >= length) {
            admin.tabsPage.index = length - 1;
        }

        var iframe = admin.tabsBody(admin.tabsPage.index).find(ELEM_IFRAME);
        window.open($(iframe[0]).attr('src'));
    };

    //弹出菜单面板
    admin.events.menu = function () {
        var func = function() {
        	resizePopupMenu();
	        
        	admin.popupRight({
	            id: 'LAY_adminPopupMenu'
	            , offset: '50px'
	            , area: '100%'
	            , anim: 1
	            , skin: 'layui-anim layui-layer-adminMenu'
	            , content: $('#LAY_app_popupmenu')
	            , success: function (layero, index) {
	                //view(this.id).render('system/menu');
	            	$('#LAY_app_popupmenu').show();
	            }
	        	, end: function() {
	        		$('#LAY_app_popupmenu').hide();
	        	}
        	});
        };
        
        if($('#LAY_app_popupmenu').html() == "") {
    		view("LAY_app_popupmenu").render('system/menu').done(func);
    		
    		$("#LAY_app_popupmenu").on('click', '*[lay-href]', function () {
    			layer.closeAll('page');
    	    });
    	}  else {
    		func();
    	}
    };
    
    //弹出修改用户信息
    admin.events.userinfo = function () {
    	/*
        layer.open({
        	  type: 2
        	  , shade: false
        	  , offset: 't'
        	  , area: ['600px', '490px']
        	  , maxmin: false
        	  , title: '用户信息'
        	  , content: setter.contextpath + "/login/settings!queryUserInfo.action"
        	  , zIndex: layer.zIndex
        	  , success: function(layero, index){
        		//layer.iframeAuto(index) 
        	    layer.setTop(layero);
        	  }
        });*/
    	popupDialog(rootPath + "/login/settings!queryUserInfo.action", 470, 600);
    };
    
    //弹出修改密码
    admin.events.changepwd = function () {
    	/*
    	layer.open({
      	  type: 2
      	  , shade: false
      	  , offset: 't'
      	  , area: ['450px', '430px']
      	  , maxmin: false
      	  , title: '修改密码'
      	  , content: setter.contextpath + "/user/ChangePassword.action?method=open"
      	  , zIndex: layer.zIndex
      	  , success: function(layero, index){
      		//layer.iframeAuto(index) 
      	    layer.setTop(layero);
      	  }
      });*/
    	popupDialog(rootPath + "/user/ChangePassword.action?method=open", 420, 430);
    };
    
    //显示主页
    admin.events.home = function () {
    	layui.index.openTabsPage(layui.setter.welcomeurl, "欢迎页", "WELCOME");
    };
    
    //收藏夹管理
    admin.events.openfavorite = function() {
    	var func = function() {
    		openFavorite();
    	};
    	
    	if($('#LAY_app_favorite').html() == "") {
    		view("LAY_app_favorite").render('system/favorite').done(func);
    	} else{
    		func();
    	}
    };
    
    //添加收藏夹
    admin.events.addfavorite = function() {
    	 if (!admin.tabsPage.index) return;
    	 var tabid = $('#LAY_app_tabsheader>li').eq(admin.tabsPage.index).attr("lay-id");
    	 if(tabid && tabid != "0") {
    		 addFavorite(tabid);
    	 }
    };
    
    //弹出消息提示框
    admin.events.message = function (othis) {
        //othis.find('.layui-badge-dot').hide();

        var func = function() {
        	togglePopupMessage();
        	
        	resizePopupMessage();
	        
        	admin.popupRight({
	            id: 'LAY_adminPopupMessage'
	            ,shade: 0
	            ,content: $('#LAY_app_popupmessage')
	            ,success: function (layero, index) {
	            	$('#LAY_app_popupmessage').show();
	            }
	        	,end: function() {
	        		$('#LAY_app_popupmessage').hide();
	        	}
        	});
        };
        
        if($('#LAY_app_popupmessage').html() == "") {
    		view("LAY_app_popupmessage").render('system/message').done(func);
    	}  else {
    		func();
    	}
    };
    
    //快速指南
    admin.events.guide = function (othis) {
    	startQuickGuide();
    };

    //对外暴露的接口
    exports('common', {});
});