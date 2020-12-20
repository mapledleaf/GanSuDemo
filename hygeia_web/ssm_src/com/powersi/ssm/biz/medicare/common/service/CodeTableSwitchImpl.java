package com.powersi.ssm.biz.medicare.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.powersi.biz.medicare.catalog.pojo.HospCataDTO;
import com.powersi.hygeia.framework.CodetableMapping;

/**
 * 代码表缓存服务.
 */
public class CodeTableSwitchImpl implements CodeTableSwitch {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Map> loadSpecialZHDtoInfo(List<Map> lParam, String aaa027) {
		List<Map> retList = new ArrayList<Map>();
		Map mParam = null;
		for (int i = 0; i < lParam.size(); i++) {
			mParam = lParam.get(i);
			if (mParam.containsKey("aae140") && mParam.get("aae140") != null && !mParam.get("aae140").equals("")) {
				String aae140 = mParam.get("aae140").toString();
				mParam.put("aae140_name", CodetableMapping.getDisplayValue("aae140", aae140, aae140));
			}
			if (mParam.containsKey("bka006") && mParam.get("bka006") != null && !mParam.get("bka006").equals("")) {
				String bka006 = mParam.get("bka006").toString();
				mParam.put("bka006_name", CodetableMapping.getDisplayValue("bka006$" + aaa027, bka006, bka006));
			}
			if (mParam.containsKey("aaa001") && mParam.get("aaa001") != null && !mParam.get("aaa001").equals("")) {
				String aaa000 = mParam.get("aaa001").toString();
				if (mParam.containsKey("aaa002") && mParam.get("aaa002") != null && !mParam.get("aaa002").equals("")) {
					aaa000 += "、" + mParam.get("aaa002").toString();
					if (mParam.containsKey("aaa003") && mParam.get("aaa003") != null
							&& !mParam.get("aaa003").equals("")) {
						aaa000 += "、" + mParam.get("aaa003").toString();
					}
				}
				mParam.put("aaa000", aaa000);
			}
			mParam.put("numb", i + 1);// 存放序号
			retList.add(mParam);
		}
		return retList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map loadPersonInfo(Map mParam, String aaa027) {
		if (mParam.containsKey("aac004") && mParam.get("aac004") != null && !mParam.get("aac004").equals("")) {
			String aac004 = mParam.get("aac004").toString();
			mParam.put("aac004_name", CodetableMapping.getDisplayValue("aac004", aac004, aac004));
		}
		if (mParam.containsKey("bka004") && mParam.get("bka004") != null && !mParam.get("bka004").equals("")) {
			String bka004 = mParam.get("bka004").toString();
			mParam.put("bka004_name", CodetableMapping.getDisplayValue("bka004", bka004, bka004));
		}
		if (mParam.containsKey("aae140") && mParam.get("aae140") != null && !mParam.get("aae140").equals("")) {
			String aae140 = mParam.get("aae140").toString();
			mParam.put("aae140_name", CodetableMapping.getDisplayValue("aae140", aae140, aae140));
		}
		return mParam;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map loadPerson(List<Map> lParam, String aaa027) {
		List<Map> retList = new ArrayList<Map>();
		Map mReturn = new HashMap();
		for (int i = 0; i < lParam.size(); i++) {
			Map mParam = (Map) lParam.get(i);
			if (mParam.containsKey("aac004") && mParam.get("aac004") != null && !mParam.get("aac004").equals("")) {
				String aac004 = mParam.get("aac004").toString();
				mParam.put("aac004_name", CodetableMapping.getDisplayValue("aac004", aac004, aac004));
			}
			if (mParam.containsKey("bka004") && mParam.get("bka004") != null && !mParam.get("bka004").equals("")) {
				String bka004 = mParam.get("bka004").toString();
				mParam.put("bka004_name", CodetableMapping.getDisplayValue("bka004", bka004, bka004));
			}
			retList.add(mParam);
		}
		mReturn.put("personinfo", retList);
		return mReturn;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List loadDeclInfo(List lParam, String aaa027) {
		List retList = new ArrayList();
		Map mParam = null;
		for (int i = 0; i < lParam.size(); i++) {
			mParam = (Map) lParam.get(i);
			if (mParam.containsKey("aae140") && mParam.get("aae140") != null && !mParam.get("aae140").equals("")) {
				String insur_no = mParam.get("aae140").toString();
				mParam.put("aae140_name", CodetableMapping.getDisplayValue("sblx", insur_no, insur_no));

			}
			if (mParam.containsKey("bka109") && mParam.get("bka109") != null && !mParam.get("bka109").equals("")) {
				String deal_phase = mParam.get("bka109").toString();
				mParam.put("bka109_name", CodetableMapping.getDisplayValue("bka109", deal_phase, deal_phase));

			}
			if (mParam.containsKey("deal_phase") && mParam.get("deal_phase") != null
					&& !mParam.get("deal_phase").equals("")) {
				String deal_phase = mParam.get("deal_phase").toString();
				mParam.put("bka109_name", CodetableMapping.getDisplayValue("bka109", deal_phase, deal_phase));

			}
			if (mParam.containsKey("decl_type") && mParam.get("decl_type") != null
					&& !mParam.get("decl_type").equals("")) {
				String decl_type = mParam.get("decl_type").toString();
				mParam.remove("decl_type");
				mParam.put("decl_type", CodetableMapping.getDisplayValue("sblx", decl_type, decl_type));

			}
			retList.add(mParam);
		}
		return retList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List loadSelfPay(List lParam, String aaa027) {
		List retList = new ArrayList();
		Map mParam = null;
		for (int i = 0; i < lParam.size(); i++) {
			mParam = (Map) lParam.get(i);
			if (mParam.containsKey("aac004") && mParam.get("aac004") != null && !mParam.get("aac004").equals("")) {
				String aac004 = mParam.get("aac004").toString();
				mParam.remove("aac004");
				mParam.put("aac004", CodetableMapping.getDisplayValue("aac004", aac004, aac004));
			}
			if (mParam.containsKey("aae016") && mParam.get("aae016") != null && !mParam.get("aae016").equals("")) {
				String aae016 = mParam.get("aae016").toString();
				mParam.remove("aae016");
				mParam.put("aae016", CodetableMapping.getDisplayValue("aae016_audit", aae016, aae016));
			}
			retList.add(mParam);
		}
		return retList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List loadCataInfo(List lParam, String aaa027) {
		List retList = new ArrayList();
		HospCataDTO mParam = null;
		for (int i = 0; i < lParam.size(); i++) {
			mParam = (HospCataDTO) lParam.get(i);
			if (StringUtils.isNotBlank(mParam.getBka006())) {
				mParam.setBka006(
						CodetableMapping.getDisplayValue("bka006$" + aaa027, mParam.getBka006(), mParam.getBka006()));
			}
			retList.add(mParam);
		}
		return retList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Map> loadSpecialInfo(List<Map> lParam, String aaa027) {
		List<Map> retList = new ArrayList<Map>();
		Map mParam = null;
		for (int i = 0; i < lParam.size(); i++) {
			mParam = lParam.get(i);
			if (mParam.containsKey("aae140") && mParam.get("aae140") != null && !mParam.get("aae140").equals("")) {
				String insur_no = mParam.get("aae140").toString();
				mParam.put("aae140_name", CodetableMapping.getDisplayValue("sblx", insur_no, insur_no));

			}
			if (mParam.containsKey("aac004") && mParam.get("aac004") != null && !mParam.get("aac004").equals("")) {
				String aac004 = mParam.get("aac004").toString();
				mParam.put("aac004_name", CodetableMapping.getDisplayValue("aac004", aac004, aac004));

			}
			if (mParam.containsKey("aae016") && mParam.get("aae016") != null && !mParam.get("aae016").equals("")) {
				String aae016 = mParam.get("aae016").toString();
				mParam.put("aae016_name", CodetableMapping.getDisplayValue("aae016_specail", aae016, aae016));
			}
			if (mParam.containsKey("bka006") && mParam.get("bka006") != null && !mParam.get("bka006").equals("")) {
				String bka006 = mParam.get("bka006").toString();
				mParam.put("bka006_name", CodetableMapping.getDisplayValue("bka006$" + aaa027, bka006, bka006));
			}
			if (mParam.containsKey("bka004") && mParam.get("bka004") != null && !mParam.get("bka004").equals("")) {
				String bka004 = mParam.get("bka004").toString();
				mParam.put("bka004_name", CodetableMapping.getDisplayValue("bka004", bka004, bka004));
			}
			if (mParam.containsKey("aae100") && mParam.get("aae100") != null && !mParam.get("aae100").equals("")) {
				String aae100 = mParam.get("aae100").toString();
				mParam.put("aae100_name", CodetableMapping.getDisplayValue("valid_flag", aae100, aae100));
			}
			retList.add(mParam);
		}
		return retList;
	}

}
