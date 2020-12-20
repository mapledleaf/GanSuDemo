package com.powersi.ssm.biz.medicare.health.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.health.pojo.HealthDTO;
import com.powersi.hygeia.comm.service.CharCodeSwitch;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.ssm.biz.medicare.common.service.CharCodeSwitchImpl;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;

import jxl.Sheet;
import jxl.Workbook;

public class HealthServiceImpl implements HealthService {

	@SuppressWarnings("unchecked")
	@Override
	public List getExcelContextCata(File cata_img,  HealthDTO healthDTO) {
		List rsList = new ArrayList();
		try {

			Workbook workbook = Workbook.getWorkbook(cata_img);
			// 获取EXCEL文件
			Sheet sheet = workbook.getSheet(0); // 获取第一页
			/**
			 * 验证文件是否为空
			 * 
			 */
			if (sheet.getRows() < 2) {
				throw new HygeiaException("上传文件为空,请核查!");
			} else {
				
				List cloumNameList = new ArrayList();// 存放文件头每列的名字
				cloumNameList.add("体检项目编码*");// //【1】
				cloumNameList.add("项目名称*"); // 【2】
				cloumNameList.add("统计类别*"); // 【3】
				cloumNameList.add("单价*"); // 【4】
				cloumNameList.add("标准单位*"); // 【5】
				cloumNameList.add("生效时间"); // 【6】
				cloumNameList.add("失效时间"); // 【7】
				cloumNameList.add("是否离休体检"); // 【8】
				cloumNameList.add("备注"); // 【9】
		
				
				for (int i = 0; i < cloumNameList.size(); i++) {
					if (!cloumNameList.get(i).equals(sheet.getCell(i, 0).getContents())) {
						throw new HygeiaException("文件对应列不符合模版标识内容,请重新下载模版并填写相关数据！" + cloumNameList.get(i));
					}
				}
			}
			String akb020 = BizHelper.getAkb020();
			String aaa027 = BizHelper.getAaa027();
			String loginUser = BizHelper.getLoginUser();
			String userName = BizHelper.getUserName();
			for (int i = 1; i < sheet.getRows(); i++) {
				Map map = new HashMap();
				String a = sheet.getCell(0, i).getContents();
				if (!sheet.getCell(0, i).getContents().equals("")) {
					
					map.put("bkh027", sheet.getCell(0, i).getContents().trim()); // i行第1列  
					map.put("bkh028", sheet.getCell(1, i).getContents().trim()); // i行第2列
					map.put("bkh046", sheet.getCell(2, i).getContents().trim().substring(0, 3)); // i行第3列
					String bkh044 = sheet.getCell(3, i).getContents();
					bkh044=UtilFunc.isEmpty(bkh044)?"":bkh044.trim();
					map.put("bkh044", bkh044.matches("\\d{0,}\\.\\d{0,}|\\d{0,}")?Double.parseDouble(bkh044):0.00); // i行第4列
					map.put("bkh045", sheet.getCell(4, i).getContents().trim()); // i行第5列
					
					map.put("bkh103", sheet.getCell(5, i).getContents().trim()); // i行第6列
					map.put("bkh104", sheet.getCell(6, i).getContents().trim()); // i行第7列
					String bkh048_str = sheet.getCell(7, i).getContents().trim();
					String bkh048 = "";
					if(bkh048_str.startsWith("1")) {
						bkh048 = "1";
					}else {
						bkh048 = "2";
					}
					map.put("bkh048", bkh048); // i行第8列
					map.put("aae013", sheet.getCell(8, i).getContents().trim()); // i行第9列
					CharCodeSwitch charCode = new CharCodeSwitchImpl();
		            String aka020 = charCode.LoadPY(map.get("bkh028").toString());
		            String aka021 = charCode.LoadWB(map.get("bkh028").toString());
					map.put("aka020", aka020);
					
					map.put("aka021", aka021);
					map.put("akb020", akb020);
					map.put("bkh068", "0");
					map.put("bke207", "");
					map.put("bke208", "");
					
					map.put("bke209", "");
					map.put("aae016", "0");
					map.put("bkh047", "0");
					map.put("aae100", "1");
					map.put("bka044", "0");
					
					map.put("aaa027", aaa027);
					map.put("bke204", strReplaceAll());
					map.put("bke205", loginUser);
					map.put("bke206", userName.length()>30?userName.substring(0, 30):userName);

				}else {
					continue;
				}
				rsList.add(map);
			}

		} catch (Exception e) {
			throw new HygeiaException(e);
		}
		return rsList;
	}
	/**
	 * 处理字符串时间 转成yyyyMMdd
	 * 
	 * @param str
	 * @return
	 */
	public String strReplaceAll() {
		String str="";
        SimpleDateFormat formatter2  = new SimpleDateFormat("yyyyMMdd");
        str = formatter2.format(new Date());
		return str;
	}
	@Override
	public List getExcelConclusion(File cata_img, HealthDTO healthDTO) {
		List rsList = new ArrayList();
		try {

			Workbook workbook = Workbook.getWorkbook(cata_img);
			// 获取EXCEL文件
			Sheet sheet = workbook.getSheet(0); // 获取第一页
			/**
			 * 验证文件是否为空
			 * 
			 */
			if (sheet.getRows() < 2) {
				throw new HygeiaException("上传文件为空,请核查!");
			} else {
				
				List cloumNameList = new ArrayList();// 存放文件头每列的名字
				cloumNameList.add("结论项目编码*");// //【1】
				cloumNameList.add("结论项目名称*"); // 【2】
				cloumNameList.add("所属指标"); // 【3】
				cloumNameList.add("人员类型"); // 【4】
				cloumNameList.add("参考范围上限"); // 【5】
				cloumNameList.add("参考范围下线"); // 【6】
				cloumNameList.add("参考范围指标"); // 【7】
				cloumNameList.add("单位控制指标"); // 【8】
				cloumNameList.add("疾病控制指标"); // 【9】
				cloumNameList.add("生效时间"); // 【10】
				cloumNameList.add("失效时间");//【11】
				cloumNameList.add("备注");//【12】
				//[结论项目编码*, 结论项目名称*, 所属指标, 人员类型, 参考范围上限, 参考范围下线, 单位控制指标, 疾病控制指标, 生效时间, 失效时间, 备注]
				for (int i = 0; i < cloumNameList.size(); i++) {
					if (!cloumNameList.get(i).equals(sheet.getCell(i, 0).getContents())) {
						throw new HygeiaException("文件对应列不符合模版标识内容,请重新下载模版并填写相关数据！" + cloumNameList.get(i));
					}
				}
			}
			String akb020 = BizHelper.getAkb020();
			String aaa027 = BizHelper.getAaa027();
			for (int i = 1; i < sheet.getRows(); i++) {
				Map map = new HashMap();
				String a = sheet.getCell(0, i).getContents();
				if (!sheet.getCell(0, i).getContents().equals("")) {
					
					map.put("bkh049", sheet.getCell(0, i).getContents().trim()); // i行第1列  
					map.put("bkh050", sheet.getCell(1, i).getContents().trim()); // i行第2列
					map.put("bkh054", sheet.getCell(2, i).getContents().trim()); // i行第3列
					String bka035 = sheet.getCell(3, i).getContents().replace(" ", ""); ;
					map.put("bka035", bka035.trim().substring(0, 1)); // i行第4列
					map.put("bkh051", sheet.getCell(4, i).getContents()); // i行第5列
					
					map.put("bkh052", sheet.getCell(5, i).getContents().trim()); // i行第6列
					map.put("bkh056", sheet.getCell(6, i).getContents().trim()); // i行第7列
					map.put("bkh057", sheet.getCell(7, i).getContents().trim()); // i行第8列
					map.put("bkh058", sheet.getCell(8, i).getContents().trim()); // i行第9列
					map.put("bkh103", sheet.getCell(9, i).getContents().trim()); // i行第10列
					map.put("bkh104", sheet.getCell(10, i).getContents().trim()); // i行第11列
					map.put("aae013", sheet.getCell(11, i).getContents().trim()); // i行第12列
					map.put("aaa027", aaa027);
					CharCodeSwitch charCode = new CharCodeSwitchImpl();
		            String aka020 = charCode.LoadPY(map.get("bkh050").toString());
		            String aka021 = charCode.LoadWB(map.get("bkh050").toString());
					map.put("aka021", aka021);
					map.put("aka020", aka020);
					map.put("bkh037", "00");
					map.put("bkh053", "0");
					
					map.put("bkh046", "");
					map.put("bkh055", "");
					map.put("bkh047", "1");
					map.put("akb020", akb020);

				}else {
					continue;
				}
				rsList.add(map);
			}

		} catch (Exception e) {
			throw new HygeiaException(e);
		}
		return rsList;
	}


}
