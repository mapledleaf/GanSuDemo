package com.powersi.ssm.biz.medicare.common.util;

import com.powersi.biz.medicare.inhospital.pojo.FundPayInfoDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.pojo.Kcg4DTO;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MissYoung
 * @version 0.1
 * @date 2020-12-21 12:01
 * @description
 */
public class GanSuDemoUtils {
    private GanSuDemoUtils() {

    }

    public static final List<InHospitalDTO> CATALOG_LIST = new ArrayList<>();
    public static final List<InHospitalDTO> PERSON_INFO = new ArrayList<>();
    public static final List<FundPayInfoDTO> FUND_PAY_LIST = new ArrayList<>();
    public static final InHospitalDTO CACHED_DTO = new InHospitalDTO();
    public static final Map<String, InHospitalDTO> BIZ_INFO_MAP = new HashMap<>();
    public static final Map<String, List<InHospitalDTO>> FEE_INFO_MAP = new HashMap<>();

    static {
        InHospitalDTO catalog1 = new InHospitalDTO();

        catalog1.setAkb020("RC0044");
        catalog1.setBkm017("101010101100119");
        catalog1.setBkc194("2020021490000000301");
        catalog1.setBkc109("14474");
        catalog1.setAka065("1");
        catalog1.setAke005("101010101100119");
        catalog1.setAke006("青霉素");
        catalog1.setAke001("101010101100119");
        catalog1.setAke002("青霉素");
        catalog1.setAke003("1");
        catalog1.setAka063("001");
        catalog1.setBka073("");
        catalog1.setBka040(BigDecimal.valueOf(10L));
        catalog1.setAka070("");
        catalog1.setAka074("1");
        catalog1.setBkc106("0");
        catalog1.setAka057("0");
        catalog1.setBke217("A000_00");

        CATALOG_LIST.add(catalog1);

        InHospitalDTO catalog2 = new InHospitalDTO();
        catalog2.setAkb020("RC0044");
        catalog2.setBkm017("86905840000244");
        catalog2.setBkc194("2019010890031669502");
        catalog2.setBkc109("16252");
        catalog2.setAka065("3");
        catalog2.setAke005("107532");
        catalog2.setAke006("青霉素V钾片");
        catalog2.setAke001("X-A12CB-P084-A001");
        catalog2.setAke002("葡萄糖酸锌");
        catalog2.setAke003("1");
        catalog2.setAka063("001");
        catalog2.setBka073("");
        catalog2.setBka040(BigDecimal.valueOf(6));
        catalog2.setAka070("585");
        catalog2.setAka074("70mg");
        catalog2.setBkc106("1");
        catalog2.setAka057("1");
        catalog2.setBke217("A000_30");

        CATALOG_LIST.add(catalog2);

        InHospitalDTO person = new InHospitalDTO();

        person.setAaa027_name("XXX市本级");
        person.setBaa027_name("XXX市本级");
        person.setAac148_name("标准");
        person.setBke301("5");
        person.setAac148("1");
        person.setBkh015(0);
        person.setAae030_special("20200326");
        person.setAae031_special("20201231");
        person.setBacu18("0.00");
        person.setAaf013("17108400");
        person.setAaz070("17209479");
        person.setBkm624("盘龙社区");
        person.setBkm625("宝塔街道");
        person.setNocardreminderflag("0");
        person.setBka333("0");
        person.setAae004("陈坤妍");
        person.setBac001("000");
        person.setAae139("1");
        person.setAac066("396");
        person.setBka889("非正常参保状态终止待遇(4)");
        person.setAkb021_last("中心医院");
        person.setAka121_last("恶性肿瘤门诊放化疗（门诊实质性治疗）");
        person.setAae030_last("20200420");
        person.setAae031_last("20200420");
        person.setBka888("1");
        person.setAac013("9");
        person.setAab301("430399");
        person.setAac008("4");
        person.setAac031("3");
        person.setAae030("20201221");
        person.setAae031("20201221");
        person.setAka101("2");
        person.setAkb020("RC0044");
        person.setAka130("12");
        person.setBaa027("430399");
        person.setAac001("11877907");
        person.setAac003("张某某");
        person.setAac004("2");
        person.setAac002("430303196411220554");
        person.setAkb021("中心医院");
        person.setAac006("20151028");
        person.setAae005("17373278118");
        person.setAab019("99");
        person.setBka006("120");
        person.setBka010("6");
        person.setAaz267("678797");
        person.setAkc193("exzlmzflmzszxzl");
        person.setBkz101("恶性肿瘤门诊放化疗（门诊实质性治疗）");
        person.setAkc196("exzlmzflmzszxzl");
        person.setBkz102("exzlmzflmzszxzl");
        person.setBka035("70");
        person.setAaa027("430399");
        person.setAae140("390");
        person.setBke054("1");
        person.setPageSize(30);
        person.setTotalPage(0);
        person.setTotalResult(0);
        person.setCurrentPage(1);
        person.setCurrentResult(1);
        person.setEntityOrField(false);
        person.setSortName("");
        person.setSortOrder("");

        PERSON_INFO.add(person);

        FundPayInfoDTO fundPayInfoDTO1 = new FundPayInfoDTO();
        FUND_PAY_LIST.add(fundPayInfoDTO1);


        CACHED_DTO.setAkc264("195.00");
        CACHED_DTO.setAka151("0.00");
        CACHED_DTO.setAkb067("195.00");
        CACHED_DTO.setAkb066("0.00");
        CACHED_DTO.setAke039("0.00");
        CACHED_DTO.setAke035("0.00");
        CACHED_DTO.setAke026("0.00");
        CACHED_DTO.setAke029("0.00");
        CACHED_DTO.setBka821("0.00");
        CACHED_DTO.setBka825("0.00");
        CACHED_DTO.setBka826("0.00");
        CACHED_DTO.setBka831("195.00");
        CACHED_DTO.setBka832("0.00");
        CACHED_DTO.setBka838("0.00");
        CACHED_DTO.setBka839("0.00");
        CACHED_DTO.setBka840("0.00");
        CACHED_DTO.setBka841("0.00");
        CACHED_DTO.setBka842("0.00");
        CACHED_DTO.setBka801("0.00");
        CACHED_DTO.setCash_pay_own("0.00");
        CACHED_DTO.setAkb020("RC0044");
        CACHED_DTO.setAaz217("SN20201221");
        CACHED_DTO.setBka843("0.00");
        CACHED_DTO.setBka844("0.00");
        CACHED_DTO.setBka845("0.00");
        CACHED_DTO.setPageSize(30);
        CACHED_DTO.setTotalPage(0);
        CACHED_DTO.setTotalResult(0);
        CACHED_DTO.setCurrentPage(1);
        CACHED_DTO.setCurrentResult(1);
        CACHED_DTO.setEntityOrField(false);
        CACHED_DTO.setSortName("");
        CACHED_DTO.setSortOrder("");
        CACHED_DTO.setBkh015(0);

    }

