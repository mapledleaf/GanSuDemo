<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<powersi:html>
<head>
<powersi:head title="垂直选项卡" />
<script src="${strutsPath}/include/highcharts.js" type="text/javascript"></script>
<style type="text/css">
.chart{height: 520px; margin: 0 auto}
</style>
</head>
<body>
<powersi:tabbedpanel id="divTabs" cssClass="ui-tabs-vertical">
	<powersi:tab id="tab1" target="divTab1" label="线状图表" />
	<powersi:tab id="tab2" target="divTab2" label="分区图表" />
	<powersi:tab id="tab3" target="divTab3" label="柱状图表" />
	<powersi:tab id="tab4" target="divTab4" label="饼状图表" />
	<powersi:tab id="tab5" target="divTab5" label="动态图表" />
	<powersi:tab id="tab6" target="divTab6" label="组合图表" />
	<powersi:tab id="tab7" target="divTab7" label="仪 表 盘" />
	<div id="divTab1">
		<div id="chart1" class="chart"></div>
	</div>
	<div id="divTab2">
		<div id="chart2" class="chart"></div>
	</div>
	<div id="divTab3">
		<div id="chart3" class="chart"></div>
	</div>
	<div id="divTab4">
		<div id="chart4" class="chart"></div>
	</div>
	<div id="divTab5">
		<div id="chart5" class="chart"></div>
	</div>
	<div id="divTab6">
		<div id="chart6" class="chart"></div>
	</div>
	<div id="divTab7">
		<div id="chart7" class="chart" style="width: 400px; height: 300px"></div>
	</div>
