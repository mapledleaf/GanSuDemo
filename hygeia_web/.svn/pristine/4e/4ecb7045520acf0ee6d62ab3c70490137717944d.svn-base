package com.powersi.ssm.biz.medicare.api.service.mcce;

import com.powersi.biz.medicare.diagnose.pojo.DiagnoseInfoDTO;
import com.powersi.biz.medicare.diagnose.service.api.mcce.MCCERemoteBIZC130121Service;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.process;

/**
 * @ClassName MCCERemoteSpecialServiceImpl
 * @Description: NTS20050700380 - 生育、门特业务省内异地联网结算需求 -- 湘潭
 * @Author 李嘉伦 on 2020/5/25
 * @Version V1.0
 **/
@Service
public class MCCERemoteBIZC130121ServiceImpl implements MCCERemoteBIZC130121Service {

    private static final long serialVersionUID = 1L;

    @Override
    public Map getPersonInfo_BIZC130121(DiagnoseInfoDTO diagnoseInfoDTO) {
        Object[] paramObj = new Object[] {diagnoseInfoDTO};
        return process("mcce_Remote_BIZC130121", "getPersonInfo_BIZC130121", paramObj);
    }
}
