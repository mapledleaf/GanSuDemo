// 统一声明全局变量
const job_order_mask = $("#LAY_app_job_order_mask");
const job_order = $("#LAY_app_job_order");
const suggestion_mask = $("#LAY_app_suggestion_mask");
const suggestion = $("#LAY_app_suggestion");
const payment_collection_mask = $("#LAY_app_payment_collection_mask");
const payment_collection = $("#LAY_app_payment_collection");
const payment_info = $("#LAY_app_payment_info");
const payment_info_mask = $("#LAY_app_payment_info_mask");

// 派工单列表
let list1;
// 回款确认单列表
let list2;
// 保存图片的名字
let imgName1, imgName2;
/**
 * 派工单、回款单客户确认
 */
$(function () {
    //loadPromiseForIE();
    //loadConfirmPanel();
});

//加载IE对Promise的依赖
function loadPromiseForIE() {
    if (!!window.ActiveXObject || "ActiveXObject" in window) {
        let script = document.createElement('script');
        script.type = 'text/javaScript';
        script.src = '../resource/js/bluebird.min.js';
        document.getElementsByTagName('head')[0].appendChild(script);
    }
}


/**
 * 加载派工单确认、回款确认单
 */
function loadConfirmPanel() {
    postJSON(rootPath + "/common/CommonManagerAction!queryConfirmList.action", function (json) {
        if (!checkJSONResult(json)) {
            return;
        }
        if (json.data !== undefined && json.data !== 'undefined' && json.data !== '' && json.data !== null) {
            list1 = json.data.list1;
            list2 = json.data.list2;
            if (list1 !== undefined && list1 !== 'undefined' && list1 !== '' && list1.length > 0) {
                createJobOrderPanel(list1);
            } else {
                createPaymentCollectionPanel(list2);
            }
        }
    });
}

/**
 * 创建派工单确认面板
 * @param list 派工单内容列表
 */
function createJobOrderPanel(list) {
    job_order_mask.show();
    job_order.empty();
    let htmlText = [];
    htmlText.push('<table>');
    htmlText.push('<tbody>');

    htmlText.push('<tr>');
    htmlText.push('<td colspan="2">');
    htmlText.push('<span style="color:red;"><u>' + improveText(list[0].hospital_name) + improveText(list[0].hospital_code) + '：</u></span>');
    htmlText.push('<td/>');
    htmlText.push('<tr/>');

    htmlText.push('<tr>');
    htmlText.push('<td colspan="2">');
    htmlText.push('<p class="textLabel">');
    htmlText.push('<br/>');
    htmlText.push('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;');
    htmlText.push('感谢您在软件使用过程中给予的支持与合作！我公司与贵单位签署的合同（如下表）已完成实施工作。系统功能已经满足合同规定建设内容，');
    htmlText.push('性能符合用户实际要求，系统运行平稳；培训工作达到预期效果。');
    htmlText.push('</p>');
    htmlText.push('<br/>');
    htmlText.push('</td>');
    htmlText.push('</tr>');

    htmlText.push('<tr>');
    htmlText.push('<td colspan="2">');
    htmlText.push('<form id="jobOrderContent">');
    htmlText.push('<table class="payment_list">');
    htmlText.push('<thead><tr style="white-space:nowrap;">');
    htmlText.push('<td style="display:none;">付款唯一标识号</td>');
    htmlText.push('<td style="display:none;">合同编号</td>');
    htmlText.push('<td style="width:5%;">序号</td>');
    htmlText.push('<td style="width:20%;">合同名称</td>');
    htmlText.push('<td style="width:60%;">服务内容</td>');
    htmlText.push('<td style="width:15%;">实施时间</td></tr></thead>');
    htmlText.push('<tbody>');
    for (let i = 0; i < list.length; i++) {
        htmlText.push('<tr>');
        htmlText.push('<td style="display:none;"><input type="text" name="system_notice_id" value="' + list[i].system_notice_id + '"</td>');
        htmlText.push('<td style="display:none;"><input type="text" name="contract_code" value="' + list[i].contract_code + '"</td>');
        htmlText.push('<td>' + (i + 1) + '</td>');
        htmlText.push('<td>' + improveText(list[i].contract_name) + '</td>');
        htmlText.push('<td>' + improveText(list[i].service_content) + '</td>');
        htmlText.push('<td>' + improveText(list[i].implement_date) + '</td></tr>');
    }
    htmlText.push('</tbody>');
    htmlText.push('</table>');
    htmlText.push('</form>');
    htmlText.push('</td>');
    htmlText.push('</tr>');

    htmlText.push('<tr>');
    htmlText.push('<td><br/>');
    htmlText.push('<p class="textLabel" style="text-align: center;">');
    htmlText.push('<button type="button" class="button" onclick="goAhead(0)">确认验收通过</button>');
    htmlText.push('</p>');
    htmlText.push('</td>');

    htmlText.push('<td><br/>');
    htmlText.push('<p class="textLabel" style="text-align: center;">');
    htmlText.push('<button type="button" class="button" onclick="goAhead(1)">验收不通过</button>');
    htmlText.push('</p>');
    htmlText.push('</td>');
    htmlText.push('</tr>');

    htmlText.push('</tbody>');
    htmlText.push('</table>');
    job_order.append(htmlText.join(''));
    job_order.show();
    imgName1 = improveText(list[0].hospital_code) + improveText(list[0].contract_code);
}

