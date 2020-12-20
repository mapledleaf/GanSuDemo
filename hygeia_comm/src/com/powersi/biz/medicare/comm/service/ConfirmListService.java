package com.powersi.biz.medicare.comm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MissYoung
 * @version 0.1
 * @description 前台派工单、回款单服务 TS20021100028 结算云增加工单、付款信息线上提示需求 杨世明 20200220
 * @date 2020-02-17 11:47
 */
public interface ConfirmListService {

    /**
     * 查询派工单、回款单内容
     *
     * @param paraMap 入参条件
     * @return 派工单、回款单列表
     */
    List<Map<String, Object>> queryConfirmList(HashMap<String, String> paraMap);

    /**
     * 更新派工单、回款单内容
     *
     * @param aaa027 统筹区编码
     * @param needUpdateNoticeList 需要进行更新的数据
     */
    @SuppressWarnings("rawtypes")
    void updateConfirmList(String aaa027, List<Map> needUpdateNoticeList);
}
