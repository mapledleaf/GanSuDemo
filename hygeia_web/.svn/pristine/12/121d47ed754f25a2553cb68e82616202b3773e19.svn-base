package com.powersi.ssm.biz.medicare.medicaretag;

import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspWriter;

import org.apache.struts2.views.jsp.StrutsBodyTagSupport;

import com.powersi.hygeia.web.util.JsonHelper;

/**
 * 医疗日期选择控件
 * 
 * @author lwyao
 * @date 2018年10月19日
 */
public class MedicareDaterange extends StrutsBodyTagSupport {

	private static final long serialVersionUID = 1L;

	private String labelValues;

	private String name;

	JspWriter out = null;

	public int doStartTag() {
		this.out = this.pageContext.getOut();
		List<Map<String, Object>> fields = JsonHelper.toList("[" + labelValues + "]");
		try {
			if (fields.size() == 1) {
				this.out.write("<td class=\"tdLabel\"><label for=\"aka030\" class=\"textLabel\">");
				this.out.write("<span class=\"required requiredLabel\">*</span><span>" + fields.get(0).get("text") + "</span>");
				this.out.write("</label></td>");
			}
			this.out.write("<td class=\"tdLabel\" align=\"right\"" + (fields.size() == 1 ? "style=\"display:none;\"" : "") + ">");
			this.out.write("<span class=\"required requiredLabel\">*</span>");
			this.out.write("<select id=\"_time_type\" name=\"" + (name != null && !"".equals(name) ? name : "time_type")
					+ "\" style=\"width: 80px;\" class=\"select tdLabel\">");
			for (Map<String, Object> m : fields) {
				this.out.write("<option labelValues='" + JsonHelper.toJson(m) + "' value=\""
						+ (m.containsKey("value") ? m.get("value") : m.get("text")) + "\">" + m.get("text") + "</option>");
			}
			this.out.write("</select></td>");
			this.out.write("<td class=\"tdInput\"><div class=\"input-icon\">");
			this.out.write(
					"<input type=\"text\" id=\"_config-range\" class=\"form-control\"><i class=\"icon-fa fa-calendar\" style=\"cursor:pointer;\"></i>");
			this.out.write("</div><div style=\"display:none;\" class=\"_input-value\"></div>");
			this.out.write("<script type=\"text/javascript\">$(function(){");
			this.out.write("$('select#_time_type').change(function(){");
			this.out.write("var sel=$(this).find('option:selected'),labelvalues=JSON.parse(sel.attr('labelvalues')),");
			this.out.write("opts={type:labelvalues.type,name:labelvalues.name,format:labelvalues.format};");
			this.out.write("if(labelvalues.extFunc&&$.isFunction(window[labelvalues.extFunc])){");
			this.out.write("var extObj=window[labelvalues.extFunc].call(this);");
			this.out.write("if($.isPlainObject(extObj)){$.each(extObj,function(k,v){opts[k]=v;});}}");
			this.out.write("_daterange($('#_config-range'),opts);");
			this.out.write("$('#_config-range').next('i.icon-fa.fa-calendar').click(function(){");
			this.out.write("$(this).prev('input#_config-range').click();});}).trigger('change');");
			this.out.write("$('#_config-range').mousedown(function(){");
			this.out.write("var drp=$(this).data('daterangepicker'),vals=this.value.split(drp.locale.separator);");
			this.out.write("if(vals&&vals.length>1){drp.setStartDate(vals[0].trim());drp.setEndDate(vals[1].trim());}");
			this.out.write("});");
			this.out.write("});</script>");
			this.out.write("</td>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public String getLabelValues() {
		return labelValues;
	}

	public void setLabelValues(String labelValues) {
		this.labelValues = labelValues;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}