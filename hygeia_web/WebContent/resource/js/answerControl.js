/*
 * 题库答题js
 */
/*初始化答题div*/
$(function() {
	//showAnswerDiv(true);
	//loadQuestion();
});

/* 答题数据对象 */
var answerData = [];
/* 当前答题次数标记 */
var cuAnswerCount = 0;
/* 阅读题提示记录，提示过的不在提示 */
var readTipRecord = [];

/* 访问后台，加载答题问题数据 */
function loadQuestion() {
	try {
		postJSON(rootPath + "/examination/ExaminationAction!getAnswerExaminationData.action",
				{}, function(json) {
					// 没有操作权限控制，隐藏答题框
					if (json && json.errortype && json.errortype != "0") {
						hideAnswerDiv();
						return;
					}
					if (!checkJSONResult(json)) {
						hideAnswerDiv();
						return;
					}
					// 没有找到相关的数据
					if (json.data == "no") {
						hideAnswerDiv();
					} else {
						showAnswerDiv();
						// state为1时是白名单
						if (json.data.state == "1") {
							hideAnswerDiv();
							// state为2时锁定登录
						} else if (json.data.state == "2") {
							$("#LAY_app_answer_content").empty();
							$("#LAY_app_answer_content").append(
									'<h4 class="textLabel"><b style="color:#FF5722;font-size:16px;">单题回答错误次数已达到'
											+ json.data.errorCount
											+ '次,账号锁定,请联系系统管理员处理！</b></h4>');
						// state为3时关闭答题	
						} else if(json.data.state == "3"){
							hideAnswerDiv();
						} else{
							answerData = JSON.parse(json.data.answerData);
							readTipRecord.length = 0;
							doReadFilterQuestion();
						}
					}
				});
	} catch (e) {
		layer.alert(e.message);
		hideAnswerDiv();
	}
}

/* 用Math.random()函数生成0~1之间的随机数与0.5比较，返回-1或1，打乱显示答案的A、B、C..顺序 */
function randomSort(a, b) {
	return Math.random() > 0.5 ? -1 : 1;
}

/* 阅读题过滤判断，如果是阅读题，先弹出阅读材料 */
function doReadFilterQuestion(){
	if (cuAnswerCount < answerData.length) {
		var read_id = answerData[cuAnswerCount]["read_id"];
		if(read_id != null && read_id != "" && read_id != "null" &&  readTipRecord.indexOf(read_id) < 0){
			readTipRecord.push(read_id);
			createReadQuestionTip();
		}else{
			createQuestionHtml();
		}
	}else{
		hideAnswerDiv();
	}
	
}

/* 页面创建阅读题提示内容 */
function createReadQuestionTip() {
	$("#LAY_app_answer_content").empty();
	var a = [];
	a.push('<table class="tableEditor">');
	a.push('<tbody>');

	a.push('<tr>');
	a.push('<td>');
	//阅读题标题展示
	a.push('<h4><b>');
	a.push(answerData[cuAnswerCount]["read_title"]);
	a.push('</b></h4>');
	a.push('<hr/>');

	// 阅读题内容展示
	a.push('<p class="textLabel"><b>');
	a.push(answerData[cuAnswerCount]["read_content"]);
	a.push('</b></p>');

	a.push('<p class="textLabel" style="text-align: right;">');
	a.push('<button type="button" class="button" onclick="createQuestionHtml()">开始答题</button>');
	a.push('</p>');
	a.push('</td>');
	a.push('</tr>');

	a.push('</tbody>');
	a.push('</table>');
	$("#LAY_app_answer_content").append(a.join(''));
}

