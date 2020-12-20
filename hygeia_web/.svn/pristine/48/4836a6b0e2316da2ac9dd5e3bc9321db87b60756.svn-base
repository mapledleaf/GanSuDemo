/**  
 * Copyright © 2018 Powersi. All rights reserved.
 */
package com.powersi.ssm.biz.medicare.medicaretag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.dispatcher.Dispatcher;
import org.apache.struts2.views.jsp.StrutsBodyTagSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.inject.Container;
import com.powersi.hygeia.framework.ParameterMapping;
import com.powersi.pcloud.dict.service.DictService;
import com.powersi.web.jquery.components.Textfield;

/**
 * 

 * <p>Title: MedicareChoosePerson</p>

 * <p>Description: 医疗业务选择人员</p>

 * <p>Company: POWERSI</p> 

 * @author xiexiao

 * @date 2018年11月22日
 */
public class MedicareChoosePerson extends StrutsBodyTagSupport {

	private static final long serialVersionUID = 1L;
	private String id;
	private String name = "aac001";
	private int buttonDisplay = 0;
	private boolean readonly = false;
	private int empower = 1;
	private int colspan = 8;
	private String afterChoosePersonEvent = null;
	private String afterNotChoosePersonEvent = null;
	private String readCards = "0";
	private boolean required = true;
	private boolean checkPass = true;
	private String aae140;
	private String aka130; //医疗类型
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	JspWriter out = null;
	
	@Autowired
	private DictService dictService;

	public int doStartTag() throws JspException {
		String contextPath = ((HttpServletRequest) this.pageContext.getRequest()).getContextPath();
		this.request = (HttpServletRequest) this.pageContext.getRequest();
		this.response = (HttpServletResponse) this.pageContext.getResponse();
		this.out = this.pageContext.getOut();
		String lInputReadonlyAttribute = "";
		if (this.readonly) {
			lInputReadonlyAttribute = " readonly = \"readonly\" ";
		}
		try {
			this.out.write("<script type=\"text/javascript\">");
			this.out.write(" var p_empower = " + this.empower + ";");
			this.out.write(" var p_aae140 = " + this.aae140 + ";");
			this.out.write(" var p_aka130 = " + this.aka130 + ";");
			this.out.write("var localFileName = \'"
					+ this.request.getRequestURI().replaceAll(this.request.getContextPath(), "") + "\';");
			if (this.afterChoosePersonEvent != null) {
				if (!this.afterChoosePersonEvent.equalsIgnoreCase("afterChoosePerson")) {
					this.out.write(" function afterChoosePerson(data){");
					this.out.write("    return " + this.afterChoosePersonEvent + "(data);");
					this.out.write(" }");
				}
			} else {
				this.out.write(" function afterChoosePerson(){}");
			}

			if (this.afterNotChoosePersonEvent != null) {
				if (!this.afterNotChoosePersonEvent.equalsIgnoreCase("afterNotChoosePerson")) {
					this.out.write(" function afterNotChoosePerson(){");
					this.out.write("    return " + this.afterNotChoosePersonEvent + "();");
					this.out.write(" }");
				}
			} else {
				this.out.write(" function afterNotChoosePerson(){ ");
				this.out.write("   alert(\'系统中没有找到符合该条件的人员，请设置正确的查询条件！\')");
				this.out.write(" }");
			}

			this.out.write(" </script>");
			this.out.write("<script type=\"text/javascript\" src=\"" + contextPath
					+ "/resource/js/medicaretag/medicareChoosePerson.js\"></script>");
			switch (this.colspan) {
			case 4:
				this.addQueryTitle(lInputReadonlyAttribute);
				this.addTextField("aac002");
				this.out.write("<input type=hidden id=aac003 value=\'-1\'>");
				break;
			case 6:
				this.addQueryTitle(lInputReadonlyAttribute);
				this.addTextField("aac003");
				this.addTextField("aac002");
				break;
			case 8:
				this.addQueryTitle(lInputReadonlyAttribute);
				this.addTextField("aac003");
				this.addTextField("aac002");
				this.addTextField("aac001", this.name, true, "");
				break;
			default:
				break;
			}

			if(this.colspan != 8) {
				this.out.write("<input type=hidden id=aac001 name=\'" + this.name + "\' value=\'\'>");
			}
		} catch (IOException arg4) {
			System.out.println(arg4.getMessage());
		}

		return 0;
	}

