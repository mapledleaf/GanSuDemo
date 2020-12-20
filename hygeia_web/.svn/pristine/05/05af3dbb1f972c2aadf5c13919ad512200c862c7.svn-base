<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<powersi:html>
<head>
<powersi:head title="数据表单" />
<script src="${rootPath }/pages/sample/webui/data-company.js" charset="utf-8" type="text/javascript"></script>
</head>
<body>
<div id="form1"></div>
<powersi:errors />
<script type="text/javascript">
var groupicon = rootPath + "/struts/css/images/ligerui/icons/communication.gif";
$(function () { 
    $("#form1").ligerForm({
        inputWidth: 170, labelWidth: 90, space: 40,
        fields: [
        { name: "ProductID", type: "hidden" },
        { display: "产品名称", name: "ProductName", newline: true, type: "text" , group: "基础信息", groupicon: groupicon},
        {
            display: "顾客", name: "CustomerID", textField: "CustomerName",
            newline: false, type: "combobox", editor: {
                selectBoxWidth: 600,
                selectBoxHeight: 385,
                textField: 'CustomerID',
                valeuField: 'CustomerID',
                condition: {
                    prefixID : 'condition_',
                    fields: [
                        { label: '顾客', name: 'CustomerID', type: 'text' }
                    ]
                },
                grid: {
                    columns: [
				        { display: '主键', name: 'CustomerID', align: 'left', width: 80, minWidth: 50 },
				        { display: '公司名', name: 'CompanyName', minWidth: 120, width: 200 },
				        { display: '联系名', name: 'ContactName', minWidth: 120, width: 150 },
				        { display: '城市', name: 'City' }
                    ], data: companydata, isScroll: false, sortName: 'CustomerID',
                    width: 600, rowHeight: 28, headerRowHeight: 28
                }
            }},
        { display: "类别 ", name: "CategoryID", newline: true, type: "select", comboboxName: "CategoryName", options: { valueFieldID: "CategoryID" }, width: 475 },
        { display: "日期 ", name: "AddTime", newline: true, type: "date"  },
        { display: "折扣", name: "QuantityPerUnit", newline: false, type: "number" },
        { display: "单价", name: "UnitPrice", newline: true,  type: "number" },
        { display: "库存量", name: "UnitsInStock", newline: true, type: "digits", group: "库存", groupicon: groupicon },
        { display: "订购量", name: "UnitsOnOrder", newline: false, type: "digits" },
        { display: "备注", name: "Remark", newline: true, type: "text" ,width:475 }
        ]
    }); 
});
</script>
</body>
</powersi:html>