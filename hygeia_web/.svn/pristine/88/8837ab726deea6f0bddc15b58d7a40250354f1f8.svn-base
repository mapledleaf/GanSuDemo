<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	request.setAttribute("loginUser", BizHelper.getLoginUser());
	request.setAttribute("userName", BizHelper.getUserName());
	request.setAttribute("AAA027", BizHelper.getAaa027());
%>
<powersi:html>
<powersi:head title="问卷调查表作答" />
<style type="text/css">
#examContentDiv {
    width: 80%;
    margin-left: 10%;
    min-height: 300px;
}
.answer-title {
    font-size: 20px;
    color: #484848;
    text-align: center;
    margin-bottom: 30px;
    margin-top: 15px;
}
.answer-cut-line {
    border: 0.5px solid #53a4f4;
    margin: 30px 0px 24px;
}
.question-content {
    margin-bottom: 18px;
}
.question-title {
    font-size: 16px;
    color: #484848;
    margin-bottom: 8px;
}
.question-option .matrix .icheckbox_div span {
    line-height: 1;
}
.question-option .matrix .icheckbox_div {
    margin-bottom: 6px;
}
.question-option .matrix .icheckbox_label {
    margin-left: 8px;
}
.question-option {
    display: inline-block;
    margin-bottom: 12px;
}
</style>

<body>
	<powersi:form>
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button onclick="query()" buttonIcon="icon-search" label="查 询" title="Alt+Q" data-hotkey="Alt+Q"/>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:codeselect id="aaa027" label="统筹区编码" name="q.aaa027" codeType="aaa027" required="true" value="${AAA027}" headerKey=" " />
					<td colspan="6"></td>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox key="panel_result" allowCollapse="false" title="问卷调查表">
		<div id="examContentDiv" ></div>
	</powersi:panelbox>
<script type="text/javascript">
/* 答题数据对象 */
var questionExamData = [];
function query(){
	try {
		var aaa027 =  $("#aaa027").val();;
		postJSON(rootPath + "/question/QuestionAction!getQuestionExamData.action",
				{"aaa027":aaa027}, function(json) {
					if(!checkJSONResult(json,"popup")){
						return;
					}
					// 没有找到相关的数据
					if (json.data.state == "no") {
						createNoResultTips();
					} else {
						questionExamData = JSON.parse(json.data.questionExamData);
						if(!questionExamData || questionExamData.length == '0'){
							createNoResultTips();
						}else{
							createQuestionExamHtml();
						}
					}
				});
	} catch (e) {
		layer.alert(e.message);
	}
}	

function createNoResultTips(){
	$("#examContentDiv").empty();
	$("#examContentDiv").append('<h4 class="textLabel">没有找到['+$("#aaa027").find("option:selected").text()+']问卷调查表数据！</h4>');
}