/**
 * 创建派工单建议面板
 */
function createSuggestionPanel() {
    job_order_mask.hide();
    job_order.hide();

    suggestion_mask.show();
    suggestion.empty();
    let htmlText = [];
    htmlText.push('<table style="width:100%;height:100%;">');
    htmlText.push('<tbody>');

    htmlText.push('<tr>');
    htmlText.push('<td>');
    htmlText.push('<h4><b>');
    htmlText.push('其他建议或问题：');
    htmlText.push('</b></h4>');
    htmlText.push('<td/>');
    htmlText.push('<tr/>');

    htmlText.push('<tr>');
    htmlText.push('<td>');
    htmlText.push('<br/>');
    htmlText.push("<textarea style='width:100%;height:100%;resize:none;' name='suggestionContent' id='suggestionContent'></textarea>");
    htmlText.push('<br/>');
    htmlText.push('</td>');
    htmlText.push('</tr>');

    htmlText.push('<td>');
    htmlText.push('<br/>');
    htmlText.push('<p class="textLabel" style="text-align: center;">');
    htmlText.push('<button type="button" class="button" onclick="saveMessage(0)">提交</button>');
    htmlText.push('</p>');
    htmlText.push('</td>');
    htmlText.push('</tr>');

    htmlText.push('</tbody>');
    htmlText.push('</table>');

    suggestion.append(htmlText.join(''));
    suggestion.show();
}

/**
 * 创建回款单确认面板
 * @param list 回款确认单列表
 */
function createPaymentCollectionPanel(list) {
    if (list !== undefined && list !== 'undefined' && list !== '' && list.length > 0) {
        payment_collection_mask.show();
        payment_collection.empty();
        let htmlText = [];
        htmlText.push('<table>');
        htmlText.push('<tbody>');

        htmlText.push('<tr>');
        htmlText.push('<td colspan="2">');
        htmlText.push('<span style="color:red;"><u>');
        htmlText.push(improveText(list[0].hospital_name) + improveText(list[0].hospital_code));
        htmlText.push('：</u></span>');
        htmlText.push('<td/>');
        htmlText.push('<tr/>');

        htmlText.push('<tr>');
        htmlText.push('<td colspan="2">');
        htmlText.push('<p class="textLabel">');
        htmlText.push('<br/>');
        htmlText.push('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;');
        htmlText.push('感谢您在软件使用过程中给予的支持与合作！贵单位于');
        htmlText.push('<u>&nbsp;&nbsp;');
        htmlText.push('<span style="color:red;"><u>' + improveText(list[0].time_interval) + '</u></span>');
        htmlText.push('&nbsp;&nbsp;</u>');
        htmlText.push('期间向我公司支付了如下款项用于我公司所提供的服务，请进行确认，如需咨询请与我公司客服中心联系，联系电话：400 602 0109。');
        htmlText.push('</p>');
        htmlText.push('<br/>');
        htmlText.push('</td>');
        htmlText.push('</tr>');

        htmlText.push('<tr>');
        htmlText.push('<td colspan="2">');
        htmlText.push('<form id="paymentContentData">');
        htmlText.push('<table class="payment_list">');
        htmlText.push('<thead><tr style="white-space:nowrap;">');
        htmlText.push('<td style="display:none;">付款唯一标识号</td>');
        htmlText.push('<td style="display:none;">合同编号</td>');
        htmlText.push('<td style="width:10%;">付款日期</td>');
        htmlText.push('<td style="width:12%;">付款名义</td>');
        htmlText.push('<td style="width:13%;">付款帐号</td>');
        htmlText.push('<td style="width:8%;">付款金额（元）</td>');
        htmlText.push('<td style="width:15%;">付款人身份证号码</td>');
        htmlText.push('<td style="width:12%;">付款方与购买方关系</td>');
        htmlText.push('<td style="width:30%;">服务内容</td></tr></thead>');
        htmlText.push('<tbody>');
        for (let i = 0; i < list.length; i++) {
            htmlText.push('<tr>');
            htmlText.push('<td style="display:none;"><input type="text" name="system_notice_id" value="' + list[i].system_notice_id + '"</td>');
            htmlText.push('<td style="display:none;"><input type="text" name="contract_code" value="' + list[i].contract_code + '"</td>');
            htmlText.push('<td>' + improveText(list[i].payment_date) + '</td>');
            htmlText.push('<td>' + improveText(list[i].payment_name) + '</td>');
            htmlText.push('<td>' + improveText(list[i].payment_account) + '</td>');
            htmlText.push('<td>' + improveText(list[i].payment_amount) + '</td>');
            htmlText.push('<td><input type="text" name="payment_id_card" id="payerId_' + i + '"></td>');
            htmlText.push('<td><select name="relationship" id="relationship_' + i + '" onchange="setPayerId(' + i + ')">');
            htmlText.push('<option value="0">请选择（必选）</option>');
            htmlText.push('<option value="1">对公账户</option>');
            htmlText.push('<option value="2">股东</option>');
            htmlText.push('<option value="3">职工</option>');
            htmlText.push('<option value="4">总/分店</option>');
            htmlText.push('<option value="5">亲属</option>');
            htmlText.push('<option value="6">客户</option>');
            htmlText.push('<option value="7">朋友</option>');
            htmlText.push('</select></td>');
            htmlText.push('<td>' + improveText(list[i].service_content) + '</td></tr>');
        }
        htmlText.push('</tbody>');
        htmlText.push('</table>');
        htmlText.push('</form>');
        htmlText.push('</td>');
        htmlText.push('</tr>');

        htmlText.push('<tr>');
        htmlText.push('<td>');
        htmlText.push('<p class="textLabel" style="text-align: center;">');
        htmlText.push('<button type="button" class="button" onclick="goAhead(2)">已确认</button>');
        htmlText.push('</p>');
        htmlText.push('</td>');

        htmlText.push('<td>');
        htmlText.push('<p class="textLabel" style="text-align: center;">');
        htmlText.push('<button type="button" class="button" onclick="goAhead(3)">信息不符</button>');
        htmlText.push('</p>');
        htmlText.push('</td>');
        htmlText.push('</tr>');

        htmlText.push('</tbody>');
        htmlText.push('</table>');
        payment_collection.append(htmlText.join(''));
        payment_collection.show();
        imgName2 = improveText(list[0].hospital_code) + improveText(list[0].contract_code);
    }
}

