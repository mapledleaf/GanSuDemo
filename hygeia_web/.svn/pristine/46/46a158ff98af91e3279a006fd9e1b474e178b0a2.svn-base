//右下角消息类
//作者：向左剑
var MsgWin = Class(base,{

	width : 350,
	height:200,
	divObj:null,
	title:'',
	contentObj:null,
	content: null,
	interval : 300,

	closeflag : true,
	scrolled : false,
	url : null,

	//内置模板
	defaultTemplate : new tempClass({template:' \
		<for expr="index<this.data.length">\
			<div class="row">\
				<div style="width:30px" class="col"> \
					<img src="$[rootPath]/resource/images/msg_unread.gif"/> \
				</div> \
				<div style="width:100px" class="col">${data[index].taskName}</div>\
				<div style="width:50px" class="col">${data[index].taskCount}条</div>\
				<div class="col" style="width:50px">\
					<a href=javascript:openMenu("${data[index].taskUrl}") >处理</a>\
				</div>\
			</div>\
		</for>'}
	),
	
	isSpace : function(s){
		return typeof(s) == "undefined"||typeof(s) == null;
	},

	//构造函数
	init : function(config){
		this.width = config.width;
		this.height = config.height;
		this.divId = config.divId;
		this.title = config.title;
		if(typeof(config.interval)!='undefined'&&config.interval!=""&&config.interval!=null){
			this.interval = config.interval;
		}
		this.divObj =$('#'+config.divId+'');
		this.divObj.hide();
		this.divObj.get(0).setAttribute("class",'todolist'); //兼容FF
		this.divObj.get(0).setAttribute("className",'todolist');
		this.divObj.get(0).style.width = config.width;
		if(!this.isSpace(config.url)){
			this.url = config.url;
		}
		if(!this.isSpace(config.template)){
			this.defaultTemplate =config.template;
		}
		this.content = config.content;
		this.createHeader();
		this.createContent();
		this.getData();		
		var This = this;
		setInterval(function(){
			This.reload();
		},this.interval*1000);
	},
	
	reload : function(){
		this.getData();
	},
	
	getData : function(){
		if(!this.isSpace(this.url)){
			var This  = this;
			$.ajax({
				url:this.url,
				type: "POST",
				dataType: "json",
				data:{ran:Math.random()*100},
				global: false,
				success:function(data){
						try{
							if(data==null||data.length==0){
								This.hide();
								return;
							}
							This.content = data;
							This.loadData();
							This.show();
						}catch(e){				
						}			
					}
				});
		}		
	},

	//生成头
	createHeader : function(){
		var textSpan = document.createElement("span");
		
		textSpan.innerHTML = typeof(this.title)=='undefined'?'':this.title;

		var imgObj =  document.createElement("img");
		imgObj.alt = "隐藏";
		imgObj.src = rootPath+"/resource/images/close.gif";
		$(imgObj).bind("click",[this,imgObj],this.toggleHide);

		var headerObj = document.createElement("div");
		headerObj.setAttribute("class",'todolist-header'); //兼容FF
		headerObj.setAttribute("className",'todolist-header');

		headerObj.appendChild(textSpan);
		headerObj.appendChild(imgObj);
		var This = this;
		$(headerObj).bind("mousedown",function(e){
			var event = e||window.event;
			var start = event.x||event.clientX;
			var startpos = parseInt(This.divObj.css("right"));
			$('body').bind("selectstart",function(){
				return false;
			});
			$(document.body).bind("mousemove",function(e){
				var event = e||window.event;				
				var x = document.body.scrollLeft + (event.x||event.clientX);
				var newpos = startpos+start-x;
				newpos = newpos<5?5:newpos;
				if(newpos>document.body.clientWidth-parseInt(This.divObj.css("width"))-5){
					newpos = document.body.clientWidth-parseInt(This.divObj.css("width"))-5;
				}
				This.divObj.css("right",newpos);
			});
		});
		$(document.body).bind("mouseup click",function(e){
			$(document.body).unbind("mousemove selectstart");
		});
		this.divObj.get(0).appendChild(headerObj);

	},
	
	//生成内容
	createContent : function(){
		this.contentObj = document.createElement("div");
		this.contentObj.setAttribute("class",'todolist-content'); //兼容FF
		this.contentObj.setAttribute("className",'todolist-content');
		this.contentObj.style.height= (this.height - 20);
		//this.contentObj.id = 'msgwin_content';
	},
	
	loadData : function(){
		this.defaultTemplate.setData(this.content);
		this.contentObj.innerHTML = this.defaultTemplate.getCompiledHtml();
		this.divObj.get(0).appendChild(this.contentObj);
	},

	getObjAttrValue : function(a,b){
		return a[b];
	},

	setContent : function(content){
		this.content = content;
		this.contentObj.innerHTML = content;
	},

	toggleHide : function(e){
		e = e||window.event;
		var varthis = e.data[0];
		var img = e.data[1];
		if(varthis.closeflag){
			img.alt = "显示";
			img.src = rootPath+"/resource/images/show.gif";
			if(varthis.scrolled){			
				$(varthis.contentObj).slideUp(1000,varthis._fix);
			}else{
				$(varthis.contentObj).slideUp(1000);
			}
		}else{
			img.alt = "隐藏";
			img.src = rootPath+"/resource/images/close.gif";	
			//$(this.contentObj).animate({height:('+'+this.height-20+'px')},'slow');
			if(varthis.scrolled){
				$(varthis.contentObj).slideDown(1,varthis._fix);
			}else{
				$(varthis.contentObj).slideDown(1000);
			}
		}
		
		varthis.closeflag = !varthis.closeflag;
	},
	
	show : function(){
		if(this.isSpace(this.divObj)||this.isSpace(this.content)){
			return;
		}		
		this.divObj.fadeIn(2000);
		/**if($.browser.msie){ // IE
			document.body.onscroll = this._fix;
			document.body.onresize= this._fix;
		}else if($.browser.mozilla){ //FF
			window.onscroll = this._fix;
			window.onresize=this._fix;
		}**/
	},

	hide : function(){		
		if(this.isSpace(this.divObj)){
			return;
		}
		this.divObj.fadeOut(2000);
		/**if($.browser.msie){ // IE
			document.body.onscroll = null;
			document.body.onresize= null;
		}else if($.browser.mozilla){ //FF
			window.onscroll = null;
			window.onresize= null;
		}**/
	},

	
	setTemplate : function(template){
		this.defaultTemplate = template;
	},

	//私有函数 ，用于在滚动中固定div
	_fix : function(){
		this.scrolled = true;
		var _todolist = this.divObj.get(0);
		if(closeflag){
			_todolist.style.top = document.body.scrollTop+document.body.clientHeight - this.height-4;
		}else{
			_todolist.style.top = document.body.scrollTop+document.body.clientHeight - 20-4;	
		}
		
	}
});