	private void addQueryTitle(String vReadOnlyText) throws IOException {
		if(required) {
			this.out.write("<td class=tdLabel><label class=\'textLabel\'><span class=\'required\'>*</span>选择人员</label></td>");
		}else {
			this.out.write("<td class=tdLabel><label class=\'textLabel\'>选择人员</label></td>");
		}
		
		String blankText = "请在此处输入个人电脑号或身份证，回车！";
		String lHTMLReadCardsButton = "";
		this.out.write("<div style=\"display:none;\">");
		if (this.readCards.equalsIgnoreCase("1")) {
			lHTMLReadCardsButton = "<button id=\"readic_btn\" style=\"width:40px; position:relative;\" type=\"button\" "
					+ "class=\"popupButton\" spellcheck=\"false\" onclick=\"__readIcCardByClient();\">读卡</button>";
		}
		
		//String cardPassFlag = BizPolicyUtil.getBizPolicyValue(BizHelper.getAaa027(), "card_password_flag", "1");
		String card_pass_flag = ParameterMapping.getStringByCode("card_pass_flag","1");//读卡是否需要密码标志
		this.out.write("<input type=\"hidden\" id=\"isInpPsw\" value=\"" + "1".equals(card_pass_flag) + "\" />");
		
		String is_card_flag = ParameterMapping.getStringByCode("is_card_flag","0");//是否只能读卡或者身份证+密码办理业务

		if(!"1".equals(is_card_flag)){
			checkPass = false;
		}
		
		if(checkPass) {
			blankText = blankText.replaceAll("个人电脑号或", "");
		}
		this.out.write("<input type=\"hidden\" id=\"checkPass\" value=\"" + checkPass + "\" />"); // 身份证是否需要密码验证
		this.out.write("</div><td >");
		if("0".equals(this.readCards) && this.buttonDisplay == 0) {
			this.out.write("     <input type=text class=\'text\' id=queryString emptyString=\'" + blankText
					+ "\'  name=\"queryString\" onclick=\"begingInput();\" ");
			this.out.write("     " + vReadOnlyText);
			this.out.write("     placeholder=\'" + blankText + "\' />");
		}else if("1".equals(this.readCards) && this.buttonDisplay == 0) { // 只读卡
			this.out.write("     <input style=\'margin-right:-40px; vertical-align:top;\' type=text class=\'text\' id=queryString emptyString=\'" + blankText
					+ "\'  name=\"queryString\" onclick=\"begingInput();\" ");
			this.out.write("     " + vReadOnlyText);
			this.out.write("     placeholder=\'" + blankText + "\' />");
			this.out.write(lHTMLReadCardsButton);
		}else if("0".equals(this.readCards) && this.buttonDisplay > 0) { // 只有按钮
			this.out.write("     <input style=\'margin-right:-40px; vertical-align:top;\' type=text class=\'text\' id=queryString emptyString=\'" + blankText
					+ "\'  name=\"queryString\"  onclick=\"begingInput();\" ");
			this.out.write("     " + vReadOnlyText);
			this.out.write("     placeholder=\'" + blankText + "\' />");
			this.out.write("<button style=\"width:40px; position:fixed;\" type=\"button\" class=\"popupButton\" onclick=\"rest()\" spellcheck=\"false\">重置</button>");
		}else if("1".equals(this.readCards) && this.buttonDisplay > 0) { // 读卡和按钮皆有
			this.out.write("     <input style=\'margin-right:-80px; vertical-align:top;\'  type=text class=\'text\' id=queryString emptyString=\'" + blankText
					+ "\'  name=\"queryString\" onclick=\"begingInput();\" ");
			this.out.write("     " + vReadOnlyText);
			this.out.write("     placeholder=\'" + blankText + "\' />");
			this.out.write(lHTMLReadCardsButton);
			this.out.write("<button style=\"width:40px; position:fixed;\" type=\"button\" class=\"popupButton\" onclick=\"rest()\" spellcheck=\"false\">重置</button>");
		}
		this.out.write("</td>");
	}