/**
 * 创建回款单信息不符，回填单
 */
function createPaymentInfoPanel() {
    payment_collection_mask.hide();
    payment_collection.hide();

    payment_info_mask.show();
    payment_info.empty();
    let htmlText = [];
    htmlText.push('<table style="width:100%;height:100%;">');
    htmlText.push('<tbody>');

    htmlText.push('<tr>');
    htmlText.push('<td>');
    htmlText.push('<h4><b>');
    htmlText.push('信息不符原因描述：');
    htmlText.push('</b></h4>');
    htmlText.push('<td/>');
    htmlText.push('<tr/>');

    htmlText.push('<tr>');
    htmlText.push('<td>');
    htmlText.push('<br/>');
    htmlText.push("<textarea style='width:100%;height:100%;resize:none;' name='messageContent' id='messageContent'></textarea>");
    htmlText.push('<br/>');
    htmlText.push('</td>');
    htmlText.push('</tr>');

    htmlText.push('<td>');
    htmlText.push('<br/>');
    htmlText.push('<p class="textLabel" style="text-align: center;">');
    htmlText.push('<button type="button" class="button" onclick="saveMessage(1)">提交</button>');
    htmlText.push('</p>');
    htmlText.push('</td>');
    htmlText.push('</tr>');

    htmlText.push('</tbody>');
    htmlText.push('</table>');

    payment_info.append(htmlText.join(''));
    payment_info.show();
}

/**
 * 确认通过或者不通过
 * @param flag 0：派工单通过，1：派工单不通过，2：回款单确认，3：汇款单信息不符
 */
function goAhead(flag) {
    switch (flag) {
        case 0 :
            saveImageAndFeedback(job_order, 0, $("#jobOrderContent").serialize(), imgName1 + "_1");
            job_order_mask.hide();
            job_order.hide();
            createPaymentCollectionPanel(list2);
            break;
        case 1 :
            createSuggestionPanel();
            break;
        case 2 :
            let payerIdArr = document.getElementsByName("payment_id_card");
            let relationshipArr = document.getElementsByName("relationship");
            for (let i = 0; i < payerIdArr.length; i++) {
                let payerId = $.trim(payerIdArr[i].value);
                if (payerId === '') {
                    popupAlert("付款人身份证号码不能为空！", "提示", "info");
                    return;
                } else {
                    let idRegExp = /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
                    let relationship = relationshipArr[i].value;
                    if (relationship !== '1' && relationship !== '4' && !idRegExp.test(payerId)) {
                        popupAlert("付款人身份证号码格式不正确！", "提示", "info");
                        return;
                    }
                }
            }
            for (let i = 0; i < relationshipArr.length; i++) {
                if ($.trim(relationshipArr[i].value) === '0') {
                    popupAlert("请选择付款方与购买方关系！", "提示", "info");
                    return;
                }
            }
            saveImageAndFeedback(payment_collection, 2, $("#paymentContentData").serialize(), imgName2+"_1");
            payment_collection_mask.hide();
            payment_collection.hide();
            break;
        case 3 :
            createPaymentInfoPanel();
            break;
        default:
            return;
    }
}

