(function($){
    $.fn.quickfilter = function(options){
    
        options = jQuery.extend({
            position: 'prepend',
            attached: 'body',
            formId: 'quickfilterid',
            labelText: '查询：',
            formClass: 'qf_class',
            labelClass: 'qf_label',
            inputText: '请输入查询关键字',
            inputTitle: '输入关键字后，按回车键显示符合条件的数据（多关键字空格分隔）',
            inputClass: 'qf_input',
            loaderClass: 'qf_loader',
            stripeRowClass: ['DataGride_data_light', 'DataGride_data_dark'],
            hideElement: null,
            delay: 0,
            focusOnLoad: false,
            onBefore: function(){
            },
            onAfter: function(){
            },
            filter: function(i){
                return i;
            },
            randomElement: 'qf' + Math.floor(Math.random() * 1000000),
            fixWidths: false,
            srcElement: ""
        }, options);
        
        var timeout = null;
        var cache = null;
        var score = {};
        var el = this;
        var stripeRowLength = (!is_empty(options.stripeRowClass)) ? options.stripeRowClass.length : 0;
        var doStripe = (stripeRowLength > 0) ? true : false;
        
        function is_empty(i){
            return (i == null || i == undefined || i == false) ? true : false;
        }
        
        function get_cache(el){
            cache = $(el).not('.' + options.noResultsClass).map(function(){
                return strip_data(normalise(this.innerHTML));
            });
        }
        
        function normalise(i){
            return $.trim(i.toLowerCase().replace(/\n/, '').replace(/\s{2,}/, ' '));
        }
        
        function get_key(){
            var input = strip_html(normalise($('input[rel="' + options.randomElement + '"]').val()));
        	
            if (input.indexOf(' ') == -1){
                return input;
            } else{
                return input.split(" ");
            }
        }
        
        function test_key(k, value, type){
            if (type == "string") {
                return test_key_string(k, value);
            } else {
                return test_key_arr(k, value);
            }
        }
        
        function test_key_string(k, value){
            return (value.indexOf(k) > -1);
        }
        
        function test_key_arr(k, value){
            for (var i = 0; i < k.length; i++) {
                if (value.indexOf(k[i]) < 0) {
                    return false;
                }
            }
            
           return true;
        }
        
        function strip_html(input){
            var regexp = new RegExp(/\<[^\<]+\>/g);
            var output = input.replace(regexp, "");
            output = output.toLowerCase();
            return output;
        }
        
        function strip_data(input){
        	var output = input.replace(new RegExp('<[^<]+\>', 'g'), "");
			output = $.trim(output.toLowerCase());
			return output;
        }
        
        function select_element(el){
            if (options.hideElement == "grandparent") {
                return $(el).parent().parent();
            } else {
            	if (options.hideElement == "parent") {
                    return $(el).parent();
                } else {
                    return $(el);
                }
            }
        }
        
        function stripe(el){
            if (doStripe) {
                var i = 0;
                select_element(el).filter(':visible').each(function(){
                
                    for (var j = 0; j < stripeRowLength; j++) {
                        if (i == j) {
                            $(this).addClass(options.stripeRowClass[i]);
                        }
                        else {
                            $(this).removeClass(options.stripeRowClass[j]);
                        }
                    }
                    i = (i + 1) % stripeRowLength;
                });
            }
        }
        
        function fix_widths(el){
            $(el).find('td').each(function(){
                $(this).attr('width', parseInt($(this).css('width')));
            });
        }
        
        function loader(o){
            if (o == 'hide') {
                $('input[rel="' + options.randomElement + '"]').removeClass(options.loaderClass).addClass(options.inputClass);
            }
            else {
                $('input[rel="' + options.randomElement + '"]').removeClass(options.inputClass).addClass(options.loaderClass);
            }
        }
        
        function place_form(){
            var formPosition = options.position;
            var formAttached = options.attached;
            
            if (formPosition == 'before') {
                $(formAttached).before(make_form());
            }
            else 
                if (formPosition == 'prepend') {
                    $(formAttached).prepend(make_form());
                }
                else 
                    if (formPosition == 'append') {
                        $(formAttached).append(make_form());
                    }
                    else {
                        $(formAttached).after(make_form());
                    }
        }
        
        function make_form(){
            return '<div id="' + options.formId + '" ' + 'class="' + options.formClass + '">' +
            make_form_label() +
            make_form_input() +
            '</div>';
        }
        
        function make_form_label(){
            if (!is_empty(options.labelText)) {
                return '<label for="' + options.randomElement + '" ' +
                'class="' +
                options.labelClass +
                '">' +
                options.labelText +
                '</label> ';
            }
            return '';
        }
        
        function make_form_input(){
            var val = (!is_empty(options.inputText)) ? options.inputText : "";
            return '<input type="text" value="' + val + '" rel="' + options.randomElement + '" class="' + options.inputClass + '" id="' + options.randomElement + '"' + ' title="' + options.inputTitle + '" /> ';
        }
        
        function focus_on_load(){
            $('input[rel="' + options.randomElement + '"]').get(0).focus();
        }
        
        function toggle_text(){
            $('input[rel="' + options.randomElement + '"]').focus(function(){
                if ($(this).val() == options.inputText) {
                    $(this).val('');
                }
            }), $('input[rel="' + options.randomElement + '"]').blur(function(){
                if ($(this).val() == "") {
                    $(this).val(options.inputText);
                }
            });
        };
        
        function init(){
            place_form();
            if (options.fixWidths) {
                fix_widths(el);
            }
            if (options.focusOnLoad) {
                focus_on_load();
            }
            if (options.inputText != "" && options.inputText != null) {
                toggle_text();
            }
            
            stripe(el);
        }
        
        function clear(data){
			if (cache) {
				cache.length = 0;
				cache = null;
			}
			
			for(var k in score){
				k.length = 0;
				k = null;
			}
			score = {};
			
			el = $(options.srcElement);
        }
        
        function qf(){
            if (cache === undefined || cache === null) {//2008-12
                get_cache(el);
            }
            
            clearTimeout(timeout);
            timeout = setTimeout(function(){
                loader('show');
                
                setTimeout(function(){
                    options.onBefore();
                    
                    var k = get_key();
                    var k_type = (typeof k);
                     
                    k = options.filter(k);
                    
                    if (k != "") {
                        if (typeof score[k] == "undefined") {
                            score[k] = new Array();
                            cache.each(function(i){
                                if (test_key(k, cache[i], k_type)) {
                                    score[k][i] = true;
                                }
                            });
                        }
                        
                        if (score[k].length == 0) {
                            select_element(el).hide();
                        } else {
                            $(el).not('.' + options.noResultsClass).each(function(i){
                                if (score[k][i]) {
                                    select_element(this).show();
                                }
                                else {
                                    select_element(this).hide();
                                }
                            });
                            
                        }
                    } else {
                        select_element(el).show();
                    }
                    
                    stripe(el);
                }, options.delay / 2);
                
                setTimeout(function(){
                    loader('hide');
                }, options.delay / 2);
                
                if(options.onAfter){
                	options.onAfter();
                }
                
            }, options.delay / 2);
        }
        
        init();
        
        $('input[rel="' + options.randomElement + '"]').each(function(){
			$(this).bind($.browser.msie ? "keydown" : "keypress", function(e){
            var keycode = e.keyCode;
				/*if (!(keycode == 9 || keycode == 13 || keycode == 16 || keycode == 17 || keycode == 18 || keycode == 38 || keycode == 40 || keycode == 224))
				{
				qf();
				}*/
				if (keycode == 13) {
					qf();
	                
					if (e.preventDefault) {
						e.preventDefault();
					}
					e.returnValue = false;
					return false;
				}
			}).bind('clear', function(){
				clear();
			}).bind('reset', function(){
				 if ($(this).val() !== options.inputText) {
					 $(this).val(options.inputText);
					 select_element(el).show();
                }
			});
		});
    };
})(jQuery);