<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<powersi:html>
<head>
<powersi:head title="表单验证" />
</head>
<body>
	<powersi:form id="form1" disabled="true">
		<powersi:panelbox title="必填项(required)" iconClass="icon-check">
			<powersi:editorlayout cols="6">
				<powersi:editorlayout-row>
					<powersi:textfield name="textfield" id="textfield" required="true" label="textfield"></powersi:textfield>
					<powersi:textarea name="textarea" id="textarea" rows="1" maxlength="100" required="true" label="textarea"></powersi:textarea>
					<powersi:textfield label="hidden" name="hidden-text" value="" id="hidden-text" />
					<powersi:hidden id="hidden-val" name="hidden-val" required="true" value="" data-validation-target="hidden-text" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:codeselect name="select" id="select" label="select" codeType="aaa027_list" required="true" multiple="false" />
					<powersi:codeselect name="select2" id="select2" label="select2" codeType="aaa027_list" required="true" cssClass="select2" multiple="false" data-show-value="true" />
					<powersi:codeselect name="select2m" id="select2m" label="select2m" codeType="aaa027_list" required="true" cssClass="select2" multiple="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:checkboxlist id="checkbox" codeType="valid_flag" name="checkbox"  label="checkbox"  required="true" />
					<powersi:radio id="radio" codeType="valid_flag" name="validFlag"  label="radio" required="true" />
					<powersi:combobox id="combobox" valueFieldID="aaa027" codeType="aaa027_list" required="true" label="combobox">
						<powersi:hidden id="aaa027" name="aaa027" value="" />
					</powersi:combobox>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:panelbox title="必填组（groupRequired[组名] 需填写其中任意一项)" iconClass="icon-check">
			<powersi:editorlayout cols="6">
				<powersi:editorlayout-row>
					<powersi:textfield name="aac001" id="aac001" label="个人电脑号" validate="groupRequired[queryString],integer" placeholder="电脑号,姓名,身份证任填一项"></powersi:textfield>
					<powersi:textfield name="aac003" id="aac003" label="姓名" validate="groupRequired[queryString],chinese" placeholder="电脑号,姓名,身份证任填一项"></powersi:textfield>
					<powersi:textfield name="aac002" id="aac002" label="公民身份号码" validate="groupRequired[queryString],idcard" placeholder="电脑号,姓名,身份证任填一项" title="身份证只做简单位数校验，不做合法性校验" maxlength="18"></powersi:textfield>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:panelbox title="必填条件依赖（condRequired[条件项1,条件项2...] 条件项不为空，则被依赖项不能为空)" iconClass="icon-check" isCollapse="true">
			<powersi:editorlayout cols="6">
				<powersi:editorlayout-row>
					<powersi:textfield id="price1" label="单价1" validate="number"></powersi:textfield>
					<powersi:textfield id="amount1" label="数量1" validate="number"></powersi:textfield>
					<powersi:textfield id="money1" label="金额1" validate="condRequired[price1,amount1],number" title="输入了单价或者数量，就必须输入金额" placeholder="输入单价或数量就必须输入金额"></powersi:textfield>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="price2" label="单价2" validate="condRequired[money2],number"></powersi:textfield>
					<powersi:textfield id="amount2" label="数量2" validate="condRequired[money2],number"></powersi:textfield>
					<powersi:textfield id="money2" label="金额2" validate="number" title="输入了金额，就必须输入单价和数量" placeholder="输入金额就必须输入单价和数量"></powersi:textfield>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:editorlayout-label>
						<powersi:label for="group-cbx" value="选项1" required="false"></powersi:label>
					</powersi:editorlayout-label>
					<powersi:editorlayout-input colspan="2">
						<powersi:checkboxlist name="group-cbx" id="group-cbx" colspan="3" required="false" list="#{1:'始祖鸟',2:'猛犸象',3:'土拨鼠',4:'其他'}" listKey="key" listValue="value" data-label='选项1' />
						<powersi:textfield name="group-other1" id="group-other1" value="" validate="condRequired[group-cbx-4]" 
							data-prompt-label="选择其他" placeholder="选择其他时必填" />
					</powersi:editorlayout-input>
					<powersi:editorlayout-label>
						<powersi:label for="group-rb" value="选项2"></powersi:label>
					</powersi:editorlayout-label>
					<powersi:editorlayout-input colspan="2">
						<powersi:radio name="group-rb" id="group-rb" colspan="3" list="#{1:'始祖鸟',2:'猛犸象',3:'土拨鼠',4:'其他'}" listKey="key" listValue="value" data-label='选项2' />
						<powersi:textfield name="group-other2" id="group-other2" value="" validate="condRequired[group-rb4]" 
							data-prompt-label="选择其他" placeholder="选择其他时必填" />
					</powersi:editorlayout-input>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:panelbox title="长度验证" iconClass="icon-check" isCollapse="true">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
						<powersi:textfield id="v1" label="min" validate="integer,min[0]" placeholder="整数[0, max]"></powersi:textfield>
						<powersi:textfield id="v2" label="max" validate="integer,max[10]" placeholder="整数[0, 10]"></powersi:textfield>
						<powersi:textfield id="v3" label="minSize" validate="minSize[6]" placeholder="最小字符数"></powersi:textfield>
						<powersi:textfield id="v4" label="maxSize" validate="maxSize[6]" placeholder="最大字符数"></powersi:textfield>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
						<powersi:textfield id="pwd1" label="密码" required="true" validate="maxSize[20]" placeholder="" value="1"></powersi:textfield>
						<powersi:textfield id="pwd2" label="确认密码" required="true" validate="maxSize[20],equals[pwd1]" value="1"></powersi:textfield>
						<powersi:checkboxlist id="cbx1" label="最喜爱的户外品牌" name="outdoor"  colspan="3" required="true" validate="minCheckbox[2],maxCheckbox[3]" list="#{1:'始祖鸟',2:'猛犸象',3:'土拨鼠',4:'火柴棍',5:'MHW',6:'北面',7:'哥伦比亚',8:'狼爪'}" listKey="key" listValue="value" value="{1,2,3}" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:panelbox title="正则验证" iconClass="icon-check" isCollapse="true">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
						<powersi:textfield id="r1" label="url" validate="url" placeholder="网址(http,https,ftp)"></powersi:textfield>
						<powersi:textfield id="r2" label="email" validate="email" placeholder="邮箱地址"></powersi:textfield>
						<powersi:textfield id="r3" label="telephone" validate="telephone" placeholder="座机"></powersi:textfield>
						<powersi:textfield id="r4" label="mobilephone" validate="mobilephone" placeholder="手机"></powersi:textfield>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
						<powersi:textfield id="r5" label="number" validate="number" placeholder="浮点数"></powersi:textfield>
						<powersi:textfield id="r6" label="integer" validate="integer" placeholder="整数"></powersi:textfield>
						<powersi:textfield id="r7" label="onlyNumberSp" validate="onlyNumberSp" placeholder="数字"></powersi:textfield>
						<powersi:textfield id="r8" label="onlyLetterSp" validate="mobilephone" placeholder="字母"></powersi:textfield>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
						<powersi:textfield id="r9" label="onlyLetterNumber" validate="onlyLetterNumber" placeholder="数字和字母"></powersi:textfield>
						<powersi:textfield id="r10" label="chinese" validate="chinese" placeholder="中文"></powersi:textfield>
						<powersi:textfield id="r11" label="zipcode" validate="zipcode" placeholder="邮政编码"></powersi:textfield>
						<powersi:textfield id="r12" label="idcard" validate="idcard" placeholder="18位身份证（没有验证校验码)"></powersi:textfield>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:panelbox title="日期和范围验证" iconClass="icon-check" isCollapse="true">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
						<powersi:textfield id="d1" label="past" validate="past[20140101]" placeholder="输入20140101以前的日期" mask="date"></powersi:textfield>
						<powersi:textfield id="d2" label="future" validate="future[now]" placeholder="输入当前以后的日期" mask="date"></powersi:textfield>
						<powersi:textfield id="d3" label="开始日期" validate="dateRange[grp1]" placeholder="开始日期必须在结束日期之前" mask="date"></powersi:textfield>
						<powersi:textfield id="d4" label="结束日期" validate="dateRange[grp1]" placeholder="开始日期必须在结束日期之前" mask="date"></powersi:textfield>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
						<powersi:textfield id="d5" label="开始时间" validate="dateTimeRange[grp2]" placeholder="开始时间必须在结束时间之前" mask="datetime"></powersi:textfield>
						<powersi:textfield id="d6" label="结束时间" validate="dateTimeRange[grp2]" placeholder="开始时间必须在结束时间之前" mask="datetime"></powersi:textfield>
						<powersi:textfield id="d7" label="开始期间" validate="dateRange[grp3]" placeholder="开始期间必须在结束期间之前" mask="period"></powersi:textfield>
						<powersi:textfield id="d8" label="结束期间" validate="dateRange[grp3]" placeholder="开始期间必须在结束期间之前" mask="period"></powersi:textfield>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
						<powersi:textfield id="d9" label="最小浮点数" validate="number,numberRange[grp4]"></powersi:textfield>
						<powersi:textfield id="d10" label="最大浮点数" validate="number,numberRange[grp4]"></powersi:textfield>
						<powersi:textfield id="d11" label="最小整数" validate="integer,numberRange[grp5]"></powersi:textfield>
						<powersi:textfield id="d12" label="最大整数" validate="integer,numberRange[grp5]"></powersi:textfield>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:panelbox title="函数验证（函数没有返回值，则表示验证通过）" iconClass="icon-check">
			<powersi:editorlayout cols="4">
				<powersi:editorlayout-row>
						<powersi:textfield id="f1" label="function" required="true" validate="funcCall[checkFunc]" placeholder="必须输入powersi"></powersi:textfield>
						<powersi:textfield id="f2" label="ajax" required="true" validate="funcCall[checkAjax]" placeholder="检查登录名是否重复"></powersi:textfield>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<div class="divButton">
			<powersi:submit id="btnSubmit" key="button_audit" label="验证表单"></powersi:submit>
			<powersi:button id="btnClear" key="button_clear" label="清除验证" onclick="clearAllValidtion()" />
			<powersi:reset id="btnReset" key="button_reset" label="重置表单"></powersi:reset>
			
			<powersi:button id="btnDisable" key="button_disable" label="禁用表单" onclick="disableAll()" cssClass="btn btn-danger"></powersi:button>
			<powersi:button id="btnEnable" key="button_enable" label="启用表单" onclick="enableAll()" cssClass="btn btn-success"></powersi:button>
		</div>
	</powersi:form>
<powersi:errors />
<script type="text/javascript">
function checkFunc(field){
	if (field.val().toLowerCase() != "powersi") {
		return "请输入powersi";
	}
}

function checkAjax(field){
	var loginUser = field.val();
	if(loginUser == ''){
		return;
	}
	
	//使用同步ajax
	var json = postSync(rootPath + '/sample/Sample!checkLoginUserExists.action',  {"login_user": loginUser});
	
	//成功直接返回
	if(json.errortype == '0'){
		return;
	}
	
	//错误类型9表示异常
	if(json.errortype == '9'){
		return "检查登录名重复出错:" + json.message;
	} else{
		return (json.message || '');
	}
}

function disableAll(){
	//排除表达启用按钮
	setDisabled(":input:not('#btnEnable')");
}

function enableAll(){
	setEnabled(":input");
}
</script>
</body>
</powersi:html>