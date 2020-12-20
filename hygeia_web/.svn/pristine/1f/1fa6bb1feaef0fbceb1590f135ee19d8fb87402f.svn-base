//实现类继承
var Class = function(parent,config){
	var ret = function(){parent.apply(this,arguments);this.init.apply(this,arguments);};	
	ret.prototype.superclass = parent.prototype;
	for(var a in config){
		ret.prototype[a] = config[a];
	}
	return ret;
};

//基类
var base = function(){
	this.version = '1.0';
	this.author = 'zuojian.xiang';	
};
base.prototype.init = function(arguments){
};

//模板类
var Template = {

	template : '',

	data : null,

	compiledJS : '',
	
	_compilingJS : '',

	_complie : function(){
		this._complieTag();
		this._complieVar();
		try{
			eval(this._compilingJS);
		}catch(e){
			//alert(this._compilingJS);
			//alert(e);
		}
	},

	init : function(config){
		this.superclass.init.apply(this,arguments);
		this.template = config.template;
		this.data = config.data;
	},

	setData : function(data){
		this.data = data;
	},

	_complieTag: function(){

		var regex_tag = {
			r_for : {
				regex: new RegExp("<for\\s+expr=[\\\"](.*?)[\\\"]\\s*>","g"),
				expr : "');for(var index=0;$1;index++){ this.echo('"
			},
			r_for_end : {
				regex: new RegExp("</for>","g"),
				expr : "')} this.echo('"
			},
			r_if : {
				regex: new RegExp("<if\\s+expr=[\\\"](.*?)[\\\"]\\s*>","g"),
				expr : "'); if($1){ this.echo('"
			},
			r_if_end_noelse : {
				regex: new RegExp("</if>(?![\\s\\t]*(\\r\\n)?[\\s\\t]*<else>)","g"),
				expr : "')} this.echo('"
			},
			r_if_end_else : {
				regex: new RegExp("</if>(?=\\s*(\\r?\\n)?\\s*<else>)","g"),
				expr : "')}"
			},
			r_else : {
				regex: new RegExp("<else>","g"),
				expr : "else{ this.echo('"
			},	
			r_else_end : {
				regex: new RegExp("</else>","g"),
				expr : "')} this.echo('"
			}
		};
		
		this._compilingJS = this.template;	
		if(this.compilingJS != ''){			
			for(var a in regex_tag){
				this._compilingJS = this._compilingJS.replace(regex_tag[a].regex,regex_tag[a].expr);
			}
			this._compilingJS = "this.echo('"+this._compilingJS;
			this._compilingJS += "');";
		}
		
	},

	_complieVar: function(){
		var var_regex = /(\$\{)(.+?)(\})/g;
		var sys_var_regex = /(\$\[)(.+?)(\])/g;
		this._compilingJS = this._compilingJS.replace(var_regex,"'); this.echo(this.$2);this.echo('");
		this._compilingJS = this._compilingJS.replace(sys_var_regex,"'); this.echo($2);this.echo('");
	},

	echo : function(str){
		this.compiledJS += str;
	},

	renderTo : function(a){
		this._complie();
		if(typeof a == 'object'){
			a.innerHTML = this.compiledJS;
		}else if(typeof a == 'string'){
			document.getElementById(a).innerHTML = this.compiledJS;
		}
	},

	getCompiledHtml:function(){
		this.compiledJS = "";
		this._complie();
		return this.compiledJS;
	}
};

var tempClass = Class(base,Template);