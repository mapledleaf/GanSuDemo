<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.TreeMap"%>
<%@page import="com.powersi.hygeia.web.util.TextHelper,com.powersi.hygeia.web.util.JsonHelper,com.powersi.hygeia.framework.util.UtilFunc"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	Map<String, String> mapText = new TreeMap<String, String>();
	Map<String, String> mapIcon = new TreeMap<String, String>();
	List<String> keys = new ArrayList(TextHelper.keySet());
	Collections.sort(keys);
	for(String key : keys){
		if(UtilFunc.startsWithIgnoreCase(key, "button_")){
			String text = TextHelper.getText(key, null);
			if(text != null){
				String icon = TextHelper.getIcon(key);
				if(icon != null){
					mapText.put(key, text);
					mapIcon.put(key, icon);
				}
			}
		}
	}
	
	String buttonText = JsonHelper.toJson(mapText);
	String buttonIcon = JsonHelper.toJson(mapIcon);
	
	/*a标记*/
	//<a href[^>]*>
%>
<powersi:html>
<head>
<powersi:head title="图标库" />
<link rel="stylesheet" href="${strutsPath }/js/plugins/layui/css/layui.css" charset="utf-8" media="all" />
<style type="text/css">
#tab-pane1 i[class*="icon-"] {
	font-size: 24px;
	color: #33383e;
	display: inline-block;
  	width: 1.1428571428571428em;
  	text-align: right;
  	padding-right: 0.2857142857142857em;
}

#tab-pane2 .l-icon,#tab-pane2 span {
	float: left;
	padding-left: 10px;
	line-height: 24px;
}

#tab-pane3 span {
	padding-left: 10px;
}

#tab-pane4 i[class*="fa-"] {
	font-size: 24px;
	color: #33383e;
	width: 1.28571429em;
  	text-align: center;
}

#tab-pane2 div[class*="l-icon"] {
	font-size: 24px;
	color: #33383e;
	width: 1.28571429em;
  	text-align: center;
}

.btn {
	color: #33383e;
	min-width: 75px;
}

.tab-pane{
	padding: 0 15px;
}

.col-3 {
	padding: 10px 5px;
}