	private void addTextField(String colName) {
		this.addTextField(colName, true, "");
	}

	private void addTextField(String colName, boolean vReadOnly, String label) {
		Textfield tagCol = new Textfield(this.getStack(), this.request, this.response);
		tagCol.setName(colName);
		tagCol.setId(colName);
		tagCol.setKey(colName);
		if (StringUtils.isNotBlank(label)) {
			tagCol.setLabel(label);
		}
		tagCol.setCssClass("text");
		if (vReadOnly) {
			tagCol.setReadonly("readonly=\'true\'");
		}
		Container container = Dispatcher.getInstance().getContainer();
		container.inject(tagCol);
		tagCol.start(this.out);
	}
	
	private void addTextField(String colID,String colName, boolean vReadOnly, String label) {
		Textfield tagCol = new Textfield(this.getStack(), this.request, this.response);
		tagCol.setName(colName);
		tagCol.setId(colID);
		tagCol.setKey(colID);
		if (StringUtils.isNotBlank(label)) {
			tagCol.setLabel(label);
		}
		tagCol.setCssClass("text");
		if (vReadOnly) {
			tagCol.setReadonly("readonly=\'true\'");
		}
		Container container = Dispatcher.getInstance().getContainer();
		container.inject(tagCol);
		tagCol.start(this.out);
	}

	public int doEndTag() throws JspException {
		return 6;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getColspan() {
		return this.colspan;
	}

	public void setColspan(int colspan) {
		this.colspan = colspan;
	}

	public int getEmpower() {
		return this.empower;
	}

	public void setEmpower(int empower) {
		this.empower = empower;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getButtonDisplay() {
		return this.buttonDisplay;
	}

	public void setButtonDisplay(int buttonDisplay) {
		this.buttonDisplay = buttonDisplay;
	}

	public String getAfterChoosePersonEvent() {
		return this.afterChoosePersonEvent;
	}

	public void setAfterChoosePersonEvent(String afterChoosePersonEvent) {
		this.afterChoosePersonEvent = afterChoosePersonEvent;
	}

	public String getAfterNotChoosePersonEvent() {
		return this.afterNotChoosePersonEvent;
	}

	public void setAfterNotChoosePersonEvent(String afterNotChoosePersonEvent) {
		this.afterNotChoosePersonEvent = afterNotChoosePersonEvent;
	}

	public boolean isReadonly() {
		return this.readonly;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}

	public String getReadCards() {
		return this.readCards;
	}

	public void setReadCards(String readCards) {
		this.readCards = readCards;
	}

	/**
	 * @return the required
	 */
	public boolean isRequired() {
		return required;
	}

	/**
	 * @param required the required to set
	 */
	public void setRequired(boolean required) {
		this.required = required;
	}

	/**
	 * @return the aae140
	 */
	public String getAae140() {
		return aae140;
	}

	/**
	 * @param aae140 the aae140 to set
	 */
	public void setAae140(String aae140) {
		this.aae140 = aae140;
	}
	
	public boolean isCheckPass() {
		return checkPass;
	}

	public void setCheckPass(boolean checkPass) {
		this.checkPass = checkPass;
	}

	/**
	 * @return the aka130
	 */
	public String getAka130() {
		return aka130;
	}

	/**
	 * @param aka130 the aka130 to set
	 */
	public void setAka130(String aka130) {
		this.aka130 = aka130;
	}
	
}