</powersi:tabbedpanel>
<powersi:errors />
<script type="text/javascript">
$(function () {
$(document).ready(function() {
    try{
		//创建图表控件，为了保证控件宽度自适应，需要激活每个tab
    	var tabs = $('#divTabs');
    	
    	tabs.tabs( "select" , 0);
       new Highcharts.Chart({
            chart: {
                renderTo: 'chart1',
                type: 'line',
                marginRight: 70/*,
                marginBottom: 25*/
            },
            exporting:{
            	filename: encodeURIComponent('平均温度统计'),
            	width: 800
			},
            title: {
                text: '平均温度',
                x: -20,
                style: {
        			fontSize: '18px'
        		}
            },
            subtitle: {
                text: '中央气象台',
                x: -20
            },
            xAxis: {
                categories: ['一月', '二月', '三月', '四月', '五月', '六月',
                    '七月', '八月', '九月', '十月', '十一月', '十二月']
            },
            yAxis: {
                title: {
                    text: '温度 (°C)'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                formatter: function() {
                        return '<b>'+ this.series.name +'</b><br/>'+
                        this.x +': '+ this.y +'°C';
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -10,
                y: 100,
                borderWidth: 0
            },
            series: [{
                name: '长沙',
                data: [-9.2, 5, 12.5, 14.5, 18.2, 24.5, 35.2, 42.5, 34.3, 16.3, 1.9, -5.8]
            },{
                name: '北京',
                data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
            }, {
                name: '广州',
                data: [-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5]
            }]
        });
       
       tabs.tabs( "select" , 1);
       new Highcharts.Chart({
           chart: {
               renderTo: 'chart2',
               type: 'areaspline'
           },
           title: {
               text: 'Average fruit consumption during one week'
           },
           legend: {
               layout: 'vertical',
               align: 'left',
               verticalAlign: 'top',
               x: 150,
               y: 100,
               floating: true,
               borderWidth: 1,
               backgroundColor: '#FFFFFF'
           },
           xAxis: {
               categories: [
                   'Monday',
                   'Tuesday',
                   'Wednesday',
                   'Thursday',
                   'Friday',
                   'Saturday',
                   'Sunday'
               ],
               plotBands: [{ // visualize the weekend
                   from: 4.5,
                   to: 6.5,
                   color: 'rgba(68, 170, 213, .2)'
               }]
           },
           yAxis: {
               title: {
                   text: 'Fruit units'
               }
           },
           tooltip: {
               formatter: function() {
                   return ''+
                   this.x +': '+ this.y +' units';
               }
           },
           credits: {
               enabled: false
           },
           plotOptions: {
               areaspline: {
                   fillOpacity: 0.5
               }
           },
           series: [{
               name: 'John',
               data: [3, 4, 3, 5, 4, 10, 12]
           }, {
               name: 'Jane',
               data: [1, 3, 4, 3, 3, 5, 4]
           }]
       });
   
       tabs.tabs( "select" , 2);
       new Highcharts.Chart({
           chart: {
               renderTo: 'chart3',
               type: 'bar'
           },
           title: {
               text: 'Historic World Population by Region'
           },
           subtitle: {
               text: 'Source: Wikipedia.org'
           },
           xAxis: {
               categories: ['Africa', 'America', 'Asia', 'Europe', 'Oceania'],
               title: {
                   text: null
               }
           },
           yAxis: {
               min: 0,
               title: {
                   text: 'Population (millions)',
                   align: 'high'
               },
               labels: {
                   overflow: 'justify'
               }
           },
           tooltip: {
               formatter: function() {
                   return ''+
                       this.series.name +': '+ this.y +' millions';
               }
           },
           plotOptions: {
               bar: {
                   dataLabels: {
                       enabled: true
                   }
               }
           },
           legend: {
               layout: 'vertical',
               align: 'right',
               verticalAlign: 'top',
               x: -100,
               y: 100,
               floating: true,
               borderWidth: 1,
               backgroundColor: '#FFFFFF',
               shadow: true
           },
           credits: {
               enabled: false
           },
           series: [{
               name: 'Year 1800',
               data: [107, 31, 635, 203, 2]
           }, {
               name: 'Year 1900',
               data: [133, 156, 947, 408, 6]
           }, {
               name: 'Year 2008',
               data: [973, 914, 4054, 732, 34]
           }]
       });
       
       tabs.tabs( "select" , 3);
       new Highcharts.Chart({
           chart: {
               renderTo: 'chart4',
               plotBackgroundColor: null,
               plotBorderWidth: null,
               plotShadow: false
           },
           title: {
               text: 'Browser market shares at a specific website, 2010'
           },
           tooltip: {
       	    pointFormat: '{series.name}: <b>{point.percentage}%</b>',
           	percentageDecimals: 1
           },
           plotOptions: {
               pie: {
                   allowPointSelect: true,
                   cursor: 'pointer',
                   dataLabels: {
                       enabled: true,
                       color: '#000000',
                       connectorColor: '#000000',
                       formatter: function() {
                           return '<b>'+ this.point.name +'</b>: '+ this.percentage +' %';
                       }
                   }
               }
           },
           series: [{
               type: 'pie',
               name: 'Browser share',
               data: [
                   ['Firefox',   45.0],
                   ['IE',       26.8],
                   {
                       name: 'Chrome',
                       y: 12.8,
                       sliced: true,
                       selected: true
                   },
                   ['Safari',    8.5],
                   ['Opera',     6.2],
                   ['Others',   0.7]
               ]
           }]
       });
       
       tabs.tabs( "select" , 4);
       Highcharts.setOptions({
           global: {
               useUTC: false
           }
       });
       new Highcharts.Chart({
           chart: {
               renderTo: 'chart5',
               type: 'spline',
               marginRight: 10,
               events: {
                   load: function() {
   
                       // set up the updating of the chart each second
                       var series = this.series[0];
                       setInterval(function() {
                           var x = (new Date()).getTime(), // current time
                               y = Math.random();
                           series.addPoint([x, y], true, true);
                       }, 1000);
                   }
               }
           },
           title: {
               text: 'Live random data'
           },
           xAxis: {
               type: 'datetime',
               tickPixelInterval: 150
           },
           yAxis: {
               title: {
                   text: 'Value'
               },
               plotLines: [{
                   value: 0,
                   width: 1,
                   color: '#808080'
               }]
           },
           tooltip: {
               formatter: function() {
                       return '<b>'+ this.series.name +'</b><br/>'+
                       Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+
                       Highcharts.numberFormat(this.y, 2);
               }
           },
           legend: {
               enabled: false
           },
           exporting: {
               enabled: false
           },
           series: [{
               name: 'Random data',
               data: (function() {
                   // generate an array of random data
                   var data = [],
                       time = (new Date()).getTime(),
                       i;
   
                   for (i = -19; i <= 0; i++) {
                       data.push({
                           x: time + i * 1000,
                           y: Math.random()
                       });
                   }
                   return data;
               })()
           }]
       });
       
       tabs.tabs( "select" , 5);
       new Highcharts.Chart({
           chart: {
               renderTo: 'chart6'
           },
           title: {
               text: 'Combination chart'
           },
           xAxis: {
               categories: ['Apples', 'Oranges', 'Pears', 'Bananas', 'Plums']
           },
           tooltip: {
               formatter: function() {
                   var s;
                   if (this.point.name) { // the pie chart
                       s = ''+
                           this.point.name +': '+ this.y +' fruits';
                   } else {
                       s = ''+
                           this.x  +': '+ this.y;
                   }
                   return s;
               }
           },
           labels: {
               items: [{
                   html: 'Total fruit consumption',
                   style: {
                       left: '40px',
                       top: '8px',
                       color: 'black'
                   }
               }]
           },
           series: [{
               type: 'column',
               name: 'Jane',
               data: [3, 2, 1, 3, 4]
           }, {
               type: 'column',
               name: 'John',
               data: [2, 3, 5, 7, 6]
           }, {
               type: 'column',
               name: 'Joe',
               data: [4, 3, 3, 9, 0]
           }, {
               type: 'spline',
               name: 'Average',
               data: [3, 2.67, 3, 6.33, 3.33],
               marker: {
               	lineWidth: 2,
               	lineColor: Highcharts.getOptions().colors[3],
               	fillColor: 'white'
               }
           }, {
               type: 'pie',
               name: 'Total consumption',
               data: [{
                   name: 'Jane',
                   y: 13,
                   color: '#4572A7' // Jane's color
               }, {
                   name: 'John',
                   y: 23,
                   color: '#AA4643' // John's color
               }, {
                   name: 'Joe',
                   y: 19,
                   color: '#89A54E' // Joe's color
               }],
               center: [100, 80],
               size: 100,
               showInLegend: false,
               dataLabels: {
                   enabled: false
               }
           }]
       });
       
       tabs.tabs( "select" , 6);
       new Highcharts.Chart({
      	    chart: {
      	        renderTo: 'chart7',
      	        type: 'gauge',
      	        plotBackgroundColor: null,
      	        plotBackgroundImage: null,
      	        plotBorderWidth: 0,
      	        plotShadow: false
      	    },
      	    
      	    title: {
      	        text: 'Speedometer'
      	    },
      	    
      	    pane: {
      	        startAngle: -150,
      	        endAngle: 150,
      	        background: [{
      	            backgroundColor: {
      	                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
      	                stops: [
      	                    [0, '#FFF'],
      	                    [1, '#333']
      	                ]
      	            },
      	            borderWidth: 0,
      	            outerRadius: '109%'
      	        }, {
      	            backgroundColor: {
      	                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
      	                stops: [
      	                    [0, '#333'],
      	                    [1, '#FFF']
      	                ]
      	            },
      	            borderWidth: 1,
      	            outerRadius: '107%'
      	        }, {
      	            // default background
      	        }, {
      	            backgroundColor: '#DDD',
      	            borderWidth: 0,
      	            outerRadius: '105%',
      	            innerRadius: '103%'
      	        }]
      	    },
      	       
      	    // the value axis
      	    yAxis: {
      	        min: 0,
      	        max: 200,
      	        
      	        minorTickInterval: 'auto',
      	        minorTickWidth: 1,
      	        minorTickLength: 10,
      	        minorTickPosition: 'inside',
      	        minorTickColor: '#666',
      	
      	        tickPixelInterval: 30,
      	        tickWidth: 2,
      	        tickPosition: 'inside',
      	        tickLength: 10,
      	        tickColor: '#666',
      	        labels: {
      	            step: 2,
      	            rotation: 'auto'
      	        },
      	        title: {
      	            text: 'km/h'
      	        },
      	        plotBands: [{
      	            from: 0,
      	            to: 120,
      	            color: '#55BF3B' // green
      	        }, {
      	            from: 120,
      	            to: 160,
      	            color: '#DDDF0D' // yellow
      	        }, {
      	            from: 160,
      	            to: 200,
      	            color: '#DF5353' // red
      	        }]        
      	    },
      	
      	    series: [{
      	        name: 'Speed',
      	        data: [80],
      	        tooltip: {
      	            valueSuffix: ' km/h'
      	        }
      	    }]
      	
      	}, 
      	// Add some life
      	function (chart) {
      	    setInterval(function () {
      	        var point = chart.series[0].points[0],
      	            newVal,
      	            inc = Math.round((Math.random() - 0.5) * 20);
      	        
      	        newVal = point.y + inc;
      	        if (newVal < 0 || newVal > 200) {
      	            newVal = point.y - inc;
      	        }
      	        
      	        point.update(newVal);
      	        
      	    }, 3000);
      	});
       
       tabs.tabs( "select" , 0);
    } catch(ex){
    	alert(ex.message);
    }
});
});
</script>
</body>
</powersi:html>