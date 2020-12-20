/**

 @Name：layuiAdmin iframe版主入口
 @Author：贤心
 @Site：http://www.layui.com/admin/
 @License：LPPL
    
 */
 
layui.extend({
  setter: 'config' //配置模块
  ,admin: 'lib/admin' //核心模块
  ,view: 'lib/view' //视图渲染模块
}).define(['setter', 'admin', 'form'], function(exports){
  var setter = layui.setter
  ,element = layui.element
  ,admin = layui.admin
  ,tabsPage = admin.tabsPage
  ,view = layui.view
  ,form = layui.form
  ,layer = layui.layer
  ,$ = layui.$
  
  //判断标签页是否存在
  ,isTabsPageExist = function(id) {
	//遍历页签选项卡
    var matchTo = false
    ,tabs = $('#LAY_app_tabsheader>li');
    id = (id || "").toString();
    tabs.each(function(index){
      var li = $(this)
      ,layid = li.attr('lay-id');
      
      if(layid == id){
        matchTo = true;
        return false;
      }
    });
    
    return matchTo;
  }
  //打开标签页
  ,openTabsPage = function(href, text, id){
    //遍历页签选项卡
    var matchTo = false
    ,tabs = $('#LAY_app_tabsheader>li');
    
    if(href == null || href.length == 0){
    	return;
    }
    //处理url
    var url = null;
    if(href.charAt(0) != '/' && href.length >= 7){
		var s = href.substring(0, 7).toLowerCase();
		if(s == "http://" || s == "https:/" || s == "file://" || s.substring(0, 6) == "ftp://"){
			url = href;
		}
	}
    if(url == null){
    	if(href.charAt(0) == '/') {
    		url = (layui.setter.contextpath || "") + href;
    	} else {
    		url = href;
    	}
    }
    var path = url.replace(/(^http(s*):)|(\?[\s\S]*$)/g, '');
    //处理id
    if(!id){
    	id = url.toString();
    }
    
    //查找tab
    tabs.each(function(index){
      var li = $(this)
      ,layid = li.attr('lay-id');
      
      if(layid == id){
        matchTo = true;
        tabsPage.index = index;
        return false;
      }
    });
    
    text = text || '新标签页';
    
    if(setter.pageTabs){
      //如果未在选项卡中匹配到，则追加选项卡
      if(!matchTo){
        $(APP_BODY).append([
          '<div class="layadmin-tabsbody-item layui-show">'
          ,'<div class="layadmin-tabs-loading" data-tabid="' + id + '" style="display:block;"></div>'
          ,'<iframe frameborder="0" class="layadmin-iframe" data-tabid="' + id + '" src="'+ url +'"></iframe>'
          ,'</div>'
        ].join(''));
        
        
        tabsPage.index = tabs.length;
        var tab = element.tabAdd(FILTER_TAB_TBAS, {
          title: '<span>'+ text +'</span>'
          ,id: id
          ,attr: path
        });
        
        //显示加载图标
        var iframe = $(APP_BODY).find('.layadmin-iframe[data-tabid="' + id + '"]');
        var loadingDiv = $(APP_BODY).find('.layadmin-tabs-loading[data-tabid="' + id + '"]');
        //监听load（解决出错加载图标一直显示的问题）
        var loadingTimer = setInterval(function() {
        	try{
	    		 var iframeDoc = iframe[0].contentDocument || iframe[0].contentWindow.document;
    			 if(iframeDoc.readyState == "complete" || iframeDoc.readyState == "loaded" || iframeDoc.readyState == "interactive") {
    				loadingDiv.hide();
 	        		if(loadingTimer) {
 	        			clearInterval(loadingTimer);
 	            		loadingTimer = null;
 	        		}
 	        	 }
        	} catch(ex) {
        		if(loadingTimer) {
        			clearInterval(loadingTimer);
            		loadingTimer = null;
        		}
        	}
        }, 1000);
        
        //关闭加载图标
        iframe.on('load.tab', function (){
        	try{
        		if(loadingTimer) {
        			clearInterval(loadingTimer);
            		loadingTimer = null;
        		}
        	} catch(ex){
        		
        	}
        	
        	try{
        		loadingDiv.hide();
        		
        		//根据网页内容设置标题
        		if(text == '' || text == '新标签页') {
	        		var title = iframe[0].contentWindow.document.title;
	        		if(title){
	        			$('#LAY_app_tabsheader>li[lay-id="' + id + '"]').attr("lay-tips", title).find("span").text(title);
	        			$('#LAY_app_tabsselect option[value="' + id + '"]').remove();
	        			$('#LAY_app_tabsselect').append('<option value="' + id + '">' + title + '</option>');
	        			form.render('select', 'layadmin-pagetabs-form');
	        		}
        		}
        	} catch(ex){
        		//忽略跨域错误
        	}
        });
         
        //显示tab提示
        $('#LAY_app_tabsheader>li[lay-id="' + id + '"]').attr("lay-tips", text);
        
        //刷新下拉标签页框
        $('#LAY_app_tabsselect').append('<option value="' + id + '">' + text + '</option>');
        form.render('select', 'layadmin-pagetabs-form');
      }
    } else {
      var iframe = admin.tabsBody(admin.tabsPage.index).find('.layadmin-iframe');
      iframe[0].contentWindow.location.href = url;
    }

    //定位当前tabs
    element.tabChange(FILTER_TAB_TBAS, id);
    admin.tabsBodyChange(tabsPage.index, {
      url: url
      ,text: text
      ,id: id
    });
  }
  
  ,removeTabsPage = function (ids) {
      if (setter.pageTabs) {
          if ($.isArray(ids)) {
              $.each(ids, function (i, id) {
                  if (id && id !== "") {
                      $('#LAY_app_tabsselect option[value="' + id.toString() + '"]').remove();
                  }
              });
          } else {
              if (ids && ids !== "") {
                  $('#LAY_app_tabsselect option[value="' + ids.toString() + '"]').remove();
              }
          }

          form.render('select', 'layadmin-pagetabs-form');
          
          layer.closeAll('tips');
      }
  }
  
  ,APP_BODY = '#LAY_app_body', FILTER_TAB_TBAS = 'layadmin-layout-tabs'
  , $win = $(window);
  
  //初始
  if(admin.screen() < 2) admin.sideFlexible();
  
  //将模块根路径设置为 controller 目录
  layui.config({
    base: setter.base + 'modules/'
  });
  
  //扩展 lib 目录下的其它模块
  layui.each(setter.extend, function(index, item){
    var mods = {};
    mods[item] = '{/}' + setter.base + 'lib/extend/' + item;
    layui.extend(mods);
  });
  
  view().autoRender();
  
  //初始化标签页下拉选择
  if (setter.pageTabs) {
      form.on('select(layadmin-pagetabs-select)', function (data) {
          var value = data.value;
          if (value == '') {
              return;
          }

          openTabsPage(data.value, null, data.value);
          $('#LAY_app_tabsselect').val("");
          form.render('select', 'layadmin-pagetabs-form');
      });
  }
  //加载公共模块
  layui.use('common');

  //对外输出
  exports('index', {
    openTabsPage: openTabsPage
    ,removeTabsPage: removeTabsPage
    ,isTabsPageExist: isTabsPageExist
  });
});