/* 宫格 */
.site-doc-icon{padding: 15px; font-size: 0; background: transparent; margin: 0 auto;}
.site-doc-icon li{display: inline-block; vertical-align: middle; width: 127px; height: 127px; line-height: 25px; padding: 20px 0; margin-right: -1px; margin-bottom: -1px; border: 1px solid #e2e2e2; font-size: 14px; text-align: center; color: #666; transition: all .3s; -webkit-transition: all .3s;}
.site-doc-anim li{height: auto;}
.site-doc-icon li .layui-icon{display: inline-block; font-size: 36px;}

.site-doc-icon li .doc-icon-name,
.site-doc-icon li .doc-icon-code{color: #c2c2c2;}
.site-doc-icon li .doc-icon-code{display: none;}
.site-doc-icon li .doc-icon-fontclass{height: 40px; line-height: 20px; padding: 0 5px; font-size: 13px; color: #333; }
.site-doc-icon li:hover{background-color: #f2f2f2; color: #000;}
</style>
<script type="text/javascript">
$(function(){
	var texts = <%=buttonText %>;
	var icons = <%=buttonIcon %>;
	var a = [];
	a.push('<div class="row">');
	$.each(texts, function(key, value){
		a.push('<div class="col-3">');
		a.push('<button type="button" class="btn btn-default"><i class="' + icons[key] + '"></i> ' + value + '</button>');
		a.push('<span>' + key + '</span>');
		a.push('</div>');
	});
	a.push('</div>');
	
	$('#tab-pane3').html(a.join(''));
});
</script>
</head>
<body>
<div class="tabbable">
	<ul class="nav nav-tabs" id="tabs1">
		<li class="active">
			<a data-toggle="tab" href="#tab-pane1" id="tab1"> 
				<i class="icon-fa fa-flag red"></i> Font Awesome 3
			</a>
		</li>

		<li>
			<a data-toggle="tab" href="#tab-pane4" id="tab4"> 
				<i class="icon-fa fa-flag-checkered yellow"></i> Font Awesome 4
			</a>
		</li>
		
		<li>
			<a data-toggle="tab" href="#tab-pane2" id="tab2"> 
				<i class="icon-fa fa-table green"></i> DataGrid Toolbar
			</a>
		</li>
		
		<li>
			<a data-toggle="tab" href="#tab-pane3" id="tab3"> 
				<i class="icon-fa fa-play blue"></i> Button Text And Icon With Key
			</a>
		</li>
		
		<li>
			<a data-toggle="tab" href="#tab-pane5" id="tab4"> 
				<i class="icon-fa fa-list orange"></i> LayUI Icons
			</a>
		</li>
	</ul>

	<div class="tab-content content">
		<div id="tab-pane1" class="tab-pane active">
			<div class="grid">
				<div class="row">
					<div class="col-3"><i class="icon-adjust"></i> icon-adjust</div>
					<div class="col-3"><i class="icon-anchor"></i> icon-anchor</div>
					<div class="col-3"><i class="icon-archive"></i> icon-archive</div>
					<div class="col-3"><i class="icon-asterisk"></i> icon-asterisk</div>
					<div class="col-3"><i class="icon-ban-circle"></i> icon-ban-circle</div>
					<div class="col-3"><i class="icon-bar-chart"></i> icon-bar-chart</div>
					<div class="col-3"><i class="icon-barcode"></i> icon-barcode</div>
					<div class="col-3"><i class="icon-beaker"></i> icon-beaker</div>
					<div class="col-3"><i class="icon-beer"></i> icon-beer</div>
					<div class="col-3"><i class="icon-bell"></i> icon-bell</div>
					<div class="col-3"><i class="icon-bell-alt"></i> icon-bell-alt</div>
					<div class="col-3"><i class="icon-bolt"></i> icon-bolt</div>
					<div class="col-3"><i class="icon-book"></i> icon-book</div>
					<div class="col-3"><i class="icon-bookmark"></i> icon-bookmark</div>
					<div class="col-3"><i class="icon-bookmark-empty"></i> icon-bookmark-empty</div>
					<div class="col-3"><i class="icon-briefcase"></i> icon-briefcase</div>
					<div class="col-3"><i class="icon-bug"></i> icon-bug</div>
					<div class="col-3"><i class="icon-building"></i> icon-building</div>
					<div class="col-3"><i class="icon-bullhorn"></i> icon-bullhorn</div>
					<div class="col-3"><i class="icon-bullseye"></i> icon-bullseye</div>
					<div class="col-3"><i class="icon-calendar"></i> icon-calendar</div>
					<div class="col-3"><i class="icon-calendar-empty"></i> icon-calendar-empty</div>
					<div class="col-3"><i class="icon-camera"></i> icon-camera</div>
					<div class="col-3"><i class="icon-camera-retro"></i> icon-camera-retro</div>
					<div class="col-3"><i class="icon-certificate"></i> icon-certificate</div>
					<div class="col-3"><i class="icon-check"></i> icon-check</div>
					<div class="col-3"><i class="icon-check-empty"></i> icon-check-empty</div>
					<div class="col-3"><i class="icon-check-minus"></i> icon-check-minus</div>
					<div class="col-3"><i class="icon-check-sign"></i> icon-check-sign</div>
					<div class="col-3"><i class="icon-circle"></i> icon-circle</div>
					<div class="col-3"><i class="icon-circle-blank"></i> icon-circle-blank</div>
					<div class="col-3"><i class="icon-cloud"></i> icon-cloud</div>
					<div class="col-3"><i class="icon-cloud-download"></i> icon-cloud-download</div>
					<div class="col-3"><i class="icon-cloud-upload"></i> icon-cloud-upload</div>
					<div class="col-3"><i class="icon-code"></i> icon-code</div>
					<div class="col-3"><i class="icon-code-fork"></i> icon-code-fork</div>
					<div class="col-3"><i class="icon-coffee"></i> icon-coffee</div>
					<div class="col-3"><i class="icon-cog"></i> icon-cog</div>
					<div class="col-3"><i class="icon-cogs"></i> icon-cogs</div>
					<div class="col-3"><i class="icon-collapse"></i> icon-collapse</div>
					<div class="col-3"><i class="icon-collapse-alt"></i> icon-collapse-alt</div>
					<div class="col-3"><i class="icon-collapse-top"></i> icon-collapse-top</div>
					<div class="col-3"><i class="icon-comment"></i> icon-comment</div>
					<div class="col-3"><i class="icon-comment-alt"></i> icon-comment-alt</div>
					<div class="col-3"><i class="icon-comments"></i> icon-comments</div>
					<div class="col-3"><i class="icon-comments-alt"></i> icon-comments-alt</div>
					<div class="col-3"><i class="icon-compass"></i> icon-compass</div>
					<div class="col-3"><i class="icon-credit-card"></i> icon-credit-card</div>
					<div class="col-3"><i class="icon-crop"></i> icon-crop</div>
					<div class="col-3"><i class="icon-dashboard"></i> icon-dashboard</div>
					<div class="col-3"><i class="icon-desktop"></i> icon-desktop</div>
					<div class="col-3"><i class="icon-download"></i> icon-download</div>
					<div class="col-3"><i class="icon-download-alt"></i> icon-download-alt</div>
					<div class="col-3"><i class="icon-edit"></i> icon-edit</div>
					<div class="col-3"><i class="icon-edit-sign"></i> icon-edit-sign</div>
					<div class="col-3"><i class="icon-ellipsis-horizontal"></i> icon-ellipsis-horizontal</div>
					<div class="col-3"><i class="icon-ellipsis-vertical"></i> icon-ellipsis-vertical</div>
					<div class="col-3"><i class="icon-envelope"></i> icon-envelope</div>
					<div class="col-3"><i class="icon-envelope-alt"></i> icon-envelope-alt</div>
					<div class="col-3"><i class="icon-eraser"></i> icon-eraser</div>
					<div class="col-3"><i class="icon-exchange"></i> icon-exchange</div>
					<div class="col-3"><i class="icon-exclamation"></i> icon-exclamation</div>
					<div class="col-3"><i class="icon-exclamation-sign"></i> icon-exclamation-sign</div>
					<div class="col-3"><i class="icon-expand"></i> icon-expand</div>
					<div class="col-3"><i class="icon-expand-alt"></i> icon-expand-alt</div>
					<div class="col-3"><i class="icon-external-link"></i> icon-external-link</div>
					<div class="col-3"><i class="icon-external-link-sign"></i> icon-external-link-sign</div>
					<div class="col-3"><i class="icon-eye-close"></i> icon-eye-close</div>
					<div class="col-3"><i class="icon-eye-open"></i> icon-eye-open</div>
					<div class="col-3"><i class="facetime-video"></i> facetime-video</div>
					<div class="col-3"><i class="icon-female"></i> icon-female</div>
					<div class="col-3"><i class="icon-fighter-jet"></i> icon-fighter-jet</div>
					<div class="col-3"><i class="icon-film"></i> icon-film</div>
					<div class="col-3"><i class="icon-filter"></i> icon-filter</div>
					<div class="col-3"><i class="icon-fire"></i> icon-fire</div>
					<div class="col-3"><i class="icon-fire-extinguisher"></i> icon-fire-extinguisher</div>
					<div class="col-3"><i class="icon-flag"></i> icon-flag</div>
					<div class="col-3"><i class="icon-flag-alt"></i> icon-flag-alt</div>
					<div class="col-3"><i class="icon-flag-checkered"></i> icon-flag-checkered</div>
					<div class="col-3"><i class="icon-folder-close"></i> icon-folder-close</div>
					<div class="col-3"><i class="icon-folder-close-alt"></i> icon-folder-close-alt</div>
					<div class="col-3"><i class="icon-folder-open"></i> icon-folder-open</div>
					<div class="col-3"><i class="icon-folder-open-alt"></i> icon-folder-open-alt</div>
					<div class="col-3"><i class="icon-food"></i> icon-food</div>
					<div class="col-3"><i class="icon-frown"></i> icon-frown</div>
					<div class="col-3"><i class="icon-gamepad"></i> icon-gamepad</div>
					<div class="col-3"><i class="icon-gear"></i> icon-gear</div>
					<div class="col-3"><i class="icon-gears"></i> icon-gears</div>
					<div class="col-3"><i class="icon-gift"></i> icon-gift</div>
					<div class="col-3"><i class="icon-glass"></i> icon-glass</div>
					<div class="col-3"><i class="icon-globe"></i> icon-globe</div>
					<div class="col-3"><i class="icon-group"></i> icon-group</div>
					<div class="col-3"><i class="icon-hdd"></i> icon-hdd</div>
					<div class="col-3"><i class="icon-headphones"></i> icon-headphones</div>
					<div class="col-3"><i class="icon-heart"></i> icon-heart</div>
					<div class="col-3"><i class="icon-heart-empty"></i> icon-heart-empty</div>
					<div class="col-3"><i class="icon-home"></i> icon-home</div>
					<div class="col-3"><i class="icon-inbox"></i> icon-inbox</div>
					<div class="col-3"><i class="icon-info"></i> icon-info</div>
					<div class="col-3"><i class="icon-info-sign"></i> icon-info-sign</div>
					<div class="col-3"><i class="icon-key"></i> icon-key</div>
					<div class="col-3"><i class="icon-keyboard"></i> icon-keyboard</div>
					<div class="col-3"><i class="icon-laptop"></i> icon-laptop</div>
					<div class="col-3"><i class="icon-leaf"></i> icon-leaf</div>
					<div class="col-3"><i class="icon-legal"></i> icon-legal</div>
					<div class="col-3"><i class="icon-lemon"></i> icon-lemon</div>
					<div class="col-3"><i class="icon-level-down"></i> icon-level-down</div>
					<div class="col-3"><i class="icon-level-up"></i> icon-level-up</div>
					<div class="col-3"><i class="icon-lightbulb"></i> icon-lightbulb</div>
					<div class="col-3"><i class="icon-location-arrow"></i> icon-location-arrow</div>
					<div class="col-3"><i class="icon-lock"></i> icon-lock</div>
					<div class="col-3"><i class="icon-magic"></i> icon-magic</div>
					<div class="col-3"><i class="icon-magnet"></i> icon-magnet</div>
					<div class="col-3"><i class="icon-mail-forward"></i> icon-mail-forward</div>
					<div class="col-3"><i class="icon-mail-reply"></i> icon-mail-reply</div>
					<div class="col-3"><i class="icon-mail-reply-all"></i> icon-mail-reply-all</div>
					<div class="col-3"><i class="icon-male"></i> icon-male</div>
					<div class="col-3"><i class="icon-map-marker"></i> icon-map-marker</div>
					<div class="col-3"><i class="icon-meh"></i> icon-meh</div>
					<div class="col-3"><i class="icon-microphone"></i> icon-microphone</div>
					<div class="col-3"><i class="icon-microphone-off"></i> icon-microphone-off</div>
					<div class="col-3"><i class="icon-minus"></i> icon-minus</div>
					<div class="col-3"><i class="icon-minus-sign"></i> icon-minus-sign</div>
					<div class="col-3"><i class="icon-minus-sign-alt"></i> icon-minus-sign-alt</div>
					<div class="col-3"><i class="icon-mobile-phone"></i> icon-mobile-phone</div>
					<div class="col-3"><i class="icon-money"></i> icon-money</div>
					<div class="col-3"><i class="icon-moon"></i> icon-moon</div>
					<div class="col-3"><i class="icon-move"></i> icon-move</div>
					<div class="col-3"><i class="icon-music"></i> icon-music</div>
					<div class="col-3"><i class="icon-off"></i> icon-off</div>
					<div class="col-3"><i class="icon-ok"></i> icon-ok</div>
					<div class="col-3"><i class="icon-ok-circle"></i> icon-ok-circle</div>
					<div class="col-3"><i class="icon-ok-sign"></i> icon-ok-sign</div>
					<div class="col-3"><i class="icon-pencil"></i> icon-pencil</div>
					<div class="col-3"><i class="icon-phone"></i> icon-phone</div>
					<div class="col-3"><i class="icon-phone-sign"></i> icon-phone-sign</div>
					<div class="col-3"><i class="icon-picture"></i> icon-picture</div>
					<div class="col-3"><i class="icon-plane"></i> icon-plane</div>
					<div class="col-3"><i class="icon-plus"></i> icon-plus</div>
					<div class="col-3"><i class="icon-plus-sign"></i> icon-plus-sign</div>
					<div class="col-3"><i class="icon-plus-sign-alt"></i> icon-plus-sign-alt</div>
					<div class="col-3"><i class="icon-power-off"></i> icon-power-off</div>
					<div class="col-3"><i class="icon-print"></i> icon-print</div>
					<div class="col-3"><i class="icon-pushpin"></i> icon-pushpin</div>
					<div class="col-3"><i class="icon-puzzle-piece"></i> icon-puzzle-piece</div>
					<div class="col-3"><i class="icon-qrcode"></i> icon-qrcode</div>
					<div class="col-3"><i class="icon-question"></i> icon-question</div>
					<div class="col-3"><i class="icon-question-sign"></i> icon-question-sign</div>
					<div class="col-3"><i class="icon-quote-left"></i> icon-quote-left</div>
					<div class="col-3"><i class="icon-quote-right"></i> icon-quote-right</div>
					<div class="col-3"><i class="icon-random"></i> icon-random</div>
					<div class="col-3"><i class="icon-refresh"></i> icon-refresh</div>
					<div class="col-3"><i class="icon-remove"></i> icon-remove</div>
					<div class="col-3"><i class="icon-remove-circle"></i> icon-remove-circle</div>
					<div class="col-3"><i class="icon-remove-sign"></i> icon-remove-sign</div>
					<div class="col-3"><i class="icon-reorder"></i> icon-reorder</div>
					<div class="col-3"><i class="icon-reply"></i> icon-reply</div>
					<div class="col-3"><i class="icon-reply-all"></i> icon-reply-all</div>
					<div class="col-3"><i class="icon-resize-horizontal"></i> icon-resize-horizontal</div>
					<div class="col-3"><i class="icon-resize-vertical"></i> icon-resize-vertical</div>
					<div class="col-3"><i class="icon-retweet"></i> icon-retweet</div>
					<div class="col-3"><i class="icon-road"></i> icon-road</div>
					<div class="col-3"><i class="icon-rocket"></i> icon-rocket</div>
					<div class="col-3"><i class="icon-rss"></i> icon-rss</div>
					<div class="col-3"><i class="icon-rss-sign"></i> icon-rss-sign</div>
					<div class="col-3"><i class="icon-screenshot"></i> icon-screenshot</div>
					<div class="col-3"><i class="icon-search"></i> icon-search</div>
					<div class="col-3"><i class="icon-share"></i> icon-share</div>
					<div class="col-3"><i class="icon-share-alt"></i> icon-share-alt</div>
					<div class="col-3"><i class="icon-share-sign"></i> icon-share-sign</div>
					<div class="col-3"><i class="icon-shield"></i> icon-shield</div>
					<div class="col-3"><i class="icon-shopping-cart"></i> icon-shopping-cart</div>
					<div class="col-3"><i class="icon-sign-blank"></i> icon-sign-blank</div>
					<div class="col-3"><i class="icon-signal"></i> icon-signal</div>
					<div class="col-3"><i class="icon-signin"></i> icon-signin</div>
					<div class="col-3"><i class="icon-signout"></i> icon-signout</div>
					<div class="col-3"><i class="icon-sitemap"></i> icon-sitemap</div>
					<div class="col-3"><i class="icon-smile"></i> icon-smile</div>
					<div class="col-3"><i class="icon-sort"></i> icon-sort</div>
					<div class="col-3"><i class="icon-sort-by-alphabet"></i> icon-sort-by-alphabet</div>
					<div class="col-3"><i class="icon-sort-by-alphabet-alt"></i> icon-sort-by-alphabet-alt</div>
					<div class="col-3"><i class="icon-sort-by-attributes"></i> icon-sort-by-attributes</div>
					<div class="col-3"><i class="icon-sort-by-attributes-alt"></i> icon-sort-by-attributes-alt</div>
					<div class="col-3"><i class="icon-sort-by-order"></i> icon-sort-by-order</div>
					<div class="col-3"><i class="icon-sort-by-order-alt"></i> icon-sort-by-order-alt</div>
					<div class="col-3"><i class="icon-sort-down"></i> icon-sort-down</div>
					<div class="col-3"><i class="icon-sort-up"></i> icon-sort-up</div>
					<div class="col-3"><i class="icon-spinner"></i> icon-spinner</div>
					<div class="col-3"><i class="icon-star"></i> icon-star</div>
					<div class="col-3"><i class="icon-star-empty"></i> icon-star-empty</div>
					<div class="col-3"><i class="icon-star-half"></i> icon-star-half</div>
					<div class="col-3"><i class="icon-star-half-empty"></i> icon-star-half-empty</div>
					<div class="col-3"><i class="icon-star-half-full"></i> icon-star-half-full</div>
					<div class="col-3"><i class="icon-subscript"></i> icon-subscript</div>
					<div class="col-3"><i class="icon-suitcase"></i> icon-suitcase</div>
					<div class="col-3"><i class="icon-sun"></i> icon-sun</div>
					<div class="col-3"><i class="icon-superscript"></i> icon-superscript</div>
					<div class="col-3"><i class="icon-tablet"></i> icon-tablet</div>
					<div class="col-3"><i class="icon-tag"></i> icon-tag</div>
					<div class="col-3"><i class="icon-tags"></i> icon-tags</div>
					<div class="col-3"><i class="icon-tasks"></i> icon-tasks</div>
					<div class="col-3"><i class="icon-terminal"></i> icon-terminal</div>
					<div class="col-3"><i class="icon-thumbs-down"></i> icon-thumbs-down</div>
					<div class="col-3"><i class="icon-thumbs-down-alt"></i> icon-thumbs-down-alt</div>
					<div class="col-3"><i class="icon-thumbs-up"></i> icon-thumbs-up</div>
					<div class="col-3"><i class="icon-thumbs-up-alt"></i> icon-thumbs-up-alt</div>
					<div class="col-3"><i class="icon-ticket"></i> icon-ticket</div>
					<div class="col-3"><i class="icon-time"></i> icon-time</div>
					<div class="col-3"><i class="icon-tint"></i> icon-tint</div>
					<div class="col-3"><i class="icon-trash"></i> icon-trash</div>
					<div class="col-3"><i class="icon-trophy"></i> icon-trophy</div>
					<div class="col-3"><i class="icon-truck"></i> icon-truck</div>
					<div class="col-3"><i class="icon-umbrella"></i> icon-umbrella</div>
					<div class="col-3"><i class="icon-unchecked"></i> icon-unchecked</div>
					<div class="col-3"><i class="icon-unlock"></i> icon-unlock</div>
					<div class="col-3"><i class="icon-unlock-alt"></i> icon-unlock-alt</div>
					<div class="col-3"><i class="icon-upload"></i> icon-upload</div>
					<div class="col-3"><i class="icon-upload-alt"></i> icon-upload-alt</div>
					<div class="col-3"><i class="icon-user"></i> icon-user</div>
					<div class="col-3"><i class="icon-volume-down"></i> icon-volume-down</div>
					<div class="col-3"><i class="icon-volume-off"></i> icon-volume-off</div>
					<div class="col-3"><i class="icon-volume-up"></i> icon-volume-up</div>
					<div class="col-3"><i class="icon-warning-sign"></i> icon-warning-sign</div>
					<div class="col-3"><i class="icon-wrench"></i> icon-wrench</div>
					<div class="col-3"><i class="icon-zoom-in"></i> icon-zoom-in</div>
					<div class="col-3"><i class="icon-zoom-out"></i> icon-zoom-out</div>
					<div class="col-3"><i class="icon-bitcoin"></i> icon-bitcoin</div>
					<div class="col-3"><i class="icon-btc"></i> icon-btc</div>
					<div class="col-3"><i class="icon-cny"></i> icon-cny</div>
					<div class="col-3"><i class="icon-dollar"></i> icon-dollar</div>
					<div class="col-3"><i class="icon-eur"></i> icon-eur</div>
					<div class="col-3"><i class="icon-euro"></i> icon-euro</div>
					<div class="col-3"><i class="icon-gbp"></i> icon-gbp</div>
					<div class="col-3"><i class="icon-inr"></i> icon-inr</div>
					<div class="col-3"><i class="icon-jpy"></i> icon-jpy</div>
					<div class="col-3"><i class="icon-krw"></i> icon-krw</div>
					<div class="col-3"><i class="icon-renminbi"></i> icon-renminbi</div>
					<div class="col-3"><i class="icon-rupee"></i> icon-rupee</div>
					<div class="col-3"><i class="icon-usd"></i> icon-usd</div>
					<div class="col-3"><i class="icon-won"></i> icon-won</div>
					<div class="col-3"><i class="icon-yen"></i> icon-yen</div>
					<div class="col-3"><i class="icon-align-center"></i> icon-align-center</div>
					<div class="col-3"><i class="icon-align-justify"></i> icon-align-justify</div>
					<div class="col-3"><i class="icon-align-left"></i> icon-align-left</div>
					<div class="col-3"><i class="icon-align-right"></i> icon-align-right</div>
					<div class="col-3"><i class="icon-bold"></i> icon-bold</div>
					<div class="col-3"><i class="icon-columns"></i> icon-columns</div>
					<div class="col-3"><i class="icon-copy"></i> icon-copy</div>
					<div class="col-3"><i class="icon-cut"></i> icon-cut</div>
					<div class="col-3"><i class="icon-eraser"></i> icon-eraser</div>
					<div class="col-3"><i class="icon-file"></i> icon-file</div>
					<div class="col-3"><i class="icon-file-alt"></i> icon-file-alt</div>
					<div class="col-3"><i class="icon-file-text"></i> icon-file-text</div>
					<div class="col-3"><i class="icon-file-text-alt"></i> icon-file-text-alt</div>
					<div class="col-3"><i class="icon-font"></i> icon-font</div>
					<div class="col-3"><i class="icon-indent-left"></i> icon-indent-left</div>
					<div class="col-3"><i class="icon-indent-right"></i> icon-indent-right</div>
					<div class="col-3"><i class="icon-italic"></i> icon-italic</div>
					<div class="col-3"><i class="icon-link"></i> icon-link</div>
					<div class="col-3"><i class="icon-list"></i> icon-list</div>
					<div class="col-3"><i class="icon-list-alt"></i> icon-list-alt</div>
					<div class="col-3"><i class="icon-list-ol"></i> icon-list-ol</div>
					<div class="col-3"><i class="icon-list-ul"></i> icon-list-ul</div>
					<div class="col-3"><i class="icon-paper-clip"></i> icon-paper-clip</div>
					<div class="col-3"><i class="icon-paperclip"></i> icon-paperclip</div>
					<div class="col-3"><i class="icon-paste"></i> icon-paste</div>
					<div class="col-3"><i class="icon-repeat"></i> icon-repeat</div>
					<div class="col-3"><i class="icon-rotate-left"></i> icon-rotate-left</div>
					<div class="col-3"><i class="icon-rotate-right"></i> icon-rotate-right</div>
					<div class="col-3"><i class="icon-save"></i> icon-save</div>
					<div class="col-3"><i class="icon-strikethrough"></i> icon-strikethrough</div>
					<div class="col-3"><i class="icon-table"></i> icon-table</div>
					<div class="col-3"><i class="icon-text-height"></i> icon-text-height</div>
					<div class="col-3"><i class="icon-text-width"></i> icon-text-width</div>
					<div class="col-3"><i class="icon-th"></i> icon-th</div>
					<div class="col-3"><i class="icon-th-large"></i> icon-th-large</div>
					<div class="col-3"><i class="icon-th-list"></i> icon-th-list</div>
					<div class="col-3"><i class="icon-underline"></i> icon-underline</div>
					<div class="col-3"><i class="icon-undo"></i> icon-undo</div>
					<div class="col-3"><i class="icon-unlink"></i> icon-unlink</div>
					<div class="col-3"><i class="icon-angle-down"></i> icon-angle-down</div>
					<div class="col-3"><i class="icon-angle-left"></i> icon-angle-left</div>
					<div class="col-3"><i class="icon-angle-right"></i> icon-angle-right</div>
					<div class="col-3"><i class="icon-angle-up"></i> icon-angle-up</div>
					<div class="col-3"><i class="icon-arrow-down"></i> icon-arrow-down</div>
					<div class="col-3"><i class="icon-arrow-left"></i> icon-arrow-left</div>
					<div class="col-3"><i class="icon-arrow-right"></i> icon-arrow-right</div>
					<div class="col-3"><i class="icon-arrow-up"></i> icon-arrow-up</div>
					<div class="col-3"><i class="icon-caret-down"></i> icon-caret-down</div>
					<div class="col-3"><i class="icon-caret-left"></i> icon-caret-left</div>
					<div class="col-3"><i class="icon-caret-right"></i> icon-caret-right</div>
					<div class="col-3"><i class="icon-caret-up"></i> icon-caret-up</div>
					<div class="col-3"><i class="icon-chevron-down"></i> icon-chevron-down</div>
					<div class="col-3"><i class="icon-chevron-left"></i> icon-chevron-left</div>
					<div class="col-3"><i class="icon-chevron-right"></i> icon-chevron-right</div>
					<div class="col-3"><i class="icon-chevron-sign-down"></i> icon-chevron-sign-down</div>
					<div class="col-3"><i class="icon-chevron-sign-left"></i> icon-chevron-sign-left</div>
					<div class="col-3"><i class="icon-chevron-sign-right"></i> icon-chevron-sign-right</div>
					<div class="col-3"><i class="icon-chevron-sign-up"></i> icon-chevron-sign-up</div>
					<div class="col-3"><i class="icon-chevron-up"></i> icon-chevron-up</div>
					<div class="col-3"><i class="icon-circle-arrow-down"></i> icon-circle-arrow-down</div>
					<div class="col-3"><i class="icon-circle-arrow-left"></i> icon-circle-arrow-left</div>
					<div class="col-3"><i class="icon-circle-arrow-right"></i> icon-circle-arrow-right</div>
					<div class="col-3"><i class="icon-circle-arrow-up"></i> icon-circle-arrow-up</div>
					<div class="col-3"><i class="icon-double-angle-down"></i> icon-double-angle-down</div>
					<div class="col-3"><i class="icon-double-angle-left"></i> icon-double-angle-left</div>
					<div class="col-3"><i class="icon-double-angle-right"></i> icon-double-angle-right</div>
					<div class="col-3"><i class="icon-double-angle-up"></i> icon-double-angle-up</div>
					<div class="col-3"><i class="icon-hand-down"></i> icon-hand-down</div>
					<div class="col-3"><i class="icon-hand-left"></i> icon-hand-left</div>
					<div class="col-3"><i class="icon-hand-right"></i> icon-hand-right</div>
					<div class="col-3"><i class="icon-hand-up"></i> icon-hand-up</div>
					<div class="col-3"><i class="icon-long-arrow-down"></i> icon-long-arrow-down</div>
					<div class="col-3"><i class="icon-long-arrow-left"></i> icon-long-arrow-left</div>
					<div class="col-3"><i class="icon-long-arrow-right"></i> icon-long-arrow-right</div>
					<div class="col-3"><i class="icon-long-arrow-up"></i> icon-long-arrow-up</div>
					<div class="col-3"><i class="icon-backward"></i> icon-backward</div>
					<div class="col-3"><i class="icon-eject"></i> icon-eject</div>
					<div class="col-3"><i class="icon-fast-backward"></i> fast-backward</div>
					<div class="col-3"><i class="icon-fast-forward"></i> fast-forward</div>
					<div class="col-3"><i class="icon-forward"></i> icon-forward</div>
					<div class="col-3"><i class="icon-fullscreen"></i> icon-fullscreen</div>
					<div class="col-3"><i class="icon-pause"></i> icon-pause</div>
					<div class="col-3"><i class="icon-play"></i> icon-play</div>
					<div class="col-3"><i class="icon-play-circle"></i> icon-play-circle</div>
					<div class="col-3"><i class="icon-play-sign"></i> icon-play-sign</div>
					<div class="col-3"><i class="icon-resize-full"></i> icon-resize-full</div>
					<div class="col-3"><i class="icon-resize-small"></i> icon-resize-small</div>
					<div class="col-3"><i class="icon-step-backward"></i> icon-step-backward</div>
					<div class="col-3"><i class="icon-step-forward"></i> icon-step-forward</div>
					<div class="col-3"><i class="icon-stop"></i> icon-stop</div>
					<div class="col-3"><i class="icon-youtube-play"></i> icon-youtube-play</div>
					<div class="col-3"><i class="icon-adn"></i> icon-adn</div>
					<div class="col-3"><i class="icon-android"></i> icon-android</div>
					<div class="col-3"><i class="icon-apple"></i> icon-apple</div>
					<div class="col-3"><i class="icon-bitbucket"></i> icon-bitbucket</div>
					<div class="col-3"><i class="icon-bitbucket-sign"></i> icon-bitbucket-sign</div>
					<div class="col-3"><i class="icon-bitcoin"></i> icon-bitcoin</div>
					<div class="col-3"><i class="icon-btc"></i> icon-btc</div>
					<div class="col-3"><i class="icon-css3"></i> icon-css3</div>
					<div class="col-3"><i class="icon-dribbble"></i> icon-dribbble</div>
					<div class="col-3"><i class="icon-dropbox"></i> icon-dropbox</div>
					<div class="col-3"><i class="icon-facebook"></i> facebook</div>
					<div class="col-3"><i class="icon-facebook-sign"></i> facebook-sign</div>
					<div class="col-3"><i class="icon-flickr"></i> icon-flickr</div>
					<div class="col-3"><i class="icon-foursquare"></i> icon-foursquare</div>
					<div class="col-3"><i class="icon-github"></i> icon-github</div>
					<div class="col-3"><i class="icon-github-alt"></i> icon-github-alt</div>
					<div class="col-3"><i class="icon-github-sign"></i> icon-github-sign</div>
					<div class="col-3"><i class="icon-gittip"></i> icon-gittip</div>
					<div class="col-3"><i class="icon-google-plus"></i> icon-google-plus</div>
					<div class="col-3"><i class="icon-google-plus-sign"></i> icon-google-plus-sign</div>
					<div class="col-3"><i class="icon-html5"></i> icon-html5</div>
					<div class="col-3"><i class="icon-instagram"></i> icon-instagram</div>
					<div class="col-3"><i class="icon-linkedin"></i> icon-linkedin</div>
					<div class="col-3"><i class="icon-linkedin-sign"></i> icon-linkedin-sign</div>
					<div class="col-3"><i class="icon-linux"></i> icon-linux</div>
					<div class="col-3"><i class="icon-maxcdn"></i> icon-maxcdn</div>
					<div class="col-3"><i class="icon-pinterest"></i> icon-pinterest</div>
					<div class="col-3"><i class="icon-pinterest-sign"></i> icon-pinterest-sign</div>
					<div class="col-3"><i class="icon-renren"></i> icon-renren</div>
					<div class="col-3"><i class="icon-skype"></i> icon-skype</div>
					<div class="col-3"><i class="icon-stackexchange"></i> icon-stackexchange</div>
					<div class="col-3"><i class="icon-trello"></i> icon-trello</div>
					<div class="col-3"><i class="icon-tumblr"></i> icon-tumblr</div>
					<div class="col-3"><i class="icon-tumblr-sign"></i> icon-tumblr-sign</div>
					<div class="col-3"><i class="icon-twitter"></i> icon-twitter</div>
					<div class="col-3"><i class="icon-twitter-sign"></i> icon-twitter-sign</div>
					<div class="col-3"><i class="icon-vk"></i> icon-vk</div>
					<div class="col-3"><i class="icon-weibo"></i> icon-weibo</div>
					<div class="col-3"><i class="icon-windows"></i> icon-windows</div>
					<div class="col-3"><i class="icon-xing"></i> icon-xing</div>
					<div class="col-3"><i class="icon-xing-sign"></i> icon-xing-sign</div>
					<div class="col-3"><i class="icon-youtube"></i> icon-youtube</div>
					<div class="col-3"><i class="icon-youtube-play"></i> icon-youtube-play</div>
					<div class="col-3"><i class="icon-youtube-sign"></i> icon-youtube-sign</div>
					<div class="col-3"><i class="icon-ambulance"></i> icon-ambulance</div>
					<div class="col-3"><i class="icon-h-sign"></i> icon-h-sign</div>
					<div class="col-3"><i class="icon-hospital"></i> icon-hospital</div>
					<div class="col-3"><i class="icon-medkit"></i> icon-medkit</div>
					<div class="col-3"><i class="icon-plus-sign-alt"></i> icon-plus-sign-alt</div>
					<div class="col-3"><i class="icon-stethoscope"></i> icon-stethoscope</div>
					<div class="col-3"><i class="icon-user-md"></i> icon-user-md</div>
				</div><!-- end row -->
			</div><!-- end grid -->
		</div>
		
		<div id="tab-pane2" class="tab-pane">
			<div class="grid">
				<div class="row">
					<div class="col-12 divCenter">
						<h3><p class="textError">支持FontAwesome4字体图标库，使用fa-前缀，缺省自动补齐l-icon-</p></h3>
					</div>
				</div>
				<div class="space-y"></div>
				<div class="row">
					<div class="col-3">
						<div class="l-icon l-icon-word"></div>
						<span>l-icon-word</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-excel"></div>
						<span>l-icon-excel</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-export"></div>
						<span>l-icon-export</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-import"></div>
						<span>l-icon-import</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-edit"></div>
						<span>l-icon-edit</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-view"></div>
						<span>l-icon-view</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-add"></div>
						<span>l-icon-add</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-add1"></div>
						<span>l-icon-add1</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-add2"></div>
						<span>l-icon-add2</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-add3"></div>
						<span>l-icon-add3</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-add4"></div>
						<span>l-icon-add4</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-add5"></div>
						<span>l-icon-add5</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-delete"></div>
						<span>l-icon-delete</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-delete1"></div>
						<span>l-icon-delete1</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-delete2"></div>
						<span>l-icon-delete2</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-delete3"></div>
						<span>l-icon-delete3</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-delete4"></div>
						<span>l-icon-delete4</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-delete5"></div>
						<span>l-icon-delete5</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-back"></div>
						<span>l-icon-back</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-bluebook"></div>
						<span>l-icon-bluebook</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-bookpen"></div>
						<span>l-icon-bookpen</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-coffee"></div>
						<span>l-icon-coffee</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-cut"></div>
						<span>l-icon-cut</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-copy"></div>
						<span>l-icon-copy</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-discuss"></div>
						<span>l-icon-discuss</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-graywarn"></div>
						<span>l-icon-graywarn</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-greenwarn"></div>
						<span>l-icon-greenwarn</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-help"></div>
						<span>l-icon-help</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-home"></div>
						<span>l-icon-home</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-lock"></div>
						<span>l-icon-lock</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-logout"></div>
						<span>l-icon-logout</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-mailbox"></div>
						<span>l-icon-mailbox</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-memeber"></div>
						<span>l-icon-memeber</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-modify"></div>
						<span>l-icon-modify</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-msn"></div>
						<span>l-icon-msn</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-ok"></div>
						<span>l-icon-ok</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-outbox"></div>
						<span>l-icon-outbox</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-pager"></div>
						<span>l-icon-pager</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-photograph"></div>
						<span>l-icon-photograph</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-prev"></div>
						<span>l-icon-prev</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-print"></div>
						<span>l-icon-print</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-qq"></div>
						<span>l-icon-qq</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-refresh"></div>
						<span>l-icon-refresh</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-left"></div>
						<span>l-icon-left</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-right"></div>
						<span>l-icon-right</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-save-disabled"></div>
						<span>l-icon-save-disabled</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-save"></div>
						<span>l-icon-save</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-search"></div>
						<span>l-icon-search</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-search2"></div>
						<span>l-icon-search2</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-settings"></div>
						<span>l-icon-settings</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-true"></div>
						<span>l-icon-true</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-up"></div>
						<span>l-icon-up</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-down"></div>
						<span>l-icon-down</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-role"></div>
						<span>l-icon-role</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-config"></div>
						<span>l-icon-config</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-address"></div>
						<span>l-icon-address</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-process"></div>
						<span>l-icon-process</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-archives"></div>
						<span>l-icon-archives</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-attibutes"></div>
						<span>l-icon-attibutes</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-busy"></div>
						<span>l-icon-busy</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-calendar"></div>
						<span>l-icon-calendar</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-comment"></div>
						<span>l-icon-comment</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-plus"></div>
						<span>l-icon-plus</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-communication"></div>
						<span>l-icon-communication</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-customers"></div>
						<span>l-icon-customers</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-database"></div>
						<span>l-icon-database</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-myaccount"></div>
						<span>l-icon-myaccount</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-star"></div>
						<span>l-icon-star</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-star-plus"></div>
						<span>l-icon-star-plus</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-star-minus"></div>
						<span>l-icon-star-minus</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-cross"></div>
						<span>l-icon-cross</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-cross1"></div>
						<span>l-icon-cross1</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-cross2"></div>
						<span>l-icon-cross2</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-cross3"></div>
						<span>l-icon-cross3</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-cross4"></div>
						<span>l-icon-cross4</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-cross5"></div>
						<span>l-icon-cross5</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-tick"></div>
						<span>l-icon-tick</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-tick1"></div>
						<span>l-icon-tick1</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-tick2"></div>
						<span>l-icon-tick2</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-tick3"></div>
						<span>l-icon-tick3</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-tick4"></div>
						<span>l-icon-tick4</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-tick5"></div>
						<span>l-icon-tick5</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-info-balloon"></div>
						<span>l-icon-info-balloon</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-info"></div>
						<span>l-icon-info</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-info1"></div>
						<span>l-icon-info1</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-info2"></div>
						<span>l-icon-info2</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-info3"></div>
						<span>l-icon-info3</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-info4"></div>
						<span>l-icon-info4</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-info5"></div>
						<span>l-icon-info5</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-sort"></div>
						<span>l-icon-sort</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-sort-desc"></div>
						<span>l-icon-sort-desc</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-sort1"></div>
						<span>l-icon-sort1</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-sort1-desc"></div>
						<span>l-icon-sort1-desc</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-sort2"></div>
						<span>l-icon-sort2</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-sort2-desc"></div>
						<span>l-icon-sort2-desc</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-clean"></div>
						<span>l-icon-clean</span>
					</div>
					<div class="col-3">
						<div class="l-icon l-icon-cancel"></div>
						<span>l-icon-cancel</span>
					</div>
				</div><!-- end row -->
			</div><!-- end grid -->
		</div>
		
		<div id="tab-pane3" class="tab-pane">
		</div>
	
		<!-- trm <a href[^>]*> </a> -->
		<div id="tab-pane4" class="tab-pane">
			<div class="grid">
				<div class="row">
					<div class="col-12 divCenter">
						<h3><span class="textError">为了兼容FontAwesome3，请使用icon-fa前缀(原版使用fa前缀)</span></h3>
					</div>
				</div>
				<div class="space-y"></div>
				<div class="row">
			      <div class="col-3"><i class="icon-fa fa-adjust"></i> fa-adjust</div>
			      <div class="col-3"><i class="icon-fa fa-anchor"></i> fa-anchor</div>
			      <div class="col-3"><i class="icon-fa fa-archive"></i> fa-archive</div>
			      <div class="col-3"><i class="icon-fa fa-area-chart"></i> fa-area-chart</div>
			      <div class="col-3"><i class="icon-fa fa-arrows"></i> fa-arrows</div>
			      <div class="col-3"><i class="icon-fa fa-arrows-h"></i> fa-arrows-h</div>
			      <div class="col-3"><i class="icon-fa fa-arrows-v"></i> fa-arrows-v</div>
			      <div class="col-3"><i class="icon-fa fa-asterisk"></i> fa-asterisk</div>
			      <div class="col-3"><i class="icon-fa fa-at"></i> fa-at</div>
			      <div class="col-3"><i class="icon-fa fa-automobile"></i> fa-automobile</div>
			      <div class="col-3"><i class="icon-fa fa-ban"></i> fa-ban</div>
			      <div class="col-3"><i class="icon-fa fa-bank"></i> fa-bank</div>
			      <div class="col-3"><i class="icon-fa fa-bar-chart"></i> fa-bar-chart</div>
			      <div class="col-3"><i class="icon-fa fa-bar-chart-o"></i> fa-bar-chart-o</div>
			      <div class="col-3"><i class="icon-fa fa-barcode"></i> fa-barcode</div>
			      <div class="col-3"><i class="icon-fa fa-bars"></i> fa-bars</div>
			      <div class="col-3"><i class="icon-fa fa-beer"></i> fa-beer</div>
			      <div class="col-3"><i class="icon-fa fa-bell"></i> fa-bell</div>
			      <div class="col-3"><i class="icon-fa fa-bell-o"></i> fa-bell-o</div>
			      <div class="col-3"><i class="icon-fa fa-bell-slash"></i> fa-bell-slash</div>
			      <div class="col-3"><i class="icon-fa fa-bell-slash-o"></i> fa-bell-slash-o</div>
			      <div class="col-3"><i class="icon-fa fa-bicycle"></i> fa-bicycle</div>
			      <div class="col-3"><i class="icon-fa fa-binoculars"></i> fa-binoculars</div>
			      <div class="col-3"><i class="icon-fa fa-birthday-cake"></i> fa-birthday-cake</div>
			      <div class="col-3"><i class="icon-fa fa-bolt"></i> fa-bolt</div>
			      <div class="col-3"><i class="icon-fa fa-bomb"></i> fa-bomb</div>
			      <div class="col-3"><i class="icon-fa fa-book"></i> fa-book</div>
			      <div class="col-3"><i class="icon-fa fa-bookmark"></i> fa-bookmark</div>
			      <div class="col-3"><i class="icon-fa fa-bookmark-o"></i> fa-bookmark-o</div>
			      <div class="col-3"><i class="icon-fa fa-briefcase"></i> fa-briefcase</div>
			      <div class="col-3"><i class="icon-fa fa-bug"></i> fa-bug</div>
			      <div class="col-3"><i class="icon-fa fa-building"></i> fa-building</div>
			      <div class="col-3"><i class="icon-fa fa-building-o"></i> fa-building-o</div>
			      <div class="col-3"><i class="icon-fa fa-bullhorn"></i> fa-bullhorn</div>
			      <div class="col-3"><i class="icon-fa fa-bullseye"></i> fa-bullseye</div>
			      <div class="col-3"><i class="icon-fa fa-bus"></i> fa-bus</div>
			      <div class="col-3"><i class="icon-fa fa-cab"></i> fa-cab</div>
			      <div class="col-3"><i class="icon-fa fa-calculator"></i> fa-calculator</div>
			      <div class="col-3"><i class="icon-fa fa-calendar"></i> fa-calendar</div>
			      <div class="col-3"><i class="icon-fa fa-calendar-o"></i> fa-calendar-o</div>
			      <div class="col-3"><i class="icon-fa fa-camera"></i> fa-camera</div>
			      <div class="col-3"><i class="icon-fa fa-camera-retro"></i> fa-camera-retro</div>
			      <div class="col-3"><i class="icon-fa fa-car"></i> fa-car</div>
			      <div class="col-3"><i class="icon-fa fa-caret-square-o-down"></i> fa-caret-square-o-down</div>
			      <div class="col-3"><i class="icon-fa fa-caret-square-o-left"></i> fa-caret-square-o-left</div>
			      <div class="col-3"><i class="icon-fa fa-caret-square-o-right"></i> fa-caret-square-o-right</div>
			      <div class="col-3"><i class="icon-fa fa-caret-square-o-up"></i> fa-caret-square-o-up</div>
			      <div class="col-3"><i class="icon-fa fa-cc"></i> fa-cc</div>
			      <div class="col-3"><i class="icon-fa fa-certificate"></i> fa-certificate</div>
			      <div class="col-3"><i class="icon-fa fa-check"></i> fa-check</div>
			      <div class="col-3"><i class="icon-fa fa-check-circle"></i> fa-check-circle</div>
			      <div class="col-3"><i class="icon-fa fa-check-circle-o"></i> fa-check-circle-o</div>
			      <div class="col-3"><i class="icon-fa fa-check-square"></i> fa-check-square</div>
			      <div class="col-3"><i class="icon-fa fa-check-square-o"></i> fa-check-square-o</div>
			      <div class="col-3"><i class="icon-fa fa-child"></i> fa-child</div>
			      <div class="col-3"><i class="icon-fa fa-circle"></i> fa-circle</div>
			      <div class="col-3"><i class="icon-fa fa-circle-o"></i> fa-circle-o</div>
			      <div class="col-3"><i class="icon-fa fa-circle-o-notch"></i> fa-circle-o-notch</div>
			      <div class="col-3"><i class="icon-fa fa-circle-thin"></i> fa-circle-thin</div>
			      <div class="col-3"><i class="icon-fa fa-clock-o"></i> fa-clock-o</div>
			      <div class="col-3"><i class="icon-fa fa-close"></i> fa-close</div>
			      <div class="col-3"><i class="icon-fa fa-cloud"></i> fa-cloud</div>
			      <div class="col-3"><i class="icon-fa fa-cloud-download"></i> fa-cloud-download</div>
			      <div class="col-3"><i class="icon-fa fa-cloud-upload"></i> fa-cloud-upload</div>
			      <div class="col-3"><i class="icon-fa fa-code"></i> fa-code</div>
			      <div class="col-3"><i class="icon-fa fa-code-fork"></i> fa-code-fork</div>
			      <div class="col-3"><i class="icon-fa fa-coffee"></i> fa-coffee</div>
			      <div class="col-3"><i class="icon-fa fa-cog"></i> fa-cog</div>
			      <div class="col-3"><i class="icon-fa fa-cogs"></i> fa-cogs</div>
			      <div class="col-3"><i class="icon-fa fa-comment"></i> fa-comment</div>
			      <div class="col-3"><i class="icon-fa fa-comment-o"></i> fa-comment-o</div>
			      <div class="col-3"><i class="icon-fa fa-comments"></i> fa-comments</div>
			      <div class="col-3"><i class="icon-fa fa-comments-o"></i> fa-comments-o</div>
			      <div class="col-3"><i class="icon-fa fa-compass"></i> fa-compass</div>
			      <div class="col-3"><i class="icon-fa fa-copyright"></i> fa-copyright</div>
			      <div class="col-3"><i class="icon-fa fa-credit-card"></i> fa-credit-card</div>
			      <div class="col-3"><i class="icon-fa fa-crop"></i> fa-crop</div>
			      <div class="col-3"><i class="icon-fa fa-crosshairs"></i> fa-crosshairs</div>
			      <div class="col-3"><i class="icon-fa fa-cube"></i> fa-cube</div>
			      <div class="col-3"><i class="icon-fa fa-cubes"></i> fa-cubes</div>
			      <div class="col-3"><i class="icon-fa fa-cutlery"></i> fa-cutlery</div>
			      <div class="col-3"><i class="icon-fa fa-dashboard"></i> fa-dashboard</div>
			      <div class="col-3"><i class="icon-fa fa-database"></i> fa-database</div>
			      <div class="col-3"><i class="icon-fa fa-desktop"></i> fa-desktop</div>
			      <div class="col-3"><i class="icon-fa fa-dot-circle-o"></i> fa-dot-circle-o</div>
			      <div class="col-3"><i class="icon-fa fa-download"></i> fa-download</div>
			      <div class="col-3"><i class="icon-fa fa-edit"></i> fa-edit</div>
			      <div class="col-3"><i class="icon-fa fa-ellipsis-h"></i> fa-ellipsis-h</div>
			      <div class="col-3"><i class="icon-fa fa-ellipsis-v"></i> fa-ellipsis-v</div>
			      <div class="col-3"><i class="icon-fa fa-envelope"></i> fa-envelope</div>
			      <div class="col-3"><i class="icon-fa fa-envelope-o"></i> fa-envelope-o</div>
			      <div class="col-3"><i class="icon-fa fa-envelope-square"></i> fa-envelope-square</div>
			      <div class="col-3"><i class="icon-fa fa-eraser"></i> fa-eraser</div>
			      <div class="col-3"><i class="icon-fa fa-exchange"></i> fa-exchange</div>
			      <div class="col-3"><i class="icon-fa fa-exclamation"></i> fa-exclamation</div>
			      <div class="col-3"><i class="icon-fa fa-exclamation-circle"></i> fa-exclamation-circle</div>
			      <div class="col-3"><i class="icon-fa fa-exclamation-triangle"></i> fa-exclamation-triangle</div>
			      <div class="col-3"><i class="icon-fa fa-external-link"></i> fa-external-link</div>
			      <div class="col-3"><i class="icon-fa fa-external-link-square"></i> fa-external-link-square</div>
			      <div class="col-3"><i class="icon-fa fa-eye"></i> fa-eye</div>
			      <div class="col-3"><i class="icon-fa fa-eye-slash"></i> fa-eye-slash</div>
			      <div class="col-3"><i class="icon-fa fa-eyedropper"></i> fa-eyedropper</div>
			      <div class="col-3"><i class="icon-fa fa-fax"></i> fa-fax</div>
			      <div class="col-3"><i class="icon-fa fa-female"></i> fa-female</div>
			      <div class="col-3"><i class="icon-fa fa-fighter-jet"></i> fa-fighter-jet</div>
			      <div class="col-3"><i class="icon-fa fa-file-archive-o"></i> fa-file-archive-o</div>
			      <div class="col-3"><i class="icon-fa fa-file-audio-o"></i> fa-file-audio-o</div>
			      <div class="col-3"><i class="icon-fa fa-file-code-o"></i> fa-file-code-o</div>
			      <div class="col-3"><i class="icon-fa fa-file-excel-o"></i> fa-file-excel-o</div>
			      <div class="col-3"><i class="icon-fa fa-file-image-o"></i> fa-file-image-o</div>
			      <div class="col-3"><i class="icon-fa fa-file-movie-o"></i> fa-file-movie-o</div>
			      <div class="col-3"><i class="icon-fa fa-file-pdf-o"></i> fa-file-pdf-o</div>
			      <div class="col-3"><i class="icon-fa fa-file-photo-o"></i> fa-file-photo-o</div>
			      <div class="col-3"><i class="icon-fa fa-file-picture-o"></i> fa-file-picture-o</div>
			      <div class="col-3"><i class="icon-fa fa-file-powerpoint-o"></i> fa-file-powerpoint-o</div>
			      <div class="col-3"><i class="icon-fa fa-file-sound-o"></i> fa-file-sound-o</div>
			      <div class="col-3"><i class="icon-fa fa-file-video-o"></i> fa-file-video-o</div>
			      <div class="col-3"><i class="icon-fa fa-file-word-o"></i> fa-file-word-o</div>
			      <div class="col-3"><i class="icon-fa fa-file-zip-o"></i> fa-file-zip-o</div>
			      <div class="col-3"><i class="icon-fa fa-film"></i> fa-film</div>
			      <div class="col-3"><i class="icon-fa fa-filter"></i> fa-filter</div>
			      <div class="col-3"><i class="icon-fa fa-fire"></i> fa-fire</div>
			      <div class="col-3"><i class="icon-fa fa-fire-extinguisher"></i> fa-fire-extinguisher</div>
			      <div class="col-3"><i class="icon-fa fa-flag"></i> fa-flag</div>
			      <div class="col-3"><i class="icon-fa fa-flag-checkered"></i> fa-flag-checkered</div>
			      <div class="col-3"><i class="icon-fa fa-flag-o"></i> fa-flag-o</div>
			      <div class="col-3"><i class="icon-fa fa-flash"></i> fa-flash</div>
			      <div class="col-3"><i class="icon-fa fa-flask"></i> fa-flask</div>
			      <div class="col-3"><i class="icon-fa fa-folder"></i> fa-folder</div>
			      <div class="col-3"><i class="icon-fa fa-folder-o"></i> fa-folder-o</div>
			      <div class="col-3"><i class="icon-fa fa-folder-open"></i> fa-folder-open</div>
			      <div class="col-3"><i class="icon-fa fa-folder-open-o"></i> fa-folder-open-o</div>
			      <div class="col-3"><i class="icon-fa fa-frown-o"></i> fa-frown-o</div>
			      <div class="col-3"><i class="icon-fa fa-futbol-o"></i> fa-futbol-o</div>
			      <div class="col-3"><i class="icon-fa fa-gamepad"></i> fa-gamepad</div>
			      <div class="col-3"><i class="icon-fa fa-gavel"></i> fa-gavel</div>
			      <div class="col-3"><i class="icon-fa fa-gear"></i> fa-gear</div>
			      <div class="col-3"><i class="icon-fa fa-gears"></i> fa-gears</div>
			      <div class="col-3"><i class="icon-fa fa-gift"></i> fa-gift</div>
			      <div class="col-3"><i class="icon-fa fa-glass"></i> fa-glass</div>
			      <div class="col-3"><i class="icon-fa fa-globe"></i> fa-globe</div>
			      <div class="col-3"><i class="icon-fa fa-graduation-cap"></i> fa-graduation-cap</div>
			      <div class="col-3"><i class="icon-fa fa-group"></i> fa-group</div>
			      <div class="col-3"><i class="icon-fa fa-hdd-o"></i> fa-hdd-o</div>
			      <div class="col-3"><i class="icon-fa fa-headphones"></i> fa-headphones</div>
			      <div class="col-3"><i class="icon-fa fa-heart"></i> fa-heart</div>
			      <div class="col-3"><i class="icon-fa fa-heart-o"></i> fa-heart-o</div>
			      <div class="col-3"><i class="icon-fa fa-history"></i> fa-history</div>
			      <div class="col-3"><i class="icon-fa fa-home"></i> fa-home</div>
			      <div class="col-3"><i class="icon-fa fa-image"></i> fa-image</div>
			      <div class="col-3"><i class="icon-fa fa-inbox"></i> fa-inbox</div>
			      <div class="col-3"><i class="icon-fa fa-info"></i> fa-info</div>
			      <div class="col-3"><i class="icon-fa fa-info-circle"></i> fa-info-circle</div>
			      <div class="col-3"><i class="icon-fa fa-institution"></i> fa-institution</div>
			      <div class="col-3"><i class="icon-fa fa-key"></i> fa-key</div>
			      <div class="col-3"><i class="icon-fa fa-keyboard-o"></i> fa-keyboard-o</div>
			      <div class="col-3"><i class="icon-fa fa-language"></i> fa-language</div>
			      <div class="col-3"><i class="icon-fa fa-laptop"></i> fa-laptop</div>
			      <div class="col-3"><i class="icon-fa fa-leaf"></i> fa-leaf</div>
			      <div class="col-3"><i class="icon-fa fa-legal"></i> fa-legal</div>
			      <div class="col-3"><i class="icon-fa fa-lemon-o"></i> fa-lemon-o</div>
			      <div class="col-3"><i class="icon-fa fa-level-down"></i> fa-level-down</div>
			      <div class="col-3"><i class="icon-fa fa-level-up"></i> fa-level-up</div>
			      <div class="col-3"><i class="icon-fa fa-life-bouy"></i> fa-life-bouy</div>
			      <div class="col-3"><i class="icon-fa fa-life-buoy"></i> fa-life-buoy</div>
			      <div class="col-3"><i class="icon-fa fa-life-ring"></i> fa-life-ring</div>
			      <div class="col-3"><i class="icon-fa fa-life-saver"></i> fa-life-saver</div>
			      <div class="col-3"><i class="icon-fa fa-lightbulb-o"></i> fa-lightbulb-o</div>
			      <div class="col-3"><i class="icon-fa fa-line-chart"></i> fa-line-chart</div>
			      <div class="col-3"><i class="icon-fa fa-location-arrow"></i> fa-location-arrow</div>
			      <div class="col-3"><i class="icon-fa fa-lock"></i> fa-lock</div>
			      <div class="col-3"><i class="icon-fa fa-magic"></i> fa-magic</div>
			      <div class="col-3"><i class="icon-fa fa-magnet"></i> fa-magnet</div>
			      <div class="col-3"><i class="icon-fa fa-mail-forward"></i> fa-mail-forward</div>
			      <div class="col-3"><i class="icon-fa fa-mail-reply"></i> fa-mail-reply</div>
			      <div class="col-3"><i class="icon-fa fa-mail-reply-all"></i> fa-mail-reply-all</div>
			      <div class="col-3"><i class="icon-fa fa-male"></i> fa-male</div>
			      <div class="col-3"><i class="icon-fa fa-map-marker"></i> fa-map-marker</div>
			      <div class="col-3"><i class="icon-fa fa-meh-o"></i> fa-meh-o</div>
			      <div class="col-3"><i class="icon-fa fa-microphone"></i> fa-microphone</div>
			      <div class="col-3"><i class="icon-fa fa-microphone-slash"></i> fa-microphone-slash</div>
			      <div class="col-3"><i class="icon-fa fa-minus"></i> fa-minus</div>
			      <div class="col-3"><i class="icon-fa fa-minus-circle"></i> fa-minus-circle</div>
			      <div class="col-3"><i class="icon-fa fa-minus-square"></i> fa-minus-square</div>
			      <div class="col-3"><i class="icon-fa fa-minus-square-o"></i> fa-minus-square-o</div>
			      <div class="col-3"><i class="icon-fa fa-mobile"></i> fa-mobile</div>
			      <div class="col-3"><i class="icon-fa fa-mobile-phone"></i> fa-mobile-phone</div>
			      <div class="col-3"><i class="icon-fa fa-money"></i> fa-money</div>
			      <div class="col-3"><i class="icon-fa fa-moon-o"></i> fa-moon-o</div>
			      <div class="col-3"><i class="icon-fa fa-mortar-board"></i> fa-mortar-board</div>
			      <div class="col-3"><i class="icon-fa fa-music"></i> fa-music</div>
			      <div class="col-3"><i class="icon-fa fa-navicon"></i> fa-navicon</div>
			      <div class="col-3"><i class="icon-fa fa-newspaper-o"></i> fa-newspaper-o</div>
			      <div class="col-3"><i class="icon-fa fa-paint-brush"></i> fa-paint-brush</div>
			      <div class="col-3"><i class="icon-fa fa-paper-plane"></i> fa-paper-plane</div>
			      <div class="col-3"><i class="icon-fa fa-paper-plane-o"></i> fa-paper-plane-o</div>
			      <div class="col-3"><i class="icon-fa fa-paw"></i> fa-paw</div>
			      <div class="col-3"><i class="icon-fa fa-pencil"></i> fa-pencil</div>
			      <div class="col-3"><i class="icon-fa fa-pencil-square"></i> fa-pencil-square</div>
			      <div class="col-3"><i class="icon-fa fa-pencil-square-o"></i> fa-pencil-square-o</div>
			      <div class="col-3"><i class="icon-fa fa-phone"></i> fa-phone</div>
			      <div class="col-3"><i class="icon-fa fa-phone-square"></i> fa-phone-square</div>
			      <div class="col-3"><i class="icon-fa fa-photo"></i> fa-photo</div>
			      <div class="col-3"><i class="icon-fa fa-picture-o"></i> fa-picture-o</div>
			      <div class="col-3"><i class="icon-fa fa-pie-chart"></i> fa-pie-chart</div>
			      <div class="col-3"><i class="icon-fa fa-plane"></i> fa-plane</div>
			      <div class="col-3"><i class="icon-fa fa-plug"></i> fa-plug</div>
			      <div class="col-3"><i class="icon-fa fa-plus"></i> fa-plus</div>
			      <div class="col-3"><i class="icon-fa fa-plus-circle"></i> fa-plus-circle</div>
			      <div class="col-3"><i class="icon-fa fa-plus-square"></i> fa-plus-square</div>
			      <div class="col-3"><i class="icon-fa fa-plus-square-o"></i> fa-plus-square-o</div>
			      <div class="col-3"><i class="icon-fa fa-power-off"></i> fa-power-off</div>
			      <div class="col-3"><i class="icon-fa fa-print"></i> fa-print</div>
			      <div class="col-3"><i class="icon-fa fa-puzzle-piece"></i> fa-puzzle-piece</div>
			      <div class="col-3"><i class="icon-fa fa-qrcode"></i> fa-qrcode</div>
			      <div class="col-3"><i class="icon-fa fa-question"></i> fa-question</div>
			      <div class="col-3"><i class="icon-fa fa-question-circle"></i> fa-question-circle</div>
			      <div class="col-3"><i class="icon-fa fa-quote-left"></i> fa-quote-left</div>
			      <div class="col-3"><i class="icon-fa fa-quote-right"></i> fa-quote-right</div>
			      <div class="col-3"><i class="icon-fa fa-random"></i> fa-random</div>
			      <div class="col-3"><i class="icon-fa fa-recycle"></i> fa-recycle</div>
			      <div class="col-3"><i class="icon-fa fa-refresh"></i> fa-refresh</div>
			      <div class="col-3"><i class="icon-fa fa-remove"></i> fa-remove</div>
			      <div class="col-3"><i class="icon-fa fa-reorder"></i> fa-reorder</div>
			      <div class="col-3"><i class="icon-fa fa-reply"></i> fa-reply</div>
			      <div class="col-3"><i class="icon-fa fa-reply-all"></i> fa-reply-all</div>
			      <div class="col-3"><i class="icon-fa fa-retweet"></i> fa-retweet</div>
			      <div class="col-3"><i class="icon-fa fa-road"></i> fa-road</div>
			      <div class="col-3"><i class="icon-fa fa-rocket"></i> fa-rocket</div>
			      <div class="col-3"><i class="icon-fa fa-rss"></i> fa-rss</div>
			      <div class="col-3"><i class="icon-fa fa-rss-square"></i> fa-rss-square</div>
			      <div class="col-3"><i class="icon-fa fa-search"></i> fa-search</div>
			      <div class="col-3"><i class="icon-fa fa-search-minus"></i> fa-search-minus</div>
			      <div class="col-3"><i class="icon-fa fa-search-plus"></i> fa-search-plus</div>
			      <div class="col-3"><i class="icon-fa fa-send"></i> fa-send</div>
			      <div class="col-3"><i class="icon-fa fa-send-o"></i> fa-send-o</div>
			      <div class="col-3"><i class="icon-fa fa-share"></i> fa-share</div>
			      <div class="col-3"><i class="icon-fa fa-share-alt"></i> fa-share-alt</div>
			      <div class="col-3"><i class="icon-fa fa-share-alt-square"></i> fa-share-alt-square</div>
			      <div class="col-3"><i class="icon-fa fa-share-square"></i> fa-share-square</div>
			      <div class="col-3"><i class="icon-fa fa-share-square-o"></i> fa-share-square-o</div>
			      <div class="col-3"><i class="icon-fa fa-shield"></i> fa-shield</div>
			      <div class="col-3"><i class="icon-fa fa-shopping-cart"></i> fa-shopping-cart</div>
			      <div class="col-3"><i class="icon-fa fa-sign-in"></i> fa-sign-in</div>
			      <div class="col-3"><i class="icon-fa fa-sign-out"></i> fa-sign-out</div>
			      <div class="col-3"><i class="icon-fa fa-signal"></i> fa-signal</div>
			      <div class="col-3"><i class="icon-fa fa-sitemap"></i> fa-sitemap</div>
			      <div class="col-3"><i class="icon-fa fa-sliders"></i> fa-sliders</div>
			      <div class="col-3"><i class="icon-fa fa-smile-o"></i> fa-smile-o</div>
			      <div class="col-3"><i class="icon-fa fa-soccer-ball-o"></i> fa-soccer-ball-o</div>
			      <div class="col-3"><i class="icon-fa fa-sort"></i> fa-sort</div>
			      <div class="col-3"><i class="icon-fa fa-sort-alpha-asc"></i> fa-sort-alpha-asc</div>
			      <div class="col-3"><i class="icon-fa fa-sort-alpha-desc"></i> fa-sort-alpha-desc</div>
			      <div class="col-3"><i class="icon-fa fa-sort-amount-asc"></i> fa-sort-amount-asc</div>
			      <div class="col-3"><i class="icon-fa fa-sort-amount-desc"></i> fa-sort-amount-desc</div>
			      <div class="col-3"><i class="icon-fa fa-sort-asc"></i> fa-sort-asc</div>
			      <div class="col-3"><i class="icon-fa fa-sort-desc"></i> fa-sort-desc</div>
			      <div class="col-3"><i class="icon-fa fa-sort-down"></i> fa-sort-down</div>
			      <div class="col-3"><i class="icon-fa fa-sort-numeric-asc"></i> fa-sort-numeric-asc</div>
			      <div class="col-3"><i class="icon-fa fa-sort-numeric-desc"></i> fa-sort-numeric-desc</div>
			      <div class="col-3"><i class="icon-fa fa-sort-up"></i> fa-sort-up</div>
			      <div class="col-3"><i class="icon-fa fa-space-shuttle"></i> fa-space-shuttle</div>
			      <div class="col-3"><i class="icon-fa fa-spinner"></i> fa-spinner</div>
			      <div class="col-3"><i class="icon-fa fa-spoon"></i> fa-spoon</div>
			      <div class="col-3"><i class="icon-fa fa-square"></i> fa-square</div>
			      <div class="col-3"><i class="icon-fa fa-square-o"></i> fa-square-o</div>
			      <div class="col-3"><i class="icon-fa fa-star"></i> fa-star</div>
			      <div class="col-3"><i class="icon-fa fa-star-half"></i> fa-star-half</div>
			      <div class="col-3"><i class="icon-fa fa-star-half-empty"></i> fa-star-half-empty</div>
			      <div class="col-3"><i class="icon-fa fa-star-half-full"></i> fa-star-half-full</div>
			      <div class="col-3"><i class="icon-fa fa-star-half-o"></i> fa-star-half-o</div>
			      <div class="col-3"><i class="icon-fa fa-star-o"></i> fa-star-o</div>
			      <div class="col-3"><i class="icon-fa fa-suitcase"></i> fa-suitcase</div>
			      <div class="col-3"><i class="icon-fa fa-sun-o"></i> fa-sun-o</div>
			      <div class="col-3"><i class="icon-fa fa-support"></i> fa-support</div>
			      <div class="col-3"><i class="icon-fa fa-tablet"></i> fa-tablet</div>
			      <div class="col-3"><i class="icon-fa fa-tachometer"></i> fa-tachometer</div>
			      <div class="col-3"><i class="icon-fa fa-tag"></i> fa-tag</div>
			      <div class="col-3"><i class="icon-fa fa-tags"></i> fa-tags</div>
			      <div class="col-3"><i class="icon-fa fa-tasks"></i> fa-tasks</div>
			      <div class="col-3"><i class="icon-fa fa-taxi"></i> fa-taxi</div>
			      <div class="col-3"><i class="icon-fa fa-terminal"></i> fa-terminal</div>
			      <div class="col-3"><i class="icon-fa fa-thumb-tack"></i> fa-thumb-tack</div>
			      <div class="col-3"><i class="icon-fa fa-thumbs-down"></i> fa-thumbs-down</div>
			      <div class="col-3"><i class="icon-fa fa-thumbs-o-down"></i> fa-thumbs-o-down</div>
			      <div class="col-3"><i class="icon-fa fa-thumbs-o-up"></i> fa-thumbs-o-up</div>
			      <div class="col-3"><i class="icon-fa fa-thumbs-up"></i> fa-thumbs-up</div>
			      <div class="col-3"><i class="icon-fa fa-ticket"></i> fa-ticket</div>
			      <div class="col-3"><i class="icon-fa fa-times"></i> fa-times</div>
			      <div class="col-3"><i class="icon-fa fa-times-circle"></i> fa-times-circle</div>
			      <div class="col-3"><i class="icon-fa fa-times-circle-o"></i> fa-times-circle-o</div>
			      <div class="col-3"><i class="icon-fa fa-tint"></i> fa-tint</div>
			      <div class="col-3"><i class="icon-fa fa-toggle-down"></i> fa-toggle-down</div>
			      <div class="col-3"><i class="icon-fa fa-toggle-left"></i> fa-toggle-left</div>
			      <div class="col-3"><i class="icon-fa fa-toggle-off"></i> fa-toggle-off</div>
			      <div class="col-3"><i class="icon-fa fa-toggle-on"></i> fa-toggle-on</div>
			      <div class="col-3"><i class="icon-fa fa-toggle-right"></i> fa-toggle-right</div>
			      <div class="col-3"><i class="icon-fa fa-toggle-up"></i> fa-toggle-up</div>
			      <div class="col-3"><i class="icon-fa fa-trash"></i> fa-trash</div>
			      <div class="col-3"><i class="icon-fa fa-trash-o"></i> fa-trash-o</div>
			      <div class="col-3"><i class="icon-fa fa-tree"></i> fa-tree</div>
			      <div class="col-3"><i class="icon-fa fa-trophy"></i> fa-trophy</div>
			      <div class="col-3"><i class="icon-fa fa-truck"></i> fa-truck</div>
			      <div class="col-3"><i class="icon-fa fa-tty"></i> fa-tty</div>
			      <div class="col-3"><i class="icon-fa fa-umbrella"></i> fa-umbrella</div>
			      <div class="col-3"><i class="icon-fa fa-university"></i> fa-university</div>
			      <div class="col-3"><i class="icon-fa fa-unlock"></i> fa-unlock</div>
			      <div class="col-3"><i class="icon-fa fa-unlock-alt"></i> fa-unlock-alt</div>
			      <div class="col-3"><i class="icon-fa fa-unsorted"></i> fa-unsorted</div>
			      <div class="col-3"><i class="icon-fa fa-upload"></i> fa-upload</div>
			      <div class="col-3"><i class="icon-fa fa-user"></i> fa-user</div>
			      <div class="col-3"><i class="icon-fa fa-users"></i> fa-users</div>
			      <div class="col-3"><i class="icon-fa fa-video-camera"></i> fa-video-camera</div>
			      <div class="col-3"><i class="icon-fa fa-volume-down"></i> fa-volume-down</div>
			      <div class="col-3"><i class="icon-fa fa-volume-off"></i> fa-volume-off</div>
			      <div class="col-3"><i class="icon-fa fa-volume-up"></i> fa-volume-up</div>
			      <div class="col-3"><i class="icon-fa fa-warning"></i> fa-warning</div>
			      <div class="col-3"><i class="icon-fa fa-wheelchair"></i> fa-wheelchair</div>
			      <div class="col-3"><i class="icon-fa fa-wifi"></i> fa-wifi</div>
			      <div class="col-3"><i class="icon-fa fa-wrench"></i> fa-wrench</div>
			      <div class="col-3"><i class="icon-fa fa-file"></i> fa-file</div>
			      <div class="col-3"><i class="icon-fa fa-file-archive-o"></i> fa-file-archive-o</div>
			      <div class="col-3"><i class="icon-fa fa-file-audio-o"></i> fa-file-audio-o</div>
			      <div class="col-3"><i class="icon-fa fa-file-code-o"></i> fa-file-code-o</div>
			      <div class="col-3"><i class="icon-fa fa-file-excel-o"></i> fa-file-excel-o</div>
			      <div class="col-3"><i class="icon-fa fa-file-image-o"></i> fa-file-image-o</div>
			      <div class="col-3"><i class="icon-fa fa-file-movie-o"></i> fa-file-movie-o</div>
			      <div class="col-3"><i class="icon-fa fa-file-o"></i> fa-file-o</div>
			      <div class="col-3"><i class="icon-fa fa-file-pdf-o"></i> fa-file-pdf-o</div>
			      <div class="col-3"><i class="icon-fa fa-file-photo-o"></i> fa-file-photo-o</div>
			      <div class="col-3"><i class="icon-fa fa-file-picture-o"></i> fa-file-picture-o</div>
			      <div class="col-3"><i class="icon-fa fa-file-powerpoint-o"></i> fa-file-powerpoint-o</div>
			      <div class="col-3"><i class="icon-fa fa-file-sound-o"></i> fa-file-sound-o</div>
			      <div class="col-3"><i class="icon-fa fa-file-text"></i> fa-file-text</div>
			      <div class="col-3"><i class="icon-fa fa-file-text-o"></i> fa-file-text-o</div>
			      <div class="col-3"><i class="icon-fa fa-file-video-o"></i> fa-file-video-o</div>
			      <div class="col-3"><i class="icon-fa fa-file-word-o"></i> fa-file-word-o</div>
			      <div class="col-3"><i class="icon-fa fa-file-zip-o"></i> fa-file-zip-o</div>
			      <div class="col-3"><i class="icon-fa fa-circle-o-notch"></i> fa-circle-o-notch</div>
			      <div class="col-3"><i class="icon-fa fa-cog"></i> fa-cog</div>
			      <div class="col-3"><i class="icon-fa fa-gear"></i> fa-gear</div>
			      <div class="col-3"><i class="icon-fa fa-refresh"></i> fa-refresh</div>
			      <div class="col-3"><i class="icon-fa fa-spinner"></i> fa-spinner</div>
			      <div class="col-3"><i class="icon-fa fa-check-square"></i> fa-check-square</div>
			      <div class="col-3"><i class="icon-fa fa-check-square-o"></i> fa-check-square-o</div>
			      <div class="col-3"><i class="icon-fa fa-circle"></i> fa-circle</div>
			      <div class="col-3"><i class="icon-fa fa-circle-o"></i> fa-circle-o</div>
			      <div class="col-3"><i class="icon-fa fa-dot-circle-o"></i> fa-dot-circle-o</div>
			      <div class="col-3"><i class="icon-fa fa-minus-square"></i> fa-minus-square</div>
			      <div class="col-3"><i class="icon-fa fa-minus-square-o"></i> fa-minus-square-o</div>
			      <div class="col-3"><i class="icon-fa fa-plus-square"></i> fa-plus-square</div>
			      <div class="col-3"><i class="icon-fa fa-plus-square-o"></i> fa-plus-square-o</div>
			      <div class="col-3"><i class="icon-fa fa-square"></i> fa-square</div>
			      <div class="col-3"><i class="icon-fa fa-square-o"></i> fa-square-o</div>
			      <div class="col-3"><i class="icon-fa fa-cc-amex"></i> fa-cc-amex</div>
			      <div class="col-3"><i class="icon-fa fa-cc-discover"></i> fa-cc-discover</div>
			      <div class="col-3"><i class="icon-fa fa-cc-mastercard"></i> fa-cc-mastercard</div>
			      <div class="col-3"><i class="icon-fa fa-cc-paypal"></i> fa-cc-paypal</div>
			      <div class="col-3"><i class="icon-fa fa-cc-stripe"></i> fa-cc-stripe</div>
			      <div class="col-3"><i class="icon-fa fa-cc-visa"></i> fa-cc-visa</div>
			      <div class="col-3"><i class="icon-fa fa-credit-card"></i> fa-credit-card</div>
			      <div class="col-3"><i class="icon-fa fa-google-wallet"></i> fa-google-wallet</div>
			      <div class="col-3"><i class="icon-fa fa-paypal"></i> fa-paypal</div>
			      <div class="col-3"><i class="icon-fa fa-area-chart"></i> fa-area-chart</div>
			      <div class="col-3"><i class="icon-fa fa-bar-chart"></i> fa-bar-chart</div>
			      <div class="col-3"><i class="icon-fa fa-bar-chart-o"></i> fa-bar-chart-o</div>
			      <div class="col-3"><i class="icon-fa fa-line-chart"></i> fa-line-chart</div>
			      <div class="col-3"><i class="icon-fa fa-pie-chart"></i> fa-pie-chart</div>
			      <div class="col-3"><i class="icon-fa fa-bitcoin"></i> fa-bitcoin</div>
			      <div class="col-3"><i class="icon-fa fa-btc"></i> fa-btc</div>
			      <div class="col-3"><i class="icon-fa fa-cny"></i> fa-cny</div>
			      <div class="col-3"><i class="icon-fa fa-dollar"></i> fa-dollar</div>
			      <div class="col-3"><i class="icon-fa fa-eur"></i> fa-eur</div>
			      <div class="col-3"><i class="icon-fa fa-euro"></i> fa-euro</div>
			      <div class="col-3"><i class="icon-fa fa-gbp"></i> fa-gbp</div>
			      <div class="col-3"><i class="icon-fa fa-ils"></i> fa-ils</div>
			      <div class="col-3"><i class="icon-fa fa-inr"></i> fa-inr</div>
			      <div class="col-3"><i class="icon-fa fa-jpy"></i> fa-jpy</div>
			      <div class="col-3"><i class="icon-fa fa-krw"></i> fa-krw</div>
			      <div class="col-3"><i class="icon-fa fa-money"></i> fa-money</div>
			      <div class="col-3"><i class="icon-fa fa-rmb"></i> fa-rmb</div>
			      <div class="col-3"><i class="icon-fa fa-rouble"></i> fa-rouble</div>
			      <div class="col-3"><i class="icon-fa fa-rub"></i> fa-rub</div>
			      <div class="col-3"><i class="icon-fa fa-ruble"></i> fa-ruble</div>
			      <div class="col-3"><i class="icon-fa fa-rupee"></i> fa-rupee</div>
			      <div class="col-3"><i class="icon-fa fa-shekel"></i> fa-shekel</div>
			      <div class="col-3"><i class="icon-fa fa-sheqel"></i> fa-sheqel</div>
			      <div class="col-3"><i class="icon-fa fa-try"></i> fa-try</div>
			      <div class="col-3"><i class="icon-fa fa-turkish-lira"></i> fa-turkish-lira</div>
			      <div class="col-3"><i class="icon-fa fa-usd"></i> fa-usd</div>
			      <div class="col-3"><i class="icon-fa fa-won"></i> fa-won</div>
			      <div class="col-3"><i class="icon-fa fa-yen"></i> fa-yen</div>
			      <div class="col-3"><i class="icon-fa fa-align-center"></i> fa-align-center</div>
			      <div class="col-3"><i class="icon-fa fa-align-justify"></i> fa-align-justify</div>
			      <div class="col-3"><i class="icon-fa fa-align-left"></i> fa-align-left</div>
			      <div class="col-3"><i class="icon-fa fa-align-right"></i> fa-align-right</div>
			      <div class="col-3"><i class="icon-fa fa-bold"></i> fa-bold</div>
			      <div class="col-3"><i class="icon-fa fa-chain"></i> fa-chain</div>
			      <div class="col-3"><i class="icon-fa fa-chain-broken"></i> fa-chain-broken</div>
			      <div class="col-3"><i class="icon-fa fa-clipboard"></i> fa-clipboard</div>
			      <div class="col-3"><i class="icon-fa fa-columns"></i> fa-columns</div>
			      <div class="col-3"><i class="icon-fa fa-copy"></i> fa-copy</div>
			      <div class="col-3"><i class="icon-fa fa-cut"></i> fa-cut</div>
			      <div class="col-3"><i class="icon-fa fa-dedent"></i> fa-dedent</div>
			      <div class="col-3"><i class="icon-fa fa-eraser"></i> fa-eraser</div>
			      <div class="col-3"><i class="icon-fa fa-file"></i> fa-file</div>
			      <div class="col-3"><i class="icon-fa fa-file-o"></i> fa-file-o</div>
			      <div class="col-3"><i class="icon-fa fa-file-text"></i> fa-file-text</div>
			      <div class="col-3"><i class="icon-fa fa-file-text-o"></i> fa-file-text-o</div>
			      <div class="col-3"><i class="icon-fa fa-files-o"></i> fa-files-o</div>
			      <div class="col-3"><i class="icon-fa fa-floppy-o"></i> fa-floppy-o</div>
			      <div class="col-3"><i class="icon-fa fa-font"></i> fa-font</div>
			      <div class="col-3"><i class="icon-fa fa-header"></i> fa-header</div>
			      <div class="col-3"><i class="icon-fa fa-indent"></i> fa-indent</div>
			      <div class="col-3"><i class="icon-fa fa-italic"></i> fa-italic</div>
			      <div class="col-3"><i class="icon-fa fa-link"></i> fa-link</div>
			      <div class="col-3"><i class="icon-fa fa-list"></i> fa-list</div>
			      <div class="col-3"><i class="icon-fa fa-list-alt"></i> fa-list-alt</div>
			      <div class="col-3"><i class="icon-fa fa-list-ol"></i> fa-list-ol</div>
			      <div class="col-3"><i class="icon-fa fa-list-ul"></i> fa-list-ul</div>
			      <div class="col-3"><i class="icon-fa fa-outdent"></i> fa-outdent</div>
			      <div class="col-3"><i class="icon-fa fa-paperclip"></i> fa-paperclip</div>
			      <div class="col-3"><i class="icon-fa fa-paragraph"></i> fa-paragraph</div>
			      <div class="col-3"><i class="icon-fa fa-paste"></i> fa-paste</div>
			      <div class="col-3"><i class="icon-fa fa-repeat"></i> fa-repeat</div>
			      <div class="col-3"><i class="icon-fa fa-rotate-left"></i> fa-rotate-left</div>
			      <div class="col-3"><i class="icon-fa fa-rotate-right"></i> fa-rotate-right</div>
			      <div class="col-3"><i class="icon-fa fa-save"></i> fa-save</div>
			      <div class="col-3"><i class="icon-fa fa-scissors"></i> fa-scissors</div>
			      <div class="col-3"><i class="icon-fa fa-strikethrough"></i> fa-strikethrough</div>
			      <div class="col-3"><i class="icon-fa fa-subscript"></i> fa-subscript</div>
			      <div class="col-3"><i class="icon-fa fa-superscript"></i> fa-superscript</div>
			      <div class="col-3"><i class="icon-fa fa-table"></i> fa-table</div>
			      <div class="col-3"><i class="icon-fa fa-text-height"></i> fa-text-height</div>
			      <div class="col-3"><i class="icon-fa fa-text-width"></i> fa-text-width</div>
			      <div class="col-3"><i class="icon-fa fa-th"></i> fa-th</div>
			      <div class="col-3"><i class="icon-fa fa-th-large"></i> fa-th-large</div>
			      <div class="col-3"><i class="icon-fa fa-th-list"></i> fa-th-list</div>
			      <div class="col-3"><i class="icon-fa fa-underline"></i> fa-underline</div>
			      <div class="col-3"><i class="icon-fa fa-undo"></i> fa-undo</div>
			      <div class="col-3"><i class="icon-fa fa-unlink"></i> fa-unlink</div>
			      <div class="col-3"><i class="icon-fa fa-angle-double-down"></i> fa-angle-double-down</div>
			      <div class="col-3"><i class="icon-fa fa-angle-double-left"></i> fa-angle-double-left</div>
			      <div class="col-3"><i class="icon-fa fa-angle-double-right"></i> fa-angle-double-right</div>
			      <div class="col-3"><i class="icon-fa fa-angle-double-up"></i> fa-angle-double-up</div>
			      <div class="col-3"><i class="icon-fa fa-angle-down"></i> fa-angle-down</div>
			      <div class="col-3"><i class="icon-fa fa-angle-left"></i> fa-angle-left</div>
			      <div class="col-3"><i class="icon-fa fa-angle-right"></i> fa-angle-right</div>
			      <div class="col-3"><i class="icon-fa fa-angle-up"></i> fa-angle-up</div>
			      <div class="col-3"><i class="icon-fa fa-arrow-circle-down"></i> fa-arrow-circle-down</div>
			      <div class="col-3"><i class="icon-fa fa-arrow-circle-left"></i> fa-arrow-circle-left</div>
			      <div class="col-3"><i class="icon-fa fa-arrow-circle-o-down"></i> fa-arrow-circle-o-down</div>
			      <div class="col-3"><i class="icon-fa fa-arrow-circle-o-left"></i> fa-arrow-circle-o-left</div>
			      <div class="col-3"><i class="icon-fa fa-arrow-circle-o-right"></i> fa-arrow-circle-o-right</div>
			      <div class="col-3"><i class="icon-fa fa-arrow-circle-o-up"></i> fa-arrow-circle-o-up</div>
			      <div class="col-3"><i class="icon-fa fa-arrow-circle-right"></i> fa-arrow-circle-right</div>
			      <div class="col-3"><i class="icon-fa fa-arrow-circle-up"></i> fa-arrow-circle-up</div>
			      <div class="col-3"><i class="icon-fa fa-arrow-down"></i> fa-arrow-down</div>
			      <div class="col-3"><i class="icon-fa fa-arrow-left"></i> fa-arrow-left</div>
			      <div class="col-3"><i class="icon-fa fa-arrow-right"></i> fa-arrow-right</div>
			      <div class="col-3"><i class="icon-fa fa-arrow-up"></i> fa-arrow-up</div>
			      <div class="col-3"><i class="icon-fa fa-arrows"></i> fa-arrows</div>
			      <div class="col-3"><i class="icon-fa fa-arrows-alt"></i> fa-arrows-alt</div>
			      <div class="col-3"><i class="icon-fa fa-arrows-h"></i> fa-arrows-h</div>
			      <div class="col-3"><i class="icon-fa fa-arrows-v"></i> fa-arrows-v</div>
			      <div class="col-3"><i class="icon-fa fa-caret-down"></i> fa-caret-down</div>
			      <div class="col-3"><i class="icon-fa fa-caret-left"></i> fa-caret-left</div>
			      <div class="col-3"><i class="icon-fa fa-caret-right"></i> fa-caret-right</div>
			      <div class="col-3"><i class="icon-fa fa-caret-square-o-down"></i> fa-caret-square-o-down</div>
			      <div class="col-3"><i class="icon-fa fa-caret-square-o-left"></i> fa-caret-square-o-left</div>
			      <div class="col-3"><i class="icon-fa fa-caret-square-o-right"></i> fa-caret-square-o-right</div>
			      <div class="col-3"><i class="icon-fa fa-caret-square-o-up"></i> fa-caret-square-o-up</div>
			      <div class="col-3"><i class="icon-fa fa-caret-up"></i> fa-caret-up</div>
			      <div class="col-3"><i class="icon-fa fa-chevron-circle-down"></i> fa-chevron-circle-down</div>
			      <div class="col-3"><i class="icon-fa fa-chevron-circle-left"></i> fa-chevron-circle-left</div>
			      <div class="col-3"><i class="icon-fa fa-chevron-circle-right"></i> fa-chevron-circle-right</div>
			      <div class="col-3"><i class="icon-fa fa-chevron-circle-up"></i> fa-chevron-circle-up</div>
			      <div class="col-3"><i class="icon-fa fa-chevron-down"></i> fa-chevron-down</div>
			      <div class="col-3"><i class="icon-fa fa-chevron-left"></i> fa-chevron-left</div>
			      <div class="col-3"><i class="icon-fa fa-chevron-right"></i> fa-chevron-right</div>
			      <div class="col-3"><i class="icon-fa fa-chevron-up"></i> fa-chevron-up</div>
			      <div class="col-3"><i class="icon-fa fa-hand-o-down"></i> fa-hand-o-down</div>
			      <div class="col-3"><i class="icon-fa fa-hand-o-left"></i> fa-hand-o-left</div>
			      <div class="col-3"><i class="icon-fa fa-hand-o-right"></i> fa-hand-o-right</div>
			      <div class="col-3"><i class="icon-fa fa-hand-o-up"></i> fa-hand-o-up</div>
			      <div class="col-3"><i class="icon-fa fa-long-arrow-down"></i> fa-long-arrow-down</div>
			      <div class="col-3"><i class="icon-fa fa-long-arrow-left"></i> fa-long-arrow-left</div>
			      <div class="col-3"><i class="icon-fa fa-long-arrow-right"></i> fa-long-arrow-right</div>
			      <div class="col-3"><i class="icon-fa fa-long-arrow-up"></i> fa-long-arrow-up</div>
			      <div class="col-3"><i class="icon-fa fa-toggle-down"></i> fa-toggle-down</div>
			      <div class="col-3"><i class="icon-fa fa-toggle-left"></i> fa-toggle-left</div>
			      <div class="col-3"><i class="icon-fa fa-toggle-right"></i> fa-toggle-right</div>
			      <div class="col-3"><i class="icon-fa fa-toggle-up"></i> fa-toggle-up</div>
			      <div class="col-3"><i class="icon-fa fa-arrows-alt"></i> fa-arrows-alt</div>
			      <div class="col-3"><i class="icon-fa fa-backward"></i> fa-backward</div>
			      <div class="col-3"><i class="icon-fa fa-compress"></i> fa-compress</div>
			      <div class="col-3"><i class="icon-fa fa-eject"></i> fa-eject</div>
			      <div class="col-3"><i class="icon-fa fa-expand"></i> fa-expand</div>
			      <div class="col-3"><i class="icon-fa fa-fast-backward"></i> fa-fast-backward</div>
			      <div class="col-3"><i class="icon-fa fa-fast-forward"></i> fa-fast-forward</div>
			      <div class="col-3"><i class="icon-fa fa-forward"></i> fa-forward</div>
			      <div class="col-3"><i class="icon-fa fa-pause"></i> fa-pause</div>
			      <div class="col-3"><i class="icon-fa fa-play"></i> fa-play</div>
			      <div class="col-3"><i class="icon-fa fa-play-circle"></i> fa-play-circle</div>
			      <div class="col-3"><i class="icon-fa fa-play-circle-o"></i> fa-play-circle-o</div>
			      <div class="col-3"><i class="icon-fa fa-step-backward"></i> fa-step-backward</div>
			      <div class="col-3"><i class="icon-fa fa-step-forward"></i> fa-step-forward</div>
			      <div class="col-3"><i class="icon-fa fa-stop"></i> fa-stop</div>
			      <div class="col-3"><i class="icon-fa fa-youtube-play"></i> fa-youtube-play</div>
			      <div class="col-3"><i class="icon-fa fa-adn"></i> fa-adn</div>
			      <div class="col-3"><i class="icon-fa fa-android"></i> fa-android</div>
			      <div class="col-3"><i class="icon-fa fa-angellist"></i> fa-angellist</div>
			      <div class="col-3"><i class="icon-fa fa-apple"></i> fa-apple</div>
			      <div class="col-3"><i class="icon-fa fa-behance"></i> fa-behance</div>
			      <div class="col-3"><i class="icon-fa fa-behance-square"></i> fa-behance-square</div>
			      <div class="col-3"><i class="icon-fa fa-bitbucket"></i> fa-bitbucket</div>
			      <div class="col-3"><i class="icon-fa fa-bitbucket-square"></i> fa-bitbucket-square</div>
			      <div class="col-3"><i class="icon-fa fa-bitcoin"></i> fa-bitcoin</div>
			      <div class="col-3"><i class="icon-fa fa-btc"></i> fa-btc</div>
			      <div class="col-3"><i class="icon-fa fa-cc-amex"></i> fa-cc-amex</div>
			      <div class="col-3"><i class="icon-fa fa-cc-discover"></i> fa-cc-discover</div>
			      <div class="col-3"><i class="icon-fa fa-cc-mastercard"></i> fa-cc-mastercard</div>
			      <div class="col-3"><i class="icon-fa fa-cc-paypal"></i> fa-cc-paypal</div>
			      <div class="col-3"><i class="icon-fa fa-cc-stripe"></i> fa-cc-stripe</div>
			      <div class="col-3"><i class="icon-fa fa-cc-visa"></i> fa-cc-visa</div>
			      <div class="col-3"><i class="icon-fa fa-codepen"></i> fa-codepen</div>
			      <div class="col-3"><i class="icon-fa fa-css3"></i> fa-css3</div>
			      <div class="col-3"><i class="icon-fa fa-delicious"></i> fa-delicious</div>
			      <div class="col-3"><i class="icon-fa fa-deviantart"></i> fa-deviantart</div>
			      <div class="col-3"><i class="icon-fa fa-digg"></i> fa-digg</div>
			      <div class="col-3"><i class="icon-fa fa-dribbble"></i> fa-dribbble</div>
			      <div class="col-3"><i class="icon-fa fa-dropbox"></i> fa-dropbox</div>
			      <div class="col-3"><i class="icon-fa fa-drupal"></i> fa-drupal</div>
			      <div class="col-3"><i class="icon-fa fa-empire"></i> fa-empire</div>
			      <div class="col-3"><i class="icon-fa fa-facebook"></i> fa-facebook</div>
			      <div class="col-3"><i class="icon-fa fa-facebook-square"></i> fa-facebook-square</div>
			      <div class="col-3"><i class="icon-fa fa-flickr"></i> fa-flickr</div>
			      <div class="col-3"><i class="icon-fa fa-foursquare"></i> fa-foursquare</div>
			      <div class="col-3"><i class="icon-fa fa-ge"></i> fa-ge</div>
			      <div class="col-3"><i class="icon-fa fa-git"></i> fa-git</div>
			      <div class="col-3"><i class="icon-fa fa-git-square"></i> fa-git-square</div>
			      <div class="col-3"><i class="icon-fa fa-github"></i> fa-github</div>
			      <div class="col-3"><i class="icon-fa fa-github-alt"></i> fa-github-alt</div>
			      <div class="col-3"><i class="icon-fa fa-github-square"></i> fa-github-square</div>
			      <div class="col-3"><i class="icon-fa fa-gittip"></i> fa-gittip</div>
			      <div class="col-3"><i class="icon-fa fa-google"></i> fa-google</div>
			      <div class="col-3"><i class="icon-fa fa-google-plus"></i> fa-google-plus</div>
			      <div class="col-3"><i class="icon-fa fa-google-plus-square"></i> fa-google-plus-square</div>
			      <div class="col-3"><i class="icon-fa fa-google-wallet"></i> fa-google-wallet</div>
			      <div class="col-3"><i class="icon-fa fa-hacker-news"></i> fa-hacker-news</div>
			      <div class="col-3"><i class="icon-fa fa-html5"></i> fa-html5</div>
			      <div class="col-3"><i class="icon-fa fa-instagram"></i> fa-instagram</div>
			      <div class="col-3"><i class="icon-fa fa-ioxhost"></i> fa-ioxhost</div>
			      <div class="col-3"><i class="icon-fa fa-joomla"></i> fa-joomla</div>
			      <div class="col-3"><i class="icon-fa fa-jsfiddle"></i> fa-jsfiddle</div>
			      <div class="col-3"><i class="icon-fa fa-lastfm"></i> fa-lastfm</div>
			      <div class="col-3"><i class="icon-fa fa-lastfm-square"></i> fa-lastfm-square</div>
			      <div class="col-3"><i class="icon-fa fa-linkedin"></i> fa-linkedin</div>
			      <div class="col-3"><i class="icon-fa fa-linkedin-square"></i> fa-linkedin-square</div>
			      <div class="col-3"><i class="icon-fa fa-linux"></i> fa-linux</div>
			      <div class="col-3"><i class="icon-fa fa-maxcdn"></i> fa-maxcdn</div>
			      <div class="col-3"><i class="icon-fa fa-meanpath"></i> fa-meanpath</div>
			      <div class="col-3"><i class="icon-fa fa-openid"></i> fa-openid</div>
			      <div class="col-3"><i class="icon-fa fa-pagelines"></i> fa-pagelines</div>
			      <div class="col-3"><i class="icon-fa fa-paypal"></i> fa-paypal</div>
			      <div class="col-3"><i class="icon-fa fa-pied-piper"></i> fa-pied-piper</div>
			      <div class="col-3"><i class="icon-fa fa-pied-piper-alt"></i> fa-pied-piper-alt</div>
			      <div class="col-3"><i class="icon-fa fa-pinterest"></i> fa-pinterest</div>
			      <div class="col-3"><i class="icon-fa fa-pinterest-square"></i> fa-pinterest-square</div>
			      <div class="col-3"><i class="icon-fa fa-qq"></i> fa-qq</div>
			      <div class="col-3"><i class="icon-fa fa-ra"></i> fa-ra</div>
			      <div class="col-3"><i class="icon-fa fa-rebel"></i> fa-rebel</div>
			      <div class="col-3"><i class="icon-fa fa-reddit"></i> fa-reddit</div>
			      <div class="col-3"><i class="icon-fa fa-reddit-square"></i> fa-reddit-square</div>
			      <div class="col-3"><i class="icon-fa fa-renren"></i> fa-renren</div>
			      <div class="col-3"><i class="icon-fa fa-share-alt"></i> fa-share-alt</div>
			      <div class="col-3"><i class="icon-fa fa-share-alt-square"></i> fa-share-alt-square</div>
			      <div class="col-3"><i class="icon-fa fa-skype"></i> fa-skype</div>
			      <div class="col-3"><i class="icon-fa fa-slack"></i> fa-slack</div>
			      <div class="col-3"><i class="icon-fa fa-slideshare"></i> fa-slideshare</div>
			      <div class="col-3"><i class="icon-fa fa-soundcloud"></i> fa-soundcloud</div>
			      <div class="col-3"><i class="icon-fa fa-spotify"></i> fa-spotify</div>
			      <div class="col-3"><i class="icon-fa fa-stack-exchange"></i> fa-stack-exchange</div>
			      <div class="col-3"><i class="icon-fa fa-stack-overflow"></i> fa-stack-overflow</div>
			      <div class="col-3"><i class="icon-fa fa-steam"></i> fa-steam</div>
			      <div class="col-3"><i class="icon-fa fa-steam-square"></i> fa-steam-square</div>
			      <div class="col-3"><i class="icon-fa fa-stumbleupon"></i> fa-stumbleupon</div>
			      <div class="col-3"><i class="icon-fa fa-stumbleupon-circle"></i> fa-stumbleupon-circle</div>
			      <div class="col-3"><i class="icon-fa fa-tencent-weibo"></i> fa-tencent-weibo</div>
			      <div class="col-3"><i class="icon-fa fa-trello"></i> fa-trello</div>
			      <div class="col-3"><i class="icon-fa fa-tumblr"></i> fa-tumblr</div>
			      <div class="col-3"><i class="icon-fa fa-tumblr-square"></i> fa-tumblr-square</div>
			      <div class="col-3"><i class="icon-fa fa-twitch"></i> fa-twitch</div>
			      <div class="col-3"><i class="icon-fa fa-twitter"></i> fa-twitter</div>
			      <div class="col-3"><i class="icon-fa fa-twitter-square"></i> fa-twitter-square</div>
			      <div class="col-3"><i class="icon-fa fa-vimeo-square"></i> fa-vimeo-square</div>
			      <div class="col-3"><i class="icon-fa fa-vine"></i> fa-vine</div>
			      <div class="col-3"><i class="icon-fa fa-vk"></i> fa-vk</div>
			      <div class="col-3"><i class="icon-fa fa-wechat"></i> fa-wechat</div>
			      <div class="col-3"><i class="icon-fa fa-weibo"></i> fa-weibo</div>
			      <div class="col-3"><i class="icon-fa fa-weixin"></i> fa-weixin</div>
			      <div class="col-3"><i class="icon-fa fa-windows"></i> fa-windows</div>
			      <div class="col-3"><i class="icon-fa fa-wordpress"></i> fa-wordpress</div>
			      <div class="col-3"><i class="icon-fa fa-xing"></i> fa-xing</div>
			      <div class="col-3"><i class="icon-fa fa-xing-square"></i> fa-xing-square</div>
			      <div class="col-3"><i class="icon-fa fa-yahoo"></i> fa-yahoo</div>
			      <div class="col-3"><i class="icon-fa fa-yelp"></i> fa-yelp</div>
			      <div class="col-3"><i class="icon-fa fa-youtube"></i> fa-youtube</div>
			      <div class="col-3"><i class="icon-fa fa-youtube-play"></i> fa-youtube-play</div>
			      <div class="col-3"><i class="icon-fa fa-youtube-square"></i> fa-youtube-square</div>
			      <div class="col-3"><i class="icon-fa fa-ambulance"></i> fa-ambulance</div>
			      <div class="col-3"><i class="icon-fa fa-h-square"></i> fa-h-square</div>
			      <div class="col-3"><i class="icon-fa fa-hospital-o"></i> fa-hospital-o</div>
			      <div class="col-3"><i class="icon-fa fa-medkit"></i> fa-medkit</div>
			      <div class="col-3"><i class="icon-fa fa-plus-square"></i> fa-plus-square</div>
			      <div class="col-3"><i class="icon-fa fa-stethoscope"></i> fa-stethoscope</div>
			      <div class="col-3"><i class="icon-fa fa-user-md"></i> fa-user-md</div>
			      <div class="col-3"><i class="icon-fa fa-wheelchair"></i> fa-wheelchair</div>
			      <!-- 4.3.0 -->
			      <div class="col-3"><i class="icon-fa fa-bed"></i> fa-bed</div>
					<div class="col-3"><i class="icon-fa fa-buysellads"></i> fa-buysellads</div>
					<div class="col-3"><i class="icon-fa fa-cart-arrow-down"></i> fa-cart-arrow-down</div>
					<div class="col-3"><i class="icon-fa fa-cart-plus"></i> fa-cart-plus</div>
					<div class="col-3"><i class="icon-fa fa-connectdevelop"></i> fa-connectdevelop</div>
					<div class="col-3"><i class="icon-fa fa-dashcube"></i> fa-dashcube</div>
					<div class="col-3"><i class="icon-fa fa-diamond"></i> fa-diamond</div>
					<div class="col-3"><i class="icon-fa fa-facebook-official"></i> fa-facebook-official</div>
					<div class="col-3"><i class="icon-fa fa-forumbee"></i> fa-forumbee</div>
					<div class="col-3"><i class="icon-fa fa-heartbeat"></i> fa-heartbeat</div>
					<div class="col-3"><i class="icon-fa fa-hotel"></i> fa-hotel</div>
					<div class="col-3"><i class="icon-fa fa-leanpub"></i> fa-leanpub</div>
					<div class="col-3"><i class="icon-fa fa-mars"></i> fa-mars</div>
					<div class="col-3"><i class="icon-fa fa-mars-double"></i> fa-mars-double</div>
					<div class="col-3"><i class="icon-fa fa-mars-stroke"></i> fa-mars-stroke</div>
					<div class="col-3"><i class="icon-fa fa-mars-stroke-h"></i> fa-mars-stroke-h</div>
					<div class="col-3"><i class="icon-fa fa-mars-stroke-v"></i> fa-mars-stroke-v</div>
					<div class="col-3"><i class="icon-fa fa-medium"></i> fa-medium</div>
					<div class="col-3"><i class="icon-fa fa-mercury"></i> fa-mercury</div>
					<div class="col-3"><i class="icon-fa fa-motorcycle"></i> fa-motorcycle</div>
					<div class="col-3"><i class="icon-fa fa-neuter"></i> fa-neuter</div>
					<div class="col-3"><i class="icon-fa fa-pinterest-p"></i> fa-pinterest-p</div>
					<div class="col-3"><i class="icon-fa fa-sellsy"></i> fa-sellsy</div>
					<div class="col-3"><i class="icon-fa fa-server"></i> fa-server</div>
					<div class="col-3"><i class="icon-fa fa-ship"></i> fa-ship</div>
					<div class="col-3"><i class="icon-fa fa-shirtsinbulk"></i> fa-shirtsinbulk</div>
					<div class="col-3"><i class="icon-fa fa-simplybuilt"></i> fa-simplybuilt</div>
					<div class="col-3"><i class="icon-fa fa-skyatlas"></i> fa-skyatlas</div>
					<div class="col-3"><i class="icon-fa fa-street-view"></i> fa-street-view</div>
					<div class="col-3"><i class="icon-fa fa-subway"></i> fa-subway</div>
					<div class="col-3"><i class="icon-fa fa-train"></i> fa-train</div>
					<div class="col-3"><i class="icon-fa fa-transgender"></i> fa-transgender</div>
					<div class="col-3"><i class="icon-fa fa-transgender-alt"></i> fa-transgender-alt</div>
					<div class="col-3"><i class="icon-fa fa-user-plus"></i> fa-user-plus</div>
					<div class="col-3"><i class="icon-fa fa-user-secret"></i> fa-user-secret</div>
					<div class="col-3"><i class="icon-fa fa-user-times"></i> fa-user-times</div>
					<div class="col-3"><i class="icon-fa fa-venus"></i> fa-venus</div>
					<div class="col-3"><i class="icon-fa fa-venus-double"></i> fa-venus-double</div>
					<div class="col-3"><i class="icon-fa fa-venus-mars"></i> fa-venus-mars</div>
					<div class="col-3"><i class="icon-fa fa-viacoin"></i> fa-viacoin</div>
					<div class="col-3"><i class="icon-fa fa-whatsapp"></i> fa-whatsapp</div>
					<!-- 4.4.0 -->
					<div class="col-3"><i class="icon-fa fa-500px"></i> fa-500px</div>
					<div class="col-3"><i class="icon-fa fa-amazon"></i> fa-amazon</div>
					<div class="col-3"><i class="icon-fa fa-balance-scale"></i> fa-balance-scale</div>
					<div class="col-3"><i class="icon-fa fa-battery-0"></i> fa-battery-0 </div>
					<div class="col-3"><i class="icon-fa fa-battery-1"></i> fa-battery-1 </div>
					<div class="col-3"><i class="icon-fa fa-battery-2"></i> fa-battery-2 </div>
					<div class="col-3"><i class="icon-fa fa-battery-3"></i> fa-battery-3 </div>
					<div class="col-3"><i class="icon-fa fa-battery-4"></i> fa-battery-4 </div>
					<div class="col-3"><i class="icon-fa fa-battery-empty"></i> fa-battery-empty</div>
					<div class="col-3"><i class="icon-fa fa-battery-full"></i> fa-battery-full</div>
					<div class="col-3"><i class="icon-fa fa-battery-half"></i> fa-battery-half</div>
					<div class="col-3"><i class="icon-fa fa-battery-quarter"></i> fa-battery-quarter</div>
					<div class="col-3"><i class="icon-fa fa-battery-three-quarters"></i> fa-battery-three-quarters</div>
					<div class="col-3"><i class="icon-fa fa-black-tie"></i> fa-black-tie</div>
					<div class="col-3"><i class="icon-fa fa-calendar-check-o"></i> fa-calendar-check-o</div>
					<div class="col-3"><i class="icon-fa fa-calendar-minus-o"></i> fa-calendar-minus-o</div>
					<div class="col-3"><i class="icon-fa fa-calendar-plus-o"></i> fa-calendar-plus-o</div>
					<div class="col-3"><i class="icon-fa fa-calendar-times-o"></i> fa-calendar-times-o</div>
					<div class="col-3"><i class="icon-fa fa-cc-diners-club"></i> fa-cc-diners-club</div>
					<div class="col-3"><i class="icon-fa fa-cc-jcb"></i> fa-cc-jcb</div>
					<div class="col-3"><i class="icon-fa fa-chrome"></i> fa-chrome</div>
					<div class="col-3"><i class="icon-fa fa-clone"></i> fa-clone</div>
					<div class="col-3"><i class="icon-fa fa-commenting"></i> fa-commenting</div>
					<div class="col-3"><i class="icon-fa fa-commenting-o"></i> fa-commenting-o</div>
					<div class="col-3"><i class="icon-fa fa-contao"></i> fa-contao</div>
					<div class="col-3"><i class="icon-fa fa-creative-commons"></i> fa-creative-commons</div>
					<div class="col-3"><i class="icon-fa fa-expeditedssl"></i> fa-expeditedssl</div>
					<div class="col-3"><i class="icon-fa fa-firefox"></i> fa-firefox</div>
					<div class="col-3"><i class="icon-fa fa-fonticons"></i> fa-fonticons</div>
					<div class="col-3"><i class="icon-fa fa-genderless"></i> fa-genderless</div>
					<div class="col-3"><i class="icon-fa fa-get-pocket"></i> fa-get-pocket</div>
					<div class="col-3"><i class="icon-fa fa-gg"></i> fa-gg</div>
					<div class="col-3"><i class="icon-fa fa-gg-circle"></i> fa-gg-circle</div>
					<div class="col-3"><i class="icon-fa fa-hand-grab-o"></i> fa-hand-grab-o </div>
					<div class="col-3"><i class="icon-fa fa-hand-lizard-o"></i> fa-hand-lizard-o</div>
					<div class="col-3"><i class="icon-fa fa-hand-paper-o"></i> fa-hand-paper-o</div>
					<div class="col-3"><i class="icon-fa fa-hand-peace-o"></i> fa-hand-peace-o</div>
					<div class="col-3"><i class="icon-fa fa-hand-pointer-o"></i> fa-hand-pointer-o</div>
					<div class="col-3"><i class="icon-fa fa-hand-rock-o"></i> fa-hand-rock-o</div>
					<div class="col-3"><i class="icon-fa fa-hand-scissors-o"></i> fa-hand-scissors-o</div>
					<div class="col-3"><i class="icon-fa fa-hand-spock-o"></i> fa-hand-spock-o</div>
					<div class="col-3"><i class="icon-fa fa-hand-stop-o"></i> fa-hand-stop-o </div>
					<div class="col-3"><i class="icon-fa fa-hourglass"></i> fa-hourglass</div>
					<div class="col-3"><i class="icon-fa fa-hourglass-1"></i> fa-hourglass-1 </div>
					<div class="col-3"><i class="icon-fa fa-hourglass-2"></i> fa-hourglass-2 </div>
					<div class="col-3"><i class="icon-fa fa-hourglass-3"></i> fa-hourglass-3 </div>
					<div class="col-3"><i class="icon-fa fa-hourglass-end"></i> fa-hourglass-end</div>
					<div class="col-3"><i class="icon-fa fa-hourglass-half"></i> fa-hourglass-half</div>
					<div class="col-3"><i class="icon-fa fa-hourglass-o"></i> fa-hourglass-o</div>
					<div class="col-3"><i class="icon-fa fa-hourglass-start"></i> fa-hourglass-start</div>
					<div class="col-3"><i class="icon-fa fa-houzz"></i> fa-houzz</div>
					<div class="col-3"><i class="icon-fa fa-i-cursor"></i> fa-i-cursor</div>
					<div class="col-3"><i class="icon-fa fa-industry"></i> fa-industry</div>
					<div class="col-3"><i class="icon-fa fa-internet-explorer"></i> fa-internet-explorer</div>
					<div class="col-3"><i class="icon-fa fa-map"></i> fa-map</div>
					<div class="col-3"><i class="icon-fa fa-map-o"></i> fa-map-o</div>
					<div class="col-3"><i class="icon-fa fa-map-pin"></i> fa-map-pin</div>
					<div class="col-3"><i class="icon-fa fa-map-signs"></i> fa-map-signs</div>
					<div class="col-3"><i class="icon-fa fa-mouse-pointer"></i> fa-mouse-pointer</div>
					<div class="col-3"><i class="icon-fa fa-object-group"></i> fa-object-group</div>
					<div class="col-3"><i class="icon-fa fa-object-ungroup"></i> fa-object-ungroup</div>
					<div class="col-3"><i class="icon-fa fa-odnoklassniki"></i> fa-odnoklassniki</div>
					<div class="col-3"><i class="icon-fa fa-odnoklassniki-square"></i> fa-odnoklassniki-square</div>
					<div class="col-3"><i class="icon-fa fa-opencart"></i> fa-opencart</div>
					<div class="col-3"><i class="icon-fa fa-opera"></i> fa-opera</div>
					<div class="col-3"><i class="icon-fa fa-optin-monster"></i> fa-optin-monster</div>
					<div class="col-3"><i class="icon-fa fa-registered"></i> fa-registered</div>
					<div class="col-3"><i class="icon-fa fa-safari"></i> fa-safari</div>
					<div class="col-3"><i class="icon-fa fa-sticky-note"></i> fa-sticky-note</div>
					<div class="col-3"><i class="icon-fa fa-sticky-note-o"></i> fa-sticky-note-o</div>
					<div class="col-3"><i class="icon-fa fa-television"></i> fa-television</div>
					<div class="col-3"><i class="icon-fa fa-trademark"></i> fa-trademark</div>
					<div class="col-3"><i class="icon-fa fa-tripadvisor"></i> fa-tripadvisor</div>
					<div class="col-3"><i class="icon-fa fa-tv"></i> fa-tv </div>
					<div class="col-3"><i class="icon-fa fa-vimeo"></i> fa-vimeo</div>
					<div class="col-3"><i class="icon-fa fa-wikipedia-w"></i> fa-wikipedia-w</div>
					<div class="col-3"><i class="icon-fa fa-y-combinator"></i> fa-y-combinator</div>
					<div class="col-3"><i class="icon-fa fa-yc"></i> fa-yc </div>
					<div class="col-3"><i class="icon-fa fa-bluetooth"></i> bluetooth</a></div>
					<div class="col-3"><i class="icon-fa fa-bluetooth-b"></i> fa-bluetooth-b</a></div>
					<div class="col-3"><i class="icon-fa fa-codiepie"></i> fa-codiepie</a></div>
					<div class="col-3"><i class="icon-fa fa-credit-card-alt"></i> fa-credit-card-alt</a></div>
					<div class="col-3"><i class="icon-fa fa-edge"></i> fa-edge</a></div>
					<div class="col-3"><i class="icon-fa fa-fort-awesome"></i> fa-fort-awesome</a></div>
					<div class="col-3"><i class="icon-fa fa-hashtag"></i> fa-hashtag</a></div>
					<div class="col-3"><i class="icon-fa fa-mixcloud"></i> fa-mixcloud</a></div>
					<div class="col-3"><i class="icon-fa fa-modx"></i> fa-modx</a></div>
					<div class="col-3"><i class="icon-fa fa-pause-circle"></i> fa-pause-circle</a></div>
					<div class="col-3"><i class="icon-fa fa-pause-circle-o"></i> fa-pause-circle-o</a></div>
					<div class="col-3"><i class="icon-fa fa-percent"></i> fa-percent</a></div>
					<div class="col-3"><i class="icon-fa fa-product-hunt"></i> fa-product-hunt</a></div>
					<div class="col-3"><i class="icon-fa fa-reddit-alien"></i> fa-reddit-alien</a></div>
					<div class="col-3"><i class="icon-fa fa-scribd"></i> fa-scribd</a></div>
					<div class="col-3"><i class="icon-fa fa-shopping-bag"></i> fa-shopping-bag</a></div>
					<div class="col-3"><i class="icon-fa fa-shopping-basket"></i> fa-shopping-basket</a></div>
					<div class="col-3"><i class="icon-fa fa-stop-circle"></i> fa-stop-circle</a></div>
					<div class="col-3"><i class="icon-fa fa-stop-circle-o"></i> fa-stop-circle-o</a></div>
					<div class="col-3"><i class="icon-fa fa-usb"></i> fa-usb</a></div>
		 		 </div><!-- end row -->
			</div><!-- end grid -->
		</div>
		
		<div id="tab-pane5" class="tab-pane">
			<ul class="site-doc-icon">
      <li>
        <i class="layui-icon layui-icon-rate-half"></i>
        <div class="doc-icon-name">半星</div>
        <div class="doc-icon-code">&amp;#xe6c9;</div>
        <div class="doc-icon-fontclass">layui-icon-rate-half</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-rate"></i>
        <div class="doc-icon-name">星星-空心</div>
        <div class="doc-icon-code">&amp;#xe67b;</div>
        <div class="doc-icon-fontclass">layui-icon-rate</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-rate-solid"></i>
        <div class="doc-icon-name">星星-实心</div>
        <div class="doc-icon-code">&amp;#xe67a;</div>
        <div class="doc-icon-fontclass">layui-icon-rate-solid</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-cellphone"></i>
        <div class="doc-icon-name">手机</div>
        <div class="doc-icon-code">&amp;#xe678;</div>
        <div class="doc-icon-fontclass">layui-icon-cellphone</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-vercode"></i>
        <div class="doc-icon-name">验证码</div>
        <div class="doc-icon-code">&amp;#xe679;</div>
        <div class="doc-icon-fontclass">layui-icon-vercode</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-login-wechat"></i>
        <div class="doc-icon-name">微信</div>
        <div class="doc-icon-code">&amp;#xe677;</div>
        <div class="doc-icon-fontclass">layui-icon-login-wechat</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-login-qq"></i>
        <div class="doc-icon-name">QQ</div>
        <div class="doc-icon-code">&amp;#xe676;</div>
        <div class="doc-icon-fontclass">layui-icon-login-qq</div>
      </li>
      
      <li>
        <i class="layui-icon layui-icon-login-weibo"></i>
        <div class="doc-icon-name">微博</div>
        <div class="doc-icon-code">&amp;#xe675;</div>
        <div class="doc-icon-fontclass">layui-icon-login-weibo</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-password"></i>
        <div class="doc-icon-name">密码</div>
        <div class="doc-icon-code">&amp;#xe673;</div>
        <div class="doc-icon-fontclass">layui-icon-password</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-username"></i>
        <div class="doc-icon-name">用户名</div>
        <div class="doc-icon-code">&amp;#xe66f;</div>
        <div class="doc-icon-fontclass">layui-icon-username</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-refresh-3"></i>
        <div class="doc-icon-name">刷新-粗</div>
        <div class="doc-icon-code">&amp;#xe9aa;</div>
        <div class="doc-icon-fontclass">layui-icon-refresh-3</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-auz"></i>
        <div class="doc-icon-name">授权</div>
        <div class="doc-icon-code">&amp;#xe672;</div>
        <div class="doc-icon-fontclass">layui-icon-auz</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-spread-left"></i>
        <div class="doc-icon-name">左向右伸缩菜单</div>
        <div class="doc-icon-code">&amp;#xe66b;</div>
        <div class="doc-icon-fontclass">layui-icon-spread-left</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-shrink-right"></i>
        <div class="doc-icon-name">右向左伸缩菜单</div>
        <div class="doc-icon-code">&amp;#xe668;</div>
        <div class="doc-icon-fontclass">layui-icon-shrink-right</div>
      </li>
      
      <li>
        <i class="layui-icon layui-icon-snowflake"></i>
        <div class="doc-icon-name">雪花</div>
        <div class="doc-icon-code">&amp;#xe6b1;</div>
        <div class="doc-icon-fontclass">layui-icon-snowflake</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-tips"></i>
        <div class="doc-icon-name">提示说明</div>
        <div class="doc-icon-code">&amp;#xe702;</div>
        <div class="doc-icon-fontclass">layui-icon-tips</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-note"></i>
        <div class="doc-icon-name">便签</div>
        <div class="doc-icon-code">&amp;#xe66e;</div>
        <div class="doc-icon-fontclass">layui-icon-note</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-home"></i>
        <div class="doc-icon-name">主页</div>
        <div class="doc-icon-code">&amp;#xe68e;</div>
        <div class="doc-icon-fontclass">layui-icon-home</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-senior"></i>
        <div class="doc-icon-name">高级</div>
        <div class="doc-icon-code">&amp;#xe674;</div>
        <div class="doc-icon-fontclass">layui-icon-senior</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-refresh"></i>
        <div class="doc-icon-name">刷新</div>
        <div class="doc-icon-code">&amp;#xe669;</div>
        <div class="doc-icon-fontclass">layui-icon-refresh</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-refresh-1"></i>
        <div class="doc-icon-name">刷新</div>
        <div class="doc-icon-code">&amp;#xe666;</div>
        <div class="doc-icon-fontclass">layui-icon-refresh-1</div>
      </li>
      
      <li>
        <i class="layui-icon layui-icon-flag"></i>
        <div class="doc-icon-name">旗帜</div>
        <div class="doc-icon-code">&amp;#xe66c;</div>
        <div class="doc-icon-fontclass">layui-icon-flag</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-theme"></i>
        <div class="doc-icon-name">主题</div>
        <div class="doc-icon-code">&amp;#xe66a;</div>
        <div class="doc-icon-fontclass">layui-icon-theme</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-notice"></i>
        <div class="doc-icon-name">消息-通知</div>
        <div class="doc-icon-code">&amp;#xe667;</div>
        <div class="doc-icon-fontclass">layui-icon-notice</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-website"></i>
        <div class="doc-icon-name">网站</div>
        <div class="doc-icon-code">&amp;#xe7ae;</div>
        <div class="doc-icon-fontclass">layui-icon-website</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-console"></i>
        <div class="doc-icon-name">控制台</div>
        <div class="doc-icon-code">&amp;#xe665;</div>
        <div class="doc-icon-fontclass">layui-icon-console</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-face-surprised"></i>
        <div class="doc-icon-name">表情-惊讶</div>
        <div class="doc-icon-code">&amp;#xe664;</div>
        <div class="doc-icon-fontclass">layui-icon-face-surprised</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-set"></i>
        <div class="doc-icon-name">设置-空心</div>
        <div class="doc-icon-code">&amp;#xe716;</div>
        <div class="doc-icon-fontclass">layui-icon-set</div>
      </li>
      
      <li>
        <i class="layui-icon layui-icon-template-1"></i>
        <div class="doc-icon-name">模板</div>
        <div class="doc-icon-code">&amp;#xe656;</div>
        <div class="doc-icon-fontclass">layui-icon-template-1</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-app"></i>
        <div class="doc-icon-name">应用</div>
        <div class="doc-icon-code">&amp;#xe653;</div>
        <div class="doc-icon-fontclass">layui-icon-app</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-template"></i>
        <div class="doc-icon-name">模板</div>
        <div class="doc-icon-code">&amp;#xe663;</div>
        <div class="doc-icon-fontclass">layui-icon-template</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-praise"></i>
        <div class="doc-icon-name">赞</div>
        <div class="doc-icon-code">&amp;#xe6c6;</div>
        <div class="doc-icon-fontclass">layui-icon-praise</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-tread"></i>
        <div class="doc-icon-name">踩</div>
        <div class="doc-icon-code">&amp;#xe6c5;</div>
        <div class="doc-icon-fontclass">layui-icon-tread</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-male"></i>
        <div class="doc-icon-name">男</div>
        <div class="doc-icon-code">&amp;#xe662;</div>
        <div class="doc-icon-fontclass">layui-icon-male</div>
      </li>
      
      <li>
        <i class="layui-icon layui-icon-female"></i>
        <div class="doc-icon-name">女</div>
        <div class="doc-icon-code">&amp;#xe661;</div>
        <div class="doc-icon-fontclass">layui-icon-female</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-camera"></i>
        <div class="doc-icon-name">相机-空心</div>
        <div class="doc-icon-code">&amp;#xe660;</div>
        <div class="doc-icon-fontclass">layui-icon-camera</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-camera-fill"></i>
        <div class="doc-icon-name">相机-实心</div>
        <div class="doc-icon-code">&amp;#xe65d;</div>
        <div class="doc-icon-fontclass">layui-icon-camera-fill</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-more"></i>
        <div class="doc-icon-name">菜单-水平</div>
        <div class="doc-icon-code">&amp;#xe65f;</div>
        <div class="doc-icon-fontclass">layui-icon-more</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-more-vertical"></i>
        <div class="doc-icon-name">菜单-垂直</div>
        <div class="doc-icon-code">&amp;#xe671;</div>
        <div class="doc-icon-fontclass">layui-icon-more-vertical</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-rmb"></i>
        <div class="doc-icon-name">金额-人民币</div>
        <div class="doc-icon-code">&amp;#xe65e;</div>
        <div class="doc-icon-fontclass">layui-icon-rmb</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-dollar"></i>
        <div class="doc-icon-name">金额-美元</div>
        <div class="doc-icon-code">&amp;#xe659;</div>
        <div class="doc-icon-fontclass">layui-icon-dollar</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-diamond"></i>
        <div class="doc-icon-name">钻石-等级</div>
        <div class="doc-icon-code">&amp;#xe735;</div>
        <div class="doc-icon-fontclass">layui-icon-diamond</div>
      </li>
      
      <li>
        <i class="layui-icon layui-icon-fire"></i>
        <div class="doc-icon-name">火</div>
        <div class="doc-icon-code">&amp;#xe756;</div>
        <div class="doc-icon-fontclass">layui-icon-fire</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-return"></i>
        <div class="doc-icon-name">返回</div>
        <div class="doc-icon-code">&amp;#xe65c;</div>
        <div class="doc-icon-fontclass">layui-icon-return</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-location"></i>
        <div class="doc-icon-name">位置-地图</div>
        <div class="doc-icon-code">&amp;#xe715;</div>
        <div class="doc-icon-fontclass">layui-icon-location</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-read"></i>
        <div class="doc-icon-name">办公-阅读</div>
        <div class="doc-icon-code">&amp;#xe705;</div>
        <div class="doc-icon-fontclass">layui-icon-read</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-survey"></i>
        <div class="doc-icon-name">调查</div>
        <div class="doc-icon-code">&amp;#xe6b2;</div>
        <div class="doc-icon-fontclass">layui-icon-survey</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-face-smile"></i>
        <div class="doc-icon-name">表情-微笑</div>
        <div class="doc-icon-code">&amp;#xe6af;</div>
        <div class="doc-icon-fontclass">layui-icon-face-smile</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-face-cry"></i>
        <div class="doc-icon-name">表情-哭泣</div>
        <div class="doc-icon-code">&amp;#xe69c;</div>
        <div class="doc-icon-fontclass">layui-icon-face-cry</div>
      </li>
      
      <li>
        <i class="layui-icon layui-icon-cart-simple"></i>
        <div class="doc-icon-name">购物车</div>
        <div class="doc-icon-code">&amp;#xe698;</div>
        <div class="doc-icon-fontclass">layui-icon-cart-simple</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-cart"></i>
        <div class="doc-icon-name">购物车</div>
        <div class="doc-icon-code">&amp;#xe657;</div>
        <div class="doc-icon-fontclass">layui-icon-cart</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-next"></i>
        <div class="doc-icon-name">下一页</div>
        <div class="doc-icon-code">&amp;#xe65b;</div>
        <div class="doc-icon-fontclass">layui-icon-next</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-prev"></i>
        <div class="doc-icon-name">上一页</div>
        <div class="doc-icon-code">&amp;#xe65a;</div>
        <div class="doc-icon-fontclass">layui-icon-prev</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-upload-drag"></i>
        <div class="doc-icon-name">上传-空心-拖拽</div>
        <div class="doc-icon-code">&amp;#xe681;</div>
        <div class="doc-icon-fontclass">layui-icon-upload-drag</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-upload"></i>
        <div class="doc-icon-name">上传-实心</div>
        <div class="doc-icon-code">&amp;#xe67c;</div>
        <div class="doc-icon-fontclass">layui-icon-upload</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-download-circle"></i>
        <div class="doc-icon-name">下载-圆圈</div>
        <div class="doc-icon-code">&amp;#xe601;</div>
        <div class="doc-icon-fontclass">layui-icon-download-circle</div>
      </li>
      
      <li>
        <i class="layui-icon layui-icon-component"></i>
        <div class="doc-icon-name">组件</div>
        <div class="doc-icon-code">&amp;#xe857;</div>
        <div class="doc-icon-fontclass">layui-icon-component</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-file-b"></i>
        <div class="doc-icon-name">文件-粗</div>
        <div class="doc-icon-code">&amp;#xe655;</div>
        <div class="doc-icon-fontclass">layui-icon-file-b</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-user"></i>
        <div class="doc-icon-name">用户</div>
        <div class="doc-icon-code">&amp;#xe770;</div>
        <div class="doc-icon-fontclass">layui-icon-user</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-find-fill"></i>
        <div class="doc-icon-name">发现-实心</div>
        <div class="doc-icon-code">&amp;#xe670;</div>
        <div class="doc-icon-fontclass">layui-icon-find-fill</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-loading layui-icon layui-anim layui-anim-rotate layui-anim-loop"></i>
        <div class="doc-icon-name">loading</div>
        <div class="doc-icon-code">&amp;#xe63d;</div>
        <div class="doc-icon-fontclass">layui-icon-loading</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-loading-1 layui-icon layui-anim layui-anim-rotate layui-anim-loop"></i>
        <div class="doc-icon-name">loading</div>
        <div class="doc-icon-code">&amp;#xe63e;</div>
        <div class="doc-icon-fontclass">layui-icon-loading-1</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-add-1"></i>
        <div class="doc-icon-name">添加</div>
        <div class="doc-icon-code">&amp;#xe654;</div>
        <div class="doc-icon-fontclass">layui-icon-add-1</div>
      </li>
      
      <li>
        <i class="layui-icon layui-icon-play"></i>
        <div class="doc-icon-name">播放</div>
        <div class="doc-icon-code">&amp;#xe652;</div>
        <div class="doc-icon-fontclass">layui-icon-play</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-pause"></i>
        <div class="doc-icon-name">暂停</div>
        <div class="doc-icon-code">&amp;#xe651;</div>
        <div class="doc-icon-fontclass">layui-icon-pause</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-headset"></i>
        <div class="doc-icon-name">音频-耳机</div>
        <div class="doc-icon-code">&amp;#xe6fc;</div>
        <div class="doc-icon-fontclass">layui-icon-headset</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-video"></i>
        <div class="doc-icon-name">视频</div>
        <div class="doc-icon-code">&amp;#xe6ed;</div>
        <div class="doc-icon-fontclass">layui-icon-video</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-voice"></i>
        <div class="doc-icon-name">语音-声音</div>
        <div class="doc-icon-code">&amp;#xe688;</div>
        <div class="doc-icon-fontclass">layui-icon-voice</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-speaker"></i>
        <div class="doc-icon-name">消息-通知-喇叭</div>
        <div class="doc-icon-code">&amp;#xe645;</div>
        <div class="doc-icon-fontclass">layui-icon-speaker</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-fonts-del"></i>
        <div class="doc-icon-name">删除线</div>
        <div class="doc-icon-code">&amp;#xe64f;</div>
        <div class="doc-icon-fontclass">layui-icon-fonts-del</div>
      </li>
      
      <li>
        <i class="layui-icon layui-icon-fonts-code"></i>
        <div class="doc-icon-name">代码</div>
        <div class="doc-icon-code">&amp;#xe64e;</div>
        <div class="doc-icon-fontclass">layui-icon-fonts-code</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-fonts-html"></i>
        <div class="doc-icon-name">HTML</div>
        <div class="doc-icon-code">&amp;#xe64b;</div>
        <div class="doc-icon-fontclass">layui-icon-fonts-html</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-fonts-strong"></i>
        <div class="doc-icon-name">字体加粗</div>
        <div class="doc-icon-code">&amp;#xe62b;</div>
        <div class="doc-icon-fontclass">layui-icon-fonts-strong</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-unlink"></i>
        <div class="doc-icon-name">删除链接</div>
        <div class="doc-icon-code">&amp;#xe64d;</div>
        <div class="doc-icon-fontclass">layui-icon-unlink</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-picture"></i>
        <div class="doc-icon-name">图片</div>
        <div class="doc-icon-code">&amp;#xe64a;</div>
        <div class="doc-icon-fontclass">layui-icon-picture</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-link"></i>
        <div class="doc-icon-name">链接</div>
        <div class="doc-icon-code">&amp;#xe64c;</div>
        <div class="doc-icon-fontclass">layui-icon-link</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-face-smile-b"></i>
        <div class="doc-icon-name">表情-笑-粗</div>
        <div class="doc-icon-code">&amp;#xe650;</div>
        <div class="doc-icon-fontclass">layui-icon-face-smile-b</div>
      </li>
      
      <li>
        <i class="layui-icon layui-icon-align-left"></i>
        <div class="doc-icon-name">左对齐</div>
        <div class="doc-icon-code">&amp;#xe649;</div>
        <div class="doc-icon-fontclass">layui-icon-align-left</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-align-right"></i>
        <div class="doc-icon-name">右对齐</div>
        <div class="doc-icon-code">&amp;#xe648;</div>
        <div class="doc-icon-fontclass">layui-icon-align-right</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-align-center"></i>
        <div class="doc-icon-name">居中对齐</div>
        <div class="doc-icon-code">&amp;#xe647;</div>
        <div class="doc-icon-fontclass">layui-icon-align-center</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-fonts-u"></i>
        <div class="doc-icon-name">字体-下划线</div>
        <div class="doc-icon-code">&amp;#xe646;</div>
        <div class="doc-icon-fontclass">layui-icon-fonts-u</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-fonts-i"></i>
        <div class="doc-icon-name">字体-斜体</div>
        <div class="doc-icon-code">&amp;#xe644;</div>
        <div class="doc-icon-fontclass">layui-icon-fonts-i</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-tabs"></i>
        <div class="doc-icon-name">Tabs 选项卡</div>
        <div class="doc-icon-code">&amp;#xe62a;</div>
        <div class="doc-icon-fontclass">layui-icon-tabs</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-radio"></i>
        <div class="doc-icon-name">单选框-选中</div>
        <div class="doc-icon-code">&amp;#xe643;</div>
        <div class="doc-icon-fontclass">layui-icon-radio</div>
      </li>
      
      <li>
        <i class="layui-icon layui-icon-circle"></i>
        <div class="doc-icon-name">单选框-候选</div>
        <div class="doc-icon-code">&amp;#xe63f;</div>
        <div class="doc-icon-fontclass">layui-icon-circle</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-edit"></i>
        <div class="doc-icon-name">编辑</div>
        <div class="doc-icon-code">&amp;#xe642;</div>
        <div class="doc-icon-fontclass">layui-icon-edit</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-share"></i>
        <div class="doc-icon-name">分享</div>
        <div class="doc-icon-code">&amp;#xe641;</div>
        <div class="doc-icon-fontclass">layui-icon-share</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-delete"></i>
        <div class="doc-icon-name">删除</div>
        <div class="doc-icon-code">&amp;#xe640;</div>
        <div class="doc-icon-fontclass">layui-icon-delete</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-form"></i>
        <div class="doc-icon-name">表单</div>
        <div class="doc-icon-code">&amp;#xe63c;</div>
        <div class="doc-icon-fontclass">layui-icon-form</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-cellphone-fine"></i>
        <div class="doc-icon-name">手机-细体</div>
        <div class="doc-icon-code">&amp;#xe63b;</div>
        <div class="doc-icon-fontclass">layui-icon-cellphone-fine</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-dialogue"></i>
        <div class="doc-icon-name">聊天 对话 沟通</div>
        <div class="doc-icon-code">&amp;#xe63a;</div>
        <div class="doc-icon-fontclass">layui-icon-dialogue</div>
      </li>
      
      <li>
        <i class="layui-icon layui-icon-fonts-clear"></i>
        <div class="doc-icon-name">文字格式化</div>
        <div class="doc-icon-code">&amp;#xe639;</div>
        <div class="doc-icon-fontclass">layui-icon-fonts-clear</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-layer"></i>
        <div class="doc-icon-name">窗口</div>
        <div class="doc-icon-code">&amp;#xe638;</div>
        <div class="doc-icon-fontclass">layui-icon-layer</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-date"></i>
        <div class="doc-icon-name">日期</div>
        <div class="doc-icon-code">&amp;#xe637;</div>
        <div class="doc-icon-fontclass">layui-icon-date</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-water"></i>
        <div class="doc-icon-name">水 下雨</div>
        <div class="doc-icon-code">&amp;#xe636;</div>
        <div class="doc-icon-fontclass">layui-icon-water</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-code-circle"></i>
        <div class="doc-icon-name">代码-圆圈</div>
        <div class="doc-icon-code">&amp;#xe635;</div>
        <div class="doc-icon-fontclass">layui-icon-code-circle</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-carousel"></i>
        <div class="doc-icon-name">轮播组图</div>
        <div class="doc-icon-code">&amp;#xe634;</div>
        <div class="doc-icon-fontclass">layui-icon-carousel</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-prev-circle"></i>
        <div class="doc-icon-name">翻页</div>
        <div class="doc-icon-code">&amp;#xe633;</div>
        <div class="doc-icon-fontclass">layui-icon-prev-circle</div>
      </li>
      
      <li>
        <i class="layui-icon layui-icon-layouts"></i>
        <div class="doc-icon-name">布局</div>
        <div class="doc-icon-code">&amp;#xe632;</div>
        <div class="doc-icon-fontclass">layui-icon-layouts</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-util"></i>
        <div class="doc-icon-name">工具</div>
        <div class="doc-icon-code">&amp;#xe631;</div>
        <div class="doc-icon-fontclass">layui-icon-util</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-templeate-1"></i>
        <div class="doc-icon-name">选择模板</div>
        <div class="doc-icon-code">&amp;#xe630;</div>
        <div class="doc-icon-fontclass">layui-icon-templeate-1</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-upload-circle"></i>
        <div class="doc-icon-name">上传-圆圈</div>
        <div class="doc-icon-code">&amp;#xe62f;</div>
        <div class="doc-icon-fontclass">layui-icon-upload-circle</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-tree"></i>
        <div class="doc-icon-name">树</div>
        <div class="doc-icon-code">&amp;#xe62e;</div>
        <div class="doc-icon-fontclass">layui-icon-tree</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-table"></i>
        <div class="doc-icon-name">表格</div>
        <div class="doc-icon-code">&amp;#xe62d;</div>
        <div class="doc-icon-fontclass">layui-icon-table</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-chart"></i>
        <div class="doc-icon-name">图表</div>
        <div class="doc-icon-code">&amp;#xe62c;</div>
        <div class="doc-icon-fontclass">layui-icon-chart</div>
      </li>
      
      <li>
        <i class="layui-icon layui-icon-chart-screen"></i>
        <div class="doc-icon-name">图标 报表 屏幕</div>
        <div class="doc-icon-code">&amp;#xe629;</div>
        <div class="doc-icon-fontclass">layui-icon-chart-screen</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-engine"></i>
        <div class="doc-icon-name">引擎</div>
        <div class="doc-icon-code">&amp;#xe628;</div>
        <div class="doc-icon-fontclass">layui-icon-engine</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-triangle-d"></i>
        <div class="doc-icon-name">下三角</div>
        <div class="doc-icon-code">&amp;#xe625;</div>
        <div class="doc-icon-fontclass">layui-icon-triangle-d</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-triangle-r"></i>
        <div class="doc-icon-name">右三角</div>
        <div class="doc-icon-code">&amp;#xe623;</div>
        <div class="doc-icon-fontclass">layui-icon-triangle-r</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-file"></i>
        <div class="doc-icon-name">文件</div>
        <div class="doc-icon-code">&amp;#xe621;</div>
        <div class="doc-icon-fontclass">layui-icon-file</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-set-sm"></i>
        <div class="doc-icon-name">设置-小型</div>
        <div class="doc-icon-code">&amp;#xe620;</div>
        <div class="doc-icon-fontclass">layui-icon-set-sm</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-add-circle"></i>
        <div class="doc-icon-name">添加-圆圈</div>
        <div class="doc-icon-code">&amp;#xe61f;</div>
        <div class="doc-icon-fontclass">layui-icon-add-circle</div>
      </li>
      
      
      <li>
        <i class="layui-icon layui-icon-404"></i>
        <div class="doc-icon-name">404</div>
        <div class="doc-icon-code">&amp;#xe61c;</div>
        <div class="doc-icon-fontclass">layui-icon-404</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-about"></i>
        <div class="doc-icon-name">关于</div>
        <div class="doc-icon-code">&amp;#xe60b;</div>
        <div class="doc-icon-fontclass">layui-icon-about</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-up"></i>
        <div class="doc-icon-name">箭头 向上</div>
        <div class="doc-icon-code">&amp;#xe619;</div>
        <div class="doc-icon-fontclass">layui-icon-up</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-down"></i>
        <div class="doc-icon-name">箭头 向下</div>
        <div class="doc-icon-code">&amp;#xe61a;</div>
        <div class="doc-icon-fontclass">layui-icon-down</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-left"></i>
        <div class="doc-icon-name">箭头 向左</div>
        <div class="doc-icon-code">&amp;#xe603;</div>
        <div class="doc-icon-fontclass">layui-icon-left</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-right"></i>
        <div class="doc-icon-name">箭头 向右</div>
        <div class="doc-icon-code">&amp;#xe602;</div>
        <div class="doc-icon-fontclass">layui-icon-right</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-circle-dot"></i>
        <div class="doc-icon-name">圆点</div>
        <div class="doc-icon-code">&amp;#xe617;</div>
        <div class="doc-icon-fontclass">layui-icon-circle-dot</div>
      </li>
      
      <li>
        <i class="layui-icon layui-icon-search"></i>
        <div class="doc-icon-name">搜索</div>
        <div class="doc-icon-code">&amp;#xe615;</div>
        <div class="doc-icon-fontclass">layui-icon-search</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-set-fill"></i>
        <div class="doc-icon-name">设置-实心</div>
        <div class="doc-icon-code">&amp;#xe614;</div>
        <div class="doc-icon-fontclass">layui-icon-set-fill</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-group"></i>
        <div class="doc-icon-name">群组</div>
        <div class="doc-icon-code">&amp;#xe613;</div>
        <div class="doc-icon-fontclass">layui-icon-group</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-friends"></i>
        <div class="doc-icon-name">好友</div>
        <div class="doc-icon-code">&amp;#xe612;</div>
        <div class="doc-icon-fontclass">layui-icon-friends</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-reply-fill"></i>
        <div class="doc-icon-name">回复 评论 实心</div>
        <div class="doc-icon-code">&amp;#xe611;</div>
        <div class="doc-icon-fontclass">layui-icon-reply-fill</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-menu-fill"></i>
        <div class="doc-icon-name">菜单 隐身 实心</div>
        <div class="doc-icon-code">&amp;#xe60f;</div>
        <div class="doc-icon-fontclass">layui-icon-menu-fill</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-log"></i>
        <div class="doc-icon-name">记录</div>
        <div class="doc-icon-code">&amp;#xe60e;</div>
        <div class="doc-icon-fontclass">layui-icon-log</div>
      </li>
      
      <li>
        <i class="layui-icon layui-icon-picture-fine"></i>
        <div class="doc-icon-name">图片-细体</div>
        <div class="doc-icon-code">&amp;#xe60d;</div>
        <div class="doc-icon-fontclass">layui-icon-picture-fine</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-face-smile-fine"></i>
        <div class="doc-icon-name">表情-笑-细体</div>
        <div class="doc-icon-code">&amp;#xe60c;</div>
        <div class="doc-icon-fontclass">layui-icon-face-smile-fine</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-list"></i>
        <div class="doc-icon-name">列表</div>
        <div class="doc-icon-code">&amp;#xe60a;</div>
        <div class="doc-icon-fontclass">layui-icon-list</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-release"></i>
        <div class="doc-icon-name">发布 纸飞机</div>
        <div class="doc-icon-code">&amp;#xe609;</div>
        <div class="doc-icon-fontclass">layui-icon-release</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-ok"></i>
        <div class="doc-icon-name">对 OK</div>
        <div class="doc-icon-code">&amp;#xe605;</div>
        <div class="doc-icon-fontclass">layui-icon-ok</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-help"></i>
        <div class="doc-icon-name">帮助</div>
        <div class="doc-icon-code">&amp;#xe607;</div>
        <div class="doc-icon-fontclass">layui-icon-help</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-chat"></i>
        <div class="doc-icon-name">客服</div>
        <div class="doc-icon-code">&amp;#xe606;</div>
        <div class="doc-icon-fontclass">layui-icon-chat</div>
      </li>
      
      <li>
        <i class="layui-icon layui-icon-top"></i>
        <div class="doc-icon-name">top 置顶</div>
        <div class="doc-icon-code">&amp;#xe604;</div>
        <div class="doc-icon-fontclass">layui-icon-top</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-star"></i>
        <div class="doc-icon-name">收藏-空心</div>
        <div class="doc-icon-code">&amp;#xe600;</div>
        <div class="doc-icon-fontclass">layui-icon-star</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-star-fill"></i>
        <div class="doc-icon-name">收藏-实心</div>
        <div class="doc-icon-code">&amp;#xe658;</div>
        <div class="doc-icon-fontclass">layui-icon-star-fill</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-close-fill"></i>
        <div class="doc-icon-name">关闭-实心</div>
        <div class="doc-icon-code">&amp;#x1007;</div>
        <div class="doc-icon-fontclass">layui-icon-close-fill</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-close"></i>
        <div class="doc-icon-name">关闭-空心</div>
        <div class="doc-icon-code">&amp;#x1006;</div>
        <div class="doc-icon-fontclass">layui-icon-close</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-ok-circle"></i>
        <div class="doc-icon-name">正确</div>
        <div class="doc-icon-code">&amp;#x1005;</div>
        <div class="doc-icon-fontclass">layui-icon-ok-circle</div>
      </li>
      <li>
        <i class="layui-icon layui-icon-add-circle-fine"></i>
        <div class="doc-icon-name">添加-圆圈-细体</div>
        <div class="doc-icon-code">&amp;#xe608;</div>
        <div class="doc-icon-fontclass">layui-icon-add-circle-fine</div>
      </li>
    </ul>
		</div>
	</div>
</div>
</body>
</powersi:html>