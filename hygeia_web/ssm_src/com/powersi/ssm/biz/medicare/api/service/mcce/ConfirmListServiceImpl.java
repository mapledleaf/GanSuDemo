package com.powersi.ssm.biz.medicare.api.service.mcce;

import com.powersi.biz.medicare.comm.service.ConfirmListService;
import com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MissYoung
 * @version 0.1
 * @description TS20021100028 结算云增加工单、付款信息线上提示需求 杨世明 20200220
 * @date 2020-02-17 11:56
 */
@Service
public class ConfirmListServiceImpl implements ConfirmListService {
    @Override
    public List<Map<String, Object>> queryConfirmList(HashMap<String, String> paraMap) {
        Object[] paramObj = new Object[]{paraMap};
        return ApiRemoteCallWebProcessor.process("esbConfirmListServiceImpl", "queryConfirmList", paramObj);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public void updateConfirmList(String aaa027, List<Map> needUpdateNoticeList) {
        Object[] paramObj = new Object[]{aaa027, needUpdateNoticeList};
        ApiRemoteCallWebProcessor.process("esbConfirmListServiceImpl", "updateConfirmList", paramObj);
    }
}
