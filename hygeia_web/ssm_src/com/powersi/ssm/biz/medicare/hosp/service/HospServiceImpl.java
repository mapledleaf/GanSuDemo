package com.powersi.ssm.biz.medicare.hosp.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.powersi.biz.medicare.catalog.pojo.HospCataDTO;
import com.powersi.biz.medicare.diagnose.pojo.KF04DTO;
import com.powersi.biz.medicare.diagnose.pojo.Kf02DTO;
import com.powersi.biz.medicare.hosp.pojo.HospInfoDTO;
import com.powersi.biz.medicare.hosp.service.api.HospApiService;
import com.powersi.hygeia.comm.service.CharCodeSwitch;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.ssm.biz.medicare.common.service.CharCodeSwitchImpl;

import jxl.Sheet;
import jxl.Workbook;

/**
 * 医院维护接口实现类
 * 
 * @author Administrator
 *
 */
public class HospServiceImpl implements HospService {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List getExcelContextBed(File file, HospInfoDTO hospInfoDto) {
		List rsList = new ArrayList();
		try {

			Workbook workbook = Workbook.getWorkbook(file);
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
				cloumNameList.add("病床编号*"); // 【1】
				cloumNameList.add("病床房间*"); // 【2】
				cloumNameList.add("床位类型*"); // 【3】
				cloumNameList.add("病床单价*"); // 【4】

				for (int i = 0; i < cloumNameList.size(); i++) {
					if (!cloumNameList.get(i).equals(sheet.getCell(i, 0).getContents())) {
						throw new HygeiaException("文件对应列不符合模版标识内容,请重新下载模版并填写相关数据！" + cloumNameList.get(i));
					}
				}
			}
			for (int i = 1; i < sheet.getRows(); i++) {
				Map map = new HashMap();
				// if (!sheet.getCell(0, i).getContents().equals("")) {
				if (!UtilFunc.isEmpty(sheet.getCell(0, i).getContents())) {

					map.put("bkc161", sheet.getCell(0, i).getContents().trim()); // i行第1列
																					// 病床编号
					map.put("bkc162", sheet.getCell(1, i).getContents().trim()); // i行第2列
																					// 病床房间
					String id=sheet.getCell(2, i).getContents().trim().substring(0,1);
					if(StringUtils.isNumeric(id)){
						map.put("bkc163", sheet.getCell(2, i).getContents().trim().charAt(0)); // i行第3列
						map.put("bkc165", sheet.getCell(2, i).getContents().trim().substring(2)); // i行第3列
						// 床位类型
					}else{
						throw new HygeiaException("请下载最新床位模板！");
					}
																					// 床位类型
					map.put("bkc164", sheet.getCell(3, i).getContents().trim()); // i行第4列
																					// 病床单价
					map.put("bkc171", "0");

					map.put("aaa027", hospInfoDto.getAaa027().toString()); // 所属统筹区
					map.put("bkc156", hospInfoDto.getBkc156().toString()); // 科室编码
					map.put("bkc153", hospInfoDto.getBkc153().toString()); // 病区编码
					map.put("akb020", hospInfoDto.getAkb020().toString()); // 医院编码
					map.put("bkc027", hospInfoDto.getBkc027().toString()); // 维护人
					map.put("bkc028", hospInfoDto.getBkc028().toString()); // 维护人工号
					map.put("bkc029", DateFunc.datetimeToString(new Date())); // 维护时间
					map.put("aae100", "1"); // 有效期
					map.put("aae016", "1"); // 审核标示

					if (map.get("bkc161") == null || map.get("bkc161").equals("")) {
						continue;
					}
					if (map.get("bkc162") == null || map.get("bkc162").equals("")) {
						continue;
					}
					if (map.get("bkc163") == null || map.get("bkc163").equals("")) {
						continue;
					}
					if (map.get("bkc164") == null || map.get("bkc164").equals("")) {
						continue;
					}
					rsList.add(map);
				}
			}

		} catch (Exception e) {
			throw new HygeiaException(e);
		}
		return rsList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Kf02DTO> getExcelContextBatchCharge(File file, String upFileName, String akb020, String uuid) {
		List<Kf02DTO> rsList = new ArrayList<Kf02DTO>();
		try {

			Workbook workbook = Workbook.getWorkbook(file);
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
				cloumNameList.add("序号*"); // 【1】
				cloumNameList.add("卫生站编号*"); // 【2】
				cloumNameList.add("身份证号*"); // 【3】
				cloumNameList.add("姓名*"); // 【4】
				cloumNameList.add("就诊日期"); // 【5】
				cloumNameList.add("总费用"); // 【6】
				cloumNameList.add("甲类费用"); // 【7】
				cloumNameList.add("丙类费用"); // 【8】
				cloumNameList.add("统筹支付金额"); // 【9】

				for (int i = 0; i < cloumNameList.size(); i++) {
					if (!cloumNameList.get(i).equals(sheet.getCell(i, 0).getContents())) {
						System.out.println("sheet.getCell(i, 0).getContents():" + sheet.getCell(i, 0).getContents());
						throw new HygeiaException("文件对应列不符合模版标识内容,请重新下载模版并填写相关数据！" + cloumNameList.get(i));
					}
				}
			}

			for (int i = 1; i < sheet.getRows(); i++) {
				Kf02DTO map = new Kf02DTO();
				if (sheet.getCell(1, i).getContents() != null && !sheet.getCell(1, i).getContents().equals("")) {
					if (!sheet.getCell(1, i).getContents().trim().equals(akb020)) {
						throw new HygeiaException("【卫生站编号】" + akb020 + "与所属医院编码"
								+ sheet.getCell(1, i).getContents().trim() + "不一致,请重新填写相关数据！");
					}
					String aac002 = sheet.getCell(2, i).getContents().trim();
					if (StringUtils.isBlank(aac002) || aac002.equals("")) {
						throw new HygeiaException((i + 1) + "行，【身份证号】不许为空！");
					}
					String aac003 = sheet.getCell(3, i).getContents().trim();
					if (StringUtils.isBlank(aac003) || aac003.equals("")) {
						throw new HygeiaException((i + 1) + "行，【姓名】不许为空！");
					}
					String bke036 = sheet.getCell(4, i).getContents().trim();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					try {
						// 设置lenient为false.
						// 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
						format.setLenient(false);
						format.parse(bke036);
					} catch (Throwable e) {
						throw new HygeiaException((i + 1) + "行，【就诊日期】格式不正确(yyyy-MM-dd)！", e);

					}
					Pattern pattern = Pattern.compile("([1-9]\\d*|0)(\\.\\d{1,2})?");
					String bke030 = sheet.getCell(5, i).getContents().trim();
					if (!bke030.equals("")) {
						Matcher isNumBke030 = pattern.matcher(bke030);
						if (!isNumBke030.matches()) {
							throw new HygeiaException((i + 1) + "行，【总费用】请输入合法的金额数值！");
						}
					}

					String bke034 = sheet.getCell(6, i).getContents().trim();
					if (!bke034.equals("")) {
						Matcher isNumBke034 = pattern.matcher(bke034);
						if (!isNumBke034.matches()) {
							throw new HygeiaException((i + 1) + "行，【甲类费用】请输入合法的金额数值！");
						}
					}
					String bke035 = sheet.getCell(7, i).getContents().trim();
					if (!bke035.equals("")) {
						Matcher isNumBke035 = pattern.matcher(bke035);
						if (!isNumBke035.matches()) {
							throw new HygeiaException((i + 1) + "行，【丙类费用】请输入合法的金额数值！");
						}
					}
					String bke039 = sheet.getCell(8, i).getContents().trim();
					if (!bke039.equals("")) {
						Matcher isNumBke039 = pattern.matcher(bke039);
						if (!isNumBke039.matches()) {
							throw new HygeiaException((i + 1) + "行，【统筹支付金额】请输入合法的金额数值！");
						}
					}
					/*
					 * SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSSSS"); Date
					 * date = new Date();
					 */
					UUID kf02Uid = UUID.randomUUID();
					map.setKf01id(uuid);
					map.setKf02id(kf02Uid.toString());
					map.setAaz217(akb020 + uuid);
					map.setAkb020(akb020); // i行第2列
					map.setAac002(aac002);// i行第3列
					map.setAac003(aac003);// i行第4列
					map.setBke036(bke036);// i行第5列
					map.setBke030(bke030.equals("") ? "0" : bke030);// i行第6列
					map.setBke034(bke034.equals("") ? "0" : bke034);// i行第7列
					map.setBke035(bke035.equals("") ? "0" : bke035);// i行第8列
					map.setAke039(bke039.equals("") ? "0" : bke039);// i行第9列
					map.setBke037("0");
					rsList.add(map);

				}

			}
			if (!UtilFunc.isEmpty(rsList) && rsList.size() > 200) {
				throw new HygeiaException("导入数据不能超过200条！");
			}

		} catch (Exception e) {
			throw new HygeiaException(e);
		}
		return rsList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List getExcelContextDoctor(File file, HospInfoDTO hospInfoDto) {
		List rsList = new ArrayList();
		try {

			Workbook workbook = Workbook.getWorkbook(file);
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
				cloumNameList.add("医师姓名*"); // 【1】
				cloumNameList.add("编号*"); // 【2】
				cloumNameList.add("医师性别*"); // 【3】
				cloumNameList.add("医师执业情况"); // 【4】
				cloumNameList.add("移动电话号码"); // 【5】
				cloumNameList.add("医师职称"); // 【6】
				cloumNameList.add("医师生日"); // 【7】
				cloumNameList.add("身份证号码"); // 【8】
				cloumNameList.add("专业"); // 【9】
				cloumNameList.add("执业医师资格证编号"); // 【13】

				for (int i = 0; i < cloumNameList.size(); i++) {
					if (!cloumNameList.get(i).equals(sheet.getCell(i, 0).getContents())) {
						throw new HygeiaException("文件对应列不符合模版标识内容,请重新下载模版并填写相关数据！" + cloumNameList.get(i));
					}
				}
			}
			for (int i = 1; i < sheet.getRows(); i++) {
				Map map = new HashMap();
				// if (!sheet.getCell(0, i).getContents().equals("")) {
				if (!UtilFunc.isEmpty(sheet.getCell(0, i).getContents())) {

					map.put("bkc275", sheet.getCell(0, i).getContents().trim()); // i行第1列
																					// 医师姓名
					map.put("bkc274", sheet.getCell(1, i).getContents().trim()); // i行第2列
																					// 编号
					map.put("bkc277", sheet.getCell(2, i).getContents().trim()); // i行第3列
																					// 医师性别
					map.put("bkc276", sheet.getCell(3, i).getContents().trim()); // i行第4列
																					// 医师执业情况
					map.put("bkc280", sheet.getCell(4, i).getContents().trim()); // i行第5列
																					// 移动电话号码
					map.put("bkc272", sheet.getCell(5, i).getContents().trim()); // i行第6列
																					// 医师职称
					map.put("bkc278", sheet.getCell(6, i).getContents().trim()); // i行第7列
																					// 医师生日
					map.put("bkc279", sheet.getCell(7, i).getContents().trim()); // i行第8列
																					// 身份证号码
					map.put("bkc281", sheet.getCell(8, i).getContents().trim()); // i行第9列
																					// 专业
					map.put("bke955", sheet.getCell(9, i).getContents().trim()); // i行第13列
																					// 执业医师资格证编号

					map.put("aaa027", hospInfoDto.getAaa027().toString()); // 所属统筹区
					map.put("bkc156", hospInfoDto.getBkc156().toString()); // 科室编码
					map.put("akb020", hospInfoDto.getAkb020().toString()); // 医院编码
					map.put("bkc027", hospInfoDto.getBkc027().toString()); // 维护人
					map.put("bkc028", hospInfoDto.getBkc028().toString()); // 维护人工号
					map.put("bkc029", DateFunc.datetimeToString(new Date())); // 维护时间
					map.put("aae100", "1"); // 有效期
					map.put("aae016", "0"); // 审核标示
					CharCodeSwitch charCode = new CharCodeSwitchImpl();
					String aka020 = charCode.LoadPY(map.get("bkc275").toString());
					String aka021 = charCode.LoadWB(map.get("bkc275").toString());
					map.put("aka020", aka020);
					map.put("aka021", aka021);
					// 写入主键
					UUID uuid = UUID.randomUUID();
					map.put("bkc269", uuid.toString());

					if (map.get("bkc275") == null || map.get("bkc275").equals("")) {
						continue;
					}
					if (map.get("bkc274") == null || map.get("bkc274").equals("")) {
						continue;
					}
					if (map.get("bkc277") == null || map.get("bkc277").equals("")) {
						continue;
					}

					rsList.add(map);
				}
			}

		} catch (Exception e) {
			throw new HygeiaException(e);
		}
		return rsList;
	}

	@Override
	public int checkExist(String akb020, String id, String type, HospApiService hospService) {
		int iRet = 0;
		HospInfoDTO hospInfoDto = new HospInfoDTO();
		if (type.equals("area")) {
			hospInfoDto.setAkb020(akb020);
			hospInfoDto.setBkc153(id);
			hospInfoDto.setAae100("1");
			iRet = hospService.queryHospArea(akb020, hospInfoDto).size();

		} else if (type.equals("dept")) {
			hospInfoDto.setAkb020(akb020);
			hospInfoDto.setBkc156(id);
			hospInfoDto.setAae100("1");
			iRet = hospService.queryHospDept(akb020, hospInfoDto).size();
		} else if (type.equals("bed")) {
			hospInfoDto.setAkb020(akb020);
			hospInfoDto.setBkc161(id);
			hospInfoDto.setAae100("1");
			iRet = hospService.queryHospBed(akb020, hospInfoDto).size();
		} else if (type.equals("doctor")) {
			hospInfoDto.setAkb020(akb020);
			hospInfoDto.setBkc274(id);
			hospInfoDto.setAae100("1");
			iRet = hospService.queryHospDoctor(akb020, hospInfoDto).size();
		}
		return iRet;
	}

	@Override
	public int checkExistByHosp(HospInfoDTO hospInfoDto, String type, HospApiService hospService) {
		int iRet = 0;
		if (type.equals("area")) {
			hospInfoDto.setAae100("1");
			iRet = hospService.queryHospArea(hospInfoDto.getAkb020(), hospInfoDto).size();
		} else if (type.equals("dept")) {
			hospInfoDto.setAae100("1");
			iRet = hospService.queryHospDept(hospInfoDto.getAkb020(), hospInfoDto).size();
		} else if (type.equals("bed")) {
			hospInfoDto.setAae100("1");
			iRet = hospService.queryHospBed(hospInfoDto.getAkb020(), hospInfoDto).size();
		} else if (type.equals("doctor")) {
			hospInfoDto.setAae100("1");
			iRet = hospService.queryHospDoctor(hospInfoDto.getAkb020(), hospInfoDto).size();
		}
		return iRet;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getExcelContextCata(File cata_img, HospCataDTO hospCataDto) {
		List rsList = new ArrayList();

		if ("cata".equals(hospCataDto.getType())) {
			rsList = getExcelByCata(cata_img, hospCataDto);
		} else if ("item".equals(hospCataDto.getType())) {
			rsList = getExcelByItem(cata_img, hospCataDto);
		} else {
			throw new HygeiaException("导入的类型不正确!");
		}
		return rsList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List getExcelByCata(File cata_img, HospCataDTO hospCataDto) {
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
				cloumNameList.add("目录编码*");// //【1】
				cloumNameList.add("目录名称*"); // 【2】
				cloumNameList.add("目录类别*"); // 【3】
				cloumNameList.add("单价*"); // 【4】
				cloumNameList.add("医院剂型*"); // 【5】
				cloumNameList.add("规格型号*"); // 【6】
				cloumNameList.add("批准文号*"); // 【7】
				cloumNameList.add("批准日期"); // 【8】
				cloumNameList.add("药监本位码"); // 【9】
				cloumNameList.add("生产单位"); // 【10】
				cloumNameList.add("英文名称"); // 【11】
				cloumNameList.add("商品名"); // 【12】
				cloumNameList.add("别名"); // 【13】
				cloumNameList.add("包装价格"); // 【14】
				cloumNameList.add("包装规格"); // 【15】
				cloumNameList.add("备注1"); // 【16】
				cloumNameList.add("备注2"); // 【17】
				cloumNameList.add("备注3"); // 【18】

				for (int i = 0; i < cloumNameList.size(); i++) {
					if (!cloumNameList.get(i).equals(sheet.getCell(i, 0).getContents())) {
						throw new HygeiaException("文件对应列不符合模版标识内容,请重新下载模版并填写相关数据！" + cloumNameList.get(i));
					}
				}
			}
			for (int i = 1; i < sheet.getRows(); i++) {
				Map map = new HashMap();
				// if (!sheet.getCell(0, i).getContents().equals("")) {
				if (!UtilFunc.isEmpty(sheet.getCell(0, i).getContents())) {

					map.put("ake005", sheet.getCell(0, i).getContents().trim()); // i行第1列
					map.put("ake006", sheet.getCell(1, i).getContents().trim()); // i行第2列
					map.put("ake003", sheet.getCell(2, i).getContents().trim()); // i行第3列
					map.put("bkc140", sheet.getCell(3, i).getContents().trim()); // i行第4列
					map.put("bkc138", sheet.getCell(4, i).getContents().trim()); // i行第5列
					map.put("bkc139", sheet.getCell(5, i).getContents().trim()); // i行第6列
					map.put("bkm007", sheet.getCell(6, i).getContents().trim()); // i行第7列
					String bkm009 = this.strReplaceAll(sheet.getCell(7, i).getContents().trim());
					map.put("bkm009", bkm009); // i行第8列 批准日期
					map.put("bkm017", sheet.getCell(8, i).getContents().trim()); // i行第9列
					map.put("bkc141", sheet.getCell(9, i).getContents().trim()); // i行第10列
					map.put("aka062", sheet.getCell(10, i).getContents().trim()); // i行第11列
					map.put("bkm022", sheet.getCell(11, i).getContents().trim()); // i行第12列
					map.put("bkm021", sheet.getCell(12, i).getContents().trim()); // i行第13列
					map.put("bkm014", sheet.getCell(13, i).getContents().trim()); // i行第14列
					map.put("bkm020", sheet.getCell(14, i).getContents().trim()); // i行第15列
					map.put("bkm025", sheet.getCell(15, i).getContents().trim()); // i行第16列
					map.put("bkm026", sheet.getCell(16, i).getContents().trim()); // i行第17列
					map.put("bkm027", sheet.getCell(17, i).getContents().trim()); // i行第18列

					CharCodeSwitch charCode = new CharCodeSwitchImpl();
					String aka020 = charCode.LoadPY(map.get("ake006").toString());
					String aka021 = charCode.LoadWB(map.get("ake006").toString());
					map.put("aka020", aka020);
					map.put("aka021", aka021);

					rsList.add(map);
				}
			}

		} catch (Exception e) {
			throw new HygeiaException(e);
		}
		return rsList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List getExcelByItem(File cata_img, HospCataDTO hospCataDto) {
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
				cloumNameList.add("目录编码*");// //【1】
				cloumNameList.add("目录名称*"); // 【2】
				cloumNameList.add("目录类别*"); // 【3】
				cloumNameList.add("单价*"); // 【4】
				cloumNameList.add("生产单位"); // 【5】
				cloumNameList.add("包装价格"); // 【6】
				cloumNameList.add("包装规格"); // 【7】
				cloumNameList.add("备注1"); // 【8】
				cloumNameList.add("备注2"); // 【9】
				cloumNameList.add("备注3"); // 【10】

				for (int i = 0; i < cloumNameList.size(); i++) {
					if (!cloumNameList.get(i).equals(sheet.getCell(i, 0).getContents())) {
						throw new HygeiaException("文件对应列不符合模版标识内容,请重新下载模版并填写相关数据！" + cloumNameList.get(i));
					}
				}
			}
			for (int i = 1; i < sheet.getRows(); i++) {
				Map map = new HashMap();
				// if (!sheet.getCell(0, i).getContents().equals("")) {
				if (!UtilFunc.isEmpty(sheet.getCell(0, i).getContents())) {

					map.put("ake005", sheet.getCell(0, i).getContents().trim()); // i行第1列
					map.put("ake006", sheet.getCell(1, i).getContents().trim()); // i行第2列
					map.put("ake003", sheet.getCell(2, i).getContents().trim()); // i行第3列
					map.put("bkc140", sheet.getCell(3, i).getContents().trim()); // i行第4列
					map.put("bkc141", sheet.getCell(4, i).getContents().trim()); // i行第5列
					map.put("bkm014", sheet.getCell(5, i).getContents().trim()); // i行第6列
					map.put("bkm020", sheet.getCell(6, i).getContents().trim()); // i行第7列
					map.put("bkm025", sheet.getCell(7, i).getContents().trim()); // i行第8列
					map.put("bkm026", sheet.getCell(8, i).getContents().trim()); // i行第9列
					map.put("bkm027", sheet.getCell(9, i).getContents().trim()); // i行第10列

					CharCodeSwitch charCode = new CharCodeSwitchImpl();
					String aka020 = charCode.LoadPY(map.get("ake006").toString());
					String aka021 = charCode.LoadWB(map.get("ake006").toString());
					map.put("aka020", aka020);
					map.put("aka021", aka021);

					rsList.add(map);
				}
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
	public String strReplaceAll(String str) {

		if (str.trim().indexOf("-") > 0) {
			str = str.replaceAll("-", "");
		}
		if (str.trim().indexOf("/") > 0) {
			str = str.replaceAll("/", "");
		}
		if (str.trim().length() > 8) {
			str = str.substring(0, 8).trim();
		}

		return str;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List getExcelInFee(File imgFile, HospInfoDTO hospInfoDto) {
		List rsList = new ArrayList();
		try {

			Workbook workbook = Workbook.getWorkbook(imgFile);
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
				cloumNameList.add("费用序列号*");// //【1】
				cloumNameList.add("医院药品项目编码*"); // 【2】
				cloumNameList.add("医院药品项目名称*"); // 【3】
				cloumNameList.add("费用发生日期(yyyy/mm/dd)*"); // 【4】
				cloumNameList.add("录入人工号*"); // 【5】
				cloumNameList.add("录入人姓名*"); // 【6】
				cloumNameList.add("金额(负数时为退费)*"); // 【7】
				cloumNameList.add("单价*"); // 【8】
				cloumNameList.add("用量*"); // 【9】
				cloumNameList.add("处方号*"); // 【10】
				cloumNameList.add("剂型"); // 【11】
				cloumNameList.add("厂家"); // 【12】
				cloumNameList.add("规格"); // 【13】
				cloumNameList.add("计量单位"); // 【14】
				cloumNameList.add("处方医生编号"); // 【15】
				cloumNameList.add("处方医生姓名"); // 【16】

				for (int i = 0; i < cloumNameList.size(); i++) {
					if (!cloumNameList.get(i).equals(sheet.getCell(i, 0).getContents())) {
						throw new HygeiaException("文件对应列不符合模版标识内容,请重新下载模版并填写相关数据！" + cloumNameList.get(i));
					}
				}
			}
			for (int i = 1; i < sheet.getRows(); i++) {
				Map map = new HashMap();
				// if (!sheet.getCell(0, i).getContents().equals("")) {
				if (!UtilFunc.isEmpty(sheet.getCell(0, i).getContents())) {
					map.put("aaz213", sheet.getCell(0, i).getContents().trim()); // i行第1列
					map.put("ake005", sheet.getCell(1, i).getContents().trim()); // i行第2列
					map.put("ake006", sheet.getCell(2, i).getContents().trim()); // i行第3列
					String bka051 = this.strReplaceAll(sheet.getCell(3, i).getContents().trim());
					map.put("bka051", bka051); // i行第4列 开始时间
					map.put("bka063", sheet.getCell(4, i).getContents().trim());
					map.put("bka064", sheet.getCell(5, i).getContents().trim()); // i行第6列
					map.put("bka058", sheet.getCell(6, i).getContents().trim()); // i行第7列
					map.put("bka056", sheet.getCell(7, i).getContents().trim()); // i行第8列
					map.put("bka057", sheet.getCell(8, i).getContents().trim()); // i行第9列
					map.put("bka070", sheet.getCell(9, i).getContents().trim()); // i行第10列
					map.put("bka052", sheet.getCell(10, i).getContents().trim()); // i行第11列
					map.put("bka053", sheet.getCell(11, i).getContents().trim()); // i行第12列
					map.put("bka054", sheet.getCell(12, i).getContents().trim());
					map.put("bka055", sheet.getCell(13, i).getContents().trim()); // i行第14列
					map.put("bka074", sheet.getCell(14, i).getContents().trim()); // i行第15列
					map.put("bka075", sheet.getCell(15, i).getContents().trim()); // i行第15列

					rsList.add(map);
				}
			}

		} catch (Exception e) {
			throw new HygeiaException(e);
		}
		return rsList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Map<String, Object>> checkDoctroList(List<Map<String, Object>> detail,String bkc156,List<HospInfoDTO> deptInfo) {
		CharCodeSwitch charCode = new CharCodeSwitchImpl();
		List<Map<String, Object>> lDoctor = new ArrayList<Map<String, Object>>();
		for(int i =0;i < detail.size();i++){
			Map mDoctor = detail.get(i);
			//mDoctor.put("bkc156", bkc156);
            //性别
			if (!mDoctor.containsKey("bkc277") || mDoctor.get("bkc277") == null ||
					mDoctor.get("bkc277").equals("")) {
				continue;
		     }else{
		    	 String bkc277 = charCode.LoadCodeTable("sex", mDoctor.get("bkc277").toString());
		    	 mDoctor.remove("bkc277");
		    	 mDoctor.put("bkc277", bkc277);
		     }
			//执业情况
			if (!mDoctor.containsKey("bkc276") || mDoctor.get("bkc276") == null ||
					mDoctor.get("bkc276").equals("")) {
			}else{
				String bkc276 = charCode.LoadCodeTable("bkc276", mDoctor.get("bkc276").toString());
				mDoctor.remove("bkc276");
				mDoctor.put("bkc276", bkc276);
			}
			//医师职称
			if (!mDoctor.containsKey("bkc272") || mDoctor.get("bkc272") == null ||
					mDoctor.get("bkc272").equals("")) {
			}else{
				String bkc272 = charCode.LoadCodeTable("bkc272", mDoctor.get("bkc272").toString());
				mDoctor.remove("bkc272");
				mDoctor.put("bkc272", bkc272);
			}
			//新增可维护到全部科室
			if("all".equals(bkc156)) {
				for (HospInfoDTO deptRow : deptInfo) {
					   Map temp=new HashMap<String, Object>();
					   temp.clear();
					   temp.putAll(mDoctor);
					   temp.put("bkc156", deptRow.getBkc156());
					   temp.put("bkc157", deptRow.getBkc157());
						//写入主键
						//UUID uuid = UUID.randomUUID();
					   temp.put("bkc269", UUID.randomUUID().toString());
					   lDoctor.add(temp);
				   }
			}else {
				//写入主键
				UUID uuid = UUID.randomUUID();
				mDoctor.put("bkc269", uuid.toString());
				mDoctor.put("bkc156", bkc156);
				lDoctor.add(mDoctor);
			}
		}
		return lDoctor;
	}

	/**
	 * @author lhy 珠海目录匹配数据批量导入需求 20171123
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<KF04DTO> getExcelContextCatalogMatch(File file, String upFileName, String akb020, String uuid) {
		List<KF04DTO> rsList = new ArrayList<KF04DTO>();
		try {
			Workbook workbook = Workbook.getWorkbook(file);
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
				cloumNameList.add("医疗服务机构编号"); // 【1】
				cloumNameList.add("三大目录类别"); // 【2】
				cloumNameList.add("医院收费项目编码"); // 【3】
				cloumNameList.add("医院收费项目名称"); // 【4】
				cloumNameList.add("生效开始时间"); // 【5】
				cloumNameList.add("生效结束时间"); // 【6】
				cloumNameList.add("医院目录剂型,录入中文名称"); // 【7】
				cloumNameList.add("医院目录规格"); // 【8】
				cloumNameList.add("医院目录单价"); // 【9】
				cloumNameList.add("医院目录厂家"); // 【10】
				cloumNameList.add("医院药监本位码"); // 【11】
				cloumNameList.add("产地（医院信息）"); // 【12】
				cloumNameList.add("医院包装规格"); // 【13】
				cloumNameList.add("批准文号/注册号（医院信息）"); // 【14】
				cloumNameList.add("医保识别码"); // 【15】

				for (int i = 0; i < cloumNameList.size(); i++) {
					if (!cloumNameList.get(i).equals(sheet.getCell(i, 0).getContents())) {
						System.out.println("sheet.getCell(i, 0).getContents():" + sheet.getCell(i, 0).getContents());
						throw new HygeiaException("文件对应列不符合模版标识内容,请重新下载模版并填写相关数据！" + cloumNameList.get(i));
					}
				}
			}

			for (int i = 1; i < sheet.getRows(); i++) {
				KF04DTO kf04DTO = new KF04DTO();
				if (sheet.getCell(0, i).getContents() != null && !sheet.getCell(0, i).getContents().equals("")) {
					if (!sheet.getCell(0, i).getContents().trim().equals(akb020)) {
						throw new HygeiaException("【医疗服务机构编号】" + akb020 + "与所属医院编码"
								+ sheet.getCell(0, i).getContents().trim() + "不一致,请重新填写相关数据！");
					}
					String ake003 = sheet.getCell(1, i).getContents().trim();
					if (StringUtils.isBlank(ake003) || ake003.equals("")) {
						throw new HygeiaException((i + 1) + "行，【三大目录类别】不允许为空！");
					}
					if (!StringUtils.isNumeric(ake003)) {
						throw new HygeiaException((i + 1) + "行，【三大目录类别】请输入数字：1-5！");
					}
					String ake005 = sheet.getCell(2, i).getContents().trim();
					if (StringUtils.isBlank(ake005) || ake005.equals("")) {
						throw new HygeiaException((i + 1) + "行，【医院收费项目编码】不允许为空！");
					}
					String ake006 = sheet.getCell(3, i).getContents().trim();
					if (StringUtils.isBlank(ake006) || ake006.equals("")) {
						throw new HygeiaException((i + 1) + "行，【医院收费项目名称】不允许为空！");
					}
					String aae030 = sheet.getCell(4, i).getContents().trim();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					try {
						// 设置lenient为false.
						// 否则SimpleDateFormat会比较宽松地验证日期，比如2007-02-29会被接受，并转换成2007-03-01
						format.setLenient(false);
						format.parse(aae030);
					} catch (Throwable e) {
						throw new HygeiaException((i + 1) + "行，【生效开始时间】格式不正确(yyyy-MM-dd)！", e);

					}
					String aae031 = sheet.getCell(5, i).getContents().trim();
					try {
						// 设置lenient为false.
						// 否则SimpleDateFormat会比较宽松地验证日期，比如2007-02-29会被接受，并转换成2007-03-01
						format.setLenient(false);
						format.parse(aae031);
					} catch (Throwable e) {
						throw new HygeiaException((i + 1) + "行，【生效结束时间】格式不正确(yyyy-MM-dd)！", e);

					}
					String bkc138 = sheet.getCell(6, i).getContents().trim();
					if (StringUtils.isBlank(bkc138) || bkc138.equals("")) {
						throw new HygeiaException((i + 1) + "行，【医院目录剂型】不允许为空！");
					}
					String bkc139 = sheet.getCell(7, i).getContents().trim();
					if (StringUtils.isBlank(bkc139) || bkc139.equals("")) {
						throw new HygeiaException((i + 1) + "行，【医院目录规格】不允许为空！");
					}
					Pattern pattern = Pattern.compile("([1-9]\\d*|0)(\\.\\d{1,2})?");
					String bkc140 = sheet.getCell(8, i).getContents().trim();
					if (!bkc140.equals("")) {
						Matcher isNumBke030 = pattern.matcher(bkc140);
						if (!isNumBke030.matches()) {
							throw new HygeiaException((i + 1) + "行，【医院目录单价】请输入合法的金额数值！");
						}
					}
					String bkc141 = sheet.getCell(9, i).getContents().trim();
					String bkm017 = sheet.getCell(10, i).getContents().trim();
					if ("1".equals(ake003) || "4".equals(ake003)) {
						if (StringUtils.isBlank(bkm017) || bkm017.equals("")) {
							throw new HygeiaException((i + 1) + "行，【医院药监本位码】不允许为空！");
						}
					}
					String bkm019 = sheet.getCell(11, i).getContents().trim();
					String bkm020 = sheet.getCell(12, i).getContents().trim();
					if (StringUtils.isBlank(bkm020) || bkm020.equals("")) {
						throw new HygeiaException((i + 1) + "行，【医院包装规格】不允许为空！");
					}
					String bkm024 = sheet.getCell(13, i).getContents().trim();
					String bkm029 = sheet.getCell(14, i).getContents().trim();
					if (StringUtils.isBlank(bkm029) || bkm029.equals("")) {
						throw new HygeiaException((i + 1) + "行，【医保识别码】不允许为空！");
					}

					UUID kf04Uid = UUID.randomUUID();
					kf04DTO.setKf04id(kf04Uid.toString());
					kf04DTO.setKf03id(uuid);
					kf04DTO.setAkb020(akb020); // i行第1列
					kf04DTO.setAke003(ake003); // i行第2列
					kf04DTO.setAke005(ake005);// i行第3列
					kf04DTO.setAke006(ake006);// i行第4列
					kf04DTO.setAae030(aae030);// i行第5列
					kf04DTO.setAae031(aae031);// i行第6列
					kf04DTO.setBkc138(bkc138);// i行第7列
					kf04DTO.setBkc139(bkc139);// i行第8列
					kf04DTO.setBkc140(Double.valueOf(bkc140));// i行第9列
					kf04DTO.setBkc141(bkc141); // i行第10列
					kf04DTO.setBkm017(bkm017); // i行第11列
					kf04DTO.setBkm019(bkm019); // i行第12列
					kf04DTO.setBkm020(bkm020);// i行第13列
					kf04DTO.setBkm024(bkm024);// i行第14列
					kf04DTO.setBkm029(bkm029);// i行第15列
					kf04DTO.setBke037("0");
					rsList.add(kf04DTO);
				}
			}
			/*
			 * if(!UtilFunc.isEmpty(rsList)&&rsList.size()>200){ throw new
			 * HygeiaException("导入数据不能超过200条！"); }
			 */
		} catch (Exception e) {
			throw new HygeiaException(e);
		}
		return rsList;
	}

}