/* 页面创建问卷调查表内容 */
function createQuestionExamHtml() {
	if(questionExamData.length > 0){
		$("#examContentDiv").empty();
		var a = [];
		a.push('<form id="question_form">');
		//问卷调查标题
		a.push('<div class="answer-title">');
		a.push(questionExamData[0]["question_exam_title"]);
		a.push('</div>');
		//分割线
		a.push('<div class="answer-cut-line"></div>');
		//加载问卷调查问题
		for (var i = 0; i < questionExamData.length; i++) {
			var question_type = questionExamData[i]["question_type"];//问卷题目类型：单选题(0)，多选题(1)，问答题(2)
			var exam_select = questionExamData[i]["exam_select"];//选择题选项内容
			var answer_content = questionExamData[i]["answer_content"];//答题人内容
			if(powersi.isempty(answer_content)){
				answer_content = "";
			}
			var operateType = "add";
			if(!powersi.isempty(answer_content)){
				operateType = "update";
			}
			a.push('<div class="question-content">');
			a.push('<input type="hidden" name = "question_type" value="'+question_type+'">');
			a.push('<input type="hidden" name = "question_content" value="'+questionExamData[i]["question_content"]+'">');
			a.push('<input type="hidden" name = "question_id" value="'+questionExamData[i]["question_id"]+'">');
			a.push('<input type="hidden" name = "question_exam_id" value="'+questionExamData[i]["question_exam_id"]+'">');
			a.push('<input type="hidden" name = "question_exam_respond_id" value="'+questionExamData[i]["question_exam_respond_id"]+'">');
			a.push('<input type="hidden" name = "operateType" value="'+operateType+'">');
			//题目标题
			a.push('<div class="question-title">');
			a.push('Q'+(1+i) +'：'+questionExamData[i]["question_content"]);
			a.push('</div>');
			
			a.push('<div class="question-option">');
			a.push('<div class="matrix">');
			var inputType = "";
			if("0" == question_type) {
				inputType = "radio";
			}else if("1" == question_type){
				inputType = "checkbox";
			}
			if(inputType != ""){
				if(operateType == "update"){
					a.push('<div class="icheckbox_div show_answer_content"><p style="color:#ADADAD">已答答案：'+answer_content+'</p></div>');
				}
				for (var j = 0; j < exam_select.length; j++) {
					a.push('<div class="icheckbox_div">');
					a.push('<input type="'+inputType+'" style="zoom: 100%;" value="'
							+ exam_select[j]["question_select_content"] + '" name="'
							+ exam_select[j]["question_id"] + '" data-question_select_id="'
							+ exam_select[j]["question_select_id"] + '"/>');
					a.push('<label class="icheckbox_label">'+exam_select[j]["question_select_content"]+'</label>');
					a.push('</div>');
				}
			}else if("2" == question_type){
				a.push('<div class="icheckbox_div">');
				a.push('<textarea rows="5" cols="120" style="width:100%;">'+answer_content+'</textarea>');
				a.push('</div>');
			}
			a.push('</div>');
			a.push('</div>');
			a.push('</div>');
		}
		//提交按钮
		a.push('<div style="text-align: center;">')
		a.push('<button type="button" class="button" id="saveQuestionExamBtn" onclick="saveQuestionExam()">提 交</button>');
		a.push('</div>');
		a.push('</form>');
		$("#examContentDiv").append(a.join(''));
	}
}

function saveQuestionExam(){
	//后台发送答题内容封装
	var dataArray = [];
	var checkBoolean = true;//校验检查
	$("#examContentDiv div.question-content").each(
		function(index, el) {
			var question_type = $(el).find("input[name='question_type']").val();
			var question_content = $(el).find("input[name='question_content']").val();
			var question_exam_id = $(el).find("input[name='question_exam_id']").val();
			var question_id = $(el).find("input[name='question_id']").val();
			var question_exam_respond_id = $(el).find("input[name='question_exam_respond_id']").val();
			var operateType = $(el).find("input[name='operateType']").val();
			var question_select_id = "";
			var answer_content = "";
			if(question_type == "0"){
				answer_content = $(el).find("input[type=radio]:checked").val();
				question_select_id = $(el).find("input[type=radio]:checked").data("question_select_id");
			}else if(question_type == "1"){
				$(el).find("input[type=checkbox]:checked").each(function(i,e){
					answer_content += i == 0?$(e).val():"；" + $(e).val();
					question_select_id += i == 0?$(e).data("question_select_id"):";" + $(e).data("question_select_id");
				})
			}else if(question_type == "2"){
				answer_content = $(el).find("textarea").val();
				
			}else{
				popupAlert("未知的题目类型:"+question_type, "提示", "error");
				checkBoolean = false;
				return false;
			}
			if(powersi.isempty(answer_content)){
				popupAlert("题目["+question_content+"]未作答，请先作答在提交", "提示", "error");
				checkBoolean = false;
				return false;
			}
			var questionJosn = {};
			questionJosn.question_content = question_content;
			questionJosn.question_exam_id = question_exam_id;
			questionJosn.question_id = question_id;
			questionJosn.question_select_id = question_select_id;
			questionJosn.answer_content = answer_content;
			questionJosn.question_exam_respond_id = question_exam_respond_id;
			questionJosn.operateType = operateType;
			
			dataArray.push(questionJosn);
	});
	if(!checkBoolean){
		return;
	}
	if(dataArray.length == 0){
		popupAlert("没有答题数据提交", "提示", "error");
		return;
	}
	postJSON(rootPath + "/question/QuestionAction!saveQuestionExamData.action",{
				datas : JSON.stringify(dataArray)
			}, function(json) {
				if(json.errortype > 0) {
					popupAlert(json.message, "提示", "error");
				}else{
					$("#saveQuestionExamBtn").hide();//隐藏提交按钮
					$("div.show_answer_content").hide();//隐藏已答题内容提示
					popupAlert("问卷调查作答提交成功。", "提示", "info", function() {
					});
				}
			});
}
</script>	
</body>
</powersi:html>