    public static InHospitalDTO saveRegisterInfo(InHospitalDTO inHospitalDTO) {
        BIZ_INFO_MAP.put("bizInfo", inHospitalDTO);
        return inHospitalDTO;
    }

    public static InHospitalDTO queryAaz217(InHospitalDTO inHospitalDTO) {
        if (MapUtils.isNotEmpty(BIZ_INFO_MAP)) {
            InHospitalDTO resultDTO = BIZ_INFO_MAP.get("bizInfo");
            if (StringUtils.isNotBlank(inHospitalDTO.getAaz217()) && inHospitalDTO.getAaz217().equals(resultDTO.getAaz217())
                    || StringUtils.isNotBlank(inHospitalDTO.getAac002()) && inHospitalDTO.getAac002().equals(resultDTO.getAac002())
                    || StringUtils.isNotBlank(inHospitalDTO.getAkc190()) && inHospitalDTO.getAkc190().equals(resultDTO.getAkc190())) {
                return resultDTO;
            }
        }
        return inHospitalDTO;
    }

    public static InHospitalDTO saveFeeInfo(List<InHospitalDTO> inHospitalDTORows) {
        List<InHospitalDTO> feeInfoList = FEE_INFO_MAP.get("feeInfo");
        if (feeInfoList != null) {
            feeInfoList.addAll(inHospitalDTORows);
        } else {
            FEE_INFO_MAP.put("feeInfo", inHospitalDTORows);
        }
        return new InHospitalDTO();
    }

    public static List<InHospitalDTO> getFeeInfo() {
        return FEE_INFO_MAP.get("feeInfo");
    }

    public static InHospitalDTO tryCheckout(InHospitalDTO inHospitalDTO) {
        return CACHED_DTO;
    }

    public static List<Kcg4DTO> loadDiagnoseInfos() {
        Kcg4DTO kcg4DTO = new Kcg4DTO();
        kcg4DTO.setAka120("00000");
        kcg4DTO.setAka121("普通疾病");
        List<Kcg4DTO> list = new ArrayList<>();
        list.add(kcg4DTO);
        return list;
    }

    public static List<FundPayInfoDTO> queryFundPay(InHospitalDTO inHospitalDTO) {
        return FUND_PAY_LIST;
    }

    public static InHospitalDTO checkout(InHospitalDTO inHospitalDTO) {
        BIZ_INFO_MAP.put("bizInfo", new InHospitalDTO());
        return CACHED_DTO;
    }
}
