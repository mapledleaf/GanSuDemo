(function($, undefined) {

    $.widget("ui.pagination", {
        options: {
            rows: 20,
            url: null,
            param: null,
            datatype: "json",
            beforeLoad: function() {
                return true;
            },
            callback: null,
            info: {
                page_size: 5,
                first: '|&lsaquo;',
                last: '&rsaquo;|',
                next: '&rsaquo;&rsaquo;',
                prev: '&lsaquo;&lsaquo;',
                first_on: true,
                last_on: true,
                next_on: true,
                prev_on: true,
                msg_on: true,
                link: '#',
                msg: '<span>&nbsp;&nbsp;跳{currText}/{sumPage}页</span>',
                text: {
                    width: '22px'
                }
            }
        },
        page: 1,
        records: 0,
        total: 0,

        _create: function() {
            this.element.addClass("pagepanel");

            this._render();
        },

        destroy: function() {
            this.element.removeClass("pagepanel");
            this.element.html("");
            
            $.Widget.prototype.destroy.call(this);
        },

        param: function(param) {
            if (param !== undefined) {
                this._setOption('param', param);
            }

            return this;
        },

        load: function(index) {
            try {
                var page = 1;
                if (index > 0) {
                    page = index;
                }

                this._load(page);

            } catch (e) {
                alert(e.message);
            }

            return this;
        },

        attr: function() {
            return {
            	'rows': this.options.rows,
            	'page' : this.page,
            	'total':this.total,
            	'records': this.records
            };
        },

        _param: function() {
        	var self = this;
        	
            var p = { "page": self.page, "pagesize": self.options.rows };
            if (this.options.param) {
                if (typeof this.options.param === "string") {
                    return $.param(p) + "&" + self.options.param;
                } else {
                    return $.extend(p, self.options.param);
                }
            } else {
                return p;
            }
        },

        _load: function(index) {
            var self = this;

            if (self.options.beforeLoad) {
                var r = false;
                if (jQuery.isFunction(self.options.beforeLoad)) {
                    r = self.options.beforeLoad();
                } else {
                    r = eval(self.options.beforeLoad + "()");
                }
                if (!r) {
                    return;
                }
            }

            self.page = index;

            postJSON(self.options.url, this._param(), function(data){
            	if (typeof(data) === 'undefined' && data === null) {
            		alert('无数据返回');
                    return;
                }
            	
	        	 if (typeof(data.errortype) !== 'undefined' && data.errortype !== null && data.errortype != "0") {
	               alert(data.message);
	               return;
	             }
            	
            	if(data.records && data.total){
            		self.records = data.records;
            		self.total = data.total;
            	}
            	
            	self._render();
            	
            	if(self.options.callback){
            		self.options.callback(data.rows);
            	}
            });
        },
        
        _render: function() {
            var self = this;
            var initRecord = ((self.page * self.options.rows) - self.options.rows) + 1;
            var lastRecord = 0;
            if (self.page < self.total) {
                lastRecord = (self.page * self.options.rows);
            } else {
                lastRecord = self.records;
            }

            try {
                var options = self.options;
                var pageCount = self.total;
                var currPage = self.page;
                var pageSize = options.info.page_size;
                var link = options.info.link;
                var tempPage = parseInt(pageSize / 2);
                var firstPage = lastPage = 1;
                if (currPage - tempPage > 0) {
                    firstPage = currPage - tempPage;
                } else {
                    firstPage = 1;
                }
                if (firstPage + pageSize > pageCount) {
                    lastPage = pageCount + 1;
                    firstPage = lastPage - pageSize;
                } else {
                    lastPage = firstPage + pageSize;
                }
                var content = [];

                if (self.records == 0) {
                    content.push("<span class=\"pagebanner\"><span>共 0 项.</span></span><span class=\"pagelinks\"></span>");
                } else {
                    content.push("<span class=\"pagebanner\">");
                    content.push("<span>");
                    content.push(" 共 " + self.records + " 项 本页 " + initRecord + " - " + lastRecord + ".");
                    content.push("</span>");
                    content.push("</span>");

                    content.push("<span class=\"pagelinks\">");

                    if(self.total > 1){
                    	if (currPage != 1) {
                            if (options.info && options.info.first_on && options.info.first) {
                                content.push("<a href='" + link + "' title='转到第 1 页' data='1'>" + options.info.first + "</a>");
                            }
                        }

                        if (currPage != 1) {
                            if (options.info && options.info.prev_on && options.info.prev) {
                                content.push("<a href='" + link + "' title='转到上一页' data='" + (currPage - 1).toString() + "'>" + options.info.prev + "</a>");
                            }
                        }

                        if (firstPage <= 0) {
                            firstPage = 1;
                        }
                        for (firstPage; firstPage < lastPage; firstPage++) {
                            if (firstPage == currPage) {
                                content.push("<span class=\"currentPageButton\">" + firstPage + "</span>");
                            } else {
                                content.push("<a href='" + link + "' title='转到第 " + firstPage + " 页' data='" + firstPage + "'>" + firstPage + "</a>");
                            }
                        }

                        if (currPage != pageCount) {
                            if (options.info && options.info.next_on && options.info.next) {
                                content.push("<a href='" + link + "' title='转到下一页' data='" + (currPage + 1).toString() + "'>" + options.info.next + "</a>");
                            }
                        }

                        if (currPage != pageCount) {
                            if (options.info && options.info.last_on && options.info.last) {
                                content.push("<a href='" + link + "' title='转到第 '" + pageCount + " 页' data='" + pageCount + "'>" + options.info.last + "</a>");
                            }
                        }
                    }
                    
                    content.push("</span>");
                }

                content.push("<div style=\"clear:both;\"></div>");
                self.element.html(content.join(""));
                content.length = 0;

                self.element.find("a").each(function(i) {
                    var data = $(this).attr("data");
                    $(this).click(function() {
                        self._load(data);
                        $(this).focus();
                        return false;
                    });
                });

            } catch (e) {
                alert(e.message);
            }
        }
    });

} (jQuery));