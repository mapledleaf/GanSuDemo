package com.powersi.ssm.biz.medicare.common.util;

import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;

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
    private GanSuDemoUtils(){

    }

    public static List<InHospitalDTO> CATALOG_LIST = new ArrayList<>();

    static{
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
    }
}