/* 页面创建答题内容 */
function createQuestionHtml() {
	var letterArray = [ "A", "B", "C", "D", "E", "F", "G", "H" ];
	$("#LAY_app_answer_content").empty();
	if (cuAnswerCount < answerData.length) {
		// 答案内容
		var examAnswer = answerData[cuAnswerCount]["exam_answer"];
		// 打乱显示顺序
		examAnswer = examAnswer.sort(randomSort);
		var answerCount = examAnswer.length;
		var rightAnswerCount = 0;
		var rightAnswerTitle = "单选题";
		for (var i = 0; i < answerCount; i++) {
			if (examAnswer[i]["right_flag"] == "1") {
				rightAnswerCount++;
			}
		}
		if(rightAnswerCount > 1){
			rightAnswerTitle = "多选题";
		}
		
		var a = [];
		a.push('<table class="tableEditor">');
		a.push('<tbody>');

		a.push('<tr>');
		a.push('<td>');
		// 题目数量提醒
		a.push('<h4><b>');
		a.push('第' + (cuAnswerCount + 1) + '题('+rightAnswerTitle+')(总共' + answerData.length + '题) ');
		a.push('</b></h4>');
		a.push('<hr/>');

		// 问题展示
		a.push('<p class="textLabel"><b>');
		a.push(answerData[cuAnswerCount]["exam_content"]);
		a.push('</b></p>');
		
		// 答案内容展示
		for (var i = 0; i < answerCount; i++) {
			a.push('<p class="textLabel">');
			a.push(letterArray[i] + '、 ');
			a.push(examAnswer[i]["answer_content"]);
			a.push('</p>');
		}

		// 给考题主键赋值
		var exam_id = answerData[cuAnswerCount]["exam_id"];

		// 答案选择展示
		a.push('<table class="tableEditor">');
		a.push('<tbody>');
		a.push('<tr>');
		for (var i = 0; i < answerCount; i++) {
			a.push('<td style="width: 50px;">');
			a.push('<input type="checkbox" style="zoom: 130%;" value="'
					+ examAnswer[i]["right_flag"] + '" name="'
					+ examAnswer[i]["exam_id"] + '">');
			a.push(letterArray[i]);
			a.push('</td>');
		}
		a.push('<td>');
		a.push('<button type="button" class="button" id="checkAnswerId" onclick="checkAnswer(\''
						+ exam_id + '\')">确定</button>');
		a.push('</td>');

		a.push('</tr>');
		a.push('</tbody>');
		a.push('</table>');
		a.push('</td>');
		a.push('</tr>');

		// 正确答案提示
		a.push('<tr id="errorHint" style="display: none">');
		a.push('<td style="width: 50px;">');
		a.push('<p>正确答案:&nbsp; &nbsp; ');
		for (var i = 0; i < answerCount; i++) {
			if (examAnswer[i]["right_flag"] == "1") {
				a.push(letterArray[i] + '、 ');
			}
		}
		a.push('<label style="margin-left: 100px;">');
		a.push('<button type="button" class="button" onclick="doReadFilterQuestion()">确定</button>');
		a.push('</label">');
		a.push('</p>');
		a.push('</td>');
		a.push('</tr>');

		a.push('</tbody>');
		a.push('</table>');
		$("#LAY_app_answer_content").append(a.join(''));

		cuAnswerCount++;
	} else {
		hideAnswerDiv();
	}
}

/* 校验答题答案的正确性 */
function checkAnswer(exam_id) {
	var right = true;
	var isAnser = false;

	if ($("div#LAY_app_answer_content input[type=checkbox]:checked").length == 0) {
		popupAlert("请先选择答案，在点击确定！", "提示", "error");
		return;
	}

	$("div#LAY_app_answer_content input[type=checkbox]").each(
			function(index, el) {
				if (($(el).prop("checked") && $(el).val() == "0")
						|| (!$(el).prop("checked") && $(el).val() == "1")) {
					right = false;
				}
			})
	saveAnswerResult(right ? "1" : "0", exam_id);
}

/* 向后台发送答题情况 */
function saveAnswerResult(right, exam_id) {
	if (exam_id != "") {
		try {
			//隐藏答题按钮，避免多次提交
			$("button#checkAnswerId").hide();
			postJSON(rootPath + "/examination/ExaminationAction!saveAnswerResult.action",
					{
						"exam_id" : exam_id,
						"right_flag" : right
					}, function(json) {
						// 没有操作权限控制，隐藏答题框
						if (json && json.errortype && json.errortype != "0") {
							hideAnswerDiv();
							return;
						}
						if (!checkJSONResult(json)) {
							hideAnswerDiv();
							return;
						}
						if (json.data.return_value == "ok") {
							if (right == "1") {
								$("tr#errorHint").hide();
								doReadFilterQuestion();
							} else {
								$("button#checkAnswerId").hide();
								$("tr#errorHint").show();
							}
						} else {
							popupAlert(json.data.return_value, "提示", "error");
						}
					});
		} catch (e) {
			layer.alert(e.message);
			hideAnswerDiv();
		}
	} else {
		popupAlert("没有需要答题的内容！", "提示", "error");
		hideAnswerDiv();
	}
}

/* 页面展示答题面板 */
function showAnswerDiv(single) {
	$("#LAY_app_answer_shadow").show();
	if(!single){
		$("#LAY_app_answer_content").show();
	}
}

/* 页面隐藏答题面板 */
function hideAnswerDiv() {
	$("#LAY_app_answer_shadow").hide();
	$("#LAY_app_answer_content").hide();
}
