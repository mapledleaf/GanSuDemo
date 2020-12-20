package com.powersi.ssm.biz.medicare.common.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powersi.biz.medicare.iccard.service.CheckCardService;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.bean.BeanService;
import com.powersi.biz.medicare.inhospital.service.date.DateService;
import com.powersi.biz.medicare.inhospital.service.util.IdcardValidatorService;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;

@Service
public class QueryStringServiceImpl implements QueryStringService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IdcardValidatorService idcardValidatorService;
	@Autowired
	private DateService dateService;
	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;
	@Autowired
	private BeanService beanService;

	@Override
	public boolean isValidatedaaz217(String aaz217) {
		// 就医登记号=医院编码(6位流水号)+8位年月日+6位流水号
		if (StringUtils.isNotBlank(aaz217) && aaz217.length() == 20) {
			String lsyyyyMMdd = aaz217.substring(6, 14);
			boolean lbdateFormate = this.dateService.isValidDate(lsyyyyMMdd, DateService.yyyyMMdd);
			if (lbdateFormate) {
				if (BizHelper.getAkb020().equals(aaz217.substring(0, 6))) {
					return true;
				} else if (lsyyyyMMdd.compareTo("20170101") > 0) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean isValidatedAllIdcard(String idcard) {
		if (StringUtils.isNotBlank(idcard)) {
			return this.idcardValidatorService.isValidatedAllIdcard(idcard);
		}
		return false;
	}

	@Override
	public boolean isValidatedAac001(String aac001) {
		boolean lbReturn = false;
		if (StringUtils.isNotBlank(aac001) && StringUtils.isNumeric(aac001)) {
			if (aac001.length() < 10) {
				lbReturn = false;
			} else if (aac001.length() == 10) {
				lbReturn = true;
			} else if (aac001.length() == 15) {
				if (aac001.indexOf("0000") != -1) {
					lbReturn = true;
				} else if (!this.idcardValidatorService.is15Idcard(aac001)) {
					lbReturn = true;
				}
			} else if (aac001.length() == 18) {
				if (aac001.indexOf("00000") != -1) {
					lbReturn = true;
				} else if (!this.idcardValidatorService.is18Idcard(aac001)) {
					lbReturn = true;
				}
			} else {
				if (aac001.indexOf("00000") != -1) {
					lbReturn = true;
				}
			}
			if (this.isValidatedaaz217(aac001)) {
				lbReturn = false;
			}
		}
		return lbReturn;
	}

	@Override
	public boolean isValidatedBka100(String bka100) {
		// 待定
		return false;
	}

	@Override
	public void convertQueryString(InHospitalDTO inHospitalDTO) {
		// 读卡
//		if (StringUtils.isNotBlank(inHospitalDTO.getBke548())) {
//			this.convertQueryStringByReadCardBase(inHospitalDTO);
//			return;
//		}
		// 非读卡
		if (StringUtils.isBlank(inHospitalDTO.getQuerystring())) {
			return;
		}
		// NTS20042700215  NTS20042700216 医保电子凭证优化 杨世明 20200427
		if (inHospitalDTO.getQuerystring().length() > 30) {
			throw new HygeiaException("您输入的查询条件位数过多(" + inHospitalDTO.getQuerystring().length() + "/30)");
		}
		
		String argName = inHospitalDTO.getArgName();
		if("aac001".equals(argName)) {
			inHospitalDTO.setAac001(inHospitalDTO.getQuerystring());
		}else if("aac002".equals(argName)) {
			inHospitalDTO.setAac002(inHospitalDTO.getQuerystring());
		}else if("akc190".equals(argName)) {
			inHospitalDTO.setAkc190(inHospitalDTO.getQuerystring());
		}else if("aaz217".equals(argName)) {
			inHospitalDTO.setAaz217(inHospitalDTO.getQuerystring());
		}
	}

	/**
	 * 
	 * @param inHospitalDTO
	 */
	private void convertQueryStringByReadCardBase(InHospitalDTO inHospitalDTO) {
		CheckCardService checkCardService = this.hygeiaServiceManager.getBeanByClass(CheckCardService.class,
				inHospitalDTO.getAkb020());
		InHospitalDTO inHospitalDTOReturn = checkCardService.convertQueryStringByReadCardBase(inHospitalDTO);
		this.beanService.copyProperties(inHospitalDTOReturn, inHospitalDTO, true);
	}

}