/**
 * 保存派工单建议或者回款单不符信息
 * @param flag 0：派工单建议，1：回款不符信息
 */
function saveMessage(flag) {
    if (flag === 0) {
        let suggestionContent = $("#suggestionContent").val();
        if ($.trim(suggestionContent) === "") {
            popupAlert("建议内容不能为空！", "提示", "info");
        } else {
            let noticeIdStr = "";
            for (let i = 0; i < list1.length; i++) {
                noticeIdStr += list1[i].system_notice_id;
                if (i < list1.length - 1) {
                    noticeIdStr += "|";
                }
            }

            saveImageAndFeedback(suggestion, 1, noticeIdStr + "=" + $.trim(suggestionContent), imgName1 + "_0");
            suggestion_mask.hide();
            suggestion.hide();
            createPaymentCollectionPanel(list2);
        }
    }
    if (flag === 1) {
        let messageContent = $("#messageContent").val();
        if ($.trim(messageContent) === "") {
            popupAlert("不符内容不能为空！", "提示", "info");
        } else {
            let noticeIdStr = "";
            for (let i = 0; i < list2.length; i++) {
                noticeIdStr += list2[i].system_notice_id;
                if (i < list2.length - 1) {
                    noticeIdStr += "|";
                }
            }
            saveImageAndFeedback(payment_info, 3, noticeIdStr + "=" + $.trim(messageContent), imgName2 + "_0");
            payment_info_mask.hide();
            payment_info.hide();
        }
    }
}

/**
 * 付款方与购买方关系与付款人身份证号码的关联关系
 * @param index
 */
function setPayerId(index) {
    let relationship = $("#relationship_" + index);
    let payerId = $("#payerId_" + index);
    if (relationship.val() === '1' || relationship.val() === '4') {
        payerId.val("对公账户无需输入");
        payerId.prop("readonly", "true");
    } else {
        payerId.val("");
        payerId.removeAttr("readonly");
    }
}

/**
 * 保存图片以及相关信息
 * @param obj 需要转换为图片的dom对象
 * @param type 保存的数据类型 0：派工单确认保存，1：派工单建议保存，2：回款单确认保存，3：回款单建议保存
 * @param data 数据信息
 * @param imgName 图片名称
 */
function saveImageAndFeedback(obj, type, data, imgName) {
    html2canvas(obj, {
        onrendered: function (canvas) {
            let imgData = canvas.toDataURL("image/jpeg", 1.0);
            let objCard = document.getElementById("cardControl");
            let systemInfo = parseSystemInfo(objCard.GetClientInfo());
            postJSON(rootPath + "/common/CommonManagerAction!saveImageAndFeedback.action",
                {
                    "infoData": data,
                    "type": type,
                    "imgName": imgName,
                    "imgData": imgData,
                    "osDisk": systemInfo.osDisk,
                    "mac": systemInfo.mac
                },
                function (json) {
                    if(json !== null) {
                        if (!checkJSONResult(json)) {
                            return;
                        }
                        if (!powersi.isnull(json.message)) {
                            popupAlert(json.message, "提示", "info");
                        }
                    }
                }
            );
        }
    });
}

/**
 * 完善页面显示
 * @param text
 * @returns {string}
 */
function improveText(text) {
    if (text === undefined || text === 'undefined' || text === '' || text === null || text === 'null') {
        return '';
    } else {
        return text;
    }
}

/**
 * 解析通过插件获取得到的系统信息
 * @param info
 * @returns {{mac: string, osDisk: string}}
 */
function parseSystemInfo(info) {
    let obj = {"mac": "", "osDisk": ""};
    if ($.trim(info) !== "" && info.substring(0, 1) === "1" && info.length > 1) {
        let systemInfo = JSON.parse("[" + info.substring(2) + "]");
        if (systemInfo !== undefined && systemInfo.length > 0) {
            obj.mac = systemInfo[0].network_info;
            obj.osDisk = systemInfo[0].os_disk;
        }
    }
    return obj;
}