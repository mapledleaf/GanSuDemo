package com.powersi.ssm.biz.medicare.queryAccount.action;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;

import com.powersi.biz.medicare.inhospital.service.util.CommunalService;
import com.powersi.biz.medicare.queryAccount.pojo.AccountDTO;
import com.powersi.biz.medicare.queryAccount.service.api.QueryAccountApiService;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.CodetableMapping;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;

import net.sf.json.JSONObject;

@Action(value = "AccountManageAction", results = {
		@Result(name = "report", location = "/pages/biz/medicare/queryAccount/AccountReport.jsp"), })
public class AccountManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CommunalService communalService;
	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;
	private AccountDTO accountDTO;

	@SuppressWarnings("rawtypes")
	public String queryAccountInfo() {
		try {
			QueryAccountApiService apiService = this.hygeiaServiceManager.getBeanByClass(QueryAccountApiService.class,
					BizHelper.getAkb020());
			this.getAccountDTO().setAkb020(BizHelper.getAkb020());
			Map<String, List<Map>> accountMap = apiService.queryAccountInfo(this.getAccountDTO());
			this.loadCode(accountMap.get("info"));
			setJSONReturn("data", accountMap);
		} catch (Exception e) {
			this.communalService.error(this.logger, e, null);
			this.saveJSONError(e.getMessage());
		}
		return NONE;
	}

	@SuppressWarnings("rawtypes")
	public void printAccountReport() {
		try {
			QueryAccountApiService apiService = this.hygeiaServiceManager.getBeanByClass(QueryAccountApiService.class,
					BizHelper.getAkb020());
			this.getAccountDTO().setAkb020(BizHelper.getAkb020());
			Map<String, List<Map>> accountMap = apiService.queryAccountInfo(this.getAccountDTO());
			List<Map> reportList = new ArrayList<Map>();
			if (StringUtils.isNotBlank(this.getAccountDTO().getGridId())
					&& this.getAccountDTO().getGridId().equals("accountDeitalInfo_lb")) {
				reportList = accountMap.get("lbdata");
			} else if (StringUtils.isNotBlank(this.getAccountDTO().getGridId())
					&& this.getAccountDTO().getGridId().equals("accountDeitalInfo_xt")) {
				reportList = accountMap.get("xtdata");
			} else {
				reportList = accountMap.get("detail");
			}

			this.exportToExcel(reportList, getRequest(), getResponse());
		} catch (Exception e) {
			this.communalService.error(this.logger, e, null);
			this.saveJSONError(e.getMessage());
		}

	}

	/**
	 * 加载码表值
	 * 
	 * @param dtoList
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void loadCode(List<Map> dtoList) {
		for (Map dto : dtoList) {
			String aae140 = dto.get("aae140") == null ? "" : dto.get("aae140").toString();
			String aaa027 = dto.get("aaa027") == null ? "" : dto.get("aaa027").toString();
			String aae140_name = CodetableMapping.getDisplayValue("aae140", aae140, aae140);
			String aaa027_name = CodetableMapping.getDisplayValue("aaa027", aaa027, aaa027);
			dto.put("aae140_name", aae140_name);
			dto.put("aaa027", aaa027_name);
			dto.put("aae140", aae140);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	public void exportToExcel(List<Map> list, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		@SuppressWarnings("resource")
		HSSFWorkbook workbook = new HSSFWorkbook();// 创建一个Excel文件
		HSSFSheet sheet = workbook.createSheet();// 创建一个Excel的Sheet
		sheet.createFreezePane(1, 1);// 冻结
		for (int i = 0; i < 37; i++) {
			sheet.setColumnWidth(i, 2500);
		}
		// 标题设置字体
		HSSFFont headfont = workbook.createFont();
		headfont.setFontName("黑体");
		headfont.setFontHeightInPoints((short) 22);// 字体大小
		headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
		// 标题样式
		HSSFCellStyle headstyle = workbook.createCellStyle();
		headstyle.setFont(headfont);
		headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		headstyle.setLocked(true);
		headstyle.setWrapText(true);// 自动换行
		// Sheet样式
		HSSFCellStyle sheetStyle = workbook.createCellStyle();

		// 设置列的样式
		for (int i = 0; i <= 14; i++) {
			sheet.setDefaultColumnStyle((short) i, sheetStyle);
		}
		HSSFFont columnHeadFont = workbook.createFont();
		columnHeadFont.setFontName("宋体");
		columnHeadFont.setFontHeightInPoints((short) 10);
		columnHeadFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 列头的样式
		HSSFCellStyle columnHeadStyle = workbook.createCellStyle();
		columnHeadStyle.setFont(columnHeadFont);
		columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		columnHeadStyle.setLocked(true);
		columnHeadStyle.setWrapText(true);
		columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框的颜色
		columnHeadStyle.setBorderLeft((short) 1);// 边框的大小
		columnHeadStyle.setRightBorderColor(HSSFColor.BLACK.index);// 右边框的颜色
		columnHeadStyle.setBorderRight((short) 1);// 边框的大小
		columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
		columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色
		// 普通单元格样式
		HSSFCellStyle style = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 10);
		style.setFont(font);
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 左右居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);// 上下居中
		style.setWrapText(true);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft((short) 1);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight((short) 1);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
		style.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．
		try {

			// 前端传过来的列头
			// JSONObject gridexport =
			// JSONObject.fromObject(getRequest().getParameter("gridexport").toString());
			String str = new String(this.getAccountDTO().getColumns().getBytes("iso8859-1"), "UTF-8");

			JSONObject gridexport = JSONObject.fromObject(str);
			Map<String, Object> gridexportMap = (Map<String, Object>) gridexport;

			// 创建第一行
			HSSFRow row0 = sheet.createRow(0);
			// 设置行高
			row0.setHeight((short) 900);
			int k = 0;
			for (String title : gridexportMap.keySet()) {
				// 创建第一列
				HSSFCell cellx = row0.createCell(k);
				cellx.setCellValue(new HSSFRichTextString(
						gridexportMap.get(title) == null ? "" : gridexportMap.get(title).toString()));
				cellx.setCellStyle(columnHeadStyle);
				k++;
			}

			// 遍历list 数组 加载excle 数据行
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> vo = list.get(i);
				HSSFRow row = sheet.createRow(1 + i);
				int y = 0;
				HSSFCell cellx = row.createCell(0);
				for (String title : gridexportMap.keySet()) {
					cellx = row.createCell(y);
					cellx.setCellValue(new HSSFRichTextString(vo.get(title) == null ? "" : vo.get(title).toString()));
					cellx.setCellStyle(style);
					y++;
				}

			}
			String filename = StringUtils.isBlank(this.getAccountDTO().getFilename()) ? "exportExcel.xls"
					: this.getAccountDTO().getFilename();
			filename = new String(filename.getBytes("iso8859-1"), "UTF-8");
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=" + filename);
			OutputStream ouputStream = response.getOutputStream();
			workbook.write(ouputStream);
			ouputStream.flush();
			ouputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AccountDTO getAccountDTO() {
		return accountDTO;
	}

	public void setAccountDTO(AccountDTO accountDTO) {
		this.accountDTO = accountDTO;
	}

}
